package jjp.gpvm;
/**
 * Implementations of this abstract class define opcodes for the GPVM.
 * @author Jim Perry
 * @since July 2012
 *
 */
public abstract class AbstractOpCode implements GPVMFunctor {
	/**
	 * <p>The string representation for the opcode used by
	 * programmers when writing assembly programs. The 
	 * mnemonic string serves as a memory aid, and are used
	 * by implementations of the GPVM assemblers to look
	 * up opcode numeric values.</p>
	 * <p>Mnemonics are chosen by the programmer when 
	 * creating a new GPVM. Users should prefer short 
	 * length (1-6 characters) mnemonics.</p>
	 */
	String mnemonic;
	/**
	 * <p>An explanatory string--usually a sentence 
	 * long--describing the opcode.</p>
	 */
	String description;
	/**
	 * <p>The number of arguments needed by the opcode.
	 * Used by the GPVM to determine the next value of
	 * the pc register.
	 */
	int numArgs;
	/**
	 * The zero-operator constructor is private to 
	 * prevent invalid opcodes from being created.
	 */
	@SuppressWarnings("unused")
	private AbstractOpCode(){
		
	}
	/**
	 * constructs an opcode.
	 * @param m the short opcode mnemonic.
	 * @param d the description of the opcode.
	 * @param n the number of parameter fields needed 
	 * by the opcode.
	 */
	public AbstractOpCode(String m, String d, int n){
		mnemonic = m;
		description = d;
		numArgs = n;
	}
	@Override
	public String toString(){
		return new String("Mnemonic: "+mnemonic
				+" Description: "+description+" Number of Args: "+numArgs+".");
	}

}
