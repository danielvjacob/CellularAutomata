import java.util.Scanner;

public class ConsoleApp {

	/*
	 * NOTE: You do not need to modify any lines at or before 
	 * the printOptions() method.
	 */
	
	private static final Generation DEFAULT_GENERATION = new Generation(false, false, false, true, false, false, false);
	private static final int DEFAULT_RULE = 22;

	private Automaton automaton;
	private Scanner input;

	public ConsoleApp() {
		automaton = new Automaton(DEFAULT_RULE, DEFAULT_GENERATION);
		input = new Scanner(System.in);
	}

	public void run() {
		Option option; // store user's input Option

		do {
			// Show possible options
			printOptions();

			// Get user input
			System.out.print("Select option > ");

			try {
				int value = input.nextInt();
				option = Option.fromInt(value);
			} catch (Exception e) {
				// Quit on an invalid input (e.g., lack of input)
				option = Option.QUIT;
			}

			// Process user input
			processOption(option);

			// Print a blank line between each action
			System.out.println();
		} while (option != Option.QUIT);
	}

	private void processOption(Option option) {
		switch (option) {
			case PRINT_RULE:
				printRule();
				break;
			case REINIT_AUTOMATON:
				reinitAutomaton();
				break;
			case EVOLVE:
				evolve();
				break;
			case SET_TRUE_SYMBOL:
				setTrueSymbol();
				break;
			case SET_FALSE_SYMBOL:
				setFalseSymbol();
				break;
			case PRINT_CURRENT_GENERATION:
				printCurrentGeneration();
				break;
			case PRINT_FULL_EVOLUTION:
				printFullEvolution();
				break;
			case QUIT:
				printQuitMessage();
				break;
		}
	}

	private static void printOptions() {
		Option[] options = Option.values();
		for (Option o : options) {
			System.out.println(o.index() + ": " + o);
		}
	}

	/* You will need to implement the methods below. */

	private void printRule() {
		// TODO: Print out the current rule in the format specified by
		// the README.
		
		System.out.print("Rule: " + automaton.getRuleNum());
	}

	private void reinitAutomaton() {
		// This method's implementation is provided for you.
		setTrueSymbol();
		setFalseSymbol();
		setRuleAndGeneration();
	}

	private void setRuleAndGeneration() {
		// TODO: Prompt the user to enter a new rule number and initial
		// generation. Refer to the README for details.
		
		
		System.out.print("Enter new rule number > ");
		int newRule = input.nextInt();
		if (newRule < 0)
		{
			newRule = 0;
		}
		if (newRule > 255)
		{
			newRule = 255;
		}
		
		System.lineSeparator();
		
		System.out.print("Enter initial generation > ");
		String newInitialGen = input.next();
		
		//get the true and false symbol before construction automaton which goes back to default
		char tempTrue = this.automaton.getTrueSymbol();
		char tempFalse = this.automaton.getFalseSymbol();
		
		this.automaton = new Automaton(newRule, new Generation(newInitialGen, automaton.getTrueSymbol()));
		
		//set new true and false symbols back to what they were
		this.automaton.setTrueSymbol(tempTrue);
		this.automaton.setFalseSymbol(tempFalse);
		
		
		System.out.println("Rule number updated to " + newRule);
		
		this.printCurrentGeneration();
		
		
		
	}

	private void setTrueSymbol() {
		// TODO: Prompts the user to enter a new true symbol representation.
		
		System.out.println("Current true symbol: " + automaton.getTrueSymbol());
		
		
		System.out.print("New true symbol > ");
		char newTrueSymbol = input.next().charAt(0);
		
		automaton.setTrueSymbol(newTrueSymbol);
		
		
	}

	private void setFalseSymbol() {
		// TODO: Prompts the user to enter a new false symbol representation.
		
		System.out.println("Current false symbol: " + this.automaton.getFalseSymbol());
		
		
		System.out.print("New false symbol > ");
		char falseSymbol = input.next().charAt(0);
		
		this.automaton.setFalseSymbol(falseSymbol);
//		System.out.println(falseSymbol);
//		System.out.println(this.automaton.getFalseSymbol());
	}

	private void evolve() {
		// TODO: Prompt the user to enter a number of evolutions and evolves
		// the Automaton.
		
		System.out.print("Enter number of evolutions > ");
		int numEvolutions = input.nextInt();
		
		if (numEvolutions < 0)
		{
			numEvolutions = 0;
		}
		
		System.out.println("Evolved " + numEvolutions + " generation(s)");
		
		automaton.evolve(numEvolutions);
		System.lineSeparator();
	}

	private void printCurrentGeneration() {
		// TODO: Print the current generation of the Automaton using the
		// Automaton's true and false symbols.
		
		
		System.out.println("Generation " + automaton.getTotalSteps() + ":");
		System.out.println(this.automaton.getCurrentGeneration().getStates(this.automaton.getFalseSymbol(), this.automaton.getTrueSymbol()));
	}

	private void printFullEvolution() {
		// TODO: Print the full evolution of the automaton.
		System.out.println(automaton.toString());
		
	}

	private void printQuitMessage() {
		// TODO: Print "Bye bye!"
		
		System.out.print("Bye bye!");
		
		//FINISHED PROJECT!!!
	}
}



