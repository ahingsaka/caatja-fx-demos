package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut00 {
    
    private static Scene scene;
    
    public static void start(Director director) throws Exception {
        
        if (scene == null) {
            scene = director.createScene();
            scene.setFillStyle("#ffffff");
            
            ShapeActor circle = new ShapeActor()
                .setLocation(20, 20)
                .setSize(60, 60)
                .setFillStrokeStyle(CaatjaColor.valueOf("#ff0000"));
            
            circle.setStrokeStyle(CaatjaColor.valueOf("#000000"));
            
            scene.addChild(circle);
        }
        
        director.setScene(scene);
        
    }

}
