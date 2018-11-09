
package jjp.gpvm;

import java.util.Scanner;

/**
 * @author Brian
 */

/*
* SystemTest allowing user to enter assembly code.
*/
public class ConsoleIOSystemTest {
    public static void main(String[] args) {
        System.out.println("Type in assembly code here:");
        Scanner sc = new Scanner(System.in); 
        String src = sc.nextLine();
        System.out.println(InputOutput.inputConsole(src));
        /*
        Demo1: ldr and str; dspace[2] = 187
            psh 1 psh 1 ldc 187 st 0 ldr -8 str -6 ldc 2 sout hlt
        
        Demo2: ldi and sti; dspace[0] = 187 and dspace[7] = 235
            ldc 187 st 3 ldc 3 ldi st 0 ldc 235 st 9 psh 1 psh 1 ldc 7 sti ldc 0 sout ldc 7 sout hlt

        Demo3: addf, subf, mulf, divf, negf
            ldc 5 st 0 ldc -1 st 1 ldc 84537 st 2 ldc 3 st 3 ldf 0 ldf 2 addf stf 0 ldc 0 f2s sout ldc 5 sin ldc 19875 st 0 ldc 3 st 1 ldc 91765 st 2 ldc 2 st 3 ldf 2 ldf 0 subf stf 0 ldc 0 f2s sout ldc 5 sin ldc -309 st 0 ldc 2 st 1 ldc -7725 st 2 ldc 3 st 3 ldf 0 ldf 2 divf stf 0 ldc 0 f2s sout ldc 5 sin ldc 105 st 0 ldc 1 st 1 ldc 75 st 2 ldc 1 st 3 ldf 0 ldf 2 mulf stf 0 ldc 0 f2s sout ldc 5 sin ldc -79514 st 0 ldc 2 st 1 ldf 0 negf stf 0 ldc 0 f2s sout 0 ldc 5 sin ldc 1987516 st 0 ldc 1 st 1 ldc 1283765 st 2 ldc 1 st 3 ldf 0 ldf 2 mulf stf 0 ldc 0 f2s sout hlt
        
        --Below are the 5 separate tests from demo3--
        Test1: 50 + 84.537 = 134.537 
            ldc 5 st 0 ldc -1 st 1 ldc 84537 st 2 ldc 3 st 3 ldf 0 ldf 2 addf stf 0 ldc 0 f2s sout hlt
        Test2: 19.875 - 917.65 = -897.775
            ldc 19875 st 0 ldc 3 st 1 ldc 91765 st 2 ldc 2 st 3 ldf 2 ldf 0 subf stf 0 ldc 0 f2s sout hlt
        Test3: -7.725 / -3.09 = 2.5
            ldc -309 st 0 ldc 2 st 1 ldc -7725 st 2 ldc 3 st 3 ldf 0 ldf 2 divf stf 0 ldc 0 f2s sout hlt
        Test4: 10.5 * 7.5 = 78.75 
            ldc 105 st 0 ldc 1 st 1 ldc 75 st 2 ldc 1 st 3 ldf 0 ldf 2 mulf stf 0 ldc 0 f2s sout hlt
        Test5: -795.14 = 795.14
            ldc -79514 st 0 ldc 2 st 1 ldf 0 negf stf 0 ldc 0 f2s sout hlt
        Test6: 198751.6 * 128376.5 = overflow
            ldc 1987516 st 0 ldc 1 st 1 ldc 1283765 st 2 ldc 1 st 3 ldf 0 ldf 2 mulf stf 0 ldc 0 f2s hlt
        */
    }
}
