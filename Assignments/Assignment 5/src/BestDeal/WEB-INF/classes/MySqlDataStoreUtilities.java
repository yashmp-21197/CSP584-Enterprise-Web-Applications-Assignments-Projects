import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
	static Connection conn = null;

	public static void getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal?useSSL=false","root","root");
		}
		catch(Exception e)
		{
			System.out.println("GET CONN : " + e.toString());
		}
	}


	public static boolean insertUser(String username,String password,String repassword,String usertype)
	{
		try
		{
			getConnection();
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
			+ "VALUES (?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,repassword);
			pst.setString(4,usertype);
			pst.execute();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("INSERT USER : " + e.toString());
			return false;
		}	
	}

	public static HashMap<String,User> getUsers()
	{
		try 
		{
			HashMap<String,User> hm=new HashMap<String,User>();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
					hm.put(rs.getString("username"), user);
			}
			return hm;
		}
		catch(Exception e)
		{
			System.out.println("GET USERS : " + e.toString());
			return null;
		}
	}

	public static HashMap<String, Product> getProducts(){
		try 
		{
			HashMap<String,Product> hm=new HashMap<String,Product>();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectProductQuery="select * from  Productdetails";
			ResultSet rs = stmt.executeQuery(selectProductQuery);
			while(rs.next())
			{	
				Product p = new Product(rs.getString("id"),rs.getString("producttype"),rs.getString("productname"),rs.getDouble("productprice"),rs.getString("productimage"),rs.getString("productmanufacturer"),rs.getString("productcondition"),rs.getDouble("productdiscount"),rs.getBoolean("productonsale"),rs.getInt("productcount"));
				hm.put(rs.getString("id"), p);
			}
			return hm;
		}
		catch(Exception e)
		{
			System.out.println("GET PRODUCTS : " + e.toString());
			return null;
		}
	}
	
	public static HashMap<String, ArrayList<String>> getAccessories(){
		try 
		{
			HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectAccessoriesQuery="select * from Productaccessories";
			ResultSet rs = stmt.executeQuery(selectAccessoriesQuery);
			while(rs.next())
			{
				String productname = rs.getString("productname");
				String accessoryname = rs.getString("accessoryname");
				ArrayList<String> list = hm.getOrDefault(productname, new ArrayList<String>());
				list.add(accessoryname);
				hm.put(productname, list);
			}
			return hm;
		}
		catch(Exception e)
		{
			System.out.println("GET ACCESSORIES : " + e.toString());
			return null;
		}
	}
	
	public static HashMap<Integer,String> getStoreLocations(){
		try{
			HashMap<Integer,String> loc = new HashMap();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectStoreLocationsQuery="select * from locations";
			ResultSet rs = stmt.executeQuery(selectStoreLocationsQuery);
			while(rs.next())
			{
				int id = rs.getInt("storeid");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				
				String address = street + ", " + city + ", " + state + ", " + zip;
				loc.put(id, address);
			}
			return loc;
		}catch(Exception e){
			System.out.println("GET STORE LOC : " + e.toString());
			return null;
		}
	}
	
	public static HashMap<Integer,StoreLocation> getStoreLocationObjs(){
		try{
			HashMap<Integer,StoreLocation> loc = new HashMap();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectStoreLocationsQuery="select * from locations";
			ResultSet rs = stmt.executeQuery(selectStoreLocationsQuery);
			while(rs.next())
			{
				int id = rs.getInt("storeid");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				
				StoreLocation sl = new StoreLocation(id, street, city, state, zip);
				loc.put(id, sl);
			}
			return loc;
		}catch(Exception e){
			System.out.println("GET STORE LOC : " + e.toString());
			return null;
		}
	}
	
	public static HashMap<Integer, ArrayList<OrderItem>> getAllOrders(){
		try{
			HashMap<Integer,ArrayList<OrderItem>> orders = new HashMap();
			getConnection();
			Statement stmt=conn.createStatement();
			String selectOrdersQuery="select * from Orders";
			ResultSet rs = stmt.executeQuery(selectOrdersQuery);
			while(rs.next())
			{
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				String useroccupation = rs.getString("useroccupation");
				int userage = rs.getInt("userage");
				String useraddress = rs.getString("useraddress");
				String creditcardno = rs.getString("creditcardno");
				int orderid = rs.getInt("orderid");
				String purchasedate = rs.getString("purchasedate");
				String shipdate = rs.getString("shipdate");
				String actualshipdate = rs.getString("actualshipdate");
				String productid = rs.getString("productid");
				String productname = rs.getString("productname");
				String producttype = rs.getString("producttype");
				String productmanufacturer = rs.getString("productmanufacturer");
				int productquantity = rs.getInt("productquantity");
				double productprice = rs.getDouble("productprice");
				double shippingcost = rs.getDouble("shippingcost");
				double productdiscount = rs.getDouble("productdiscount");
				double totalsales = rs.getDouble("totalsales");
				double reviewrating = rs.getDouble("reviewrating");
				String trackingid = rs.getString("trackingid");
				int storeid = rs.getInt("storeid");
				String storeaddress = rs.getString("storeaddress");
				String zipcode = rs.getString("zipcode");
				String transactionstatus = rs.getString("transactionstatus");
				boolean iscancelled = rs.getBoolean("iscancelled");
				boolean isreturned = rs.getBoolean("isreturned");
				boolean isdeliveredontime = rs.getBoolean("isdeliveredontime");
				
				OrderItem oi = new OrderItem(userid, username, useroccupation, userage, useraddress, creditcardno, orderid, purchasedate, shipdate, actualshipdate, productid, productname, producttype, productmanufacturer, productquantity, productprice, shippingcost, productdiscount, totalsales, reviewrating, trackingid, storeid, storeaddress, zipcode, transactionstatus, iscancelled, isreturned, isdeliveredontime);
				
				ArrayList<OrderItem> list = orders.getOrDefault(orderid, new ArrayList());
				list.add(oi);
				orders.put(orderid, list);
			}
			return orders;
		}catch(Exception e){
			System.out.println("GET STORE LOC : " + e.toString());
			return null;
		}
	}
	
	public static boolean insertOrderItem(OrderItem oi){
		try
		{
			getConnection();
			String insertOrderItemQuery = "INSERT INTO Orders(userid,username,useroccupation,userage,useraddress,creditcardno,orderid,purchasedate,shipdate,actualshipdate,productid,productname,producttype,productmanufacturer,productquantity,productprice,shippingcost,productdiscount,totalsales,reviewrating,trackingid,storeid,storeaddress,zipcode,transactionstatus,iscancelled,isreturned,isdeliveredontime) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertOrderItemQuery);
			pst.setString(1,oi.userid);
			pst.setString(2,oi.username);
			pst.setString(3,oi.useroccupation);
			pst.setInt(4,oi.userage);
			pst.setString(5,oi.useraddress);
			pst.setString(6,oi.creditcardno);
			pst.setInt(7,oi.orderid);
			pst.setString(8,oi.purchasedate);
			pst.setString(9,oi.shipdate);
			pst.setString(10,oi.actualshipdate);
			pst.setString(11,oi.productid);
			pst.setString(12,oi.productname);
			pst.setString(13,oi.producttype);
			pst.setString(14,oi.productmanufacturer);
			pst.setInt(15,oi.productquantity);
			pst.setDouble(16,oi.productprice);
			pst.setDouble(17,oi.shippingcost);
			pst.setDouble(18,oi.productdiscount);
			pst.setDouble(19,oi.totalsales);
			pst.setDouble(20,oi.reviewrating);
			pst.setString(21,oi.trackingid);
			pst.setInt(22,oi.storeid);
			pst.setString(23,oi.storeaddress);
			pst.setString(24,oi.zipcode);
			pst.setString(25,oi.transactionstatus);
			pst.setBoolean(26,oi.iscancelled);
			pst.setBoolean(27,oi.isreturned);
			pst.setBoolean(28,oi.isdeliveredontime);
			pst.execute();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("INSERT ORDER : " + e.toString());
			return false;
		}
	}
	
	public static boolean cancelOrderItem(int orderid, String productid){
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();	
			String cancelOrderItemQuery = "update orders set iscancelled=1 where orderid="+orderid+" and productid='"+productid+"';";
			stmt.executeUpdate(cancelOrderItemQuery);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("CANCEL ORDER : " + e.toString());
			return false;
		}
	}
	
	public static boolean updateOrderItem(OrderItem oi){
		try
		{	
			getConnection();
			String updateProductQuery = "UPDATE orders SET userid=?,username=?,useroccupation=?,userage=?,useraddress=?,creditcardno=?,purchasedate=?,shipdate=?,actualshipdate=?,productname=?,producttype=?,productmanufacturer=?,productquantity=?,productprice=?,shippingcost=?,productdiscount=?,totalsales=?,reviewrating=?,trackingid=?,storeid=?,storeaddress=?,zipcode=?,transactionstatus=?,iscancelled=?,isreturned=?,isdeliveredontime=? where orderid=? and productid=?;";
			PreparedStatement pst = conn.prepareStatement(updateProductQuery);
			
			pst.setString(1,oi.userid);
			pst.setString(2,oi.username);
			pst.setString(3,oi.useroccupation);
			pst.setInt(4,oi.userage);
			pst.setString(5,oi.useraddress);
			pst.setString(6,oi.creditcardno);
			pst.setString(7,oi.purchasedate);
			pst.setString(8,oi.shipdate);
			pst.setString(9,oi.actualshipdate);
			pst.setString(10,oi.productname);
			pst.setString(11,oi.producttype);
			pst.setString(12,oi.productmanufacturer);
			pst.setInt(13,oi.productquantity);
			pst.setDouble(14,oi.productprice);
			pst.setDouble(15,oi.shippingcost);
			pst.setDouble(16,oi.productdiscount);
			pst.setDouble(17,oi.totalsales);
			pst.setDouble(18,oi.reviewrating);
			pst.setString(19,oi.trackingid);
			pst.setInt(20,oi.storeid);
			pst.setString(21,oi.storeaddress);
			pst.setString(22,oi.zipcode);
			pst.setString(23,oi.transactionstatus);
			pst.setBoolean(24,oi.iscancelled);
			pst.setBoolean(25,oi.isreturned);
			pst.setBoolean(26,oi.isdeliveredontime);
			
			pst.setInt(27,oi.orderid);
			pst.setString(28,oi.productid);
			
			pst.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("UPDATE ORDER : " + e.toString());
			return false;
		}
	}
	
	public static boolean addProduct(Product p){
		
		try{
			getConnection();
			String addProductQuery = "INSERT INTO Productdetails(id, producttype, productname, productprice, productimage, productmanufacturer, productcondition, productdiscount, productonsale, productcount) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
					
			PreparedStatement pst = conn.prepareStatement(addProductQuery);
			pst.setString(1,p.getId());
			pst.setString(2,p.getType());
			pst.setString(3,p.getName());
			pst.setDouble(4,p.getPrice());
			pst.setString(5,p.getImage());
			pst.setString(6,p.getManufacturer());
			pst.setString(7,p.getCondition());
			pst.setDouble(8,p.getDiscount());
			pst.setBoolean(9,p.getOnsale());
			pst.setInt(10,p.getCount());
			pst.execute();
			return true;
		}catch(Exception e){
			System.out.println("ADD PROD : " + e.toString());
			return false;
		}
		
	}
	
	public static boolean updateProduct(Product p){
		
		try{
			getConnection();
			String updateProductQuery = "UPDATE Productdetails SET producttype=?,productname=?,productprice=?,productimage=?,productmanufacturer=?,productcondition=?,productdiscount=?,productonsale=?,productcount=? where id=?;";
			PreparedStatement pst = conn.prepareStatement(updateProductQuery);
			pst.setString(1,p.getType());
			pst.setString(2,p.getName());
			pst.setDouble(3,p.getPrice());
			pst.setString(4,p.getImage());
			pst.setString(5,p.getManufacturer());
			pst.setString(6,p.getCondition());
			pst.setDouble(7,p.getDiscount());
			pst.setBoolean(8,p.getOnsale());
			pst.setInt(9,p.getCount());
			pst.setString(10,p.getId());
			pst.executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println("UPDATE PROD : " + e.toString());
			return false;
		}
		
	}
	
	public static boolean deleteProduct(Product p){
		
		try{
			getConnection();
			String deleteProductQuery ="Delete from Productdetails where id=?;";
			PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
			pst.setString(1,p.getId());
			pst.executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println("DELETE PROD : " + e.toString());
			return false;
		}
		
	}
	
}	