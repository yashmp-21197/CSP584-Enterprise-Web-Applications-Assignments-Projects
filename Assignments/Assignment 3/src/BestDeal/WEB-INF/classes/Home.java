import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/Home")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
				
		if(utility.isLoggedin() && utility.usertype().equalsIgnoreCase("store manager")){
			utility.printHtml("Head.html");
			utility.printSMHeader();
			utility.printSMLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Wellcome, Store Manager</h1>");
			pw.write("</div>");
			pw.write("</div>");
			utility.printHtml("Footer.html");
		}else if(utility.isLoggedin() && utility.usertype().equalsIgnoreCase("salesman")){
			utility.printHtml("Head.html");
			utility.printSmHeader();
			utility.printSmLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Wellcome, Salesman</h1>");
			pw.write("</div>");
			pw.write("</div>");
			utility.printHtml("Footer.html");
		}else{
			utility.printHtml("Head.html");
			utility.printHeader();
			utility.printLeftNavBar();
			utility.printHtml("LeftNavigationBar.html");
			
			String type = request.getParameter("type");
			String manufacturer = request.getParameter("manufacturer");
			
			if(type==null && manufacturer==null){
				utility.printHtml("Content.html");
			}
			
			pw.write("<div class='w3-main' style='margin-left:250px'>");
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>COLLECTION 2020</h1>");
			pw.write("</div>");
			
			
			HashMap<String, Product> product_map = MySqlDataStoreUtilities.getProducts();

			if(product_map == null){
				pw.println("<h2 style='color:red'>MySQL server is not up and running.</h2>");
			}else{
				HashMap<String, ArrayList<Product>> sorted_product_map = new HashMap();
			
				if(type != null && !type.equalsIgnoreCase("")){
					
					if(manufacturer != null && !manufacturer.equalsIgnoreCase("")){
						for(Map.Entry<String, Product> entry : product_map.entrySet()){
							Product p = entry.getValue();
							String t = p.getType();
							String m = p.getManufacturer();
							if(t.equalsIgnoreCase(type) && m.equalsIgnoreCase(manufacturer)){
								ArrayList<Product> list = sorted_product_map.getOrDefault(t, new ArrayList());
								list.add(p);
								sorted_product_map.put(t, list);
							}
						}
					}else{
						for(Map.Entry<String, Product> entry : product_map.entrySet()){
							Product p = entry.getValue();
							String t = p.getType();
							if(t.equalsIgnoreCase(type)){
								ArrayList<Product> list = sorted_product_map.getOrDefault(t, new ArrayList());
								list.add(p);
								sorted_product_map.put(t, list);
							}
						}
					}
					
				}else{
					
					for(Map.Entry<String, Product> entry : product_map.entrySet()){
						Product p = entry.getValue();
						String t = p.getType();
						ArrayList<Product> list = sorted_product_map.getOrDefault(t, new ArrayList());
						list.add(p);
						sorted_product_map.put(t, list);
					}
					
				}
				
				for(Map.Entry<String, ArrayList<Product>> entry: sorted_product_map.entrySet()){
					pw.write("<div id='"+entry.getKey()+"list'></div>");
					pw.write("<p><h3 class='w3-hide-small'>"+entry.getKey()+" : " + entry.getValue().size() + " products</h3></p>");
					pw.write("<div class='w3-row w3-grayscale'>");
					
					for(Product p : entry.getValue()){
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
					
					pw.write("</div>");
				}
			}
			
			pw.write("</div>");
			utility.printHtml("Footer.html");
		}
	}

}
