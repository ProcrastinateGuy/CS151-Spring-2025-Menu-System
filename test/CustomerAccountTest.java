import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;


import static org.junit.Assert.assertNotNull;

public class CustomerAccountTest {

    @Test
    public void testCustomerAccountNames() {
        String nameTestFilePath = ".\\testResource\\names.txt";
        List<String> Names = new ArrayList<String>();
        List<CustomerAccount> accountListName = new ArrayList<CustomerAccount>();

        //names
        //try with resource
        try(BufferedReader br = new BufferedReader(new FileReader(nameTestFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                Names.add(line);
            }
        }catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }

        Iterator<String> nameIterator = Names.iterator();

        CustomerAccount a;
        while (nameIterator.hasNext()) {
            a = new CustomerAccount(nameIterator.next(), "4443332221");
            accountListName.add(a);
        }
        Iterator<CustomerAccount> accountIterator2 = accountListName.iterator();
        Iterator<CustomerAccount> accountIterator3 = accountListName.iterator();
        while (accountIterator2.hasNext()) {
            System.out.println(accountIterator2.next().getCustomerName()
                + ", " + accountIterator3.next().getPhone());
        }

        Iterator<CustomerAccount> accountIterator = accountListName.iterator();
        while (accountIterator.hasNext()) {
            assertNotNull("Object should not be null", accountIterator.next());
        }

    }
    @Test
    public void testCustomerAccountPhones() {
        String phoneTestFilePath = ".\\testResource\\phones.txt";
        List<String> Phones = new ArrayList<String>();
        List<CustomerAccount> accountListPhone = new ArrayList<CustomerAccount>();

        //phones
        // try with resource
        try(BufferedReader br = new BufferedReader(new FileReader(phoneTestFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                Phones.add(line);
            }
        }catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }

        Iterator<String> phoneIterator = Phones.iterator();

        CustomerAccount b;
        while (phoneIterator.hasNext()) {
            b = new CustomerAccount("acceptable name", phoneIterator.next());
            accountListPhone.add(b);
        }
        Iterator<CustomerAccount> accountIterator4 = accountListPhone.iterator();
        Iterator<CustomerAccount> accountIterator5 = accountListPhone.iterator();
        while (accountIterator4.hasNext()) {
            System.out.println(accountIterator4.next().getCustomerName()
                + ", " + accountIterator5.next().getPhone());
        }

        Iterator<CustomerAccount> accountIterator6 = accountListPhone.iterator();
        while (accountIterator6.hasNext()) {
            assertNotNull("Object should not be null", accountIterator6.next());
        }

    }

    @Test
    public void testCustomerAccountEmails() {
        String emailTestFilePath = ".\\testResource\\emails.txt";
        CustomerAccount a = new CustomerAccount();

        List<String> Emails = new ArrayList<String>();
        // try with resource
        try(BufferedReader br = new BufferedReader(new FileReader(emailTestFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                Emails.add(line);
            }
        }catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }

        Iterator<String> emailIterator = Emails.iterator();
        while(emailIterator.hasNext()) {
            a.setEmail(emailIterator.next());
            System.out.println(a.getEmail());
        }



    }
}
