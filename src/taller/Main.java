package taller;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
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
}
