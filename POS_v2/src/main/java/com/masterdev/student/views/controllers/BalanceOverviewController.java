package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.SortingMethods;
import com.masterdev.student.pojos.SalesHistoryEntry;
import com.masterdev.student.services.SaleService;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.InventoryList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class BalanceOverviewController implements Initializable {
	
	@FXML private AreaChart<String, Number> chrtDate;
	@FXML private AreaChart<String, Number> chrtToday;
	@FXML private AreaChart<String, Number> chrtWeek;
	@FXML private AreaChart<String, Number> chrtMonth;
	@FXML private AreaChart<String, Number> chrtYear;
	
	@FXML private PieChart chrtCategories;
	
	@FXML private JFXListView<VBox> lstVwProducts; 
	
	private DatePickerMethods dpm;
	
	public BalanceOverviewController() {
		dpm = new DatePickerMethods();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		dpm = new DatePickerMethods();
		List<Sale> history = getSaleHistory();
		initialiseCharts(chrtDate, chrtToday, chrtWeek, chrtMonth, chrtYear, history);
		List<Product> products = showProductList();
		List<Product> mostSoldProducts = getMostSoldProducts(products);
		setProducts(mostSoldProducts);
		List<ProductType> categories = showCategories();
		List<ProductType> mostSoldCategories = getMostSoldCategories(categories);
		setCategories(mostSoldCategories);
	}
	
	// ------------------------------ SETTING CHART CONTENT -----------------------------// 
	
	public void initialiseCharts(AreaChart chartDate, AreaChart chartToday, AreaChart chartWeek, AreaChart chartMonth, AreaChart chartYear, List<Sale> history) {
		List<Sale> todayHistory = new ArrayList<Sale>();
		List<Sale> weekHistory = new ArrayList<Sale>();
		List<Sale> monthHistory = new ArrayList<Sale>();
		List<Sale> yearHistory = new ArrayList<Sale>();
		if(history != null) {
			for(Sale sale : history) {
				if(dpm.compareDates(dpm.getCurrentDate(), dpm.stringToDate(dpm.dateToString(sale.getDate()))) == 0) {
					todayHistory.add(sale);
				}
			}
			for(Sale sale : history) {
				if(dpm.compareDates(dpm.getNDaysAgo(7), sale.getDate()) == 0 || dpm.compareDates(dpm.getNDaysAgo(7), sale.getDate()) == -1) {
					weekHistory.add(sale);
				}
			}
			for(Sale sale : history) {
				if(dpm.areSameMonth(dpm.getCurrentDate(), sale.getDate())) {
					monthHistory.add(sale);
				}
			}
			for(Sale sale : history) {
				if(dpm.areSameYear(dpm.getCurrentDate(), sale.getDate())) {
					yearHistory.add(sale);
				}
			}
		}
		setSearchChartContent(chartDate, todayHistory);
		setTodayChartContent(chartToday, todayHistory);
		setWeekChartContent(chartWeek, weekHistory);
		setMonthChartContent(chartMonth, monthHistory);
		setYearChartContent(chartYear, yearHistory);
	}
	
	public void setSearchChartContent(AreaChart chart, List<Sale> history) {
		if(chart != null) {
			List<XYChart.Series> series = todayContent(history);
			for(XYChart.Series graphic : series) {
				chart.getData().add(graphic);
			}
		}
	}
	
	public void setTodayChartContent(AreaChart chart, List<Sale> history) {
		List<XYChart.Series> series = todayContent(history);
		for(XYChart.Series graphic : series) {
			chart.getData().add(graphic);
		}
	}
	
	public List<XYChart.Series> todayContent(List<Sale> history) {
		Date hour;
		Date nextHour;
		List<XYChart.Series> series = new ArrayList<XYChart.Series>(); 
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Ingresos");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Egresos");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Capital");
		//if(history != null && !history.isEmpty()) {
			for(int i=0;i<23;i++) {
				Float revenuePerHour = 0f;
				Float expensePerHour = 0f;
				Float utilityPerHour = 0f;
				if(i >= 10) {
					hour = dpm.stringToTime(String.valueOf(i) + ":00:00");
					if(i == 23) {
						nextHour = dpm.stringToTime("00:00:00");
					} else {
						nextHour = dpm.stringToTime(String.valueOf(i + 1) + ":00:00");
					}
				} else {
					hour = dpm.stringToTime("0" + String.valueOf(i) + ":00:00");
					if(i == 9) {
						nextHour = dpm.stringToTime(String.valueOf(i + 1) + ":00:00");
					} else {
						nextHour = dpm.stringToTime("0" + String.valueOf(i + 1) + ":00:00");
					}
				}
				for(Sale sale : history) {
					if(sale.getTotal() != null) {
						Date saleHour = dpm.stringToTime(dpm.timeToString(sale.getDate()));
						if(dpm.compareDates(hour, saleHour) == 0 || dpm.compareDates(hour, saleHour) == -1) {
							if(dpm.compareDates(nextHour, saleHour) == 1) {
								revenuePerHour += sale.getTotal();
								List<SaleDetail> sdList = sale.getDetail();
								for(SaleDetail sd : sdList) {
									expensePerHour += sd.getQuantity() * sd.getProduct().getRetailCost();
								}
								utilityPerHour = revenuePerHour - expensePerHour;
							}
						}
					}
				}
				XYChart.Data<String, Float> revenueNode = addChartNode(String.valueOf(i), revenuePerHour, "floating-label-revenue");
				XYChart.Data<String, Float> expenseNode = addChartNode(String.valueOf(i), expensePerHour, "floating-label-expense");
				XYChart.Data<String, Float> utilityNode = addChartNode(String.valueOf(i), utilityPerHour, "floating-label-utility");
				series1.getData().add(revenueNode);
				series2.getData().add(expenseNode);
				series3.getData().add(utilityNode);
			}
			hour = dpm.stringToTime("23:00:00");
			Float revenuePerHour = 0f;
			Float expensePerHour = 0f;
			Float utilityPerHour = 0f;
			for(Sale sale : history) {
				if(sale.getTotal() != null) {
					Date saleHour = dpm.stringToTime(dpm.timeToString(sale.getDate()));
					if(dpm.compareDates(hour, saleHour) == 0 || dpm.compareDates(hour, saleHour) == -1) {
						revenuePerHour += sale.getTotal();
						List<SaleDetail> sdList = sale.getDetail();
						for(SaleDetail sd : sdList) {
							expensePerHour += sd.getQuantity() * sd.getProduct().getRetailCost();
						}
						utilityPerHour = revenuePerHour - expensePerHour;
					}
				}
			}
			XYChart.Data<String, Float> revenueNode = addChartNode("23", revenuePerHour, "floating-label-revenue");
			XYChart.Data<String, Float> expenseNode = addChartNode("23", expensePerHour, "floating-label-expense");
			XYChart.Data<String, Float> utilityNode = addChartNode("23", utilityPerHour, "floating-label-utility");
			series1.getData().add(revenueNode);
			series2.getData().add(expenseNode);
			series3.getData().add(utilityNode);
			revenueNode = addChartNode("24", 0f, "floating-label-revenue");
			expenseNode = addChartNode("24", 0f, "floating-label-expense");
			utilityNode = addChartNode("24", 0f, "floating-label-utility");
			series1.getData().add(revenueNode);
			series2.getData().add(expenseNode);
			series3.getData().add(utilityNode);
		//}
		series.add(series1);
		series.add(series2);
		series.add(series3);
		return series;
	}
	
	public XYChart.Data<String, Float> addChartNode(String category, Float quantity, String labelStyle) {
		XYChart.Data<String, Float> data = new XYChart.Data<String, Float>(category, quantity);
		StackPane node = new StackPane();
		Label label = new Label("$" + String.format("%.2f", quantity));
		label.getStyleClass().addAll(labelStyle);
		label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		label.setTranslateY(-5);
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent mouseEvent) {
				node.getChildren().setAll(label);
				label.toFront();
			}
		});
		node.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				node.getChildren().clear();
			}
		});
		data.setNode(node);
		return data;
	}
	
	public void setWeekChartContent(AreaChart chart, List<Sale> history) {
		List<XYChart.Series> series = weekContent(history);
		for(XYChart.Series graphic : series) {
			chart.getData().add(graphic);
		}
	}
	
	public List<XYChart.Series> weekContent(List<Sale> history) {
		Date dayOfTheWeek;
		Date nextHour;
		List<XYChart.Series> series = new ArrayList<XYChart.Series>(); 
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Ingresos");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Egresos");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Capital");
		//if(history != null && !history.isEmpty()) {
			for(int i=0;i<7;i++) {
				Float revenuePerHour = 0f;
				Float expensePerHour = 0f;
				Float utilityPerHour = 0f;
				dayOfTheWeek = dpm.getNDaysAgo(6 - i);
				for(Sale sale : history) {
					if(sale.getTotal() != null) {
						Date saleDay = dpm.stringToDate(dpm.dateToString(sale.getDate()));
						if(dpm.compareDates(dayOfTheWeek, saleDay) == 0) {
							revenuePerHour += sale.getTotal();
							List<SaleDetail> sdList = sale.getDetail();
							for(SaleDetail sd : sdList) {
								expensePerHour += sd.getQuantity() * sd.getProduct().getRetailCost();
							}
							utilityPerHour = revenuePerHour - expensePerHour;
						}
					}
				}
				String day;
				String month;
				if(dpm.getDayOfMonth(dayOfTheWeek) < 10) {
					day = "0" + String.valueOf(dpm.getDayOfMonth(dayOfTheWeek));
				} else {
					day = String.valueOf(dpm.getDayOfMonth(dayOfTheWeek));
				}
				if(dpm.getMonth(dayOfTheWeek) + 1 < 10) {
					month = "0" + String.valueOf(dpm.getMonth(dayOfTheWeek) + 1);
				} else {
					month = String.valueOf(dpm.getMonth(dayOfTheWeek) + 1);
				}
				String date = day + " / " + month;
				XYChart.Data<String, Float> revenueNode = addChartNode(date, revenuePerHour, "floating-label-revenue");
				XYChart.Data<String, Float> expenseNode = addChartNode(date, expensePerHour, "floating-label-expense");
				XYChart.Data<String, Float> utilityNode = addChartNode(date, utilityPerHour, "floating-label-utility");
				series1.getData().add(revenueNode);
				series2.getData().add(expenseNode);
				series3.getData().add(utilityNode);
			}
		//}
		series.add(series1);
		series.add(series2);
		series.add(series3);
		return series;
	}
	
	public void setMonthChartContent(AreaChart chart, List<Sale> history) {
		List<XYChart.Series> series = monthContent(history);
		for(XYChart.Series graphic : series) {
			chart.getData().add(graphic);
		}
	}
	
	public List<XYChart.Series> monthContent(List<Sale> history) {
		Date dayOfMonth;
		List<XYChart.Series> series = new ArrayList<XYChart.Series>(); 
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Ingresos");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Egresos");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Capital");
		//if(history != null && !history.isEmpty()) {
			Date today = dpm.getCurrentDate();
			Integer year = dpm.getYear(today);
			Integer month = dpm.getMonth(today);
			for(int i=0;i<dpm.getDaysNumber(year, month+1);i++) {
				Float revenuePerHour = 0f;
				Float expensePerHour = 0f;
				Float utilityPerHour = 0f;
				for(Sale sale : history) {
					if(sale.getTotal() != null) {
						Date saleDay = dpm.stringToDate(dpm.dateToString(sale.getDate()));
						if(dpm.getDayOfMonth(saleDay) == i + 1d && dpm.areSameMonth(today, saleDay) && dpm.areSameYear(today, saleDay)) {
							revenuePerHour += sale.getTotal();
							List<SaleDetail> sdList = sale.getDetail();
							for(SaleDetail sd : sdList) {
								expensePerHour += sd.getQuantity() * sd.getProduct().getRetailCost();
							}
							utilityPerHour = revenuePerHour - expensePerHour;
						}
					}
				}				
				String date = String.valueOf(i + 1);
				XYChart.Data<String, Float> revenueNode = addChartNode(date, revenuePerHour, "floating-label-revenue");
				XYChart.Data<String, Float> expenseNode = addChartNode(date, expensePerHour, "floating-label-expense");
				XYChart.Data<String, Float> utilityNode = addChartNode(date, utilityPerHour, "floating-label-utility");
				series1.getData().add(revenueNode);
				series2.getData().add(expenseNode);
				series3.getData().add(utilityNode);
			}
		//}
		series.add(series1);
		series.add(series2);
		series.add(series3);
		return series;
	}
	
	public void setYearChartContent(AreaChart chart, List<Sale> history) {
		List<XYChart.Series> series = yearContent(history);
		for(XYChart.Series graphic : series) {
			chart.getData().add(graphic);
		}
	}
	
	public List<XYChart.Series> yearContent(List<Sale> history) {
		Date month;
		List<XYChart.Series> series = new ArrayList<XYChart.Series>(); 
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Ingresos");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Egresos");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Capital");
		//if(history != null && !history.isEmpty()) {
			Date today = dpm.getCurrentDate();
			Integer year = dpm.getYear(dpm.getCurrentDate());
			Integer day = dpm.getMonth(dpm.getCurrentDate());
			for(int i=0;i<12;i++) {
				Float revenuePerHour = 0f;
				Float expensePerHour = 0f;
				Float utilityPerHour = 0f;
				for(Sale sale : history) {
					if(sale.getTotal() != null) {
						Date saleDay = dpm.stringToDate(dpm.dateToString(sale.getDate()));
						if(dpm.getMonth(saleDay) == i && dpm.areSameYear(today, saleDay)) {
							revenuePerHour += sale.getTotal();
							List<SaleDetail> sdList = sale.getDetail();
							for(SaleDetail sd : sdList) {
								expensePerHour += sd.getQuantity() * sd.getProduct().getRetailCost();
							}
							utilityPerHour = revenuePerHour - expensePerHour;
						}
					}
				}				
				XYChart.Data<String, Float> revenueNode = addChartNode(getMonthName(i), revenuePerHour, "floating-label-revenue");
				XYChart.Data<String, Float> expenseNode = addChartNode(getMonthName(i), expensePerHour, "floating-label-expense");
				XYChart.Data<String, Float> utilityNode = addChartNode(getMonthName(i), utilityPerHour, "floating-label-utility");
				series1.getData().add(revenueNode);
				series2.getData().add(expenseNode);
				series3.getData().add(utilityNode);
			}
		//}
		series.add(series1);
		series.add(series2);
		series.add(series3);
		return series;
	}
	
	private String getMonthName(Integer month) {
		String name = "";
		switch(month) {
			case Calendar.JANUARY: name = "Enero";
					break;
			case Calendar.FEBRUARY: name = "Febrero";
					break;
			case Calendar.MARCH: name = "Marzo";
					break;
			case Calendar.APRIL: name = "Abril";
					break;
			case Calendar.MAY: name = "Mayo";
					break;
			case Calendar.JUNE: name = "Junio";
					break;
			case Calendar.JULY: name = "Julio";
					break;
			case Calendar.AUGUST: name = "Agosto";
					break;
			case Calendar.SEPTEMBER: name = "Septiembre";
					break;
			case Calendar.OCTOBER: name = "Octubre";
					break;
			case Calendar.NOVEMBER: name = "Noviembre";
					break;
			case Calendar.DECEMBER: name = "Diciembre";
					break;
		}
		return name;
	}
	
	// ------------------------------ SETTING PRODUCT LIST DATA -----------------------------//
	private List<Product> getMostSoldProducts(List<Product> products) {
		if(products != null) {
			float[] unitsSold = new float[products.size()];
			int[] indexes = new int[unitsSold.length];
			List<Product> mostSoldProducts = new ArrayList<Product>();
			if(products != null) {
				for(int i=0;i<products.size();i++) {
					unitsSold[i] = products.get(i).getUnitsSold();
					indexes[i] = i;
				}
				//float[] sortedProducts = SortingMethods.quickSort(unitsSold, indexes, 0, unitsSold.length - 1);
				int[] sortedIndexes = SortingMethods.selectionSort(unitsSold, indexes);
				
				Integer i = 0, n = 0;
				while(i<products.size() && n<=10) {
					if(products.get(sortedIndexes[i]).getUnitsSold() > 0) {
						mostSoldProducts.add(products.get(sortedIndexes[i]));
						n++;
					} 
					i++;
				}
			}
			return mostSoldProducts;
		} else {
			return null;
		}
	}
	
	private void setProducts(List<Product> mostSoldProducts) {
		if(mostSoldProducts != null && !mostSoldProducts.isEmpty()) {
			for(Product product : mostSoldProducts) {
				Label title = new Label(product.getProduct().toUpperCase());
				title.getStyleClass().add("title");
				Label description = new Label("Unidades vendidas: " + String.valueOf(product.getUnitsSold()) + " " +  product.getPurchaseSubunit());
				description.getStyleClass().add("description");
				VBox vbox = new VBox(title, description);
				vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
				    public void handle(MouseEvent click) {
				        if (click.getClickCount() == 2) {
				        	Dashboard.getDashboardController().loadInventoryListView();
							InventoryList.getInventoryListController().showResult(product);
							InventoryList.getInventoryListController().selectFoundProduct(product);
				        }
					}
			    });
				lstVwProducts.getItems().add(vbox);
			}
		} else {
			Label title = new Label("No haz realizado ventas aún.");
			title.getStyleClass().add("title");
		}
	}
	
	// ------------------------------ GETTING CATEGORIES LIST DATA -----------------------------//
	public List<ProductType> getMostSoldCategories(List<ProductType> categories) {
		if(categories != null) {
			float[] unitsSold = new float[categories.size()];
			int[] indexes = new int[unitsSold.length];
			List<ProductType> mostSoldCategories = new ArrayList<ProductType>();
			if(categories != null) {
				for(int i=0;i<categories.size();i++) {
					unitsSold[i] = categories.get(i).getProductsSold();
					indexes[i] = i;
				}
				
				int[] sortedIndexes = SortingMethods.selectionSort(unitsSold, indexes);
				Integer i = 0, n = 0;
				while(i<categories.size() && n<=6) {
					if(categories.get(sortedIndexes[i]).getProductsSold() > 0) {
						mostSoldCategories.add(categories.get(sortedIndexes[i]));
						n++;
					} 
					i++;
				}
			}
			return mostSoldCategories;
		} else { 
			return null;
		}
	}
	
	public void setCategories(List<ProductType> mostSoldCategories) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		if(mostSoldCategories != null) {
			for(ProductType category : mostSoldCategories) {
				pieChartData.add(new PieChart.Data(category.getType(), category.getProductsSold()));
			}
		}
		chrtCategories.setData(pieChartData);
		
	}
	
	// ------------------------------ GETTING DATA FROM THE DATABASE -----------------------------//
	
	public List<Sale> getSaleHistory() {
		SaleService service = new SaleService();
		List<Sale> data = service.showSales();
		return data;
	}
	
	public List<Product> showProductList() {
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		return data;
	}
	
	public List<ProductType> showCategories() {
		WarehouseService service = new WarehouseService();
		List<ProductType> data = service.showProductTypes();
		return data;
	}
	
}
