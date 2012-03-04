package Interpretator;

import rvm.*;

/**
 *
 * @author ernestas
 */
public class Operation {
    public enum Opcode {ADD, SUB, LR, SR, CM, PUSH, POP, JP, JE, JL, JG, PD, GD};
    private Opcode operation;
    private byte x1, x2;
    
    public Operation(Word op) {
        String opcode = op.get().toString();
        switch (opcode) {
            case "AD": // ADD, AD D ar ADD?
                add(/**/);
                break;
            case "SB":
                break;
            case "LR":
                break;
            case "SR":
                break;
            case "CM":
                break;
            case "PU": // 4b ne 2
                if (opcode)
                break;
            case "PO":
                break;
            case "JP":
                break;
            case "JE":
                break;
            case "JL": 
                break;
            case "JG":
                break;
            case "PD":
                break;
            case "GD":
                break;
            case "HALT":
                break;
            default:
                // interrupt. unknown opcode
                break;
                
        }
    }
        
        public Opcode getOperation() {
            return operation;
        }
        
        public byte getArgument1() {
            return x1;
        }
        
        public byte getArgument2() {
            return x2;
        }
        
        
               
    }
    
    
    

