
a.out:     file format elf64-x86-64


Disassembly of section .init:

0000000000400390 <_init>:
  400390:	48 83 ec 08          	sub    $0x8,%rsp
  400394:	e8 73 00 00 00       	callq  40040c <call_gmon_start>
  400399:	e8 02 01 00 00       	callq  4004a0 <frame_dummy>
  40039e:	e8 5d 02 00 00       	callq  400600 <__do_global_ctors_aux>
  4003a3:	48 83 c4 08          	add    $0x8,%rsp
  4003a7:	c3                   	retq   

Disassembly of section .plt:

00000000004003a8 <printf@plt-0x10>:
  4003a8:	ff 35 82 05 20 00    	pushq  0x200582(%rip)        # 600930 <_GLOBAL_OFFSET_TABLE_+0x8>
  4003ae:	ff 25 84 05 20 00    	jmpq   *0x200584(%rip)        # 600938 <_GLOBAL_OFFSET_TABLE_+0x10>
  4003b4:	0f 1f 40 00          	nopl   0x0(%rax)

00000000004003b8 <printf@plt>:
  4003b8:	ff 25 82 05 20 00    	jmpq   *0x200582(%rip)        # 600940 <_GLOBAL_OFFSET_TABLE_+0x18>
  4003be:	68 00 00 00 00       	pushq  $0x0
  4003c3:	e9 e0 ff ff ff       	jmpq   4003a8 <_init+0x18>

00000000004003c8 <__libc_start_main@plt>:
  4003c8:	ff 25 7a 05 20 00    	jmpq   *0x20057a(%rip)        # 600948 <_GLOBAL_OFFSET_TABLE_+0x20>
  4003ce:	68 01 00 00 00       	pushq  $0x1
  4003d3:	e9 d0 ff ff ff       	jmpq   4003a8 <_init+0x18>

Disassembly of section .text:

00000000004003e0 <_start>:
  4003e0:	31 ed                	xor    %ebp,%ebp
  4003e2:	49 89 d1             	mov    %rdx,%r9
  4003e5:	5e                   	pop    %rsi
  4003e6:	48 89 e2             	mov    %rsp,%rdx
  4003e9:	48 83 e4 f0          	and    $0xfffffffffffffff0,%rsp
  4003ed:	50                   	push   %rax
  4003ee:	54                   	push   %rsp
  4003ef:	49 c7 c0 60 05 40 00 	mov    $0x400560,%r8
  4003f6:	48 c7 c1 70 05 40 00 	mov    $0x400570,%rcx
  4003fd:	48 c7 c7 d9 04 40 00 	mov    $0x4004d9,%rdi
  400404:	e8 bf ff ff ff       	callq  4003c8 <__libc_start_main@plt>
  400409:	f4                   	hlt    
  40040a:	90                   	nop
  40040b:	90                   	nop

000000000040040c <call_gmon_start>:
  40040c:	48 83 ec 08          	sub    $0x8,%rsp
  400410:	48 8b 05 09 05 20 00 	mov    0x200509(%rip),%rax        # 600920 <_DYNAMIC+0x190>
  400417:	48 85 c0             	test   %rax,%rax
  40041a:	74 02                	je     40041e <call_gmon_start+0x12>
  40041c:	ff d0                	callq  *%rax
  40041e:	48 83 c4 08          	add    $0x8,%rsp
  400422:	c3                   	retq   
  400423:	90                   	nop
  400424:	90                   	nop
  400425:	90                   	nop
  400426:	90                   	nop
  400427:	90                   	nop
  400428:	90                   	nop
  400429:	90                   	nop
  40042a:	90                   	nop
  40042b:	90                   	nop
  40042c:	90                   	nop
  40042d:	90                   	nop
  40042e:	90                   	nop
  40042f:	90                   	nop

0000000000400430 <__do_global_dtors_aux>:
  400430:	55                   	push   %rbp
  400431:	48 89 e5             	mov    %rsp,%rbp
  400434:	53                   	push   %rbx
  400435:	48 83 ec 08          	sub    $0x8,%rsp
  400439:	80 3d 18 05 20 00 00 	cmpb   $0x0,0x200518(%rip)        # 600958 <__bss_start>
  400440:	75 4b                	jne    40048d <__do_global_dtors_aux+0x5d>
  400442:	bb 80 07 60 00       	mov    $0x600780,%ebx
  400447:	48 8b 05 12 05 20 00 	mov    0x200512(%rip),%rax        # 600960 <dtor_idx.6351>
  40044e:	48 81 eb 78 07 60 00 	sub    $0x600778,%rbx
  400455:	48 c1 fb 03          	sar    $0x3,%rbx
  400459:	48 83 eb 01          	sub    $0x1,%rbx
  40045d:	48 39 d8             	cmp    %rbx,%rax
  400460:	73 24                	jae    400486 <__do_global_dtors_aux+0x56>
  400462:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)
  400468:	48 83 c0 01          	add    $0x1,%rax
  40046c:	48 89 05 ed 04 20 00 	mov    %rax,0x2004ed(%rip)        # 600960 <dtor_idx.6351>
  400473:	ff 14 c5 78 07 60 00 	callq  *0x600778(,%rax,8)
  40047a:	48 8b 05 df 04 20 00 	mov    0x2004df(%rip),%rax        # 600960 <dtor_idx.6351>
  400481:	48 39 d8             	cmp    %rbx,%rax
  400484:	72 e2                	jb     400468 <__do_global_dtors_aux+0x38>
  400486:	c6 05 cb 04 20 00 01 	movb   $0x1,0x2004cb(%rip)        # 600958 <__bss_start>
  40048d:	48 83 c4 08          	add    $0x8,%rsp
  400491:	5b                   	pop    %rbx
  400492:	c9                   	leaveq 
  400493:	c3                   	retq   
  400494:	66 66 66 2e 0f 1f 84 	data32 data32 nopw %cs:0x0(%rax,%rax,1)
  40049b:	00 00 00 00 00 

00000000004004a0 <frame_dummy>:
  4004a0:	48 83 3d e0 02 20 00 	cmpq   $0x0,0x2002e0(%rip)        # 600788 <__JCR_END__>
  4004a7:	00 
  4004a8:	55                   	push   %rbp
  4004a9:	48 89 e5             	mov    %rsp,%rbp
  4004ac:	74 12                	je     4004c0 <frame_dummy+0x20>
  4004ae:	b8 00 00 00 00       	mov    $0x0,%eax
  4004b3:	48 85 c0             	test   %rax,%rax
  4004b6:	74 08                	je     4004c0 <frame_dummy+0x20>
  4004b8:	bf 88 07 60 00       	mov    $0x600788,%edi
  4004bd:	c9                   	leaveq 
  4004be:	ff e0                	jmpq   *%rax
  4004c0:	c9                   	leaveq 
  4004c1:	c3                   	retq   
  4004c2:	90                   	nop
  4004c3:	90                   	nop

00000000004004c4 <test>:
  4004c4:	55                   	push   %rbp
  4004c5:	48 89 e5             	mov    %rsp,%rbp
  4004c8:	c7 05 82 04 20 00 34 	movl   $0x4a276234,0x200482(%rip)        # 600954 <global_value>
  4004cf:	62 27 4a 
  4004d2:	b8 01 00 00 00       	mov    $0x1,%eax
  4004d7:	c9                   	leaveq 
  4004d8:	c3                   	retq   

00000000004004d9 <main>:
  4004d9:	55                   	push   %rbp
  4004da:	48 89 e5             	mov    %rsp,%rbp
  4004dd:	48 83 ec 10          	sub    $0x10,%rsp
  4004e1:	89 7d fc             	mov    %edi,-0x4(%rbp)
  4004e4:	48 89 75 f0          	mov    %rsi,-0x10(%rbp)
  4004e8:	8b 15 66 04 20 00    	mov    0x200466(%rip),%edx        # 600954 <global_value>
  4004ee:	b8 58 06 40 00       	mov    $0x400658,%eax
  4004f3:	89 d6                	mov    %edx,%esi
  4004f5:	48 89 c7             	mov    %rax,%rdi
  4004f8:	b8 00 00 00 00       	mov    $0x0,%eax
  4004fd:	e8 b6 fe ff ff       	callq  4003b8 <printf@plt>
  400502:	c7 05 48 04 20 00 ff 	movl   $0xff,0x200448(%rip)        # 600954 <global_value>
  400509:	00 00 00 
  40050c:	8b 15 42 04 20 00    	mov    0x200442(%rip),%edx        # 600954 <global_value>
  400512:	b8 6d 06 40 00       	mov    $0x40066d,%eax
  400517:	89 d6                	mov    %edx,%esi
  400519:	48 89 c7             	mov    %rax,%rdi
  40051c:	b8 00 00 00 00       	mov    $0x0,%eax
  400521:	e8 92 fe ff ff       	callq  4003b8 <printf@plt>
  400526:	b8 00 00 00 00       	mov    $0x0,%eax
  40052b:	e8 94 ff ff ff       	callq  4004c4 <test>
  400530:	8b 15 1e 04 20 00    	mov    0x20041e(%rip),%edx        # 600954 <global_value>
  400536:	b8 82 06 40 00       	mov    $0x400682,%eax
  40053b:	89 d6                	mov    %edx,%esi
  40053d:	48 89 c7             	mov    %rax,%rdi
  400540:	b8 00 00 00 00       	mov    $0x0,%eax
  400545:	e8 6e fe ff ff       	callq  4003b8 <printf@plt>
  40054a:	b8 00 00 00 00       	mov    $0x0,%eax
  40054f:	c9                   	leaveq 
  400550:	c3                   	retq   
  400551:	90                   	nop
  400552:	90                   	nop
  400553:	90                   	nop
  400554:	90                   	nop
  400555:	90                   	nop
  400556:	90                   	nop
  400557:	90                   	nop
  400558:	90                   	nop
  400559:	90                   	nop
  40055a:	90                   	nop
  40055b:	90                   	nop
  40055c:	90                   	nop
  40055d:	90                   	nop
  40055e:	90                   	nop
  40055f:	90                   	nop

0000000000400560 <__libc_csu_fini>:
  400560:	f3 c3                	repz retq 
  400562:	66 66 66 66 66 2e 0f 	data32 data32 data32 data32 nopw %cs:0x0(%rax,%rax,1)
  400569:	1f 84 00 00 00 00 00 

0000000000400570 <__libc_csu_init>:
  400570:	48 89 6c 24 d8       	mov    %rbp,-0x28(%rsp)
  400575:	4c 89 64 24 e0       	mov    %r12,-0x20(%rsp)
  40057a:	48 8d 2d e3 01 20 00 	lea    0x2001e3(%rip),%rbp        # 600764 <__init_array_end>
  400581:	4c 8d 25 dc 01 20 00 	lea    0x2001dc(%rip),%r12        # 600764 <__init_array_end>
  400588:	4c 89 6c 24 e8       	mov    %r13,-0x18(%rsp)
  40058d:	4c 89 74 24 f0       	mov    %r14,-0x10(%rsp)
  400592:	4c 89 7c 24 f8       	mov    %r15,-0x8(%rsp)
  400597:	48 89 5c 24 d0       	mov    %rbx,-0x30(%rsp)
  40059c:	48 83 ec 38          	sub    $0x38,%rsp
  4005a0:	4c 29 e5             	sub    %r12,%rbp
  4005a3:	41 89 fd             	mov    %edi,%r13d
  4005a6:	49 89 f6             	mov    %rsi,%r14
  4005a9:	48 c1 fd 03          	sar    $0x3,%rbp
  4005ad:	49 89 d7             	mov    %rdx,%r15
  4005b0:	e8 db fd ff ff       	callq  400390 <_init>
  4005b5:	48 85 ed             	test   %rbp,%rbp
  4005b8:	74 1c                	je     4005d6 <__libc_csu_init+0x66>
  4005ba:	31 db                	xor    %ebx,%ebx
  4005bc:	0f 1f 40 00          	nopl   0x0(%rax)
  4005c0:	4c 89 fa             	mov    %r15,%rdx
  4005c3:	4c 89 f6             	mov    %r14,%rsi
  4005c6:	44 89 ef             	mov    %r13d,%edi
  4005c9:	41 ff 14 dc          	callq  *(%r12,%rbx,8)
  4005cd:	48 83 c3 01          	add    $0x1,%rbx
  4005d1:	48 39 eb             	cmp    %rbp,%rbx
  4005d4:	72 ea                	jb     4005c0 <__libc_csu_init+0x50>
  4005d6:	48 8b 5c 24 08       	mov    0x8(%rsp),%rbx
  4005db:	48 8b 6c 24 10       	mov    0x10(%rsp),%rbp
  4005e0:	4c 8b 64 24 18       	mov    0x18(%rsp),%r12
  4005e5:	4c 8b 6c 24 20       	mov    0x20(%rsp),%r13
  4005ea:	4c 8b 74 24 28       	mov    0x28(%rsp),%r14
  4005ef:	4c 8b 7c 24 30       	mov    0x30(%rsp),%r15
  4005f4:	48 83 c4 38          	add    $0x38,%rsp
  4005f8:	c3                   	retq   
  4005f9:	90                   	nop
  4005fa:	90                   	nop
  4005fb:	90                   	nop
  4005fc:	90                   	nop
  4005fd:	90                   	nop
  4005fe:	90                   	nop
  4005ff:	90                   	nop

0000000000400600 <__do_global_ctors_aux>:
  400600:	55                   	push   %rbp
  400601:	48 89 e5             	mov    %rsp,%rbp
  400604:	53                   	push   %rbx
  400605:	48 83 ec 08          	sub    $0x8,%rsp
  400609:	48 8b 05 58 01 20 00 	mov    0x200158(%rip),%rax        # 600768 <__CTOR_LIST__>
  400610:	48 83 f8 ff          	cmp    $0xffffffffffffffff,%rax
  400614:	74 19                	je     40062f <__do_global_ctors_aux+0x2f>
  400616:	bb 68 07 60 00       	mov    $0x600768,%ebx
  40061b:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)
  400620:	48 83 eb 08          	sub    $0x8,%rbx
  400624:	ff d0                	callq  *%rax
  400626:	48 8b 03             	mov    (%rbx),%rax
  400629:	48 83 f8 ff          	cmp    $0xffffffffffffffff,%rax
  40062d:	75 f1                	jne    400620 <__do_global_ctors_aux+0x20>
  40062f:	48 83 c4 08          	add    $0x8,%rsp
  400633:	5b                   	pop    %rbx
  400634:	c9                   	leaveq 
  400635:	c3                   	retq   
  400636:	90                   	nop
  400637:	90                   	nop

Disassembly of section .fini:

0000000000400638 <_fini>:
  400638:	48 83 ec 08          	sub    $0x8,%rsp
  40063c:	e8 ef fd ff ff       	callq  400430 <__do_global_dtors_aux>
  400641:	48 83 c4 08          	add    $0x8,%rsp
  400645:	c3                   	retq   
