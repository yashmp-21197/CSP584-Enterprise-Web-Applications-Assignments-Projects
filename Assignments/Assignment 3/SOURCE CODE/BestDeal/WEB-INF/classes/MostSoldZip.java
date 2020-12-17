import java.io.*;

public class MostSoldZip{
	String zip_code;
	String count;
	
	public MostSoldZip(String zip_code,String count)
	{
		this.zip_code = zip_code ;
		this.count = count;
	}
	
	public String getZip_code(){
	 return zip_code;
	}

	public String getCount () {
	 return count;
	}
}