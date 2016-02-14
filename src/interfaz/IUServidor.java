package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IUServidor extends JFrame{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUServidor frame = new IUServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Atributos

	JEditorPane panelservidor; //Una vez conectado el servidor, nos saldrá un mensaje de éxito.
	ServerSocket server; //Objeto para crear el servidor.
	Socket socket; //Objeto socket que emplearemos para aceptar todos los sockets entrantes. 
	Thread aceptarSockets = new Thread(){ //Con este hilo aceptamos, reiteradamente, nuevos sockets.
		@Override
		public void run() {
			try {
				socket = server.accept(); //Aceptamos el socket en la IP y puerto determinados (localhost y 55555)
				((HiloServidor) new HiloServidor(socket)).start(); //Creamos un hilo, por cada socket, que gestione los output y input stram.
			} catch (IOException e) {
				e.printStackTrace();
			}          
		};
	};
	
	/**
	 * Interfaz gráfica del servidor.
	 * @throws IOException 
	 */

	public IUServidor() throws IOException {
		server = new ServerSocket(55555); //En el constructor le damos al servidor el puerto de escucha 55555
		
		//Toda esta parte se ha hecho directamente con la paleta del IDE
		setFont(new Font("Lucida Console", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\David\\workspace\\CalculadoraServidor\\src\\imagenes\\Cloud_Computing-512.png"));
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		panelservidor = new JEditorPane();
		panelservidor.setText("");
		panelservidor.setBackground(Color.DARK_GRAY);
		panelservidor.setForeground(Color.WHITE);
		panelservidor.setFont(new Font("Consolas", Font.PLAIN, 11));
		panelservidor.setEditable(false);
		panelservidor.setBounds(0, 0, 434, 261);
		getContentPane().add(panelservidor);
		
		//Ejecutamos el hilo que aceptará los sockets entrantes. 
		aceptarSockets.start();
		
		//Escribimos el mensaje de exito al conectar el panel servidor. 
		actualizarPanel("Servidor inicializado con éxito.");
	}

	/**
	 * Métodos propios.
	 * 
	 */
	
	//Método para actualizar el panel de la interfaz del servidor. Es puramente estético, para no imprimirlo por consola. 
	public void actualizarPanel(String text) {
		panelservidor.setText(panelservidor.getText() + "\n" + text);
	}

}