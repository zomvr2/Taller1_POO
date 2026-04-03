package taller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static String[] usuariosID = new String[10];
	static String[] usuariosPass = new String[10];
	static int cantidadUsuarios = 0;
	static String usuarioActual;

	static String[] registrosID = new String[300];
	static String[] registrosFecha = new String[300];
	static int[] registrosHoras = new int[300];
	static String[] registrosActividad = new String[300];
	static int cantidadRegistros = 0;


	public static void main(String[] args) {
		cargarUsuarios();
		cargarActividades();
		menuPrincipal();
	}

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
					// Eliminar actividad
					break;
				case "4":
					// Cambiar contraseña
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

		System.out.print("Ingrese la nueva fecha (dd/mm/aaaa): ");
		String fecha = scanner.nextLine();
		System.out.print("Ingrese las nuevas horas procrastinadas: ");
		String horasTexto = scanner.nextLine();
		System.out.print("Ingrese la nueva actividad: ");
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

		registrosFecha[indice] = fecha;
		registrosHoras[indice] = horas;
		registrosActividad[indice] = actividad;

		guardarRegistros();
		System.out.println();
		System.out.println("Actividad actualizada correctamente.");
	}

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

	public static void menuAnalisis() {
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
				// Actividad más realizada
				break;
			case "2":
				// Actividad más realizada por cada usuario
				break;
			case "3":
				// Usuario con mayor procastinacion
				break;
			case "4":
				// Ver todas las actividades
				break;
			case "5":
				menuPrincipal();
				break;
			default:
				System.out.println();
				System.out.println("Opcion no valida.");
				menuAnalisis();
		}
	}

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

	private static boolean verificarUsuario(String id, String pass) {
		for (int i = 0; i < cantidadUsuarios; i++) {
			if (usuariosID[i].equals(id) && usuariosPass[i].equals(pass)) {
				return true;
			}
		}

		return false;
	}

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
}
