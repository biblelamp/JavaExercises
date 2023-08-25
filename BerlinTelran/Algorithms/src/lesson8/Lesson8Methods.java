package lesson8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lesson8Methods {

    // 1я задача: выбор задач
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

    // 2я задача: размен монет
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
        return new ArrayList<>();
    }

    // 3я задача: упаковка рюкзака
    static List<Item> fractionalKnapsack(List<Item> items, int capacity) {
        List<Item> result = new ArrayList<>();
        Collections.sort(items); // сортировка по убыванию вес/стоимость

        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.getWeight() <= capacity) {
                currentWeight = currentWeight + item.getWeight();
                result.add(item);
            } else {
                break;
            }
        }

        return result;
    }
}
