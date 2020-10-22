package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SettingPgController {
	@FXML
	private Slider slider;

	@FXML
	void playClicked(MouseEvent event) {
		Parent parent = null;
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/application/GamePg.fxml"));
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GamePgController controller = loader.getController();
		controller.getData((int) slider.getValue());
		controller.setColumns((int) slider.getValue());
		controller.setRows((int) slider.getValue());
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(
				getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node) event.getSource()).getScene()
				.getWindow();
		window.setScene(scene);
		window.show();
	}

	public int getSlider() {
		return (int) slider.getValue();
	}

}
