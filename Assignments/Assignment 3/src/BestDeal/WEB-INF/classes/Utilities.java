import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		pw.print(result);
	}
	
	public void printHeader(){
		pw.print("<div class='w3-black w3-center w3-padding-24'>");
		pw.print("<p class='w3-left'>");
		pw.print("<i><a href='Home'>Home</a>&ensp;</i>");
		
		HashMap<String, Product> hm = MySqlDataStoreUtilities.getProducts();
		HashMap<String, String> tags = new HashMap<String, String>();
		
		if(hm!=null){
			for(Map.Entry<String, Product> entry : hm.entrySet()){
				tags.put(entry.getValue().getType(),entry.getValue().getType());
			}
		}
		
		for(Map.Entry<String, String> entry : tags.entrySet()){
			pw.print("<i><a href='Home?type="+entry.getKey()+"'>"+entry.getKey()+"</a>&ensp;</i>");
		}
		
		pw.print("<i><a href='Trending'>Trending</a>&ensp;</i>");
		pw.print("</p>");
		pw.print("</div>");
		pw.print("<div class='w3-black w3-center w3-padding-24'>");
		pw.print("<p class='w3-center'>");
		pw.print("<div name='autofillform'>");
		pw.print("<input type='text' name='complete-field' value='' class='input' id='complete-field' onkeyup='doCompletion()' autocomplete='off' placeholder='search here..' style='padding: 5px; font-size: 16px;'/>");
		pw.print("<div id='auto-row'>");
		pw.print("<table id='complete-table' class='gridtable' style='position: absolute; width: 315px;'></table>");
		pw.print("</div>");
		pw.print("</div>");
		pw.print("</p>");
		pw.print("</div>");
		
		pw.print("<div class='w3-black w3-center w3-padding-64'>");
		pw.print("<p class='w3-right'>");
		if (session.getAttribute("username")!=null){
			String username = session.getAttribute("username").toString();
			username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
			pw.print("<i><a href='ViewOrder'>View Order</a>&ensp;</i>");
			pw.print("<i><a>Hello,"+username+"</a>&ensp;</i>");
			pw.print("<i><a href='Account'>Account</a>&ensp;</i>");
			pw.print("<i><a href='Logout'>Logout</a>&ensp;</i>");
		}
		else
			pw.print("<i><a href='ViewOrder'>View Order</a>&ensp;</i>"+ "<i><a href='Login'>Login</a>&ensp;</i>");
				
		pw.print("<i><a href='Cart'>Cart("+CartCount()+")</a>&ensp;</i></p></div></div></div>");
		
	}
	
	public void printSMHeader(){
		pw.print("<div class='w3-black w3-center w3-padding-24'>");
		pw.print("<p class='w3-left'>");
		pw.print("<i><a href='Home'>Home</a>&ensp;</i>");
		pw.print("<i><a href='ProductModify?action=add'>Add Product</a>&ensp;</i>");
		pw.print("<i><a href='ProductModify?action=update'>Update Product</a>&ensp;</i>");
		pw.print("<i><a href='ProductModify?action=delete'>Delete Product</a>&ensp;</i>");
		pw.print("</p>");
		pw.print("</div>");
		
		pw.print("<div class='w3-black w3-center w3-padding-64'>");
		pw.print("<p class='w3-right'>");
		String username = session.getAttribute("username").toString();
		username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
		pw.print("<i><a>Hello,"+username+"</a>&ensp;</i>");
		pw.print("<i><a href='Logout'>Logout</a>&ensp;</i>");
		pw.print("</p></div></div></div>");
		
	}
	
	public void printSmHeader(){
		pw.print("<div class='w3-black w3-center w3-padding-24'>");
		pw.print("<p class='w3-left'>");
		pw.print("<i><a href='Home'>Home</a>&ensp;</i>");
		pw.print("</p>");
		pw.print("</div>");
		
		pw.print("<div class='w3-black w3-center w3-padding-64'>");
		pw.print("<p class='w3-right'>");
		String username = session.getAttribute("username").toString();
		username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
		pw.print("<i><a>Hello,"+username+"</a>&ensp;</i>");
		pw.print("<i><a href='Logout'>Logout</a>&ensp;</i>");
		pw.print("</p></div></div></div>");
		
	}
	
	public void printLeftNavBar(){
		
		HashMap<String, Product> hm = MySqlDataStoreUtilities.getProducts();
		HashMap<String, HashMap<String,String>> tags = new HashMap<String, HashMap<String,String>>();
		
		if(hm!=null){
			for(Map.Entry<String, Product> entry : hm.entrySet()){
				Product p = entry.getValue();
				
				HashMap<String,String> manufacturer = tags.getOrDefault(p.getType(), new HashMap());
				manufacturer.put(p.getManufacturer(), p.getManufacturer());
				tags.put(p.getType(), manufacturer);
			}
		}
		
		pw.print("<nav class='w3-sidebar w3-bar-block w3-white w3-collapse w3-top' style='z-index:3;width:250px' id='mySidebar'>");
		pw.print("<div class='w3-container w3-display-container w3-padding-16'><i onclick='closeSideBar()' class='fa fa-remove w3-hide-large w3-button w3-display-topright'>close</i><h3 class='w3-wide'><b>BestDeal</b></h3></div>");
		pw.print("<div class='w3-padding-64 w3-large w3-text-grey' style='font-weight:bold'>");
		
		for(Map.Entry<String, HashMap<String,String>> entry : tags.entrySet()){
			pw.print("<a onclick=\"showSubList('"+entry.getKey()+"List');\" href='#"+entry.getKey()+"list' class='w3-button w3-block w3-white w3-left-align' id='navBtn1'>"+entry.getKey()+"<i class='fa fa-caret-down'></i></a>");
			pw.print("<div id='"+entry.getKey()+"List' class='w3-bar-block w3-hide w3-padding-large w3-medium'>");
			
			HashMap<String,String> manufacturer = entry.getValue();
			
			for(Map.Entry<String, String> man : manufacturer.entrySet()){
				pw.print("<a href='Home?type="+entry.getKey()+"&manufacturer="+man.getKey()+"' class='w3-bar-item w3-button'>"+man.getKey()+"</a>");
			}
			pw.print("</div>");
		}
		
		pw.print("</div></nav>");
	}
	
	public void printSMLeftNavBar(){
		pw.print("<nav class='w3-sidebar w3-bar-block w3-white w3-collapse w3-top' style='z-index:3;width:250px' id='mySidebar'>");
		pw.print("<div class='w3-container w3-display-container w3-padding-16'><i onclick='closeSideBar()' class='fa fa-remove w3-hide-large w3-button w3-display-topright'>close</i><h3 class='w3-wide'><b>BestDeal</b></h3></div>");
		pw.print("<div class='w3-padding-64 w3-large w3-text-grey' style='font-weight:bold'>");
		
		pw.print("<a href='DataVisualization?action=inventory' class='w3-button w3-block w3-white w3-left-align' id='navBtn1'>Inventory</a>");
		pw.print("<a href='DataVisualization?action=sales_reports' class='w3-button w3-block w3-white w3-left-align' id='navBtn1'>Sales Reports</a>");
		
		pw.print("</div></nav>");
	}
	
	public void printSmLeftNavBar(){
		pw.print("<nav class='w3-sidebar w3-bar-block w3-white w3-collapse w3-top' style='z-index:3;width:250px' id='mySidebar'>");
		pw.print("<div class='w3-container w3-display-container w3-padding-16'><i onclick='closeSideBar()' class='fa fa-remove w3-hide-large w3-button w3-display-topright'>close</i><h3 class='w3-wide'><b>BestDeal</b></h3></div>");
		pw.print("<div class='w3-padding-64 w3-large w3-text-grey' style='font-weight:bold'></div>");
		pw.print("<div class='w3-padding-64 w3-large w3-text-grey' style='font-weight:bold'>");
		pw.print("<a href='ManageCustomer?action=order' class='w3-button w3-block w3-white w3-left-align' id='navBtn1'>Manage Customer Orders</a>");
		pw.print("<a href='ManageCustomer?action=account' class='w3-button w3-block w3-white w3-left-align' id='navBtn1'>Create Customer Account</a>");
		pw.print("</div>");
		pw.print("</nav>");
	}
	
	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
//	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
//	public User getUser(){
//		HashMap<String, User> hm=MySqlDataStoreUtilities.getUsers();
//		if(hm!=null)
//			return hm.get(username());
//		return null;
//	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
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
//			int size=0;
//			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){				
//					 size=size + 1;
//			}
//			return size;
			return orderPayments.size();
	}

	public HashMap<String,Integer> getCustomerOrders(){
		return CartItem.items.getOrDefault(username(), new HashMap());
	}

	public int CartCount(){
		if(isLoggedin())
			return getCustomerOrders().size();
		return 0;
	}
	
	public int cartItemCount(String id){
		HashMap<String, Integer> cartitems = CartItem.items.getOrDefault(username(), new HashMap());
		CartItem.items.put(username(), cartitems);
		return cartitems.getOrDefault(id, 0);
	}
	
	public void removeCart(String id){
		HashMap<String, Integer> cartitems = CartItem.items.getOrDefault(username(), new HashMap());
		CartItem.items.put(username(), cartitems);
		cartitems.remove(id);
	}
	
	public void updateCart(String id, int count){
		HashMap<String, Integer> cartitems = CartItem.items.getOrDefault(username(), new HashMap());
		CartItem.items.put(username(), cartitems);
		cartitems.put(id, count);
	}
	
//	public void addCart(String id){
//		HashMap<String, Integer> cartitems = CartItem.items.getOrDefault(username(), new HashMap());
//		CartItem.items.put(username(), cartitems);
//		cartitems.put(id, cartitems.getOrDefault(id, 0)+1);
//	}
	
	public void storePayment(int orderId,String orderName,double orderPrice, String delivery_option, String creditCardNo, String userAddress, String date){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
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
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice, delivery_option,creditCardNo,userAddress, "Not Delivered", date);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

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
				System.out.println("inside exception file not written properly");
			}	
	}
	
}
