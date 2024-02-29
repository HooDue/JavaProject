import java.util.ArrayList;

/**
 * This class represents a shopping Cart. 
 * It includes functionalities like adding items, counting number of items, and calculating total cost and calories.
 */
public class Cart {
    /**List of items bought in the cart**/
    private ArrayList<Item> itemBought;

    /**
     * Constructor for the Cart class.
     * Initializes an empty ArrayList of items.
     */
    public Cart(){
        this.itemBought = new ArrayList<>();
        this.itemBought.clear();
    }

    /**
     * Gets an item bought by its name.
     *
     * @param name The name of the item.
     * @return The item if found. If the item is not found, returns the last item in the list.
     */
    public Item getItemBought(String name){
        int i =0;
        boolean found = false;
        for (i =0; i<this.itemBought.size() && !found; i++){
            if (this.itemBought.get(i).getItemName().equals(name)){
                found = true;
            }
        }
        return this.itemBought.get(i);
    }

    /**
     * Gets the list of all items bought.
     *
     * @return The list of items bought.
     */
    public ArrayList<Item> getItemBought() {
        return this.itemBought;
    }

    /**
     * Calculates the total calories of all items in the cart.
     *
     * @return The total calories.
     */
    public  double genTotalCalories(){
        int i = 0;
        double total = 0;
        for (i =0; i<this.itemBought.size(); i++){
            total = total + this.itemBought.get(i).getCalories()*this.itemBought.get(i).getAmount();
        }
        return total;
    }

    /**
     * Calculates the total cost of all items in the cart.
     *
     * @return The total cost.
     */
    public double genTotalCost(){
        int i = 0;
        double total = 0;
        for (i =0; i<this.itemBought.size(); i++){
            total = total + this.itemBought.get(i).getCost()*this.itemBought.get(i).getAmount();
        }
        return total;
    }

    /**
     * a setter method to set the ItemsBought
     * @param itemBought
     */
    public void setItemBought(ArrayList<Item> itemBought) {
        this.itemBought = itemBought;
    }



}
