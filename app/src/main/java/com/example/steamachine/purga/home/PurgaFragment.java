package com.example.steamachine.purga.home;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.steamachine.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class PurgaFragment extends Fragment   implements View.OnClickListener  {


    private PurgaViewModel purgaViewModel;
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;
    public View onCreateView;
    private ViewModel mViewModel;
     public TextView tempControl;
    public TextView tempControl2;
    ImageButton ac,des;
    public static PurgaFragment newInstance() {
        return new PurgaFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_purga, container, false);
        Componentes(root);

        VideoView videoView = (VideoView) root.findViewById(R.id.videoView);
     reproducir2(videoView);

        return  root;
    }

               public void firebase(){

                   databaseReference.child("SistemaPurga/").child("button").setValue(0);


               }


    public void firebaseborrar(){

        databaseReference.child("SistemaPurga/").child("button").removeValue();


    }


                       private void iniciaFirebase(View root){
        FirebaseApp.initializeApp(root.getContext());
        firebaseDataBase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDataBase.getReference();

    }


    public void reproducir( VideoView videoView){

        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.calderanormald);//

        videoView.start();
        videoView.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.start();


    }

    public void reproducir2( VideoView videoView){

        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.calderapurga);//

        videoView.start();
        videoView.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.start();


    }


    public void Componentes( View root){
        iniciaFirebase(root);

        ac= root.findViewById(R.id.btnactivar);

        des= root.findViewById(R.id.btndesactivar);


        ac.setOnClickListener(this);

        des.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnactivar:

                firebase();
                break;

            case R.id.btndesactivar:
                firebaseborrar();
                break;

        }
    }


}