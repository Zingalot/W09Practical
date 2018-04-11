import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class dblpPublicationHandler extends DefaultHandler {

    private boolean publication = false;
    private int numberOfAuthors = 0;
    private static ArrayList<String> publications = new ArrayList<String>();
    private static ArrayList<Integer> authorNumbers = new ArrayList<Integer>();
    private StringBuilder chars = new StringBuilder();

    // Call super constructor
    public dblpPublicationHandler() {
        super();
    }

    public boolean isPublication() {
        return publication;
    }

    public int getNumberOfAuthors() {
        return numberOfAuthors;
    }

    public StringBuilder getChars() {
        return chars;
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
        // Only an increment needed here, other info not relevant
        if (qName.compareTo("author") == 0) {
            numberOfAuthors++;
        }

        // Sets a boolean to true if the parser is 'in' a tag
        if (qName.compareTo("title") == 0) {
            publication = true;
        }

    }

    public void endElement(String uri, String name, String qName) {
        if (qName.compareTo("title") == 0) {
            publications.add(chars.toString());
            authorNumbers.add(numberOfAuthors);
            publication = false;
            chars.setLength(0);
            numberOfAuthors = 0;

        }
    }

    public void characters(char ch[], int start, int length) {

        if (publication) {
            // Adds the publication and the number of authors to ArrayLists
            chars.append(ch, start, length);

        }
    }

    public static ArrayList<String> getPublications() {
        return publications;
    }

    public static ArrayList<Integer> getAuthorNumbers() {
        return authorNumbers;
    }
}
