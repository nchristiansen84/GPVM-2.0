package jjp.gpvm;

import jjp.gpvm.*;
/**
 * Test the implementation of user defined opcodes, and can be used 
 * as a regression test for the GPVM
 * @author jim perry
 *
 */
public class GPVMTest {
	/**
	 * Number of tests successfully completed
	 */
	static int numPassed = 0;
	/**
	 * Number of tests attempted
	 */
	static int numRan= 0;
	/*
	 * Instantiates a GPVM for the tests
	 */
	GPVM gPVM = new GPVM();

	/**
	 * Runs test, formats output, and determines success of tests
	 * @param testCase an assembled array of GPVM opcodes and operands.
	 * @param exp the value of the expected result
	 * @param caseNum the user-defined test number for tracking purposes
	 * @param s a verbose description of the test.
	 */
	public void runTest(int[] testCase, int exp, String caseNum, String s){
	
		System.out.println("-----Test ID: "+caseNum+" Title: " + s+" -----");
		numRan++;
		
		int act = gPVM.calculate(testCase, new int[10], 1000);
		System.out.print("Cycles of execution: " + gPVM.getCycle()+" Expected: "+exp+". Actual: " +act +". Result: ");
		if (exp==act){
			System.out.println("PASSED.");
			numPassed++;
		}
		else{
			System.out.println("FAILED.");
		}
		System.out.println("----------");
	}

	/**
	 * Test series
	 */
	private void test() {
		
		// Opcodes being tested are added to the GPVM.
		// each gPVM.addOpcode() adds an opcode to the array maintained 
		// by the GPVM instance
		gPVM.addOpCode(new PSH());
		gPVM.addOpCode(new HLT());
		gPVM.addOpCode(new DUP());
		gPVM.addOpCode(new ADD());	
		gPVM.addOpCode(new SUB());			
		gPVM.addOpCode(new MUL());	
		gPVM.addOpCode(new DIV());			
		gPVM.addOpCode(new MOD());		
		gPVM.addOpCode(new POP());	
		gPVM.addOpCode(new SGN());	
		gPVM.addOpCode(new NEG());	
		gPVM.addOpCode(new AND());	
		gPVM.addOpCode(new OR ());			
		gPVM.addOpCode(new XOR());	
		gPVM.addOpCode(new NOT());	
		gPVM.addOpCode(new BOO());			
		gPVM.addOpCode(new CLL());	
		gPVM.addOpCode(new RET());	
		gPVM.addOpCode(new J  ());	
		gPVM.addOpCode(new JZ ());			
		gPVM.addOpCode(new JP());	
		gPVM.addOpCode(new DPSH());
		
		// Display the opcodes and their descriptions being used by 
		// this run of the GPVM.
		gPVM.showInstructionSet(true);
		
		// Test definitions
			// define the program
		int[] test1= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("hlt")
			};
			// call the test
		
		runTest(test1,100,"1", "Testing psh.");
		
		int[] test1a= {
				gPVM.getOpCode("psh"), 
				1, 
				gPVM.getOpCode("psh"), 
				2, 			
				gPVM.getOpCode("psh"), 
				3, 
				gPVM.getOpCode("psh"), 
				4, 	
				gPVM.getOpCode("psh"), 
				5, 
				gPVM.getOpCode("psh"), 
				6, 			
				gPVM.getOpCode("psh"), 
				7, 
				gPVM.getOpCode("psh"), 
				8, 		
				gPVM.getOpCode("psh"), 
				9, 			
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"), 
				11, 	
				gPVM.getOpCode("hlt")
			};
			// call the test
		
		runTest(test1a,11,"1a", "Testing psh--wrapping the stack.");
		
		// Don't show the opcodes for the remaining tests.
		gPVM.showInstructionSet(false);
		
		int[] test2= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("hlt")
			};
		runTest(test2,100,"2", "Testing dup.");
		
		int[] test3= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("add"),
				gPVM.getOpCode("hlt")
			};
		
		runTest(test3,200,"3", "Testing add.");
		
		int[] test4a= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4a,20,"4", "Testing sub.");
		
		int[] test4b= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4b,-80,"4", "Testing sub.");
		
		int[] test4c= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"), 
				-80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4c,90,"4b", "Testing sub.");		
		
		int[] test5= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("mul"),
				gPVM.getOpCode("hlt")
			};
		
		
		runTest(test5,10000, "5", "Testing mul.");

		int[] test6= {
 				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"),
				20,
				gPVM.getOpCode("div"),
				gPVM.getOpCode("hlt")
			};
		runTest(test6,5, "6", "Testing div.");
		
		int[] test7= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("mod"),
				gPVM.getOpCode("hlt")
			};
		runTest(test7,2, "7", "Testing mod.");
		
		int[] test8= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("pop"),
				gPVM.getOpCode("hlt")
			};	
		runTest(test8 ,22, "8", "Testing pop.");

		int[] test9= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test9, 1, "9a", "Testing sgn.");
		
		int[] test10= {
				gPVM.getOpCode("psh"), 
				-22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test10,-1, "9b", "Testing sgn.");
		
		int[] test11= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test11, 0, "9c", "Testing sgn.");
		
		int[] test12= {
  				gPVM.getOpCode("psh"), 
				15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test12, -15, "10a", "Testing neg.");
		
		int[] test13= {
				gPVM.getOpCode("psh"), 
				-15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13, 15, "10b", "Testing neg.");
		
		int[] test13a= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13a, 0, "10c", "Testing neg.");
		
		int[] test14= {
 				gPVM.getOpCode("psh"), 
				8, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("and"),
				gPVM.getOpCode("hlt")
			};
		runTest(test14, 8, "11", "Testing and.");
		
		int[] test15= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("or"),
				gPVM.getOpCode("hlt")
			};
		runTest(test15, 26, "12", "Testing or.");	
				
		int[] test16= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("xor"),
				gPVM.getOpCode("hlt")
			};
		runTest(test16, 18, "13", "Testing xor.");	
		
		int[] test17= {
 				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test17, -1, "14a", "Testing not.");	
		
		int[] test18= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test18, 1, "14b", "Testing not.");	
		
		int[] test19= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test19, 1, "15a", "Testing boo.");	
		
		int[] test20= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20, 0, "15b", "Testing boo.");	
		
		int[] test20a= {
				gPVM.getOpCode("psh"), 
				20, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20a, 1, "15c", "Testing boo.");			
		
		int[] test21= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("cll"),	//2
				5,							//3
				gPVM.getOpCode("hlt"),	//4
				gPVM.getOpCode("ret")		//5
			};
		runTest(test21, 4, "16", "Testing cll and ret.");	
		
		int[] test22= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("j"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test22, 4, "17", "Testing j.");		
		
		int[] test23= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test23, 19, "18a", "Testing jz.");			
		
		int[] test24= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test24, 0, "18b", "Testing jz.");	
		
		int[] test25= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test25, 19, "19a", "Testing jp.");	
		
		int[] test26= {
				gPVM.getOpCode("psh"), 	//0
				-1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test26, 19, "19b", "Testing jp.");	
		
		int[] test27= {
				gPVM.getOpCode("psh"), 	//0
				1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test27, 1, "19c", "Testing jp.");	
		
		int[] test28= {		
				gPVM.getOpCode("dpsh"),	
				19,
				23,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test28, 23, "20a", "Testing Multiparameter Support.");	
		
		int[] test29= {		
	
				10000,

			};
		runTest(test29, -1, "20b", "Testing illegal opcode.");	
		
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("Tests run: "+numRan+". Tests passed: "+numPassed+". ");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
	}

	public static void main(String[] args){

		new GPVMTest().test();
		

	}
}
