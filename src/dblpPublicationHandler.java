import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class dblpPublicationHandler extends DefaultHandler
{

    boolean publication = false;
    int numberOfAuthors = 0;
    private static ArrayList<String> publications = new ArrayList();
    private static ArrayList<Integer> authorNumbers = new ArrayList();


    // Make sure that the code in DefaultHandler's
    // constructor is called:
    public dblpPublicationHandler()
    {
        super();
    }


    /*** Below are the three methods that we are extending ***/

    /*@Override
    public void startDocument()
    {
        System.out.println("Start document");
    }


    @Override
    public void endDocument()
    {
        System.out.println("End document");
    }
    */


    // This is where all the work is happening:
    @Override
    public void startElement(String uri, String name, String qName, Attributes atts)
    {
        if(qName.compareTo("author") == 0) {
            numberOfAuthors++;
        }

        if (qName.compareTo("title") == 0) {
            publication = true;
        }



    }
    public void characters(char ch[], int start, int length){

        if (publication) {
            //System.out.println("Author: " + new String(ch, start, length));
            publications.add(new String(ch, start, length));
            authorNumbers.add(numberOfAuthors);
            publication = false;
            numberOfAuthors = 0;
        }
    }

    public static ArrayList<String> getPublications() {
        return publications;
    }

    public static ArrayList<Integer> getAuthorNumbers() {
        return authorNumbers;
    }
}