import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class dblpAuthorUrlHandler extends DefaultHandler
{

    private static ArrayList<Integer> publicationsByAuthor = new ArrayList();
    private static ArrayList<Integer> coAuthors = new ArrayList();


    // Make sure that the code in DefaultHandler's
    // constructor is called:
    public dblpAuthorUrlHandler()
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
        if(qName.compareTo("dblpperson") == 0)
        {
            publicationsByAuthor.add(Integer.parseInt((atts.getValue(1))));
        }
        if(qName.compareTo("coauthors") == 0)
        {
            coAuthors.add(Integer.parseInt((atts.getValue(0))));
        }

    }

    public static ArrayList<Integer> getPublicationsByAuthor() {
        return publicationsByAuthor;
    }

    public static ArrayList<Integer> getCoAuthors() {
        return coAuthors;
    }
}