 
import java.text.DecimalFormat;

public class Customer
{
    String custName,custaddress,custtele,order;
    public static String orderStatus;
    double foodPrice;
    int custId,amount;
    DecimalFormat df = new DecimalFormat("0.00");
    String status = "Delivered";
    
    /*default const*/
    public Customer ()
    {
        custId =0;
        custName = "";
        custaddress = "";
        custtele = "";
        order = "";
        amount = 0;
        orderStatus = "";
    }
    
    //normal const
    public Customer(int custId, String custName , String custaddress,String custtele,String order,int amount,double foodPrice,String orderStatus){
        this.custId =custId;
        this.custName = custName;
        this.custaddress = custaddress;
        this.custtele = custtele;
        this.order = order;
        this.amount = amount;
        this.foodPrice = foodPrice;
        this.orderStatus = orderStatus;
    }
    
    //processor
    public double calcPayment(){
        double total = 0.0;
        if(order.equalsIgnoreCase("Chee Chong Fun")){
           foodPrice = 7.00;
        }
        else if (order.equalsIgnoreCase("Nasi Lemak"))
        {
            foodPrice = 2.50;
        }
        
        else{
            foodPrice = 5.00;
        }
        
        total = foodPrice * amount;
        return total;
        
    }
        
    //setter
    public void setStatus(String status) {
        orderStatus = status;
    }
    
    
    //getter(s)
    public int getCustId()
    {return custId;}
    public String getCustAddress()
    {return custaddress;}
    public String getTele()
    {return custtele;}
    public String getStatus()
    {return orderStatus;}
    
    //displayer
    public void DisplayInfo()
    {
     System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",custId,custName,custaddress,custtele,order,amount,df.format(calcPayment()),orderStatus));
    }
    
    public void DisplayDelivered(int searchedId)
    {     
        status = "";
        if(searchedId == custId)
        {status = "Delivered";}
        System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",custId,custName,custaddress,custtele,order,amount,df.format(calcPayment()),status));
    }

    public String toString()
    {
        return("Customer Id : "+custId + "\nCustomer Name: " +custName + "\nCustomer Address :" +custaddress + "\nCustomer number : "+custtele + "\nCustomer Order : "+order+"\nAmount of "+order+" ordered:"+amount+"\nPayment : "+df.format(calcPayment())+"\nOrder Status : "+status);
    }
    }