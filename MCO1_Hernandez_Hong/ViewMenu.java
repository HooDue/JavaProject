import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * ViewMenu class to present and handle user interaction with menus. separation lines,
 * program messages and asks input from user.
 */
public class ViewMenu {

    /**
     * Scanner for string input.
     */
    public Scanner st = new Scanner(System.in);

    /**
     * Scanner for number input.
     */
    public Scanner num = new Scanner(System.in);

    /**
     * Scanner for decimal input.
     */
    public Scanner deci = new Scanner(System.in);

    /**
     * ViewMenu Constructor, initializes scanners
     */
    public ViewMenu() {
    }

    /**
     * Presents the main menu to the user and returns their choice.
     *
     * @return choice1
     */
    public int mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Create a Vending Machine\n" +
                "2. Test the Vending Machine Features\n" +
                "3. Exit\n");
        int choice1 = this.st.nextInt();
        return choice1;
    }

    /**
     * Presents the maintenance feature panel to the user and returns their choice.
     *
     * @return choice1
     */
    public int maintenanceFeaturePanel(){
        int choice1;
        System.out.println("\n\nMaintenance Feature Menu");
        System.out.println("1. Restock\n" +
                "2. Change Price of Items\n" +
                "3. Replenish Money for change\n" +
                "4. View ending inventory\n"+
                "5. View Transaction summary\n"+
                "6. Exit\n");
        System.out.println("Enter the number of Choice: ");
        choice1 = this.num.nextInt();
        return choice1;
    }


    /**
     * Presents the restock panel to the user and returns their choice.
     *
     * @return choice1
     */
    public int restockPanel() {
        System.out.println("Do you want to:");
        System.out.println("1. See vending machine Items and choose index to restock\n" +
                "2. Replenish item stock in Vending Machine\n" +
                "3. Assign new item to empty Slot in Vending Machine\n" +
                "4. Delete an item in the Vending Machine slot\n" +
                "5. back\n");
        int choice1 = this.num.nextInt();
        return choice1;
    }

    /**
     * menu panel to be displayed when user chooses 1. of restock option
     * Presents restock option to the user and returns their choice.
     *
     * @return the index of choice to restock
     */
    public int restockPanel1() {
        System.out.println("Enter the index of Choice to Restock: ");
        int choice1 = this.num.nextInt();
        return choice1;
    }

    /**
     * menu panel to be displayed when user chooses 4. of restock option
     * Presents replenish option to the user and returns their choice.
     *
     * @return the index of choice to replenish
     */
    public int restockPanel2() {
        System.out.println("Enter the index of Choice to Replenish: ");
        int choice1 = this.num.nextInt();
        return choice1;
    }

    /**
     * menu panel to be displayed when user chooses 3. of restock option
     * Collects restock details from the user and returns them.
     *
     * @return a List of restock details provided by the user
     */
    public List<String> restockPanel3(){
        System.out.println("type 0 for item cost or calories that is undecided, separate inputs by commas(,)\n" +
                "[Format:] Item name,Quantity(integer),Cost(double),Calories(integer) ");
        String stInput =  this.st.nextLine();
        return Arrays.asList(stInput.split(","));
    }

    /**
     * menu panel to be displayed when user chooses the change Price of items option
     * Presents the price change menu to the user and returns their choice.
     *
     * @return choice, the user's selected option
     */
    public int changePricePanel() {
        System.out.println("Change Price of Items\n" +
                "1. [Change Price in Item slots]\tPrint all Item then select index to change price\n" +
                "2. [Change Price in Item slots]\tSearch Item in vending machine slots by name and change price\n" +
                "3. back");
        int choice = this.num.nextInt();
        return choice;
    }

    /**
     * Prints out the income summary panel.
     *
     * @param previous previous income amount
     * @param currIncome current income amount
     */
    public void incomeCollectPanel(double previous, double currIncome) {
        System.out.println("\n=============================\nPrevious Income: " +previous + " Pesos\n" +
                "Current Income(starting from last restocking): " + currIncome + " Pesos\n");
        System.out.println("Printing Summary: \n");
    }


    /**
     * Asks the user for an item quantity.
     *
     * @return the item quantity input by the user
     */
    public int askInputNum() {
        System.out.println("Enter the quantity: ");
        int input = this.num.nextInt();
        return input;
    }

    /**
     * Asks the user for an index of choice.
     *
     * @return the index input by the user
     */
    public int askInputIndex() {
        System.out.println("Enter index of choice: ");
        int input = this.num.nextInt();
        return input;
    }

    public double askInputDenomination(){
        System.out.println("Enter the value the denomination to replenish: ");
        return this.deci.nextDouble();
    }

    /**
     * Asks the user for a string input to search for an item.
     *
     * @return the string input by the user
     */
    public String askInputString() {
        System.out.println("Enter the item name to search: ");
        String name = this.st.nextLine();
        return name;
    }

    /**
     * Asks the user for a new cost.
     *
     * @return the cost input by the user
     */
    public double askInputCost() {
        System.out.println("Enter new cost: ");
        double cost = this.deci.nextDouble();
        return cost;
    }

    /**
     * Asks the user whether they're done or not.
     *
     * @return a boolean indicating whether the user is done or not
     */
    public boolean askIfDone(){
        boolean done = false;
        System.out.println("Done?\t[NO] - 0\n\t\t[YES] - 1");
        if (this.num.nextInt() ==1)
        {
            done = true;
        }
        return done;
    }

    /**
     * Prints an error message saying that the denomination does not exist.
     */
    public void printDenoNotExist(){
        System.out.println("This Denomination does not exist\n");
    }

    /**
     * Prints an error message when a slot is empty.
     */
    public void printEmpty() {
        System.out.println("error [This slot is empty]");
    }

    /**
     * Prints the starting inventory.
     */
    public void printStarting() {
        System.out.println("\nStarting Inventory: \n===========================================================\n");
    }

    /**
     * Prints the ending inventory.
     */
    public void printEnding() {
        System.out.println("\nEnding Inventory: \n===========================================================\n");
    }

    /**
     * Prints a message if the starting and ending inventory are the same.
     */
    public void printDidNotRestock() {
        System.out.println("\nDid not restock yet, Inventory from last restock: \n" +
                "===========================================================\n");
    }

    /**
     * Prints a message indicating that something has been updated.
     */
    public void printUpdateMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\nUpdated: ");
    }

    /**
     * Clears the console screen.
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}