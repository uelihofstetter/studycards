package ch.hofstetter.studycards.javafx.ui.scenes;

import ch.hofstetter.studycards.dtos.StudyCardDto;
import ch.hofstetter.studycards.dtos.StudyDeckDto;
import ch.hofstetter.studycards.javafx.webapi.StudyDeckRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class LearningDeckScene {


    StudyDeckRepository studyDeckRepository;

    private Scene scene;
    private TableView table = new TableView();


    //final ObservableList<StudyDeckDto> data;
    final ObservableList<StudyDeckDto> data;//=
    /*new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan", "Williams", "ethan.williams@example.com"),
    new Person("Emma", "Jones", "emma.jones@example.com"),
    new Person("Michael", "Brown", "michael.brown@example.com"));*/


    public LearningDeckScene(StudyDeckRepository studyDeckRepository) {
        data = FXCollections.observableArrayList(
                new StudyDeckDto(1L, "My Study Deck", new ArrayList<StudyCardDto>(), LocalDateTime.now()));//FXCollections.observableList(getStudyDecks());

        scene = new Scene(new Group());
        this.studyDeckRepository = studyDeckRepository;


        createTable();


    }

    public List<StudyDeckDto> getStudyDecks() {
        return new ArrayList<StudyDeckDto>();
    }


    public Scene getScene() {
        return scene;
    }


    public static <T, U, V> TableColumn createTableColumn(String header, String property, int width, BiConsumer<T, U> propertySetter, Function<U, V> typeConverter) {
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell(typeConverter);
                    }
                };
        TableColumn tableColumn = new TableColumn(header);
        tableColumn.setMinWidth(width);

        tableColumn.setCellValueFactory(
                new PropertyValueFactory<T, U>(property));

        tableColumn.setCellFactory(cellFactory);

        tableColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<T, U>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<T, U> t) {
                        T object = t.getTableView().getItems().get(
                                t.getTablePosition().getRow());

                        System.out.println(t.getNewValue().getClass());
                        System.out.println(object);

                        propertySetter.accept(object, t.getNewValue());
                    }
                }
        );
        return tableColumn;
    }


    private void createTable() {
        HBox hb = new HBox();

        final Label label = new Label("Study Decks");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);


        TableColumn deckNameColumn = createTableColumn("Deck Name", "name", 200, (StudyDeckDto d, String name) -> {
            d.setName(name);
        }, (String str) -> {
            return str;
        });
        TableColumn lastStudiedColumn = createTableColumn("Last Studied", "lastStudied", 200, (StudyDeckDto d, String date) -> {
        }, (String str) -> {
            return str;
        });
        TableColumn numberOfCardsColumn = createTableColumn("Number of Cards", "numberOfCards", 200, (StudyDeckDto d, String numberOfCards) -> {

        }, (String str) -> {
            return Integer.valueOf(str);
        });


        table.setItems(data);
        table.getColumns().addAll(deckNameColumn, lastStudiedColumn, numberOfCardsColumn);

        /*final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        */

        final TextField addDeckName = new TextField();
        addDeckName.setPromptText("Deck Name");
        addDeckName.setMaxWidth(deckNameColumn.getPrefWidth());


        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new StudyDeckDto(0L,
                        addDeckName.getText(),
                        new ArrayList<StudyCardDto>(), null));
                addDeckName.clear();

            }
        });

        hb.getChildren().addAll(addDeckName, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
    }


    static class EditingCell<T, V> extends TableCell<T, V> {

        private TextField textField;
        private Function<T, V> typeConverter;

        public EditingCell(Function<T, V> typeConverter) {
            this.typeConverter = typeConverter;
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            if (getItem() != null) {
                setText(getItem().toString());
            }
            setGraphic(null);
        }

        @Override
        public void updateItem(V item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString().toString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString() != null ? getString().toString() : "");
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString().toString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                                    Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(typeConverter.apply((T) textField.getText()));
                    }
                }
            });
        }

        private V getString() {
            return getItem() == null ? null : getItem();
        }
    }


}
