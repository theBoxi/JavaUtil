package ch.boxi.javaUtil.gui.ClickAction;

import java.awt.event.MouseListener;

public class ClickActionListener<T> implements MouseListener {
	
	private ClickAction<T> ca;
	private Selector<T> selector;
	
	public ClickActionListener(ClickAction<T> ca){
		this(ca, null);
	}
	
	public ClickActionListener(ClickAction<T> ca, Selector<T> selector){
		this.ca = ca;
		this.selector = selector;
	}
	
	public void setSelector(Selector<T> s){
		this.selector = s;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		T selected = selector.getSelected();
		if(e.getClickCount() == 1){
			ca.doOnClick(selected);
		}else if(e.getClickCount() == 2){
			ca.doOnDoubleClick(selected);
		}
	}
	
	public void mouseEntered(java.awt.event.MouseEvent e) {}
	public void mouseExited(java.awt.event.MouseEvent e) {}
	public void mousePressed(java.awt.event.MouseEvent e) {}
	public void mouseReleased(java.awt.event.MouseEvent e) {}

}
