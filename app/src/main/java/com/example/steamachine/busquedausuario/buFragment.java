package com.example.steamachine.busquedausuario;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.steamachine.R;
import com.example.steamachine.bdfirebase.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link buFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class buFragment extends Fragment
        implements View.OnClickListener {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String nombreusu="No logueado";
    TextView usernm;
    ImageButton bottonBusca;
    ImageButton adm,usun;
    User u;
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference,databaseReference2;

    public EditText busqueda;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public buFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment busquedausuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static buFragment newInstance(String param1, String param2) {
        buFragment fragment = new buFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_busquedausuario, container, false);

        iniciaFirebase(root);
        Componentes(root);


        ImageButton busca = root.findViewById(R.id.btnBuscausu);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( busqueda.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Ingrese email de la persona", Toast.LENGTH_LONG).show();

                } else {


                    databaseReference=FirebaseDatabase.getInstance().getReference("User");
                    databaseReference.addChildEventListener(new ChildEventListener() {

                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            User aux= snapshot.getValue(User.class);

                            String nom = aux.getCorreo();
                            String nom2= busqueda.getText().toString();
                            if(nom.equals(nom2)){
                                u=aux;

                                usernm.setText("Usuario seleccionado: "+u.getCorreo());
                                Toast.makeText(getContext(), "Usuario encontrado"+aux.getCorreo(), Toast.LENGTH_LONG).show();
                            }




                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        }


                    });



                }




            }
        });

        return root;
}
    private void iniciaFirebase(View root) {
        FirebaseApp.initializeApp(root.getContext());
        firebaseDataBase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDataBase.getReference();
        databaseReference2 = firebaseDataBase.getReference();


    }
    private void iniciaFirebase() {

        firebaseDataBase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDataBase.getReference();
        databaseReference2 = firebaseDataBase.getReference();


    }
    private void clean(){

        usernm.setText("");
        busqueda.setText("");
    }

    private void Componentes(View root) {

        EditTextComponent(root);
   adm= root.findViewById(R.id.btnadmin);

   usun= root.findViewById(R.id.btnusuarionormal);


   adm.setOnClickListener(this);

   usun.setOnClickListener(this);


    }

    private void EditTextComponent(View root) {
usernm = root.findViewById(R.id.usuarionom);
busqueda = root.findViewById(R.id.IDbusqueda);


    }



    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnadmin:
                if (u!=null){
                    databaseReference2.child("User/"+u.getName()).child("status").setValue(1);
                Toast.makeText(getContext(), u.getCorreo() + " Ahora puede supervisar la caldera", Toast.LENGTH_LONG).show();
                clean();}

                else{
                    Toast.makeText(getContext(), "Busque Usuario por favor", Toast.LENGTH_LONG).show();


                }
                break;

            case R.id.btnusuarionormal:
                if (u!=null){
                databaseReference2.child("User/"+u.getName()).child("status").setValue(0);
                                Toast.makeText(getContext(), u.getCorreo()+"  el  Usuario  ahora no tiene privilegios ", Toast.LENGTH_LONG).show();
                clean();}
                 else{
                    iniciaFirebase();
                    Toast.makeText(getContext(), "Busque Usuario por favor", Toast.LENGTH_LONG).show();
            }
                break;

        }
    }






}

