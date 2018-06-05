package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.sql.*;


public class AddItem
{
	public static final double Height=10;
	public static final double Width=178;
	public static final double ip=35;
	
	GridPane Host_Name_format = new GridPane();
	GridPane ip_format_grid= new GridPane();
	GridPane switch_grid= new GridPane();
	StackPane add_item_root = new StackPane();
	VBox vbox_addItem = new VBox(10);
	
	Scene add_item_scene;
	Stage add_item_stage = new Stage();
	
	public  AddItem()
	{
		Host_Name_format.setVgap(10);
		Host_Name_format.setHgap(10);
		Host_Name_format.setPadding(new Insets(5, 10, 5, 10));
		
		ip_format_grid.setPadding(new Insets(0, 10, 0, 10));

		switch_grid.setVgap(10);
		switch_grid.setHgap(33);
		switch_grid.setPadding(new Insets(10, 10, 10, 10));

		add_item_scene = new Scene(add_item_root,500,500);
		
		/* ---------------------------- host name format grid  ------------------------- */
		Text choose_buliding = new Text("choose building : ");
		Host_Name_format.add(choose_buliding, 0, 0); // Category in column 1, row 1
		
		ComboBox<String> Building_list = new ComboBox<String>();
						 Building_list.resize(Width, Height);
						 Building_list.getItems().addAll("תמך","מרגנית","צפוני","דרומי","בור");
		Host_Name_format.add(Building_list, 1, 0); // Category in column 2, row 1
				 
		Text Koma = new Text("choose floor : ");
		Host_Name_format.add(Koma, 0, 1); // Category in column 1, row 2
		
		ComboBox<String> cb_null = new ComboBox<String>();
				 cb_null.setVisible(true);
				 cb_null.resize(Width, Height);
				 cb_null.getItems().addAll("");
		Host_Name_format.add(cb_null, 1, 1); // Category in column 2, row 2
		 
		ComboBox<String> Temeh = new ComboBox<String>();
				 Temeh.setVisible(false);
				 Temeh.resize(Width, Height);
				 Temeh.getItems().addAll("3","2","1","0","-1","-2","-3","-4");
		Host_Name_format.add(Temeh, 1, 1); // Category in column 2, row 2
	
		ComboBox<String> Meganit = new ComboBox<String>();
				 Meganit.setVisible(false);
				 Meganit.resize(Width, Height);
				 Meganit.getItems().addAll("2","1","0","-1","-2");
		Host_Name_format.add(Meganit, 1, 1); // Category in column 2, row 2

		ComboBox<String> Zfoni_Dromi = new ComboBox<String>();
				 Zfoni_Dromi.setVisible(false);
				 Zfoni_Dromi.resize(Width, Height);
				 Zfoni_Dromi.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17");
		Host_Name_format.add(Zfoni_Dromi, 1, 1); // Category in column 2, row 2

		ComboBox<String> Bor = new ComboBox<String>();
				 Bor.setVisible(false);
				 Bor.resize(Width, Height);
				 Bor.getItems().addAll("ד","ג","ב","א","-5");
		Host_Name_format.add(Bor, 1, 1); // Category in column 2, row 2
				 
				 Building_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
				 {
					 public void changed(ObservableValue<? extends String> observable,String oldValue, String Value) 
					 {
						 if(Value.equals("תמך"))
						 {
							 Meganit.setVisible(false);
							 Meganit.setValue("");
							 
							 Bor.setVisible(false);
							 Bor.setValue("");
							 
							 Zfoni_Dromi.setVisible(false);
							 Zfoni_Dromi.setValue("");
							 
							 cb_null.setVisible(false);
							 cb_null.setValue("");
							 
							 Temeh.setVisible(true);
						 }
						 if(Value.equals("מרגנית"))
						 {
							 Bor.setVisible(false);
							 Bor.setValue("");
							 
							 Zfoni_Dromi.setVisible(false);
							 Zfoni_Dromi.setValue("");
							 
							 cb_null.setVisible(false);
							 cb_null.setValue("");
							 
							 Temeh.setVisible(false);
							 Temeh.setValue("");
							 
							 Meganit.setVisible(true);
						 }
						 if(Value.equals("צפוני") || Value.equals("דרומי"))
						 {
							 Bor.setVisible(false);
							 Bor.setValue("");
							 
							 cb_null.setVisible(false);
							 cb_null.setValue("");
							 
							 Temeh.setVisible(false);
							 Temeh.setValue("");
							 
							 Meganit.setVisible(false);
							 Meganit.setValue("");
							 
							 Zfoni_Dromi.setVisible(true);
						 }
						 if(Value.equals("בור"))
						 {
							 cb_null.setVisible(false);
							 cb_null.setValue("");
							 
							 Temeh.setVisible(false);
							 Temeh.setValue("");
							 
							 Meganit.setVisible(false);
							 Meganit.setValue("");
							 
							 Zfoni_Dromi.setVisible(false);
							 Zfoni_Dromi.setValue("");
							 
							 Bor.setVisible(true);
						 }
					 }
				 });

		
		Text Host_Name = new Text("Host Name : ");
		Host_Name_format.add(Host_Name, 0, 2); // Category in column 1, row 3
				
		TextField Host_Name_Text = new TextField("");
				  Host_Name_Text.setPrefWidth(Width);
		Host_Name_format.add(Host_Name_Text, 1, 2); // Category in column 2, row 3
		
		/*  ------------------------- ip format grid  --------------------------*/
		Label ip_address = new Label("IP address : ");
			  ip_address.setPrefWidth(105);
		ip_format_grid.add(ip_address, 0, 0); // Category in column 1, row 1
			
		Text dote1 = new Text(" - ");
		Text dote2 = new Text(" - ");
		Text dote3 = new Text(" - ");
		
		TextField oct1 = new TextField("");
				  oct1.setPrefWidth(ip);
				  oct1.textProperty().addListener(new ChangeListener<String>() 
					{
						public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) 
						{
							if (oct1.getText().length() > 3) 
							{
								String s = oct1.getText().substring(0, 3);
								oct1.setText(s);
							}
						}
					});
		ip_format_grid.add(oct1, 1, 0); // Category in column 2, row 1
		ip_format_grid.add(dote1, 2, 0); // Category in column 3, row 1
		
		
		TextField oct2 = new TextField("");
				  oct2.setPrefWidth(ip);
				  oct2.textProperty().addListener(new ChangeListener<String>() 
					{
						public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) 
						{
							if (oct2.getText().length() > 3) 
							{
								String s = oct2.getText().substring(0, 3);
								oct2.setText(s);
							}
						}
					});
		ip_format_grid.add(oct2, 3, 0); // Category in column 4, row 1
		ip_format_grid.add(dote2, 4, 0); // Category in column 5, row 1


		TextField oct3 = new TextField("");
				  oct3.setPrefWidth(ip);
				  oct3.textProperty().addListener(new ChangeListener<String>() 
					{
						public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) 
						{
							if (oct3.getText().length() > 3) 
							{
								String s = oct3.getText().substring(0, 3);
								oct3.setText(s);
							}
						}
					});
		ip_format_grid.add(oct3, 5, 0); // Category in column 6, row 1
		ip_format_grid.add(dote3, 6, 0); // Category in column 7, row 1
		

		TextField oct4 = new TextField("");
				  oct4.setPrefWidth(ip);
				  oct4.textProperty().addListener(new ChangeListener<String>() 
				  	{
					  public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) 
					  {
						  if (oct4.getText().length() > 3) 
						  {
							  String s = oct4.getText().substring(0, 3);
							  oct4.setText(s);
						  }
					  }
				  	});
		ip_format_grid.add(oct4, 7, 0); // Category in column 8, row 1

		/*  ------------------------- switch type selection  --------------------------*/
		Text Switch_Category = new Text("Switch Type : ");
		switch_grid.add(Switch_Category, 0, 0); // Category in column 1, row 1 
		
		ComboBox<String> switch_type = new ComboBox<String>();
						 switch_type.resize(Width, Height);
				 		 switch_type.getItems().addAll("Cisco Switch","Cisco Router","Cisco F.W","Cisco L2+L3","Juniper");
		switch_grid.add(switch_type, 1, 0); // Category in column 2, row 1
		
		Text series = new Text("Series : ");
		switch_grid.add(series, 0, 1); // Category in column 1, row 2
		
		ComboBox<String> cb_null2= new ComboBox<String>();
						 cb_null2.getItems().addAll("");
						 cb_null2.setVisible(true);
						 cb_null2.resize(Width, Height);
		switch_grid.add(cb_null2, 1, 1); // Category in column 2, row 2
				 		 
		ComboBox<String> Cisco_Switch = new ComboBox<String>();
						 Cisco_Switch.setVisible(false);
						 Cisco_Switch.resize(Width, Height);
						 Cisco_Switch.getItems().addAll("Cisco 2950 series","Cisco 2960 series","Cisco 2970 series");
		switch_grid.add(Cisco_Switch, 1, 1); // Category in column 2, row 2
		
		ComboBox<String> Cisco_Router = new ComboBox<String>();
						 Cisco_Router.setVisible(false);
						 Cisco_Router.resize(Width, Height);
						 Cisco_Router.getItems().addAll("Cisco 2600 series","Cisco 2700 series",
														"Cisco 2800 series","Cisco 2900 series",
														"Cisco 3800 series","Cisco 3900 series","Cisco 800 series");
		switch_grid.add(Cisco_Router, 1, 1); // Category in column 2, row 2
		
		ComboBox<String> Cisco_FW = new ComboBox<String>();
						 Cisco_FW.setVisible(false);
						 Cisco_FW.resize(Width, Height);
						 Cisco_FW.getItems().addAll("ASA 5510-k9","ASA 5515-k9","ASA 5515-X","ASA 5520-k9","ASA 5525-X","ASA 5545-X","ASA 5555-X");
		switch_grid.add(Cisco_FW, 1, 1); // Category in column 2, row 2
		
		ComboBox<String> Cisco_L2_L3 = new ComboBox<String>();
						 Cisco_L2_L3.setVisible(false);
						 Cisco_L2_L3.resize(Width, Height);
						 Cisco_L2_L3.getItems().addAll("Cisco 4500 series","Cisco 4500X series","Cisco 6500 series",
													   "Cisco 3750 series","Cisco 3850 series","Cisco N4K",
						 							   "Cisco N5K","Cisco N6K","Cisco N7K","Cisco N9K");
		switch_grid.add(Cisco_L2_L3, 1, 1); // Category in column 2, row 2
		
		
	    // load the image
	    FlowPane ASA = new FlowPane();
		 		 ASA.setPadding(new Insets(5, 10, 5, 10));
		 		 ASA.setVgap(4);
		 		 ASA.setHgap(4);
		 		 ASA.setPrefSize(100, 100);; // preferred width allows for two columns
		 		 ASA.setStyle("-fx-background-color: DAE6F3;");

		 ImageView pages[] = new ImageView[2];
		 for (int i=1; i<=2; i++) 
		 {
			 Image image = new Image(AddItem.class.getResourceAsStream("../asa/ASA_"+i+".jpg"));
			 pages[i-1] = new ImageView(image);
			 pages[i-1].setFitHeight(40);
			 pages[i-1].setFitWidth(100);
			 ASA.getChildren().add(pages[i-1]);
		 }
		 ASA.setVisible(false);
		
		switch_type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
		 {
			 public void changed(ObservableValue<? extends String> observable,String oldValue, String Value) 
			 { 
				 if(Value.equals("Cisco Switch"))
				 {	
					 Cisco_FW.setVisible(false);
					 Cisco_FW.setValue("");
					 
					 Cisco_Router.setVisible(false);
					 Cisco_Router.setValue("");
					 
					 Cisco_L2_L3.setVisible(false);
					 Cisco_L2_L3.setValue("");
					 
					 cb_null2.setVisible(false);
					 cb_null2.setValue("");
					 
					 Cisco_Switch.setVisible(true);
					 ASA.setVisible(false);
				 }
				 if(Value.equals("Cisco Router"))
				 {
					 Cisco_FW.setVisible(false);
					 Cisco_FW.setValue("");
					 
					 Cisco_L2_L3.setVisible(false);
					 Cisco_L2_L3.setValue("");
					 
					 cb_null2.setVisible(false);
					 cb_null2.setValue("");
					 
					 Cisco_Switch.setVisible(false);
					 Cisco_Switch.setValue("");
					 
					 Cisco_Router.setVisible(true);
					 ASA.setVisible(false);
				 }
				 if(Value.equals("Cisco F.W"))
				 {
					 Cisco_Router.setVisible(false);
					 Cisco_Router.setValue("");
					 
					 Cisco_L2_L3.setVisible(false);
					 Cisco_L2_L3.setValue("");
					 
					 cb_null2.setVisible(false);
					 cb_null2.setValue("");
					 
					 Cisco_Switch.setVisible(false);
					 Cisco_Switch.setValue("");
					 
					 Cisco_FW.setVisible(true);
					 ASA.setVisible(true);
				 }
				 if(Value.equals("Cisco L2+L3"))
				 {
					 Cisco_FW.setVisible(false);
					 Cisco_FW.setValue("");
					 
					 Cisco_Router.setVisible(false);
					 Cisco_Router.setValue("");
					 
					 cb_null2.setVisible(false);
					 cb_null2.setValue("");
					 
					 Cisco_Switch.setVisible(false);
					 Cisco_Switch.setValue("");
					 
					 Cisco_L2_L3.setVisible(true);
					 ASA.setVisible(false);
				 }
			 }
		 });
		
		Button add = new Button ("הוסף");
			   add.setOnMouseClicked(new EventHandler<MouseEvent>() 
			   {
				   @Override
				   public void handle(MouseEvent e)
				   {
					   if(check_Validation(oct1,oct2,oct3,oct4) == true)
					   {
						 //if(check_comboBox() == true)
						 //if(exist_ip == false)
						 //add
					   }
					   else
					   {
						   Stage popup_window=new Stage();
						   		 popup_window.initModality(Modality.APPLICATION_MODAL);
						   		 popup_window.setTitle("This is a pop up window");

						   Label label1= new Label("you must eneter a ip address or the ip address dont correct");

						   Button button1= new Button("ok");       
								  button1.setOnMouseClicked(new EventHandler<MouseEvent>() 
								   {
									   @Override
									   public void handle(MouseEvent e)
									   {
										   popup_window.close();
									   }
								   });

						   VBox layout= new VBox(5);        
						   		layout.getChildren().addAll(label1, button1);     
						   		layout.setAlignment(Pos.CENTER);

						   Scene scene1= new Scene(layout, 150 , 50);

						   popup_window.setScene(scene1);     
						   popup_window.showAndWait();
						   
					   }
					   Network_Topology.root.setDisable(false);
					   add_item_stage.close();
				   }
			   });
			   
		Button clear = new Button("נקה");
		
		HBox buttons = new HBox(10);
			 buttons.setAlignment(Pos.CENTER);
			 buttons.getChildren().addAll(clear,add);
		
		vbox_addItem.getChildren().addAll(Host_Name_format,ip_format_grid,switch_grid,ASA,buttons);
		add_item_root.getChildren().add(vbox_addItem);
		add_item_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		add_item_stage.setScene(add_item_scene);
		add_item_stage.setResizable(false);
		add_item_stage.show();
	}


	protected boolean check_Validation(TextField oct1, TextField oct2, TextField oct3, TextField oct4) 
	{
		int o1,o2,o3,o4;

		if(oct1.getText().equals("") || oct2.getText().equals("") || oct3.getText().equals("") || oct4.getText().equals("")) return false;
		else
		{
			if(oct1.getText().matches("[0-9]+") && oct2.getText().matches("[0-9]+") && oct3.getText().matches("[0-9]+") && oct4.getText().matches("[0-9]+"))
			{
					o1 = Integer.parseInt(oct1.getText());
					o2 = Integer.parseInt(oct2.getText());
					o3 = Integer.parseInt(oct3.getText());
					o4 = Integer.parseInt(oct4.getText());

					if(o1 >= 0 && o1 < 256 && o2 >= 0 && o2 < 256 && o3 >= 0 && o3 < 256 && o4 >= 0 && o4 < 256) return true;
					else return false;
			}
		}
		return false;
	}
	
	
}
