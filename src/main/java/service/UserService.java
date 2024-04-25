package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserService {

    private final SessionFactory factory;


    public UserService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

    }
    public Customer viewUser(String name){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName").setParameter("accountName", name).uniqueResult();
        if(existingCustomer == null){
            throw  new RuntimeException("Customer Does Not Exists");
        }
        transaction.commit();
        session.close();
        return existingCustomer;
    }

    public String pinChange(String anumber,String oldPin, String newPin){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountNumber = :accountNumber").setParameter("accountNumber", anumber).uniqueResult();
        if(existingCustomer == null){
            throw  new RuntimeException("Customer Does Not Exists");
        }
       if(existingCustomer.getPin().equals(oldPin)){
           existingCustomer.setPin(newPin);
           session.persist(existingCustomer);
       }
        transaction.commit();
        session.close();
        return existingCustomer.getAccountName() + "\t" + "Pin Has Been Changed";
    }


    public String updateAccount(String oldname,String newname,Long phoneNumber){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName").setParameter("accountName", oldname).uniqueResult();
        if(existingCustomer == null){
            throw  new RuntimeException("Customer Does Not Exists");
        }
        if(existingCustomer.getRole().equals("customer")){
            existingCustomer.setAccountName(newname);
            existingCustomer.setPhoneNumber(phoneNumber);
            session.persist(existingCustomer);
        }

        transaction.commit();
        session.close();
        return existingCustomer.getAccountName() + "\t" + "Account Has Been Updated";

    }
    public String DeleteAccount(String name){
        Session session =  factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName").setParameter("accountName", name).uniqueResult();
        session.remove(existingCustomer);
        transaction.commit();
        session.close();
        String message  =  existingCustomer.getAccountName() + "\t" + "Has Been Deleted SuccessFully";
        return message;
    }
}
