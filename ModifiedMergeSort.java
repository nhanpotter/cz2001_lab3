public class ModifiedMergeSort {
    public static long keyComparison=0;
    public static long runTime=0;
    
    /**
     * Initialize keyComparison to 0 and get start time
     */
    public static void start() {
        keyComparison = 0;
        runTime = System.nanoTime();
    }

    /**
     * Capture the runtime
     */
    public static void end() {
        long endTime = System.nanoTime();
        runTime = endTime - runTime;
    }

    /**
     * Main function
     */

    public static void sort(int arr[], int threshold) {
        start();
        modifiedMergeSort(arr, 0, arr.length-1, threshold);
        end();
    }

    public static void modifiedMergeSort(int arr[], int l, int r, int threshold)
    {
        if (l < r)
        {
            if (r-l > threshold) {
                // Find the middle point
                int m = (l+r)/2;

                // Sort first and second halves
                modifiedMergeSort(arr, l, m, threshold);
                modifiedMergeSort(arr , m+1, r, threshold);

                // Merge the sorted halves
                merge(arr, l, m, r);
            }
            else {
                //System.out.println("Executing Insertion Sort");
                InsertionSort.sort(arr);
            }
        }
    }

    public static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            // Increment keyComparison
            keyComparison++;

            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}