package com.example.nterlien;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


public class Fragment_Nbox extends Fragment {
    String video_url = "https://firebasestorage.googleapis.com/v0/b/nterlien-3ec13.appspot.com/o/Racing%20Bike%20-%207251%20(1).mp4?alt=media&token=5d214a28-1ec5-4539-9f7c-8ab0fe4b634d";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_nbox, container, false);
        return rootView;
    }
}