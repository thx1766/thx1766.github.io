	.file	"new.c"
	.section	.rodata
.LC0:
	.string	"Segment Fault Detected"
	.align 4
.LC1:
	.string	"Segment Fault Handler invoked with signal: %d\n"
	.text
.globl segment_fault_handler
	.type	segment_fault_handler, @function
segment_fault_handler:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$40, %esp
	movl	$.LC0, (%esp)
	call	puts
	movl	8(%ebp), %edx
	movl	$.LC1, %eax
	movl	%edx, 4(%esp)
	movl	%eax, (%esp)
	call	printf
	leal	8(%ebp), %eax
	movl	%eax, -12(%ebp)
#APP
# 9 "new.c" 1
	jmp %eax
# 0 "" 2
# 11 "new.c" 1
	ret
# 0 "" 2
#NO_APP
	leave
	ret
	.size	segment_fault_handler, .-segment_fault_handler
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
	movl	$0, %eax
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.4.7 20120313 (Red Hat 4.4.7-16)"
	.section	.note.GNU-stack,"",@progbits
