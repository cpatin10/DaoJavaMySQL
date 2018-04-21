package Modelos;

public class Item {
	
	private int idItem;
	private TipoItem tipo_item;
	private String descripcion;
	private double valor;
	
	public Item(int idItem, TipoItem tipo_item, String descripcion,
			double valor) {
		this.idItem = idItem;
		this.tipo_item = tipo_item;
		this.descripcion = descripcion;
		this.valor = valor;
	}
	
	public int getIdItem() {
		return idItem;
	}
	
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	
	public TipoItem getTipo_Item() {
		return tipo_item;
	}
	
	public void setTipoItem(TipoItem tipo_item) {
		this.tipo_item = tipo_item;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
