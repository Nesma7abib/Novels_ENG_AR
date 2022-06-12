package com.example.novels_eng_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listIndex;
    ArrayList<List_itme_Index> List_Favorite;
    TextView textView_Title;
    ImageView imageView;
    ArrayAdapter<String> arrayAdapter;
    DB_Sqlite_Favorite db_fav;
    String List_Type;
    String Title;
    int pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listIndex = new ArrayList<String>();
        List_Favorite = new ArrayList<List_itme_Index>();
        db_fav = new DB_Sqlite_Favorite(this);

        listView = (ListView) findViewById(R.id.listView_main);
        listView.deferNotifyDataSetChanged();
        textView_Title = (TextView) findViewById(R.id.textView_Title);
        imageView = (ImageView) findViewById(R.id.imageView1);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font.otf");
        textView_Title.setTypeface(typeface);

        list_index();
    }

    public void list_index() {
        listIndex.clear();
        List_Type = "Index";
        textView_Title.setText(getString(R.string.app_name));
        try {
            InputStream input = getAssets().open("index.txt");
            InputStreamReader rd = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(rd);
            String line;
            while ((line = br.readLine()) != null) {
                listIndex.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.textView_itme, listIndex);
         listView.setAdapter(arrayAdapter);
        int img[] = {R.drawable.pok1, R.drawable.pok2, R.drawable.pok3, R.drawable.pok4, R.drawable.pok5, R.drawable.pok6, R.drawable.pok7, R.drawable.pok8
                , R.drawable.pok9, R.drawable.pok10, R.drawable.pok11, R.drawable.pok12, R.drawable.pok13, R.drawable.pok14, R.drawable.pok15
                , R.drawable.pok15, R.drawable.pok17, R.drawable.pok18, R.drawable.pok19};
        listView.setAdapter(new AdabterForStory(listIndex, img, MainActivity.this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView Title = (TextView) view.findViewById(R.id.textView_itme);
                Toast.makeText(MainActivity.this, Title.getText(), Toast.LENGTH_SHORT).show();
                int PageNum = 0;
                if (List_Type.equals("Index")) {
                    PageNum = position;
                } else if (List_Type.equals("Favorite")) {
                    PageNum = List_Favorite.get(position).getPage_id();
                }

                Intent intent = new Intent(MainActivity.this, WebNovels.class);
                intent.putExtra("pageNum", PageNum);
                intent.putExtra("Title", Title.getText());
                startActivity(intent);

            }
        });


    }

    public void btn_favorite(View view) {
        List_Type = "Favorite";
        textView_Title.setText(getString(R.string.favorite));
        List_Favorite = db_fav.get_All_Favorite();
        if (List_Favorite.size() == 0) {
            Toast.makeText(MainActivity.this, R.string.favorite_is_Empity, Toast.LENGTH_SHORT).show();
            list_index();
            textView_Title.setText(getString(R.string.app_name));
        } else {
            listIndex.clear();
            for (int i = 0; i < List_Favorite.size(); i++) {
                listIndex.add(List_Favorite.get(i).getMain_Title());
            }

             arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.textView_itme, listIndex);
              listView.setAdapter(arrayAdapter);
          //  int img[] = {R.drawable.splash, R.drawable.help, R.drawable.close};
            int img[] = {R.drawable.pok1, R.drawable.pok2, R.drawable.pok3, R.drawable.pok4, R.drawable.pok5, R.drawable.pok6, R.drawable.pok7, R.drawable.pok8
                    , R.drawable.pok9, R.drawable.pok10, R.drawable.pok11, R.drawable.pok12, R.drawable.pok13, R.drawable.pok14, R.drawable.pok15
                    , R.drawable.pok15, R.drawable.pok17, R.drawable.pok18, R.drawable.pok19};
            listView.setAdapter(new AdabterForStory(listIndex, img, MainActivity.this));

        }
    }

    public void btn_book(View view) {
        list_index();
    }

    public void btn_Close(View view) {
        finish();
    }
}
