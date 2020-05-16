package juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Acceso;
import entidades.Herramienta;
import entidades.IdManager;
import entidades.Item;
import entidades.Liquido;
import entidades.Personaje;
import entidades.Recipiente;
import entidades.Ubicacion;

public class ManagerJuego {
	private Personaje personaje;
	private List<Ubicacion> mapa;

	public ManagerJuego() {
		mapa = new ArrayList<>();
		cargarJuego();
	}

	private void cargarJuego() {
		try {
			cargarEjemplo();			
			personaje = new Personaje(mapa.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String comando = "";

		while (comando != "-") {
			try {
				comando = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scanner cmdScanner = new Scanner(comando);
			System.out.println(personaje.hacer(cmdScanner));
		}
	}

	public void reset() {

	}

	private void cargarEjemplo() {
		IdManager idManager = new IdManager();
		Item llave = new Herramienta(idManager.getSiguiente(), "llave", "Llave de bronce", true);
		ArrayList<Item> itemsHabitacion = new ArrayList<>();
		itemsHabitacion.add(llave);

		Recipiente botella = new Recipiente(idManager.getSiguiente(), "botella", "Botella de vidrio", true);
		Liquido cerveza = new Liquido(idManager.getSiguiente(), "cerveza", "No es light", true);
		botella.setContenido(cerveza);
		ArrayList<Item> itemsSalida = new ArrayList<>();
		itemsSalida.add(botella);

		Ubicacion habitacion = new Ubicacion(idManager.getSiguiente(), "habitacion",
				"Habitacion chica, con una llave en el suelo", true, false, itemsHabitacion);
		Ubicacion salida = new Ubicacion(idManager.getSiguiente(), "salida", "Una birra nomas", true, false,
				itemsSalida);
		Acceso a1p2 = new Acceso(idManager.getSiguiente(), "puerta", "puerta de madera", true, true, false,
				salida.getId(), llave.getId());
		ArrayList<Acceso> accesosHabitacion = new ArrayList<>();
		accesosHabitacion.add(a1p2);
		Acceso a2p1 = new Acceso(a1p2.getId(), "puerta", "puerta de madera", true, true, false,
				habitacion.getId(), llave.getId());
		ArrayList<Acceso> accesosSalida = new ArrayList<>();
		accesosSalida.add(a2p1);

		habitacion.setAccesos(accesosHabitacion);
		salida.setAccesos(accesosSalida);

		habitacion.agregarVinculo(salida);
		salida.agregarUbicacion(habitacion);

		mapa.add(habitacion);
		mapa.add(salida);
	}
}
