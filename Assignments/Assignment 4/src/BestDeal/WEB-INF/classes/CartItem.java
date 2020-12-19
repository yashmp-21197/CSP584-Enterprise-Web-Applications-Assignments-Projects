import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrdersHashMap")

public class CartItem extends HttpServlet{
	
	public static HashMap<String, HashMap<String, Integer>> items = new HashMap<String, HashMap<String, Integer>>();
	
}
