import java.io.*;
class Read{
  private static int curs = 0;
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
  public String readLine(String file){
   return((read(file).split(" : "))[1]);
  }
}