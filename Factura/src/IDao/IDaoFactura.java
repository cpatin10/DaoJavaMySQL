package IDao;

import java.util.List;

import Modelos.Factura;

public interface IDaoFactura {
	public boolean agregar(Factura factura);
	public List<Factura> obtener();
//	public Factura obtener(int idFactura);
	public boolean actualizar(Factura factura);
	public boolean eliminar(int idFactura);
}


