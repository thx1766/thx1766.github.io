.globl fib
	.type fib, @function

fib:
	pushl %ebp
	movl %esp, %ebp
	#move stack pointer to ebp

	cmpl	$2, 8(%ebp)
	jl	end
	#check if given variable <2, jump to end

	movl 	$1, %ebx #last
	movl	$0, %ecx #temp
	movl	$0, 12(%ebp) #last2
	movl	$1, %esi #counter

loop:
	cmpl %esi, 8(%ebp)
	jle end

	movl 12(%ebp), %ecx

	addl %ebx, %ecx 
	jo overflow

	movl %ecx, %eax

	movl %ebx, 12(%ebp)

	movl %eax, %ebx
	
	addl $1, %esi
	
	jmp loop

overflow:
	movl $-1, %eax

end:
	popl %ebp
	ret
