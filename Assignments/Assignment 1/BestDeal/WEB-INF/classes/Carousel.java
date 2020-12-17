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
			
			
public class Carousel{
			
	public String  carouselfeature(Utilities utility){
		
		HashMap<String, String> accessories = new HashMap();
		
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
		
		for(OrderItem oi : utility.getCustomerOrders()){
			for(Map.Entry<String, TV> entry : SaxParserDataStore.tvs.entrySet()){
				TV tv = entry.getValue();
				if(tv.getName().equalsIgnoreCase(oi.getName())){
					for(Map.Entry<String, String> acc : tv.accessories.entrySet()){
						accessories.put(acc.getKey(), acc.getValue());
					}
				}
			}
			for(Map.Entry<String, Phone> entry : SaxParserDataStore.phones.entrySet()){
				Phone phone = entry.getValue();
				if(phone.getName().equalsIgnoreCase(oi.getName())){
					for(Map.Entry<String, String> acc : phone.accessories.entrySet()){
						accessories.put(acc.getKey(), acc.getValue());
					}
				}
			}
			for(Map.Entry<String, Laptop> entry : SaxParserDataStore.laptops.entrySet()){
				Laptop laptop = entry.getValue();
				if(laptop.getName().equalsIgnoreCase(oi.getName())){
					for(Map.Entry<String, String> acc : laptop.accessories.entrySet()){
						accessories.put(acc.getKey(), acc.getValue());
					}
				}
			}
		}
		
		System.out.println("carousel");
		for(Map.Entry<String,String> acc : accessories.entrySet()){
			System.out.println(acc.getKey() + "  " + acc.getValue());
		}
		
		if(accessories.size()>0){
			
			sb.append("<div class='w3-container w3-text-grey' id='all_content'>");
			sb.append("<h3 class='w3-hide-small'>Accessories</h3>");
			sb.append("<p></p>");
			sb.append("</div>");
			
//			max-width: 1000px;
//			position: relative;
//			margin: auto;
			
			sb.append("<div class='w3-content w3-display-container' style='max-width:700px;margin-top:150px'>");
			
			for(Map.Entry<String, String> entry : accessories.entrySet()){
				Accessory accessory= SaxParserDataStore.accessories.get(entry.getValue());

				sb.append("<div class='w3-display-container mySlides'>");
				sb.append("<div class='w3-display-middle'>");
				sb.append(
					"<div style='max-width:500px;'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
					"<img src='images/accessory/"+accessory.getImage()+"' style='width:100%'>"+
					"<div class='w3-display-middle w3-display-hover'>"+
					"<form action='Cart' method='post'>"+
					"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
					"<input type='hidden' name='name' value='"+entry.getValue()+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"</form>"+
					"<form action='WriteReview' method='post'>"+
					"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
					"<input type='hidden' name='name' value='"+entry.getValue()+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"</form>"+
					"<form action='ViewReview' method='post'>"+
					"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
					"<input type='hidden' name='name' value='"+entry.getValue()+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"</form>"+
					"</div></div>"+
					"<p>"+accessory.getRetailer()+"-"+accessory.getName()+"<br><b>$"+accessory.getPrice()+"</b></p>"+
					"</div></div>"
				);
				sb.append("</div>");
				sb.append("</div>");
			}
			
			
			sb.append("<button class='w3-button w3-display-left w3-black' onclick='plusDivs(-1)'>&#10094;</button>");
			sb.append("<button class='w3-button w3-display-right w3-black' onclick='plusDivs(1)'>&#10095;</button>");
			
			sb.append("</div>");
			sb.append("<div style='margin-top:200px;'></div>");
			
			sb.append("<script>");
			sb.append("var slideIndex = 0;");
			sb.append("showDivs(slideIndex);");
			sb.append("function plusDivs(n) {showDivs(slideIndex += n);}");
			sb.append("function showDivs(n) {var i;var x = document.getElementsByClassName('mySlides');if (n >= x.length) {slideIndex = 0}if (n < 0) {slideIndex = x.length-1}for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}x[slideIndex].style.display = 'block';}");
			sb.append("carousel();");
			sb.append("function carousel() {var i;var x = document.getElementsByClassName('mySlides');for (i = 0; i < x.length; i++) {x[i].style.display = 'none';}slideIndex++;if (slideIndex >= x.length) {slideIndex = 0}x[slideIndex].style.display = 'block';setTimeout(carousel, 5000);}");
			sb.append("</script>");

		}
		
		return sb.toString();
		}
	}
	