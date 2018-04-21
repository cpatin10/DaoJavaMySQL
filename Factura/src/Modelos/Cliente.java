package Modelos;

import java.util.Date;

public class Cliente {
	
	private int idCliente;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String genero;
	private EstadoCivil estado_civil;
	
	public Cliente (int idCliente, String nombre, String apellido,
			Date fecha_nacimiento, String genero, EstadoCivil estado_civil) 
	{
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.genero = genero;
		this.estado_civil = estado_civil;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Date getfechaNacimiento() {
		return fecha_nacimiento;
	}
	
	public void setFechaNacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public EstadoCivil getEstadoCivil() {
		return estado_civil;
	}
	
	public void setEstadoCivil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
	}
}
