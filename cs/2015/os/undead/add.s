	.file	"add.c"
	.text
.globl main
	.type	main, @function
main:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
#APP
# 2 "add.c" 1
	nop
# 0 "" 2
#NO_APP
	movl	$0, -4(%ebp)
#APP
# 4 "add.c" 1
	nop
# 0 "" 2
#NO_APP
	addl	$1, -4(%ebp)
#APP
# 6 "add.c" 1
	nop
# 0 "" 2
#NO_APP
	movl	$0, %eax
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.4.7 20120313 (Red Hat 4.4.7-16)"
	.section	.note.GNU-stack,"",@progbits
