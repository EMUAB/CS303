import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab02 {

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the file name of the test case (sorting txt files): ");
            String intputFileName = scanner.nextLine() + ".txt";
            if (intputFileName.equalsIgnoreCase("exit.txt")) {
                exit = true;
                continue;
            }
            ArrayList<Integer> allNums = new ArrayList<>();
            try {
                Scanner fileScan = new Scanner(new File("Test_cases_for_sorting/" + intputFileName));
                while (fileScan.hasNextInt()) {
                    allNums.add(fileScan.nextInt());
                }
                Integer[] a = allNums.toArray(new Integer[0]);
                Integer[] b = allNums.toArray(new Integer[0]);
//                Integer[] c = allNums.toArray(new Integer[0]);
//                Integer[] d = allNums.toArray(new Integer[0]);
                long ctime = System.nanoTime();
                insertionSort(a);
                ctime = System.nanoTime() - ctime;
//                for (Integer i : a) {
//                    System.out.print(i + ", ");
//                }
                System.out.println("\nInsertion sort took " + ctime + " nanoseconds.\n");
                ctime = System.nanoTime();
                mergeSort(b, 0, (b.length - 1));
                ctime = System.nanoTime() - ctime;
//                for (Integer i : b) {
//                    System.out.print(i + ", ");
//                }
                System.out.println("\nMerge sort took " + ctime + " nanoseconds.\n");
//                ctime = System.nanoTime();
//                int qInversions = quadInversions(c);
//                ctime = System.nanoTime() - ctime;
//                System.out.println(qInversions + " inversions found in " + ctime + " nanoseconds in quadratic running time.\n");
//                ctime = System.nanoTime();
//                int lInversions = loglinSort(d, 0, (d.length - 1));
//                ctime = System.nanoTime() - ctime;
//                System.out.println(lInversions + " inversions found in " + ctime + " nanoseconds in log-linear running time.\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertionSort(Integer[] a) {
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
    }

    public static int quadInversions(Integer[] a) {
        int count = 0, size = a.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int loglinSort(Integer[] a, int beginning, int end) {
        int count = 0;
        if (beginning < end) {
            int middle = (beginning + end) / 2;
            count += loglinSort(a, beginning, middle);
            count += loglinSort(a, middle + 1, end);
            count += loglin(a, beginning, middle, end);
        }
        return count;
    }

    public static int loglin(Integer[] a, int beginning, int middle, int end) {
        int count = 0;
        int left = beginning, right = middle + 1, tempArrayIndex = beginning;
        Integer[] tempArray = new Integer[end + 1];

        while (left <= middle && right <= end) {
            if (a[left] < a[right]) {
                tempArray[tempArrayIndex] = a[left];
                left++;

            } else {
                tempArray[tempArrayIndex] = a[right];
                right++;
                count += (middle + 1 - left);
            }
            tempArrayIndex++;
        }
        for (int t = left; t <= middle; t++) {
            tempArray[tempArrayIndex] = a[t];
            tempArrayIndex++;
        }
        for (int k = right; k <= end; k++) {
            tempArray[tempArrayIndex] = a[k];
            tempArrayIndex++;
        }

        for (int n = beginning; n < tempArrayIndex; n++) {
            a[n] = tempArray[n];
        }
        return count;
    }

    public static void mergeSort(Integer[] a, int beginning, int end) {
        if (beginning < end) {
            int middle = (beginning + end) / 2;
            mergeSort(a, beginning, middle);
            mergeSort(a, middle + 1, end);
            merge(a, beginning, middle, end);
        }
    }

    public static void merge(Integer[] a, int beginning, int middle, int end) {
        int left = beginning, right = middle + 1, tempArrayIndex = beginning;
        Integer[] tempArray = new Integer[end + 1];

        while (left <= middle && right <= end) {
            if (a[left] < a[right]) {
                tempArray[tempArrayIndex] = a[left];
                left++;

            } else {
                tempArray[tempArrayIndex] = a[right];
                right++;
            }
            tempArrayIndex++;
        }
        for (int t = left; t <= middle; t++) { // Runs if right has ran out
            tempArray[tempArrayIndex] = a[t];
            tempArrayIndex++;
        }
        for (int k = right; k <= end; k++) { // Runs if left has ran out
            tempArray[tempArrayIndex] = a[k];
            tempArrayIndex++;
        }

        for (int n = beginning; n < tempArrayIndex; n++) { // Copies the temp array to the original array in sorted order
            a[n] = tempArray[n];
        }
    }
}
