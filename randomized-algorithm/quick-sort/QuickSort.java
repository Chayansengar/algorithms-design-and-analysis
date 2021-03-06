/**
 * @author QingxiaoDong
 * 
 * Sorts an array of integers using QuickSort.
 * 
 * Input format: 
 * The file contains all of the integers between 1 and 10,000 (inclusive, with 
 * no repeats) in unsorted order. The integer in the ith row of the file gives 
 * you the ith entry of an input array.
 * 
 * Output format:
 * Computes and returns the total number of comparisons used to sort the given 
 * input file by QuickSort
 * 
 * Pivot choosing: 
 * Consider the first, middle, and final elements of the given array. Identify 
 * which of these three elements is the median, and use this as pivot.
 * 
 * Time complexity: O(n log n) if randomly choose pivot
 */

import java.io.*;
import java.util.*;

public class QuickSort {
	
	static int comparisonCount = 0;
	
	static List<Integer> quickSort(List<Integer> toSort, int start, int end) {
		if (start >= end) {
			return toSort;
		}
		//pick first element as pivot:
		//int pivot = toSort.get(start);
		
		//pick last element as pivot:
		//int pivot = toSort.get(end);
		//Collections.swap(toSort, start, end);
		
		//pick the median of (first, middle, last) as  pivot
		int median = median(toSort, start, end);
		int pivot = toSort.get(median);
		Collections.swap(toSort, start, median);
		
		int i = start + 1;
		for (int j = start + 1; j <= end; j++) {
			if (toSort.get(j) < pivot) {
				Collections.swap(toSort, i, j);
				i++;
			}
		}
		comparisonCount= comparisonCount + end - start;
		Collections.swap(toSort, start, i - 1);
		quickSort(toSort, start, i - 2);
		quickSort(toSort, i, end);
		return toSort;
	}
	
	static int median(List<Integer> toSort, int start, int end) {
		int middle = (end + start) / 2;
		int n1 = toSort.get(start);
		int n2 = toSort.get(middle);
		int n3 = toSort.get(end);
		if ((n1 - n2)*(n3 - n1) > 0) {
			return start;
		} else if ((n2 - n1)*(n3 - n2) > 0) {
			return middle;
		} else {
			return end;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Scanner in = new Scanner(new File("Test.txt"));
		Scanner in = new Scanner(new File("QuickSortInput.txt"));
		List<Integer> toSort = new ArrayList<Integer>();
		while (in.hasNextInt()) {
			toSort.add(in.nextInt());
		}
		int end = toSort.size() - 1;
		System.out.println(quickSort(toSort, 0, end));
		System.out.println(comparisonCount);
	}
}
