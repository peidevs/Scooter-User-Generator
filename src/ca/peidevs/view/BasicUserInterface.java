package ca.peidevs.view;

import ca.peidevs.model.meetup.common.Venue;
import ca.peidevs.model.meetup.rsvp.RsvpList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BasicUserInterface extends Application implements IView{

    private final String dateFormatpattern = "yyyy-MM-dd";

    @FXML private TextField groupName;
    @FXML private TextField key;


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load( getClass().getResource("BasicUserInterface.fxml"));
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Meetup User Generator For Scooter");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setMeetupName(String name) {

    }

    @Override
    public void setMeetupLocation(Venue venue) {

    }

    @Override
    public void setUserList(RsvpList attendeeList) {

    }
}
