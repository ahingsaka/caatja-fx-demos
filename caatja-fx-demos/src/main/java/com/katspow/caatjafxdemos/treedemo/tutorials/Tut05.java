package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

public class Tut05 {
    
    private static Scene scene_1;
    
    public static void start(Director director_1) throws Exception {
        
       if (scene_1 == null) {
           
           scene_1= director_1.createScene();
           
           final TextActor children= new TextActor();
           children.setFont(new TextFont(15, "px", "sans-serif"));
           children.textAlign="left";
           children.textBaseline="top";
           children.setText("");
           children.setLocation(5,5);
           children.setTextFillStyle(CaatjaColor.valueOf("black"));
           children.outlineColor= "black";
           children.outline= true;
           
         // create six actors for this scene.
            for(int i=0; i<6; i++ ) {
    
                // rectangle shaped actors of 80x80 pixels.
                int s = 80;
    
                // containers can contain other actors or containers.
                ActorContainer _c1_container = (ActorContainer) new ActorContainer() {
                    @Override
                    public void paint(Director director, double time) {
                        CaatjaContext2d crx= director.ctx;
    
                        // fill actor
                        crx.setFillStyle(this.fillStyle);
                        crx.fillRect(0,0,this.width,this.height );
    
                        // outline it.
                        crx.setStrokeStyle(CaatjaColor.valueOf("black"));
                        crx.strokeRect(0,0,this.width,this.height );
    
                        // draw a white arrow. just to point where position 0,0 is.
                        crx.setStrokeStyle(CaatjaColor.valueOf("white"));
                        crx.beginPath();
                        crx.moveTo(5,10);
                        crx.lineTo(20,10);
                        crx.lineTo(15,5);
    
                        crx.moveTo(20,10);
                        crx.lineTo(15,15);
    
                        crx.setLineWidth(2);
                        crx.setLineJoin("round");
                        crx.setLineCap("round");
    
                        crx.stroke();
                    }
    
                }.
                        setBounds(i*100+10, 20, s, s).
                       
                        setRotation( Math.PI*2*Math.random() ).
                        setFillStrokeStyle(CaatjaColor.valueOf("#ff3fff"));
                
                _c1_container.setMouseMoveListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        // get the scene Actor the event was generated for.
                        Actor actor= e.source;
    
                        // show some event info:
                        children.setText(
                                "Actor: "+ actor.name+" "+
                                "Local Coord: ("+
                                    // with all this stuff i'm just stripping
                                    // off any decimal beyond .99
                                    ((int)(e.point.x*100)>>0)/100+","+
                                    ((int)(e.point.y*100)>>0)/100+") "+
                                "Screen Coord: ("+
                                    e.screenPoint.x+","+
                                    e.screenPoint.y+") "+
                                "Parent Coord: ("+
                                    actor.x+","+actor.y+")");
                    }
                });
    
                _c1_container.name = "rectangle"+i;
    
                // add actor to scene.
                scene_1.addChild(_c1_container);
                
                // create a container.
                ActorContainer _c1_container_child= (ActorContainer) new ActorContainer() {
                    @Override
                    public void paint(Director director, double time) {
                        // call default container paint method.
                        super.paint(director,time);
                        CaatjaContext2d ctx= director.ctx;
    
                        // fill a white circle of 10x10 pixels at position 2,2
                        // just to show where 0,0 is positioned on screen.
                        ctx.setFillStyle(CaatjaColor.valueOf("white"));
                        ctx.beginPath();
                        ctx.arc(7,7,5,0,2*Math.PI,false);
                        ctx.fill();
                    }
                    
                }.
                        setBounds(s/2,s/2,s/4,s/4).
                        setRotation( Math.PI*2*Math.random() ).
                        setFillStrokeStyle(CaatjaColor.valueOf("#00ff00"));
                
                _c1_container.setMouseMoveListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        // get the scene Actor the event was generated for.
                        Actor actor= e.source;
    
                        // show some event info:
                        children.setText(
                                "Actor: "+ actor.name+" "+
                                "Local Coord: ("+
                                    // with all this stuff i'm just stripping
                                    // off any decimal beyond .99
                                    ((int)(e.point.x*100)>>0)/100+","+
                                    ((int)(e.point.y*100)>>0)/100+") "+
                                "Screen Coord: ("+
                                    e.screenPoint.x+","+
                                    e.screenPoint.y+") "+
                                "Parent Coord: ("+
                                    actor.x+","+actor.y+")");
                        
                    }
                });;
    
                // set a custom paint function for children inside containers.
    
                // add this container as a child of the previous created container.
                _c1_container.addChild(_c1_container_child);
    
            }
            
            scene_1.addChild(children);
        
       }
       
       director_1.setScene(scene_1);
        
        
    }

}
