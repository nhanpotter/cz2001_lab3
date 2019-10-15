public class InsertionSort {
    public static void sort(int arr[], int l, int r)
    {
        for (int i = l+1; i < r+1; i++) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= l && arr[j] > key) {
                // Increment Modified MergeSort keyComparison
                ModifiedMergeSort.keyComparison++;

                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // Increment Modified MergeSort keyComparison
            ModifiedMergeSort.keyComparison++;
            
            arr[j + 1] = key;
        }
    }
}