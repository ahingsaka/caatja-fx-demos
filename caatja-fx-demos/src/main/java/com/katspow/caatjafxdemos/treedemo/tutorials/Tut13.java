package com.katspow.caatjafxdemos.treedemo.tutorials;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.InterpolatorActor;

public class Tut13 {
    
    private static Scene _scene_1;
    
    public static void start(Director _director_1) throws Exception {
        
        if (_scene_1 == null) {
            
            _scene_1= _director_1.createScene();
            
            Interpolator[] lerps= new Interpolator [] {
                    Interpolator.createLinearInterpolator(false, false),
                    Interpolator.createLinearInterpolator(true,  false)
            };
            
            int i;
            for( i=0; i<lerps.length; i++) {
                
                _scene_1.addChild(
                        new InterpolatorActor().
                            setInterpolator( lerps[i], null).
                            setBounds( 10, 10, 80, 80 ).
                            setFillStyle("#d0d0d0").
                            setLocation(0, i * 90));

            }
            
            
            Interpolator[] lerps2 = new Interpolator [] {
                        Interpolator.createLinearInterpolator(false, true),
                        Interpolator.createLinearInterpolator(true,  true)
            };

                for( i=0; i<lerps2.length; i++) {
                    
                    _scene_1.addChild(
                            new InterpolatorActor().
                                setInterpolator( lerps2[i], null ).
                                setBounds( 10, 10, 80, 80 ).
                                setFillStyle("#d0d0d0").
                                setLocation(90, i * 90));

                }
            
        }
        
        _director_1.setScene(_scene_1);
        
    }

}
