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

public class Viewl extends LinearLayout implements Observer {

    private Model model;
    private ArrayList<Button> buttons = new ArrayList<Button>();

    private static int[] line_list = {R.id.line_0, R.id.line_1, R.id.line_2, R.id.line_3};

    public Viewl(Context context, Model m) {
        super(context);

        Log.d("MVC", "Viewc constructor");

        // get the xml description of the view and "inflate" it
        // into the display (kind of like rendering it)
        View.inflate(context, R.layout.viewl, this);

        // save the model reference
        model = m;
        // add this view to model's list of observers
        model.addObserver(this);


        // get a reference to widgets to manipulate on update
        for (int i = 0; i < 4; i++) {
            final int itmp = i;
            //buttons[i] = (Button) findViewById(R.id.select_tool);
            Button button = (Button)findViewById(line_list[i]);
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
                    model.changeLine(itmp);
                    //model.incrementCounter();
                }
            });
        }
    }

    // the model call this to update the view
    public void update(Observable observable, Object data) {
        Log.d("MVC", "update Viewl");
        for (int i = 0; i < 4; i++) {
            if (Model.thickness_no != i) {
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
