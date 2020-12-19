import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String product_id;
	private String product_name;
	private String product_category;
	private String product_price;
	private String store_id;
	private String store_zip;
	private String store_city;
	private String store_state;
	private String product_onsale;
	private String manufacturer_name;
	private String manufacturer_rebate;
	private String user_id;
	private String user_age;
	private String user_gender;
	private String user_occupation;
	private String review_rating;
	private String review_date;
	private String review_text;
	
	public Review (
	String product_id,
	String product_name,
	String product_category,
	String product_price,
	String store_id,
	String store_zip,
	String store_city,
	String store_state,
	String product_onsale,
	String manufacturer_name,
	String manufacturer_rebate,
	String user_id,
	String user_age,
	String user_gender,
	String user_occupation,
	String review_rating,
	String review_date,
	String review_text){
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_category = product_category;
		this.product_price = product_price;
		this.store_id = store_id;
		this.store_zip = store_zip;
		this.store_city = store_city;
		this.store_state = store_state;
		this.product_onsale = product_onsale;
		this.manufacturer_name = manufacturer_name;
		this.manufacturer_rebate = manufacturer_rebate;
		this.user_id = user_id;
		this.user_age = user_age;
		this.user_gender = user_gender;
		this.user_occupation = user_occupation;
		this.review_rating = review_rating;
		this.review_date = review_date;
		this.review_text = review_text;
		
		
	}

	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id){
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name){
		this.product_name = product_name;
	}
	
	
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category){
		this.product_category = product_category;
	}
	
	
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price){
		this.product_price = product_price;
	}
	
	
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id){
		this.store_id = store_id;
	}
	
	
	public String getStore_zip() {
		return store_zip;
	}
	public void setStore_zip(String store_zip){
		this.store_zip = store_zip;
	}
	
	
	public String getStore_city() {
		return store_city;
	}
	public void setStore_city(String store_city){
		this.store_city = store_city;
	}
	
	
	public String getStore_state() {
		return store_state;
	}
	public void setStore_state(String store_state){
		this.store_state = store_state;
	}
	
	
	public String getProduct_onsale() {
		return product_onsale;
	}
	public void setProduct_onsale(String product_onsale){
		this.product_onsale = product_onsale;
	}
	
	
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name){
		this.manufacturer_name = manufacturer_name;
	}

	
	public String getManufacturer_rebate() {
		return manufacturer_rebate;
	}
	public void setManufacturer_rebate(String manufacturer_rebate){
		this.manufacturer_rebate = manufacturer_rebate;
	}

	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}

	
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age){
		this.user_age = user_age;
	}

	
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender){
		this.user_gender = user_gender;
	}

	
	public String getUser_occupation() {
		return user_occupation;
	}
	public void setUser_occupation(String user_occupation){
		this.user_occupation = user_occupation;
	}

	
	public String getReview_rating() {
		return review_rating;
	}
	public void setReview_rating(String review_rating){
		this.review_rating = review_rating;
	}

	
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date){
		this.review_date = review_date;
	}

	
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text){
		this.review_text = review_text;
	}
}
