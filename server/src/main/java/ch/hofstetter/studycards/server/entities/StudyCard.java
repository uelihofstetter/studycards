package ch.hofstetter.studycards.server.entities;

import javax.persistence.*;

@Entity
public class StudyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String front;
    private String back;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyDeckId")
    private StudyDeck studyDeck;

    private StudyCard() {
    }

    public StudyCard(StudyDeck studyDeck, String front, String back) {
        this.studyDeck = studyDeck;
        this.front = front;
        this.back = back;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }


}
