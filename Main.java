import java.io.*;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) {
    	
    	// Comment this part and uncomment the codes below if you want to run normal code
    	// ################ TESTING SECTION ###################
    	int[] array_sizes = {1000, 10000, 100000, 1000000};
    	int[] thresholds = {3, 10, 30, 50};
    	for (int j=0; j < array_sizes.length; j++) {
    		System.out.println("Array size: " + array_sizes[j]);
    		testThreshold(array_sizes[j], thresholds);
    		System.out.println();
    	}
    	// ####################################################
    	// ####################################################
    	
    	
//        long[] result= new long[2];
//        long[] resultModified = new long[2];
//        
//        // Input the size and threshold
//        int[][] input = {{100,5}, {1000,30}};
//        
//        long[][] storeResult = new long[input.length][2];
//        long[][] storeResultModified = new long[input.length][2];
//
//        // Run and store into CSV file
//        for (int i=0; i<input.length; i++) {
//            // Initialize and create an array with random integer
//            App app = new App(input[i][0], input[i][1]);
//            app.randomArrayGenerator();
//
//            resultModified = app.modifiedMergeSort();
//            result = app.mergeSort();
//
//            storeResult[i][0] = result[0];
//            storeResult[i][1] = result[1];
//            storeResultModified[i][0] = resultModified[0];
//            storeResultModified[i][1] = resultModified[1];
//
//            printArray(resultModified, "Modified: ");
//            printArray(result, "Original: ");
//            System.out.println();
//        }
//
//        storeCSV("./data/original.csv", storeResult);
//        storeCSV("./data/modified.csv", storeResult);
    	
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
    
    private static void testThreshold(int array_size, int[] thresholds) {
    	long[] resultOriginal= new long[2];
        long[] resultModified = new long[2];
    	
        long[][] storeResult = new long[1][2];
        long[][] storeResultModified = new long[thresholds.length][2];
        
    	// For original MergeSort
    	App appOriginal = new App(array_size, 1);
    	appOriginal.randomArrayGenerator();
    	resultOriginal = appOriginal.mergeSort();
    	storeResult[0][0] = resultOriginal[0];
    	storeResult[0][1] = resultOriginal[1];
    	printArray(resultOriginal, "Original: ");
    	
    	// For modified MergeSort
    	for (int i=0; i<thresholds.length; i++) {
    		App appModified = new App(array_size, thresholds[i]);
    		System.out.println("Threshold: " + thresholds[i]);
    		// copy the same array for fair comparison
    		appModified.array = appOriginal.array.clone();
    		resultModified = appModified.modifiedMergeSort();
    		
    		storeResultModified[i][0] = resultModified[0];
    		storeResultModified[i][1] = resultModified[1];
    		printArray(resultModified, "Modified: ");
    		
    		
    	}
    	
    	
    	storeCSV("./data/original_test" + array_size+ ".csv", storeResult);
        storeCSV("./data/modified_test" + array_size+ ".csv", storeResultModified);
    	
    }

    
}