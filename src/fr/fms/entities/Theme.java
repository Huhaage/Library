package fr.fms.entities;

public class Theme {
	private int id;
	private String themeName;
	public Theme(int id, String themeName) {
		super();
		this.id = id;
		this.themeName = themeName;
	}
	public Theme(String themeName) {
		super();
		this.themeName = themeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	@Override
	public String toString() {
		return "Theme [id=" + id + ", themeName=" + themeName + "]";
	}
	public static String centerString(String str) {
		if(str.length() >= 20) return str;
		String dest = "                    ";
		int deb = (20 - str.length())/2 ;
		String data = new StringBuilder(dest).replace( deb, deb + str.length(), str ).toString();
		return data;
	}
}
