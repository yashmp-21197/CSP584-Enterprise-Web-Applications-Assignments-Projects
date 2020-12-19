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
		
		HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();

		if(products != null){
			
			String id = request.getParameter("id");
			Product p = products.get(id);
			int old_count = utility.cartItemCount(id);
			
			if(action.equalsIgnoreCase("aCart")){
				int new_count = old_count + 1;
				if(p.getCount() < new_count){
					HttpSession session = request.getSession(true);				
					session.setAttribute("cart_msg", "Store has only "+p.getCount()+" counts of "+p.getName()+" in inventory.");
				}else{
					utility.updateCart(id, new_count);
				}
			}else if(action.equalsIgnoreCase("rCart")){
				utility.removeCart(id);
			}else if(action.equalsIgnoreCase("uCart")){
				int new_count = Integer.parseInt(request.getParameter("count"));
				if(p.getCount() < new_count){
					HttpSession session = request.getSession(true);				
					session.setAttribute("cart_msg", "Store has only "+p.getCount()+" counts of "+p.getName()+" in inventory.");
				}else{
					utility.updateCart(id, new_count);
				}
			}
		}else{
			HttpSession session = request.getSession(true);				
			session.setAttribute("cart_msg", "MySQL server is not up and running.");
		}
		response.sendRedirect("Cart");
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
		
		utility.printHtml("Head.html");
		utility.printHeader();
		utility.printLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>Cart("+utility.CartCount()+")</h1>");
		pw.write("</div>");
		
		HttpSession session = request.getSession(true);	
		
		if(session.getAttribute("cart_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("cart_msg")+"</h4>");
			session.removeAttribute("cart_msg");
		}
		
		if(utility.CartCount()>0)
		{
			HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
			
			if(products!=null){
				pw.print("<table  class='gridtable'>");
				pw.print("<tr><td>Index</td><td>Product</td><td>Price</td><td>Discount</td><td>Counts</td></tr>");
				int ind = 1;
				double total_price = 0;
				double total_discount = 0;
				
				for (Map.Entry<String, Integer> entry : utility.getCustomerOrders().entrySet()) 
				{
					String id = entry.getKey();
					int count = entry.getValue();
					
					Product p = products.get(id);
					
					pw.print("<tr>");
					pw.print("<td>"+ind+".)</td><td>"+p.getName()+"</td><td>$"+p.getPrice()+"</td><td>"+p.getDiscount()+"%</td>");
					pw.print("<form name ='Cart' action='Cart' method='post'>"+
						"<td>"+
						"<input type='number' name='count' value='"+count+"' min='1' max='10'>"+
						"</td>"+
						"<td>"+
						"<input type='submit' name='UpdateCart' value='Update Item' class='btnbuy w3-button w3-black'>"+
						"<input type='hidden' name='id' value='"+id+"'>"+"<input type='hidden' name='action' value='uCart'>"+
						"</td>"+"</form>");
					pw.print("<form name ='Cart' action='Cart' method='post'>"+"<td>"+"<input type='submit' name='RemoveCart' value='Remove Item' class='btnbuy w3-button w3-black'>"+"<input type='hidden' name='id' value='"+id+"'>"+"<input type='hidden' name='action' value='rCart'>"+"</td>"+"</form>");
					pw.print("</tr>");
					
					total_price += (count * p.getPrice());
					total_discount += (count * p.getDiscount() * p.getPrice() / 100.0);
					ind++;
				}
				
				pw.print("<tr></tr>");
				pw.print("<tr></tr>");
				pw.print("<tr></tr>");
				pw.print("<form name ='Cart' action='CheckOut' method='post'>");
				pw.print("<tr><td></td><td>Total price</td><td>: $"+total_price+"</td></tr>");
				pw.print("<tr><td></td><td>Total discount</td><td>: $"+total_discount+"</td></tr>");
				pw.print("<tr><td></td><td>Final Amount</td><td>: $"+(total_price-total_discount)+"</td></tr>");

				pw.print("<tr><td></td><td>Delivery Option</td><td>: </td></tr>");
				pw.print("<tr><td></td><td></td><td><input type='radio' id='Home_Delivery' name='delivery_option' value='Home Delivery' checked><label for='Home_Delivery'>Home Delivery</label> </td></tr>");
				pw.print("<tr><td></td><td></td><td><input type='radio' id='In_Store_Pickup' name='delivery_option' value='In-Store Pickup'><label for='In_Store_Pickup'>In-Store Pickup</label> </td></tr>");
				pw.print("<tr><td></td><td></td><td></td></tr>");

				pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy w3-button w3-black'></td></tr>");
				pw.print("</table></form>");
				pw.print(carousel.carouselfeature(utility));
			}else{
				pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
			}
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
