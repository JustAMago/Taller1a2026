SISTEMA DE CONTROL DEL GRUPO POO
DESCRIPCION DEL PROYECTO
El Sistema de Control del Grupo POO es una aplicacion de consola desarrollada en Java para automatizar y gestionar el proceso de inscripcion, filtrado y administracion de estudiantes en una asignatura de Programacion Orientada a Objetos.

El programa permite:

Lectura de archivos planos: Carga y lectura masiva de listas de estudiantes matriculados (Alumnos.txt) y postulantes (Solicitudes.txt).

Procesamiento y filtrado automatico: Cruce de datos para determinar admisiones y rechazos segun las listas oficiales.

Inscripcion manual: Registro directo por nombre o RUT para excepciones.

Administracion del curso: Modificacion de paralelos (C1/C2), eliminacion e inscripcion de nuevos estudiantes.

Generacion de reportes: Exportacion automatizada de archivos .txt en la carpeta Reportes versionados correlativamente.

Analisis estadistico: Muestra el calculo porcentual de admitidos y rechazados, desglosado por paralelos.

INTEGRANTES:

Nombre completo: Enzo Salvatore Cornieles Medina

RUT: 26.814.980-5

Usuario de GitHub: JustAMago

ESTRUCTURA DEL PROYECTO

Estructura de archivos:

src/Taller1/Main.java: Archivo de codigo fuente principal.

Alumnos.txt: Archivo de entrada con la lista oficial de alumnos (Formato: Nombre;Apellido;RUT;Paralelo).

Solicitudes.txt: Archivo de entrada con las postulaciones (Formato: Nombre-Apellido).

Reportes/: Carpeta donde se guardan los archivos generados por el programa.

README.md: Documentacion del proyecto.

Paquetes y clases principales:

Paquete Taller1:

Main.java: Clase principal que contiene el metodo main, la logica del menu interactivo, las funciones de lectura/escritura de archivos y la administracion de los arreglos en memoria.

INSTRUCCIONES DE EJECUCION DESDE ECLIPSE

Preparar el proyecto en Eclipse:

Abre Eclipse IDE.

Crea un nuevo proyecto Java (File > New > Java Project) con el nombre Taller1.

Dentro de la carpeta src del proyecto, crea un paquete llamado Taller1.

Coloca la clase Main.java dentro del paquete Taller1.

Ubicar los archivos de entrada:

Copia los archivos Alumnos.txt y Solicitudes.txt en la carpeta raiz del proyecto (al mismo nivel que la carpeta src, no dentro de ella).

Ejecutar la aplicacion:

En el explorador de paquetes (Package Explorer), haz clic derecho sobre el archivo Main.java.

Selecciona Run As > Java Application (o presiona Ctrl + F11).

Uso del programa:

El menu interactivo se mostrara en la pestaña Console de Eclipse.

Utiliza la Opción 1 primero para cargar los archivos de texto antes de procesar solicitudes, hacer modificaciones o generar reportes.
