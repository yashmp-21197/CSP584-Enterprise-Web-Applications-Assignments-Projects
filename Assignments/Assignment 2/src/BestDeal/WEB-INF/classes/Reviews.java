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

@WebServlet("/Reviews")
	
public class Reviews extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
			
			if(!utility.isLoggedin()){
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to Write a Review");
				response.sendRedirect("Login");
				return;
			}
			
			utility.printHtml("Head.html");
			utility.printHeader();
			utility.printLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Review</h1>");
			pw.write("</div>");
			
			String action = request.getParameter("action");
			
			if(action.equalsIgnoreCase("write")){
				String pid = request.getParameter("id");
				
				HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();
				HashMap<Integer, String> loc = MySqlDataStoreUtilities.getStoreLocations();

				if(product_map != null && loc != null){
					Product p = product_map.get(pid);
				
					pw.print("<form action='Reviews' method='post'>");
					pw.print("<input type='hidden' name='action' value='submit'>");
					pw.print("<input type='hidden' name='product_id' value='"+p.getId()+"'>");
				
					pw.print("<table class='gridtable'>");
					
					pw.print("<tr><td> Product Name: </td><td>");
					pw.print(p.getName());
					pw.print("<input type='hidden' name='product_name' value='"+p.getName()+"'>");
					pw.print("</td></tr>");
					pw.print("<tr><td> Product Category:</td><td>");
					pw.print(p.getType());
					pw.print("<input type='hidden' name='product_category' value='"+p.getType()+"'>");
					pw.print("</td></tr>");
					pw.print("<tr><td> Product Price:</td><td> $");
					pw.print(p.getPrice());
					pw.print("<input type='hidden' name='product_price' value='"+p.getPrice()+"'>");
					pw.print("</td></tr>");
					
					pw.print("<tr><td> Store: </td><td>");
					pw.print("<select name='store_id' class='input'>");
					pw.print("<option value='0' selected>Select</option>");
					for(Map.Entry<Integer, String> add : loc.entrySet()){
						pw.print("<option value='"+add.getKey()+"'>"+add.getValue()+"</option>");
					}
					pw.print("</select>");
					pw.print("</td></tr>");
					
					pw.print("<tr><td></td><td>");
					pw.print("<input type='hidden' name='product_onsale' value='"+(p.getDiscount() > 0 ? "yes" : "no")+"'>");
					pw.print("</td></tr>");
					
					pw.print("<tr><td> Product Manufacture: </td><td>");
					pw.print(p.getManufacturer());
					pw.print("<input type='hidden' name='manufacturer_name' value='"+p.getManufacturer()+"'>");
					pw.print("</td></tr>");
					pw.print("<tr><td> Product Manufacture Rebate: </td><td>");
					pw.print(p.getDiscount() + "%");
					pw.print("<input type='hidden' name='manufacturer_rebate' value='"+p.getDiscount()+"'>");
					pw.print("</td></tr>");
					
					pw.print("<tr><td> User ID: </td><td>");
					pw.print(utility.username());
					pw.print("<input type='hidden' name='user_id' value='"+utility.username()+"'>");
					pw.print("</td></tr>");
					pw.print("<tr><td> User Age: </td><td>");
					pw.print("<input type='number' name='user_age' value='' max='100'>");
					pw.print("</td></tr>");
					pw.print("<tr><td> User Gender: </td><td>");
					pw.print("<select name='user_gender'>");
					pw.print("<option value='M' selected>M</option>");
					pw.print("<option value='F'>F</option>");
					pw.print("</select></td></tr>");
					pw.print("</td></tr>");
					pw.print("<tr><td> User Occupation: </td><td>");
					pw.print("<input type='text' name='user_occupation' value=''>");
					pw.print("</td></tr>");
					
					pw.print("<tr><td>Product Rating: </td>");
					pw.print("<td>");
					pw.print("<select name='review_rating'>");
					pw.print("<option value='1' selected>1</option>");
					pw.print("<option value='2'>2</option>");
					pw.print("<option value='3'>3</option>");
					pw.print("<option value='4'>4</option>");
					pw.print("<option value='5'>5</option>");  
					pw.print("</select></td></tr>");
			
					pw.print("<tr>");
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					Calendar c = Calendar.getInstance();
					String date = sdf.format(c.getTime());
					pw.print("<td><input type='hidden' name='review_date' value='"+date+"'> </td>");
					pw.print("</tr>");

					pw.print("<tr>");
					pw.print("<td>Review Text: </td>");
					pw.print("<td><textarea name='review_text' rows='4' cols='50'></textarea></td></tr>");
					
					pw.print("<tr><td colspan='2'><input type='submit' class='btnreview w3-button w3-black' name='SubmitReview' value='SubmitReview'></td></tr></table>");
					
					pw.print("</form>");
				}else{
					pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
				}
				
				
			}
			else if(action.equalsIgnoreCase("submit")){
				String product_id = request.getParameter("product_id");
				String product_name = request.getParameter("product_name");
				String product_category = request.getParameter("product_category");
				String product_price = request.getParameter("product_price");
				String store_id = request.getParameter("store_id");
				String product_onsale = request.getParameter("product_onsale");
				String manufacturer_name = request.getParameter("manufacturer_name");
				String manufacturer_rebate = request.getParameter("manufacturer_rebate");
				String user_id = request.getParameter("user_id");
				String user_age = request.getParameter("user_age");
				String user_gender = request.getParameter("user_gender");
				String user_occupation = request.getParameter("user_occupation");
				String review_rating = request.getParameter("review_rating");
				String review_date = request.getParameter("review_date");
				String review_text = request.getParameter("review_text");
				
				System.out.println(product_id + " " + product_name + " " + product_category + " " + product_price + " " + store_id + " " + product_onsale + " " + manufacturer_name + " " + manufacturer_rebate + " " + user_id + " " + user_age + " " + user_gender + " " + user_occupation + " " + review_rating + " " + review_date + " " + review_text);
				
				if(
				product_id != null &&
				product_name != null && 
				product_category != null && 
				product_price != null && 
				store_id != null && 
				product_onsale != null && 
				manufacturer_name != null &&
				manufacturer_rebate != null &&
				user_id != null &&
				user_age != null &&
				user_gender != null &&
				user_occupation != null &&
				review_rating != null && 
				review_date != null &&
				review_text != null &&
				!product_id.isEmpty() &&
				!product_name.isEmpty() &&
				!product_category.isEmpty() &&
				!product_price.isEmpty() &&
				!store_id.isEmpty() && !store_id.equals("0") &&
				!product_onsale.isEmpty() &&
				!manufacturer_name.isEmpty() &&
				!manufacturer_rebate.isEmpty() &&
				!user_id.isEmpty() &&
				!user_age.isEmpty() &&
				!user_gender.isEmpty() &&
				!user_occupation.isEmpty() &&
				!review_rating.isEmpty() &&
				!review_date.isEmpty() &&
				!review_text.isEmpty()
				){
					
					int sid= Integer.parseInt(store_id);
					HashMap<Integer, StoreLocation> loc = MySqlDataStoreUtilities.getStoreLocationObjs();
					if(loc!=null){
						StoreLocation sl = loc.get(sid);
						String store_zip = sl.getZip();
						String store_city = sl.getCity();
						String store_state = sl.getState();
						
						System.out.println(product_id + " " + product_name + " " + product_category + " " + product_price + " " + store_id + " " + store_zip + " " + store_city + " " + store_state + " " + product_onsale + " " + manufacturer_name + " " + manufacturer_rebate + " " + user_id + " " + user_age + " " + user_gender + " " + user_occupation + " " + review_rating + " " + review_date + " " + review_text);
						
						Review r = new Review(product_id, product_name, product_category, product_price, store_id, store_zip, store_city, store_state, product_onsale, manufacturer_name, manufacturer_rebate, user_id, user_age, user_gender, user_occupation, review_rating, review_date, review_text);
						
						boolean ret = MongoDBDataStoreUtilities.insertReview(r);
						
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						if(ret==true)
							pw.print("<h4 style='color:red'>Your review is successfully stored.</h4>");
						else
							pw.print("<h4 style='color:red'>MongoDB server is not up and running.</h4>");
						pw.write("</div>");
					}else{
						pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
					}
					
				}else{
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
					pw.write("</div>");
				}
				
			}else if(action.equalsIgnoreCase("view")){
				String pid = request.getParameter("id");
				HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
				
				if(hm!=null){
					if(hm.containsKey(pid)){
						int counter = 0;
						for(Review r : hm.get(pid)){
							pw.print("<table class='gridtable' style='border: 2px solid #000000;border-radius: 12px;padding: 10px;'>");
							counter++;
							pw.print("<tr>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>"+counter+"). </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Product Name: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getProduct_name()+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Product Category: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getProduct_category()+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Product price: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getProduct_price()+ "</td>");
							pw.print("</tr>");
							
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Product OnSale: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getProduct_onsale()+ "</td>");
							pw.print("</tr>");
							
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Retailer: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getManufacturer_name()+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Retailer Discount: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getManufacturer_rebate()+ "</td>");
							pw.print("</tr>");
							
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Retailer Zip: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getStore_zip()+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Retailer City: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getStore_city()+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Retailer State: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getStore_state()+ "</td>");
							pw.print("</tr>");

							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>User Id: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getUser_id()+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>User Age: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getUser_age()+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>User gender: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getUser_gender()+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>User Occupation: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getUser_occupation()+ "</td>");
							pw.print("</tr>");
							
							pw.println("<tr>");
							pw.print("<td></td>");
							pw.println("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Review Rating: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getReview_rating()+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Review Date: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getReview_date()+ "</td>");
							pw.print("</tr>");			
							pw.print("<tr>");
							pw.print("<td></td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>Review Text: </td>");
							pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>" +r.getReview_text()+ "</td>");
							pw.print("</tr>");
							
							pw.println("</table>");
							pw.println("<br>");
						}
					}else{
						pw.println("<h2>There are no reviews for this product.</h2>");
					}
				}else{
					pw.print("<h4 style='color:red'>MongoDB server is not up and running.</h4>");
				}
			}
			pw.write("</div>");
			utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
    }
}
