package com.example.controlinventario.Commons;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface GenericDAO <T>{
    @Insert
    void insert(T t);

    @Delete
    public void delete(T t);

    @Update
    public void update(T t);



}
