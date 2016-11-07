package com.oplle.child;

import android.app.Activity;
import android.os.Bundle;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float rost = Float.parseFloat(getIntent().getExtras().getString("username2"));
        float ves = Float.parseFloat(getIntent().getExtras().getString("username"));
        int ned = getIntent().getExtras().getInt("ned");
        float vesseg = Float.parseFloat(getIntent().getExtras().getString("vesseg"));
        boolean d=getIntent().getExtras().getBoolean("b");
        Draw2D draw2D = new Draw2D(this, rost, ves, ned, vesseg, d);
        setContentView(draw2D);
    }
}
