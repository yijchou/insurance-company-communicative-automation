package problem1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * This class parses up the command line that is input from the user.
 * Some options take arguments, they must immediately follow the command.
 * Some options require other options to also be present.
 * A user can request both emails and letters as long as all the necessary inputs are provided.
 */
public class CommandProcessor implements ICommandProcessor {

    // Accepted Command Line Arguments
    private static final String EMAIL = "--email";
    private static final String EMAIL_TEMPLATE = "--email-template";
    private static final String LETTER = "--letter";
    private static final String LETTER_TEMPLATE = "--letter-template";
    protected static final String OUTPUT_DIR = "--output-dir";
    protected static final String CSV_FILE = "--csv-file";

    // A HashSet of Strings that holds the commands
    private HashSet<String> commands = new HashSet<>();
    // A HashMap of String Key and String Value that holds parameters
    private HashMap<String, String> parameters = new HashMap<>();

    /**
     * Creating A Command Processor with cmdline line input
     * @param cmdline the input from the command line from the user
     * @throws InvalidArgumentException is thrown if the process arguments catches an error
     */
    public CommandProcessor(String[] cmdline) throws InvalidArgumentException {
        this.commands = commands;
        this.parameters = parameters;

        this.processArgs(cmdline);
    }

    public CommandProcessor()  {
        this.commands = commands;
        this.parameters = parameters;

    }

    /**
     * take in what was written in the command line and populate command and parameter
     * @param cmdline the string from main
     * @throws InvalidArgumentException is thrown when the input violates rules for arguments
     */
    @Override
    public void processArgs(String[] cmdline) throws InvalidArgumentException {
        for (int i = 0; i < (cmdline.length); i++) {
            try {
                switch (cmdline[i]) {
                    case EMAIL:
                        this.commands.add(cmdline[i]);
                        break;
                    case EMAIL_TEMPLATE:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(EMAIL_TEMPLATE, cmdline[i + 1]);
                            i++; // skip to next
                        } else {
                            throw new InvalidArgumentException("Email Template requires an argument!");
                        }
                        break;
                    case LETTER:
                        this.commands.add(cmdline[i]);
                        break;
                    case LETTER_TEMPLATE:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(LETTER_TEMPLATE, cmdline[i + 1]);
                            i++; // skip to next
                        } else {
                            throw new InvalidArgumentException("Letter Template requires an argument!");
                        }
                        break;
                    case OUTPUT_DIR:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(OUTPUT_DIR, cmdline[i + 1]);
                            i++; // skip to next 
                        } else {
                            throw new InvalidArgumentException("Output Directory requires an argument!");
                        }
                        break;
                    case CSV_FILE:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(CSV_FILE, cmdline[i + 1]);
                            i++;
                        } else {
                            throw new InvalidArgumentException("CSV File requires an argument!");
                        }
                        break;
                    default:
                        throw new InvalidArgumentException("Unknown Argument!");
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidArgumentException("Argument doesn't follow a valid value.");
            }
        }
        if (this.commands.contains(EMAIL)){
            if(!emailValidate()){
                throw new InvalidArgumentException("Email needs a template!");
            }
        }
        if (this.commands.contains(LETTER)){
            if(!letterValidate()) {
                throw new InvalidArgumentException("Letter needs a template!");
            }
        }
        if(!(this.commands.contains(CSV_FILE))){
            throw new InvalidArgumentException("A CSV File is needed!");
        }
        if(!(this.commands.contains(OUTPUT_DIR))){
            throw new InvalidArgumentException("An Output Directory is needed!");
        }

    }

    private boolean emailValidate () {
        return ((this.commands.contains(EMAIL)) && (this.commands.contains(EMAIL_TEMPLATE)));
    }

    private boolean letterValidate () {
        return ((this.commands.contains(LETTER)) && (this.commands.contains(LETTER_TEMPLATE)));
    }

    /**
     * Checks that the arguments following the commands that require them exist
     * @param arg the argument following the command
     * @return True if the argument is valid and False if it is not
     */
    private boolean validateArgument(String[] arg, Integer index, Integer length) {
        // can't be nothing
        Integer endOfCmd = index + 1;
        if (endOfCmd.equals(length)){
            return Boolean.FALSE;
        }

        // can't be another command
        if ((arg[index + 1].equals(EMAIL)) || (arg[index + 1].equals(LETTER)) ||
                (arg[index + 1].equals(OUTPUT_DIR)) || (arg[index + 1].equals(CSV_FILE))){
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public HashSet<String> getCommands() {
        return this.commands;
    }

    public HashMap<String, String> getParameters() {
        return this.parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandProcessor)) return false;
        CommandProcessor that = (CommandProcessor) o;
        return Objects.equals(commands, that.commands) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commands, parameters);
    }

    @Override
    public String toString() {
        return "CommandProcessor{" +
                "commands=" + commands +
                ", parameters=" + parameters +
                '}';
    }
}
