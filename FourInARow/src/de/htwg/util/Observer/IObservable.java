package de.htwg.util.Observer;

/**
 * Four in a row
 * 
 * @author David Föllenz
 * @version 21/05/2013
 *  
 */

public interface IObservable {

	/**
	 * @param s
	 */
	void addObserver(IObserver s);

	/**
	 * @param s
	 */
	void removeObserver(IObserver s);

	/**
	 * removes all observers
	 */
	void removeAllObservers();

	/**
	 * notifies all observers
	 */
	void notifyObservers();

}