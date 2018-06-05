package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class Main extends Application 
{
	public static final double btn_Height=50;
	public static final double btn_Width=100;
	public static  Stage nt_stage= new Stage();
	public static  Stage primaryStage_main= new Stage();
	
	private static StackPane root_main = new StackPane();
	private static Scene scene_main = new Scene(root_main,400,400);
	private VBox vbox = new VBox(10);
	
	@Override
	public void start(Stage primaryStage)
	{
		try 
		{

			Button NetWork_topology = new Button("תצורת הרשת");
				   NetWork_topology.setVisible(true);
				   NetWork_topology.setPrefSize(btn_Width, btn_Height);
				   NetWork_topology.setOnMouseClicked(new EventHandler<MouseEvent>() 
				   {
					   @Override
					   public void handle(MouseEvent e)
					   {
						   if(e.getClickCount() == 1)
						   {
							   Network_Topology nt = new  Network_Topology();
							   Scene nt_scene = nt.getScene();

							   nt_stage.setScene(nt_scene);
							   nt_stage.show();

							   primaryStage_main.hide();
						   }
						   else
						   {
							   nt_stage.show();
							   primaryStage_main.hide();
						   }
					   }
				   });
				   
				   
			Button Bakara = new Button("בקרות");
				   Bakara.setVisible(true);
				   Bakara.setPrefSize(btn_Width, btn_Height);
			   
			Button Exit = new Button("יציאה");
				   Exit.setVisible(true);
				   Exit.setPrefSize(btn_Width, btn_Height);
				   Exit.setOnMouseClicked(new EventHandler<MouseEvent>() 
				   {
					   @Override
					   public void handle(MouseEvent e)
					   {
						   System.exit(0);
					   }
				   });
				   
			vbox.getChildren().addAll(NetWork_topology,Bakara,Exit);
			vbox.setAlignment(Pos.CENTER);
			
			root_main.getChildren().add(vbox);

			scene_main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage_main.setScene(scene_main);
			primaryStage_main.setResizable(false);
			primaryStage_main.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		Application.launch(args);
	}

	public static Scene getScene() 
	{
		return scene_main;
	}
}
