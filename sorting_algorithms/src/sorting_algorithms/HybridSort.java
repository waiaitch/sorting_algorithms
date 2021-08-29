package sorting_algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HybridSort {
	public static int s = 4; // Threshold
	public static int comp;
	public static int sample = 1	; //Sample size

	private static void hybridSort(int[] arr, int left, int right) {
	    if (right - left <= s) {
	        insertionSort(arr, left, right);
	    } else {
	        int mid = left + (right - left) / 2;
	        hybridSort(arr, left, mid);
	        hybridSort(arr, mid + 1, right);
	        merge2(arr, left, right);
	    }
	}
	
	static void mergeSort(int arr[], int n, int m) {
		
		int mid = n+(m-n)/2;
		if(m-n<=0) return;
		
		//Split the array into half recursively 
		else if(m-n>1) { //if 2 elements left
			mergeSort(arr,n,mid);
			mergeSort(arr,mid+1,m);
		}
		merge2(arr,n,m);
	}

	//In place merge sort
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
				//shift all elements between 'i' and 'j' to right
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
				for(int x=j;x>i;x--) { //shift all elements between 'i' and 'j' to right
					arr[x] = arr[x-1];
				}
				arr[i+1] = arr[i]; //Copy element into position behind end of merged list
				i+=2;
				j++;
				mid++;
			}
		}
	}
	
	//Merge Sort
	static void merge2(int arr[], int n, int m)
    {
		int mid = (n+m)/2;
        //Divide list into 2 halves
        int h1 = mid - n + 1;
        int h2 = m - mid;
 
        //Split into 2 arrays
        int L[] = new int[h1];
        int R[] = new int[h2];
 
        //Insert elements into both halves
        for (int i = 0; i < h1; ++i)
            L[i] = arr[n + i];
        for (int j = 0; j < h2; ++j)
            R[j] = arr[mid + 1 + j];
 
        // Index of start of both halves
        int i = 0, j = 0;
 
        // Index of current position of merged list
        int k = n;
        // while both halves are not empty
        while (i < h1 && j < h2) {
        	comp++;
        	//if element of 1st half is smaller
            if (L[i] < R[j]) {
                arr[k] = L[i]; //element joins end of merged list
                i++;
            }
            //if element of 
            else if(L[i]>R[j]) {
                arr[k] = R[j];
                j++;
            }
            else {
            	arr[k] = L[i];
            	arr[++k] = R[j];
            	i++;
            	j++;
            }
            k++;
        }
 
        // Copy remaining elements of 1st half
        while (i < h1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        // Copy remaining elements of 2nd half
        while (j < h2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
	
	
	static boolean isSorted(int[] array) {
	    for (int i = 0; i < array.length - 1; i++) {
	        if (array[i] > array[i + 1])
	            return false;
	    }
	    return true;
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
		int arrSize = 100;
		double avgTime =0;
		long avgComp = 0;
		double avgTime2 = 0;
		long avgComp2 = 0;
		
		/*
		//For testing sorted array
		for(int x=0;x<sample;x++) {
			comp = 0;
			int arr[] = new int[arrSize];
			for(int z=0;z<arrSize;z++) {
				arr[z] = z;
			}
			int arr2[] = arr.clone();
			
			//Start of hybridsort
			double startTime = System.nanoTime();
			hybridSort(arr, 0, arr.length - 1);
			double stopTime = System.nanoTime();
			avgTime+=(stopTime-startTime)/1000000;
			avgComp+=comp;
			//End of hybridsort
			//Start of merge sort
			comp = 0;
			double startTime2 = System.nanoTime();
			mergeSort(arr2, 0, arr2.length-1);
			double stopTime2 = System.nanoTime();
			avgTime2+=(stopTime2-startTime2)/1000000;
			avgComp2+=comp;
			//System.out.println(isSorted(arr));
			//End of merge sort
		}
		
		
		System.out.println("Average computational time of Hybrid Sort: " + avgTime/sample + "ms");
		System.out.println("Average comparisons of Hybrid Sort: " + avgComp/sample);

		System.out.println("\nAverage computational time of Merge Sort: "+avgTime2/sample + "ms");
		System.out.println("Average comparisons of Merge Sort: " + avgComp2/sample);
		int diff = (int) (((avgTime2/1000)/(avgTime/1000))*100);
		System.out.println("\nDifference in Computational time of Hybrid vs Merge: " + diff +"%");
		System.out.println("Difference in Comparisons: " + (avgComp2/sample-avgComp/sample));
		
		
		*/
		
		//Start of hybridsort
		for(int x=0;x<sample;x++) {
			comp = 0;
			int arr[] = new int[arrSize];
			arr = randomArray(arrSize);// Generate random array
			int arr2[] = arr.clone();
			
			//Start of hybridsort
			double startTime = System.nanoTime();
			hybridSort(arr, 0, arr.length - 1);
			double stopTime = System.nanoTime();
			avgTime+=(stopTime-startTime)/1000000;
			avgComp+=comp;
			//End of hybridsort
			//Start of merge sort
			comp = 0;
			double startTime2 = System.nanoTime();
			mergeSort(arr2, 0, arr2.length-1);
			double stopTime2 = System.nanoTime();
			avgTime2+=(stopTime2-startTime2)/1000000;
			avgComp2+=comp;
			//System.out.println(isSorted(arr));
			//End of merge sort
		}
		
		
		System.out.println("Average computational time of Hybrid Sort: " + avgTime/sample + "ms");
		System.out.println("Average comparisons of Hybrid Sort: " + avgComp/sample);

		System.out.println("\nAverage computational time of Merge Sort: "+avgTime2/sample + "ms");
		System.out.println("Average comparisons of Merge Sort: " + avgComp2/sample);
		int diff = (int) (((avgTime2/1000)/(avgTime/1000))*100);
		System.out.println("\nDifference in Computational time of Hybrid vs Merge: " + diff +"%");
		System.out.println("Difference in Comparisons: " + (avgComp2/sample-avgComp/sample));
		
		
	}

}