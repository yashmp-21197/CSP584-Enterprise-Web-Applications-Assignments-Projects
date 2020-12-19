import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.*;
import java.text.*;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
	String error_msg = null;
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String delivery_option = request.getParameter("delivery_option");
		
		HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getAllOrders();;
		HashMap<Integer,String> storeloc = MySqlDataStoreUtilities.getStoreLocations();
		HashMap<Integer,StoreLocation> storelocobjs = MySqlDataStoreUtilities.getStoreLocationObjs();
		HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
		HashMap<String, Double> avg_reviews = MongoDBDataStoreUtilities.getAvgRating();
		
		if(orders != null && storeloc != null && products != null){
			
			if(delivery_option.equalsIgnoreCase("Home Delivery")){
				
				String customername = request.getParameter("customername");
				String customeroccupation = request.getParameter("customeroccupation");
				String customerage = request.getParameter("customerage");
				String creditCardNo=request.getParameter("creditCardNo");
				String street=request.getParameter("street");
				String city=request.getParameter("city");
				String state=request.getParameter("state");
				String zip=request.getParameter("zip");
				
				if(street != null && city != null && state != null && zip != null && creditCardNo != null && customerage != null && customeroccupation != null && customername != null && !customername.isEmpty() && !customerage.isEmpty() && !customeroccupation.isEmpty() && !street.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zip.isEmpty() && !creditCardNo.isEmpty()){

					Random ran = new Random();
					
					String userAddress = street +", "+ city +", "+ state +", "+ zip;
					int orderId = orders.size() + 1;
					int age = Integer.parseInt(customerage);
					
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					Calendar c1 = Calendar.getInstance();
					Calendar c2 = Calendar.getInstance();
					String purchase_date = sdf.format(c1.getTime());
					c1.add(Calendar.DATE, 14);
					String ship_date = sdf.format(c1.getTime());
					c2.add(Calendar.DATE, 14 + ran.nextInt(2));
					String actual_ship_date = sdf.format(c2.getTime());

					boolean ret = true;
					for (Map.Entry<String, Integer> entry: utility.getCustomerOrders().entrySet())
					{
						String pid = entry.getKey();
						int count = entry.getValue();
						Product p = products.get(pid);
						double shipping_cost = 5.0;
						
						String userid = utility.username();
						String username = customername;
						String useroccupation = customeroccupation;
						int userage = age;
						String useraddress = userAddress;
						String creditcardno = creditCardNo;
						int orderid = orderId;
						String purchasedate = purchase_date;
						String shipdate = ship_date;
						String actualshipdate = actual_ship_date;
						String productid = pid;
						String productname = p.getName();
						String producttype = p.getType();
						String productmanufacturer = p.getManufacturer();
						int productquantity = count;
						double productprice = p.getPrice();
						double shippingcost = shipping_cost;
						double productdiscount = p.getDiscount();
						double totalsales = count*(shippingcost + (p.getPrice()*(1.0-(p.getDiscount()/100.0))));
						double reviewrating = avg_reviews.getOrDefault(pid, 0.0);
						String trackingid = "xyz"+orderId;
						int storeid = 0;
						String storeaddress = "";
						String zipcode = zip;
						String transactionstatus = ran.nextBoolean() ? "Approved" : "disputed";
						boolean iscancelled = false;
						boolean isreturned = ran.nextBoolean();
						boolean isdeliveredontime = ship_date.equalsIgnoreCase(actual_ship_date);
						
						OrderItem oi = new OrderItem(userid,username,useroccupation,userage,useraddress,creditcardno,orderid,purchasedate,shipdate,actualshipdate,productid,productname,producttype,productmanufacturer,productquantity,productprice,shippingcost,productdiscount,totalsales,reviewrating,trackingid,storeid,storeaddress,zipcode,transactionstatus,iscancelled,isreturned,isdeliveredontime);
						ret = ret & MySqlDataStoreUtilities.insertOrderItem(oi);
						
						Product up = new Product(p.getId(), p.getType(), p.getName(), p.getPrice(), p.getImage(), p.getManufacturer(), p.getCondition(), p.getDiscount(), p.getOnsale(), p.getCount() - count);
						ret = ret & MySqlDataStoreUtilities.updateProduct(up);
					}

					if(ret==false){
						error_msg = "MySQL server is not up and running.";
					}else
						CartItem.items.remove(utility.username());
					
					if(error_msg == null){
						utility.printHtml("Head.html");
						utility.printHeader();
						utility.printLeftNavBar();
						utility.printHtml("LeftNavigationBar.html");
			
						pw.write("<div class='w3-main' style='margin-left:250px'>");
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>Payment successful</h1>");
						pw.print("<h3>Your Order is stored");
						pw.print("<br>Your Order Number is "+(orderId));
						pw.print("<br>Your Order will be reached at your given address("+userAddress+") on "+ship_date+"</h3>");
						pw.write("</div>");
						
						pw.print("</div>");
						utility.printHtml("Footer.html");
					}else{
						utility.printHtml("Head.html");
						utility.printHeader();
						utility.printLeftNavBar();
						utility.printHtml("LeftNavigationBar.html");
			
						pw.write("<div class='w3-main' style='margin-left:250px'>");
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>"+error_msg+"</h1>");
						pw.write("</div>");
						
						pw.print("</div>");
						utility.printHtml("Footer.html");
					}
					
				}else{
					utility.printHtml("Head.html");
					utility.printHeader();
					utility.printLeftNavBar();
					utility.printHtml("LeftNavigationBar.html");
			
					pw.write("<div class='w3-main' style='margin-left:250px'>");
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.write("<h1 class='w3-hide-small'>Payment Error</h1>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
					pw.write("</div>");
					
					pw.print("</div>");
					utility.printHtml("Footer.html");
				}
				
			}else if(delivery_option.equalsIgnoreCase("In-Store Pickup")){
				
				String customername = request.getParameter("customername");
				String customeroccupation = request.getParameter("customeroccupation");
				String customerage = request.getParameter("customerage");
				String creditCardNo=request.getParameter("creditCardNo");
				int pickup_location = Integer.parseInt(request.getParameter("pickup_location"));
				
				if(customername != null && !customername.isEmpty() && customerage != null && !customerage.isEmpty() && customeroccupation != null && !customeroccupation.isEmpty() && creditCardNo != null && !creditCardNo.isEmpty() && pickup_location != 0){
					
					Random ran = new Random();
					
					String storeAddress = storeloc.get(pickup_location);
					String zip = storelocobjs.get(pickup_location).getZip();
					int orderId = orders.size() + 1;
					int age = Integer.parseInt(customerage);
					
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					Calendar c1 = Calendar.getInstance();
					Calendar c2 = Calendar.getInstance();
					String purchase_date = sdf.format(c1.getTime());
					c1.add(Calendar.DATE, 14);
					String ship_date = sdf.format(c1.getTime());
					c2.add(Calendar.DATE, 14 + ran.nextInt(2));
					String actual_ship_date = sdf.format(c2.getTime());

					boolean ret = true;
					for (Map.Entry<String, Integer> entry: utility.getCustomerOrders().entrySet())
					{
						
						String pid = entry.getKey();
						int count = entry.getValue();
						Product p = products.get(pid);
						double shipping_cost = 5.0;
						
						String userid = utility.username();
						String username = customername;
						String useroccupation = customeroccupation;
						int userage = age;
						String useraddress = "";
						String creditcardno = creditCardNo;
						int orderid = orderId;
						String purchasedate = purchase_date;
						String shipdate = ship_date;
						String actualshipdate = actual_ship_date;
						String productid = pid;
						String productname = p.getName();
						String producttype = p.getType();
						String productmanufacturer = p.getManufacturer();
						int productquantity = count;
						double productprice = p.getPrice();
						double shippingcost = shipping_cost;
						double productdiscount = p.getDiscount();
						double totalsales = count*(shippingcost + (p.getPrice()*(1.0-(p.getDiscount()/100.0))));
						double reviewrating = avg_reviews.getOrDefault(pid, 0.0);
						String trackingid = "xyz"+orderId;
						int storeid = pickup_location;
						String storeaddress = storeAddress;
						String zipcode = zip;
						String transactionstatus = ran.nextBoolean() ? "Approved" : "disputed";
						boolean iscancelled = false;
						boolean isreturned = ran.nextBoolean();
						boolean isdeliveredontime = ship_date.equalsIgnoreCase(actual_ship_date);
						
						OrderItem oi = new OrderItem(userid,username,useroccupation,userage,useraddress,creditcardno,orderid,purchasedate,shipdate,actualshipdate,productid,productname,producttype,productmanufacturer,productquantity,productprice,shippingcost,productdiscount,totalsales,reviewrating,trackingid,storeid,storeaddress,zipcode,transactionstatus,iscancelled,isreturned,isdeliveredontime);
						ret = ret & MySqlDataStoreUtilities.insertOrderItem(oi);
						
						Product up = new Product(p.getId(), p.getType(), p.getName(), p.getPrice(), p.getImage(), p.getManufacturer(), p.getCondition(), p.getDiscount(), p.getOnsale(), p.getCount() - count);
						ret = ret & MySqlDataStoreUtilities.updateProduct(up);
					}
					
					if(ret==false){
						error_msg = "MySQL server is not up and running.";
					}else
						CartItem.items.remove(utility.username());
					
					if(error_msg == null){
						utility.printHtml("Head.html");
						utility.printHeader();
						utility.printLeftNavBar();
						utility.printHtml("LeftNavigationBar.html");

						pw.write("<div class='w3-main' style='margin-left:250px'>");
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>Payment successful</h1>");
						pw.print("<h3>Your Order is stored");
						pw.print("<br>Your Order Number is "+(orderId));
						pw.print("<br>You can pick your Order from pickup store("+storeAddress+") on "+ship_date+"</h3>");
						pw.write("</div>");
						
						pw.print("</div>");
						utility.printHtml("Footer.html");
					}else{
						utility.printHtml("Head.html");
						utility.printHeader();
						utility.printLeftNavBar();
						utility.printHtml("LeftNavigationBar.html");

						pw.write("<div class='w3-main' style='margin-left:250px'>");
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>"+error_msg+"</h1>");
						pw.write("</div>");
						
						pw.print("</div>");
						utility.printHtml("Footer.html");
					}
				
				}else{
					utility.printHtml("Head.html");
					utility.printHeader();
					utility.printLeftNavBar();
					utility.printHtml("LeftNavigationBar.html");

					pw.write("<div class='w3-main' style='margin-left:250px'>");
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.write("<h1 class='w3-hide-small'>Payment Error</h1>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
					pw.write("</div>");
					
					pw.print("</div>");
					utility.printHtml("Footer.html");
				}
				
			}
			
		}else{
			utility.printHtml("Head.html");
			utility.printHeader();
			utility.printLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h4 class='w3-hide-small'>MySQL server is not up and running.</h4>");
			pw.write("</div>");

			pw.print("</div>");
			utility.printHtml("Footer.html");
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
