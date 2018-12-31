package ch.hofstetter.studycards.server.controllers;


import ch.hofstetter.studycards.dtos.StudyCardDto;
import ch.hofstetter.studycards.server.entities.StudyCard;
import ch.hofstetter.studycards.server.services.StudyCardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudyCardController {

    @Autowired
    private ModelMapper modelMapper;

    private StudyCardService studyCardService;

    public StudyCardController(StudyCardService studyCardService) {
        this.studyCardService = studyCardService;
    }

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello";
    }

    @RequestMapping(value = "/studycards", method = RequestMethod.GET)
    public List<StudyCardDto> getStudyCards() {
        return studyCardService.getAllStudyCards().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/studycards", method = RequestMethod.POST)
    public StudyCardDto postStudyCard(@RequestBody StudyCardDto studyCardDto) {
        return convertToDto(studyCardService.createStudyCard(studyCardDto));
    }

    @RequestMapping(value = "/ch/hofstetter/studycards/server", method = RequestMethod.PUT)
    public StudyCardDto putStudyCard(@RequestBody StudyCardDto studyCardDto) {
        return convertToDto(studyCardService.updateStudyCard(studyCardDto));
    }


    private StudyCardDto convertToDto(StudyCard studyCard) {
        return modelMapper.map(studyCard, StudyCardDto.class);
    }


}
