
a.out:     file format elf64-x86-64


Disassembly of section .interp:

0000000000400200 <.interp>:
  400200:	2f                   	(bad)  
  400201:	6c                   	insb   (%dx),%es:(%rdi)
  400202:	69 62 36 34 2f 6c 64 	imul   $0x646c2f34,0x36(%rdx),%esp
  400209:	2d 6c 69 6e 75       	sub    $0x756e696c,%eax
  40020e:	78 2d                	js     40023d <_init-0x153>
  400210:	78 38                	js     40024a <_init-0x146>
  400212:	36                   	ss
  400213:	2d 36 34 2e 73       	sub    $0x732e3436,%eax
  400218:	6f                   	outsl  %ds:(%rsi),(%dx)
  400219:	2e 32 00             	xor    %cs:(%rax),%al

Disassembly of section .note.ABI-tag:

000000000040021c <.note.ABI-tag>:
  40021c:	04 00                	add    $0x0,%al
  40021e:	00 00                	add    %al,(%rax)
  400220:	10 00                	adc    %al,(%rax)
  400222:	00 00                	add    %al,(%rax)
  400224:	01 00                	add    %eax,(%rax)
  400226:	00 00                	add    %al,(%rax)
  400228:	47                   	rex.RXB
  400229:	4e 55                	rex.WRX push %rbp
  40022b:	00 00                	add    %al,(%rax)
  40022d:	00 00                	add    %al,(%rax)
  40022f:	00 02                	add    %al,(%rdx)
  400231:	00 00                	add    %al,(%rax)
  400233:	00 06                	add    %al,(%rsi)
  400235:	00 00                	add    %al,(%rax)
  400237:	00 12                	add    %dl,(%rdx)
  400239:	00 00                	add    %al,(%rax)
	...

Disassembly of section .note.gnu.build-id:

000000000040023c <.note.gnu.build-id>:
  40023c:	04 00                	add    $0x0,%al
  40023e:	00 00                	add    %al,(%rax)
  400240:	14 00                	adc    $0x0,%al
  400242:	00 00                	add    %al,(%rax)
  400244:	03 00                	add    (%rax),%eax
  400246:	00 00                	add    %al,(%rax)
  400248:	47                   	rex.RXB
  400249:	4e 55                	rex.WRX push %rbp
  40024b:	00 8e ae 16 97 d0    	add    %cl,-0x2f68e952(%rsi)
  400251:	1f                   	(bad)  
  400252:	a7                   	cmpsl  %es:(%rdi),%ds:(%rsi)
  400253:	f8                   	clc    
  400254:	04 5b                	add    $0x5b,%al
  400256:	17                   	(bad)  
  400257:	26                   	es
  400258:	c9                   	leaveq 
  400259:	ea                   	(bad)  
  40025a:	3b aa 4e 9f 2e aa    	cmp    -0x55d160b2(%rdx),%ebp

Disassembly of section .gnu.hash:

0000000000400260 <.gnu.hash>:
  400260:	01 00                	add    %eax,(%rax)
  400262:	00 00                	add    %al,(%rax)
  400264:	01 00                	add    %eax,(%rax)
  400266:	00 00                	add    %al,(%rax)
  400268:	01 00                	add    %eax,(%rax)
	...

Disassembly of section .dynsym:

0000000000400280 <.dynsym>:
	...
  400298:	1a 00                	sbb    (%rax),%al
  40029a:	00 00                	add    %al,(%rax)
  40029c:	12 00                	adc    (%rax),%al
	...
  4002ae:	00 00                	add    %al,(%rax)
  4002b0:	01 00                	add    %eax,(%rax)
  4002b2:	00 00                	add    %al,(%rax)
  4002b4:	20 00                	and    %al,(%rax)
	...
  4002c6:	00 00                	add    %al,(%rax)
  4002c8:	21 00                	and    %eax,(%rax)
  4002ca:	00 00                	add    %al,(%rax)
  4002cc:	12 00                	adc    (%rax),%al
	...

Disassembly of section .dynstr:

00000000004002e0 <.dynstr>:
  4002e0:	00 5f 5f             	add    %bl,0x5f(%rdi)
  4002e3:	67 6d                	insl   (%dx),%es:(%edi)
  4002e5:	6f                   	outsl  %ds:(%rsi),(%dx)
  4002e6:	6e                   	outsb  %ds:(%rsi),(%dx)
  4002e7:	5f                   	pop    %rdi
  4002e8:	73 74                	jae    40035e <_init-0x32>
  4002ea:	61                   	(bad)  
  4002eb:	72 74                	jb     400361 <_init-0x2f>
  4002ed:	5f                   	pop    %rdi
  4002ee:	5f                   	pop    %rdi
  4002ef:	00 6c 69 62          	add    %ch,0x62(%rcx,%rbp,2)
  4002f3:	63 2e                	movslq (%rsi),%ebp
  4002f5:	73 6f                	jae    400366 <_init-0x2a>
  4002f7:	2e 36 00 70 72       	cs add %dh,%cs:%ss:0x72(%rax)
  4002fc:	69 6e 74 66 00 5f 5f 	imul   $0x5f5f0066,0x74(%rsi),%ebp
  400303:	6c                   	insb   (%dx),%es:(%rdi)
  400304:	69 62 63 5f 73 74 61 	imul   $0x6174735f,0x63(%rdx),%esp
  40030b:	72 74                	jb     400381 <_init-0xf>
  40030d:	5f                   	pop    %rdi
  40030e:	6d                   	insl   (%dx),%es:(%rdi)
  40030f:	61                   	(bad)  
  400310:	69 6e 00 47 4c 49 42 	imul   $0x42494c47,0x0(%rsi),%ebp
  400317:	43 5f                	rex.XB pop %r15
  400319:	32 2e                	xor    (%rsi),%ch
  40031b:	32 2e                	xor    (%rsi),%ch
  40031d:	35                   	.byte 0x35
	...

Disassembly of section .gnu.version:

0000000000400320 <.gnu.version>:
  400320:	00 00                	add    %al,(%rax)
  400322:	02 00                	add    (%rax),%al
  400324:	00 00                	add    %al,(%rax)
  400326:	02 00                	add    (%rax),%al

Disassembly of section .gnu.version_r:

0000000000400328 <.gnu.version_r>:
  400328:	01 00                	add    %eax,(%rax)
  40032a:	01 00                	add    %eax,(%rax)
  40032c:	10 00                	adc    %al,(%rax)
  40032e:	00 00                	add    %al,(%rax)
  400330:	10 00                	adc    %al,(%rax)
  400332:	00 00                	add    %al,(%rax)
  400334:	00 00                	add    %al,(%rax)
  400336:	00 00                	add    %al,(%rax)
  400338:	75 1a                	jne    400354 <_init-0x3c>
  40033a:	69 09 00 00 02 00    	imul   $0x20000,(%rcx),%ecx
  400340:	33 00                	xor    (%rax),%eax
  400342:	00 00                	add    %al,(%rax)
  400344:	00 00                	add    %al,(%rax)
	...

Disassembly of section .rela.dyn:

0000000000400348 <.rela.dyn>:
  400348:	98                   	cwtl   
  400349:	08 60 00             	or     %ah,0x0(%rax)
  40034c:	00 00                	add    %al,(%rax)
  40034e:	00 00                	add    %al,(%rax)
  400350:	06                   	(bad)  
  400351:	00 00                	add    %al,(%rax)
  400353:	00 02                	add    %al,(%rdx)
	...

Disassembly of section .rela.plt:

0000000000400360 <.rela.plt>:
  400360:	b8 08 60 00 00       	mov    $0x6008,%eax
  400365:	00 00                	add    %al,(%rax)
  400367:	00 07                	add    %al,(%rdi)
  400369:	00 00                	add    %al,(%rax)
  40036b:	00 01                	add    %al,(%rcx)
	...
  400375:	00 00                	add    %al,(%rax)
  400377:	00 c0                	add    %al,%al
  400379:	08 60 00             	or     %ah,0x0(%rax)
  40037c:	00 00                	add    %al,(%rax)
  40037e:	00 00                	add    %al,(%rax)
  400380:	07                   	(bad)  
  400381:	00 00                	add    %al,(%rax)
  400383:	00 03                	add    %al,(%rbx)
	...

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
  4003a8:	ff 35 fa 04 20 00    	pushq  0x2004fa(%rip)        # 6008a8 <_GLOBAL_OFFSET_TABLE_+0x8>
  4003ae:	ff 25 fc 04 20 00    	jmpq   *0x2004fc(%rip)        # 6008b0 <_GLOBAL_OFFSET_TABLE_+0x10>
  4003b4:	0f 1f 40 00          	nopl   0x0(%rax)

00000000004003b8 <printf@plt>:
  4003b8:	ff 25 fa 04 20 00    	jmpq   *0x2004fa(%rip)        # 6008b8 <_GLOBAL_OFFSET_TABLE_+0x18>
  4003be:	68 00 00 00 00       	pushq  $0x0
  4003c3:	e9 e0 ff ff ff       	jmpq   4003a8 <_init+0x18>

00000000004003c8 <__libc_start_main@plt>:
  4003c8:	ff 25 f2 04 20 00    	jmpq   *0x2004f2(%rip)        # 6008c0 <_GLOBAL_OFFSET_TABLE_+0x20>
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
  4003fd:	48 c7 c7 e1 04 40 00 	mov    $0x4004e1,%rdi
  400404:	e8 bf ff ff ff       	callq  4003c8 <__libc_start_main@plt>
  400409:	f4                   	hlt    
  40040a:	90                   	nop
  40040b:	90                   	nop

000000000040040c <call_gmon_start>:
  40040c:	48 83 ec 08          	sub    $0x8,%rsp
  400410:	48 8b 05 81 04 20 00 	mov    0x200481(%rip),%rax        # 600898 <_DYNAMIC+0x190>
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
  400439:	80 3d 90 04 20 00 00 	cmpb   $0x0,0x200490(%rip)        # 6008d0 <completed.6349>
  400440:	75 4b                	jne    40048d <__do_global_dtors_aux+0x5d>
  400442:	bb f8 06 60 00       	mov    $0x6006f8,%ebx
  400447:	48 8b 05 8a 04 20 00 	mov    0x20048a(%rip),%rax        # 6008d8 <dtor_idx.6351>
  40044e:	48 81 eb f0 06 60 00 	sub    $0x6006f0,%rbx
  400455:	48 c1 fb 03          	sar    $0x3,%rbx
  400459:	48 83 eb 01          	sub    $0x1,%rbx
  40045d:	48 39 d8             	cmp    %rbx,%rax
  400460:	73 24                	jae    400486 <__do_global_dtors_aux+0x56>
  400462:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)
  400468:	48 83 c0 01          	add    $0x1,%rax
  40046c:	48 89 05 65 04 20 00 	mov    %rax,0x200465(%rip)        # 6008d8 <dtor_idx.6351>
  400473:	ff 14 c5 f0 06 60 00 	callq  *0x6006f0(,%rax,8)
  40047a:	48 8b 05 57 04 20 00 	mov    0x200457(%rip),%rax        # 6008d8 <dtor_idx.6351>
  400481:	48 39 d8             	cmp    %rbx,%rax
  400484:	72 e2                	jb     400468 <__do_global_dtors_aux+0x38>
  400486:	c6 05 43 04 20 00 01 	movb   $0x1,0x200443(%rip)        # 6008d0 <completed.6349>
  40048d:	48 83 c4 08          	add    $0x8,%rsp
  400491:	5b                   	pop    %rbx
  400492:	c9                   	leaveq 
  400493:	c3                   	retq   
  400494:	66 66 66 2e 0f 1f 84 	data32 data32 nopw %cs:0x0(%rax,%rax,1)
  40049b:	00 00 00 00 00 

00000000004004a0 <frame_dummy>:
  4004a0:	48 83 3d 58 02 20 00 	cmpq   $0x0,0x200258(%rip)        # 600700 <__JCR_END__>
  4004a7:	00 
  4004a8:	55                   	push   %rbp
  4004a9:	48 89 e5             	mov    %rsp,%rbp
  4004ac:	74 12                	je     4004c0 <frame_dummy+0x20>
  4004ae:	b8 00 00 00 00       	mov    $0x0,%eax
  4004b3:	48 85 c0             	test   %rax,%rax
  4004b6:	74 08                	je     4004c0 <frame_dummy+0x20>
  4004b8:	bf 00 07 60 00       	mov    $0x600700,%edi
  4004bd:	c9                   	leaveq 
  4004be:	ff e0                	jmpq   *%rax
  4004c0:	c9                   	leaveq 
  4004c1:	c3                   	retq   
  4004c2:	90                   	nop
  4004c3:	90                   	nop

00000000004004c4 <test>:
  4004c4:	55                   	push   %rbp
  4004c5:	48 89 e5             	mov    %rsp,%rbp
  4004c8:	b8 08 06 40 00       	mov    $0x400608,%eax
  4004cd:	48 89 c7             	mov    %rax,%rdi
  4004d0:	b8 00 00 00 00       	mov    $0x0,%eax
  4004d5:	e8 de fe ff ff       	callq  4003b8 <printf@plt>
  4004da:	b8 01 00 00 00       	mov    $0x1,%eax
  4004df:	c9                   	leaveq 
  4004e0:	c3                   	retq   

00000000004004e1 <main>:
  4004e1:	55                   	push   %rbp
  4004e2:	48 89 e5             	mov    %rsp,%rbp
  4004e5:	48 83 ec 10          	sub    $0x10,%rsp
  4004e9:	89 7d fc             	mov    %edi,-0x4(%rbp)
  4004ec:	48 89 75 f0          	mov    %rsi,-0x10(%rbp)
  4004f0:	b8 00 00 00 00       	mov    $0x0,%eax
  4004f5:	e8 ca ff ff ff       	callq  4004c4 <test>
  4004fa:	b8 00 00 00 00       	mov    $0x0,%eax
  4004ff:	c9                   	leaveq 
  400500:	c3                   	retq   
  400501:	90                   	nop
  400502:	90                   	nop
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
  40052a:	48 8d 2d ab 01 20 00 	lea    0x2001ab(%rip),%rbp        # 6006dc <__init_array_end>
  400531:	4c 8d 25 a4 01 20 00 	lea    0x2001a4(%rip),%r12        # 6006dc <__init_array_end>
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
  4005b9:	48 8b 05 20 01 20 00 	mov    0x200120(%rip),%rax        # 6006e0 <__CTOR_LIST__>
  4005c0:	48 83 f8 ff          	cmp    $0xffffffffffffffff,%rax
  4005c4:	74 19                	je     4005df <__do_global_ctors_aux+0x2f>
  4005c6:	bb e0 06 60 00       	mov    $0x6006e0,%ebx
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

Disassembly of section .rodata:

00000000004005f8 <_IO_stdin_used>:
  4005f8:	01 00                	add    %eax,(%rax)
  4005fa:	02 00                	add    (%rax),%al
  4005fc:	00 00                	add    %al,(%rax)
	...

0000000000400600 <__dso_handle>:
	...
  400608:	74 65                	je     40066f <__dso_handle+0x6f>
  40060a:	73 74                	jae    400680 <__dso_handle+0x80>
	...

Disassembly of section .eh_frame_hdr:

0000000000400610 <.eh_frame_hdr>:
  400610:	01 1b                	add    %ebx,(%rbx)
  400612:	03 3b                	add    (%rbx),%edi
  400614:	2c 00                	sub    $0x0,%al
  400616:	00 00                	add    %al,(%rax)
  400618:	04 00                	add    $0x0,%al
  40061a:	00 00                	add    %al,(%rax)
  40061c:	b4 fe                	mov    $0xfe,%ah
  40061e:	ff                   	(bad)  
  40061f:	ff 48 00             	decl   0x0(%rax)
  400622:	00 00                	add    %al,(%rax)
  400624:	d1 fe                	sar    %esi
  400626:	ff                   	(bad)  
  400627:	ff 68 00             	ljmpq  *0x0(%rax)
  40062a:	00 00                	add    %al,(%rax)
  40062c:	00 ff                	add    %bh,%bh
  40062e:	ff                   	(bad)  
  40062f:	ff 88 00 00 00 10    	decl   0x10000000(%rax)
  400635:	ff                   	(bad)  
  400636:	ff                   	(bad)  
  400637:	ff                   	.byte 0xff
  400638:	a0                   	.byte 0xa0
  400639:	00 00                	add    %al,(%rax)
	...

Disassembly of section .eh_frame:

0000000000400640 <__FRAME_END__-0x98>:
  400640:	14 00                	adc    $0x0,%al
  400642:	00 00                	add    %al,(%rax)
  400644:	00 00                	add    %al,(%rax)
  400646:	00 00                	add    %al,(%rax)
  400648:	01 7a 52             	add    %edi,0x52(%rdx)
  40064b:	00 01                	add    %al,(%rcx)
  40064d:	78 10                	js     40065f <__dso_handle+0x5f>
  40064f:	01 1b                	add    %ebx,(%rbx)
  400651:	0c 07                	or     $0x7,%al
  400653:	08 90 01 00 00 1c    	or     %dl,0x1c000001(%rax)
  400659:	00 00                	add    %al,(%rax)
  40065b:	00 1c 00             	add    %bl,(%rax,%rax,1)
  40065e:	00 00                	add    %al,(%rax)
  400660:	64                   	fs
  400661:	fe                   	(bad)  
  400662:	ff                   	(bad)  
  400663:	ff 1d 00 00 00 00    	lcallq *0x0(%rip)        # 400669 <__dso_handle+0x69>
  400669:	41 0e                	rex.B (bad) 
  40066b:	10 86 02 43 0d 06    	adc    %al,0x60d4302(%rsi)
  400671:	58                   	pop    %rax
  400672:	0c 07                	or     $0x7,%al
  400674:	08 00                	or     %al,(%rax)
  400676:	00 00                	add    %al,(%rax)
  400678:	1c 00                	sbb    $0x0,%al
  40067a:	00 00                	add    %al,(%rax)
  40067c:	3c 00                	cmp    $0x0,%al
  40067e:	00 00                	add    %al,(%rax)
  400680:	61                   	(bad)  
  400681:	fe                   	(bad)  
  400682:	ff                   	(bad)  
  400683:	ff 20                	jmpq   *(%rax)
  400685:	00 00                	add    %al,(%rax)
  400687:	00 00                	add    %al,(%rax)
  400689:	41 0e                	rex.B (bad) 
  40068b:	10 86 02 43 0d 06    	adc    %al,0x60d4302(%rsi)
  400691:	5b                   	pop    %rbx
  400692:	0c 07                	or     $0x7,%al
  400694:	08 00                	or     %al,(%rax)
  400696:	00 00                	add    %al,(%rax)
  400698:	14 00                	adc    $0x0,%al
  40069a:	00 00                	add    %al,(%rax)
  40069c:	5c                   	pop    %rsp
  40069d:	00 00                	add    %al,(%rax)
  40069f:	00 70 fe             	add    %dh,-0x2(%rax)
  4006a2:	ff                   	(bad)  
  4006a3:	ff 02                	incl   (%rdx)
	...
  4006ad:	00 00                	add    %al,(%rax)
  4006af:	00 24 00             	add    %ah,(%rax,%rax,1)
  4006b2:	00 00                	add    %al,(%rax)
  4006b4:	74 00                	je     4006b6 <__dso_handle+0xb6>
  4006b6:	00 00                	add    %al,(%rax)
  4006b8:	68 fe ff ff 89       	pushq  $0xffffffff89fffffe
  4006bd:	00 00                	add    %al,(%rax)
  4006bf:	00 00                	add    %al,(%rax)
  4006c1:	51                   	push   %rcx
  4006c2:	8c 05 86 06 5f 0e    	mov    %es,0xe5f0686(%rip)        # e9f0d4e <_end+0xe3f046e>
  4006c8:	40 83 07 8f          	rex addl $0xffffffffffffff8f,(%rdi)
  4006cc:	02 8e 03 8d 04 02    	add    0x2048d03(%rsi),%cl
  4006d2:	58                   	pop    %rax
  4006d3:	0e                   	(bad)  
  4006d4:	08 00                	or     %al,(%rax)
	...

00000000004006d8 <__FRAME_END__>:
  4006d8:	00 00                	add    %al,(%rax)
	...

Disassembly of section .ctors:

00000000006006e0 <__CTOR_LIST__>:
  6006e0:	ff                   	(bad)  
  6006e1:	ff                   	(bad)  
  6006e2:	ff                   	(bad)  
  6006e3:	ff                   	(bad)  
  6006e4:	ff                   	(bad)  
  6006e5:	ff                   	(bad)  
  6006e6:	ff                   	(bad)  
  6006e7:	ff 00                	incl   (%rax)

00000000006006e8 <__CTOR_END__>:
	...

Disassembly of section .dtors:

00000000006006f0 <__DTOR_LIST__>:
  6006f0:	ff                   	(bad)  
  6006f1:	ff                   	(bad)  
  6006f2:	ff                   	(bad)  
  6006f3:	ff                   	(bad)  
  6006f4:	ff                   	(bad)  
  6006f5:	ff                   	(bad)  
  6006f6:	ff                   	(bad)  
  6006f7:	ff 00                	incl   (%rax)

00000000006006f8 <__DTOR_END__>:
	...

Disassembly of section .jcr:

0000000000600700 <__JCR_END__>:
	...

Disassembly of section .dynamic:

0000000000600708 <_DYNAMIC>:
  600708:	01 00                	add    %eax,(%rax)
  60070a:	00 00                	add    %al,(%rax)
  60070c:	00 00                	add    %al,(%rax)
  60070e:	00 00                	add    %al,(%rax)
  600710:	10 00                	adc    %al,(%rax)
  600712:	00 00                	add    %al,(%rax)
  600714:	00 00                	add    %al,(%rax)
  600716:	00 00                	add    %al,(%rax)
  600718:	0c 00                	or     $0x0,%al
  60071a:	00 00                	add    %al,(%rax)
  60071c:	00 00                	add    %al,(%rax)
  60071e:	00 00                	add    %al,(%rax)
  600720:	90                   	nop
  600721:	03 40 00             	add    0x0(%rax),%eax
  600724:	00 00                	add    %al,(%rax)
  600726:	00 00                	add    %al,(%rax)
  600728:	0d 00 00 00 00       	or     $0x0,%eax
  60072d:	00 00                	add    %al,(%rax)
  60072f:	00 e8                	add    %ch,%al
  600731:	05 40 00 00 00       	add    $0x40,%eax
  600736:	00 00                	add    %al,(%rax)
  600738:	f5                   	cmc    
  600739:	fe                   	(bad)  
  60073a:	ff 6f 00             	ljmpq  *0x0(%rdi)
  60073d:	00 00                	add    %al,(%rax)
  60073f:	00 60 02             	add    %ah,0x2(%rax)
  600742:	40 00 00             	add    %al,(%rax)
  600745:	00 00                	add    %al,(%rax)
  600747:	00 05 00 00 00 00    	add    %al,0x0(%rip)        # 60074d <_DYNAMIC+0x45>
  60074d:	00 00                	add    %al,(%rax)
  60074f:	00 e0                	add    %ah,%al
  600751:	02 40 00             	add    0x0(%rax),%al
  600754:	00 00                	add    %al,(%rax)
  600756:	00 00                	add    %al,(%rax)
  600758:	06                   	(bad)  
  600759:	00 00                	add    %al,(%rax)
  60075b:	00 00                	add    %al,(%rax)
  60075d:	00 00                	add    %al,(%rax)
  60075f:	00 80 02 40 00 00    	add    %al,0x4002(%rax)
  600765:	00 00                	add    %al,(%rax)
  600767:	00 0a                	add    %cl,(%rdx)
  600769:	00 00                	add    %al,(%rax)
  60076b:	00 00                	add    %al,(%rax)
  60076d:	00 00                	add    %al,(%rax)
  60076f:	00 3f                	add    %bh,(%rdi)
  600771:	00 00                	add    %al,(%rax)
  600773:	00 00                	add    %al,(%rax)
  600775:	00 00                	add    %al,(%rax)
  600777:	00 0b                	add    %cl,(%rbx)
  600779:	00 00                	add    %al,(%rax)
  60077b:	00 00                	add    %al,(%rax)
  60077d:	00 00                	add    %al,(%rax)
  60077f:	00 18                	add    %bl,(%rax)
  600781:	00 00                	add    %al,(%rax)
  600783:	00 00                	add    %al,(%rax)
  600785:	00 00                	add    %al,(%rax)
  600787:	00 15 00 00 00 00    	add    %dl,0x0(%rip)        # 60078d <_DYNAMIC+0x85>
	...
  600795:	00 00                	add    %al,(%rax)
  600797:	00 03                	add    %al,(%rbx)
  600799:	00 00                	add    %al,(%rax)
  60079b:	00 00                	add    %al,(%rax)
  60079d:	00 00                	add    %al,(%rax)
  60079f:	00 a0 08 60 00 00    	add    %ah,0x6008(%rax)
  6007a5:	00 00                	add    %al,(%rax)
  6007a7:	00 02                	add    %al,(%rdx)
  6007a9:	00 00                	add    %al,(%rax)
  6007ab:	00 00                	add    %al,(%rax)
  6007ad:	00 00                	add    %al,(%rax)
  6007af:	00 30                	add    %dh,(%rax)
  6007b1:	00 00                	add    %al,(%rax)
  6007b3:	00 00                	add    %al,(%rax)
  6007b5:	00 00                	add    %al,(%rax)
  6007b7:	00 14 00             	add    %dl,(%rax,%rax,1)
  6007ba:	00 00                	add    %al,(%rax)
  6007bc:	00 00                	add    %al,(%rax)
  6007be:	00 00                	add    %al,(%rax)
  6007c0:	07                   	(bad)  
  6007c1:	00 00                	add    %al,(%rax)
  6007c3:	00 00                	add    %al,(%rax)
  6007c5:	00 00                	add    %al,(%rax)
  6007c7:	00 17                	add    %dl,(%rdi)
  6007c9:	00 00                	add    %al,(%rax)
  6007cb:	00 00                	add    %al,(%rax)
  6007cd:	00 00                	add    %al,(%rax)
  6007cf:	00 60 03             	add    %ah,0x3(%rax)
  6007d2:	40 00 00             	add    %al,(%rax)
  6007d5:	00 00                	add    %al,(%rax)
  6007d7:	00 07                	add    %al,(%rdi)
  6007d9:	00 00                	add    %al,(%rax)
  6007db:	00 00                	add    %al,(%rax)
  6007dd:	00 00                	add    %al,(%rax)
  6007df:	00 48 03             	add    %cl,0x3(%rax)
  6007e2:	40 00 00             	add    %al,(%rax)
  6007e5:	00 00                	add    %al,(%rax)
  6007e7:	00 08                	add    %cl,(%rax)
  6007e9:	00 00                	add    %al,(%rax)
  6007eb:	00 00                	add    %al,(%rax)
  6007ed:	00 00                	add    %al,(%rax)
  6007ef:	00 18                	add    %bl,(%rax)
  6007f1:	00 00                	add    %al,(%rax)
  6007f3:	00 00                	add    %al,(%rax)
  6007f5:	00 00                	add    %al,(%rax)
  6007f7:	00 09                	add    %cl,(%rcx)
  6007f9:	00 00                	add    %al,(%rax)
  6007fb:	00 00                	add    %al,(%rax)
  6007fd:	00 00                	add    %al,(%rax)
  6007ff:	00 18                	add    %bl,(%rax)
  600801:	00 00                	add    %al,(%rax)
  600803:	00 00                	add    %al,(%rax)
  600805:	00 00                	add    %al,(%rax)
  600807:	00 fe                	add    %bh,%dh
  600809:	ff                   	(bad)  
  60080a:	ff 6f 00             	ljmpq  *0x0(%rdi)
  60080d:	00 00                	add    %al,(%rax)
  60080f:	00 28                	add    %ch,(%rax)
  600811:	03 40 00             	add    0x0(%rax),%eax
  600814:	00 00                	add    %al,(%rax)
  600816:	00 00                	add    %al,(%rax)
  600818:	ff                   	(bad)  
  600819:	ff                   	(bad)  
  60081a:	ff 6f 00             	ljmpq  *0x0(%rdi)
  60081d:	00 00                	add    %al,(%rax)
  60081f:	00 01                	add    %al,(%rcx)
  600821:	00 00                	add    %al,(%rax)
  600823:	00 00                	add    %al,(%rax)
  600825:	00 00                	add    %al,(%rax)
  600827:	00 f0                	add    %dh,%al
  600829:	ff                   	(bad)  
  60082a:	ff 6f 00             	ljmpq  *0x0(%rdi)
  60082d:	00 00                	add    %al,(%rax)
  60082f:	00 20                	add    %ah,(%rax)
  600831:	03 40 00             	add    0x0(%rax),%eax
	...

Disassembly of section .got:

0000000000600898 <.got>:
	...

Disassembly of section .got.plt:

00000000006008a0 <_GLOBAL_OFFSET_TABLE_>:
  6008a0:	08 07                	or     %al,(%rdi)
  6008a2:	60                   	(bad)  
	...
  6008b7:	00 be 03 40 00 00    	add    %bh,0x4003(%rsi)
  6008bd:	00 00                	add    %al,(%rax)
  6008bf:	00 ce                	add    %cl,%dh
  6008c1:	03 40 00             	add    0x0(%rax),%eax
  6008c4:	00 00                	add    %al,(%rax)
	...

Disassembly of section .data:

00000000006008c8 <__data_start>:
  6008c8:	00 00                	add    %al,(%rax)
	...

Disassembly of section .bss:

00000000006008d0 <completed.6349>:
	...

00000000006008d8 <dtor_idx.6351>:
	...

Disassembly of section .comment:

0000000000000000 <.comment>:
   0:	47                   	rex.RXB
   1:	43                   	rex.XB
   2:	43 3a 20             	rex.XB cmp (%r8),%spl
   5:	28 47 4e             	sub    %al,0x4e(%rdi)
   8:	55                   	push   %rbp
   9:	29 20                	sub    %esp,(%rax)
   b:	34 2e                	xor    $0x2e,%al
   d:	34 2e                	xor    $0x2e,%al
   f:	37                   	(bad)  
  10:	20 32                	and    %dh,(%rdx)
  12:	30 31                	xor    %dh,(%rcx)
  14:	32 30                	xor    (%rax),%dh
  16:	33 31                	xor    (%rcx),%esi
  18:	33 20                	xor    (%rax),%esp
  1a:	28 52 65             	sub    %dl,0x65(%rdx)
  1d:	64 20 48 61          	and    %cl,%fs:0x61(%rax)
  21:	74 20                	je     43 <_init-0x40034d>
  23:	34 2e                	xor    $0x2e,%al
  25:	34 2e                	xor    $0x2e,%al
  27:	37                   	(bad)  
  28:	2d 31 36 29 00       	sub    $0x293631,%eax
