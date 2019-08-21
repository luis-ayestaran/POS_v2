package com.masterdev.student.views.controllers;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.User;
import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.Notifications;
import com.masterdev.student.middle.animations.DashboardButtonAnimations;
import com.masterdev.student.services.CashRegisterService;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.BalanceOverview;
import com.masterdev.student.views.CashierCutOffAdd;
import com.masterdev.student.views.CashierCutOffList;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.DepartmentAddForm;
import com.masterdev.student.views.DepartmentList;
import com.masterdev.student.views.Home;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.InventoryList;
import com.masterdev.student.views.Login;
import com.masterdev.student.views.PersonnelAddForm;
import com.masterdev.student.views.PersonnelList;
import com.masterdev.student.views.SalesForm;
import com.masterdev.student.views.SalesHistory;
import com.masterdev.student.views.SalesWaitingList;
import com.masterdev.student.views.ServicesList;
import com.masterdev.student.views.WarehouseList;
import com.masterdev.student.services.ScaleService;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


public class DashboardController implements Initializable{ 
	//Methods to animate the sidebar buttons-----------------------------------------------------
	
	@FXML Label lblUser;
	@FXML Label lblJob;
	
	//Dashboard button elements
	@FXML Button btnDashboard;
	@FXML HBox hboxDashboard;
	@FXML FontAwesomeIconView icoDashboard;
	//Sales button elements
	@FXML Button btnSales;
	@FXML HBox hboxSales;
	@FXML FontAwesomeIconView icoSales;
	//Inventory button elements
	@FXML Button btnInventory;
	@FXML HBox hboxInventory;
	@FXML FontAwesomeIconView icoInventory;
	//Personnel button elements
	@FXML Button btnServices;
	@FXML HBox hboxServices;
	@FXML FontAwesomeIconView icoServices;
	//Personnel button elements
	@FXML Button btnPersonal;
	@FXML HBox hboxPersonal;
	@FXML FontAwesomeIconView icoPersonal;
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
	/*@FXML Button btnProjects;
	@FXML HBox hboxProjects;
	@FXML FontAwesomeIconView icoProjects;*/
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
	@FXML Circle notificationReminder;
	//Settings button elements
	@FXML Button btnSettings;
	@FXML FontAwesomeIconView icoSettings;
	@FXML Circle settingsReminder;
	//Help button elements
	@FXML Button btnHelp;
	@FXML FontAwesomeIconView icoHelp;
	@FXML Circle helpReminder;
	
	//Main view elements
	@FXML ScrollPane sclMainView;
	
	private ScaleService scale;
	
	//Popup menus
	private PopOver salesPopOver;
	private PopOver inventoryPopOver;
	private PopOver servicesPopOver;
	private PopOver personalPopOver;
	private PopOver resourcesPopOver;
	private PopOver statisticsPopOver;
	private PopOver documentsPopOver;
	private PopOver notificationPopOver;
	
	//QUICK BUTTONS
	@FXML VBox btnOpenSalesForm;
	@FXML VBox btnOpenInventoryEditForm;
	@FXML VBox btnOpenPriceChecker;
	@FXML VBox btnOpenAddEmployeeForm;
	@FXML VBox btnOpenAddSupplierForm;
	
	//A flag to manage popOver graphic menus
	private Boolean optionSelected = false;
	
	private User user;
	private CashRegister cashRegister;
	
	//--------------------------------------------------------------- MANAGING FLAGS --------------------------------------------------//
	public Boolean getOptionSelected() {
		return optionSelected;
	}
	
	public void setOptionSelected(Boolean optionSelected) {
		this.optionSelected = optionSelected;
	}
	
	
	//--------------------------------------------------------------- LOADING VIEWS -------------------------------------------------------//
	//******* HOME********
	public void loadHomeView() {
		Home view = new Home();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sclMainView.setContent(node);
	}
	
	//******* SALES ********
	public void loadSalesFormView() {
		if(SalesForm.getNode() != null) {
			StackPane node = SalesForm.getNode();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			node.prefHeightProperty().bind(sclMainView.heightProperty());
			sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
			sclMainView.setContent(node);
			
		} else {
			SalesForm view = new SalesForm();
				StackPane node = view.loadView();
			node.prefWidthProperty().bind(sclMainView.widthProperty());
			node.prefHeightProperty().bind(sclMainView.heightProperty());
			sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
			sclMainView.setContent(node);
		}
		SalesForm.getSalesFormController().txtSearchProductRequestsFocus();
	}
	
	public void loadSalesHistoryView() {
		SalesHistory view = new SalesHistory();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
		sclMainView.setContent(node);
	}
	
	//******* PERSONNEL ********
	public void loadDepartmentListView() {
		DepartmentList view = new DepartmentList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
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
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
		sclMainView.setContent(node);
	}
	
	public void loadPersonnelAddFormView() {
		PersonnelAddForm view = new PersonnelAddForm();
		view.loadView();
	}
	
	//******* INVENTORY ********
	public void loadInventoryListView() {
		InventoryList view = new InventoryList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
		sclMainView.setContent(node);
	}
	
	public void loadInventoryAddFormView() {
		if(InventoryAddForm.getStage() != null) {
			if(!InventoryAddForm.getStage().isShowing())
				InventoryAddForm.getStage().show();
			InventoryAddForm.getStage().setAlwaysOnTop(true);
			InventoryAddForm.getStage().setAlwaysOnTop(false);
		} else {
			InventoryAddForm view = new InventoryAddForm();
			view.loadView();
		}
	}
	
	//******* SERVICES ********
	public void loadServicesListView() {
		ServicesList view = new ServicesList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
		sclMainView.setContent(node);
	}
	
	//******* WAREHOUSE ********
	public void loadWarehouseListView() {
		WarehouseList view = new WarehouseList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		node.prefHeightProperty().bind(sclMainView.heightProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.NEVER);
		sclMainView.setContent(node);
	}
	
	//******* STATISTICS ********
	public void loadBalanceOverviewView() {
		BalanceOverview view = new BalanceOverview();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sclMainView.setContent(node);
	}
	
	//******* CASH REGISTER CUT-OFF ********
	public void loadCashierCutOffListView() {
		CashierCutOffList view = new CashierCutOffList();
		StackPane node = view.loadView();
		node.prefWidthProperty().bind(sclMainView.widthProperty());
		sclMainView.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sclMainView.setContent(node);
	}
	
	public void loadCashierCutOffAddView() {
		CashierCutOffAdd view = new CashierCutOffAdd();
		view.loadView();
		CashierCutOffAdd.getStage().setAlwaysOnTop(true);
		CashierCutOffAdd.getStage().setAlwaysOnTop(false);
	}
	
	
	
	//--------------------------------------------------------------- INITIALISING COMPONENTS ----------------------------------------------//
	@FXML ComboBox<String> cmbAccount;
	@FXML ImageView imgAccount;
	
	public void initialize(URL location, ResourceBundle resources) {
		ComboBoxMethods cbm = new ComboBoxMethods();

		//Adding user options to the combo box
		String[] items = {"Configurar cuenta", "Cambiar de cuenta", "Cerrar sesión"};
		cbm.addItems(cmbAccount, items);
		
		//Making a rounded user image
		//Integer radius = 20;
		//ivm.makeRoundImage(imgAccount, radius);
		
		//Charging the home scene
		clickedDBButton();
		
		//Adding TooltipTexts to the buttons
		initialiseTooltipTexts();
		
		// ****** Initialising popup menus ******
		initialisePopOverMenus();
		
		// ****** Initialising notifications ******
		initialiseNotifications();
		
		this.scale = new ScaleService();
	}
	
	//
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public CashRegister getCashRegister() {
		return cashRegister;
	}
	
	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
	
	public ScaleService getScale() {
		return scale;
	}
	
	public void setScale(ScaleService scale) {
		this.scale = scale;
	}
	
	//Initialises the worker's name and job
	public void setUsername() {
		lblUser.setText(getUser().getName() + " " + getUser().getLastName());
		if(getUser().getUserGroup().getGroup().equals("admin"))
			lblJob.setText("Administrador(a)");
		else
			lblJob.setText("Cajero(a)");
		// ****** Hiding admin menus (if not admin) *****
		hideAdminMenus();
	}
	
	@FXML
	public void accountOptions() {
		switch(cmbAccount.getSelectionModel().getSelectedItem()) {
		case "Configurar cuenta": ;
			break;
		case "Cambiar de cuenta": ;//changeSession();
			break;
		case "Cerrar sesión": closeRequest();
			break;
		}
	}
	
	public void initialiseTooltipTexts() {
		btnNotification.setTooltip(new Tooltip("Notificaciones (F3)")); 
		btnSettings.setTooltip(new Tooltip("Ajustes (F2)"));
		btnHelp.setTooltip(new Tooltip("Ayuda (F1)"));
	}
	
	//Initialising popOverMenus -----------------
	public void initialisePopOverMenus() {
		initialiseSalesMenu();
		initialiseInventoryMenu();
		initialiseServicesMenu();
		initialisePersonnelMenu();
		initialiseResourcesMenu();
		initialiseStatisticsMenu();
		initialiseDocumentsMenu();
	}
	
	// SALES MENU *****
	public void initialiseSalesMenu() {
		//Initialising sales' popover
		ImageView makeSalesIcon = new ImageView();
		makeSalesIcon.setFitHeight(40);
		makeSalesIcon.setPreserveRatio(true);
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
		salesHistoryIcon.setFitHeight(40);
		salesHistoryIcon.setPreserveRatio(true);
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
	}
	
	// INVENTORY MENU *****
	public void initialiseInventoryMenu() {
		//Initialising Inventory popover
		ImageView inventoryListIcon = new ImageView();
		inventoryListIcon.setFitHeight(40);
		inventoryListIcon.setPreserveRatio(true);
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
		inventoryAddIcon.setFitHeight(40);
		inventoryAddIcon.setPreserveRatio(true);
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
	}
	
	// SERVICES MENU *****
		public void initialiseServicesMenu() {
			//Initialising Inventory popover
			ImageView servicesListIcon = new ImageView();
			servicesListIcon.setFitHeight(40);
			servicesListIcon.setPreserveRatio(true);
			servicesListIcon.setImage(new Image("/stylesheets/images/serviceList.png"));
			Label servicesListText = new Label("Lista de servicios");
			servicesListText.setId("pop-over-button-text");
			Label servicesListShortcut = new Label("Alt + S");
			servicesListShortcut.setId("pop-over-button-shortcut");
			VBox servicesListButton = new VBox(servicesListIcon, servicesListText, servicesListShortcut);
			servicesListButton.setId("pop-over-button");
			servicesListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
				public void handle(MouseEvent event) {
	            	loadServicesListView();
	            	servicesPopOver.hide();
				}
			});
			
			ImageView servicesAddIcon = new ImageView();
			servicesAddIcon.setFitHeight(40);
			servicesAddIcon.setPreserveRatio(true);
			servicesAddIcon.setImage(new Image("/stylesheets/images/serviceAdd.png"));
			Label servicesAddText = new Label("Agrega un producto");
			servicesAddText.setId("pop-over-button-text");
			Label servicesAddShortcut = new Label("Alt + ");
			servicesAddShortcut.setId("pop-over-button-shortcut");
			VBox servicesAddButton = new VBox(servicesAddIcon, servicesAddText, servicesAddShortcut);
			servicesAddButton.setId("pop-over-button");
			servicesAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
				public void handle(MouseEvent event) {
	            	//loadServicesAddFormView();
	            	servicesPopOver.hide();
				}
			});
			
			HBox servicesHBox = new HBox(servicesListButton, servicesAddButton);
			servicesHBox.setId("pop-over-hbox");
			servicesPopOver = new PopOver(servicesHBox);
			servicesPopOver.setDetachable(false);
			servicesPopOver.setId("pop-over");
		}
	
	// PERSONNEL MENU *****
	public void initialisePersonnelMenu() {
		//Initialising personal's popover
		/*ImageView departmentListIcon = new ImageView();
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
		});*/
		
		ImageView employeeListIcon = new ImageView();
		employeeListIcon.setFitHeight(40);
		employeeListIcon.setPreserveRatio(true);
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
		employeeAddIcon.setFitHeight(40);
		employeeAddIcon.setPreserveRatio(true);
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
		
		HBox personalHBox = new HBox(/*departmentListButton, departmentAddButton,*/ employeeListButton, employeeAddButton);
		personalHBox.setId("pop-over-hbox");
		personalPopOver = new PopOver(personalHBox);
		personalPopOver.setDetachable(false);
		personalPopOver.setId("pop-over");
	}
	
	//RESOURCES MENU *****
	public void initialiseResourcesMenu() {
		//Initialising Resources popover
		ImageView warehouseListIcon = new ImageView();
		warehouseListIcon.setFitHeight(40);
		warehouseListIcon.setPreserveRatio(true);
		warehouseListIcon.setImage(new Image("/stylesheets/images/warehouseList.png"));
		Label warehouseListText = new Label("Lista de recursos");
		warehouseListText.setId("pop-over-button-text");
		Label warehouseListShortcut = new Label("Alt + R");
		warehouseListShortcut.setId("pop-over-button-shortcut");
		VBox warehouseListButton = new VBox(warehouseListIcon, warehouseListText, warehouseListShortcut);
		warehouseListButton.setId("pop-over-button");
		warehouseListButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	resourcesPopOver.hide();
			}
		});
		
		ImageView warehouseAddIcon = new ImageView();
		warehouseAddIcon.setFitHeight(40);
		warehouseAddIcon.setPreserveRatio(true);
		warehouseAddIcon.setImage(new Image("/stylesheets/images/warehouseAdd.png"));
		Label warehouseAddText = new Label("Agrega un recurso");
		warehouseAddText.setId("pop-over-button-text");
		Label warehouseAddShortcut = new Label("Alt + S");
		warehouseAddShortcut.setId("pop-over-button-shortcut");
		VBox warehouseAddButton = new VBox(warehouseAddIcon, warehouseAddText, warehouseAddShortcut);
		warehouseAddButton.setId("pop-over-button");
		warehouseAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	resourcesPopOver.hide();
			}
		});
		
		HBox resourcesHBox = new HBox(warehouseListButton, warehouseAddButton);
		resourcesHBox.setId("pop-over-hbox");
		resourcesPopOver = new PopOver(resourcesHBox);
		resourcesPopOver.setDetachable(false);
		resourcesPopOver.setId("pop-over");
	}
	
	//STATISTICS MENU *****
	public void initialiseStatisticsMenu() {
		//Initialising Documents' popover
		ImageView balanceOverviewIcon = new ImageView();
		balanceOverviewIcon.setFitHeight(40);
		balanceOverviewIcon.setPreserveRatio(true);
		balanceOverviewIcon.setImage(new Image("/stylesheets/images/lineChart.png"));
		Label balanceOverviewText = new Label("Resumen de finanzas");
		balanceOverviewText.setId("pop-over-button-text");
		Label balanceOverviewShortcut = new Label("Alt + F");
		balanceOverviewShortcut.setId("pop-over-button-shortcut");
		VBox balanceOverviewButton = new VBox(balanceOverviewIcon, balanceOverviewText, balanceOverviewShortcut);
		balanceOverviewButton.setId("pop-over-button");
		balanceOverviewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadBalanceOverviewView();
            	statisticsPopOver.hide();
			}
		});
		
		/*ImageView invoiceHistoryIcon = new ImageView();
		invoiceHistoryIcon.setFitHeight(40);
		invoiceHistoryIcon.setPreserveRatio(true);
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
		});*/
		
		HBox statisticsHBox = new HBox(balanceOverviewButton/*, invoiceHistoryButton*/);
		statisticsHBox.setId("pop-over-hbox");
		statisticsPopOver = new PopOver(statisticsHBox);
		statisticsPopOver.setDetachable(false);
		statisticsPopOver.setId("pop-over");
	}
	
	//BOX CUT OFF MENU *****
	public void initialiseDocumentsMenu() {
		//Initialising Documents' popover
		ImageView ticketHistoryIcon = new ImageView();
		ticketHistoryIcon.setFitHeight(40);
		ticketHistoryIcon.setPreserveRatio(true);
		ticketHistoryIcon.setImage(new Image("/stylesheets/images/billHistory.png"));
		Label ticketHistoryText = new Label("Historial de cortes de caja");
		ticketHistoryText.setId("pop-over-button-text");
		Label ticketHistoryShortcut = new Label("Alt + C");
		ticketHistoryShortcut.setId("pop-over-button-shortcut");
		VBox ticketHistoryButton = new VBox(ticketHistoryIcon, ticketHistoryText, ticketHistoryShortcut);
		ticketHistoryButton.setId("pop-over-button");
		ticketHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadCashierCutOffListView();
            	documentsPopOver.hide();
			}
		});
		
		ImageView invoiceHistoryIcon = new ImageView();
		invoiceHistoryIcon.setFitHeight(40);
		invoiceHistoryIcon.setPreserveRatio(true);
		invoiceHistoryIcon.setImage(new Image("/stylesheets/images/invoiceHistory.png"));
		Label invoiceHistoryText = new Label("Realizar corte de caja");
		invoiceHistoryText.setId("pop-over-button-text");
		Label invoiceHistoryShortcut = new Label("Alt + ");
		invoiceHistoryShortcut.setId("pop-over-button-shortcut");
		VBox invoiceHistoryButton = new VBox(invoiceHistoryIcon, invoiceHistoryText, invoiceHistoryShortcut);
		invoiceHistoryButton.setId("pop-over-button");
		invoiceHistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
			public void handle(MouseEvent event) {
            	loadCashierCutOffAddView();
            	documentsPopOver.hide();
			}
		});
		
		HBox documentsHBox = new HBox(ticketHistoryButton, invoiceHistoryButton);
		documentsHBox.setId("pop-over-hbox");
		documentsPopOver = new PopOver(documentsHBox);
		documentsPopOver.setDetachable(false);
		documentsPopOver.setId("pop-over");
	}
	
	// NOTIFICATIONS *******
	public void initialiseNotifications() {
		notificationPopOver = new PopOver();
		Notifications.setNotificationPopOver(notificationPopOver);
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		Notifications.init(data);
		if(!Notifications.getNotificationVBox().getChildren().isEmpty())
			showNotificationReminder();
	}
	
	//HIDING MENUS ********
	public void hideAdminMenus() {
		if(!getUser().getUserGroup().getGroup().equals("admin")) {
			((VBox) hboxDashboard.getParent()).getChildren().remove(hboxDashboard);
			((VBox) hboxResources.getParent()).getChildren().remove(hboxResources);
			((VBox) hboxStatistics.getParent()).getChildren().remove(hboxStatistics);
			((VBox) hboxPersonal.getParent()).getChildren().remove(hboxPersonal);
			((VBox) hboxProviders.getParent()).getChildren().remove(hboxProviders);
			((VBox) hboxDocuments.getParent()).getChildren().remove(hboxDocuments);
			
			
			//((VBox) hboxProjects.getParent()).getChildren().remove(hboxProjects);
		}
	}
	
	//--------------------------------------------------------------- CLOSING METHODS ------------------------------------------------------//
	public void changeSession() {
		Boolean close = false;
		if(InventoryAddForm.getStage() != null) {
			if(InventoryAddForm.getInventoryAddFormController().formHasInformation()) {
				loadInventoryAddFormView();
				Dialogs d = new Dialogs();
				Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas datos no guardados. \n¿Estás seguro de cerrar sesión? \n");
				if(exit) {
					InventoryAddForm.getInventoryAddFormController().exitView();
					close = true;
				} else {
					cmbAccount.getSelectionModel().clearSelection();
				}
			} else {
				close = true;
			}
		} else {
			close = true;
		}
		if(close) {
			closeSession();
		}
	}
	
	public void closeSession() {
		if(SalesForm.getNode() != null) {
			SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
		}
		Dashboard.getStage().close();
		Login view = new Login();
		view.loadView();
	}
	
	public void closeRequest(Event e) {
		Boolean close = false;
		
		if(SalesForm.getNode() != null) {
			if(SalesForm.getSalesFormController().currentSaleIsEmpty() && SalesForm.getSalesFormController().waitingListIsEmpty()) {
				close = true;
			} else {
				if(!SalesForm.getSalesFormController().currentSaleIsEmpty()) {
					salesFormWithoutSubmenu();
				}
				if(!SalesForm.getSalesFormController().waitingListIsEmpty()) {
					SalesForm.getSalesFormController().loadSalesWaitingListView();
				}
				Dialogs d = new Dialogs();
				Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas una venta no completada. \n¿Estás seguro de cerrar sesión? \n");
				if(exit) {
					close = true;
				} else {
					e.consume();
				}
			}
		} else {
			close = true;
		}
		
		if(close) {
			if(SalesWaitingList.getStage() != null) {
				if(SalesWaitingList.getStage().isShowing()) {
					SalesWaitingList.getStage().close();
				}
			}
			if(InventoryAddForm.getStage() != null) {
				if(InventoryAddForm.getInventoryAddFormController().formHasInformation()) {
					loadInventoryAddFormView();
					Dialogs d = new Dialogs();
					Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas datos de producto no guardados. \n¿Estás seguro de cerrar sesión? \n");
					if(exit) {
						if(SalesForm.getSalesFormController() != null) {
							SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
						}
						if(SalesWaitingList.getSalesWaitingListController() != null) {
							SalesWaitingList.getSalesWaitingListController().cancelAll();
						}
						InventoryAddForm.getInventoryAddFormController().exitView();
					} else {
						e.consume();						//Si el usuario no elige, o da clic en Cancelar, no cierra la ventana
						close = false;
					}
				} else {
					if(SalesForm.getSalesFormController() != null) {
						SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
					}
					if(SalesWaitingList.getSalesWaitingListController() != null) {
						SalesWaitingList.getSalesWaitingListController().cancelAll();
					}
				}
			} else {
				if(SalesForm.getSalesFormController() != null) {
					SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
				}
				if(SalesWaitingList.getSalesWaitingListController() != null) {
					SalesWaitingList.getSalesWaitingListController().cancelAll();
				}
			}
		}
		
		if(close) {
			closeConfirmation();
		}
	}
	
	public void closeRequest() {
		Boolean close = false;
		
		if(SalesForm.getNode() != null) {
			if(SalesForm.getSalesFormController().currentSaleIsEmpty() && SalesForm.getSalesFormController().waitingListIsEmpty()) {
				close = true;
			} else {
				if(!SalesForm.getSalesFormController().currentSaleIsEmpty()) {
					salesFormWithoutSubmenu();
				}
				if(!SalesForm.getSalesFormController().waitingListIsEmpty()) {
					SalesForm.getSalesFormController().loadSalesWaitingListView();
				}
				Dialogs d = new Dialogs();
				Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas una venta no completada. \n¿Estás seguro de cerrar sesión? \n");
				if(exit) {
					close = true;
				} else {
					cmbAccount.getSelectionModel().clearSelection();
				}
			}
		} else {
			close = true;
		}
		
		if(close) {
			if(SalesWaitingList.getStage() != null) {
				if(SalesWaitingList.getStage().isShowing()) {
					SalesWaitingList.getStage().close();
				}
			}
			if(InventoryAddForm.getStage() != null) {
				if(InventoryAddForm.getInventoryAddFormController().formHasInformation()) {
					loadInventoryAddFormView();
					Dialogs d = new Dialogs();
					Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas datos de producto no guardados. \n¿Estás seguro de cerrar sesión? \n");
					if(exit) {
						if(SalesForm.getSalesFormController() != null) {
							SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
						}
						if(SalesWaitingList.getSalesWaitingListController() != null) {
							SalesWaitingList.getSalesWaitingListController().cancelAll();
						}
						InventoryAddForm.getInventoryAddFormController().exitView();
					} else {
						cmbAccount.getSelectionModel().clearSelection();						//Si el usuario no elige, o da clic en Cancelar, no cierra la ventana
						close = false;
					}
				} else {
					if(SalesForm.getSalesFormController() != null) {
						SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
					}
					if(SalesWaitingList.getSalesWaitingListController() != null) {
						SalesWaitingList.getSalesWaitingListController().cancelAll();
					}
				}
			} else {
				if(SalesForm.getSalesFormController() != null) {
					SalesForm.getSalesFormController().cancelSale(SalesForm.getSalesFormController().getSale());
				}
				if(SalesWaitingList.getSalesWaitingListController() != null) {
					SalesWaitingList.getSalesWaitingListController().cancelAll();
				}
			}
		}
		
		if(close) {
			closeConfirmation();
		}
	}
	
	public void closeConfirmation() {
		doCashRegisterCutOff();
		closeApp();
	}
	
	public void doCashRegisterCutOff() {
		CashRegisterService service = new CashRegisterService();
		getCashRegister().setUsed(false);
		service.updateCashRegister(cashRegister);
	}
	
	public void closeApp() {
		Platform.exit(); //Shuts down the GUI thread
		System.exit(0); //Exit killing the JVM
	}
	
	
	//-------------------------------------------- HOTKEY METHODS -----------------------------------------------//
	
	public void setHotkeys() {
		//Adding hotkeys (keyboard shortcuts) to the app 
		final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.V, KeyCombination.ALT_DOWN);
		final KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.H, KeyCombination.ALT_DOWN);
		Dashboard.getStage().getScene().addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (keyComb1.match(event)) {
					setSalesFormMnemonic();
				} else if (keyComb2.match(event)) {
					setSalesHistoryMnemonic();
				}
			}
		});
		
		Dashboard.getStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
		 	public void handle(final KeyEvent keyEvent) {
		 		if (keyEvent.getCode() == KeyCode.F1) {
		 			setHelpMnemonic();
		 		} else if (keyEvent.getCode() == KeyCode.F2) {
		 			setSettingsMnemonic();
			   	} else if (keyEvent.getCode() == KeyCode.F3) {
		 			setNotificationMnemonic();
			   	}
		 	}
		});
	}
	
	public void setSalesFormMnemonic() {
		salesFormWithoutMenu();
	}
	
	public void setSalesHistoryMnemonic() {
		salesHistoryWithoutMenu();
	}
	
	public void setNotificationMnemonic() {
		openNotification();
	}
	
	public void setSettingsMnemonic() {
		
	}
	
	public void setHelpMnemonic() {
		openHelp();
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			if(getUser().getUserGroup().getGroup().equals("admin")) {
				//loadSaleslView();
				if(!getOptionSelected())
					salesPopOver.show(hboxSales);
			} else {
				loadSalesFormView();
			}
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			//loadPersonalView();
			if(!getOptionSelected())
				personalPopOver.show(hboxPersonal);
		}
		
		@FXML
		public void personnelListWithoutSubmenu() {
			personnelListWithoutMenu();
		}
		
		public void personnelListWithoutMenu() {
			setOptionSelected(true);
			clickedPersButton();
			loadPersonnelListView();
			setOptionSelected(false);
		}
		
		@FXML
		public void personnelAddFormWithoutSubmenu() {
			personnelAddFormWithoutMenu();
		}
		
		public void personnelAddFormWithoutMenu() {
			setOptionSelected(true);
			clickedPersButton();
			loadPersonnelAddFormView();
			setOptionSelected(false);
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
				dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
				dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
				//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
				
				//Warehouse resources = new Warehouse();
				//sclMainView.setContent(resources.showResources());
				if(!getOptionSelected())
					inventoryPopOver.show(hboxInventory);
			}
			
			@FXML
			public void inventoryListWithoutSubmenu() {
				inventoryListWithoutMenu();
			}
			
			public void inventoryListWithoutMenu() {
				setOptionSelected(true);
				clickedInvButton();
				loadInventoryListView();
				setOptionSelected(false);
			}
			
			@FXML
			public void inventoryAddFormWithoutSubmenu() {
				inventoryAddFormWithoutMenu();
			}
			
			public void inventoryAddFormWithoutMenu() {
				setOptionSelected(true);
				clickedInvButton();
				loadInventoryAddFormView();
				setOptionSelected(false);
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
			
		//Animating the services button
			
			@FXML
			protected void clickedServButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.clickedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
				btnServices.requestFocus();
				/*Changing the other buttons to their original state*/
				dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
				dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
				dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
				dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
				//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
				
				//Warehouse resources = new Warehouse();
				//sclMainView.setContent(resources.showResources());
				if(!getOptionSelected())
					servicesPopOver.show(hboxServices);
			}
			
			@FXML
			protected void pressedServButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.pressedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			}
			
			@FXML
			protected void releasedServButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.releasedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			}
			
			@FXML
			protected void enteredServButton() {
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.enteredButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			}
			
			@FXML
			protected void exitedServButton() {
				if(!btnServices.isFocused())
				{
					DashboardButtonAnimations dba = new DashboardButtonAnimations();
					dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			if(!getOptionSelected())
				statisticsPopOver.show(hboxStatistics);
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
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
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
	
		/*@FXML
		protected void clickedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			btnProjects.requestFocus();
			//Changing the other buttons to their original state
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
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
		}*/
		
	//Animating the documents button
		
		@FXML
		protected void clickedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			btnDocuments.requestFocus();
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxSales, btnSales, icoSales, DashboardButtonAnimations.SALESICON);
			dba.exitedButton(hboxInventory, btnInventory, icoInventory, DashboardButtonAnimations.INVICON);
			dba.exitedButton(hboxServices, btnServices, icoServices, DashboardButtonAnimations.SERVICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProviders, btnProviders, icoProviders, DashboardButtonAnimations.PROVICON);
			//dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			
			documentsPopOver.show(hboxDocuments);
		}
		
		@FXML
		protected void cashierCutOffAddWithoutSubmenu() {
			cashierCutOffAddWithoutMenu();
		}
		
		public void cashierCutOffAddWithoutMenu() {
			setOptionSelected(true);
			clickedDocButton();
			loadCashierCutOffAddView();
			setOptionSelected(false);
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
		dba.clickedButton(btnNotification, notificationReminder, icoNotification, DashboardButtonAnimations.NOTICON);
		
		openNotification();
	}
	
	public void openNotification() {
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		Notifications.init(data);
		notificationPopOver.show(btnNotification);
	}
	
	@FXML 
	protected void pressedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnNotification, notificationReminder, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void releasedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnNotification, notificationReminder, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void enteredNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnNotification, notificationReminder, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void exitedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnNotification, notificationReminder, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	//Settings button animations
	@FXML 
	protected void clickedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnSettings, settingsReminder, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML 
	protected void pressedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnSettings, settingsReminder, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void releasedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnSettings, settingsReminder, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void enteredSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnSettings, settingsReminder, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void exitedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnSettings, settingsReminder, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	//Help button animations
	@FXML 
	protected void clickedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnHelp, helpReminder, icoHelp, DashboardButtonAnimations.HELPICON);
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
		dba.pressedButton(btnHelp, helpReminder, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void releasedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnHelp, helpReminder, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void enteredHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnHelp, helpReminder, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void exitedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnHelp, helpReminder, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	//----- SHOWING AND HIDING USER OPTIONS BUTTONS' REMINDERS -------//
	public void showNotificationReminder() {
		notificationReminder.setVisible(true);
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(250), notificationReminder);
        scaleTransition.setToX(1.3f);
        scaleTransition.setToY(1.3f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
	}
	
	public void hideNotificationReminder() {
		notificationReminder.setVisible(false);
	}
	
	public void showSettingsReminder() {
		settingsReminder.setVisible(true);
	}
	
	public void hideSettingsReminder() {
		settingsReminder.setVisible(false);
	}
	
	public void showHelpReminder() {
		helpReminder.setVisible(true);
	}
	
	public void hideHelpReminder() {
		helpReminder.setVisible(false);
	}
	
}
