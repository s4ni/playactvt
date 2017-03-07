package com.playactvt.sa4ni.playactvt;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    GridView grd_main;
    TextView tv_Hw;
    EditText et_app;
    Button btn_submit;
    private PackageManager pmp;
    private List<ApplicationInfo> all_app;
    int count=0;
    private GridviewAdapter GVA_avtvt;
    private ArrayList<String> al_nm;
    private ArrayList<String> al_lbl;
    private ArrayList<Drawable> lst_icn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        grd_main=(GridView)findViewById(R.id.GRD_mian);
        grd_main.setAdapter(GVA_avtvt);

        tv_Hw = (TextView)findViewById(R.id.tvHw);
        btn_submit = (Button)findViewById(R.id.btn_Submit);
        et_app = (EditText)findViewById(R.id.etApp);

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        lst_icn = new ArrayList<Drawable>();
        al_nm = new ArrayList<String>();
        al_lbl = new ArrayList<String>();
        pmp = getPackageManager();
        all_app = pmp.getInstalledApplications(0);
        for(ApplicationInfo appinfo: all_app){

            String str_nm = pmp.getApplicationLabel(appinfo).toString();
            Drawable icn = pmp.getApplicationIcon(appinfo);
            al_nm.add(str_nm);
            lst_icn.add(icn);
            count = count + 1;

        }
        for (int i=0; i < count; i++) {

            int lngth = all_app.get(i).toString().length();
            String str_lbl = all_app.get(i).toString().substring(24, lngth - 1);
            al_lbl.add(str_lbl);
        }
        GVA_avtvt=new GridviewAdapter(this,al_nm,al_lbl, lst_icn);
        grd_main.setAdapter(GVA_avtvt);

        grd_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String sel_app = GVA_avtvt.getItem(position);
                Log.d(sel_app," Clicked");
                et_app.setText(sel_app);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_pkg="";
                for (int i=0; i < count; i++) {
                    if (et_app.getText().toString().equals(al_nm.get(i).toString())) {

                        str_pkg = al_lbl.get(i);
                        Log.d("Cached Package", str_pkg);
                        break;

                    }else{
                        str_pkg = "";
                    }
                }
                if(str_pkg != ""){

                    Log.d("Cached", str_pkg);
                    Intent intent = pmp.getLaunchIntentForPackage(str_pkg);//com.android.deskclock
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), et_app.getText().toString()+" Loaded", Toast.LENGTH_SHORT).show();

                }else if(str_pkg == ""){
                    Toast.makeText(getApplicationContext(), "Application Not Found, Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
//Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] flg=0x10000000 pkg=com.android.deskclock cmp=com.android.deskclock/.DeskClock }