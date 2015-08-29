package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut01 {
    
    private static Scene scene_1;
    
    public static void start(Director director_1) throws Exception {
        
        if (scene_1 == null) { 
            scene_1 = director_1.createScene();
            scene_1.setFillStyle("#ffffff");
            
            ShapeActor rectangle_1 = (ShapeActor) new ShapeActor();
            
            rectangle_1.setShape(ShapeActor.Shape.RECTANGLE)
                .setLocation(10, 10)
                .setSize(60, 60)
                .setFillStrokeStyle(CaatjaColor.valueOf("#ff0000"));
            
            rectangle_1.setStrokeStyle(CaatjaColor.valueOf("#000000"));
    
            scene_1.addChild(rectangle_1);
    
            // setup a behaviour. take 5 seconds to change alpha transparency from 1
            // to .1
            // start fading when 2 seconds have passed from scene start time.
            AlphaBehavior alpha_1 = new AlphaBehavior().
                    setValues(1,.1).
                    setFrameTime( 2000, 5000 );
    
            rectangle_1.addBehavior(alpha_1);
        }
        
        director_1.setScene(scene_1);
        
    }
    
    

}
