package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;


    private String accountName;

    private String accountNumber;

    private String pin;


    private long PhoneNumber;


    private Integer currentBalance;
    private Integer  total_amount;

    private Integer deposited_amount;

    private Integer  withDrawn_amount;



    private String role;

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getDeposited_amount() {
        return deposited_amount;
    }

    public void setDeposited_amount(Integer deposited_amount) {
        this.deposited_amount = deposited_amount;
    }

    public Integer getWithDrawn_amount() {
        return withDrawn_amount;
    }

    public void setWithDrawn_amount(Integer withDrawn_amount) {
        this.withDrawn_amount = withDrawn_amount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "account_id=" + account_id +
                ", accountName='" + accountName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", currentBalance=" + currentBalance +
                ", total_amount=" + total_amount +
                ", deposited_amount=" + deposited_amount +
                ", withDrawn_amount=" + withDrawn_amount +
                ", role='" + role + '\'' +
                '}';
    }
}