package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// OutInstruction class, to be used with Machine class. Helps interpret the sml instructions for out.

public class OutInstruction extends Instruction {

    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label,RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value2 = m.getRegisters().get(source);
        System.out.println(value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
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