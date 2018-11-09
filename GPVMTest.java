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
	public void runTest(int[] testCase, String exp, String caseNum, String s){
	
		System.out.println("-----Test ID: "+caseNum+" Title: " + s+" -----");
		numRan++;
		
		String act = gPVM.calculate(testCase, new int[10], new String[10], 1000);
		System.out.print("Cycles of execution: " + gPVM.getCycle()+" Expected: "+exp+". Actual: " +act +". Result: ");
		if (exp.equals(act)){
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
		gPVM.addOpCode(new ADDF());	
		gPVM.addOpCode(new SUB());	
		gPVM.addOpCode(new SUBF());	
		gPVM.addOpCode(new MUL());	
		gPVM.addOpCode(new MULF());	
		gPVM.addOpCode(new DIV());	
		gPVM.addOpCode(new DIVF());
		gPVM.addOpCode(new MOD());
		gPVM.addOpCode(new POP());	
		gPVM.addOpCode(new SGN());	
		gPVM.addOpCode(new NEG());	
		gPVM.addOpCode(new NEGF());	
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
		gPVM.addOpCode(new LD());
		gPVM.addOpCode(new ST());
		gPVM.addOpCode(new LDR());
		gPVM.addOpCode(new STR());
		gPVM.addOpCode(new LDC());
		gPVM.addOpCode(new LDI());
		gPVM.addOpCode(new STI());
		gPVM.addOpCode(new SIN());
		gPVM.addOpCode(new SOUT());
		gPVM.addOpCode(new S2I());
		gPVM.addOpCode(new I2S());
		gPVM.addOpCode(new S2F());
		gPVM.addOpCode(new F2S());
		gPVM.addOpCode(new I2F());
		gPVM.addOpCode(new F2I());
		gPVM.addOpCode(new STS());
		gPVM.addOpCode(new LDS());
		gPVM.addOpCode(new LDF());
		gPVM.addOpCode(new STF());
		gPVM.addOpCode(new CONCAT());
		// Display the opcodes and their descriptions being used by 
		// this run of the GPVM.
		gPVM.showInstructionSet(true);
		
		// Don't need to see dspace for these tests
		gPVM.showDSpace(false);

		// Test definitions
			// define the program
		int[] test1= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("hlt")
			};
			// call the test
		
		runTest(test1,"100","1", "Testing psh.");

		// Don't show the opcodes for the remaining tests.
		gPVM.showInstructionSet(false);

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
		
		runTest(test1a,"11","1a", "Testing psh--wrapping the stack.");
		
		int[] test2= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("hlt")
			};
		runTest(test2,"100","2", "Testing dup.");
		
		int[] test3= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("add"),
				gPVM.getOpCode("hlt")
			};
		
		runTest(test3,"200","3", "Testing add.");
		
		int[] test4a= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4a,"20","4", "Testing sub.");
		
		int[] test4b= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4b,"-80","4", "Testing sub.");
		
		int[] test4c= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"), 
				-80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4c,"90","4b", "Testing sub.");
		
		int[] test5= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("mul"),
				gPVM.getOpCode("hlt")
			};
		
		
		runTest(test5,"10000", "5", "Testing mul.");

		int[] test6= {
 				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"),
				20,
				gPVM.getOpCode("div"),
				gPVM.getOpCode("hlt")
			};
		runTest(test6,"5", "6", "Testing div.");
		
		int[] test7= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("mod"),
				gPVM.getOpCode("hlt")
			};
		runTest(test7,"2", "7", "Testing mod.");
		
		int[] test8= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("pop"),
				gPVM.getOpCode("hlt")
			};	
		runTest(test8,"22", "8", "Testing pop.");

		int[] test9= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test9,"1", "9a", "Testing sgn.");
		
		int[] test10= {
				gPVM.getOpCode("psh"), 
				-22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test10,"-1", "9b", "Testing sgn.");
		
		int[] test11= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test11, "0", "9c", "Testing sgn.");
		
		int[] test12= {
  				gPVM.getOpCode("psh"), 
				15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test12, "-15", "10a", "Testing neg.");
		
		int[] test13= {
				gPVM.getOpCode("psh"), 
				-15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13, "15", "10b", "Testing neg.");
		
		int[] test13a= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13a, "0", "10c", "Testing neg.");
		
		int[] test14= {
 				gPVM.getOpCode("psh"), 
				8, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("and"),
				gPVM.getOpCode("hlt")
			};
		runTest(test14, "8", "11", "Testing and.");
		
		int[] test15= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("or"),
				gPVM.getOpCode("hlt")
			};
		runTest(test15, "26", "12", "Testing or.");	
				
		int[] test16= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("xor"),
				gPVM.getOpCode("hlt")
			};
		runTest(test16, "18", "13", "Testing xor.");
		
		int[] test17= {
 				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test17, "-1", "14a", "Testing not.");
		
		int[] test18= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test18, "1", "14b", "Testing not.");
		
		int[] test19= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test19, "1", "15a", "Testing boo.");
		
		int[] test20= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20, "0", "15b", "Testing boo.");
		
		int[] test20a= {
				gPVM.getOpCode("psh"), 
				20, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20a, "1", "15c", "Testing boo.");
		
		int[] test21= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("cll"),	//2
				5,							//3
				gPVM.getOpCode("hlt"),	//4
				gPVM.getOpCode("ret")		//5
			};
		runTest(test21, "4", "16", "Testing cll and ret.");
		
		int[] test22= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("j"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test22, "4", "17", "Testing j.");
		
		int[] test23= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test23, "19", "18a", "Testing jz.");
		
		int[] test24= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test24, "0", "18b", "Testing jz.");
		
		int[] test25= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test25, "19", "19a", "Testing jp.");
		
		int[] test26= {
				gPVM.getOpCode("psh"), 	//0
				-1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test26, "19", "19b", "Testing jp.");
		
		int[] test27= {
				gPVM.getOpCode("psh"), 	//0
				1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test27, "1", "19c", "Testing jp.");
		
		int[] test28= {		
				gPVM.getOpCode("dpsh"),	
				19,
				23,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test28, "23", "20a", "Testing Multiparameter Support.");
		
		int[] test29= {		
	
				10000,

			};
		runTest(test29, "-1", "20b", "Testing illegal opcode.");

		// No longer need to see stack, but need to see dspace
		gPVM.showStack(false);
		gPVM.showDSpace(true);

		int[] test30= {
				gPVM.getOpCode("ldc"),
				50,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("hlt"),
			};
		runTest(test30, "50", "21", "Testing ldc and st.");

		int[] test31= {
				gPVM.getOpCode("ldc"),
				75,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ld"),
				0,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("hlt"),
			};
		runTest(test31, "75", "22", "Testing ld and st.");

		int[] test32= {
				gPVM.getOpCode("ldc"),
				51426,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				2,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("f2s"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test32, "514.26", "23", "Testing f2s.");

		int[] test33= {
				gPVM.getOpCode("ldc"),
				51426,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				2,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("f2s"),
				gPVM.getOpCode("s2i"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test33, "514", "24a", "Testing s2i with string being fp.");

		int[] test34= {
				gPVM.getOpCode("ldc"),
				50,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("s2i"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test34, "50", "24b", "Testing s2i with string being int.");

		int[] test35= {
				gPVM.getOpCode("ldc"),
				50,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("i2s"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test35, "50", "25", "Testing i2s.");

		int[] test36= {
				gPVM.getOpCode("ldc"),
				51426,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				2,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("f2s"),
				gPVM.getOpCode("s2f"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test36, "51426 and 2", "26", "Testing s2f.");

		int[] test37= {
				gPVM.getOpCode("ldc"),
				81724,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("i2f"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test37, "81724 and 0", "27", "Testing i2f.");

		int[] test38= {
				gPVM.getOpCode("ldc"),
				91327,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				2,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("f2i"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test38, "913", "28", "Testing f2i.");

		int[] test39= {
				gPVM.getOpCode("ldc"),
				142,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("lds"),
				0,
				gPVM.getOpCode("sts"),
				1,
				gPVM.getOpCode("hlt"),
			};
		runTest(test39, "142", "29a", "Testing lds and sts with int.");

		int[] test40= {
				gPVM.getOpCode("ldc"),
				14265,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				2,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("f2s"),
				gPVM.getOpCode("lds"),
				0,
				gPVM.getOpCode("sts"),
				1,
				gPVM.getOpCode("hlt"),
			};
		runTest(test40, "142.65", "29b", "Testing lds and sts with fp.");

		int[] test41= {
				gPVM.getOpCode("ldc"),
				643,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				147,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("concat"),
				0,
				gPVM.getOpCode("hlt"),
			};
		runTest(test41, "643147", "30", "Testing concat.");

				// Need to see stack for this test
				gPVM.showStack(true);

				int[] test42= {
				gPVM.getOpCode("ldc"),
				71652,
				gPVM.getOpCode("st"),
				0,
				gPVM.getOpCode("ldc"),
				3,
				gPVM.getOpCode("st"),
				1,
				gPVM.getOpCode("ldf"),
				gPVM.getOpCode("hlt"),
			};
		runTest(test42, "643147", "31", "Testing ldf.");

		// DOn't need to see stack for following tests
		gPVM.showStack(true);
		
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("Tests run: "+numRan+". Tests passed: "+numPassed+". ");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
	}

	public static void main(String[] args){

		new GPVMTest().test();
	}
}
