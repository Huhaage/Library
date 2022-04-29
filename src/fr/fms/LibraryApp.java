package fr.fms;

import java.util.Scanner;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Book;
import fr.fms.entities.Theme;

public class LibraryApp {
	private static Scanner scan = new Scanner(System.in); 
	private static IBusinessImpl business = new IBusinessImpl();
	
	private static int idCustomer = 0;
	private static String login = null;
	
	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenu dans ma boutique, voici la liste des livre en stock en stock\n");
		displayBooks();
		int choice = 0;
		while(choice != 8) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
				case 1 : addBook();				
					break;					
				case 2 : remBook();
					break;					
				case 3 : displayCart(true);
					break;					
				case 4 : displayBooks();
					break;						
				case 5 : displayThemes();
					break;
				case 6 : displayBooksByThemeId();
					break;
				case 7 : connection();
					break;
				case 8 : System.out.println("A  bientôt dans notre boutique :)");
					break;					
				default : System.out.println("veuillez saisir une valeur entre 1 et 8");
			}
		}
	}
	
	public static void displayMenu() {	
		if(login != null)	System.out.print("Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter un livre au panier");
		System.out.println("2 : Retirer un livre du panier");
		System.out.println("3 : Afficher le contenu de mon panier, le total et passer commande");
		System.out.println("4 : Afficher tous les livres en stock");
		System.out.println("5 : Afficher toutes les theme en base");
		System.out.println("6 : Afficher tous les livres d'un theme");
		System.out.println("7 : Connexion à  votre compte");
		System.out.println("8 : sortir de l'application");
	}
	
	public static void displayBooks() { 		
		String titles = Book.centerString("IDENTIFIANT") + Book.centerString("TITRE") + Book.centerString("AUTEUR") + Book.centerString("MAISON DE PUBLIQUATION") + Book.centerString("OCCATION") + Book.centerString("PRIX");
		System.out.println(titles);
		business.readBook().forEach(System.out::println);
	}
	
	private static void displayBooksByThemeId() {
		System.out.println("saisissez l'id du theme concerné");
		int id = scanInt();
		Theme theme = business.readOneTheme(id);
		if(theme != null) {
			System.out.println("Theme : " + theme.getThemeName());
			String titles = Book.centerString("IDENTIFIANT") + Book.centerString("TITRE") + Book.centerString("AUTEUR") + Book.centerString("MAISON DE PUBLIQUATION") + Book.centerString("OCCATION") + Book.centerString("PRIX");
			System.out.println(titles);
			business.readBookByThemeId(id).forEach(System.out::println);
		}
		else System.out.println("ce theme n'existe pas !");
	}
	
	private static void displayThemes() {
		String titles = Theme.centerString("IDENTIFIANT") + Theme.centerString("NOM");
		System.out.println(titles);
		business.readTheme().forEach(System.out::println);		
	}
	
	public static void addBook() {
		System.out.println("Selectionner l'id de l'article à  ajouter au panier");
		int id = scanInt();
		Book book = business.readOneBook(id);
		if(book != null) {
			business.addToCart(book);
			displayCart(false);
		}
		else System.out.println("l'article que vous souhaitez ajouter n'existe pas, pb de saisi id");
	} 
	
	public static void remBook() {
		System.out.println("Selectionner l'id de l'article Ã  supprimer du panier");
		int id = scanInt();
		business.removemFromCart(id);
		displayCart(false);
	}
	
	private static void displayCart(boolean flag) {
		if(business.isCartEmpty()) 	System.out.println("PANIER VIDE");
		else {
			System.out.println("CONTENU DU PANIER :");
			String titles = Book.centerString("IDENTIFIANT") + Book.centerString("TITRE") + Book.centerString("AUTEUR")
			+ Book.centerString("MAISON DE PUBLIQUATION") + Book.centerString("OCCATION") + Book.centerString("PRIX") + Book.centerString("QUANTITE");
			System.out.println(titles);
			business.getCart().forEach(a -> System.out.println(a.toString() + Book.centerString(String.valueOf(a.getQuantity()))));
			if(flag) {
				System.out.println("MONTANT TOTAL : " + business.getTotal());
				System.out.println("Souhaitez vous passer commande ? Oui/Non :");
				if(scan.next().equalsIgnoreCase("Oui")) {
					if(login == null)	{ 
						System.out.println("Vous devez étre connecté pour continuer");
						connection();
					}
					if(login != null) {
						System.out.println(idCustomer);
						int idOrder = business.order(idCustomer);
						if(idOrder == 0)	System.out.println("problème lors du passage de commande");
						else {
							System.out.println("Votre commande a bien été validé, voici son numéro : " + idOrder);
							business.clearCart();
						}
					}
				}
			}
		}
	}
	
	private static void connection() {
		if(login != null)	System.out.println("vous ètes déjà  connecté");
		else {
			System.out.println("saisissez votre login : ");
			String log = scan.next();
			System.out.println("saisissez votre password : ");
			String pwd = scan.next();
			
			int id = business.existCustomer(log,pwd);
			if(id > 0)	{
				login = log;
				idCustomer = id;
			}
			else System.out.println("login ou password incorrect");
		}
	}
	
	public static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
	}
}
