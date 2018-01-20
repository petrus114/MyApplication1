package com.example.machado.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {
    ListView list;
    List<String> listaNorte;
    List<String> listaCentro;
    List<String> listaSul;
    List<String> listaIlhas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        list = findViewById(R.id.list);
        final Spinner spinner = findViewById(R.id.spinner);


        listaNorte = new ArrayList<>();
        listaNorte.add("Futebol CLube do Porto");
        listaNorte.add("Boavista Futebol Clube");
        listaNorte.add("Sporting Clube de Braga");
        listaNorte.add("Vitória Sport Clube");

        listaCentro = new ArrayList<>();
        listaCentro.add("Clube de Futebol Os Belenenses ");
        listaCentro.add("Clube Desportivo Feirense");

        listaSul = new ArrayList<>();
        listaSul.add("Portimonense Sporting Clube");


        listaIlhas = new ArrayList<>();
        listaIlhas.add("Club Sport Maritimo");


        List<String> spList = new ArrayList<>();
        spList.add("Selecionar...");
        spList.add("Norte");
        spList.add("Centro");
        spList.add("Sul");
        spList.add("Ilhas");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zona = spinner.getSelectedItem().toString();
                setLista(zona);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                intent.putExtra("url", "http://www.maisfutebol.iol.pt/fc-porto/plantel/1574");
                startActivity(intent);
                finish();
            }
        });

    }

    private void setLista(String zona) {
        List<String> lista = new ArrayList<>();
        switch (zona) {
            case "Norte":
                lista = listaNorte;
                break;
            case "Centro":
                lista = listaCentro;
                break;
            case "Sul":
                lista = listaSul;
                break;
            case "Ilhas":
                lista = listaIlhas;
                break;
            default:
                lista = new ArrayList<>();
                lista.addAll(listaNorte);
                lista.addAll(listaCentro);
                lista.addAll(listaSul);
                break;
        }

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adap);
    }




        }




