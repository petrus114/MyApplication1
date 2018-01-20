package com.example.machado.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    ListView list;
    String url = "http://www.maisfutebol.iol.pt/ultimas";
    List listaEquipas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        list = findViewById(R.id.listNews);
        listaEquipas = new ArrayList();
        ListaEquipas();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
                intent.putExtra("url", "http://www.maisfutebol.iol.pt/ultimas");
                startActivity(intent);
                finish();
            }
        });
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
        Elements els = fulldoc.select(".topNews > li");
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