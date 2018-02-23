package us.matrixcraft.KenKen;

/*
 * Created on 2/23/2018 by ***REMOVED***
 *
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        int screen = 500;

        stage.setTitle("Hello, World!");
        stage.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(screen, screen);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, screen, screen);


        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        new AnimationTimer() {

            int size = 10;
            KenKen game = new KenKen(size);
            boolean generated = false;
            int place = -1;
            double inc = (screen - 5) / size;
            long previous = 0;

            @Override
            public void handle(long now) {
                if (now - previous < 1_000_000_000) return;
                previous = now;
                if (!generated) {
                    int at = 0;
                    place++;
                    gc.setLineWidth(1);
                    for (int y = 1; y < size; y++) {
                        if (at > place) return;
                        at++;
                        gc.strokeLine(0, y * inc, 500, y * inc);
                    }
                    for (int x = 1; x < size; x++) {
                        if (at > place) return;
                        at++;
                        gc.strokeLine(x * inc, 0, x * inc, 500);
                    }

                    generated = true;
                    return;
                }
                for (int c = 0; c < size; c++) {
                    for (int r = 0; r < size; r++) {
                        //game.board[c][r];
                    }
                }
            }
        }.start();
        stage.show();
    }


}


