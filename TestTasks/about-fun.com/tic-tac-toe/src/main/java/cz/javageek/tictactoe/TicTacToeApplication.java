package cz.javageek.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Useful links:
 * http://www.springboottutorial.com/spring-boot-and-h2-in-memory-database
 * https://o7planning.org/ru/11893/integrating-spring-boot-jpa-and-h2-database
 */

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }

}

