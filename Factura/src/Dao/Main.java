package Dao;

import Modelos.*;
import IDao.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		IDaoCliente daoCliente = new ClienteDaoImpl();
		
		Cliente cata = new Cliente(0, "Nombre", "Apellido", 
				new Date(2018, 4, 4), "F", new EstadoCivil(0));
		Cliente pepe = new Cliente(1, "Pepe", "Pepillo", 
				new Date(2000, 5, 4), "M", new EstadoCivil(1));
		
		daoCliente.agregar(cata);
		daoCliente.agregar(pepe);
		
		imprimirClientes(daoCliente);		

		cata = new Cliente (0, "Cata", "Patiño", 
				new Date(1993, 5, 4), "F", new EstadoCivil(1));		
		
		daoCliente.actualizar(cata);
		
		imprimirCliente(daoCliente.obtener(0));
		System.out.println();

		IDaoItem daoItem = new ItemDaoImpl();
		
		Item carne = new Item(0, new TipoItem(0), "Rico y delicioso", 2.5);
		Item sorpresa = new Item(1, new TipoItem("Otro"), "Sorpresa", 0.2);
		
		daoItem.agregar(carne);
		daoItem.agregar(sorpresa);
		
		imprimirItems(daoItem);
		
		carne = new Item(0, new TipoItem("Carne"), "De res", 3.0);
		
		daoItem.actualizar(carne);
		
		imprimirItem(daoItem.obtener(0));
		System.out.println();
		
		IDaoFactura daoFactura = new FacturaDaoImpl();
		
		HashMap<Integer, Integer> items1 = new HashMap<>();
		items1.put(carne.getIdItem(), 1);
		items1.put(sorpresa.getIdItem(), 3);
		HashMap<Integer, Integer> items2 = new HashMap<>();
		items2.put(carne.getIdItem(), 2);
		
		Factura f1 = new Factura(0, new Date(2018, 4, 5), 7.0, "pendiente", cata, items1);
		Factura f2 = new Factura(1, new Date(2017, 12, 24), 3.0, "pagado", pepe, items2);
		
		daoFactura.agregar(f1);
		daoFactura.agregar(f2);
		
		imprimirFacturas(daoFactura);
		
		items1.remove(sorpresa.getIdItem());
		f1 = new Factura(0, new Date(2018, 4, 5), 7.0, "pagado", cata, items1);
		
		daoFactura.actualizar(f1);
		
		imprimirFactura(daoFactura.obtener(0));
		System.out.println();
		
		daoFactura.eliminar(f1.getIdFactura());
		daoFactura.eliminar(f2.getIdFactura());
		
		daoCliente.eliminar(cata.getIdCliente());
		daoCliente.eliminar(pepe.getIdCliente());
		
		daoItem.eliminar(carne.getIdItem());
		daoItem.eliminar(sorpresa.getIdItem());
		
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
	
	public static void imprimirFacturas(IDaoFactura daoFactura) {
		List<Factura> facturas = daoFactura.obtener();
		for (Factura factura: facturas) {
			imprimirFactura(factura);
		}
		System.out.println();
	}

	private static void imprimirFactura(Factura factura) {
		System.out.println(factura.getIdFactura() + " " + factura.getEstado() + " Items: ");
		for (Map.Entry<Integer, Integer> item: factura.getItems().entrySet()) {
			System.out.println("  " + item.getKey() + " " + item.getValue());
		}
	}
}
