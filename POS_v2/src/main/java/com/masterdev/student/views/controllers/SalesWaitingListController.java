package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.pojos.SaleWaiting;
import com.masterdev.student.pojos.WaitingList;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.SalesForm;
import com.masterdev.student.views.SalesWaitingList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class SalesWaitingListController implements Initializable {
	
	@FXML TableView<SaleWaiting> tabSales;
	
	@FXML TableColumn<SaleWaiting, Long> colFolio;
	@FXML TableColumn<SaleWaiting, String> colClient;
	@FXML TableColumn<SaleWaiting, String> colDate;
	@FXML TableColumn<SaleWaiting, String> colTime;
	@FXML TableColumn<SaleWaiting, Float> colTotal;
	
	@FXML JFXButton btnShowSales;
	@FXML JFXButton btnAccept;
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTooltipText();
		initialiseTableView();
		List<SaleWaiting> waitingList = WaitingList.getWaitingList();
		if(waitingList != null) {
			setTableContent(waitingList);
		}
		initialiseListeners();
	}
	
	public void initialiseTooltipText() {
		tabSales.setTooltip(new Tooltip("Selecciona una venta de la lista."));
	}
	
	public void initialiseTableView() {
		initCols();
	}
	
	public void initCols() {
		colFolio.setCellValueFactory(new PropertyValueFactory<SaleWaiting, Long>("folio"));
		colClient.setCellValueFactory(new PropertyValueFactory<SaleWaiting, String>("client"));
		colDate.setCellValueFactory(new PropertyValueFactory<SaleWaiting, String>("strDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<SaleWaiting, String>("strTime"));
		colTotal.setCellValueFactory(new PropertyValueFactory<SaleWaiting, Float>("total"));
	}
	
	public void setTableContent(List<SaleWaiting> data) {
		ObservableList<SaleWaiting> products = FXCollections.observableArrayList(data);
		tabSales.setItems(products);
	}
	
	public void initialiseListeners() {
		tabSales.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
		    public void handle(MouseEvent click) {
		        if (click.getClickCount() == 2) {
		        	System.out.println("Listened double click");
		        	accept();
		        }
			}
	    });
	}
	
	// ------------------------------ METHODS FOR FINISHING THE TRANSACTION ------------------------------------//
	
	@FXML
	protected void acceptTransaction() {
		accept();
	}
	
	public void accept() {
		if(!tabSales.getItems().isEmpty()) {
			if(tabSales.getSelectionModel().getSelectedItem() != null) {
				currentSaleToWaitingList();
				openSelectedSale(tabSales.getSelectionModel().getSelectedItem(), tabSales.getSelectionModel().getSelectedIndex());
				SalesWaitingList.getStage().close();
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Ninguna venta seleccionada",
						"Selecciona, en la lista de ventas en espera, la venta que deseas retomar.",
						(StackPane)SalesWaitingList.getStage().getScene().getRoot(), null);
			}
		}  else {
			SalesWaitingList.getStage().close();
		}
	}
	
	private void openSelectedSale(SaleWaiting saleWaiting, int index) {
		SalesForm.setNode(saleWaiting.getView());
		SalesForm.setSalesFormController(saleWaiting.getData());
		Dashboard.getDashboardController().sclMainView.setContent(saleWaiting.getView());
		WaitingList.getWaitingList().remove(index);
	}
	
	private void currentSaleToWaitingList() {
		DatePickerMethods dpm = new DatePickerMethods();
		SaleWaiting saleWaiting = new SaleWaiting(SalesForm.getSalesFormController().getSale().getFolio(), SalesForm.getSalesFormController().txtClient.getText(), dpm.dateToString(SalesForm.getSalesFormController().getSale().getDate()), dpm.timeToString(SalesForm.getSalesFormController().getSale().getDate()), SalesForm.getSalesFormController().getTotal(), SalesForm.getSalesFormController().getSale(), SalesForm.getNode(), SalesForm.getSalesFormController());
		WaitingList.addSaleToWaitingList(saleWaiting);
	}
	
	@FXML
	protected void cancelSale() {
		cancel();
	}
	
	public void cancel() {
		if(tabSales.getSelectionModel().getSelectedItem() != null) {
			Dialogs d = new Dialogs();
			Boolean exit = d.confirmationDialog("Confirmación", "Cancelar venta", "¿Realmente deseas cancelar esta venta?\n");
			if(exit) {
				deleteSale(tabSales.getSelectionModel().getSelectedItem().getSale(), tabSales.getSelectionModel().getSelectedIndex());
				List<SaleWaiting> waitingList = WaitingList.getWaitingList();
				if(waitingList != null) {
					setTableContent(waitingList);
				}
			}
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Ninguna venta seleccionada",
					"Selecciona, en la lista de ventas en espera, la venta que deseas cancelar.",
					(StackPane)SalesWaitingList.getStage().getScene().getRoot(), null);
		}
	}
	
	public void deleteSale(Sale sale, Integer index) {
		SalesForm.getSalesFormController().cancelSale(sale);
		WaitingList.removeSaleFromWaitingList(index);
	}
	
	@FXML
	protected void cancelAllSales() {
		cancelAll();
	}
	
	public void cancelAll() {
		List<SaleWaiting> waitingList = WaitingList.getWaitingList();
		if(waitingList != null) {
			while(waitingList.size() > 0) {
				deleteSale(waitingList.get(0).getSale(), 0);
			}
		}
	}
}
