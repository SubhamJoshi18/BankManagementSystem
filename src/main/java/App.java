import Transcation.TranscationCrud;
import dto.DetailsResponse;
import model.Customer;
import model.RoleEnum;
import org.hibernate.Transaction;
import service.CustomerService;
import utils.FirstRoleCheck;

import java.util.Scanner;

public class App {
    static Customer customer = new Customer();
    static Scanner stringScanner = new Scanner(System.in);
    static Scanner intScanner = new Scanner(System.in);
    static Scanner doubleScanner = new Scanner(System.in);

    static Scanner longScanner = new Scanner(System.in);


    static CustomerService customerService = new CustomerService();
    public static void main(String[] args){
        boolean check = true;
        while(check){
            System.out.println("Welcome To Bank Application");
//            System.out.println("1) Create A New Account");
            System.out.println("2) Login into An Account");
            System.out.println("3) Exit System");
            System.out.println("\nEnter Your Choice:  ");
            int choice = intScanner.nextInt();
            switch(choice){
//                case 1 : {
//
//                    FirstRoleCheck firstRoleCheck = new FirstRoleCheck();
//                    firstRoleCheck.RoleCheck();
//                    System.out.println("Registrating Your Account");
//                    System.out.println("Enter Your Account Name: ");
//                    String name = stringScanner.nextLine();
//                    System.out.println("Enter Your Account Number: ");
//                    String anumber = stringScanner.nextLine();
//                    System.out.println("Enter Your Account Pin: ");
//                    String pin = stringScanner.nextLine();
//                    System.out.println("Enter Your Account phoneNumber: ");
//                    long phoneNumber = longScanner.nextLong();
//
//                    try{
//
//                        String message = customerService.createNewAccount(name,anumber,pin,phoneNumber);
//                        if(message.length() > 0){
//                            System.out.println(message);
//                        }
//                    }catch(Exception e){
//                        throw new RuntimeException(e.getMessage());
//                    }
//                    break;
//                }

                case 2: {
                    System.out.println("Log into Your Account");
                    System.out.println("Enter Your Account Number: ");
                    String anumber = stringScanner.nextLine();
                    System.out.println("Enter Your Account Pin: ");
                    String pin = stringScanner.nextLine();
                    try{
                   String message = customerService.loginAccount(anumber,pin);
                    if(!message.isEmpty()){
                        System.out.println(message);
                        TranscationCrud transcationCrud = new TranscationCrud();
                        transcationCrud.TranscationMenu(message);
                    }
                    }catch(Exception e){
                        throw new RuntimeException(e.getMessage());
                    }
                    break;
                }
                default:
                {
                    throw new RuntimeException("INTERNAL SERVER ERROR");
                }
            }


        }


    }


}