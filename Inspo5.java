/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inspo5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Inspo5 extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
//        ScrollPane root = new ScrollPane();
        
        BorderPane root = new BorderPane();
        
        //AnchorPane leftAnchor = new AnchorPane();
        VBox rightVBox = new VBox();
        
        //root.setLeft(leftAnchor);
        
        root.setRight(rightVBox);
        
        ScrollPane galleryScroll = new ScrollPane();
        //leftAnchor.setLeftAnchor(galleryScroll, 0.5);
        
        root.setLeft(galleryScroll);
        
        TilePane tile = new TilePane();
        //root.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 80, 15, 15));
        tile.setHgap(15);
        
//        SplitPane sp = new SplitPane();
//        SplitPane splitPane = new SplitPane();
//
//        VBox leftControl  = new VBox();
//        VBox rightControl = new VBox();
//
//        splitPane.getItems().addAll(leftControl, rightControl);

        String path = "C:\\Users\\Troy\\Documents\\Uni\\Project\\Resources\\Test Images";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
                ImageView imageView;
                imageView = mediaTile(file);
                tile.getChildren().addAll(imageView);
        }


        galleryScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        galleryScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        galleryScroll.setFitToWidth(true);
        galleryScroll.setContent(tile);
        //root.setContent(sp);

//        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
//        primaryStage.setHeight(Screen.getPrimary().getVisualBounds()
//                .getHeight());
        
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inspo");
        primaryStage.show();

    }

    private ImageView mediaTile(final File imageFile) {

        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: GRAY");
                                imageView.setFitHeight(stage.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: GRAY");
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth());
                                newStage.setHeight(stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane,Color.GRAY);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
