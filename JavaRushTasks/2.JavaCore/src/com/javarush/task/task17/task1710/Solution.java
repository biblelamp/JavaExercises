package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  // сегодня родился id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  // сегодня родился id=1
    }

    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat dateFormatPrt = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        // add/create
        if (args[0].equals("-c")) {
            Person person = args[2].equals("м") ?
                            Person.createMale(args[1], dateFormat.parse(args[3])) :
                            Person.createFemale(args[1], dateFormat.parse(args[3]));
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }

        // update
        if (args[0].equals("-u")) {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(args[2]);
            person.setBirthDay(dateFormat.parse(args[4]));
            person.setSex(args[3].equals("м")? Sex.MALE : Sex.FEMALE);
        }

        // delete
        if (args[0].equals("-d")) {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }

        // show info
        if (args[0].equals("-i")) {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            System.out.println(
                person.getName() + " " +
                (person.getSex() == Sex.MALE ? "м" : "ж") + " " +
                dateFormatPrt.format(person.getBirthDay())
            );
        }
    }
}