package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;
import static sml.Registers.Register.EBX;

public class DivInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
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
        registers.set(EAX, 30);
        registers.set(EBX, 5);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(6, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 40);
        registers.set(EBX, 10);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(4, machine.getRegisters().get(EAX));
    }

    @Test
    void equalsTest() {
        DivInstruction a = new DivInstruction(null,EAX,EBX);
        DivInstruction b = new DivInstruction(null,EAX,EBX);
        Assertions.assertTrue(b.equals(a));

        DivInstruction c = new DivInstruction(null,EBP,EBX);
        DivInstruction d = new DivInstruction(null,EAX,EBX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void hashcodeTest() {
        DivInstruction a = new DivInstruction(null,EAX,EBX);
        DivInstruction b = new DivInstruction(null,EAX,EBX);
        Assertions.assertEquals(a.hashCode(),b.hashCode());

        DivInstruction c = new DivInstruction("f2",EAX,EBX);
        DivInstruction d = new DivInstruction(null,EAX,EBX);
        Assertions.assertNotEquals(c.hashCode(),d.hashCode());
    }
}
