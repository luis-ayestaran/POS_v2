package com.masterdev.student.views.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.pojos.SalesHistoryEntry;
import com.masterdev.student.services.ExcelService;
import com.masterdev.student.services.SaleService;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.SalesForm;

public class SalesHistoryController implements Initializable {
	
	@FXML JFXDatePicker dtPckrDate;
	
	@FXML TableView<SalesHistoryEntry> tabSales1;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio1;
	@FXML TableColumn<SalesHistoryEntry, String> colDate1;
	@FXML TableColumn<SalesHistoryEntry, String> colTime1;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount1;
	
	@FXML TableView<SalesHistoryEntry> tabSales2;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio2;
	@FXML TableColumn<SalesHistoryEntry, String> colDate2;
	@FXML TableColumn<SalesHistoryEntry, String> colTime2;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount2;
	@FXML Label lblTitle2;
	
	@FXML TableView<SalesHistoryEntry> tabSales3;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio3;
	@FXML TableColumn<SalesHistoryEntry, String> colDate3;
	@FXML TableColumn<SalesHistoryEntry, String> colTime3;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount3;
	@FXML Label lblTitle3;
	
	@FXML TableView<SalesHistoryEntry> tabSales4;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio4;
	@FXML TableColumn<SalesHistoryEntry, String> colDate4;
	@FXML TableColumn<SalesHistoryEntry, String> colTime4;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount4;
	@FXML Label lblTitle4;
	
	@FXML TableView<SalesHistoryEntry> tabSales5;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio5;
	@FXML TableColumn<SalesHistoryEntry, String> colDate5;
	@FXML TableColumn<SalesHistoryEntry, String> colTime5;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount5;
	@FXML Label lblTitle5;
	
	@FXML TableView<SalesHistoryEntry> tabSales6;
	@FXML TableColumn<SalesHistoryEntry, Long> colFolio6;
	@FXML TableColumn<SalesHistoryEntry, String> colDate6;
	@FXML TableColumn<SalesHistoryEntry, String> colTime6;
	@FXML TableColumn<SalesHistoryEntry, Float> colAmount6;
	@FXML Label lblTitle6;
	
	@FXML JFXTextField txtFolio;
	@FXML JFXTextField txtDate;
	@FXML JFXTextField txtTime;
	@FXML JFXTextField txtArticleNumber;
	@FXML JFXTextField txtAmount;
	
	@FXML JFXButton btnShowDetails;
	
	private DatePickerMethods dpm = new DatePickerMethods();
	
	private List<String> headers;
	private List<String> detailsHeaders;
	
	public void initialize(URL location, ResourceBundle resources) {
		initCols();
		initialiseTooltipText();										//1. Initialising tooltip texts
		showHistory();
		initialiseListeners();
		initialiseDatePickers();
		initialiseHistoryHeaders();
		initialiseHistoryDetailsHeaders();
		dtPckrDate.requestFocus();
	}
	
	public void initCols() {
		colFolio1.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate1.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime1.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount1.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
		
		colFolio2.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate2.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime2.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount2.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
		
		colFolio3.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate3.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime3.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount3.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
		
		colFolio4.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate4.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime4.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount4.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
		
		colFolio5.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate5.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime5.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount5.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
		
		colFolio6.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Long>("folio"));
		colDate6.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strConventionalDate"));
		colTime6.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, String>("strTime"));
		colAmount6.setCellValueFactory(new PropertyValueFactory<SalesHistoryEntry, Float>("total"));
	}
	
	public void initialiseTooltipText() {
		tabSales1.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
		tabSales2.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
		tabSales3.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
		tabSales4.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
		tabSales5.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
		tabSales6.setTooltip(new Tooltip("Selecciona una venta de la lista para ver más detalles."));
	}
	
	public void initialiseListeners() {
		dpm.setConverter(dtPckrDate);
		tabSales1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales1.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales1);
		        }
	         }
		});
		
		tabSales2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales2.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales2);
		        }
		    }
		});
		
		tabSales3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales3.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales3);
		        }
	         }
		});
		
		tabSales4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales4.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales4);
		        }
	         }
		});
		
		tabSales5.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales5.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales5);
		        }
	         }
		});
		
		tabSales6.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalesHistoryEntry>() {
		    @Override
		    public void changed(ObservableValue<? extends SalesHistoryEntry> observableValue, SalesHistoryEntry oldValue, SalesHistoryEntry newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabSales6.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection(tabSales6);
		        }
	         }
		});
	}
	
	public void updateTableSelection(TableView<SalesHistoryEntry> tableView) {
		TableViewSelectionModel<SalesHistoryEntry> selectionModel = tableView.getSelectionModel();
		SalesHistoryEntry selectedItem = selectionModel.getSelectedItem();
        txtFolio.setText(String.valueOf(selectedItem.getFolio()));
        txtDate.setText(selectedItem.getStrConventionalDate());
        txtTime.setText(selectedItem.getStrTime());
        txtArticleNumber.setText(String.valueOf(selectedItem.getSale().getDetail().size()));
        txtAmount.setText("$"+String.format("%.2f", selectedItem.getTotal()));
	}
	
	public void initialiseDatePickers() {
		dpm.setConverter(dtPckrDate);
	}
	
	public void setHeaders() {
		switch(dpm.getDayOfWeek(dpm.getCurrentDate())) {
			case Calendar.MONDAY: lblTitle2.setText("Lunes");
					break;
			case Calendar.TUESDAY: lblTitle2.setText("Martes");
					break;
			case Calendar.WEDNESDAY: lblTitle2.setText("Miércoles");
					break;
			case Calendar.THURSDAY: lblTitle2.setText("Jueves");
					break;
			case Calendar.FRIDAY: lblTitle2.setText("Viernes");
					break;
			case Calendar.SATURDAY: lblTitle2.setText("Sábado");
					break;
			case Calendar.SUNDAY: lblTitle2.setText("Domingo");
					break;
		}
		
		switch(dpm.getDayOfWeek(dpm.getNDaysAgo(1))) {
			case Calendar.MONDAY: lblTitle3.setText("Lunes");
					break;
			case Calendar.TUESDAY: lblTitle3.setText("Martes");
					break;
			case Calendar.WEDNESDAY: lblTitle3.setText("Miércoles");
					break;
			case Calendar.THURSDAY: lblTitle3.setText("Jueves");
					break;
			case Calendar.FRIDAY: lblTitle3.setText("Viernes");
					break;
			case Calendar.SATURDAY: lblTitle3.setText("Sábado");
					break;
			case Calendar.SUNDAY: lblTitle3.setText("Domingo");
					break;
		}
		
		switch(dpm.getMonth(dpm.getCurrentDate())) {
			case Calendar.JANUARY: lblTitle5.setText("Enero");
					break;
			case Calendar.FEBRUARY: lblTitle5.setText("Febrero");
					break;
			case Calendar.MARCH: lblTitle5.setText("Marzo");
					break;
			case Calendar.APRIL: lblTitle5.setText("Abril");
					break;
			case Calendar.MAY: lblTitle5.setText("Mayo");
					break;
			case Calendar.JUNE: lblTitle5.setText("Junio");
					break;
			case Calendar.JULY: lblTitle5.setText("Julio");
					break;
			case Calendar.AUGUST: lblTitle5.setText("Agosto");
					break;
			case Calendar.SEPTEMBER: lblTitle5.setText("Septiembre");
					break;
			case Calendar.OCTOBER: lblTitle5.setText("Octubre");
					break;
			case Calendar.NOVEMBER: lblTitle5.setText("Noviembre");
					break;
			case Calendar.DECEMBER: lblTitle5.setText("Diciembre");
					break;
		}
		
		lblTitle6.setText(String.valueOf(dpm.getYear(dpm.getCurrentDate())));
	}
	
	private void initialiseHistoryHeaders() {
		headers = new ArrayList<String>();
		headers.add("Folio");
		headers.add("Fecha");
		headers.add("Hora");
		headers.add("No. de artículos");
		headers.add("Importe");
	}
	
	private void initialiseHistoryDetailsHeaders() {
		detailsHeaders = new ArrayList<String>();
		detailsHeaders.add("Folio de venta");
		detailsHeaders.add("Producto");
		detailsHeaders.add("Precio unitario");
		detailsHeaders.add("Cantidad");
		detailsHeaders.add("Subtotal");
	}
	
	//------------------------------- METHODS FOR SHOWING ALL PRODUCTS ----------------------------------//
	
	public List<Sale> getSaleList() {
														//Initialises the values of the products registered in the table
		SaleService service = new SaleService();
		List<Sale> data = service.showSales();
		//setTableContent(data);
		cleanSaleDetails();									//Cleans the fields filled with information about the selected product
		
		return data;
	}
	
	public void setTableContent(List<Sale> data) {
		ArrayList<SalesHistoryEntry> salesHistory = getSalesHistory(data); 
		fillSearchTable(salesHistory);
		fillTodayTable(salesHistory);
		fillYesterdayTable(salesHistory);
		fillThisWeekTable(salesHistory);
		fillThisMonthTable(salesHistory);
		fillThisYearTable(salesHistory);
	}
	
	private ArrayList<SalesHistoryEntry> getSalesHistory(List<Sale> data) {
		if(SalesForm.getNode() != null) {
			data.remove(data.size() - 1);
		}
		ArrayList<SalesHistoryEntry> salesHistory = new ArrayList<SalesHistoryEntry>();
		for(Sale s : data) {
			String strConventionalDate = dpm.dateToConventionalString(s.getDate());
			String strDate = dpm.dateToString(s.getDate());
			String strTime = dpm.timeToString(s.getDate());
			SalesHistoryEntry she = new SalesHistoryEntry(s.getFolio(), strConventionalDate, strDate, strTime, s.getTotal(), s.getDate(), s);
			salesHistory.add(she);
		}
		return salesHistory;
	}
	
	private void fillSearchTable(ArrayList<SalesHistoryEntry> salesHistory) {
		ObservableList<SalesHistoryEntry> products = FXCollections.observableArrayList(salesHistory);
		tabSales1.setItems(products);
		setSortType(tabSales1, colFolio1);
	}
	
	private void fillSearchTable(ArrayList<SalesHistoryEntry> salesHistory, Date date) {
		tabSales1.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.compareDates(date, dpm.stringToDate(entry.getStrDate())) == 0) {
				tabSales1.getItems().add(entry);
			}
		}
		setSortType(tabSales1, colFolio1);
	}
	
	private void fillTodayTable(ArrayList<SalesHistoryEntry> salesHistory) {
		tabSales2.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.compareDates(dpm.getCurrentDate(), dpm.stringToDate(entry.getStrDate())) == 0) {
				tabSales2.getItems().add(entry);
			}
		}
		setSortType(tabSales2, colFolio2);
	}
	
	private void fillYesterdayTable(ArrayList<SalesHistoryEntry> salesHistory) {
		tabSales3.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.compareDates(dpm.getNDaysAgo(1), dpm.stringToDate(entry.getStrDate())) == 0) {
				tabSales3.getItems().add(entry);
			}
		}
		setSortType(tabSales3, colFolio3);
	}
	
	private void fillThisWeekTable(ArrayList<SalesHistoryEntry> salesHistory) {
		tabSales4.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.compareDates(dpm.getNDaysAgo(7), dpm.stringToDate(entry.getStrDate())) == 0 || dpm.compareDates(dpm.getNDaysAgo(7), dpm.stringToDate(entry.getStrDate())) == -1) {
				tabSales4.getItems().add(entry);
			}
		}
		setSortType(tabSales4, colFolio4);
	}
	
	private void fillThisMonthTable(ArrayList<SalesHistoryEntry> salesHistory) {
		tabSales5.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.areSameMonth(dpm.getCurrentDate(), dpm.stringToDate(entry.getStrDate()))) {
				tabSales5.getItems().add(entry);
			}
		}
		setSortType(tabSales5, colFolio5);
	}
	
	private void fillThisYearTable(ArrayList<SalesHistoryEntry> salesHistory) {
		tabSales6.getItems().clear();
		for(SalesHistoryEntry entry : salesHistory) {
			if(dpm.areSameYear(dpm.getCurrentDate(), dpm.stringToDate(entry.getStrDate()))) {
				tabSales6.getItems().add(entry);
			}
		}
		setSortType(tabSales6, colFolio6);
	}
	
	private void setSortType(TableView<SalesHistoryEntry> table, TableColumn<SalesHistoryEntry, Long> column) {
		column.setSortType(SortType.DESCENDING);
		table.getSortOrder().clear();
		table.getSortOrder().add(column);
	}
	
	@FXML
	protected void searchDate() {
		SaleService service = new SaleService();
		List<Sale> data = service.showSales();
		ArrayList<SalesHistoryEntry> salesHistory = getSalesHistory(data);
		fillSearchTable(salesHistory, dpm.localDateToUtilDate(dtPckrDate.getValue()));
	}
	
	@FXML
	protected void showSalesHistory() {
		showHistory();
	}
	
	public void showHistory() {
		List<Sale> sales = getSaleList();
		setHeaders();
		if(sales != null) {
			setTableContent(sales);
		} 
	}
	
	//------------------------------- METHODS FOR CLEANING GRAPHIC COMPONENTS ----------------------------------//
		
		public void cleanSaleDetails() {
			txtFolio.clear();
	     	txtDate.clear();
	     	txtTime.clear();
	     	txtArticleNumber.clear();
	     	txtAmount.clear();
		}
	
	//------------------------------- METHODS FOR EXPORTING DOCUMENTS ----------------------------------//	
	@FXML
	protected void dateExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(1, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales1.getItems());
		if(saved) {
			fileSavedCorrectly(dtPckrDate);
		} else {
			fileNotSaved(null);
		}
	}
	
	@FXML
	protected void todayExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(2, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales2.getItems());
		if(saved) {
			fileSavedCorrectly(null);
		} else {
			fileNotSaved(null);
		}
	}
	
	@FXML
	protected void yesterdayExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(3, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales3.getItems());
		if(saved) {
			fileSavedCorrectly(null);
		} else {
			fileNotSaved(null);
		}
	}
	
	@FXML
	protected void weekExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(4, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales4.getItems());
		if(saved) {
			fileSavedCorrectly(null);
		} else {
			fileNotSaved(null);
		}
	}
	
	@FXML
	protected void monthExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(5, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales5.getItems());
		if(saved) {
			fileSavedCorrectly(null);
		} else {
			fileNotSaved(null);
		}
	}
	
	@FXML
	protected void yearExportToExcel() {
		ExcelService service = new ExcelService();
		Boolean saved = service.writeHistory(6, dpm.dateToString(dpm.getCurrentDate()), headers, detailsHeaders, tabSales6.getItems());
		if(saved) {
			fileSavedCorrectly(null);
		} else {
			fileNotSaved(null);
		}
	}
	
	private void fileSavedCorrectly(Node focusable) {
		Dialogs d = new Dialogs();
		d.acceptDialog("Documento guargado con éxito",
				"Encuentra tu archivo en tu escritorio.",
				(StackPane)Dashboard.getStage().getScene().getRoot(), focusable);
	}
	
	private void fileNotSaved(Node focusable) {
		Dialogs d = new Dialogs();
		d.acceptDialog("No se ha podido guardar el documento",
				"El archivo se encuentra abierto. Ciérralo antes de guardar de nuevo.",
				(StackPane)Dashboard.getStage().getScene().getRoot(), focusable);
	}
	
}
