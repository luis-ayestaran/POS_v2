package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.entities.CutOff;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.CashRegisterService;
import com.masterdev.student.views.CashCalculator;
import com.masterdev.student.views.CashierCutOffAdd;
import com.masterdev.student.views.CashierCutOffList;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.InventoryList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class CashierCutOffAddController implements Initializable {
	
	@FXML TextField txtAccountedCash;
	@FXML TextField txtCalculatedCash;
	@FXML TextField txtDifferenceCash;
	@FXML TextField txtWithdrawCash;
	
	@FXML TextField txtAccountedChecks;
	@FXML TextField txtCalculatedChecks;
	@FXML TextField txtDifferenceChecks;
	@FXML TextField txtWithdrawChecks;
	
	@FXML TextField txtAccountedVouchers;
	@FXML TextField txtCalculatedVouchers;
	@FXML TextField txtDifferenceVouchers;
	@FXML TextField txtWithdrawVouchers;
	
	@FXML TextField txtAccountedCards;
	@FXML TextField txtCalculatedCards;
	@FXML TextField txtDifferenceCards;
	@FXML TextField txtWithdrawCards;
	
	@FXML TextField txtAccountedTotal;
	@FXML TextField txtCalculatedTotal;
	@FXML TextField txtDifferenceTotal;
	@FXML TextField txtWithdrawTotal;
	
	@FXML Label lblCashRegister;
	@FXML Label lblRemaining;
	
	@FXML Button btnCalculator;
	
	private Float cashAccounted = 0f;
	private Float checksAccounted = 0f;
	private Float vouchersAccounted = 0f;
	private Float cardsAccounted = 0f;
	
	private Float cashCalculated = 0f;
	private Float checksCalculated = 0f;
	private Float vouchersCalculated = 0f;
	private Float cardsCalculated = 0f;
	
	private Float cashDifference = 0f;
	private Float checksDifference = 0f;
	private Float vouchersDifference = 0f;
	private Float cardsDifference = 0f;
	
	private Float cashWithdraw = 0f;
	private Float checksWithdraw = 0f;
	private Float vouchersWithdraw = 0f;
	private Float cardsWithdraw = 0f;
	
	private Float totalAccounted = 0f;
	private Float totalCalculated = 0f;
	private Float totalDifference = 0f;
	private Float totalWithdraw = 0f;
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseLabels();
		initialiseTextFields();
		initialiseListeners();
		
	} 
	
	private void initialiseLabels() {
		
	}
	
	private void initialiseTextFields() {
		txtAccountedCash.setText(String.format("%.2f", cashAccounted));
		CashRegister currentCashRegister = Dashboard.getDashboardController().getCashRegister();
		cashCalculated = currentCashRegister.getRemaining();
		txtCalculatedCash.setText(String.format("%.2f", cashCalculated));
		cashDifference = cashAccounted - cashCalculated;
		txtDifferenceCash.setText(String.format("%.2f", (cashDifference)));
		changeColor(cashDifference, txtDifferenceCash);
		txtWithdrawCash.setText(String.format("%.2f", (cashWithdraw)));
		
		txtAccountedChecks.setText(String.format("%.2f", (checksAccounted)));
		txtCalculatedChecks.setText(String.format("%.2f", (checksCalculated)));
		checksDifference = checksAccounted - checksCalculated;
		txtDifferenceChecks.setText(String.format("%.2f", (checksDifference)));
		changeColor(checksDifference, txtDifferenceChecks);
		txtWithdrawChecks.setText(String.format("%.2f", (checksWithdraw)));
		
		txtAccountedVouchers.setText(String.format("%.2f", (vouchersAccounted)));
		txtCalculatedVouchers.setText(String.format("%.2f", (vouchersCalculated)));
		vouchersDifference = vouchersAccounted - vouchersCalculated;
		txtDifferenceVouchers.setText(String.format("%.2f", (vouchersDifference)));
		changeColor(vouchersDifference, txtDifferenceVouchers);
		txtWithdrawVouchers.setText(String.format("%.2f", (vouchersWithdraw)));
		
		txtAccountedCards.setText(String.format("%.2f", (cardsAccounted)));
		txtCalculatedCards.setText(String.format("%.2f", (cardsAccounted)));
		cardsDifference = cardsAccounted - cardsCalculated;
		txtDifferenceCards.setText(String.format("%.2f", (cardsDifference)));
		changeColor(cardsDifference, txtDifferenceCards);
		txtWithdrawCards.setText(String.format("%.2f", (cardsWithdraw)));
		
		totalAccounted = refreshTotalAccounted();
		txtAccountedTotal.setText(String.format("%.2f", totalAccounted));
		totalCalculated = refreshTotalCalculated();
		txtCalculatedTotal.setText(String.format("%.2f", totalCalculated));
		totalDifference = refreshTotalDifference();
		txtDifferenceTotal.setText(String.format("%.2f", totalDifference));
		changeColor(totalDifference, txtDifferenceTotal);
		totalWithdraw = refreshTotalWithdraw();
		txtWithdrawTotal.setText(String.format("%.2f", totalWithdraw));
	}
	
	public void initialiseListeners() {
		txtAccountedCash.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					cashAccounted = Float.parseFloat(newValue);
				} else {
					cashAccounted = 0.0f;
				}
				cashCalculated = Float.parseFloat(txtCalculatedCash.getText());
				cashDifference = cashAccounted - cashCalculated;
				txtDifferenceCash.setText(String.format("%.2f", cashDifference));
				setTotals();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtAccountedCash);
				txtAccountedCash.setText(oldValue);
			}
		});
		
		txtAccountedVouchers.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					vouchersAccounted = Float.parseFloat(newValue);
				} else {
					vouchersAccounted = 0.0f;
				}
				vouchersCalculated = Float.parseFloat(txtCalculatedVouchers.getText());
				vouchersDifference = vouchersAccounted - vouchersCalculated;
				txtDifferenceVouchers.setText(String.format("%.2f", vouchersDifference));
				setTotals();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtAccountedVouchers);
				txtAccountedVouchers.setText(oldValue);
			}
		});
		
		txtAccountedChecks.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					checksAccounted = Float.parseFloat(newValue);
				} else {
					checksAccounted = 0.0f;
				}
				checksCalculated = Float.parseFloat(txtCalculatedChecks.getText());
				checksDifference = checksAccounted - checksCalculated;
				txtDifferenceChecks.setText(String.format("%.2f", checksDifference));
				setTotals();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtAccountedChecks);
				txtAccountedChecks.setText(oldValue);
			}
		});
		
		txtAccountedCards.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(newValue.trim().equals("")) {
					cardsAccounted = Float.parseFloat(newValue);
				} else {
					cardsAccounted = 0.0f;
				}
				cardsCalculated = Float.parseFloat(txtCalculatedCards.getText());
				cardsDifference = cardsAccounted - cardsCalculated;
				txtDifferenceCards.setText(String.format("%.2f", cardsDifference));
				setTotals();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtAccountedCards);
				txtAccountedCards.setText(oldValue);
			}
		});
		
		txtWithdrawCash.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					cashWithdraw = Float.parseFloat(newValue);
				} else {
					cashWithdraw = 0.0f;
				}
				if(cashWithdraw <= cashAccounted) { 
					txtWithdrawTotal.setText(String.format("%.2f", refreshTotalWithdraw()));
				} else {
					if(!oldValue.trim().equals("")) {
						cashWithdraw = Float.parseFloat(oldValue);
					} else {
						cashWithdraw = 0.0f;
					}
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"La cantidad de efectivo que puedes retirar no puede ser mayor que \nla cantidad de efectivo contada.",
							(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawCash);
					txtWithdrawCash.setText(oldValue);
				}
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawCash);
				txtWithdrawCash.setText(oldValue);
			}
		});
		
		txtWithdrawVouchers.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					vouchersWithdraw = Float.parseFloat(newValue);
				} else {
					vouchersWithdraw = 0.0f;
				}
				if(vouchersWithdraw <= vouchersAccounted) {
					txtWithdrawTotal.setText(String.format("%.2f", refreshTotalWithdraw()));
				} else {
					vouchersWithdraw = Float.parseFloat(oldValue);
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"La cantidad en vales que puedes retirar no puede ser mayor que \nla cantidad en vales contada.",
							(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawVouchers);
					txtWithdrawVouchers.setText(oldValue);
				}
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawVouchers);
				txtWithdrawVouchers.setText(oldValue);
			}
		});
		
		txtWithdrawChecks.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					checksWithdraw = Float.parseFloat(newValue);
				} else {
					checksWithdraw = 0.0f;
				}
				if(checksWithdraw <= checksAccounted) {
					txtWithdrawTotal.setText(String.format("%.2f", refreshTotalWithdraw()));
				} else {
					checksWithdraw = Float.parseFloat(oldValue);
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"La cantidad en cheques que puedes retirar no puede ser mayor que \nla cantidad en cheques contada.",
							(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawChecks);
					txtWithdrawChecks.setText(oldValue);
				}
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawChecks);
				txtWithdrawChecks.setText(oldValue);
			}
		});
		
		txtWithdrawCards.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					cardsWithdraw = Float.parseFloat(newValue);
				} else {
					cardsWithdraw = 0.0f;
				}
				if(cardsWithdraw <= cardsAccounted) {
					totalWithdraw = refreshTotalWithdraw();
					txtWithdrawTotal.setText(String.format("%.2f", refreshTotalWithdraw()));
				} else {
					checksWithdraw = Float.parseFloat(oldValue);
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"La cantidad en tareta que puedes retirar no puede ser mayor que \nla cantidad en tarjeta contada.",
							(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawCards);
					txtWithdrawCards.setText(oldValue);
				}
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashierCutOffAdd.getStage().getScene().getRoot(), txtWithdrawCards);
				txtWithdrawCards.setText(oldValue);
			}
		});
	}
	
	public void loadCashCalculatorView() {
		CashCalculator cc = new CashCalculator();
		cc.loadView();
	}
	
	@FXML
	protected void openCalculator() {
		loadCashCalculatorView();
	}
	
	private void changeColor(Float difference, TextField textField) {
		if(difference >= 0) {
			textField.getStyleClass().removeAll("warning-text-field");
			textField.getStyleClass().add("ok-text-field");
		} else {
			textField.getStyleClass().removeAll("ok-text-field");
			textField.getStyleClass().add("warning-text-field");
		}
	}
	
	private void setTotals() {
		totalAccounted = refreshTotalAccounted();
		totalDifference = refreshTotalDifference();
		txtAccountedTotal.setText(String.format("%.2f", totalAccounted));
		txtDifferenceTotal.setText(String.format("%.2f", totalDifference));
	}
	
	public void setAccountedCash(Float accountedCash) {
		cashAccounted = accountedCash;
		txtAccountedCash.setText(String.format("%.2f", cashAccounted));
	}
	
	private Float refreshTotalAccounted() {
		return cashAccounted + checksAccounted + vouchersAccounted + cardsAccounted;
	}
	
	private Float refreshTotalCalculated() {
		return cashCalculated + checksCalculated + vouchersCalculated + cardsCalculated;
	}
	
	private Float refreshTotalDifference() {
		return cashDifference + checksDifference + vouchersDifference + cardsDifference;
	}
	
	private Float refreshTotalWithdraw() {
		return cashWithdraw + checksWithdraw + vouchersWithdraw + cardsWithdraw;
	}
	
	@FXML
	protected void acceptTransaction() {
		accept();
	}
	
	public void accept() {
		Dialogs d = new Dialogs();
		Boolean exit = d.confirmationDialog("Confirmación", "Realizar corte de caja", "¿Confirmas la realización del corte de caja actual? \nEfectivo en caja: \t\t" + String.format("%.2f", Dashboard.getDashboardController().getCashRegister().getRemaining()) + "\nRetiro: \t\t\t\t" + String.format("%.2f", cashAccounted));
		if(exit) {
			addCashierCutOff();
			exportToExcel();
			closeStageCompletely();
			updateDashboardViewsData();	
		}
		
	}
	
	public void addCashierCutOff() {
		CashRegisterService service = new CashRegisterService();
		DatePickerMethods dpm = new DatePickerMethods();
		CutOff cutOff = new CutOff(dpm.getCurrentDateTime(), Dashboard.getDashboardController().getCashRegister().getRemaining(), totalWithdraw, Dashboard.getDashboardController().getUser(), Dashboard.getDashboardController().getCashRegister());
		Dashboard.getDashboardController().getCashRegister().setRemaining(Dashboard.getDashboardController().getCashRegister().getRemaining() - totalWithdraw);
		service.updateCashRegister(Dashboard.getDashboardController().getCashRegister());
		service.addCutOff(cutOff);
	}
	
	public void exportToExcel() {
		
	}
	
	public void updateDashboardViewsData() {
		List<CutOff> cutOffs = null;
		if(CashierCutOffList.getCashierCutOffListController() != null) {
			CashierCutOffList.getCashierCutOffListController().showHistory();		//Refresh the product list in case it is open
			CashierCutOffList.getCashierCutOffListController().printCash();
		}
	}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		closeStageCompletely();
	}
	
	public void closeStageCompletely() {
		if(CashierCutOffAdd.getStage() != null) {
			CashierCutOffAdd.getStage().close();
			CashierCutOffAdd.setStage(null);
		}
	}
	
}
