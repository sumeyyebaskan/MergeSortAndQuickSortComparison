package sorting;

import java.util.Random;

public class SortingClass {
	int[] arr = null;
	int size;

	public SortingClass(int size) {
		this.size = size;
		this.arr = new int[size];
	}

	void load_same_number() {// loads equal integers
		for (int i = 0; i < this.size; i++) {
			arr[i] = 9;
		}
	}

	void load_increasing() {// loads increasing integers
		for (int i = 0; i < this.size; i++) {
			arr[i] = i;
		}
	}

	void load_decreasing() {// loads decreasing integers
		for (int i = 0; i < this.size; i++) {
			arr[i] = this.size - i;
		}
	}

	void load_random() {// loads random integers
		Random rand = new Random();
		for (int i = 0; i < this.size; i++) {
			this.arr[i] = rand.nextInt(this.size * 10);
		}
	}

	void mergeSort(int[] arrayToSort, String partition) {// merge sort method to call the wanted method
		if (partition.equalsIgnoreCase("ThreeParts")) {// merge sort with three parts
			int dup[] = duplicate(arrayToSort);
			merge3way(arrayToSort, 0, arrayToSort.length, dup);
		} else// merge sort with two parts
			merge2way(arrayToSort);
	}

	void merge2way(int[] inputArray) {// merge sort with two parts method
		int inputLength = inputArray.length;

		if (inputLength < 2) {// if input array has less than 2 elements it means it is sorted and nothing to
								// do at this point
			return;
		}

		int midIndex = inputLength / 2; // finds the middle
		int[] leftHalf = new int[midIndex];// takes the left part
		int[] rightHalf = new int[inputLength - midIndex];// takes the right part

		// loading the integers in their halves
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = inputArray[i];
		}
		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = inputArray[i];
		}

		// recursively calling the method to divide it into sub-arrays with one element
		// that is already sorted
		merge2way(leftHalf);
		merge2way(rightHalf);

		// merges the left half and right half
		merge(inputArray, leftHalf, rightHalf);
	}

	void merge3way(int[] arr, int low, int high, int[] dup) {// merge sort with three parts method

		if (high - low < 2)// if input array has less than 2 elements it means it is sorted and nothing to
							// do at this point
			return;

		// finds the partition points
		int m1, m2;
		m1 = low + ((high - low) / 3);
		m2 = low + 2 * ((high - low) / 3) + 1;

		// recalls the method to divide it into sub-arrays with one element
		merge3way(dup, low, m1, arr);
		merge3way(dup, m1, m2, arr);
		merge3way(dup, m2, high, arr);

		// merges the three parts
		merge(dup, low, m1, m2, high, arr);

	}

	void merge(int[] arr, int low, int m1, int m2, int high, int[] dup) {// merge method used in three parts
		int i = low, j = m1, k = m2, l = low;

		while ((i < m1) && (j < m2) && (k < high)) {// if three of them still has an element
			if (arr[i] < arr[j]) {
				if (arr[i] < arr[k])// if left part's element is the smallest one
					dup[l++] = arr[i++];
				else// if right part's element is the smallest one
					dup[l++] = arr[k++];
			} else {
				if (arr[j] < arr[k])// if middle part's element is the smallest one
					dup[l++] = arr[j++];
				else// if right part's element is the smallest one
					dup[l++] = arr[k++];
			}
		}

		while (i < m1 && j < m2) {// if left and middle parts have elements
			if (arr[i] < arr[j]) {
				dup[l++] = arr[i++];
			} else {
				dup[l++] = arr[j++];
			}
		}
		while (k < high && j < m2) {// if right and middle parts have elements
			if (arr[j] < arr[k]) {
				dup[l++] = arr[j++];
			} else {
				dup[l++] = arr[k++];
			}
		}
		while (i < m1 && k < high) {// if left and right parts have elements
			if (arr[i] < arr[k]) {
				dup[l++] = arr[i++];
			} else {
				dup[l++] = arr[k++];
			}
		}
		while (i < m1) {// if left part has elements
			dup[l++] = arr[i++];
		}
		while (j < m2) {// if middle part has elements
			dup[l++] = arr[j++];
		}
		while (k < high) {// if right part has elements
			dup[l++] = arr[k++];
		}
	}

	void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {// merge method used in two parts
		int leftSize = leftHalf.length;
		int rightSize = rightHalf.length;

		int i = 0, j = 0, k = 0;

		while (i < leftSize && j < rightSize) {// if both halves still has element
			if (leftHalf[i] <= rightHalf[j]) {// if left element is smaller it is added to the new sorted array
				inputArray[k] = leftHalf[i];
				i++;
			} else {// if right element is smaller it is added to the new sorted array
				inputArray[k] = rightHalf[j];
				j++;
			}
			k++;
		}

		while (i < leftSize) {// if right half is finished before left half it loads the remaining sorted
								// integers to the new sorted array
			inputArray[k] = leftHalf[i];
			i++;
			k++;
		}

		while (j < rightSize) {// if left half is finished before right half it loads the remaining sorted
								// integers to the new sorted array
			inputArray[k] = rightHalf[j];
			j++;
			k++;
		}

	}

	void quickSort(int[] arrayToSort, String pivotType) {// quick sort method to call the wanted method
		if (pivotType.equalsIgnoreCase("FirstElement")) {// pivot type is first element
			swap(arrayToSort, 0, arrayToSort.length - 1);// swaps the first element and the last element
			quickSort(arrayToSort, 0, arrayToSort.length - 1);
		} else if (pivotType.equalsIgnoreCase("RandomElement")) {// pivot type is random element
			int highIndex = arrayToSort.length - 1;
			int pivotIndex = new Random().nextInt(highIndex);// chooses a random pivot
			swap(arrayToSort, pivotIndex, highIndex);// swaps it with the last element
			quickSort(arrayToSort, 0, highIndex);
		} else {//pivot the is the middle of first middle and last element
			quickSortMid(arrayToSort, 0, arrayToSort.length - 1);
		}
	}

	void quickSortMid(int[] arr, int low, int high) {
		if (low >= high)// if dividing part is over
			return;

		int left = low, right = high;
		int pivot = arr[(low + high) / 2];//finds the pivot

		//parts the array into smaller integers and bigger integers
		while (left <= right) {
			while (arr[left] < pivot)
				left++;
			while (arr[right] > pivot)
				right--;
			if (left > right)
				break;
			//swaps the left wrong piece and right wrong piece
			swap(arr, left, right);
			left++;
			right--;
		}
		//recalls the method
		quickSortMid(arr, low, right);
		quickSortMid(arr, left, high);
	}

	


	void quickSort(int[] array, int lowIndex, int highIndex) {

		if (lowIndex >= highIndex)// if dividing part is over
			return;

		int pivot = array[highIndex];//sets pivot

		int pointerL = partition(array, lowIndex, highIndex, pivot);//parts the array and takes one of the pointer to use it as the partition point
		//recalls the method
		quickSort(array, lowIndex, pointerL - 1);
		quickSort(array, pointerL + 1, highIndex);

	}

	int partition(int[] array, int lowIndex, int highIndex, int pivot) {// parts into smaller and bigger parts the array
																		// according to pivot
		int pointerL = lowIndex;
		int pointerR = highIndex;

		while (pointerL < pointerR) {//finds the integers that are in the wrong half and swaps them and continues until left and right pointer meets in the middle
			while (array[pointerL] <= pivot && pointerL < pointerR) {
				pointerL++;
			}

			while (array[pointerR] >= pivot && pointerL < pointerR) {
				pointerR--;
			}
			swap(array, pointerL, pointerR);
		}
		swap(array, pointerL, highIndex);//puts the pivot in the right place

		return pointerL;//returns the pointer index
	}

	void swap(int[] array, int index1, int index2) {// swaps the index1 and index2's place
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	void check() {// checks if the array is sorted
		boolean Flag = true;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				Flag = false;
				break;
			}
		}
		System.out.println(Flag);
	}

	int[] duplicate(int[] arr) {// duplicates the array

		int duplicate[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			duplicate[i] = arr[i];
		return duplicate;
	}
}
