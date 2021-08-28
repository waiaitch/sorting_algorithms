package sorting_algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HybridSort {
	public static int s = 10; // Threshold should be in logn(?)
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
		
		int mid = n+(m-n)/2;
		if(m-n<=0) return;
		
		//Split the array into half recursively 
		else if(m-n>1) { //if 2 elements left
			mergesort(arr,n,mid);
			mergesort(arr,mid+1,m);
		}
		merge(arr,n,m);
	}

	//Merge with no auxiliary storage
	static void merge(int arr[], int n, int m) {
			
		int mid = (n+m)/2;
		int i=n;//1st element of 1st half
		int j=mid+1;//1st element of 2nd half
		if(m-n<=0) return;
		
		//while both halves are not empty
		while(i<=mid && j<=m) {
			comp++;
			//compare 1st element of both halves
			if(arr[i]<arr[j]) {
				i++; //no shifting done as element in correct position
			}
			else if(arr[i]>arr[j]) {
				int temp = arr[j]; //1st element of 2nd half into temp
				//shift all elements towards 'j' position
				for(int x=j;x>i;x--) {
					arr[x] = arr[x-1];
				}
				arr[i] = temp;//1st element of 2nd half joins end of merged list
				//Increment all position by one
				j++;
				i++;
				mid++;
			}
			else { //if 1st elements of 2 halves are equal
				if(i==mid && j==m) break; //if last element
				for(int x=j;x>i;x--) { ////shift all elements towards 'j' position
					arr[x] = arr[x-1];
				}
				arr[i+1] = arr[i]; //Copy element into position behind end of merged list
				i+=2;
				j++;
				mid++;
			}
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
			comp++;
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
		int arrSize = 1000;
		double avgTime =0;
		long avgComp = 0;
		double avgTime2 = 0;
		long avgComp2 = 0;
		
		//Start of hybridsort
		for(int x=0;x<10000;x++) {
			comp = 0;
			int arr[] = new int[arrSize];
			arr = randomArray(arrSize);// Generate random array
			int arr2[] = arr.clone();
			
			
			//System.out.println("Given Array");
			//printArray(arr);
			
			//Start of hybridsort
			double startTime = System.nanoTime();
			hybridSort(arr, 0, arr.length - 1);
			double stopTime = System.nanoTime();
			avgTime+=(stopTime-startTime)/1000000;
			avgComp+=comp;
			//End of hybridsort
			//Start of merge sort
			comp = 0;
			arr = randomArray(arrSize); // Generate random array
			double startTime2 = System.nanoTime();
			mergesort(arr2, 0, arr2.length-1);
			double stopTime2 = System.nanoTime();
			avgTime2+=(stopTime2-startTime2)/1000000;
			avgComp2+=comp;
			//End of merge sort
		}
		
		
		System.out.println("Average computational time of Hybrid Sort: " + avgTime/1000 + "ms");
		System.out.println("Average comparisons of Hybrid Sort: " + avgComp/1000);
		/*
		//Start of mergesort
		avgComp = 0;
		avgTime = 0;
		for(int x=0;x<10000;x++) {
			comp = 0;
			int arr[] = new int[arrSize];
			arr = randomArray(arrSize); // Generate random array
			double startTime2 = System.nanoTime();
			mergesort(arr, 0, arr.length-1);
			double stopTime2 = System.nanoTime();
			avgTime+=(stopTime2-startTime2)/1000000;
			avgComp+=comp;
		}
		//End of mergesort
		 */
		System.out.println("\nAverage computational time of Merge Sort: "+avgTime2/1000 + "ms");
		System.out.println("Average comparisons of Merge Sort: " + avgComp2/1000);
		
	}

}