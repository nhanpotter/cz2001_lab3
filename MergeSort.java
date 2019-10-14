package app;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] test = new int[] {7,6,3,1,9,4};
        System.out.println(Arrays.toString(test));
        mergeSort(test, 0, 6, 1000);
        System.out.println(Arrays.toString(test));
    }

    public static void insertionSort(int[] E, int first, int last){

        for (int i = first; i < last; i++ ) {
            for (int j = i; j>0; j--) {
                if (E[j] < E[j-1]) {
                    int tmp = E[j-1];
                    E[j-1] = E[j];
                    E[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    public static void mergeSort(int[] E, int first, int last, int S) {
        if ( (last - first) > S) {
            int mid = (first + last)/2;
            mergeSort(E, first, mid, S);
            mergeSort(E, mid + 1, last, S);
            merge(E, first, mid, last);
        } else {
            insertionSort(E, first, last);
        }
    }

    public static void merge(int[] slot, int n, int mid, int m) {
        int a = n, b = mid+1, i, tmp;

        if (m-n <= 0) return;
        
        while (a <= mid && b <= m) {   
		    boolean cmp = slot[a] > slot[b];

            if (cmp == true) { //slot[a] > slot[b]
                tmp = slot[b++];
                for (i = ++mid; i > a; i--)
                    slot[i] = slot[i-1];
                    slot[a++] = tmp;

            } else if (cmp == false) //slot[a] < slot[b]
                a++;        
            else {   //slot[a] == slot[b]
                if (a == mid && b == m) 
                    break;
                tmp = slot[b++];
                a++;
                for (i = ++mid; i > a; i--)
                    slot[i] = slot[i-1];
                    slot[a++] = tmp;
            }
        }
    }
}