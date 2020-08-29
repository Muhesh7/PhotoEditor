package com.example.prac;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prac.databinding.VideolayoutBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    private ArrayList<RecyclerModel> mList;
    private ClickInterface mClickInterface;

    public Adapter(ArrayList<RecyclerModel> list, ClickInterface clickInterface) {
        mList = list;
        mClickInterface = clickInterface;
    }

    @NonNull
    @Override
    public Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        VideolayoutBinding videolayoutBinding= DataBindingUtil.inflate(layoutInflater,R.layout.videolayout,parent,false);
        return new MyHolder(videolayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyHolder holder, int position) {
             holder.mBinding.setModel(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        VideolayoutBinding mBinding;
        public MyHolder(@NonNull VideolayoutBinding itemView) {
            super(itemView.getRoot());
            mBinding=itemView;
            mBinding.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickInterface.click(getAdapterPosition());
                }
            });
        }
    }
}
