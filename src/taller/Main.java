package taller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
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
		Scanner scanner = new Scanner(System.in);

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

		Scanner scanner = new Scanner(System.in);

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
				// Registrar actividad
				break;
			case "2":
				// Modificar actividad
				break;
			case "3":
				// Eliminar actividad
				break;
			case "4":
				// Cambiar contraseña
				break;
			case "5":
				menuPrincipal();
				break;
			default:
				System.out.println();
				System.out.println("Opcion no valida.");
				menuUsuarios();
		}
	}

	public static void menuAnalisis() {
		Scanner scanner = new Scanner(System.in);

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
		Scanner scanner = new Scanner(System.in);

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
