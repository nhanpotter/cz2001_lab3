public class Main {
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method
    public static void main(String args[])
    {
        RandomArrayGenerator random_arr = new RandomArrayGenerator();
        int[] arr = random_arr.generate(15);

        System.out.println("Given Array");
        printArray(arr);

        ModifiedMergeSort ob = new ModifiedMergeSort();
        ob.sort(arr, 0, arr.length-1);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
