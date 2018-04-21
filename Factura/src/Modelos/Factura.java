package Modelos;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	
	private int idFactura;
	private Date fecha;
	private double total;
	private String estado;
	private Cliente cliente;
	private ArrayList<Item> items;
	
	public Factura(int idFactura, Date fecha, double total, 
			String estado, Cliente cliente, ArrayList<Item> items) {
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.cliente = cliente;
		this.items = items;
	}
	
	public int getIdFactura() {
		return idFactura;
	}
	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public Date getDate() {
		return fecha;
	}
	
	public void setDate(Date fecha) {
		this.fecha = fecha;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}		
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}		
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setCliente(ArrayList<Item> items) {
		this.items = items;
	}
	
}
