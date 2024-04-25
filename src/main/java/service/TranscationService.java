package service;

import dto.CustomerDTO.DepositResponseDTO;
import dto.CustomerDTO.WithdrawnResponse;
import dto.DetailsResponse;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class TranscationService {

    static DetailsResponse detailsResponse = new DetailsResponse();
    private final SessionFactory factory;


    public TranscationService() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        factory = configuration.buildSessionFactory();

    }

    public DetailsResponse viewAccount(Integer id){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Customer existCustomer = session.find(Customer.class,id);
        if(existCustomer == null ){
            throw new RuntimeException("Customer is not available");
        }
        detailsResponse.setCurrentBalance(existCustomer.getCurrentBalance());
        detailsResponse.setTotal_amount(existCustomer.getTotal_amount());
        detailsResponse.setDeposited_amount(existCustomer.getDeposited_amount());
        detailsResponse.setWithDrawn_amount(existCustomer.getWithDrawn_amount());
        transaction.commit();
        session.close();
        return detailsResponse;
    }

    public DepositResponseDTO  depositAmount(Integer id, Integer amount,String pin){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        if( amount < 0){
            throw new RuntimeException("Invalid Amount");
        }
        switch(amount){
            case 0:{
                throw new RuntimeException("Deposit Amount should be greater than 0");
            }
            case 5:{
                throw new RuntimeException("Deposit Amount Should not be equal or lesser than 5");
            }
        }
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer is not available");
        }

        int newTotalMoney = customer.getTotal_amount() + amount;
        int newTotalCash = newTotalMoney;
        int DepositAmount = amount;
        CustomerService customerService = new CustomerService();
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO();
        boolean checkPin= customer.getPin().equals(pin);
        if(customerService.verifyLogin(checkPin,customer)) {
            customer.setDeposited_amount(DepositAmount);
            customer.setCurrentBalance(newTotalCash);
            customer.setTotal_amount(newTotalMoney);
            session.persist(customer);


            depositResponseDTO.setDeposited_amount(DepositAmount);
            depositResponseDTO.setTotal_amount(newTotalMoney);
            depositResponseDTO.setCurrentBalance(newTotalCash);

        }
        transaction.commit();
        session.close();
        return depositResponseDTO;
    }


    public WithdrawnResponse withDrawMoney(Integer id,Integer amount,String pin){
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        transaction.begin();
        if( amount < 0){
            throw new RuntimeException("Invalid Amount");
        }
        switch(amount){
            case 0:{
                throw new RuntimeException("Cannot With Draw Amount 0");
            }
            case 5:{
                throw new RuntimeException("Cannot With Draw Amount lesser or equal to 5");
            }
        }
        Customer customer = session.find(Customer.class,id);
        if(customer == null){
            throw new RuntimeException("Customer is not available");
        }
        if(amount > customer.getTotal_amount()){
            throw new RuntimeException("Cannot with Draw amount greater than Total Amount");
        }
        int newTotalMoney = customer.getTotal_amount() -  amount;
        int newTotalCash = newTotalMoney;
        int WithDrawnAmount = amount;
        CustomerService customerService = new CustomerService();
        WithdrawnResponse withdrawnResponse = new WithdrawnResponse();
        boolean checkPin= customer.getPin().equals(pin);
        if(customerService.verifyLogin(checkPin,customer)) {
            customer.setTotal_amount(newTotalMoney);
            customer.setCurrentBalance(newTotalCash);
            customer.setWithDrawn_amount(WithDrawnAmount);
            session.persist(customer);


          withdrawnResponse.setCurrentBalance(newTotalCash);
          withdrawnResponse.setTotal_amount(newTotalMoney);
          withdrawnResponse.setWithDrawn_amount(WithDrawnAmount);

        }

        transaction.commit();
        session.close();
        return withdrawnResponse;
    }

    public boolean  verifyAdmin(String name){
           Session session = factory.openSession();
           Transaction transaction = session.getTransaction();
           transaction.begin();
        Customer existingCustomer = (Customer) session.createQuery("SELECT c FROM Customer c WHERE c.accountName = :accountName").setParameter("accountName", name).uniqueResult();
        if(existingCustomer == null){
            throw  new RuntimeException("Customer Does Not Exists");
        }
           boolean verified = false;
           if(existingCustomer.getRole().equals("admin"))
           {
               verified = true;
           }
           transaction.commit();
           session.close();
           return verified;
    }

}
