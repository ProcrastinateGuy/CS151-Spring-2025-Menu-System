import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertAll;


public class ItemTest {

    @Test
    public void itemListCreationTest() throws IOException {
        ItemShelf itemShelf = new ItemShelf(".\\src\\itemDB.txt");
        Item burger = new Item("1","Burger",5.99,2,"FastFood",10);
//        assertAll(() ->{
                assertEquals("Deli_Burger", itemShelf.getItemName("Deli_Burger").getName());

  //          }
  //      );

        /*
        * 1,Burger,5.99,2,FastFood,10
2,Pizza,8.99,1,FastFood,15
3,Pasta,7.49,5,Italian,5
4,Salad,4.99,3,Healthy,0
5,Coffee,2.99,10,Beverage,10
6,IceCream,3.99,4,Dessert,20
7,Sandwich,6.49,2,FastFood,8
8,Juice,3.49,6,Beverage,12
9,Sushi,12.99,1,Japanese,0
10,Steak,15.99,2,FineDining,5*/
    }

}
