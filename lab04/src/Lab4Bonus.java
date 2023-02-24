public class Lab4Bonus {
    public static final int IS_MM = 0, NOT_MM = 1, NO_CHILD = 2;

    public static String checkHeap(Integer[] a) {
        if (mainMaxHeap(a, 0) == IS_MM) {
            return "Is max heap";
        } else if (mainMinHeap(a, 0) == IS_MM) {
            return "Is min heap";
        } else {
            return "Is neither";
        }
    }

    private static int mainMaxHeap(Integer[] a, int i) {
        int leftResult = 0, rightResult = 0;
        int l = (2 * i)+1, r = (2 * i)+2, largest = 0;
        if (l < a.length) {
            if (a[l] < a[i]) {
                if (r < a.length) {
                    leftResult = mainMaxHeap(a, l);
                    if (a[r] < a[i]) {
                        rightResult = mainMaxHeap(a, r);
                    } else {
                        return NOT_MM;
                    }
                } else {
                    leftResult = mainMaxHeap(a, l);
                }
            } else {
                return NOT_MM;
            }
        } else {
            return NO_CHILD;
        }
        if (leftResult != NOT_MM && rightResult != NOT_MM) {
            return IS_MM;
        } else {
            return NOT_MM;
        }
    }

    private static int mainMinHeap(Integer[] a, int i) {
        int leftResult = 0, rightResult = 0;
        int l = (2 * i)+1, r = (2 * i)+2, largest = 0;
        if (l < a.length) {
            if (a[l] > a[i]) {
                if (r < a.length) {
                    leftResult = mainMinHeap(a, l);
                    if (a[r] > a[i]) {
                        rightResult = mainMinHeap(a, r);
                    } else {
                        return NOT_MM;
                    }
                } else {
                    leftResult = mainMinHeap(a, l);
                }
            } else {
                return NOT_MM;
            }
        } else {
            return NO_CHILD;
        }
        if (leftResult != NOT_MM && rightResult != NOT_MM) {
            return IS_MM;
        } else {
            return NOT_MM;
        }
    }
}
