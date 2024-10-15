import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME

        //create two array list to store the Real Estate Data
        ArrayList<RealEstateData> storedArray = new ArrayList<RealEstateData>();
        //Read the file line and store the data line by line.
        while (inputFileNameScanner.hasNextLine()) {
            String line = inputFileNameScanner.nextLine().trim();//trims away any data
            if (line.isEmpty()) continue; // skip the empty lines
            String[] parts = line.split(",");
            //create a new RealEstateData object
            RealEstateData data = new RealEstateData(
                    //ID
                    Integer.parseInt(parts[0]),
                    //possessionStatus
                    parts[1],
                    //Commercial
                    parts[2],
                    //Developer
                    parts[3],
                    //price
                    Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5]), //sqftprice
                    parts[6],//furnished
                    Integer.parseInt(parts[7]), //bathroom
                    parts[8], //direction facing
                    parts[9], //transaction
                    parts[10], //type
                    parts[11], //city
                    Integer.parseInt(parts[12]), // bedrooms
                    Integer.parseInt(parts[13]), //floors
                    parts[14], //isprimelocation
                    parts[15]); // lifespan

                    storedArray.add(data);
        }
        inputFileNameScanner.close();

        //Sort the Data
        Collections.sort(storedArray);

        //print the data
        for(RealEstateData data : storedArray) {
            System.out.println(data);
        }
    }
}
