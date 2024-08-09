package com.example.ap01.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ap01.dao.TarefaDao;
import com.example.ap01.models.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract TarefaDao tarefaDao();
}
