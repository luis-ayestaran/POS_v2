package com.masterdev.student.views.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.masterdev.student.entities.Employee;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.ImageManagement;
import com.masterdev.student.services.PersonnelService;
import com.masterdev.student.views.PersonnelAddForm;
import com.masterdev.student.views.PersonnelList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;

public class PersonnelAddFormController implements Initializable {
	
	private final String DEFAULT_IMAGE = "/stylesheets/images/user_light.png";
	
	@FXML TextField txtName;
	@FXML TextField txtJob;
	@FXML TextField txtPhoneNumber;
	@FXML TextField txtEmail;
	@FXML TextField txtAddress;
	@FXML TextField txtSalary;
	
	@FXML ImageView imgVwEmployee;
	
	private File imageFile;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	//--------------------
	public File getImageFile() {
		return imageFile;
	}
	
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	
	//---------------------
	@FXML
	protected void acceptTransaction() {
		if(fieldsAreFilledUp()) {
			addEmployee();
		} else {
			unhighlightObligatoryFields();
			highlightObligatoryFields();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar empleado",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)PersonnelAddForm.getStage().getScene().getRoot(), txtName);
		}
	}
	
	public void addEmployee() {
		PersonnelService service = new PersonnelService();
		Employee employee = new Employee();
		editEmployeeData(employee);
		service.registrateEmployee(employee);
		closeStageCompletely();
		updateDashboardViewsData();					//UPDATING INFO. IN THE DASHBOARD
	}
	
	public void editEmployeeData(Employee e) {
		e.setName(txtName.getText().trim());
		e.setJob(txtJob.getText().trim());
		e.setPhoneNumber(txtPhoneNumber.getText().trim());
		e.setEmail(txtEmail.getText().trim());
		e.setAddress(txtAddress.getText().trim());
		e.setSalary(Float.parseFloat(txtSalary.getText().trim()));
		e.setLate(0);
		e.setMiss(0);
		File file = null;
		if(getImageFile() != null) {
			file = getImageFile();
			String path = "/stylesheets/images/product_images/" + file.getName();
			copyFile(file.getAbsolutePath(), path);
			e.setImage(path);
		}
		else {
			e.setImage(DEFAULT_IMAGE);
		}
	}
	
	public void copyFile(String from, String to) {
		ImageManagement im = new ImageManagement();
		im.copyImage(from, to);
	}
	
	public void updateDashboardViewsData() {
		List<Employee> employees = null;
		//INVENTORY LIST *****
		if(PersonnelList.getPersonnelListController() != null) {
			employees = PersonnelList.getPersonnelListController().showEmployeeList();		//Refresh the product list in case it is open
			PersonnelList.getPersonnelListController().suggestEmployees(employees);
		}
	}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		if(formHasInformation()) {
			Dialogs d = new Dialogs();
			Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas datos no guardados. \n¿Estás seguro de cancelar el proceso? \n");
			if(exit) {
				exitView();
			}
		} else {
			exitView();
		}
		if(PersonnelList.getPersonnelListController() != null) {
			//PersonnelList.getPersonnelListController().showPersonnelList();		//Refresh the product list in case it is open
		}
	}
	
	public void exitView() {
		closeStageCompletely();
	}
	
	public Boolean formHasInformation() {
		Boolean result = false;
		if(!txtName.getText().trim().equals("") || !txtJob.getText().trim().equals("") 
			|| !txtPhoneNumber.getText().trim().equals("") || !txtEmail.getText().trim().equals("")
			|| !txtAddress.getText().trim().equals("")) {
			result = true;
		}
		return result;
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtName.getText().trim().equals("") && !txtJob.getText().trim().equals("") && !txtPhoneNumber.getText().trim().equals("") && !txtAddress.getText().trim().equals(""))
			return true;
		else
			return false;
	}
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
	private void unhighlightObligatoryFields() {
		if(!txtName.getStyleClass().isEmpty())
			txtName.getStyleClass().removeAll("important");
		if(!txtJob.getStyleClass().isEmpty())
			txtJob.getStyleClass().removeAll("important");
		if(!txtPhoneNumber.getStyleClass().isEmpty())
			txtPhoneNumber.getStyleClass().removeAll("important");
		if(!txtAddress.getStyleClass().isEmpty())
			txtAddress.getStyleClass().removeAll("important");
	}
	
	private void highlightObligatoryFields() {
		if(txtName.getText().trim().equals(""))
			txtName.getStyleClass().add("important");
		if(txtJob.getText().trim().equals(""))
			txtJob.getStyleClass().add("important");
		if(txtPhoneNumber.getText().trim().equals(""))
			txtPhoneNumber.getStyleClass().add("important");
		if(txtAddress.getText().trim().equals(""))
			txtAddress.getStyleClass().add("important");
	}
	
	//
	
	public void closeStageCompletely() {
		//Closing this stage completely
		if(PersonnelAddForm.getStage() != null) {
			PersonnelAddForm.getStage().close();
			PersonnelAddForm.setStage(null);
		}
	}
	
	//------------------------- Methods for dragging and dropping images ------------------------
	@FXML
	protected void handleDragOver(DragEvent event) {
		ImageManagement management = new ImageManagement();
		management.imageDragOver(event);
	}
	
	@FXML
	protected void handleDrop(DragEvent event) {
		File file = null;
		ImageManagement management = new ImageManagement();
		file = management.imageDrop(event);
		setImageFile(file);
		Image img = null;
		try {
			img = new Image(new FileInputStream(file));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		if(img != null)
			imgVwEmployee.setImage(img); //Sets the image we're dropping into the ImageView
	}
	
	@FXML
	protected void openFileChooser() {
		File file = null;
		ImageManagement management = new ImageManagement();
		file = management.imageBrowse();
		setImageFile(file);
		Image img = null;
		if (file != null) {
        	try {
        		img = new Image(new FileInputStream(file));
        	} catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}
            //openFile(file);			Only if we want to open the image with another application
        }
		if(img != null)
			imgVwEmployee.setImage(img); //Sets the image we've selected into the ImageView
	}
}
