import java.util.Random;
/** 
 * The App generates random integers data set and runs sorting algorithm on it.
 * The sorting algorith is Mergesort and Modified Mergesort with Insertion sort
 * It count the number of key comparisons key comparisons and CPU times taken
 */
public class App
{
    // For random array generator
    private static final int SIZE_FACTOR=10;
    // Number of times to run the sorting algorithm and take average to get runtime
    private static final int LOOP=100;
    private int threshold;
    private int size;
    private int[] array;

    /** 
     * App constructor 
     * @param size - the size of the array
     * @param threshold - the value of threshold S to use Insertion Sort
     */

    public App(int size, int threshold) {
        this.size = size;
        this.threshold = threshold;
        this.array = new int[size];
    }

    /**
     * Generate random integer and store in array attribute
     */

    public void randomArrayGenerator() {
        Random random = new Random();
        for (int i=0; i<this.size; i++) {
            this.array[i] = random.nextInt(SIZE_FACTOR*this.size);
        }
    }

    /**
     * Sort this instance's array using original mergesort
     * Print out the array
     * @return result long array which includes no of key comparison and runtime
     */
    
    public long[] mergeSort() {
        long runtime =0;
        int[] newArray = new int[this.size];
        for (int i=0; i<LOOP; i++) {
            newArray = this.array.clone();
            MergeSort.sort(newArray);
            runtime += MergeSort.runTime;
        }
        
        printArray(newArray);
        long[] res = new long[2];
        res[0] = MergeSort.keyComparison;
        res[1] = runtime/LOOP;
        return res;
    }

    /**
     * Sort this instance's array using modified mergesort with insertion sort
     * Print out the array
     * @return result long array which includes no of key comparison and runtime
     */

    public long[] modifiedMergeSort() {
        long runtime =0;
        int[] newArray = new int[this.size];
        for (int i=0; i<LOOP; i++) {
            newArray = this.array.clone();
            ModifiedMergeSort.sort(newArray, this.threshold);
            runtime += ModifiedMergeSort.runTime;
        }        


        printArray(newArray);
        long[] res = new long[2];
        res[0] = ModifiedMergeSort.keyComparison;
        res[1] = runtime/LOOP;
        return res;
    }

    /**
     * Print out array
     */

    public void printArray() {
        printArray(this.array);
    }

    public void printArray(int[] arr) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return this.array.clone();
    }

}