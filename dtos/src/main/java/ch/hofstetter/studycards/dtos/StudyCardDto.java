package ch.hofstetter.studycards.dtos;

public class StudyCardDto {


    private Long id;



    private Long studyDeckId;
    private String front;
    private String back;

    private StudyCardDto() {
    }


    public StudyCardDto(Long id, Long studyDeckId,  String front, String back) {
        this.id = id;
        this.studyDeckId = studyDeckId;
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

    public Long getStudyDeckId() {
        return studyDeckId;
    }

    public void setStudyDeckId(Long studyDeckId) {
        this.studyDeckId = studyDeckId;
    }

}
