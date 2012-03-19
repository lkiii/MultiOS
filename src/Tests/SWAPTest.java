/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;
import rvm.*;

/**
 *
 * @author lukas
 */
public class SWAPTest {
    public static void main(String[] args) {
        Memory mem = new ExternalMemory(0xFFF);
        ExternalMemory exMem = new ExternalMemory(0xFFFF);
        VirtualMemorySWAP vm = new VirtualMemorySWAP(new Word(0xFF), mem, exMem);
        mem.writeWord(0xFF, new Word(0x0));
    }
}
