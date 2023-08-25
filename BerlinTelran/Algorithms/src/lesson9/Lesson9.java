package lesson9;

/**
 * Algorithms. Lesson #9. Greedy algorithm, continue
 *
 * @author Sergey Iryupin
 * @version 25 Aug 2023
 */

public class Lesson9 {
    public static void main(String[] args) {
        int[] lock = {1, 9, 1, 9}; //{2, 3, 4, 5};
        int[] code = {0, 0, 0, 0}; //{5, 4, 3, 2};
        System.out.println(codeLock(lock, code));
    }

    static int codeLock(int[] lock, int[] code) {
        int rotations = 0;
        for (int i = 0; i < lock.length; i++) {
            int a = lock[i];
            int b = code[i];
            int rotateForward = b - a > 0? b - a : b - a + 10;
            int rotateBack = a - b > 0? a - b : a - b + 10;
            System.out.println(rotateForward + " : " + rotateBack);
            rotations += Math.min(rotateForward, rotateBack);
        }
        return rotations;
    }
}
