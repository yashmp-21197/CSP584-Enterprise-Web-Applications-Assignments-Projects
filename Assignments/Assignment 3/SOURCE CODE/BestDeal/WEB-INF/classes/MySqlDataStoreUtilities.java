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
			{	Product p = new Product(rs.getString("id"),rs.getString("producttype"),rs.getString("productname"),rs.getDouble("productprice"),rs.getString("productimage"),rs.getString("productmanufacturer"),rs.getString("productcondition"),rs.getDouble("productdiscount"),rs.getBoolean("productonsale"),rs.getInt("productcount"));
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
	
	public static HashMap<Integer, ArrayList<OrderItem>> getOrders(){
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
				 String useraddress = rs.getString("useraddress");
				 String creditcardno = rs.getString("creditcardno");
				 int orderid = rs.getInt("orderid");
				 String purchasedate = rs.getString("purchasedate");
				 String shipdate = rs.getString("shipdate");
				 String productid = rs.getString("productid");
				 String producttype = rs.getString("producttype");
				 int productquantity = rs.getInt("productquantity");
				 double productprice = rs.getDouble("productprice");
				 double shippingcost = rs.getDouble("shippingcost");
				 double productdiscount = rs.getDouble("productdiscount");
				 double totalsales = rs.getDouble("totalsales");
				 int storeid = rs.getInt("storeid");
				 String storeaddress = rs.getString("storeaddress");
				 boolean iscancelled = rs.getBoolean("iscancelled");
				
				OrderItem oi = new OrderItem(userid, username, useraddress, creditcardno, orderid, purchasedate, shipdate, productid, producttype, productquantity,productprice, shippingcost, productdiscount, totalsales, storeid, storeaddress, iscancelled);
				
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
			String insertOrderItemQuery = "INSERT INTO Orders(userid,username,useraddress,creditcardno,orderid,purchasedate,shipdate,productid,producttype,productquantity,productprice,shippingcost,productdiscount,totalsales,storeid,storeaddress,iscancelled) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertOrderItemQuery);
			pst.setString(1,oi.userid);
			pst.setString(2,oi.username);
			pst.setString(3,oi.useraddress);
			pst.setString(4,oi.creditcardno);
			pst.setInt(5,oi.orderid);
			pst.setString(6,oi.purchasedate);
			pst.setString(7,oi.shipdate);
			pst.setString(8,oi.productid);
			pst.setString(9,oi.producttype);
			pst.setInt(10,oi.productquantity);
			pst.setDouble(11,oi.productprice);
			pst.setDouble(12,oi.shippingcost);
			pst.setDouble(13,oi.productdiscount);
			pst.setDouble(14,oi.totalsales);
			pst.setInt(15,oi.storeid);
			pst.setString(16,oi.storeaddress);
			pst.setBoolean(17,oi.iscancelled);
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
			String updateProductQuery = "UPDATE orders SET userid=?,username=?,useraddress=?,creditcardno=?,purchasedate=?,shipdate=?,producttype=?,productquantity=?,productprice=?,shippingcost=?,productdiscount=?,totalsales=?,storeid=?,storeaddress=?,iscancelled=? where orderid=? and productid=?;";
			PreparedStatement pst = conn.prepareStatement(updateProductQuery);
			pst.setString(1,oi.userid);
			pst.setString(2,oi.username);
			pst.setString(3,oi.useraddress);
			pst.setString(4,oi.creditcardno);
			pst.setString(5,oi.purchasedate);
			pst.setString(6,oi.shipdate);
			pst.setString(7,oi.producttype);
			pst.setInt(8,oi.productquantity);
			pst.setDouble(9,oi.productprice);
			pst.setDouble(10,oi.shippingcost);
			pst.setDouble(11,oi.productdiscount);
			pst.setDouble(12,oi.totalsales);
			pst.setInt(13,oi.storeid);
			pst.setString(14,oi.storeaddress);
			pst.setBoolean(15,oi.iscancelled);
			pst.setInt(16,oi.orderid);
			pst.setString(17,oi.productid);
			pst.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("CANCEL ORDER : " + e.toString());
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