package com.example.prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickInterface {
RecyclerView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=findViewById(R.id.fragments);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        mListView.setLayoutManager(linearLayoutManager);
        display();
    }
    public ArrayList<File> findfile(File file)
    {   ArrayList<File> mStrings=new ArrayList<>();
        File[] files=file.listFiles();
        for(File file1:files)
        {
            if(file1.isDirectory()&&!file1.isHidden())
            {
                mStrings.addAll(findfile(file1));
            }
            else {
                if(file1.getName().endsWith(".png")||file1.getName().endsWith(".jpeg")
                ||file1.getName().endsWith(".jpg"))
                {
                    mStrings.add(file1);
                }
            }
        }
        return mStrings;
    }
    void display()
    {
        ArrayList<File> images=findfile(Environment.getExternalStorageDirectory());
        strings=new String[images.size()];
        String[] st=new String[images.size()];
        ArrayList<RecyclerModel> recyclerModels=new ArrayList<>();
        for(int i=0;i<images.size();++i)
        { RecyclerModel model=new RecyclerModel();
            model.setName(images.get(i).getName());
            model.setUri(images.get(i).getPath());
            strings[i]=images.get(i).getPath();
            st[i]=images.get(i).getName();
            recyclerModels.add(model);
        }
       Adapter adapter=new Adapter(recyclerModels,this);
        mListView.setAdapter(adapter);
    }
    String[] strings;

    @Override
    public void click(int position) {

    }
}
