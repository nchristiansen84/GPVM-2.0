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
	/**
	 * Number of tests the user has to confirm passed
	 */
	static int numUserChecked=0;
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
         * @param chk used to determine where to check; -3 = User must check
         *                                              -2 = top 2 on stack
         *                                              -1 = top of stack 
         *                                              0-x = position in dspace
         *                                              +0-x = position in dspace and position +1
	 */
	public void runTest(int[] testCase, String exp, String caseNum, String s, String chk){
	
		System.out.println("-----Test ID: "+caseNum+" Title: " + s+" -----");
		numRan++;
		
		String act = gPVM.calculate(testCase, new int[10], new String[10], 1000, chk);
		System.out.print("Cycles of execution: " + gPVM.getCycle()+" Expected: "+exp+". Actual: " +act +". Result: ");
		if (exp.equals(act)){
			System.out.println("PASSED.");
			numPassed++;
		}
                else if(act.equals("User must check")){
                    System.out.println("User must check.");
                    numUserChecked++;
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
		
		runTest(test1,"100","1", "Testing psh.", "-1");

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
		
		runTest(test1a,"11","1a", "Testing psh--wrapping the stack.", "-1");
		
		int[] test2= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("hlt")
			};
		runTest(test2,"100","2", "Testing dup.", "-1");
		
		int[] test3= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("dup"),
				gPVM.getOpCode("add"),
				gPVM.getOpCode("hlt")
			};
		
		runTest(test3,"200","3", "Testing add.", "-1");
		
		int[] test4a= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4a,"20","4", "Testing sub.", "-1");
		
		int[] test4b= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("psh"), 
				80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4b,"-80","4", "Testing sub.", "-1");
		
		int[] test4c= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"), 
				-80, 
				gPVM.getOpCode("sub"),
				gPVM.getOpCode("hlt")
			};
		runTest(test4c,"90","4b", "Testing sub.", "-1");
		
		int[] test5= {
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("mul"),
				gPVM.getOpCode("hlt")
			};
		
		
		runTest(test5,"10000", "5", "Testing mul.", "-1");

		int[] test6= {
 				gPVM.getOpCode("psh"), 
				100, 
				gPVM.getOpCode("psh"),
				20,
				gPVM.getOpCode("div"),
				gPVM.getOpCode("hlt")
			};
		runTest(test6,"5", "6", "Testing div.", "-1");
		
		int[] test7= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("mod"),
				gPVM.getOpCode("hlt")
			};
		runTest(test7,"2", "7", "Testing mod.", "-1");
		
		int[] test8= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("psh"),
				5,
				gPVM.getOpCode("pop"),
				gPVM.getOpCode("hlt")
			};	
		runTest(test8,"22", "8", "Testing pop.", "-1");

		int[] test9= {
				gPVM.getOpCode("psh"), 
				22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test9,"1", "9a", "Testing sgn.", "-1");
		
		int[] test10= {
				gPVM.getOpCode("psh"), 
				-22, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test10,"-1", "9b", "Testing sgn.", "-1");
		
		int[] test11= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("sgn"),
				gPVM.getOpCode("hlt")
			};
		runTest(test11, "0", "9c", "Testing sgn.", "-1");
		
		int[] test12= {
  				gPVM.getOpCode("psh"), 
				15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test12, "-15", "10a", "Testing neg.", "-1");
		
		int[] test13= {
				gPVM.getOpCode("psh"), 
				-15, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13, "15", "10b", "Testing neg.", "-1");
		
		int[] test13a= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("neg"),
				gPVM.getOpCode("hlt")
			};
		runTest(test13a, "0", "10c", "Testing neg.", "-1");
		
		int[] test14= {
 				gPVM.getOpCode("psh"), 
				8, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("and"),
				gPVM.getOpCode("hlt")
			};
		runTest(test14, "8", "11", "Testing and.", "-1");
		
		int[] test15= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("or"),
				gPVM.getOpCode("hlt")
			};
		runTest(test15, "26", "12", "Testing or.", "-1");	
				
		int[] test16= {
				gPVM.getOpCode("psh"), 
				10, 
				gPVM.getOpCode("psh"),
				24,
				gPVM.getOpCode("xor"),
				gPVM.getOpCode("hlt")
			};
		runTest(test16, "18", "13", "Testing xor.", "-1");	
		
		int[] test17= {
 				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test17, "-1", "14a", "Testing not.", "-1");	
		
		int[] test18= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("not"),
				gPVM.getOpCode("hlt")
			};
		runTest(test18, "1", "14b", "Testing not.", "-1");	
		
		int[] test19= {
				gPVM.getOpCode("psh"), 
				-2, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test19, "1", "15a", "Testing boo.", "-1");	
		
		int[] test20= {
				gPVM.getOpCode("psh"), 
				0, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20, "0", "15b", "Testing boo.", "-1");	
		
		int[] test20a= {
				gPVM.getOpCode("psh"), 
				20, 
				gPVM.getOpCode("boo"),
				gPVM.getOpCode("hlt")
			};
		runTest(test20a, "1", "15c", "Testing boo.", "-1");			
		
		int[] test21= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("cll"),	//2
				5,							//3
				gPVM.getOpCode("hlt"),	//4
				gPVM.getOpCode("ret")		//5
			};
		runTest(test21, "4", "16", "Testing cll and ret.", "-1");	
		
		int[] test22= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("j"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test22, "4", "17", "Testing j.", "-1");		
		
		int[] test23= {
				gPVM.getOpCode("psh"), 	//0
				4,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test23, "19", "18a", "Testing jz.", "-1");			
		
		int[] test24= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jz"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test24, "0", "18b", "Testing jz.", "-1");	
		
		int[] test25= {
				gPVM.getOpCode("psh"), 	//0
				0,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test25, "19", "19a", "Testing jp.", "-1");	
		
		int[] test26= {
				gPVM.getOpCode("psh"), 	//0
				-1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test26, "19", "19b", "Testing jp.", "-1");	
		
		int[] test27= {
				gPVM.getOpCode("psh"), 	//0
				1,							//1
				gPVM.getOpCode("jp"),		//2
				6,							//3
				gPVM.getOpCode("psh"),	//4
				19,							//5
				gPVM.getOpCode("hlt"),	//6
			};
		runTest(test27, "1", "19c", "Testing jp.", "-1");	
		
		int[] test28= {		
				gPVM.getOpCode("dpsh"),	
				19,
				23,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test28, "23", "20a", "Testing Multiparameter Support.", "-1");	
		
		int[] test29= {		
	
				10000,

			};
		runTest(test29, "-1", "20b", "Testing illegal opcode.", "-1");
                
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
		runTest(test30, "50", "21a", "Testing ldc and st with positive int.", "0");
		
		int[] test31= {		
				gPVM.getOpCode("ldc"),	
				-50,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test31, "-50", "21b", "Testing ldc and st with negative int.", "0");
		
		int[] test32= {		
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
		runTest(test32, "75", "22a", "Testing ld and st with postive int.", "1");
		
		int[] test33= {		
				gPVM.getOpCode("ldc"),	
				-75,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ld"),
                                0,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test33, "-75", "22b", "Testing ld and st with negative int.", "1");
		
		int[] test34= {	
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
		runTest(test34, "514.26", "23a", "Testing f2s with positive.", "0");
		
		int[] test35= {	
				gPVM.getOpCode("ldc"),	
				-51426,
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
		runTest(test35, "-514.26", "23b", "Testing f2s with negative.", "0");
		
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
                                gPVM.getOpCode("s2i"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test36, "514", "24a", "Testing s2i with string being a positive fp.", "0");
                
		int[] test37= {	
				gPVM.getOpCode("ldc"),	
				-51426,
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
		runTest(test37, "-514", "24b", "Testing s2i with string being a negative fp.", "0");
                
		int[] test38= {	
				gPVM.getOpCode("ldc"),	
				50,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("s2i"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test38, "50", "24c", "Testing s2i with string being a positive int.", "0");
                
		int[] test39= {	
				gPVM.getOpCode("ldc"),	
				-50,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("s2i"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test39, "-50", "24d", "Testing s2i with string being a negative int.", "0");
                
		int[] test40= {	
				gPVM.getOpCode("ldc"),	
				50,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("i2s"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test40, "50", "25a", "Testing i2s with positive int.", "0");
                
		int[] test41= {	
				gPVM.getOpCode("ldc"),	
				-50,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("i2s"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test41, "-50", "25b", "Testing i2s with negative int.", "0");
                
		int[] test42= {	
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
		runTest(test42, "51426 and 2", "26a", "Testing s2f with positive.", "+0");
                
		int[] test43= {	
				gPVM.getOpCode("ldc"),	
				-51426,
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
		runTest(test43, "-51426 and 2", "26b", "Testing s2f with negative.", "+0");
                
		int[] test44= {	
				gPVM.getOpCode("ldc"),	
				81724,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("i2f"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test44, "81724 and 0", "27a", "Testing i2f with positive.", "+0");

		int[] test45= {	
				gPVM.getOpCode("ldc"),	
				-81724,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("i2f"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test45, "-81724 and 0", "27b", "Testing i2f with negative.", "+0");

                int[] test46= {	
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
		runTest(test46, "913", "28a", "Testing f2i with positive.", "0");                
		
                int[] test47= {	
				gPVM.getOpCode("ldc"),	
				-91327,
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
		runTest(test47, "-913", "28b", "Testing f2i with negative.", "0");                
		
                int[] test48= {	
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
		runTest(test48, "142", "29a", "Testing lds and sts with positive int.", "1"); 
		
                int[] test49= {	
				gPVM.getOpCode("ldc"),	
				-142,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("lds"),
                                0,
                                gPVM.getOpCode("sts"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test49, "-142", "29b", "Testing lds and sts with negative int.", "1"); 
		
                int[] test50= {	
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
		runTest(test50, "142.65", "29c", "Testing lds and sts with positive fp.", "1");                
		
                int[] test51= {	
				gPVM.getOpCode("ldc"),	
				-14265,
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
		runTest(test51, "-142.65", "29d", "Testing lds and sts with negative fp.", "1");                
		
                int[] test52= {	
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
		runTest(test52, "643147", "30", "Testing concat.", "0");                
		
                // Need to see stack for this test
                gPVM.showStack(true);
                
                int[] test53= {	
				gPVM.getOpCode("ldc"),	
				71652,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test53, "71652 and 3", "31a", "Testing ldf positive.", "-2"); 
                
                int[] test54= {	
				gPVM.getOpCode("ldc"),	
				-71652,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test54, "-71652 and 3", "31b", "Testing ldf negative.", "-2"); 
                
                int[] test55= {	
				gPVM.getOpCode("ldc"),	
				71652,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("stf"),
                                2,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test55, "71652 and 3", "32a", "Testing stf positive.", "+2"); 
                
                int[] test56= {	
				gPVM.getOpCode("ldc"),	
				-71652,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("stf"),
                                2,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test56, "-71652 and 3", "32b", "Testing stf negative.", "+2"); 
                
                // Don't need to see stack or dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(false);

                int[] test57= {	
				gPVM.getOpCode("ldc"),	
				90125,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("sout"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test57, "90125", "33a", "Testing sout with positive int.", "-3"); 
                
                int[] test58= {	
				gPVM.getOpCode("ldc"),	
				-90125,
                                gPVM.getOpCode("st"),
                                0,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("sout"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test58, "-90125", "33b", "Testing sout with negative int.", "-3"); 
                
                int[] test59= {	
				gPVM.getOpCode("ldc"),	
				90125,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("f2s"),
                                gPVM.getOpCode("sout"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test59, "90.125", "33c", "Testing sout with positive fp string.", "-3"); 
                
                int[] test60= {	
				gPVM.getOpCode("ldc"),	
				-90125,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("f2s"),
                                gPVM.getOpCode("sout"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test60, "-90.125", "33d", "Testing sout with negative fp string.", "-3"); 

                // Need to see stack and dspace for these test
                gPVM.showStack(true);
                gPVM.showDSpace(true);

                int[] test61= {	
				gPVM.getOpCode("ldc"),	
				90178,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,                                
                                gPVM.getOpCode("negf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test61, "-90178 and 3", "34a", "Testing negf with positive.", "-2"); 
                
                int[] test62= {	
				gPVM.getOpCode("ldc"),	
				-90178,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldf"),
                                0,                                
                                gPVM.getOpCode("negf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test62, "90178 and 3", "34b", "Testing negf with negative.", "-2"); 
                
                int[] test63= {	
				gPVM.getOpCode("ldc"),	
				501,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				10095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("addf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test63, "15105 and 2", "35a", "Testing addf with two positive.", "-2"); 
                
                int[] test64= {	
				gPVM.getOpCode("ldc"),	
				-501,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-10095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("addf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test64, "-15105 and 2", "35b", "Testing addf with two negative.", "-2"); 
                
                int[] test65= {	
				gPVM.getOpCode("ldc"),	
				501,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-10095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("addf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test65, "-5085 and 2", "35c", "Testing addf with one positive and one negative.", "-2"); 
                
                // Don't need to see stack or dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(false);

                int[] test66= {	
				gPVM.getOpCode("ldc"),	
				2147483647,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				20000,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("addf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test66, "0", "35d", "Testing addf positive overflow.", "-3"); 
                
                int[] test67= {	
				gPVM.getOpCode("ldc"),	
				-2147483647,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-20000,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("addf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test67, "0", "35e", "Testing addf negative overflow.", "-3"); 
                
                // Need to see stack and dspace for these tests
                gPVM.showStack(true);
                gPVM.showDSpace(true);
                
                int[] test68= {	
				gPVM.getOpCode("ldc"),	
				10095,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				501,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("subf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test68, "-5085 and 2", "36a", "Testing subf with two positive.", "-2"); 
                
                int[] test69= {	
				gPVM.getOpCode("ldc"),	
				-10095,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-501,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("subf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test69, "5085 and 2", "36b", "Testing subf with two negative.", "-2"); 
                
                int[] test70= {	
				gPVM.getOpCode("ldc"),	
				501,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-10095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("subf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test70, "-15105 and 2", "36c", "Testing subf with one positive and one negative.", "-2"); 
                
                // Don't need to see stack or dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(false);

                int[] test71= {	
				gPVM.getOpCode("ldc"),	
				-2147483647,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				20000,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("subf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test71, "0", "36d", "Testing subf positive overflow.", "-3"); 
                
                int[] test72= {	
				gPVM.getOpCode("ldc"),	
				2147483647,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				1,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-20000,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("subf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test72, "0", "36e", "Testing subf negative overflow.", "-3"); 
                
                // Need to see stack and dspace for these tests
                gPVM.showStack(true);
                gPVM.showDSpace(true);
		
                int[] test73= {	
				gPVM.getOpCode("ldc"),	
				335,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				575,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("mulf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test73, "192625 and 4", "37a", "Testing mulf with two positive.", "-2"); 
                
                int[] test74= {	
				gPVM.getOpCode("ldc"),	
				-335,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-575,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("mulf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test74, "192625 and 4", "37b", "Testing mulf with two negative.", "-2"); 
                
                int[] test75= {	
				gPVM.getOpCode("ldc"),	
				-335,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				575,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("mulf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test75, "-192625 and 4", "37c", "Testing mulf with one positive and one negative.", "-2"); 
                
                // Don't need to see stack or dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(false);

                int[] test76= {	
				gPVM.getOpCode("ldc"),	
				5187234,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				459287323,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				4,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("mulf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test76, "0", "37d", "Testing mulf positive overflow.", "-3"); 
                
                int[] test77= {	
				gPVM.getOpCode("ldc"),	
				4634595,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-4635095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("mulf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test77, "0", "37e", "Testing mulf negative overflow.", "-3"); 
                
                // Need to see stack and dspace for these tests
                gPVM.showStack(true);
                gPVM.showDSpace(true);
		
                int[] test78= {	
				gPVM.getOpCode("ldc"),	
				309,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				7725,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test78, "25 and 1", "38a", "Testing divf with two positive.", "-2"); 
                
                int[] test79= {	
				gPVM.getOpCode("ldc"),	
				-309,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-7725,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test79, "25 and 1", "38b", "Testing divf with two negative.", "-2"); 
                
                int[] test80= {	
				gPVM.getOpCode("ldc"),	
				309,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-7725,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test80, "-25 and 1", "38c", "Testing divf with one positive and one negative.", "-2"); 
                
                // Don't need to see stack or dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(false);

                int[] test81= {	
				gPVM.getOpCode("ldc"),	
				3098,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				927,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test81, "0", "38d", "Testing divf positive overflow.", "-3"); 
                
                int[] test82= {	
				gPVM.getOpCode("ldc"),	
				-3098,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				3,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				927,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test82, "0", "38e", "Testing divf negative overflow.", "-3"); 
                
                int[] test83= {	
				gPVM.getOpCode("ldc"),	
				0,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),	
				0,
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("ldc"),	
				-4635095,
                                gPVM.getOpCode("st"),
                                2,
				gPVM.getOpCode("ldc"),	
				2,
                                gPVM.getOpCode("st"),
                                3,
                                gPVM.getOpCode("ldf"),
                                0,
                                gPVM.getOpCode("ldf"),
                                2,
                                gPVM.getOpCode("divf"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test83, "0", "38f", "Testing divf with 0 as divisor.", "-1"); 
                
                // Need to see dspace for these tests
                gPVM.showStack(false);
                gPVM.showDSpace(true);
		
                int[] test84= {	
				gPVM.getOpCode("ldc"),	
				187,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldr"),
				0,
                                gPVM.getOpCode("str"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test84, "187", "39a", "Testing ldr and str with positive", "1"); 
                
                int[] test85= {	
				gPVM.getOpCode("ldc"),	
				-187,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldr"),
				0,
                                gPVM.getOpCode("str"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test85, "-187", "39b", "Testing ldr and str with negative", "1"); 
                
                int[] test86= {	
				gPVM.getOpCode("ldc"),	
				187,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("ldi"),
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test86, "187", "40a", "Testing ldi with positive", "1"); 
                
                int[] test87= {	
				gPVM.getOpCode("ldc"),	
				-187,
                                gPVM.getOpCode("st"),
                                0,
				gPVM.getOpCode("ldc"),
				0,
				gPVM.getOpCode("ldi"),
                                gPVM.getOpCode("st"),
                                1,
				gPVM.getOpCode("hlt"),	
			};
		runTest(test87, "-187", "40b", "Testing ldi with negative", "1"); 

                int[] test88= {	
				gPVM.getOpCode("ldc"),	
				187,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("sti"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test88, "187", "40a", "Testing sti with positive", "0"); 
                
                int[] test89= {	
				gPVM.getOpCode("ldc"),	
				-187,
                                gPVM.getOpCode("st"),
                                1,
                                gPVM.getOpCode("ldc"),
                                0,
                                gPVM.getOpCode("sti"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test89, "-187", "40b", "Testing sti with negative", "0"); 

                int[] test90= {	
				gPVM.getOpCode("ldc"),	
				0,
                                gPVM.getOpCode("sin"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test90, "10", "41a", "Testing sin with input as 10", "0"); 
                
                int[] test91= {	
				gPVM.getOpCode("ldc"),	
				0,
                                gPVM.getOpCode("sin"),
				gPVM.getOpCode("hlt"),	
			};
		runTest(test91, "-10", "41b", "Testing sin with input as -10", "0"); 
                
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("Tests run: "+numRan+". Tests passed: "+numPassed+". Tests user must check: "+numUserChecked+".");
		System.out.println("++++++++++++++++++++++++++++++++++++");
	}

	public static void main(String[] args){

		new GPVMTest().test();
	}
}
