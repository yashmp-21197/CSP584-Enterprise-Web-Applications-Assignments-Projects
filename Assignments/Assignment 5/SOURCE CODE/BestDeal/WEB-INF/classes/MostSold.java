import java.io.*;
public class MostSold{
	String product_id;
	String count;

	public MostSold(String product_id,String count){
		this.product_id = product_id;
		this.count = count;
	}

	public String getProduct_id(){
	 return product_id;
	}

	public String getCount () {
	 return count;
	}
}