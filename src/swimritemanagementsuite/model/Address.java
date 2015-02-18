package swimritemanagementsuite.model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Address database table that will hold all the address
 * information for each student.
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "Address")
public class Address {
    
    /**
     * Primary Key: Auto generated Address ID number.
     */
    @DatabaseField(columnName = "addressId", generatedId = true)
    private int addressId;
    
    @DatabaseField (columnName = "addressLine1", canBeNull = false)
    private String addressLine1;
    
    @DatabaseField (columnName = "addressLine2", canBeNull = false)
    private String addressLine2;
    
    @DatabaseField (columnName = "city", canBeNull = false)
    private String city;
    
    @DatabaseField (columnName = "county", canBeNull = false)
    private String county;
    
    @DatabaseField (columnName = "postcode", canBeNull = false)
    private String postcode;
    
    public Address() {
        
    }
    
    public Address(String addressLine1, String addressLine2, String city, String county, String postcode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }
    
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCounty() {
        return county;
    }
    
    public void setCounty() {
        this.county = county;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
