.globl fib
	.type fib, @function

fib:
	pushl %ebp
	movl %esp, %ebp
	#move stack pointer to ebp

	movl 8(%ebp), %eax
	#put input into eax

	cmpl	$2, 8(%ebp)
	jl	end
	#check if given variable <2, jump to end

	movl 	$0, -4(%ebp) #last
	movl	$1, -8(%ebp) #last2
	movl	$0, -12(%ebp) #temp
	movl	$0, -16(%ebp) #count
	#assign last, last2, temp, count

loop:
	cmpl %eax, -8(%ebp)
	jl end
	movl -12(%ebp), %ebx
	movl %ebx, -8(%ebp)

	movl -8(%ebp), %ebx
	movl %ebx, -4(%ebp)

	movl -4(%ebp),%ebx
	movl %ebx, -12(%ebp)

	addl $1, -16(%ebp)
	jmp loop

end:
	movl -8(%ebp), %eax
	popl %ebp
	ret
