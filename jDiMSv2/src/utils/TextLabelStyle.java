package utils;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JLabel;

public final class TextLabelStyle{
	public static void makeStrikeThrough(JLabel label){
		Font font = label.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.STRIKETHROUGH_ON);
		label.setFont(font.deriveFont(attributes));
	}
	
	public static void makeUnderLine(JLabel label){
		Font font = label.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label.setFont(font.deriveFont(attributes));
	}
}
