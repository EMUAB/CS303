import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab03 {
    private static int[] files = {10, 20, 30, 40, 50, 100, 1000, 5000, 50000, 100000, 500000};

    public static void main(String[] args) {
        boolean exit = false;
        int count = -1;
        while (!exit) {
            if (count == 9) {
                exit = true;
            }
            count++;
            ArrayList<Integer> allNums = new ArrayList<>();
            try {
                Scanner fileScan = new Scanner(new File("Test_cases_for_sorting/" + files[count] + ".txt"));
                while (fileScan.hasNextInt()) {
                    allNums.add(fileScan.nextInt());
                }
                Integer[] a = allNums.toArray(new Integer[0]);
                long ctime = System.nanoTime();
                quicksort(a, 0, a.length - 1);
                ctime = System.nanoTime() - ctime;
                System.out.println("Insertion sort for " + files[count] + " items took " + ctime + " nanoseconds.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            List<Integer> points = new ArrayList<>(2);
            points.add(0);
        }
    }

    public static void quicksort(Integer[] a, int pos1, int pos2) {
        if (pos1 < pos2) {
            int q = quickPartition(a, pos1, pos2);
            quicksort(a, pos1, q - 1);
            quicksort(a, q + 1, pos2);
        }
    }

    private static int quickPartition(Integer[] a, int pos1, int pos2) {
        int i = pos1 + 1, j = pos2, pivot = a[pos1];
        boolean exit = false;
        while (!exit) {
            while ((a[i] <= pivot) && (i < j)) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i < j) {
                swapElements(a, i, j);
            } else {
                exit = true;
            }
        }
        swapElements(a, pos1, j);
        return j;
    }

    private static void swapElements(Integer[] a, int el1, int el2) {
        int temp = a[el1];
        a[el1] = a[el2];
        a[el2] = temp;
    }

}
