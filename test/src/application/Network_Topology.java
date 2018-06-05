package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.sql.*;


public class Network_Topology
{
	public static final double btn_Height=25;
	public static final double btn_Width=80;
	public static final double ip=35;
	
	public static BorderPane root = new BorderPane();
	
	private Scene scene = new Scene(root,500,500);
	private HBox hbox = new HBox();
	
	public Network_Topology()
	{
		make_Main_Manu();
	}
	
	
	public Scene getScene()
	{
		return this.scene;
	}
	
	
	public void make_Main_Manu()
	{
		Button AddItem = new Button("הוסף פריט");
				AddItem.setVisible(true);
				AddItem.setPrefSize(btn_Width, btn_Height);
				AddItem.setOnMouseClicked(new EventHandler<MouseEvent>() 
				  {
					@Override
					public void handle(MouseEvent e)
					  {
						AddItem();
					  }
				  });
				
		Button EditItem = new Button("ערוך פריט");
				EditItem.setVisible(true);
				EditItem.setPrefSize(btn_Width, btn_Height);
				EditItem.setOnMouseClicked(new EventHandler<MouseEvent>() 
				{
					@Override
					public void handle(MouseEvent e)
					{
						EditItem();
					}
				});
				
		Button RemoveItem = new Button("הסר פריט");
				RemoveItem.setVisible(true);
				RemoveItem.setPrefSize(btn_Width, btn_Height);
				RemoveItem.setOnMouseClicked(new EventHandler<MouseEvent>() 
				  {
					@Override
					public void handle(MouseEvent e)
					  {
						//check_validation()
						//check_if_exist()
					  }
				  });
				
		Button Connect = new Button("היתחבר");
				Connect.setVisible(true);
				Connect.setPrefSize(btn_Width, btn_Height);
				Connect.setOnMouseClicked(new EventHandler<MouseEvent>() 
				  {
					@Override
					public void handle(MouseEvent e)
					  {
						Stage connect_windows = new Stage();
							  connect_windows.initModality(Modality.APPLICATION_MODAL);
							  connect_windows.setTitle("Connect");

						Label ip_address = new Label("IP address : ");
							  ip_address.setPrefWidth(105);
							  
							Text dote1 = new Text(" - ");
							Text dote2 = new Text(" - ");
							Text dote3 = new Text(" - ");
							Text dote4 = new Text(" -> ");
							
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

						Button Connect= new Button("Connect");       
								Connect.setOnMouseClicked(new EventHandler<MouseEvent>() 
								{
									@Override
									public void handle(MouseEvent e)
									{
										     //check_Validation(oct1,oct2,oct3,oct4) == true)
											 //if(check_comboBox() == true)
											 //if(exist_ip == false)
											 //add
										connect_windows.close();
									}
								});

						HBox main_layout= new HBox(5);        
							 main_layout.getChildren().addAll(ip_address,oct1,dote1,oct2,dote2,oct3,dote3,oct4,dote4,Connect);     
							 main_layout.setAlignment(Pos.CENTER);

						Scene connetc_sence = new Scene(main_layout, 500 , 100);

						connect_windows.setScene(connetc_sence);     
						connect_windows.showAndWait(); 
					  }
				  });
		   
		Button Back = new Button("חזור");
				Back.setVisible(true);
				Back.setPrefSize(btn_Width, btn_Height);
				Back.setOnMouseClicked(new EventHandler<MouseEvent>() 
				  {
					@Override
					public void handle(MouseEvent e)
					  {
						Main.primaryStage_main.show();
						Main.nt_stage.hide();
					  }
				  });
				
		hbox.getChildren().addAll(AddItem,EditItem,RemoveItem,Connect,Back);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.TOP_RIGHT);
		
		root.setTop(hbox);
	}
	
	protected void EditItem() 
	{
		root.setDisable(true);
		
		EditItem edititem = new EditItem();
		
		root.setDisable(false);
		
	}


	public void AddItem()
	{
		root.setDisable(true);
		
		AddItem additem = new AddItem();
		
		root.setDisable(false);
	}
	
}
