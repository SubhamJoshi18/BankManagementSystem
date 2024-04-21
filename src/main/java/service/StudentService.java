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


   public String registerCustomer(String name,String email,Integer age){
        Customer customer1 = new Customer();
        Session session= factory.openSession();
       Transaction transaction = session.getTransaction();
       transaction.begin();
       customer1.setName(name);
       customer1.setEmail(email);
       customer1.setAge(age);
       customer1.setCurrent_Money(0);
       customer1.setTotal_money(0);
       customer1.setWithdrawn_money(0);
       customer1.setDeposited_money(0);
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


    public String DepositedAmountProfile(Integer id,Integer amount){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        switch(amount){
            case 0:{
                throw new RuntimeException("Amount should be greater than 0");
            }
            case 5: {
                throw new RuntimeException("Amount should be greater than 5");
            }
            default:
        }
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer does not exists");
        }
        int newTotalCash = customer.getTotal_money() + amount;
        int newCurrentMoney = newTotalCash;
        int Deposited  = amount;
        customer.setTotal_money(newTotalCash);
        customer.setCurrent_Money(newCurrentMoney);
        customer.setDeposited_money(Deposited);
        session.persist(customer);
        transaction.commit();
        session.close();
        return "Deposited Amount : " + Deposited +  "\t" + "newTotalCash : " + newTotalCash + "\t" + "CurrentMoney : " + "\t" + newCurrentMoney;
    }


    public String withDrawn(Integer id, Integer amount){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        switch(amount){
            case 0:{
                throw new RuntimeException("Cannot withdrawn 0");
            }

            case 5:{
                throw new RuntimeException("Cannot withdrawn amount less or equal than 5");
            }
            default:
        }
        Customer customer = session.find(Customer.class,id);
        if(customer == null || customer.getId() == null){
            throw new RuntimeException("Customer Does not Exists");
        }
        if(customer.getTotal_money() < amount){
            throw new RuntimeException("WithDrawn Failed");
        }
        int newTotalCash = customer.getTotal_money() - amount;
        int currentCash = newTotalCash;
        int withDrawnAmount = amount;

        customer.setWithdrawn_money(withDrawnAmount);
        customer.setCurrent_Money(currentCash);
        customer.setTotal_money(newTotalCash);

        session.persist(customer);
        transaction.commit();
        session.close();
        return "WithDrawn Amount : " + withDrawnAmount +  "\t" + "newTotalCash : " + newTotalCash + "\t" + "CurrentMoney : " + "\t" + currentCash;
    }

    public Customer details(Integer id){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer Does not Exists");
        }
        transaction.commit();
        session.close();
        return  customer;
    }

}