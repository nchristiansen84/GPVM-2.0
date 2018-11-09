package jjp.gpvm;
/**
 * Opcode representing tempA pop the top two items from the stack, subtract the first from the second: = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempB-tempA;
 */	
public class SUB extends AbstractOpCode {

	public SUB() {
		super("sub", "Subtraction",0);
	}

	@Override
	public void opCode(GPVM g) {
		int temp = g.pop();
		g.push(g.pop()-temp);
	}

}
