package tools;

/**
 * tools.IConstants - set of constants
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jan 26, 2018
 */
public interface IConstants {

    // for class JBasic
    String MSG_START = "Darthmouth BASIC (Oct 01, 1964)";
    String MSG_READY = "Ready";
    String CMD_NEW = "new";
    String CMD_LIST = "list";
    String CMD_VALUES = "values";
    String CMD_SAVE = "save";
    String CMD_LOAD = "load";
    String CMD_RUN = "run";
    String CMD_EXIT = "exit";
    String ERR_UNKNOWN_COMMAND = "Error: unknown command";

    // for class ProgramLines
    String FILE_EXT = ".bas";

    // for class Interpreter
    String OPER_REM = "rem";
    String OPER_INPUT = "input";
    String OPER_PRINT = "print";
    String OPER_IF = "if";
    String OPER_GOTO = "goto";
}