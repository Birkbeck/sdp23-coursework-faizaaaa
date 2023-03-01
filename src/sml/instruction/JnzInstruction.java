package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.awt.*;

// JnzInstruction class, to be used with Machine class. Helps interpret the sml instructions for jnz.

public class JnzInstruction extends Instruction {
    private final RegisterName source;

    private final String labelToJumpTo;
    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String labelToJumpTo) {
        super(label,OP_CODE);
        this.source = source;
        this.labelToJumpTo = labelToJumpTo;
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
