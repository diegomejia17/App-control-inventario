package com.example.controlinventario.FacultadJA;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.controlinventario.Commons.GenericDAO;

@Dao
public interface TriggerQueries extends GenericDAO<FacultadEntity> {
   // @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("CREATE TRIGGER prevent_delete_user " +
            "BEFORE DELETE ON FacultadEntity " +
            "BEGIN " +
            "    SELECT CASE WHEN " +
            "        (SELECT COUNT(*) FROM EscuelaEntity WHERE IDFACULTAD = OLD.IDFACULTAD) > 0 " +
            "    THEN " +
            "        RAISE(ABORT, 'El usuario tiene tareas asociadas y no puede ser eliminado.') " +
            "    END; " +
            "END;")
    public abstract void createPreventDeleteFacultadTrigger();
}
