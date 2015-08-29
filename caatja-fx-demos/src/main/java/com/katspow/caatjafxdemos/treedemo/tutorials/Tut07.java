package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

public class Tut07 {
    
    private static Scene _scene_3;

    private static TextActor text;
    
    public static void start(Director _director_3) throws Exception {
        
        if (_scene_3 == null) {
        
            _scene_3=     _director_3.createScene();
            
            text= new TextActor();
            text.setFont(new TextFont(15, "px", "sans-serif"));
            text.textAlign="left";
            text.textBaseline="top";
            text.setText("");
            text.setLocation(5,5);
            text.setTextFillStyle(CaatjaColor.valueOf("black"));
            text.outlineColor= "black";
            text.outline= true;
    
            // create a simple actor. no transformations.
            Actor _pulsating_actor_3_0= new Actor().
                    setBounds(10,20,80,80).
                    setFillStyle("#00ff00");
            _pulsating_actor_3_0.name= "no transformation";
    
            // rotated 30 degrees
            Actor _pulsating_actor_3_1= new Actor().
                    setBounds(120,20,80,80).
                    setFillStyle("#00ff00").
                    setRotation( Math.PI/6 );
            _pulsating_actor_3_1.name= "rotated 30 degrees";
    
            // half in width
            Actor _pulsating_actor_3_2= new Actor().
                    setBounds(200,20,80,80).
                    setFillStyle("#00ff00").
                    setScale( .5, 1 );
            _pulsating_actor_3_2.name= "scaled(.5,1)";
    
            // 125% width, half height
            Actor _pulsating_actor_3_3= new Actor().
                    setBounds(300,20,80,80).
                    setFillStyle("#00ff00").
                    setScale( 1.2, .4 );
            _pulsating_actor_3_3.name= "scaled(1.25,.5)";
    
            // change default mouse handler to report coordinates.
            MouseListener mouseMoveListener = new Tut07().new MouseMoveHandler();
            _pulsating_actor_3_0.setMouseMoveListener(mouseMoveListener);
            _pulsating_actor_3_1.setMouseMoveListener(mouseMoveListener);
            _pulsating_actor_3_2.setMouseMoveListener(mouseMoveListener);
            _pulsating_actor_3_3.setMouseMoveListener(mouseMoveListener);
    
            // don't forget to actors to the scene.
            _scene_3.addChild( _pulsating_actor_3_0 );
            _scene_3.addChild( _pulsating_actor_3_1 );
            _scene_3.addChild( _pulsating_actor_3_2 );
            _scene_3.addChild( _pulsating_actor_3_3 );
        
        }
        
        _scene_3.addChild(text);
        
        _director_3.setScene(_scene_3);
        
    }
    
    class MouseMoveHandler implements MouseListener {

        @Override
        public void call(CAATMouseEvent mouseEvent) throws Exception {
            Actor actor = mouseEvent.source;
            text.setText(
                                "Actor: "+ actor.name+" "+
                                "Local Coord: ("+
                                    // with all this stuff i'm just stripping
                                    // off any decimal beyond .99
                                    (((int) mouseEvent.point.x*100)>>0)/100+", "+
                                    (((int) mouseEvent.point.y*100)>>0)/100+") "+
                                "Screen Coord: ("+
                                    mouseEvent.screenPoint.x+", "+
                                    mouseEvent.screenPoint.y+") ");
            
        }
        
    }

}
