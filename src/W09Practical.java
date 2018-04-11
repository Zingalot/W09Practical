import java.io.File;

public class W09Practical {
    private static String cachePath;

    public static String getCachePath() {
        return cachePath;
    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        final String search_command = "--search";
        final String query_command = "--query";
        final String cache_command = "--cache";
        int searchPosition = -1;
        int queryPosition = -1;
        int cachePosition = -1;
        int searchChoice = 0;
        String query = "";
        if (args.length == 0) {
            System.out.println("Usage: java W09Practical --search [author, publication, venue] --query <query> "
                    + "--cache <cache_filepath>");
            System.exit(0);
        }
        //Finds indicies of "--" commands
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case search_command:
                    searchPosition = i;
                    break;
                case query_command:
                    queryPosition = i;
                    break;
                case cache_command:
                    cachePosition = i;
                    break;
                default:
                    break;
            }
        }
        if (searchPosition == -1) {
            System.out.println("Missing search command");
            System.out.println("Malformed command line arguments.");
            System.exit(0);
        }
        if (queryPosition == -1) {
            System.out.println("Missing query command");
            System.out.println("Malformed command line arguments.");
            System.exit(0);
        }
        if (cachePosition == -1) {
            System.out.println("Missing cache command");
            System.out.println("Malformed command line arguments.");
            System.exit(0);
        }

        try {
            switch (args[searchPosition + 1]) {
                    case "author":
                        searchChoice = 1;
                        break;
                    case "publication":
                        searchChoice = 2;
                        break;
                    case "venue":
                        searchChoice = 3;
                        break;
                    default:
                        System.out.println("Invalid value for --search: " + args[searchPosition + 1]);
                        System.out.println("Malformed command line arguments.");
                        System.exit(0);
                        break;
            }
        } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Missing value for --search");
                System.out.println("Malformed command line arguments.");
                System.exit(0);
        }


        try {
            query = args[queryPosition + 1];
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Missing value for --query");
            System.out.println("Malformed command line arguments.");
            System.exit(0);
        }

        try {
            cachePath = args[cachePosition + 1];
            File testDirectory = new File(cachePath);
            if (!testDirectory.exists()) {
                System.out.println("Cache directory doesn't exist: " + args[cachePosition + 1]);
                System.exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Missing value for cache");
            System.out.println("Malformed command line arguments.");
            System.exit(0);
        }



        switch (searchChoice) {
            case 1:
                Search searcher = new Search("author");
                searcher.executeSearch(query);
                for (int i = 0; i < dblpAuthorHandler.getAuthors().size(); i++) {
                System.out.println(dblpAuthorHandler.getAuthors().get(i) + " - "
                        + dblpAuthorUrlHandler.getPublicationsByAuthor().get(i) + " publications with "
                        + dblpAuthorUrlHandler.getCoAuthors().get(i) + " co-authors.");
                }
                break;
            case 2:
                Search searcher2 = new Search("publication");
                searcher2.executeSearch(query);
                for (int i = 0; i < dblpPublicationHandler.getPublications().size(); i++) {
                    System.out.println(dblpPublicationHandler.getPublications().get(i) + " (number of authors: "
                            + dblpPublicationHandler.getAuthorNumbers().get(i) + ")");
                }
                break;
            case 3:
                Search searcher3 = new Search("venue");
                searcher3.executeSearch(query);
                for (int i = 0; i < dblpVenueHandler.getVenues().size(); i++) {
                    System.out.println(dblpVenueHandler.getVenues().get(i));
                }
                break;
            default:
                break;
        }
    }
}
