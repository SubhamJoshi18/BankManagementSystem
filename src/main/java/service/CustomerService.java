package service;

import model.Customer;
import model.RoleEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.implemention.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService implements CustomerServiceImpl {

    private final SessionFactory factory;


    public CustomerService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

    }


    public String createNewAccount(String name, String aname, String pin, Long phoneNumber) {
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer resposneCustomer = new Customer();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName")
                .setParameter("accountName", name).uniqueResult();
        boolean verified = false;
        boolean admin = false;
        Customer customer1 = new Customer();
        if (existingCustomer != null) {
            throw new RuntimeException("An account with the same name already exists. Please choose another name.");
        }

        boolean isAdmin = false;
        List<Customer> customers = session.createQuery("FROM Customer").list();
        if (customers.isEmpty()) {
            isAdmin = true; // First customer is admin
        }

        String role = isAdmin ? "admin" : "";

        String message = null;
        List<String> roleList = new ArrayList<>();
        roleList.add(RoleEnum.customer.name());roleList.add(RoleEnum.admin.name());
        if (roleList.contains(role) && role == "admin") {
            try {
                Customer customer = new Customer();
                customer.setAccountName(name);
                customer.setAccountNumber(aname);
                customer.setPin(pin);
                customer.setRole(role);
                customer.setPhoneNumber(phoneNumber);
                //intializing yo default aba register vayesi sab default ma 0
                customer.setTotal_amount(0);
                customer.setCurrentBalance(0);
                customer.setDeposited_amount(0);
                customer.setWithDrawn_amount(0);
                session.persist(customer);

            } catch (Exception e) {
                throw new RuntimeException("message" + e.getMessage());
            }

        }else {
            throw new RuntimeException("Customer cannot create An Account");
        }
        transaction.commit();
        session.close();
        message =  "Account Created SuccessFully " ;
        return message;
    }


      public String loginAccount(String anumber,String pin){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
          Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountNumber = :accountNumber")
                  .setParameter("accountNumber", anumber).uniqueResult();
         if(existingCustomer == null){
             throw new RuntimeException("Invalid Account Number");
         }
         boolean checkPin= existingCustomer.getPin().equals(pin);
         if(!checkPin){
             throw new RuntimeException("Invalid Pin, Please Enter Correct Pin");
         }
         boolean verified  = this.verifyLogin(checkPin,existingCustomer);
         if(!verified){
             System.out.println("Log In Authentication Failed");
             throw new RuntimeException("Log In Authentication Failed");
         }

         String message = existingCustomer.getRole();


        transaction.commit();
        session.close();
        return  message;
      }


      public boolean verifyLogin(boolean checkpin, Customer exisitingCustomer) {
          return checkpin && exisitingCustomer.getAccountNumber() != null;
      }

      public boolean checkRole(String role){
        boolean random;
       if(role.equals("admin")){
           random =  true;
       }else {
           random = false;
       }
       return  random;
      }
}