package com.example.mvc;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.MotionEventCompat;
import android.util.*;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View2 extends LinearLayout implements Observer {
	
	private Model model;
	//private ShapeDrawable mDrawable;
	//private TextView textview;
	static private int curw = -1;
	static private int curh = -1;
	static private int iter = 0;

	public View2(Context context, Model m) {
		super(context);
		
	    Log.d("MVC", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view2, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

		// get a reference to widgets to manipulate on update
		//textview = (TextView)findViewById(R.id.view2_textview);
		
		// this is a view only view, no controller 
		// (unlike the mvc java swing example)
		View view = (View)findViewById(R.id.canvas);
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				final int pointerIndex = MotionEventCompat.getActionIndex(motionEvent);
				int x = (int) MotionEventCompat.getX(motionEvent, pointerIndex);
				int y = (int) MotionEventCompat.getY(motionEvent, pointerIndex);
				final int action = MotionEventCompat.getActionMasked(motionEvent);
				if (action == MotionEvent.ACTION_DOWN) {
					switch (model.tool_no) {
						case 0 :
							model.selectShape(x, y);
							break;
						case 1 :
							model.deleteShape(x, y);
							break;
						case 2 :
						case 3 :
						case 4 :
							model.createShape(x, y);
							break;
						case 5 :
							model.fillShape(x, y);
							break;
					}
					Log.d("touch0", Integer.toString(x)+ " " +Integer.toString(y));
				} else {
					switch (model.tool_no) {
						case 0 :
							model.updatePosition(x, y);
							break;
						case 1 :
							break;
						case 2 :
						case 3 :
						case 4 :
							model.updateShape(x, y);
							break;
						case 5 :
							break;
						default :
							break;
					}
					//Log.d("touch1", Integer.toString(x)+ " " +Integer.toString(y));
				}

				return true;
			}
		});




		//int x = 100;
		//int y = 100;
		//int width = 300;
		//int height = 50;

		//mDrawable = new ShapeDrawable(new OvalShape());
		//mDrawable.getPaint().setColor(0xff74AC23);
		//mDrawable.setBounds(x, y, x + width, y + height);
		//View textview = (View)findViewById(R.id.view2_textview);
		this.setBackgroundColor(Color.WHITE);
		//this.setWillNotDraw(false);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
		//mDrawable.draw(canvas);
		//model.mDrawable.draw(canvas);
		if (iter < 3) {
			curw = canvas.getWidth();
			curh = canvas.getHeight();
			iter++;
		}
		double sx = (double)canvas.getWidth() / (double)curw;
		double sy = (double)canvas.getHeight() / (double)curh;


		canvas.scale((float)sx, (float)sy);
		for (GeoShape geo : model.geos) {
			geo.draw(canvas);
		}

		Log.d("Ondraw", "view2 ondraw");
	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("MVC", "update View2");
	    invalidate();
	    int n = model.getCounterValue();


	    /*
	    StringBuilder s = new StringBuilder(n);
	    for (int i = 0; i < n; i++) {
	    	s.append("x");
	    }
        */
		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
	    //textview.setText(s);
	}
}
