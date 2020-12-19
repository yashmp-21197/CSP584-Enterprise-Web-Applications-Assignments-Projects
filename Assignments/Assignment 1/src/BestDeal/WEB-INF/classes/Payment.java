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
		System.out.println(delivery_option);
		
		if(delivery_option.equalsIgnoreCase("Home Delivery")){
			String creditCardNo=request.getParameter("creditCardNo");
			String street=request.getParameter("street");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String zip=request.getParameter("zip");
			
			if(street != null && city != null && state != null && zip != null && creditCardNo != null && !street.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zip.isEmpty() && !creditCardNo.isEmpty()){
				String userAddress = street +", "+ city +", "+ state +", "+ zip;
				
				System.out.println(userAddress);
				System.out.println(creditCardNo);
				
				int orderId = utility.getOrderPaymentSize() + 1;
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 14);
				String date = sdf.format(c.getTime());
				System.out.println(date);

				//iterate through each order

				for (OrderItem oi : utility.getCustomerOrders())
				{
					utility.storePayment(orderId,oi.getName(),oi.getPrice(), "Home Delivery",creditCardNo, userAddress, date);
				}

				//remove the order details from cart after processing
				
				OrdersHashMap.orders.remove(utility.username());
				
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.write("<div class='w3-main' style='margin-left:250px'>");
				pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
				pw.write("<h1 class='w3-hide-small'>Payment successful</h1>");
				pw.write("</div>");

				pw.print("<h3>Your Order is stored");
				pw.print("<br>Your Order Number is "+(orderId));
				pw.print("<br>Your Order will be reached at your given address("+userAddress+") on "+date+"</h3>");
				
				pw.print("</div>");
				utility.printHtml("Footer.html");
			
			}else{
				utility.printHtml("Header.html");
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
			String creditCardNo=request.getParameter("creditCardNo");
			String pickup_location = request.getParameter("pickup_location");
			
			if(pickup_location != null && creditCardNo != null && !pickup_location.isEmpty() && !creditCardNo.isEmpty() && !pickup_location.equalsIgnoreCase("select")){
				String userAddress = pickup_location;
				
				System.out.println(userAddress);
				System.out.println(creditCardNo);
				
				int orderId = utility.getOrderPaymentSize() + 1;

				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 14);
				String date = sdf.format(c.getTime());
				System.out.println(date);

				for (OrderItem oi : utility.getCustomerOrders())
				{
					utility.storePayment(orderId,oi.getName(),oi.getPrice(), "In-Store Pickup",creditCardNo, userAddress, date);
				}

				//remove the order details from cart after processing
				
				OrdersHashMap.orders.remove(utility.username());
				
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.write("<div class='w3-main' style='margin-left:250px'>");
				pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
				pw.write("<h1 class='w3-hide-small'>Payment successful</h1>");
				pw.write("</div>");

				pw.print("<h3>Your Order is stored");
				pw.print("<br>Your Order Number is "+(orderId));
				pw.print("<br>You can pick your Order from pickup store("+userAddress+") on "+date+"</h3>");
				
				pw.print("</div>");
				utility.printHtml("Footer.html");
			
			}else{
				utility.printHtml("Header.html");
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
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
