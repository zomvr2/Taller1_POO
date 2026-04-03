package taller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	static String[] usuariosID = new String[10];
	static String[] usuariosPass = new String[10];
	static int cantidadUsuarios = 0;



	public static void main(String[] args) {
		cargarUsuarios();
		menuPrincipal();
	}

	public static void menuPrincipal() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1) Menu de Usuarios\n" +
											 "2) Menu de Analisis\n" +
											 "3) Salir");

		String opcion = scanner.nextLine();

		switch (opcion) {
			case "1":
				menuUsuarios();
				break;
			case "2":
				menuAnalisis();
				break;
			case "3":
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion no valida");
				menuPrincipal();
		}
	}

	public static void menuUsuarios() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1) Registrar actividad\n" +
											 "2) Modificar actividad\n" +
											 "3) Eliminar actividad\n" +
											 "4) Cambiar contraseña\n" +
											 "5) Salir");

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
				System.out.println("Opcion no valida");
				menuUsuarios();
		}
	}

	public static void menuAnalisis() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1) Actividad más realizada\n" +
											 "2) Actividad más realizada por cada usuario\n" +
											 "3) Usuario con mayor procastinacion\n" +
											 "4) Ver todas las actividades\n" +
											 "5) Salir");

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
				System.out.println("Opcion no valida");
				menuAnalisis();
		}
	}

	static void cargarUsuarios() {
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
}
