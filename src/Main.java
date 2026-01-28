import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;


/**
 * Main class of the program
 *
 * @author Sylvain Bourboul
 */
public class Main
{
   /**
    * @param args
    * The first parameter contains the file path for the map
    * The second parameter contains the file path for the movements
    * If no parameters are entered, the test programme will be launched
    */
   public static void main(String[] args)
   {
      if (args.length == 0)
      {
         Test();
      }
      else
      {
         Adventurer adv = new Adventurer(args[0],args[1]);
         adv.move();
         System.out.println("The character is in (" + adv.showPosition() + ")");         
      }
   }
   
   public static void Test()
   {
      String tmpdir = System.getProperty("java.io.tmpdir");
      PrintStream in = null;
      String mapFilename = tmpdir + "/carte_v2.txt";
      
      try
      {
         OutputStream ips = new FileOutputStream(mapFilename);
         in = new PrintStream(ips);
         in.print("###    ######    ###\n");
         in.print("###      ##      ###\n");
         in.print("##     ##  ##     ##\n");
         in.print("#      ##  ##      #\n");
         in.print("##                ##\n");
         in.print("#####          #####\n");
         in.print("###### ##  ##  #####\n");
         in.print(" #     ######     # \n");
         in.print("     ########       \n");
         in.print("    ############    \n");
         in.print("    ############    \n");
         in.print("     ########      #\n");
         in.print(" #     ######     ##\n");
         in.print("###### ##  ## ######\n");
         in.print("#####          #####\n");
         in.print("##                ##\n");
         in.print("#   ## #    # ##   #\n");
         in.print("##   ##      ##   ##\n");
         in.print("###    #    #    ###\n");
         in.print("###    ######    ###\n");
         in.flush();
         in.close();
         
         ips = new FileOutputStream(tmpdir + "/test1.txt");
         in = new PrintStream(ips);
         in.print("3,0");
         in.print("\n");
         in.print("SSSSEEEEEENN");
         
         in.flush();
         in.close();

         ips = new FileOutputStream(tmpdir + "/test2.txt");
         in = new PrintStream(ips);
         in.print("6,7");
         in.print("\n");
         in.print("OONOOOSSO");
         
         in.flush();
         in.close();
      }
      catch(Exception e)
      {
         in.flush();
         in.close();
      }
      
      Adventurer adv = new Adventurer(mapFilename, tmpdir + "/test1.txt");
      adv.move();
      System.out.println("The character is in (" + adv.showPosition() + ")");

      adv = new Adventurer(mapFilename, tmpdir + "/test2.txt");
      adv.move();
      System.out.println("The character is in (" + adv.showPosition() + ")");

   }
}
