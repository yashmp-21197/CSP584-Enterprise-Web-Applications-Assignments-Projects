import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SoundSystemList")

public class SoundSystemList extends HttpServlet {

	/* SoundSystem Page Displays all the SoundSystem and their Information */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the SoundSystem type whether it is bose or logitech or jbl */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
		
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.soundsystems);
			name = "";
		}
		else
		{
			
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
			 {
				if(entry.getValue().getRetailer().equalsIgnoreCase(CategoryName))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
			name = "-" + CategoryName;
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the TV and TV information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<p><h3 class='w3-hide-small'>Sound System"+name+" : " + hm.size() + " products </h3></p>");
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, SoundSystem> entry : hm.entrySet()){
			SoundSystem soundsystem = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/soundsystem/"+soundsystem.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='soundsystems'>"+
				"<input type='hidden' name='maker' value='"+soundsystem.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='soundsystems'>"+
				"<input type='hidden' name='maker' value='"+soundsystem.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='soundsystems'>"+
				"<input type='hidden' name='maker' value='"+soundsystem.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+soundsystem.getRetailer()+"-"+soundsystem.getName()+"<br><b>$"+soundsystem.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		pw.write("</div>");
		
		utility.printHtml("Footer.html");
		
	}

}
