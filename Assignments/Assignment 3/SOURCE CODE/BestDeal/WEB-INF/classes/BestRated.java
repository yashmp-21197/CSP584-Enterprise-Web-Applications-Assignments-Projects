import java.io.*;

public class BestRated{
	String product_id;
	String review_rating;

	public BestRated(String product_id,String review_rating){
		this.product_id = product_id ;
		this.review_rating = review_rating;
	}

	public String getProduct_id(){
	 return product_id;
	}

	public String getReview_rating() {
	 return review_rating;
	}
}