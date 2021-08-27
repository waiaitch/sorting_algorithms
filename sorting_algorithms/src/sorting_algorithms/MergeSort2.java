package sorting_algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MergeSort2 {
	
	public static int comp;
	
	void mergesort(int arr[], int n, int m) {
		
		int mid = (n+m)/2;
		if(m-n<=0) return;
		
		//Split the array into half recursively 
		else if(m-n>1) { //if 2 elements left
			mergesort(arr,n,mid);
			mergesort(arr,mid+1,m);
		}
		merge(arr,n,m);
	}
	

	void merge(int arr[],int n, int m) {
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
	static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	static int[] randomArray(int arrSize) {
		int min = 0;
		int max = arrSize+1;
		
		int arr[] = new int[arrSize];
		for(int x=0;x<arrSize;x++) {
			arr[x] = ThreadLocalRandom.current().nextInt(min,max);
		}
		
		return arr;
	}
	
	public static void main(String args[])
    {
        int arrSize = 100;
        int arr[] = new int[arrSize];
        arr = randomArray(arrSize); //Generate random array
        
        System.out.println("Given Array");
        printArray(arr);
 
        MergeSort2 ob = new MergeSort2();
        ob.mergesort(arr, 0, arr.length - 1);
 
        System.out.println("\nSorted array");
        printArray(arr);
        
        System.out.println("\nNumber of Comparisons: "+comp);
    }
	
}