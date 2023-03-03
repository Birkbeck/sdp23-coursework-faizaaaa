package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents JnzInstruction class, to be used with Machine class. Helps interpret the sml instructions for jnz.
 *
 * @author Faizaa Fazal
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;

    private final String labelToJumpTo;
    /**
     * Represents the instruction String type the instruction can take.
     */
    public static final String OP_CODE = "jnz";

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label         optional label (can be null)
     * @param labelToJumpTo label used to identify instruction to jump to
     * @param source        register used to determine if the instruction
     *                      should jump to label specified or continue to next instruction in program
     */
    public JnzInstruction(String label, RegisterName source, String labelToJumpTo) {
        super(label, OP_CODE);
        this.source = source;
        this.labelToJumpTo = labelToJumpTo;
    }

    /**
     * Executes the jump instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the new program counter (for jump instructions)
     * or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     * the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value2 = m.getRegisters().get(source);
        if (value2 == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        } else {
            return m.getLabels().getAddress(labelToJumpTo);
        }


    }

    /**
     * Returns a string representation of JnzInstruction object.
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + labelToJumpTo;
    }

    /**
     * Checks if a different instruction (the parameter) is equal to the current Instruction object.
     */
    @Override
    public boolean equals(Instruction i) {
        if (i.getOpcode().equals(this.opcode)) {
            JnzInstruction jnzInstruction = (JnzInstruction) i;

            if (this.label != null && jnzInstruction.getLabel() != null) {

                return this.label.equals(jnzInstruction.getLabel())
                        && this.labelToJumpTo.equals(jnzInstruction.labelToJumpTo)
                        && this.source.equals(jnzInstruction.source);

            } else if (this.label == null && jnzInstruction.getLabel() == null) {

                return this.labelToJumpTo.equals(jnzInstruction.labelToJumpTo)
                        && this.source.equals(jnzInstruction.source);
            }
        }
        return false;
    }

    /**
     * Generates hashcode for JnzInstruction.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, opcode, label, labelToJumpTo);
    }
}
