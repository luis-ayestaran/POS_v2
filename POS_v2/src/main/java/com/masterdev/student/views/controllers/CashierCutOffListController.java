package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.masterdev.student.entities.CutOff;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.pojos.CutOffHistoryEntry;
import com.masterdev.student.services.CashRegisterService;
import com.masterdev.student.views.Dashboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class CashierCutOffListController implements Initializable {
	
	@FXML JFXDatePicker dtPckrSearch;
	@FXML JFXTimePicker tmPckrSearch;
	
	@FXML JFXButton btnNewCutOff;
	@FXML JFXButton btnShowCutOffs;
	
	@FXML Button btnSearchHelp;
	@FXML Button btnDetailsHelp;
	
	@FXML TableView<CutOffHistoryEntry> tabCutOffs;
	@FXML TableColumn<CutOffHistoryEntry, Long> colFolio;
	@FXML TableColumn<CutOffHistoryEntry, String> colDate;
	@FXML TableColumn<CutOffHistoryEntry, String> colTime;
	@FXML TableColumn<CutOffHistoryEntry, String> colAdmin;
	@FXML TableColumn<CutOffHistoryEntry, Float> colRemaining;
	@FXML TableColumn<CutOffHistoryEntry, Float> colAmount;
	
	@FXML JFXTextField txtFolio;
	@FXML JFXTextField txtDate;
	@FXML JFXTextField txtTime;
	@FXML JFXTextField txtAdmin;
	@FXML JFXTextField txtRemaining;
	@FXML JFXTextField txtAmount;
	
	@FXML Label lblCashRegister;
	@FXML Label lblRemaining;
	
	private DatePickerMethods dpm = new DatePickerMethods();
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseLabels();
		initialiseColumns();
		initialiseTooltipText();										//1. Initialising tooltip texts
		showHistory();
		initialiseListeners();
		initialiseDatePickers();
		//initialiseHistoryHeaders();
		//initialiseHistoryDetailsHeaders();
		dtPckrSearch.requestFocus();
	}
	
	public void initialiseLabels() {
		lblCashRegister.setText(Dashboard.getDashboardController().getCashRegister().getName());
		printCash();
	}
	
	public void printCash() {
		lblRemaining.setText("Efectivo en caja:   $ " + String.format("%.2f", Dashboard.getDashboardController().getCashRegister().getRemaining()));
	}
	
	public void initialiseColumns() {
		colFolio.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, Long>("folio"));
		colDate.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, String>("strConventionalDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, String>("strTime"));
		colAdmin.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, String>("admin"));
		colRemaining.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, Float>("remaining"));
		colAmount.setCellValueFactory(new PropertyValueFactory<CutOffHistoryEntry, Float>("amount"));
	}
	
	public void initialiseTooltipText() {
		tabCutOffs.setTooltip(new Tooltip("Selecciona un corte de caja de la lista para ver más detalles."));
	}
	
	public void initialiseListeners() {
		tabCutOffs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CutOffHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends CutOffHistoryEntry> observableValue, CutOffHistoryEntry oldValue, CutOffHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabCutOffs.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabCutOffs);
		        }
	         }
		});
	}
	
	public void updateTableSelection(TableView<CutOffHistoryEntry> tableView) {
		TableViewSelectionModel<CutOffHistoryEntry> selectionModel = tableView.getSelectionModel();
		CutOffHistoryEntry selectedItem = selectionModel.getSelectedItem();
        txtFolio.setText(String.valueOf(selectedItem.getFolio()));
        txtDate.setText(selectedItem.getStrConventionalDate());
        txtTime.setText(selectedItem.getStrTime());
        txtAdmin.setText(selectedItem.getAdmin());
        txtRemaining.setText("$"+String.format("%.2f", selectedItem.getRemaining()));
        txtAmount.setText("$"+String.format("%.2f", selectedItem.getAmount()));
	}
	
	public void initialiseDatePickers() {
		dpm.setConverter(dtPckrSearch);
	}
	
	//--------------------------------------- LOADING VIEWS ------------------------------------//
	public void loadCashierCutOffAddView() {
		Dashboard.getDashboardController().cashierCutOffAddWithoutMenu();
	}
	
	//--------------------------------------- METHODS FOR ADDING A CUT OFF ------------------------------------//
	@FXML
	protected void addCutOff() {
		loadCashierCutOffAddView();
	}
	
	//-----------------------------------------
	@FXML
	protected void showCutOffsHistory() {
		showHistory();
	}
	
	public void showHistory() {
		List<CutOff> cutOffs = getCutOffList();
		if(cutOffs != null) {
			setTableContent(cutOffs);
		} 
	}
	
	//------------------------------- METHODS FOR SHOWING ALL CUTOFFS ----------------------------------//
	
	public List<CutOff> getCutOffList() {
		CashRegisterService service = new CashRegisterService();
		List<CutOff> data = service.showCutOffs();
		cleanCutOffDetails();									//Cleans the fields filled with information about the selected product
		return data;
	}
	
	public void setTableContent(List<CutOff> data) {
		List<CutOffHistoryEntry> cutOffHistory = getCutOffHistory(data); 
		fillTable(cutOffHistory);
	}
	
	private List<CutOffHistoryEntry> getCutOffHistory(List<CutOff> data) {
		List<CutOffHistoryEntry> cutOffHistory = new ArrayList<CutOffHistoryEntry>();
		for(CutOff c : data) {
			String strConventionalDate = dpm.dateToConventionalString(c.getDate());
			String strDate = dpm.dateToString(c.getDate());
			String strTime = dpm.timeToString(c.getDate());
			CutOffHistoryEntry cohe = new CutOffHistoryEntry(c.getFolio(), strConventionalDate, strDate, strTime, c.getDate(), c.getUser().getName() + c.getUser().getLastName(), c.getRemaining(), c.getAmount(), c);
			cutOffHistory.add(cohe);
		}
		return cutOffHistory;
	}
	
	private void fillTable(List<CutOffHistoryEntry> cutOffsHistory) {
		ObservableList<CutOffHistoryEntry> cutOffs = FXCollections.observableArrayList(cutOffsHistory);
		tabCutOffs.setItems(cutOffs);
		setSortType(tabCutOffs, colFolio);
	}
	
	private void setSortType(TableView<CutOffHistoryEntry> table, TableColumn<CutOffHistoryEntry, Long> column) {
		column.setSortType(SortType.DESCENDING);
		table.getSortOrder().clear();
		table.getSortOrder().add(column);
	}
		
	//------------------------------- METHODS FOR CLEANING GRAPHIC COMPONENTS ----------------------------------//
	
	public void cleanCutOffDetails() {
		txtFolio.clear();
     	txtDate.clear();
     	txtTime.clear();
     	txtAdmin.clear();
     	txtRemaining.clear();
     	txtAmount.clear();
	}
}
