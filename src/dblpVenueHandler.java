import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class dblpVenueHandler extends DefaultHandler {

    private boolean venue = false;
    private static ArrayList<String> venues = new ArrayList<String>();
    private StringBuilder chars = new StringBuilder();

    // Call super constructor
    public dblpVenueHandler() {
        super();
    }

    public boolean isVenue() {
        return venue;
    }

    // Operates similarly to the other handlers
    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
        if (qName.compareTo("venue") == 0) {
            venue = true;
        }
    }

    public void endElement(String uri, String name, String qName) {
        if (qName.compareTo("venue") == 0) {
            venues.add(chars.toString());
            venue = false;
            chars.setLength(0);
        }
    }

    public void characters(char ch[], int start, int length) {
        if (venue) {
            chars.append(ch, start, length);
        }
    }

    public static ArrayList<String> getVenues() {
        return venues;
    }
}
