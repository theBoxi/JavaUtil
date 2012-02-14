package ch.boxi.javaUtil.helper;
import java.util.Observable;
import java.util.Observer;


public class ObserverHelper {
	public static void registerObserverIfPossible(Observer observer, Object o){
		if(o != null && observer != null && isObservable(o)){
			Observable observable = (Observable) o;
			observable.addObserver(observer);
		}
	}
	
	public static void unregisterObserverIfPossible(Observer observer, Object o){
		if(o != null && observer != null && isObservable(o)){
			Observable observable = (Observable) o;
			observable.deleteObserver(observer);
		}
	}
	
	private static boolean isObservable(Object o){
		if(o != null){
			return Observable.class.isInstance(o);
		} else{
			return false;
		}
	}
	
}
