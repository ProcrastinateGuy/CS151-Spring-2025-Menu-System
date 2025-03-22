//manager interface
public interface ManagerInterface <T> {
    // this interface defines what a "xyz-manager" class needs to implement
    // CustomerAccount class is considered one of the class in this category

    // ID generation method for the object it's managing
    // allows multiple Strings as parameter
    String generateID();
    //String getID( T itemToCheck);

    // this method has a generic return type
    T getMember(String memberID);


}
