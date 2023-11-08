package ejercicioAlertas;

import java.time.LocalDateTime;



public class AlertaUrgente extends Alerta{

	private String tipo="urgente";
	private String descripcion;
	
	
	
	public AlertaUrgente(String des,String tema,LocalDateTime fechaHora) {
		super.setTema(tema);
		super.setFechaYHora(fechaHora); 
		
		
		this.setDescripcion(des);
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

	
}
