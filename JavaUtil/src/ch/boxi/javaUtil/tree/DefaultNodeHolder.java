package ch.boxi.javaUtil.tree;
import ch.boxi.javaUtil.helper.ObserverHelper;

public class DefaultNodeHolder<T> extends TreeNode<DefaultNodeHolder<T> > {

	private T nodeObject;
	
	public DefaultNodeHolder(DefaultNodeHolder<T> parent, T nodeObject){
		super(parent);
		this.nodeObject = nodeObject;
		ObserverHelper.registerObserverIfPossible(this, nodeObject);
	}
	
	public DefaultNodeHolder(T nodeObject){
		this(null, nodeObject);
	}
	
	public T getNodeObject(){
		return nodeObject;
	}
	
	@Override
	protected DefaultNodeHolder<T> getThis() {
		return this;
	}
	
	public String toString(){
		return "DefaultNodeHolder: " + nodeObject.toString();
	}
	
	public boolean addChild(DefaultNodeHolder<T> child){
		return super.structureAddChild(child);
	}
	
	public boolean removeChild(DefaultNodeHolder<T> child){
		return super.structureRemoveChild(child);		
	}

}
