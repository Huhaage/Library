package fr.fms.entities;

public class Book {
	private int id;
	private String titel;
	private String author;
	private String publishingHouse;
	private boolean occasions;
	private float price;
	
	private int quantity=1;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book(int id, String titel, String author, String publishingHouse, boolean occasions, float price) {
		super();
		this.id = id;
		this.titel = titel;
		this.author = author;
		this.publishingHouse = publishingHouse;
		this.occasions = occasions;
		this.price = price;
	}

	public Book(String titel, String author, String publishingHouse, boolean occasions, float price) {
		super();
		this.titel = titel;
		this.author = author;
		this.publishingHouse = publishingHouse;
		this.occasions = occasions;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public boolean getOccasions() {
		return occasions;
	}

	public void setOccasions(boolean occasions) {
		this.occasions = occasions;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\nBook [id=" + id + ", titel=" + titel + ", author=" + author + ", publishingHouse=" + publishingHouse
				+ ", occasions=" + occasions + ", price=" + price + "]";
	}

	
}
