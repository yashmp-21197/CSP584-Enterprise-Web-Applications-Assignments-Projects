import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

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
		System.out.println(delivery_option);
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>CheckOut</h1>");
		pw.write("</div>");

		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order Details</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table>");
		
		pw.print("<table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
		
		if(delivery_option.equalsIgnoreCase("Home Delivery")){
			pw.print("Customer Address :</td></tr>");
			pw.print("<tr><td></td><td>Street :</td><td> <input type='text' name='street' value=''> </td></tr>");
			pw.print("<tr><td></td><td>City :</td><td> <input type='text' name='city' value=''> </td></tr>");
			pw.print("<tr><td></td><td>State :</td><td> <input type='text' name='state' value=''> </td></tr>");
			pw.print("<tr><td></td><td>Zip :</td><td> <input type='text' name='zip' value=''> </td></tr>");
			pw.print("<input type='hidden' name='delivery_option' value='Home Delivery'>");
		}else if(delivery_option.equalsIgnoreCase("In-Store Pickup")){
			pw.print("In-Store Pickup Location :</td></tr>");
			pw.print("<tr><td></td><td><select name='pickup_location' class='input'>"+
				"<option value='select' selected>Select</option>"+
				"<option value='780 Lynnway, Lynn MA 1905'>780 Lynnway, Lynn MA 1905</option>"+
				"<option value='250 Rt 59, Airmont NY 10901'>250 Rt 59, Airmont NY 10901</option>"+
				"<option value='255 W Main St, Avon CT 6001'>255 W Main St, Avon CT 6001</option>"+
				"<option value='1972 Hwy 431, Boaz AL 35957'>1972 Hwy 431, Boaz AL 35957</option>"+
				"<option value='207 South Washington, Naperville, IL 60540'>207 South Washington, Naperville, IL 60540</option>"+
				"<option value='1186 Roseville Pkwy, Roseville CA 95678'>1186 Roseville Pkwy, Roseville CA 95678</option>"+
				"<option value='71 Silhavy Rd Ste A-1, Valparaiso IN 46383'>71 Silhavy Rd Ste A-1, Valparaiso IN 46383</option>"+
				"<option value='1555 Lake Woodlands Dr Spring, TX 77380'>1555 Lake Woodlands Dr Spring, TX 77380</option>"+
				"<option value='7541 W Bell Road Suite 101, Peoria AZ 85382'>7541 W Bell Road Suite 101, Peoria AZ 85382</option>"+
				"<option value='232 State Street, Madison WI 53703'>232 State Street, Madison WI 53703</option>"+
				"</select></td><tr>"
			);
			pw.print("<input type='hidden' name='delivery_option' value='In-Store Pickup'>");
		}
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy w3-button w3-black'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");


		
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
