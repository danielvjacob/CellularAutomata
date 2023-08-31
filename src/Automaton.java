import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Automaton {

	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();

	char falseSymbol;
	char trueSymbol;

	public Automaton(int ruleNum, Generation initial) {
		this.rule = new Rule(ruleNum);

		generations.add(initial);
		falseSymbol = '0';
		trueSymbol = '1';

	}

	public Automaton(String filename) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(filename));

		rule = new Rule(scan.nextInt());

		scan.nextLine();

		falseSymbol = scan.next().charAt(0);
		trueSymbol = scan.next().charAt(0);

		scan.nextLine();

		String stringGeneration = scan.next();

		Generation gen = new Generation(stringGeneration, trueSymbol);
		generations.add(gen);

	}

	public int getRuleNum() {
		return rule.getRuleNum();
	}

	public int evolve(int numSteps) {
		if (numSteps <= 0) {
			return 0;
		} else {
			for (int i = 1; i <= numSteps; ++i) {
				generations.add(rule.evolve(generations.get(generations.size() - 1)));

			}
		}
		return numSteps;
	}

	public Generation getGeneration(int stepNum) {
		if (stepNum >= generations.size()) {
			evolve(stepNum - (generations.size() - 1));
		}

		return generations.get(stepNum);

	}

	public Generation getCurrentGeneration() {
		return getGeneration(generations.size() - 1);
	}

	public int getTotalSteps() {
		return generations.size() - 1;
	}

	public String toString() {

		String eachLine = "";

		eachLine = generations.get(0).getStates(falseSymbol, trueSymbol);
		for (int i = 1; i < generations.size(); ++i) {
			eachLine += System.lineSeparator() + generations.get(i).getStates(falseSymbol, trueSymbol);
		}

		return eachLine;
	}

	public void saveEvolution(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

		writer.write(this.toString());

		writer.close();
	}

	public void setTrueSymbol(char newTrueSymbol) {
		this.trueSymbol = newTrueSymbol;
	}

	public char getTrueSymbol() {
		return this.trueSymbol;
	}

	public void setFalseSymbol(char newFalseSymbol) {
		this.falseSymbol = newFalseSymbol;
	}

	public char getFalseSymbol() {
		return this.falseSymbol;
	}

}
