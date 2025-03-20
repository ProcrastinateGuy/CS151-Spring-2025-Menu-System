import java.util.ArrayList;

public class Items {
    private ArrayList<Item> items;

    /**
     * Default constructor - Creates Empty Item ArrayList
     */
    public Items() {
        this.items = new ArrayList<>(); // Initialize with a default size
    }

    /**
     * Constructor - Copies another Item ArrayList to this object.
     * @param size - int
     */
    public Items(ArrayList<Item> items) {
        this.items = items;
    }

    //Setters and Getters

    /**
     * Appends and Item object to the ArrayList
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Removes an Item object from the ArrayList given the name
     * @param name
     */
    public void removeItemByName(String name) {
        Item itemToRemove = getItemName(name);
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Removes an Item object from the ArrayList given the ID
     * @param id
     */
    public void removeItemByID(int id) {
        Item itemToRemove = getItemID(id);
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Get item by name
     * @param ItemName - String
     * @return item
     */
    public Item getItemName(String ItemName) {
        for (Item item : items) {
            // Why the hell does == not check value equality?
            // If I wanted to check reference equality, I would use C++
            if (item.getName().equals(ItemName)) {
                return item;
            }
        }
        return null; // Return null if item not found
    }

    /**
     * Get item by ID
     * @param ItemID - int
     * @return item
     */
    public Item getItemID(int ItemID) {
        for (Item item : items) {
            // This is how == should work smh
            if (item.getItemID() == ItemID) {
                return item;
            }
        }
        return null; // Return null if item not found
    }


}
