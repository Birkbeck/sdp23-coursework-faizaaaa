package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// MulInstruction class, to be used with Machine class. Helps interpret the sml instructions for mul.

public class MulInstruction extends Instruction {

    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result
     * @param source
     */
    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Instruction i) {
        if(i.getOpcode().equals(this.opcode)) {
            MulInstruction b = (MulInstruction) i;

            if(this.label!=null&&i.getLabel()!=null)
            {

                return this.label.equals(i.getLabel())
                        &&this.result.equals(((MulInstruction) i).result)
                        &&this.source.equals(((MulInstruction) i).source);

            }
            else if(this.label==null&&i.getLabel()==null){

                return this.result.equals(((MulInstruction) i).result)
                        &&this.source.equals(((MulInstruction) i).source);
            }
            return false;
        }
        else {return false;}
    }

    @Override
    public int hashCode() {
        return Objects.hash(result,source,opcode,label);
    }
}
