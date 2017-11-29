import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Reference 
{

	public static void main(String[] args0)
	{
		String answer = getHTMLFromURL("https://www.google.com");
		System.out.print(answer);
	}


	public static String getHTMLFromURL(String url)
	{
		String answer = "";
		URL u;
	    InputStream is = null;
	    BufferedReader dis;
	    String s;

	    try
	    {
	      u = new URL(url);
	      is = u.openStream();
	      dis = new BufferedReader(new InputStreamReader(is));
		     
	      while ((s = dis.readLine()) != null)
	      {
	    	  answer += s + "\n";
	      }
	    }
	    catch (MalformedURLException mue)
	    {
	      System.err.println("Ouch - a MalformedURLException happened.");
	      mue.printStackTrace();
	      System.exit(2);
	    }
	    catch (IOException ioe)
	    {
	      System.err.println("Oops- an IOException happened.");
	      ioe.printStackTrace();
	      System.exit(3);
	    }
	    finally
	    {
	      try
	      {
	        is.close();
	      }
	      catch (IOException ioe)
	      {
	      }
	    }
		return answer;

	  }
}
