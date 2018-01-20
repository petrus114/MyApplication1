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
       // implements NavigationView.OnNavigationItemSelectedListener

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

//       createDrawer();
//
//       layout = (FrameLayout) findViewById(R.id.content_frame);
//
//       getLayoutInflater().inflate(R.layout.content_main, layout);

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
                //Toast.makeText(this, "Login efectuado", Toast.LENGTH_LONG).show();//login ok
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Login Efectuado!");
                alertDialog.setMessage("Bem Vindo! " + user );
                alertDialog.show();
                ///passar p activity
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                finish();

            }
            else{
                Toast.makeText(this, "Password errada", Toast.LENGTH_LONG).show();   //password errada
            }
        }
    }

//    public void createDrawer(){
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//         Handle action bar item clicks here. The action bar will
//         automatically handle clicks on the Home/Up button, so long
//         as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//         Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.dashboard) {
//            Intent intent = new Intent(this, Dashboard.class);
//            startActivity(intent);
//            finish();
//        } else if (id == R.id.agenda) {
//            Intent intent = new Intent(this, Agenda.class);
//            startActivity(intent);
//            finish();
//
//        } else if (id == R.id.praias) {
//            Intent intent = new Intent(this, Praias.class);
//            startActivity(intent);
//            finish();
//
//        } else if (id == R.id.tutoriais) {
//            Intent intent = new Intent(this, Tutoriais.class);
//            startActivity(intent);
//            finish();
//        }
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
