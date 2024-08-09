package com.example.ap01.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ap01.models.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {
    @Insert
    void insertAll(Tarefa... tarefas);
    @Delete
    void delete(Tarefa tarefa);
    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAllTarefas();

}
