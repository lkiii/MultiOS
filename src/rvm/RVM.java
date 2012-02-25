/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author ernestas
 */
public class RVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        byte [] a = new byte[8];
        a[0]=1;
        a[1]=1;
        a[2]=1;
        a[3]=1;
        a[4]=1;
        a[5]=1;
        a[6]=1;
        Word w = new Word(a);
    }
}
