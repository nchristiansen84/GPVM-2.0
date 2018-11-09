package gpvm;

/**
* @author  LadyWarship
* @version 1.0
* @since   April 2016 
*/

public class InputOutput {
	final static int STACK_SIZE = 10;
	final static int MAX_CYCLES = 1000;
	
	/**
	 *  Contains set up for InputOutput to identify various opCodes
	 */
	private static GPVM getGPVM() {
		GPVM gPVM = new GPVM();
		
		gPVM.addOpCode(new ADD());
                gPVM.addOpCode(new ADDF());
                gPVM.addOpCode(new AND());
                gPVM.addOpCode(new BOO());
                gPVM.addOpCode(new CLL());
                gPVM.addOpCode(new CONCAT());
                gPVM.addOpCode(new DIV());
                gPVM.addOpCode(new DIVF());
                gPVM.addOpCode(new DPSH());
                gPVM.addOpCode(new DUP());
                gPVM.addOpCode(new F2I());
                gPVM.addOpCode(new F2S());
                gPVM.addOpCode(new HLT());
                gPVM.addOpCode(new I2F());
                gPVM.addOpCode(new I2S());
                gPVM.addOpCode(new J());
                gPVM.addOpCode(new JP());
                gPVM.addOpCode(new JZ());
                gPVM.addOpCode(new LD());
                gPVM.addOpCode(new LDC());
                gPVM.addOpCode(new LDF());
                gPVM.addOpCode(new LDI());
                gPVM.addOpCode(new LDR());
                gPVM.addOpCode(new LDS());
                gPVM.addOpCode(new MOD());
                gPVM.addOpCode(new MUL());
                gPVM.addOpCode(new MULF());
                gPVM.addOpCode(new NEG());
                gPVM.addOpCode(new NEGF());
                gPVM.addOpCode(new NOT());
                gPVM.addOpCode(new OR ());
                gPVM.addOpCode(new POP());
                gPVM.addOpCode(new PSH());
                gPVM.addOpCode(new RET());
                gPVM.addOpCode(new S2F());
                gPVM.addOpCode(new S2I());
                gPVM.addOpCode(new SGN());
                gPVM.addOpCode(new SIN());
                gPVM.addOpCode(new SOUT());
                gPVM.addOpCode(new ST());
                gPVM.addOpCode(new STF());
                gPVM.addOpCode(new STI());
                gPVM.addOpCode(new STR());
                gPVM.addOpCode(new STS());
                gPVM.addOpCode(new SUB());
                gPVM.addOpCode(new SUBF());
                gPVM.addOpCode(new XOR());
                
		return gPVM;
	}
	
	/**
	 *  Takes a file, assembles that file into an int array, passes that int array back into the GPVM
	 *  then executes commands using calculate. Finally, returns (outputs) the results.
	 */
	public static int inputFile(String fileName){
		GPVM gPVM = getGPVM();
		GPVMAssm assm = new GPVMAssm(gPVM);
		int[] obj = assm.assemble(fileName);
		return Integer.parseInt(gPVM.calculate(obj, new int[STACK_SIZE], new String[0], MAX_CYCLES,"-1"));
	}
	
	/**
	 * Takes input from a console allowing for white space and tabs, assembles that input into an int array
	 * passes that int array back into the GPVM then executes commands using calculate. Finally, returns (outputs) the results.
	 */
	public static int inputConsole(String s){
		GPVM gPVM = getGPVM();
		GPVMAssm assm = new GPVMAssm(gPVM);
		String delimiters = "(\\s|\\t)";
		int[] obj = assm.assemble(s.split(delimiters));
		return Integer.parseInt(gPVM.calculate(obj, new int[STACK_SIZE], new String[0], MAX_CYCLES,"-1"));
	}
	
	
}
