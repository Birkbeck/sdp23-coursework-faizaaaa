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

public class DivInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        Labels labels = new Labels();
        List<Instruction> program = new ArrayList<>();
        machine = new Machine(labels, program, Registers.getInstance());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void testExecuteValid() {
        registers.set(EAX, 30);
        registers.set(EBX, 5);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(6, machine.getRegisters().get(EAX));
    }

    @Test
    void testExecuteValidTwo() {
        registers.set(EAX, 40);
        registers.set(EBX, 10);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(4, machine.getRegisters().get(EAX));
    }

    @Test
    void testExecuteWithInvalidInputForRegister() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> registers.set(Registers.Register.valueOf("zzz"), 40));
    }

    @Test
    void testExecuteWithInvalidInputForInstruction() throws IllegalArgumentException {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DivInstruction divInstruction = new DivInstruction(null, Registers.Register.valueOf("XXX"), EBX);
            divInstruction.execute(machine);
        });
    }

    @Test
    void testEqualsMethod() {
        DivInstruction a = new DivInstruction(null, EAX, EBX);
        DivInstruction b = new DivInstruction(null, EAX, EBX);
        Assertions.assertTrue(b.equals(a));

        DivInstruction c = new DivInstruction(null, EBP, EBX);
        DivInstruction d = new DivInstruction(null, EAX, EBX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void testHashCodeMethod() {
        DivInstruction a = new DivInstruction(null, EAX, EBX);
        DivInstruction b = new DivInstruction(null, EAX, EBX);
        Assertions.assertEquals(a.hashCode(), b.hashCode());

        DivInstruction c = new DivInstruction("f2", EAX, EBX);
        DivInstruction d = new DivInstruction(null, EAX, EBX);
        Assertions.assertNotEquals(c.hashCode(), d.hashCode());
    }
}
