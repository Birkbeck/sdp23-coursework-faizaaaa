package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents OutInstruction class, to be used with Machine class. Helps interpret the sml instructions for out.
 *
 * @author Faizaa Fazal
 */
public class OutInstruction extends Instruction {

    private final RegisterName source;
    /**
     * Represents the instruction String type the instruction can take.
     */
    public static final String OP_CODE = "out";

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param source register which contains integer to print
     */
    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    /**
     * Executes the out instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     * the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value2 = m.getRegisters().get(source);
        System.out.println(value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Returns a string representation of OutInstruction object.
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    /**
     * Checks if a different instruction (the parameter) is equal to the current Instruction object.
     */
    @Override
    public boolean equals(Instruction i) {
        if (i.getOpcode().equals(this.opcode)) {
            OutInstruction outInstruction = (OutInstruction) i;

            if (this.label != null && outInstruction.getLabel() != null) {

                return this.label.equals(outInstruction.getLabel())
                        && this.source.equals(outInstruction.source);

            } else if (this.label == null && outInstruction.getLabel() == null) {

                return this.source.equals(outInstruction.source);
            }
        }
        return false;
    }

    /**
     * Generates hashcode for OutInstruction.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, opcode, label);
    }
}
