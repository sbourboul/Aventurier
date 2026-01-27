
public class Main
{
   /**
    * @param args
    */
   public static void main(String[] args)
   {
      //Adventurer adv = new Adventurer(args[0],args[1]);
      Adventurer adv = new Adventurer("/tmp/carte_v2.txt", "/tmp/test1.txt");
      adv.move();
      System.out.println(adv.showPosition());
      
      adv = new Adventurer("/tmp/carte_v2.txt", "/tmp/test2.txt"); 
      adv.move();
      System.out.println(adv.showPosition());
   }
}
