package ch.hofstetter.studycards.server.controllers;


import ch.hofstetter.studycards.dtos.StudyCardDto;
import ch.hofstetter.studycards.dtos.StudyDeckDto;
import ch.hofstetter.studycards.server.entities.StudyDeck;
import ch.hofstetter.studycards.server.services.StudyCardService;
import ch.hofstetter.studycards.server.services.StudyDeckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudyDeckController {

    @Autowired
    private ModelMapper modelMapper;

    private StudyDeckService studyDeckService;

    public StudyDeckController(StudyCardService studyDeckService) {
        this.studyDeckService = studyDeckService;
    }

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello";
    }

    @RequestMapping(value = "/studydecks", method = RequestMethod.GET)
    public List<StudyDeckDto> getStudyCards() {
        return studyDeckService.getAllStudyDecks().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/studydecks", method = RequestMethod.POST)
    public StudyCardDto postStudyDeck(@RequestBody StudyDeckDto studyDeckDto) {
        return convertToDto(studyDeckService.createStudyDeck(studyDeckDto));
    }

    @RequestMapping(value = "/studydecks", method = RequestMethod.PUT)
    public StudyDeckDto putStudyDeck(@RequestBody StudyDeckDto studyDeckDto) {
        return convertToDto(studyDeckService.updateStudyDeck(studyDeckDto));
    }


    private StudyDeckDto convertToDto(StudyDeck studyDeck) {
        return modelMapper.map(studyDeck, StudyDeckDto.class);
    }
    private StudyDeckDto convertToEntity(StudyDeckDto studyDeckDto) {
        return modelMapper.map(studyDeckDto, StudyDeck.class);
    }


}
