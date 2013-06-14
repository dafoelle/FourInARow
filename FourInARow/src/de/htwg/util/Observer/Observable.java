/**
 * Four in a row
 * 
 * @author David Föllenz
 * @version 21/05/2013
 *  
 */

package de.htwg.util.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable implements IObservable  {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	@Override
	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	@Override
	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	@Override
	public void removeAllObservers() {
		subscribers.clear();
	}

	@Override
	public void notifyObservers() {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update();
		}
	}
}
