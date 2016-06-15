package com.bh25034.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GUIGroovity extends Application {

	private ApplicationContext context;
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private Stage primaryStage;
	private Scene scene;
	private FlowPane flow;
	private HBox hbControls;
	private double width;
	private double height;
	private Rectangle2D primaryScreenBounds;
	private BorderPane border;
	private Task<String> task;
	private Button btnStart;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Groovity - A Gravity Simulator");
		
		this.border = new BorderPane();
		this.border.setStyle("-fx-background-color: black;");
        
		this.primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		this.width = this.primaryScreenBounds.getWidth();
		this.height = this.primaryScreenBounds.getHeight();
		
		this.canvas = new Canvas(this.width - 300, this.height);
		this.graphicsContext = this.canvas.getGraphicsContext2D();
		
		this.border.setCenter(this.canvas);
		
		this.flow = new FlowPane();
		this.flow.setPadding(new Insets(5, 0, 5, 0));
		this.flow.setVgap(4);
		this.flow.setHgap(4);
		this.flow.setPrefWrapLength(170); // preferred width allows for two columns
		this.flow.setStyle("-fx-background-color: grey;");
		
		this.hbControls = new HBox();
		this.hbControls.setStyle("-fx-background-color: grey;");
		this.hbControls.setMinSize(200, this.primaryScreenBounds.getHeight());
		this.hbControls.setMaxSize(200, this.primaryScreenBounds.getHeight());
		
		this.btnStart = new Button("Start");
		this.btnStart.setMinSize(100, 30);
		this.btnStart.setMaxSize(100, 30);
		this.btnStart.setOnAction(new EventHandler<ActionEvent>() {
		      //@Override 
		      public void handle(ActionEvent event) {
		    	  animate();
		      }
		    });
		
		this.hbControls.getChildren().add(this.btnStart);
		
		this.flow.getChildren().add(this.hbControls);
		
		this.border.setRight(this.flow);
		
        this.scene = new Scene(this.border, this.width, this.height, Color.BLACK);
        
        this.primaryStage.setScene(this.scene);
        this.primaryStage.setWidth(this.width);
        this.primaryStage.setHeight(this.height);
		this.primaryStage.resizableProperty().set(false);
		this.primaryStage.show();
		
	}
	
	public void animate() {
		
		this.task = new Task<String>() {
			@Override 
			protected String call() throws InterruptedException {
				
				graphicsContext.setFill(Color.WHITE);
				graphicsContext.setLineWidth(5);
				
				
				for (int a = 0; a <= height; a ++) {
					
					graphicsContext.clearRect(0, 0, width - 300, height);
					graphicsContext.fillOval(300, a, 10, 10);
					Thread.sleep(10);
					
				}
				
				return null;
				
			};
			
		};
		
		new Thread(this.task).start();
		
	}

}
