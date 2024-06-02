import java.time.LocalDate;

/**
 * Class RTitle represents a title that has been restored.
 * It includes the original title and the date of restoration.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1 (date of last revision) 
 */
public class RTitle {
    private Title title;
    private LocalDate date;

    /**
     * Constructs an RTitle object with the specified title and restoration date.
     * 
     * @param t the title that has been restored
     * @param d the date of restoration
     */
    public RTitle(Title t, LocalDate d) {
        this.title = t;
        this.date = d;
    }

    /**
     * Returns the restored title.
     * 
     * @return the restored title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Returns the date of restoration.
     * 
     * @return the date of restoration
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the RTitle object.
     * The string includes the title's string representation and the restoration date.
     * 
     * @return a string representation of the RTitle object
     */
    public String toString() {
        return title.toString() + "\t" + date.toString();
    }

    /**
     * Returns a simple string representation of the RTitle object.
     * The string includes the title's simple string representation and the restoration date.
     * 
     * @return a simple string representation of the RTitle object
     */
    public String simpleToString() {
        return title.simpleToString() + "|" + date.toString();
    }
}