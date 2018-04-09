package tools;

/**
 * tools.IConstants - set of constants
 *
 * @author Sergey Iryupin
 * @version 0.4.9 dated Apr 09, 2018
 */
public interface IConstants {

    // for class JBasic
    String MSG_START = "Darthmouth BASIC (Oct 01, 1964)";
    String MSG_READY = "Ready";
    String CMD_NEW = "new";
    String CMD_LIST = "list";
    String CMD_SAVE = "save";
    String CMD_LOAD = "load";
    String CMD_RUN = "run";
    String CMD_EXIT = "exit";

    // error messages
    String ERR_ILLEGAL_COMMAND = "Error: Illegal Instruction";
    String ERR_ILLEGAL_CONSTANT = "Error: Illegal Constant";
    String ERR_ILLEGAL_VARIABLE = "Error: Illegal Variable";
    String ERR_INVALID_EXPRESSION = "Error: Invalid Expression";
    String ERR_NO_DATA = "Error: No Data";
    String ERR_UNDEFINED_FUNCTION = "Error: Undefined Function";
    String ERR_UNDEFINED_LINE_NUMBER = "Error: Undefined Line Number";
    String ERR_ILLEGAL_RETURN = "Error: Illegal Return";
    String ERR_FOR_WITHOUT_NEXT = "Error: For Without Next";
    String ERR_NOT_MATCH_WITH_FOR = "Error: Not Match With For";
    String ERR_SUBSCRIPT_ERROR = "Error: Subscript Error";

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

    // defined functions
    String FUNC_FN = "fn";

    // built-in functions
    String FN_SQR = "sqr";
    String FN_INT = "int";
    String FN_ABS = "abs";
    String FN_SIN = "sin";
    String FN_COS = "cos";
    String FN_TAN = "tan";
    String FN_ATN = "atn";
    String FN_LOG = "log";
    String FN_EXP = "exp";
    String FN_RND = "rnd";

    // for compare
    String SIGN_EQU = "=";
    String SIGN_LSS = "<";
    String SIGN_GRT = ">";
    String SIGN_LQU = "<=";
    String SIGN_GQU = ">=";
    String SIGN_NQU = "<>";
}