package autozone.com.principal;
import dbConnection.Inventario;
import dbConnection.Prestamos;
import dbConnection.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		//Biblioteca store = new Biblioteca();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		
		while (running) {
			try {
				System.out.println("\nSistema de Manejo de Gestión de la Biblioteca");
				System.out.println("\nSelecciona una opción válida");
				System.out.println("1. Inventario de libros");
				System.out.println("2. Manejo de catalogo de usuarios");
				System.out.println("3. Registrar un nuevo prestamo de libro");
				System.out.println("4. Salir");
				
				int choice = scanner.nextInt();
				//scanner.nextLine();
				
				switch (choice) {
				case 1:
					try {
					System.out.println("\nManejo de Inventario de Libros");
					System.out.println("\nIngresa la opción a realizar");
					System.out.println("1. Agregar un libro");
					System.out.println("2. Actualizar Información de un libro");
					System.out.println("3. Eliminar un libro del inventario");
					System.out.println("4. Buscar un libro en el inventario");
					System.out.println("5. Regresar al menu anterior");
					int choice2 = scanner.nextInt();
					scanner.nextLine();
					switch (choice2) {
						case 1:
							//insertar el libro
							System.out.println("\nInserta el titulo del libro");
							String titulo = scanner.nextLine();
							System.out.println("\nInserta el nombre del autor del libro a insertar");
							String autor = scanner.nextLine();							
							Inventario.insertarLibro(titulo,autor);
							break;
						case 2:
							Inventario.obtenerLibros();
							//poner logica para obtener id titulo y autor de scanner
							System.out.println("\nSelecciona el id del libro a actualizar");
				        	int idISBN = scanner.nextInt();
				        	scanner.nextLine();
				        	System.out.println("\nTeclea el nuevo titulo del libro ");
				        	String nTitulo = scanner.nextLine();
				        	System.out.println("\nTeclea el nuevo autor del libro");
				        	String nAutor = scanner.nextLine();
							Inventario.actualizaLibro(idISBN,nTitulo,nAutor);
							break;
						case 3:
							//eliminar libro
							Inventario.obtenerLibros();
							System.out.println("\nSelecciona el id del libro a eliminar");
				        	int id = scanner.nextInt();
				        	System.out.println("ID a eliminar=" +id);
				        	
							Inventario.eliminarLibro(id);
							break;
						case 4:
							//buscar libro
							Inventario.obtenerLibros();
							System.out.println("\n ¿Cuál campo quieres buscar?");
							System.out.println("\n 1. ISBN");
							System.out.println("\n 2. Titulo");
							System.out.println("\n 3. Autor");
				        	int opcion3 = scanner.nextInt();
				        	scanner.nextLine();
				        	switch (opcion3) {
				        	case 1:
				        		System.out.println("Ingresa el ISBN");
				        		int idISBN_ = scanner.nextInt();
				        		Inventario.buscarLibro(idISBN_,"", "");
				        		break;
				        	case 2:
				        		System.out.println("Ingresa el Nombre del libro a buscar");
				        		String titulo_ = scanner.nextLine();
				        		Inventario.buscarLibro(0,titulo_, "");
				        		break;
				        	case 3:
				        		System.out.println("Ingresa el Nombre del autor a buscar");
				        		String autor_ = scanner.nextLine();
				        		Inventario.buscarLibro(0,"", autor_);
				        		break;
				        	}
							break;
						case 5:
							break;
							}
						}
						catch (Exception exception) {
							System.out.println("La entrada no es válida, por favor ingresa un número válido?|Libros");
						}
						break;
				case 2:
						try {
							System.out.println("\nManejo de Usuarios");
							System.out.println("\nIngresa la opción a realizar");
							System.out.println("1. Agregar un usuario");
							System.out.println("2. Actualizar Información de un usuario");
							System.out.println("3. Eliminar un usuario del catalogo");
							System.out.println("4. Buscar un usuario en el catalogo");
							System.out.println("5. Regresar al menu anterior");
							int choice5 = scanner.nextInt();
							scanner.nextLine();
							switch (choice5) {
								case 1:
									//insertar el usuario
									System.out.println("\nInserta el nombre del usuario");
									String nombre = scanner.nextLine();							
									Usuarios.insertaUsuario(nombre);
									break;
								case 2:
									Usuarios.obtenerUsuarios();
									//poner logica para obtener id titulo y autor de scanner
									System.out.println("\nSelecciona el id del usuario a actualizar");
						        	int idUsuario = scanner.nextInt();
						        	scanner.nextLine();
						        	System.out.println("\nTeclea el nuevo nombre de usuario ");
						        	String nombreUsuario = scanner.nextLine();
									Usuarios.actualizaUsuario(idUsuario, nombreUsuario);
									break;
								case 3:
									//eliminar libro
									Usuarios.obtenerUsuarios();
									System.out.println("\nSelecciona el id del usuario a eliminar");
						        	int id = scanner.nextInt();
						        	System.out.println("ID a eliminar=" +id);
						        	
									Usuarios.eliminarUsuario(id);
									break;
								case 4:
									//buscar usuario
									System.out.println("\n ¿Cuál campo quieres buscar para buscar a un usuario?");
									System.out.println("\n 1. ID Usuario");
									System.out.println("\n 2. Nombre");
						        	int opcion5 = scanner.nextInt();
						        	scanner.nextLine();
						        	switch (opcion5) {
						        	case 1:
						        		System.out.println("Ingresa el Id de usuario");
						        		int idUsu_ = scanner.nextInt();
						        		Usuarios.buscarUsuario(idUsu_,"");
						        		break;
						        	case 2:
						        		System.out.println("Ingresa el Nombre del usuario a buscar");
						        		String nombre_ = scanner.nextLine();
						        		Usuarios.buscarUsuario(0,nombre_);
						        		break;
						        	}
									break;
								case 5:
									break;
									}
							break;
							}
						catch (Exception exception) {
							System.out.println("La entrada no es válida, por favor ingresa un número válido?|Usuarios");
						}
						break;
				case 3:
					//prestamos
					try {
						System.out.println("\nManejo de Prestamos de Libros");
						System.out.println("\nIngresa la opción a realizar en un numero entero");
						System.out.println("1. Registrar un prestamo de un libro");
						System.out.println("2. Registrar la devolucion de un libro");
						System.out.println("3. Ver historial de prestamos de un usuario");
						System.out.println("4. Ver disponibilidad de un libro");
						System.out.println("5. Regresar al menu anterior");
						int choice6 = scanner.nextInt();
						scanner.nextLine();
						switch (choice6) {
							case 1:
								//Registrar un prestamo
								System.out.println("\nUsuarios:");
								Usuarios.obtenerUsuarios();
								System.out.println("\nLibros:");
								Inventario.obtenerLibros();
								System.out.println("\nInserta id del usuario");
								int idUsu = scanner.nextInt();	
								System.out.println("\nInserta id del libro");
								int idLibro = scanner.nextInt();
								scanner.nextLine();
								System.out.println("\nInserta la fecha de inicio del prestamo| Formato de fecha: anio-mes-dia ej: 2024-07-11");
								String fechaIni = scanner.nextLine();
								System.out.println("fecha=" + fechaIni);
								try 
								{
									String fecha = "'" + fechaIni + "'";
								}
								catch (Exception exception) {
									System.out.println("La fecha no es válida, por favor ingresa el formato válido");
								} 
								System.out.println("\nInserta la fecha de inicio del prestamo| Formato de fecha: anio-mes-dia ej: 2024-07-11");
								String fechaFin = scanner.nextLine();
								try 
								{
									String fecha = "'" + fechaIni + "'";
									//System.out.println(fecha);
								}
								catch (Exception exception) {
									System.out.println("La fecha no es válida, por favor ingresa el formato válido");
								} 
								Prestamos.registrarPrestamo(idLibro,fechaIni,fechaFin,idUsu);
								break;
							case 2:
								System.out.println("\nUsuarios:");
								Usuarios.obtenerUsuarios();
								System.out.println("\nLibros:");
								Inventario.obtenerLibros();
								System.out.println("\nSelecciona el id del libro a devolver");
					        	int ISBN = scanner.nextInt();
					        	scanner.nextLine();
					        	System.out.println("\nInserta la fecha de inicio del prestamo");
								System.out.println("\nFormato de fecha: anio-mes-dia ej: 2024-07-11");
								LocalDate FechaDevolucion = LocalDate.now();
					        	System.out.println("\nSelecciona el id del usuario que devuelve");
					        	int idUsuario = scanner.nextInt();
					        	DateTimeFormatter FormatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					        	String fechaFormateada = FechaDevolucion.format(FormatoFecha);
					        	Prestamos.registraDevolucion(ISBN,fechaFormateada, idUsuario);
								break;
							case 3:
								//Buscar prestamos de un usuario
								System.out.println("\nUsuarios:");
								Usuarios.obtenerUsuarios();
								System.out.println("\nSelecciona el id del usuario a buscar");
					        	int idUsuario_3 = scanner.nextInt();					        	
					        	Prestamos.obtenerPrestamosPorUsuario(idUsuario_3);
								break;
							case 4:
								//Consulta disponibilidad de un libro
								System.out.println("\n ¿Cuál campo quieres buscar para buscar a un usuario?");
								System.out.println("\n 1. ID Usuario");
								System.out.println("\n 2. Nombre");
					        	int ISBN_4 = scanner.nextInt();
					        	scanner.nextLine();
					        	Prestamos.consultaDisponibilidadLibro(ISBN_4);
								break;
							case 5:
								break;
								}
						break;
						}
					catch (Exception exception) {
						System.out.println("La entrada no es válida, por favor ingresa un número válido?|Prestamos");
					}
					break;
				case 4:
					running = false;
					break;
					
				default:
					System.out.println("Selección incorrecta, por favor intentálo de nuevo");
					break;
				}
			}
			catch (NoSuchElementException exception) {
			System.out.println("La entrada no es válida, por favor ingresa un número válido");
			scanner.nextLine();
		}

	}
		scanner.close();
}
	
}
	
