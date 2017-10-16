/*
PolyAlphabetic class which brings plain text and 
encrypt with specific pattern. it uses 2 caesar 
ciphers C1,C2,C2,C1,C2 pattern, the key of C1 is 5 
C2 is 19.
@author jeongj
*/

import static java.lang.Character.isLetter;
import static java.lang.Character.isLowerCase;

public class PolyAlphabetic 
{
   private final int FIRSTKEY = 5;
   private final int SECKEY = 19;
   private final int FIRSTKEYUSED = 1;
   private final int FIRSTKEYUSED2 = 4;
   private final int PATTERNLENGTH = 5;
   private final String LOWERALPHABET = "abcdefghijklmnopqrstuvwxyz";
   private final String UPPERALPHABET = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
   
   /*
   encrypt method basically does encryption of plain text
   ciphers C1,C2,C2,C1,C2 pattern, the key of C1 is 5 
   C2 is 19.
   @param String plain plain text that will be encrypted
   */
   public String encrypt(String plain)
   {
      String cipherTxt = "";
      int trackPattern = 1;
       
      for(int i = 0; i < plain.length(); i++)
      {
         if(isLetter(plain.charAt(i)))
         {
            if( trackPattern % PATTERNLENGTH == FIRSTKEYUSED 
                || trackPattern % PATTERNLENGTH == FIRSTKEYUSED2)
            {
               char oldChar = plain.charAt(i);
               cipherTxt += shift(oldChar, FIRSTKEY);
            }
            else
            {
               char oldChar = plain.charAt(i);
               cipherTxt += shift(oldChar, SECKEY);
            }
            trackPattern++;
         }
         else
            cipherTxt += plain.charAt(i);
      }
      return cipherTxt;
   }
   
   private char shift(char oldChar, int key)
   {
      if(isLowerCase(oldChar))
      {
         int charPos = LOWERALPHABET.indexOf(oldChar);
         int keyVal = (key + charPos) % LOWERALPHABET.length();
         char newChar = LOWERALPHABET.charAt(keyVal);
         return newChar;
      }
      else    
      {
         int charPos = UPPERALPHABET.indexOf(oldChar);
         int keyVal = (key + charPos) % UPPERALPHABET.length();
         char newChar = UPPERALPHABET.charAt(keyVal);
         return newChar;
      }
   }
}


