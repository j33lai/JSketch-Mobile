package com.example.mvc;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int counter;

	static int tool_no = -1;
	static int color_no = -1;
	static int thickness_no = -1;
	static int cur_shape = -1;

	//ShapeDrawable mDrawable;

	static ArrayList<GeoShape> geos = new ArrayList<GeoShape>();

	int x0 = 0;
	int y0 = 0;

	int x1 = 0;
	int y1 = 0;

	Model() {
		counter = 0;
		Log.d("MVC", "new model");
		//mDrawable = new ShapeDrawable(new OvalShape());
		//mDrawable.getPaint().setColor(0xff74AC23);
		//mDrawable.setBounds(x0, y0, x1, y1);
	}

	// Data methods
	public int getCounterValue() {
		return counter;
	}

	public void setCounterValue(int i) {
		counter = i;
		Log.d("MVC", "Model: set counter to " + counter);
		setChanged();
		notifyObservers();
	}

	public void incrementCounter() {
		counter++;
		Log.d("MVC", "Model: increment counter to " + counter);
		setChanged();
		notifyObservers();
	}

	public void changeTool(int i) {
		if (tool_no != i) {
			int tmp = tool_no;
			tool_no = i;

			setChanged();
			Log.d("MVC", "Model: increment counter to " + Integer.toString(i));
			notifyObservers();
		}
	}

	public void changeCol(int i) {
		if (color_no != i) {
			int tmp = color_no;
			color_no = i;

			setChanged();
			notifyObservers();
		}
	}

	public void changeLine(int i) {
		if (thickness_no != i) {
			int tmp = thickness_no;
			thickness_no = i;

			setChanged();
			notifyObservers();
		}
	}

	public void createShape(int px, int py) {
		GeoShape shape = new GeoShape(px, py, px, py, tool_no - 2);
		geos.add(shape);
		shape.setCol(color_no);
		shape.setLine(thickness_no);
		cur_shape = geos.size() - 1;
		Log.d("create shape", Integer.toString(geos.size()));
	}

	public void selectShape(int px, int py) {
		cur_shape = -1;
		int N = geos.size();
		for (int i = N - 1; i >= 0; i--) {
			if (geos.get(i).contains(px, py)) {
				cur_shape = i;
				geos.get(i).select(px, py);
				changeCol(geos.get(i).shape_col);
				changeLine(geos.get(i).shape_line);
				break;
			}
		}

	}


	public void updateShape(int px, int py) {
		GeoShape tmpgeo = geos.get(cur_shape);
		tmpgeo.x1 = px;
		tmpgeo.y1 = py;
		setChanged();
		notifyObservers();
	}

	public void deleteShape(int px, int py) {
		cur_shape = -1;
		int N = geos.size();
		for (int i = N - 1; i >= 0; i--) {
			if (geos.get(i).contains(px , py)) {
				cur_shape = i;
				geos.remove(i);
				break;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void fillShape(int px, int py) {
		cur_shape = -1;
		int N = geos.size();
		for (int i = N - 1; i >= 0; i--) {
			if (geos.get(i).contains(px, py)) {
				cur_shape = i;
				geos.get(i).fill = true;

				break;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void updatePosition(int px, int py) {
		if (cur_shape >= 0) {
			geos.get(cur_shape).updatePosition(px, py);
			setChanged();
			notifyObservers();
		}
	}



	// Observer methods
	@Override
	public void addObserver(Observer observer) {
		Log.d("MVC", "Model: Observer added");
		super.addObserver(observer);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void notifyObservers() {
		Log.d("MVC", "Model: Observers notified");
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

	@Override
	protected void clearChanged() {
		super.clearChanged();
	}
}