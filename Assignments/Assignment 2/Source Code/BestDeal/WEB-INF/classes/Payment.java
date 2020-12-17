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
		
		HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getOrders();;
		HashMap<Integer,String> storeloc = MySqlDataStoreUtilities.getStoreLocations();
		HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
		
		if(orders != null && storeloc != null && products != null){
			
			if(delivery_option.equalsIgnoreCase("Home Delivery")){
				String customername = request.getParameter("customername");
				String creditCardNo=request.getParameter("creditCardNo");
				String street=request.getParameter("street");
				String city=request.getParameter("city");
				String state=request.getParameter("state");
				String zip=request.getParameter("zip");
				
				if(street != null && city != null && state != null && zip != null && creditCardNo != null && customername != null && !customername.isEmpty() && !street.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zip.isEmpty() && !creditCardNo.isEmpty()){
					String userAddress = street +", "+ city +", "+ state +", "+ zip;
					
					int orderId = orders.size() + 1;
					
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					Calendar c = Calendar.getInstance();
					String purchasedate = sdf.format(c.getTime());
					c.add(Calendar.DATE, 14);
					String shipdate = sdf.format(c.getTime());

					boolean ret = true;
					for (Map.Entry<String, Integer> entry: utility.getCustomerOrders().entrySet())
					{
						String pid = entry.getKey();
						int count = entry.getValue();
						Product p = products.get(pid);
						double shippingcost = 5.0;
						
						OrderItem oi = new OrderItem(utility.username(),customername,userAddress,creditCardNo,orderId,purchasedate,shipdate,pid,p.getType(),count,p.getPrice(),shippingcost,p.getDiscount(),count*(shippingcost + (p.getPrice()*(1.0-(p.getDiscount()/100.0)))),0,"",false);
						ret = ret & MySqlDataStoreUtilities.insertOrderItem(oi);
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
						pw.print("<br>Your Order will be reached at your given address("+userAddress+") on "+shipdate+"</h3>");
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
				String creditCardNo=request.getParameter("creditCardNo");
				int pickup_location = Integer.parseInt(request.getParameter("pickup_location"));
				
				if(customername != null && !customername.isEmpty() && creditCardNo != null && !creditCardNo.isEmpty() && pickup_location != 0){
					
					String storeAddress = storeloc.get(pickup_location);
					
					int orderId = orders.size() + 1;
					
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					Calendar c = Calendar.getInstance();
					String purchasedate = sdf.format(c.getTime());
					c.add(Calendar.DATE, 14);
					String shipdate = sdf.format(c.getTime());

					boolean ret = true;
					for (Map.Entry<String, Integer> entry: utility.getCustomerOrders().entrySet())
					{
						String pid = entry.getKey();
						int count = entry.getValue();
						Product p = products.get(pid);
						double shippingcost = 5.0;
						
						OrderItem oi = new OrderItem(utility.username(),customername,"",creditCardNo,orderId,purchasedate,shipdate,pid,p.getType(),count,p.getPrice(),shippingcost,p.getDiscount(),count*(shippingcost + (p.getPrice()*(1.0-(p.getDiscount()/100.0)))),pickup_location,storeAddress,false);
						ret = ret & MySqlDataStoreUtilities.insertOrderItem(oi);
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
						pw.print("<br>You can pick your Order from pickup store("+storeAddress+") on "+shipdate+"</h3>");
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
