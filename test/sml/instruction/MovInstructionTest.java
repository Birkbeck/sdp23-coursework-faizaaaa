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

public class MovInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        Labels labels = new Labels();
        List<Instruction> program = new ArrayList<>();
        machine = new Machine(labels, program, Registers.getInstance());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        Instruction instruction = new MovInstruction(null, EAX, 1);
        instruction.execute(machine);
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        Instruction instruction = new MovInstruction(null, EBP, 123);
        instruction.execute(machine);
        Assertions.assertEquals(123, machine.getRegisters().get(EBP));

    }

    @Test
    void equalsTest() {
        MovInstruction a = new MovInstruction(null,EAX,1);
        MovInstruction b = new MovInstruction(null,EAX,1);
        Assertions.assertTrue(b.equals(a));

        MovInstruction c = new MovInstruction(null,EBP,3);
        MovInstruction d = new MovInstruction(null,EAX,2);
        Assertions.assertFalse(c.equals(d));

    }

    @Test
    void hashcodeTest() {
        MovInstruction a = new MovInstruction(null,EAX,7);
        MovInstruction b = new MovInstruction(null,EAX,7);
        Assertions.assertEquals(a.hashCode(),b.hashCode());

        MovInstruction c = new MovInstruction("f2",EAX,2);
        MovInstruction d = new MovInstruction(null,EAX,3);
        Assertions.assertNotEquals(c.hashCode(),d.hashCode());

    }
}
