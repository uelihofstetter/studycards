package ch.hofstetter.studycards.server.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class StudyDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Set<StudyCard> getStudyCards() {
        return studyCards;
    }

    public void setStudyCards(Set<StudyCard> studyCards) {
        this.studyCards = studyCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    Set<StudyCard> studyCards;

    private String name;

    private StudyDeck(){}

    public StudyDeck(String name){
        this.name = name;
        this.studyCards = new LinkedHashSet<>();
    }





}
