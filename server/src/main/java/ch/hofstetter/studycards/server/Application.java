package ch.hofstetter.studycards.server;

import ch.hofstetter.studycards.dtos.StudyDeckDto;
import ch.hofstetter.studycards.server.entities.StudyDeck;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.print.attribute.standard.Destination;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //modelMapper.getTypeMap(StudyDeck.class, StudyDeckDto.class).addMappings(mapper -)
        //modelMapper.addMappings(mapper -> mapper.skip(Destination::setName));

        return modelMapper;
    }
}