package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Parent parent = loader.load();			//Chama o load, carrega a View
			Scene mainScene = new Scene(parent);	//Cena Principal, sendo parent o objeto principal da View
			primaryStage.setScene(mainScene);		//Palco da Cena
			primaryStage.setTitle("Sample JavaFX application");	//Titulo do Palco
			primaryStage.show();	//Mostrar o Palco
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
