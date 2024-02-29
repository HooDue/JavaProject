import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a factory for handling the creation and testing of vending machines.
 */
public class HazFactory{
    private RegularVM regularVM;
    private SpecialVendingMachine specialVendingMachine;
    private MaintenanceFeature maintenanceFeature;
    private VMFeature vmFeature;
    private ViewFactory viewFactory;

    /**
     * Constructor to initialize a HazFactory instance.
     */
    public HazFactory(){
        super();
        this.viewFactory = new ViewFactory();
    }
    
    /**
     * Method to create a new regular vending machine and initialize its items and denominations.
     */
    public void createRegularVendingMachine(){
        this.regularVM = new RegularVM();
        this.initializeVendingSlotItems();
        this.regularVM.initializeChangeDenominations();
    }

    public void createSpecialVendingMachine(){
        this.specialVendingMachine = new SpecialVendingMachine("Granola Mix Vending Machine");
        this.initializeVendingSlotItems();
        this.specialVendingMachine.initializeChangeDenominations();
    }


    /**
     * Method to test the maintenance feature on a regular vending machine.
     */
    public void testMaintenanceFeature(){
        this.maintenanceFeature = new MaintenanceFeature(this.regularVM);
        this.maintenanceFeature.maintenanceMenu();
    }
    /**
     * Method to test the vending feature on a regular vending machine.
     */
    public void testVendingFeature(){
        this.vmFeature = new VMFeature(this.regularVM);
        this.vmFeature.vendingFeatureMenu();
    }


    /**
     * Method to initialize items to be stocked in the vending machine.
     */
    public void initializeVendingSlotItems(){
        Item a = new Item("Granola Oats",10,25,120);
        Item b = new Item("Dried fruits",10,15,140);
        Item c = new Item("Mixed nuts",10,10,60);
        Item d = new Item("Mixed seeds",10,15,40);
        Item e = new Item("Puffed Rice",10,30,60);
        Item f = new Item("Chocolate Chips",10,30,60);
        Item g = new Item("Shredded Coconut",10,30,60);
        Item h = new Item("Candied Nuts",10,30,60);

        this.regularVM.getItemSlots().get(0).setItemStored(a);
        this.regularVM.getItemSlots().get(1).setItemStored(b);
        this.regularVM.getItemSlots().get(2).setItemStored(c);
        this.regularVM.getItemSlots().get(3).setItemStored(d);
        this.regularVM.getItemSlots().get(4).setItemStored(e);
        this.regularVM.getItemSlots().get(5).setItemStored(f);
        this.regularVM.getItemSlots().get(6).setItemStored(g);
        this.regularVM.getItemSlots().get(7).setItemStored(h);

    }
    /**
     * Method to provide a testing menu for the user to test different vending machine features.
     */
    public void testingMenu(){
        int choice;
        choice = this.viewFactory.showTestingPanel();
        switch (choice){
            case 1:
                this.testVendingFeature();
                this.testingMenu();
                break;
            case 2:
                this.testMaintenanceFeature();
                this.testingMenu();
                break;
            case 3:
                System.exit(0);
        }
    }
    /**
     * Method to display the main menu of the application.
     */
    public void mainMenu(){
        this.showMain("HazFactory Menu Panel", "Welcome User");
        this.createButton("Create Regular Vending Machine", 250, 300);
        this.createButton("Create Special Vending Machine", 250, 300);
        this.getjButtons().get(0).addActionListener(e ->this.createVM(1));
    }

    public void createVM(int choice){
        choice = this.viewFactory.showMenuPanel();
        switch (choice){
            case 1:
                this.createRegularVendingMachine();
                this.getFrame1().dispose();
                MenuPanel window1 = new MenuPanel();
                //this.getPanels().get(0).setVisible(true);
                //this.testingMenu();
                break;
            case 2:
                System.exit(0);
        }
    }

    /**
     * Main method to run the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args){
       HazFactory hazFactory = new HazFactory();
       hazFactory.mainMenu();
    }

}
