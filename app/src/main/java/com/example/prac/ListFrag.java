package com.example.prac;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private String mParam3;

    public ListFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListFrag newInstance(int param1, int param2, String param3) {
        ListFrag fragment = new ListFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);

        }
    }

    cavass mCavass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rect);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mParam1, mParam2);
        relativeLayout.setLayoutParams(params);
        mCavass = new cavass(getContext(), mParam3);
        relativeLayout.addView(mCavass);
        return view;
    }

    MyViewModel mViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        mViewModel.getOptions().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    mCavass.circlecrop();
                } else if (integer == 2) {
                    mCavass.rectcrop();
                } else if (integer == 3) {
                    mCavass.tricrop();
                } else if (integer == 4) {
                    mCavass.freecrop();
                }

            }
        });
        mViewModel.getrotate().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mCavass.setRotate(integer);
            }
        });
        mViewModel.getstatus().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    bitmap = mCavass.getCroppedBitmap();
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog);
                    dialog.show();
                    ImageView img = dialog.findViewById(R.id.image);
                    final Button share,save;
                    share=dialog.findViewById(R.id.share);
                    save=dialog.findViewById(R.id.save);
                    share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            shareImage();
                        }
                    });
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveImage();
                        }
                    });
                    img.setImageBitmap(bitmap);
                }
            }
        });
        mViewModel.getSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mCavass.setRadius(integer);
            }
        });
        mViewModel.gettext().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mCavass.putString(s);
            }
        });
        mViewModel.getcolor().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mCavass.c=integer;
            }
        });
    }

    String fileUri=null;
    Bitmap bitmap;
    public void saveImage() {

        try {
            File mydir = new File(Environment.getExternalStorageDirectory() + "/PhotoEditor");
            if (!mydir.exists()) {
                mydir.mkdirs();
            }

            fileUri = mydir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
            FileOutputStream outputStream = new FileOutputStream(fileUri);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shareImage()
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);
        OutputStream outstream;
        try {
            outstream =  getActivity().getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));
    }
}


