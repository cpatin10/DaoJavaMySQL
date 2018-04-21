package Modelos;

import java.util.HashMap;

public class EstadoCivil {
	private static final String OTRO = "Otro";

	private static final HashMap<String, Integer> mapa = mapeo(); 

	private static HashMap<String, Integer> mapeo() {
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("Casado", 0);
		mapa.put("Soltero", 1);
		mapa.put(OTRO, 2);
		return mapa;
	}

	private String estado;
	private int id;
	
	public EstadoCivil(String estado) {
		definirTipoItem(estado);
	}
	
	public EstadoCivil(int idEstado) {
		this.id = idEstado;
		this.estado = getTipoParaUnId(id);
	}

	public String getEstado() {
		return estado;
	}
	
	public int getIdEstado() {
		return id;
	}
	
	public void setTipo(String estado) {
		definirTipoItem(estado);
	}
	
	private void definirTipoItem(String estado) {
		if (mapa.containsKey(estado)) {
			this.estado = estado;
			this.id = mapa.get(estado);
		} else {
			this.estado = OTRO;
			this.id = mapa.get(OTRO);
		}
	}
	
	public String getTipoParaUnId(int id) {
		String tipo = "";
		for (String key : mapa.keySet()) {
	        if (mapa.get(key) == id) {
	        	tipo = key;
	        	break;
	        }
	    }
		return tipo;
	}
}
