// deal manager

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.management.openmbean.KeyAlreadyExistsException;

/*
 The DealManager is responsible for creating the deals, assign dealID to deals,
 getting and setting the attributes of a deal object,
 keep track of all the deals associated with it
 */
public class DealManager implements ManagerInterface<Deal> {

    private final int CREATION_LIMIT = 100;
    HashMap<String, Deal> dealsMap = new HashMap<>();
    // Key: the dealID, Value: the Deal object
    // we used HashMap to store deals because we don't care about ordering the Deals

    // interface methods
    @Override
    public String generateID() {
        UUID uniqueID = UUID.randomUUID();
        return "deal".concat(uniqueID.toString());
    }

    @Override
    public Deal getMember(String dealCode) {

        // check if the key is in the map
        if (!dealsMap.containsKey(dealCode)) {
            System.out.println("Member not found");
            return null;
        }
        return dealsMap.get(dealCode);
    }

    // generating a deal with no customID provided
    public void generateDeal(float dealModifier, String startDate, String endDate) throws InvalidArgumentException, IllegalArgumentException, KeyAlreadyExistsException {
        if (dealsMap.size() >= CREATION_LIMIT) {
            System.out.println("Deal creation limit exceeded");
            return;
        }
        String ID = generateID();
        exceptionHandling(() -> {
            Deal deal = new Deal(dealModifier, startDate, endDate, ID);
            dealsMap.put(ID, deal);
        });

    }

    // overloaded, generating a deal with custom ID provided
    public void generateDeal(float dealModifier, String startDate, String endDate, String customID) throws InvalidArgumentException, KeyAlreadyExistsException {
        exceptionHandling(() -> {
            Deal deal = new Deal(dealModifier, startDate, endDate, customID);
            dealsMap.put(customID, deal);
        });

    }

    // this method handles exceptions when creating a deal
    private void exceptionHandling(Runnable oprn) {
        try {
            oprn.run();

        } catch (KeyAlreadyExistsException e) {
            System.out.println("Duplicate deal code detected, failed to create deal");
            System.err.println(e.getMessage());
        } catch (InvalidArgumentException e) {
            System.out.println("One or more date String passed in is not formatted correctly");
            System.out.println("Date String Format: MM-dd-yyyy H:m");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception occurred");
            e.printStackTrace();
        }
    }

    public void printDeals() {
        if (dealsMap.isEmpty()) {
            System.out.println("No deals currently exist.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy H:m");
        System.out.println("----- Current Deals -----");
        for (Map.Entry<String, Deal> entry : dealsMap.entrySet()) {
            Deal deal = entry.getValue();
            System.out.println("Deal Code: " + deal.getDealCode());
            System.out.println("Modifier: " + deal.getDealModifier());
            // Format the dates nicely if they are not null
            if (deal.getStartDate() != null) {
                System.out.println("Start Date: " + deal.getStartDate().format(formatter));
            } else {
                System.out.println("Start Date: N/A");
            }
            if (deal.getEndDate() != null) {
                System.out.println("End Date: " + deal.getEndDate().format(formatter));
            } else {
                System.out.println("End Date: N/A");
            }
            System.out.println();
        }
    }
}
