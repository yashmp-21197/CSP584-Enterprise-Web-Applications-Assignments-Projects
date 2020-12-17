import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
	static DBCollection myReviews;

	public static DBCollection getConnection()
	{
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");	
		return myReviews; 
	}

	public static boolean insertReview(Review r)
	{
		try
			{		
				getConnection();
				BasicDBObject doc = new BasicDBObject("title", "myReviews").
					append("product_id", r.getProduct_id()).
					append("product_name", r.getProduct_name()).
					append("product_category", r.getProduct_category()).
					append("product_price", r.getProduct_price()).
					append("store_id", r.getStore_id()).
					append("store_zip", r.getStore_zip()).
					append("store_city", r.getStore_city()).
					append("store_state", r.getStore_state()).
					append("product_onsale", r.getProduct_onsale()).
					append("manufacturer_name", r.getManufacturer_name()).
					append("manufacturer_rebate", r.getManufacturer_rebate()).
					append("user_id", r.getUser_id()).
					append("user_age", r.getUser_age()).
					append("user_gender", r.getUser_gender()).
					append("user_occupation", r.getUser_occupation()).
					append("review_rating", r.getReview_rating()).
					append("review_date", r.getReview_date()).
					append("review_text", r.getReview_text());
				myReviews.insert(doc);
				return true;
			}
			catch(Exception e)
			{
			return false;
			}	
			
	}

	public static HashMap<String, ArrayList<Review>> selectReview()
	{	
		try
		{
			getConnection();
			DBCursor cursor = myReviews.find();
			HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
			while (cursor.hasNext())
			{
				BasicDBObject obj = (BasicDBObject) cursor.next();				
				ArrayList<Review> list = reviews.getOrDefault(obj.getString("product_id") , new ArrayList<Review>());		
				Review r = new Review(obj.getString("product_id"),obj.getString("product_name"),obj.getString("product_category"),obj.getString("product_price"),obj.getString("store_id"),obj.getString("store_zip"),obj.getString("store_city"),obj.getString("store_state"),obj.getString("product_onsale"),obj.getString("manufacturer_name"),obj.getString("manufacturer_rebate"),obj.getString("user_id"),obj.getString("user_age"),obj.getString("user_gender"),obj.getString("user_occupation"),obj.getString("review_rating"),obj.getString("review_date"),obj.getString("review_text"));
				list.add(r);
				reviews.put(obj.getString("product_id"), list);
			}
			return reviews;
		}
		catch(Exception e)
		{
			return null;	
		}		 
	}
	
	public static  ArrayList<BestRated> topBestRatedProducts(){
		try{
			ArrayList<BestRated> best_rated = new ArrayList<BestRated>();
			HashMap<String, ArrayList<Review>> reviews = selectReview();
			HashMap<String, Double> unsorted = new HashMap();
			LinkedHashMap<String, Double> sorted = new LinkedHashMap();
			
			ArrayList<BestRated> br = new ArrayList();
			
			for(Map.Entry<String, ArrayList<Review>> entry :reviews.entrySet()){
				
				String pid = entry.getKey();
				ArrayList<Review> list = entry.getValue();
				
				double avg = 0.0;
				
				for(Review r : list){
					avg += Double.parseDouble(r.getReview_rating());
				}
				avg /= (list.size());
				
				unsorted.put(pid, avg);
			}
			
			unsorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
			
			int limit=5;
			
			for(Map.Entry<String, Double> entry : sorted.entrySet()){
				if(limit==0)
					break;
				best_rated.add(new BestRated(entry.getKey(), entry.getValue().toString()));
				limit--;
			}
			
			return best_rated;
		}catch (Exception e){ 
			System.out.println(e.getMessage());
			return null;
		}
	}
  
  	public static ArrayList <MostSoldZip> topMostSoldZip(){
		try{
			ArrayList<MostSoldZip> most_sold_zip = new ArrayList<MostSoldZip>();
			getConnection();
			DBObject group = new BasicDBObject("_id","$store_zip"); 
			group.put("count",new BasicDBObject("$sum",1));
			group = new BasicDBObject("$group",group);
			DBObject sort = new BasicDBObject("$sort",new BasicDBObject("count",-1));
			DBObject limit = new BasicDBObject("$limit",5);
			AggregationOutput output = myReviews.aggregate(group,sort,limit);
			for(DBObject res : output.results()) {
				String zip_code =(res.get("_id")).toString();
				String count = (res.get("count")).toString();	
				MostSoldZip msz = new MostSoldZip(zip_code, count);
				most_sold_zip.add(msz);
			}
			return most_sold_zip;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
  
	public static ArrayList <MostSold> topMostSoldProducts(){
		try{
			ArrayList<MostSold> most_sold = new ArrayList<MostSold>();
			getConnection();
			DBObject group = new BasicDBObject("_id","$product_id"); 
			group.put("count",new BasicDBObject("$sum",1));
			group = new BasicDBObject("$group",group);
			DBObject sort = new BasicDBObject("$sort",new BasicDBObject("count",-1));
			DBObject limit = new BasicDBObject("$limit",5);
			AggregationOutput output = myReviews.aggregate(group,sort,limit);
			for(DBObject res : output.results()) {
				String prodcut_id =(res.get("_id")).toString();
				String count = (res.get("count")).toString();	
				MostSold ms = new MostSold(prodcut_id,count);
				most_sold.add(ms);
			}
			return most_sold;
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}	
}	