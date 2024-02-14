import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.util.*;
//Binary Search
//
public class custApp extends Customer
{
    public static void main(String[] args) throws IOException
    {
        //declaration
        String searchAdd;
        char repeat = 'Y';
        char repeatVAR = 'Y';
        double totalFoodPrice = 0.0,totalRiderFee = 0.0,riderFee = 0.0;
        int totalDelivered = 0,counterCustomer = -1,counterCustomerDelivered = 0;
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        DecimalFormat df = new DecimalFormat("0.00");
        File file = new File("Delivery.txt");
        Scanner scanFile = new Scanner(file);
        LinkedList CustomerList = new LinkedList();
        Customer C= new Customer();
        QueueCustomer QueueAdd = new QueueCustomer();
        QueueCustomer QueueMerlimau = new QueueCustomer();
        QueueCustomer QueueJasin = new QueueCustomer();
        QueueCustomer QueueMendapat = new QueueCustomer();
        QueueCustomer QueueBandar = new QueueCustomer();
        QueueCustomer tempAdd = new QueueCustomer();
        QueueCustomer selectQ = new QueueCustomer();
        LinkedList deliveredList = new LinkedList();
        FileOutputStream fout = new FileOutputStream("deliveredOrders.txt");
        ObjectOutputStream outWriter = new ObjectOutputStream(fout);
        PrintWriter writer = new PrintWriter("deliveredOrders.txt");
        writer.println("LIST OF DELIVERED ORDERS\n");
        writer.println("======================================================");

        //enter data - create object - insert object into Queue(CustomerList)
        while (scanFile.hasNext())
        {
            String indata = scanFile.nextLine();
            StringTokenizer st = new StringTokenizer(indata,";");
            int custId = Integer.parseInt(st.nextToken());
            String custName = st.nextToken();
            String custaddress = st.nextToken();
            String custtele = st.nextToken();
            String order= st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            double foodPrice= Double.parseDouble(st.nextToken());
            String orderStatus = st.nextToken();

            C = new Customer(custId,custName,custaddress,custtele,order,amount,foodPrice,orderStatus);
            counterCustomer++;
            CustomerList.insertAtFront(C);
            QueueAdd.enqueue(C);
        }
        
        //main system
        while(repeatVAR == 'Y' && !CustomerList.isEmpty())
        {
        
            //display CustomerList
            System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|","ID","NAME","ADDRESS","TELE.NO","ORDER","AMOUNT","TOTAL (RM)","ORDER STATUS"));
            System.out.println("==========================================================================================================================================================================");
            for(int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                C.DisplayInfo();
            }
            System.out.println("==========================================================================================================================================================================");
        
            //sort customerList by id
            CustomerList.bubbleSort();
        
            System.out.print("\nLIST SORTED BY ID : \n");
            System.out.println("==========================================================================================================================================================================");
            System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|","ID","NAME","ADDRESS","TELE.NO","ORDER","AMOUNT","TOTAL (RM)","ORDER STATUS"));
            System.out.println("==========================================================================================================================================================================");
            for(int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                C.DisplayInfo();
            }
            System.out.println("==========================================================================================================================================================================");
        
            //sort customer data based off address
            while(!QueueAdd.isEmpty())
            {
               C = QueueAdd.dequeue();
               String CustAdd = C.getCustAddress();
                if (CustAdd.equalsIgnoreCase("Merlimau"))
                {
                    QueueMerlimau.enqueue(C);
                }
                else if(CustAdd.equalsIgnoreCase("jasin"))
                {
                    QueueJasin.enqueue(C);
                }
                else if(CustAdd.equalsIgnoreCase("Bandaraya Melaka"))
                {
                    QueueBandar.enqueue(C);
                }
                else if(CustAdd.equalsIgnoreCase("mendapat"))
                {
                    QueueMendapat.enqueue(C);
                }
            }
            System.out.println("==========================================================================================================================================================================");
            System.out.println("Total Amount of Orders Today:"+counterCustomer);
        
            //search and display customer data based off address
            boolean validAddress = true;
            do{
                System.out.println("Enter Selected Area of Delivery:");
                searchAdd = scan.next();
                System.out.print('\u000C');
                System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|","ID","NAME","ADDRESS","TELE.NO","ORDER","AMOUNT","TOTAL (RM)","ORDER STATUS"));
                System.out.println("==========================================================================================================================================================================");
                if (searchAdd.equalsIgnoreCase("Merlimau"))
                {
                    while(!QueueMerlimau.isEmpty())
                    {
                        C = QueueMerlimau.dequeue();
                        C.DisplayInfo();
                        tempAdd.enqueue(C);
                        validAddress = true;
                    }
                }
                else if (searchAdd.equalsIgnoreCase("Jasin"))
                {
                    while(!QueueJasin.isEmpty())
                    {
                        C = QueueJasin.dequeue();
                        C.DisplayInfo();
                        tempAdd.enqueue(C);
                        validAddress = true;
                    }
                }
                else if (searchAdd.equalsIgnoreCase("Mendapat"))
                {
                    while(!QueueMendapat.isEmpty())
                    {
                        C = QueueMendapat.dequeue();
                        C.DisplayInfo();
                        tempAdd.enqueue(C);
                        validAddress = true;
                    }
                }
                else if (searchAdd.equalsIgnoreCase("Bandar Melaka"))
                {
                    while(!QueueBandar.isEmpty())
                    {
                        C = QueueBandar.dequeue();
                        C.DisplayInfo();
                        tempAdd.enqueue(C);
                        validAddress = true;
                    }
                }
        
                else
                {
                    System.out.println("No orders match the address.Please enter address according to the displayed data.");
                    validAddress = false;
                }
                }while(!validAddress);
            System.out.println("==========================================================================================================================================================================");

            //choose customer order to deliver
            char changeStatus = 'N';
            System.out.println("Enter customer ID for delivery: ");
            int searchId = scan.nextInt();
            
            while(!tempAdd.isEmpty())
            {
                    C = tempAdd.dequeue();
                    if(C.getCustId() == searchId)
                    {
                        while(changeStatus != 'D'){
                            System.out.println("Press D when order is delivered:");
                            changeStatus = scan.next().charAt(0);
                            Character.toUpperCase(changeStatus);
                            System.out.print('\u000C');
                            if(changeStatus == 'D')
                            {
                                C.DisplayDelivered(searchId);
                                totalFoodPrice = C.calcPayment();
                                totalDelivered += totalFoodPrice;
                                riderFee = 4;
                                totalRiderFee += riderFee;
                                writer.println(C);
                                writer.println("======================================================");
                                CustomerList.remove(C);
                                counterCustomerDelivered++;
                            }
                        }
                    }
                
                else
                {
                    C.setStatus("Undelivered");
                    QueueAdd.enqueue(C);
                }
            }
        
        
            
                //display total for:
                System.out.println("TOTAL PAYMENT:");
                
                System.out.println("Total Customer Payment:RM"+totalFoodPrice);
                
                System.out.println("Total Fee for Rider:RM"+riderFee);
                
                double overallpay= 0 ;
                overallpay = riderFee + totalFoodPrice;
                
                System.out.println("Total Overall Payment:RM"+overallpay);
                
                //rider's tracker
                char seeTracker;
                System.out.println("Do you want to see your current performance:");
                seeTracker = scan.next().charAt(0);
                if(seeTracker == 'Y' || seeTracker == 'y')
                {   System.out.println("\nDELIVERY RUN TRACKER:\n");
            
                    System.out.println("Total Amount of Customer's Order Delivered:"+counterCustomerDelivered+"\n");
                    
                    System.out.println("Total amount of money collected today:RM"+totalDelivered+"\n");
                
                    System.out.println("Total collected fee for rider:RM"+totalRiderFee+"\n");
                
                    System.out.println("\nWell done!");
                }
            
                //system looping
                System.out.println("Do you want to deliver more orders? (Y = yes / N = no):");
                repeatVAR = scan.next().charAt(0);
                repeatVAR = Character.toUpperCase(repeatVAR);
                System.out.print('\u000C');
            
            }

            writer.close();
            System.out.println("\nDELIVERY RUN TRACKER:\n");
        
                System.out.println("Total Amount of Customer's Order Delivered:"+counterCustomerDelivered+"\n");
                
                System.out.println("Total amount of money collected today:RM"+totalDelivered+"\n");
            
                System.out.println("Total collected fee for rider:RM"+totalRiderFee+"\n");
            
                System.out.println("\nWell done!");
    
        System.out.println("Thank you for your service,Rider!");
    }
}