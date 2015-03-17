package uk.ac.tees.m2081433.swimritemanagementsuite.model;

/**
 * All database controllers will implements this generic interface that has methods to create, update and delete
 * objects from the appropriate objects database table.
 * @param <T> The type of object this controller uses.
 */
public interface DatabaseTableController<T> {
    
    /**
     * Creates the object within the appropriate database table.
     * @param object - Object to create/add to the database table.
     */
    public void create(T object);

    /**
     * Updates the object within the appropriate database table.
     * @param object - Object to update in the database table.
     */
    public void update(T object);
    
    /**
     * Deletes the object within the appropriate database table.
     * @param object - Object to delete from the database table.
     */
    public void delete(T object);
}
