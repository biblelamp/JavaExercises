package cz.javageek.tictactoe.repository;

import cz.javageek.tictactoe.domain.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer> {
    Match getOne(Integer id);
}
