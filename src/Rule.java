import java.util.Arrays;
import java.util.*;
public class Rule {

	private int ruleNum;
	private boolean[] neighborhood; 
	 
	
	
	
	public Rule(int ruleNum)
	{
		if (ruleNum < 0)
		{
			this.ruleNum = 0;
		}
		else if(ruleNum > 255)
		{
			this.ruleNum = 255;
		}
		else 
		{
			this.ruleNum = ruleNum;
		}
		
	}
	
	public int getRuleNum()
	{
		return ruleNum;
	}
	
	public static boolean[] getNeighborhood(int idx, Generation gen)
	{
		
	
	boolean[] array = new boolean[3];
	
		if (gen.getStates().length == 1)
		{
			array[0] = gen.getState(0);
			array[1] = gen.getState(0);
			array[2] = gen.getState(0);
		}
		else if (idx == 0)
		{
			array[0] = gen.getState(gen.size()- 1);
			array[1] = gen.getState(idx);
			array[2] = gen.getState(1);
				
		}
		else if (idx == gen.size()-1)
		{
			array[0] = gen.getState(idx - 1);
			array[1] = gen.getState(idx);
			array[2] = gen.getState(0);
		}
		else
		{
			array[0] = gen.getState(idx - 1);
			array[1] = gen.getState(idx);
			array[2] = gen.getState(idx + 1);
		}
		
	return array;
		
	}
	public boolean evolve(boolean[] neighborhood)
	{
	
	String binary = Integer.toBinaryString(ruleNum);
			
	
	
	while (binary.length() < 8)
	{
		binary = "0" + binary;
	}
		
		if (neighborhood[0] == true && neighborhood[1] == true && neighborhood[2] == true)
		{
			if (binary.charAt(0) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (neighborhood[0] == true && neighborhood[1] == true && neighborhood[2] == false)
		{
			if (binary.charAt(1) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (neighborhood[0] == true && neighborhood[1] == false && neighborhood[2] == true)
		{
			if (binary.charAt(2) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		else if (neighborhood[0] == true && neighborhood[1] == false && neighborhood[2] == false)
		{
			if (binary.charAt(3) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == true && neighborhood[2] == true)
		{
			if (binary.charAt(4) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == true && neighborhood[2] == false)
		{
			if (binary.charAt(5) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == false && neighborhood[2] == true)
		{
			if (binary.charAt(6) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if (binary.charAt(7) == '1')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
		
	}
	public Generation evolve(Generation gen)
	{
		
		boolean[] states = new boolean[gen.size()];

		for (int i = 0; i < gen.size(); ++i)
		{
			states[i] = evolve(getNeighborhood(i, gen));
		}
		 return new Generation(states);
	}
	
}
