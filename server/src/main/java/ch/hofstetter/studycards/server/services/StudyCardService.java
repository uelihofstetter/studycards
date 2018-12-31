package ch.hofstetter.studycards.server.services;


 import ch.hofstetter.studycards.dtos.StudyCardDto;
 import ch.hofstetter.studycards.server.entities.StudyCard;
 import ch.hofstetter.studycards.server.entities.StudyDeck;
 import ch.hofstetter.studycards.server.repositories.StudyCardRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudyCardService {

    private StudyCardRepository studyCardRepository;
    private StudyDeckService studyDeckService;

    public StudyCardService(StudyCardRepository studyCardRepository) {
        this.studyCardRepository = studyCardRepository;
    }

    public StudyCard createStudyCard(StudyCardDto studyCardDto) {
        StudyDeck studyDeck = studyDeckService.getStudyDeck(studyCardDto.getStudyDeckId());
        StudyCard studyCard = studyCardRepository.save(new StudyCard(studyDeck, studyCardDto.getFront(), studyCardDto.getBack()));
        studyDeck.getStudyCards().add(studyCard);
        return studyCard;
    }

    public List<StudyCard> getAllStudyCards() {
        return studyCardRepository.findAll();
    }

    public StudyCard updateStudyCard(StudyCardDto studyCardDto) {
        StudyCard studyCard = studyCardRepository.findById(studyCardDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Studycard with id {} could not be found"));
        studyCard.setFront(studyCardDto.getFront());
        studyCard.setBack(studyCardDto.getBack());
        return studyCard;
    }
}
