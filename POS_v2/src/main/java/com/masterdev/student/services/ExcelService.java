package com.masterdev.student.services;

import java.io.File;
import java.util.List;

import com.masterdev.student.pojos.SalesHistoryEntry;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelService {
	
	public final static Integer HISTORY_SHEET = 0;
	public final static Integer BALANCE_SHEET = 1;
	
    private WritableWorkbook wWorkbook;
    private WritableSheet wSheet;
	
    public ExcelService() {}
    
	// ---------------------- GETTING AND SETTING METHODS -------------------------//
    private WritableWorkbook getWWorkbook() {
    	return wWorkbook;
    }
    
    private void setWWorkbook(WritableWorkbook wWorkbook) {
    	this.wWorkbook = wWorkbook;
    }
    
    private WritableSheet getWSheet() {
    	return wSheet;
    }
    
    private void setWSheet(WritableSheet wSheet) {
    	this.wSheet = wSheet;
    }
    
    private void createWWorkbook(String path, String fileName) {
    	try {
    		File file = new File(path + System.getProperty("file.separator") + fileName);
    		wWorkbook = Workbook.createWorkbook(file);
    		System.out.println(file.getAbsolutePath());
    		setWWorkbook(wWorkbook);
    	} catch(Exception e) {}
    }
    
    private void createWSheet(String name, Integer index) {
    	wSheet = getWWorkbook().createSheet(name, index);
    	setWSheet(wSheet);
    }
	
    private void writeHistoryTitle(String title) {
    	try {
	    	WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 16);
	        cellFont.setBoldStyle(WritableFont.BOLD);
	        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	        Label lblTitle = new Label(1, 1, title, cellFormat);
	    	getWSheet().addCell(lblTitle);
    	} catch(WriteException e) { e.printStackTrace(); }
    }
    
    private void writeHistoryInfo(String details) {
    	setHistoryCellData(1, 3, details);
    }
    
    private void writeHistoryDetailsTitle(Integer whiteSpaces, String detailsTitle) {
    	try {
	    	WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 14);
	        cellFont.setBoldStyle(WritableFont.BOLD);
	        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	        Label lblDetailsTitle = new Label(whiteSpaces + 2, 3, detailsTitle, cellFormat);
	    	getWSheet().addCell(lblDetailsTitle);
    	} catch(WriteException e) { e.printStackTrace(); }
    }
    
    private void writeHistoryHeaders(List<String> headers) {
    	try {
	    	Integer i = 5, j = 1, widthInChars = 22;
	    	WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 13);
	        cellFont.setBoldStyle(WritableFont.BOLD);
	        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    	for(int k=0;k<headers.size();k++) {
	    	    getWSheet().setColumnView(j, widthInChars);
	    		Label label = new Label(j, i, headers.get(k), cellFormat);
		    	getWSheet().addCell(label);
		    	j++;
	    	}
    	} catch(WriteException e) { e.printStackTrace(); }
    }
    
    private void writeHistoryDetailsHeaders(Integer whitespaces, List<String> headers) {
    	try {
	    	Integer i = 5, j = whitespaces + 2, widthInChars = 22;
	    	WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 13);
	        cellFont.setBoldStyle(WritableFont.BOLD);
	        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    	for(int k=0;k<headers.size();k++) {
	    	    getWSheet().setColumnView(j, widthInChars);
	    		Label label = new Label(j, i, headers.get(k), cellFormat);
		    	getWSheet().addCell(label);
		    	j++;
	    	}
    	} catch(WriteException e) { e.printStackTrace(); }
    }
    
    private void writeHistory(Integer columns, List<SalesHistoryEntry> history) {
    	Integer i = 6;
    	for(int j=0;j<history.size();j++) {
    		setHistoryCellData(1, i+j, String.valueOf(history.get(j).getFolio()));
    		setHistoryCellData(2, i+j, history.get(j).getStrConventionalDate());
    		setHistoryCellData(3, i+j, history.get(j).getStrTime());
    		setHistoryCellData(4, i+j, String.valueOf(history.get(j).getSale().getDetail().size()));
    		setHistoryCellData(5, i+j, String.valueOf(history.get(j).getTotal()));
    	}
    }
    
    private void writeHistoryDetails(Integer whiteSpaces, Integer columns, List<SalesHistoryEntry> history) {
    	Integer k = 6;
    	for(int i=0;i<history.size();i++) {
	    	for(int j=0;j<history.get(i).getSale().getDetail().size();j++) {
	    		setHistoryCellData((whiteSpaces + 2), k+j, String.valueOf(history.get(i).getFolio()));
	    		setHistoryCellData((whiteSpaces + 2) + 1, k+j, history.get(i).getSale().getDetail().get(j).getProduct().getProduct());
	    		setHistoryCellData((whiteSpaces + 2) + 2, k+j, String.valueOf(history.get(i).getSale().getDetail().get(j).getProduct().getRetailPrice()));
	    		setHistoryCellData((whiteSpaces + 2) + 3, k+j, String.valueOf(history.get(i).getSale().getDetail().get(j).getQuantity()));
	    		setHistoryCellData((whiteSpaces + 2) + 4, k+j, String.valueOf(history.get(i).getSale().getDetail().get(j).getSubTotal()));
	    	}
	    	k += history.get(i).getSale().getDetail().size();
    	}
    }
    
    private void setHistoryCellData(Integer iColumnNumber, Integer iRowNumber, String strData) {
    	WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 12);
    	WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    Label labTemp = new Label(iColumnNumber, iRowNumber, strData, cellFormat);
	    try {
	        getWSheet().addCell(labTemp);
	    } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void closeFile() {
        try {
            wWorkbook.write();
            wWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public Boolean writeHistory(Integer timeLapse, String strDate, List<String> headers, List<String> detailsHeaders, List<SalesHistoryEntry> history) {
		Boolean saved = true;
		try {
			File desktopDir = new File(System.getProperty("user.home"), "Desktop");
			String path = desktopDir.getPath();
			String fileName = "historial" + getHistoryDocumentName(timeLapse) + strDate + ".xls";
			//String path = "C:\\Users\\Public\\Documents\\historial" + getHistoryDocumentName(timeLapse) + strDate + ".xls";
			this.createWWorkbook(path, fileName);
			this.createWSheet(getHistoryDocumentName(timeLapse), ExcelService.HISTORY_SHEET);
			this.writeHistoryTitle("HISTORIAL DE VENTAS");
			this.writeHistoryInfo("CONCEPTO: " + getHistoryDocumentName(timeLapse) + "      FECHA DE REALIZACIÓN: " + strDate);
			this.writeHistoryHeaders(headers);
			this.writeHistory(headers.size(), history);
			this.writeHistoryDetailsTitle(headers.size(), "Detalles de venta");
			this.writeHistoryDetailsHeaders(headers.size(), detailsHeaders);
			this.writeHistoryDetails(headers.size(), detailsHeaders.size(), history);
			this.closeFile();
		} catch(Exception e) {
			saved = false;
			this.closeFile();
			e.printStackTrace();
		}
		return saved;
		/*String fileName = "C:\\Users\\Public\\Documents\\" + getHistoryDocumentName(timeLapse) + strDate + ".xls";
		WritableWorkbook wworkbook;
	    try {
	   	wworkbook = Workbook.createWorkbook(new File(fileName));
	  		
	   	
			 WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
			 Label label = new Label(0, 2, "A label record");
			  wsheet.addCell(label);
	          int i=0;
			 
	           
	           int j=1;
			 
				
				 label = new Label(i++, j, j+"hola");
				  wsheet.addCell(label);
				 label = new Label(i++, j, "11");
				  wsheet.addCell(label);
				  label = new Label(i++, j, "12");
				  wsheet.addCell(label);
				  label = new Label(i++, j, "13");
				  wsheet.addCell(label);
				 j++;
		 
				
				 
				 
				 i=0;
				 label = new Label(i++, j, j+"");
				  wsheet.addCell(label);
				 label = new Label(i++, j, "21");
				  wsheet.addCell(label);
				  label = new Label(i++, j,"22");
				  wsheet.addCell(label);
				  label = new Label(i++, j, "33");
				  wsheet.addCell(label);
				 j++;
		 
			
			
	    
	    wworkbook.write();
	    wworkbook.close();
	    System.out.println("finished");
	    
	    
	    
	    
	    
	    } catch (Exception e) {
	    System.out.println(e);
		}*/
	}
	
	private String getHistoryDocumentName(Integer timeLapse) {
		String name = "";
		switch(timeLapse) {
			case 1: name = "Fecha";
					break;
			case 2: name = "Hoy";
					break;
			case 3: name = "Ayer";
					break;
			case 4: name = "Semana";
					break;
			case 5: name = "Mes";
					break;
			case 6: name = "Año";
					break;
		}
		return name;
	}
}
