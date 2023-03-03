package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents SubInstruction class, to be used with Machine class. Helps interpret the sml instructions for sub.
 * @author Faizaa Fazal
 */
public class SubInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;
    /**
     * Represents the instruction String type the instruction can take.
     */
    public static final String OP_CODE = "sub";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result register, contains integer used to subtract from, also used to store result
     * @param source register, contains integer which is subtracted from result
     */
    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }
    /**
     * Executes the sub instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return  NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 - value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    /**
     * Returns a string representation of SubInstruction object.
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }
    /**
     * Checks if a different instruction (the parameter) is equal to the current Instruction object.
     */
    @Override
    public boolean equals(Instruction i) {
        if(i.getOpcode().equals(this.opcode)) {
            SubInstruction b = (SubInstruction) i;

            if(this.label!=null&&i.getLabel()!=null)
            {

                return this.label.equals(i.getLabel())
                        &&this.result.equals(((SubInstruction) i).result)
                        &&this.source.equals(((SubInstruction) i).source);

            }
            else if(this.label==null&&i.getLabel()==null){

                return this.result.equals(((SubInstruction) i).result)
                        &&this.source.equals(((SubInstruction) i).source);
            }
            return false;
        }
        else {return false;}
    }
    /**
     * Generates hashcode for OutInstruction.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(result,source,opcode,label);
    }
}
