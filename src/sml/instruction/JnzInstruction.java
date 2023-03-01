package sml.instruction;

import sml.Instruction;
import sml.Machine;

public class JnzInstruction extends Instruction {

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
