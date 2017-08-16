package com.example.mvc;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View1 extends LinearLayout implements Observer {
	
	private Model model;
	private ArrayList<Button> buttons = new ArrayList<Button>();

    private static int[] tool_list = {R.id.select_tool, R.id.erase_tool, R.id.line_tool, R.id.circle__tool, R.id.rec_tool, R.id.fill_tool};

	public View1(Context context, Model m) {
		super(context);
		
	    Log.d("MVC", "View1 constructor");
		
		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view1, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);


		// get a reference to widgets to manipulate on update
        for (int i = 0; i < 6; i++) {
            final int itmp = i;
            //buttons[i] = (Button) findViewById(R.id.select_tool);
            Button button = (Button)findViewById(tool_list[i]);
            //Log.d("MVC", Integer.toString(tnum));
            //buttons[i].setText("Off");
            //buttons[i].setTextSize(12);
            //buttons[i].setTextOn(Integer.toString(i));
            buttons.add(button);
            //button.setText("1");

            // create a controller for the button
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do this each time the button is clicked
                    model.changeTool(itmp);
                    //model.incrementCounter();
                }
            });
        }
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("MVC", "update View1");
        for (int i = 0; i < 6; i++) {
            if (Model.tool_no != i) {
                buttons.get(i).setText("");
            } else {
                buttons.get(i).setText("On");
            }

        }
		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
		//button.setText(String.valueOf(model.getCounterValue()));
	}
}
