package ejercicioAlertas;


import java.time.LocalDateTime;

import java.util.ArrayList;



public abstract class Alerta {

	
	private String tema;
	private LocalDateTime fechaYHora;
	private Boolean estado=false;
	
	
	
	public  String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}


	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}


	public void enviarAlertas(ArrayList<User> usuarios) {
		for (User user : usuarios) {
            if(user.GetTemasDelUsuario().contains(tema)) {
            	
            	user.recibirAlerta(this);
            }
        }
	}
	
	
	public void enviarAlertasEspecifico(User usuario,String tema) {
		if(this.tema.equals(tema)) {
			if(usuario.getTemasDelUsuario().contains(tema)) {
				usuario.recibirAlerta(this);
			}
			
		}
	}
	
	public boolean cumploConPunto9() {
		LocalDateTime today=LocalDateTime.now();
		if((this.getFechaYHora().isBefore(today) || this.getFechaYHora().isEqual(today))&&(!this.getEstado())) {
        	return true;
        }else 
        	return false;
	}
	
	public boolean cumploConPunto10(String tema) {
		LocalDateTime today=LocalDateTime.now();
		if((this.getFechaYHora().isBefore(today) || this.getFechaYHora().isEqual(today))&&(this.getTema().equals(tema))) {
        	return true;
        }else 
        	return false;
	}
	
	
	
	
	public abstract String getTipo();


	
	
	
	
	
}
