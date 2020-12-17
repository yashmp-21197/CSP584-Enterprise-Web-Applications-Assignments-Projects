import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

public class OrderItem extends HttpServlet{
	
	String userid;
	String username;
	String useroccupation;
	int userage;
	String useraddress;
	String creditcardno;
	int orderid;
	String purchasedate;
    String shipdate;
	String actualshipdate;
    String productid;
	String productname;
    String producttype;
	String productmanufacturer;
    int productquantity;
	double productprice;
    double shippingcost;
    double productdiscount;
    double totalsales;
	double reviewrating;
	String trackingid;
    int storeid;
    String storeaddress;
	String zipcode;
	String transactionstatus;
    boolean iscancelled;
	boolean isreturned;
	boolean isdeliveredontime;
	
	public OrderItem(
		String userid,
		String username,
		String useroccupation,
		int userage,
		String useraddress,
		String creditcardno,
		int orderid,
		String purchasedate,
		String shipdate,
		String actualshipdate,
		String productid,
		String productname,
		String producttype,
		String productmanufacturer,
		int productquantity,
		double productprice,
		double shippingcost,
		double productdiscount,
		double totalsales,
		double reviewrating,
		String trackingid,
		int storeid,
		String storeaddress,
		String zipcode,
		String transactionstatus,
		boolean iscancelled,
		boolean isreturned,
		boolean isdeliveredontime
	){
		this.userid = userid;
		this.username = username;
		this.useroccupation = useroccupation;
		this.userage = userage;
		this.useraddress = useraddress;
		this.creditcardno = creditcardno;
		this.orderid = orderid;
		this.purchasedate = purchasedate;
		this.shipdate = shipdate;
		this.actualshipdate = actualshipdate;
		this.productid = productid;
		this.productname = productname;
		this.producttype = producttype;
		this.productmanufacturer = productmanufacturer;
		this.productquantity = productquantity;
		this.productprice = productprice;
		this.shippingcost = shippingcost;
		this.productdiscount = productdiscount;
		this.totalsales = totalsales;
		this.reviewrating = reviewrating;
		this.trackingid = trackingid;
		this.storeid = storeid;
		this.storeaddress = storeaddress;
		this.zipcode = zipcode;
		this.transactionstatus = transactionstatus;
		this.iscancelled = iscancelled;
		this.isreturned = isreturned;
		this.isdeliveredontime = isdeliveredontime;
	}
}
