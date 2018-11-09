package jjp.gpvm;
/**
 * Opcode representing push to the stack:  
 * stack[--sp] = program[pc+1];
 */
public class PSH extends AbstractOpCode{
	public PSH() {
		super("psh", "Push",1);
		
	}

	public void opCode(GPVM g){
		g.push(g.getOperand());
	}

}
