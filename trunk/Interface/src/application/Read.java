package application;

import java.io.*;
/**
 * classe servant à la lecture d'un fichier
 */
class Read{
  private static int curs = 0;
  /**
   * méthode servant à la lecture d'une ligne d'un fichier
   * @param file correspond au nom du fichier à lire
   */
  public String read(String file){
    File f = new File(file);
    String line = null;
    try{
      
      FileReader fr = new FileReader (f);
      BufferedReader br = new BufferedReader (fr);
 
      try{
        for(int i = 0; i < curs; i++){
          br.readLine();
        }
        line = br.readLine();
        curs++;
        br.close();
        fr.close();
      }

      catch (IOException exception){
        
        exception.getMessage();
      }
    }catch (FileNotFoundException exception){
      System.out.println("Le fichier n'existe pas");
    }
    return line;
  }
  /**
   * méthode servant de relais à la methode read
   * @param file nom du fichier à lire
   * @return la ligne qui a été lu
   */
  public String readLine(String file){
	  return((read(file).split(" : "))[1]);
  }
  /*public void clearLine(String file){
		Write tmpFile = new Write("5");
		boolean verif = false;
		String line = null;
		do{
			line = read(file);
			tmpFile.write(line);
			if(line == null){
				verif = true;
			}
			
		}while(!verif);
  }*/
}