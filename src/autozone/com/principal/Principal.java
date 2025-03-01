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
				System.out.println("3. Préstamos");
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
							//generar isbn aleatorio
							long isbn = (long)((Math.random() * 9999999999999L) + 1000000000000L);
				        	Inventario.insertarLibro(titulo,autor,isbn);
							break;
						case 2:
							if(Inventario.obtenerLibros()==0) {
								System.out.println("No hay libros dados de alta");
								break;}
							//ACTUALIZAR LIBRO
							System.out.println("\nSelecciona el id del libro a actualizar");
				        	long idISBN = scanner.nextLong();
				        	scanner.nextLine();
				        	System.out.println("\nTeclea el nuevo titulo del libro ");
				        	String nTitulo = scanner.nextLine();
				        	System.out.println("\nTeclea el nuevo autor del libro");
				        	String nAutor = scanner.nextLine();
				        	if (idISBN >0) {
				        		Inventario.actualizaLibro(nTitulo,nAutor,idISBN);}
				        	else {
				        		System.out.println("\nIngresa un valor valido de Isbn");}
							break;
						case 3:
							//eliminar libro
							if(Inventario.obtenerLibros()==0) {
								System.out.println("No hay libros dados de alta");
								break;}
							System.out.println("\nSelecciona el id del libro a eliminar");
				        	long id = scanner.nextLong();
				        	if (id >0) {
				        		Inventario.eliminarLibro(id);}
				        	else {
				        		System.out.println("\nIngresa un valor valido de Isbn");}
							break;
						case 4:
							System.out.println("\n ¿Cuál campo quieres buscar?");
							System.out.println("\n 1. ISBN");
							System.out.println("\n 2. Titulo");
							System.out.println("\n 3. Autor");
				        	int opcion3 = scanner.nextInt();
				        	scanner.nextLine();
				        	switch (opcion3) {
				        	case 1:
				        		System.out.println("Ingresa el ISBN");
				        		long idISBN_ = scanner.nextLong();
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
									//eliminar usuario
									if(Usuarios.obtenerUsuarios()==0){
										System.out.println("\nNo hay usuarios dados de alta");
										break;
									};
									//poner logica para obtener id titulo y autor de scanner
									System.out.println("\nSelecciona el id del usuario a actualizar");
						        	int idUsuario = scanner.nextInt();
						        	scanner.nextLine();
						        	System.out.println("\nTeclea el nuevo nombre de usuario ");
						        	String nombreUsuario = scanner.nextLine();
									Usuarios.actualizaUsuario(idUsuario, nombreUsuario);
									break;
								case 3:
									//eliminar usuario
									if(Usuarios.obtenerUsuarios()==0){
										System.out.println("\nNo hay usuarios dados de alta");
										break;
									};
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
								//eliminar usuario
								if(Usuarios.obtenerUsuarios()==0){
									System.out.println("\nNo hay usuarios dados de alta");
									break;
								};
								System.out.println("\nLibros:");
								if(Inventario.obtenerLibros()==0) {
									System.out.println("No hay libros dados de alta");
									break;}
								System.out.println("\nInserta id del libro");
								long idLibro = scanner.nextLong();
								if (!Prestamos.consultaDisponibilidadLibro(idLibro)) {
									System.out.println("\nLibro no disponible :(");
									break;
								}
								System.out.println("\nInserta id del usuario");
								int idUsu = scanner.nextInt();	
								scanner.nextLine();
								System.out.println("\nInserta la fecha de entrega del prestamo| Formato de fecha: año-mes-dia ej: 2024-07-11");
								String fechaFin = scanner.nextLine();
								Prestamos.registrarPrestamo(idLibro,fechaFin,idUsu);
								break;
							case 2:
								//registrar la devolucion
								System.out.println("\nUsuarios: ");
								//eliminar usuario
								if(Usuarios.obtenerUsuarios()==0){
									System.out.println("\nNo hay usuarios dados de alta");
									break;
								};
								System.out.println("\nLibros: ");
								if(Inventario.obtenerLibros()==0) {
									System.out.println("No hay libros dados de alta");
									break;}
								System.out.println("\nPrestamos actuales: ");
								Prestamos.obtenerPrestamos();
								System.out.println("\nSelecciona el id del libro a devolver");
					        	long ISBN = scanner.nextLong();
					        	scanner.nextLine();
					        	//Se toma fecha de hoy para la devolucion
								LocalDate FechaDevolucion = LocalDate.now();
					        	System.out.println("\nSelecciona el id del usuario que devuelve");
					        	int idUsuario = scanner.nextInt();
					        	DateTimeFormatter FormatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					        	String fechaFormateada = FechaDevolucion.format(FormatoFecha);
					        	System.out.println(fechaFormateada);
					        	Prestamos.registraDevolucion(ISBN,fechaFormateada, idUsuario);
								break;
							case 3:
								//Buscar prestamos de un usuario
								System.out.println("\nUsuarios:");
								//eliminar usuario
								if(Usuarios.obtenerUsuarios()==0){
									System.out.println("\nNo hay usuarios dados de alta");
									break;
								};
								System.out.println("\nSelecciona el id del usuario a buscar");
					        	int idUsuario_3 = scanner.nextInt();
					        	scanner.nextLine();
					        	Prestamos.obtenerPrestamosPorUsuario(idUsuario_3);
								break;
							case 4:
								//Consulta disponibilidad de un libro
								System.out.println("\nLibros: ");
								if(Inventario.obtenerLibros()==0) {
									System.out.println("No hay libros dados de alta");
									break;}
								System.out.println("\n Digita el ID del libro a consultar su disponibilidad");
					        	long ISBN_4 = scanner.nextLong();
					        	scanner.nextLine();
					        	boolean disponible = Prestamos.consultaDisponibilidadLibro(ISBN_4);
					        	if (disponible) {
					        		System.out.println("Libro disponible :)");
					        	}
					        	else {
					        		System.out.println("Libro no disponible :(");
					        	}
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
	
