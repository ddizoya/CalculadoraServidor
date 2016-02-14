package interfaz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTextPane;

public class HiloServidor extends Thread {
	private Socket socket; //Socket aceptado por el servidor. 
	private ObjectOutputStream oos; //Streams de lectura y escritura 
	private ObjectInputStream ois;
	private String datos[] = null; //Datos del panel que vamos a 'separar' uno por uno para operar 
	private JTextPane cuenta; //Panel que vamos a leer y que nos llega desde cliente. 

	public HiloServidor(Socket socket) { //Desde el constructor, directamente abrimos los streams. 
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void desconnectar() { //Método para cerrar el socket 
		try {
			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() { //Método en bucle de lectura y envío de datos automático por parte del servidor con el cliente. 
		try {
			cuenta = (JTextPane) ois.readObject(); //Casteamos el panel que nos llega de cliente 
			datos = cuenta.getText().split(" "); //Leemos el texto del panel, lo troceamos con el método split donde haye spacios, y metemos el resultado en un array
			calcular(); //Con ese array, llamamos al método calcular, que en función de los valores, opera 
			oos.writeObject(cuenta); //Le mandamos al cliente el resultado final

		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		desconnectar(); //Desconectamos 
	}

	private void calcular() { //Al panel que nos llega, le cambiamos los datos con las operaciones correspondientes. 
		int resul;
		switch (datos[1]) {
		case "+":
			resul = Integer.parseInt(datos[0]) + Integer.parseInt(datos[2]);
			cuenta.setText(String.valueOf(resul));
			break;
		case "—":
			resul = Integer.parseInt(datos[0]) - Integer.parseInt(datos[2]);
			cuenta.setText(String.valueOf(resul));
			break;
		case "/":
			resul = Integer.parseInt(datos[0]) / Integer.parseInt(datos[2]);
			cuenta.setText(String.valueOf(resul));
			break;
		case "x":
			resul = Integer.parseInt(datos[0]) * Integer.parseInt(datos[2]);
			cuenta.setText(String.valueOf(resul));
			break;
		default:
			cuenta.setText("ERROR");
			break;
		}
	}
}
