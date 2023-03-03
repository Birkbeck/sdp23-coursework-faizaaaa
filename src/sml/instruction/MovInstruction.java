package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents MovInstruction class, to be used with Machine class. Helps interpret the sml instructions for mov.
 * @author Faizaa Fazal
 */
public class MovInstruction extends Instruction {

    private final RegisterName result;
    private final int input;
    /**
     * Represents the instruction String type the instruction can take.
     */
    public static final String OP_CODE = "mov";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result register where input is moved into
     * @param input integer which is stored in register
     */
    public MovInstruction(String label, RegisterName result, int input) {
        super(label, OP_CODE);
        this.result = result;
        this.input = input;
    }

    /**
     * Executes the move instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return  NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, input);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    /**
     * Returns a string representation of MovInstruction object.
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + Integer.toString(input);
    }
    /**
     * Checks if a different instruction (the parameter) is equal to the current Instruction object.
     */
    @Override
    public boolean equals(Instruction i) {
        if(i.getOpcode().equals(this.opcode)) {
            MovInstruction movInstruction = (MovInstruction) i;

            if(this.label!=null&&movInstruction.getLabel()!=null)
            {

                return this.label.equals(movInstruction.getLabel())
                        &&this.result.equals(movInstruction.result)
                        &&this.input== movInstruction.input;

            }
            else if(this.label==null&&movInstruction.getLabel()==null){

                return this.result.equals(movInstruction.result)
                        &&this.input== movInstruction.input;
            }
        }
        return false;
    }
    /**
     * Generates hashcode for MovInstruction.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(result,opcode,label,input);
    }
}
