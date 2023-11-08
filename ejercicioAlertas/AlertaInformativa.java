package ejercicioAlertas;

import java.time.LocalDateTime;


public class AlertaInformativa extends Alerta { 

	private String tipo="informativa";
	private String descripcion;//agregue una descripcion para la hora de saber si se esta imprimiendo bien y en orden
	
	
	
	public AlertaInformativa(String des,String tema,LocalDateTime fechaHora) {
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
