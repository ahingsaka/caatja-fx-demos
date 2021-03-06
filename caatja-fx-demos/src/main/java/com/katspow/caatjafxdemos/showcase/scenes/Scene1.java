package com.katspow.caatjafxdemos.showcase.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.foundation.ui.Dock;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

public class Scene1 {
    
    static InterpolatorActor selectedInterpolatorActor= null;
    
    private static void __scene1_generateInterpolators(Director director, Scene scene, final PathBehavior pathBehavior) throws Exception {
         Map<String, Interpolator> lerps = Interpolator.enumerateInterpolators();

        int cols= 21;
        int j=0, i=0;
        int rows= lerps.size()/cols;
        int min= 20;
        int max= 45;

        // generate interpolator actors.
        for( j=0; j<rows; j++ ) {

            Dock root= (Dock) new Dock().
                    setBounds(
                        director.canvas.getCoordinateSpaceWidth()-(j+1)*max,
                        0,
                        max,
                        director.canvas.getCoordinateSpaceHeight());
                    root.setSizes(min, max).
                    setApplicationRange( 3 ).
                    setLayoutOp( Dock.OpLayout.RIGHT);
            root.scene= scene;

            scene.addChild(root);

            for( i=0; i<cols; i++ ) {

                if ( j*cols+i>=lerps.size() ) {
                    break;
                }

                InterpolatorActor actor= new InterpolatorActor().
                     setInterpolator((Interpolator) lerps.values().toArray()[(j*cols+i)], null ).
                     setBounds( 0, 0, min, min ).
                     setStringStrokeStyle("blue" );
                
                actor.setMouseExitListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        if ( e.source!=selectedInterpolatorActor ) {
                            e.source.setFillStrokeStyle(null);
                        }
    
//                          ((Dock) mouseEvent.source.parent).actorMouseExit(mouseEvent);
                    }
                });
                
                actor.setMouseEnterListener(new MouseListener() {
                    @Override
                    public void call(CAATMouseEvent e) throws Exception {
//                      ((Dock) mouseEvent.source.parent).actorMouseEnter(mouseEvent);

                      if (e.source != selectedInterpolatorActor) {
                          e.source.setFillStyle("#f0f0f0");
                      }
                    }
                });
                
                
                actor.setMouseClickListener(new MouseListener() {
                    public void call(CAATMouseEvent mouseEvent) {
                        if (null != selectedInterpolatorActor) {
                            selectedInterpolatorActor.setFillStyle(null);
                        }
                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;
                        mouseEvent.source.setFillStyle("#00ff00");
                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;

                        pathBehavior.setInterpolator(((InterpolatorActor) mouseEvent.source).getInterpolator());
                    }
                });

                root.addChild( actor );
            }

            root.layout();
        }
    }

    public static Scene init(Director director) throws Exception {

        Scene scene= new Scene();
        scene.setFillStrokeStyle(CaatjaColor.valueOf("#FFFFFF"));
        
        double dw= director.width;
        double dh= director.height;


        int i;
        double R= (Math.min( dw,dh ) - 40)/2;
        List<Pt> pp= new ArrayList<Pt>();
        double angle;
        int NP=7;
        for( i=0; i<NP; i++ ) {
            angle= i*Math.PI/(NP);

            pp.add( new Pt(
                    dw/2 + R*Math.cos(angle + (Math.PI*(i%2)) ) ,
                    dh/2 + R*Math.sin(angle + (Math.PI*(i%2))) ) );

        }

        // path actor. to show the path and manipulate its control points.
        PathActor pa= new PathActor().
            setBounds(0,0,600,director.canvas.getCoordinateSpaceHeight()).
            setInteractive(true).
            setPath(
                new Path().
                beginPath(200,200).
                addCubicTo( 300,15, 400,10, 550,250, null ).
                addQuadricTo( 550,300, 450,350, null ).
                addQuadricTo( 400,400, 350,200, null ).
                addCubicTo( 100,300, 300,450, 10,400,null).
                addQuadricTo( 40,200, 200,200, null ).
                closePath() );
        
        SpriteImage fishImage = new SpriteImage();
        fishImage.initialize(director.getImage("fish4"), 1, 3);
        
        Actor fishActor = new Actor().
                setBackgroundImage(fishImage, true).
                setSpriteIndex(0).
                enableEvents(false).
                setChangeFPS(300).
                setAnimationImageIndex(new int[] {0,1,2,1}).
                setId("111");
        
        fishActor.setPositionAnchor(0.5, 0.5);

//        SpriteActor fish = new SpriteActor().
//            setAnimationImageIndex(Arrays.asList(0,1,2,1) ).
//            setChangeFPS(300).
//            setSpriteImage(
//                new CompoundImage().
//                    initialize(director.getImage("fish"), 1, 3) ).
//            enableEvents(false).
//            setId("111");
        

        fishActor.setPositionAnchor(.5, .5);

        // path measurer behaviour
        PathBehavior pb= new PathBehavior().
            setPath(pa.getPath()).
            setFrameTime(0,20000).
            setCycle(true).
            setAutoRotate(true);
            //setTranslation( fish.width/2, fish.height/2 );

        fishActor.addBehavior( pb );


        scene.addChild(pa);
        
        scene1_text(director, scene);
        scene.addChild(fishActor);

        __scene1_generateInterpolators(director, scene, pb);

        return scene; 
    }

    private static void scene1_text(Director director, Scene scene) throws Exception {
        ActorContainer cc1 = new ActorContainer();
        cc1.setBounds(0, 30, 280, 110);
        cc1.mouseEnabled = false;
        scene.addChild(cc1);

        cc1.addBehavior(
                new RotateBehavior().
                    setCycle(true).
                    setFrameTime( 0, 4000 ).
                    setValues( -Math.PI/8, Math.PI/8 ).
                    setInterpolator(
                        Interpolator.createExponentialInOutInterpolator(3,true) ).
                    setAnchor(cc1)
            );

        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, 30);
        gradient.addColorStop(0, "#00ff00");
        gradient.addColorStop(0.5, "red");
        gradient.addColorStop(1, "blue");

        TextActor text = new TextActor().
            setFont(new TextFont(20, "px", "sans-serif")).
            setText("Conpound Path").
            calcTextSize(director).
            setTextFillStyle(gradient).
            setOutline(true);
        
        text.cacheAsBitmap();
        cc1.addChild(text.setLocation((cc1.width-text.textWidth)/2,0));
        
        TextActor text2 = new TextActor().
            setFont(new TextFont(20, "px", "sans-serif")).
            setText("Quadric,Cubic,Line segments").
            calcTextSize(director).
            setTextFillStyle(gradient).
            setOutline(true);
        text2.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width-text2.textWidth)/2,20));
        
        TextActor text4 = new TextActor().
            setFont(new TextFont(20, "px", "sans-serif")).
            setText("Fish Path").
            calcTextSize(director).
            setTextFillStyle(gradient).
            setOutline(true);
            text4.cacheAsBitmap();
        cc1.addChild(text4.setLocation((cc1.width-text4.textWidth)/2,50));
        
        TextActor text3 = new TextActor().
            setFont(new TextFont(20, "px", "sans-serif")).
            setText("Interpolators").
            calcTextSize(director).
            setTextFillStyle(gradient).
            setOutline(true);
        text3.cacheAsBitmap();
        cc1.addChild(text3.setLocation((cc1.width-text3.textWidth)/2,70));

        TextActor text5 = new TextActor().
                setFont(new TextFont(20, "px", "sans-serif")).
                setText("DblClick to Select").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text5.cacheAsBitmap();
        cc1.addChild(text5.setLocation((cc1.width-text5.textWidth)/2,90));

    }

}
