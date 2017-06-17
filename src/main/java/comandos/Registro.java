package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import frames.MenuCreacionPj;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class Registro extends ComandosCliente {

	@Override
	public void ejecutar() {
		synchronized (this) {
			
		
			Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
			if (paquete.getMensaje().equals(Paquete.msjExito)) {

				// Abro el menu para la creaci�n del personaje
				MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje(),gson);
				
				menuCreacionPJ.setVisible(true);
				// Espero a que el usuario cree el personaje

				// Recibo el paquete personaje con los datos (la id incluida)


				// Indico que el usuario ya inicio sesion
				cliente.getPaqueteUsuario().setInicioSesion(true);

			} else {
				if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
					JOptionPane.showMessageDialog(null, "No se pudo registrar.");
				}
				// El usuario no pudo iniciar sesión
				cliente.getPaqueteUsuario().setInicioSesion(false);
			}

		}
	}

}
