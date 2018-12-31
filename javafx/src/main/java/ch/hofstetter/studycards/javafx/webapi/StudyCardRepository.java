package ch.hofstetter.studycards.javafx.webapi;

import ch.hofstetter.studycards.dtos.StudyCardDto;
import ch.hofstetter.studycards.dtos.StudyDeckDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudyCardRepository {

    private RestTemplate restTemplate;

    public StudyCardRepository(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public StudyDeckDto createStudyDeck(StudyDeckDto studyDeckDto){
        return restTemplate.exchange("http://localhost:8080/studydecks", HttpMethod.POST, new HttpEntity(studyDeckDto), StudyDeckDto.class).getBody();
    }

    public List<StudyDeckDto> getAllStudyDecks(){
        return restTemplate.getForEntity("http://localhost:8080/studydecks",  List.class).getBody();
    }


}
