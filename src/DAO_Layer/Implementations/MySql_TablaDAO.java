/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_Layer.Implementations;

import BO_Layer.BussinesObjects.OTabla;
import DAO_Layer.Interfaces.ITablaDAO;
import Data_Layer.ConexionBaseDatos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * MySQL implementation of ITablaDAO
 * @author Desarrollo
 */
public class MySql_TablaDAO implements ITablaDAO {
    
    private ConexionBaseDatos conexion;
    
    public MySql_TablaDAO() throws Exception {
        conexion = new ConexionBaseDatos();
        
        if (!conexion.estaConectado()) {
            throw new Exception("No se conecto a la BD");
        }
    }
    
    private ArrayList<OTabla> Cargar_ResultSet(ResultSet rs) throws Exception {
        ArrayList<OTabla> resultado = new ArrayList<OTabla>();
        try {
            while (rs.next()) {
                OTabla tabla = new OTabla();
                
                tabla.setId(rs.getInt("Id"));
                tabla.setCampo(rs.getString("Campo"));
                tabla.setNum(rs.getInt("Num"));
                
                resultado.add(tabla);
            }
            
            return resultado;
        } catch (Exception error) {
            throw new Exception("Ocurrio un error al cargar los datos en Cargar_ResultSet - MySql_TablaDAO " + error.getMessage());
        }
    }
    
    @Override
    public ArrayList<OTabla> Obtener_filas() throws Exception {
        ArrayList<OTabla> resultado = new ArrayList<OTabla>();
        StringBuilder sql;
        sql = new StringBuilder();
        try {
            sql.append("SELECT * FROM Tabla");
            
            resultado = this.Cargar_ResultSet(conexion.ejecutarConsulta(sql.toString()));
            
            return resultado;
        } catch (Exception error) {
            throw error;
        }
    }
    
    @Override
    public void Insertar_registro(String pCampo) throws Exception {
        try {
            if (conexion.ejecutarUpDate("INSERT INTO tabla  (Campo) VALUES('" + pCampo + "');INSERT INTO tabla  (Campo) VALUES('" + pCampo + "')") != 1) {
                throw new Exception("Hubo mas de un registro");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     * @return the conexion
     */
    public ConexionBaseDatos getConexion() {
        return conexion;
    }
    
    /**
     * @param conexion the conexion to set
     */
    public void setConexion(ConexionBaseDatos conexion) {
        this.conexion = conexion;
    }
}
