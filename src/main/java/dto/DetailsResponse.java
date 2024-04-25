package dto;

public class DetailsResponse {

    private Integer currentBalance;
    private Integer  total_amount;

    private Integer deposited_amount;

    private Integer  withDrawn_amount;

    public DetailsResponse(){

    }
    @Override
    public String toString() {
        return "DetailsResponse{" +
                "currentBalance=" + currentBalance +
                ", total_amount=" + total_amount +
                ", deposited_amount=" + deposited_amount +
                ", withDrawn_amount=" + withDrawn_amount +
                '}';
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
}
