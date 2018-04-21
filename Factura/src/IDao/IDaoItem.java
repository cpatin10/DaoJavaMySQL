package IDao;

import java.util.List;

import Modelos.Item;

public interface IDaoItem {
	public boolean agregar(Item item);
	public List<Item> obtener();
	public boolean actualizar(Item item);
	public boolean eliminar(int idItem);

}
