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
		daoCliente.agregar(new Cliente(1, "Pepe", "Pepito", 
				new Date(2000, 3, 4), "M", new EstadoCivil(1)));
		imprimirClientes(daoCliente);
		daoCliente.actualizar(new Cliente (0, "Cata", "Patiño", 
				new Date(1993, 5, 4), "F", new EstadoCivil(1)));
		imprimirCliente(daoCliente.obtener(0));
		System.out.println();
		
		IDaoItem daoItem = new ItemDaoImpl();
		daoItem.agregar(new Item(0, new TipoItem(0), "Rico y delicioso", 2.5));
		daoItem.agregar(new Item(1, new TipoItem("Otro"), "Sorpresa", 0.2));
		imprimirItems(daoItem);
		daoItem.actualizar(new Item(0, new TipoItem("Carne"), "De res", 3.0));
		imprimirItem(daoItem.obtener(0));
		System.out.println();
		
//		IDaoFactura daoFactura = new FacturaDaoImpl();
//		daoFactura.agregar(new Factura(0, new Date(2018, 4, 4), 10.2, "Deuda", 0,  ));
		
		daoCliente.eliminar(0);
		daoCliente.eliminar(1);
		daoItem.eliminar(0);
		daoItem.eliminar(1);
	}
	
	public static void imprimirClientes(IDaoCliente daoCliente) {
		List<Cliente> clientes = daoCliente.obtener();
		for (Cliente cliente: clientes) {
			imprimirCliente(cliente);
		}
		System.out.println();
	}

	private static void imprimirCliente(Cliente cliente) {
		System.out.println(cliente.getIdCliente() + " " + cliente.getNombre() + " " 
				+ cliente.getApellido());
	}
	
	public static void imprimirItems(IDaoItem daoItem) {
		List<Item> items = daoItem.obtener();
		for (Item item: items) {
			imprimirItem(item);
		}
		System.out.println();
	}

	private static void imprimirItem(Item item) {
		System.out.println(item.getIdItem() + " " + item.getTipo_Item().getTipo());
	}
}
