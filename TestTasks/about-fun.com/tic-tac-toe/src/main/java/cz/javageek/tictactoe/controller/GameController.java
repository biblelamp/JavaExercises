package cz.javageek.tictactoe.controller;

// @see https://www.baeldung.com/spring-new-requestmapping-shortcuts

import cz.javageek.tictactoe.domain.Match;
import cz.javageek.tictactoe.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    MatchRepository matchRepository;

    @GetMapping(path="/join")
    public @ResponseBody String joinBattle() {

        Match match = new Match();

        if (matchRepository.count() == 0) {
            match.setField(".........");
            match.setNext("x");
            match.setStatus("W");
            matchRepository.save(match);

            return "Your mark is 'x'. Waiting for a partner...";
        } else {
            match = matchRepository.getOne(1);

            if (match.getStatus().equals("W")) {
                match.setStatus("R");
                matchRepository.save(match);

                return "Your mark is 'o'. Waiting for the action of a partner...";
            }
        }

        return "Join: " + match;
    }

    @GetMapping(path="/action/{id}")
    public @ResponseBody String action(@PathVariable String id) {

        return "Action: id=" + id;
    }

    @GetMapping(path="/")
    public @ResponseBody String status() {

        return "Status: " + matchRepository.getOne(1);
    }
}
