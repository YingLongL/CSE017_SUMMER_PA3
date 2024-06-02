import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Class Catalog to model the entity Catalog.
 * It maintains an array of Title objects and an array of restored Title objects.
 * Provides methods to read from a file, save to a file, and manage Title objects.
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-1 (date of last revision)
 */
public class Catalog {
    // Data members
    private Title[] titles;
    private RTitle[] restoredTitles;
    private int count;
    private int rcount;

    // Default constructor
    /**
     * Default constructor that initializes the titles and restoredTitles arrays,
     * and sets the count and rcount to 0.
     */
    public Catalog() {
        titles = new Title[50];
        restoredTitles = new RTitle[25];
        count = 0;
        rcount = 0;
    }

    // readTitles method
    /**
     * Reads titles from a file and stores them in the titles array.
     * If a title has a restoration date, it is stored in the restoredTitles array instead.
     * 
     * @param filename the name of the file to read titles from
     */
    public void readTitles(String filename) {
        try {
            File file = new File(filename);
            Scanner readFile = new Scanner(file);

            while (readFile.hasNextLine()) {
                String s = readFile.nextLine();
                String[] t = s.split("\\|");
                if (t[0].charAt(0) == 'P') {
                    try {
                        Periodical p = new Periodical(t[0], t[1], t[2], Integer.valueOf(t[3]), Integer.valueOf(t[4]), Integer.valueOf(t[5]), Integer.valueOf(t[6]));
                        addTitle(p);
                    } catch (Exception e) {
                        Periodical p = new Periodical(t[0], t[1], t[2], Integer.valueOf(t[3]), Integer.valueOf(t[4]), changeMonth(t[5]), Integer.valueOf(t[6]));
                        addTitle(p);
                    }
                } else {
                    Book b = new Book(t[0], t[1], t[2], Integer.valueOf(t[3]), Integer.valueOf(t[4]), t[5], t[6]);
                    addTitle(b);
                }

                if (t.length > 7) {
                    LocalDate restorationDate = LocalDate.parse(t[7]);
                    RTitle rt = new RTitle(titles[count - 1], restorationDate);
                    addRestoredTitle(rt);
                    titles[count - 1] = null;
                    count--;
                }
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // Change the month into an integer type
    /**
     * Converts a month from a string to its corresponding integer value.
     * 
     * @param m the name of the month
     * @return the integer value of the month
     */
    public int changeMonth(String m) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < 12; i++) {
            if (m.equals(months[i])) {
                return i + 1;
            }
        }
        return 0;
    }

    // saveTitles method
    /**
     * Saves the titles and restored titles to a file.
     * 
     * @param filename the name of the file to save the titles to
     */
    public void saveTitles(String filename) {
        try {
            File file = new File(filename);
            PrintWriter writeFile = new PrintWriter(file);
            List<String> restoredTitlesList = new ArrayList<>();

            // 先处理恢复的标题，移除并添加到恢复列表中
            for (int i = 0; i < count; i++) {
                boolean isRestored = false;
                for (int j = 0; j < rcount; j++) {
                    if (restoredTitles[j].getTitle().equals(titles[i])) {
                        restoredTitlesList.add(restoredTitles[j].simpleToString());
                        isRestored = true;
                        break;
                    }
                }
                if (!isRestored) {
                    writeFile.println(titles[i].simpleToString());
                }
            }

            // 保存恢复的标题
            for (String restoredTitle : restoredTitlesList) {
                writeFile.println(restoredTitle);
            }

            writeFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to the " + filename);
        }
    }

    // Remove a Title object to the array
    /**
     * Removes a Title object from the array by its call number.
     * 
     * @param c the call number of the title to remove
     * @return true if the title was successfully removed, false otherwise
     */
    public boolean removeTitle(String c) {
        for (int i = 0; i < count; i++) {
            if (titles[i].getCallN().equals(c)) {
                for (int j = i; j < count - 1; j++) {
                    titles[j] = titles[j + 1];
                }
                titles[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    // Accessor for the count
    /**
     * Returns the count of titles.
     * 
     * @return the count of titles
     */
    public int getCount() {
        return count;
    }

    // Adds a Title object to the collection
    /**
     * Adds a Title object to the collection.
     * 
     * @param t the Title object to add
     * @return true if the title was successfully added, false otherwise
     */
    public boolean addTitle(Title t) {
        if (count < titles.length) {
            titles[count] = t;
            count++;
            return true;
        }
        return false;
    }

    // Adds a restored Title object to the collection
    /**
     * Adds a restored Title object to the collection.
     * 
     * @param rt the restored Title object to add
     * @return true if the restored title was successfully added, false otherwise
     */
    public boolean addRestoredTitle(RTitle rt) {
        if (rcount < restoredTitles.length) {
            restoredTitles[rcount] = rt;
            rcount++;
            return true;
        }
        return false;
    }

    // Finds and returns a Title object with a call number
    /**
     * Finds and returns a Title object with a specified call number.
     * 
     * @param c the call number to search for
     * @return the Title object with the specified call number, or null if not found
     */
    public Title findCallN(String c) {
        for (int i = 0; i < count; i++) {
            if (titles[i].getCallN().equals(c)) {
                return titles[i];
            }
        }
        return null;
    }

    // Finds and returns an array of Title objects with a specified title
    /**
     * Finds and returns an array of Title objects with a specified title.
     * 
     * @param title the title to search for
     * @return an array of Title objects with the specified title, or null if not found
     */
    public Title[] findTitle(String title) {
        Title[] t = new Title[count];
        int n = 0;
        for (int i = 0; i < count; i++) {
            if (titles[i].getTitle().equals(title)) {
                t[n] = titles[i];
                n++;
            }
        }
        if (n == 0) {
            return null;
        }
        Title[] T = new Title[n];
        for (int i = 0; i < n; i++) {
            T[i] = t[i];
        }
        return T;
    }

    // Finds and returns an array of Title objects with a specified year
    /**
     * Finds and returns an array of Title objects with a specified year.
     * 
     * @param year the year to search for
     * @return an array of Title objects with the specified year, or null if not found
     */
    public Title[] findYear(int year) {
        Title[] t = new Title[count];
        int n = 0;
        for (int i = 0; i < count; i++) {
            if (titles[i].getYear() == year) {
                t[n] = titles[i];
                n++;
            }
        }
        if (n == 0) {
            return null;
        }
        Title[] T = new Title[n];
        for (int i = 0; i < n; i++) {
            T[i] = t[i];
        }
        return T;
    }

    // Sorts the array of Title objects in ascending order based on the year of publication using insertion sort
    /**
     * Sorts the array of Title objects in ascending order based on the year of publication.
     */
    public void sort() {
        Arrays.sort(titles, 0, count);
    }

    // Finds a restored title
    /**
     * Checks if a title is in the restoredTitles array.
     * 
     * @param title the Title object to check
     * @return true if the title is in the restoredTitles array, false otherwise
     */
    public boolean findRestored(Title title) {
        for (int i = 0; i < rcount; i++) {
            if (restoredTitles[i].getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    // View restorable titles
    /**
     * Displays the restorable titles.
     */
    public void viewRestorable() {
        int restorableCount = 0;
        for (int i = 0; i < count; i++) {
            if (titles[i].isRestorable() && !findRestored(titles[i])) {
                System.out.println(titles[i]);
                restorableCount++;
            }
        }
        if (restorableCount == 0) {
            System.out.println("No restorable titles found");
        } else {
            System.out.println(restorableCount + " title(s) due for restoration");
        }
    }

    // View restored titles
    /**
     * Displays the restored titles.
     */
    public void viewRestored() {
        if (rcount == 0) {
            System.out.println("There are no restored titles");
        } else {
            for (int i = 0; i < rcount; i++) {
                System.out.println(restoredTitles[i]);
            }
            System.out.println(rcount + " title(s) restored");
        }
    }

    // Restore titles
    /**
     * Restores titles that are due for restoration and adds them to the restoredTitles array.
     */
    public void restore() {
        int restoredCount = 0;
        for (int i = 0; i < count; i++) {
            if (titles[i].isRestorable() && !findRestored(titles[i])) {
                try {
                    RTitle rt = new RTitle((Title) titles[i].clone(), LocalDate.now());
                    addRestoredTitle(rt);
                    System.out.println(titles[i]);
                    restoredCount++;
                } catch (CloneNotSupportedException e) {
                    System.out.println("Error cloning title: " + titles[i].getTitle());
                }
            }
        }
        if (restoredCount == 0) {
            System.out.println("There are no titles due for restoration");
        } else {
            System.out.println(restoredCount + " title(s) restored");
        }
    }

    // toString method
    /**
     * Returns a string representation of the Catalog object.
     * 
     * @return a string representation of the Catalog object
     */
    public String toString() {
        String str = String.format("%-12s\t%-35s\t%-25s\t%-5s\t%-7s\t%-15s\t%-10s", "Call Number", "Title", "Publisher", "Year", "#Copies", "Author/Month", "ISBN/Issue");
        for (int i = 0; i < count; i++) {
            str += "\n" + titles[i].toString();
        }
        return str;
    }
}