package lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Algorithms. Lesson #8. Greedy algorithm
 *
 * @author Sergey Iryupin
 * @version 17 Aug 2023
 */

public class Lesson8 {
    public static void main(String[] args) {
        // 1 задача - выбор задач
        List<Integer> tasks = Arrays.asList(3, 2, 1, 4, 5, 3);
        int maxTime = 10;
        List<Integer> resolved = taskSelection(tasks, maxTime);
        System.out.println(resolved);

        // 2 задача - размен монет
        List<Integer> coins = Arrays.asList(1, 5, 10, 25);
        int amount = 63;
        System.out.println(coinChange(coins, amount));
    }

    static List<Integer> taskSelection(List<Integer> tasks, int maxTime) {
        List<Integer> result = new ArrayList<>();
        int resultTime = 0;

        LinkedList<Integer> sortedTasks = new LinkedList<>(tasks);
        Collections.sort(sortedTasks);
        while (resultTime < maxTime && maxTime - resultTime >= sortedTasks.peek()) {
            int taskTime = sortedTasks.pop();
            result.add(taskTime);
            resultTime = resultTime + taskTime;
        }
        return result;
    }

    static List<Integer> coinChange(List<Integer> coins, int amount) {
        List<Integer> result = new ArrayList<>();

        Collections.sort(coins);
        for (int i = coins.size() - 1; i >= 0; i--) {
            while (amount >= coins.get(i)) {
                amount = amount - coins.get(i);
                result.add(coins.get(i));
            }
        }

        if (amount == 0) {
            return result;
        }
        return Collections.EMPTY_LIST;
    }
}
