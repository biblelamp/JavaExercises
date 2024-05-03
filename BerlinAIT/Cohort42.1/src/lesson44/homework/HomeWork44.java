package lesson44.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #44
 *
 * @author Sergey Iryupin
 * @version 03-May-24
 */
public class HomeWork44 {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> userMoneyMap = new HashMap<>();

        // read file and accumulate money by users
        List<String> lines = Files.readAllLines(Paths.get("src\\lesson44\\homework\\money_in.txt"));
        for (String str : lines) {
            String[] fields = str.split(":");
            String userName = fields[0];
            Integer money = Integer.valueOf(fields[1]);
            Integer sum = userMoneyMap.get(userName);
            if (sum == null) {
                userMoneyMap.put(userName, 0);
                sum = 0;
            }
            userMoneyMap.put(userName, sum + money);
        }

        // write to files by conditions:
        // if money < 2_000 -> less.txt else -> more.txt
        try (FileWriter less = new FileWriter("src\\lesson44\\homework\\less.txt");
             FileWriter more = new FileWriter("src\\lesson44\\homework\\more.txt")) {
            for (String userName : userMoneyMap.keySet()) {
                Integer userMoney = userMoneyMap.get(userName);
                if (userMoney < 2_000) {
                    less.write(userName + ":" + String.valueOf(userMoney) + "\n");
                } else {
                    more.write(userName + ":" + String.valueOf(userMoney) + "\n");
                }
            }
        }
    }
}
