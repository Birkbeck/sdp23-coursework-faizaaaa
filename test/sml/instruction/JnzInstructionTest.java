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

public class JnzInstructionTest {
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
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        instruction.execute(machine);
        Assertions.assertNotEquals(-1, instruction.execute(machine));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        instruction.execute(machine);
        Assertions.assertEquals(-1, instruction.execute(machine));

    }

    @Test
    void equalsTest() {

    }

    @Test
    void hashcodeTest() {

    }

}
