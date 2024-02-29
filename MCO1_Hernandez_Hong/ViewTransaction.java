import java.util.ArrayList;

/**
 * This class is responsible for viewing various information about different transactions.
 */
public class ViewTransaction {
    /**list of transactions**/
    private ArrayList<Transactions> transactions;

    /**ViewCart object to display information about bought items**/
    private ViewCart viewCart;

    /**ViewCart object to display information about customer's payment**/
    private ViewPayment viewPayment;

    /**
     * Constructs a ViewTransaction with specified list of transactions.
     * @param transactions List of transactions
     */
    public ViewTransaction(ArrayList<Transactions> transactions){
        this.transactions = transactions;
    }

    /**
     * Prints all the transactions, displaying the items bought, 
     * their total cost and their total calories.
     */
    public void printAllTransactions(){
        int i;
        for (i=0; i<this.transactions.size(); i++){
            if(this.transactions.get(i)!=null){
                System.out.println("Transaction "+(i+1)+" :");
                this.viewCart = new ViewCart(this.transactions.get(i).getBoughtItems());
                this.viewPayment = new ViewPayment(this.transactions.get(i).getPayment());
                this.viewCart.printItems();
                this.viewCart.printTotalCost();
                System.out.println("Customer's Payment: ");
                this.viewPayment.printTotalAmount();
                this.viewCart.printTotalCalories();
                System.out.println("----------------------------\n");
            }

        }
    }

    /**
     * Prints a transaction by index
     * @param index the index of where the transaction is stored in the Arraylist
     */
    public void printTransaction(int index){
        if(this.transactions.get(index)!=null){
            System.out.println("Transaction "+(index+1)+" :");
            this.viewCart = new ViewCart(this.transactions.get(index).getBoughtItems());
            this.viewPayment = new ViewPayment(this.transactions.get(index).getPayment());
            this.viewCart.printItems();
            this.viewCart.printTotalCost();
            System.out.println("Customer's Payment: ");
            this.viewPayment.printTotalAmount();
            this.viewCart.printTotalCalories();
            this.printChange(index);
            System.out.println("----------------------------\n");
        }
    }


    /**
     * Prints the total income from all transactions.
     */
    public void printTransactionTotal(){
        double total = 0;
        int i;
        for (i=0; i<this.transactions.size(); i++){
            total = total + this.transactions.get(i).getBoughtItems().genTotalCost();
        }
        System.out.println("Transaction total income: " + total);
    }

    /**
     * Prints change calculated
     * @param index the index of the transaction of which the change is calculated
     */
    public void printChange(int index){
        System.out.println("Change: "+this.transactions.get(index).calculateChange());
    }
}
