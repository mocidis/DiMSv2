package utils;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MousePageTitle;

public class PageTitle extends JPanel{
	private MousePageTitle listener = new MousePageTitle();
	
	public PageTitle(String objectType, JPanel numberService){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setPreferredSize(SubDimension.LINEBOX);
		this.setMaximumSize(this.getPreferredSize());
		
		JLabel title = new JLabel(Glossary.TITLE_PAGE+": ");
		JLabel object_name = new JLabel(objectType);
		object_name.setForeground(Color.decode(ColorBG.LINK));
		object_name.addMouseListener(listener);
		
		this.add(title, BorderLayout.LINE_START);
		this.add(object_name);
		this.add(numberService, BorderLayout.PAGE_END);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
	}
}
