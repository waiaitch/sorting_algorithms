package sorting_algorithms;

import java.util.concurrent.ThreadLocalRandom;

public class HybridSort {
	public static int s = 4; // Threshold should be in logn(?)
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

	static void merge(int arr[],int n, int m) {
		if(m-n<=0) return;
		
		int mid = (n+m)/2;
		int i=n;//1st element of 1st half
		int j=mid+1;//1st element of 2nd half
		
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
	
	public static void insertionSort(int arr[], int left, int right) {
	    for (int i = left; i < right; i++) {
	        int tempVal = arr[i + 1];
	        int j = i + 1;
	        while (j > left && arr[j - 1] > tempVal) {
	        	comp++;
	            arr[j] = arr[j - 1];
	            j--;
	        }
	        arr[j] = tempVal;
	    }
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
		int arr[] = new int[arrSize];
		arr = randomArray(arrSize); // Generate random array

		System.out.println("Given Array");
		printArray(arr);

		hybridSort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		printArray(arr);

		System.out.println("\nNumber of Comparisons: " + comp);
	}

}