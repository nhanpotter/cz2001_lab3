import java.io.*;

public class Main
{
    public static void main(String[] args) {
    	
    	// Comment this part and uncomment the codes below if you want to run normal code
    	// ################ TESTING SECTION ###################
//    	int[] array_sizes = {1000, 10000, 100000, 1000000};
//    	int[] thresholds = {3, 10, 30, 50};
//    	for (int element : array_sizes) {
//    		System.out.println("Array size: " + element);
//    		testThreshold(element, thresholds);
//    		System.out.println();
//    	}
    	// ####################################################
    	// ####################################################

        // Input the size and threshold
        int threshold = 70;
        int startsize = 20;
        int maximum = 30;
        int step = 5;
        int index = 0;
//        int maximum = 11001;

        long[][] storeResult = new long[(maximum-startsize)/step +1][2];
        long[][] storeResultModified = new long[(maximum-startsize)/step +1][2];

        // Run and store into CSV file
        for (int i=startsize; i <= maximum; i+=step) {

            // Initialize and create an array with random integer
            App app = new App(i, threshold);
            app.randomArrayGenerator();

            System.out.println("\nCurrent array:");
            app.printArray();
            System.out.println("\nSorted using Merge Sort:");
            long[] result = app.mergeSort();
            System.out.println("\nCurrent array:");
            app.printArray();
            System.out.println("\nSorted Using Modified Merge Sort:");
            long[] resultModified = app.modifiedMergeSort();

            storeResult[index] = result;
            storeResultModified[index] = resultModified;

            printArray(resultModified, "\nModified: ");
            printArray(result, "Original: ");
            System.out.printf("Array size: %d\n\n", i);
            index++;
        }

        storeCSV("./data/original.csv", storeResult);
        storeCSV("./data/modified.csv", storeResultModified);
    	
    }

    private static void storeCSV(String filepath, long[][] store) {
        try {
            FileWriter writer = new FileWriter(filepath);

            for (long[] store_elem : store) {
                writer.append(String.join(", ", String.valueOf(store_elem[0]),
                                            String.valueOf(store_elem[1])));
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void printArray(long[] arr, String word) {
        System.out.print(word);
        for (long val : arr)
            System.out.print(val + " ");
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (long val : arr)
            System.out.print(val + " ");
        System.out.println();
    }

    private static void testThreshold(int array_size, int[] thresholds) {
    	
        long[][] storeResult = new long[1][2];
        long[][] storeResultModified = new long[thresholds.length][2];
        
    	// For original MergeSort
    	App appOriginal = new App(array_size, 1);
    	appOriginal.randomArrayGenerator();
    	long[] resultOriginal = appOriginal.mergeSort();

    	storeResult[0][0] = resultOriginal[0];
    	storeResult[0][1] = resultOriginal[1];
    	printArray(resultOriginal, "Original: ");
    	
    	// For modified MergeSort
    	for (int i=0; i<thresholds.length; i++) {
    		App appModified = new App(array_size, thresholds[i]);
    		System.out.println("Threshold: " + thresholds[i]);
    		// copy the same array for fair comparison
    		appModified.setArray(appOriginal.getArray());
    		long[] resultModified = appModified.modifiedMergeSort();
    		
    		storeResultModified[i][0] = resultModified[0];
    		storeResultModified[i][1] = resultModified[1];
    		printArray(resultModified, "Modified: ");
    		
    		
    	}
    	
    	
    	storeCSV("data/original_test" + array_size+ ".csv", storeResult);
        storeCSV("data/modified_test" + array_size+ ".csv", storeResultModified);
    	
    }

    
}
