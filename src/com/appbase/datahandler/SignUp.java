package com.appbase.datahandler;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;


public class SignUp extends Thread  {

	
	UpdateListener iUpdateListener;
	String response;
	public SignUp(String response,UpdateListener mUpdateListener){
		
		iUpdateListener	=	mUpdateListener;
		//run();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		parse(response);
	}
	
	/**
	 * Function used to parse and store the data on local db.
	 */
	private void parse(String response){
		/*
		 * 
		 * Parse the response here
		 */
		
		
		
		/*
		 * Updating the Ui.
		 * 
		 */
		 Handler mainThread = new Handler(Looper.getMainLooper());
		 
		   mainThread.post(new Runnable() {
               @Override
               public void run() {
            	   iUpdateListener.onUpdate();
               }
           });
		
	}

	
}
