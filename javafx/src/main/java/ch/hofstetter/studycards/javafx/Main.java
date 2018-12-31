package ch.hofstetter.studycards.javafx;

import ch.hofstetter.studycards.javafx.ui.scenes.LearningDeckScene;
import ch.hofstetter.studycards.javafx.ui.scenes.LearningScene;
import ch.hofstetter.studycards.javafx.webapi.StudyCardRepository;
import ch.hofstetter.studycards.javafx.webapi.StudyDeckRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main extends Application {

    ApplicationContext springContext;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public LearningScene learningScene() {
        return new LearningScene();
    }

    @Bean
    public LearningDeckScene learningDeckScene(StudyDeckRepository studyDeckRepository) {
        return new LearningDeckScene(studyDeckRepository);
    }

    @Bean
    public StudyCardRepository studyCardRepository(RestTemplate restTemplate) {
        return new StudyCardRepository(restTemplate);
    }

    @Bean
    public StudyDeckRepository studyDeckRepository(RestTemplate restTemplate) {
        return new StudyDeckRepository(restTemplate);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        LearningDeckScene learningDeckScene = springContext.getBean(LearningDeckScene.class);
        primaryStage.setScene(learningDeckScene.getScene());
        primaryStage.show();
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(Main.class);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
