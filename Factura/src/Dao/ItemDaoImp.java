package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import IDao.IDaoItem;
import Modelos.Item;
import Modelos.TipoItem;

public class ItemDaoImp implements IDaoItem {
	
	@Override
	public boolean agregar(Item item) {
		
		String query = "INSERT INTO tienda.Item values ( '" 
				+ item.getIdItem() + "','" 
				+ item.getTipo_Item().getIdTipo() + "','" 
				+ item.getDescripcion() + "','"
				+ item.getValor() + "')";
		
		return hacerQuery(query);
	}

	@Override
	public List<Item> obtener() {
		
		String query = "SELECT * FROM tienda.Item ORDER BY idItem";
		
		Statement statement = null;
		Connection conexion = null;
		ResultSet resultado = null;
		
		List<Item> listaClientes = new ArrayList<Item>();
		
		try {
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			resultado = statement.executeQuery(query);
			while(resultado.next()){
				Item item = new Item(
                		resultado.getInt(1),
                		new TipoItem(resultado.getInt(2)),   
                		resultado.getString(3),                		
                		resultado.getDouble(4));                
                listaClientes.add(item);
            }
			statement.close();
			resultado.close();
			conexion.close();
			
		}catch(SQLException e){
          System.out.println("a ocurrido un error:  " + e);
		}
		
		return listaClientes;
	}

	@Override
	public boolean actualizar(Item item) {
		
		String query = "UPDATE tienda.Item SET "
				+ "descripcion='" + item.getDescripcion() + "', "
				+ "valor='" + item.getValor() + "', "
				+ "tipo_item='" + item.getTipo_Item().getIdTipo()
				+ "' WHERE idItem=" + item.getIdItem();
		
		return hacerQuery(query);
	}

	@Override
	public boolean eliminar(int idItem) {
		
		String query = "DELETE FROM tienda.Item WHERE idItem=" + idItem;
		
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
          System.out.println("a ocurrido un error:  " + e);
		}
		
		return registroExitoso;
	}
}
