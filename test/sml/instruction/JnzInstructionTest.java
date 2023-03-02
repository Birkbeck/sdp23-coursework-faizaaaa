package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.util.ArrayList;
import java.util.List;

import static sml.Registers.Register.*;

public class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    private Labels labels;


    @BeforeEach
    void setUp() {
        Labels labels = new Labels();
        List<Instruction> program = new ArrayList<>();
        machine = new Machine(labels, program, new Registers());
        registers = machine.getRegisters();
        labels = machine.getLabels();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() throws Exception {
        registers.set(EAX, 30);
        labels.addLabel("f3",4);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Assertions.assertEquals(4, instruction.execute(machine));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Assertions.assertEquals(-1, instruction.execute(machine));

    }

    @Test
    void equalsTest() {
        JnzInstruction a = new JnzInstruction(null,EAX,"e");
        JnzInstruction b = new JnzInstruction(null,EAX,"e");
        Assertions.assertTrue(b.equals(a));

        JnzInstruction c = new JnzInstruction(null,EBP,"f3");
        JnzInstruction d = new JnzInstruction(null,EAX,"fe");
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void hashcodeTest() {
        JnzInstruction a = new JnzInstruction(null,EAX,"7");
        JnzInstruction b = new JnzInstruction(null,EAX,"7");
        Assertions.assertEquals(a.hashCode(),b.hashCode());

        JnzInstruction c = new JnzInstruction("f2",EAX,"2");
        JnzInstruction d = new JnzInstruction(null,EAX,"3");
        Assertions.assertNotEquals(c.hashCode(),d.hashCode());
    }

}
