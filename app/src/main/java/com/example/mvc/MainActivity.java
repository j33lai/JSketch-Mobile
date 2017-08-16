package com.example.mvc;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	Model model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MVC", "onCreate");

		// load the base UI (has places for the views)
		setContentView(R.layout.mainactivity);

		// Setup model
		model = new Model();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		Log.d("MVC", "onPostCreate");
		// can only get widgets by id in onPostCreate for activity xml res

		// create the views and add them to the main activity
		View1 view1 = new View1(this, model);
        Viewc viewc = new Viewc(this, model);
		Viewl viewl = new Viewl(this, model);
		LinearLayout v1 = (LinearLayout) findViewById(R.id.mainactivity_1);
		//v1.addView(view1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2);
			LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.5f);
			v1.addView(view1, lp);
			v1.addView(viewc, lp1);
			v1.addView(viewl, lp2);
		} else {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2);
			LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
			v1.addView(view1, lp);
			v1.addView(viewc, lp1);
			v1.addView(viewl, lp1);
		}

		View2 view2 = new View2(this, model);
		ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
		v2.addView(view2);

		// initialize views
		model.setChanged();
		model.notifyObservers();

	}

	// save and restore state (need to do this to support orientation change)
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("MVC", "save state");
		outState.putInt("Counter", model.getCounterValue());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("MVC", "restore state");
		super.onRestoreInstanceState(savedInstanceState);
		model.setCounterValue(savedInstanceState.getInt("Counter"));
	}



}
