package com.example.mvc;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;


/**
 * Created by j33lai on 04/12/16.
 */

public class GeoShape {

    static int[] col_list = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};

    Paint mp = new Paint();

    int x0;
    int y0;
    int x1;
    int y1;

    int xs;
    int ys;

    int shape_type = -1;
    int shape_col = -1;
    int shape_line = -1;

    boolean fill = false;

    GeoShape(int x0, int y0, int x1, int y1, int t) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        shape_type = t;
        mp.setStyle(Paint.Style.STROKE);
    }

    public void setCol(int col) {
        shape_col = col;
    }

    public void setLine(int l) {
        shape_line = l;
    }

    public void draw(Canvas canvas) {
        if (fill) {
            mp.setStyle(Paint.Style.FILL);
        } else {
            mp.setStyle(Paint.Style.STROKE);
        }
        if (shape_col >=0) {
            mp.setColor(col_list[shape_col]);
        }
        mp.setStrokeWidth(shape_line * 5 + 5);
        switch (shape_type) {
            case 0 :
                canvas.drawLine(x0, y0, x1, y1, mp);
                //g2.drawLine(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, x0 < x1 ? x1 : x0, y0 < y1 ? y1 : y0);
                break;
            case 1 :
                if (fill) {
                    int t = (shape_line + 1) * 5 / 2;
                    canvas.drawOval(new RectF((x0 < x1 ? x0 : x1) - t, (y0 < y1 ? y0 : y1) - t, (x0 >= x1 ? x0 : x1) + t, (y0 >= y1 ? y0 : y1) + t), mp);
                } else {
                    canvas.drawOval(new RectF(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, x0 >= x1 ? x0 : x1, y0 >= y1 ? y0 : y1), mp);
                }
                break;
            case 2 :
                if (fill) {
                    int t = (shape_line + 1) * 5 / 2;
                    canvas.drawRect((x0 < x1 ? x0 : x1) - t, (y0 < y1 ? y0 : y1) - t, (x0 >= x1 ? x0 : x1) + t, (y0 >= y1 ? y0 : y1) + t, mp);
                } else {
                    canvas.drawRect(x0 < x1 ? x0 : x1, y0 < y1 ? y0 : y1, x0 >= x1 ? x0 : x1, y0 >= y1 ? y0 : y1, mp);
                }
                break;
            default :
                break;
        }
    }

    public boolean contains(float px, float py) {
        switch (shape_type) {
            case 0 :
                return false;
            case 1 :
            case 2 :
                if (px >= (x0 < x1 ? x0 : x1) && px <= (x0 >= x1 ? x0 : x1) && py >= (y0 < y1 ? y0 : y1) && py <= (y0 >= y1 ? y0 : y1)) {
                    return true;
                } else {
                    return false;
                }
            default :
                return false;
        }
    }

    public void select(int px, int py) {
        xs = px;
        ys = py;
    }

    public void updatePosition(int px, int py) {
        System.out.println("updateposition");
        int xt = px - xs;
        int yt = py - ys;
        x0 += xt;
        y0 += yt;
        x1 += xt;
        y1 += yt;
        xs = px;
        ys = py;
    }



}
