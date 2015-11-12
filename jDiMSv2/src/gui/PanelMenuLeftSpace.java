package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ColorBG;
import utils.DIMS;

public class PanelMenuLeftSpace extends JPanel{
	private Dimension text = new Dimension(200, 50);
	private Dimension icon = new Dimension(15, 15);
	private Dimension title = new Dimension(200, 30);
	public PanelMenuLeftSpace() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		try {
			//Add notice
			JPanel nnOk = new JPanel(new FlowLayout(FlowLayout.LEFT));
			nnOk.setPreferredSize(text);
			nnOk.setMaximumSize(nnOk.getPreferredSize());
			nnOk.setBackground(Color.decode(ColorBG.SUBMENU));
			JPanel nOk = new JPanel();
			nOk.setBackground(Color.decode(ColorBG.OK));
			nOk.setPreferredSize(icon);
			JLabel Ok = new JLabel("OK");
			nnOk.add(nOk);
			nnOk.add(Ok);
			
			JPanel nnWarning = new JPanel(new FlowLayout(FlowLayout.LEFT));
			nnWarning.setPreferredSize(text);
			nnWarning.setMaximumSize(nnWarning.getPreferredSize());
			nnWarning.setBackground(Color.decode(ColorBG.SUBMENU));
			JPanel nWarning = new JPanel();
			nWarning.setBackground(Color.decode(ColorBG.WARNING));
			nWarning.setPreferredSize(icon);
			JLabel Warning = new JLabel("Warning");
			nnWarning.add(nWarning);
			nnWarning.add(Warning);
			
			JPanel nnCritical = new JPanel(new FlowLayout(FlowLayout.LEFT));
			nnCritical.setPreferredSize(text);
			nnCritical.setMaximumSize(nnOk.getPreferredSize());
			nnCritical.setBackground(Color.decode(ColorBG.SUBMENU));
			JPanel nCritical = new JPanel();
			nCritical.setBackground(Color.decode(ColorBG.CRITICAL));
			nCritical.setPreferredSize(icon);
			JLabel Critical = new JLabel("Critical");
			nnCritical.add(nCritical);
			nnCritical.add(Critical);
			
			JPanel nnPending = new JPanel(new FlowLayout(FlowLayout.LEFT));
			nnPending.setPreferredSize(text);
			nnPending.setMaximumSize(nnOk.getPreferredSize());
			nnPending.setBackground(Color.decode(ColorBG.SUBMENU));
			JPanel nPending = new JPanel();
			nPending.setBackground(Color.decode(ColorBG.PENDING));
			nPending.setPreferredSize(icon);
			JLabel Pending = new JLabel("Pending");
			nnPending.add(nPending);
			nnPending.add(Pending);
			
			JPanel nnUnknown = new JPanel(new FlowLayout(FlowLayout.LEFT));
			nnUnknown.setPreferredSize(text);
			nnUnknown.setMaximumSize(nnOk.getPreferredSize());
			nnUnknown.setBackground(Color.decode(ColorBG.SUBMENU));
			JPanel nUnknown = new JPanel();
			nUnknown.setBackground(Color.decode(ColorBG.UNKNOWN));
			nUnknown.setPreferredSize(icon);
			JLabel Unknown = new JLabel("Unknown");
			nnUnknown.add(nUnknown);
			nnUnknown.add(Unknown);
			
			JPanel title  = new JPanel(new FlowLayout(FlowLayout.LEFT));
			title.setPreferredSize(new Dimension(200, 50));
			title.setMaximumSize(title.getPreferredSize());
			title.setBackground(Color.decode(ColorBG.SUBMENU));
			JLabel titleLabel = new JLabel("System core is "+DIMS.getInstance().getCoreStatus());
			titleLabel.setForeground(Color.decode(ColorBG.CRITICAL));
			title.add(titleLabel);
			
			//Add notice to this
			this.add(title);
			this.add(nnOk);
			this.add(nnWarning);
			this.add(nnCritical);
			this.add(nnPending);
			this.add(nnUnknown);
			JPanel tail = new JPanel();
			tail.setBackground(Color.decode(ColorBG.SUBMENU));
			this.add(tail);
		} catch (SQLException e) {
			new FrameNotification(e.getMessage());
		}
	}
}
