import java.io.*;
import java.util.*;
class Write{
	private PrintWriter file;
	private String nomtmp;
	
	public Write(String nomtmp){
		this.nomtmp = nomtmp;
	}
	
	public void write(String str){
		File f = new File("Tmp"+this.nomtmp+".txt");
		try{
			this.file = new PrintWriter(new BufferedWriter(new FileWriter(f,true)));
			this.file.println(str);
			this.file.close();
		}catch(IOException e){
			System.out.println ("Erreur lors de la lecture : " + e.getMessage());
		}
		this.file.println(str);
	}
	public String getName(){
		return("Tmp"+this.nomtmp+".txt");
	}
}