

package Data_Layer;
import java.sql.*;

public class ConexionBaseDatos {
    
    //----------- Atributos ---------------
    private final String nombreBD="test3"; // nombre fisico de la BD
    
    private String url="";//camino + nombre de la base de datos
    private String driver="";  // nombre del driver
    
    private Connection conexion;   // Objeto de conexi�n a la base de datos
    private Statement sentencia;   // Objeto que permite ejecutar comando SQL
    private ResultSet resultado;   // (tabla)Objeto con el resultado de la consulta SQL
    private boolean conectado;
    
    public String ipHostServidor; // almacena la direccion (ip) de la maquina en donde se ejecuta el Servidor BD
          
    // ---------- cosntructores --------------
    public ConexionBaseDatos() throws Exception {
        try{
            ipHostServidor= "localhost";
            //System.out.println("IP "+Configuracion.ipServer);
            url="jdbc:mysql://"+ipHostServidor+":3306/"+nombreBD; //cambiar por servidor1 para q vuelva andar para el logueo
            driver="com.mysql.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd
            conectado=false;
            conectarBD();
        }catch(Exception e){
            throw e;
        }
        
    }
    
    public ConexionBaseDatos(String ipHostServidor) throws Exception {
        try{
        this.ipHostServidor=ipHostServidor;
        
        url="jdbc:mysql://"+ipHostServidor+":3306/"+nombreBD; //cambiar por servidor1 para q vuelva andar para el logueo 
	driver="com.mysql.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd 	
	
        conectado=false;
        conectarBD();
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void conectarBD() throws Exception{ 
        try {
            Class.forName(driver);//CARGA EL DRIVER SUN.JDBC.... PERMITE LA COMUNICACION ENTRE EL PROG JAVA Y EL DRIVER DEL MOTOR
            conexion = DriverManager.getConnection(url,"root","1234");//se crea una conexion con bd especificada
            sentencia = conexion.createStatement(); //se crea una sentencia p ejecutar comandos sql y se asocia con conexion
            //conexion.setAutoCommit(false);
            conectado=true;            
        }catch( Exception e ) {            
            throw e;

//  javax.swing.JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos !\n"+e.getMessage(),"Mensaje !!!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public boolean estaConectado()
    {
        return conectado;
    }
    
    public ResultSet ejecutarConsulta(String con) throws SQLException{
       
       ResultSet resultado2=null;
       
       try{
           sentencia = conexion.createStatement();
           resultado2 = sentencia.executeQuery(con);     
           
           return resultado2;   
           
       }catch(SQLException e){
           throw e;
           //javax.swing.JOptionPane.showMessageDialog(null,"Error al ejecutar la siguiente consulta: \n"+con+"\n"+e.getMessage(),"Mensaje !!!",javax.swing.JOptionPane.ERROR_MESSAGE);
       }
      
    }
    
     public int ejecutarUpDate(String con){
         
       int registros_afectados = 0;
       
       try{
             registros_afectados = sentencia.executeUpdate(con);
             
             return registros_afectados;
                  
       }catch(SQLException e){
            System.out.println(e.getMessage());
       }
       
       return 0;
    }
    
    public void cerrarConexion(){
        try{
            sentencia.close();
            conexion.close();
            if(resultado!=null)
                resultado.close();
              
	  }catch( Exception e ) {
            System.out.println( e.toString() );
          }
    }

}
