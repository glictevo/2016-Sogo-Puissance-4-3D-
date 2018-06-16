package application;

import java.io.Serializable;
/**
 * classe servant à gérer le choix du début à savoir Terminal ou Interface
 */
public class MonRunnable implements Runnable,Serializable{
	/**
	 * Dans le cas du choix Terminal 
	 * lancement d'une partie en mode terminal
	 */
	@Override
	public void run() {
		Test.main(new String[1]);
		System.exit(0);
	}

}
