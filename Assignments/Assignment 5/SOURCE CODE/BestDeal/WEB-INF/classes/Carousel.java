/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display 
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the 
accessories realated to product will get displayed in the carousel.*/
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	
import java.util.*;		
			
			
public class Carousel{
			
	public String  carouselfeature(Utilities utility){
		
		ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();

		HashMap<String,String> prodRecmMap = prodRecUtility.readOutputFile();

		HashMap<String, String> accessories = new HashMap();
		HashMap<String, Product> products = MySqlDataStoreUtilities.getProducts();
		HashMap<String, ArrayList<String>> product_accessories = MySqlDataStoreUtilities.getAccessories();
		HashMap<Integer, ArrayList<OrderItem>> orders = MySqlDataStoreUtilities.getAllOrders();
		
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
		
		if(products!=null && product_accessories !=null && orders!=null){
			
			String uid = utility.username();
			String rec = prodRecmMap.get(uid);
			
			if(rec != null){
				rec = rec.replace("[","");
				rec = rec.replace("]","");
				rec = rec.replace("\"","");
				rec = rec.replace("'","");
				rec = rec.replace(" ","");
				
				ArrayList<String> rec_list = new ArrayList<String>(Arrays.asList(rec.split(",")));
				ArrayList<String> sorted_rec_list = new ArrayList<String>();
				
				for(String product_id : rec_list){
					boolean flag = true;
					
					for(Map.Entry<Integer, ArrayList<OrderItem>> entry : orders.entrySet()){
						if(flag == true){
							ArrayList<OrderItem> ois = entry.getValue();
							for(OrderItem oi : ois){
								if(product_id.equalsIgnoreCase(oi.productid) && (oi.isreturned || oi.transactionstatus.equalsIgnoreCase("disputed"))){
									flag = false;
									break;
								}
							}
						}
					}
					
					if(flag==true){
						sorted_rec_list.add(product_id);
					}
				}
				
				if(sorted_rec_list.size() > 0){
					sb.append("<div class='w3-container w3-text-grey' id='all_content'>");
					sb.append("<h3 class='w3-hide-small'>Recommended Products</h3>");
					sb.append("<p></p>");
					sb.append("</div>");
					
					sb.append("<div class='w3-content w3-display-container' style='max-width:700px;margin-top:150px'>");
					
					for(String product_id : sorted_rec_list){
						
						Product p = products.get(product_id);

						sb.append("<div class='w3-display-container mySlides1'>");
						sb.append("<div class='w3-display-middle'>");
						sb.append(
							"<div style='max-width:500px;'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
							"<img src='images/"+p.getType()+"/"+p.getImage()+"' style='width:100%'>"+
							(p.getOnsale()==true ? "<span class='w3-tag w3-display-topleft'>OnSale</span>" : "") +
							"<div class='w3-display-middle w3-display-hover'>"+
							"<form action='Cart' method='post'>"+
							"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
							"<input type='hidden' name='id' value='"+p.getId()+"'>"+
							"<input type='hidden' name='action' value='aCart'>"+
							"</form>"+
							"<form action='Reviews' method='post'>"+
							"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
							"<input type='hidden' name='action' value='write'>"+
							"<input type='hidden' name='id' value='"+p.getId()+"'>"+
							"</form>"+
							"<form action='Reviews' method='post'>"+
							"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
							"<input type='hidden' name='action' value='view'>"+
							"<input type='hidden' name='id' value='"+p.getId()+"'>"+
							"</form>"+
							"</div></div>"+
							"<p>"+p.getManufacturer()+"-"+p.getName()+" <b>("+p.getCondition()+")</b><br><b>$"+p.getPrice()+"(discount: "+p.getDiscount()+"%)"+"</b></p>"+
							"</div></div>"
						);
						sb.append("</div>");
						sb.append("</div>");
					}
					
					
					sb.append("<button class='w3-button w3-display-left w3-black' onclick='plusDivs1(-1)'>&#10094;</button>");
					sb.append("<button class='w3-button w3-display-right w3-black' onclick='plusDivs1(1)'>&#10095;</button>");
					
					sb.append("</div>");
					
					sb.append("<div style='margin-top:400px;'></div>");
					
					sb.append("<script>");
					sb.append("var slideIndex1 = 0;");
					sb.append("showDivs1(slideIndex1);");
					sb.append("function plusDivs1(n) {showDivs1(slideIndex1 += n);}");
					sb.append("function showDivs1(n) {var i;var x = document.getElementsByClassName('mySlides1');if (n >= x.length) {slideIndex1 = 0}if (n < 0) {slideIndex1 = x.length-1}for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}x[slideIndex1].style.display = 'block';}");
					sb.append("carousel1();");
					sb.append("function carousel1() {var i;var x = document.getElementsByClassName('mySlides1');for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}slideIndex1++;if (slideIndex1 >= x.length) {slideIndex1 = 0}x[slideIndex1].style.display = 'block';setTimeout(carousel1, 5000);}");
					sb.append("</script>");
				}

			}
			
			for(Map.Entry<String, Integer> entry : utility.getCustomerOrders().entrySet()){
				String id = entry.getKey();
				ArrayList<String> list = product_accessories.getOrDefault(id, new ArrayList());
				for(String acc : list){
					accessories.put(acc,acc);
				}
			}
			
			for(Map.Entry<String,String> acc : accessories.entrySet()){
				System.out.println(acc.getKey() + "  " + acc.getValue());
			}
			
			if(accessories.size()>0){
				
				sb.append("<div class='w3-container w3-text-grey' id='all_content'>");
				sb.append("<h3 class='w3-hide-small'>Accessories</h3>");
				sb.append("<p></p>");
				sb.append("</div>");
				
				sb.append("<div class='w3-content w3-display-container' style='max-width:700px;margin-top:150px'>");
				
				for(Map.Entry<String, String> entry : accessories.entrySet()){
					Product p = products.get(entry.getKey());

					sb.append("<div class='w3-display-container mySlides'>");
					sb.append("<div class='w3-display-middle'>");
					sb.append(
						"<div style='max-width:500px;'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
						"<img src='images/"+p.getType()+"/"+p.getImage()+"' style='width:100%'>"+
						(p.getOnsale()==true ? "<span class='w3-tag w3-display-topleft'>OnSale</span>" : "") +
						"<div class='w3-display-middle w3-display-hover'>"+
						"<form action='Cart' method='post'>"+
						"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
						"<input type='hidden' name='id' value='"+p.getId()+"'>"+
						"<input type='hidden' name='action' value='aCart'>"+
						"</form>"+
						"<form action='Reviews' method='post'>"+
						"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
						"<input type='hidden' name='action' value='write'>"+
						"<input type='hidden' name='id' value='"+p.getId()+"'>"+
						"</form>"+
						"<form action='Reviews' method='post'>"+
						"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
						"<input type='hidden' name='action' value='view'>"+
						"<input type='hidden' name='id' value='"+p.getId()+"'>"+
						"</form>"+
						"</div></div>"+
						"<p>"+p.getManufacturer()+"-"+p.getName()+" <b>("+p.getCondition()+")</b><br><b>$"+p.getPrice()+"(discount: "+p.getDiscount()+"%)"+"</b></p>"+
						"</div></div>"
					);
					sb.append("</div>");
					sb.append("</div>");
				}
				
				
				sb.append("<button class='w3-button w3-display-left w3-black' onclick='plusDivs(-1)'>&#10094;</button>");
				sb.append("<button class='w3-button w3-display-right w3-black' onclick='plusDivs(1)'>&#10095;</button>");
				
				sb.append("</div>");
				sb.append("<div style='margin-top:400px;'></div>");
				
				sb.append("<script>");
				sb.append("var slideIndex = 0;");
				sb.append("showDivs(slideIndex);");
				sb.append("function plusDivs(n) {showDivs(slideIndex += n);}");
				sb.append("function showDivs(n) {var i;var x = document.getElementsByClassName('mySlides');if (n >= x.length) {slideIndex = 0}if (n < 0) {slideIndex = x.length-1}for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}x[slideIndex].style.display = 'block';}");
				sb.append("carousel();");
				sb.append("function carousel() {var i;var x = document.getElementsByClassName('mySlides');for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}slideIndex++;if (slideIndex >= x.length) {slideIndex = 0}x[slideIndex].style.display = 'block';setTimeout(carousel, 5000);}");
				sb.append("</script>");

			}
		}else{
			sb.append("<h4 style='color:red'>MySQL server is not up and running.</h4>");
		}
		
		return sb.toString();
		}
	}
	