taskKey="com.javarush.task.task17.task1715"

Аптека

Реализуй интерфейс Runnable в классах Apteka и Person.
Все нити должны работать пока не isStopped.
Логика для Apteka: drugsController должен сделать закупку случайного лекарства (getRandomDrug) в количестве (getRandomCount) и подождать 300 мс.
Логика для Person: drugsController должен сделать продажу случайного лекарства (getRandomDrug) в количестве (getRandomCount) и подождать 100 мс.
Расставь synchronized там, где это необходимо.


Требования:
1.	Класс Solution должен содержать public static поле drugsController типа DrugsController.
2.	Класс Solution должен содержать public static поле isStopped типа boolean.
3.	Класс Solution должен содержать private static void метод waitAMoment(), который должен ждать 100 мс.
4.	Класс Apteka должен реализовывать интерфейс Runnable.
5.	Нить Apteka должна работать пока isStopped = false.
6.	Нить Apteka должна использовать drugsController для закупки случайного лекарства (getRandomDrug) в количестве (getRandomCount).
7.	Нить Apteka должна ждать 300мс + "между закупками", используя метод waitAMoment().
8.	Класс Person должен реализовывать интерфейс Runnable.
9.	Нить Person должна работать пока isStopped = false.
10.	Нить Person должна использовать drugsController для продажи случайного лекарства (getRandomDrug) в количестве (getRandomCount).
11.	Нить Person должна ждать 100мс + "между закупками", используя метод waitAMoment().
12.	Методы класса DrugsController должны быть synchronized.


