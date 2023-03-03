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


public class SubInstructionTest {

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
    void testExecuteValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void testExecuteValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }

    @Test
    void testEqualsTest() {
        SubInstruction a = new SubInstruction(null, EAX, EBX);
        SubInstruction b = new SubInstruction(null, EAX, EBX);
        Assertions.assertTrue(b.equals(a));

        SubInstruction c = new SubInstruction(null, EBP, EBX);
        SubInstruction d = new SubInstruction(null, EAX, EBX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void testHashcodeTest() {
        SubInstruction a = new SubInstruction(null, EAX, EBX);
        SubInstruction b = new SubInstruction(null, EAX, EBX);
        Assertions.assertEquals(a.hashCode(), b.hashCode());

    }
}
