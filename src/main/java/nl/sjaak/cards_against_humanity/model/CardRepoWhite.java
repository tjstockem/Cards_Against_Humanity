package nl.sjaak.cards_against_humanity.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepoWhite extends CrudRepository<WhiteCard, Long> {

}
