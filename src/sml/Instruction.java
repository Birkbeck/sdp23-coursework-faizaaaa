package sml;


// TODO: write a JavaDoc for the class
// Abstract class of Instruction which is inherited by other classes to create specific instructions.

import java.util.Objects;

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;


	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;

	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}


	// It shows that it is a method that does not have it's implementation.
	@Override
	public abstract String toString();

	public abstract boolean equals(Instruction i);
	public abstract int hashCode();

}
