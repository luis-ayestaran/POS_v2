package com.masterdev.student.middle;

public class SortingMethods {
	
	public static float[] quickSort(float[] array, int[] indexes, int left, int right) {
		int i = left;
		int j = right;
		
		float pivot = array[i];
		while(i < j) {
			while(array[i] < pivot) {
				i++;
			}
			while(array[j] > pivot) {
				j--;
			}
			if(i <= j) {
				float x = array[j];
				array[j] = array[i];
				array[i] = x;
				indexes[i] = j;
				indexes[j] = i;
				
				i++;
				j--;
			}
		}
		if(left < j) {
			array = quickSort(array, indexes, left, j);
		}
		if(right > i) {
			array = quickSort(array, indexes, i, right);
		}
		
	    return array;  
	}
	
	public static int[] selectionSort(float[] array, int[] indexes) {
		int[] sortedIndexes = new int[indexes.length];
		int greatestPos;
		float greatest;
		for(int i=0; i<array.length-1; i++) {
			greatest = array[i];
			greatestPos = i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j] > greatest) {
					greatest = array[j];
					greatestPos = j;
				}
			}
			array[greatestPos] = array[i];
			array[i] = greatest;
			int temp = indexes[greatestPos];
			indexes[greatestPos] = indexes[i];
			indexes[i] = temp;
		}
		sortedIndexes = indexes;
		return sortedIndexes;
	}
}
