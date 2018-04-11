import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class dblpAuthorHandler extends DefaultHandler {

    private boolean author = false;
    private boolean info = false;
    private boolean url = false;
    private static ArrayList<String> authors = new ArrayList<String>();
    private static ArrayList<URL> urls = new ArrayList<URL>();
    private StringBuilder chars = new StringBuilder();

    // Call super constructor
    public dblpAuthorHandler() {
        super();
    }

    public boolean isAuthor() {
        return author;
    }

    public boolean isInfo() {
        return info;
    }

    public boolean isUrl() {
        return url;
    }

    //Override Element and Character events
    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
        // Sets a boolean to true if the parser is 'in' a tag
        if (qName.compareTo("info") == 0) {
            info = true;
        }

        if (qName.compareTo("author") == 0) {
            author = true;
        }

        // An extra check needed here as there is more than one type of URL tag
        if ((qName.compareTo("url") == 0) && info) {
            url = true;
        }
    }

    public void endElement(String uri, String name, String qName) {
        if (qName.compareTo("author") == 0) {
            authors.add(chars.toString());
            author = false;
            chars.setLength(0);
        }
        if (qName.compareTo("url") == 0 && info) {
            String urlString = chars.toString();
            String urlIncludingFile = (urlString + ".xml");
            try {
                URL formattedURL = new URL(urlIncludingFile);
                urls.add(formattedURL);
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            }
            info = false;
            url = false;
            chars.setLength(0);
        }

    }
    public void characters(char ch[], int start, int length) {

        if (author) {
            // Adds the author's name to the ArrayList,
            chars.append(ch, start, length);
        }


        if (info && url) {
            // Creates and adds the URL object to an ArrayList
            chars.append(ch, start, length);
        }
    }

    public static ArrayList<String> getAuthors() {
        return authors;
    }

    public static ArrayList<URL> getUrls() {
        return urls;
    }
}
