package utils;

import java.util.Scanner;

public class FirstRoleCheck {


    public boolean RoleCheck(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Your Role: ");
        String role = scanner.nextLine();
        FirstRoleCheck firstRoleCheck = new FirstRoleCheck();
        boolean checkrole = firstRoleCheck.verifiedFilter(role);
         if(checkrole){
             System.out.println("Admin Role Is Authenticated");
         }else{
             throw new RuntimeException("Role is not an Admin");
         }
         return checkrole;
    }

    public boolean verifiedFilter(String role){
        boolean next = false;
        if(role.equals("admin")){
            next = true;
        }
        return next;
    }
}
