package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import IDao.IDaoCliente;
import IDao.IDaoFactura;
import IDao.IDaoItem;
import Modelos.Factura;
import Modelos.Cliente;
import Modelos.Item;
import Modelos.TipoItem;
import Modelos.EstadoCivil;
import Dao.ClienteDaoImpl;
import Dao.ItemDaoImpl;;

public class FacturaDaoImpl implements IDaoFactura {

	@Override
	public boolean agregar(Factura factura) {
		
		String query = "INSERT INTO tienda.Factura values ( '" 
				+ factura.getIdFactura() + "','" 
				+ new java.sql.Date(factura.getDate().getTime()) + "','"
				+ factura.getCliente().getIdCliente() + "','"
				+ factura.getTotal() + "','"
				+ factura.getEstado() +  "')";
		
		return hacerQuery(query);
	}

	@Override
	public List<Factura> obtener() {
		
		String query = "SELECT * FROM tienda.Factura ORDER BY idFactura";
		
		Statement statement = null;
		Connection conexion = null;
		ResultSet resultado = null;
		
		List<Factura> listaClientes = new ArrayList<Factura>();
		
		try {
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			resultado = statement.executeQuery(query);
			
			
			while(resultado.next()){
                Factura es = new Factura(
                		resultado.getInt(1),
                		resultado.getDate(2),
                		resultado.getDouble(4),
                		resultado.getString(5),    
                		buscarCliente(resultado.getInt(3)),
                		buscarItems(resultado.getInt(1), statement));      
                
                listaClientes.add(es);
            }
			statement.close();
			resultado.close();
			conexion.close();
			
		}catch(SQLException e){
          System.out.println("a ocurrido un error  y es this   " + e);
		}
		
		return listaClientes;
	}
	
	private Cliente buscarCliente(int idCliente) {
//		String query = "SELECT * FROM tienda.Cliente WHERE idCliente=" + idCliente;
//		try {
//			ResultSet resultado = statement.executeQuery(query);
//			
//			return new Cliente(
//					resultado.getInt(1),
//	        		resultado.getString(2),
//	        		resultado.getString(3),
//	        		resultado.getDate(5),
//	        		resultado.getString(4),
//	        		new EstadoCivil(resultado.getInt(6)));
//		}catch(SQLException e){
//	          System.out.println("a ocurrido un error  y es this   " + e);
//	          return null;
//		}
		
		IDaoCliente daoCliente = new ClienteDaoImpl();
		return daoCliente.obtener(idCliente);
	}
	
	private ArrayList<Item> buscarItems(int idFactura, Statement statement) {
		
		ArrayList<Item> listaItems = new ArrayList<Item>();
		
		String query = "SELECT id_item FROM tienda.Detalle_Factura "
				+ "WHERE id_factura=" + idFactura;
		
		IDaoItem daoItem = new ItemDaoImpl();
		
		try {
			
			ResultSet resultado = statement.executeQuery(query);
			while(resultado.next()){
				listaItems.add(daoItem.obtener(resultado.getInt(1)));
			}
			
		} catch(SQLException e){
	          System.out.println("a ocurrido un error  y es this   " + e);
	          return null;
		}
		
//		String query = "SELECT * FROM tienda.Item "
//				+ "INNER JOIN Detalle_Factura "
//				+ "ON tienda.Detalle_Factura.id_item = tienda.Item.idItem "
//				+ "WHERE tienda.Detalle_Factura.id_factura = " + idFactura;
//		try {
//			ResultSet resultado = statement.executeQuery(query);
//			while(resultado.next()){
//				Item item = new Item(
//	            		resultado.getInt(1),
//	            		new TipoItem(resultado.getInt(2)),   
//	            		resultado.getString(3),                		
//	            		resultado.getDouble(4));
//				listaItems.add(item);
//			}
//		}catch(SQLException e){
//	          System.out.println("a ocurrido un error  y es this   " + e);
//	          return null;
//		}
		
		
		return listaItems;
	}

	@Override
	public boolean actualizar(Factura factura) {
		
		String query = "UPDATE tienda.Cliente SET "
				+ "fecha='" + factura.getDate() + "', "
				+ "cliente='" + factura.getCliente().getIdCliente() + "', "
				+ "total='" + factura.getTotal() + "', "
				+ "estado='" + factura.getEstado()
				+ "' WHERE idCliente=" + factura.getIdFactura();
		
		return hacerQuery(query);
	}

	@Override
	public boolean eliminar(Factura factura) {
		
		String query = "DELETE FROM tienda.Cliente WHERE idFactura=" + factura.getIdFactura();
		
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
          System.out.println("a ocurrido un error  y es this   " + e);
		}
		
		return registroExitoso;
	}
	
}
