package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.masterdev.student.entities.Sale;
import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.pojos.SalesHistoryEntry;
import com.masterdev.student.services.SaleService;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;

public class BalanceOverviewController implements Initializable {
	
	@FXML private AreaChart<String, Number> chrtDate;
	@FXML private AreaChart<String, Number> chrtToday;
	@FXML private AreaChart<String, Number> chrtWeek;
	@FXML private AreaChart<String, Number> chrtMonth;
	@FXML private AreaChart<String, Number> chrtYear;
	
	private DatePickerMethods dpm;
	
	public void initialize(URL location, ResourceBundle resources) {
		dpm = new DatePickerMethods();
		List<Sale> history = getSaleHistory();
		initialiseCharts(history);
	}
	
	public void initialiseCharts(List<Sale> history) {
		List<Sale> todayHistory = new ArrayList<Sale>();
		List<Sale> weekHistory = new ArrayList<Sale>();
		List<Sale> monthHistory = new ArrayList<Sale>();
		List<Sale> yearHistory = new ArrayList<Sale>();
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
		setSearchChartContent(todayHistory);
		setTodayChartContent(todayHistory);
		setWeekChartContent(weekHistory);
		setMonthChartContent(monthHistory);
		setYearChartContent(yearHistory);
	}
	
	public void setSearchChartContent(List<Sale> history) {
		List<XYChart.Series> series = todayContent(history);
		for(XYChart.Series graphic : series) {
			chrtDate.getData().add(graphic);
		}
	}
	
	public void setTodayChartContent(List<Sale> history) {
		List<XYChart.Series> series = todayContent(history);
		for(XYChart.Series graphic : series) {
			chrtToday.getData().add(graphic);
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
	
	public void setWeekChartContent(List<Sale> history) {
		List<XYChart.Series> series = weekContent(history);
		for(XYChart.Series graphic : series) {
			chrtWeek.getData().add(graphic);
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
	
	public void setMonthChartContent(List<Sale> history) {
		List<XYChart.Series> series = monthContent(history);
		for(XYChart.Series graphic : series) {
			chrtMonth.getData().add(graphic);
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
	
	public void setYearChartContent(List<Sale> history) {
		List<XYChart.Series> series = yearContent(history);
		for(XYChart.Series graphic : series) {
			chrtYear.getData().add(graphic);
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
	
	public List<Sale> getSaleHistory() {
		SaleService service = new SaleService();
		List<Sale> data = service.showSales();
		return data;
	}
	
}
