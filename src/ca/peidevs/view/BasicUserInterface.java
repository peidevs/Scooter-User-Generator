package ca.peidevs.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class BasicUserInterface extends Application{

    private final String dateFormatpattern = "yyyy-MM-dd";

    private StringConverter dateFormatConverter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormatpattern);
        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Meetup User Generator For Scooter");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setHgap(5);
        grid.setVgap(10);

        Text meetupGroupNameLabel = new Text("Group Name");
        TextField meetupGroupName = new TextField();
        grid.add(meetupGroupNameLabel, 0, 0);
        grid.add(meetupGroupName, 1, 0);

        Text meetupDateLabel = new Text("Meetup Date");
        DatePicker meetupDate = new DatePicker(LocalDate.now());
        meetupDate.setPromptText(dateFormatpattern);
        meetupDate.setConverter(dateFormatConverter);
        grid.add(meetupDateLabel, 2, 0);
        grid.add(meetupDate, 3, 0);

        Text apiKeyLabel = new Text("API Key");
        TextField apiKey = new TextField();
        grid.add(apiKeyLabel, 0, 1);
        grid.add(apiKey, 1, 1, 2, 1);

        Button getMeetingBtn = new Button("Get Attendees");
        grid.add( getMeetingBtn, 3, 1);

        Separator hr = new Separator();
        grid.add( hr, 0, 2, 4, 1);

        Text eventNameLabel = new Text("Event Name: ");
        Text eventName = new Text("");
        grid.add( eventNameLabel, 0, 3);
        grid.add( eventName, 1, 3);

        Text eventLocationLabel = new Text("Location: ");
        Text eventLocation = new Text("");
        grid.add( eventLocationLabel, 0, 4);
        grid.add( eventLocation, 1, 4);

        TabPane tabPane = buildTabPane();
        grid.add( tabPane,0,5,4,25);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    private TabPane buildTabPane() {
        TabPane tabPane = new TabPane();

        Tab dojoTab = new Tab();
        dojoTab.setText("Dojo Format");
        dojoTab.setContent(new TextArea());
        dojoTab.setClosable( false );
        tabPane.getTabs().add(dojoTab);

        Tab angularTab = new Tab();
        angularTab.setText("Angular Format");
        angularTab.setContent(new TextArea());
        angularTab.setClosable( false );
        tabPane.getTabs().add(angularTab);

        return tabPane;
    }
}
