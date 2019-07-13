package com.masterdev.student.middle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class DatePickerMethods {
	public void setConverter(DatePicker datePicker) {
		datePicker.setConverter(new StringConverter<LocalDate>()
		{
		    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
		    @Override
		    public String toString(LocalDate localDate)
		    {
		        if(localDate==null)
		            return "";
		        return dateTimeFormatter.format(localDate);
		    }
	
		    @Override
		    public LocalDate fromString(String dateString)
		    {
		        if(dateString==null || dateString.trim().isEmpty())
		        {
		            return null;
		        }
		        return LocalDate.parse(dateString,dateTimeFormatter);
		    }
		});
	}
	
	public Date stringToDateTime(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String dateTimeToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mysqlDate = formatter.format(date);
		return mysqlDate;
	}
	
	public Date stringToDate(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mysqlDate = formatter.format(date);
		return mysqlDate;
	}
	
	public String dateToConventionalString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String mysqlDate = formatter.format(date);
		return mysqlDate;
	}
	
	public Date stringToTime(String string) {
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm:ss").parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String timeToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String mysqlDate = formatter.format(date);
		return mysqlDate;
	}
	
	public Integer compareDates(Date date1, Date date2) {
		Integer result = -2;
		if (date1.before(date2)) {
            result = -1;
        }
		if (date1.equals(date2)) {
        	result = 0;
        }
        if (date1.after(date2)) {
        	result = 1;
        }
		return result;
	}
	
	public Boolean areSameMonth(Date date1, Date date2) {
		Boolean sameMonth = false;
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date1);
		int month1 = gg.get(Calendar.MONTH);
		int year1 = gg.get(Calendar.YEAR);
		gg.setTime(date2);
		int month2 = gg.get(Calendar.MONTH);
		int year2 = gg.get(Calendar.YEAR);
		if(year1 == year2 && month1 == month2) {
			sameMonth = true;
		}
		return sameMonth;
	}
	
	public Boolean areSameYear(Date date1, Date date2) {
		Boolean sameMonth = false;
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date1);
		int year1 = gg.get(Calendar.YEAR);
		gg.setTime(date2);
		int year2 = gg.get(Calendar.YEAR);
		if(year1 == year2) {
			sameMonth = true;
		}
		return sameMonth;
	}
	
	public Boolean datesAreClose(Date date1, Date date2) {
		Boolean result = false;
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date1);
		int today = gg.get(Calendar.DAY_OF_MONTH);
		int thisMonth = gg.get(Calendar.MONTH);
		int thisYear = gg.get(Calendar.YEAR);
		gg.setTime(date2);
		int dischargeDay = gg.get(Calendar.DAY_OF_MONTH);
		int dischargeMonth = gg.get(Calendar.MONTH);
		int dischargeYear = gg.get(Calendar.YEAR);
		if(thisYear == dischargeYear) {
			if(thisMonth == dischargeMonth) {
				if(dischargeDay - today <= 10) {
					result = true;
				}
			} else {
				if(dischargeMonth - thisMonth == 1) {
					if(today - dischargeDay <= 20) {
						result = true;
					}
				}
			}
		} else {
			if(thisMonth - dischargeMonth == 11) {
				if(today - dischargeDay <= 20) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public Date getCurrentDateTime() {
		GregorianCalendar gg = new GregorianCalendar();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		String strDate = sdf1.format(gg.getTime()) + " " + sdf2.format(gg.getTime());
		Date date = stringToDateTime(strDate);
		return date;
	}
	
	public Date getCurrentDate() {
		GregorianCalendar gg = new GregorianCalendar();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf1.format(gg.getTime());
		Date date = stringToDate(strDate);
		return date;
	}
	
	public Date getCurrentTime() {
		GregorianCalendar gg = new GregorianCalendar();
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String strDate = sdf1.format(gg.getTime());
		Date date = stringToDate(strDate);
		return date;
	}
	
	public Date getNDaysAgo(Integer nDays) {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -nDays);
	    return stringToDate(dateToString(cal.getTime()));
	}
	
	public Integer getDayOfWeek(Date date) {
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date);
		Integer dayOfWeek = gg.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	public Integer getMonth(Date date) {
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date);
		Integer month = gg.get(Calendar.MONTH);
		return month;
	}
	
	public Integer getYear(Date date) {
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTime(date);
		Integer year = gg.get(Calendar.YEAR);
		return year;
	}
	
	public Date localDateToUtilDate(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
}
