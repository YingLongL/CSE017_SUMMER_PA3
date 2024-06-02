import java.time.LocalDate;

/**
 * Abstract class Title to model the entity Title.
 * This class implements the Comparable, Cloneable, and Restorable interfaces.
 * It represents a general title with common attributes and methods.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1 (date of last revision) 
 */
public abstract class Title implements Comparable<Title>, Cloneable, Restorable {
    private String callN;
    private String title;
    private String publisher;
    private int year;
    private int copies;

    /**
     * Default constructor that initializes the title with default values.
     */
    protected Title() {
        this("none", "none", "none", 0, 0);
    }

    /**
     * Constructor with parameters to initialize the title with specific values.
     * 
     * @param ca the call number of the title
     * @param ti the title name
     * @param pu the publisher of the title
     * @param ye the publication year of the title
     * @param co the number of copies of the title
     */
    protected Title(String ca, String ti, String pu, int ye, int co) {
        callN = ca;
        title = ti;
        publisher = pu;
        year = ye;
        copies = co;
    }

    /**
     * Returns the call number of the title.
     * 
     * @return the call number of the title
     */
    public String getCallN() { return callN; }

    /**
     * Returns the title name.
     * 
     * @return the title name
     */
    public String getTitle() { return title; }

    /**
     * Returns the publisher of the title.
     * 
     * @return the publisher of the title
     */
    public String getPublisher() { return publisher; }

    /**
     * Returns the publication year of the title.
     * 
     * @return the publication year of the title
     */
    public int getYear() { return year; }

    /**
     * Returns the number of copies of the title.
     * 
     * @return the number of copies of the title
     */
    public int getCopies() { return copies; }

    /**
     * Sets the call number of the title.
     * 
     * @param callN the new call number of the title
     */
    public void setCallN(String callN) { this.callN = callN; }

    /**
     * Sets the title name.
     * 
     * @param title the new title name
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Sets the publisher of the title.
     * 
     * @param publisher the new publisher of the title
     */
    public void setPublisher(String publisher) { this.publisher = publisher; }

    /**
     * Sets the publication year of the title.
     * 
     * @param year the new publication year of the title
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Sets the number of copies of the title.
     * 
     * @param copies the new number of copies of the title
     */
    public void setCopies(int copies) { this.copies = copies; }

    /**
     * Returns a formatted string representation of the title.
     * 
     * @return a formatted string representation of the title
     */
    public String toString() {
        return String.format("%-12s\t%-35s\t%-25s\t%-5d\t%-7d", callN, title, publisher, year, copies);
    }

    /**
     * Returns a simple string representation of the title.
     * 
     * @return a simple string representation of the title
     */
    public String simpleToString() {
        return callN + "|" + title + "|" + publisher + "|" + year + "|" + copies;
    }

    /**
     * Compares this title with another title based on the publication year.
     * 
     * @param other the other title to compare with
     * @return a negative integer, zero, or a positive integer as this title is less than, equal to, or greater than the specified title
     */
    @Override
    public int compareTo(Title other) {
        return Integer.compare(this.year, other.year);
    }

    /**
     * Creates and returns a copy of this title.
     * 
     * @return a clone of this title
     * @throws CloneNotSupportedException if the object's class does not support the Cloneable interface
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Determines if the title is restorable based on the publication year.
     * 
     * @return true if the title is restorable, false otherwise
     */
    @Override
    public boolean isRestorable() {
        return LocalDate.now().getYear() - year >= 50;
    }
}