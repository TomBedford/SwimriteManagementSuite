package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class AddressDAC {
    
    /**
     * The Data Access Object used to access the Teacher table in the mySQL database.
     */
    private Dao<Address, Integer> addressDAO;
    
    /**
     * Teacher object used for creating, updating and deleting teachers from the database.
     */
    Address address;
    
    public AddressDAC() {
        
    }
    
    public AddressDAC(ConnectionSource connectionSource){
        try {
            addressDAO = DaoManager.createDao(connectionSource, Address.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Address database access objects");
        }
    }
    
    public void createAddress(String addressLine1, String addressLine2, String city, String county, String postcode) throws Exception {
        // Create an instance of an address.
        address = new Address(addressLine1, addressLine2, city, county, postcode);

        // Persist the Address object to the database.
        addressDAO.create(address);
    }

    public void updateAddress(int addressId, String addressLine1, String addressLine2, String city, String county, String postcode) throws Exception {
        // Retrieves the desired Address from the database.
        address = addressDAO.queryForId(addressId);

        // If the Address object isnt null then the new updated Address values are set and updated.
        if (address != null) {
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setCity(city);
            address.setCounty();
            address.setPostcode(postcode);
            addressDAO.update(address);
        }
    }

    public void deleteAddress(int addressId) throws Exception {
        // Retrieves the desired Teacher from the database.
        address = addressDAO.queryForId(addressId);

        // Deletes the desired Teacher from the Teacher table in the database.
        addressDAO.delete(address);
    }
    
}
