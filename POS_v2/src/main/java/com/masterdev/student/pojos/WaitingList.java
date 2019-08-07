package com.masterdev.student.pojos;

import java.util.ArrayList;

public class WaitingList {
	
	public static final Integer MAX = 8;
	
	private static ArrayList<SaleWaiting> waitingList = new ArrayList<SaleWaiting>();
	
	public WaitingList() {}
	
	public static ArrayList<SaleWaiting> getWaitingList() {
		return waitingList;
	}
	
	public static void setWaitingList(ArrayList<SaleWaiting> newWaitingList) {
		waitingList = newWaitingList;
	}
	
	public static void addSaleToWaitingList(SaleWaiting saleWaiting) {
		waitingList.add(saleWaiting);
	}
	
	public static Integer findSaleInWaitingList(SaleWaiting saleWaiting) {
		Integer index = null;
		if(!waitingList.isEmpty()) {
			for(int i=0;i<waitingList.size();i++) {
				if(waitingList.get(i).getFolio() == saleWaiting.getFolio()) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public static void removeSaleFromWaitingList(int index) {
		waitingList.remove(index);
	}
	
}
