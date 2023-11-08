package ejercicioAlertas;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class User {

	
	private List<String> temasDelUsuario = new ArrayList<>();
	
	private String userName;//suponemos que es unico por el punto 5 del ejercicio
	
	private List<Alerta> alertasDelUsuario = new ArrayList<>();
	
	private Deque<Alerta> alertasUrgentes;
	private Queue<Alerta> alertasInformativas;
	
	
	
	
	public void setUserName(String userName) {
		this.userName = userName; 
	}

	public List<String> getTemasDelUsuario() {
		return temasDelUsuario;
	}

	public String getUserName() {
		return userName;
	}

	public List<Alerta> getAlertasDelUsuario() {
		return alertasDelUsuario;
	}

	public void elegirTemas(ArrayList<String>temas,int tema1,int tema2) {
		//a modo de ejemplo se pasan como parametro 2 indices para elegir los temas,
		//pero en caso real solo se pasaria la lista de temas y podria elegir que temas guardar en su lista.
		this.temasDelUsuario.add(temas.get(tema1));
		this.temasDelUsuario.add(temas.get(tema2));
	}
	
	public void recibirAlerta(Alerta alerta) {
		this.alertasDelUsuario.add(alerta);
	}
	
	public List<String> GetTemasDelUsuario(){
		return this.temasDelUsuario;
	}
	public void marcarComoLeida(Alerta alertaABuscar) {
		
        for (Alerta alerta : alertasDelUsuario) {
            if (alerta.equals(alertaABuscar)) {
                alerta.setEstado(true);
                break; 
            }
        }
	}
	
	
	
	
	
	
	
	public ArrayList<Alerta> punto9(){
		this.alertasUrgentes = new ArrayDeque<Alerta>();
		this.alertasInformativas = new LinkedList<Alerta>();
		ArrayList<Alerta> listaADevolver =new ArrayList<Alerta>();
		for (Alerta alerta : (this.getAlertasDelUsuario())) {
            if(alerta.cumploConPunto9()) {
            	if(alerta.getTipo().equals("urgente")){
            		this.alertasUrgentes.push(alerta);
            	}else {
            		this.alertasInformativas.add(alerta);
            	}
        }
		
		
		}
		//primero las informativas en orden
		for (int i = 0; i < this.alertasInformativas.size(); i++) {
            listaADevolver.add(this.alertasInformativas.poll());
        }
		//por ultimo las urgentes (quedando estas primero)
		for (int i = 0; i < this.alertasUrgentes.size(); i++) {
            listaADevolver.add(this.alertasUrgentes.poll());
        }
		
        
		
		
		return listaADevolver;
	}
	
	public ArrayList<Alerta> punto10(){
		this.alertasUrgentes = new ArrayDeque<Alerta>();
		this.alertasInformativas = new LinkedList<Alerta>();
		ArrayList<Alerta> listaADevolver =new ArrayList<Alerta>();
		
		
		for (Alerta alerta : (this.getAlertasDelUsuario())) {
            if(alerta.cumploConPunto10(alerta.getTema())) {
            	if(alerta.getTipo().equals("urgente")){
            		this.alertasUrgentes.push(alerta);
            	}else {
            		this.alertasInformativas.add(alerta);
            	}
        }
		
		
		}
		//primero las informativas en orden
		for (int i = 0; i < this.alertasInformativas.size(); i++) {
            listaADevolver.add(this.alertasInformativas.poll());
        }
		//por ultimo las urgentes (quedando estas primero)
		for (int i = 0; i < this.alertasUrgentes.size(); i++) {
            listaADevolver.add(this.alertasUrgentes.poll());
        }
		
        
		
		
		return listaADevolver;
	}
	
}
