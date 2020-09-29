import java.util.Scanner;


public class SearchingSorting {

	public static void main(String[] args) {
		int N;
		//long swaps;
		long start, end;
		int[] selection, bubble, merge;
		long selectionTime = 0, bubbleTime = 0, mergeTime = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the size of the array to sort: ");
		N = input.nextInt();
		System.out.print("Would you like to print the arrays before and after sorting (Y/N)?");
		char printArrays = input.next().charAt(0);
		System.out.print("Would you like to include merge sort (Y/N)?");
		char includeMerge = input.next().charAt(0);
		System.out.println();
		
		selection = new int[N];
		bubble = new int[N];
		merge = new int[N];
		
		//Set the worst case values for each array
		initializeArrayValues(N, selection, bubble, merge);
		
		if(includeMerge == 'Y')
		{
			if(printArrays == 'Y')
			{
				System.out.print("Merge sort worst case array: ");
				printArray(merge);
			}
			//Time sorting functions
			start = System.nanoTime();
			mergeSort(N, merge);
			end = System.nanoTime();
			mergeTime += end - start;
			if(printArrays == 'Y')
			{
				System.out.print("After Sorting: ");
				printArray(merge);
			}
			System.out.println("Merge Sort time is: " + mergeTime/1000000000.0 + " s\n");
		}	
		if(printArrays == 'Y')
		{
			System.out.print("Selection sort worst case array: ");
			printArray(selection);
		}
		
		start = System.nanoTime();
		selectionSort(N, selection);
		end = System.nanoTime();
		selectionTime += end - start;

		if(printArrays == 'Y')
		{
			System.out.print("After sorting: ");
			printArray(selection);
		}
		System.out.println("Selection Sort time is: " + selectionTime/1000000000.0 + " s\n");
		
		if(printArrays == 'Y')
		{
			System.out.print("Bubble sort worst case array: ");
			printArray(bubble);
		}
		
		start = System.nanoTime();
		bubbleSort(bubble);
		end = System.nanoTime();
		bubbleTime += end - start;
		if(printArrays == 'Y')
		{
			System.out.print("After sorting: ");
			printArray(bubble);
		}
				
		System.out.println("Bubble Sort time is: " + bubbleTime/1000000000.0 + " s");
	}
	public static void bubbleSort (int array[]) 
	{
		boolean swapped;
		do
		{
			swapped = false;
			for (int j = 0; j < array.length - 1; j++) 
			{
				if (array[j] > array[j + 1]) 
				{
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					swapped = true;
				}
			}
		} while (swapped);
	}

	public static void selectionSort(int n, int[] sortArray) {
		//iMin represents the minimum for a given iteration.  This will be swapped with the index value, if necessary.
		int iMin;
		//long swaps = 0;

		//Loop over each position in the array, find the minimum value and swap if necessary
		for (int index = 0; index < n - 1; index++)
		{
			iMin = index;
			for (int i = index + 1; i < n; i++) {
				if (sortArray[i] < sortArray[iMin]) {
					iMin = i;
				}
			}

			//If iMin is not already in the correct place, we do a swap
			if (iMin != index) {
				int temp = sortArray[index];
				sortArray[index] = sortArray[iMin];
				sortArray[iMin] = temp;
			}
		}
	}
	
	public static void mergeSort(int n, int[] sortArray)
	{
		if(n > 1)
		{
			int size1 = (int)(Math.ceil((double)n/2.0));
			int size2 = (int)(Math.floor((double)n/2.0));
			int[] part1 = new int[size1];
			int[] part2 = new int[size2];
			for(int i = 0; i < size1; i++)
				part1[i] = sortArray[i];
			for(int i = 0; i < size2; i++)
				part2[i] = sortArray[i+size1];
			mergeSort(size1, part1);
			mergeSort(size2, part2);
			merge(size1, size2, part1, part2, sortArray);
		}	
	}
	public static void merge(int size1, int size2, int[] part1, int[] part2, int[] sortArray)
	{
		int i = 0, j = 0, k = 0;
		while(i < size1 && j < size2)
		{
			if(part1[i] <= part2[j])
			{
				sortArray[k] = part1[i];
				i++;
			}
			else
			{
				sortArray[k] = part2[j];
				j++;
			}
			k++;
		}
		while(i < size1)
		{
			sortArray[k] = part1[i];
			i++;
			k++;
		}
		while(j < size2)
		{
			sortArray[k] = part2[j];
			j++;       
			k++;
		}
	}
	
	public static void quickSort (int[] array, int first, int last)
	{
		if (last > first)
		{
			int pivotIndex = partition(array, first, last);
			quickSort(array, first, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, last);
		}
	}
	
	public static int partition (int[] array, int first, int last)
	{
		int pivot = array[first];
		int low = first + 1;
		int high = last;
		
		while (high > low)
		{
			while (low <= high && array[low] <= pivot)
				low++;
			
			while (low <= high && array[high] > pivot)
				high--;
			
			if (high > low)
			{
				int temp = array[high];
				array[high] = array[low];
				array[low] = temp;
			}
		}
		
		while (high > first && array[high] >= pivot)
			high--;
		
		if (pivot > array[high])
		{
			array[first] = array[high];
			array[high] = pivot;
			return high;
		}
		else
		{
			return first;
		}
	}
	
	public static void printArray(int[] searchArray)
	{
	   for(int i = 0; i < searchArray.length; i++)
	   {
	      if(i % 20 == 0)
	      {
	    	  System.out.println();
	      }
	      System.out.print(searchArray[i] + " " );
	   }
	   System.out.println("\n");
	}
	
	public static void initializeArrayValues(int N, int[] selection, int[] bubble, int[] merge)
	{
		int count = N-1;
		for(int i = 0; i < N; i++)
		{
			bubble[count] = i;
			merge[i] = (int)(Math.random()*N);
			count--;
		}
		
		selection[0] = N-1;
		
		for(int i = 1; i < N; i++)
		{
			selection[i] = i - 1;
		}

	}
}
