package testing;
import java.util.*;

public class test {
	
	static void prompt()
	{
		Scanner YN;
		String ans;
		
	    System.out.println("\n"+"");
	    System.out.println("********************");
	    System.out.print("Do you want to encode a message again? Y/N: ");
	    YN = new Scanner(System.in);
	    ans = YN.nextLine();

        	if(ans.equals("Y") || ans.equals("y"))
        	{
        		System.out.println();
        		textTomorse();
        	}
        	else if(ans.equals("N") || ans.equals("n"))
        	{
        		System.out.println("Closed...\n");
        	}
        	else
        	{
        		System.out.println("Try again... \n");
        		prompt();
        	}
	}
	
	static void textTomorse() 
	{
		  Scanner x;
		  String arg;

		  String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
				  "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "/"};

		  
		  String[] morse2 = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
		  "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "/"};

		  char[] character = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' ,'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
		  'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

		  x = new Scanner(System.in);
		  System.out.print("********************\nMessage: ");
		  arg = x.nextLine();

		  String fetch = arg.toUpperCase();
		  System.out.print("OUTPUT:\t");

		  
			  for(int i = 0; i < fetch.length(); i++)
			  {
				  char akeep = fetch.charAt(i);
				  String conv = Character.toString(akeep);
				  	for(int p = 0; p < character.length; p++)
				  	{
				  		for(int j = akeep; j == character[p]; j++)
				  		{
				  			for(int u = 0; u < morse.length; u++)
				  			{
				  				for(int y = u; y == p; y++)
				  				{
				  					String encoded = morse[y];
				  					System.out.printf(encoded+" ");
				  				}
				  			}
				  		}
				  	}
			  }
		  prompt();
    }

	     
	
	public static void main(String[] args) 
	{
	    textTomorse();
	}
  
}
