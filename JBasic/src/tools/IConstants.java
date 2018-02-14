package tools;

/**
 * tools.IConstants - set of constants
 *
 * @author Sergey Iryupin
 * @version 0.4 dated Feb 14, 2018
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

    // error messages
    String ERR_ILLEGAL_COMMAND = "Error: Illegal Instruction";
    String ERR_ILLEGAL_VARIABLE = "Error: Illegal Variable";
    String ERR_INVALID_EXPRESSION = "Error: Invalid Expression";

    // for class ProgramLines
    String FILE_EXT = ".bas";

    // for class Interpreter
    String OPER_LET = "let";
    String OPER_READ = "read";
    String OPER_DATA = "data";
    String OPER_INPUT = "input";
    String OPER_PRINT = "print";
    String OPER_GOTO = "goto";
    String OPER_IF = "if";
    String OPER_THEN = "then";
    String OPER_FOR = "for";
    String OPER_TO = "to";
    String OPER_STEP = "step";
    String OPER_NEXT = "next";
    String OPER_END = "end";
    String OPER_STOP = "stop";
    String OPER_DEF = "def";
    String OPER_GOSUB = "gosub";
    String OPER_RETURN = "return";
    String OPER_DIM = "dim";
    String OPER_REM = "rem";
}