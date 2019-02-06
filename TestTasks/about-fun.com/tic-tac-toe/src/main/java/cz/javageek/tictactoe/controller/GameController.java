package cz.javageek.tictactoe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @GetMapping(path="/JoinBattle")
    public @ResponseBody String joinBattle() {

        return "JoinBattle";
    }

    @GetMapping(path="/PlaceMarker")
    public @ResponseBody String placeMarker() {

        return "PlaceMarker";
    }
}
