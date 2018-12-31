package ch.hofstetter.studycards.javafx.webapi;

import ch.hofstetter.studycards.dtos.StudyCardDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudyDeckRepository {

    private RestTemplate restTemplate;

    public StudyDeckRepository(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public StudyCardDto createStudyCard(StudyCardDto studyCardDto){
        return restTemplate.exchange("http://localhost:8080/studycards", HttpMethod.POST, new HttpEntity(studyCardDto), StudyCardDto.class).getBody();
    }


}
