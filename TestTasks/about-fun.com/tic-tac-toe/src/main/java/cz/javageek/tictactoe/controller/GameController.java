package cz.javageek.tictactoe.controller;

// @see https://www.baeldung.com/spring-new-requestmapping-shortcuts

import cz.javageek.tictactoe.domain.Match;
import cz.javageek.tictactoe.domain.MatchNext;
import cz.javageek.tictactoe.domain.MatchStatus;
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
        Match match;

        if (matchRepository.count() == 0) {
            match = new Match();
            matchRepository.save(match);
        } else {
            match = matchRepository.getOne(1);
        }

        if (match.getStatus() == null) {
            match.setField(".........");
            match.setStatus(MatchStatus.WAIT);
            matchRepository.save(match);

            return "Your mark is 'x'. Waiting for a partner...";
        } else if (match.getStatus() == MatchStatus.WAIT) {
            match.setStatus(MatchStatus.READY);
            match.setNext(MatchNext.X_DOT);
            matchRepository.save(match);

            return "Your mark is 'o'. Waiting for the action of a partner...";
        }

        return "Waiting for action: " + match;
    }

    @GetMapping(path="/action/{id}")
    public @ResponseBody String action(@PathVariable String id) {

        return "Action: id=" + id;
    }

    @GetMapping(path="/")
    public @ResponseBody String status() {

        if (matchRepository.count() == 0) {
            Match match = new Match();
            matchRepository.save(match);
        }

        return "Status: " + matchRepository.getOne(1);
    }
}
