/**
 * Interface Restorable to define the contract for restorable titles.
 * Classes that implement this interface should provide an implementation 
 * for determining if a title is restorable.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1 (date of last revision) 
 */
public interface Restorable {
    /**
     * Determines if the title is restorable.
     * 
     * @return true if the title is restorable, false otherwise.
     */
    boolean isRestorable();
}