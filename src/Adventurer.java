import java.io.*;

import java.util.HashMap;

/**
 * Class who contains the adventurer informations
 * 
 * @author Sylvain Bourboul
 */
public class Adventurer
{
   private HashMap<Integer,Object> map;
   private Integer positionX;
   private Integer positionY;
   private String movements;

   /**
    * Constructor of the adventurer's class
    * @param mapFilename
    * @param movementsFilename
    */
   public Adventurer(String mapFilename, String movementsFilename)
   {
      positionX = 0;
      positionY = 0;
      movements = "";
      
      // Load the map
      this.loadMap(mapFilename);
      
      //Load the movements
      this.loadMovements(movementsFilename);
   }

   /**
    * Update the attribute positionX
    * @param positionX
    */
   public void setPositionX(Integer positionX)
   {
      this.positionX = positionX;
   }

   /**
    * Update the attribute positionY
    * @param positionY
    */
   public void setPositionY(Integer positionY)
   {
      this.positionY = positionY;
   }

   /**
    * Update the attribute movements
    * @param movements
    */
   public void setMovements(String movements)
   {
      this.movements = movements;
   }

   /**
    * Get the attribute positionX
    * @return positionX
    */
   public Integer getPositionX()
   {
      return this.positionX;
   }

   /**
    * Get the attribute positionY
    * @return positionY
    */
   public Integer getPositionY()
   {
      return this.positionY;
   }

   /**
    * Get the attribute movements
    * @return movements
    */
   public String getMovements()
   {
      return this.movements;
   }

   /**
    * Get the attribute map
    * @return map
    */
   public HashMap<Integer,Object> getMap()
   {
      return this.map;
   }

   /**
    * Load the map in the attribute map
    * @param mapFilename
    */
   private void loadMap(String mapFilename)
   {
      HashMap<Integer,Object> map = new HashMap<Integer,Object>();
      
      //Check if the file name is filled
      if (mapFilename == null || mapFilename.equalsIgnoreCase(""))
      {
         System.out.println("Please specify the file name for the map !");
         System.exit(0);
      }
      
      try
      {
         // Init the buffer
         InputStream inputStream = new FileInputStream(mapFilename);
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
         BufferedReader buffer = new BufferedReader(inputStreamReader);

         String ligne = buffer.readLine();
         Integer numLigne = 0;
         
         // Read the file and put the values in a map
         while (ligne != null)
         {
            int i = 0;
            HashMap<Integer,String> ligneCarte = new HashMap<Integer,String>();
            
            while (i < ligne.length())
            {
               ligneCarte.put(i, String.valueOf(ligne.charAt(i)));
               i++;
            }
            
            map.put(numLigne, ligneCarte);
            numLigne++;
            
            ligne = buffer.readLine();
         }
         
         this.map = map;
      }
      catch (IOException e)
      {
         System.out.println(" erreur :" + e.toString());
         System.exit(0);
      }
   }

   /**
    * Load the starting position and the movements
    * @param movementsFilename
    */
   private void loadMovements(String movementsFilename)
   {
      //Check if the file name is filled
      if (movementsFilename == null || movementsFilename.equalsIgnoreCase(""))
      {
         System.out.println("Please specify the file name for the movements !");
         System.exit(0);
      }
      
      try
      {
         // Init the buffer
         InputStream inputStream = new FileInputStream(movementsFilename);
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
         BufferedReader buffer = new BufferedReader(inputStreamReader);
            
         String ligne = buffer.readLine();
         Boolean firstLine = true;

         // Read the file and put the values in the class attributes
         while (ligne != null)
         {
            if (firstLine)
            {
               String[] positions = ligne.split(",");
               
               this.positionX = Integer.valueOf(positions[0]);
               this.positionY = Integer.valueOf(positions[1]);
               firstLine = false;
            }
            else
            {
               this.movements = ligne;
            }

            ligne = buffer.readLine();
         }
      }
      catch (IOException e)
      {
         System.out.println(" erreur :" + e.toString());
         System.exit(0);
      }
   }
   
   /**
    * Adventurer's movement
    */
   public void move()
   {
      Integer positionX = this.getPositionX();
      Integer positionY = this.getPositionY();
      String movements = this.getMovements();
      HashMap <Integer,Object> map = this.getMap();
      int i = 0;
      HashMap mapLine;
      String initPosition;
      
      // Check the initial coordinates
      if (map.get(positionX) == null || map.get(positionY) == null)
      {
         System.out.println("The starting coordinates are not on the map !");
         System.exit(0);
      }

      mapLine = (HashMap) map.get(positionY);
      initPosition = (String) mapLine.get(positionX);
      
      if (initPosition.equalsIgnoreCase(" ") == false)
      {
         System.out.println("The starting coordinates are in an impenetrable forest !");
         System.exit(0);
      }
         
      while (i < movements.length())
      {
         String movement = String.valueOf(movements.charAt(i));
         
         switch (movement)
         {
            case "S":
               // Get the position and check if the movement is possible
               if (map.get(positionY + 1) != null)
               {
                  mapLine = (HashMap) map.get(positionY + 1);
                  String nextPosition = (String) mapLine.get(positionX);
                  
                  if (nextPosition.equalsIgnoreCase(" "))
                  {
                     positionY++;
                  }
               }

               break;
               
            case "N":
               // Get the position and check if the movement is possible
               if (map.get(positionY - 1) != null)
               {
                  mapLine = (HashMap) map.get(positionY - 1);
                  String nextPosition = (String) mapLine.get(positionX);
                  
                  if (nextPosition.equalsIgnoreCase(" "))
                  {
                     positionY--;
                  }
               }

               break;
            
            case "E":
               // Get the position and check if the movement is possible
               mapLine = (HashMap) map.get(positionY);
               
               if (map.get(positionX + 1) != null)
               {
                  String nextPosition = (String) mapLine.get(positionX + 1);
                  
                  if (nextPosition.equalsIgnoreCase(" "))
                  {
                     positionX++;
                  }
               }

               break;
            
            case "O":
               // Get the position and check if the movement is possible
               mapLine = (HashMap) map.get(positionY);
               
               if (map.get(positionX - 1) != null)
               {
                  String nextPosition = (String) mapLine.get(positionX - 1);
                  
                  if (nextPosition.equalsIgnoreCase(" "))
                  {
                     positionX--;
                  }
               }

               break;
            
            default:
               System.out.println("The indicated direction is incorrect (expected values: N, S, O, E) !");
               System.exit(0);
         }
         
         i++;
      }
      
      this.setPositionX(positionX);
      this.setPositionY(positionY);
   }
   
   /**
    * Show the new position of the adventurer
    * @return new position of the adventurer
    */
   public String showPosition()
   {
      return this.getPositionX() + "," + this.getPositionY();
   }
}