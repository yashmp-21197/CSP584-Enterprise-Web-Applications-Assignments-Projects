import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	/* Accessory Page Displays all the Accessories and their Information in BestDeal */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;

		/* Checks the Console maker whether it is microsft or sony or nintendo 
		   Add the respective product value to hashmap  */

		String CategoryName = request.getParameter("maker");
		String CategoryType = request.getParameter("type");

		HashMap<String, Accessory> hm = new HashMap<String, Accessory>();
		
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{
			if(entry.getValue().getRetailer().equalsIgnoreCase(CategoryName) && entry.getValue().getType().equalsIgnoreCase(CategoryType))
			{
				hm.put(entry.getValue().getId(),entry.getValue());
			}
		}
		name = "-" + CategoryType + "-" + CategoryName;
						
		/* Header, Left Navigation Bar are Printed.

		All the Accessories and Accessories information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<p><h3 class='w3-hide-small'>Accessory"+name+" : " + hm.size() + " products </h3></p>");
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, Accessory> entry : hm.entrySet()){
			Accessory accessory = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/accessory/"+accessory.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='accessories'>"+
				"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
				"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='accessories'>"+
				"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
				"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='accessories'>"+
				"<input type='hidden' name='subtype' value='"+accessory.getType()+"'>"+
				"<input type='hidden' name='maker' value='"+accessory.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+accessory.getRetailer()+"-"+accessory.getName()+"<br><b>$"+accessory.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		pw.write("</div>");
		
		utility.printHtml("Footer.html");
	}
}
