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

class AddInstructionTest {
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
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new AddInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(11, machine.getRegisters().get(EAX));
    }

    @Test
    void testExecuteIsValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new AddInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    @Test
    void testExecuteWithInvalidInput() throws IllegalArgumentException {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Instruction instruction = new AddInstruction(null, Registers.Register.valueOf("XXX"), EBX);
            instruction.execute(machine);
        });
    }

    @Test
    void testEqualsMethod() {
        AddInstruction a = new AddInstruction(null, EAX, EBX);
        AddInstruction b = new AddInstruction(null, EAX, EBX);
        Assertions.assertTrue(b.equals(a));

        AddInstruction c = new AddInstruction(null, EBP, EBX);
        AddInstruction d = new AddInstruction(null, EAX, EBX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void testHashCodeMethod() {
        AddInstruction a = new AddInstruction(null, EAX, EBX);
        AddInstruction b = new AddInstruction(null, EAX, EBX);
        Assertions.assertEquals(a.hashCode(), b.hashCode());
    }
}