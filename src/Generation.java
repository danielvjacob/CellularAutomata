import java.util.Arrays;
public class Generation {

	private boolean[] cellStates;
	
	
	
	public Generation(boolean...states)
	{
		if (states == null || states.length == 0)
		{
			cellStates = new boolean[] {false};
		}
		else
		{
			
			cellStates = Arrays.copyOf(states, states.length);
		}
		
	}
	
	public Generation(String states, char trueSymbol)
	{
		
		if (states == "" || states == null)
		{
			cellStates = new boolean[] {false};
		}
		
		else
		{
			cellStates = new boolean[states.length()];
			
			for (int i = 0; i < states.length(); ++i)
			{
			if (states.charAt(i) == trueSymbol)
			{
				cellStates[i] = true;
			}
			else
			{
				cellStates[i] = false;
			}
			}
		}
	}
	
	public boolean getState(int idx)
	{
		return cellStates[idx];
	}
	
	public boolean[] getStates()
	{
		return Arrays.copyOf(cellStates, cellStates.length);
	}
	
	public String getStates(char falseSymbol, char trueSymbol)
	{
		String code = "";

		for (int i = 0; i < cellStates.length; ++i)
		{
			if (cellStates[i] == false)
			{
				code = code + falseSymbol;
			}
			else
			{
				code = code + trueSymbol;
			}
		}
		return code;
	}
	
	public int size()
	{
		return cellStates.length;
	}

	
	
	
	
}
