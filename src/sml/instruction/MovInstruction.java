package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// MovInstruction class, to be used with Machine class. Helps interpret the sml instructions for mov.

public class MovInstruction extends Instruction {

    private final RegisterName result;
    private final int input;

    public static final String OP_CODE = "mov";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result
     * @param input
     */
    public MovInstruction(String label, RegisterName result, int input) {
        super(label, OP_CODE);
        this.result = result;
        this.input = input;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, input);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + Integer.toString(input);
    }

    @Override
    public boolean equals(Instruction i) {
        if(i.getOpcode().equals(this.opcode)) {
            MovInstruction b = (MovInstruction) i;

            if(this.label!=null&&i.getLabel()!=null)
            {

                return this.label.equals(i.getLabel())
                        &&this.result.equals(((MovInstruction) i).result)
                        &&this.input==((MovInstruction) i).input;

            }
            else if(this.label==null&&i.getLabel()==null){

                return this.result.equals(((MovInstruction) i).result)
                        &&this.input==((MovInstruction) i).input;
            }
            return false;
        }
        else {return false;}
    }

    @Override
    public int hashCode() {
        return Objects.hash(result,opcode,label,input);
    }
}
