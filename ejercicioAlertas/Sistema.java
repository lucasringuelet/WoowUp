package ejercicioAlertas;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sistema {
	
	
	private ArrayList<Alerta> alertasDelSistema = new ArrayList<>();
	
	private ArrayList<User> usuarios= new ArrayList<>(); 
	private ArrayList<String> temas= new ArrayList<>();
	
	private Deque<Alerta> alertasUrgentes;
	private Queue<Alerta> alertasInformativas;

	
	
	
	public void agregarUsuarios(User user) {
		
		this.usuarios.add(user);
	}
	
	public ArrayList<User> usuariosDelSistema(){
		return this.usuarios;
	}
	
	
	//temas
	
	public void agregarTemas(String tema) {
		
		this.temas.add(tema);
	}
	
	public ArrayList<String> temasDelSistema(){
		return this.temas;
	}
	
	
	//punto 4
	public void enviarAlertasAUsuarios(ArrayList<User> usuarios) {
		for (Alerta alerta : getAlertasDelSistema()) {
            alerta.enviarAlertas(usuarios);
        }
	}
	
	
	//punto 5
	public void enviarAlertaEspecifico(User usuarioAenviar,String tema) {
		for (User usuario : usuarios) {
            if(usuario.getUserName().equals(usuarioAenviar.getUserName())) {
            	for (Alerta alerta : getAlertasDelSistema()) {
            		if(alerta.getTema().equals(tema)) {
            			alerta.enviarAlertasEspecifico(usuario,tema);
                        break;
            		}
                    
                }
            	break;
            }
        }
		
		
	}
	
	public ArrayList<Alerta> punto9(User usuario){
		return usuario.punto9();
	}
	public ArrayList<Alerta> punto10(User usuario){
		return usuario.punto10();
	}
	public ArrayList<Alerta> punto10ParaTodosLosUsuarios(ArrayList<User> usuarios){
		this.alertasUrgentes = new ArrayDeque<Alerta>();
		this.alertasInformativas = new LinkedList<Alerta>();
		ArrayList<Alerta> listaADevolver =new ArrayList<Alerta>();
		
		for(User usuario : usuarios) {
			for (Alerta alerta : (usuario.getAlertasDelUsuario())) {
	            if(alerta.cumploConPunto10(alerta.getTema())) {
	            	if(alerta.getTipo().equals("urgente")){ //aca se tendria que usar un patron que se llama Strategy (pero no me dio el tiempo,perdon)
	            		this.alertasUrgentes.push(alerta);
	            	}else {
	            		this.alertasInformativas.add(alerta);
	            	}
	            }
			
			
			}
		}
		System.out.println(this.alertasInformativas);
		System.out.println(this.alertasUrgentes);
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

	public List<Alerta> getAlertasDelSistema() {
		return alertasDelSistema;
	}

}
