/**
 * This class represents the Periodical , extending the Title class.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1(date of last revision) 
 */
public class Periodical extends Title{
    // Data members
    private int month;
    private int issue;
    /**
     * Default constructor
     */
    public Periodical(){
        super();
        month = 0;
        issue = 0;
    }
    /**
     * Constructor with seven parameters
     * @param ca initial value for thecategory
     * @param ti initial value for the title
     * @param pu initial value for the publisher
     * @param co initial value for the cost
     * @param ye initial value for the year
     * @param mo initial value for the month 
     * @param is initial value for the issue
     */
    public Periodical(String ca, String ti, String pu, int ye, int co, int mo, int is){
        super(ca,ti,pu,ye,co);
        month = mo;
        issue = is;
    }
    /**
     * Accessor for the month
     * @return value of the month
     */
    public int getMonth(){ return month;}
    /**
     * Accessor for the issue
     * @return value of the issue
     */
    public int getIssue(){ return issue;}
    /**
     * Override of the toString method with Periodical-specific information
     * @return formatted string with the object attributes
     */
    public String toString(){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String str = super.toString() + String.format("\t%-15s\t%-10d", months[month], issue);
        return str;
    }
    /**
     * Accessor for the Title attributes
     * @return simply formatted string with the object attributes
     */
    public String simpleToString(){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return super.simpleToString() + "|" + months[month] + "|" + issue;
    }
    /**
     * Mutator for the month
     * @param month value of the month
     */
    public void setMonth(int month){
        this.month = month;
    }
    /**
     * Mutator for the issue
     * @param issue value of the issue
     */
    public void setIssue(int issue){
        this.issue = issue;
    }



}