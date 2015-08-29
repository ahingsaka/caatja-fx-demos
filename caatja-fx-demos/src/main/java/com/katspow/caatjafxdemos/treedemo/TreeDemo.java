package com.katspow.caatjafxdemos.treedemo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatjafxdemos.hypernumber.HyperNumber;
import com.katspow.caatjafxdemos.showcase.Showcase;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut00;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut01;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut02;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut03;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut04;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut05;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut06;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut07;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut08;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut09;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut10;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut11;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut12;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut13;
import com.katspow.caatjafxdemos.treedemo.tutorials.Tut14;


public class TreeDemo {
    
    private Director director = null;
    private final HomeScene homeScene = new HomeScene();
    private TextActor loadingText;
    
    private static final String RED_BALLOON = "Red balloon";
    private static final String FADING_RECTANGLE = "Fading rectangle";
    private static final String SCALING_AND_ROTATING_RECTANGLE = "Scaling and rotating rectangle";
    private static final String CONTAINER_BEHAVIOR = "Container Behavior";
    private static final String TWO_SQUARES_WITH_PATH = "2 squares with path";
    private static final String MOUSE_COORDS = "Mouse coordinates";
    private static final String DISAPPEAR_AND_REAPPEARS = "Disappear and reappears";
    private static final String SOME_TRANSFORMATIONS = "Some transformations";
    private static final String NINE_ROTATE_SCALE = "9 rotate and scale";
    private static final String DRAG_ME = "Drag me";
    private static final String GRADIENT = "Gradient";
    private static final String EFFECTS_ON_TEXT = "Effects on text (BUGGED)";
    private static final String SODA_EFFECT = "Soda effect";
    private static final String INTERPOLATORS = "Interpolators";
    private static final String PATHS = "Paths";
    
    private static final String SHOWCASE = "Showcase";
    
    private static final String HYPERNUMBER = "Hypernumber";
    

    public void start(Pane root) throws Exception {
        TreeItem<String> treeRoot = new TreeItem<String>("CAATJAFX-showcase");
        
        TreeItem<String> tutorialsTreeItem = new TreeItem<String>("Tutorials");
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(RED_BALLOON));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(FADING_RECTANGLE));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(SCALING_AND_ROTATING_RECTANGLE));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(CONTAINER_BEHAVIOR));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(TWO_SQUARES_WITH_PATH));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(MOUSE_COORDS));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(DISAPPEAR_AND_REAPPEARS));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(SOME_TRANSFORMATIONS));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(NINE_ROTATE_SCALE));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(DRAG_ME));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(GRADIENT));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(EFFECTS_ON_TEXT));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(SODA_EFFECT));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(INTERPOLATORS));
        tutorialsTreeItem.getChildren().add(new TreeItem<String>(PATHS));
        
        TreeItem<String> showcaseTreeItem = new TreeItem<String>(SHOWCASE);
        
        TreeItem<String> hyperNumberTreeItem = new TreeItem<String>(HYPERNUMBER);
        
        treeRoot.getChildren().add(tutorialsTreeItem);
        treeRoot.getChildren().add(showcaseTreeItem);
        treeRoot.getChildren().add(hyperNumberTreeItem);
        
        TreeView treeView = new TreeView();
        treeView.setShowRoot(true);
        treeView.setRoot(treeRoot);
        treeView.setMaxHeight(768);
        treeView.setMaxWidth(250);
        
        treeRoot.setExpanded(true);
        
        root.getChildren().add(treeView);
        
        CaatjaCanvas canvas = Caatja.createCanvas();
        Caatja.addCanvas(canvas, 251, 0);
        
        director = new Director();
        director.initialize(680, 500, canvas);
        director.addScene(homeScene);
        
        homeScene.load(director);
        director.setScene(homeScene);
        
        loadingText = createLoadingText();
        
        loadImages();
        
        
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                
                String value = selectedItem.getValue();
                
                try {
                    
                    if (RED_BALLOON.equals(value)) {
                        Tut00.start(director);
                    } else if (FADING_RECTANGLE.equals(value)) {
                        Tut01.start(director);
                    } else if (SCALING_AND_ROTATING_RECTANGLE.equals(value)) {
                        Tut02.start(director);
                    } else if (CONTAINER_BEHAVIOR.equals(value)) {
                        Tut03.start(director);
                    } else if (TWO_SQUARES_WITH_PATH.equals(value)) {
                        Tut04.start(director);
                    } else if (MOUSE_COORDS.equals(value)) {
                        Tut05.start(director);
                    } else if (DISAPPEAR_AND_REAPPEARS.equals(value)) {
                        Tut06.start(director);
                    } else if (SOME_TRANSFORMATIONS.equals(value)) {
                        Tut07.start(director);
                    } else if (NINE_ROTATE_SCALE.equals(value)) {
                        Tut08.start(director);
                    } else if (DRAG_ME.equals(value)) {
                        Tut09.start(director);
                    } else if (GRADIENT.equals(value)) {
                        Tut10.start(director);
                    } else if (EFFECTS_ON_TEXT.equals(value)) {
                        Tut11.start(director);
                    } else if (SODA_EFFECT.equals(value)) {
                        Tut12.start(director);
                    } else if (INTERPOLATORS.equals(value)) {
                        Tut13.start(director);
                    } else if (PATHS.equals(value)) {
                        Tut14.start(director);
                        
                    } else if (SHOWCASE.equals(value)) {
                        Showcase.start(director);
                        
                    } else if (HYPERNUMBER.equals(value)) {
                        HyperNumber.start(director);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        Caatja.loop(60);
    }
    
    private void loadImages() {
        preloadImages();

        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(Caatja.getCaatjaImagePreloader(), new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                finishImageLoading();
            }
        });

    }
    
    private void preloadImages() {
        final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
        
        // Showcase images
        preloader.addImage("fish", "anim1.png");
        preloader.addImage("fish2", "anim2.png");
        preloader.addImage("fish3", "anim3.png");
        preloader.addImage("fish4", "anim4.png");
        preloader.addImage("chapas", "chapas.jpg");
        preloader.addImage("buble1", "burbu1.png");
        preloader.addImage("buble2", "burbu2.png");
        preloader.addImage("buble3", "burbu3.png");
        preloader.addImage("buble4", "burbu4.png");
        preloader.addImage("plants", "plants.jpg");
        preloader.addImage("bumps", "3.jpg");
       
        // Hypernumber images
        preloader.addImage("bricks", "nums.png");
        preloader.addImage("buttons", "buttons.png");
        preloader.addImage("madewith", "madewith.png");
        preloader.addImage("space", "space2.jpg");
        preloader.addImage("background", "fondo.jpg");
        preloader.addImage("background_op", "fondo_opciones.jpg");
        preloader.addImage("cloud1", "nube1.png");
        preloader.addImage("cloud2", "nube2.png");
        preloader.addImage("cloud3", "nube3.png");
        preloader.addImage("cloud4", "nube4.png");

        preloader.addImage("cloudb1", "nubefondo1.png");
        preloader.addImage("cloudb2", "nubefondo2.png");
        preloader.addImage("cloudb3", "nubefondo3.png");
        preloader.addImage("cloudb4", "nubefondo4.png");
       
        // Specific demos images
//        preloader.addImage(SimpleDemo.LOGO_FRENZY.img, "demo15.png");
//        preloader.addImage(SimpleDemo.MASKING.img, "demo16.png");
//        preloader.addImage(SimpleDemo.KEYBOARD.img, "demo18.png");
//        preloader.addImage(SimpleDemo.QUADTREE_BASED_COLLISION.img, "demo19.png");
    }
    
    private void finishImageLoading() throws Exception {
        director.imagesCache = Caatja.getCaatjaImagePreloader().getCaatjaImages();
       
//        demosStackPanel.setVisible(true);
        
        homeScene.removeChild(loadingText);
        homeScene.showIntroduction();
        
    }
    
    private TextActor createLoadingText() throws Exception {
        TextActor loading = new TextActor();
        loading.setFont(new TextFont(30, "px", "sans-serif")).
            setTextBaseline("top").
            setText("Loading ...").
            calcTextSize(director).
            setTextFillStyle("white");

        loading.setLocation((director.canvas.getCoordinateSpaceWidth() - loading.width) / 2,
                (director.canvas.getCoordinateSpaceHeight()) / 2);

        homeScene.addChild(loading);
        
        return loading;
    }

}
