/*

* Copyright 2008, Karen Hains, UWA . All rights reserved.

*

* NOTICE TO USER:

*

* This source code is subject to NVIDIA ownership rights under U.S. and

* international Copyright laws. Users and possessors of this source code

* are hereby granted a nonexclusive, royalty-free license to use this code

* in individual and commercial software.

*

* WE MAKES NO REPRESENTATION ABOUT THE SUITABILITY OF THIS SOURCE

* CODE FOR ANY PURPOSE. IT IS PROVIDED "AS IS" WITHOUT EXPRESS OR

* IMPLIED WARRANTY OF ANY KIND.

*/

/* HellowWorld Project
* This project demonstrates the basics on how to setup
* an example GPU Copmuting application.
*
* THis file contains the CPU (host) code.
*/

// Host defines
#define NUM_THREADS 32
#define STR_SIZE 50

// Includes
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

// CUDA includes
#include <cutil.h> // CUDA Utility Tools

// GPU Kernels declarations - declare as inlcude
#include <HelloWorld_kernel.cu>

//////////////////////
// Program main
//////////////////////
int main( int argc, char** argv)
{

// Host variables
int i,nBytes;
unsigned int timer;
unsigned int num_threads;
char *cpu_odata;
char *string;

// GPU variables
char *gpu_odata;
int str_size;

/////////////////////////////////////////////////////////////////////
// This routine gets the number of GPUs existing in the computer
// For each GPU (device) found, it checks to see if there is a GPU
// that supports CUDA. If no GPU that supports CUDA is found,
// the routine wll exit
/////////////////////////////////////////////////////////////////////
CUT_DEVICE_INIT();

/////////////////////////////////////////////////////////////////////
// Create and start a timer called "timer"
// alls to create ans start times are enveloped in the CUT_SAFE_CALL
// This CUDA Utility Tool checks for errors upon return.
// If an error is found, it prints out and error message, file name,
// and line number in file where the error can be found
/////////////////////////////////////////////////////////////////////
timer = 0;
CUT_SAFE_CALL(cutCreateTimer(&timer));
CUT_SAFE_CALL(cutStartTimer(timer));

// Initialize CPU variables and allocate required memory
num_threads = (unsigned int) NUM_THREADS;
nBytes = num_threads*STR_SIZE*sizeof(char);

// Allocate and initialize CPU output vector
string = (char *) malloc(STR_SIZE);
if(!string) {
printf("Cannot allocate string memory on CPU\n");
exit(-1);
}
cpu_odata = (char *) malloc(nBytes);
if(!cpu_odata) {
printf("Cannot allocate cpu_odata memory on CPU\n");
exit(-1);
}

// Allocate GPU (device) memory and variables
str_size = (int) STR_SIZE;
CUDA_SAFE_CALL(cudaMalloc( (void**) &gpu_odata, nBytes));

// Setup kernel execution parameters
dim3 grid(1,1,1);
dim3 threads(num_threads,1,1);

// Execute the kernel on the GPU
HelloWorld_kernel&lt;&lt;&lt; grid, threads &gt;&gt;&gt;(str_size, gpu_odata);

// Check if kernel execution generated and error
CUT_CHECK_ERROR("Kernel execution failed");

// Copy result from GPU to CPU
CUDA_SAFE_CALL(cudaMemcpy(cpu_odata,gpu_odata,nBytes,cudaMemcpyDeviceToHost));

// Stop the timer
CUT_SAFE_CALL(cutStopTimer(timer));
printf( "Processing time: %f (ms)\n", cutGetTimerValue(timer));

// Delete the timer
CUT_SAFE_CALL(cutDeleteTimer(timer));

// Output results is same as the expected solution
for(i=0;i&lt;num_threads;i++) {
strncpy(string,&cpu_odata[i*STR_SIZE],STR_SIZE);
printf("From thread %d: %s\n",i,string);
}


//////////////////////////////////////////
// All done - clean up and exit
//////////////////////////////////////////
// Free up CPU memory
free(cpu_odata);

// Free up GPU memory
CUDA_SAFE_CALL(cudaFree(gpu_odata));

// Use CUDA Utility Tool to exit cleanly
CUT_EXIT(argc, argv); 
}
