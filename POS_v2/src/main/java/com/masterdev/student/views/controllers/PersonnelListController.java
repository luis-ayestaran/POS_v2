package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.masterdev.student.entities.Employee;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.services.PersonnelService;
import com.masterdev.student.views.Dashboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;

public class PersonnelListController implements Initializable {
	
	@FXML TextField txtSearchEmployee;
	@FXML JFXButton btnSearchEmployee;
	
	@FXML TableView<Employee> tabEmployees;
	@FXML TableColumn<Employee, String> colName;
	@FXML TableColumn<Employee, String> colJob;
	@FXML TableColumn<Employee, String> colPhoneNumber;
	@FXML TableColumn<Employee, String> colEmail;
	@FXML TableColumn<Employee, String> colAddress;
	
	@FXML Label lblName;
	@FXML Label lblJob;
	
	@FXML JFXTextField txtPhoneNumber;
	@FXML JFXTextField txtEmail;
	@FXML JFXTextField txtAddress;
	
	@FXML JFXButton btnAddEmployee;
	@FXML JFXButton btnShowEmployees;
	
	@FXML JFXButton btnEditEmployee;
	@FXML JFXButton btnDeleteEmployee;
	
	private AutoCompletionBinding<String> employeeSuggestions;
	
	//--------------------------------------- INITIALISING ------------------------------------//
	public void initialize(URL location, ResourceBundle resources) {
		initCols();
		initialiseTooltipText();										//1. Initialising tooltip texts
		List<Employee> employees = showEmployeeList();
		suggestEmployees(employees);									//2. Add word suggestions (if needed)
		initialiseListeners();											//3. Add table listeners
	}
	
	public void initCols() {
		colName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		colJob.setCellValueFactory(new PropertyValueFactory<Employee, String>("job"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
		
		editableCols();
	}
	
	public void editableCols() {
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(e -> {
			Employee emp = e.getTableView().getItems().get(e.getTablePosition().getRow());
			emp.setName(e.getNewValue().trim());
			updateEmployee(emp);
			refreshSelectionAfterEdition();
		});
		colJob.setCellFactory(TextFieldTableCell.forTableColumn());
		colJob.setOnEditCommit(e ->  {
			Employee emp = e.getTableView().getItems().get(e.getTablePosition().getRow());
			emp.setJob(e.getNewValue().trim());
			updateEmployee(emp);
			refreshSelectionAfterEdition();
		});
		colPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		colPhoneNumber.setOnEditCommit(e ->  {
			Employee emp = e.getTableView().getItems().get(e.getTablePosition().getRow());
			emp.setPhoneNumber(e.getNewValue().trim());
			updateEmployee(emp);
			refreshSelectionAfterEdition();
		});
		colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		colEmail.setOnEditCommit(e ->  {
			Employee emp = e.getTableView().getItems().get(e.getTablePosition().getRow());
			emp.setEmail(e.getNewValue().trim());
			updateEmployee(emp);
			refreshSelectionAfterEdition();
		});
		colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
		colAddress.setOnEditCommit(e ->  {
			Employee emp = e.getTableView().getItems().get(e.getTablePosition().getRow());
			emp.setAddress(e.getNewValue().trim());
			updateEmployee(emp);
			refreshSelectionAfterEdition();
		});
		
		tabEmployees.setEditable(true);
	}
	
	public void initialiseTooltipText() {
		tabEmployees.setTooltip(new Tooltip("Selecciona un empleado de la lista."));
	}
	
	public void initialiseListeners() {
		tabEmployees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
		    @Override
		    public void changed(ObservableValue<? extends Employee> observableValue, Employee oldValue, Employee newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabEmployees.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection();
		        }
	         }
	     });
	}
	
	public void updateTableSelection() {
		TableViewSelectionModel<Employee> selectionModel = tabEmployees.getSelectionModel();
        Employee selectedItem = selectionModel.getSelectedItem();
        lblName.setText(selectedItem.getName());
        lblJob.setText(selectedItem.getJob());
        txtPhoneNumber.setText(selectedItem.getPhoneNumber());
        txtEmail.setText(selectedItem.getEmail());
        txtAddress.setText(selectedItem.getAddress());
	}
	
	//--------------------------------------- LOADING VIEWS ------------------------------------//
	public void loadPersonnelAddFormView() {
		Dashboard.getDashboardController().personnelAddFormWithoutMenu();
	}
		
	
	//--------------------------------------- METHODS FOR ADDING AN EMPLOYEE ------------------------------------//
	@FXML
	protected void addEmployee() {
		loadPersonnelAddFormView();
	}
	
	//------------------------------- METHODS FOR SEARCHING A PRODUCT ----------------------------------//
	@FXML
	protected void searchEmployee() {
		search();
	}
	
	public void search() {
		if(!txtSearchEmployee.getText().trim().equals("")) {
			Employee employeeFound = findEmployee(txtSearchEmployee.getText().trim());
			if(employeeFound != null) {
				showResult(employeeFound);
				selectFoundEmployee(employeeFound);
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Empleado no encontrado",
						"No se encontraron coincidencias.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchEmployee);
			}
			cleanSearchField();
		}
	}
	
	public Employee findEmployee(String employeeName) {
		Employee employeeReturn = null;
		Employee e = new Employee();
		e.setName(employeeName);
		PersonnelService service = new PersonnelService();
		employeeReturn = service.searchEmployee(e);
		return employeeReturn;
	}
	
	public void showResult(Employee result) {
		ArrayList<Employee> data = new ArrayList<Employee>();
		data.add(result);
		ObservableList<Employee> categories = FXCollections.observableArrayList(data);
		
		tabEmployees.setItems(categories);
	}
	
	protected void suggestEmployees(List<Employee> data) {
		if(data != null) {
			if(employeeSuggestions != null)
				employeeSuggestions.dispose(); 							//We get rid of the past list of word suggestions
			TextFieldMethods tfm = new TextFieldMethods();
			ArrayList<String> employees = new ArrayList<String>();
			for(Employee e : data) {
				employees.add(e.getName());
			}
			employeeSuggestions = tfm.addWordSuggestions(txtSearchEmployee, employees);
		}
	}

	//------------------------------- METHODS FOR SHOWING ALL PRODUCTS ----------------------------------//
	@FXML
	protected void showEmployees() {
		showEmployeeList();
	}
	
	public List<Employee> showEmployeeList() {
														//Initialises the values of the products registered in the table
		PersonnelService service = new PersonnelService();
		List<Employee> data = service.showEmployees();
		setTableContent(data);
		cleanEmployeeDetails();									//Cleans the fields filled with information about the selected product
		
		return data;
	}
	
	//Common methods when editing
	public void refreshSelectionAfterEdition() {
		tabEmployees.getSortOrder().clear();
		Employee selectedItem = tabEmployees.getSelectionModel().getSelectedItem();
		Integer selectedIndex = tabEmployees.getSelectionModel().getSelectedIndex();
		showEmployeeList();
		selectEditedEmployee(selectedItem, selectedIndex);
	}
	
	public void updateEmployee(Employee e) {
		PersonnelService service = new PersonnelService();
		service.updateEmployee(e);
	}
	
	public void setTableContent(List<Employee> data) {
		if(data != null) {//productList != null) {
			//data = debugProductList(productList);
			ObservableList<Employee> products = FXCollections.observableArrayList(data);
			tabEmployees.setItems(products);
		}
	}
	
	//Methods for catching the information coming from Adding and Editing category forms
	public void selectFoundEmployee(Employee employee) {
		tabEmployees.requestFocus();
		tabEmployees.getSelectionModel().selectFirst();
	}
	
	public void selectAddedEmployee(Employee employee) {
		tabEmployees.requestFocus();
		tabEmployees.getSelectionModel().selectLast();
		tabEmployees.scrollTo(tabEmployees.getSelectionModel().getSelectedIndex());
	}
	
	public void selectEditedEmployee(Employee employee, Integer index) {
		tabEmployees.requestFocus();
		tabEmployees.getSelectionModel().select(index);
		//tabEmployees.scrollTo(tabEmployees.getSelectionModel().getSelectedIndex());
	}
	
	//------------------------------- METHODS FOR CLEANING GRAPHIC COMPONENTS ----------------------------------//
	
	public void cleanSearchField() {
		txtSearchEmployee.setText("");
	}
	
	public void cleanEmployeeDetails() {
		lblName.setText("");
     	lblJob.setText("");
     	txtPhoneNumber.setText("");
     	txtEmail.setText("");
     	txtAddress.setText("");
	}
	
}
