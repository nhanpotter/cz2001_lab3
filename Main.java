public class Main
{
    public static void main(String[] args) {
        long[] result= new long[2];
        long[] result2 = new long[2];
        // Initialize
        App app = new App(100, 3);
        // Create an array with random integer
        app.randomArrayGenerator();
        result = app.modifiedMergeSort();
        result2 = app.mergeSort();
        printArray(result);
        printArray(result2);
    }

    public static void printArray(long arr[]) {
        int n = arr.length;
        System.out.println("Array:");
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