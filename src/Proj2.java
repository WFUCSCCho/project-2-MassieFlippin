import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
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
        ArrayList<RealEstateData> originalArray = new ArrayList<RealEstateData>();
        //ArrayList<RealEstateData> shuffledArray = new ArrayList<RealEstateData>();
        //Read the file line and store the data line by line.
        for(int i = 0; i < numLines && inputFileNameScanner.hasNextLine(); i++) {
            String line = inputFileNameScanner.nextLine().trim();//trims away any data
            if (line.isEmpty()) continue;// skip the empty lines
            String[] parts = line.split(",");
            //create a new RealEstateData object
            try {
                if (parts.length < 16) {
                    continue;
                }
                RealEstateData data = new RealEstateData(
                        Integer.parseInt(parts[0]), //ID
                        parts[1], //PossesionStatus
                        parts[2], // Commercial
                        parts[3], //Developer
                        Integer.parseInt(parts[4]), //price
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
                originalArray.add(data);
            }catch (NumberFormatException e) {
                System.err.println("Error parsing line: " + line);
            }
        }
        inputFileNameScanner.close();


        //Sort the Data
        Collections.sort(originalArray);

        //Calculate the Insert and Search time for a sorted BST
        BST<RealEstateData> bstsorted = new BST<>();
        long startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            bstsorted.insert(data);

        }
        long endtime = System.nanoTime();
        long time = endtime - startTime;
        System.out.println("Sorted BST Insert Runtime: " + time);
        writeToFile("Sorted BST Insert Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //Search for an element in a Sorted BST
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            bstsorted.find(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Sorted BST Search Runtime: " + time);
        writeToFile("Sorted BST Search Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //Calculate the Insert and Search time for a shuffled BST
        Collections.shuffle(originalArray); //Shuffle the array
        BST<RealEstateData> bstshuffled = new BST<>();
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            bstshuffled.insert(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Shuffled BST Insert Runtime: " + time);
        writeToFile("Shuffled BST Insert Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //search in a shuffled Array
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            bstshuffled.find(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Shuffled BST Search Runtime: " + time);
        writeToFile("Shuffled BST Search Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        writeToFile("\n", "./output.txt");

        System.out.println();
        System.out.println();

        //Start of the AVL Calcualtions
        Collections.sort(originalArray); //sort the array
        AvlTree<RealEstateData> sortedAVL = new AvlTree<>();
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            sortedAVL.insert(data);

        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Sorted AVL Insert Runtime: " + time);
        writeToFile("Sorted AVL Insert Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //Search for an element in a Sorted AVL
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            sortedAVL.contains(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Sorted AVL Search Runtime: " + time);
        writeToFile("Sorted AVL Search Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //Calculate the Insert and Search time for a shuffled BST
        Collections.shuffle(originalArray); //Shuffle the array
        AvlTree<RealEstateData> shuffledAVL = new AvlTree<RealEstateData>();
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            shuffledAVL.insert(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Shuffled AVL Insert Run Time: " + time);
        writeToFile("Shuffled AVL Insert Runtime: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");

        //search in a shuffled Array
        startTime = System.nanoTime();
        for (RealEstateData data : originalArray) {
            shuffledAVL.contains(data);
        }
        endtime = System.nanoTime();
        time = endtime - startTime;
        System.out.println("Shuffled AVL Search time: " + time);
        writeToFile("Shuffled AVL Search time: " + String.valueOf(time), "./output.txt");
        writeToFile("\n", "./output.txt");
        writeToFile("\n", "./output.txt");
    }
    //implement the writeToFile path.
    public static void writeToFile(String content, String filePath) {
        try {
            //FileWriter and BufferedWriter to assure that when writeToFile is called it can write to a file.
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            fileWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException ignored) {}
    }
}
