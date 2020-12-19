import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class StoreLocation implements Serializable{
	private int id;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public StoreLocation (int id,String street,String city,String state,String zip){
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street){
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
}
