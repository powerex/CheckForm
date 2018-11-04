import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/form.fxml";
        String styleFile = "/style.css";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setController(new Controller());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        /*  зайве
            primaryStage.setMaximized(true);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
        */

        // empty hint in fullscreen mode
        primaryStage.setFullScreenExitHint("");
        // disable exit from fullscreen on Esc button
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//         turn on fullscreen mode
        primaryStage.setFullScreen(true);


/*         // turn off exit button
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
//*/
        scene.getStylesheets().add(App.class.getResource(styleFile).toExternalForm());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
