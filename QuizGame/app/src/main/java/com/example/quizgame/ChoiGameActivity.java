package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class ChoiGameActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {
    private final ArrayList<LinhVuc> mListLinhVuc=new ArrayList<>();
    private final ArrayList<LinhVuc> mListCauHoi=new ArrayList<>();

    private ImageButton btnCallActivityMain;
    CountDownTimer countDownTimer;
    int n=0;
    private TextView mNoiDung;
    private TextView mDapAnA;
    private TextView mDapAnB;
    private TextView mDapAnC;
    private TextView mDapAnD;
    private String dapan;
    private int ada, bda, cda,dda;
    Button btnDapAnA;
    Button btnDapAnB;
    Button btnDapAnC;
    Button btnDapAnD;
    private Button linhvuc1;
    private Button linhvuc2;
    private Button linhvuc3;
    private Button linhvuc4;
    String[] Listlinhvuc = new String[4];
    int[] Listid = new int[4];

    private  int id_linhvuc;
    private   Button[] Listbutton=new Button[4];
    private String phuongandung;
    private int dapandung=0;
    int dem=0;
    int socau=0;
    int tongsocau;
    private TextView soCau;
    private Button btnTiepTheo;
    int mdefault;
    private String kqa,kqb,kqc,kqd;
    private TextView goiDien;
    private ImageButton imgButton5050,quacauhoi;
    View incLinhVuc,incCauHoi;
    private TextView tv_tongCredit;
    private  String tongCredit;
    private TextView tvTongSoCredit;
    private TextView textView_Diem;
    private int diem=0;
    private int tongsodiem;
    private int diemtraitim=0;

    private int dem_trai_tim = 0;
    private String phuongana;
    private String phuonganb;
    private String phuonganc;
    private String phuongand;
    private String kytu;
    private ImageView imvTraiTim1;
    private ImageView imvTraiTim2;
    private ImageView imvTraiTim3;
    private ImageView imvTraiTim4;
    private ImageView imvTraiTim5;
    private ImageView[] listTraiTim = new ImageView[5];
    private int[] listTraiTimxoa = new int[5];
    private int id_imView_Traitim = 0;
    private ImageView imvMuaDapAn;
    private TextView textView_dapan;
    private int id_dapan_dung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        final int mColor= ContextCompat.getColor(this,R.color.colorPrimary);
        mdefault=ContextCompat.getColor(this,R.color.colordefault);
        incLinhVuc=findViewById(R.id.inc_linhvuc);
        incCauHoi=findViewById(R.id.inc_cauhoi);
        linhvuc1=findViewById(R.id.button_LinhVuc1);
        linhvuc2=findViewById(R.id.button_LinhVuc2);
        linhvuc3=findViewById(R.id.button_LinhVuc3);
        linhvuc4=findViewById(R.id.button_LinhVuc4);
        imvTraiTim1 = findViewById(R.id.imageView1);
        imvTraiTim2 = findViewById(R.id.imageView2);
        imvTraiTim3 = findViewById(R.id.imageView3);
        imvTraiTim4 = findViewById(R.id.imageView4);
        imvTraiTim5 = findViewById(R.id.imageView5);
        quacauhoi=findViewById(R.id.imageButton_qua_cau_hoi);
        listTraiTim[0]=imvTraiTim2;
        listTraiTim[1]=imvTraiTim1;
        listTraiTim[2]=imvTraiTim5;
        listTraiTim[3]=imvTraiTim3;
        listTraiTim[4]=imvTraiTim4;
        textView_Diem=findViewById(R.id.textView_Diem);
        //textView_dapan = findViewById(R.id.);
        btnTiepTheo=findViewById(R.id.button_TiepTheo);

        imgButton5050=findViewById(R.id.imageButton_5050);
        imvMuaDapAn=findViewById(R.id.imageButton_mua_credit);
        tv_tongCredit=findViewById(R.id.textView_tongCredit_lv);
        Intent intent=getIntent();
        tongCredit=intent.getStringExtra("credit_lv");
        tv_tongCredit.setText(tongCredit);
        soCau=findViewById(R.id.textView_So_Cau_Hoi);

        if(getSupportLoaderManager().getLoader(0)!=null) {
            getSupportLoaderManager().initLoader(0, null , this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);

        intent =getIntent();
        int id = intent.getIntExtra("linh_vuc_id",0);
        Bundle bundle =new Bundle();
        bundle.putInt("idLinhVuc",id);
        mNoiDung=findViewById(R.id.textView_noi_dung_cau_hoi);
        mDapAnA=findViewById(R.id.button_dap_an_a);
        mDapAnB=findViewById(R.id.button_dap_an_b);
        mDapAnC=findViewById(R.id.button_dap_an_c);
        mDapAnD=findViewById(R.id.button_dap_an_d);

        btnDapAnA=findViewById(R.id.button_dap_an_a);
        btnDapAnB=findViewById(R.id.button_dap_an_b);
        btnDapAnC=findViewById(R.id.button_dap_an_c);
        btnDapAnD=findViewById(R.id.button_dap_an_d);

        Listbutton[0]=btnDapAnA;
        Listbutton[1]=btnDapAnB;
        Listbutton[2]=btnDapAnC;
        Listbutton[3]=btnDapAnD;


       /* btnDapAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDapAnA.setBackgroundColor(mColor);
                btnDapAnB.setEnabled(false);
                btnDapAnC.setEnabled(false);
                btnDapAnD.setEnabled(false);

            }
        });
        btnDapAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDapAnB.setBackgroundColor(mColor);
                btnDapAnA.setEnabled(false);
                btnDapAnC.setEnabled(false);
                btnDapAnD.setEnabled(false);
            }
        });
        btnDapAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDapAnC.setBackgroundColor(mColor);
                btnDapAnA.setEnabled(false);
                btnDapAnB.setEnabled(false);
                btnDapAnD.setEnabled(false);
            }
        });
        btnDapAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDapAnD.setBackgroundColor(mColor);
                btnDapAnA.setEnabled(false);
                btnDapAnB.setEnabled(false);
                btnDapAnC.setEnabled(false);
            }
        });*/


        // Gọi hàm trợ giúp từ  người thân




        btnDapAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btnDapAnA,phuongana,mDapAnA);
            }
        });
        btnDapAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btnDapAnB,phuonganb,mDapAnB);
            }
        });
        btnDapAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btnDapAnC,phuonganc,mDapAnC);
            }
        });
        btnDapAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btnDapAnD,phuongand,mDapAnD);

            }
        });
    }
    public void DiemNguoc(){
        final TextView DemNguoc= findViewById(R.id.textView_dem_nguoc);
        countDownTimer =new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                DemNguoc.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                Toast.makeText(ChoiGameActivity.this,"Hết thời gian",Toast.LENGTH_SHORT).show();
                btnTiepTheo.callOnClick();

            }


        };
        countDownTimer.start();
    }
    public void moCauHoi1(View view) {
        DiemNguoc();
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        tongsocau=socau+1;
        soCau.setText(tongsocau+"");
        socau=tongsocau;

        id_linhvuc=Listid[0];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi2(View view) {
        DiemNguoc();
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        tongsocau=socau+1;
        soCau.setText(tongsocau+"");
        socau=tongsocau;
        id_linhvuc=Listid[1];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi3(View view) {
        DiemNguoc();
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        tongsocau=socau+1;
        soCau.setText(tongsocau+"");
        socau=tongsocau;
        id_linhvuc=Listid[2];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }

    public void moCauHoi4(View view) {
        DiemNguoc();
        incCauHoi.setVisibility(View.VISIBLE);
        incLinhVuc.setVisibility(View.INVISIBLE);
        tongsocau=socau+1;
        soCau.setText(tongsocau+"");
        socau=tongsocau;
        id_linhvuc=Listid[3];
        if(getSupportLoaderManager().getLoader(1)!=null){
            getSupportLoaderManager().initLoader(1,null,this);
        }
        getSupportLoaderManager().restartLoader(1,null,this);
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(id==0)
        {
            return new LinhVucLoader(this);
        }
        else
        {
            return new CauHoiLoader(this,id_linhvuc);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(loader.getId()==0) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemsArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < itemsArray.length(); i++) {
                    Listlinhvuc[i] = itemsArray.getJSONObject(i).getString("ten_linh_vuc");
                    Listid[i] = itemsArray.getJSONObject(i).getInt("id");
                }
                String lv1 = Listlinhvuc[0];
                String lv2 = Listlinhvuc[1];
                String lv3 = Listlinhvuc[2];
                String lv4 = Listlinhvuc[3];
                linhvuc1.setText(lv1);
                linhvuc2.setText(lv2);
                linhvuc3.setText(lv3);
                linhvuc4.setText(lv4);




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(loader.getId()==1)
        {
            try {
                JSONObject jsonObject=new JSONObject(data);
                JSONArray itemsArray=jsonObject.getJSONArray("data");

                for(int i=0;i<itemsArray.length();i++){

                    String noiDung=itemsArray.getJSONObject(i).getString("noi_dung");
                    mNoiDung.setText(noiDung);
                    String dapAnA=itemsArray.getJSONObject(i).getString("phuong_an_a");
                    //kqa=dapAnA;
                    kqa=dapAnA;
                    mDapAnA.setText("A."+dapAnA);
                    String dapAnB=itemsArray.getJSONObject(i).getString("phuong_an_b");
                    //kqb=dapAnB;
                    kqb=dapAnB;
                    mDapAnB.setText("B."+dapAnB);
                    String dapAnC=itemsArray.getJSONObject(i).getString("phuong_an_c");
                    //kqc=dapAnC;
                    kqc=dapAnC;
                    mDapAnC.setText("C."+dapAnC);
                    String dapAnD=itemsArray.getJSONObject(i).getString("phuong_an_d");
                    //kqd=dapAnD;
                    kqd=dapAnD;
                    mDapAnD.setText("D."+dapAnD);
                    String DapAn = itemsArray.getJSONObject(i).getString("dap_an");
                    dapan=DapAn.trim();

                    if(kqa.equalsIgnoreCase(dapan))
                    {
                        Random random = new Random();
                        ada = random.nextInt(100);
                        bda = random.nextInt(100-ada);
                        cda = random.nextInt(100-ada-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if(kqb.equalsIgnoreCase(dapan))
                    {
                        Random random = new Random();
                        bda = random.nextInt(100);
                        ada = random.nextInt(100-bda);
                        cda = random.nextInt(100-bda-ada);
                        dda = 100-ada-bda-cda;
                    }
                    if(kqc.equalsIgnoreCase(dapan))
                    {
                        Random random = new Random();
                        cda = random.nextInt(100);
                        bda = random.nextInt(100-cda);
                        ada = random.nextInt(100-cda-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if (kqd.equalsIgnoreCase(dapan))
                    {
                        Random random = new Random();
                        dda =  random.nextInt(100);
                        bda = random.nextInt(100-dda);
                        cda = random.nextInt(100-dda-bda);
                        ada = 100-dda-bda-cda;
                    }
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void click5050(View view){
        imgButton5050.setClickable(false);
        imgButton5050.setBackgroundColor(Color.GRAY);
        if(dapan.equals("A"))
        {
            dapandung = 0;
        }
        if(dapan.equals("B"))
        {
            dapandung = 1;
        }
        if(dapan.equals("C"))
        {
            dapandung = 2;
        }
        if(dapan.equals("D"))
        {
            dapandung = 3;
        }
        int dem =0;
        for (int i =0; i <4;i++){
            if(dapandung!=i){
                Listbutton[i].setText("");
                Listbutton[i].setClickable(false);
                dem++;
            }
            if(dem==2){
                return;
            }
        }

    }

    public void toTuVan(View view) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.bieudo);
        dialog.setCanceledOnTouchOutside(false);//->Click vào bên ngoài thì đóng dialog
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        toTuVan(dialog);
        dialog.show();
    }
    public void toTuVan(Dialog dialog){
        BarChart barChart =dialog.findViewById(R.id.barChart);

        ArrayList<BarEntry> datas = new ArrayList<>();
        datas.add(new BarEntry(0, ada));
        datas.add(new BarEntry(1, bda));
        datas.add(new BarEntry(2, cda));
        datas.add(new BarEntry(3, dda));

        BarDataSet barDataSet = new BarDataSet(datas, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(20f);


        BarData barData = new BarData( barDataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] labels=new String[]{"A","B","C","D"};
        IndexAxisValueFormatter formatter=new IndexAxisValueFormatter(labels);
        xAxis.setTextSize(18f);
        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(formatter);
        //gán dữ liệu vào barChart
        barChart.setData(barData);

        //Khong ve luoi tren bieu do
        xAxis.setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        //Zoom bieu do
        barChart.setDoubleTapToZoomEnabled(false);
        //Touch biêu đồ
        barChart.setTouchEnabled(false);

        barChart.animateY(3000);

        barChart.invalidate();

    }

    public void tiepTheo(View view) {
        countDownTimer.cancel();
        incCauHoi.setVisibility(View.INVISIBLE);
        incLinhVuc.setVisibility(View.VISIBLE);

        btnDapAnA.setBackgroundColor(mdefault);
        btnDapAnB.setBackgroundColor(mdefault);
        btnDapAnC.setBackgroundColor(mdefault);
        btnDapAnD.setBackgroundColor(mdefault);

        btnDapAnA.setEnabled(true);
        btnDapAnB.setEnabled(true);
        btnDapAnC.setEnabled(true);
        btnDapAnD.setEnabled(true);

    }



    public void Click_traloi(Button btnPhuongAn, String phuongan, TextView mTextView){
        phuongan = mTextView.getText().toString().trim();


        if(mTextView.getText().toString().substring(2).trim().equals(dapan)){
            tongsodiem=diem+10;
            btnPhuongAn.setBackgroundColor(Color.BLUE);
            diem = tongsodiem;
            textView_Diem.setText(tongsodiem+"");
        }
        else
        {
            btnPhuongAn.setBackgroundColor(Color.YELLOW);
            Toast.makeText(ChoiGameActivity.this, dapan, Toast.LENGTH_SHORT).show();
            if(id_imView_Traitim == 4){
                Intent intent = new Intent(ChoiGameActivity.this,ManHinhChinh.class);
                Toast.makeText(ChoiGameActivity.this, "Tro choi ket thuc!!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else
            {
                xoaTraiTim(id_imView_Traitim);
                id_imView_Traitim++;
                Toast.makeText(ChoiGameActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Hàm mua đáp án
    public void muaDapAn(View view) {
        ImageView img_muaDapAn = findViewById(R.id.imageButton_qua_cau_hoi);
        int soCredit = Integer.parseInt(tvTongSoCredit.getText().toString());
        if (soCredit > 0) {

            Listbutton[id_dapan_dung].callOnClick();
            soCredit = soCredit-100;
            String chuoi = soCredit + "";
            tvTongSoCredit.setText(chuoi);

        }
        else
        {
            Toast.makeText(ChoiGameActivity.this, "Số dư Credit của bạn không đủ.", Toast.LENGTH_SHORT).show();

            img_muaDapAn.setBackgroundColor(Color.GRAY);
            img_muaDapAn.setClickable(false);
        }

    }

    // Hàm xóa mạng người chơi
    public void xoaTraiTim(int id){
        listTraiTim[id].setImageResource(R.drawable.heartdie);
        listTraiTimxoa[id]=id;
    }

    public void XuLiThoat(View view) {
        Intent intent = new Intent(this,ManHinhChinh.class);
        intent.putExtra("credit",tv_tongCredit.getText().toString());
        startActivity(intent);
    }

    public void muadapan(View view) {
        int soCredit = Integer.parseInt(tv_tongCredit.getText().toString());
        if (soCredit > 0) {
            diemtraitim++;
            if(diemtraitim >=5)
            {
                diemtraitim=1;
            }
            else
            {
                if(diemtraitim==1 && diemtraitim<=listTraiTimxoa.length && id_imView_Traitim>0){
                    listTraiTim[listTraiTimxoa[0]].setImageResource(R.drawable.heart);
                    id_imView_Traitim--;
                    soCredit = soCredit-100;
                    String chuoi = soCredit + "";
                    tv_tongCredit.setText(chuoi);
                }
                if(diemtraitim==2 && diemtraitim<=listTraiTimxoa.length && id_imView_Traitim>0){
                    listTraiTim[listTraiTimxoa[1]].setImageResource(R.drawable.heart);
                    id_imView_Traitim--;
                    soCredit = soCredit-100;
                    String chuoi = soCredit + "";
                    tv_tongCredit.setText(chuoi);
                }
                if(diemtraitim==3 && diemtraitim<=listTraiTimxoa.length && id_imView_Traitim>0){
                    listTraiTim[listTraiTimxoa[2]].setImageResource(R.drawable.heart);
                    id_imView_Traitim--;
                    soCredit = soCredit-100;
                    String chuoi = soCredit + "";
                    tv_tongCredit.setText(chuoi);
                }
                if(diemtraitim==4 && diemtraitim<=listTraiTimxoa.length && id_imView_Traitim>0){
                    listTraiTim[listTraiTimxoa[3]].setImageResource(R.drawable.heart);
                    id_imView_Traitim--;
                    soCredit = soCredit-100;
                    String chuoi = soCredit + "";
                    tv_tongCredit.setText(chuoi);
                }
                if(diemtraitim==5 && diemtraitim<=listTraiTimxoa.length && id_imView_Traitim>0){
                    listTraiTim[listTraiTimxoa[4]].setImageResource(R.drawable.heart);
                    id_imView_Traitim--;
                    soCredit = soCredit-100;
                    String chuoi = soCredit + "";
                    tv_tongCredit.setText(chuoi);
                }
            }


        }
        else
        {
            Toast.makeText(ChoiGameActivity.this, "Số dư Credit của bạn không đủ.", Toast.LENGTH_SHORT).show();

        }


    }

    public void quacauhoi(View view) {

        countDownTimer.cancel();
        incCauHoi.setVisibility(View.INVISIBLE);
        incLinhVuc.setVisibility(View.VISIBLE);

        btnDapAnA.setBackgroundColor(mdefault);
        btnDapAnB.setBackgroundColor(mdefault);
        btnDapAnC.setBackgroundColor(mdefault);
        btnDapAnD.setBackgroundColor(mdefault);

        btnDapAnA.setEnabled(true);
        btnDapAnB.setEnabled(true);
        btnDapAnC.setEnabled(true);
        btnDapAnD.setEnabled(true);
        quacauhoi.setClickable(false);
        quacauhoi.setBackgroundColor(Color.GRAY);
    }
}
