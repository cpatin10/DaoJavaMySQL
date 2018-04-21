package Dao;

import Modelos.*;
import IDao.*;
import Dao.*;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		IDaoCliente daoCliente = new ClienteDaoImpl();
		daoCliente.agregar(new Cliente(0, "Nombre", "Apellido", 
				new Date(2018, 4, 4), "F", new EstadoCivil(0)));
		imprimirClientes(daoCliente);
		daoCliente.actualizar(new Cliente (0, "Cata", "Patiño", 
				new Date(1993, 5, 4), "F", new EstadoCivil(1)));
		imprimirClientes(daoCliente);
		
		IDaoItem daoItem = new ItemDaoImp();
		daoItem.agregar(new Item(0, new TipoItem(0), "Rico y delicioso", 2.5));
		imprimirItems(daoItem);
		daoItem.actualizar(new Item(0, new TipoItem("Carne"), "De res", 3.0));
		imprimirItems(daoItem);
		
		
		daoCliente.eliminar(0);
		daoItem.eliminar(0);
	}
	
	public static void imprimirClientes(IDaoCliente daoCliente) {
		List<Cliente> clientes = daoCliente.obtener();
		for (Cliente cliente: clientes) {
			System.out.println(cliente.getNombre() + " " + cliente.getApellido());
		}
	}
	
	public static void imprimirItems(IDaoItem daoItem) {
		List<Item> items = daoItem.obtener();
		for (Item item: items) {
			System.out.println(item.getIdItem() + " " + item.getTipo_Item().getTipo());
		}
	}
}
