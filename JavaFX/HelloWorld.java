/**
 * HelloWorld on JavaFX
 * got from http://easy-code.ru/lesson/javafx-2-basic
 */

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*; // for Pos & Orientation
 
public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Label lbl = new Label("Label");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
            public void handle(ActionEvent event) {
                lbl.setText("Hello World!");
                //System.out.println("Hello World!");
            }
        });
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setOrientation(Orientation.VERTICAL);
        root.getChildren().addAll(btn, lbl);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}