import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatches")

public class DealMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		pw.print("<div>");
		pw.write("<h2 class='w3-hide-small'>CURRENT DEALS</h2>");
		try{
			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Product> productmap = MySqlDataStoreUtilities.getProducts();
			
			for(Map.Entry<String, Product> entry : productmap.entrySet()){
				if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey())){
					BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\BestDeal\\DealMatches.txt")));
					line=reader.readLine().toLowerCase();

					if(line==null){
						pw.print("<h3 align='center'>No Offers Found</h3>");
						break;
					}else{	
						do{	
							if(line.toLowerCase().contains(entry.getValue().getName().toLowerCase())){
								pw.print("<h3>"+line+"</h3>");
								pw.print("<br>");
								selectedproducts.put(entry.getKey(),entry.getValue());
								break;
							}
						}while((line = reader.readLine()) != null);
					}
				}
			}
		}catch(Exception e){
			pw.print("<h3 align='center'>No Offers Found</h3>");
		}
		pw.print("</div>");
		
		pw.print("<div>");
		pw.print("<div class='post'>");
		pw.write("<h2 class='w3-hide-small'>Deal Matches</h2>");
		pw.print("<div class='entry'>");
		if(selectedproducts.size()==0){
			pw.print("<h3 align='center'>No Deals Found</h3>");	
		}else{
			pw.print("<table id='bestseller'>");
			pw.print("<tr>");
			for(Map.Entry<String, Product> entry : selectedproducts.entrySet()){
				Product p = entry.getValue();
				pw.write(
					"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
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
			}
			pw.print("</tr></table>");
		}
		pw.print("</div></div>");
		pw.print("</div>");
		pw.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
	}
}
