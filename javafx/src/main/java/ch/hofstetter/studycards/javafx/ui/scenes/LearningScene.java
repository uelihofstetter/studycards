package ch.hofstetter.studycards.javafx.ui.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


public class LearningScene  {

    private Scene scene;


    public LearningScene(){
        Button button1 = new Button("Button Number 1");
        Button button2 = new Button("Button Number 2");

        HBox hbox = new HBox(button1, button2);
        scene = new Scene(hbox, 200, 100);

    }

    public Scene getScene(){
        return scene;
    }





}
