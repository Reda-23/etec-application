package etec.etecapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene login = new Scene(fxmlLoader.load(), 600, 400);

        String path = "src/main/resources/images/etecLogo-removebg-preview.png";
        File file = new File(path);
        String localUrl = file.toURI().toURL().toString();
        Image thumbnail = new Image(localUrl, false);
        primaryStage.getIcons().add(thumbnail);

        primaryStage.setTitle("ETEC Emploi Du Temps");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(login);
        primaryStage.show();
    }


    public static void  switchscene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }


    public static void main(String[] args) {
        launch();
    }
}