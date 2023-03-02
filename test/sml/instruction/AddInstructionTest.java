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
    machine = new Machine(labels, program, new Registers());
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
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void equalsTest() {
    AddInstruction a = new AddInstruction(null,EAX,EBX);
    AddInstruction b = new AddInstruction(null,EAX,EBX);
    Assertions.assertTrue(b.equals(a));

    AddInstruction c = new AddInstruction(null,EBP,EBX);
    AddInstruction d = new AddInstruction(null,EAX,EBX);
    Assertions.assertFalse(c.equals(d));
    // add in more tests here once created other classes
  }

  @Test
  void hashcodeTest() {
    AddInstruction a = new AddInstruction(null,EAX,EBX);
    AddInstruction b = new AddInstruction(null,EAX,EBX);
    Assertions.assertEquals(a.hashCode(),b.hashCode());
  }
}