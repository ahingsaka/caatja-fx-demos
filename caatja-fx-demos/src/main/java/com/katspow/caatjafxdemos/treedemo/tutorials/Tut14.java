package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.pathutil.Path;

public class Tut14 {
    
private static Scene _scene;
    
    public static void start(Director _director) throws Exception {
        
        if (_scene == null) {
            
            _scene = _director.createScene();

            Path p = new Path().beginPath(40, 40).addCubicTo(60, 160, 180, 60, 100, 140, "red")
                    .addQuadricTo(160, 120, 130, 170, "blue").addLineTo(199, 80, "rgb(0,255,255)").endPath()
                    .setInteractive(false);

            PathActor path = new PathActor().setBounds(0, 0, 100, 100).setPath(p);

            // Path p2= new Path().
            // beginPath(40,40).
            // addCubicTo( 60,160, 180,60, 100,140, "red" ).
            // addQuadricTo( 160,120, 130,170, "blue" ).
            // addLineTo( 199,80, "rgb(0,255,255)" ).
            // closePath().
            // setInteractive(false);
            //
            // PathActor path2= new PathActor().
            // create().
            // setBounds(0,0,100,100).
            // setPath(p2);
            // _scene.addChild(path2);

            _scene.addChild(path);
            
        }
        
        _director.setScene(_scene);
        
    }

}
