import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Head.html");
		utility.printHeader();
		utility.printLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>Trending</h1>");
		pw.write("</div>");
		
		ArrayList<MostSold> most_sold = MongoDBDataStoreUtilities.topMostSoldProducts();
		ArrayList<MostSoldZip> most_sold_zip = MongoDBDataStoreUtilities.topMostSoldZip();
		ArrayList<BestRated> best_rated = MongoDBDataStoreUtilities.topBestRatedProducts();
		
		if(best_rated!=null && most_sold_zip!=null && most_sold!=null){
			HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();
			
			pw.write("<div><div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h2 class='w3-hide-small'>Best Rated Products</h2>");
			pw.write("</div>");
			if(product_map!=null){
				pw.print("<div class='entry'><table id='bestseller'>");
				for(BestRated br : best_rated) {
					Product p = product_map.get(br.getProduct_id());
					if(p!=null){
						pw.write(
							"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
							"<img src='images/"+p.getType()+"/"+p.getImage()+"' style='width:100%'>"+
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
							"<p><b>Avg. Rating: "+br.getReview_rating()+"</b></p>"+
							"</div></div>"
						);
					}
				}
				pw.print("</table></div>");
			}else{
				pw.println("<h2 style='color:red'>MySQL server is not up and running.</h2>");
			}
			pw.print("</div>");
			
			pw.write("<div><div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h2 class='w3-hide-small'>Most Sold Zipcodes</h2>");
			pw.write("</div>");
			pw.print("<div class='entry'><table id='bestseller' style='border: 2px solid #000000;border-radius: 12px;padding: 10px;'>");
			pw.print("<tr><td style='padding: 10px;border-bottom: 2px solid #000000;'></td><td style='padding: 10px;border-bottom: 2px solid #000000;'><b>Zipcode</b></td><td style='padding: 10px;border-bottom: 2px solid #000000;'><b>Selling Counts</b></td></tr>");
			int index=0;
			for(MostSoldZip msz : most_sold_zip) {
				index++;
				pw.print("<tr>");
				pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>"+index+"). </td>");
				pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>"+msz.getZip_code()+"</td>");
				pw.print("<td style='padding: 10px;border-bottom: 2px solid #000000;'>"+msz.getCount()+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table></div></div>");
			
			pw.write("<div><div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h2 class='w3-hide-small'>Most Sold Products</h2>");
			pw.write("</div>");
			if(product_map!=null){
				pw.print("<div class='entry'><table id='bestseller'>");
				for(MostSold ms : most_sold) {
					Product p = product_map.get(ms.getProduct_id());
					if(p!=null){
						pw.write(
							"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
							"<img src='images/"+p.getType()+"/"+p.getImage()+"' style='width:100%'>"+
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
							"<p><b>Selling Counts: "+ms.getCount()+"</b></p>"+
							"</div></div>"
						);
					}
				}
				pw.print("</table></div>");
			}else{
				pw.println("<h2 style='color:red'>MySQL server is not up and running.</h2>");
			}
			pw.print("</div>");
			pw.print("<div style='margin-bottom:750px;'></div>");
		}else{
			pw.write("<h4 style='color:red'>MongoDB server is not up and running.</h4>");
		}
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
