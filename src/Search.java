import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Search {
    public static String urlStart = "";
    public static final String AUTHOR_URL_START = "http://dblp.org/search/author/api?q=";
    public static final String VENUE_URL_START = "http://dblp.org/search/venue/api?q=";
    public static final String PUBLICATION_URL_START ="http://dblp.org/search/publ/api?q=";
    public static final String URL_END = "&h=40&c=0";
    private boolean author;
    private boolean venue;
    private boolean publication;

    // Sets the type of search, and picks the correct API to use
    public Search(String choice){
        if(choice.equals("author")){
            this.author = true;
            urlStart = AUTHOR_URL_START;
        }
        if(choice.equals("venue")){
            this.venue = true;
            urlStart = VENUE_URL_START;
        }
        if(choice.equals("publication")){
            this.publication = true;
            urlStart = PUBLICATION_URL_START;
        }
    }

    public void executeSearch(String searchTerm) {

        // Concatonate the URL
        String query = urlStart + searchTerm + URL_END;

        // Replace blanks just in case
        query = query.replace(" ", "%20");

        //Implement the query in a try-catch block
        try {
            URL urlObject = new URL(query);

            // Open the stream (which returns an InputStream)
            InputStream in = urlObject.openStream();
            // Wrap the steam in an input source for compatibility with the SAX parser
            InputSource is = new InputSource(in);
            is.setEncoding("ISO_8859_1");

            // Create the parser
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            SAXParser parser = factory.newSAXParser();

            // Parse the data
            if(this.author == true){
                parser.parse(is, new dblpAuthorHandler());
                for(int i = 0;  i < dblpAuthorHandler.getUrls().size(); i++) {
                    parser.parse(dblpAuthorHandler.getUrls().get(i).openStream(),new dblpAuthorUrlHandler());
                }
                this.author = false;
            }
            if(this.publication == true){
                parser.parse(is, new dblpPublicationHandler());
                this.publication = false;
            }
            if(this.venue == true){
                parser.parse(is, new dblpVenueHandler());
                this.publication = false;
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch(SAXException se){
            se.printStackTrace();
        } catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }
    }
}