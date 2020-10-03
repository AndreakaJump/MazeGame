package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePgController {


    @FXML
    void playClicked(MouseEvent event) {
    	Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/application/SettingPg.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();

    }

}
