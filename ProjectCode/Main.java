import java.io.*;
import java.util.*;

public class Main {
    final int LINKS = 50;

    // current User
    static String currentUser = "";

    // check to see if user is admin or not
    static boolean isAdmin = false;

    // A collection of all post offices
    Set<PostOffice> postOffices = new HashSet<>();

    // Connections between the post offices
    Map<String, Set<String>> connections = new HashMap<>();

    /**
     * Build graph
     */
    void buildGraph() {
        // We use test.csv because postal.csv is too large to be used at run time
        // postal.csv contains about 30000 postal offices, to compute this, we requre a lot
        // of memory. Therefore, we used test.csv, which has about 100 postal offices
        readCSV("test.csv");
        buildConnections();
    }

    /**
     * Build connections between post postOffices,
     * This builds connections/roads between cities and allows for mail sending
     * Simulates roads
     * May result if no connection of the number of links is low
     */
    void buildConnections() {
        for (PostOffice postOffice : postOffices) {
            Random rand = new Random();
            // Create a set of connections
            Set<String> conn = new HashSet<>();

            // convert set of post offices to a list
            List<PostOffice> offices = new ArrayList<>(this.postOffices);
            // generate a random number of connections
            // Links is used as a seed. currently links is set to 50
            // If the links are too many, the route between each post office will be direct, no connecting post offices
            // If the links are too few, there will be broken link, i.e no connections between some post offices
            int count = rand.nextInt(LINKS);
            // This for loop creates random connections between post offices as it is tedious to
            // add connections manually/hard coded
            for (int j = 0; j < count; j++) {
                int index = rand.nextInt(offices.size());
                conn.add(offices.get(index).zip);
            }
            // add links to a post office zip code
            // distance is not factored here. it will be factored in the computation of distance between
            // two postal offices to establish the shortest path
            connections.put(postOffice.zip, conn);
        }

    }

    /**
     * Read in the CSV file of postal offices and add them to a set
     *
     * @param csvFile
     */
    public void readCSV(String csvFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            // Read the first line
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                // read each line add create a post Office
                postOffices.add(new PostOffice(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Login the user as Admin or as Customer
     *
     * @return true if user is logged in
     */
    private boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login as: \n1. Admin\n2. Customer");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            //admin password is "password"
            if (!Objects.equals(password, "password")) {
                System.out.println("Invalid Password");
                return false;
            } else {
                // Set current user as admin
                System.out.println("Welcome Admin");
                currentUser = "Admin";
                isAdmin = true;
                return true;
            }
        } else if (choice == 2) {
            // Set current user as customer
            System.out.println("Enter your Name");
            currentUser = scanner.nextLine();
            return true;
        }
        return false;
    }

    /**
     * Method to send mail. This method requests the user for the source zipcode, destination zipcode
     * and displays the route, and the number of days taken
     */
    void sendMail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your current ZIP Code:");
        String src = scanner.nextLine();
        System.out.println("Enter destination ZIP Code: ");
        String dest = scanner.nextLine();

        // Get the source post office
        PostOffice client = null, target = null;
        for (PostOffice postOffice : postOffices) {
            if (Objects.equals(postOffice.zip, src)) {
                client = postOffice;
            }
        }

        // set the destination post office
        for (PostOffice postOffice : postOffices) {
            if (Objects.equals(postOffice.zip, dest)) {
                target = postOffice;
            }
        }
        if (client == null || target == null) {
            System.out.println("Can't send mail, Invalid Source or Destination!");
        } else {
            // Number of days taken
            int daysTaken = 0;

            // Compute the shortest distance
            Graph graph = new Graph(postOffices, connections);
            RouteFinder routeFinder = new RouteFinder(graph, new HaversineScorer(), new HaversineScorer());
            List<PostOffice> route = routeFinder.findRoute(client, target);

            // Display results
            System.out.println("--------------------------------------------------------");
            System.out.println("Route");
            System.out.println("--------------------------------------------------------");
            for (PostOffice office : route) {
                System.out.println(office);
                // Increase the days taken
                daysTaken += office.delay;
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Mail will be delivered in " + daysTaken + " days");
            saveToFile(route);
        }
    }

    /**
     * Display Amin menu if the user is admin
     */
    void displayAdminMenu() {
        System.out.println("Admin\n" + "1. View All mail\n" + "2. Send Mail");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (Objects.equals(choice, "1")) {
            displayAllMail();
        } else if (Objects.equals(choice, "2")) {
            sendMail();
        } else System.out.println("Invalid Choice");
    }

    /**
     * display customer menu if the user is a customer
     */
    private void displayCustomerMenu() {
        System.out.println("Customer Menu\n" + "1. View my mail\n" + "2. Send Mail");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (Objects.equals(choice, "1")) {
            displayCustomerMail();
        } else if (Objects.equals(choice, "2")) {
            sendMail();
        } else System.out.println("Invalid Choice");

    }

    /**
     * Display all mail saved in file
     */
    void displayAllMail() {
        try {
            File file = new File("mail.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            fr.close();    //closes the stream and release the resources
            System.out.println("All Mail: ");
            System.out.println(buffer.toString());   //returns a string that textually represents the object
        } catch (IOException ignored) {
        }
    }

    /**
     * Display all mail saved in file with current customer Name
     */
    void displayCustomerMail() {
        try {
            File file = new File("mail.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String mailUser = data[data.length - 1].trim();
                if (mailUser.equals(currentUser)) {
                    buffer.append(line);
                    buffer.append("\n");
                }

            }
            fr.close();    //closes the stream and release the resources
            System.out.println("Your Mail: ");
            System.out.println(buffer.toString());   //returns a string that textually represents the object
        } catch (IOException ignored) {
        }
    }

    /**
     * Method to save mail routes to file
     * @param route
     */
    void saveToFile(List<PostOffice> route) {
        // save to file
        try {
            FileWriter fileWriter = new FileWriter("mail.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (PostOffice office : route) {
                printWriter.append(office.toString());
            }
            printWriter.append(",").append(currentUser).append("\n");
            printWriter.close();
            System.out.println("Saved to mail.txt");
        } catch (Exception ignored) {

        }
    }

    /**
     * Method to run the program
     */
    void run() {
        boolean run = true;
        loginUser();
        while (run) {
            if (isAdmin) {
                displayAdminMenu();
            } else {
                displayCustomerMenu();
            }
            System.out.println("Do you want to exit(Y/n)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                run = false;
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.buildGraph();
        main.run();
    }
}
