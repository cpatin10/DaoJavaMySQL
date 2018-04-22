package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import IDao.IDaoCliente;
import Modelos.Cliente;
import Modelos.EstadoCivil;

public class ClienteDaoImpl implements IDaoCliente {

	@Override
	public boolean agregar(Cliente cliente) {
		
		String query = "INSERT INTO tienda.Cliente values ( '" 
				+ cliente.getIdCliente() + "','" 
				+ cliente.getNombre() + "','"
				+ cliente.getApellido() + "','"
				+ cliente.getGenero() + "','"				
				+ new java.sql.Date(cliente.getfechaNacimiento().getTime()) + "','"				
				+ cliente.getEstadoCivil().getIdEstado() + "')";
		
		return hacerQuery(query);
	}

	@Override
	public List<Cliente> obtener() {
		
		String query = "SELECT * FROM tienda.Cliente ORDER BY idCliente";
		
		return selectClientes(query);
	}

	@Override
	public Cliente obtener(int idCliente) {
		
		String query = "SELECT * FROM tienda.Cliente WHERE idCliente=" + idCliente;
		
		return selectClientes(query).get(0);
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		
		String query = "UPDATE tienda.Cliente SET "
				+ "nombre='" + cliente.getNombre() + "', "
				+ "apellido='" + cliente.getApellido() + "', "
				+ "genero='" + cliente.getGenero() + "', "
				+ "fecha_nacimiento='" + new java.sql.Date(cliente.getfechaNacimiento().getTime()) + "', "
				+ "estado_civil='" + cliente.getEstadoCivil().getIdEstado() 
				+ "' WHERE idCliente=" + cliente.getIdCliente();
		
		return hacerQuery(query);
	}

	@Override
	public boolean eliminar(int idCliente) {
		
		String query = "DELETE FROM tienda.Cliente WHERE idCliente=" + idCliente;
		
		return hacerQuery(query);
	}
	
	private boolean hacerQuery(String query) {
		boolean registroExitoso = false;
		Statement statement = null;
		Connection conexion = null;
		
		try {
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			registroExitoso = true;
			statement.close();
			conexion.close();
			
		}catch(SQLException e){
          System.out.println("A ocurrido un error: " + e);
		}
		
		return registroExitoso;
	}
	
	private List<Cliente> selectClientes(String query) {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		Statement statement = null;
		Connection conexion = null;
		ResultSet resultado = null;
		
		try {
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			resultado = statement.executeQuery(query);
			while(resultado.next()){
                Cliente es = new Cliente(
                		resultado.getInt(1),
                		resultado.getString(2),
                		resultado.getString(3),
                		resultado.getTime(5),
                		resultado.getString(4),
                		new EstadoCivil(resultado.getInt(6)));                
                listaClientes.add(es);
            }
			statement.close();
			resultado.close();
			conexion.close();
			
		}catch(SQLException e){
          System.out.println("a ocurrido un error: " + e);
		}
		
		return listaClientes;
	}

}
