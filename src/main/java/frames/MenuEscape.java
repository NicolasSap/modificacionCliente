package frames;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import mensajeria.Comando;
import mensajeria.Paquete;

public class MenuEscape extends JFrame {

	private JPanel contentPane;
	private MenuAsignarSkills m1;
	private MenuInventario menu;
	private MenuStats s1;
	private final Gson gson = new Gson();
	/**
	 * Create the frame.
	 */
	public MenuEscape(final Cliente cliente) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setBounds(100, 100, 180, 270);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton verStats = new JButton("Estadísticas");
		verStats.setIcon(new ImageIcon("recursos//stats.png"));
		verStats.setToolTipText("Presiona S para ver estadísticas");
		verStats.setBounds(29, 13, 125, 25);
		verStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
				s1 = new MenuStats(cliente);
				s1.setVisible(true);
			}
		});
		contentPane.add(verStats);
		
		JButton asignarSkills = new JButton("Asignar Skills");
		asignarSkills.setIcon(new ImageIcon("recursos//asignar skills.png"));
		asignarSkills.setToolTipText("Presiona A para asignar skills");
		asignarSkills.setBounds(29, 66, 125, 25);
		asignarSkills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
				m1 = new MenuAsignarSkills(cliente);
				m1.setVisible(true);
			}
		});
		contentPane.add(asignarSkills);
		
		JButton inventario = new JButton("Inventario");
		inventario.setIcon(new ImageIcon("recursos//inventario.png"));
		inventario.setToolTipText("Presiona I para abrir inventario");
		inventario.setBounds(29, 121, 125, 25);
		inventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(Estado.getEstado().esEstadoDeJuego()) {
					menu = new MenuInventario(cliente);
					menu.setVisible(true);
				}
			}
		});
		contentPane.add(inventario);
		
		JButton desconectarse = new JButton("Desconectarse");
		desconectarse.setBounds(29, 175, 125, 25);
		desconectarse.setIcon(new ImageIcon("recursos//desconectarse.png"));
		desconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Paquete p = new Paquete();
						p.setComando(Comando.DESCONECTAR);
						p.setIp(cliente.getMiIp());
						cliente.getSalida().writeObject(gson.toJson(p));
						cliente.getEntrada().close();
						cliente.getSalida().close();
						cliente.getSocket().close();
						System.exit(0);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		contentPane.add(desconectarse);
		
		JButton volver = new JButton("Volver");
		volver.setIcon(new ImageIcon("recursos//volver.png"));
		volver.setBounds(29, 227, 125, 25);
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(volver);
		
		BufferedImage imagenFondo = null;
		try {
			imagenFondo = ImageIO.read(new File("recursos//fondo2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel background = new JLabel(new ImageIcon(imagenFondo.getScaledInstance(200, 350, Image.SCALE_DEFAULT)));
		background.setBounds(0, 0, 186, 273);
		contentPane.add(background);
	}
}
