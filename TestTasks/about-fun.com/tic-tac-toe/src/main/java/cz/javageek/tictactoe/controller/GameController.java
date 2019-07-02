package cz.javageek.tictactoe.controller;

import cz.javageek.tictactoe.domain.Match;
import cz.javageek.tictactoe.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

            return "Your mark is 'x'. Waiting for a partner";
        } else {
            match = matchRepository.getOne(1);

            if (match.getStatus().equals("W")) {
                match.setStatus("R");
                matchRepository.save(match);

                return "Your mark is 'o'. Waiting for the action of a partner";
            }
        }

        return "Join: " + match;
    }

    @GetMapping(path="/action")
    public @ResponseBody String action() {

        return "Action";
    }
}
