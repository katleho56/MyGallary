package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloApplication extends Application {

    private static final int THUMBNAIL_SIZE = 85;
    private static final int FULL_IMAGE_SIZE = 400;

    private int currentIndex = 0;
    private Image[] images = {
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image1.jpg"),
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image2.jpg"),
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image3.jpg"),
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image4.jpg"),
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image5.jpg"),
            loadImage("C:/Users/katle/Desktop/KatlehoMabethoane901015173Multimedia/demo/src/main/resources/images/image6.jpg"),
            // Add more image paths as needed
    };

    private ImageView imageView = new ImageView();

    @Override

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox lefty = new HBox(11);
        HBox righty = new HBox(11);


        FlowPane thumbnailPane = createThumbnailPane();

        Button prevButton = new Button("UP");
        prevButton.setOnAction(e -> showPreviousImage());

        Button nextButton = new Button("DOWN");
        nextButton.setOnAction(e -> showNextImage());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> clearSelectedImage());

        BorderPane toPane = new BorderPane();
        toPane.setTop(backButton);


        BorderPane ritPane = new BorderPane();
        ritPane.setTop(prevButton);
        ritPane.setBottom(nextButton);



        root.setCenter(imageView);
        root.setTop(toPane);
        root.setLeft(thumbnailPane);
        root.setRight(ritPane);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("style.css"); // Relative path to CSS file
        primaryStage.setTitle("My Gallery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FlowPane createThumbnailPane() {
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        for (int i = 0; i < images.length; i++) {
            ImageView thumbnail = new ImageView(images[i]);
            thumbnail.setFitWidth(THUMBNAIL_SIZE);
            thumbnail.setFitHeight(THUMBNAIL_SIZE);
            int index = i;
            thumbnail.setOnMouseClicked(e -> showFullImage(index));
            pane.getChildren().add(thumbnail);
        }

        return pane;
    }

    private void showFullImage(int index) {
        currentIndex = index;
        imageView.setImage(images[index]);
        imageView.setFitWidth(FULL_IMAGE_SIZE);
        imageView.setFitHeight(FULL_IMAGE_SIZE);
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private  void clearSelectedImage(){
        imageView.setImage(null);
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private Image loadImage(String path) {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load image: " + path);
            alert.showAndWait();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


