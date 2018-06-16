import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
class SerializeObjects{
	private String nomtmp;
	public SerializeObjects(String nomtmp){
		this.nomtmp = nomtmp;
	}
	public void saveGame(Sogo sogo){
		try{
			FileOutputStream file = new FileOutputStream("Partie"+this.nomtmp+".ser");
			ObjectOutputStream obj = new ObjectOutputStream(file);
			obj.writeObject(sogo);
			obj.close();
		}catch(IOException e){
			System.out.print("Fichier inaccessible");
		}
	}

	
}