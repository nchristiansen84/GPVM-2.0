package jjp.gpvm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
* @author  LadyWarship
* @version 1.0
* @since   March 2016 
*/

public class InputOutputTester {

	/**
	 * Number of tests successfully completed
	 */
	static int numPassed = 0;
	/**
	 * Number of tests attempted
	 */
	static int numRan= 0;
	
	public static void runTestFile(String src, int exp, String caseNum, String s) throws FileNotFoundException, UnsupportedEncodingException{
		
		System.out.println("-----Test ID: "+caseNum+" Title: " + s+" -----");
		numRan++;
		
		String fileName = "test.txt";
		
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.print(src);
		writer.close();
	
		int act = InputOutput.inputFile(fileName);
		
		System.out.print(" Expected: "+exp+". Actual: " +act +". Result: ");
		if (exp==act){
			System.out.println("PASSED.");
			numPassed++;
		}
		else{
			System.out.println("FAILED.");
		}
		System.out.println("----------");
	}
	
	public static void runTestConsole(String src, int exp, String caseNum, String s) throws FileNotFoundException, UnsupportedEncodingException{
		
		System.out.println("-----Test ID: "+caseNum+" Title: " + s+" -----");
		numRan++;
		
		int act = InputOutput.inputConsole(src);
		
		System.out.print(" Expected: "+exp+". Actual: " +act +". Result: ");
		if (exp==act){
			System.out.println("PASSED.");
			numPassed++;
		}
		else{
			System.out.println("FAILED.");
		}
		System.out.println("----------");
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/*int answer = InputOutput.inputFile("src/jjp/gpvm/test");
		if (answer == 20)
			System.out.println("Passed");
		else
			System.out.println("Failed");
		
		answer = InputOutput.inputConsole("psh\t100 psh\t80\tsub hlt");
		if (answer == 20)
			System.out.println("Passed");
		else
			System.out.println("Failed");*/
		
		String test01 = "psh\n100\npsh\n80\nsub\nhlt";
		runTestFile(test01, 20, "01", "Subtraction");	
		
		String test02 = "psh\n100\npsh\n80\nadd\nhlt";
		runTestFile(test02, 180, "02", "Addition");
		
		String test03 = "psh\n100\npsh\n80\nmul\nhlt";
		runTestFile(test03, 8000, "03", "Multiplication");
		
		String test04 = "psh\n100\npsh\n-80\nadd\nhlt";
		runTestFile(test04, 20, "04", "NegativeAddition");
		
		String test05 = "psh\t100 psh\t80\tsub hlt";
		runTestConsole(test05, 20, "05", "Subtraction");	
		
		String test06 = "psh\t100\tpsh 80 add\thlt";
		runTestConsole(test06, 180, "06", "Addition");
		
		String test07 = "psh 100\tpsh 80 mul\thlt";
		runTestConsole(test07, 8000, "07", "Multiplication");
		
		String test08 = "psh\t100\tpsh\t-80\tadd hlt";
		runTestConsole(test08, 20, "08", "NegativeAddition");
		
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("Tests run: "+numRan+". Tests passed: "+numPassed+". ");
		System.out.println("++++++++++++++++++++++++++++++++++++");

	}

}
