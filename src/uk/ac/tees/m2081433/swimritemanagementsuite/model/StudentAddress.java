/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Address database table that will hold all the address
 * information for each student.
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "StudentAddress")
public class StudentAddress {
    
    /**
     * The column name for the Address ID.
     */
    public static final String ADDRESSID_COLUMN_NAME = "addressId";
    
    /**
     * The column name for the 1st line of the Address.
     */
    public static final String ADDRESSLINE1_COLUMN_NAME = "addressLine1";
    
    /**
     * The column name for the 2nd line of the Address.
     */
    public static final String ADDRESSLINE2_COLUMN_NAME = "addressLine2";
    
    /**
     * The column name for the City of the Address.
     */
    public static final String CITY_COLUMN_NAME = "city";
    
    /**
     * The column name for the county of the Address.
     */
    public static final String COUNTY_COLUMN_NAME = "county";
    
    /**
     * The column name for the postcode of the Address.
     */
    public static final String POSTCODE_COLUMN_NAME = "postcode";
    
    
    
    /**
     * Primary Key: Auto generated Address ID number.
     */
    @DatabaseField(columnName = ADDRESSID_COLUMN_NAME, generatedId = true)
    private int addressId;
    
    /**
     * The column name for the 1st line of the address.
     */
    @DatabaseField (columnName = ADDRESSLINE1_COLUMN_NAME, canBeNull = false)
    private String addressLine1;
    
    /**
     * The column name for the 2nd line of the address.
     */
    @DatabaseField (columnName = ADDRESSLINE2_COLUMN_NAME, canBeNull = false)
    private String addressLine2;
    
    /**
     * The column name for the city of the address.
     */
    @DatabaseField (columnName = CITY_COLUMN_NAME, canBeNull = false)
    private String city;
    
    /**
     * The column name for the county of the address.
     */
    @DatabaseField (columnName = COUNTY_COLUMN_NAME, canBeNull = false)
    private String county;
    
    /**
     * The column name for the postcode of the address.
     */
    @DatabaseField (columnName = POSTCODE_COLUMN_NAME, canBeNull = false)
    private String postcode;
    
    
    
    /**
     * Default constructor of the Student Record class.
     */
    public StudentAddress() {
        // ORMLite needs a no-arg constructor
    }
    
    /**
     * Parameterized constructor to create a new Student Address in the Student Address database table.
     * @param addressLine1 The first line of the address
     * @param addressLine2 The second line of the address
     * @param city The city of the address
     * @param county The county of the address
     * @param postcode The postcode of the address
     */
    public StudentAddress(String addressLine1, String addressLine2, String city, String county, String postcode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }
    
    
    
    /**
     * Accessor to retrieve the auto generated Address Id of the Student Address Record.
     * @return addressId The auto generated Address Id.
     */
    public int getAddressId() {
        return addressId;
    }
    
    /**
     * Accessor to retrieve the first line of the Address Record.
     * @return addressLine1 The first line of the address
     */
    public String getAddressLine1() {
        return addressLine1;
    }
    
    /**
     * Mutator to set the new first line of the Address Record.
     * @param addressLine1 The updated first line of the address
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    /**
     * Accessor to retrieve the second line of the Address Record.
     * @return addressLine2 The second line of the address
     */
    public String getAddressLine2() {
        return addressLine2;
    }
    
    /**
     * Mutator to set the new second line of the Address Record.
     * @param addressLine2 The updated second line of the address
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    /**
     * Accessor to retrieve the city of the Address Record.
     * @return city The city of the address
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Mutator to set the new city of the Address Record.
     * @param city The updated city of the address
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Accessor to retrieve the county of the Address Record.
     * @return county The county of the address
     */
    public String getCounty() {
        return county;
    }
    
    /**
     * Mutator to set the new county of the Address Record.
     * @param county The updated city of the address
     */
    public void setCounty(String county) {
        this.county = county;
    }
    
    /**
     * Accessor to retrieve the postcode of the Address Record.
     * @return postcode The postcode of the address
     */
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * Mutator to set the new postcode of the Address Record.
     * @param postcode The updated postcode of the address
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
