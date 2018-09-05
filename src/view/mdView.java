package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// 三种方式实现监听 1.重写handle 2.匿名函数 3. lamda函数
public class mdView extends Application{

    private Button button;
    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Title of the window");
//        Label label = new Label("");
//        button = new Button();
//        button.setText("click me");
////        button.setOnAction(this); // 方法1
////        button.setOnAction(new EventHandler<ActionEvent>() { // 方法2
////            @Override
////            public void handle(ActionEvent event) {
////                System.out.println("hello javafx");
////            }
////        });
//
//        button.setOnAction(event -> window.setScene(scene2)); //方法3
//
////        button.setOnAction(event -> {
////            System.out.println("hello");
////            System.out.println("javafx");
////        });
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        window = primaryStage;

        //Button 1
        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);


        //Button 2
        Button button2 = new Button("This sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }

//    @Override
//    public void handle(ActionEvent event) {
//        if(event.getSource() == button){
//            System.out.println("hello javafx");
//        }
//    }
}
