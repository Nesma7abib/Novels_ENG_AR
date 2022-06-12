package com.example.novels_eng_ar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdabterForStory extends BaseAdapter {

    ArrayList arrayList;
    int image[];
    Activity activity;
    DB_Sqlite_Favorite db_fav;

    public AdabterForStory(ArrayList arrayList, int[] image, Activity activity) {
        this.arrayList = arrayList;
        this.image = image;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        view = LayoutInflater.from(activity).inflate(R.layout.row, null, false);
        db_fav = new DB_Sqlite_Favorite(activity);

        TextView textView = view.findViewById(R.id.textView_itme);
        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(image[position]);
        textView.setText(arrayList.get(position) + "");
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //  int pageNum = (int) parent.getItemIdAtPosition(i);
boolean x = false ;
                db_fav.get_All_Favorite();
                //  int check = db_fav.get_check_List_Favorite();
                // if (check > 0) {
                // Toast.makeText(MainActivity.this, "عفوا العنوان موجود في المفضلة", Toast.LENGTH_SHORT).show();
                //  } else {
                if(x == true){
                    Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
                }
                
                //     Toast.makeText(MainActivity.this, "تم الاضافة في المفضلة", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        return view;
    }
}
