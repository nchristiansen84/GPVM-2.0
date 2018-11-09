package jjp.gpvm;
/**
 * Opcode representing pop the top item from the stack, push 0 if item is zero, 1 if positive, or -1 if negative:  if(stack[sp]>0)stack[sp]=1; else if(stack[sp]>0)stack[sp]=0; else stack[sp]=-1;
 */	
public class SGN extends AbstractOpCode{

	public SGN() {
		super("sgn", "Determine the sign",0);
	}

	@Override
	public void opCode(GPVM g) {
		int temp = g.pop();
		if (temp>0){
			g.push(1);
		}
		else if (temp<0){
			g.push(-1);
		}
		else{
			g.push(0);
		}	

	}

}
