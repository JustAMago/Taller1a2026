// ENZO SALVATORE CORNIELES MEDINA - 26.814.980-5 ICCI
package Taller1;

import java.util.Scanner;
import java.io.*;

public class Main {
	// Alumnos de Alumnos.txt
	static String[] nombreAlumnos = new String[100];
	static String[] apellidoAlumnos = new String[100];
	static String[] rutAlumnos = new String[100];
	static String[] paraleloAlumnos = new String[100];
	static int totalAlumnos = 0;
	// Solicitudes de Solicitudes.txt
	static String[] solicitudNombres = new String[100];
	static String[] solicitudApellidos = new String[100];
	static int totalSolicitudes = 0;
	// Alumnos Admitidos
	static String[] nombresAdmitidos = new String[100];
	static String[] apellidosAdmitidos = new String[100];
	static String[] rutsAdmitidos = new String[100];
	static String[] paralelosAdmitidos = new String[100];
	static int totalAdmitidos = 0;
	// Alumnos Rechazados
	static String[] nombresRechazados = new String[100];
	static String[] apellidosRechazados = new String[100];
	static String[] rutsRechazados = new String[100];
	static String[] paralelosRechazados = new String[100];
	static String[] soloRutRechazados = new String [100]; 
	static int totalRechazados = 0;
	// Reportes
	static int reporteC1 = 1;
	static int reporteC2 = 1;
	static int reporteRechazados = 1;
	
	public static void main(String[] args) {
		Scanner pregunta = new Scanner(System.in);
		int opcion = 0;
		do {
		System.out.println("===== Sistema de Control del Grupo POO =====\r\n"
				+ "1) Cargar archivos (Alumnos y Solicitudes)\r\n"
				+ "2) Procesar solicitudes (Filtrado automatico)\r\n"
				+ "3) Inscripcion manual al grupo\r\n"
				+ "4) Administracion del curso\r\n"
				+ "5) Generar reportes\r\n"
				+ "6) Analisis estadistico\r\n"
				+ "7) Salir"
				+ ": ");
				
		try {
			opcion = Integer.parseInt(pregunta.nextLine());
		} catch (Exception e) {
			opcion = 0;
		} 
		switch(opcion) {
		case 1:
			leerAlumnos();
			leerSolicitudes();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			System.out.println("Saliendo del programa...");
			break;
		default:
			System.out.println("Opcion invalida. Intente nuevamente.");
		}
		
	} while (opcion != 7);
}
	public static void leerAlumnos() {
		File archivo = new File("Alumnos.txt");
		if (!archivo.exists()) {
			System.out.println("No se encontró ningún archivo");
			return;
		}
		try (Scanner lector = new Scanner(archivo)) {
			totalAlumnos = 0;
			while (lector.hasNextLine() && totalAlumnos < 100) {
				String linea = lector.nextLine().trim();
				if (!linea.isEmpty()) {
					String[] datos = linea.split(";");
					nombreAlumnos[totalAlumnos] = datos[0].trim();
					apellidoAlumnos[totalAlumnos] = datos[1].trim();
					rutAlumnos[totalAlumnos] = datos[2].trim();
					paraleloAlumnos[totalAlumnos] = datos[3].trim();
					totalAlumnos++;
				}
			}
			System.out.println("Archivo de Alumnos leído correctamente");
		}
		catch (IOException e) {
			System.out.println("Error al leer archivo");
		}
	}
	
	public static void leerSolicitudes() {
		File archivo = new File("Solicitudes.txt");
		if (!archivo.exists()) {
			System.out.println("No se encontró ningún archivo");
			return;
		}
		try (Scanner lector = new Scanner(archivo)) {
			totalSolicitudes = 0;
			while (lector.hasNextLine() && totalSolicitudes < 100) {
				String linea = lector.nextLine().trim();
				if (!linea.isEmpty()) {
					String[] datos = linea.split("-");
					solicitudNombres[totalSolicitudes] = datos[0].trim();
					solicitudApellidos[totalSolicitudes] = datos[1].trim();
					totalSolicitudes++;
				}
			}
			System.out.println("Archivo de Solicitudes leído correctamente");
		}
		catch (IOException e) {
			System.out.println("Error al leer archivo");
		}
	}
}

