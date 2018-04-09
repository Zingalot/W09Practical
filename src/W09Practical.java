public class W09Practical {

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        final String SEARCH_COMMAND = "--search";
        final String QUERY_COMMAND = "--query";
        final String CACHE_COMMAND = "--cache";
        int searchChoice = 0;
        String query = "";
        String cachePath;
        if(args.length == 0){
            System.out.println("Usage: java W09Practical --search [author, publication, venue] --query <query> " +
                    "--cache <cache_filepath>");
            System.exit(0);
        }
        for(int j = 0; j < args.length; j+=2){
            try{
                if(args[j].equals(SEARCH_COMMAND)){
                    switch (args[j+1]){
                        case "author":
                            searchChoice = 1;
                            break;
                        case "publication":
                            searchChoice = 2;
                            break;
                        case "venue":
                            searchChoice = 3;
                            break;
                    }
                } else {
                    if (args[j].equals(QUERY_COMMAND)) {
                        query = args[j + 1];
                    } else{
                        if(args[j].equals(CACHE_COMMAND)){
                            cachePath = args[j+1];
                        } else{
                            System.out.println("Incorrect Arguments. Usage: java W09Practical --search [author, publication, venue] --query <query>" +
                                    " --cache <cache_filepath>");
                            System.exit(0);
                        }
                    }
                }


            } catch (ArrayIndexOutOfBoundsException a){
                System.out.println("Incorrect arguments. Usage: java W09Practical --search [author, publication, venue]"
                        + " --query <query> --cache <cache_filepath>");
                System.exit(0);
            }
        }

        switch (searchChoice){
            case 1:
                Search searcher = new Search("author");
                searcher.executeSearch(query);
                for (int i = 0; i < dblpAuthorHandler.getAuthors().size(); i++ ) {
                System.out.println(dblpAuthorHandler.getAuthors().get(i) + " - "
                        + dblpAuthorUrlHandler.getPublicationsByAuthor().get(i) + " publications with "
                        + dblpAuthorUrlHandler.getCoAuthors().get(i) + " co-authors.");
                }
                break;
            case 2:
                Search searcher2 = new Search("publication");
                searcher2.executeSearch(query);
                for (int i = 0; i <dblpPublicationHandler.getPublications().size(); i++ ) {
                    System.out.println(dblpPublicationHandler.getPublications().get(i) + " (number of authors: "
                            + dblpPublicationHandler.getAuthorNumbers().get(i) + ")");
                }
                break;
            case 3:
                Search searcher3 = new Search("venue");
                searcher3.executeSearch("Logic");
                for (int i = 0; i <dblpVenueHandler.getVenues().size(); i++ ) {
                    System.out.println(dblpVenueHandler.getVenues().get(i));
                }
                break;
            default:
                break;
        }
    }
}