package sorting;

import java.util.Scanner;

public class Main {

	static void printArray(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}

	public static void main(String[] args) {
		SortingClass array = null;
		String number = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome! Please specify the size of the array:\n1- 1000\t\t2- 10000\t3- 100000");
		int size = scan.nextInt();
		if (size == 1)
			array = new SortingClass(1000);
		else if (size == 2)
			array = new SortingClass(10000);
		else if (size == 3)
			array = new SortingClass(100000);
		System.out.println(
				"How would you like to load the array?"
				+ "\n1- Equal Integers\t2- Random Integers\t3- Increasing Integers\t4- Decreasing Integers");
		int load = scan.nextInt();
		if (load == 1) {
			array.load_same_number();
			number = "Equal";
		} else if (load == 2) {
			array.load_random();
			number = "Random";

		} else if (load == 3) {
			array.load_increasing();
			number = "Increasing";

		} else if (load == 4) {
			array.load_decreasing();
			number = "Decreasing";
		}
		System.out.println("Which technique do you want to use?"
				+ "\n1- Merge Sort\t2 -Quick Sort");
		int choice = scan.nextInt();
		if (choice == 1) {
			System.out.println(
					"We have two different merge algorithms. Which one would you like to use?"
					+ "\n1- Two Parts\t2- Three Parts");
			int part = scan.nextInt();
			if (part == 1) {
				long startTime = System.currentTimeMillis();
				array.mergeSort(array.arr, "TwoParts");
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Array size: "+ array.size+ "\nInteger Type: "+ number + 
						"\nUsed algorithm: Merge Sort with Two Parts \nThe time elapsed for this process is: " + estimatedTime + " ms");
			} else if (part == 2) {
				long startTime = System.currentTimeMillis();
				array.mergeSort(array.arr, "ThreeParts");
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Array size: "+ array.size+ "\nInteger Type: "+ number + 
						"\nUsed algorithm: Merge Sort with Three Parts \nThe time elapsed for this process is: " + estimatedTime + " ms");
			}

		} else if (choice == 2) {
			System.out.println(
					"We have three different quick sort algorithms. Which one would you like to use as pivot?"
					+ "\n1- First Element\t2- Random Element\t3- Middle of First Middle and Last Element");
			int pivot = scan.nextInt();
			if (pivot == 1) {
				long startTime = System.currentTimeMillis();
				array.quickSort(array.arr, "FirstElement");
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Array size: "+ array.size+ "\nInteger Type: "+ number + 
						"\nUsed algorithm: Quick Sort with First Element as pivot \nThe time elapsed for this process is: " + estimatedTime + " ms");
			} else if (pivot == 2) {
				long startTime = System.currentTimeMillis();
				array.quickSort(array.arr, "RandomElement");
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Array size: "+ array.size+ "\nInteger Type: "+ number + 
						"\nUsed algorithm: Quick Sort with Random Element as pivot \nThe time elapsed for this process is: " + estimatedTime + " ms");
			} else if (pivot == 3) {
				long startTime = System.currentTimeMillis();
				array.quickSort(array.arr, "MiddleOfFirstMiddleAndLastElement");
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Array size: "+ array.size+ "\nInteger Type: "+ number + 
						"\nUsed algorithm: Quick Sort with Middle of First Middle and Last Element as pivot \nThe time elapsed for this process is: " + estimatedTime + " ms");
			}

		}
		scan.close();
	}

}
