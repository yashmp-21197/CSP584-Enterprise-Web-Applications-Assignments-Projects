
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    TV tv;
    SoundSystem soundsystem;
    Phone phone;
	Laptop laptop;
	VoiceAssistant voiceassistant;
    Accessory accessory;
	
    static HashMap<String,TV> tvs;
    static HashMap<String,SoundSystem> soundsystems;
    static HashMap<String,Phone> phones;
	static HashMap<String,Laptop> laptops;
	static HashMap<String,VoiceAssistant> voiceassistants;
    static HashMap<String,Accessory> accessories;

    String productXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
	
    public SaxParserDataStore()
	{
	}
	
	public SaxParserDataStore(String productXmlFileName) {
		this.productXmlFileName = productXmlFileName;
		tvs = new HashMap<String, TV>();
		soundsystems=new  HashMap<String, SoundSystem>();
		phones=new HashMap<String, Phone>();
		laptops=new HashMap<String, Laptop>();
		voiceassistants=new HashMap<String, VoiceAssistant>();
		accessories=new HashMap<String, Accessory>();
		accessoryHashMap=new HashMap<String,String>();
		parseDocument();
    }

	//parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(productXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("tv")) {
			currentElement="tv";
			tv = new TV();
            tv.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("soundsystem")) {
			currentElement="soundsystem";
			soundsystem = new SoundSystem();
            soundsystem.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("phone")) {
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("laptop")) {
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("voiceassistant")) {
			currentElement="voiceassistant";
			voiceassistant = new VoiceAssistant();
            voiceassistant.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("accessory") &&  !currentElement.equalsIgnoreCase("tv") &&  !currentElement.equalsIgnoreCase("phone") &&  !currentElement.equalsIgnoreCase("laptop")){
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equalsIgnoreCase("tv")) {
			tvs.put(tv.getId(),tv);
			System.out.println(tv.getId());
			System.out.print("accessories : ");
			for(Map.Entry<String, String> e : tv.accessories.entrySet()){
				System.out.print( e.getValue()+", ");
			}
			System.out.println();
			return;
        }
		if (element.equalsIgnoreCase("soundsystem")) {
			soundsystems.put(soundsystem.getId(),soundsystem);
			System.out.println(soundsystem.getId());
			return;
        }
		if (element.equalsIgnoreCase("phone")) {
			phones.put(phone.getId(),phone);
			System.out.println(phone.getId());
			System.out.print("accessories : ");
			for(Map.Entry<String, String> e : phone.accessories.entrySet()){
				System.out.print( e.getValue()+", ");
			}
			System.out.println();
			return;
        }
		if (element.equalsIgnoreCase("laptop")) {
			laptops.put(laptop.getId(),laptop);
			System.out.println(laptop.getId());
			System.out.print("accessories : ");
			for(Map.Entry<String, String> e : laptop.accessories.entrySet()){
				System.out.print( e.getValue()+", ");
			}
			System.out.println();
			return;
        }
		if (element.equalsIgnoreCase("voiceassistant")) {
			voiceassistants.put(voiceassistant.getId(),voiceassistant);
			System.out.println(voiceassistant.getId());
			return;
        }
 

        if (element.equalsIgnoreCase("accessory") && currentElement.equalsIgnoreCase("accessory")) {
			accessories.put(accessory.getId(),accessory); 
			System.out.println(accessory.getId());			
			return; 
        }
		if (element.equalsIgnoreCase("accessory") && (currentElement.equalsIgnoreCase("tv") || currentElement.equalsIgnoreCase("phone") || currentElement.equalsIgnoreCase("laptop"))) {
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equalsIgnoreCase("tv")) {
			tv.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
		if (element.equalsIgnoreCase("accessories") && currentElement.equalsIgnoreCase("phone")) {
			phone.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
		if (element.equalsIgnoreCase("accessories") && currentElement.equalsIgnoreCase("laptop")) {
			laptop.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
		
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equalsIgnoreCase("tv"))
				tv.setImage(elementValueRead);
        	if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setImage(elementValueRead);
        	if(currentElement.equalsIgnoreCase("phone"))
				phone.setImage(elementValueRead);
        	if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setImage(elementValueRead);
        	if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setImage(elementValueRead);
        	if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equalsIgnoreCase("tv"))
				tv.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equalsIgnoreCase("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equalsIgnoreCase("tv"))
				tv.setCondition(elementValueRead);
        	if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setCondition(elementValueRead);
        	if(currentElement.equalsIgnoreCase("phone"))
				phone.setCondition(elementValueRead);
        	if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setCondition(elementValueRead);
        	if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setCondition(elementValueRead);
        	if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equalsIgnoreCase("tv"))
				tv.setRetailer(elementValueRead);
            if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setRetailer(elementValueRead);
            if(currentElement.equalsIgnoreCase("phone"))
				phone.setRetailer(elementValueRead);
            if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setRetailer(elementValueRead);
            if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setRetailer(elementValueRead);
            if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equalsIgnoreCase("tv"))
				tv.setName(elementValueRead);
            if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setName(elementValueRead);
            if(currentElement.equalsIgnoreCase("phone"))
				phone.setName(elementValueRead);
            if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setName(elementValueRead);
            if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setName(elementValueRead);
            if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equalsIgnoreCase("tv"))
				tv.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("soundsystem"))
				soundsystem.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("voiceassistant"))
				voiceassistant.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }
		
		if(element.equalsIgnoreCase("type")){
            if(currentElement.equalsIgnoreCase("accessory"))
				accessory.setType(elementValueRead);          
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\BestDeal\\ProductCatalog.xml");
    } 
}
