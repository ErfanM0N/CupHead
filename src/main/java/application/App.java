package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loadFXML("SignupPage");
        App.scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("cupHead");
        stage.setResizable(false);
        stage.show();
    }

    public static void changeMenu(String name){
        Parent root = loadFXML(name);
        App.scene.setRoot(root);
    }

    public static Parent loadFXML(String name){
        try {
            URL address = new URL(App.class.getResource("/fxml/" + name + ".fxml").toString());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
