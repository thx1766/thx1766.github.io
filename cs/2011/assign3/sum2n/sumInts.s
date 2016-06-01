.globl sumInts
	.type sumInts, @function

sumInts:
	pushl %ebp
	movl %esp, %ebp
	#move stack pointer to ebp

	movl $0, %eax
	#put n into eax

	movl	$1, %esi #i
	#assign i

loop:
	cmpl %esi, 8(%ebp)
	jl end

	movl 12(%ebp), %ebx
	#put k into ebx
	
	addl %esi, %ebx
	jo overflow
	addl %ebx, %eax
	jo overflow
	
	addl $1, %esi
	jmp loop

overflow:
	movl $-1, %eax

end:
	popl %ebp
	ret
