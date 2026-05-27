/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO_Layer.Objects;

import BO_Layer.BussinesObjects.OTabla;
import BO_Layer.Interfaces.IBO_Tabla;
import DAO_Layer.Implementations.MySql_TablaDAO;
import DAO_Layer.Interfaces.ITablaDAO;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Business Object implementation for Tabla
 * @author Desarrollo
 */
public class BO_Tabla implements IBO_Tabla {
    
    private ITablaDAO _dao;

    public BO_Tabla() throws Exception {
        try {
            this._dao = new MySql_TablaDAO();
        } catch (Exception ex) {
          throw ex;
        }       
    }
    
    @Override
    public ArrayList<OTabla> Obtener_filas() throws Exception
    {
        try{            
            ArrayList<OTabla> coleccion =  _dao.Obtener_filas();
            return coleccion;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    @Override
    public void Insertar_registro(String pCampo) throws SQLException, Exception{
        try{
           
            // Transaction management at BO layer
            // Note: You may need to expose connection management from DAO or handle it differently
            
            _dao.Insertar_registro(pCampo);
                
                                  
        }catch(SQLException ex){
           throw ex;
        }catch(Exception e){
            throw e;
        }
    }
}
