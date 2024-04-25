package Transcation;

import AdminPanel.Admin;
import UserPanel.Userr;
import dto.CustomerDTO.DepositResponseDTO;
import dto.CustomerDTO.WithdrawnResponse;
import dto.DetailsResponse;
import service.TranscationService;

import java.util.Scanner;

public class TranscationCrud {

    static Scanner intScanner= new Scanner(System.in);

    static Scanner stringScanner = new Scanner(System.in);
    public void TranscationMenu(String message){
        boolean check = true;
        while(check){
            System.out.println("Transcation");
            System.out.println("1) Deposit Amount");
            System.out.println("2) Withdraw Amount");
            System.out.println("3) Fund Transfer");
            System.out.println("4) Account Details");
            if(message.equals("admin")) {
                System.out.println("5) ADMIN PANEL");
            }else if(message.equals("customer")){
                System.out.println("5) USER PANEL");
            }else{
                System.out.println("");
            }
            System.out.println("6) Exit");
            System.out.println("\nEnter Your Choice:  ");
            int choice = intScanner.nextInt();
            switch(choice){
                case 4:{
                    System.out.println("------Account Details--------");
                    System.out.println("Enter the Customer Id: ");
                    int myid = intScanner.nextInt();;


                    TranscationService transcationService = new TranscationService();
                    DetailsResponse detailsResponse = transcationService.viewAccount(myid);
                    System.out.println(detailsResponse);
                   break;
                }

                case 1:{
                    System.out.println("------Account Deposit--------");
                    System.out.println("Enter the Customer id:  ");
                    int myid = intScanner.nextInt();
                    System.out.println("Enter The Amount to Be Deposited");
                    int amount = intScanner.nextInt();
                    System.out.println("Enter Your Account Pin: ");
                    String pin = stringScanner.nextLine();
                    TranscationService transcationService = new TranscationService();
                    DepositResponseDTO depositResponseDTO = transcationService.depositAmount(myid,amount,pin);
                    System.out.println(depositResponseDTO);
                    break;
                }

                case 2:{
                    System.out.println("------Account Withdraw------");
                    System.out.println("Enter the Customer id:  ");
                    int myid = intScanner.nextInt();
                    System.out.println("Enter The Amount to Be WithDrawn: ");
                    int amount = intScanner.nextInt();
                    System.out.println("Enter Your Account Pin: ");
                    String pin = stringScanner.nextLine();
                    TranscationService transcationService = new TranscationService();
                    WithdrawnResponse withdrawnResponse = transcationService.withDrawMoney(myid,amount,pin);
                    System.out.println(withdrawnResponse);
                    break;
                }
                case 5:{
                    System.out.println("Enter Your User Name: ");
                    String name = stringScanner.nextLine();
                    System.out.println("Verifying Account as an Admin");
                    TranscationService transcationService = new TranscationService();
                    boolean checkVerified = transcationService.verifyAdmin(name);
                    if(checkVerified){
                        Admin admin = new Admin();
                        admin.AdminPanel();
                    }else{
                        Userr userr = new Userr();
                        userr.UserPanel();
                    }
                }
                default:{
                    throw new RuntimeException("INTERNAL SERVER ERROR");
                }
            }
    }
}
}
