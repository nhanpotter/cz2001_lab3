import java.io.*;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) {
        long[] result= new long[2];
        long[] resultModified = new long[2];
        
        // Input the size and threshold
        int[][] input = {{100,5}, {1000,30}};
        
        long[][] storeResult = new long[input.length][2];
        long[][] storeResultModified = new long[input.length][2];

        // Run and store into CSV file
        for (int i=0; i<input.length; i++) {
            // Initialize and create an array with random integer
            App app = new App(input[i][0], input[i][1]);
            app.randomArrayGenerator();

            resultModified = app.modifiedMergeSort();
            result = app.mergeSort();

            storeResult[i][0] = result[0];
            storeResult[i][1] = result[1];
            storeResultModified[i][0] = resultModified[0];
            storeResultModified[i][1] = resultModified[1];

            printArray(resultModified, "Modified: ");
            printArray(result, "Original: ");
            System.out.println();
        }

        storeCSV("./data/original.csv", storeResult);
        storeCSV("./data/modified.csv", storeResult);

    }

    public static void storeCSV (String filepath, long[][] store) {
        try {
            FileWriter writer = new FileWriter(filepath);

            for (int i=0; i<store.length; i++) {
                writer.append(String.join(", ", String.valueOf(store[i][0]), 
                                            String.valueOf(store[i][1])));
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void printArray(long arr[], String word) {
        int n = arr.length;
        System.out.print(word);
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    
}