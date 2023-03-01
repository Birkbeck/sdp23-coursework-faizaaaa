package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class DivInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param result
     * @param source
     */
    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 / value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Instruction i) {
        if(i.getOpcode().equals(this.opcode)) {
            DivInstruction b = (DivInstruction) i;

            if(this.label!=null&&i.getLabel()!=null)
            {

                return this.label.equals(i.getLabel())
                        &&this.result.equals(((DivInstruction) i).result)
                        &&this.source.equals(((DivInstruction) i).source);

            }
            else if(this.label==null&&i.getLabel()==null){

                return this.result.equals(((DivInstruction) i).result)
                        &&this.source.equals(((DivInstruction) i).source);
            }
            return false;
        }
        else {return false;}
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
