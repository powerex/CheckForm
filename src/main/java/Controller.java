import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    SpaceDrawer back = null;
    Thread space = null;
    Thread timer = null;
    TimerTask timerTask;

    boolean task1 = false;
    boolean task2 = false;
    boolean task3 = false;

    int correctAnswer = 123;

    @FXML
    Label infoLabel;

    @FXML
    VBox mainVBox;

    @FXML
    HBox HBox1;

    @FXML
    HBox HBox2;

    @FXML
    HBox HBox3;

    @FXML
    Canvas test;

    @FXML
    AnchorPane parent;

    @FXML
    Button check;

    @FXML
    Button exitButton;

    @FXML
    Button startButton;

    @FXML
    TextField field1;

    @FXML
    TextField field2;

    @FXML
    TextField field3;

/*    @FXML
    ToggleButton tButton1;
    @FXML
    ToggleButton tButton2;
    @FXML
    ToggleButton tButton3;*/

    private void setVisibleElements() {
        field1.setVisible(true);
        field2.setVisible(true);
        check.setVisible(true);
//        tButton1.setVisible(true);
//        tButton2.setVisible(true);
        HBox1.setVisible(true);
        HBox2.setVisible(true);
        HBox3.setVisible(true);

    }

    public void check(ActionEvent actionEvent) {

        check01();
        check02();
        check03();

        if (task1 && task2 && task3) {
//        if (!task1 && !task2 && !task3) {
            if (timer != null) {
                timer.interrupt();
                timer = null;
            }
            exitButton.setVisible(true);
            infoLabel.setTextFill(Color.rgb(255, 0, 0));
            IntegerProperty property = new SimpleIntegerProperty();
            property.set(correctAnswer);
            infoLabel.textProperty().bind(property.asString());
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        field2.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");

        AnchorPane.setLeftAnchor(test, 0.0);
        AnchorPane.setTopAnchor(test, 0.0);
        AnchorPane.setBottomAnchor(test, 0.0);
        AnchorPane.setRightAnchor(test, 0.0);
        test.widthProperty().bind(parent.widthProperty());
        test.heightProperty().bind(parent.heightProperty());

        mainVBox.getChildren().remove(HBox1);
        mainVBox.getChildren().remove(HBox2);
        mainVBox.getChildren().remove(HBox3);

        mainVBox.setOpacity(0.8);

    }

    public void updateSpeed(MouseEvent mouseEvent) {
        if (back != null) {
            back.setSpeed(mouseEvent.getX());
        }
    }

    public void exit(ActionEvent actionEvent) {
        if (space != null)
            space.interrupt();
        if (timer != null)
            timer.interrupt();
        Platform.exit();
        System.exit(0);
    }

    public void start(ActionEvent actionEvent) {
        field1.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        field2.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        field3.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));

        back = new SpaceDrawer(test.getGraphicsContext2D(), test.getWidth(), test.getHeight());

        timerTask = new TimerTask();
        infoLabel.textProperty().bind(timerTask.messageProperty());
        timer = new Thread(timerTask);
        timer.start();

        space = back.getT();

        startButton.setVisible(false);

        mainVBox.getChildren().addAll(HBox1, HBox2, HBox3);
        mainVBox.getChildren().remove(startButton);

        setVisibleElements();
    }


    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }

    public void check1(ActionEvent actionEvent) {
        check01();
    }

    public void check2(ActionEvent actionEvent) {
        check02();
    }

    public void check3(ActionEvent actionEvent) {
        check03();
    }

    private void check03() {
        if (field3.getText().equals("7410")) {
//            tButton3.setSelected(true);
            field3.setStyle(("-fx-text-inner-color: green"));
            task3 = true;
        }
        else{
//            tButton3.setSelected(false);
            field3.setStyle(("-fx-text-inner-color: red"));
            task3 = false;

        }
    }

    private void check02() {
        if (field2.getText().equals("5678")) {
//            tButton2.setSelected(true);
            field2.setStyle(("-fx-text-inner-color: green"));
            task2 = true;
        }
        else{
//            tButton2.setSelected(false);
            field2.setStyle(("-fx-text-inner-color: red"));
            task2 = false;

        }
    }

    private void check01() {
        if (field1.getText().equals("1234")) {
//            tButton1.setSelected(true);
            field1.setStyle(("-fx-text-inner-color: green"));
            task1 = true;
        }
        else{
//            tButton1.setSelected(false);
            field1.setStyle(("-fx-text-inner-color: red"));
            task1 = false;

        }
    }
}
