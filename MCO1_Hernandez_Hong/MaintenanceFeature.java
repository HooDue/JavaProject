import java.util.ArrayList;
import java.util.List;


/**
 * controller class for maintenance feature of the vending machine
 */
public class MaintenanceFeature {
    /**the current vending machine being maintained**/
    private RegularVM curVM;

    /**the income of the previous restocking**/
    private double currIncome;

    /**the income of the last restocking**/
    private double prevIncome;

    /**the slots of the current vending machine being maintained**/
    private ArrayList<ItemSlot> itemSlots;

    /**the list of transactions made of the current vending machine**/
    private ArrayList<Transactions> transactions;

    /**the available denomination and pieces for giving as change**/
    private ArrayList<Denomination> vmChange;

    /**the income of the vending machine in bills**/
    private ArrayList<Denomination> vmIncome;

    /**used to print and display item slots to the user**/
    private ViewVMSlots viewVMSlots;

    /**used to print and display the transactions to the user**/
    private ViewTransaction viewTransaction;

    /**used to print and display the available bills to give as change to users**/
    private ViewVMDeno viewDenoChange;

    /**the restock status of the vending machine**/
    private boolean restocked;

    /**used to print and display the income made by the vending machine**/
    private ViewVMDeno viewDenoIncome;

    /**used to print and display the menu panels and messages to user, also asks for user input**/
    private ViewMenu viewMenu;


    /**
     * this is a constructor for the maintenance feature of vending machines. accepts
     * the vending machine as parameter in order to access the components of the
     * vending machine.
     * @param regularVM the instance of vending machine that user wishes to access the
     *                  maintenance feature of.
     */
    public MaintenanceFeature(RegularVM regularVM){
        this.curVM = regularVM;
        this.itemSlots = this.curVM.getItemSlots();
        this.transactions = this.curVM.getTransactions();
        this.vmChange = this.curVM.getVmChange();
        this.vmIncome = this.curVM.getVmIncome();
        this.viewVMSlots = new ViewVMSlots(this.itemSlots);
        this.viewTransaction = new ViewTransaction(this.transactions);
        this.viewDenoChange = new ViewVMDeno(this.vmChange);
        this.currIncome = 0;
        this.prevIncome = 0;
        //this.viewDenoIncome = new ViewVMDeno(this.vmIncome);
        this.viewMenu = new ViewMenu();
        this.restocked = false;
    }

    /**
     * this method is used to restock the items (set the quantity)
     * @param index     the index where the item is stored in the stockRoom
     * @param amount    the amount / quantity of item that user wishes to restock
     */
    public void restock(int index, int amount){
        this.itemSlots.get(index).getItemStored().setAmount(amount);
    }

    /**
     * this method is used to replenish item stock (add quantity to the existing
     * quantity of an item)
     * @param index     the index of vending machine where the item is stored
     * @param amount    the amount / quantity of items that user wishes to replenish
     */
    public void replenish(int index, int amount){
        int temp = this.itemSlots.get(index).getItemStored().getAmount();
        this.itemSlots.get(index).getItemStored().setAmount(amount+temp);
    }

    /**
     * this method is used to delete/remove an item in the vending machine slot
     * @param index     the index of the vending machine slot at where the item
     *                  is stored
     */
    public void deleteItemInSlot(int index){
        this.itemSlots.get(index).setItemStored(null);
    }


    /**
     * this method is used to change the price of an item
     * @param index     the index of slot where the item is stored
     * @param cost      the new price that the user wants to replace
     *                  with the old price
     */
    public void changeCost(int index, double cost){
        this.itemSlots.get(index).getItemStored().setCost(cost);
    }


    /**
     * this method is used to search for a specific item in the vending machine
     * slots by item name, or substring of the item name.
     * @param name      the name of the item that the user wants to search for
     * @return          the index of the vending machine slot where the item is
     *                  stored if found
     */
    public int searchItemInSlots(String name){
        int iIndex;
        iIndex = this.viewVMSlots.displayItemByName(name);
        return iIndex;
    }

    /**
     * this method replenishes the quantity of denominations for change in the vending machine.
     * searches the value entered by the user in the list of denominations for change,
     * if found, will ask user of how much he wishes to replenish. else will show an error message
     * @param value the value of the denomination
     */
    public void replenishDenomination(double value){
        boolean found = false;
        int i,input;
        for (i =0; i<this.vmChange.size()&&found==false; i++){
            if(this.vmChange.get(i).getValue()==value){
                found = true;
                input = this.viewMenu.askInputNum();
                int prevPieces = this.vmChange.get(i).getPieces();
                int newPieces = input+prevPieces;
                this.vmChange.get(i).setPieces(newPieces);
                System.out.println(newPieces);
                this.viewMenu.printUpdateMessage();
                this.viewDenoChange.printDenoInfo(value);
            }
        }
        if(!found){
            this.viewMenu.printDenoNotExist();
        }
    }

    /**
     * this method serves as the function panel for maintenance features, with
     * methods to display the needed menu panels and to perform the options
     * selected by the user. As well to update the components inside the vending
     * machine after performing the maintenance features.
     */
    public void maintenanceMenu(){
        ArrayList<Transactions> tempTransactions = new ArrayList<>();

        //temporary Arraylist storing the slots and their items inside the vending machine slots
        ArrayList<ItemSlot> tempSlot = new ArrayList<>();
        //storing the items
        for (int i =0; i<this.itemSlots.size(); i++){
            ItemSlot a = this.itemSlots.get(i);
            tempSlot.add(a);
        }


        int choice1, choice2, choice3;
        double incomeTotal=0;

        choice1 = this.viewMenu.maintenanceFeaturePanel();
        switch (choice1){
            case 1: //restock
                    choice1 = this.viewMenu.restockPanel();
                    this.restocked = this.restockMenu(choice1);
                    if (this.restocked){
                        tempTransactions = (ArrayList<Transactions>) this.transactions.clone();
                        for (int i = 0; i < tempTransactions.size(); i++) {
                            incomeTotal = incomeTotal + tempTransactions.get(i).getBoughtItems().genTotalCost();
                        }
                        if (this.prevIncome !=0){
                            this.prevIncome = this.currIncome;
                            this.currIncome = incomeTotal;
                        }
                        else {
                            for (int i = 0; i < tempTransactions.size(); i++) {
                                incomeTotal = incomeTotal + tempTransactions.get(i).getBoughtItems().genTotalCost();
                            }
                            this.prevIncome = 0;
                            this.currIncome = incomeTotal;
                        }
                        this.curVM.getVmIncome().clear();
                        this.curVM.getVmCart().getItemBought().clear();
                        this.transactions.clear();
                    }
                    this.maintenanceMenu();
                    break;

            case 2: //change item price
                /* first to ask the choice of user, then perform change price*/
                choice2 = this.viewMenu.changePricePanel();
                this.changeItemPrice(choice2);
                this.maintenanceMenu();
                break;

            case 3: //replenish denomination for change
                boolean done = false;
                while (!done){
                    if (this.vmChange!=null){
                        this.viewDenoChange.printDenoForChange();
                        double input = this.viewMenu.askInputDenomination();
                        this.replenishDenomination(input);
                        done = this.viewMenu.askIfDone();
                    }
                    if(done){
                        this.maintenanceMenu();
                        break;
                    }
                }


                break;

            case 4: //view starting inventory and ending inventory
                /* First check if the inventory is being restocked, if it is restocked, will display the list of items in
                    the vending machine slots before the restocking and after the restocking. if it is not restocked,
                    will display that the inventory is not restocked and will also display the items in both stockRoom and vending
                    machine slots.
                 */
                if (this.restocked){
                    ViewVMSlots prevSL = new ViewVMSlots(tempSlot);
                    prevSL.printAllSlots();
                    this.viewMenu.printEnding();
                }
                else{
                    this.viewMenu.printDidNotRestock();
                }
                this.viewVMSlots.printAllSlots();
                this.maintenanceMenu();

            case 5: //Transaction summary
                /* first get all the total amount of transactions made in all transactions, then set the currentIncome to
                    equal to this amount, then print the past and current total income and print all the transactions to show
                    as summary.
                 */

                this.viewMenu.incomeCollectPanel(this.prevIncome, this.currIncome);
                this.viewTransaction = new ViewTransaction(tempTransactions);
                this.viewTransaction.printAllTransactions();
                this.maintenanceMenu();
                break;

            case 6:
                break;

        }


    }
    /**
     * Changes the price of an item based on user's choice.
     * 
     * @param choice The option chosen by user to decide where and how to change the price.
     * 1. Change in vending machine (VM) slot, print all then choose index
     * 2. Change in VM slot, search by name then change price
     * 3. Change in stockroom, print all then choose index
     * 4. Change in stockroom, search by name then change price
     * 5. Go back to the maintenance menu
     */
    public void changeItemPrice(int choice){
        int iIndex;
        double input;
        String stInput;

        switch(choice){
            case 1: // change in vm slot, print all then choose index
                this.viewVMSlots.printAllSlots();
                iIndex = this.viewMenu.askInputIndex();
                while (this.itemSlots.get(iIndex).getItemStored() == null){
                    this.viewMenu.printEmpty();
                    iIndex = this.viewMenu.askInputIndex();
                }
                input = this.viewMenu.askInputCost();
                this.changeCost(iIndex, input);
                this.viewMenu.printUpdateMessage();
                this.viewVMSlots.printSlot(iIndex);
                this.viewMenu.clearScreen();
                this.maintenanceMenu();
                break;

            case 2: //change in vm slot, search by name then change price
                stInput = this.viewMenu.askInputString();
                iIndex = this.searchItemInSlots(stInput);
                if (iIndex!=-1) {
                    input = this.viewMenu.askInputCost();
                    this.changeCost(iIndex, input);
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printSlot(iIndex);
                }
                break;

            case 3: // back
                this.maintenanceMenu();
        }
    }

    /**
     * Provides various options to restock items based on user's choice.
     *
     * @param choice The option chosen by user to decide how to restock items.
     * 1. Display all items in vending machine and choose index to restock
     * 2. Replenish Item Stock in Vending Machine
     * 3. Assign new item to empty Slot in Vending Machine
     * 4. Remove item from a slot in the vending machine
     */
    public boolean restockMenu(int choice) {
        int iIndex, input;
        String stInput;
        boolean done = false;

        switch (choice) {
            case 1: //display all items and choose index to restock
                while (!done){
                    this.viewVMSlots.printAllSlots();
                    iIndex = this.viewMenu.restockPanel1();
                    input = this.viewMenu.askInputNum();
                    this.restock(iIndex, input);
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printSlot(iIndex);
                    done = this.viewMenu.askIfDone();
                }
                if(done){
                    restocked = true;
                }

                break;
            case 2: //Replenish Item Stock in Vending machine
                while (!done) {
                    this.viewVMSlots.printAllSlots();
                    iIndex = this.viewMenu.restockPanel2();
                    input = this.viewMenu.askInputNum();
                    this.replenish(iIndex, input);
                    done = this.viewMenu.askIfDone();
                }
                if(done){
                    restocked = true;
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printAllSlots();
                }
                break;

            case 3: //Assign new item to empty Slot in Vending Machine
                while (!done) {
                    this.viewVMSlots.printEmptySlots(this.curVM.getSlotLimit());
                    iIndex = this.viewMenu.askInputIndex();
                    List<String> userInput1 = new ArrayList<>();
                    userInput1 = this.viewMenu.restockPanel3();
                    Item item1 = new Item(userInput1.get(0), Integer.parseInt(userInput1.get(1)), Double.parseDouble(userInput1.get(2)), Integer.parseInt(userInput1.get(3)));
                    this.itemSlots.get(iIndex).setItemStored(item1);
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printSlot(iIndex);
                    done = this.viewMenu.askIfDone();
                }
                if(done){
                    restocked = true;
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printAllSlots();
                }

                break;

            case 4:
                while (!done) {
                    this.viewVMSlots.printAllSlots();
                    iIndex = this.viewMenu.askInputIndex();
                    this.deleteItemInSlot(iIndex);
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printSlot(iIndex);
                    done = this.viewMenu.askIfDone();
                }
                if(done){
                    restocked = true;
                    this.viewMenu.printUpdateMessage();
                    this.viewVMSlots.printAllSlots();
                }
                break;

        }
    return restocked;
    }
}