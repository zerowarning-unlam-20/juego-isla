package entidades;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import comandos.Accion;
import comandos.Agarrar;
import comandos.Comando;
import comandos.Desbloquear;
import comandos.Ir;
import comandos.Tomar;
import comandos.Ver;
import estados.Estado;
import estados.Normal;

public class Personaje extends ObjetoJuego {
	private String nombre;
	private Ubicacion ubicacion;
	private Estado estado;
	private List<Item> inventario;
	private List<Accion> acciones;

	public Personaje(Ubicacion locacion) {
		super(0, "Carlos", "Tachero", true);
		this.estado = new Normal();
		setInventario(new LinkedList<Item>());
		this.ubicacion = locacion;
		acciones = Arrays.asList(new Accion[] { new Tomar(this), new Ver(this), new Ir(this), new Agarrar(this), new Desbloquear(this)});
	}

	public String hacer(Scanner comando) {
		String cmd = comando.next();
		for (Accion a : acciones) {
			if (a.getClass().getAnnotation(Comando.class).value().equals(cmd)) {
				return a.hacer(comando);
			}
		}
		return "Que es eso";
	}

	public String verAlrededor() {
		return ubicacion.getDescripcion();
	}

	public String verInventario() {
		return getInventario().isEmpty() ? "Inventario vacio" : "En el inventario hay " + getInventario().toString();
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + "\n Locacion=" + ubicacion + "\n Estado=" + estado + "\n Inventario="
				+ getInventario();
	}

	public void cambiarUbicacion(Ubicacion destino) {
		if (ubicacion.getUbicaciones().contains(destino)) {
			if (!destino.estaBloqueado()) {
				this.ubicacion = destino;
				System.out.println("Ubicacion cambiada");
				return;
			}
		}
		System.out.println("Ubicacion no cambiada");
	}

	@Comando("ver")
	public void ver() {

	}

	@Comando("usar")
	public void usar(Item item) {

	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public List<Item> getInventario() {
		return inventario;
	}

	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}

	public void agregarItem(Item item) {
		inventario.add(item);
	}

	public Item eliminarItem(Item item) {
		inventario.remove(item);
		return item;
	}

	public Item eliminarItem(int idItem) {
		Item eliminado = null;
		for (Item i : inventario) {
			if (i.getId() == idItem) {
				eliminado = i;
				inventario.remove(i);
				break;
			}
		}
		return eliminado;
	}

	@Override
	public void recibirObjeto(ObjetoJuego objeto) {
		// TODO Auto-generated method stub

	}
}
