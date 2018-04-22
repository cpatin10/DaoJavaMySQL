package IDao;

import java.util.List;
import Modelos.Cliente;

public interface IDaoCliente {
	public boolean agregar(Cliente cliente);
	public List<Cliente> obtener();
	public Cliente obtener(int idCliente);
	public boolean actualizar(Cliente cliente);
	public boolean eliminar(int idCliente);
}