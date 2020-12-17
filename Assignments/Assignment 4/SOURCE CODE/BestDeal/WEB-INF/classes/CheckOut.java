import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request,pw);
		
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 

			String delivery_option = request.getParameter("delivery_option");
			String userName = session.getAttribute("username").toString();

			utility.printHtml("Head.html");
			utility.printHeader();
			utility.printLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");

			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>CheckOut</h1>");
			pw.write("</div>");

			pw.print("<form name ='CheckOut' action='Payment' method='post'>");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order Details</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer Name : </td><td>"+userName+"</td></tr>");
			
			pw.print("<tr><td>Index</td><td>Product</td><td>Counts</td><td>Price</td><td>Discount</td></tr>");
			
			HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
			if(products!=null){
				int ind = 1;
				double total_price = 0;
				double total_discount = 0;
				for (Map.Entry<String, Integer> entry: utility.getCustomerOrders().entrySet())
				{
					String id = entry.getKey();
					int count = entry.getValue();
					Product p = products.get(id);
					
					pw.print("<tr>");
					pw.print("<td>"+ind+".)</td><td>"+p.getName()+"</td><td>"+count+"</td><td>$"+p.getPrice()+"</td><td>"+p.getDiscount()+"%</td>");
					pw.print("</tr>");
					
					total_price += (count * p.getPrice());
					total_discount += (count * p.getDiscount() * p.getPrice() / 100.0);
					ind++;
				}
				pw.print("<tr><td>");
				pw.print("Total Price : </td><td>$"+total_price);
				pw.print("</td><tr>");
				pw.print("<tr><td>");
				pw.print("Total Discount : </td><td>$"+total_discount);
				pw.print("</td><tr>");
				pw.print("<tr><td>");
				pw.print("Final Amount : </td><td>$"+(total_price - total_discount));
				pw.print("</td><tr>");
				pw.print("</table>");
				
				pw.print("<table><tr></tr><tr></tr>");	
			
				pw.print("<tr><td>");
				pw.print("Customer Name</td>");
				pw.print("<td><input type='text' name='customername'>");
				pw.print("</td></tr>");
				
				pw.print("<tr><td>");
				pw.print("Customer Occupation</td>");
				pw.print("<td><input type='text' name='customeroccupation'>");
				pw.print("</td></tr>");
				
				pw.print("<tr><td>");
				pw.print("Customer age</td>");
				pw.print("<td><input type='number' name='customerage' min='0' max='200'>");
				pw.print("</td></tr>");

				pw.print("<tr><td>");
				pw.print("Credit/account Number</td>");
				pw.print("<td><input type='text' name='creditCardNo'>");
				pw.print("</td></tr>");
				
				if(delivery_option.equalsIgnoreCase("Home Delivery")){
					pw.print("Customer Address :</td></tr>");
					pw.print("<tr><td></td><td>Street :</td><td> <input type='text' name='street' value=''> </td></tr>");
					pw.print("<tr><td></td><td>City :</td><td> <input type='text' name='city' value=''> </td></tr>");
					pw.print("<tr><td></td><td>State :</td><td> <input type='text' name='state' value=''> </td></tr>");
					pw.print("<tr><td></td><td>Zip :</td><td> <input type='text' name='zip' value=''> </td></tr>");
					pw.print("<input type='hidden' name='delivery_option' value='Home Delivery'>");
				}else if(delivery_option.equalsIgnoreCase("In-Store Pickup")){
					HashMap<Integer, String> loc = MySqlDataStoreUtilities.getStoreLocations();

					if(loc!=null){
						pw.print("In-Store Pickup Location :</td></tr>");
						pw.print("<tr><td></td><td><select name='pickup_location' class='input'>"+"<option value='0' selected>Select</option>");
						for(Map.Entry<Integer, String> add : loc.entrySet()){
							pw.print("<option value='"+add.getKey()+"'>"+add.getValue()+"</option>");
						}
						pw.print("</select></td><tr>");
						pw.print("<input type='hidden' name='delivery_option' value='In-Store Pickup'>");
					}else{
						pw.print("In-Store Pickup Location :</td></tr>");
						pw.print("<tr><td></td><td><select name='pickup_location' class='input'>"+"<option value='0' selected>Select</option>");
						pw.print("</select></td><tr>");
						pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
						pw.print("<input type='hidden' name='delivery_option' value='In-Store Pickup'>");
					}
				}
				pw.print("<tr><td colspan='2'>");
				pw.print("<input type='submit' name='submit' class='btnbuy w3-button w3-black'>");
				pw.print("</td></tr></table></form>");
				pw.print("</div></div></div>");
			}else{
				pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
			}
		
		pw.print("</div>");
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
	}
		
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	}
}
