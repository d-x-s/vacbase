package ca.ubc.cs304.project.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Commonly used setup functions for GUI
 **/
public class HelpfulFunctions {
    public static final int pageWidth = 654;
    public static final int pageHeight = 474;


    /*
        Positions node on the specified (x,y).
    */
    public static void setLayout(Node node, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    /*
        Sets the background color of a pane to green
    */
    public static void setBackgroundColor(Pane root) {
        root.setBackground(new Background(new BackgroundFill(Color.rgb(196, 227, 199),
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /*
        Sets the background color of a pane to green
    */
    public static void setWhiteBackgroundColor(Pane root) {
        root.setBackground(new Background(new BackgroundFill(Color.rgb(253, 255, 242),
                CornerRadii.EMPTY, Insets.EMPTY)));
    }



    /*
        Creates a rounded button as seen on Figma
     */
    public static Button makeButton(Button button) {
        //TODO: Find better color or something idk, parameterize with color to make it easier
        button.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 100px; -fx-text-fill: #ffffff");
        return button;
    }

    public static ImageView createImage(String directory) {
        ImageView imageView = new ImageView();
        try {
            InputStream stream = new FileInputStream(directory);
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageView;
    }


}
