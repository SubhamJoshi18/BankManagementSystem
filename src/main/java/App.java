import dto.DetailsResponse;
import model.Customer;
import service.StudentService;

import java.util.Scanner;

public class App {

    static Customer customer = new Customer();

   static StudentService studentService = new StudentService();
        public static void main( String[] args ) {
            boolean choice = Boolean.TRUE;
            Scanner stringScanner = new Scanner(System.in);
            Scanner intScanner = new Scanner(System.in);
            Scanner longScanner = new Scanner(System.in);
            while (choice) {
                System.out.println();
                System.out.println("Bank MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Register Account");
                System.out.println("2.Get Account");
                System.out.println("3.Delete Account");
                System.out.println("4.Update Account");
                System.out.println("5. Deposited Amount");
                System.out.println("6. WithDrawn Amount");
                System.out.println("7. Account Detailss");
                System.out.println("5.EXIT");
                int options = sc.nextInt();
                switch (options) {

                    case 1: {
                        System.out.println("Registration Account");
                        System.out.print("Enter Account Holder Name");
                        String name = stringScanner.nextLine();
                        System.out.print("Enter Account Holder email");
                        String email = stringScanner.nextLine();
                        System.out.println("Enter Account Holder age : ");
                        int age = intScanner.nextInt();
                        String message = studentService.registerCustomer(name,email,age);
                        System.out.println(message);
                        break;
                    }
                    case 2:{
                        System.out.println("Fetching data from database");
                        System.out.println("Enter the Customer id");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(check) {
                            System.out.println(myint + "\t" + "Has Been Logged In SuccessFully");
                            Customer customer1 = studentService.getById(myint);
                            System.out.println(customer1);
                        }else{
                            throw new RuntimeException("You are Not Registered");
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Deleting data from database");
                        System.out.println("Enter the customer id to be deleted : ");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(check){
                            System.out.println(myint + "\t" + "Has Been Logged In SuccessFully");
                             String message = studentService.deleteByID(myint);
                            System.out.println(message);
                        }
                        break;
                    }
                    case 4:{
                        System.out.println("Updating Data from database");
                        System.out.println("Enter the Customer id to be updated");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(check){
                            System.out.println(myint + "\t" + "Has Been Logged In SuccessFully");
                            System.out.println("Enter the new Name : ");
                            String newname = stringScanner.nextLine();
                            System.out.println("Enter the new Email : ");
                            String newEmail = stringScanner.nextLine();
                            System.out.println("Enter the new Age: ");
                            int newage = intScanner.nextInt();
                            String message = studentService.updateCustomerProfile(myint,newname,newEmail,newage);
                            System.out.println(message);
                        }
                        break;
                    }

                    case 5:{
                        System.out.println("Deposit Amount in Bank");
                        System.out.println("Enter Account Id: ");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(!check){
                            throw  new RuntimeException("You are not Logged In");
                        }
                        System.out.println("Id" + myint + "\t" + "Has Been Logged In");
                        System.out.print("Enter the amount to be Deposited : ");
                        int amount =  intScanner.nextInt();
                        String message = studentService.DepositedAmountProfile(myint,amount);
                        if(!message.isEmpty()){
                            System.out.println(message);
                        }

                 break;
                    }

                    case 6:{
                        System.out.println("WithDrawing Amount From Bank");
                        System.out.println("Enter Account Id: ");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(!check){
                            throw new RuntimeException("You are Not Logged In");
                        }
                        System.out.println("Id" + myint + "\t"+ "Has Been logged In");
                        System.out.println("Enter the amount to Withdrawn: ");
                        int amount = intScanner.nextInt();
                        String message = studentService.withDrawn(myint,amount);
                        if(!message.isEmpty()){
                            System.out.println(message);
                        }
                        break;
                    }

                    case 7:{
                        System.out.println("Account Details");
                        System.out.println("Enter Account Id");
                        int myint = intScanner.nextInt();
                        boolean check = studentService.loginFilter(myint);
                        if(!check){
                            throw new RuntimeException("You are not Logged In");
                        }
                        System.out.println("Id" + myint + "\t"+ "Has Been logged In");
                        DetailsResponse detailsResponse = new DetailsResponse();
                        Customer customer1 = studentService.details(myint);
                        detailsResponse.setCurrent_Money(customer1.getCurrent_Money());
                        detailsResponse.setDeposited_money(customer1.getDeposited_money());
                        detailsResponse.setTotal_money(customer1.getTotal_money());
                        detailsResponse.setWithdrawn_money(customer1.getWithdrawn_money());
                        System.out.println(detailsResponse);
                    }
                }
            }
        }


    }