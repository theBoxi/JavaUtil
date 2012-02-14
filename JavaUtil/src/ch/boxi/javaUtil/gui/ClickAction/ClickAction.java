package ch.boxi.javaUtil.gui.ClickAction;

public interface ClickAction<T> {
	public void doOnClick(T selected);
	public void doOnDoubleClick(T selected);
}
