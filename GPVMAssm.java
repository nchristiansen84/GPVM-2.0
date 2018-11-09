package jjp.gpvm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
/**
 * This class contains the methods to assembly in one pass a GPVM source file into an int[] that can be processed by the GPVM.  It does not support labels 
 * @author LordHighCommander
 *
 */
public class GPVMAssm {
	private static InputStream in;
	GPVM gPVM;
	int[] objArray;
	ArrayList<SymbolTableEntry> symtab;
	ArrayList<String>labellessSource;
        ArrayList<Integer> objAL;
	/**
	 * Create an assembler for the GPVM
	 */
	public GPVMAssm(GPVM g ){
		gPVM = g;
	}
	
	/**
	 * A private utility used to show listing at the end of assembly.  
	 * 
	 * TO DO :: make this utility private after testing.
	 * 
	 * @param s object code array.
	 * @param n executable code array.
	 */
	public static void showListing(String[]s, int[]n){
		for (int x = 0; x<s.length; x++){
			System.out.println(n[x]+"\t"+s[x]);
		}
	}
	/**
	 * A private utility used to save listing at the end of assembly.
	 * 
	 *   TO DO :: Convert to the creation of a file before release.
	 *   
	 * @param s object code array.
	 * @param n executable code array.
	 */
	@SuppressWarnings("unused")
	private static void saveListing(String[]s, int[]n){
		for (int x = 0; x<s.length; x++){
			System.out.println(n[x]+"\t"+s[x]);
		}
	}
	
	/*
	static void showProgram(String[] s){
		for (String x : s){
			System.out.println(x);
		}
	}
	
	static void showSource(int[] n){
		for (int x : n){
			System.out.println(x);
		}
	}*/
	
	/**
	 * Reads in a file for assembly.
	 * 
	 * TO DO:  Convert to simpler fileio code.
	 * 
	 * @param file path and file to assemble.
	 * @return Returns an array of the strings (by line) stored in the input file.
	 */
	public static String[] read (String file){
		ArrayList<String> al = new ArrayList<String>();
		String[] source = new String[1];
		try{	
			in = new FileInputStream(file);
			//in = new FileInputStream("./test");
		}
		catch (IOException ioe){
			System.out.println("ERROR:  reading the saved file.");    
			return source;
		}
		
		String s = new String();
		char next = 0;
		boolean more = true;
		do{

			try{
				int b = (in.read());
				if (b==-1){
					al.add(s);
					more=false;
				}
				else{
					if(b=='\n'){
						al.add(s);
						s="";
					}
					else if(b=='\r'){

					}
					else{
						next = (char)b;
						s += next;
					}
				}
			}
			catch (IOException ioe){
				more = false;
			}
		
		}while(more);
		
		try{
			in.close();
		}
		catch (IOException ioe){
			  System.out.println("ERROR:  reading the saved file.");         		   		        		   			         		   			
		}
		
		source = new String[al.size()];
		for (int x = 0;  x<al.size(); x++){
			source[x] = al.get(x);
		}
		return source;
	}
	
	/**
	 * Checks the validity of tokens and aborts assembly if problem is found.
	 * 
	 * TODO change to regex.
	 * 
	 * @param s token to check for validity.
	 * @param lineNum Line number of assembly line of code being checked.
	 * @param line Full line containing token
	 */
	
	public void invalidTokenCheck(String s, int lineNum, String line){
		if (s.contains(" ")
				||s.contains(")")
				||s.contains("(")
				||s.contains(";")){
			System.out.println("ASSM PASS 1:  Line number "+lineNum+": \""+line+"\" contains an illegal space or character. Assembly aborted.");
			System.exit(0);
		}
	}
	/**
	 * Symbol Table look-up function
	 * @param s Symbol to look up
	 * @return numerical value of symbol (if found) or -1 if not in symbol table.
	 */
	public int findSymbol(String s){
		System.out.println("Looking for \""+s+"\" in the symtab");
		for (int x = 0; x<symtab.size(); x++){
			System.out.println("Checking against symtab: "+symtab.get(x).symbol);
			if (symtab.get(x).symbol.equals(s)){
				System.out.println("Returning "+x+" from findSymbol");
				return x;
			}
		}
		System.out.println("Symbol not found in symtab");
		return -1;
	}
	/**
	 * Performs full assemble on a source file.
	 * 
	 * TO DO change to create a file instead of array,
	 * 
	 * @param s Path and file to assemble
	 * @return executable int[]
	 */
	public int[] assemble(String s){
		return assemble(read(s));
	}
	/**
	 * Method to perform a full assemble of a string of source code.
	 * @param source
	 * @return executable int[]
	 */
	public int[] assemble(String[] source){
		assm1(source);
		return assm2();
	}
	/**
	 * Pass 1 of assemble constructs symbol table and labelless source as input to Pass 2, and finds some syntax and other errors.
	 * @param source array
	 */
	public void assm1(String[]source){
		symtab = new ArrayList<SymbolTableEntry>();
		labellessSource = new ArrayList<String>();
		String[] tokens;
		int lineNum=0;
		for (String s : source){
			//System.out.println("Line number:"+lineNum+",  String:"+s);
			tokens = s.trim().split(":");
			if (tokens == null)			{
				System.out.println("ASSM PASS 1:  Blank lines are not permitted. Assembly aborted.");
				System.exit(0);
			}
			else if (tokens.length == 0)			{
				System.out.println("ASSM PASS 1:  Blank lines are not permitted. Assembly aborted.");
				System.exit(0);
			}
			else if (tokens.length==1){
				//System.out.println("One token; tokens[0] = \""+tokens[0]+"\"");
				this.invalidTokenCheck(tokens[0], lineNum, s);
				labellessSource.add(tokens[0].trim());
				//no pass one work, because labels are not permitted on blank lines.
			}
			else{	
                                if(tokens[0].length()>15 ||
                                        Character.isDigit(tokens[0].charAt(0))){
                                    System.out.println("Invalid Label. Assembly aborted.");
                                    System.exit(0);
                                }
                                
				System.out.println("tokens[0] = \""+tokens[0]+"\", and tokens[1]=\""+tokens[1]+".");
				this.invalidTokenCheck(tokens[0], lineNum, s);
				symtab.add(new SymbolTableEntry(tokens[0].trim(), lineNum));
				labellessSource.add(tokens[1].trim());
				System.out.println("Added (\""+tokens[0]+"\", "+ lineNum+")");
			}
			lineNum++;
		}
		for( String s : labellessSource){
			System.out.println(s);
		}
	}
	
	/**
	 * Pass 2 constructs executable array, and finds some syntax errors.
	 * @return An array of integer values that can be executed by the GPVM
	 */
	public int[] assm2(){
		

		String[] source = new String[labellessSource.size()];
		labellessSource.toArray(source);
                objAL = new ArrayList<Integer>();
		
		for(int x = 0; x<source.length; x++){
			//System.out.println("Processing line "+x+": "+"source: "+source[x]+".");
                        String temps=source[x];
                        String tempArg=null;
                        
                        if (source[x].trim().contains(" ")){ //there is an argument(s)
                                temps=source[x].substring(0,source[x].indexOf(" "));
                                tempArg=source[x].substring(source[x].indexOf(" "));
                        }
                        
			int temp =gPVM.getOpCode(temps);
                                                                      
			if (temp>=0){
				objAL.add(temp);
			}
                                           
                        else {        
				try{
					objAL.add(Integer.parseInt(temps));
				}
				catch(NumberFormatException e){
					temp = findSymbol(temps);
					if (temp>=0){
						objAL.add(Integer.parseInt(source[symtab.get(temp).line]));
						System.out.println("bang");
					}
					else{
						System.out.println("ASSM PASS 2: Line number "+x+" contains a invalid opCode.  Assembly Aborted.");
						System.exit(0);
					}
				}
			}
                        if (tempArg!=null){
                            if(tempArg.trim().contains(" ")){ //multiple arguments
                                while(tempArg.trim().contains(" ")){
                                    String arg = tempArg.substring(0,tempArg.indexOf(" "));
                                    tempArg = tempArg.substring(tempArg.indexOf(" "));
                                    try{
                                        objAL.add(Integer.parseInt(arg));
                                    }
                                    catch(NumberFormatException e){
                                        temp = findSymbol(arg);
                                        if (temp>=0){
                                            objAL.add(Integer.parseInt(source[symtab.get(temp).line]));
                                            System.out.println("bang");
                                        }
                                        else{
                                            System.out.println("ASSM PASS 2: Line number "+x+" contains a invalid opCode.  Assembly Aborted.");
                                            System.exit(0);
                                        }
                                    }
                                }
                            }
                            else {
                                try{
                                    objAL.add(Integer.parseInt(tempArg));
				}
                                catch(NumberFormatException e){
                                    temp = findSymbol(tempArg);
                                    if (temp>=0){
                                        objAL.add(Integer.parseInt(source[symtab.get(temp).line]));
                                        System.out.println("bang");
                                    }
                                    else{
                                        System.out.println("ASSM PASS 2: Line number "+x+" contains a invalid opCode.  Assembly Aborted.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
		}
                
                objArray = new int[objAL.size()];
                //convert ArrayList to array
                for(int x=0; x<objAL.size(); x++){
                    objArray[x]=objAL.get(x);
                }
                
                return objArray;
	}
	
}
