public class W09Practical {

    public static void main(String[] args) {
        //This will eventually be a command line input, for now just a test string
        /*Search searcher = new Search("author");
        searcher.executeSearch("jack cole");
        for (int i = 0; i <dblpAuthorHandler.getAuthors().size(); i++ ) {
            System.out.println(dblpAuthorHandler.getAuthors().get(i) + " - "
            + dblpAuthorUrlHandler.getPublicationsByAuthor().get(i) + " publications with "
            + dblpAuthorUrlHandler.getCoAuthors().get(i) + " co-authors.");
        }*/

        Search searcher2 = new Search("publication");
        searcher2.executeSearch("database");
        for (int i = 0; i <dblpPublicationHandler.getPublications().size(); i++ ) {
            System.out.println(dblpPublicationHandler.getPublications().get(i) + " (number of authors: "
                    + dblpPublicationHandler.getAuthorNumbers().get(i) + ")");
        }

        /*Search searcher3 = new Search("venue");
        searcher3.executeSearch("Logic");
        //System.out.println(dblpVenueHandler.getVenues().get(1));*/
    }
}