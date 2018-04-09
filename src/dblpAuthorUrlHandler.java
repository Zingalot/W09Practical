import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;


// Handler for the second API search needed for author search only
public class dblpAuthorUrlHandler extends DefaultHandler
{

    private static ArrayList<Integer> publicationsByAuthor = new ArrayList();
    private static ArrayList<Integer> coAuthors = new ArrayList();
    private boolean coAuthorsFound = false;


    // Call super constructor
    public dblpAuthorUrlHandler()
    {
        super();
    }

    // Similar to other handlers
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
            coAuthorsFound = true;
        }

    }

    public void endElement(String uri, String name, String qName){
        if(qName.compareTo("dblpperson") == 0 && !coAuthorsFound){
            coAuthors.add(0);
        }
    }

    public static ArrayList<Integer> getPublicationsByAuthor() {
        return publicationsByAuthor;
    }

    public static ArrayList<Integer> getCoAuthors() {
        return coAuthors;
    }
}