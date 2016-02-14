package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IUCliente extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUCliente frame = new IUCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Atributos
	JTextPane panel; //Yo en vez de mandar datos simplemente por los streamings, voy a mandar el objeto panel con todo lo que tiene escrito.
	Socket socket; //Objeto socket que tendrá el puerto de petición ene l puerto de escucha del serversocket, y que hará que este lo acepte automáticamente. 
	ObjectOutputStream oos; //Para mandar un objeto. 
	ObjectInputStream ois; //Para recibir objetos.
	

	/**
	 * Interfaz gráfica de usuaria creada directamente en el constructor.
	 * 
	 * @throws IOException
	 */
	public IUCliente() throws IOException {
		
		socket = new Socket("localhost", 55555); //Lo ponemos en contacto con el servidor, que lo aceptará una vez le llegue 
		oos = new ObjectOutputStream(socket.getOutputStream()); //Abrimos los streamings de escritura y lectura.
		ois = new ObjectInputStream(socket.getInputStream());
		
		//Parte de la interfaz gráfica que he creado con la paleta del IDE
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 295);
		getContentPane().setLayout(null);

		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);

		panel = new JTextPane();
		panel.setText("");
		panel.setFont(new Font("Consolas", Font.PLAIN, 15));
		panel.setEditable(false);
		panel.setBounds(10, 11, 177, 41);
		panel.setParagraphAttributes(attribs, true);

		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 63, 284, 188);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//Todos los eventos de la calculadora por cada botón. Básicamente cada botóne escribe en el panel lo que pone en dicho botón.

		JButton btnNewButton = new JButton("1");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setText(panel.getText() + btnNewButton.getText());
			}
		});
		btnNewButton.setBounds(0, 0, 56, 33);
		panel_1.add(btnNewButton);

		JButton button = new JButton("2");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button.getText());
			}
		});
		button.setBounds(62, 0, 56, 33);
		panel_1.add(button);

		JButton button_1 = new JButton("3");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_1.getText());
			}
		});
		button_1.setBounds(123, 0, 56, 33);
		panel_1.add(button_1);

		JButton button_2 = new JButton("4");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_2.getText());
			}
		});
		button_2.setBounds(0, 44, 56, 33);
		panel_1.add(button_2);

		JButton button_3 = new JButton("5");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_3.getText());
			}
		});
		button_3.setBounds(62, 44, 56, 33);
		panel_1.add(button_3);

		JButton button_4 = new JButton("6");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_4.getText());
			}
		});
		button_4.setBounds(123, 44, 56, 33);
		panel_1.add(button_4);

		JButton button_5 = new JButton("7");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_5.getText());
			}
		});
		button_5.setBounds(0, 88, 56, 33);
		panel_1.add(button_5);

		JButton button_6 = new JButton("8");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_6.getText());
			}
		});
		button_6.setBounds(62, 88, 56, 33);
		panel_1.add(button_6);

		JButton button_7 = new JButton("9");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_7.getText());
			}
		});
		button_7.setBounds(123, 88, 56, 33);
		panel_1.add(button_7);

		JButton button_8 = new JButton("0");
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + button_8.getText());
			}
		});

		button_8.setBounds(62, 132, 56, 33);
		panel_1.add(button_8);

		JButton button_9 = new JButton("/");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + " " + button_9.getText() + " ");
			}
		});
		button_9.setBounds(225, 88, 56, 33);
		panel_1.add(button_9);

		JButton button_10 = new JButton("—");
		button_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + " " + button_10.getText() + " ");
			}
		});
		button_10.setBounds(225, 44, 56, 33);
		panel_1.add(button_10);

		JButton button_11 = new JButton("+");
		button_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + " " + button_11.getText() + " ");
			}
		});
		button_11.setBounds(225, 0, 56, 33);
		panel_1.add(button_11);

		JButton btnX = new JButton("X");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setText(panel.getText() + " " + btnX.getText() + " ");
			}
		});
		btnX.setBounds(225, 132, 56, 33);
		panel_1.add(btnX);
		
		//Este es el único evento que cambia un poco. No tenemos un hilo que envíe y recibe automáticamente, sino que
		//va en función del click del usuario.  LLama a dos métodos que he definido más abajo. 
		JButton btnNewButton_1 = new JButton("Calcular");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					enviar();
					recibir();
				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				
				}
			}
		});
		btnNewButton_1.setBounds(197, 11, 96, 41);
		getContentPane().add(btnNewButton_1);
	}

	/**
	 * Métodos propios.
	 * 
	 * @throws IOException
	 */

	//Envía directamente el objeto panel por stream, que será evaluado desde el HiloServidor.java
	public void enviar() throws IOException {
		oos.writeObject(this.panel);
	}
	
	//Leemos lo que nos llega desde HiloServidor. Si nos llega un objeto y contiene datos, se plasmarán en nuestro panel.
	//Lo que hago es recoger el panel con el resultado, cojo el texto que tiene plasmado, y lo plasmo en el panel de la calculadora.
	//Podría haber simplemente mandado una cadena de texto como resultado, lo sé.
	public void recibir() throws ClassNotFoundException, IOException{	
		JTextPane aux = (JTextPane)ois.readObject();
		if (aux.getText() != null){
			panel.setText(aux.getText());
		}
		
	}


}
