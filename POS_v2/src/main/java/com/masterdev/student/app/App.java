package com.masterdev.student.app;

import com.masterdev.student.views.SplashScreen;

public class App{
	
    public static void main( String[] args ) {
        App app = new App();
        app.initialise(args);
    }
    
    private void initialise(String[] args) {
    	new SplashScreen().launchView(args);
    }
}
