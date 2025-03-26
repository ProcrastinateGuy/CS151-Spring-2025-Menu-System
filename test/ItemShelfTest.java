import org.junit.Test;
import static org.junit.Assert.*;

public class ItemShelfTest {

    private String filePath = "./src/itemDB.txt";
    @Test
    public void testLoadItems() {
        ItemShelf shelf = new ItemShelf(filePath);
        assertTrue(shelf.contains("1"));
        assertTrue(shelf.contains("3"));
    }
    @Test
    public void testGetItemWithID() {
        ItemShelf shelf = new ItemShelf(filePath);
        Item item = shelf.getItem("1");
        assertEquals("Deli_Burger", item.getName());
        assertEquals(2, item.getQuantity());
    }
    @Test
    public void testGetItemWithName() {
        ItemShelf shelf = new ItemShelf(filePath);
        Item item = shelf.getItemName("Deli_Burger");
        assertEquals("1", item.getItemID());
    }
    @Test
    public void testAdd() {
        ItemShelf shelf = new ItemShelf(filePath);
        Item newItem = new Item("11", "fakeitem", 10.99, 5, "Test", 5, false);
        shelf.addItem(newItem);
        assertEquals("fakeitem", shelf.getItem("11").getName());
    }
    @Test
    public void testRemoveItemWithID() {
        ItemShelf shelf = new ItemShelf(filePath);
        Item willRemove = new Item("31", "fakeitem", 12.99, 3, "Test", 1, false);
        shelf.addItem(willRemove);
        assertTrue(shelf.contains("31"));
        shelf.removeItemByID("31");
    }
    @Test
    public void testRemoveItemWithName() {
        ItemShelf shelf = new ItemShelf(filePath);
        Item tempor = new Item("12", "WillDelete", 7.99, 4, "Temp", 0, false);
        shelf.addItem(tempor);
        shelf.removeItemByName("WillDelete");
    }

}
