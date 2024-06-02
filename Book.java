/**
 * This class represents the Book, extending the Title class.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1 (date of last revision) 
 */

 public class Book extends Title{
    // Data members
    private String author;
    private String ISBN;
    /**
     * Default constructor
     */
    public Book(){
        super();
        author = "none";
        ISBN = "none";
    }
    /**
     * Constructor with seven parameters
     * @param ca initial value for thecategory
     * @param ti initial value for the title
     * @param pu initial value for the publisher
     * @param co initial value for the cost
     * @param ye initial value for the year
     * @param au initial value for the author 
     * @param is initial value for the ISBN 
     */
    public Book(String ca, String ti, String pu, int ye, int co, String au, String is){
        super(ca,ti,pu,ye,co);
        author = au;
        ISBN = is;
    }
    /**
     * Accessor for the author
     * @return value of the author
     */
    public String getAuthor(){ return author;}
    /**
     * Accessor for the ISBN
     * @return value of the author the ISBN
     */
    public String getISBN(){ return ISBN;}
    /**
     * Override of the toString method with book-specific information
     * @return formatted string with the object attributes
     */
    public String toString(){
        String str = super.toString() + String.format("\t%-15s\t%-10s", author, ISBN);
        return str;
    }

    /**
     * Accessor for the Title attributes
     * @return simply formatted string with the object attributes
     */
    public String simpleToString(){
        return super.simpleToString() + "|" + author + "|" + ISBN;
    }
    /**
     * Mutator for the author
     * @param author value of the author
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     * Mutator for the ISBN
     * @param ISBN value of the ISBN
     */
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    

}