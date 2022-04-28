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
	
}
