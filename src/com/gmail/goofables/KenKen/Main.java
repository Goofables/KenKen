package com.gmail.goofables.KenKen;

/*
 * Created on 2/15/18 at 10:19 PM by ***REMOVED***
 *
 * Created in KenKen (com.gmail.goofables.KenKen)
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

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    
    private static int screenSize = 500;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        stage.setTitle("Hello, World!");
        //stage.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(screenSize, screenSize);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        new loop(canvas).start();
        stage.show();
    }
}

class loop extends AnimationTimer {
    
    private static int border = 5;
    private static int size = 4;
    
    private long last = 0;
    private double tps = 0;
    
    private Canvas canvas;
    private GraphicsContext gc;
    
    private double WIDTH;
    private double HEIGHT;
    private double W_INC;
    private double H_INC;
    
    private List<QueueItem> queue = new ArrayList<>();
    private KenKen game;
    
    loop(Canvas canvas) {
        super();
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
        W_INC = (WIDTH - 2 * border) / size;
        H_INC = (HEIGHT - 2 * border) / size;
        
        setup();
    }
    
    @Override
    public void handle(long now) {
        if (now - last <= 45_000_000) return;
        tps = 1_000_000_000 / (now - last);
        last = now;
        if (queue.size() > 0) {
            QueueItem item = queue.remove(0);
            gc.setStroke(item.color);
            gc.setLineWidth(item.weight);
            gc.strokeLine(item.x1, item.y1, item.x2, item.y2);
            return;
        }
        
        gc.setFill(Color.WHITE);
        gc.fillRect(8, 8, 50, 20);
        gc.setFill(Color.BLUE);
        gc.fillText(String.valueOf(tps), 10, 20);
        
        
        
        /*for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                //game.board[c][r];
            }
        }*/
    }
    
    private void setup() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(border * 2);
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
        for (int y = 1; y < size; y++)
            queue.add(new QueueItem(border, y * H_INC + border, HEIGHT - border, y * H_INC + border, Color.BLACK, 1));
        for (int x = 1; x < size; x++)
            queue.add(new QueueItem(x * W_INC + border, border, x * W_INC + border, WIDTH - border, Color.BLACK, 1));
    }
}

class QueueItem {
    double x1;
    double y1;
    double x2;
    double y2;
    Color color;
    int weight;
    
    QueueItem(double x1, double y1, double x2, double y2, Color color, int weight) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.weight = weight;
    }
}