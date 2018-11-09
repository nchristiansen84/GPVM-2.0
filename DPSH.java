package jjp.gpvm;

/**
 * Used to test multiparameter GPVM support
 * @author LordHighCommander
 *
 */
public class DPSH extends AbstractOpCode {

	public DPSH() {
		super("dpsh", "Multiple Push", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		int[] a= g.getMultiOperand();
		for (int x : a){
			g.push(x);
		}

	}

}
