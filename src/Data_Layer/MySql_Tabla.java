/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Layer;
import BO_Layer.BussinesObjects.OTabla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desarrollo
 */
public class MySql_Tabla {  
    
    private ConexionBaseDatos Conexion;
    
    public MySql_Tabla() throws Exception{
        
        Conexion = new ConexionBaseDatos();
              
        if(!Conexion.estaConectado())
        {
            try {
                throw new Exception("No se conecto a la BD");
            } catch (Exception ex) {
               // Logger.getLogger(MySql_Tabla.class.getName()).log(Level.SEVERE, null, ex);
               throw ex;
            }
        }
    
    }
    
    private ArrayList<OTabla> Cargar_ResultSet(ResultSet rs) throws Exception
    {
        ArrayList<OTabla> _resultado = new ArrayList<OTabla>();
        try
        {
            while (rs.next()){
                OTabla tabla = new OTabla();

                tabla.setId(rs.getInt("Id"));
                tabla.setCampo(rs.getString("Campo"));
                tabla.setNum(rs.getInt("Num"));

                _resultado.add(tabla);                    
            }

            return _resultado;
        }
        catch (Exception error)
        {
            throw new Exception("Ocurrio un error al cargar los datos en Cargar_ResultSet - MySql_Tabla " + error.getMessage());
        }
    }
    
    public ArrayList<OTabla> Obtener_filas() throws Exception
    {
         ArrayList<OTabla> _resultado = new  ArrayList<OTabla>();
        StringBuilder sql;
        sql = new StringBuilder();
        try
        {
            sql.append("SELECT * FROM Tabla");
           /* sql.append("FROM         " + BD_CONFIG.BD_FACTURACION + ".dbo.Año_lectivo_facturable_detalle AS año_lec");
            sql.AppendLine("INNER JOIN " + BD_CONFIG.BD_FACTURACION + ".dbo.Items_facturables AS item ON año_lec.Fk_id_item_facturable = item.Id_item_facturable");
            sql.AppendLine("LEFT JOIN " + BD_CONFIG.BD_FACTURACION + ".dbo.Meses_facturables AS mes ON año_lec.Fk_id_mes_facturable = mes.Id_mes_facturable");
            sql.AppendLine("WHERE año_lec.Para_serializar = 1 AND año_lec.Fecha_baja IS NULL");
            sql.AppendLine("ORder by año_lec.Id desc");
*/
            _resultado = this.Cargar_ResultSet(getConexion().ejecutarConsulta(sql.toString()));
            
            return _resultado;
        }
        catch (Exception error)
        {
            throw error;
        }
    }
        
    public void Insertar_registro(String pCampo)throws Exception{
       try{
            if(getConexion().ejecutarUpDate("INSERT INTO tabla  (Campo) VALUES('"+pCampo+"');INSERT INTO tabla  (Campo) VALUES('"+pCampo+"')") != 1)
                throw new Exception("Hubo mas de un registro");
       
       }catch(Exception ex){
           throw ex;
       }
    }

    /**
     * @return the Conexion
     */
    public ConexionBaseDatos getConexion() {
        return Conexion;
    }

    /**
     * @param Conexion the Conexion to set
     */
    public void setConexion(ConexionBaseDatos Conexion) {
        this.Conexion = Conexion;
    }

}
