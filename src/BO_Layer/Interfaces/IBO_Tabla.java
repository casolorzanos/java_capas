/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO_Layer.Interfaces;

import BO_Layer.BussinesObjects.OTabla;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * Interface for Tabla Business Object operations
 * @author Desarrollo
 */
public interface IBO_Tabla {
    
    /**
     * Retrieves all rows from the Tabla table
     * @return ArrayList of OTabla objects
     * @throws Exception if business logic error occurs
     */
    public ArrayList<OTabla> Obtener_filas() throws Exception;
    
    /**
     * Inserts a new record into the Tabla table with transaction management
     * @param pCampo The value for the Campo field
     * @throws SQLException if database error occurs
     * @throws Exception if business logic error occurs
     */
    public void Insertar_registro(String pCampo) throws SQLException, Exception;
}
