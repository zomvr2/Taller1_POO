/*
Benjamín Felipe Delgado Sánchez
22.223.703-3
ICCI
*/

package taller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Sistema simple de registro y analisis de actividades de procrastinacion.
 */
public class Main {
	// Recursos compartidos para lectura de entrada y estado en memoria.
	static Scanner scanner = new Scanner(System.in);

	// Datos de usuarios (maximo 10).
	static String[] usuariosID = new String[10];
	static String[] usuariosPass = new String[10];
	static int cantidadUsuarios = 0;
	static String usuarioActual;

	// Datos de actividades registradas (maximo 300).
	static String[] registrosID = new String[300];
	static String[] registrosFecha = new String[300];
	static int[] registrosHoras = new int[300];
	static String[] registrosActividad = new String[300];
	static int cantidadRegistros = 0;

	/** Punto de entrada: carga datos y muestra el menu principal. */
	public static void main(String[] args) {
		cargarUsuarios();
		cargarActividades();
		menuPrincipal();
	}

	/** Muestra el menu principal del sistema. */
	public static void menuPrincipal() {
		System.out.println();
		System.out.println("==== MENU PRINCIPAL ====");
		System.out.println("1) Menu de Usuarios");
		System.out.println("2) Menu de Analisis");
		System.out.println("3) Salir");
		System.out.print("Seleccione una opcion: ");

		String opcion = scanner.nextLine();

		switch (opcion) {
			case "1":
				menuUsuarios();
				break;
			case "2":
				menuAnalisis();
				break;
			case "3":
				System.out.println();
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println();
				System.out.println("Opcion no valida.");
				menuPrincipal();
		}
	}

	/**
	 * Muestra el menu de usuarios autenticados.
	 * Requiere inicio de sesion.
	 */
	public static void menuUsuarios() {
		if (!iniciarSesion()) {
			System.out.println("No puede acceder sin login.");
			menuPrincipal();
			return;
		}

		boolean continuar = true;

		while (continuar) {
			System.out.println();
			System.out.println("==== MENU DE USUARIOS ====");
			System.out.println("1) Registrar actividad");
			System.out.println("2) Modificar actividad");
			System.out.println("3) Eliminar actividad");
			System.out.println("4) Cambiar contraseña");
			System.out.println("5) Salir");
			System.out.print("Seleccione una opcion: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
				case "1":
					registrarActividad();
					break;
				case "2":
					modificarActividad();
					break;
				case "3":
					eliminarActividad();
					break;
				case "4":
					cambiarContrasena();
					break;
				case "5":
					usuarioActual = null;
					continuar = false;
					break;
				default:
					System.out.println();
					System.out.println("Opcion no valida.");
			}
		}

		menuPrincipal();
	}

	/** Muestra el menu con consultas de analisis. */
	public static void menuAnalisis() {
		boolean continuar = true;

		while (continuar) {
			System.out.println();
			System.out.println("==== MENU DE ANALISIS ====");
			System.out.println("1) Actividad mas realizada");
			System.out.println("2) Actividad mas realizada por cada usuario");
			System.out.println("3) Usuario con mayor procastinacion");
			System.out.println("4) Ver todas las actividades");
			System.out.println("5) Salir");
			System.out.print("Seleccione una opcion: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
				case "1":
					getActividadMasRealizada();
					break;
				case "2":
					getActividadMasRealizadaPorUsuario();
					break;
				case "3":
					getUsuarioConMayorProcastinacion();
					break;
				case "4":
					getTodasLasActividades();
					break;
				case "5":
					continuar = false;
					break;
				default:
					System.out.println();
					System.out.println("Opcion no valida.");
			}
		}

		menuPrincipal();
	}

	/** Solicita credenciales y guarda el usuario autenticado. */
	private static boolean iniciarSesion() {
		System.out.println();
		System.out.println("---- INICIO DE SESION ----");
		System.out.print("Ingrese su usuario: ");
		String id = scanner.nextLine();
		System.out.print("Ingrese su contraseña: ");
		String pass = scanner.nextLine();

		if (verificarUsuario(id, pass)) {
			usuarioActual = id;
			System.out.println();
			System.out.printf("Bienvenido %s!%n", id);
			return true;
		} else {
			System.out.println();
			System.out.println("Usuario o contraseña incorrectos.");
			return false;
		}
	}

	/** Valida si existe un usuario con ese ID y contraseña. */
	private static boolean verificarUsuario(String id, String pass) {
		for (int i = 0; i < cantidadUsuarios; i++) {
			if (usuariosID[i].equals(id) && usuariosPass[i].equals(pass)) {
				return true;
			}
		}

		return false;
	}

	/** Registra una nueva actividad para el usuario actual. */
	public static void registrarActividad() {
		if (cantidadRegistros >= 300) {
			System.out.println();
			System.out.println("No se pueden registrar mas actividades.");
			return;
		}

		System.out.println();
		System.out.println("---- REGISTRAR ACTIVIDAD ----");
		System.out.print("Ingrese la fecha (dd/mm/aaaa): ");
		String fecha = scanner.nextLine();
		System.out.print("Ingrese las horas procrastinadas: ");
		String horasTexto = scanner.nextLine();
		System.out.print("Ingrese la actividad: ");
		String actividad = scanner.nextLine();

		if (fecha.length() == 0 || horasTexto.length() == 0 || actividad.length() == 0) {
			System.out.println();
			System.out.println("Todos los campos son obligatorios.");
			return;
		}

		int horas;

		try {
			horas = Integer.parseInt(horasTexto);
		} catch (Exception e) {
			System.out.println();
			System.out.println("Las horas deben ser un numero entero valido.");
			return;
		}

		if (horas <= 0) {
			System.out.println();
			System.out.println("Las horas deben ser mayores que cero.");
			return;
		}

		registrosID[cantidadRegistros] = usuarioActual;
		registrosFecha[cantidadRegistros] = fecha;
		registrosHoras[cantidadRegistros] = horas;
		registrosActividad[cantidadRegistros] = actividad;
		cantidadRegistros++;

		guardarRegistros();

		System.out.println();
		System.out.println("Actividad registrada correctamente.");
	}

	/** Permite editar fecha, horas o actividad de un registro propio. */
	public static void modificarActividad() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para modificar.");
			return;
		}

		System.out.println();
		System.out.println("---- MODIFICAR ACTIVIDAD ----");

		System.out.println("0) Regresar");
		int[] indicesVisibles = new int[300];
		int cantidadOpciones = 0;

		for (int i = 0; i < cantidadRegistros; i++) {
			if (registrosID[i].equals(usuarioActual)) {
				cantidadOpciones++;
				indicesVisibles[cantidadOpciones - 1] = i;
				System.out.printf("%d) %s - %s - %d horas - %s%n", cantidadOpciones, registrosID[i], registrosFecha[i], registrosHoras[i], registrosActividad[i]);
			}
		}

		if (cantidadOpciones == 0) {
			System.out.println("No tiene actividades registradas para modificar.");
			return;
		}

		System.out.print("Seleccione la actividad a modificar: ");
		String opcion = scanner.nextLine();

		if (opcion.equals("0")) {
			return;
		}

		int seleccion;

		try {
			seleccion = Integer.parseInt(opcion);
		} catch (Exception e) {
			System.out.println();
			System.out.println("Opcion no valida.");
			return;
		}

		if (seleccion < 1 || seleccion > cantidadOpciones) {
			System.out.println();
			System.out.println("Opcion no valida.");
			return;
		}

		int indice = indicesVisibles[seleccion - 1];

		if (!registrosID[indice].equals(usuarioActual)) {
			System.out.println();
			System.out.println("No puede modificar actividades de otros usuarios.");
			return;
		}

		System.out.println();
		System.out.println("Que deseas modificar?");
		System.out.println("0) Regresar.");
		System.out.println("1) Fecha");
		System.out.println("2) Duracion");
		System.out.println("3) Tipo de actividad");
		System.out.print("Seleccione una opcion: ");
		String opcionCampo = scanner.nextLine();

		switch (opcionCampo) {
			case "0":
				return;
			case "1":
				System.out.print("Ingrese la nueva fecha (dd/mm/aaaa): ");
				String fecha = scanner.nextLine();

				if (fecha.length() == 0) {
					System.out.println();
					System.out.println("La fecha es obligatoria.");
					return;
				}

				registrosFecha[indice] = fecha;
				break;
			case "2":
				System.out.print("Ingrese las nuevas horas procrastinadas: ");
				String horasTexto = scanner.nextLine();
				int horas;

				try {
					horas = Integer.parseInt(horasTexto);
				} catch (Exception e) {
					System.out.println();
					System.out.println("Las horas deben ser un numero entero valido.");
					return;
				}

				if (horas <= 0) {
					System.out.println();
					System.out.println("Las horas deben ser mayores que cero.");
					return;
				}

				registrosHoras[indice] = horas;
				break;
			case "3":
				System.out.print("Ingrese la nueva actividad: ");
				String actividad = scanner.nextLine();

				if (actividad.length() == 0) {
					System.out.println();
					System.out.println("La actividad es obligatoria.");
					return;
				}

				registrosActividad[indice] = actividad;
				break;
			default:
				System.out.println();
				System.out.println("Opcion no valida.");
				return;
		}

		guardarRegistros();
		System.out.println();
		System.out.println("Actividad actualizada correctamente.");
	}

	/** Elimina una actividad registrada por el usuario actual. */
	public static void eliminarActividad() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para eliminar.");
			return;
		}

		System.out.println();
		System.out.println("---- ELIMINAR ACTIVIDAD ----");

		System.out.println("0) Regresar");
		int[] indicesVisibles = new int[300];
		int cantidadOpciones = 0;

		for (int i = 0; i < cantidadRegistros; i++) {
			if (registrosID[i].equals(usuarioActual)) {
				cantidadOpciones++;
				indicesVisibles[cantidadOpciones - 1] = i;
				System.out.printf("%d) %s - %s - %d horas - %s%n", cantidadOpciones, registrosID[i], registrosFecha[i], registrosHoras[i], registrosActividad[i]);
			}
		}

		if (cantidadOpciones == 0) {
			System.out.println("No tiene actividades registradas para eliminar.");
			return;
		}

		System.out.print("Seleccione la actividad a eliminar: ");
		String opcion = scanner.nextLine();

		if (opcion.equals("0")) {
			return;
		}

		int seleccion;
		try {
			seleccion = Integer.parseInt(opcion);
		} catch (Exception e) {
			System.out.println();
			System.out.println("Opcion no valida.");
			return;
		}

		if (seleccion < 1 || seleccion > cantidadOpciones) {
			System.out.println();
			System.out.println("Opcion no valida.");
			return;
		}

		int indice = indicesVisibles[seleccion - 1];

		if (!registrosID[indice].equals(usuarioActual)) {
			System.out.println();
			System.out.println("No puede eliminar actividades de otros usuarios.");
			return;
		}

		for (int i = indice; i < cantidadRegistros - 1; i++) {
			registrosID[i] = registrosID[i + 1];
			registrosFecha[i] = registrosFecha[i + 1];
			registrosHoras[i] = registrosHoras[i + 1];
			registrosActividad[i] = registrosActividad[i + 1];
		}

		registrosID[cantidadRegistros - 1] = null;
		registrosFecha[cantidadRegistros - 1] = null;
		registrosHoras[cantidadRegistros - 1] = 0;
		registrosActividad[cantidadRegistros - 1] = null;
		cantidadRegistros--;

		guardarRegistros();

		System.out.println();
		System.out.println("Actividad eliminada correctamente.");
	}

	/** Cambia la contraseña del usuario autenticado. */
	private static void cambiarContrasena() {
		System.out.println();
		System.out.println("---- CAMBIAR CONTRASEÑA ----");
		System.out.print("Ingrese su contraseña actual: ");
		String passActual = scanner.nextLine();

		if (!verificarUsuario(usuarioActual, passActual)) {
			System.out.println();
			System.out.println("Contraseña actual incorrecta.");
			return;
		}

		System.out.print("Ingrese su nueva contraseña: ");
		String passNueva = scanner.nextLine();

		if (passNueva.length() == 0) {
			System.out.println();
			System.out.println("La nueva contraseña no puede estar vacia.");
			return;
		}

		for (int i = 0; i < cantidadUsuarios; i++) {
			if (usuariosID[i].equals(usuarioActual)) {
				usuariosPass[i] = passNueva;
				break;
			}
		}

		guardarUsuarios();

		System.out.println();
		System.out.println("Contraseña cambiada correctamente.");
	}

	/** Muestra la actividad con mayor cantidad de horas registradas. */
	private static void getActividadMasRealizada() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para analizar.");
			return;
		}

		String actividadMasRealizada = null;
		int maxHoras = 0;

		for (int i = 0; i < cantidadRegistros; i++) {
			if (registrosHoras[i] > maxHoras) {
				maxHoras = registrosHoras[i];
				actividadMasRealizada = registrosActividad[i];
			}
		}

		System.out.println();
		System.out.printf("La actividad mas realizada es: %s con %d horas.%n", actividadMasRealizada, maxHoras);
	}

	/** Muestra por usuario su actividad con mas horas registradas. */
	private static void getActividadMasRealizadaPorUsuario() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para analizar.");
			return;
		}

		for (int i = 0; i < cantidadUsuarios; i++) {
			String usuario = usuariosID[i];
			String actividadMasRealizada = null;
			int maxHoras = 0;

			for (int j = 0; j < cantidadRegistros; j++) {
				if (registrosID[j].equals(usuario) && registrosHoras[j] > maxHoras) {
					maxHoras = registrosHoras[j];
					actividadMasRealizada = registrosActividad[j];
				}
			}

			System.out.printf("* %s ->  %s -> %d horas registradas.%n", usuario, actividadMasRealizada, maxHoras);
		}
	}

	/** Muestra el usuario con mayor suma total de horas. */
	private static void getUsuarioConMayorProcastinacion() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para analizar.");
			return;
		}

		String usuarioMayorProcastinacion = null;
		int maxHoras = 0;

		for (int i = 0; i < cantidadUsuarios; i++) {
			String usuario = usuariosID[i];
			int totalHoras = 0;

			for (int j = 0; j < cantidadRegistros; j++) {
				if (registrosID[j].equals(usuario)) {
					totalHoras += registrosHoras[j];
				}
			}

			if (totalHoras > maxHoras) {
				maxHoras = totalHoras;
				usuarioMayorProcastinacion = usuario;
			}
		}

		System.out.println();
		System.out.printf("El usuario con mayor procastinacion es: %s con %d horas.%n", usuarioMayorProcastinacion, maxHoras);
	}

	/** Lista todas las actividades almacenadas. */
	public static void getTodasLasActividades() {
		if (cantidadRegistros == 0) {
			System.out.println();
			System.out.println("No hay actividades registradas para mostrar.");
			return;
		}

		System.out.println();
		System.out.println("---- TODAS LAS ACTIVIDADES ----");

		for (int i = 0; i < cantidadRegistros; i++) {
			System.out.printf("%s - %s - %d horas - %s%n", registrosID[i], registrosFecha[i], registrosHoras[i], registrosActividad[i]);
		}
	}

	/** Carga los usuarios desde el archivo usuarios.txt. */
	private static void cargarUsuarios() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
			String linea = br.readLine();

			while (linea != null && cantidadUsuarios < 10) {
				String[] partes = linea.split(";");

				if (partes.length == 2) {
					String id = partes[0];
					String pass = partes[1];

					usuariosID[cantidadUsuarios] = id;
					usuariosPass[cantidadUsuarios] = pass;

					cantidadUsuarios++;
				}

				linea = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			System.out.println("Error al cargar usuarios: " + e.getMessage());
		}
	}

	/** Carga los registros desde el archivo registros.txt. */
	private static void cargarActividades() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("registros.txt"));
			String linea = br.readLine();

			while (linea != null) {
				String[] partes = linea.split(";");

				if (partes.length == 4 && cantidadRegistros < 300) {
					String id = partes[0];
					String fecha = partes[1];
					int horas = Integer.parseInt(partes[2]);
					String actividad = partes[3];

					registrosID[cantidadRegistros] = id;
					registrosFecha[cantidadRegistros] = fecha;
					registrosHoras[cantidadRegistros] = horas;
					registrosActividad[cantidadRegistros] = actividad;

					cantidadRegistros++;
				}

				linea = br.readLine();
			}

			br.close();
		} catch(Exception e) {
			System.out.println("Error al cargar actividades: " + e.getMessage());
		}
	}

	/** Guarda los usuarios en el archivo usuarios.txt. */
	private static void guardarUsuarios() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", false));

			for (int i = 0; i < cantidadUsuarios; i++) {
				bw.write(usuariosID[i] + ";" + usuariosPass[i]);
				bw.newLine();
			}

			bw.close();
		} catch (Exception e) {
			System.out.println("Error al guardar usuarios: " + e.getMessage());
		}
	}

	/** Guarda los registros en el archivo registros.txt. */
	private static void guardarRegistros() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("registros.txt", false));

			for (int i = 0; i < cantidadRegistros; i++) {
				bw.write(registrosID[i] + ";" + registrosFecha[i] + ";" + registrosHoras[i] + ";" + registrosActividad[i]);
				bw.newLine();
			}

			bw.close();
		} catch (Exception e) {
			System.out.println("Error al guardar actividades: " + e.getMessage());
		}
	}
}
