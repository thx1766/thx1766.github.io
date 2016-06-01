#include <stdio.h>

const int N = 16;
const int blocksize = 16;

/*void hello(char *a, int *b)
{
	a[threadIdx.x] += b[threadIdx.x];
}
*/

int
main()
{
	char *ad;

	const int csize = N*sizeof(char);

	cudaMalloc( (void**)&ad, csize );

	return 0;
}
