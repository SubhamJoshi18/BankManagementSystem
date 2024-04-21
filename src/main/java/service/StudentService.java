package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utils.HibernateSessionProperty;

public class StudentService {

    private final SessionFactory factory;


    public StudentService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

    }


   public String saveCustomer(String name,String email,Integer age){
        Customer customer1 = new Customer();
        Session session= factory.openSession();
       Transaction transaction = session.getTransaction();
       transaction.begin();
       customer1.setName(name);
       customer1.setEmail(email);
       customer1.setAge(age);
       session.persist(customer1);
       transaction.commit();
       session.close();
       return  "Customer Has Been Saved";
    }

    public boolean loginFilter(Integer id){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            return false;
        }
        System.out.println(id  + "\t" + "Is Registered SuccessFully");
        transaction.commit();
        session.close();
        return true;
    }
    public Customer getById(Integer id){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer customer = session.find(Customer.class,id);
        if(customer.getName()  == null || customer.getId() == null || customer.getEmail() == null){
            throw new RuntimeException("You are Not Registered");
        }

        transaction.commit();
        session.close();
        return customer;
    }

    public String deleteByID(Integer id){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer Does not Exists");
        }
        session.remove(customer);
        transaction.commit();
        session.close();

        return "Customer with " + id  + "\t" + "is Deleted SuccessFully";
    }

    public String updateCustomerProfile(Integer id,String name, String email, Integer age){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer Does not Exists");
        }
        customer.setName(name);
        customer.setEmail(email);
        customer.setAge(age);
        session.persist(customer);
        transaction.commit();
        session.close();

        return "Customer with " + id + "is Updated SuccessFully";
    }


}