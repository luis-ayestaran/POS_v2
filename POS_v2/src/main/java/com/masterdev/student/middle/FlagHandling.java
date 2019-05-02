package com.masterdev.student.middle;

public class FlagHandling {
	private Boolean flag;
	
	public FlagHandling() {
		this.flag = false;
	}
	
	public Boolean isEnabled() {
		return flag;
	}
	
	public void setEnabled(Boolean flag) {
		this.flag = flag;
	}
}
