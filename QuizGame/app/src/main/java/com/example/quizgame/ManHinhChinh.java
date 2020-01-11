package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
public class ManHinhChinh extends AppCompatActivity {

    Button btn_out;
    private SharedPreferences mPreferences;
    private SharedPreferences mPref;
    private TextView tv_tongCredit;
    private  String tongCredit;
    private String sharedPrefFile = "com.example.quizgame";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        tv_tongCredit=findViewById(R.id.textView_tongCredit_lv);
        Intent intent=getIntent();
        tongCredit=intent.getStringExtra("credit");
        tv_tongCredit.setText(tongCredit);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    public void XuLiDangXuat(View view) {
        // Xoa token trong SharedPreferences
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
        btn_out= findViewById(R.id.btnDangXuat);
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ManHinhChinh.this);
                dialog.setContentView(R.layout.custom_dialog_log_out);
                dialog.setCancelable(false);
                Button btnout = dialog.findViewById(R.id.btn_dangxuat);
                Button btncontinue = dialog.findViewById(R.id.btn_tieptuc);
                btnout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManHinhChinh.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                btncontinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    public void XuLiQuanLiTaiKhoan(View view) {
        startActivity(new Intent(this,QuanLiTaiKhoanActivity.class));
    }

    public void XuLiTroChoiMoi(View view) {
        Intent intent = new Intent(this,ChoiGameActivity.class);
        intent.putExtra("credit_lv",tongCredit);
        startActivity(intent);
    }

    public void XuLiMuaCreadit(View view) {
        startActivity(new Intent(this,MuaCreaditActivity.class));
    }

    public void XuLiHuongDan(View view) {
        startActivity(new Intent(this,HuongDanActivity.class));
    }
}
