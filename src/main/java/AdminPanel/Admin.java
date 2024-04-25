package AdminPanel;

import model.Customer;
import org.hibernate.Session;
import service.AdminService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Admin{

    static Scanner intScanner = new Scanner(System.in);
    static Scanner stringScanner = new Scanner(System.in);

    static Scanner longScanner = new Scanner(System.in);

    public void AdminPanel(){
       boolean value = true;
       while(value){
           System.out.println("1) View All Account ");
           System.out.println("2) Create A New Account");
           System.out.println("3) View Account ");
           System.out.println("4) Delete Account ");
           System.out.println("5) Exit");
           int choice = intScanner.nextInt();;
           switch(choice){
               case 1:{
                   System.out.println("Fetching All Accounts ");
                   AdminService adminService = new AdminService();
                   List<Customer> obtainedList = adminService.allAccounts();
                   if(obtainedList.isEmpty()){
                       throw new RuntimeException("No User Is Register, It is Empty");
                   }
                   List<Customer> output = obtainedList.stream().filter(data -> !Objects.equals(data.getRole(), "admin")).toList();
                   if(!output.isEmpty()){
                       System.out.println(obtainedList.toString());
                   }
                  break;
               }
               case 2: {
                   System.out.println("----Create A New Account------");
                   AdminService adminService = new AdminService();
                   System.out.println("Enter A User Name: ");
                   String name = stringScanner.nextLine();
                   System.out.println("Enter Account Number: ");
                   String anumber = stringScanner.nextLine();
                   System.out.println("Enter Account Pin: ");
                   String pin = stringScanner.nextLine();
                   System.out.println("Enter User Phone Number: ");
                   long  phoneNo = longScanner.nextLong();
                   final String role = "customer";
                   String message = adminService.createNewAccount(name,anumber,pin,phoneNo,role);
                   if(message.length() > 0 && !message.isEmpty()){
                       System.out.println(message);
                   }
                 break;
               }
               case 3:{
                   System.out.println("----Account OF Individual Customer----");
                   AdminService adminService = new AdminService();
                   System.out.println("Enter Account Name: ");
                   String name = stringScanner.nextLine();
                   Customer customer = adminService.getAccountById(name);
                   if(customer.getRole().equals("customer")){
                       System.out.println(customer.toString());
                   }
                   break;
               }
               case 4: {
                   System.out.println("-------Delete An Account-------");
                   AdminService adminService = new AdminService();
                   System.out.println("Enter The Account Name To Be Deleted: ");
                   String name = stringScanner.nextLine();
                   String message = adminService.deleteAccountByName(name);
                   if(message.length()>0 && !message.isEmpty()){
                       System.out.println(message.toUpperCase());
                   }
                   break;
               }
               default:{
                   throw new RuntimeException("INTERNAL SERVER ERROR");
               }
           }
        }
    }


}