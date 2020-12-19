import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/DataVisualization")
public class DataVisualization extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Head.html");
		utility.printSMHeader();
		utility.printSMLeftNavBar();
		utility.printHtml("LeftNavigationBar.html");
		pw.write("<div class='w3-main' style='margin-left:250px'>");
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("inventory")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Data Visualization - Inventory</h1>");
			pw.write("</div>");
			
			pw.print("<h3><button id='btnTableProductInventory' class='btnreview w3-button w3-black'>View Table of Product Inventory</h3>");
			pw.println("<div id='table_product_inventory' style='display:none;'></div>");
			
			pw.print("<h3><button id='btnChartProductInventory' class='btnreview w3-button w3-black'>View Chart of Product Inventory</h3>");
			pw.println("<div id='chart_product_inventory' style='display:none;'></div>");
			
			pw.print("<h3><button id='btnTableProductOnSale' class='btnreview w3-button w3-black'>View Table of Product on Sale</h3>");
			pw.println("<div id='table_product_on_sale' style='display:none;'></div>");
			
			pw.print("<h3><button id='btnTableProductHavingRebate' class='btnreview w3-button w3-black'>View Table of Product having Rebate</h3>");
			pw.println("<div id='table_product_having_rebate' style='display:none;'></div>");
			
		}else if(action.equalsIgnoreCase("sales_reports")){
			pw.write("<div class='w3-container w3-text-grey' id='all_content'>");
			pw.write("<h1 class='w3-hide-small'>Data Visualization - Sales Reports</h1>");
			pw.write("</div>");
			
			pw.print("<h3><button id='btnTableProductSold' class='btnreview w3-button w3-black'>View Table of Product Sold</h3>");
			pw.println("<div id='table_product_sold' style='display:none;'></div>");
			
			pw.print("<h3><button id='btnChartProductSold' class='btnreview w3-button w3-black'>View Chart of Product Sold</h3>");
			pw.println("<div id='chart_product_Sold' style='display:none;'></div>");
			
			pw.print("<h3><button id='btnTableDailySalesTransaction' class='btnreview w3-button w3-black'>View Table of Daily Sales Transaction</h3>");
			pw.println("<div id='table_daily_sales_transaction' style='display:none;'></div>");
			
		}
		
		pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
		pw.write("</div>");
		utility.printHtml("Footer.html");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
			
			String action = request.getParameter("action");
			
			String dataJson = null;
			
			if(action.equalsIgnoreCase("table_product_inventory")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				ArrayList<Product> list_products = new ArrayList();
				for(Map.Entry<String, Product> entry : map_products.entrySet()){
					list_products.add(entry.getValue());
				}
				dataJson = new Gson().toJson(list_products);
			}
			if(action.equalsIgnoreCase("chart_product_inventory")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				ArrayList<Product> list_products = new ArrayList();
				for(Map.Entry<String, Product> entry : map_products.entrySet()){
					list_products.add(entry.getValue());
				}
				dataJson = new Gson().toJson(list_products);
			}
			if(action.equalsIgnoreCase("table_product_on_sale")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				ArrayList<Product> list_products = new ArrayList();
				for(Map.Entry<String, Product> entry : map_products.entrySet()){
					if(entry.getValue().getOnsale() == true){
						list_products.add(entry.getValue());
					}
				}
				dataJson = new Gson().toJson(list_products);
			}
			if(action.equalsIgnoreCase("table_product_having_rebate")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				ArrayList<Product> list_products = new ArrayList();
				for(Map.Entry<String, Product> entry : map_products.entrySet()){
					if(entry.getValue().getDiscount() > 0.0){
						list_products.add(entry.getValue());
					}
				}
				dataJson = new Gson().toJson(list_products);
			}
			if(action.equalsIgnoreCase("table_product_sold")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				HashMap<Integer, ArrayList<OrderItem>> map_orders = MySqlDataStoreUtilities.getAllOrders();
				
				HashMap<String, HashMap<String, Object>> map_product_orders = new HashMap();
				
				for(Map.Entry<Integer, ArrayList<OrderItem>> entry : map_orders.entrySet()){
					for(OrderItem oi : entry.getValue()){
						if(oi.iscancelled == false){
							String product_name = map_products.get(oi.productid).getName();
							double product_price = map_products.get(oi.productid).getPrice();
							int product_quentity = oi.productquantity;
							double product_sales = oi.totalsales;
							
							HashMap<String, Object> map_product = map_product_orders.getOrDefault(product_name, new HashMap());
							map_product.put("name", product_name);
							map_product.put("price", product_price);
							map_product.put("quentity", (int)map_product.getOrDefault("quentity", 0) + product_quentity);
							map_product.put("sales", (double)map_product.getOrDefault("sales", 0.0) + product_sales);
							map_product_orders.put(product_name, map_product);	
						}
					}
				}
				
				ArrayList<HashMap<String, Object>> list_product_orders = new ArrayList();
				for(Map.Entry<String,HashMap<String, Object>> entry : map_product_orders.entrySet()){
					list_product_orders.add(entry.getValue());
				}
				dataJson = new Gson().toJson(list_product_orders);
			}
			if(action.equalsIgnoreCase("chart_product_Sold")){
				HashMap<String, Product> map_products = MySqlDataStoreUtilities.getProducts();
				HashMap<Integer, ArrayList<OrderItem>> map_orders = MySqlDataStoreUtilities.getAllOrders();
				
				HashMap<String, HashMap<String, Object>> map_product_orders = new HashMap();
				
				for(Map.Entry<Integer, ArrayList<OrderItem>> entry : map_orders.entrySet()){
					for(OrderItem oi : entry.getValue()){
						if(oi.iscancelled == false){
							String product_name = map_products.get(oi.productid).getName();
							double product_sales = oi.totalsales;
							
							HashMap<String, Object> map_product = map_product_orders.getOrDefault(product_name, new HashMap());
							map_product.put("name", product_name);
							map_product.put("sales", (double)map_product.getOrDefault("sales", 0.0) + product_sales);
							map_product_orders.put(product_name, map_product);	
						}
					}
				}
				
				ArrayList<HashMap<String, Object>> list_product_orders = new ArrayList();
				for(Map.Entry<String,HashMap<String, Object>> entry : map_product_orders.entrySet()){
					list_product_orders.add(entry.getValue());
				}
				dataJson = new Gson().toJson(list_product_orders);
			}
			if(action.equalsIgnoreCase("table_daily_sales_transaction")){
				HashMap<Integer, ArrayList<OrderItem>> map_orders = MySqlDataStoreUtilities.getAllOrders();
				
				HashMap<String, HashMap<String, Object>> map_daily_sales = new HashMap();
				
				for(Map.Entry<Integer, ArrayList<OrderItem>> entry : map_orders.entrySet()){
					for(OrderItem oi : entry.getValue()){
						if(oi.iscancelled == false){
							String date = oi.purchasedate;
							double sales = oi.totalsales;
							HashMap<String, Object> map_ds = map_daily_sales.getOrDefault(date, new HashMap());
							map_ds.put("date", date);
							map_ds.put("sales", (double)map_ds.getOrDefault("sales", 0.0) + sales);
							map_daily_sales.put(date, map_ds);
						}
					}
				}
				
				ArrayList<HashMap<String, Object>> list_daily_sales = new ArrayList();
				for(Map.Entry<String,HashMap<String, Object>> entry : map_daily_sales.entrySet()){
					list_daily_sales.add(entry.getValue());
				}
				dataJson = new Gson().toJson(list_daily_sales);
			}

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(dataJson);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
