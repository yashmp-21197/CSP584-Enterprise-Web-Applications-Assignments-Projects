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

@WebServlet("/ManageCustomer")

public class ManageCustomer extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		displayManageCustomer(request, response);
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		displayManageCustomer(request, response);
	}
	
	private void displayManageCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Head.html");
		utility.printSmHeader();
		utility.printSmLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("order")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Manage Customer Orders</h1>");
			pw.write("</div>");
			
			String action1 = request.getParameter("action1");
			
			if(action1 == null){
				pw.print("<form name ='ManageCustomer' action='ManageCustomer' method='get'>");
				pw.print("<input type='hidden' name='action' value='order'><input type='hidden' name='action1' value='Search Order'>");
				pw.print("<table align='center'><tr><td>Enter Order Number : &nbsp&nbsp<input name='order_id' type='text' required></td>");
				pw.print("<td><input type='submit' name='search_order' value='Search Order' class='btnbuy w3-button w3-black'></td></tr></table>");
				pw.print("</form>");
			}else if(action1.equalsIgnoreCase("search order")){
				HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getOrders();
				HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
				
				if(orders!=null && products!=null){
					
					String order_id = request.getParameter("order_id");
					
					if(order_id != null && !order_id.isEmpty()){
						int oid = Integer.parseInt(order_id);
						
						if(orders.containsKey(oid)){
							pw.print("<form name ='ManageCustomer' action='ManageCustomer' method='get'>");
							pw.print("<input type='hidden' name='action' value='order'><input type='hidden' name='order_id' value='"+order_id+"'>");
							pw.print("<table class='gridtable'>");
							pw.print("<tr><td>Cancel</td><td>Update</td>");
							pw.print("<td>OrderId</td>");
							pw.print("<td>Customer Name</td>");
							pw.print("<td>Product</td>");
							pw.print("<td>Quentity</td>");
							pw.print("<td>PurchaseDate</td>");
							pw.print("<td>ShipDate</td>");
							pw.print("<td>TotalSale</td>");
							pw.print("<td>D/P</td>");
							pw.print("<td>Address</td>");
							pw.print("<td>IsCancelled</td>");
							pw.print("</tr>");
							for (OrderItem oi : orders.get(oid)) 
							{
								pw.print("<tr>");
								pw.print("<input type='hidden' name='action' value='order'><input type='hidden' name='order_id' value='"+order_id+"'>");
								if(!oi.iscancelled)
									pw.print("<td><input type='radio' name='productid' value='"+oi.productid+"'></td>");
								else
									pw.print("<td></td>");
								pw.print("<form name ='ManageCustomer' action='ManageCustomer' method='get'>");
								pw.print("<input type='hidden' name='action' value='order'><input type='hidden' name='order_id' value='"+order_id+"'>");
								String address = "";
								if(oi.storeid==0){
									address = oi.useraddress;
								}else{
									address = oi.storeaddress;
								}		
								if(!oi.iscancelled)
									pw.print("<td><input type='radio' name='productid' value='"+oi.productid+"'></td>");
								else
									pw.print("<td></td>");
								pw.print("<td>"+oi.orderid+"</td>");
								pw.print("<td>"+oi.username+"</td>");
								pw.print("<td>"+products.get(oi.productid).getName()+"</td>");
								pw.print("<td><input type='number' min='0' max='10' name='productquantity' value='"+oi.productquantity+"'></td>");
								pw.print("<td>"+oi.purchasedate+"</td>");
								pw.print("<td>"+oi.shipdate+"</td>");
								pw.print("<td>"+oi.totalsales+"</td>");
								pw.print("<td>"+(oi.storeid!=0 ? "Pickup" : "Delivery")+"</td>");
								pw.print("<td>"+address+"</td>");
								pw.print("<td>"+oi.iscancelled+"</td>");
								if(!oi.iscancelled)
									pw.print("<td><input type='submit' name='action1' value='Update Order' class='btnbuy w3-button w3-black'></input></td>");
								pw.print("</form>");
								pw.print("</tr>");
							}
							pw.print("<td><input type='submit' name='action1' value='Cancel Order' class='btnbuy w3-button w3-black'></input></td>");
							pw.print("</table>");
							pw.print("</form>");
						}else{
							pw.print("<h4 style='color:red'>There is no order placed with this order id.</h4>");
						}
						
					}else{
						pw.print("<h4 style='color:red'>Please enter valid information.</h4>");
					}
					
				}else{
					pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
				}
			}else if(action1.equalsIgnoreCase("cancel order")){
				HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getOrders();
				
				if(orders!=null){
					String oid = request.getParameter("order_id");
					String productid = request.getParameter("productid");
					
					if(oid != null && productid != null && !oid.isEmpty() && !productid.isEmpty())
					{
						int orderid = Integer.parseInt(oid);
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
												
						for(OrderItem oi : orders.get(orderid)) 
							{
									if(oi.productid.equals(productid) && !oi.iscancelled)
									{
										try{
											String shipdate = oi.shipdate;
											Date cDate = Calendar.getInstance().getTime();
											Date sDate = sdf.parse(shipdate);
											
											long diffInMillies = Math.abs(sDate.getTime() - cDate.getTime());
											long days_diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
																				
											if(days_diff >= 0){
												boolean ret = MySqlDataStoreUtilities.cancelOrderItem(oi.orderid, oi.productid);
												if(ret==true)
													pw.print("<h4 style='color:red'>Order is Cancelled.</h4>");
												else
													pw.print("<h4 style='color:red'>Order Cancellation Error.</h4>");
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
			}else if(action1.equalsIgnoreCase("update order")){
				HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getOrders();
				
				if(orders!=null){
					String oid = request.getParameter("order_id");
					String productid = request.getParameter("productid");
					String pquantity = request.getParameter("productquantity");
					
					if(oid != null && productid != null && pquantity!=null && !oid.isEmpty() && !productid.isEmpty() && !pquantity.isEmpty())
					{
						int orderid = Integer.parseInt(oid);
						int productquantity = Integer.parseInt(pquantity);
						
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
												
						for(OrderItem oi : orders.get(orderid)) 
							{
									if(oi.productid.equals(productid) && !oi.iscancelled)
									{
										try{
											String shipdate = oi.shipdate;
											Date cDate = Calendar.getInstance().getTime();
											Date sDate = sdf.parse(shipdate);
											
											long diffInMillies = Math.abs(sDate.getTime() - cDate.getTime());
											long days_diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
																				
											if(days_diff >= 0){
												boolean ret = MySqlDataStoreUtilities.updateOrderItem(oi.orderid, oi.productid, productquantity);
												if(ret==true)
													pw.print("<h4 style='color:red'>Order is Updated.</h4>");
												else
													pw.print("<h4 style='color:red'>Order Update Error.</h4>");
											}else{
												pw.print("<h4 style='color:red'>You cannot update delivered/pickedup order</h4>");
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
			
		}else if(action.equalsIgnoreCase("account")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Create Customer Account</h1>");
			pw.write("</div>");
			
			String action1 = request.getParameter("action1");
			
			if(action1 != null && action1.equalsIgnoreCase("create")){
								
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String repassword = request.getParameter("repassword");
				String usertype = request.getParameter("usertype");
				
				if(
					username!=null &&
					password!=null &&
					repassword!=null &&
					usertype!=null &&
					!username.isEmpty() &&
					!password.isEmpty() &&
					!repassword.isEmpty() &&
					!usertype.isEmpty()
				){
					
					if(password.equals(repassword)){
						
						HashMap<String, User> hm = MySqlDataStoreUtilities.getUsers();
						
						if(hm!=null){
							
							if(hm.containsKey(username))
								pw.write("<h4 style='color:red'>Username("+username+") already exist.</h4>");
							else
							{
								User user = new User(username,password,usertype);
								hm.put(username, user);
								boolean ret = MySqlDataStoreUtilities.insertUser(username, password, repassword, usertype);
								if(ret==true){
									pw.print("<h4 style='color:red'>Customer account has been created with username("+username+").</h4>");
								}else{
									pw.print("<h4 style='color:red'>Error in creating customer account.</h4>");
								}
							}
							
						}else{
							pw.write("<h4 style='color:red'>MySQL server is not up and running.</h4>");
						}
						
					}else{
						pw.write("<h4 style='color:red'>Password doesn't match!</h4>");
					}
					
				}else{
					pw.write("<h4 style='color:red'>Please enter valid information.</h4>");
				}
			}
				pw.write("<div><form action='ManageCustomer' name='ManageCustomer' method='post'>");
				pw.write("<input type='hidden' name='action' value='account'>");
				pw.write("<input type='hidden' name='action1' value='create'>");
				pw.write("<input type='hidden' name='usertype' value='customer'>");
				pw.write("<table>");
				pw.write("<tr><td>Username : </td><td><input type='text' name='username' value='' required></td></tr>");
				pw.write("<tr><td>Password : </td><td><input type='password' name='password' value='' required></td></tr>");
				pw.write("<tr><td>Re-Password : </td><td><input type='password' name='repassword' value='' required></td></tr>");
				pw.write("<tr><td></td><td><input type='submit' name='create_acount' value='Create Account' class='btnbuy w3-button w3-black'></td></tr>");
				pw.write("</table>");
				pw.write("</form></div>");
		}
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}
}