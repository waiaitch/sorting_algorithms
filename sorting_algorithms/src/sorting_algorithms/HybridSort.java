package sorting_algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HybridSort {
	public static int s = 6; // Threshold should be in logn(?)
	public static int comp;

	private static void hybridSort(int[] arr, int left, int right) {
	    if (right - left <= s) {
	        insertionSort(arr, left, right);
	    } else {
	        int mid = left + (right - left) / 2;
	        hybridSort(arr, left, mid);
	        hybridSort(arr, mid + 1, right);
	        merge(arr, left, right);
	    }
	}
	
	static void mergesort(int arr[], int n, int m) {
		
		int mid = (n+m)/2;
		if(m-n<=0) return;
		
		//Split the array into half recursively 
		else if(m-n>1) { //if 2 elements left
			mergesort(arr,n,mid);
			mergesort(arr,mid+1,m);
		}
		merge(arr,n,m);
	}

	//Merge with auxiliary storage
	static void merge(int arr[], int n, int m) {
		
		int mid = (n+m)/2;
		
		int h1 = mid - n + 1;
        int h2 = m - mid;
 
        //Split array into 2 halves
        int L[] = new int[h1];
        int R[] = new int[h2];
 
        //Copy elements into each halves respectively
        for (int i = 0; i < h1; ++i)
            L[i] = arr[n + i];
        for (int j = 0; j < h2; ++j)
            R[j] = arr[mid + 1 + j];
 
        // Initial indexes of first and second halves
        int i = 0, j = 0;
 
        // Initial index of merged list
        int k = n;
        while (i < h1 && j < h2) {
        	comp++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        // Copy remaining elements of L[] into merged list
        while (i < h1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        // Copy remaining elements of R[] into merged list
        while (j < h2) {
            arr[k] = R[j];
            j++;
            k++;
        }
		
	}
	
	private static void insertionSort(int[] ar, int left, int right) {
		if (ar == null) {
			return;
		}
		for (int i = left; i < right; i++) {
			for (int j = i + 1; j > left && ar[j] < ar[j - 1]; j--) {
				comp++;
				swap(ar, j - 1, j);
			}
		}
	}

	private static void swap(int[] ar, int i, int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}
	
	

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static int[] randomArray(int arrSize) {
		int min = 0;
		int max = arrSize + 1;

		int arr[] = new int[arrSize];
		for (int x = 0; x < arrSize; x++) {
			arr[x] = ThreadLocalRandom.current().nextInt(min, max);
		}

		return arr;
	}

	public static void main(String args[]) {
		int arrSize = 10000;
		int arr[] = new int[arrSize];
		arr = randomArray(arrSize); // Generate random array
		int arr2[] = new int[arrSize];
		arr2 = arr.clone();
		
		
		System.out.println("Given Array");
		//printArray(arr);
		
		//Start of hybridsort
		double startTime = System.nanoTime();
		hybridSort(arr, 0, arr.length - 1);
		double stopTime = System.nanoTime();
		
		System.out.println("\nSorted array of HybridSort");
		//printArray(arr);
		
		System.out.println("\nNumber of Comparisons for HybridSort: " + comp);
		System.out.println("\nComputational time: "+ (stopTime - startTime)/1000000+"ms");
		//End of hybrid sort
		
		
		//Start of mergesort
		comp = 0;
		double startTime2 = System.nanoTime();
		mergesort(arr2, 0, arr2.length-1);
		double stopTime2 = System.nanoTime();
		//System.out.println("\nSorted array of MergeSort");

		System.out.println("\nNumber of Comparisons for MergeSort: " + comp);
		System.out.println("\nComputational time: "+ (stopTime2 - startTime2)/1000000+"ms");
		//End of mergesort
		
		//Check if both sorted arrays are equal
		System.out.println("\nIs array equal: "+Arrays.equals(arr, arr2));
	}

}