package com.example.machado.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
//import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    protected Toolbar toolbar;
    protected FrameLayout layout;

    protected Button login;
    protected Button registar;
    protected EditText username;
    protected EditText password;
    protected AdaptadorBaseDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

//

        bd = new AdaptadorBaseDados(this).open();

        login = findViewById(R.id.login);
        registar = findViewById(R.id.registrar);
        username = findViewById(R.id.nome);
        password = findViewById(R.id.password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registar(username.getText().toString(), password.getText().toString());
            }
        });



    }

    private void registar(String user, String pass) {
        boolean exists = bd.checkUsernameExists(user);
        if(exists){
            Toast.makeText(this, "Utilizador já existe", Toast.LENGTH_LONG).show(); //ja existe
        }
        else{
            bd.inserirUser(user, pass);
            Toast.makeText(this, "Registo efectuado", Toast.LENGTH_LONG).show();
            login(user, pass);

        }
    }

    private void login(String user, String pass) {
        boolean exists = bd.checkUsernameExists(user);
        if(!exists){
            Toast.makeText(this, "Utilizador não existe", Toast.LENGTH_LONG).show(); //user nao existe
        }
        else{
            if(bd.getPasswordFromUser(user).equals(pass)){

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Login Efectuado!");
                alertDialog.setMessage("Bem Vindo! " + user );
                alertDialog.show();

                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                finish();

            }
            else{
                Toast.makeText(this, "Password errada", Toast.LENGTH_LONG).show();   //password errada
            }
        }
    }

}
