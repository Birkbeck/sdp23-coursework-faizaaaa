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
public class MulInstructionTest {

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
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-30, machine.getRegisters().get(EAX));
    }

    @Test
    void equalsTest() {
        MulInstruction a = new MulInstruction(null,EAX,EBX);
        MulInstruction b = new MulInstruction(null,EAX,EBX);
        Assertions.assertTrue(b.equals(a));

        MulInstruction c = new MulInstruction(null,EBP,EBX);
        MulInstruction d = new MulInstruction(null,EAX,EBX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void hashcodeTest() {
        MulInstruction a = new MulInstruction(null,EAX,EBX);
        MulInstruction b = new MulInstruction(null,EAX,EBX);
        Assertions.assertEquals(a.hashCode(),b.hashCode());

    }
}
