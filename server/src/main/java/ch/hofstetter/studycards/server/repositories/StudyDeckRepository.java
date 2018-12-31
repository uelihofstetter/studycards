package ch.hofstetter.studycards.server.repositories;

import ch.hofstetter.studycards.server.entities.StudyCard;
import ch.hofstetter.studycards.server.entities.StudyDeck;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudyDeckRepository extends JpaRepository<StudyDeck, Long> {
 }