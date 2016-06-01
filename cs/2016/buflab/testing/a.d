
a.out:     file format elf64-x86-64


Disassembly of section .init:

0000000000400390 <_init>:
  400390:	48 83 ec 08          	sub    $0x8,%rsp
  400394:	e8 73 00 00 00       	callq  40040c <call_gmon_start>
  400399:	e8 02 01 00 00       	callq  4004a0 <frame_dummy>
  40039e:	e8 0d 02 00 00       	callq  4005b0 <__do_global_ctors_aux>
  4003a3:	48 83 c4 08          	add    $0x8,%rsp
  4003a7:	c3                   	retq   

Disassembly of section .plt:

00000000004003a8 <printf@plt-0x10>:
  4003a8:	ff 35 ca 04 20 00    	pushq  0x2004ca(%rip)        # 600878 <_GLOBAL_OFFSET_TABLE_+0x8>
  4003ae:	ff 25 cc 04 20 00    	jmpq   *0x2004cc(%rip)        # 600880 <_GLOBAL_OFFSET_TABLE_+0x10>
  4003b4:	0f 1f 40 00          	nopl   0x0(%rax)

00000000004003b8 <printf@plt>:
  4003b8:	ff 25 ca 04 20 00    	jmpq   *0x2004ca(%rip)        # 600888 <_GLOBAL_OFFSET_TABLE_+0x18>
  4003be:	68 00 00 00 00       	pushq  $0x0
  4003c3:	e9 e0 ff ff ff       	jmpq   4003a8 <_init+0x18>

00000000004003c8 <__libc_start_main@plt>:
  4003c8:	ff 25 c2 04 20 00    	jmpq   *0x2004c2(%rip)        # 600890 <_GLOBAL_OFFSET_TABLE_+0x20>
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
  4003ef:	49 c7 c0 10 05 40 00 	mov    $0x400510,%r8
  4003f6:	48 c7 c1 20 05 40 00 	mov    $0x400520,%rcx
  4003fd:	48 c7 c7 c4 04 40 00 	mov    $0x4004c4,%rdi
  400404:	e8 bf ff ff ff       	callq  4003c8 <__libc_start_main@plt>
  400409:	f4                   	hlt    
  40040a:	90                   	nop
  40040b:	90                   	nop

000000000040040c <call_gmon_start>:
  40040c:	48 83 ec 08          	sub    $0x8,%rsp
  400410:	48 8b 05 51 04 20 00 	mov    0x200451(%rip),%rax        # 600868 <_DYNAMIC+0x190>
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
  400439:	80 3d 60 04 20 00 00 	cmpb   $0x0,0x200460(%rip)        # 6008a0 <completed.6349>
  400440:	75 4b                	jne    40048d <__do_global_dtors_aux+0x5d>
  400442:	bb c8 06 60 00       	mov    $0x6006c8,%ebx
  400447:	48 8b 05 5a 04 20 00 	mov    0x20045a(%rip),%rax        # 6008a8 <dtor_idx.6351>
  40044e:	48 81 eb c0 06 60 00 	sub    $0x6006c0,%rbx
  400455:	48 c1 fb 03          	sar    $0x3,%rbx
  400459:	48 83 eb 01          	sub    $0x1,%rbx
  40045d:	48 39 d8             	cmp    %rbx,%rax
  400460:	73 24                	jae    400486 <__do_global_dtors_aux+0x56>
  400462:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)
  400468:	48 83 c0 01          	add    $0x1,%rax
  40046c:	48 89 05 35 04 20 00 	mov    %rax,0x200435(%rip)        # 6008a8 <dtor_idx.6351>
  400473:	ff 14 c5 c0 06 60 00 	callq  *0x6006c0(,%rax,8)
  40047a:	48 8b 05 27 04 20 00 	mov    0x200427(%rip),%rax        # 6008a8 <dtor_idx.6351>
  400481:	48 39 d8             	cmp    %rbx,%rax
  400484:	72 e2                	jb     400468 <__do_global_dtors_aux+0x38>
  400486:	c6 05 13 04 20 00 01 	movb   $0x1,0x200413(%rip)        # 6008a0 <completed.6349>
  40048d:	48 83 c4 08          	add    $0x8,%rsp
  400491:	5b                   	pop    %rbx
  400492:	c9                   	leaveq 
  400493:	c3                   	retq   
  400494:	66 66 66 2e 0f 1f 84 	data32 data32 nopw %cs:0x0(%rax,%rax,1)
  40049b:	00 00 00 00 00 

00000000004004a0 <frame_dummy>:
  4004a0:	48 83 3d 28 02 20 00 	cmpq   $0x0,0x200228(%rip)        # 6006d0 <__JCR_END__>
  4004a7:	00 
  4004a8:	55                   	push   %rbp
  4004a9:	48 89 e5             	mov    %rsp,%rbp
  4004ac:	74 12                	je     4004c0 <frame_dummy+0x20>
  4004ae:	b8 00 00 00 00       	mov    $0x0,%eax
  4004b3:	48 85 c0             	test   %rax,%rax
  4004b6:	74 08                	je     4004c0 <frame_dummy+0x20>
  4004b8:	bf d0 06 60 00       	mov    $0x6006d0,%edi
  4004bd:	c9                   	leaveq 
  4004be:	ff e0                	jmpq   *%rax
  4004c0:	c9                   	leaveq 
  4004c1:	c3                   	retq   
  4004c2:	90                   	nop
  4004c3:	90                   	nop

00000000004004c4 <main>:
  4004c4:	55                   	push   %rbp
  4004c5:	48 89 e5             	mov    %rsp,%rbp
  4004c8:	48 83 ec 10          	sub    $0x10,%rsp
  4004cc:	89 7d fc             	mov    %edi,-0x4(%rbp)
  4004cf:	48 89 75 f0          	mov    %rsi,-0x10(%rbp)
  4004d3:	8b 05 d7 03 20 00    	mov    0x2003d7(%rip),%eax        # 6008b0 <global_variable>
  4004d9:	83 c0 01             	add    $0x1,%eax
  4004dc:	89 05 ce 03 20 00    	mov    %eax,0x2003ce(%rip)        # 6008b0 <global_variable>
  4004e2:	8b 15 c8 03 20 00    	mov    0x2003c8(%rip),%edx        # 6008b0 <global_variable>
  4004e8:	b8 08 06 40 00       	mov    $0x400608,%eax
  4004ed:	89 d6                	mov    %edx,%esi
  4004ef:	48 89 c7             	mov    %rax,%rdi
  4004f2:	b8 00 00 00 00       	mov    $0x0,%eax
  4004f7:	e8 bc fe ff ff       	callq  4003b8 <printf@plt>
  4004fc:	b8 00 00 00 00       	mov    $0x0,%eax
  400501:	c9                   	leaveq 
  400502:	c3                   	retq   
  400503:	90                   	nop
  400504:	90                   	nop
  400505:	90                   	nop
  400506:	90                   	nop
  400507:	90                   	nop
  400508:	90                   	nop
  400509:	90                   	nop
  40050a:	90                   	nop
  40050b:	90                   	nop
  40050c:	90                   	nop
  40050d:	90                   	nop
  40050e:	90                   	nop
  40050f:	90                   	nop

0000000000400510 <__libc_csu_fini>:
  400510:	f3 c3                	repz retq 
  400512:	66 66 66 66 66 2e 0f 	data32 data32 data32 data32 nopw %cs:0x0(%rax,%rax,1)
  400519:	1f 84 00 00 00 00 00 

0000000000400520 <__libc_csu_init>:
  400520:	48 89 6c 24 d8       	mov    %rbp,-0x28(%rsp)
  400525:	4c 89 64 24 e0       	mov    %r12,-0x20(%rsp)
  40052a:	48 8d 2d 7b 01 20 00 	lea    0x20017b(%rip),%rbp        # 6006ac <__init_array_end>
  400531:	4c 8d 25 74 01 20 00 	lea    0x200174(%rip),%r12        # 6006ac <__init_array_end>
  400538:	4c 89 6c 24 e8       	mov    %r13,-0x18(%rsp)
  40053d:	4c 89 74 24 f0       	mov    %r14,-0x10(%rsp)
  400542:	4c 89 7c 24 f8       	mov    %r15,-0x8(%rsp)
  400547:	48 89 5c 24 d0       	mov    %rbx,-0x30(%rsp)
  40054c:	48 83 ec 38          	sub    $0x38,%rsp
  400550:	4c 29 e5             	sub    %r12,%rbp
  400553:	41 89 fd             	mov    %edi,%r13d
  400556:	49 89 f6             	mov    %rsi,%r14
  400559:	48 c1 fd 03          	sar    $0x3,%rbp
  40055d:	49 89 d7             	mov    %rdx,%r15
  400560:	e8 2b fe ff ff       	callq  400390 <_init>
  400565:	48 85 ed             	test   %rbp,%rbp
  400568:	74 1c                	je     400586 <__libc_csu_init+0x66>
  40056a:	31 db                	xor    %ebx,%ebx
  40056c:	0f 1f 40 00          	nopl   0x0(%rax)
  400570:	4c 89 fa             	mov    %r15,%rdx
  400573:	4c 89 f6             	mov    %r14,%rsi
  400576:	44 89 ef             	mov    %r13d,%edi
  400579:	41 ff 14 dc          	callq  *(%r12,%rbx,8)
  40057d:	48 83 c3 01          	add    $0x1,%rbx
  400581:	48 39 eb             	cmp    %rbp,%rbx
  400584:	72 ea                	jb     400570 <__libc_csu_init+0x50>
  400586:	48 8b 5c 24 08       	mov    0x8(%rsp),%rbx
  40058b:	48 8b 6c 24 10       	mov    0x10(%rsp),%rbp
  400590:	4c 8b 64 24 18       	mov    0x18(%rsp),%r12
  400595:	4c 8b 6c 24 20       	mov    0x20(%rsp),%r13
  40059a:	4c 8b 74 24 28       	mov    0x28(%rsp),%r14
  40059f:	4c 8b 7c 24 30       	mov    0x30(%rsp),%r15
  4005a4:	48 83 c4 38          	add    $0x38,%rsp
  4005a8:	c3                   	retq   
  4005a9:	90                   	nop
  4005aa:	90                   	nop
  4005ab:	90                   	nop
  4005ac:	90                   	nop
  4005ad:	90                   	nop
  4005ae:	90                   	nop
  4005af:	90                   	nop

00000000004005b0 <__do_global_ctors_aux>:
  4005b0:	55                   	push   %rbp
  4005b1:	48 89 e5             	mov    %rsp,%rbp
  4005b4:	53                   	push   %rbx
  4005b5:	48 83 ec 08          	sub    $0x8,%rsp
  4005b9:	48 8b 05 f0 00 20 00 	mov    0x2000f0(%rip),%rax        # 6006b0 <__CTOR_LIST__>
  4005c0:	48 83 f8 ff          	cmp    $0xffffffffffffffff,%rax
  4005c4:	74 19                	je     4005df <__do_global_ctors_aux+0x2f>
  4005c6:	bb b0 06 60 00       	mov    $0x6006b0,%ebx
  4005cb:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)
  4005d0:	48 83 eb 08          	sub    $0x8,%rbx
  4005d4:	ff d0                	callq  *%rax
  4005d6:	48 8b 03             	mov    (%rbx),%rax
  4005d9:	48 83 f8 ff          	cmp    $0xffffffffffffffff,%rax
  4005dd:	75 f1                	jne    4005d0 <__do_global_ctors_aux+0x20>
  4005df:	48 83 c4 08          	add    $0x8,%rsp
  4005e3:	5b                   	pop    %rbx
  4005e4:	c9                   	leaveq 
  4005e5:	c3                   	retq   
  4005e6:	90                   	nop
  4005e7:	90                   	nop

Disassembly of section .fini:

00000000004005e8 <_fini>:
  4005e8:	48 83 ec 08          	sub    $0x8,%rsp
  4005ec:	e8 3f fe ff ff       	callq  400430 <__do_global_dtors_aux>
  4005f1:	48 83 c4 08          	add    $0x8,%rsp
  4005f5:	c3                   	retq   
