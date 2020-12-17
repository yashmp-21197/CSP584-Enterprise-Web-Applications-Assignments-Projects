import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String action = request.getParameter("action");
		Utilities utility = new Utilities(request, pw);
		
		if(action == null){

			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String maker = request.getParameter("maker");
			String access = request.getParameter("access");
			System.out.println("name : " + name + " type : " + type + " maker : " + maker + " access : " + access);

			/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
			
			utility.storeProduct(name, type, maker, access);
		}else{
			if(action.equalsIgnoreCase("rCart")){
				String name = request.getParameter("orderName");
				utility.removeProduct(name);
			}
		}
		
		
		response.sendRedirect("Cart");
//		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>Cart("+utility.CartCount()+")</h1>");
		pw.write("</div>");
		
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<form name ='Cart' action='Cart' method='post'>");
				pw.print("<tr>");
				pw.print("<td>"+i+".)</td><td>"+
					oi.getName()+
					"</td><td>: "+oi.getPrice()+"</td><td>" + 
					"<input type='submit' name='RemoveCart' value='Remove from Cart' class='btnbuy w3-button w3-black'>" + "</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='action' value='rCart'>");
				pw.print("</tr>");
				pw.print("</form>");
				
				total = total +oi.getPrice();
				i++;
			}
			pw.print("<form name ='Cart' action='CheckOut' method='post'>");
			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");	
			pw.print("<tr><td></td><td>Total</td><td>: "+total+"</td></tr>");

			pw.print("<tr><td></td><td>Delivery Option</td><td>: </td></tr>");
			pw.print("<tr><td></td><td></td><td> <input type='radio' id='Home_Delivery' name='delivery_option' value='Home Delivery' checked><label for='Home_Delivery'>Home Delivery</label> </td></tr>");
			pw.print("<tr><td></td><td></td><td> <input type='radio' id='In_Store_Pickup' name='delivery_option' value='In-Store Pickup'><label for='In_Store_Pickup'>In-Store Pickup</label> </td></tr>");
			pw.print("<tr><td></td><td></td><td>  </td></tr>");

			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy w3-button w3-black'></td></tr>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		pw.write("</div>");
		pw.write("<div style='margin-top:400px;'></div>");
		utility.printHtml("Footer.html");
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
