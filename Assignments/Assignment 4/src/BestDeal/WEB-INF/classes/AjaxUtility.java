import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean names_added = false;
	static Connection conn = null;
    static String message;
	
	public static String getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal?useSSL=false","root","root");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String search_id)
	{	
		HashMap<String,Product> data = getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while(it.hasNext()) 
	    {
            Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Product p=(Product)pi.getValue();                   
                if(p.getName().toLowerCase().startsWith(search_id))
                {
                    sb.append("<product>");
                    sb.append("<product_id>" + p.getId() + "</product_id>");
                    sb.append("<product_name>" + p.getName() + "</product_name>");
                    sb.append("</product>");
                }
			}
       }
	   return sb;
	}
	
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
		    String selectproduct="select * from  Productdetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Product p = new Product(rs.getString("id"),rs.getString("producttype"),rs.getString("productname"),rs.getDouble("productprice"),rs.getString("productimage"),rs.getString("productmanufacturer"),rs.getString("productcondition"),rs.getDouble("productdiscount"),rs.getBoolean("productonsale"),rs.getInt("productcount"));
				hm.put(rs.getString("id"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	public static void storeData(HashMap<String,Product> productdata)
	{
		try
		{
			getConnection();				
			String addProductQuery = "INSERT INTO Productdetails(id, producttype, productname, productprice, productimage, productmanufacturer, productcondition, productdiscount, productonsale, productcount) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			for(Map.Entry<String, Product> entry : productdata.entrySet())
			{	
				Product p = entry.getValue();
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
			}
		}
		catch(Exception e)
		{	
	
		}		
	}

}
