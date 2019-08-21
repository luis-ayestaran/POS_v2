package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.MathematicMethods;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.pojos.ProductOnSale;
import com.masterdev.student.pojos.SaleWaiting;
import com.masterdev.student.pojos.WaitingList;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.SalesForm;
import com.masterdev.student.views.SalesWaitingList;
import com.masterdev.student.services.CashRegisterService;
import com.masterdev.student.services.PrinterService;
import com.masterdev.student.services.SaleService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.converter.FloatStringConverter;

public class SalesFormController implements Initializable {
	//@FXML TextField txtDate;
	//@FXML Button btnDate;
	
	@FXML TextField txtCashRegister;
	@FXML TextField MoneyUnit;
	@FXML Button btnCash;
	
	//@FXML TextField txtEmployee;
	//@FXML Button btnEmployee;
	
	@FXML TextField txtClient;
	@FXML Button btnClient;
	
	@FXML TextField txtSearchProduct;	//********
	@FXML JFXButton btnSearchProduct;	//********

	@FXML TextField txtQuantity;		//********
	@FXML JFXButton btnQuantity;		//********
	
	@FXML TextField txtIdSale;
	@FXML TextField txtIdSalesperson;
	@FXML TextField txtTaxes;
	@FXML TextField txtDiscount;
	@FXML TextField txtSubtotal;
	@FXML TextField txtTotal;
	
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnWaitingList;
	@FXML JFXButton btnAccept;
	
	@FXML TableView<ProductOnSale> tabProducts;
	
	@FXML TableColumn<ProductOnSale, String> colBarcode;
	@FXML TableColumn<ProductOnSale, String> colProduct;
	@FXML TableColumn<ProductOnSale, Float> colUnitPrice;
	@FXML TableColumn<ProductOnSale, Float> colQuantity;
	@FXML TableColumn<ProductOnSale, String> colUnit;		//*********
	@FXML TableColumn<ProductOnSale, Float> colDiscount;
	@FXML TableColumn<ProductOnSale, Float> colAmount;
	
	private AutoCompletionBinding<String> productSuggestions;
	
	private SaleService saleService;
	
	private Sale sale;
	
	private Float subtotal;
	private Float total;
	private Float discount;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTooltipTexts();									//1. Initialising tooltip texts
		List<Product> products = getProductList();
		suggestProducts(products);									//2. Add word suggestions (if needed)
		initialiseTableView();										//3. Initialise table view
		initialiseListeners();										//4. Add table listeners
		initialiseSale();
		initialiseTextFields();
		txtSearchProductRequestsFocus();
	}
	
	public void initialiseTooltipTexts() {
		
	}
	
	public void initialiseTableView() {
		tabProducts.getItems().clear();
		initCols();
	}
	
	public void initCols() {
		colBarcode.setCellValueFactory(new PropertyValueFactory<ProductOnSale, String>("barcode"));
		colProduct.setCellValueFactory(new PropertyValueFactory<ProductOnSale, String>("description"));
		colUnitPrice.setCellValueFactory(new PropertyValueFactory<ProductOnSale, Float>("unitPrice"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<ProductOnSale, Float>("quantity"));
		colUnit.setCellValueFactory(new PropertyValueFactory<ProductOnSale, String>("unit"));
		colDiscount.setCellValueFactory(new PropertyValueFactory<ProductOnSale, Float>("discount"));
		colAmount.setCellValueFactory(new PropertyValueFactory<ProductOnSale, Float>("amount"));
		
		editableCols();
	}
	
	public void editableCols() {
		colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colQuantity.setOnEditCommit(e ->  {
			try {
				ProductOnSale pos = tabProducts.getSelectionModel().getSelectedItem();
				pos.setQuantity(e.getNewValue());
				pos.setAmount(pos.getAmount());
				Float changedQuantity = e.getNewValue() - pos.getSaleDetail().getQuantity();
				pos.getSaleDetail().setQuantity(changedQuantity);
				saleService.discardProduct(pos.getSaleDetail());
				pos.getSaleDetail().setQuantity(e.getNewValue());
				tabProducts.refresh();
				refreshSubtotalAndTotal();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		
		tabProducts.setEditable(true);
	}
	
	public void initialiseListeners() {
		tabProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductOnSale>() {
		    @Override
		    public void changed(ObservableValue<? extends ProductOnSale> observableValue, ProductOnSale oldValue, ProductOnSale newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabProducts.getSelectionModel().getSelectedItem() != null)
		        {
		           TableViewSelectionModel<ProductOnSale> selectionModel = tabProducts.getSelectionModel();
		           ProductOnSale selectedItem = selectionModel.getSelectedItem();
		           txtTaxes.setText(String.format("%.2f", selectedItem.getProduct().getTaxes()));
		        }
	         }
	     });
		
		/*tabProducts.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
		    public void handle(MouseEvent click) {
		        if (click.getClickCount() == 2) {
		           //acceptTransaction();
		        }
			}
	    });*/
		
	}
	
	public void initialiseSale() {
		saleService = new SaleService();
		sale = saleService.openSale();
		setSale(sale);
	}
	
	public void initialiseTextFields() {
		txtCashRegister.setText(Dashboard.getDashboardController().getCashRegister().getName());
		txtIdSalesperson.setText(String.valueOf(Dashboard.getDashboardController().getUser().getId()));
		txtIdSale.setText(String.valueOf(getSale().getFolio()));
		txtTaxes.setText("0.00");
		refreshSubtotalAndTotal();
	}
	
	public void txtSearchProductRequestsFocus() {
		txtSearchProduct.requestFocus();
	}
	
	//------------------------------- LOADING VIEWS ----------------------------------//
	public void loadSalesWaitingListView() {
		SalesWaitingList view = new SalesWaitingList();
		view.loadView();
	}
	
	//------------------------------- GETTING AND SETTING METHODS ----------------------------------//
	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	//------------------------------- METHODS FOR SEARCHING AND SELLING A PRODUCT ----------------------------------//
	@FXML
	protected void searchProduct() {
		search();
	}
	
	public void search() {
		if(!txtSearchProduct.getText().trim().equals("")) {
			Product productFound = findProduct(txtSearchProduct.getText().trim());
			if(productFound != null) {
				ProductOnSale pos = checkIfProductIsOnSale(productFound);
				addSaleDetail(productFound, pos);
				txtSearchProduct.requestFocus();
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Producto no encontrado",
						"No se encontraron coincidencias.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
				txtSearchProduct.clear();
			}
		}
		List<Product> data = getProductList();
		suggestProducts(data);
	}
	
	public Product findProduct(String product) {
		Product productReturn = null;
		Product productFound = null;
		Product p = new Product();
		WarehouseService service = new WarehouseService();
		//Checking by name
		p.setProduct(product);
		productFound = service.searchProduct(p);
		if(productFound != null) {
			return productFound;
		}
		//Checking by barcode
		p.setProduct(null);
		p.setBarCode(product);
		productFound = service.searchProduct(p);
		if(productFound != null) {
			return productFound;
		}
		//Checking by internal code
		p.setProduct(null);
		p.setBarCode(null);
		p.setInternalCode(product);
		productFound = service.searchProduct(p);
		if(productFound != null) {
			return productFound;
		}
		return productReturn;
	}
	
	public ProductOnSale checkIfProductIsOnSale(Product productFound) { //Checking if the product is on sale already
		ProductOnSale result = null;
		List<ProductOnSale> productsOnSale = tabProducts.getItems();
		if(productFound.getProduct().equals(""))
			productFound.setProduct(null);
		if(productFound.getBarCode().equals(""))
			productFound.setBarCode(null);
		if(productFound.getInternalCode().equals(""))
			productFound.setInternalCode(null);
		if(productsOnSale != null) {
			for(ProductOnSale pos : productsOnSale) {
				if(pos.getProduct().getProduct().equals(productFound.getProduct()) || pos.getProduct().getBarCode().equals(productFound.getBarCode()) || pos.getProduct().getInternalCode().equals(productFound.getInternalCode())) {
					result = pos;
					break;
				}
			}
		}
		if(productFound.getProduct() == null)
			productFound.setProduct("");
		if(productFound.getBarCode() == null)
			productFound.setBarCode("");
		if(productFound.getInternalCode() == null)
			productFound.setInternalCode("");
		return result;
	}
	
	public void addSaleDetail(Product productFound, ProductOnSale productOnSale) {
		try {
			if(productFound.getQuantity() > 0) {
				if(productFound.getQuantity() > productFound.getMinStock()) {
					
				} else {
					Dashboard.getDashboardController().showNotificationReminder();
				}
			} else {
				Dashboard.getDashboardController().showNotificationReminder();
			}
			Float quantity = null;
			if(txtQuantity.getText().trim().equals("")) {
				quantity = 1.0F;
			} else {
				quantity = Float.parseFloat(txtQuantity.getText().trim());
			}
			if(quantity > 0.0F) {
				if(productFound.getQuantity() - quantity < 0.0F) {
					Dashboard.getDashboardController().showNotificationReminder();
				}
				if(productOnSale != null) {
					productOnSale.setQuantity(productOnSale.getQuantity() + quantity);
					productOnSale.setAmount(Float.parseFloat(String.format("%.2f", productOnSale.getAmount())));
					productOnSale.getSaleDetail().setQuantity(quantity);
					productOnSale.getSaleDetail().setUnitPrice(productOnSale.getUnitPrice());    //CHANGED -----------------
					saleService.discardProduct(productOnSale.getSaleDetail());
					productOnSale.getSaleDetail().setQuantity(productOnSale.getQuantity());
					tabProducts.refresh();
				} else {
					SaleDetail sd = new SaleDetail(quantity, productFound.getRetailPrice(), productFound, getSale());
					saleService.addDetail(sd);
					ProductOnSale pos = new ProductOnSale(productFound.getProduct(), productFound.getBarCode(), productFound.getRetailPrice(), sd.getQuantity(), productFound.getPurchaseSubunit(), productFound.getDiscount(), sd.getSubTotal(), productFound, sd);
					addProductOnSale(pos);
				}
				refreshSubtotalAndTotal();
				cleanProductInformationFields();
				
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Cantidad no válida",
						"La cantidad debe ser mayor a 0.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
				cleanProductInformationFields();
			}
		} catch(NumberFormatException e) {
			Dialogs d = new Dialogs();
			d.acceptDialog("Cantidad no válida",
					"La cantidad debe ser expresada en numero.",
					(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
		}
	}
	
	public void addProductOnSale(ProductOnSale anotherProduct) {
		tabProducts.getItems().add(anotherProduct);
	}
	
	public void refreshSubtotalAndTotal() {
		List<ProductOnSale> productsOnSale = tabProducts.getItems();
		MathematicMethods mm = new MathematicMethods();
		//REFRESHING SUBTOTAL, DISCOUNT AND TOTAL AMOUNTS OF MONEY
		setSubtotal(mm.calculateSubtotal(productsOnSale));
		setDiscount(mm.calculateWholeDiscount(productsOnSale));
		setTotal(mm.calculateTotal(productsOnSale));
		//SHOWING THEM ON THE INTERFACE
		txtSubtotal.setText(String.format("%.2f", getSubtotal()));
		txtDiscount.setText(String.format("%.2f", getDiscount()));
		txtTotal.setText(String.format("%.2f", getTotal()));
	}
	
	@FXML
	protected void weigh() {
		if(!txtSearchProduct.getText().equals("")) {
			Product p = findProduct(txtSearchProduct.getText());
			if(p != null) {
				if(p.isInBulk()) {
					txtQuantity.setText(String.format("%.2f", Dashboard.getDashboardController().getScale().weigh()));
				} else {
					Dialogs d = new Dialogs();
					d.acceptDialog("No fue posible pesar el producto",
							"Este producto no está etiquetado para su venta a granel",
							(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
				}
			}
		}
	}
	
	public void cleanSearchField() {
		txtSearchProduct.setText("");
	}
	
	public void cleanProductInformationFields() {
		txtSearchProduct.setText("");
		txtQuantity.setText("");
	}
	
	// ------------------------------ METHODS FOR EDITING AND DELETING SINGLEPRODUCTS ------------------------------------//
	@FXML
	protected void deleteProduct() {
		if(tabProducts.getSelectionModel().getSelectedItem() != null) {
			Product product = tabProducts.getSelectionModel().getSelectedItem().getProduct();
			Float quantity = tabProducts.getSelectionModel().getSelectedItem().getQuantity();
			SaleDetail sd = new SaleDetail(quantity, product.getRetailPrice(), product, getSale());	// CHANGED
			saleService.removeDetail(sd);
			//Deleting the product from the interface
			List<ProductOnSale> productsOnSale = tabProducts.getItems();
			productsOnSale.remove(tabProducts.getSelectionModel().getSelectedIndex());
			tabProducts.refresh();
			refreshSubtotalAndTotal();
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Ningún producto seleccionado",
					"Selecciona, en la lista de productos, el producto que deseas borrar.",
					(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
		}
	}
	
	// ------------------------------ METHODS FOR FINISHING THE TRANSACTION ------------------------------------//
	@FXML 
	protected void acceptTransaction() {
		accept();
	}
	
	public void accept() {
		List<ProductOnSale> productsOnSale = tabProducts.getItems();
		if(productsOnSale.size() > 0) {
			Float cash = null;
			Dialogs d = new Dialogs();
			do {
				String strCash = d.inputDialog("Pago de la compra", "Cantidad de efectivo", "Cantidad en efectivo recibida: ");
				if(!strCash.trim().equals("") && strCash.trim() != null) {
					try {
						cash = Float.parseFloat(strCash);
					} catch(NumberFormatException e) {
						d.confirmationDialog("Reintentar", "Cantidad de efectivo no válida", "La cantidad de efectivo debe ser un número.\n");
						cash = null;
					}
				}
				if(cash != null) {
					if(cash < getTotal()) {
						d.confirmationDialog("Reintentar", "Cantidad de efectivo no válida", "La cantidad de efectivo debe ser igual o mayor al valor total de la compra.\n");
					}
				}
			} while(cash == null || cash < getTotal());
			Float change = cash - getTotal();
			d.confirmationDialog("Impresión de ticket", "Ticket de compra",
									"Imprimiendo ticket de compra."
									+"\n\n\tSubtotal:\t\t\t$" + String.format("%.2f", getSubtotal())
									+"\n\tDescuento:\t\t$" + String.format("%.2f", getDiscount())
									+"\n\tImporte:\t\t\t$" + String.format("%.2f", getTotal())
									+"\n\n\tPAGO:\t\t\t$" + String.format("%.2f", cash)
									+"\n\tCAMBIO:\t\t\t$" + String.format("%.2f", change));
			PrinterService service = new PrinterService();
			service.print(tabProducts.getItems(), getSubtotal(), getDiscount(), getTotal(), cash, change);
			closeNodeCompletely();
			CashRegisterService cashRegisterService = new CashRegisterService();
			Dashboard.getDashboardController().getCashRegister().setRemaining(Dashboard.getDashboardController().getCashRegister().getRemaining() + getTotal());
			cashRegisterService.updateCashRegister(Dashboard.getDashboardController().getCashRegister());
			saleService.saveSale(getSale());
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Venta sin artículos",
					"No se pudo cerrar la venta ya que no has agregado artículos.",
					(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
		}
	}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		if(tabProducts.getItems().size() > 0) {
			Dialogs d = new Dialogs();
			Boolean exit = d.confirmationDialog("Confirmación", "Cancelar venta", "¿Realmente deseas cancelar esta venta?\n");
			if(exit) {
				cancelSale(getSale());
				closeNodeCompletely();
				
			}
		}
	}
	
	public void cancelSale(Sale sale) {
		saleService.cancelSale(sale);
	}
	
	// -------------------------------------
	@FXML
	protected void putIntoWaitingList() {
		toWaitingList();
	}
	
	public void toWaitingList() {
		if(WaitingList.getWaitingList().size() < WaitingList.MAX) {
			DatePickerMethods dpm = new DatePickerMethods();
			SaleWaiting saleWaiting = new SaleWaiting(getSale().getFolio(), txtClient.getText(), dpm.dateToString(getSale().getDate()), dpm.timeToString(getSale().getDate()), getTotal(), getSale(), SalesForm.getNode(), SalesForm.getSalesFormController());
			WaitingList.addSaleToWaitingList(saleWaiting);
			closeNodeCompletely();
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Límite de ventas en espera",
					"El número máximo de ventas en espera que puedes tener es de "+WaitingList.MAX+".",
					(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
		}
	}
	
	@FXML
	protected void openWaitingList() {
		loadSalesWaitingListView();
	}
	
	public void closeNodeCompletely() {
		if(SalesForm.getNode() != null) {
			SalesForm.setNode(null);
			Dashboard.getDashboardController().loadSalesFormView();
		}
	}
	
	//------------------------------- METHODS FOR GETTING AND SETTING TOTAL / SUBTOTAL ----------------------------------//
	public Float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}
	
	public Float getTotal() {
		return total;
	}
	
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public Float getDiscount() {
		return discount;
	}
	
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	//------------------------------- METHODS FOR SUGGESTING PRODUCTS ----------------------------------//
	public List<Product> getProductList() {
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		return data;
	}
	
	protected void suggestProducts(List<Product> data) {
		if(data != null) {
			if(productSuggestions != null)
				productSuggestions.dispose(); 							//We get rid of the past list of word suggestions
			TextFieldMethods tfm = new TextFieldMethods();
			ArrayList<String> products = new ArrayList<String>();
			for(Product p : data) {
				products.add(p.getProduct());
			}
			productSuggestions = tfm.addWordSuggestions(txtSearchProduct, products);
			productSuggestions.setHideOnEscape(true);
		}
	}
	
	//---------------------------------- VARIOUS METHODS --------------------------//
	public Boolean currentSaleIsEmpty() {
		if(tabProducts.getItems().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean waitingListIsEmpty() {
		if(WaitingList.getWaitingList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}