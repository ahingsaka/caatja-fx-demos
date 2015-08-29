package com.katspow.caatjafxdemos;

import javafx.application.Application;
import javafx.stage.Stage;

import com.katspow.caatjafx.CaatjaFx;
import com.katspow.caatjafxdemos.treedemo.TreeDemo;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// primaryStage.setTitle("Drawing Operations Test");

		try {
			CaatjaFx.init(primaryStage, new ImageLoader());
			
			new TreeDemo().start(CaatjaFx.getFxPane());
			
//			primaryStage.setWidth(480);
//			primaryStage.setHeight(800);
//			new Teetrys().onModuleLoad();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
