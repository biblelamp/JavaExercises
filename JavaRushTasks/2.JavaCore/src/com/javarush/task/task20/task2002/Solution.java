package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here
            User user = new User();
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object
            System.out.println(javaRush.equals(loadedObject));
            for (User usr : loadedObject.users) {
                System.out.println(usr.getFirstName());
                System.out.println(usr.getLastName());
                System.out.println(usr.getBirthDate());
                System.out.println(usr.isMale());
                System.out.println(usr.getCountry());
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintStream writer = new PrintStream(outputStream);
            for (User user : users) {
                writer.println(user.getFirstName());
                writer.println(user.getLastName());
                writer.println(user.getBirthDate().getTime());
                writer.println(user.isMale());
                writer.println(user.getCountry());
            }
            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String firstName = reader.readLine();
                String lastName = reader.readLine();
                String birthDate = reader.readLine();
                String male = reader.readLine();
                String country = reader.readLine();
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                Date dt = new Date();
                dt.setTime(Long.parseLong(birthDate));
                user.setBirthDate(dt);
                user.setMale(male.equals("true"));
                user.setCountry(User.Country.valueOf(country));
                users.add(user);
            }
            reader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}