#include <stdio.h>

//the utility is run with no arguments or the wrong number
void usage(){
	puts("\tUsage:\n\t$ tokenizer <separator string> <tokens>");
        puts("\t<separator string> must be a list of separator symbols,");
        puts("\t\twhich may be any character and/ or escape character");
        puts("\t\tsequence such as \"a \\n\\t\" etc.");
        puts("\t\tUnpredictable behavior will result if unknown escape");
        puts("\t\tsequences are supplied, such as \'\\d\'.");
        puts("\t<tokens> may be a list of characters separated by the");
        puts("\t\tseparator tokens.");
        puts("\tOutput:\n\t\tTokens from <tokens>, printed one per line");
}
