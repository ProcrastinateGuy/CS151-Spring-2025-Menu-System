import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ItemShelf{
    private final int CREATION_LIMIT = 100;
    private HashMap<String, Item> itemShelf = new HashMap<>();


    public void printDeal(){
        int [] outputList = new int[itemShelf.size()+1];
        for(Item item : itemShelf.values()){
            if(item.getDiscount() != 0){
                outputList[Integer.parseInt(item.getItemID())] = 1;
            }
        }

        for(int i=0; i<outputList.length; i++){
            if(outputList[i] == 0){continue;}
            Item itemPrint = itemShelf.get(String.valueOf(i));
            System.out.println("ID: " +  String.format("%-3s", itemPrint.getItemID()) +
                "   Name: " + String.format("%-16s",itemPrint.getName())+
                "   Discount %: " + String.format("%-3d%%",itemPrint.getDiscount()));

        }
    }
    //no argument constructor
    // create an Item shelf object based on default path
    public ItemShelf(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                loadItems(".\\resource\\itemDB.txt");
            }else{
                loadItems("./resource/itemDB.txt");
            }
        }
        catch (IOException e){
            System.err.println("Exception Occurred: " + e.getMessage());

            //propagate the Exception to the caller
            throw new InvalidArgumentException(e.getMessage(), new Throwable());
        }
    }

    /**
     * constructor - Creates Empty Item ArrayList
     */
    public ItemShelf(String filePath){


        try {
            loadItems(filePath);
        } catch (IOException e) {
            System.err.println("Exception Occurred: " + e.getMessage());
            //propagate the Exception to the caller
            throw new InvalidArgumentException(e.getMessage(), new Throwable());

        }
    }

    /**
     * Copy Constructor - Copies another Item ArrayList to this object.
     */
    public ItemShelf (HashMap<String, Item> items) {
        if(items.isEmpty()){
            itemShelf  = new HashMap<>();
        }else{
            itemShelf.putAll(items);
        }
    }

    private void loadItems(String filePath) throws IOException{
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] values;
            boolean taxable = false;
            while ((line = reader.readLine()) != null) {

                if(itemShelf.size() >= CREATION_LIMIT){
                    System.out.println("Item creation limit exceeded");
                    break;
                }
                values = line.split(",");
                taxable = (values[6].equalsIgnoreCase("y")) ? true : false;

                Item item = new Item(values[0], values[1],
                    Double.parseDouble(values[2]), Integer.parseInt(values[3]),
                    values[4], Integer.parseInt(values[5]), taxable);

                itemShelf.put(values[0], item);

            }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }

    }

    public boolean contains(String itemID) {
        if (itemID == null) return false;
        return itemShelf.containsKey(itemID);
    }
    //Setters and Getters
    public Item getItem(String itemID) {
        if (itemID == null) return null;
        return itemShelf.get(itemID);
    }
    /**
     * Appends and Item object to the HashMap
     */
    public void addItem(Item item) {
        itemShelf.put(item.getItemID(), item);
    }

    /**
     * Removes an Item object from the ArrayList given the name
     */
    public void removeItemByName(String name) {
        Item itemToRemove = getItemName(name);
        if (itemToRemove != null) {
            itemShelf.remove(itemToRemove);
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Removes an Item object from the ArrayList given the ID
     */
    public void removeItemByID(String itemID) {

        if (itemID != null && itemShelf.containsKey(itemID)) {
            itemShelf.remove(itemID);
        } else {
            System.out.println("Item not found or NULL ID provided");
        }
    }

    /**
     * Get item by name
     */
    public Item getItemName(String ItemName) {
        for (Item item : itemShelf.values()) {
            // From Ryan
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
     */
    public Item getItemID(String ItemID) {
        if (itemShelf.containsKey(ItemID)){
            return itemShelf.get(ItemID);
        }
        return null; // Return null if item not found
    }

    public void printAllItems(){
        String [] outputArray = new String[itemShelf.size()];

        //grep all the item from HashMap
        for (int i=1; i<=outputArray.length; i++) {
            outputArray[Integer.valueOf(itemShelf.get(Integer.toString(i)).getItemID()) - 1] =
                itemShelf.get(Integer.toString(i)).toString();
        }
        //sort the array
        //Arrays.sort(outputArray);

        //output the array
        for (String output : outputArray) {
            System.out.println(output);
        }
    }

}
