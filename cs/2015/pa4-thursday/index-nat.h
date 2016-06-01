#include <stdio.h>

struct filestruct{
    char* filename;
    int count;
    struct filestruct* next;
};

struct wordstruct{
    char* word;
    int totalcount;
    struct filestruct* filelist;
    struct wordstruct* next;
};

struct indexstruct{
    struct wordstruct* wordlist;
};

int contains_word(struct indexstruct* myindex, char* word);
struct wordstruct* contains_word_s(struct indexstruct* myindex, char* word);

int contains_file(struct wordstruct* myword, char* filename);
struct filestruct* contains_file_s(struct wordstruct* myword, char* filename);

int insertfile(struct wordstruct* myword, struct filestruct* myfile);

int insertword(struct indexstruct* myindex, struct wordstruct* myword);

int add_to_index(struct indexstruct* myindex, char* word, char* filename);
int add_word(struct indexstruct* myindex, char* word, char* filename);

void print_index(struct indexstruct* myindex);

void print_index_to_file(struct indexstruct* myindex, FILE* fp);