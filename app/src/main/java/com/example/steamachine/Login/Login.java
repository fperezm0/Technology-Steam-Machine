package com.example.steamachine.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steamachine.MainActivity;
import com.example.steamachine.R;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton btnSalir;
        ImageButton btnEntrar;


        btnSalir=findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this,"Hasta Pronto",Toast.LENGTH_LONG).show();
                onStop();
                finish();
            }
        });

        btnEntrar= findViewById(R.id.btnIngresar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tUsuario,tContra;
                tUsuario=findViewById(R.id.txtUsuario);
                tContra=findViewById(R.id.txtPaswsword);

                if(tUsuario.getText().toString().equals("Mercedes") && tContra.getText().toString().equals("AMG"))
                {
                    Intent intent=new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(Login.this,"Usuario y/o contraseña incorrecta",Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}