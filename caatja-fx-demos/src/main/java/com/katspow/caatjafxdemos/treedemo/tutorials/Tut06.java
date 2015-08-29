package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorListener;
import com.katspow.caatja.foundation.actor.Actor.EventType;

public class Tut06 {
    
    private static Scene _scene_2;
    
    public static void start(Director _director_2) throws Exception {
        
        if (_scene_2 == null) {
        
            _scene_2=    _director_2.createScene();
    
            // create a simple actor. will last for two seconds on Scene.
            Actor _pulsating_actor_2= new Actor().
                    setBounds(10,10,80,80).
                    setFillStrokeStyle(CaatjaColor.valueOf("#00ff00")).
                    setFrameTime(0,2000);
    
            // add a life cycle listener to the actor.
            _pulsating_actor_2.addListener(new ActorListener() {
                @Override
                public void actorLifeCycleEvent(Actor actor, EventType eventType, double time) {
                    // on expiration notification,
                    if (eventType == EventType.EXPIRED) {
                        // just make the Actor sleep for 1 second.
                        // After waking up, last for 2 seconds.
                        actor.setFrameTime( time+1000, 2000 );
                    }
                }
            });
    
            // don't forget to add the actor to the scene.
            _scene_2.addChild( _pulsating_actor_2 );
        
        }
        
        _director_2.setScene(_scene_2);
        
    }

}
