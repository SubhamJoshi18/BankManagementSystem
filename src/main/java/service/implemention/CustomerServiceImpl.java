package service.implemention;

public interface CustomerServiceImpl {
    String createNewAccount(String name, String aname, String pin, Long phoneNumber);
    String loginAccount(String anumber,String pin);

}
