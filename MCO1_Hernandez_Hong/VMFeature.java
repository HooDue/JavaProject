import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to manage the vending machine features.
 */
public class VMFeature {
    /**change of the customer**/
    private double customerChange;

    /**the current vending machine being maintained**/
    private RegularVM curVM;

    /**the slots of the current vending machine being maintained**/
    private ArrayList<ItemSlot> itemSlots;

    /**the list of transactions made of the current vending machine**/
    private ArrayList<Transactions> transactions;

    /**the valid denominations accepted by the current vending machine**/
    private ArrayList<Denomination> validDeno;

    /**the available denomination and pieces for giving as change**/
    private ArrayList<Denomination> vmChange;

    /**temporary array holding the denomination that will be given as change to customer**/
    private ArrayList<Denomination> change;

    /**the income of the vending machine in bills**/
    private ArrayList<Denomination> vmIncome;

    /**the payment of the customer, or money that the customer inserted**/
    private Payment payment;

    /**the cart to hold the items selected by the customer**/
    private Cart cart;

    /**used to print and display item slots to the user**/
    private ViewVMSlots viewVMSlots;

    /**used to print and display the transactions to the user**/
    private ViewTransaction viewTransaction;

    /**used to print and display the available bills to give as change to users**/
    private ViewVMDeno viewDenoChange;

    /**used to print and display the income made by the vending machine**/
    private ViewVMDeno viewDenoIncome;

    /**used to print and display the payment of the user**/
    private ViewPayment viewPayment;

    /**used to print and display the items selected by the user**/
    private ViewCart viewCart;

    /**used to print and display the menu panels and messages to user, also asks for user input**/
    private ViewVMFeature viewVMFeature;

    /**
     * Creates a VMFeature object to manage a given vending machine.
     * 
     * @param regularVM The vending machine to manage.
     */
    public VMFeature(RegularVM regularVM) {
        this.customerChange = 0;
        this.curVM = regularVM;
        this.payment = new Payment();
        this.cart = new Cart();
        this.itemSlots = this.curVM.getItemSlots();
        this.transactions = this.curVM.getTransactions();
        this.vmChange = this.curVM.getVmChange();
        this.change = new ArrayList<>();
        this.vmIncome = this.curVM.getVmIncome();
        this.validDeno = this.curVM.getValidDenominations();
        this.viewVMSlots = new ViewVMSlots(this.itemSlots);
        this.viewTransaction = new ViewTransaction(this.transactions);
        this.viewDenoChange = new ViewVMDeno(this.change);
        this.viewDenoIncome = new ViewVMDeno(this.vmIncome);
        this.viewPayment = new ViewPayment(this.payment);
        this.viewCart = new ViewCart(this.cart);
        this.viewVMFeature = new ViewVMFeature(this.curVM);
    }
    /**
    * This method manages the vending feature menu.
    */
    public void vendingFeatureMenu() {
        int choice1;            
        boolean done = false;   
        boolean done2 = false;
        boolean found;
        List<String> userInput = new ArrayList<>();    

        this.viewVMFeature.chooseItemPanel1();
        this.viewVMSlots.printAllSlots();
        this.viewVMFeature.chooseItemPanel2();

        while(!done){
            found = false;
            userInput = this.viewVMFeature.insertPayment();
            Denomination input = new Denomination(Double.parseDouble(userInput.get(0)), Integer.parseInt(userInput.get(1)));
            for (int i =0; i<this.validDeno.size()&&!found; i++) {
                if (this.validDeno.get(i).getValue()==(input.getValue())) {
                    found = true;
                    this.payment.getDenominations().add(input);
                    this.viewVMFeature.printAccepted();
                }
            }
            if (!found){
                this.viewVMFeature.printNotAccepted();
            }

            int j = this.viewVMFeature.askIfDone();
            if(j==1){
                done = true;
            }
            else done = false;
        }

        if (done){
            this.viewPayment.printBills();
            this.viewPayment.printTotalAmount();

            choice1 = this.viewVMFeature.printVMFeatureMenu();
            switch (choice1){
                case 1: //choose items
                    int j;
                    this.viewPayment.printBills();
                    this.viewPayment.printTotalAmount();
                    this.viewVMFeature.chooseItemPanel3();

                    while (!done2)
                    {
                        userInput = this.viewVMFeature.chooseItem();
                        int index = Integer.parseInt(userInput.get(0));
                        int quantity = Integer.parseInt(userInput.get(1));

                        if ((this.itemSlots.get(index).getItemStored().getAmount()>=quantity)){
                            Item item = new Item(this.itemSlots.get(index).getItemStored().getItemName(),
                                    quantity, this.itemSlots.get(index).getItemStored().getCost(),
                                    this.itemSlots.get(index).getItemStored().getCalories());
                            this.cart.getItemBought().add(item);
                            this.viewVMFeature.printAdded();
                        }
                        else {
                            this.viewVMFeature.printNotAdded();
                        }
                        j = this.viewVMFeature.askIfDone();
                        if(j==1){
                            done2 = true;
                        }
                        else done2=false;
                    }

                    if (done2){
                        Transactions t = new Transactions(this.cart, this.payment);
                        this.transactions.add(t);
                        this.viewCart.printItems();
                        this.viewCart.printTotalCost();
                        this.viewCart.printTotalCalories();
                        //this.viewTransaction.printTransaction(this.transactions.indexOf(t));
                        this.viewTransaction.printChange(this.transactions.indexOf(t));
                        this.produceChange();
                        this.payment.getDenominations().clear();
                        this.cart.getItemBought().clear();
                    }
                    break;
                case 2: //produce change (give back money)
                    this.viewVMFeature.printReturnCash(this.payment.getBillTotal());
                    this.payment.getDenominations().clear();
            }


        }



    }
    /**
     * Produces change by given denomination.
     *
     * @param payment  The payment object.
     * @param vmChange The change in the vending machine.
     * @param value    The value to be deducted.
     */
    public void produceChangeByDeno(Payment payment, ArrayList<Denomination> vmChange, double value){
        double payed = this.payment.getBillTotal();
        double cost = this.cart.genTotalCost();
        int pieces = (int)(this.customerChange/value);
        boolean found = false;


        if (pieces !=0){
            found = false;
            for (int i =0; i<this.vmChange.size()&&!found; i++){
                if(this.vmChange.get(i).getValue()==value){
                    found = true;
                    if(this.vmChange.get(i).getPieces()>=pieces){
                        Denomination bill = new Denomination(value, pieces);
                        this.change.add(bill);
                        this.customerChange = (this.customerChange - (pieces*value));
                    }
                    else{
                        Denomination bill = new Denomination(value, this.vmChange.get(i).getPieces());
                        this.change.add(bill);
                        this.customerChange = (this.customerChange - (this.vmChange.get(i).getPieces()*value));
                    }
                }
            }
        }
    }

    /**
     * This method handles the production of change.
     */
    public void produceChange(){
        double payed = payment.getBillTotal();
        double cost = cart.genTotalCost();
        customerChange = payed - cost;
        double customerChange = this.customerChange;
        boolean removed = false;

        for (int i =0; i<validDeno.size(); i++){
            produceChangeByDeno(payment, vmChange, validDeno.get(i).getValue());
        }

        if (this.customerChange == 0){
            for (int i =0; i<this.payment.getDenominations().size(); i++){
                this.vmIncome.add(this.payment.getDenominations().get(i));
            }

            for (int i =0; i<this.change.size(); i++){
                removed = false;
                for (int j = 0; j<this.vmChange.size() && !removed; j++){
                    if (this.vmChange.get(j).getValue() == this.change.get(i).getValue()){
                        int prevPieces = this.vmChange.get(i).getPieces();
                        int piecesToRemove = this.change.get(i).getPieces();
                        this.vmChange.get(j).setPieces(prevPieces-piecesToRemove);
                        removed = true;
                    }
                }
            }

            for (int i = 0; i<cart.getItemBought().size(); i++){
                removed = false;
                for (int j = 0; j<curVM.getSlotLimit() && !removed; j++){
                    if (itemSlots.get(j).getItemStored() != null){
                        if (cart.getItemBought().get(i).getItemName().equals(itemSlots.get(j).getItemStored().getItemName())){
                            int prevAmount = itemSlots.get(j).getItemStored().getAmount();
                            itemSlots.get(j).getItemStored().setAmount(prevAmount - cart.getItemBought().get(i).getAmount());
                            removed = true;
                        }
                    }

                }
            }
            this.viewVMFeature.printSuccessBuy(customerChange);
            this.viewDenoChange.printDenoForChange();
            this.viewVMFeature.goodByePanel();
        } else {
            this.viewVMFeature.printNotSuccessBuy(this.payment.getBillTotal());
            this.viewVMFeature.printVMFeatureMenu();
        }
    }
}