package isla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.Acceso;
import entidades.Herramienta;
import entidades.IdManager;
import entidades.Item;
import entidades.Liquido;
import entidades.Personaje;
import entidades.Recipiente;
import entidades.Ubicacion;

public class Main {

	public static void main(String[] args) {
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
		Ubicacion salida = new Ubicacion(idManager.getSiguiente(), "salida", "Una birra nomas", true, false, itemsSalida);
		Acceso a1p2 = new Acceso(idManager.getSiguiente(), "puerta", "puerta de madera", true, true, false,
				salida.getId(), llave.getId());
		ArrayList<Acceso> accesosHabitacion = new ArrayList<>();
		accesosHabitacion.add(a1p2);
		Acceso a2p1 = new Acceso(idManager.getSiguiente(), "puerta", "puerta de madera", true, true, false,
				habitacion.getId(), llave.getId());
		ArrayList<Acceso> accesosSalida = new ArrayList<>();
		accesosSalida.add(a2p1);

		habitacion.setAccesos(accesosHabitacion);
		salida.setAccesos(accesosSalida);
		
		habitacion.agregarVinculo(salida);
		salida.agregarUbicacion(habitacion);

		/*
		 * for(Ubicacion ubicacion : ubicaciones) { for(Ubicacion otro : ubicaciones) {
		 * if(!ubicacion.equals(otro)) { ubicacion.agregarVinculo(otro); } } }
		 */

		Personaje p = new Personaje(habitacion);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String comando = "";

		while (comando != "-") {
			try {
				comando = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scanner cmdScanner = new Scanner(comando);
			System.out.println(p.hacer(cmdScanner));
		}
	}

}
