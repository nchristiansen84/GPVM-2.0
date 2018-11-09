/**
 * A package designed to support experiments with virtual machine design.
 */
package jjp.gpvm;
/**
 * A core programSpace to support the design of experimental VMs.
 * 
 * @author Jim Perry
 * 
 * In the opcode explanations below the following conventions are used:
 * <ul>
 * <li> pc : programSpace counter
 * <li> sp : stack pointer
 * <li> stack : memory array for stack data.  This array is a fixed length linear array that does not wrap around.
 * <li> programSpace : memory array for programSpace data.  This array is a fixed length linear array which wraps around.
 * </ul>
 *
 */

public class GPVM {

	private AbstractOpCode[] instSet = new AbstractOpCode[0];
	/**
	 * the programSpace counter.
	 */
	private int pc = 0;
	/**
	 * the array containing the executable int[] to run in the VM.
	 */
	private int[] programSpace;
	/**
	 * the address of the first operand (if there is one).
	 */
	private int firstOperand;
	/**
	 * the stack pointer.
	 */
	private int sp;
	/**
	 *  the array containing the int[] stack to hold data for the VM.
	 */
	private int[] stack;
	/**
	 * ---Added---
	 *  the array containing the String[] dspace to hold data for the VM during runtime.
	 */
	private String[] dspace;
		/**
		 * ---Added---
		 *  used to contain the results of an arithmetical or logical operation
		 */
		private int accumulator;
		/**
		 * ---Added---
		 *  used to contain a String from the dspace or from input
		 */
		private String stringHolder;
	
	/**
	 * the current opcode being processed.
	 */
	
	private int current;
	
	/**
	 * Checked to determine if the instruction set should be displayed before a run. Defaults to suppress display.
	 */
	private boolean showInstructionSet = false;
	
	/**
	 * Checked to determine if the stack should be displayed after a run. Defaults to display.
	 */
	private boolean showStack = true;

	/**
	 * Checked to determine if the dspace should be displayed after a run. Defaults to display.
	 */
	private boolean showDSpace = true;
	
	boolean done = false;
	
	long cycle = 0;
	
	public long getCycle(){
		return cycle+1;
	}
	/**
	 * Displays the current instruction set being used to the console.
	 */
	
	
	
	private void showInstSet(){
		System.out.println("Current Instruction Set Description:");
		for (AbstractOpCode x: instSet){
			System.out.println(getOpCode(x.mnemonic)+":  "+x);
		}
	}
	
	/**
	 * allows or disallows the display of the supported instruction set at the beginning of each run.
	 * @param b true if the instruction set should be show, false if not.
	 */
	public void showInstructionSet(boolean b){
		showInstructionSet = b;
	}
	
	/**
	 * adds new opcode support to the VM.
	 * @param o new opcode object.
	 */
	public void addOpCode(AbstractOpCode o){
		AbstractOpCode[] a = new AbstractOpCode[instSet.length+1];
		for (int x=0; x<instSet.length; x++){
			a[x] = instSet[x];
		}
		a[instSet.length]= o;
		instSet = a;
		
	}
	/**
	 * looks up a String to determine if it is the mnemonic for a supported opcode.
	 * @param s potential mnemonic to find.
	 * @return opcode is the mnemonic is supported and -1 otherwise.
	 */
	public int getOpCode(String s){
		for (int x = 0; x<instSet.length; x++){
			if (s.equals(instSet[x].mnemonic)) {
				return x;
			}
		}
		return -1;
	}
	/**
	 * allows or disallows the display of the stack at the end of each run.
	 * @param b true if the instruction set should be show, false if not.
	 */
	public void showStack(boolean b){
		showStack = b;
	}
	/**
	 * Debug utility to display the contact of the stack.
	 */
	private void showStack(){
		System.out.println("(STACK, {");
		int counter = 0;
		for (int x : stack){
			if(counter<15){
				System.out.print("("+x+")");
			}
			else{
				counter = 0;
				System.out.println("("+x+")");
			}
			counter++;
		}
		System.out.println();
		System.out.println("})");
	}
	/**
	 * allows or disallows the display of the dspace at the end of each run.
	 * @param b true if the instruction set should be show, false if not.
	 */
	public void showDSpace(boolean b){
		showDSpace = b;
	}
	/**
	 * Debug utility to display the contact of the dspace.
	 */
	private void showDSpace(){
		System.out.println("(DSAPCE, {");
		int counter = 0;
		for (String x : dspace){
			if(counter<15){
				System.out.print("("+x+")");
			}
			else{
				counter = 0;
				System.out.println("("+x+")");
			}
			counter++;
		}
		System.out.println();
		System.out.println("})");
	}

	/**
	 * <p>pushes the data parameter onto the programSpace stack.</p>
	 * <p>Opcode definitions use this method to manipulate the execution data stack.</p>
	 * <p>Semantically: stack[--sp] = data.
	 * @param data to place on stack
	 */
	public synchronized void push(int data){
		sp--;

		if(sp<0){
			sp=stack.length-1;
		}
		stack[sp]=data;
//		System.out.println("-->pushed(" +data+")");
	}

	/**
	 * <p>pushes the current value of the pc onto the programSpace stack.</p>
	 * <p>Opcode definitions use this method to manipulate the execution data stack.</p>

	 */
	
	public synchronized void pushPC(){
		push(pc);
	}

	/**
	 * pops the stack, returning the top value of the stack and incrementing the sp.
	 * <p>Note: It is important to note that the stack wraps if it reaches either end.</p>
	 * @return the top value of the stack
	 */
	public synchronized int pop(){
		int temp = stack[sp];
		sp=(sp+1)%stack.length;
//		System.out.println("-->popped(" +temp+")");
		return temp;
	}
	/**
	 * returns the top value on the stack without incrementing the sp.
	 * @return the top value of the stack
	 */
	public synchronized int peek(){
//		System.out.println("-->peek(" +temp+")");
		return stack[sp];
	}
	/**
	 * Causes the program to stop execution
	 */
	public synchronized void terminate(){
//		System.out.println("##### STOP #####");
		pc=-9999;
	}
	/**
	 * creates and returns an array of parameters of the length specified by the opcode's numArgs property.
	 * @return An array of parameters
	 */
	public synchronized int[] getMultiOperand(){

		int size = instSet[current].numArgs;
		if(size==0){
			return null;
		}
		int[] a = new int[size];
		for (int x=0; x< size; x++){
			a[x]=programSpace[(firstOperand+x)%programSpace.length];
		}
		return a;
		
	}
	
	
	/**
	 * creates and returns an array of parameters of the length specified by the opcode's numArgs property.
	 * @return An array of parameters
	 */
	public synchronized int[] ALTgetMultiOperand(){
		
		if(instSet[current].numArgs==0){
			return null;
		}
		int[] a = new int[instSet[current].numArgs];
		for (int x=0; x< instSet[current].numArgs; x++){
			a[x]=programSpace[(firstOperand+x)%programSpace.length];
		}
		return a;
	}
	/**
	 * returns the data following the current opcode in memory.
	 * @return parameter
	 */
	public synchronized int getOperand(){
		return programSpace[firstOperand];
	}
	/**
	 * Sets the PC with the address held by the program space immediately following the current opcode,
	 * but first it modifies the data by performing data%programSpace.length to ensure the resulting value
	 * lies within the program stack.
	 */
	public synchronized void setPC(){
		 pc=programSpace[firstOperand]%programSpace.length;
	}
	/**
	 * ---Added---
	 * Sets the Accumulator to the value passed
	 */
	public synchronized void setAcc(int acc){
		 accumulator=acc;
	}
	/**
	 * ---Added---
	 * Returns the value in the accumulator
	 */
	public synchronized int getAcc(){
		 return accumulator;
	}
	/**
	 * ---Added---
	 * Returns the value in the stringHolder
	 */
	public synchronized String getSH(){
		 return stringHolder;
	}
	/**
	 * ---Added---
	 * Sets the value to the stringHolder
	 */
	public synchronized void setSH(String sh){
		 stringHolder=sh;
	}
	/**
	 * ---Added---
	 * Returns the value of the stack pointer
	 */
	public synchronized int getSP(){
		 return sp;
	}
	/**
	 * ---Added---
	 * Returns the value in the dspace at the accumulator
	 */
	public synchronized String getDSpace(int add){
		 return dspace[add];
	}
	/**
	 * ---Added---
	 * Sets the dspace at the accumulator 
	 */
	public synchronized void setDSpace(int add, String val){
		 dspace[add]=val;
	}
	/**
	 * Sets the PC with the address popped from the stack, but first it modifies the data by performing 
	 * data%programSpace.length to ensure the resulting value
	 * lies within the program stack.
	 */
	public synchronized void popPC(){
		pc=pop()%programSpace.length;
	}

	/**
	 * 
	 * @param p int[] of executable object code
	 * @param n stack size for programSpace.
	 * @param d size for dspace
	 * @param chk used to determine where to check; -3 = user must check
	 *                                              -2 = top 2 on stack
	 *                                              -1 = top of stack 
	 *                                              0-x = position in dspace
	 *                                              +0-x = position in dspace and position +1
	 * @return The value on top of the stack.
	 */
	public String calculate(int[] p, int[] n, String[] d, long maxExecute, String chk){
		programSpace=p;
		stack = n;
		sp  = 0;
		pc = 0;
		dspace = d;
		for(int i=0; i<dspace.length; i++) dspace[i]="0";
		accumulator = 0;
		
		if(showInstructionSet){
			showInstSet();
		}
		cycle = 0;
		
		while ((pc>=0) && (cycle++< maxExecute)){
//			System.out.println("(PC, "+pc+") (SP, "+sp+")");
			current = programSpace[pc];
			
			if((current>-1)&&(current<instSet.length)){
//				System.out.println(instSet[current].mnemonic);
				firstOperand=(pc+1)%programSpace.length;
				pc = (firstOperand+instSet[current].numArgs)%programSpace.length;
				instSet[current].opCode(this);
			}
			else{
//				System.out.println("Unrecognized opcode: "+current);
				return -1+"";
			}
			
		}
//		System.out.println("(PC, "+pc+") (SP, "+sp+")");
		
		if(showStack){
			showStack();
		}
		if(showDSpace){
			showDSpace();
		}
		
		if(chk.equals("-3")) return "User must check";
		else if(chk.equals("-2")) return stack[sp]+" and "+stack[sp+1];
		else if(chk.equals("-1")) return stack[sp]+"";
		else if(chk.charAt(0)=='+') return dspace[Integer.parseInt(chk)]+" and "+dspace[Integer.parseInt(chk)+1];
		else return dspace[Integer.parseInt(chk)];
	}
}
