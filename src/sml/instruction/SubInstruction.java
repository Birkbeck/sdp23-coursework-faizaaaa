package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class SubInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result
     * @param source
     */
    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine machine) {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Instruction i) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
