package nl.sjaak.cards_against_humanity.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepoBlack extends CrudRepository<BlackCard, Long> {

}
