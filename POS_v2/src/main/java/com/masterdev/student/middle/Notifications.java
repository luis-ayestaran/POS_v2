package com.masterdev.student.middle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.InventoryList;

public class Notifications {
	
	public static final String ERROR_ICON = "EXCLAMATION_CIRCLE";
	public static final String WARNING_ICON = "EXCLAMATION_TRIANGLE";
	
	private static PopOver notificationPopOver;
	private static VBox notificationVBox;
	private static ScrollPane notificationScrollPane;
	
	private static ArrayList<HBox> notifications = new ArrayList<HBox>();
	
	public static PopOver getNotificationPopOver() {
		return notificationPopOver;
	}
	
	public static void setNotificationPopOver(PopOver newPopOver) {
		notificationPopOver = newPopOver;
	}
	
	public static VBox getNotificationVBox() {
		return notificationVBox;
	}
	
	public static void setNotificationVBox(VBox newVBox) {
		notificationVBox = newVBox;
	}
	
	public static ScrollPane getNotificationScrollPane() {
		return notificationScrollPane;
	}
	
	public static void setNotificationScrollPane(ScrollPane newScrollPane) {
		notificationScrollPane = newScrollPane;
	}
	
	public static ArrayList<HBox> getNotifications() {
		return notifications;
	}
	
	public static void setNotifications(ArrayList<HBox> newNotifications) {
		notifications = newNotifications;
	}
	
	public static void init(List<Product> data) {
		VBox notificationVBox = new VBox();
		notificationVBox.setId("pop-over-vbox");
		setNotificationVBox(notificationVBox);
		ScrollPane notificationContent = new ScrollPane(notificationVBox);
		setNotificationScrollPane(notificationContent);
		getNotificationScrollPane().setPrefWidth(375);
		getNotificationScrollPane().setPrefHeight(450);
		getNotificationScrollPane().setHbarPolicy(ScrollBarPolicy.NEVER);
		getNotificationScrollPane().setId("pop-over-scroll-pane");
		getNotificationVBox().prefWidthProperty().bind(getNotificationScrollPane().widthProperty());
		
		getNotificationPopOver().setTitle("Notificaciones");
		getNotificationPopOver().setHeaderAlwaysVisible(true);
		getNotificationPopOver().setCloseButtonEnabled(false);
		getNotificationPopOver().setContentNode(notificationContent);
		getNotificationPopOver().setArrowLocation(ArrowLocation.TOP_CENTER);
		getNotificationPopOver().setDetachable(false);
		getNotificationPopOver().setId("pop-over");
		
		refreshNotifications(data);
	}
	
	public static void refreshNotifications(List<Product> data) {
		getNotificationVBox().getChildren().clear();
		DatePickerMethods dpm = new DatePickerMethods();
		Date date = dpm.getCurrentDateTime();
		if(data != null) {
			for(int i=0;i<data.size();i++) {
				if(data.get(i).getQuantity() <= 0) {
					addNotification(Notifications.ERROR_ICON, "Cantidad nula", "No quedan más unidades de "+data.get(i).getProduct(), data.get(i));
				} else {
					if(data.get(i).getQuantity() <= data.get(i).getMinStock()) {
						addNotification(Notifications.WARNING_ICON, "Mínimo stock", "Has alcanzado tu mínimo stock de "+data.get(i).getProduct(), data.get(i));
					}
				}
				if(dpm.compareDates(date, data.get(i).getProductBatches().get(data.get(i).getProductBatches().size() - 1).getDischargeDate()) == -1) {
					if(dpm.datesAreClose(date, data.get(i).getProductBatches().get(data.get(i).getProductBatches().size() - 1).getDischargeDate())) {
						addNotification(Notifications.WARNING_ICON, "Próximo a caducar", "El lote más reciente de "+data.get(i).getProduct()+" caducará próximamente.", data.get(i));
					}
				}
				if(dpm.compareDates(date, data.get(i).getProductBatches().get(data.get(i).getProductBatches().size() - 1).getDischargeDate()) == 0 || dpm.compareDates(date, data.get(i).getProductBatches().get(data.get(i).getProductBatches().size() - 1).getDischargeDate()) == 1) {
					addNotification(Notifications.ERROR_ICON, "Producto caducado", "El lote más reciente de "+data.get(i).getProduct()+" ha caducado.", data.get(i));
				}
			}
		}
	}
	
	public static void addNotification(String icon, String header, String content, Product product) {
		FontAwesomeIconView notificationIcon = new FontAwesomeIconView();
		notificationIcon.setGlyphName(icon);
		notificationIcon.setId("notification-glyph");
		Label notificationHeader = new Label(header);
		notificationHeader.setId("notification-header");
		Label notificationContent = new Label(content);
		notificationContent.setId("notification-content");
		VBox notificationText = new VBox(notificationHeader, notificationContent);
		notificationText.setId("notification-text");
		HBox notification = new HBox(notificationIcon, notificationText);
		notification.setId("notification");
		notification.setOnMouseEntered(e -> {
			notification.setStyle("-fx-background-color: #eee;");
			notificationIcon.setGlyphName(icon);
			notificationIcon.setId("notification-glyph");
		});
		notification.setOnMouseExited(e -> {
			notification.setStyle("-fx-background-color: transparent;");
			notificationIcon.setGlyphName(icon);
			notificationIcon.setId("notification-glyph");
		});
		notification.setOnMousePressed(e -> {
			notification.setStyle("-fx-background-color: #ddd;");
			notificationIcon.setGlyphName(icon);
			notificationIcon.setId("notification-glyph");
		});
		notification.setOnMouseReleased(e -> {
			notification.setStyle("-fx-background-color: #eee;");
			notificationIcon.setGlyphName(icon);
			notificationIcon.setId("notification-glyph");
		});
		notification.setOnMouseClicked(e -> {
			notificationPopOver.hide();
			Dashboard.getDashboardController().loadInventoryListView();
			InventoryList.getInventoryListController().showResult(product);
			InventoryList.getInventoryListController().selectFoundProduct(product);
			notificationIcon.setGlyphName(icon);
			notificationIcon.setId("notification-glyph");
		});
		
		getNotificationVBox().getChildren().add(notification);
	}
}
