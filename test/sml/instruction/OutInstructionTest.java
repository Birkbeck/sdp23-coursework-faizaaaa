package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sml.Registers.Register.*;

public class OutInstructionTest {
    private Machine machine;
    private Registers registers;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Labels labels = new Labels();
        List<Instruction> program = new ArrayList<>();
        machine = new Machine(labels, program, Registers.getInstance());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(standardOut);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        assertEquals("5",outputStreamCaptor.toString().trim());

    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 7);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        assertEquals("7",outputStreamCaptor.toString().trim());

    }

    @Test
    void equalsTest() {
        OutInstruction a = new OutInstruction(null,EAX);
        OutInstruction b = new OutInstruction(null,EAX);
        Assertions.assertTrue(b.equals(a));

        OutInstruction c = new OutInstruction(null,EBP);
        OutInstruction d = new OutInstruction(null,EAX);
        Assertions.assertFalse(c.equals(d));
    }

    @Test
    void hashcodeTest() {
        OutInstruction a = new OutInstruction(null,EAX);
        OutInstruction b = new OutInstruction(null,EAX);
        Assertions.assertEquals(a.hashCode(),b.hashCode());

        OutInstruction c = new OutInstruction("f2",EAX);
        OutInstruction d = new OutInstruction(null,EAX);
        Assertions.assertNotEquals(c.hashCode(),d.hashCode());

    }
}
