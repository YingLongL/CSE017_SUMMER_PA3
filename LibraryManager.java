/**
 * Class LibraryManager to manage the operations on a Catalog of titles.
 * This class includes methods for reading, finding, removing, and restoring titles.
 * @author  Yinglong Lin    
 * @version Java 11 / VSCode
 * @since   2024-6-1 (date of last revision) 
 */
public class LibraryManager {

    /**
     * Main method to execute test cases on a Catalog of titles.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Create an instance of the class Catalog
        Catalog myLibrary = new Catalog();
        System.out.println("\nTest case 1: create a new catalog");
        System.out.println("A new catalog was created with " + myLibrary.getCount() + " titles");

        // Reading the titles from the file
        System.out.println("\nTest case 2: read titles from the file");
        myLibrary.readTitles("titles.txt");
        System.out.println(myLibrary.getCount() + " titles read into myLibrary");
        System.out.println();

        // view the list of titles
        System.out.println("\nTest case 3: view all titles");
        System.out.println(myLibrary.toString());

        // find a title by call number (successful search)
        System.out.println("\nTest case 4: find title by call number (successful)");
        testFindCallN(myLibrary, "B-111-111-111");

        // find a title by call number (failed search)
        System.out.println("\nTest case 5: find title by call number (fail)");
        testFindCallN(myLibrary, "P-333-333-333");

        // find a title by call number (invalid call number)
        System.out.println("\nTest case 6: find title by call number (invalid call number)");
        testFindCallN(myLibrary, "B-333-333");

        System.out.println("\nTest case 7: find titles by title (one title found)");
        // find titles with the same title (search returns one title)
        testFindTitle(myLibrary, "Computer Systems");

        // find titles with the same title (search returns more than one title)
        System.out.println("\nTest case 8: find titles by title (more than one title found)");
        testFindTitle(myLibrary, "Introduction to C language");

        // find titles with the same title (search returns no title)
        System.out.println("\nTest case 9: find titles by title (no title found)");
        testFindTitle(myLibrary, "Introduction to Python");

        System.out.println("\nTest case 10: find titles by year (one title found)");
        // find titles with the same publication year (search returns one title)
        testFindYear(myLibrary, 2021);

        System.out.println("\nTest case 11: find titles by year (more than one title found)");
        // find titles with the same publication year (search returns more than one title)
        testFindYear(myLibrary, 2020);

        // find titles with the same publication year (search returns no title)
        System.out.println("\nTest case 12: find titles by year (no title found)");
        testFindYear(myLibrary, 2017);

        System.out.println("\nTest case 13: sort the titles by year");
        // sort the titles by year
        myLibrary.sort();
        System.out.println(myLibrary.toString());

        System.out.println("\nTest case 14: remove a title (successful)");
        testRemoveCallN(myLibrary, "B-333-333-333");

        System.out.println("\nTest case 15: remove a title (failed)");
        testRemoveCallN(myLibrary, "B-666-666-666");

        System.out.println("\nTest case 16: remove a title (invalid call number)");
        testRemoveCallN(myLibrary, "P-123");

        System.out.println("\nTest case 17: save titles to the text file");
        myLibrary.saveTitles("titles.txt");
        System.out.println(myLibrary.getCount() + " titles saved to \"titles.txt\"");
        myLibrary = new Catalog();
        myLibrary.readTitles("titles.txt");
        System.out.println(myLibrary.getCount() + " titles read from \"titles.txt\"");

        System.out.println("\nTest case 18: view the restorable titles (one title)");
        myLibrary.viewRestorable();

        System.out.println("\nTest case 19: view the restored titles (no titles)");
        myLibrary.viewRestored();

        System.out.println("\nTest case 20: restore the restorable titles (one title)");
        myLibrary.restore();

        System.out.println("\nTest case 21: save the titles and their restoration date, if any, to the text file");
        myLibrary.saveTitles("titles.txt");
        System.out.println(myLibrary.getCount() + " titles written to the file");

        System.out.println("\nTest case 22: read the titles and their restoration date, if any, from the text file");
        myLibrary = new Catalog();
        myLibrary.readTitles("titles.txt");
        System.out.println(myLibrary.getCount() + " titles read from the file");

        System.out.println("\nTest case 23: view the restorable titles (no titles)");
        myLibrary.viewRestorable();

        System.out.println("\nTest case 24: view the restored titles (one title)");
        myLibrary.viewRestored();

        System.out.println("\nTest case 25: restore the restorable titles (no titles)");
        myLibrary.restore();
    }

    /**
     * Tests finding a title by call number and prints the result.
     * 
     * @param catalog the catalog to search in
     * @param calln the call number to search for
     */
    public static void testFindCallN(Catalog catalog, String calln) {
        if (checkCallNumber(calln)) {
            Title title = catalog.findCallN(calln);
            if (title == null) {
                System.out.println("No title found with call number " + calln);
            } else {
                System.out.println("Title found with call number " + calln + ":");
                System.out.println(title);
            }
        }
    }

    /**
     * Tests removing a title by call number and prints the result.
     * 
     * @param catalog the catalog to remove from
     * @param calln the call number to remove
     */
    public static void testRemoveCallN(Catalog catalog, String calln) {
        if (checkCallNumber(calln)) {
            if (catalog.removeTitle(calln)) {
                System.out.println("Title with call number " + calln + " found and removed successfully.");
                System.out.println(catalog.toString());
            } else {
                System.out.println("Title with call number " + calln + " not found.");
            }
        }
    }

    /**
     * Tests finding titles by title and prints the result.
     * 
     * @param catalog the catalog to search in
     * @param title the title to search for
     */
    public static void testFindTitle(Catalog catalog, String title) {
        Title[] titles = catalog.findTitle(title);
        if (titles == null) {
            System.out.println("No titles found with the title: " + title);
        } else {
            if (titles.length > 1) {
                System.out.println(titles.length + " titles found with the title: " + title);
                for (Title t : titles) {
                    System.out.println(t);
                }
            } else {
                System.out.println("One title found with the title: " + title);
                System.out.println(titles[0]);
            }
        }
        System.out.println();
    }

    /**
     * Tests finding titles by year and prints the result.
     * 
     * @param catalog the catalog to search in
     * @param year the year to search for
     */
    public static void testFindYear(Catalog catalog, int year) {
        Title[] titles = catalog.findYear(year);
        if (titles == null) {
            System.out.println("No titles found for the year " + year);
        } else {
            if (titles.length > 1) {
                System.out.println(titles.length + " titles found for the year " + year + ":");
                for (Title t : titles) {
                    System.out.println(t);
                }
            } else {
                System.out.println("One title found for the year " + year + ":");
                System.out.println(titles[0]);
            }
        }
        System.out.println();
    }

    /**
     * Checks if a call number is valid and prints an error message if invalid.
     * 
     * @param calln the call number to check
     * @return true if the call number is valid, false otherwise
     */
    public static boolean checkCallNumber(String calln) {
        try {
            if (calln.matches("[B|P]-\\d{3}-\\d{3}-\\d{3}")) {
                return true;
            } else {
                throw new InvalidCallNumber("Invalid Call number: " + calln + ". Must be B|P-ddd-ddd-ddd");
            }
        } catch (InvalidCallNumber e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}