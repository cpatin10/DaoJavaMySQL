package Modelos;

import java.util.HashMap;

public class TipoItem {
	
	private static final String OTRO = "Otro";
	
	private static final HashMap<String, Integer> mapa = mapeo(); 
	
	private static HashMap<String, Integer> mapeo() {
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("Lacteo", 0);
		mapa.put("Carne", 1);
		mapa.put(OTRO, 2);
		return mapa;
	}
	
	private String tipo;
	private int id;
	
	public TipoItem(String tipo) {
		definirTipoItem(tipo);
	}
	
	public TipoItem(int idTipo) {
		this.id = idTipo;
		this.tipo = getTipoParaUnId(id);
	}

	private void definirTipoItem(String tipo) {
		if (mapa.containsKey(tipo)) {
			this.tipo = tipo;
			this.id = mapa.get(tipo);
		} else {
			this.tipo = OTRO;
			this.id = mapa.get(OTRO);
		}
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getIdTipo() {
		return id;
	}
	
	public void setTipo(String tipo) {
		definirTipoItem(tipo);
	}
	
	public String getTipoParaUnId(int id) {
		String estado = "";
		for (String key : mapa.keySet()) {
	        if (mapa.get(key) == id) {
	        	estado = key;
	        	break;
	        }
	    }
		return estado;
	}
}
