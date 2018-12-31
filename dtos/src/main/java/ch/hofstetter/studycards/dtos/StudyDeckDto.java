package ch.hofstetter.studycards.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class StudyDeckDto {


    private Long id;

    private String name;

    public LocalDateTime getLastStudied() {
        return lastStudied;
    }

    public void setLastStudied(LocalDateTime lastStudied) {
        this.lastStudied = lastStudied;
    }

    private LocalDateTime lastStudied;

    public List<StudyCardDto> getStudyCardDtos() {
        return studyCardDtos;
    }

    public void setStudyCardDtos(List<StudyCardDto> studyCardDtos) {
        this.studyCardDtos = studyCardDtos;
    }

    private List<StudyCardDto> studyCardDtos;


    public StudyDeckDto() {
    }

    public StudyDeckDto(Long id, String name, List<StudyCardDto> studyCardDtos, LocalDateTime lastStudied) {
        this.id = id;
        this.name = name;
        this.studyCardDtos = studyCardDtos;
        this.lastStudied = lastStudied;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCards() {
        return studyCardDtos.size();
    }


}
