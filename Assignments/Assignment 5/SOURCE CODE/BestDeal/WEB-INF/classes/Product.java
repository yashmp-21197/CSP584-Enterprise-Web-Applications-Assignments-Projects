import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Product")


/* 
	TV class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	TV class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class Product extends HttpServlet{
	private String id;
	private String type;
	private String name;
	private double price;
	private String image;
	private String manufacturer;
	private String condition;
	private double discount;
	private boolean onsale;
	private int count;

	public Product(String id, String type, String name, double price, String image, String manufacturer, String condition,double discount, boolean onsale, int count){
		this.id = id;
		this.type = type;
		this.name=name;
		this.price=price;
		this.image=image;
		this.manufacturer = manufacturer;
		this.condition=condition;
		this.discount = discount;
		this.onsale = onsale;
		this.count = count;
	}
	

	public Product(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public boolean getOnsale() {
		return onsale;
	}

	public void setOnsale(boolean onsale) {
		this.onsale = onsale;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
