package model;


import jakarta.persistence.*;

@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;

    private String name;

    private String email;


    private Integer age;


    private Integer total_money;


    private Integer current_Money;


    private Integer withdrawn_money;


    private Integer deposited_money;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getTotal_money() {
        return total_money;
    }

    public void setTotal_money(Integer total_money) {
        this.total_money = total_money;
    }

    public Integer getCurrent_Money() {
        return current_Money;
    }

    public void setCurrent_Money(Integer current_Money) {
        this.current_Money = current_Money;
    }

    public Integer getWithdrawn_money() {
        return withdrawn_money;
    }

    public void setWithdrawn_money(Integer withdrawn_money) {
        this.withdrawn_money = withdrawn_money;
    }

    public Integer getDeposited_money() {
        return deposited_money;
    }

    public void setDeposited_money(Integer deposited_money) {
        this.deposited_money = deposited_money;
    }
}
