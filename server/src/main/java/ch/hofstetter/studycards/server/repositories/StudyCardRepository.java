package ch.hofstetter.studycards.server.repositories;

import ch.hofstetter.studycards.server.entities.StudyCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudyCardRepository extends JpaRepository<StudyCard, Long> {
 }