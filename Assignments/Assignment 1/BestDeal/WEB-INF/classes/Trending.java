import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Consoles type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Trending";
		String CategoryName = request.getParameter("maker");
		
		
		HashMap<String, TV> hm_tv = new HashMap<String, TV>();
		HashMap<String, SoundSystem> hm_ss = new HashMap<String, SoundSystem>();
		HashMap<String, Phone> hm_p = new HashMap<String, Phone>();
		HashMap<String, Laptop> hm_l = new HashMap<String, Laptop>();
		HashMap<String, VoiceAssistant> hm_va = new HashMap<String, VoiceAssistant>();

		if(CategoryName==null)
		{
			hm_tv.putAll(SaxParserDataStore.tvs);
			hm_ss.putAll(SaxParserDataStore.soundsystems);
			hm_p.putAll(SaxParserDataStore.phones);
			hm_l.putAll(SaxParserDataStore.laptops);
			hm_va.putAll(SaxParserDataStore.voiceassistants);
		}
		else
		{

		}
		

		/* Header, Left Navigation Bar are Printed.

		All the consoles and Console information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<p><h3 class='w3-hide-small'>Trending : " + (hm_tv.size()+hm_ss.size()+hm_p.size()+hm_l.size()+hm_va.size()) + " products </h3></p>");
		
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, TV> entry : hm_tv.entrySet()){
			TV tv = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/tv/"+tv.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='tvs'>"+
				"<input type='hidden' name='maker' value='"+tv.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='tvs'>"+
				"<input type='hidden' name='maker' value='"+tv.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='tvs'>"+
				"<input type='hidden' name='maker' value='"+tv.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+tv.getRetailer()+"-"+tv.getName()+"<br><b>$"+tv.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		
		
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, SoundSystem> entry : hm_ss.entrySet()){
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
		
		
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, Phone> entry : hm_p.entrySet()){
			Phone phone = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/phone/"+phone.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='phones'>"+
				"<input type='hidden' name='maker' value='"+phone.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='phones'>"+
				"<input type='hidden' name='maker' value='"+phone.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='phones'>"+
				"<input type='hidden' name='maker' value='"+phone.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+phone.getRetailer()+"-"+phone.getName()+"<br><b>$"+phone.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		
		
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, Laptop> entry : hm_l.entrySet()){
			Laptop laptop = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/laptop/"+laptop.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='laptops'>"+
				"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='laptops'>"+
				"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='laptops'>"+
				"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+laptop.getRetailer()+"-"+laptop.getName()+"<br><b>$"+laptop.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		
		
		pw.write("<div class='w3-row w3-grayscale'>");
		for(Map.Entry<String, VoiceAssistant> entry : hm_va.entrySet()){
			VoiceAssistant voiceassistant = entry.getValue();
			pw.write(
				"<div class='w3-col l3 s6'>"+"<div class='w3-container'>"+"<div class='w3-display-container'>"+
				"<img src='images/voiceassistant/"+voiceassistant.getImage()+"' style='width:100%'>"+
				"<div class='w3-display-middle w3-display-hover'>"+
				"<form action='Cart' method='post'>"+
				"<input type='submit' class='btnbuy w3-button w3-black' value='Buy Now' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='voiceassistants'>"+
				"<input type='hidden' name='maker' value='"+voiceassistant.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='WriteReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='WriteReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='voiceassistants'>"+
				"<input type='hidden' name='maker' value='"+voiceassistant.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"<form action='ViewReview' method='post'>"+
				"<input type='submit' class='btnreview w3-button w3-black' value='ViewReview' style='width:100%'>"+
				"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				"<input type='hidden' name='type' value='voiceassistants'>"+
				"<input type='hidden' name='maker' value='"+voiceassistant.getRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"</form>"+
				"</div></div>"+
				"<p>"+voiceassistant.getRetailer()+"-"+voiceassistant.getName()+"<br><b>$"+voiceassistant.getPrice()+"</b></p>"+
				"</div></div>"
			);
		}
		pw.write("</div>");
		
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
