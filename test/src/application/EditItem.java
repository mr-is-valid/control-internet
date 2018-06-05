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


public class EditItem
{
	public static final double Height=10;
	public static final double Width=178;
	public static final double ip=35;
	
	VBox vbox_edit_item = new VBox(10);
	HBox hbox_ip_format = new HBox(5);
	
	StackPane edit_item_root = new StackPane();
	Scene edit_item_scene =  new Scene(edit_item_root,500,500);
	Stage edit_item_stage = new Stage();
	
	Connection connect = null;
	Statement statement = null;
	PreparedStatement pst = null;
	ResultSet resultSet = null;
	
	Label ip_address;
	Text dote1,dote2,dote3;
	TextField oct1,oct2,oct3,oct4;
	
	Image image;
	
	public EditItem()
	{
		
		/*  ------------------------- ip format grid  --------------------------*/
		 ip_address = new Label("IP address : ");
		 ip_address.setPrefWidth(105);
			
		 dote1 = new Text(" . ");
		 dote2 = new Text(" . ");
		 dote3 = new Text(" . ");
		
		 oct1 = new TextField("");
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
				  
		
		 oct2 = new TextField("");
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

				  
		 oct3 = new TextField("");
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
		

		 oct4 = new TextField("");
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

		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			//Class.forName("org.sqlite.JDBC");
			
			connect = DriverManager.getConnection("jdbc:ucanaccess://E://program file//workspace//mydatabase.accdb");
			//connect = DriverManager.getConnection("jdbc:sqlite:E:\\program file\\workspace\\mydatabase.sqlite");
			
		} 
		catch (ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Button add = new Button ("חפש");
		add.setOnMouseClicked(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent e)
				{
					if(check_Validation(oct1,oct2,oct3,oct4) == true)
					{
						if(check_exsist(oct1.getText() +"."+ oct2.getText() +"."+ oct3.getText() +"."+ oct4.getText()) == true)//all this is the ip format
						{
							View_Details();
						}
						 else System.out.println("sorry the ip adrees is not exsit");
					 }
					 else  System.out.println("sorry check the validation of the ip input plz");
				}
			});
				   
		Button clear = new Button("נקה");
		
		HBox buttons = new HBox(10);
			 buttons.setAlignment(Pos.CENTER);
			 buttons.getChildren().addAll(clear,add);
			 
			 
		hbox_ip_format.setAlignment(Pos.CENTER);
		hbox_ip_format.getChildren().addAll(ip_address,oct1,dote1,oct2,dote2,oct3,dote3,oct4,buttons);
		
		vbox_edit_item.setAlignment(Pos.TOP_CENTER);
		vbox_edit_item.getChildren().add(hbox_ip_format);
		
		edit_item_root.getChildren().add(vbox_edit_item);
		edit_item_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		edit_item_stage.setScene(edit_item_scene);
		edit_item_stage.setResizable(false);
		edit_item_stage.show();
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
	
	
	protected boolean check_exsist(String IP) 
	{
		try
		{
			if(connect!=null)System.out.println("Connetc to the DB succssfully");

			String query = "SELECT * FROM switch;";

			
			statement = connect.createStatement();
			//pst = connect.prepareStatement(query);
			//resultSet = pst.executeQuery();
			resultSet = statement.executeQuery(query);
			
			
			
			while(resultSet.next()) 
			{
				if(resultSet.getString("IP_Address").equals(IP))return true;
			}

		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}
		return false;
	}
	
	
	public void View_Details()
	{
		System.out.println("Connetc to the DB succssfully and add the new ip address");
		
		 Label ip_address_l = new Label("IP Address : ");
		 	
		 Label Host_Name = new Label("Host Name : ");
		 TextField Host_Name_txt = new TextField("");
		 
		 Label Floor = new Label("Floor : ");
		 ComboBox<String> Floor_txt = new ComboBox<String>();
		 Floor_txt.resize(Width, Height);
		 Floor_txt.getItems().addAll("-4","-3","-2","-1","0","1","2","3","4","5","6","7","8","9","10",
				 					 "11","12","13","14","15","16","17","18","19","20","21","22","23");

		 Label Group = new Label("Bulding : ");
		 TextField Group_txt = new TextField("");
		 
		 Label Type = new Label("Type : ");
		 TextField Type_txt = new TextField("");
		 
		 ComboBox<String> item_type = new ComboBox<String>();
			 			  item_type.setVisible(false);
			 			  item_type.resize(Width, Height);
			 			  item_type.getItems().addAll("2960s24","2960s48");
	
		 Text dote1_1 = new Text(" . ");
		 Text dote2_2 = new Text(" . ");
		 Text dote3_3 = new Text(" . ");
		
		 TextField oct1_1 = new TextField("");
		  oct1_1.setText(oct1.getText());
		  oct1_1.setPrefWidth(ip);
		  oct1_1.textProperty().addListener(new ChangeListener<String>() 
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
				  
		
		 TextField oct2_2 = new TextField("");
		 oct2_2.setText(oct2.getText());
		 oct2_2.setPrefWidth(ip);
		 oct2_2.textProperty().addListener(new ChangeListener<String>() 
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

				  
		 TextField oct3_3 = new TextField("");
		 oct3_3.setText(oct3.getText());
		 oct3_3.setPrefWidth(ip);
		 oct3_3.textProperty().addListener(new ChangeListener<String>() 
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
		

		 TextField oct4_4 = new TextField("");
		 oct4_4.setText(oct4.getText());
		 oct4_4.setPrefWidth(ip);
		 oct4_4.textProperty().addListener(new ChangeListener<String>() 
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

		 
		try
		{
			String ip_add = oct1.getText() +"."+ oct2.getText() +"."+ oct3.getText() +"."+ oct4.getText();

			String query = "SELECT * FROM switch " + "WHERE IP_Address = '" + ip_add + "' ;";

			
			
			statement = connect.createStatement();
			//pst = connect.prepareStatement(query);
			//resultSet = pst.executeQuery();
			resultSet = statement.executeQuery(query);

			
			
			while(resultSet.next()) 
			{
				Host_Name_txt.setText(resultSet.getString("Host_Name"));
				Floor_txt.setValue(resultSet.getString("floorID"));
				Type_txt.setText(resultSet.getString("type"));
			}
			
			
			query =" SELECT group.group_Name FROM [group] INNER JOIN switch ON group.groupID = switch.groupID " + "WHERE (((switch.IP_Address)='" + ip_add + "'))";

			
			statement = connect.createStatement();
			//pst = connect.prepareStatement(query);
			//resultSet = pst.executeQuery();
			resultSet = statement.executeQuery(query);
			
			
			while(resultSet.next()) 
			{
				Group_txt.setText(resultSet.getString("group_Name"));
			}

		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}
		
		FlowPane image_panel = new FlowPane();
				 image_panel.setPadding(new Insets(5, 10, 5, 10));
				 image_panel.setVgap(4);
				 image_panel.setHgap(4);
				 image_panel.setPrefSize(100, 100);; // preferred width allows for two columns
				 image_panel.setStyle("-fx-background-color: DAE6F3;");
				 image_panel.setVisible(false);
				 
		ImageView pages[] = new ImageView[1];
		
		item_type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
		 {
			 public void changed(ObservableValue<? extends String> observable,String oldValue, String Value) 
			 { 
				 image = new Image(AddItem.class.getResourceAsStream("../switchs/"+item_type.getValue()+".jpg"));
				 pages[0] = new ImageView(image);
				 image_panel.getChildren().clear();
				 image_panel.getChildren().add(pages[0]); 
			 }
		 });
		item_type.setVisible(false);
		
	HBox new_ip_formate = new HBox();
		 new_ip_formate.getChildren().addAll(ip_address_l,oct1_1,dote1_1,oct2_2,dote2_2,oct3_3,dote3_3,oct4_4);
		 new_ip_formate.setAlignment(Pos.CENTER_LEFT);
		 new_ip_formate.setPadding(new Insets(0,0,0,10));
		 
	HBox host_name = new HBox();
		 host_name.getChildren().addAll(Host_Name,Host_Name_txt);
		 host_name.setAlignment(Pos.CENTER_LEFT);
		 host_name.setPadding(new Insets(0,0,0,10));
		
	HBox Group_hbox = new HBox();
		 Group_hbox.getChildren().addAll(Group,Group_txt);
		 Group_hbox.setAlignment(Pos.CENTER_LEFT);
		 Group_hbox.setPadding(new Insets(0,0,0,10));
		 
	HBox Floor_hbox = new HBox();
		 Floor_hbox.getChildren().addAll(Floor,Floor_txt);
		 Floor_hbox.setAlignment(Pos.CENTER_LEFT);
		 Floor_hbox.setPadding(new Insets(0,0,0,10));
		 
			 
	HBox type_hbox = new HBox();
		 type_hbox.getChildren().addAll(image_panel,Type,Type_txt,item_type);
		 type_hbox.setAlignment(Pos.CENTER_LEFT);
		 type_hbox.setPadding(new Insets(0,0,0,10));
		    
		Button change = new Button("chanage");
		change.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent e)
			{
				type_hbox.getChildren().remove(Type_txt);
				item_type.setVisible(true);
				image_panel.setVisible(true);
			}
		});
		type_hbox.getChildren().add(change);
		
			 
		vbox_edit_item.getChildren().addAll(new_ip_formate,host_name,type_hbox,Group_hbox,Floor_hbox);
		
		oct1.setDisable(true);
		oct2.setDisable(true);
		oct3.setDisable(true);
		oct4.setDisable(true);
	}
	
}
