package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Conexion.Conexion;
import IDao.IDaoCliente;
import IDao.IDaoFactura;
import Modelos.Factura;
import Modelos.Cliente;
import Dao.ClienteDaoImpl;

public class FacturaDaoImpl implements IDaoFactura {
	
	static int idDetalleFactura = 0;

	@Override
	public boolean agregar(Factura factura) {
		
		boolean registroExitoso = false;
		String query;
		
		query = "INSERT INTO tienda.Factura VALUES ( '" 
				+ factura.getIdFactura() + "','" 
				+ new java.sql.Date(factura.getDate().getTime()) + "','"
				+ factura.getCliente().getIdCliente() + "','"
				+ factura.getTotal() + "','"
				+ factura.getEstado() +  "')";
		
		registroExitoso = hacerQuery(query);
		
		registroExitoso = registrarItemsFacturaEnDetalleFactura(factura.getIdFactura(), 
				factura.getItems(), registroExitoso);
		
		return registroExitoso;
	}

	@Override
	public List<Factura> obtener() {
		
		String query = "SELECT * FROM tienda.Factura ORDER BY idFactura";
		
		return selectFacturas(query);
	}
	
	@Override
	public Factura obtener(int idFactura) {
		
		String query = "SELECT * FROM tienda.Factura WHERE idFactura=" + idFactura;
		
		return selectFacturas(query).get(0);
	}
	
	private Cliente buscarCliente(int idCliente) {
		
		IDaoCliente daoCliente = new ClienteDaoImpl();
		return daoCliente.obtener(idCliente);
	}
	
	private HashMap<Integer, Integer> buscarItems(int idFactura) {
		
		Statement statement = null;
		Connection conexion = null;
		ResultSet resultado = null;
		
		HashMap<Integer, Integer> listaItems = new HashMap<Integer, Integer>();
		
		String query = "SELECT id_item, cantidad FROM tienda.Detalle_factura "
				+ "WHERE id_factura=" + idFactura;
		
		try {		
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			resultado = statement.executeQuery(query);
			
			while(resultado.next()){
				listaItems.put(resultado.getInt(1), resultado.getInt(2));
			}
			
			statement.close();
			resultado.close();
			conexion.close();
			
		} catch(SQLException e){
	          System.out.println("A ocurrido un error  y es this   " + e);
	          return null;
		}		
		
		return listaItems;
	}

	@Override
	public boolean actualizar(Factura factura) {
		
		String query;
		boolean registroExitoso = false;
		
		query = "UPDATE tienda.Factura SET "
				+ "fecha='" + new java.sql.Date(factura.getDate().getTime())+ "', "
				+ "cliente='" + factura.getCliente().getIdCliente() + "', "
				+ "total='" + factura.getTotal() + "', "
				+ "estado='" + factura.getEstado()
				+ "' WHERE idFactura=" + factura.getIdFactura();
		
		registroExitoso = hacerQuery(query);
		
		query = queryEliminarItemsFacturaEnDetalleItems(factura.getIdFactura());
		
		registroExitoso = hacerQuery(query) && registroExitoso;
		
		registroExitoso = registrarItemsFacturaEnDetalleFactura(factura.getIdFactura(), 
				factura.getItems(), registroExitoso) && registroExitoso;
		
		return registroExitoso;
	}

	@Override
	public boolean eliminar(int idFactura) {
		
		String query;
		boolean eliminacionExitosa = false;		

		query = queryEliminarItemsFacturaEnDetalleItems(idFactura);
		
		eliminacionExitosa = hacerQuery(query);
		
		query = "DELETE FROM tienda.Factura WHERE idFactura=" + idFactura;
			
		return hacerQuery(query) && eliminacionExitosa;	
	}

	private String queryEliminarItemsFacturaEnDetalleItems(int idFactura) {
		String query;
		query = "DELETE FROM tienda.Detalle_factura WHERE id_factura=" + idFactura;
		return query;
	}

	private boolean registrarItemsFacturaEnDetalleFactura(int idFactura, 
			HashMap<Integer, Integer> itemList, boolean registroExitoso) {
		
		String query;
		
		for (Map.Entry<Integer, Integer> item: itemList.entrySet()) {
			query = "INSERT INTO tienda.Detalle_factura VALUES ( '"
					+ idDetalleFactura++ + "','"
					+ item.getKey() + "','"
					+ idFactura + "','"
					+ item.getValue() +  "')";
			
			registroExitoso = hacerQuery(query) && registroExitoso;			
		}
		return registroExitoso;
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

	private List<Factura> selectFacturas(String query) {
		Statement statement = null;
		Connection conexion = null;
		ResultSet resultado = null;
		
		List<Factura> listaFacturas = new ArrayList<Factura>();
		
		try {
			conexion = Conexion.conectar();
			statement = conexion.createStatement();
			statement.execute(query);
			resultado = statement.executeQuery(query);
			
			
			while(resultado.next()){				
                Factura factura = new Factura(
                		resultado.getInt(1),
                		resultado.getDate(2),
                		resultado.getDouble(4),
                		resultado.getString(5),    
                		buscarCliente(resultado.getInt(3)),
                		buscarItems(resultado.getInt(1)));    
                listaFacturas.add(factura);
            }
			statement.close();
			resultado.close();
			conexion.close();
			
		}catch(SQLException e){
          System.out.println("A ocurrido un error  y es this   " + e);
		}
		
		return listaFacturas;
	}
	
}
