package com.masterdev.student.views.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.animations.DashboardButtonAnimations;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.DepartmentAddForm;
import com.masterdev.student.views.DepartmentList;
import com.masterdev.student.views.Home;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.InventoryList;
import com.masterdev.student.views.PersonnelAddForm;
import com.masterdev.student.views.PersonnelList;
import com.masterdev.student.views.SalesForm;
import com.masterdev.student.views.SalesHistory;
import com.masterdev.student.views.WarehouseList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


public class DashboardController implements Initializable{
	//Methods to animate the sidebar buttons-----------------------------------------------------
	
	@FXML Label lblName;
	@FXML Label lblJob;
	
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
	//@FXML TextField txtSearch;
	
	//Search button elements
	//@FXML Button btnSearch;
	//@FXML FontAwesomeIconView icoSearch;
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
	private PopOver inventoryPopOver;
	private PopOver resourcesPopOver;
	private PopOver documentsPopOver;
	private PopOver notificationPopOver;
	
	//A flag to manage popOver graphic menus
	private Boolean optionSelected = false;
	
	//QUICK BUTTONS
	@FXML VBox btnOpenSalesForm;
	@FXML VBox btnOpenInventoryEditForm;
	@FXML VBox btnOpenPriceChecker;
	@FXML VBox btnOpenAddEmployeeForm;
	@FXML VBox btnOpenAddSupplierForm;
	
	//--------------------------------------------------------------- MANAGING FLAGS --------------------------------------------------//
	public Boolean getOptionSelected() {
		return optionSelected;
	}
	
	public void setOptionSelected(Boolean optionSelected) {
		this.optionSelected = optionSelected;
	}
	
	
	//--------------------------------------------------------------- LOADING VIEWS -------------------------------------------------------//
	
	public void loadHomeView() {
		Home view = new Home();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		sclMainView.setContent(node);
	}
	
	public void loadSalesFormView() {
		SalesForm view = new SalesForm();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	public void loadSalesHistoryView() {
		SalesHistory view = new SalesHistory();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	public void loadDepartmentListView() {
		DepartmentList view = new DepartmentList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	public void loadDepartmentAddFormView() {
		DepartmentAddForm view = new DepartmentAddForm();
		view.loadView();
	}
	
	public void loadPersonnelListView() {
		PersonnelList view = new PersonnelList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	public void loadPersonnelAddFormView() {
		PersonnelAddForm view = new PersonnelAddForm();
		view.loadView();
	}
	
	public void loadInventoryListView() {
		InventoryList view = new InventoryList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	public void loadInventoryAddFormView() {
		InventoryAddForm view = new InventoryAddForm();
		view.loadView();
	}
	
	public void loadWarehouseListView() {
		WarehouseList view = new WarehouseList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setContent(node);
	}
	
	//--------------------------------------------------------------- INITIALISING COMPONENTS ----------------------------------------------//
	@FXML ComboBox<String> cmbAccount;
	@FXML ImageView imgAccount;
	
	public void initialize(URL location, ResourceBundle resources) {
		/*Initialising components from the upper bar (user-options */
		//TextFieldMethods tfm = new TextFieldMethods();
		ComboBoxMethods cbm = new ComboBoxMethods();
		//ImageViewMethods ivm = new ImageViewMethods();
		
		//Adding word suggestions for auto-completion. ************** STILL IMPROVABLE ******************
		//String[] wordSuggestions = {"Word", "Office", "Microsoft", "HP", "Apple", "Resident Evil", "IOS", "Android", "Google", "Go", "Amazon", "Amazing", "Facebook", "WhatsApp", "WWW"};
		//tfm.addWordSuggestions(txtSearch, wordSuggestions);
		
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
		//btnSearch.setTooltip(new Tooltip("Buscar"));
		btnNotification.setTooltip(new Tooltip("Notificaciones (F3)"));
		btnSettings.setTooltip(new Tooltip("Ajustes (F2)"));
		btnHelp.setTooltip(new Tooltip("Ayuda (F1)"));
		
		// ****** Initialising popup menus ******
		//Initialising sales' popover
		ImageView makeSalesIcon = new ImageView();
		makeSalesIcon.setFitHeight(30);
		makeSalesIcon.setFitWidth(30);
		makeSalesIcon.setImage(new Image("/stylesheets/images/sale.png"));
		Label makeSalesText = new Label("Realiza una venta");
		makeSalesText.setId("pop-over-button-text");
		Label makeSalesShortcut = new Label("Alt + V");
		makeSalesShortcut.setId("pop-over-button-shortcut");
		VBox makeSalesButton = new VBox(makeSalesIcon, makeSalesText, makeSalesShortcut);
		makeSalesButton.setId("pop-over-button");
		makeSalesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
				loadSalesFormView();
				salesPopOver.hide();
			}
		});
		
		ImageView salesHistoryIcon = new ImageView();
		salesHistoryIcon.setFitHeight(30);
		salesHistoryIcon.setFitWidth(30);
		salesHistoryIcon.setImage(new Image("/stylesheets/images/history.png"));
		Label salesHistoryText = new Label("Historial de ventas");
		salesHistoryText.setId("pop-over-button-text");
		Label salesHistoryShortcut = new Label("Alt + H");
		salesHistoryShortcut.setId("pop-over-button-shortcut");
		VBox SalesHistoryButton = new VBox(salesHistoryIcon, salesHistoryText, salesHistoryShortcut);
		SalesHistoryButton.setId("pop-over-button");
		SalesHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadSalesHistoryView();
				salesPopOver.hide();
			}
		});
		
		HBox salesHBox = new HBox(makeSalesButton, SalesHistoryButton);
		salesHBox.setId("pop-over-hbox");
		
		salesPopOver = new PopOver(salesHBox);
		salesPopOver.setDetachable(false);
		salesPopOver.setId("pop-over");
		
		//Initialising personal's popover
		ImageView departmentListIcon = new ImageView();
		departmentListIcon.setFitHeight(30);
		departmentListIcon.setFitWidth(30);
		departmentListIcon.setImage(new Image("/stylesheets/images/departmentList.png"));
		Label departmentListText = new Label("Lista de departamentos");
		departmentListText.setId("pop-over-button-text");
		Label departmentListShortcut = new Label("Alt + D");
		departmentListShortcut.setId("pop-over-button-shortcut");
		VBox departmentListButton = new VBox(departmentListIcon, departmentListText, departmentListShortcut);
		departmentListButton.setId("pop-over-button");
		departmentListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadDepartmentListView();
            	personalPopOver.hide();
			}
		});
		
		ImageView departmentAddIcon = new ImageView();
		departmentAddIcon.setFitHeight(30);
		departmentAddIcon.setFitWidth(30);
		departmentAddIcon.setImage(new Image("/stylesheets/images/departmentAdd.png"));
		Label departmentAddText = new Label("Agrega un departamento");
		departmentAddText.setId("pop-over-button-text");
		Label departmentAddShortcut = new Label("Alt + ");
		departmentAddShortcut.setId("pop-over-button-shortcut");
		VBox departmentAddButton = new VBox(departmentAddIcon, departmentAddText, departmentAddShortcut);
		departmentAddButton.setId("pop-over-button");
		departmentAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadDepartmentAddFormView();
				personalPopOver.hide();
			}
		});
		
		ImageView employeeListIcon = new ImageView();
		employeeListIcon.setFitHeight(30);
		employeeListIcon.setFitWidth(30);
		employeeListIcon.setImage(new Image("/stylesheets/images/clientGroup.png"));
		Label employeeListText = new Label("Lista de empleados");
		employeeListText.setId("pop-over-button-text");
		Label employeeListShortcut = new Label("Alt + E");
		employeeListShortcut.setId("pop-over-button-shortcut");
		VBox employeeListButton = new VBox(employeeListIcon, employeeListText, employeeListShortcut);
		employeeListButton.setId("pop-over-button");
		employeeListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadPersonnelListView();
				personalPopOver.hide();
			}
		});
		
		ImageView employeeAddIcon = new ImageView();
		employeeAddIcon.setFitHeight(30);
		employeeAddIcon.setFitWidth(30);
		employeeAddIcon.setImage(new Image("/stylesheets/images/clientAdd.png"));
		Label employeeAddText = new Label("Agrega un empleado");
		employeeAddText.setId("pop-over-button-text");
		Label employeeAddShortcut = new Label("Alt + A");
		employeeAddShortcut.setId("pop-over-button-shortcut");
		VBox employeeAddButton = new VBox(employeeAddIcon, employeeAddText, employeeAddShortcut);
		employeeAddButton.setId("pop-over-button");
		employeeAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadPersonnelAddFormView();
				personalPopOver.hide();
			}
		});
		
		HBox personalHBox = new HBox(departmentListButton, departmentAddButton, employeeListButton, employeeAddButton);
		personalHBox.setId("pop-over-hbox");
		personalPopOver = new PopOver(personalHBox);
		personalPopOver.setDetachable(false);
		personalPopOver.setId("pop-over");
		
		//Initialising Inventory popover
		ImageView inventoryListIcon = new ImageView();
		inventoryListIcon.setFitHeight(30);
		inventoryListIcon.setFitWidth(25);
		inventoryListIcon.setImage(new Image("/stylesheets/images/inventoryList.png"));
		Label inventoryListText = new Label("Lista de productos");
		inventoryListText.setId("pop-over-button-text");
		Label inventoryListShortcut = new Label("Alt + I");
		inventoryListShortcut.setId("pop-over-button-shortcut");
		VBox inventoryListButton = new VBox(inventoryListIcon, inventoryListText, inventoryListShortcut);
		inventoryListButton.setId("pop-over-button");
		inventoryListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadInventoryListView();
            	inventoryPopOver.hide();
			}
		});
		
		ImageView inventoryAddIcon = new ImageView();
		inventoryAddIcon.setFitHeight(30);
		inventoryAddIcon.setFitWidth(25);
		inventoryAddIcon.setImage(new Image("/stylesheets/images/inventoryAdd.png"));
		Label inventoryAddText = new Label("Agrega un producto");
		inventoryAddText.setId("pop-over-button-text");
		Label inventoryAddShortcut = new Label("Alt + P");
		inventoryAddShortcut.setId("pop-over-button-shortcut");
		VBox inventoryAddButton = new VBox(inventoryAddIcon, inventoryAddText, inventoryAddShortcut);
		inventoryAddButton.setId("pop-over-button");
		inventoryAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	//Dashboard.getStage().getScene().getRoot().setDisable(true); 				Still testing...
            	loadInventoryAddFormView();
            	inventoryPopOver.hide();
			}
		});
		
		HBox inventoryHBox = new HBox(inventoryListButton, inventoryAddButton);
		inventoryHBox.setId("pop-over-hbox");
		inventoryPopOver = new PopOver(inventoryHBox);
		inventoryPopOver.setDetachable(false);
		inventoryPopOver.setId("pop-over");
		
		//Initialising Resources popover
		ImageView warehouseListIcon = new ImageView();
		warehouseListIcon.setFitHeight(30);
		warehouseListIcon.setFitWidth(30);
		warehouseListIcon.setImage(new Image("/stylesheets/images/warehouseList.png"));
		Label warehouseListText = new Label("Lista de almacenes");
		warehouseListText.setId("pop-over-button-text");
		Label warehouseListShortcut = new Label("Alt + ");
		warehouseListShortcut.setId("pop-over-button-shortcut");
		VBox warehouseListButton = new VBox(warehouseListIcon, warehouseListText, warehouseListShortcut);
		warehouseListButton.setId("pop-over-button");
		warehouseListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
			}
		});
		
		ImageView warehouseAddIcon = new ImageView();
		warehouseAddIcon.setFitHeight(30);
		warehouseAddIcon.setFitWidth(30);
		warehouseAddIcon.setImage(new Image("/stylesheets/images/warehouseAdd.png"));
		Label warehouseAddText = new Label("Agrega un almacén");
		warehouseAddText.setId("pop-over-button-text");
		Label warehouseAddShortcut = new Label("Alt + ");
		warehouseAddShortcut.setId("pop-over-button-shortcut");
		VBox warehouseAddButton = new VBox(warehouseAddIcon, warehouseAddText, warehouseAddShortcut);
		warehouseAddButton.setId("pop-over-button");
		warehouseAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
			}
		});
		
		HBox resourcesHBox = new HBox(warehouseListButton, warehouseAddButton);
		resourcesHBox.setId("pop-over-hbox");
		resourcesPopOver = new PopOver(resourcesHBox);
		resourcesPopOver.setDetachable(false);
		resourcesPopOver.setId("pop-over");
		
		//Initialising Documents' popover
		ImageView ticketHistoryIcon = new ImageView();
		ticketHistoryIcon.setFitHeight(30);
		ticketHistoryIcon.setFitWidth(24);
		ticketHistoryIcon.setImage(new Image("/stylesheets/images/billHistory.png"));
		Label ticketHistoryText = new Label("Historial de tickets");
		ticketHistoryText.setId("pop-over-button-text");
		Label ticketHistoryShortcut = new Label("Alt + D");
		ticketHistoryShortcut.setId("pop-over-button-shortcut");
		VBox ticketHistoryButton = new VBox(ticketHistoryIcon, ticketHistoryText, ticketHistoryShortcut);
		ticketHistoryButton.setId("pop-over-button");
		ticketHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
			}
		});
		
		ImageView invoiceHistoryIcon = new ImageView();
		invoiceHistoryIcon.setFitHeight(30);
		invoiceHistoryIcon.setFitWidth(30);
		invoiceHistoryIcon.setImage(new Image("/stylesheets/images/invoiceHistory.png"));
		Label invoiceHistoryText = new Label("Historial de facturas");
		invoiceHistoryText.setId("pop-over-button-text");
		Label invoiceHistoryShortcut = new Label("Alt + F");
		invoiceHistoryShortcut.setId("pop-over-button-shortcut");
		VBox invoiceHistoryButton = new VBox(invoiceHistoryIcon, invoiceHistoryText, invoiceHistoryShortcut);
		invoiceHistoryButton.setId("pop-over-button");
		invoiceHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
			}
		});
		
		HBox documentsHBox = new HBox(ticketHistoryButton, invoiceHistoryButton);
		documentsHBox.setId("pop-over-hbox");
		documentsPopOver = new PopOver(documentsHBox);
		documentsPopOver.setDetachable(false);
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
	}
	
	//Initialises the worker's name
	public void setWorkersName(String name) {
		lblName.setText(name);
		lblJob.setText("cajero");
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
			
			//loadHomeView();
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
		public void salesFormWithoutSubmenu() {
			salesFormWithoutMenu();
		}
		
		public void salesFormWithoutMenu() {
			setOptionSelected(true);
			clickedSalesButton();
			loadSalesFormView();
			setOptionSelected(false);
		}
		
		public void salesHistoryWithoutMenu() {
			setOptionSelected(true);
			clickedSalesButton();
			loadSalesHistoryView();
			setOptionSelected(false);
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
			if(!getOptionSelected())
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
				
				//Warehouse resources = new Warehouse();
				//sclMainView.setContent(resources.showResources());
				if(!getOptionSelected())
					inventoryPopOver.show(hboxInventory);
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
			
			//Warehouse resources = new Warehouse();
			//sclMainView.setContent(resources.showResources());
			if(!getOptionSelected())
				resourcesPopOver.show(hboxResources);
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
	
	/*@FXML
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
	}*/

	
	//Search button animations
	/*@FXML 
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
	}*/
	
	//Notification button animations
	@FXML 
	protected void clickedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
		
		openNotification();
	}
	
	public void openNotification() {
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
		openHelp();
	}
	
	public void openHelp() {
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
