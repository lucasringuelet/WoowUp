package ejercicioAlertas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class TestEjercicioAlertas {

	private Sistema sistema;
    private User usuario1;
    private User usuario2;
    private AlertaUrgente alertaUrgente1;
    private AlertaUrgente alertaUrgente2;
    private AlertaInformativa alertaInformativa1;
    private AlertaInformativa alertaInformativa2;
    

    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
        usuario1 = new User();
        usuario1.setUserName("Usuario1"); 
        
        usuario2 = new User();
        usuario2.setUserName("Usuario2"); 
        alertaUrgente1 = new AlertaUrgente("Alerta urgente 1", "autos", LocalDateTime.now());
        alertaUrgente2 = new AlertaUrgente("Alerta urgente 2", "motos", LocalDateTime.now());
        alertaInformativa1 = new AlertaInformativa("Alerta informativa 1", "autos", LocalDateTime.now());
        alertaInformativa2 = new AlertaInformativa("Alerta informativa 2", "motos", LocalDateTime.now());
        
    }

    @Test
    public void testAgregarUsuarios() {
    	
        sistema.agregarUsuarios(usuario1);
        sistema.agregarUsuarios(usuario2);

        ArrayList<User> usuarios = sistema.usuariosDelSistema();
        assertEquals(2, usuarios.size());
        assertTrue(usuarios.contains(usuario1));
        assertTrue(usuarios.contains(usuario2));
    }

    @Test
    public void testAgregarTemas() {
    	sistema.agregarTemas("autos");
        sistema.agregarTemas("motos");
        sistema.agregarTemas("camionetas");
        
        ArrayList<String> temas = sistema.temasDelSistema();
        assertEquals(3, temas.size());
        assertTrue(temas.contains("autos"));
        assertTrue(temas.contains("motos"));
        assertTrue(temas.contains("camionetas"));
    }

    @Test
    public void testEnviarAlertasAUsuarios() {
    	sistema.agregarTemas("autos");
        sistema.agregarTemas("motos");
        sistema.agregarTemas("camionetas");
    	
        sistema.agregarUsuarios(usuario1);
        sistema.agregarUsuarios(usuario2);
        usuario1.elegirTemas(sistema.temasDelSistema(), 0, 1);//eligio autos y motos
        usuario2.elegirTemas(sistema.temasDelSistema(), 0, 2);//eligio autos y camionetas
        sistema.getAlertasDelSistema().add(alertaUrgente1);
        sistema.getAlertasDelSistema().add(alertaUrgente2);
        sistema.getAlertasDelSistema().add(alertaInformativa1);

        sistema.enviarAlertasAUsuarios(sistema.usuariosDelSistema());

        assertTrue(usuario1.getAlertasDelUsuario().contains(alertaUrgente1));
        assertTrue(usuario1.getAlertasDelUsuario().contains(alertaUrgente2));
        assertTrue(usuario2.getAlertasDelUsuario().contains(alertaInformativa1));
    }

    @Test
    public void testEnviarAlertaEspecifico() {
    	sistema.agregarTemas("autos");
        sistema.agregarTemas("motos");
        sistema.agregarTemas("camionetas");
        
        sistema.agregarUsuarios(usuario1);
        sistema.agregarUsuarios(usuario2);
        usuario1.elegirTemas(sistema.temasDelSistema(), 0, 1);//eligio autos y motos
        usuario2.elegirTemas(sistema.temasDelSistema(), 0, 2);//eligio autos y camionetas
        
        System.out.println(usuario2.getTemasDelUsuario());
        sistema.getAlertasDelSistema().add(alertaUrgente1);//urgente de autos
        sistema.getAlertasDelSistema().add(alertaUrgente2);//urgente de motos
        sistema.getAlertasDelSistema().add(alertaInformativa1);
        System.out.println(sistema.getAlertasDelSistema());
        
        sistema.enviarAlertaEspecifico(usuario1, "autos");
        sistema.enviarAlertaEspecifico(usuario1, "camionetas");
        sistema.enviarAlertaEspecifico(usuario2, "autos");//nose porque no se estan pudiendo enviar alertasInformales 
        System.out.println(usuario1.getAlertasDelUsuario());//tendria que tener 2 una Urgente y otra Informal (ya que hay 2 de "autos")
        
        assertTrue(usuario1.getAlertasDelUsuario().contains(alertaUrgente1));
        assertTrue(usuario2.getAlertasDelUsuario().contains(alertaUrgente1));
    }

    @Test
    public void testPunto10ParaTodosLosUsuarios() {
    	sistema.agregarTemas("autos");
        sistema.agregarTemas("motos");
        sistema.agregarTemas("camionetas");
        
        sistema.agregarUsuarios(usuario1);
        sistema.agregarUsuarios(usuario2);
        usuario1.elegirTemas(sistema.temasDelSistema(), 0, 1);
        usuario2.elegirTemas(sistema.temasDelSistema(), 1, 2);

        sistema.getAlertasDelSistema().add(alertaUrgente1);
        sistema.getAlertasDelSistema().add(alertaUrgente2);
        sistema.getAlertasDelSistema().add(alertaInformativa1);
        
        ArrayList<Alerta> resultado = sistema.punto10ParaTodosLosUsuarios(sistema.usuariosDelSistema());
        
        assertEquals(3, resultado.size());
        assertTrue(resultado.get(0) instanceof AlertaUrgente);
        assertTrue(resultado.get(1) instanceof AlertaUrgente);
        assertTrue(resultado.get(2) instanceof AlertaInformativa);
    }
}


