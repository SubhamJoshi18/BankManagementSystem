package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminService {

    private final SessionFactory factory;


    public AdminService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

    }

    public List<Customer> allAccounts(){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<Customer> customerList = new ArrayList<>();
        List<Customer> users = session.createQuery("FROM Customer c WHERE c.role != 'admin'", Customer.class).getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    public String createNewAccount(String name, String aname, String pin, Long phoneNumber,String role){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer resposneCustomer = new Customer();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName")
                .setParameter("accountName", name).uniqueResult();
        if (existingCustomer != null) {
            throw new RuntimeException("An account with the same name already exists. Please choose another name.");
        }

        try {
            Customer customer = new Customer();
            customer.setAccountName(name);
            customer.setAccountNumber(aname);
            customer.setPin(pin);
            customer.setRole(role);
            customer.setPhoneNumber(phoneNumber);
            customer.setTotal_amount(0);
            customer.setCurrentBalance(0);
            customer.setDeposited_amount(0);
            customer.setWithDrawn_amount(0);
            session.persist(customer);

        } catch (Exception e) {
            throw new RuntimeException("message" + e.getMessage());
        }
        transaction.commit();
        session.close();

        return name + "\t" + "Has Been Created Successfully";
    }


    public Customer getAccountById(String name){
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


public String deleteAccountByName(String name){
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
