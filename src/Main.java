import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int input;

        do {
            System.out.println("Here are things that you can do:");
            System.out.println("1. Test S");
            System.out.println("2. Test Array Size");
            System.out.println("0. Quit");
            System.out.printf("Your choice: ");

            input = sc.nextInt();


            switch (input) {
                case 1:
                    int[] array_sizes = {10000};
                    int[] thresholds = new int[100];

                    for (int s = 0; s < 100; s++) {
                        thresholds[s] = (s + 1) * 10;
                    }

                    for (int element : array_sizes) {
                        System.out.println("Array size: " + element);
                        testThreshold(element, thresholds);
                        System.out.println();
                    }
                    break;

                case 2:
                    // Input the size and threshold
                    int threshold = 20;
                    int startsize = 1000;
                    int maximum = 1000000;
                    int step = 10000;
                    int index = 0;
//        int maximum = 11001;

                    long[][] storeResult = new long[(maximum - startsize) / step][2];
                    long[][] storeResultModified = new long[(maximum - startsize) / step][2];

                    // Run and store into CSV file
                    for (int i = startsize; i <= maximum; i += step) {

                        // Initialize and create an array with random integer
                        App app = new App(i, threshold);
                        app.randomArrayGenerator();

                        long[] resultModified = app.modifiedMergeSort();
                        long[] result = app.mergeSort();

                        storeResult[index] = result;
                        storeResultModified[index] = resultModified;

                        printArray(resultModified, "Modified: ");
                        printArray(result, "Original: ");
                        System.out.println(i);
                        index++;
                    }

                    storeCSV("./data/original.csv", storeResult);
                    storeCSV("./data/modified.csv", storeResultModified);
                    break;

                case 0:
                    System.out.printf("Ciao!");
                    break;

                default:
                    System.out.printf("Invalid input. Try again");
            }

        }  while(input!=0);

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
