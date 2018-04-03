import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class dblpAuthorHandler extends DefaultHandler
{

    boolean author = false;
    boolean info = false;
    boolean url = false;
    private static ArrayList<String> authors = new ArrayList();
    private static ArrayList<URL> urls = new ArrayList();


    // Make sure that the code in DefaultHandler's
    // constructor is called:
    public dblpAuthorHandler()
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
        if(qName.compareTo("info") == 0){
            info = true;
        }

        if(qName.compareTo("author") == 0) {
            author = true;
        }

        if((qName.compareTo("url") == 0) && info == true){
            url = true;
        }
    }
    public void characters(char ch[], int start, int length){

        if (author) {
            //System.out.println("Author: " + new String(ch, start, length));
            authors.add(new String(ch, start, length));
            author = false;
        }
        if (info && url){
            String urlString = new String(ch, start, length);
            String urlIncludingFile = (urlString + ".xml");
            try {
                URL formattedURL = new URL(urlIncludingFile);
                urls.add(formattedURL);
            }catch (MalformedURLException mue){
                mue.printStackTrace();
            }

            //System.out.println(urlFound);
            info = false;
            url = false;
        }
    }

    public static ArrayList<String> getAuthors() {
        return authors;
    }

    public static ArrayList<URL> getUrls(){
        return urls;
    }
}