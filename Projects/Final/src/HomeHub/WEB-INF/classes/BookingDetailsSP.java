import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/BookingDetailsSP")


public class BookingDetailsSP extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		HttpSession session = request.getSession(true);

		utility.printHtml("Head.html");
		utility.printSPHeader();
		utility.printSPLeftNav();

		String action = request.getParameter("action");
		int transId = Integer.parseInt( request.getParameter("transId"));
		// Update the booking date
		if( action.equals("confirm") ){
			// Action  is confirm
			boolean updateOprtn = MySqlDataStoreUtilities.updateBookingDateSP(transId, request.getParameter("confirmed-booking-date"));

			if( updateOprtn )
				pw.print("<h1> Booking Updated.  </h1>");
			else
				pw.print("<h1> Operation failed. Try again </h1>");
		} else {
			// Cancel the transaction
			boolean cancelOprtn = MySqlDataStoreUtilities.cancelBookingSP(transId);
			if( cancelOprtn )
				pw.print("<h1> Booking cancelled  </h1>");
			else
				pw.print("<h1> Operation failed. Try again </h1>");

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		if(utility.isLoggedin() && (utility.user_type().equals("service_provider"))) {
			if(!utility.user_info_set()) {
				response.sendRedirect("MoreInfo");
			return;
			} else {
				displayTransactionDetails(request, response, pw);
			}
		} else {
			response.sendRedirect("Login");
			return;
		}

	}

	protected void displayTransactionDetails(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)throws ServletException, IOException {

    Utilities utility = new Utilities(request, pw);
    HttpSession session = request.getSession(true);

    utility.printHtml("Head.html");
    utility.printSPHeader();
    utility.printSPLeftNav();

	int transaction_id = Integer.parseInt(request.getParameter("transaction_id"));

    HashMap<Integer, Transaction> hm = MySqlDataStoreUtilities.getTransactionByID(transaction_id);

    if(hm!=null){
      Transaction transaction = hm.get(transaction_id);

      pw.print("<div class='container-fluid text-center'>");
      pw.print("<div class='row content'>");

      pw.print("<div class='col-sm-4 text-left w3-card w3-round w3-white user-profile-sec' id='user-profile-sec'>");
      pw.print("<div class='w3-container '>");
      pw.print("<h4 class='w3-center'>Booking details</h4>");
      pw.print("<p class='w3-center'><img src='images/profile/avatar3.png' class='w3-circle' style='height:106px;width:106px' alt='Avatar'></p>");
      pw.print("<hr>");
      pw.print("<label>Customer Name : "+transaction.customer_name+"</label><br>");
	  pw.print("<label>Customer Email : "+transaction.customer_email+"</label><br>");
	  pw.print("<label>Customer Phone : "+transaction.customer_phone+"</label><br>");
	  String address = transaction.customer_street1 + transaction.customer_street2 + transaction.customer_city + transaction.customer_state + transaction.customer_zip + transaction.customer_country;
	  pw.print("<label>Customer Address : "+address+"</label><br>");
	  pw.print("<label>Service Name : "+transaction.service_name+"</label><br>");
		pw.print("<label>Service Category : "+transaction.service_category_super+"</label><br>");
	  pw.print("<label>Service Sub-Category : "+transaction.service_category_sub+"</label><br>");
	  pw.print("<label>Request no. of hours :"+transaction.service_hours+"</label><br>");
	  pw.print("<label>booking date :"+transaction.booking_date+"</label><br>");
		pw.print("<label>Service date :"+transaction.service_date+"</label><br>");
		pw.print("<label>Actual Service date :"+transaction.actual_service_date+"</label><br>");
		pw.print("<label>Transaction Status :"+transaction.transaction_status+"</label><br>");
		pw.print("<label>Service Status :"+transaction.service_status+"</label><br>");
		pw.print("<label>Is Cancelled :"+transaction.is_cancelled+"</label><br>");
		pw.print("<label>Is Delivered On Time :"+transaction.is_delivered_on_time+"</label><br>");
      pw.print("</div>");
      pw.print("</div>");

      pw.print("<div class='col-sm-4 text-left w3-card w3-round w3-white user-profile-map-sec' id='user-profile-map-sec'>");
      pw.print("<div class='w3-container '>");
      pw.print("<div id='google_map' style='width:100%;height:550px;'>");

	  // Transaction is pending needs a date
		if(transaction.is_cancelled == false){
			if(  transaction.service_status.equalsIgnoreCase("pending") ){
			  pw.print("<h1> Booking date is not confirmed. Update booking date </h1>");
			  pw.print("<form method='post' action='BookingDetailsSP'>");
			  pw.print("<input type='date' required name='confirmed-booking-date'>");
			  pw.print("<input type='hidden' name='action' value='confirm'>");
			  pw.print("<input type='hidden' name='transId' value='"+transaction_id+"'>");
			  pw.print("<input type='submit' class='w3-button w3-block w3-green w3-section' value='Confirm Booking'/></form>");
			  pw.print("<form method='post' action='BookingDetailsSP'>");
			  pw.print("<input type='hidden' name='action' value='cancel'>");
			  pw.print("<input type='hidden' name='transId' value='"+transaction_id+"'>");
			  pw.print("<input type='submit' class='w3-button w3-block w3-red w3-section' value='Cancel Booking'/></form>");
		  } else if( transaction.service_status.equalsIgnoreCase("accepted") ){
			  pw.print("<h1> Booking date is confirmed. Want t change ? </h1>");
			  pw.print("<form method='post' action='BookingDetailsSP'>");
			  pw.print("<input type='date' required name='confirmed-booking-date'>");
			  pw.print("<input type='hidden' name='action' value='confirm'>");
			  pw.print("<input type='hidden' name='transId' value='"+transaction_id+"'>");
			  pw.print("<input type='submit' class='w3-button w3-block w3-green w3-section' value='Update Booking'/></form>");
			  pw.print("<form method='post' action='BookingDetailsSP'>");
			  pw.print("<input type='hidden' name='action' value='cancel'>");
			  pw.print("<input type='hidden' name='transId' value='"+transaction_id+"'>");
			  pw.print("<input type='submit' class='w3-button w3-block w3-red w3-section' value='Cancel Booking'/></form>");
		  } else {
			  pw.print("<h1> Booking is "+transaction.service_status+"</h1>");
		  }
		} else {
			pw.print("<h1> Booking is cancelled by customer.</h1>");
		}


      pw.print("</div></div>");
      pw.print("</div>");

      pw.print("</div>");
      pw.print("</div>");
      pw.print("</div>");

    }else{
      pw.print("<h4 style='margin-left:5%;color:red;'>MySQL server is not up and running.</h4>");
    }

		utility.printHtml("Footer.html");

	}

}
