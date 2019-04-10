package com.masterdev.student.views.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.jfoenix.controls.JFXButton;

import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.animations.DashboardButtonAnimations;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Warehouse;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


public class DashboardController extends Controller implements Initializable{
	//Methods to animate the sidebar buttons-----------------------------------------------------
	
	//*** Declarating scene controllers ***
	private HomeController homeController;
	//private SalesController salesController;
	private PersonalController personalController;
	//private InventoryController inventoryController;
	private WarehouseController resourcesController;
	//private StatisticsController statisticsController;
	//private ProvidersController providersController;
	//private ProjectsController projectsController;
	//private DocumentsController documentsController;
	
	//Dashboard button elements
	@FXML Button btnDashboard;
	@FXML HBox hboxDashboard;
	@FXML FontAwesomeIconView icoDashboard;
	//Personal button elements
	@FXML Button btnSales;
	@FXML HBox hboxSales;
	@FXML FontAwesomeIconView icoSales;
	//Personal button elements
	@FXML Button btnPersonal;
	@FXML HBox hboxPersonal;
	@FXML FontAwesomeIconView icoPersonal;
	//Inventory button elements
	@FXML Button btnInventory;
	@FXML HBox hboxInventory;
	@FXML FontAwesomeIconView icoInventory;
	//Resources button elements
	@FXML Button btnResources;
	@FXML HBox hboxResources;
	@FXML FontAwesomeIconView icoResources;
	//Statistics button elements
	@FXML Button btnStatistics;
	@FXML HBox hboxStatistics;
	@FXML FontAwesomeIconView icoStatistics;
	//Providers button elements
	@FXML Button btnProviders;
	@FXML HBox hboxProviders;
	@FXML FontAwesomeIconView icoProviders;
	//Projects button elements
	@FXML Button btnProjects;
	@FXML HBox hboxProjects;
	@FXML FontAwesomeIconView icoProjects;
	//Documents button elements
	@FXML Button btnDocuments;
	@FXML HBox hboxDocuments;
	@FXML FontAwesomeIconView icoDocuments;
	
	//------------------------------------------------
	//Methods for searching
	@FXML TextField txtSearch;
	
	//Search button elements
	@FXML Button btnSearch;
	@FXML FontAwesomeIconView icoSearch;
	//Notification button elements
	@FXML Button btnNotification;
	@FXML FontAwesomeIconView icoNotification;
	//Settings button elements
	@FXML Button btnSettings;
	@FXML FontAwesomeIconView icoSettings;
	//Help button elements
	@FXML Button btnHelp;
	@FXML FontAwesomeIconView icoHelp;
	
	//Main view elements
	@FXML ScrollPane sclMainView;
	
	//Popup menus
	private PopOver salesPopOver;
	private PopOver personalPopOver;
	private PopOver documentsPopOver;
	private PopOver notificationPopOver;
	
	//A flag to manage popOver graphic menus
	private Boolean optionSelected = false;
	
	//Mnemonics
	private Mnemonic salesMnemonic;
	
	
	//--------------------------------------------------------------- SETTING CONTROLLERS --------------------------------------------------//
	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}
	
	public void setPersonalController(PersonalController personalController) {
		this.personalController = personalController;
	}
	
	//--------------------------------------------------------------- GETTING CONTROLLERS --------------------------------------------------//
	public HomeController getHomeController() {
		return homeController;
	}
	
	public PersonalController getPersonalController() {
		return personalController;
	}
	
	//--------------------------------------------------------------- MANAGING FLAGS --------------------------------------------------//
	public Boolean getOptionSelected() {
		return optionSelected;
	}
	
	public void setOptionSelected(Boolean optionSelected) {
		this.optionSelected = optionSelected;
	}
	
	//--------------------------------------------------------------- MANAGING MNEMONICS --------------------------------------------------//
	public Mnemonic getSalesMnemonic() {
		return salesMnemonic;
	}
	
	public void setSalesMnemonic(Mnemonic salesMnemonic) {
		this.salesMnemonic = salesMnemonic;
	}
	
	
	//--------------------------------------------------------------- LOADING VIEWS -------------------------------------------------------//
	public void loadHomeView() {
		FXMLLoader loader = null;
		HomeController hc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
			StackPane node;
			node = (StackPane) loader.load();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			sclMainView.setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
		hc = (HomeController) loader.getController();
		this.setHomeController(hc);
		this.getHomeController().setDashboardController(this);
		
	}
	
	public void loadSalesFormView() {
		FXMLLoader loader = null;
		//HomeController hc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesForm.fxml"));
			StackPane node;
			node = (StackPane) loader.load();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			sclMainView.setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//hc = (HomeController) loader.getController();
		//this.setHomeController(hc);
	}
	
	public void loadPersonalView() {
		FXMLLoader loader = null;
		PersonalController pc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/personal.fxml"));
			StackPane node;
			node = (StackPane) loader.load();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			sclMainView.setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pc = (PersonalController) loader.getController();
		this.setPersonalController(pc);
	}
	
	public void loadAddDepartmentFormView() {
		FXMLLoader loader = null;
		//DepartmentFormController dfc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/departmentForm.fxml"));
			StackPane node;
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 800, 600);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("POS");
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//dfc = (DepartmentFormController) loader.getController();
		//this.setHomeController(hc);
	}
	
	public void loadWarehouseView() {
		FXMLLoader loader = null;
		HomeController hc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/warehouse.fxml"));
			BorderPane node;
			node = (BorderPane) loader.load();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			sclMainView.setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
		hc = (HomeController) loader.getController();
		this.setHomeController(hc);
	}
	
	//--------------------------------------------------------------- INITIALISING COMPONENTS ----------------------------------------------//
	@FXML ComboBox<String> cmbAccount;
	@FXML ImageView imgAccount;
	
	public void initialize(URL location, ResourceBundle resources) {
		/*Initialising components from the upper bar (user-options */
		TextFieldMethods tfm = new TextFieldMethods();
		ComboBoxMethods cbm = new ComboBoxMethods();
		//ImageViewMethods ivm = new ImageViewMethods();
		
		//Adding word suggestions for auto-completion. ************** STILL IMPROVABLE ******************
		String[] wordSuggestions = {"Word", "Office", "Microsoft", "HP", "Apple", "Resident Evil", "IOS", "Android", "Google", "Go", "Amazon", "Amazing", "Facebook", "WhatsApp", "WWW"};
		tfm.addWordSuggestions(txtSearch, wordSuggestions);
		
		//Adding user options to the combo box
		String[] items = {"Configurar cuenta", "Cambiar de cuenta", "Cerrar sesión"};
		cbm.addItems(cmbAccount, items);
		cmbAccount.getSelectionModel().selectedItemProperty()
	    .addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
	        	switch(newValue) {
	        		case "Configurar cuenta": ;
	        			break;
	        		case "Cambiar de cuenta": ;
        				break;
	        		case "Cerrar sesión": /*Dialogs d = new Dialogs();
	        			System.out.println(d.getFlag()+" Sí paso por aquí");
        				d.acceptCancelDialog("Confirmar cierre de sesión",
    					"¿Estás seguro de que desea cerrar sesión?",
    					(StackPane)Dashboard.getStage().getScene().getRoot());
        				if(d.getFlag()) {*/
        					Dashboard.getStage().close();
        					System.exit(0);
        				//}
        				break;
	        	}
	            
	        }
	    });
		
		//Making a rounded user image
		//Integer radius = 20;
		//ivm.makeRoundImage(imgAccount, radius);
		
		//Charging the home scene
		clickedDBButton();
		
		//Adding TooltipTexts to the buttons
		btnSearch.setTooltip(new Tooltip("Buscar"));
		btnNotification.setTooltip(new Tooltip("Notificaciones"));
		btnSettings.setTooltip(new Tooltip("Ajustes"));
		btnHelp.setTooltip(new Tooltip("Ayuda"));
		
		// ****** Initialising popup menus ******
		//Initialising sales' popover
		JFXButton sale = new JFXButton("Realizar ventas");
		sale.setId("pop-over-buttons");
		sale.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadSalesFormView();
				salesPopOver.hide();
			}
		});
		JFXButton history = new JFXButton("Historial de ventas");
		history.setId("pop-over-buttons");
		VBox salesVBox = new VBox(sale, history);
		salesVBox.setId("pop-over-vbox");
		salesPopOver = new PopOver(salesVBox);
		salesPopOver.setDetachable(false);
		salesPopOver.setArrowSize(0);
		salesPopOver.setCornerRadius(0);
		salesPopOver.setId("pop-over");
		
		//Initialising personal's popover
		JFXButton departmentList = new JFXButton("Lista de departamentos");
		departmentList.setId("pop-over-buttons");
		JFXButton employeeList = new JFXButton("Lista de empleados");
		employeeList.setId("pop-over-buttons");
		employeeList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadPersonalView();
				personalPopOver.hide();
			}
		});
		JFXButton addDepartment = new JFXButton("Agregar departamentos");
		addDepartment.setId("pop-over-buttons");
		addDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadAddDepartmentFormView();
				personalPopOver.hide();
			}
		});
		JFXButton addEmployee = new JFXButton("Agregar empleados");
		addEmployee.setId("pop-over-buttons");
		VBox personalVBox = new VBox(departmentList, employeeList, addDepartment, addEmployee);
		personalVBox.setId("pop-over-vbox");
		personalPopOver = new PopOver(personalVBox);
		personalPopOver.setDetachable(false);
		personalPopOver.setArrowSize(0);
		personalPopOver.setCornerRadius(0);
		personalPopOver.setId("pop-over");
		
		//Initialising Documents' popover
		JFXButton ticketHistory = new JFXButton("Historial de tickets");
		ticketHistory.setId("pop-over-buttons");
		JFXButton invoiceHistory = new JFXButton("Historial de facturas");
		invoiceHistory.setId("pop-over-buttons");
		VBox documentsVBox = new VBox(ticketHistory, invoiceHistory);
		documentsVBox.setId("pop-over-vbox");
		documentsPopOver = new PopOver(documentsVBox);
		documentsPopOver.setDetachable(false);
		documentsPopOver.setArrowSize(0);
		documentsPopOver.setCornerRadius(0);
		documentsPopOver.setId("pop-over");
		
		
		
		//*******Initialising Notifications pane*******
		
		FontAwesomeIconView passedOutIcon = new FontAwesomeIconView();
		passedOutIcon.setGlyphName("EXCLAMATION_CIRCLE");
		passedOutIcon.setId("notification-glyph");
		Label passedOutHeader = new Label("Productos caducados");
		passedOutHeader.setId("notification-header");
		Label passedOutContent = new Label("10 lotes han caducado");
		passedOutContent.setId("notification-content");
		VBox passedOutText = new VBox(passedOutHeader, passedOutContent);
		passedOutText.setId("notification-text");
		HBox passedOut = new HBox(passedOutIcon, passedOutText);
		passedOut.setId("notification");
		passedOut.setOnMouseEntered(e -> {
			passedOut.setStyle("-fx-background-color: #eee;");
		});
		passedOut.setOnMouseExited(e -> {
			passedOut.setStyle("-fx-background-color: transparent;");
		});
		passedOut.setOnMousePressed(e -> {
			passedOut.setStyle("-fx-background-color: #ddd;");
		});
		passedOut.setOnMouseReleased(e -> {
			passedOut.setStyle("-fx-background-color: #eee;");
		});
		passedOut.setOnMouseClicked(e -> {
		});
		
		FontAwesomeIconView perishablesIcon = new FontAwesomeIconView();
		perishablesIcon.setGlyphName("EXCLAMATION_TRIANGLE");
		perishablesIcon.setId("notification-glyph");
		Label perishablesHeader = new Label("Próximos a caducar");
		perishablesHeader.setId("notification-header");
		Label perishablesContent = new Label("32 lotes próximos a caducar");
		perishablesContent.setId("notification-content");
		VBox perishablesText = new VBox(perishablesHeader, perishablesContent);
		perishablesText.setId("notification-text");
		HBox perishables = new HBox(perishablesIcon, perishablesText);
		perishables.setId("notification");
		perishables.setOnMouseEntered(e -> {
			perishables.setStyle("-fx-background-color: #eee;");
		});
		perishables.setOnMouseExited(e -> {
			perishables.setStyle("-fx-background-color: transparent;");
		});
		perishables.setOnMousePressed(e -> {
			perishables.setStyle("-fx-background-color: #ddd;");
		});
		perishables.setOnMouseReleased(e -> {
			perishables.setStyle("-fx-background-color: #eee;");
		});
		perishables.setOnMouseClicked(e -> {
		});
		
		FontAwesomeIconView noStockIcon = new FontAwesomeIconView();
		noStockIcon.setGlyphName("EXCLAMATION_CIRCLE");
		noStockIcon.setId("notification-glyph");
		Label noStockHeader = new Label("Stock nulo");
		noStockHeader.setId("notification-header");
		Label noStockContent = new Label("No hay más unidades de 8 lotes en almacén");
		noStockContent.setId("notification-content");
		VBox noStockText = new VBox(noStockHeader, noStockContent);
		noStockText.setId("notification-text");
		HBox noStock = new HBox(noStockIcon, noStockText);
		noStock.setId("notification");
		noStock.setOnMouseEntered(e -> {
			noStock.setStyle("-fx-background-color: #eee;");
		});
		noStock.setOnMouseExited(e -> {
			noStock.setStyle("-fx-background-color: transparent;");
		});
		noStock.setOnMousePressed(e -> {
			noStock.setStyle("-fx-background-color: #ddd;");
		});
		noStock.setOnMouseReleased(e -> {
			noStock.setStyle("-fx-background-color: #eee;");
		});
		noStock.setOnMouseClicked(e -> {
		});
		
		FontAwesomeIconView minStockIcon = new FontAwesomeIconView();
		minStockIcon.setGlyphName("EXCLAMATION_TRIANGLE");
		minStockIcon.setId("notification-glyph");
		Label minStockHeader = new Label("Mínimo stock");
		minStockHeader.setId("notification-header");
		Label minStockContent = new Label("5 lotes han alcanzado el mínimo stock");
		minStockContent.setId("notification-content");
		VBox minStockText = new VBox(minStockHeader, minStockContent);
		minStockText.setId("notification-text");
		HBox minStock = new HBox(minStockIcon, minStockText);
		minStock.setId("notification");
		minStock.setOnMouseEntered(e -> {
			minStock.setStyle("-fx-background-color: #eee;");
		});
		minStock.setOnMouseExited(e -> {
			minStock.setStyle("-fx-background-color: transparent;");
		});
		minStock.setOnMousePressed(e -> {
			minStock.setStyle("-fx-background-color: #ddd;");
		});
		minStock.setOnMouseReleased(e -> {
			minStock.setStyle("-fx-background-color: #eee;");
		});
		minStock.setOnMouseClicked(e -> {
		});
		
		VBox notificationVBox = new VBox(passedOut, perishables, noStock, minStock);
		notificationVBox.setId("pop-over-vbox");
		notificationPopOver = new PopOver(notificationVBox);
		notificationPopOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		notificationPopOver.setDetachable(false);
		notificationPopOver.setId("pop-over");
		
		//Initiaising an accelerator
		KeyCombination kp = new KeyCharacterCombination("V", KeyCombination.ALT_DOWN);
		Mnemonic mn = new Mnemonic(btnSales, kp);
		this.setSalesMnemonic(mn);
	}
	
	
	//--------------------------------------------------------------- SIDEBAR ANIMATIONS ---------------------------------------------------//
	
	//Animating the dashboard button
		@FXML
		protected void clickedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			btnDashboard.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			loadHomeView();
		}
		
		@FXML
		protected void pressedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void releasedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void enteredDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void exitedDBButton() {
			if(!btnDashboard.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			}
		}
		
		//Animating the sales button
		
				@FXML
				protected void clickedSalesButton() {
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.clickedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
					btnSales.requestFocus();
					/*Changing the other buttons to their original state*/
					dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
					dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
					dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
					dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
					dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
					dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
					dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
					dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
					
					//loadSaleslView();
					if(!getOptionSelected())
						salesPopOver.show(hboxSales);
				}
				
				@FXML
				protected void pressedSalesButton() {
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.pressedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				}
				
				@FXML
				protected void releasedSalesButton() {
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.releasedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				}
				
				@FXML
				protected void enteredSalesButton() {
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.enteredButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				}
				
				@FXML
				protected void exitedSalesButton() {
					if(!btnSales.isFocused())
					{
						DashboardButtonAnimations dba = new DashboardButtonAnimations();
						dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
					}
				}
	
	//Animating the personal button
		
		@FXML
		protected void clickedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			btnPersonal.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			//loadPersonalView();
			personalPopOver.show(hboxPersonal);
		}
		
		public void responding(String message) {
			System.out.println(message);
			btnPersonal.setText(message);
		}
		
		@FXML
		protected void pressedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void releasedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void enteredPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void exitedPersButton() {
			if(!btnPersonal.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			}
		}
	
		//Animating the inventory button
		
			@FXML
			protected void clickedInvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.clickedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
				btnInventory.requestFocus();
				/*Changing the other buttons to their original state*/
				dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
				dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
				dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
				dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
				Warehouse resources = new Warehouse();
				sclMainView.setContent(resources.showResources());
			}
			
			@FXML
			protected void pressedInvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.pressedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			}
			
			@FXML
			protected void releasedInvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.releasedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			}
			
			@FXML
			protected void enteredInvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.enteredButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			}
			
			@FXML
			protected void exitedInvButton() {
				if(!btnInventory.isFocused())
				{
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
				}
			}
		
	//Animating the resources button
	
		@FXML
		protected void clickedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			btnResources.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			Warehouse resources = new Warehouse();
			sclMainView.setContent(resources.showResources());
		}
		
		@FXML
		protected void pressedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void releasedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void enteredResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void exitedResButton() {
			if(!btnResources.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			}
		}
		
	//Animating the statistics button
	
		@FXML
		protected void clickedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			btnStatistics.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void pressedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void releasedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void enteredStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void exitedStatButton() {
			if(!btnStatistics.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			}
		}
	
		//Animating the providers button
		
			@FXML
			protected void clickedProvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.clickedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
				btnProviders.requestFocus();
				/*Changing the other buttons to their original state*/
				dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
				dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
				dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
				dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			}
			
			@FXML
			protected void pressedProvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.pressedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			}
			
			@FXML
			protected void releasedProvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.releasedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			}
			
			@FXML
			protected void enteredProvButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.enteredButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			}
			
			@FXML
			protected void exitedProvButton() {
				if(!btnProviders.isFocused())
				{
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
				}
			}
		
		
	//Animating the projects button
	
		@FXML
		protected void clickedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			btnProjects.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void pressedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void releasedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void enteredProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void exitedProjButton() {
			if(!btnProjects.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			}
		}
		
	//Animating the documents button
		
		@FXML
		protected void clickedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			btnDocuments.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			
			documentsPopOver.show(hboxDocuments);
		}
		
		@FXML
		protected void pressedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void releasedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void enteredDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void exitedDocButton() {
			if(!btnDocuments.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			}
		}
	
	//-------------------------------------------------------- UPPER BAR (USER OPTIONS) ANIMATIONS --------------------------------------------//
	
	@FXML
	protected void search() {
		if(txtSearch.getText().equals("Buscar"))
			cleanTxtSearch();
		else
		{
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de búsqueda",
					"No se encontraron coincidencias.",
					(StackPane)Dashboard.getStage().getScene().getRoot());
		}
	}
	
	private void cleanTxtSearch() {
		txtSearch.setText("");
	}
	
	public TextField getTxtSearch() {
		return txtSearch;
	}

	
	//Search button animations
	@FXML 
	protected void clickedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML 
	protected void pressedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void releasedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void enteredSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void exitedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	//Notification button animations
	@FXML 
	protected void clickedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
		
		notificationPopOver.show(btnNotification);
	}
	
	@FXML 
	protected void pressedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void releasedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void enteredNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void exitedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	//Settings button animations
	@FXML 
	protected void clickedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML 
	protected void pressedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void releasedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void enteredSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void exitedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	//Help button animations
	@FXML 
	protected void clickedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
		try {
		    Desktop.getDesktop().browse(new URL("https://google.com").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
	@FXML 
	protected void pressedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void releasedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void enteredHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void exitedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
}
