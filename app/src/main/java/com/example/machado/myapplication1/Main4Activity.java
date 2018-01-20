package com.example.machado.myapplication1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    ListView list;
    String url = "http://www.maisfutebol.iol.pt/fc-porto/plantel/1574";
    List listaEquipas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        list = findViewById(R.id.list);
        listaEquipas = new ArrayList();
        ListaEquipas();
    }
    @SuppressLint("StaticFieldLeak")
    public void ListaEquipas() {

        new AsyncTask<String, Void, Document>() {

            @Override
            protected Document doInBackground(String... s) {
                Document fulldoc = null;
                try {
                    fulldoc = Jsoup.connect(s[0]).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fulldoc;
            }

            @Override
            protected void onPostExecute(Document fulldoc) {
                actualizarListaEquipasCont(fulldoc);
            }
        }.execute(url);
    }

    private void actualizarListaEquipasCont(Document fulldoc) {
        List<String> listaEquipasTemp = new ArrayList<>();
        Elements els = fulldoc.select(".tdnumber,.numeroPlayer, .tdNomeJogador");
        for(int k = 0; k < els.size() - 1; ++k){
//.tdnumber,.numeroPlayer, .tdNomeJogador
            listaEquipasTemp.add(els.get(k).text());
        }
        listaEquipas.addAll(listaEquipasTemp);
        setListAdap();

    }
    protected void setListAdap(){
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaEquipas);
        list.setAdapter(adap);


    }
}