package ch.hofstetter.studycards.server.services;

import ch.hofstetter.studycards.dtos.StudyDeckDto;
import ch.hofstetter.studycards.server.entities.StudyDeck;
import ch.hofstetter.studycards.server.repositories.StudyDeckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudyDeckService {

    private StudyDeckRepository studyDeckRepository;

    public StudyDeckService(StudyDeckRepository studyDeckRepository){
        this.studyDeckRepository = studyDeckRepository;
    }

    public StudyDeck getStudyDeck(Long studyDeckId) {
        return studyDeckRepository.findById(studyDeckId).get();
    }

    public List<StudyDeck> getAllStudyDecks() {
        return studyDeckRepository.findAll();
    }

    public StudyDeck updateStudyDeck(StudyDeckDto studyDeckDto) {
        return null;
    }

    public StudyDeck createStudyDeck(StudyDeck studyDeck) {
        return studyDeckRepository.save(studyDeck);
    }
}
