package com.oplle.child;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import android.view.View;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 11.06.2015.
 */
public class Draw2D extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Path mPath1 = new Path();
    private Path Ox = new Path();
    private Path Oy = new Path();
    private DashPathEffect mDashPathEffect;
    private CornerPathEffect mCornerPathEffect;
    public float rost=0;
    public float ves=0;
    public int ned1=0;
    public float vesseg1=0;
    public boolean Dowble;
    public float ox=0, oy;

    public Draw2D(Context context, float a, float b, int ned, float vesseg, boolean d) {
        super(context);
        init();
       rost=a;
        ves=b;
        ned1=ned;
        vesseg1=vesseg;
        Dowble=d;
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        float radius = 70.0f;
        mCornerPathEffect = new CornerPathEffect(radius);
        mPaint.setPathEffect(mDashPathEffect);
        mPaint.setPathEffect(mCornerPathEffect);

    }

    @Override
    protected  void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);


        float x1 = (float) getWidth();
        float y1 = (float) getHeight() - 20;

        // -----------------закрашиваем холст кактм-то цветом----------------------
        mPaint.setColor(Color.argb(255, 231, 255, 94));
        canvas.drawPaint(mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath, mPaint);


        //--------------------------------------------------------------------------

        //-----------------------------------------------------------
        float vmax = 0;
        float raznica_vesa = (float) (vesseg1 - ves);
        float IMT = (float) (ves / ((rost / 100) * (rost / 100)));
        if (Dowble) {
            if (IMT < 19.8) {vmax = (float) (ves + 24.49);}
            if ((IMT >= 19.8) & (IMT < 30)) {vmax = (float) (ves + 22.67);}
            if (IMT >= 30) {vmax = (float) (ves + 19.05);}
                    }
        else {
            if (IMT < 19.8) {vmax = (float) (ves + 18.14);}
            if ((IMT >= 19.8) & (IMT < 26)) {vmax = (float) (ves + 15.87);}
            if (IMT >= 26) {vmax = (float) (ves + 11.34);}
            if (IMT >= 32) {vmax = (float) (ves + 9.12);}
            }
            float ymax;
            if (raznica_vesa > (vmax - ves)) {
                ymax = vesseg1;
            } else {
                ymax = vmax;
            }
            float t2 = x1 / 46;
            for (int u = 1; u <= ned1; u++) {
                if (u == ned1) {
                    ox = t2 * (u) + 56;
                }
            }
            float ymin = ves;
            float promegutok;
            if (((vesseg1 - ves) >= 0)) {
                promegutok = (float) (ymax - ves);
            } else {
                ymin = vesseg1;
                promegutok = (float) (ymax - vesseg1);
            }
            float koef = (y1 - 20) / promegutok;
            float promegutok_gr = koef * (vmax - ves);
oy=y1-((vesseg1-ymin)*koef);

            //-----------------------Стром два графика-------------------
            mPaint.setColor(Color.argb(250, 128, 71, 60));
            mPaint.setStrokeWidth(4);
            float ynach;
            if ((vesseg1 - ves) >= 0) {
                ynach = y1;
            } else {
                ynach = y1 - (koef * (ves - vesseg1));
            }
            mPath.moveTo(50, ynach);
            mPath1.moveTo(50, ynach);
            for (int i = 1; i < 4; i++) {
                 mPath.lineTo((i * (int) (x1 / 3) + 50), mass1(i, 1, promegutok_gr, ynach));
               float pessy= mass1(i, 1, promegutok_gr, ynach);
                float pessx=(i * (int) (x1 / 3));
                float del=pessy/pessx;
                pessy=(pessx-50)*del;

                if (i==3) { mPath1.lineTo(pessx+50, y1-((vmax-ymin)*koef)-koef);}
                else { mPath1.lineTo((i * (float) (x1 / 3)), mass1(i, 0, promegutok_gr, ynach));}
            }
            canvas.drawPath(mPath, mPaint);
            canvas.drawPath(mPath1, mPaint);

            //---------------------------------Рисуем Оси координат----------------------------------------------
            mPaint.setColor(Color.argb(254, 0, 0, 0));
            Ox.moveTo(0, y1);
            Ox.lineTo(x1, y1);

            Oy.moveTo(50, y1 + 20);
            Oy.lineTo(50, -100);

            mPaint.setStrokeWidth(1);//задаём ширину осей
            mPaint.setTextSize(19);
            canvas.drawPath(Ox, mPaint);
            canvas.drawPath(Oy, mPaint);
            float t = x1 / 23;
            for (int u = 1; u <= 21; u++) {
                if (u > 4) {
                    canvas.drawText(u * 2 + "", t * (u) + 47, y1 + 19, mPaint);
                } else {
                    canvas.drawText(u * 2 + "", t * (u) + 50, y1 + 19, mPaint);
                }
            }


            canvas.drawText(Float.toString(ymax), 0, 20, mPaint);

            canvas.drawText(Float.toString(ymin), 0, y1, mPaint);
            Ox.moveTo(0, 20);
            Ox.lineTo(54, 20);
            canvas.drawPath(Ox, mPaint);
            int a1 = (int) ((ymax - ymin) * 100);
            float b = (ymax - ymin) / 5;
            int ts = 0;
            int i = 0;
            for (i = 0; i < 4; i++) {
                Ox.moveTo(0, y1 - (koef * b * (i + 1)));
                Ox.lineTo(54, y1 - (koef * b * (i + 1)));
                canvas.drawPath(Ox, mPaint);
                ts = (int) ((ymin + (b * (i + 1))) * 100);
                canvas.drawText(ts / 100.00 + "", 0, y1 - (koef * b * (i + 1)), mPaint);

            }
            Ox.moveTo(0,  y1-(koef*b*(i+1)));
            Ox.lineTo(54,  y1-(koef*b*(i+1)));
            canvas.drawPath(Ox, mPaint);

        /*int a2= (int) ((ves+((vmax-ves)/3))*100);
        canvas.drawText(a2/100.00+"", 0, y1-((y1-20)/3), mPaint);
        canvas.drawText(a1/100.00+"", 0, y1-((y1-20)/3)-((y1-20)/3), mPaint);
        canvas.drawText(ves+"", 0, y1, mPaint);*/
//------------------------------------------------------------------------------------
//--------------------------Рисуем точку на графике(ромашка)--------------------------
            mPaint.setColor(Color.argb(254, 244, 115, 19));
            int r = 8;

            mPaint.setStyle(Paint.Style.FILL);
           float oy2=oy;
            canvas.drawCircle(ox, oy2, r, mPaint);
            mPaint.setColor(Color.WHITE);
            canvas.drawCircle(ox - (18 * r / 10), oy2 + r, r, mPaint);
            canvas.drawCircle(ox - (18 * r / 10), oy2 - r, r, mPaint);
            canvas.drawCircle(ox + (18 * r / 10), oy2 + r, r, mPaint);
            canvas.drawCircle(ox + (18 * r / 10), oy2 - r, r, mPaint);
            canvas.drawCircle(ox, oy2 + (2 * r), r, mPaint);
            canvas.drawCircle(ox, oy2 - (2 * r), r, mPaint);


    }






    final int mass1(int k1,int k2, float yn, float yna) //Опеределяет коорд. точки по оси Оу, k1-номер точки (1,2,3), k2-номер графика(0 или 1)
    {
        int e[][]= new int[4][3];
        int g1[]={3, 13,  16};
        int g2[]={1, 8,  11};
        //g1[0]=10;g1[1]=9;g1[2]=8;

        float k=(float) 16.0;
//if (oy<0) {k=32;}
        e[0][0]= (int)yn;
        e[0][1]= (int)yn;
        for (int i=1; i<4; i++){
            e[i][0]=(int) (yna-((yn/16)*g1[i-1]));
           // if (i==2) {e[i][0]=yna-yn;}
            e[i][1]=(int) (yna-((yn/16)*g2[i-1]));
            //e[i][0]=(int) ((yn/k)*g1[i-1]);
          //  e[i][1]=(int) ((yn/k)*g2[i-1]);
            if (k1==i) {return e[k1][k2];}
        }

        return 0;
    }


}