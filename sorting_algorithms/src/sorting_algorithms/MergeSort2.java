package sorting_algorithms;

import java.util.Arrays;

public class MergeSort2 {
	
	void mergesort(int arr[], int n, int m) {
		
		int mid = (n+m)/2;
		if(m-n<=0) return;
		
		else if(m-n>1) {
			mergesort(arr,n,mid);
			mergesort(arr,mid+1,m);
		}
		merge(arr,n,m);
		System.out.println("\nSorted array");
        printArray(arr);
	}
	

	void merge(int arr[],int n, int m) {
		if(m-n<=0) return;
		
		int mid = (n+m)/2;
		int i=0;
		int j=mid+1;
		
		while(i<=mid && j<=m) {
			if(arr[i]<arr[j]) {
				i++;
			}
			else if(arr[j]<arr[i]) {
				int temp = arr[j];
				for(int x=j;x>i;x--) {
					arr[x] = arr[x-1];
				}
				arr[i] = temp;
				j++;
			}
			else {
				if(i==mid && j==m) break;
				int temp = arr[j];
				for(int x=j;x>i;x--) {
					arr[x] = arr[x-1];
				}
				arr[i] = temp;
				i++;
				j++;
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
	
	public static void main(String args[])
    {
        int arr[] = { 90, 25, 10, 71, 94, 22, 59, 74 };
 
        System.out.println("Given Array");
        printArray(arr);
 
        MergeSort2 ob = new MergeSort2();
        ob.mergesort(arr, 0, arr.length - 1);
 
        System.out.println("\nSorted array");
        printArray(arr);
    }
	
}