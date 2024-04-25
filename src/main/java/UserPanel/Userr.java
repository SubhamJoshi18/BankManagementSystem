package UserPanel;

import model.Customer;
import service.UserService;

import java.util.Scanner;

public class Userr {

    static Scanner intscanner = new Scanner(System.in);
    static  Scanner stringScanner = new Scanner(System.in);

    static Scanner longScanner = new Scanner(System.in);
    public void UserPanel(){
        boolean value = true;
        while(value){
            System.out.println("------USER PANEL------");
            System.out.println("1) View Your Account: ");
            System.out.println("2) Change Pin");
            System.out.println("3) Update Account");
            System.out.println("4) Delete Account");
            int choice  = intscanner.nextInt();
            switch(choice){
                case 1:{
                    System.out.println("Enter Your Name : ");
                    String name = stringScanner.nextLine();
                    System.out.println("Fetching The User Data....");
                    UserService userService = new UserService();
                    Customer user = userService.viewUser(name);
                    if(user.getRole().equals("customer")){
                        System.out.println(user.toString());
                    }
                    break;
                }

                case 2:{
                    System.out.println("----Pin Change----");
                    System.out.println("Enter Your Account Number: ");
                    String  anumber = stringScanner.nextLine();
                    System.out.println("Enter Your Account Pin Number: ");
                    String oldPin = stringScanner.nextLine();
                    System.out.println("Enter Your New Account Pin Number: ");
                    String newPin = stringScanner.nextLine();
                    UserService userService = new UserService();
                    String message = userService.pinChange(anumber,oldPin,newPin);
                    if(message.length() > 0 || !message.isEmpty()){
                        System.out.println(message);
                    }
                    break;
                }

                case 3:{
                    System.out.println("----Updating Your Account------");
                    System.out.println("Enter Your Account Name: ");
                    String oldname = stringScanner.nextLine();
                    System.out.println("Enter Your Account New Name: ");
                    String newname = stringScanner.nextLine();
                    System.out.println("Enter Your new Account Phone Number: ");
                    long newPhoneNumber = longScanner.nextLong();
                    UserService userService = new UserService();
                    String message= userService.updateAccount(oldname,newname,newPhoneNumber);
                    if(!message.isEmpty()){
                        System.out.println(message);
                    }
                    break;
                }

                case 4: {
                    System.out.println("-----Delete Your Account-----");
                    System.out.println("Enter Your Name To Confirmed: ");
                    String name = stringScanner.nextLine();
                    UserService userService = new UserService();
                    String message  = userService.DeleteAccount(name);
                    if(!message.isEmpty()){
                        System.out.println(message);
                    }
                    break;

                }
            }



        }

    }

}
