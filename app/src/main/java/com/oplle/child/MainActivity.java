package com.oplle.child;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.ByteArrayOutputStream;


public class MainActivity extends Activity {

    public int a=1;Spinner spinner; CheckBox check;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_VES = "Nickname";
    public static final String APP_PREFERENCES_ROST = "Age";
    public static final String APP_PREFERENCES_FLAG = "Flag";
  //  private AdView mAdView;
    SharedPreferences mSettings;
    public EditText Text;
    public EditText Text2;
    private InterstitialAd mInterstitialAd;
public  AdRequest adRequest,  adRequest2;
    public AdView mAdView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2744392137373671/4227762748");
        adRequest = new AdRequest.Builder()
                .addTestDevice("2A0EE4C6399FB557969D6A842F2B52ED")
                .build();
        if (mInterstitialAd.isLoaded()) {
          //  mInterstitialAd.show();
        }
        mInterstitialAd.loadAd(adRequest);
        mAdView = (AdView) findViewById(R.id.adView);
        adRequest2 = new AdRequest.Builder()
                .addTestDevice("A4FFB83843D069EA331119E19BCBD280")
                .build();
        mAdView.loadAd(adRequest2);
        String[] data = {"1" , "2" , "3" , "4" , "5", "6", "7" ,
                         "8" , "9" , "10", "11", "12", "13", "14",
                         "15", "16", "17", "18", "19", "20", "21",
                         "22", "23", "24", "25", "26", "27", "28",
                         "29", "30", "31", "32", "33", "34", "35",
                         "36", "37", "38", "39", "40", "41", "42"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        check=(CheckBox) findViewById(R.id.check);
        check.isChecked();
           // Поле ввода роста
       /* */
        Text = (EditText) findViewById(R.id.editText);      //поле ввода массы тела до беременности
        Text2 = (EditText) findViewById(R.id.editText2);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSettings.contains(APP_PREFERENCES_VES)) {
            Text.setText(mSettings.getString(APP_PREFERENCES_VES, "")); }
        if(mSettings.contains(APP_PREFERENCES_ROST)) {
            Text2.setText(mSettings.getString(APP_PREFERENCES_ROST, "")); }

        if(mSettings.contains(APP_PREFERENCES_FLAG)) {
    if  (mSettings.getString(APP_PREFERENCES_FLAG, "").equals("1")) {check.setChecked(true);}}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void onClicks(View view) {

        //formirueca visual effekt
        Bitmap bmp = getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        //---------------
        mAdView.loadAd(adRequest2);
        if (true) {
          //  mInterstitialAd.show();
        }   mInterstitialAd.loadAd(adRequest);
        a=spinner.getSelectedItemPosition()+1;                       //Выбор недели
          //поле ввода массы тела В данный момент
        boolean d=check.isChecked();                                  //Флажок-двойня или нет
         Text = (EditText) findViewById(R.id.editText);      //поле ввода массы тела до беременности
        Text2 = (EditText) findViewById(R.id.editText2);
        EditText vesseg = (EditText) findViewById(R.id.editText4);

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

      //---------------отправка в др. активити вес до берем., рост, неделя, вес в данный момент, флажок двойни-----------
        String u=Text.getText().toString();
        String u1=Text2.getText().toString();
        String u2=vesseg.getText().toString();
        if  (vesseg.getText().toString().isEmpty())
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
        else if  (u2.equals("."))
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
        else  if  (Text2.getText().toString().isEmpty())
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
        else if  (u1.equals("."))
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
         else  if  (Text.getText().toString().isEmpty())
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
        else if  (u.equals("."))
        {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
         else if((Float.valueOf(Text.getText().toString())<4)|
                ((Float.valueOf(Text2.getText().toString())<54))|
                ((Float.valueOf(vesseg.getText().toString())<4))|
                (Float.valueOf(Text.getText().toString())>550)|
                ((Float.valueOf(Text2.getText().toString())>250))|
                ((Float.valueOf(vesseg.getText().toString())>550))|
                (((Float.valueOf(vesseg.getText().toString())-(Float.valueOf(Text.getText().toString()))>100))|
                (((Float.valueOf(Text.getText().toString())-(Float.valueOf(vesseg.getText().toString()))>100))  )))
                {Toast toast = Toast.makeText(MainActivity.this, R.string.Showmess, Toast.LENGTH_LONG ); toast.show();}
        else {
        intent.putExtra("username", Text.getText().toString());
        intent.putExtra("username2", Text2.getText().toString());
        intent.putExtra("ned",a );
        intent.putExtra("vesseg",  vesseg.getText().toString());
        intent.putExtra("b", d);
            //perehod na druguu activiti
            intent.putExtra("picture", byteArray);
            startActivity(intent);
            overridePendingTransition(0,0);
            //---------------------
        }



    }
    @Override
    public void onPause() {
        super.onPause();
        if (mInterstitialAd.isLoaded()) {
            //mInterstitialAd.show();
        }
        EditText ves = (EditText)findViewById(R.id.editText);
        EditText rost = (EditText)findViewById(R.id.editText2);
        check=(CheckBox) findViewById(R.id.check);
        String i="0";
        if (check.isChecked()) {i="1";}
        String strves = ves.getText().toString();
        String strrost = rost.getText().toString();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_VES, strves);
        editor.putString(APP_PREFERENCES_ROST, strrost);
        editor.putString(APP_PREFERENCES_FLAG, i);
        editor.commit();
    }

    private Bitmap getBitmap(){
        View root = getWindow().getDecorView().findViewById(android.R.id.content);
        root.setDrawingCacheEnabled(true);
        return root.getDrawingCache();




    }
}
