import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Search {
    public static final String ENCODING = "UTF-8";
    public static final String AUTHOR_URL_START = "http://dblp.org/search/author/api?format=xml";
    public static final String VENUE_URL_START = "http://dblp.org/search/venue/api?format=xml";
    public static final String PUBLICATION_URL_START = "http://dblp.org/search/publ/api?format=xml";
    public static final String URL_END = "&c=0&h=40&q=";
    private static String urlStart = "";
    private boolean author;
    private boolean venue;
    private boolean publication;
    private boolean newFile = true;
    private InputStream in;

    // Sets the type of search, and picks the correct API to use
    public Search(String choice) {
        if (choice.equals("author")) {
            this.author = true;
            urlStart = AUTHOR_URL_START;
        }
        if (choice.equals("venue")) {
            this.venue = true;
            urlStart = VENUE_URL_START;
        }
        if (choice.equals("publication")) {
            this.publication = true;
            urlStart = PUBLICATION_URL_START;
        }
    }

    public void executeSearch(String searchTerm) {

        // Concatonate the URL
        String query = urlStart + URL_END + searchTerm;

        // Replace blanks with supported character
        query = query.replace(" ", "+");



        //Implement the query in a try-catch block
        try {
            //Create the cache directory and download the xml file

            //WINDOWS VERSION
            /*File directory = new File("src\\\\" + String.valueOf(W09Practical.cachePath));
            directory.mkdir();
            URL urlObject = new URL(query);
            String path = "src\\\\" + W09Practical.cachePath + "\\\\" + URLEncoder.encode(query,ENCODING);*/

            //LINUX VERSION
            File directory = new File(W09Practical.getCachePath());
            URL urlObject = new URL(query);
            String path = W09Practical.getCachePath() + "/" + URLEncoder.encode(query, ENCODING);


            File cacheFile = new File(path);
            File[] cacheDirectory = directory.listFiles();
            if (cacheDirectory != null) {
                for (File child : cacheDirectory) {
                    if (child.getName().equals(URLEncoder.encode(query, ENCODING))) {
                        newFile = false;
                    }
                }
                if (newFile) {
                    Files.copy(urlObject.openStream(), Paths.get(path));
                }
            }


            // Open the stream (which returns an InputStream)
            if (newFile) {
                this.in = urlObject.openStream();
            }
            else {
                this.in = new FileInputStream(cacheFile);
            }
            // Wrap the steam in an input source for compatibility with the SAX parser
            InputSource is = new InputSource(in);
            is.setEncoding(ENCODING);

            // Create the parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Parse the data
            if (this.author) {
                parser.parse(is, new dblpAuthorHandler());
                for (int i = 0;  i < dblpAuthorHandler.getUrls().size(); i++) {
                    parser.parse(dblpAuthorHandler.getUrls().get(i).openStream(), new dblpAuthorUrlHandler());
                }
                this.author = false;
            }
            if (this.publication) {
                parser.parse(is, new dblpPublicationHandler());
                this.publication = false;
            }
            if (this.venue) {
                parser.parse(is, new dblpVenueHandler());
                this.publication = false;
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }
}
