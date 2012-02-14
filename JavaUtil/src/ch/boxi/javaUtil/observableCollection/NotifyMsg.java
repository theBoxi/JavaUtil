package ch.boxi.javaUtil.observableCollection;

public class NotifyMsg {
	private Change change;
	private Object object;
	
	NotifyMsg(Change c, Object o){
		change = c;
		object = o;
	}

	public Change getChange() {
		return Change.valueOf(change.toString());
	}

	public Object getObject() {
		return object;
	}
}
