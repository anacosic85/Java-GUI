

public class Property
{
   private int propID, years;
   private String street;
   private String town;
   private String county;
   private int beds;
   private int receptions;
   private int baths;
   private String type;
   private double price;
   
   //Constructors
   //User default constructor
   public Property()
   {
      propID = 0;
      street = "";
      town = "";
      county = "";
      beds = 0;
      receptions = 0;
      baths = 0;
      type = "";
      price = 0.00;
   }      
      
   //Constructor
   //Before object of the property is constructed - check that values entered are valid
   public Property(int id, String s, String t, String c, int bed, int bath, int reception, String propType, double p) throws IllegalArgumentException
   {
      if(id < 0 || bed < 0 || reception < 0 || bath < 0 || p < 0)
      {
         throw new IllegalArgumentException();
      }
      
      else //go ahead and set all parameters
      {
         propID = id;
         street = s;
         town = t;
         county = c;
         beds = bed;
         receptions = reception;
         baths = bath;
         type = propType;
         price = p; 
      }
   }
   
   
   
   //Methods
   public String toString()
   {
      return "ID: "+propID+ " Street: "+street+ "   Town: "+town+ "   County: "+county+
      "   No bedrooms: "+beds+ "   Reception rooms: "+ receptions+ "   No bathrooms: " +baths+ "   Type " +type+ "   Price €" + price;
   }
   
   //Accessor methods
   
   public int getId()
   {
      return propID;
   }
   
   public String street()
   {
      return street;
   }
   
   public String town()
   {
      return town;
   }
   
   public String county()
   {
      return county;
   }
   
   public int bed()
   {
      return beds;
   }
   
   public int bath()
   {  
      return baths;
   }
   
   public int reception()
   {
      return receptions;
   }
   
   public double viewPrice()
   {
      return price;
   }
   
   public String type()
   {
      return type;
   }
   
   public String mortgage(double amount)
   {
      double limit = amount * 3;
      if(price <= limit)
      {
         return "Qualify for a mortgage";
      }
      else
      {
         return "Unfortunately you don't qualify for a mortgage";
      }
   }
      
   
   public double propertyTax()
   {
      
      double tax =0.00;
      if (price >0.00 && price <=100000.00)
      {
         tax = 90.00;
      }
      else if (price >=100001.00 && price <= 200000.00)
      {
         tax = 225.00;
      }
      else if (price >=200001.00 && price <=500000.00)
      {
         tax = 405.00;
      }
      else if (price >=500001.00 && price <=1000000.00)
      {
         tax = 1500.00;
      }
      else 
      {
         tax = 3000.00;
      }
      return tax;
   }
}