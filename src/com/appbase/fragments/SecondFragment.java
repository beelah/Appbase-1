package com.appbase.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbase.connection.Request;
import com.appbase.connection.RequestQueue;
import com.appbase.connection.Response;
import com.appbase.connection.VolleyError;
import com.appbase.connection.toolbox.StringRequest;
import com.appbase.connection.toolbox.Volley;
import com.appbase.datahandler.SignUp;
import com.appbase.datahandler.UpdateListener;
import com.appbase.R;

public class SecondFragment extends BaseFragment{

	private TextView textViewServerResponse;	// Server response
	
	private String requestUrl		=	"http://api.wiink.it/1.0/LoginApi?vUserName=anas&vPassword=123&vDeviceId=1"; //Sample request url
	
	private RequestQueue queue;	//Volley request queue
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if (container == null) {
			// We have different layouts, and in one of them this 
			// fragment's containing frame doesn't exist.  The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed.  Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;        
		}
		
		   // Inflate the layout for this fragment
		return inflater.inflate(R.layout.second_fragment, container, false);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		init();
	}
	
	
	/**
	 * Method to initialise all the UI element and start
	 *  any network operation if required.
	 */
	private void init(){
		
		textViewServerResponse	=	(TextView) getActivity().findViewById(R.id.txtview_fetching);
		
		//Sample Http request
		doReqHit();
		
	}
	
	/**
	 * Performs the network request.
	 * gets the server response and set that to textView.
	 * Use Volley open source library.
	 * @param 
	 * 
	 */
	
	private void doReqHit(){
		queue = Volley.newRequestQueue(getActivity());
		StringRequest jsObjRequest = new StringRequest(Request.Method.GET,requestUrl,  new Response.Listener<String>() {

			@Override
			public void onResponse(final String response) {
				
				/**
				 * Parse the content. this code might need to move to volley lib.
				 *  To avoid possible performce impact 
				 */
				
				textViewServerResponse.setText("Response => "+response.toString());
				SignUp mSignUp	=	new SignUp(response, new UpdateListener() {
					
					@Override
					public void onUpdate() {
						// TODO Auto-generated method stub
						textViewServerResponse.setText("Response => "+response.toString());
						if(getActivity()!=null){
						ProgressBar mProgressBar	=	(ProgressBar) getActivity().findViewById(R.id.progressBar1);
						if(mProgressBar!=null)
							mProgressBar.setVisibility(View.GONE);
						}
					}
				});
				mSignUp.start();
				
				// TODO Auto-generated method stub
			
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.e("Error","Error"+error.networkResponse);
			}
		});
		jsObjRequest.addMarker("DUMMYTAG");
		queue.add(jsObjRequest);
		
	}
	
	@Override
	public void onPause(){
		super.onPause();
		queue.cancelAll("DUMMYTAG");
	}
}
