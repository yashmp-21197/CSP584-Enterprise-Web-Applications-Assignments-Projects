import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {
	
	private String error_msg;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and 
		password are validated and added to session variable and display Login Function is called */

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		
		System.out.println(username + " " + password + " " + usertype);
				
		HashMap<String, User> hm=MySqlDataStoreUtilities.getUsers();
		
		if(hm==null){
			error_msg = "MySQL server is not up and running.";
		}else{
			User user = hm.get(username);
		
			if(user!=null)
			{
			 String user_password = user.getPassword();
			 String user_type = user.getUsertype();
			 if (password.equals(user_password) && usertype.equals(user_type)) 
				{
				HttpSession session = request.getSession(true);
				session.setAttribute("username", user.getName());
				session.setAttribute("usertype", user.getUsertype());
				response.sendRedirect("Home");
				return;
				}else{
					error_msg = "Please check your username, password and user type!";
				}
			}else{
				error_msg = "Please check your username, password and user type!";
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	protected void displayLogin(HttpServletRequest request,HttpServletResponse response, PrintWriter pw, boolean error)throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Head.html");
		utility.printHeader();
		utility.printHtml("LeftNavigationBar1.html");
		
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
		pw.write("<h1 class='w3-hide-small'>LOGIN</h1>");
		pw.write("</div>");
				
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form method='post' action='Login'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='store manager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy w3-button w3-black' value='Login'></input>"
				+ "</td></tr>"
				+ "</table>"
				+ "</form>");
		
		
		pw.write("</div>");
		utility.printHtml("Footer.html");
	}

}
