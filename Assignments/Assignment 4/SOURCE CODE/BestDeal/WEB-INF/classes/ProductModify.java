import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		String action = request.getParameter("action");
		
		utility.printHtml("Head.html");
		utility.printSMHeader();
		utility.printSMLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		
		if(action.equalsIgnoreCase("add")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Add Product</h1>");
			pw.write("</div>");
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<div class='entry'>");
			pw.print("<form method='get' action='ProductModify'>"
					+ "<input type='hidden' name='action' value='modify'>"
					+ "<input type='hidden' name='action1' value='add'>"
					+ "<table id='bestseller'><tr><td>"
					
					+ "<h3>Product Id :</h3></td><td><input type='text' name='product_id' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Type :</h3></td><td><select name='product_type' class='input'>"
					+ "<option value='select' selected>select</option>"
					+ "<option value='tv'>TV</option>"
					+ "<option value='phone'>Phone</option>"
					+ "<option value='laptop'>Laptop</option>"
					+ "<option value='sound system'>Sound System</option>"
					+ "<option value='voice assistant'>Voice Assistant</option>"
					+ "<option value='fitness watch'>Fitness Watch</option>"
					+ "<option value='smart watch'>Smart Watch</option>"
					+ "<option value='headphone'>Headphone</option>"
					+ "<option value='vr'>VR</option>"
					+ "<option value='pet tracker'>Pet Tracker</option>"
					+ "</select>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Name :</h3></td><td><input type='text' name='product_name' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Price :</h3></td><td><input type='number' name='product_price' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Image :</h3></td><td><input type='text' name='product_image' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Manufacturer :</h3></td><td><input type='text' name='product_manufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Condition :</h3></td><td><input type='text' name='product_condition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Discount :</h3></td><td><input type='number' min='0' max='100' name='product_discount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product OnSale :</h3></td><td><input type='radio' id='false_product_onsale' name='product_onsale' value='false' checked><label for='product_onsale'>No</label></td><td><input type='radio' id='true_product_onsale' name='product_onsale' value='true'><label for='product_onsale'>Yes</label>" 
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Inventory Count :</h3></td><td><input type='number' min='0' name='product_inventory_count' value='' class='input' required></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "<input type='submit' class='btnbuy w3-button w3-black' name='add_product' value='Add Product'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		if(action.equalsIgnoreCase("update")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Update Product</h1>");
			pw.write("</div>");
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<div class='entry'>");
			
			pw.print("<form method='get' action='ProductModify'>"
					+ "<input type='hidden' name='action' value='modify'>"
					+ "<input type='hidden' name='action1' value='update'>"
					+ "<table id='bestseller'><tr><td>"
					
					+ "<h3>Product Id :</h3></td><td><input type='text' name='product_id' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Type :</h3></td><td><select name='product_type' class='input'>"
					+ "<option value='select' selected>select</option>"
					+ "<option value='tv'>TV</option>"
					+ "<option value='phone'>Phone</option>"
					+ "<option value='laptop'>Laptop</option>"
					+ "<option value='sound system'>Sound System</option>"
					+ "<option value='voice assistant'>Voice Assistant</option>"
					+ "<option value='fitness watch'>Fitness Watch</option>"
					+ "<option value='smart watch'>Smart Watch</option>"
					+ "<option value='headphone'>Headphone</option>"
					+ "<option value='vr'>VR</option>"
					+ "<option value='pet tracker'>Pet Tracker</option>"
					+ "</select>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Name :</h3></td><td><input type='text' name='product_name' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Price :</h3></td><td><input type='number' name='product_price' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Image :</h3></td><td><input type='text' name='product_image' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Manufacturer :</h3></td><td><input type='text' name='product_manufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Condition :</h3></td><td><input type='text' name='product_condition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Discount :</h3></td><td><input type='number' min='0' max='100' name='product_discount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product OnSale :</h3></td><td><input type='radio' id='false_product_onsale' name='product_onsale' value='false' checked><label for='product_onsale'>No</label></td><td><input type='radio' id='true_product_onsale' name='product_onsale' value='true'><label for='product_onsale'>Yes</label>" 
					+ "</td></tr><tr><td>"
					
					+ "<h3>Product Inventory Count :</h3></td><td><input type='number' min='0' name='product_inventory_count' value='' class='input' required></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "<input type='submit' class='btnbuy w3-button w3-black' name='update_product' value='Update Product'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		if(action.equalsIgnoreCase("delete")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Delete Product</h1>");
			pw.write("</div>");
			pw.print("<div id='content'><div class='post'>");
			pw.print("<div class='entry'>");
			pw.print("<form method='get' action='ProductModify'>"
					+ "<input type='hidden' name='action' value='modify'>"
					+ "<input type='hidden' name='action1' value='delete'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>ProductId</h3></td><td><input type='text' name='product_id' value='' class='input' required></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "<input type='submit' class='btnbuy w3-button w3-black' name='delete_product' value='Delete Product'></input>"
					+ "</td></tr></table>"
					+ "</form>"+"</div></div></div>");
		}
		if(action.equalsIgnoreCase("modify")){
			String action1 = request.getParameter("action1");
			
			if(action1.equalsIgnoreCase("add")){
				String id = request.getParameter("product_id");
				String type = request.getParameter("product_type");
				String name = request.getParameter("product_name");
				String price = request.getParameter("product_price");
				String image = request.getParameter("product_image");
				String manufacturer = request.getParameter("product_manufacturer");
				String condition = request.getParameter("product_condition");
				String discount = request.getParameter("product_discount");
				String onsale = request.getParameter("product_onsale");
				String inventory_count = request.getParameter("product_inventory_count");
				
				if(
					id!=null &&
					type!=null &&
					name!=null &&
					price!=null &&
					image!=null &&
					manufacturer!=null &&
					condition!=null &&
					discount!=null &&
					onsale!=null &&
					inventory_count!=null &&
					!id.isEmpty() &&
					!type.isEmpty() && !type.equalsIgnoreCase("select") &&
					!name.isEmpty() &&
					!price.isEmpty() &&
					!image.isEmpty() &&
					!manufacturer.isEmpty() &&
					!condition.isEmpty() &&
					!discount.isEmpty() &&
					!onsale.isEmpty() &&
					!inventory_count.isEmpty()
				){
					int pprice = Integer.parseInt(price);
					int pdiscount = Integer.parseInt(discount);
					boolean ponsale = Boolean.parseBoolean(onsale);
					int pcount = Integer.parseInt(inventory_count);
					
					HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();
					
					if(product_map != null){
						Product p = new Product(id, type, name, pprice, image, manufacturer, condition, pdiscount, ponsale, pcount);
						
						if(product_map.containsKey(p.getId())){
							pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
							pw.write("<h1 class='w3-hide-small'>Add Product Error</h1>");
							pw.write("</div>");
							pw.print("<h4 style='color:red'>Product ID already exiest.</h4>");
						}else{
							
							boolean ret = MySqlDataStoreUtilities.addProduct(p);
							
							if(ret==true){
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Add Product Success</h1>");
								pw.write("</div>");
							}else{
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Add Product Error</h1>");
								pw.write("</div>");
							}
						}
					}else{
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>Add Product Error</h1>");
						pw.write("</div>");
						pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
					}
				}else{
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.write("<h1 class='w3-hide-small'>Add Product Error</h1>");
					pw.write("</div>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
				}
			}
			if(action1.equalsIgnoreCase("update")){
				String id = request.getParameter("product_id");
				String type = request.getParameter("product_type");
				String name = request.getParameter("product_name");
				String price = request.getParameter("product_price");
				String image = request.getParameter("product_image");
				String manufacturer = request.getParameter("product_manufacturer");
				String condition = request.getParameter("product_condition");
				String discount = request.getParameter("product_discount");
				String onsale = request.getParameter("product_onsale");
				String inventory_count = request.getParameter("product_inventory_count");
				
				if(
					id!=null &&
					type!=null &&
					name!=null &&
					price!=null &&
					image!=null &&
					manufacturer!=null &&
					condition!=null &&
					discount!=null &&
					onsale!=null &&
					inventory_count!=null &&
					!id.isEmpty() &&
					!type.isEmpty() && !type.equalsIgnoreCase("select") &&
					!name.isEmpty() &&
					!price.isEmpty() &&
					!image.isEmpty() &&
					!manufacturer.isEmpty() &&
					!condition.isEmpty() &&
					!discount.isEmpty() &&
					!onsale.isEmpty() &&
					!inventory_count.isEmpty()
				){
					int pprice = Integer.parseInt(price);
					int pdiscount = Integer.parseInt(discount);
					boolean ponsale = Boolean.parseBoolean(onsale);
					int pcount = Integer.parseInt(inventory_count);
					
					HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();
					
					if(product_map != null){
						Product p = new Product(id, type, name, pprice, image, manufacturer, condition, pdiscount, ponsale, pcount);
						
						if(!product_map.containsKey(p.getId())){
							pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
							pw.write("<h1 class='w3-hide-small'>Update Product Error</h1>");
							pw.write("</div>");
							pw.print("<h4 style='color:red'>Product ID is not exiest.</h4>");
						}else{
							
							boolean ret = MySqlDataStoreUtilities.updateProduct(p);
							
							if(ret==true){
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Update Product Success</h1>");
								pw.write("</div>");
							}else{
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Update Product Error</h1>");
								pw.write("</div>");
							}
						}
					}else{
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>Update Product Error</h1>");
						pw.write("</div>");
						pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
					}
				}else{
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.write("<h1 class='w3-hide-small'>Update Product Error</h1>");
					pw.write("</div>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
				}
			}
			if(action1.equalsIgnoreCase("delete")){
				String id = request.getParameter("product_id");
				
				if(
					id!=null &&
					!id.isEmpty()
				){
					HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();
					
					if(product_map != null){
						
						if(!product_map.containsKey(id)){
							pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
							pw.write("<h1 class='w3-hide-small'>Delete Product Error</h1>");
							pw.write("</div>");
							pw.print("<h4 style='color:red'>Product ID is not exiest.</h4>");
						}else{
							Product p = product_map.get(id);;
							boolean ret = MySqlDataStoreUtilities.deleteProduct(p);
							
							if(ret==true){
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Delete Product Success</h1>");
								pw.write("</div>");
							}else{
								pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
								pw.write("<h1 class='w3-hide-small'>Delete Product Error</h1>");
								pw.write("</div>");
							}
						}
					}else{
						pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
						pw.write("<h1 class='w3-hide-small'>Delete Product Error</h1>");
						pw.write("</div>");
						pw.print("<h4 style='color:red'>MySQL server is not up and running.</h4>");
					}
				}else{
					pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
					pw.write("<h1 class='w3-hide-small'>Delete Product Error</h1>");
					pw.write("</div>");
					pw.print("<h4 style='color:red'>Please enter valid information</h4>");
				}
			}
			
		}
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}
}