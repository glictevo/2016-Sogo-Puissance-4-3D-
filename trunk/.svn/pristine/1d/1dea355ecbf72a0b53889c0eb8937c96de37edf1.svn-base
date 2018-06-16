import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
class DeserializeObjects{

	public Sogo suiteGame(String nomfichier){
		try{
			FileInputStream file = new FileInputStream(nomfichier);
			ObjectInputStream obj = new ObjectInputStream(file);
			return((Sogo)(obj.readObject()));
		}catch(IOException e){
			System.out.println("Aucune partie en cours");
			Test tmp = new Test();
			tmp.main(new String[1]);
			System.exit(0);
		}catch(ClassNotFoundException e){
			System.out.println("Classe Sogo non trouvé");
		}
		return (new Sogo(new Terminal(),new ScannerInput()));
	}
}
