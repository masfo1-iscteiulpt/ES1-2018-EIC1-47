package enums;

import java.awt.Color;

public enum ServiceType {

	FB(new Color(74, 110, 170)), TW(new Color(29, 161, 243)), GM(new Color(193, 64, 63)), BDA(new Color(255, 138, 0));
	
	private Color color;
	
	ServiceType(Color c){
		this.color = c;
	}
	
	public Color color() {
		return color;
	}
}
