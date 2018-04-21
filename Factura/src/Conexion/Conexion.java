package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexion {
	
    public static Connection conexion = null;
     
    public Conexion() {}
    
    public static Connection conectar(){
        
    String driver = "com.mysql.jdbc.Driver";
    String contrasena = "Er1uska493";
    String usuario = "root";
    String url = "jdbc:mysql://localhost:3306/tienda";
		try{
                    Class.forName(driver);
                    conexion = DriverManager.getConnection(url, usuario, contrasena );
//                    if(conexion != null){
//                        System.out.println("conexion establecida");
//                    }
                }catch(ClassNotFoundException | SQLException e){
                    System.out.println("error al conectar a la base de datos  " + e);
                }
                return conexion;
                
    }
    
    //Retorna la conexion a la base datos 
     public Connection getConexion(){           
    	 return conexion;	
     }
     
     //metodo de desconexion de la base de datos
     public void desconectar(){
         conexion = null;
         if(conexion == null){
             System.out.println("conexcion terminada");
         }
     }
}