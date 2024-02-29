import java.util.Scanner;

/**
 * this is a View class for displaying menu panels, options and for asking user inputs for the HazFactory
 */
public class ViewFactory {
    /**a scanner for integer**/
    private Scanner num = new Scanner(System.in);

    /**
     * constructor for view Factory class
     */
    public ViewFactory(){
    }

    /**
     * Shows the menu panel and options that user may choose
     * @return the choice of the user
     */
    public int showMenuPanel(){
        System.out.println("Welcome to HazFactory!\n[1] Create Regular Vending Machine\n" +
                "[2] Create Special Vending Machine\n[3] Exit\nInput the number of choice: ");
        return  this.num.nextInt();
    }

    /**
     * shows the testing panel and options that user may choose
     * @return the choice of the user
     */
    public int showTestingPanel(){
        System.out.println("\nWelcome to HazFactory!\n[1] Test Vending Machine Feature\n" +
                "[2] Test Maintenance Feature\n[3] Exit\nInput the number of choice: ");
        return  this.num.nextInt();
    }
}
