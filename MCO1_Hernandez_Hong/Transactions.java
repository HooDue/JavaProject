/**
 * This class represents a Transaction which includes the items bought and the payment method used.
 */
public class Transactions {
    
    /** The Cart object containing the items bought in this transaction */
    private Cart boughtItems;
    
    /** The Payment object used for this transaction */
    private Payment payment;

    /**
     * Constructs a new Transaction with the specified cart and payment.
     *
     * @param boughtItems the cart containing the items bought in this transaction
     * @param payment the payment method used for this transaction
     */
    public Transactions(Cart boughtItems, Payment payment){
        this.boughtItems = boughtItems;
        this.payment = payment;
    }

    /**
     * Returns the cart containing the items bought in this transaction.
     *
     * @return the cart of this transaction
     */
    public Cart getBoughtItems() {
        return this.boughtItems;
    }

    /**
     * this is a getter methode to get the payment(money inserted by user)
     * @return the payment of user
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * a method used to calculate the change in a transaction, change is equals to the payment
     * inserted by the user minus the cost of all the items selected by the user
     * @return the change calculated
     */
    public double calculateChange(){
        double payment, cost, change;
        payment = this.getPayment().getBillTotal();
        cost = this.getBoughtItems().genTotalCost();
        change = payment - cost;
        return change;
    }
}
