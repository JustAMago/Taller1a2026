// ENZO SALVATORE CORNIELES MEDINA - 26.814.980-5 ICCI
package Taller1;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

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
	static int totalSoloRutRechazados = 0;
	// Reportes
	static int reporteC1 = 1;
	static int reporteC2 = 1;
	static int reporteRechazados = 1;
	//Menu Principal
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
			opcion = 0; //Para llevarlo al default del switch
		} 
		switch(opcion) {
		case 1: //Se leen los archivos
			leerAlumnos();
			leerSolicitudes();
			break;
		case 2: //Se procesan a los admitidos y rechazados
			procesarSolicitudes();
			break;
		case 3: //Inscribe manualmente a un alumno
			inscripcionManual();
			break;
		case 4: //Se elimina, añade o se cambia de paralelo a un alumno
			administracionCurso();
			break;
		case 5: //Se generan reportes en archivos nuevos
			generarReportes();
			break;
		case 6: //Analisis de Admitidos, Rechazados y porcentaje de Alumnos en cada paralelo
			analisisEstadistico();
			break;
		case 7: //Sale del programa
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
	
	public static void procesarSolicitudes() {
		if (totalSolicitudes == 0 || totalAlumnos == 0) {
			System.out.println("No se han cargado los archivos");
			return;
		}
		totalAdmitidos = 0;
		totalRechazados = 0;
		for (int i = 0; i < totalSolicitudes; i++) {
			boolean encontrado = false;
			for (int j = 0; j < totalAlumnos; j++) {
				if(solicitudNombres[i].equalsIgnoreCase(nombreAlumnos[j]) && solicitudApellidos[i].equalsIgnoreCase(apellidoAlumnos[j])) {
					agregarAAdmitidos(j, false); // Se agregan los estudiantes admitidos a la lista
					encontrado = true;
					break;
					}
			}
			if (!encontrado) {
				if (totalRechazados > 100) {
					nombresRechazados[totalRechazados] = solicitudNombres[i];
					apellidosRechazados[totalRechazados] = solicitudApellidos[i];
					totalRechazados++;
				}
			}
		}
		System.out.println("--- Procesamiento Completado ---");
	    System.out.println("Solicitudes procesadas: " + totalSolicitudes);
	    System.out.println("Alumnos admitidos: " + totalAdmitidos);
	    System.out.println("Solicitudes rechazadas: " + totalRechazados);
		
	}
	
	public static void inscripcionManual() {
		if (totalAlumnos == 0) {
			System.out.println("No se han cargado los archivos");
			return;
		}
		Scanner pregunta2 = new Scanner(System.in);
		System.out.println("Como desea inscribir a la persona?\r\n"
				+ "1) Por nombre completo\r\n"
				+ "2) Por RUT\r\n"
				+ "Ingrese opcion: ");
		int opcion1 = 0;
		try {
		opcion1 = Integer.parseInt(pregunta2.nextLine());
		} catch (Exception e) {
			opcion1 = 0;
		}
		switch(opcion1) {
		case 1:
			System.out.println("Ingrese nombre: ");
			String nombreIng = pregunta2.nextLine();
			System.out.println("Ingrese apellido: ");
			String apellidoIng = pregunta2.nextLine();
			int posicionNom = -1;
			for (int i = 0; i < totalAlumnos; i++) {
				if (nombreAlumnos[i].equalsIgnoreCase(nombreIng) && apellidoAlumnos[i].equalsIgnoreCase(apellidoIng)) {
					posicionNom = i;
					break;
				}
			}
			
			if (posicionNom != -1) {
				agregarAAdmitidos(posicionNom, true); //Se agregan a los admitidos a la lista de estudiantes
			}
			else {
				if (totalRechazados < 100) {
					nombresRechazados[totalRechazados] = nombreIng;
					apellidosRechazados[totalRechazados] = apellidoIng;
					totalRechazados++;
				}
				System.out.println("El alumno " + nombreIng + " " + apellidoIng + " no pertenece a este curso.");
			}
			break;
		case 2:
			System.out.println("Ingrese el RUT (ej. 12345678-9): ");
			String rutIngresado = pregunta2.nextLine();
			for (int i = 0; i < totalAdmitidos; i++) {
				if (rutsAdmitidos[i].equalsIgnoreCase(rutIngresado)) {
					System.out.println("El estudiante con RUT " + rutIngresado + " ya se encuentra registrado en el grupo");
					return;
				}
			}
			int posicionRut = -1;
			for (int j = 0; j < totalAlumnos; j++) {
				if (rutAlumnos[j].equalsIgnoreCase(rutIngresado)) {
					posicionRut = j;
					break;
				}
			}
			
			if (posicionRut != -1) {
				agregarAAdmitidos(posicionRut, true);
			}
			else {
				if (totalSoloRutRechazados < 100) {
					soloRutRechazados[totalSoloRutRechazados] = rutIngresado;
					totalSoloRutRechazados++;
				}
				System.out.println("El alumno no pertenece a ninguna lista oficial. Registrado en Rechazados");
			}
			break;
		default: 
			System.out.println("Opcion invalida, debe ingresar un valor entero valido");
			break;
		}
		}	
	
	public static void administracionCurso() {
		if (totalAlumnos == 0) {
			System.out.println("No se ha encontrado ningun alumno.");
			return;
		}
		
		if (totalAdmitidos == 0) {
			System.out.println("No hay alumnos registrados para administrar.");
			return;
		}
		
		Scanner pregunta3 = new Scanner(System.in);
		System.out.println("--- Administración del Curso ---\n"
				+ "1) Eliminar alumno admitido\n"
				+ "2) Cambiar paralelo de un alumno\n" 
				+ "3) Inscribir un alumno nuevo al curso\n"
				+ "4) Salir\n"
				+ "Seleccione una opción: ");
		
		int opcion2 = 0;
		try {
			opcion2 = Integer.parseInt(pregunta3.nextLine());
			switch (opcion2) {
			case 1:
				eliminarAlumno();
				break;
			case 2:
				cambiarParalelo();
				break;
			case 3:
				inscribirAlumnoNuevo();
				break;
			case 4:
				break;
			default:
				System.out.println("Ingrese un numero del 1 al 4 por favor");
			}
		} catch (Exception e) {
			System.out.println("Error, ingrese un número entero valido");
		}
		
		
		
	}
	
	public static void generarReportes() {
		if (totalAlumnos == 0) {
			System.out.println("No se han cargado los archivos de los alumnos");
			return;
		}
		
		File carpeta = new File("Reportes");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		
		Scanner pregunta7 = new Scanner(System.in);
		System.out.println("--- Menú de Generación de Reportes ---\r\n"
				+ "1) Generar Reporte Paralelo C1\r\n"
				+ "2) Generar Reporte Paralelo C2\r\n"
				+ "3) Generar Reporte Rechazados\r\n"
				+ "4) Volver al menú principal\r\n"
				+ "Seleccione una opción:");
		try {
			int opcion3 = Integer.parseInt(pregunta7.nextLine());
			switch (opcion3) {
			case 1:
				reporteC1();
				break;
			case 2:
				reporteC2();
				break;
			case 3:
				reporteRechazados();
				break;
			case 4:
				break;
			default:
				System.out.println("Ingrese un numero entero valido");
			}
		} catch (Exception e) {
			System.out.println("Error, no se ingreso un valor valido");
		}
		
		
	}
	
	public static void analisisEstadistico() {
		int totalIntentos = totalAdmitidos + totalRechazados + totalSoloRutRechazados;
		if (totalIntentos == 0) {
			System.out.println("No se han cargado los archivos");
			return;
		}
		int totalRechazos = totalRechazados + totalSoloRutRechazados;
		double porcentajeRechazo = ((double) totalRechazos / totalIntentos) *100;
		double porcentajeAdmitido = ((double) totalAdmitidos / totalIntentos)*100;
		
		int contadorC1 = 0;
		int contadorC2 = 0;
		for (int i = 0; i < totalAdmitidos; i++) {
			if (paralelosAdmitidos[i].equalsIgnoreCase("C1")) {
				contadorC1++;
			}
			else if (paralelosAdmitidos[i].equalsIgnoreCase("C2")) {
				contadorC2++;
			}
		}
		
		System.out.println("=== ANALISIS ESTADISTICO ===\r\n"
				+ "Total de solicitudes/intentos:" + totalIntentos + "\r\n"
				+ "Admitidos totales:" + totalAdmitidos + " (" + porcentajeAdmitido + "%)\r\n"
				+ "- Admitidos Paralelo C1: " + contadorC1 + "\r\n"
				+ "- Admitidos Paralelo C2:" + contadorC2 + "\r\n"
				+ "Rechazados totales: " + totalRechazos + " (" + porcentajeRechazo + "%)\r\n");
		
		
	}
	
	public static void agregarAAdmitidos(int posicion, boolean mensaje) {
		for (int i = 0; i < totalAdmitidos; i++) {
			if (rutsAdmitidos[i].equalsIgnoreCase(rutAlumnos[posicion])) {
				System.out.println("El alumno ya se encuentra registrado en el grupo");
				return;
			}
		}
		
		if (totalAdmitidos < 100) {
			nombresAdmitidos[totalAdmitidos] = nombreAlumnos[posicion];
			apellidosAdmitidos[totalAdmitidos] = apellidoAlumnos[posicion];
			rutsAdmitidos[totalAdmitidos] = rutAlumnos[posicion];
			paralelosAdmitidos[totalAdmitidos] = paraleloAlumnos[posicion];
			totalAdmitidos++;
			if (mensaje) {
				System.out.println("Inscripcion exitosa! " + nombreAlumnos[posicion] + " " + apellidoAlumnos[posicion] + " ha sido agregado a admitidos");
			}
		}
		else { 
			System.out.println("Capacidad de alumnos máxima.");
		}
	}
	
	public static void guardarAlumnos() {
		File archivo = new File("Alumnos.txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			for (int i = 0; i < totalAlumnos; i++) {
				String linea = nombreAlumnos[i] + ";" + apellidoAlumnos[i] + ";" + rutAlumnos[i] + ";" + paraleloAlumnos[i];
				bw.write(linea);
				bw.newLine();
			}
			System.out.println("El archivo Alumnos.txt se ha actualizado correctamente");
		} catch (IOException e) {
			System.out.println("Error al cargar el archivo");
		}
	}
	
	public static void eliminarAlumno() {
		Scanner pregunta4 = new Scanner(System.in);
		System.out.println("Ingrese el RUT del alumno a eliminar: ");
		String rutBusqueda = pregunta4.nextLine();
		
		int posicion2 = -1;
		for (int i = 0; i < totalAlumnos; i++) {
			if (rutAlumnos[i].equalsIgnoreCase(rutBusqueda)) {
				posicion2 = i;
				break;
			}
		}
		
		if (posicion2 == -1) {
			System.out.println("El alumno no existe en la lista de alumnos ingresados.");
			return;
		}
		
		for (int j = posicion2; j < totalAlumnos - 1; j++) {
			nombreAlumnos[j] = nombreAlumnos[j + 1];
			apellidoAlumnos[j] = apellidoAlumnos[j + 1];
			rutAlumnos[j] = rutAlumnos[j + 1];
			paraleloAlumnos[j] = paraleloAlumnos[j + 1];
		}
		totalAlumnos--;
		
		for (int k = 0; k < totalAdmitidos; k++) {
			if (rutsAdmitidos[k].equalsIgnoreCase(rutBusqueda)) {
				for (int l = k; l < totalAdmitidos-1; l++) {
					nombresAdmitidos[l] = nombresAdmitidos[l+1];
					apellidosAdmitidos[l] = apellidosAdmitidos[l+1];
					rutsAdmitidos[l] = rutsAdmitidos[l+1];
					paralelosAdmitidos[l] = paralelosAdmitidos[l+1];
				}
				totalAdmitidos--;
				System.out.println("El alumno que era miembro del grupo ha sido eliminado de los admitidos");
				break;
			}
		}
		guardarAlumnos();
		System.out.println("Alumno eliminado del Sistema.");
	}
	
	public static void cambiarParalelo() {
		Scanner pregunta5 = new Scanner(System.in);
		System.out.println("Ingrese el RUT del alumno: ");
		String rutBusqueda2 = pregunta5.nextLine();
		
		int posicion3 = -1;
		
		for (int i = 0; i < totalAlumnos; i++) {
			if (rutAlumnos[i].equalsIgnoreCase(rutBusqueda2)) {
				posicion3 = i;
				break;
			}
		}
		
		if (posicion3 == -1) {
			System.out.println("El alumno no existe en la lista de alumnos ingresados.");
			return;
		}
		String nuevoParalelo = "";
		do {
		System.out.println("Ingrese el nuevo paralelo del estudiante (C1/C2): ");
		nuevoParalelo = pregunta5.nextLine().toUpperCase();
		
		if (!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2")) {
			System.out.println("Ingrese un paralelo valido (C1/C2)");
		}
		} while(!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2"));
		
		paraleloAlumnos[posicion3] = nuevoParalelo;
		for (int j = 0; j < totalAdmitidos; j++) {
			if (rutsAdmitidos[j].equalsIgnoreCase(rutBusqueda2)) {
				paralelosAdmitidos[j] = nuevoParalelo;
				System.out.println("Se ha actualizado su paralelo");
				break;
			}
		}
		guardarAlumnos();
		System.out.println("Paralelo actualizado con exito a " + nuevoParalelo);
	}
	
	public static void inscribirAlumnoNuevo() {
		if (totalAlumnos >= 100) {
			System.out.println("Error. Máxima capacidad de alumnos alcanzada");
			return;
		}
		
		Scanner pregunta6 = new Scanner(System.in);
		System.out.println("Ingrese RUT del nuevo alumno ");
		String rutNuevo = pregunta6.nextLine();
		
		for (int i = 0; i < totalAlumnos; i++) {
			if(rutAlumnos[i].equalsIgnoreCase(rutNuevo)) {
				System.out.println("El alumno ya existe en la lista.");
				return;
			}
		}
		
		System.out.println("Ingrese el nombre: ");
		String nuevoNombre = pregunta6.nextLine();
		System.out.println("Ingrese el apellido: ");
		String nuevoApellido = pregunta6.nextLine();
		String nuevoParalelo = "";
		do {
			System.out.println("Ingrese el paralelo (C1/C2): ");
			nuevoParalelo = pregunta6.nextLine().toUpperCase();
			
			if (!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2")) {
				System.out.println("Ingrese un paralelo valido (C1/C2)");
			}
			} while(!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2"));
		nombreAlumnos[totalAlumnos] = nuevoNombre;
		apellidoAlumnos[totalAlumnos] = nuevoApellido;
		rutAlumnos[totalAlumnos] = rutNuevo;
		paraleloAlumnos[totalAlumnos] = nuevoParalelo;
		totalAlumnos++;
		
		guardarAlumnos();
		System.out.println(nuevoNombre + " " + nuevoApellido + " ha sido ingresado correctamente al paralelo " + nuevoParalelo);
	}
	
	public static void reporteC1() {
		File carpeta = new File("Reportes");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		
		String nombreArchivoC1 = "Reportes/ReporteC1-V" + reporteC1 + ".txt";
		try(BufferedWriter bwC1 = new BufferedWriter(new FileWriter(nombreArchivoC1))) {
			bwC1.write("=== Miembros del grupo - Paralelo C1 ===");
			bwC1.newLine();
			int contadorC1 = 0;
			for (int i = 0; i < totalAdmitidos; i++) {
				if(paralelosAdmitidos[i].equalsIgnoreCase("C1")) {
					String linea = nombresAdmitidos[i] + " " + apellidosAdmitidos[i] + " - " + rutsAdmitidos[i];
					bwC1.write(linea);
					bwC1.newLine();
					contadorC1++;
				}
			}
			System.out.println(nombreArchivoC1 + " generado con exito (" + contadorC1 + " alumnos)");
			reporteC1++;
		} catch (IOException e) {
			System.out.println("Error al generar " + nombreArchivoC1);
		}
	}
	
	public static void reporteC2() {
		File carpeta = new File("Reportes");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		
		String nombreArchivoC2 = "Reportes/ReporteC2-V" + reporteC2 + ".txt";
		try(BufferedWriter bwC2 = new BufferedWriter(new FileWriter(nombreArchivoC2))) {
			bwC2.write("=== Miembros del grupo - Paralelo C2 ===");
			bwC2.newLine();
			int contadorC2 = 0;
			for (int i = 0; i < totalAdmitidos; i++) {
				if(paralelosAdmitidos[i].equalsIgnoreCase("C2")) {
					String linea = nombresAdmitidos[i] + " " + apellidosAdmitidos[i] + " - " + rutsAdmitidos[i];
					bwC2.write(linea);
					bwC2.newLine();
					contadorC2++;
				}
			}
			System.out.println(nombreArchivoC2 + " generado con exito (" + contadorC2 + " alumnos)");
			reporteC2++;
		} catch (IOException e) {
			System.out.println("Error al generar " + nombreArchivoC2);
		}
	}
	
	public static void reporteRechazados() {
		File carpeta = new File("Reportes");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		String nombreArchivoRechazados = "Reportes/Rechazados-V" + reporteRechazados + ".txt";
		try(BufferedWriter bwRechazados = new BufferedWriter(new FileWriter(nombreArchivoRechazados))) {
			bwRechazados.write("=== Solicitudes rechazadas ===");
			bwRechazados.newLine();
			int contadorRechazados = 0;
			for (int i = 0; i < totalRechazados; i++) {
					String linea = nombresRechazados[i] + " " + apellidosRechazados[i] + " - No pertenece a ningun paralelo del curso";
					bwRechazados.write(linea);
					bwRechazados.newLine();
					contadorRechazados++;
			}
			
			for (int j = 0; j < totalSoloRutRechazados; j++) {
				String linea = "Sin nombre registrado, RUT: " + soloRutRechazados[j] + " - No pertenece a ningun paralelo del curso";
				bwRechazados.write(linea);
				bwRechazados.newLine();
				contadorRechazados++;
			}
			System.out.println(nombreArchivoRechazados + " generado con exito (" + contadorRechazados + " alumnos)");
			reporteRechazados++;
		} catch (IOException e) {
			System.out.println("Error al generar " + nombreArchivoRechazados);
		}
	}
}


