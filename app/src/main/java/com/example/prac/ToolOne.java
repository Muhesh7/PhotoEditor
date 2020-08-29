package com.example.prac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolOne extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToolOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolOne.
     */
    // TODO: Rename and change types and number of parameters
    public static ToolOne newInstance(String param1, String param2) {
        ToolOne fragment = new ToolOne();
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
ImageButton circle,rect,free,tri;
    ImageButton plus,minus;
    MyViewModel mMyViewModel;
    int size=50;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tool_one, container, false);
       circle=view.findViewById(R.id.circle);
        rect=view.findViewById(R.id.rectangle);
        free=view.findViewById(R.id.free);
        tri=view.findViewById(R.id.tri);
        plus=view.findViewById(R.id.plus);
        minus=view.findViewById(R.id.minus);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=50;
                mMyViewModel.putOptions(1);
            }
        });
        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=50;
                mMyViewModel.putOptions(2);
            }
        });tri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=50;
                mMyViewModel.putOptions(3);
            }
        });
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size=50;
                mMyViewModel.putOptions(4);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyViewModel.putSize(size);
                size+=10;
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size-=10;
                mMyViewModel.putSize(size);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMyViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);

    }
}
