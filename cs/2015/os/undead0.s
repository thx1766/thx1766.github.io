	.file	"undead.c"
	.section	.rodata
.LC0:
	.string	"I am slain!"
.LC1:
	.string	"%x\n"
	.text
.globl segment_fault_handler
	.type	segment_fault_handler, @function
segment_fault_handler:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$40, %esp
	movl	$.LC0, (%esp)
	call	puts
	leal	8(%ebp), %eax
	movl	%eax, -12(%ebp)
	movl	$.LC1, %eax
	movl	-12(%ebp), %edx
	movl	%edx, 4(%esp)
	movl	%eax, (%esp)
	call	printf
	movl	4(%ebp), %eax
	movl	%eax, %edx
	movl	$.LC1, %eax
	movl	%edx, 4(%esp)
	movl	%eax, (%esp)
	call	printf
	leave
	ret
	.size	segment_fault_handler, .-segment_fault_handler
	.section	.rodata
.LC2:
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
	movl	$.LC2, (%esp)
	call	puts
	movl	$0, %eax
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.4.7 20120313 (Red Hat 4.4.7-16)"
	.section	.note.GNU-stack,"",@progbits
