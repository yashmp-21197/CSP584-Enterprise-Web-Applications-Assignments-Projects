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
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>Order</h1>");
		pw.write("</div>");
		
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");

		/*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/
	
		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter Order Number : &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy w3-button w3-black'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		

		/*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			if (request.getParameter("orderId") != null && !request.getParameter("orderId").equals(""))
			{	
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
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
			

				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				if(orderPayments.get(orderId)!=null)
				{
					for(OrderPayment od:orderPayments.get(orderId))	
						if(od.getUserName().equals(username))
							size++;
				}
				// display the orders if there exist order with order id
				if(size>0)
				{	
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>Id</td>");
					pw.print("<td>User</td>");
					pw.print("<td>Product</td>");
					pw.print("<td>Price</td>");
					pw.print("<td>Delivery/PickUp</td>");
					pw.print("<td>Address</td>");
					pw.print("<td>Delivery/Pickup Date</td>");
					pw.print("<td>Status</td></tr>");
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						if(oi.getUserName().equals(username)){
							pw.print("<tr>");			
							pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>$"+oi.getOrderPrice()+"</td><td>"+oi.getDelivery_option()+"</td><td>"+oi.getAddress()+"</td><td>"+oi.getDelivery_pickup_date()+"</td><td>"+oi.getDelivery_status()+"<td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy w3-button w3-black'></td>");
							pw.print("</tr>");
						}
					}
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
		}
		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			if(request.getParameter("orderName") != null)
			{
				String orderName=request.getParameter("orderName");
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{
		
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
				}
				catch(Exception e)
				{
			
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for(OrderPayment oi : orderPayments.get(orderId)) 
					{
							if(oi.getOrderName().equals(orderName) && oi.getUserName().equals(username) && oi.getDelivery_status().equalsIgnoreCase("not delivered"))
							{
								try{
									String date = oi.getDelivery_pickup_date();
									
									SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
									Date firstDate = Calendar.getInstance().getTime();
									Date secondDate = sdf.parse(date);
									
									long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
									long days_diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
									
									System.out.println(days_diff);
									
									if(days_diff > 5){
										ListOrderPayment.add(oi);
										pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
										break;
									}else if(days_diff >= 0){
										pw.print("<h4 style='color:red'>You cannot cancel order in five days before delivery</h4>");
									}else{
										pw.print("<h4 style='color:red'>You cannot cancel delivered order</h4>");
									}		
								}catch(Exception e){}
							}
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				//save the updated hashmap with removed order to the file	
				try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeal\\PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
				
				}	
			}else
			{
				pw.print("<h4 style='color:red'>Please select product</h4>");
			}
		}
		pw.print("</form>");
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}

}


