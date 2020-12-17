import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
			response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			
			HttpSession session=request.getSession();
			
			utility.printHtml("Head.html");
			utility.printHeader();
			utility.printLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>My Account</h1>");
			pw.write("</div>");
						
			pw.print("<table class='gridtable'>");
			
			pw.print("<tr>");
			pw.print("<td> UserID: </td>");
			pw.print("<td>" +utility.username()+ "</td>");
			pw.print("</tr>");
			
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +utility.usertype()+ "</td>");
			pw.print("</tr>");
			
			HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getAllOrders();
			HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
			
			
			if(orders != null && products != null){
				int my_order_size = 0;
				for(Map.Entry<Integer, ArrayList<OrderItem>> entry : orders.entrySet())
					{
						if(entry.getValue().get(0).userid.equals(utility.username()))
							my_order_size += 1;
					}
				
				if(my_order_size>0)
					{
						pw.print("<tr>");
						pw.print("<td>OrderId</td>");
						pw.print("<td>Customer Name</td>");
						pw.print("<td>Product</td>");
						pw.print("<td>PurchaseDate</td>");
						pw.print("<td>ShipDate</td>");
						pw.print("<td>TotalSale</td>");
						pw.print("<td>D/P</td>");
						pw.print("<td>Address</td>");
						pw.print("<td>IsCancelled</td>");
						pw.print("</tr>");
						for(Map.Entry<Integer, ArrayList<OrderItem>> entry : orders.entrySet()){
							for(OrderItem oi:entry.getValue()){
								if(oi.userid.equals(utility.username())) 
								{
									String address = "";
									if(oi.storeid==0){
										address = oi.useraddress;
									}else{
										address = oi.storeaddress;
									}
									pw.print("<tr>");
									pw.print("<td>"+oi.orderid+"</td>");
									pw.print("<td>"+oi.username+"</td>");
									pw.print("<td>"+products.get(oi.productid).getName()+"</td>");
									pw.print("<td>"+oi.purchasedate+"</td>");
									pw.print("<td>"+oi.shipdate+"</td>");
									pw.print("<td>"+oi.totalsales+"</td>");
									pw.print("<td>"+(oi.storeid!=0 ? "Pickup" : "Delivery")+"</td>");
									pw.print("<td>"+address+"</td>");
									pw.print("<td>"+oi.iscancelled+"</td>");
									pw.print("</tr>");
								}
							}
						}
					}
					else
					{
						pw.print("<h4 style='color:red'>You have not placed any order yet.</h4>");
					}
			}else{
				pw.write("<h1 class='w3-hide-small'>MySQL server is not up and running.</h1>");
			}
			
			
			pw.print("</table>");
			
			
			pw.write("</div>");
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}
}
