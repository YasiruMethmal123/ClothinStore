import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static java.util.Objects.*;
import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    public void start(Stage stage) throws IOException {
        stage.setScene(FXMLLoader.load(Objects.<URL>requireNonNull(getClass().getResource("/view/user/home-form.fxml"))));
        stage.show();
    }
}