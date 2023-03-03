package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.*;

import static sml.Registers.Register;


/**
 * This class takes in a file and translates that file containing SML instructions.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Faizaa Fazal and Lecturer
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    /**
     * Takes in file
     * @param fileName file containing SML instructions
     */
    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    /**
     *
     * @param labels labels map containing all the labels and the instruction positions at which they occur
     * @param program contains list of instructions present in program
     * @throws Exception
     */
    public void readAndTranslate(Labels labels, List<Instruction> program) throws Exception {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String className = "sml.instruction."+opcode.substring(0,1).toUpperCase() + opcode.substring(1)+ "Instruction";
        try {
            Class<?> instruction = Class.forName(className);
            Constructor<?>[] constructor = instruction.getConstructors();
            Class[] parameterTypes = constructor[0].getParameterTypes();
            if (parameterTypes.length==3) {
                String param = parameterTypes[2].toString();
                if(param.equals("interface sml.RegisterName")) {
                    String r = scan();
                    String s = scan();
                    return (Instruction)
                            constructor[0].newInstance(label,Register.valueOf(r),Register.valueOf(s));
                }
                else if(param.equals("class java.lang.String")) {

                    String r = scan();
                    String s = scan();
                    return (Instruction)
                            constructor[0].newInstance(label,Register.valueOf(r),s);
                }
                else {
                    String r = scan();
                    String s = scan();
                    int input = Integer.parseInt(s);
                    return (Instruction)
                            constructor[0].newInstance(label,Register.valueOf(r),input);
                }
            }
            else {
                String s = scan();
                return (Instruction)
                        constructor[0].newInstance(label,Register.valueOf(s));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /**
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}