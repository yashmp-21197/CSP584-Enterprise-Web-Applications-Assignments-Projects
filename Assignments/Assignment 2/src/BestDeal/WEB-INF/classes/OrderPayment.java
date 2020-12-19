import java.io.IOException;
import java.io.*;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String delivery_option;
	private String creditCardNo;
	private String address;
	private String delivery_status;
	private String delivery_pickup_date;
	
	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice, String delivery_option,String creditCardNo,String address, String delivery_status, String delivery_pickup_date){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.delivery_option=delivery_option;
		this.address=address;
	 	this.creditCardNo=creditCardNo;
		this.delivery_status = delivery_status;
		this.delivery_pickup_date = delivery_pickup_date;
		}

	public String getAddress() {
		return address;
	}

	public void setUserAddress(String address) {
		this.address = address;
	}
	
	public void setDelivery_pickup_date(String delivery_pickup_date){
		this.delivery_pickup_date = delivery_pickup_date;
	}
	
	public String getDelivery_pickup_date(){
		return delivery_pickup_date;
	}
	
	public String getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	
	public String getDelivery_option() {
		return delivery_option;
	}

	public void setDelivery_option(String delivery_option) {
		this.delivery_option = delivery_option;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
