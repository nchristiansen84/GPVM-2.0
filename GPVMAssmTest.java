package jjp.gpvm;

import jjp.gpvm.*;

public class GPVMAssmTest {

	/**
	 * @param args
	 */
	

	public static void main(String[] args) {
		String[] prg = GPVMAssm.read("test");
		GPVM gPVM = new GPVM();
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
		gPVM.addOpCode(new JP ());		
		
		GPVMAssm assm = new GPVMAssm(gPVM);
		
		int[]obj = assm.assemble(prg);

		GPVMAssm.showListing(prg, obj);
		

	}

}
