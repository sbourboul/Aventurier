import java.io.*;

import java.util.HashMap;

public class Adventurer
{
   private HashMap<Integer,Object> map;
   private Integer positionX;
   private Integer positionY;
   private String movements;

   /**
    * @param mapFilename
    */
   public Adventurer(String mapFilename, String movementsFilename)
   {
      positionX = 0;
      positionY = 0;
      movements = "";
      
      this.loadMap(mapFilename);
      this.loadMovements(movementsFilename);
   }

   /**
    * @param positionX
    */
   public void setPositionX(Integer positionX)
   {
      this.positionX = positionX;
   }

   /**
    * @param positionY
    */
   public void setPositionY(Integer positionY)
   {
      this.positionY = positionY;
   }

   /**
    * @param movements
    */
   public void setMovements(String movements)
   {
      this.movements = movements;
   }

   public Integer getPositionX()
   {
      return this.positionX;
   }

   public Integer getPositionY()
   {
      return this.positionY;
   }

   public String getMovements()
   {
      return this.movements;
   }

   public HashMap<Integer,Object> getMap()
   {
      return this.map;
   }

   private void loadMap(String mapFilename)
   {
      HashMap<Integer,Object> map = new HashMap<Integer,Object>();
      
      if (mapFilename == null || mapFilename.equalsIgnoreCase(""))
      {
         System.out.println("Veuillez renseigner le nom du fichier de la carte !");
         System.exit(0);
      }
      
      try
      {
         InputStream inputStream = new FileInputStream(mapFilename);
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
         BufferedReader buffer = new BufferedReader(inputStreamReader);

         String ligne = buffer.readLine();
         Integer numLigne = 0;
            
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
    * @param args
    */
   private void loadMovements(String movementsFilename)
   {
      if (movementsFilename == null || movementsFilename.equalsIgnoreCase(""))
      {
         System.out.println("Veuillez renseigner le nom du fichier de déplacement !");
         System.exit(0);
      }
      
      try
      {
         InputStream inputStream = new FileInputStream(movementsFilename);
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
         BufferedReader buffer = new BufferedReader(inputStreamReader);
            
         String ligne = buffer.readLine();
         Boolean firstLine = true;
            
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
   
   public void move()
   {
      Integer positionX = this.getPositionX();
      Integer positionY = this.getPositionY();
      String movements = this.getMovements();
      HashMap <Integer,Object> map = this.getMap();
      int i = 0;
      HashMap mapLine;
      
      while (i < movements.length())
      {
         String movement = String.valueOf(movements.charAt(i));
         
         switch (movement)
         {
            case "S":
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
               System.out.println("La direction indiquée est fausse (valeurs attendues: N,S,O,E) !");
               System.exit(0);
         }
         
         i++;
      }
      
      this.setPositionX(positionX);
      this.setPositionY(positionY);
   }
   
   public String showPosition()
   {
      return this.getPositionX() + "," + this.getPositionY();
   }
}