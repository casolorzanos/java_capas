/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO_Layer.Objects;

import BO_Layer.BussinesObjects.OTabla;
import Data_Layer.MySql_Tabla;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Desarrollo
 */
public class BO_Tabla {
    
    MySql_Tabla _bo;

    public BO_Tabla() throws Exception {
        try {
            this._bo = new MySql_Tabla();
        } catch (Exception ex) {
          throw ex;
        }       
    }
    
    public ArrayList<OTabla> Obtener_filas() throws Exception
    {
        try{            
            ArrayList<OTabla> coleccion =  _bo.Obtener_filas();
            return coleccion;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void Insertar_registro(String pCampo) throws SQLException, Exception{
        try{
       
            
            _bo.getConexion().getConexion().setAutoCommit(false);
            
            
            _bo.Insertar_registro(pCampo);
                
                                  
            _bo.getConexion().getConexion().commit();
             
        }catch(SQLException ex){
           if(_bo.getConexion().getConexion() !=null){
                try{
                    _bo.getConexion().getConexion().rollback();
                } catch (SQLException exc) {
                      throw exc;
                }           
                
                throw ex;
            }
        }catch(Exception e){
            _bo.getConexion().getConexion().rollback();
            throw e;
        }
    }
}
