/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_Layer.Interfaces;

import BO_Layer.BussinesObjects.OTabla;
import java.util.ArrayList;

/**
 * Interface for Tabla DAO operations
 * @author Desarrollo
 */
public interface ITablaDAO {
    
    /**
     * Retrieves all rows from the Tabla table
     * @return ArrayList of OTabla objects
     * @throws Exception if database error occurs
     */
    public ArrayList<OTabla> Obtener_filas() throws Exception;
    
    /**
     * Inserts a new record into the Tabla table
     * @param pCampo The value for the Campo field
     * @throws Exception if database error occurs
     */
    public void Insertar_registro(String pCampo) throws Exception;
}
