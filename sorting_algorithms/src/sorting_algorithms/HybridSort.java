package sorting_algorithms;

//import java.io.*;
public class HybridSort {
	static int s = 10; //THRESHOLD
 
    private static void insertionSort(int a[], int low,
                                     int high)
    {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i - 1; j >= low; j--) {
                if (a[j] > a[j + 1]) {
                    // Swap
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                else
                    break;
            }
        }
    }
 
    private static int partition(int arr[], int low,
                                int high)
    {
        int pivot = arr[high];
        int i = low;
        int j = low;
 
        while (i <= high) {
            if (arr[i] > pivot)
                i++;
            else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j++;
            }
        }
        return j - 1;
    }
 
    public static void hybridQuickSort(int arr[], int low,
                                       int high)
    {
        while (low < high) {
            // Check if array size on which we will be working is less than 10
            if (high - low < s) {
                insertionSort(arr, low, high);
                break;
            }
            else {
                int pivot = partition(arr, low, high);
 
                // We will do recursion on small size
                // subarray So we can check pivot - low  and
                // pivot - high
 
                if (pivot - low < pivot - high) {
                    hybridQuickSort(arr, low, pivot - 1);
                    low = pivot + 1;
                }
                else {
                    hybridQuickSort(arr, pivot + 1, high);
                    high = pivot - 1;
                }
            }
        }
    }
   
  // Driver code
    public static void main(String[] args)
    {
 
        int arr[]
            = { 24, 97, 40, 67, 88, 85, 15, 66, 53, 44, 26 };
 
        hybridQuickSort(arr, 0, arr.length - 1);
        for (int i : arr) //for int in array
            System.out.print(i + "  ");
    }
}
 