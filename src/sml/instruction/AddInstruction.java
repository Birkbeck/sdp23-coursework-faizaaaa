package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Represents AddInstruction class, to be used with Machine class. Helps interpret the sml instructions for add.
 * @author Faizaa Fazal
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	/**
	 * Represents the instruction String type the instruction can take.
	 */
	public static final String OP_CODE = "add";

	/**
	 *Constructor: an instruction with a label and an opcode
	 *      * (opcode must be an operation of the language)
	 *      *
	 *      * @param label  optional label (can be null)
	 *      * @param result register containing value, also stores the result
	 *      * @param source register containing value for instruction
	 */

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the add instruction in the given machine.
	 *
	 * @param m the machine the instruction runs on
	 * @return NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Returns a string representation of AddInstruction object.
	 * @return string
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
			AddInstruction addInstruction = (AddInstruction) i;

			if(this.label!=null&&addInstruction.getLabel()!=null)
			{

				return this.label.equals(addInstruction.getLabel())
						&&this.result.equals(addInstruction.result)
						&&this.source.equals(addInstruction.source);

			}
			else if(this.label==null&&addInstruction.getLabel()==null){

					return this.result.equals(addInstruction.result)
							&&this.source.equals(addInstruction.source);
				}
		}
		return false;
	}

	/**
	 * Generates hashcode integer for AddInstruction.
	 * @return int of hashcode
	 */
	public int hashCode() {
		return Objects.hash(result,source,opcode,label);
	}
}
