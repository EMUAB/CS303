import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab04 {
    public static int[] files = {200, 400, 800, 1600, 3200, 6400, 12800, 25600};
    public static void main(String[] args) {
        int median;
        for (int i = 0; i < files.length; i++) {
            ArrayList<Integer> allNums = new ArrayList<>();
            try {
                Scanner fileScan = new Scanner(new File("input_data/" + files[i] + ".txt"));
                while (fileScan.hasNextInt()) {
                    allNums.add(fileScan.nextInt());
                }
                Integer[] a = allNums.toArray(new Integer[0]), b = a.clone(), c = a.clone(), d = a.clone();
                long ctime = System.nanoTime();
                median = rSelect(a, 0, a.length - 1, ((int) ((a.length) / 2.0)));
                ctime = System.nanoTime() - ctime;
                System.out.println("RSelect found median of " + median + " in " + files[i] + " items in " + ctime + " nanoseconds.");
                ctime = System.nanoTime();
                median = quicksort(b, 0, b.length - 1, ((int) ((b.length) / 2.0)));
                ctime = System.nanoTime() - ctime;
                System.out.println("Quicksort found median of " + median + " in " + files[i] + " items in " + ctime + " nanoseconds.");
                ctime = System.nanoTime();
                median = insertionSort(c, ((int) ((c.length) / 2.0)));
                ctime = System.nanoTime() - ctime;
                System.out.println("InsertionSort found median of " + median + " in " + files[i] + " items in " + ctime + " nanoseconds.");
                System.out.println(Lab4Bonus.checkHeap(d));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static int rSelect(Integer[] a, int lo, int hi, int i) {
        i++;
        if (hi == lo) {
            return a[lo];
        }
        int q = new Random().nextInt(lo, hi + 1);
        q = rPartition(a, lo, hi, q);
        int k = q - lo + 1;
        if (i == k) {
            return a[q];
        } else if (i < k) {
            return rSelect(a, lo, q - 1, i-1);
        } else {
            return rSelect(a, q + 1, hi, (i-1) - k);
        }
    }

    public static int rPartition(Integer[] a, int lo, int hi, int pivot) {
        swapElements(a, pivot, hi);
        int pivValue = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (a[j] < pivValue) {
                i++;
                swapElements(a, i, j);
            }
        }
        swapElements(a, i + 1, hi);
        return i + 1;
    }

    public static int quicksort(Integer[] a, int pos1, int pos2, int median) {
        if (pos1 < pos2) {
            int q = quickPartition(a, pos1, pos2);
            quicksort(a, pos1, q - 1, median);
            quicksort(a, q + 1, pos2, median);
        }
        return a[median];
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

    private static int insertionSort(Integer[] a, int median) {
        int size = a.length;
        for (int i = 1; i < size; i++) {
            int lookingAt = a[i];
            int comparingTo = i - 1;
            while (comparingTo >= 0 && a[comparingTo] > lookingAt) {
                a[comparingTo + 1] = a[comparingTo];
                comparingTo--;
            }
            a[comparingTo + 1] = lookingAt;
        }
        return a[median];
    }

    private static void swapElements(Integer[] a, int el1, int el2) {
        int temp = a[el1];
        a[el1] = a[el2];
        a[el2] = temp;
    }

    private static String maxMinHeap(Integer[] a) {
        int size = a.length, prev = 0;
        boolean max = true, min = true;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                prev = a[i];
            } else if (a[i] < prev) {
                prev = a[i];
            } else {
                max = false;
            }
        }
        if (!max) {
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    prev = a[i];
                } else if (a[i] > prev) {
                    prev = a[i];
                } else {
                    min = false;
                }
            }
        }
        return max ? "It is max heap" : (min ? "It is min heap" : "It is neither max or min heap");
    }
}
