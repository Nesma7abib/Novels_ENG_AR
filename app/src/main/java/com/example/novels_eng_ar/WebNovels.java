package com.example.novels_eng_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.MobileAds;

public class WebNovels extends AppCompatActivity {
    int pageNum;
    PDFView pdfView;
    String Title;
    DB_Sqlite_Favorite db_fav = new DB_Sqlite_Favorite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_novels);

        MobileAds.initialize(this, "ca-app-pub-7606101407223656~3379270755");

        Intent data = getIntent();
        pageNum = data.getExtras().getInt("pageNum");
        Title= data.getExtras().getString("Title");

        pdfView = (PDFView) findViewById(R.id.PDFView1);
       // pdfView.getSettings().setBuiltInZoomControls(true);
        try {
            pdfView.fromAsset("html/page"+pageNum+".pdf").load();//
        }catch(Exception e){
            e.getMessage();
            Log.e("catch for pdf ",e.getMessage());
        }
    }

    public void btn_favorite(View view) {
        int check = db_fav.get_check_List_Favorite(Title);
        if (check>0){
            Toast.makeText(WebNovels.this, "عفوا العنوان موجود في المفضلة", Toast.LENGTH_SHORT).show();
        }else{
            db_fav.Insert_to_favorite(Title,pageNum);
            Toast.makeText(WebNovels.this,"تم الاضافة في المفضلة", Toast.LENGTH_SHORT).show();
        }

    }
    public void btn_Home(View view) {
        finish();

    }
}
