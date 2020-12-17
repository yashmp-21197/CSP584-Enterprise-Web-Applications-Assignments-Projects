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
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>My Account</h1>");
			pw.write("</div>");
			
			User user=utility.getUser();
			
			pw.print("<table class='gridtable'>");
			
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
				{
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
			catch(Exception e)
				{
			
				}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	
						if(od.getUserName().equals(user.getName()))
							size= size+1;
				}
				
			if(size>0)
				{	
					
					pw.print("<tr><td></td>");
					pw.print("<td>Id</td>");
					pw.print("<td>User</td>");
					pw.print("<td>Product</td>");
					pw.print("<td>Price</td>");
					pw.print("<td>Delivery/PickUp</td>");
					pw.print("<td>Address</td>");
					pw.print("<td>Delivery/Pickup Date</td>");
					pw.print("<td>Status</td></tr>");
					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{
						for(OrderPayment oi:entry.getValue())
						if(oi.getUserName().equals(user.getName())) 
						{
							pw.print("<form method='get' action='ViewOrder'>");
							pw.print("<tr>");			
							pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>$"+oi.getOrderPrice()+"</td><td>"+oi.getDelivery_option()+"</td><td>"+oi.getAddress()+"</td><td>"+oi.getDelivery_pickup_date()+"</td><td>"+oi.getDelivery_status()+"<td>");
							pw.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy  w3-button w3-black'></td>");
							pw.print("</tr>");
							pw.print("</form>");
						}
					
					}
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order yet.</h4>");
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
