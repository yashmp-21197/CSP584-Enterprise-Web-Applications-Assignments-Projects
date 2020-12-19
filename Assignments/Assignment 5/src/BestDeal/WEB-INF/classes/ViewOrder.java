import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);

		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		
		String username=utility.username();
		
		utility.printHtml("Head.html");
		utility.printHeader();
		utility.printLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
			
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>Search Order</h1>");
		pw.write("</div>");
		
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
	
		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter Order Number : &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy w3-button w3-black'></td></tr></table>");
		}else if(request.getParameter("Order")!=null && request.getParameter("Order").equalsIgnoreCase("ViewOrder"))
		{
			HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getAllOrders();
			HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
			
			if(orders!=null && products!=null){
				if (request.getParameter("orderId") != null && !request.getParameter("orderId").equals(""))
				{	
					int orderId=Integer.parseInt(request.getParameter("orderId"));
					pw.print("<input type='hidden' name='orderid' value='"+orderId+"'>");
					
					int my_order_size=0;
					
					if(orders.get(orderId)!=null)
					{
						for(OrderItem oi : orders.get(orderId)){
							if(oi.userid.equals(username) && !oi.iscancelled)
								my_order_size+=1;
						}
					}

					if(my_order_size>0)
					{	
						pw.print("<table class='gridtable'>");
						pw.print("<tr><td></td>");
						pw.print("<td>OrderId</td>");
						pw.print("<td>Customer Name</td>");
						pw.print("<td>Product</td>");
						pw.print("<td>PurchaseDate</td>");
						pw.print("<td>ShipDate</td>");
						pw.print("<td>TotalSale</td>");
						pw.print("<td>D/P</td>");
						pw.print("<td>Address</td>");
						pw.print("</tr>");
						for (OrderItem oi : orders.get(orderId)) 
						{
							if(oi.userid.equals(username) && !oi.iscancelled){
								String address = "";
								if(oi.storeid==0){
									address = oi.useraddress;
								}else{
									address = oi.storeaddress;
								}
									
								pw.print("<tr>");
								pw.print("<td><input type='radio' name='productid' value='"+oi.productid+"'></td>");
								pw.print("<td>"+oi.orderid+"</td>");
								pw.print("<td>"+oi.username+"</td>");
								pw.print("<td>"+products.get(oi.productid).getName()+"</td>");
								pw.print("<td>"+oi.purchasedate+"</td>");
								pw.print("<td>"+oi.shipdate+"</td>");
								pw.print("<td>"+oi.totalsales+"</td>");
								pw.print("<td>"+(oi.storeid!=0 ? "Pickup" : "Delivery")+"</td>");
								pw.print("<td>"+address+"</td>");
								pw.print("</tr>");
							}
						}
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy w3-button w3-black'></td>");
						pw.print("</table>");
					}
					else
					{
						pw.print("<h4 style='color:red'>You have not placed any order with this Order Number.</h4>");
					}
				}else
					
				{
					pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");	
				}
			}else{
				pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
			}
		}else if(request.getParameter("Order")!=null && request.getParameter("Order").equalsIgnoreCase("CancelOrder"))
		{
			
			HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getAllOrders();
			HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
			
			if(orders!=null && products != null){
				String oid = request.getParameter("orderid");
				String productid = request.getParameter("productid");
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				
				if(oid != null && productid != null && !oid.isEmpty() && !productid.isEmpty())
				{
					int orderid = Integer.parseInt(oid);
					
					System.out.println(orderid + "  " + productid);
					
					for(OrderItem oi : orders.get(orderid)) 
						{
								if(oi.userid.equals(username) && oi.productid.equals(productid) && !oi.iscancelled)
								{
									Product p = products.get(productid);
									try{
										String shipdate = oi.shipdate;
										Date cDate = Calendar.getInstance().getTime();
										Date sDate = sdf.parse(shipdate);
										
										long diffInMillies = Math.abs(sDate.getTime() - cDate.getTime());
										long days_diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
																			
										if(days_diff > 5){
											p.setCount(p.getCount() + oi.productquantity);
											boolean ret = MySqlDataStoreUtilities.updateProduct(p);
											ret = ret & MySqlDataStoreUtilities.cancelOrderItem(oi.orderid, oi.productid);
											if(ret==true)
												pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
											else
												pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
										}else if(days_diff >= 0){
											pw.print("<h4 style='color:red'>You cannot cancel order in five days before delivery/pickup</h4>");
										}else{
											pw.print("<h4 style='color:red'>You cannot cancel delivered/pickedup order</h4>");
										}		
									}catch(Exception e){}
								}
						}
				}else
				{
					pw.print("<h4 style='color:red'>Please select product</h4>");
				}
			}else{
				pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
			}
		}
		
		pw.print("</form>");
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}

}


