import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Registration")

public class Registration extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String usertype = request.getParameter("usertype");
		
		System.out.println(username + "  " + usertype + "  " + password + "  " + repassword);

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{
			HashMap<String, User> hm = MySqlDataStoreUtilities.getUsers();
			
			if(hm == null){
				error_msg = "MySQL server is not up and running.";
			}else{
				if(hm.containsKey(username))
					error_msg = "Username already exist.";
				else
				{
					User user = new User(username,password,usertype);
					hm.put(username, user);
					MySqlDataStoreUtilities.insertUser(username, password, repassword, usertype);
					HttpSession session = request.getSession(true);				
					session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
					if(!utility.isLoggedin()){
						response.sendRedirect("Login"); return;
					} else {
						response.sendRedirect("Account"); return;
					}
				}
			}
		}

		//display the error message
		if(utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", error_msg);
			response.sendRedirect("Account"); return;
		}
		displayRegistration(request, response, pw, true);
		
	}

	/*  displayRegistration function displays the Registration page of New User */
	
	protected void displayRegistration(HttpServletRequest request,HttpServletResponse response, PrintWriter pw, boolean error)throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Head.html");
		utility.printHeader();
		utility.printHtml("LeftNavigationBar1.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>SIGNUP</h1>");
		pw.write("</div>");

		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		
		pw.print("<form method='post' action='Registration'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='store manager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy w3-button w3-black' name='ByUser' value='Create User'></input>"
				+ "</td></tr>"
				+ "</table>"
				+ "</form>");
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}
}
