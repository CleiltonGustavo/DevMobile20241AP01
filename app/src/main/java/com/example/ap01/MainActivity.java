package com.example.ap01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ap01.dao.TarefaDao;
import com.example.ap01.database.AppDataBase;
import com.example.ap01.models.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button btn_CriarNova;

    private List<Tarefa> tarefasList;

    ItemAdapter itensAdapter;
    String valorTitulo, valorDesc;

    private AppDataBase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_CriarNova = findViewById(R.id.btn_criarNova);

        recyclerView = findViewById(R.id.rv_itens);

        tarefasList = new ArrayList<>();



        Tarefa supermercado = new Tarefa("Supermercado", "Comprar leite e ovos");

        Intent dado_enviado = getIntent();
        valorTitulo = dado_enviado.getStringExtra("valorPassadoTitulo");
        valorDesc = dado_enviado.getStringExtra("valorPassadoDesc");

        Tarefa ta = new Tarefa(valorTitulo, valorDesc);



        appDatabase = Room.databaseBuilder(this, AppDataBase.class, "db_tarefas")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        TarefaDao tarefaDao = appDatabase.tarefaDao();
        tarefaDao.insertAll(supermercado); //teste com inseção manual

        List<Tarefa> tarefasDoBd = tarefaDao.getAllTarefas();
        for(Tarefa t : tarefasDoBd){
            Log.d("sid-tag", t.toString());
        }
        tarefaDao.insertAll(ta);
        tarefasList.add(ta);
        tarefasList = tarefasDoBd;




        itensAdapter = new ItemAdapter(tarefasList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itensAdapter);







        btn_CriarNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chamaCadastrarTarefa = new Intent(MainActivity.this, CadastrarTarefa.class);
                startActivity(chamaCadastrarTarefa);
            }
        });
    }
}