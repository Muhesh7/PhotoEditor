package com.example.prac;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFrag newInstance(String param1, String param2) {
        ListFrag fragment = new ListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
private ListView mRecyclerView;
    ArrayList<File> mStrings=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);
      display();

        return view;
    }

    public ArrayList<File> findfile(File file)
    {   File[] files=file.listFiles();
        for(File file1:files)
        {
            if(file1.isDirectory()&&!file1.isHidden())
            { mStrings.addAll(findfile(file1));
                Log.d("SSS",file1.getPath());
            }
            else {
                if(file1.getName().endsWith(".mp3")||file1.getName().endsWith(".wav"))
                {  Log.d("SSS",file1.getPath());
                    mStrings.add(file1);
                }
            }
        }
        return mStrings;
    }
    void display()
    {
        ArrayList<File> songs=findfile(getContext().getFilesDir());
         String[] strings=new String[songs.size()];
         for(int i=0;i<songs.size();++i)
         {
             strings[i]=songs.get(i).getPath();
         }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,strings);
         mRecyclerView.setAdapter(arrayAdapter);
    }
}
