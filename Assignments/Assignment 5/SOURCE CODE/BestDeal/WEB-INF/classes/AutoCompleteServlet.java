import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;


import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import java.util.*;
import java.io.*;

@WebServlet("/autocomplete")

public class AutoCompleteServlet extends HttpServlet {
	
	private ServletContext context;
	String search_id = null;
	
	@Override
    public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		try{	
			String action = request.getParameter("action");
			search_id = request.getParameter("search_id");
			StringBuffer sb = new StringBuffer();
			
			if (search_id != null && action.equals("complete")) {
				search_id = search_id.trim().toLowerCase();
			} 
			
			if(search_id==null){
				context.getRequestDispatcher("/Error").forward(request, response);
			}
			
			boolean names_added = false;
			
			if (action.equals("complete")) 
			{	
				if (!search_id.equals("")) 
				{
					AjaxUtility a = new AjaxUtility();
					sb = a.readdata(search_id);
					if(sb!=null || !sb.equals(""))
					{
						names_added=true;
					}
					if(names_added) 
					{
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + sb.toString() + "</products>");
					} 
					else 
					{
						response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					}
				}
			}
			if(action.equals("lookup"))
			{
				HashMap<String,Product> data = AjaxUtility.getData();     	
				if((search_id != null) && data.containsKey(search_id.trim())) 
				{	
					RequestDispatcher rd = context.getRequestDispatcher("/Home?product_id="+search_id.trim());
					rd.forward(request,response);
				}
			}
		}
		catch(Exception e){}
	}
}
