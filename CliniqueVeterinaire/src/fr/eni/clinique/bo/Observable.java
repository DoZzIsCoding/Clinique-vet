package fr.eni.clinique.bo;

import java.util.ArrayList;

public class Observable<T> {

	private T valeur;
	
	public T getValeur() {
		return valeur;
	}

	public void setValeur(T valeur) {
		this.valeur = valeur;
		notifyToutLesListeners();
	}
	
	public Observable(T value) {
		this.valeur = value;
	}
	
	public interface ObservableListener {
		void onChanged();
	}
	
	private ArrayList<ObservableListener> listeners = new ArrayList<>();
	
	public void registerListener(ObservableListener listener) {
		listeners.add(listener);
	}
	
	public void unregisterListener(ObservableListener listener) {
		listeners.remove(listener);
	}
	
	//avertit tous les listeners que la valeur a changé
	private void notifyToutLesListeners() {
		for(ObservableListener listener : listeners) {
			listener.onChanged();
		}
	}
	
	
}
