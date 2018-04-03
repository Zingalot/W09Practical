import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class dblpVenueHandler extends DefaultHandler
{

    boolean venue = false;
    private static ArrayList<String> venues = new ArrayList();


    // Make sure that the code in DefaultHandler's
    // constructor is called:
    public dblpVenueHandler()
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
        if(qName.compareTo("venue") == 0)
        {
            venue = true;
        }

    }
    public void characters(char ch[], int start, int length){

        if (venue) {
            //System.out.println("Author: " + new String(ch, start, length));
            venues.add(new String(ch, start, length));
            venue = false;
        }
    }

    public static ArrayList<String> getVenues() {
        return venues;
    }
}