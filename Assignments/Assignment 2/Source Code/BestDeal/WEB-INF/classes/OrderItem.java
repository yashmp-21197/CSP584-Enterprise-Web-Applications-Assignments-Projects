import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

/* 
	OrderItem class contains class variables name,price,image,retailer.

	OrderItem  class has a constructor with Arguments name,price,image,retailer.
	  
	OrderItem  class contains getters and setters for name,price,image,retailer.
*/

public class OrderItem extends HttpServlet{
	
	 String userid;
	 String username;
	 String useraddress;
	 String creditcardno;
	 int orderid;
	 String purchasedate;
     String shipdate;
     String productid;
     String producttype;
     int productquantity;
	 double productprice;
     double shippingcost;
     double productdiscount;
     double totalsales;
     int storeid;
     String storeaddress;
     boolean iscancelled;
	
	public OrderItem(String userid, String username, String useraddress, String creditcardno, int orderid, String purchasedate, String shipdate, String productid, String producttype,int productquantity, double productprice, double shippingcost, double productdiscount, double totalsales, int storeid, String storeaddress, boolean iscancelled){
		this.userid = userid;
		this.username = username;
		this.useraddress = useraddress;
		this.creditcardno = creditcardno;
		this.orderid = orderid;
		this.purchasedate = purchasedate;
		this.shipdate = shipdate;
		this.productid = productid;
		this.producttype = producttype;
		this.productquantity = productquantity;
		this.productprice = productprice;
		this.shippingcost = shippingcost;
		this.productdiscount = productdiscount;
		this.totalsales = totalsales;
		this.storeid = storeid;
		this.storeaddress = storeaddress;
		this.iscancelled = iscancelled;
	}
}
