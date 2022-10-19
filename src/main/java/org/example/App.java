package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private GridPane gridPane = new GridPane();
    private BorderPane borderPane = new BorderPane();
    private Label title = new Label("Tic Tac Toe");
    private  Button restartButton = new Button("Restart");
    Font font = Font.font("Roboto",FontWeight.BOLD,30);

    private Button [] btns = new Button[9];
  //  Logical variables
    boolean gameOver  = false;
    int activePlayer =0;
    int gameState [] = {3,3,3,3,3,3,3,3,3};
    int  winningPosition [][]= {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };


    @Override
    public void start(Stage stage) throws IOException {

       this.createGUI();
       this.handleEvent();
       Scene scene = new Scene(borderPane,400,600);
       stage.setScene(scene);
       stage.show();
    }

    private void createGUI() {
        title.setFont(font);
        restartButton.setFont(font);
        restartButton.setDisable(true);
        borderPane.setTop(title);
        borderPane.setBottom(restartButton);

        BorderPane.setAlignment(title,Pos.CENTER);
        BorderPane.setAlignment(restartButton, Pos.CENTER );
        borderPane.setPadding(new Insets(50,20,50,20));


        // Buttons 9
        int label=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                Button button = new Button( );
                button.setId(label+"");
                button.setFont(font);
                button.setPrefWidth(150);
                button.setPrefHeight(150);
                gridPane.add(button, i, j);
                gridPane.setAlignment(Pos.CENTER.CENTER);
                btns[label] = button;
                label++;
            }
        }

        borderPane.setCenter(gridPane);
    }

    private  void handleEvent()
    {
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i=0;i<9;i++){
                    gameState[i]=3;
                    btns[i].setText("");
                    gameOver=false;
                    restartButton.setDisable(true);
                }
            }
        });
        for (Button btn : btns){
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("numbers buttons clicked");
                   Button currBtn = (Button)actionEvent.getSource();
                   String ids = currBtn.getId();
                   int idI = Integer.parseInt(ids);
                   if(gameOver){
                       Alert alert = new Alert((Alert.AlertType.ERROR));
                       alert.setTitle("Errot message");
                       alert.setContentText("Game Overr !! try Again");
                       alert.show();
                   }else {
                         if(gameState[idI]==3) {
                              if(activePlayer==1){
                                  //chane of 1
                                  currBtn.setText(activePlayer+"");
                                //  currBtn.setGraphic(new ImageView(new Image("file:src/main/resources/img/cross.png")));
                                  gameState[idI]=activePlayer;
                                  checkForWinner();
                                  activePlayer=0;
                              }else{
                                  //chance of 0
                                  currBtn.setText(activePlayer +"");
                                  gameState[idI]=activePlayer;
                                  checkForWinner();
                                  activePlayer=1;
                              }
                         }else{
                             Alert alert = new Alert((Alert.AlertType.ERROR));
                             alert.setTitle("Errot message");
                             alert.setContentText("Occupied try Another One");
                             alert.show();
                         }
                   }
                }
            });
        }
    }

    private void checkForWinner() {
        if(!gameOver){
            for (int wp[] : winningPosition){
                if(gameState[wp[0]]==gameState[wp[1]] &&  gameState[wp[1]]==gameState[wp[2]] && gameState[wp[1]]!=3){
                    Alert alert = new Alert((Alert.AlertType.ERROR));
                    alert.setTitle("Errot message");
                    alert.setContentText(activePlayer +"Won");
                    alert.show();
                    gameOver=true;
                    restartButton.setDisable(false);
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {

        launch();
    }

}