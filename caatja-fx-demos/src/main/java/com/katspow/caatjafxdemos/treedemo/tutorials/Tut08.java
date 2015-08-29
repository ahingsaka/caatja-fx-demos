package com.katspow.caatjafxdemos.treedemo.tutorials;

import java.util.Arrays;
import java.util.List;

import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.Actor.Anchor;
import com.katspow.caatja.math.Pt;

public class Tut08 {
    
    private static Scene _scene_4;
    
    public static void start(Director _director_4) throws Exception {
        
        if (_scene_4 == null) {
            
            _scene_4 = _director_4.createScene();

            List<Anchor> anchors = Arrays.asList(Anchor.TOP_LEFT, Anchor.TOP, Anchor.TOP_RIGHT, Anchor.LEFT, Anchor.CENTER,
                    Anchor.RIGHT, Anchor.BOTTOM_LEFT, Anchor.BOTTOM, Anchor.BOTTOM_RIGHT);

          int i;

          for (i = 0; i < 9; i++) {

              // background actors under rotating ones. Just to have a reference
              // of where the anchor is.
              Actor _scene_4_rotating_actor_background = new Actor()
                      .setPosition(50 + 50 * (i % 3), 35 + 50 * ((i / 3) >> 0)).setSize(30, 30).setFillStyle("#ffffff")
                      .setStringStrokeStyle("#000000").
                      // do not accept mouse events.
                      enableEvents(false);
              _scene_4.addChild(_scene_4_rotating_actor_background);

              // rotating actors.
              Actor _scene_4_rotating_actor = new Actor()
                      .setPosition(50 + 50 * (i % 3), 35 + 50 * ((i / 3) >> 0)).setSize(30, 30).setFillStyle("#ff0000");

              // never ending rotating behavior
              Pt anchor = _scene_4.getAnchorPercent(anchors.get(i).getValue());
              RotateBehavior _scene_4_rotating_behavior = (RotateBehavior) new RotateBehavior().
                      setCycle(true)
                      .setFrameTime(0, 2000).
                      setValues(0, 2*Math.PI, anchor.x, anchor.y );
              _scene_4_rotating_actor.addBehavior(_scene_4_rotating_behavior);
              _scene_4.addChild(_scene_4_rotating_actor);

              // scaling actors
              Actor _scene_4_scaling_actor = new Actor()
                      .setPosition(300 + 60 * (i % 3), 30 + 60 * ((i / 3) >> 0)).
                      setSize(30, 30).
                      setFillStyle("#ff00ff");
              // never ending scaling behavior
              
              ScaleBehavior _scene_4_scaling_behavior = (ScaleBehavior) new ScaleBehavior().
                      setCycle(true).
                      setFrameTime(0, 2000).
                      setValues(.5, 1.5, .5, 1.5,anchor.x, anchor.y).
                      setPingPong();
              _scene_4_scaling_actor.addBehavior(_scene_4_scaling_behavior);
              _scene_4.addChild(_scene_4_scaling_actor);
          }
            
            
        }
        
        _director_4.setScene(_scene_4);
        
        
    }

}
