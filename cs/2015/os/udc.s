	.file	"udc.c"
	.section	.rodata
.LC0:
	.string	"I am slain!"
	.text
.globl segment_fault_handler
	.type	segment_fault_handler, @function
segment_fault_handler:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	$.LC0, (%esp)
	call	puts
	leave
	ret
	.size	segment_fault_handler, .-segment_fault_handler
	.section	.rodata
.LC1:
	.string	"I live again!"
	.text
.globl main
	.type	main, @function
main:
	pushl	%ebp
	movl	%esp, %ebp
	andl	$-16, %esp
	subl	$32, %esp
	movl	$0, 28(%esp)
	movl	$segment_fault_handler, 4(%esp)
	movl	$11, (%esp)
	call	signal
	movl	$0, %eax
	movl	(%eax), %eax
	movl	%eax, 28(%esp)
	movl	$.LC1, (%esp)
	call	puts
	movl	$0, %eax
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.4.7 20120313 (Red Hat 4.4.7-16)"
	.section	.note.GNU-stack,"",@progbits
