import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test_Processes 
{
	static int count = 0;
	private static final String FILENAME = "E:\\program file\\workspace\\Test_Processes\\src\\computers.txt";

	public static void main(String[] args)
	{
		while(true)
		{
		try 
		{
			//br = new BufferedReader(new FileReader(FILENAME));
			
			FileReader fr = new FileReader(FILENAME);;
			BufferedReader br = new BufferedReader(fr);;
			
		    Thread t1 = new Thread(new Runnable()
		    {
		        public void run()
		        {
		        	String sCurrentLine;
		        	
					try 
					{
						//read from file or any database
						while ((sCurrentLine = br.readLine()) != null)
						{
							System.out.println(sCurrentLine);
						}
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally 
					{
						try 
						{
							if (br != null)br.close();
							if (fr != null)fr.close();
						} 
						catch (IOException ex) 
						{
							ex.printStackTrace();
						}
					}//finallt end
		        }//void run end
		    });//thread end  
		    t1.start();
		    
		    t1.sleep(5000);
		}//main try end 
		catch (IOException e) 
		{
			System.err.println(e);
		}//catch end
		catch (InterruptedException e)
		{
			System.err.println(e);
		}
		count ++;
		System.out.println(count);
		}
	}//main end
}//class end
