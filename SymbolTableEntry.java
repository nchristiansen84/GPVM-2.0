package jjp.gpvm;

public class SymbolTableEntry {
	String symbol;
	int line;
	public SymbolTableEntry(String s, int n){
		symbol = s;
		line = n;
	}
}
