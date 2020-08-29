package com.example.prac;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToolTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static ToolTwo newInstance(String param1, String param2) {
        ToolTwo fragment = new ToolTwo();
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
ImageButton rotate;
    Button done;
    MyViewModel mViewModel;
    int r=90;
    SearchView mEditText;
    int[] colors;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tool_two, container, false);
        done=view.findViewById(R.id.done);
        rotate=view.findViewById(R.id.rotate);
        mEditText=view.findViewById(R.id.text);
        Spinner spinner=view.findViewById(R.id.spinner);
        colors = getActivity().getResources().getIntArray(R.array.colors);
        ArrayList<Integer> col=new ArrayList<>();
        for(int i=0;i<colors.length;++i)
        {
            col.add(i);
        }
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(getActivity()
        ,R.layout.support_simple_spinner_dropdown_item,col);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.putcolor(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.putrotate(r);
                r+=90;
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.putstatus(1);
            }
        });
        mEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.puttext(newText);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);
    }
}
