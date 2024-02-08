package com.example.steamachine.ui.home;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;
    public View onCreateView;
    private ViewModel mViewModel;
     public TextView tempControl;
    public TextView tempControl2;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_home, container, false);
        Componentes(root);
        firebase(root);
        VideoView videoView = (VideoView) root.findViewById(R.id.videoView);
     reproducir(videoView);

        return  root;
    }

               public void firebase(View root){
                   Timer timer=new Timer ();

                   TimerTask task=new TimerTask () {

                       public void run () {


                   Query query=databaseReference.child("Temperatura");
                   final ValueEventListener valueEventListener = query.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           int cont = 0;
                           String s="";
                           for (DataSnapshot objSnapshot : snapshot.getChildren()) {
                               cont++;
                           }
                           if (cont != 0) {
                               for (DataSnapshot objSnapshot : snapshot.getChildren()) {
                                 //  Temperatura t = objSnapshot.getValue(Temperatura.class);
                                s+= (objSnapshot.getValue().toString()+" \n");
                               }
                               String[] parts = s.split("\n");
                               tempControl.setText(parts[1] +"º C\n"  );

                               tempControl2.setText(parts[0]+"º F\n" );

                               int numv = Math.round(Float.parseFloat(parts[1]));
                               int numv1 = Math.round(Float.parseFloat(parts[0]));
                               progressBar.setProgress(numv);
                               progressBar2.setProgress(numv1);

                               if (Float.parseFloat(parts[1])>40.0) {
                                   VideoView videoView = (VideoView) root.findViewById(R.id.videoView);
                                   reproducir2(videoView);
                               }
                               else{
                                   VideoView videoView = (VideoView) root.findViewById(R.id.videoView);
                                   reproducir(videoView);
                               }

                           }

                           else{ }

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

                       }

                   };
                   timer.schedule (task, 2, 1000);
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

        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.calderacaliente);//

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
        tempControl = root.findViewById(R.id.id_Temperatura);
        tempControl2 = root.findViewById(R.id.id_Temperatura2);
        progressBar = root.findViewById(R.id.progress_bar);
        progressBar2 = root.findViewById(R.id.progress_bar2);
    }
}