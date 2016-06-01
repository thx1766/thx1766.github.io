
./bufbomb:     file format elf32-i386


Disassembly of section .init:

0804861c <_init>:
 804861c:	55                   	push   %ebp
 804861d:	89 e5                	mov    %esp,%ebp
 804861f:	83 ec 08             	sub    $0x8,%esp
 8048622:	e8 0d 02 00 00       	call   8048834 <call_gmon_start>
 8048627:	e8 94 02 00 00       	call   80488c0 <frame_dummy>
 804862c:	e8 1f 0e 00 00       	call   8049450 <__do_global_ctors_aux>
 8048631:	c9                   	leave  
 8048632:	c3                   	ret    

Disassembly of section .plt:

08048634 <sprintf@plt-0x10>:
 8048634:	ff 35 e4 a0 04 08    	pushl  0x804a0e4
 804863a:	ff 25 e8 a0 04 08    	jmp    *0x804a0e8
 8048640:	00 00                	add    %al,(%eax)
	...

08048644 <sprintf@plt>:
 8048644:	ff 25 ec a0 04 08    	jmp    *0x804a0ec
 804864a:	68 00 00 00 00       	push   $0x0
 804864f:	e9 e0 ff ff ff       	jmp    8048634 <_init+0x18>

08048654 <srand@plt>:
 8048654:	ff 25 f0 a0 04 08    	jmp    *0x804a0f0
 804865a:	68 08 00 00 00       	push   $0x8
 804865f:	e9 d0 ff ff ff       	jmp    8048634 <_init+0x18>

08048664 <random@plt>:
 8048664:	ff 25 f4 a0 04 08    	jmp    *0x804a0f4
 804866a:	68 10 00 00 00       	push   $0x10
 804866f:	e9 c0 ff ff ff       	jmp    8048634 <_init+0x18>

08048674 <signal@plt>:
 8048674:	ff 25 f8 a0 04 08    	jmp    *0x804a0f8
 804867a:	68 18 00 00 00       	push   $0x18
 804867f:	e9 b0 ff ff ff       	jmp    8048634 <_init+0x18>

08048684 <__gmon_start__@plt>:
 8048684:	ff 25 fc a0 04 08    	jmp    *0x804a0fc
 804868a:	68 20 00 00 00       	push   $0x20
 804868f:	e9 a0 ff ff ff       	jmp    8048634 <_init+0x18>

08048694 <calloc@plt>:
 8048694:	ff 25 00 a1 04 08    	jmp    *0x804a100
 804869a:	68 28 00 00 00       	push   $0x28
 804869f:	e9 90 ff ff ff       	jmp    8048634 <_init+0x18>

080486a4 <system@plt>:
 80486a4:	ff 25 04 a1 04 08    	jmp    *0x804a104
 80486aa:	68 30 00 00 00       	push   $0x30
 80486af:	e9 80 ff ff ff       	jmp    8048634 <_init+0x18>

080486b4 <memset@plt>:
 80486b4:	ff 25 08 a1 04 08    	jmp    *0x804a108
 80486ba:	68 38 00 00 00       	push   $0x38
 80486bf:	e9 70 ff ff ff       	jmp    8048634 <_init+0x18>

080486c4 <__libc_start_main@plt>:
 80486c4:	ff 25 0c a1 04 08    	jmp    *0x804a10c
 80486ca:	68 40 00 00 00       	push   $0x40
 80486cf:	e9 60 ff ff ff       	jmp    8048634 <_init+0x18>

080486d4 <_IO_getc@plt>:
 80486d4:	ff 25 10 a1 04 08    	jmp    *0x804a110
 80486da:	68 48 00 00 00       	push   $0x48
 80486df:	e9 50 ff ff ff       	jmp    8048634 <_init+0x18>

080486e4 <__ctype_b_loc@plt>:
 80486e4:	ff 25 14 a1 04 08    	jmp    *0x804a114
 80486ea:	68 50 00 00 00       	push   $0x50
 80486ef:	e9 40 ff ff ff       	jmp    8048634 <_init+0x18>

080486f4 <fclose@plt>:
 80486f4:	ff 25 18 a1 04 08    	jmp    *0x804a118
 80486fa:	68 58 00 00 00       	push   $0x58
 80486ff:	e9 30 ff ff ff       	jmp    8048634 <_init+0x18>

08048704 <getopt@plt>:
 8048704:	ff 25 1c a1 04 08    	jmp    *0x804a11c
 804870a:	68 60 00 00 00       	push   $0x60
 804870f:	e9 20 ff ff ff       	jmp    8048634 <_init+0x18>

08048714 <fopen@plt>:
 8048714:	ff 25 20 a1 04 08    	jmp    *0x804a120
 804871a:	68 68 00 00 00       	push   $0x68
 804871f:	e9 10 ff ff ff       	jmp    8048634 <_init+0x18>

08048724 <alarm@plt>:
 8048724:	ff 25 24 a1 04 08    	jmp    *0x804a124
 804872a:	68 70 00 00 00       	push   $0x70
 804872f:	e9 00 ff ff ff       	jmp    8048634 <_init+0x18>

08048734 <strcpy@plt>:
 8048734:	ff 25 28 a1 04 08    	jmp    *0x804a128
 804873a:	68 78 00 00 00       	push   $0x78
 804873f:	e9 f0 fe ff ff       	jmp    8048634 <_init+0x18>

08048744 <printf@plt>:
 8048744:	ff 25 2c a1 04 08    	jmp    *0x804a12c
 804874a:	68 80 00 00 00       	push   $0x80
 804874f:	e9 e0 fe ff ff       	jmp    8048634 <_init+0x18>

08048754 <srandom@plt>:
 8048754:	ff 25 30 a1 04 08    	jmp    *0x804a130
 804875a:	68 88 00 00 00       	push   $0x88
 804875f:	e9 d0 fe ff ff       	jmp    8048634 <_init+0x18>

08048764 <fwrite@plt>:
 8048764:	ff 25 34 a1 04 08    	jmp    *0x804a134
 804876a:	68 90 00 00 00       	push   $0x90
 804876f:	e9 c0 fe ff ff       	jmp    8048634 <_init+0x18>

08048774 <fprintf@plt>:
 8048774:	ff 25 38 a1 04 08    	jmp    *0x804a138
 804877a:	68 98 00 00 00       	push   $0x98
 804877f:	e9 b0 fe ff ff       	jmp    8048634 <_init+0x18>

08048784 <remove@plt>:
 8048784:	ff 25 3c a1 04 08    	jmp    *0x804a13c
 804878a:	68 a0 00 00 00       	push   $0xa0
 804878f:	e9 a0 fe ff ff       	jmp    8048634 <_init+0x18>

08048794 <cuserid@plt>:
 8048794:	ff 25 40 a1 04 08    	jmp    *0x804a140
 804879a:	68 a8 00 00 00       	push   $0xa8
 804879f:	e9 90 fe ff ff       	jmp    8048634 <_init+0x18>

080487a4 <fputc@plt>:
 80487a4:	ff 25 44 a1 04 08    	jmp    *0x804a144
 80487aa:	68 b0 00 00 00       	push   $0xb0
 80487af:	e9 80 fe ff ff       	jmp    8048634 <_init+0x18>

080487b4 <puts@plt>:
 80487b4:	ff 25 48 a1 04 08    	jmp    *0x804a148
 80487ba:	68 b8 00 00 00       	push   $0xb8
 80487bf:	e9 70 fe ff ff       	jmp    8048634 <_init+0x18>

080487c4 <rand@plt>:
 80487c4:	ff 25 4c a1 04 08    	jmp    *0x804a14c
 80487ca:	68 c0 00 00 00       	push   $0xc0
 80487cf:	e9 60 fe ff ff       	jmp    8048634 <_init+0x18>

080487d4 <tempnam@plt>:
 80487d4:	ff 25 50 a1 04 08    	jmp    *0x804a150
 80487da:	68 c8 00 00 00       	push   $0xc8
 80487df:	e9 50 fe ff ff       	jmp    8048634 <_init+0x18>

080487e4 <__strdup@plt>:
 80487e4:	ff 25 54 a1 04 08    	jmp    *0x804a154
 80487ea:	68 d0 00 00 00       	push   $0xd0
 80487ef:	e9 40 fe ff ff       	jmp    8048634 <_init+0x18>

080487f4 <exit@plt>:
 80487f4:	ff 25 58 a1 04 08    	jmp    *0x804a158
 80487fa:	68 d8 00 00 00       	push   $0xd8
 80487ff:	e9 30 fe ff ff       	jmp    8048634 <_init+0x18>

Disassembly of section .text:

08048810 <_start>:
 8048810:	31 ed                	xor    %ebp,%ebp
 8048812:	5e                   	pop    %esi
 8048813:	89 e1                	mov    %esp,%ecx
 8048815:	83 e4 f0             	and    $0xfffffff0,%esp
 8048818:	50                   	push   %eax
 8048819:	54                   	push   %esp
 804881a:	52                   	push   %edx
 804881b:	68 d0 93 04 08       	push   $0x80493d0
 8048820:	68 e0 93 04 08       	push   $0x80493e0
 8048825:	51                   	push   %ecx
 8048826:	56                   	push   %esi
 8048827:	68 90 90 04 08       	push   $0x8049090
 804882c:	e8 93 fe ff ff       	call   80486c4 <__libc_start_main@plt>
 8048831:	f4                   	hlt    
 8048832:	90                   	nop
 8048833:	90                   	nop

08048834 <call_gmon_start>:
 8048834:	55                   	push   %ebp
 8048835:	89 e5                	mov    %esp,%ebp
 8048837:	53                   	push   %ebx
 8048838:	83 ec 04             	sub    $0x4,%esp
 804883b:	e8 00 00 00 00       	call   8048840 <call_gmon_start+0xc>
 8048840:	5b                   	pop    %ebx
 8048841:	81 c3 a0 18 00 00    	add    $0x18a0,%ebx
 8048847:	8b 93 fc ff ff ff    	mov    -0x4(%ebx),%edx
 804884d:	85 d2                	test   %edx,%edx
 804884f:	74 05                	je     8048856 <call_gmon_start+0x22>
 8048851:	e8 2e fe ff ff       	call   8048684 <__gmon_start__@plt>
 8048856:	58                   	pop    %eax
 8048857:	5b                   	pop    %ebx
 8048858:	c9                   	leave  
 8048859:	c3                   	ret    
 804885a:	90                   	nop
 804885b:	90                   	nop
 804885c:	90                   	nop
 804885d:	90                   	nop
 804885e:	90                   	nop
 804885f:	90                   	nop

08048860 <__do_global_dtors_aux>:
 8048860:	55                   	push   %ebp
 8048861:	89 e5                	mov    %esp,%ebp
 8048863:	53                   	push   %ebx
 8048864:	83 ec 04             	sub    $0x4,%esp
 8048867:	80 3d 8c a1 04 08 00 	cmpb   $0x0,0x804a18c
 804886e:	75 3f                	jne    80488af <__do_global_dtors_aux+0x4f>
 8048870:	b8 0c a0 04 08       	mov    $0x804a00c,%eax
 8048875:	2d 08 a0 04 08       	sub    $0x804a008,%eax
 804887a:	c1 f8 02             	sar    $0x2,%eax
 804887d:	8d 58 ff             	lea    -0x1(%eax),%ebx
 8048880:	a1 88 a1 04 08       	mov    0x804a188,%eax
 8048885:	39 c3                	cmp    %eax,%ebx
 8048887:	76 1f                	jbe    80488a8 <__do_global_dtors_aux+0x48>
 8048889:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 8048890:	83 c0 01             	add    $0x1,%eax
 8048893:	a3 88 a1 04 08       	mov    %eax,0x804a188
 8048898:	ff 14 85 08 a0 04 08 	call   *0x804a008(,%eax,4)
 804889f:	a1 88 a1 04 08       	mov    0x804a188,%eax
 80488a4:	39 c3                	cmp    %eax,%ebx
 80488a6:	77 e8                	ja     8048890 <__do_global_dtors_aux+0x30>
 80488a8:	c6 05 8c a1 04 08 01 	movb   $0x1,0x804a18c
 80488af:	83 c4 04             	add    $0x4,%esp
 80488b2:	5b                   	pop    %ebx
 80488b3:	5d                   	pop    %ebp
 80488b4:	c3                   	ret    
 80488b5:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 80488b9:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

080488c0 <frame_dummy>:
 80488c0:	55                   	push   %ebp
 80488c1:	89 e5                	mov    %esp,%ebp
 80488c3:	83 ec 08             	sub    $0x8,%esp
 80488c6:	a1 10 a0 04 08       	mov    0x804a010,%eax
 80488cb:	85 c0                	test   %eax,%eax
 80488cd:	74 12                	je     80488e1 <frame_dummy+0x21>
 80488cf:	b8 00 00 00 00       	mov    $0x0,%eax
 80488d4:	85 c0                	test   %eax,%eax
 80488d6:	74 09                	je     80488e1 <frame_dummy+0x21>
 80488d8:	c7 04 24 10 a0 04 08 	movl   $0x804a010,(%esp)
 80488df:	ff d0                	call   *%eax
 80488e1:	c9                   	leave  
 80488e2:	c3                   	ret    
 80488e3:	90                   	nop
 80488e4:	90                   	nop
 80488e5:	90                   	nop
 80488e6:	90                   	nop
 80488e7:	90                   	nop
 80488e8:	90                   	nop
 80488e9:	90                   	nop
 80488ea:	90                   	nop
 80488eb:	90                   	nop
 80488ec:	90                   	nop
 80488ed:	90                   	nop
 80488ee:	90                   	nop
 80488ef:	90                   	nop

080488f0 <save_char>:
 80488f0:	8b 0d c0 a1 04 08    	mov    0x804a1c0,%ecx
 80488f6:	55                   	push   %ebp
 80488f7:	89 e5                	mov    %esp,%ebp
 80488f9:	53                   	push   %ebx
 80488fa:	89 c3                	mov    %eax,%ebx
 80488fc:	81 f9 ff 03 00 00    	cmp    $0x3ff,%ecx
 8048902:	7f 37                	jg     804893b <save_char+0x4b>
 8048904:	c0 f8 04             	sar    $0x4,%al
 8048907:	83 e0 0f             	and    $0xf,%eax
 804890a:	0f b6 80 2c 9a 04 08 	movzbl 0x8049a2c(%eax),%eax
 8048911:	8d 14 49             	lea    (%ecx,%ecx,2),%edx
 8048914:	c6 82 e2 a1 04 08 20 	movb   $0x20,0x804a1e2(%edx)
 804891b:	88 82 e0 a1 04 08    	mov    %al,0x804a1e0(%edx)
 8048921:	89 d8                	mov    %ebx,%eax
 8048923:	83 e0 0f             	and    $0xf,%eax
 8048926:	0f b6 80 2c 9a 04 08 	movzbl 0x8049a2c(%eax),%eax
 804892d:	88 82 e1 a1 04 08    	mov    %al,0x804a1e1(%edx)
 8048933:	8d 41 01             	lea    0x1(%ecx),%eax
 8048936:	a3 c0 a1 04 08       	mov    %eax,0x804a1c0
 804893b:	5b                   	pop    %ebx
 804893c:	5d                   	pop    %ebp
 804893d:	c3                   	ret    
 804893e:	66 90                	xchg   %ax,%ax

08048940 <entry_check>:
 8048940:	55                   	push   %ebp
 8048941:	89 e5                	mov    %esp,%ebp
 8048943:	8b 45 08             	mov    0x8(%ebp),%eax
 8048946:	5d                   	pop    %ebp
 8048947:	a3 64 a1 04 08       	mov    %eax,0x804a164
 804894c:	c3                   	ret    
 804894d:	8d 76 00             	lea    0x0(%esi),%esi

08048950 <illegalhandler>:
 8048950:	55                   	push   %ebp
 8048951:	89 e5                	mov    %esp,%ebp
 8048953:	83 ec 08             	sub    $0x8,%esp
 8048956:	c7 04 24 a0 94 04 08 	movl   $0x80494a0,(%esp)
 804895d:	e8 52 fe ff ff       	call   80487b4 <puts@plt>
 8048962:	c7 04 24 14 98 04 08 	movl   $0x8049814,(%esp)
 8048969:	e8 46 fe ff ff       	call   80487b4 <puts@plt>
 804896e:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048975:	e8 7a fe ff ff       	call   80487f4 <exit@plt>
 804897a:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi

08048980 <alarmhandler>:
 8048980:	55                   	push   %ebp
 8048981:	89 e5                	mov    %esp,%ebp
 8048983:	83 ec 08             	sub    $0x8,%esp
 8048986:	a1 68 a1 04 08       	mov    0x804a168,%eax
 804898b:	c7 04 24 cc 94 04 08 	movl   $0x80494cc,(%esp)
 8048992:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048996:	e8 a9 fd ff ff       	call   8048744 <printf@plt>
 804899b:	c7 04 24 14 98 04 08 	movl   $0x8049814,(%esp)
 80489a2:	e8 0d fe ff ff       	call   80487b4 <puts@plt>
 80489a7:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 80489ae:	e8 41 fe ff ff       	call   80487f4 <exit@plt>
 80489b3:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 80489b9:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

080489c0 <seghandler>:
 80489c0:	55                   	push   %ebp
 80489c1:	89 e5                	mov    %esp,%ebp
 80489c3:	83 ec 08             	sub    $0x8,%esp
 80489c6:	c7 04 24 00 95 04 08 	movl   $0x8049500,(%esp)
 80489cd:	e8 e2 fd ff ff       	call   80487b4 <puts@plt>
 80489d2:	c7 04 24 14 98 04 08 	movl   $0x8049814,(%esp)
 80489d9:	e8 d6 fd ff ff       	call   80487b4 <puts@plt>
 80489de:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 80489e5:	e8 0a fe ff ff       	call   80487f4 <exit@plt>
 80489ea:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi

080489f0 <bushandler>:
 80489f0:	55                   	push   %ebp
 80489f1:	89 e5                	mov    %esp,%ebp
 80489f3:	83 ec 08             	sub    $0x8,%esp
 80489f6:	c7 04 24 28 95 04 08 	movl   $0x8049528,(%esp)
 80489fd:	e8 b2 fd ff ff       	call   80487b4 <puts@plt>
 8048a02:	c7 04 24 14 98 04 08 	movl   $0x8049814,(%esp)
 8048a09:	e8 a6 fd ff ff       	call   80487b4 <puts@plt>
 8048a0e:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048a15:	e8 da fd ff ff       	call   80487f4 <exit@plt>
 8048a1a:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi

08048a20 <usage>:
 8048a20:	55                   	push   %ebp
 8048a21:	89 e5                	mov    %esp,%ebp
 8048a23:	83 ec 08             	sub    $0x8,%esp
 8048a26:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048a2a:	c7 04 24 48 95 04 08 	movl   $0x8049548,(%esp)
 8048a31:	e8 0e fd ff ff       	call   8048744 <printf@plt>
 8048a36:	c7 04 24 2a 98 04 08 	movl   $0x804982a,(%esp)
 8048a3d:	e8 72 fd ff ff       	call   80487b4 <puts@plt>
 8048a42:	c7 04 24 48 98 04 08 	movl   $0x8049848,(%esp)
 8048a49:	e8 66 fd ff ff       	call   80487b4 <puts@plt>
 8048a4e:	c7 04 24 6c 95 04 08 	movl   $0x804956c,(%esp)
 8048a55:	e8 5a fd ff ff       	call   80487b4 <puts@plt>
 8048a5a:	c7 04 24 94 95 04 08 	movl   $0x8049594,(%esp)
 8048a61:	e8 4e fd ff ff       	call   80487b4 <puts@plt>
 8048a66:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048a6d:	e8 82 fd ff ff       	call   80487f4 <exit@plt>
 8048a72:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 8048a79:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08048a80 <validate>:
 8048a80:	55                   	push   %ebp
 8048a81:	89 e5                	mov    %esp,%ebp
 8048a83:	81 ec 48 01 00 00    	sub    $0x148,%esp
 8048a89:	8b 0d b0 a1 04 08    	mov    0x804a1b0,%ecx
 8048a8f:	89 5d f4             	mov    %ebx,-0xc(%ebp)
 8048a92:	8b 5d 08             	mov    0x8(%ebp),%ebx
 8048a95:	89 75 f8             	mov    %esi,-0x8(%ebp)
 8048a98:	89 7d fc             	mov    %edi,-0x4(%ebp)
 8048a9b:	85 c9                	test   %ecx,%ecx
 8048a9d:	0f 84 d8 01 00 00    	je     8048c7b <validate+0x1fb>
 8048aa3:	83 fb 04             	cmp    $0x4,%ebx
 8048aa6:	77 58                	ja     8048b00 <validate+0x80>
 8048aa8:	3b 1d 64 a1 04 08    	cmp    0x804a164,%ebx
 8048aae:	74 20                	je     8048ad0 <validate+0x50>
 8048ab0:	c7 04 24 0c 96 04 08 	movl   $0x804960c,(%esp)
 8048ab7:	e8 f8 fc ff ff       	call   80487b4 <puts@plt>
 8048abc:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048ac0:	8b 5d f4             	mov    -0xc(%ebp),%ebx
 8048ac3:	8b 75 f8             	mov    -0x8(%ebp),%esi
 8048ac6:	8b 7d fc             	mov    -0x4(%ebp),%edi
 8048ac9:	89 ec                	mov    %ebp,%esp
 8048acb:	5d                   	pop    %ebp
 8048acc:	c3                   	ret    
 8048acd:	8d 76 00             	lea    0x0(%esi),%esi
 8048ad0:	8b 04 9d 6c a1 04 08 	mov    0x804a16c(,%ebx,4),%eax
 8048ad7:	c7 05 b8 a1 04 08 01 	movl   $0x1,0x804a1b8
 8048ade:	00 00 00 
 8048ae1:	83 e8 01             	sub    $0x1,%eax
 8048ae4:	85 c0                	test   %eax,%eax
 8048ae6:	89 04 9d 6c a1 04 08 	mov    %eax,0x804a16c(,%ebx,4)
 8048aed:	7e 21                	jle    8048b10 <validate+0x90>
 8048aef:	c7 04 24 5f 98 04 08 	movl   $0x804985f,(%esp)
 8048af6:	e8 b9 fc ff ff       	call   80487b4 <puts@plt>
 8048afb:	eb c3                	jmp    8048ac0 <validate+0x40>
 8048afd:	8d 76 00             	lea    0x0(%esi),%esi
 8048b00:	c7 04 24 e4 95 04 08 	movl   $0x80495e4,(%esp)
 8048b07:	e8 a8 fc ff ff       	call   80487b4 <puts@plt>
 8048b0c:	eb b2                	jmp    8048ac0 <validate+0x40>
 8048b0e:	66 90                	xchg   %ax,%ax
 8048b10:	8b 15 b4 a1 04 08    	mov    0x804a1b4,%edx
 8048b16:	85 d2                	test   %edx,%edx
 8048b18:	0f 85 7f 01 00 00    	jne    8048c9d <validate+0x21d>
 8048b1e:	a1 60 a1 04 08       	mov    0x804a160,%eax
 8048b23:	85 c0                	test   %eax,%eax
 8048b25:	0f 84 61 01 00 00    	je     8048c8c <validate+0x20c>
 8048b2b:	c7 44 24 04 70 98 04 	movl   $0x8049870,0x4(%esp)
 8048b32:	08 
 8048b33:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048b3a:	e8 95 fc ff ff       	call   80487d4 <tempnam@plt>
 8048b3f:	c7 44 24 04 77 98 04 	movl   $0x8049877,0x4(%esp)
 8048b46:	08 
 8048b47:	89 85 e0 fe ff ff    	mov    %eax,-0x120(%ebp)
 8048b4d:	89 04 24             	mov    %eax,(%esp)
 8048b50:	e8 bf fb ff ff       	call   8048714 <fopen@plt>
 8048b55:	85 c0                	test   %eax,%eax
 8048b57:	89 c6                	mov    %eax,%esi
 8048b59:	0f 84 76 01 00 00    	je     8048cd5 <validate+0x255>
 8048b5f:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8048b63:	c7 44 24 08 1b 00 00 	movl   $0x1b,0x8(%esp)
 8048b6a:	00 
 8048b6b:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 8048b72:	00 
 8048b73:	c7 04 24 79 98 04 08 	movl   $0x8049879,(%esp)
 8048b7a:	e8 e5 fb ff ff       	call   8048764 <fwrite@plt>
 8048b7f:	89 74 24 04          	mov    %esi,0x4(%esp)
 8048b83:	c7 04 24 0a 00 00 00 	movl   $0xa,(%esp)
 8048b8a:	e8 15 fc ff ff       	call   80487a4 <fputc@plt>
 8048b8f:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048b96:	e8 f9 fb ff ff       	call   8048794 <cuserid@plt>
 8048b9b:	85 c0                	test   %eax,%eax
 8048b9d:	0f 84 19 01 00 00    	je     8048cbc <validate+0x23c>
 8048ba3:	8d 7d eb             	lea    -0x15(%ebp),%edi
 8048ba6:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048baa:	89 3c 24             	mov    %edi,(%esp)
 8048bad:	e8 82 fb ff ff       	call   8048734 <strcpy@plt>
 8048bb2:	89 7c 24 08          	mov    %edi,0x8(%esp)
 8048bb6:	c7 44 24 04 95 98 04 	movl   $0x8049895,0x4(%esp)
 8048bbd:	08 
 8048bbe:	89 34 24             	mov    %esi,(%esp)
 8048bc1:	e8 ae fb ff ff       	call   8048774 <fprintf@plt>
 8048bc6:	a1 ac a1 04 08       	mov    0x804a1ac,%eax
 8048bcb:	89 5c 24 10          	mov    %ebx,0x10(%esp)
 8048bcf:	8d 9d eb fe ff ff    	lea    -0x115(%ebp),%ebx
 8048bd5:	c7 44 24 1c 00 00 00 	movl   $0x0,0x1c(%esp)
 8048bdc:	00 
 8048bdd:	c7 44 24 18 e0 a1 04 	movl   $0x804a1e0,0x18(%esp)
 8048be4:	08 
 8048be5:	89 44 24 14          	mov    %eax,0x14(%esp)
 8048be9:	a1 b0 a1 04 08       	mov    0x804a1b0,%eax
 8048bee:	c7 44 24 08 6f 3b 00 	movl   $0x3b6f,0x8(%esp)
 8048bf5:	00 
 8048bf6:	c7 44 24 04 7c 96 04 	movl   $0x804967c,0x4(%esp)
 8048bfd:	08 
 8048bfe:	89 34 24             	mov    %esi,(%esp)
 8048c01:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8048c05:	e8 6a fb ff ff       	call   8048774 <fprintf@plt>
 8048c0a:	89 34 24             	mov    %esi,(%esp)
 8048c0d:	e8 e2 fa ff ff       	call   80486f4 <fclose@plt>
 8048c12:	8b 85 e0 fe ff ff    	mov    -0x120(%ebp),%eax
 8048c18:	c7 44 24 14 a3 98 04 	movl   $0x80498a3,0x14(%esp)
 8048c1f:	08 
 8048c20:	c7 44 24 10 b2 98 04 	movl   $0x80498b2,0x10(%esp)
 8048c27:	08 
 8048c28:	c7 44 24 0c b9 98 04 	movl   $0x80498b9,0xc(%esp)
 8048c2f:	08 
 8048c30:	89 44 24 08          	mov    %eax,0x8(%esp)
 8048c34:	c7 44 24 04 c2 98 04 	movl   $0x80498c2,0x4(%esp)
 8048c3b:	08 
 8048c3c:	89 1c 24             	mov    %ebx,(%esp)
 8048c3f:	e8 00 fa ff ff       	call   8048644 <sprintf@plt>
 8048c44:	89 1c 24             	mov    %ebx,(%esp)
 8048c47:	e8 58 fa ff ff       	call   80486a4 <system@plt>
 8048c4c:	85 c0                	test   %eax,%eax
 8048c4e:	75 5e                	jne    8048cae <validate+0x22e>
 8048c50:	c7 04 24 d5 98 04 08 	movl   $0x80498d5,(%esp)
 8048c57:	e8 58 fb ff ff       	call   80487b4 <puts@plt>
 8048c5c:	c7 04 24 9c 96 04 08 	movl   $0x804969c,(%esp)
 8048c63:	e8 4c fb ff ff       	call   80487b4 <puts@plt>
 8048c68:	8b 85 e0 fe ff ff    	mov    -0x120(%ebp),%eax
 8048c6e:	89 04 24             	mov    %eax,(%esp)
 8048c71:	e8 0e fb ff ff       	call   8048784 <remove@plt>
 8048c76:	e9 45 fe ff ff       	jmp    8048ac0 <validate+0x40>
 8048c7b:	c7 04 24 b8 95 04 08 	movl   $0x80495b8,(%esp)
 8048c82:	e8 2d fb ff ff       	call   80487b4 <puts@plt>
 8048c87:	e9 34 fe ff ff       	jmp    8048ac0 <validate+0x40>
 8048c8c:	c7 04 24 0c 97 04 08 	movl   $0x804970c,(%esp)
 8048c93:	e8 1c fb ff ff       	call   80487b4 <puts@plt>
 8048c98:	e9 23 fe ff ff       	jmp    8048ac0 <validate+0x40>
 8048c9d:	c7 04 24 6a 98 04 08 	movl   $0x804986a,(%esp)
 8048ca4:	e8 0b fb ff ff       	call   80487b4 <puts@plt>
 8048ca9:	e9 12 fe ff ff       	jmp    8048ac0 <validate+0x40>
 8048cae:	c7 04 24 cc 96 04 08 	movl   $0x80496cc,(%esp)
 8048cb5:	e8 fa fa ff ff       	call   80487b4 <puts@plt>
 8048cba:	eb ac                	jmp    8048c68 <validate+0x1e8>
 8048cbc:	8d 7d eb             	lea    -0x15(%ebp),%edi
 8048cbf:	c7 45 eb 6e 6f 62 6f 	movl   $0x6f626f6e,-0x15(%ebp)
 8048cc6:	66 c7 45 ef 64 79    	movw   $0x7964,-0x11(%ebp)
 8048ccc:	c6 45 f1 00          	movb   $0x0,-0xf(%ebp)
 8048cd0:	e9 dd fe ff ff       	jmp    8048bb2 <validate+0x132>
 8048cd5:	c7 04 24 48 96 04 08 	movl   $0x8049648,(%esp)
 8048cdc:	e8 63 fa ff ff       	call   8048744 <printf@plt>
 8048ce1:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 8048ce8:	e8 07 fb ff ff       	call   80487f4 <exit@plt>
 8048ced:	8d 76 00             	lea    0x0(%esi),%esi

08048cf0 <bang>:
 8048cf0:	55                   	push   %ebp
 8048cf1:	89 e5                	mov    %esp,%ebp
 8048cf3:	83 ec 08             	sub    $0x8,%esp
 8048cf6:	c7 04 24 02 00 00 00 	movl   $0x2,(%esp)
 8048cfd:	e8 3e fc ff ff       	call   8048940 <entry_check>
 8048d02:	a1 bc a1 04 08       	mov    0x804a1bc,%eax
 8048d07:	3b 05 ac a1 04 08    	cmp    0x804a1ac,%eax
 8048d0d:	74 21                	je     8048d30 <bang+0x40>
 8048d0f:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048d13:	c7 04 24 df 98 04 08 	movl   $0x80498df,(%esp)
 8048d1a:	e8 25 fa ff ff       	call   8048744 <printf@plt>
 8048d1f:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048d26:	e8 c9 fa ff ff       	call   80487f4 <exit@plt>
 8048d2b:	90                   	nop
 8048d2c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048d30:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048d34:	c7 04 24 58 97 04 08 	movl   $0x8049758,(%esp)
 8048d3b:	e8 04 fa ff ff       	call   8048744 <printf@plt>
 8048d40:	c7 04 24 02 00 00 00 	movl   $0x2,(%esp)
 8048d47:	e8 34 fd ff ff       	call   8048a80 <validate>
 8048d4c:	eb d1                	jmp    8048d1f <bang+0x2f>
 8048d4e:	66 90                	xchg   %ax,%ax

08048d50 <fizz>:
 8048d50:	55                   	push   %ebp
 8048d51:	89 e5                	mov    %esp,%ebp
 8048d53:	53                   	push   %ebx
 8048d54:	83 ec 14             	sub    $0x14,%esp
 8048d57:	8b 5d 08             	mov    0x8(%ebp),%ebx
 8048d5a:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 8048d61:	e8 da fb ff ff       	call   8048940 <entry_check>
 8048d66:	3b 1d ac a1 04 08    	cmp    0x804a1ac,%ebx
 8048d6c:	74 22                	je     8048d90 <fizz+0x40>
 8048d6e:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048d72:	c7 04 24 80 97 04 08 	movl   $0x8049780,(%esp)
 8048d79:	e8 c6 f9 ff ff       	call   8048744 <printf@plt>
 8048d7e:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048d85:	e8 6a fa ff ff       	call   80487f4 <exit@plt>
 8048d8a:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 8048d90:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048d94:	c7 04 24 fd 98 04 08 	movl   $0x80498fd,(%esp)
 8048d9b:	e8 a4 f9 ff ff       	call   8048744 <printf@plt>
 8048da0:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 8048da7:	e8 d4 fc ff ff       	call   8048a80 <validate>
 8048dac:	eb d0                	jmp    8048d7e <fizz+0x2e>
 8048dae:	66 90                	xchg   %ax,%ax

08048db0 <smoke>:
 8048db0:	55                   	push   %ebp
 8048db1:	89 e5                	mov    %esp,%ebp
 8048db3:	83 ec 08             	sub    $0x8,%esp
 8048db6:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048dbd:	e8 7e fb ff ff       	call   8048940 <entry_check>
 8048dc2:	c7 04 24 1b 99 04 08 	movl   $0x804991b,(%esp)
 8048dc9:	e8 e6 f9 ff ff       	call   80487b4 <puts@plt>
 8048dce:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048dd5:	e8 a6 fc ff ff       	call   8048a80 <validate>
 8048dda:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 8048de1:	e8 0e fa ff ff       	call   80487f4 <exit@plt>
 8048de6:	8d 76 00             	lea    0x0(%esi),%esi
 8048de9:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08048df0 <Gets>:
 8048df0:	55                   	push   %ebp
 8048df1:	89 e5                	mov    %esp,%ebp
 8048df3:	57                   	push   %edi
 8048df4:	56                   	push   %esi
 8048df5:	53                   	push   %ebx
 8048df6:	83 ec 0c             	sub    $0xc,%esp
 8048df9:	8b 1d a8 a1 04 08    	mov    0x804a1a8,%ebx
 8048dff:	c7 05 c0 a1 04 08 00 	movl   $0x0,0x804a1c0
 8048e06:	00 00 00 
 8048e09:	8b 75 08             	mov    0x8(%ebp),%esi
 8048e0c:	85 db                	test   %ebx,%ebx
 8048e0e:	74 72                	je     8048e82 <Gets+0x92>
 8048e10:	bf 01 00 00 00       	mov    $0x1,%edi
 8048e15:	c7 45 f0 00 00 00 00 	movl   $0x0,-0x10(%ebp)
 8048e1c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048e20:	a1 a0 a1 04 08       	mov    0x804a1a0,%eax
 8048e25:	89 04 24             	mov    %eax,(%esp)
 8048e28:	e8 a7 f8 ff ff       	call   80486d4 <_IO_getc@plt>
 8048e2d:	83 f8 ff             	cmp    $0xffffffff,%eax
 8048e30:	89 c3                	mov    %eax,%ebx
 8048e32:	74 60                	je     8048e94 <Gets+0xa4>
 8048e34:	83 f8 0a             	cmp    $0xa,%eax
 8048e37:	74 5b                	je     8048e94 <Gets+0xa4>
 8048e39:	e8 a6 f8 ff ff       	call   80486e4 <__ctype_b_loc@plt>
 8048e3e:	8b 00                	mov    (%eax),%eax
 8048e40:	f6 44 58 01 10       	testb  $0x10,0x1(%eax,%ebx,2)
 8048e45:	74 d9                	je     8048e20 <Gets+0x30>
 8048e47:	8d 43 d0             	lea    -0x30(%ebx),%eax
 8048e4a:	83 f8 09             	cmp    $0x9,%eax
 8048e4d:	89 c2                	mov    %eax,%edx
 8048e4f:	76 0f                	jbe    8048e60 <Gets+0x70>
 8048e51:	8d 43 bf             	lea    -0x41(%ebx),%eax
 8048e54:	83 f8 05             	cmp    $0x5,%eax
 8048e57:	8d 53 c9             	lea    -0x37(%ebx),%edx
 8048e5a:	76 04                	jbe    8048e60 <Gets+0x70>
 8048e5c:	8d 53 a9             	lea    -0x57(%ebx),%edx
 8048e5f:	90                   	nop
 8048e60:	85 ff                	test   %edi,%edi
 8048e62:	74 4c                	je     8048eb0 <Gets+0xc0>
 8048e64:	31 ff                	xor    %edi,%edi
 8048e66:	89 55 f0             	mov    %edx,-0x10(%ebp)
 8048e69:	eb b5                	jmp    8048e20 <Gets+0x30>
 8048e6b:	90                   	nop
 8048e6c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048e70:	83 f8 0a             	cmp    $0xa,%eax
 8048e73:	74 1f                	je     8048e94 <Gets+0xa4>
 8048e75:	88 06                	mov    %al,(%esi)
 8048e77:	0f be c0             	movsbl %al,%eax
 8048e7a:	83 c6 01             	add    $0x1,%esi
 8048e7d:	e8 6e fa ff ff       	call   80488f0 <save_char>
 8048e82:	a1 a0 a1 04 08       	mov    0x804a1a0,%eax
 8048e87:	89 04 24             	mov    %eax,(%esp)
 8048e8a:	e8 45 f8 ff ff       	call   80486d4 <_IO_getc@plt>
 8048e8f:	83 f8 ff             	cmp    $0xffffffff,%eax
 8048e92:	75 dc                	jne    8048e70 <Gets+0x80>
 8048e94:	c6 06 00             	movb   $0x0,(%esi)
 8048e97:	a1 c0 a1 04 08       	mov    0x804a1c0,%eax
 8048e9c:	c6 84 40 e0 a1 04 08 	movb   $0x0,0x804a1e0(%eax,%eax,2)
 8048ea3:	00 
 8048ea4:	8b 45 08             	mov    0x8(%ebp),%eax
 8048ea7:	83 c4 0c             	add    $0xc,%esp
 8048eaa:	5b                   	pop    %ebx
 8048eab:	5e                   	pop    %esi
 8048eac:	5f                   	pop    %edi
 8048ead:	5d                   	pop    %ebp
 8048eae:	c3                   	ret    
 8048eaf:	90                   	nop
 8048eb0:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8048eb3:	bf 01 00 00 00       	mov    $0x1,%edi
 8048eb8:	c1 e0 04             	shl    $0x4,%eax
 8048ebb:	8d 04 02             	lea    (%edx,%eax,1),%eax
 8048ebe:	88 06                	mov    %al,(%esi)
 8048ec0:	0f be c0             	movsbl %al,%eax
 8048ec3:	83 c6 01             	add    $0x1,%esi
 8048ec6:	e8 25 fa ff ff       	call   80488f0 <save_char>
 8048ecb:	e9 50 ff ff ff       	jmp    8048e20 <Gets+0x30>

08048ed0 <getbufn>:
 8048ed0:	55                   	push   %ebp
 8048ed1:	89 e5                	mov    %esp,%ebp
 8048ed3:	81 ec 08 02 00 00    	sub    $0x208,%esp
 8048ed9:	8d 85 00 fe ff ff    	lea    -0x200(%ebp),%eax
 8048edf:	89 04 24             	mov    %eax,(%esp)
 8048ee2:	e8 09 ff ff ff       	call   8048df0 <Gets>
 8048ee7:	b8 01 00 00 00       	mov    $0x1,%eax
 8048eec:	c9                   	leave  
 8048eed:	c3                   	ret    
 8048eee:	66 90                	xchg   %ax,%ax

08048ef0 <testn>:
 8048ef0:	55                   	push   %ebp
 8048ef1:	89 e5                	mov    %esp,%ebp
 8048ef3:	83 ec 18             	sub    $0x18,%esp
 8048ef6:	c7 45 fc ef be ad de 	movl   $0xdeadbeef,-0x4(%ebp)
 8048efd:	c7 04 24 04 00 00 00 	movl   $0x4,(%esp)
 8048f04:	e8 37 fa ff ff       	call   8048940 <entry_check>
 8048f09:	e8 c2 ff ff ff       	call   8048ed0 <getbufn>
 8048f0e:	89 c2                	mov    %eax,%edx
 8048f10:	8b 45 fc             	mov    -0x4(%ebp),%eax
 8048f13:	3d ef be ad de       	cmp    $0xdeadbeef,%eax
 8048f18:	74 0e                	je     8048f28 <testn+0x38>
 8048f1a:	c7 04 24 a0 97 04 08 	movl   $0x80497a0,(%esp)
 8048f21:	e8 8e f8 ff ff       	call   80487b4 <puts@plt>
 8048f26:	c9                   	leave  
 8048f27:	c3                   	ret    
 8048f28:	3b 15 ac a1 04 08    	cmp    0x804a1ac,%edx
 8048f2e:	74 12                	je     8048f42 <testn+0x52>
 8048f30:	89 54 24 04          	mov    %edx,0x4(%esp)
 8048f34:	c7 04 24 36 99 04 08 	movl   $0x8049936,(%esp)
 8048f3b:	e8 04 f8 ff ff       	call   8048744 <printf@plt>
 8048f40:	c9                   	leave  
 8048f41:	c3                   	ret    
 8048f42:	89 54 24 04          	mov    %edx,0x4(%esp)
 8048f46:	c7 04 24 cc 97 04 08 	movl   $0x80497cc,(%esp)
 8048f4d:	e8 f2 f7 ff ff       	call   8048744 <printf@plt>
 8048f52:	c7 04 24 04 00 00 00 	movl   $0x4,(%esp)
 8048f59:	e8 22 fb ff ff       	call   8048a80 <validate>
 8048f5e:	c9                   	leave  
 8048f5f:	c3                   	ret    

08048f60 <getbuf>:
 8048f60:	55                   	push   %ebp
 8048f61:	89 e5                	mov    %esp,%ebp
 8048f63:	83 ec 18             	sub    $0x18,%esp
 8048f66:	8d 45 f4             	lea    -0xc(%ebp),%eax
 8048f69:	89 04 24             	mov    %eax,(%esp)
 8048f6c:	e8 7f fe ff ff       	call   8048df0 <Gets>
 8048f71:	b8 01 00 00 00       	mov    $0x1,%eax
 8048f76:	c9                   	leave  
 8048f77:	c3                   	ret    
 8048f78:	90                   	nop
 8048f79:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi

08048f80 <test>:
 8048f80:	55                   	push   %ebp
 8048f81:	89 e5                	mov    %esp,%ebp
 8048f83:	83 ec 18             	sub    $0x18,%esp
 8048f86:	c7 45 fc ef be ad de 	movl   $0xdeadbeef,-0x4(%ebp)
 8048f8d:	c7 04 24 03 00 00 00 	movl   $0x3,(%esp)
 8048f94:	e8 a7 f9 ff ff       	call   8048940 <entry_check>
 8048f99:	e8 c2 ff ff ff       	call   8048f60 <getbuf>
 8048f9e:	89 c2                	mov    %eax,%edx
 8048fa0:	8b 45 fc             	mov    -0x4(%ebp),%eax
 8048fa3:	3d ef be ad de       	cmp    $0xdeadbeef,%eax
 8048fa8:	74 0e                	je     8048fb8 <test+0x38>
 8048faa:	c7 04 24 a0 97 04 08 	movl   $0x80497a0,(%esp)
 8048fb1:	e8 fe f7 ff ff       	call   80487b4 <puts@plt>
 8048fb6:	c9                   	leave  
 8048fb7:	c3                   	ret    
 8048fb8:	3b 15 ac a1 04 08    	cmp    0x804a1ac,%edx
 8048fbe:	74 12                	je     8048fd2 <test+0x52>
 8048fc0:	89 54 24 04          	mov    %edx,0x4(%esp)
 8048fc4:	c7 04 24 6f 99 04 08 	movl   $0x804996f,(%esp)
 8048fcb:	e8 74 f7 ff ff       	call   8048744 <printf@plt>
 8048fd0:	c9                   	leave  
 8048fd1:	c3                   	ret    
 8048fd2:	89 54 24 04          	mov    %edx,0x4(%esp)
 8048fd6:	c7 04 24 52 99 04 08 	movl   $0x8049952,(%esp)
 8048fdd:	e8 62 f7 ff ff       	call   8048744 <printf@plt>
 8048fe2:	c7 04 24 03 00 00 00 	movl   $0x3,(%esp)
 8048fe9:	e8 92 fa ff ff       	call   8048a80 <validate>
 8048fee:	c9                   	leave  
 8048fef:	c3                   	ret    

08048ff0 <launch>:
 8048ff0:	55                   	push   %ebp
 8048ff1:	89 e5                	mov    %esp,%ebp
 8048ff3:	53                   	push   %ebx
 8048ff4:	89 c3                	mov    %eax,%ebx
 8048ff6:	8d 45 bc             	lea    -0x44(%ebp),%eax
 8048ff9:	83 ec 54             	sub    $0x54,%esp
 8048ffc:	25 f8 3f 00 00       	and    $0x3ff8,%eax
 8049001:	01 c2                	add    %eax,%edx
 8049003:	8d 42 1e             	lea    0x1e(%edx),%eax
 8049006:	83 e0 f0             	and    $0xfffffff0,%eax
 8049009:	29 c4                	sub    %eax,%esp
 804900b:	8d 44 24 1b          	lea    0x1b(%esp),%eax
 804900f:	83 e0 f0             	and    $0xfffffff0,%eax
 8049012:	89 54 24 08          	mov    %edx,0x8(%esp)
 8049016:	c7 44 24 04 f4 00 00 	movl   $0xf4,0x4(%esp)
 804901d:	00 
 804901e:	89 04 24             	mov    %eax,(%esp)
 8049021:	e8 8e f6 ff ff       	call   80486b4 <memset@plt>
 8049026:	a1 a4 a1 04 08       	mov    0x804a1a4,%eax
 804902b:	85 c0                	test   %eax,%eax
 804902d:	75 15                	jne    8049044 <launch+0x54>
 804902f:	a1 a8 a1 04 08       	mov    0x804a1a8,%eax
 8049034:	85 c0                	test   %eax,%eax
 8049036:	75 40                	jne    8049078 <launch+0x88>
 8049038:	c7 04 24 9b 99 04 08 	movl   $0x804999b,(%esp)
 804903f:	e8 00 f7 ff ff       	call   8048744 <printf@plt>
 8049044:	85 db                	test   %ebx,%ebx
 8049046:	74 29                	je     8049071 <launch+0x81>
 8049048:	e8 a3 fe ff ff       	call   8048ef0 <testn>
 804904d:	a1 b8 a1 04 08       	mov    0x804a1b8,%eax
 8049052:	85 c0                	test   %eax,%eax
 8049054:	75 16                	jne    804906c <launch+0x7c>
 8049056:	c7 04 24 14 98 04 08 	movl   $0x8049814,(%esp)
 804905d:	e8 52 f7 ff ff       	call   80487b4 <puts@plt>
 8049062:	c7 05 b8 a1 04 08 00 	movl   $0x0,0x804a1b8
 8049069:	00 00 00 
 804906c:	8b 5d fc             	mov    -0x4(%ebp),%ebx
 804906f:	c9                   	leave  
 8049070:	c3                   	ret    
 8049071:	e8 0a ff ff ff       	call   8048f80 <test>
 8049076:	eb d5                	jmp    804904d <launch+0x5d>
 8049078:	c7 04 24 8a 99 04 08 	movl   $0x804998a,(%esp)
 804907f:	e8 c0 f6 ff ff       	call   8048744 <printf@plt>
 8049084:	eb be                	jmp    8049044 <launch+0x54>
 8049086:	8d 76 00             	lea    0x0(%esi),%esi
 8049089:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08049090 <main>:
 8049090:	8d 4c 24 04          	lea    0x4(%esp),%ecx
 8049094:	83 e4 f0             	and    $0xfffffff0,%esp
 8049097:	ff 71 fc             	pushl  -0x4(%ecx)
 804909a:	55                   	push   %ebp
 804909b:	89 e5                	mov    %esp,%ebp
 804909d:	57                   	push   %edi
 804909e:	56                   	push   %esi
 804909f:	53                   	push   %ebx
 80490a0:	51                   	push   %ecx
 80490a1:	83 ec 18             	sub    $0x18,%esp
 80490a4:	8b 31                	mov    (%ecx),%esi
 80490a6:	8b 59 04             	mov    0x4(%ecx),%ebx
 80490a9:	c7 44 24 04 c0 89 04 	movl   $0x80489c0,0x4(%esp)
 80490b0:	08 
 80490b1:	c7 04 24 0b 00 00 00 	movl   $0xb,(%esp)
 80490b8:	e8 b7 f5 ff ff       	call   8048674 <signal@plt>
 80490bd:	c7 44 24 04 f0 89 04 	movl   $0x80489f0,0x4(%esp)
 80490c4:	08 
 80490c5:	c7 04 24 07 00 00 00 	movl   $0x7,(%esp)
 80490cc:	e8 a3 f5 ff ff       	call   8048674 <signal@plt>
 80490d1:	c7 44 24 04 80 89 04 	movl   $0x8048980,0x4(%esp)
 80490d8:	08 
 80490d9:	c7 04 24 0e 00 00 00 	movl   $0xe,(%esp)
 80490e0:	e8 8f f5 ff ff       	call   8048674 <signal@plt>
 80490e5:	c7 44 24 04 50 89 04 	movl   $0x8048950,0x4(%esp)
 80490ec:	08 
 80490ed:	c7 04 24 04 00 00 00 	movl   $0x4,(%esp)
 80490f4:	e8 7b f5 ff ff       	call   8048674 <signal@plt>
 80490f9:	a1 80 a1 04 08       	mov    0x804a180,%eax
 80490fe:	c7 45 e8 00 00 00 00 	movl   $0x0,-0x18(%ebp)
 8049105:	c7 45 ec 01 00 00 00 	movl   $0x1,-0x14(%ebp)
 804910c:	a3 a0 a1 04 08       	mov    %eax,0x804a1a0
 8049111:	c7 44 24 08 d7 99 04 	movl   $0x80499d7,0x8(%esp)
 8049118:	08 
 8049119:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 804911d:	89 34 24             	mov    %esi,(%esp)
 8049120:	e8 df f5 ff ff       	call   8048704 <getopt@plt>
 8049125:	3c ff                	cmp    $0xff,%al
 8049127:	0f 84 04 01 00 00    	je     8049231 <main+0x1a1>
 804912d:	83 e8 66             	sub    $0x66,%eax
 8049130:	3c 12                	cmp    $0x12,%al
 8049132:	77 0c                	ja     8049140 <main+0xb0>
 8049134:	0f b6 c0             	movzbl %al,%eax
 8049137:	ff 24 85 e0 99 04 08 	jmp    *0x80499e0(,%eax,4)
 804913e:	66 90                	xchg   %ax,%ax
 8049140:	8b 03                	mov    (%ebx),%eax
 8049142:	e8 d9 f8 ff ff       	call   8048a20 <usage>
 8049147:	eb c8                	jmp    8049111 <main+0x81>
 8049149:	c7 05 b4 a1 04 08 01 	movl   $0x1,0x804a1b4
 8049150:	00 00 00 
 8049153:	c7 05 a4 a1 04 08 01 	movl   $0x1,0x804a1a4
 804915a:	00 00 00 
 804915d:	c7 05 68 a1 04 08 01 	movl   $0x1,0x804a168
 8049164:	00 00 00 
 8049167:	eb a8                	jmp    8049111 <main+0x81>
 8049169:	c7 44 24 04 c0 99 04 	movl   $0x80499c0,0x4(%esp)
 8049170:	08 
 8049171:	a1 84 a1 04 08       	mov    0x804a184,%eax
 8049176:	89 04 24             	mov    %eax,(%esp)
 8049179:	e8 96 f5 ff ff       	call   8048714 <fopen@plt>
 804917e:	85 c0                	test   %eax,%eax
 8049180:	a3 a0 a1 04 08       	mov    %eax,0x804a1a0
 8049185:	75 8a                	jne    8049111 <main+0x81>
 8049187:	a1 84 a1 04 08       	mov    0x804a184,%eax
 804918c:	c7 04 24 c2 99 04 08 	movl   $0x80499c2,(%esp)
 8049193:	89 44 24 04          	mov    %eax,0x4(%esp)
 8049197:	e8 a8 f5 ff ff       	call   8048744 <printf@plt>
 804919c:	8b 03                	mov    (%ebx),%eax
 804919e:	e8 7d f8 ff ff       	call   8048a20 <usage>
 80491a3:	e9 69 ff ff ff       	jmp    8049111 <main+0x81>
 80491a8:	c7 45 e8 01 00 00 00 	movl   $0x1,-0x18(%ebp)
 80491af:	c7 45 ec 05 00 00 00 	movl   $0x5,-0x14(%ebp)
 80491b6:	e9 56 ff ff ff       	jmp    8049111 <main+0x81>
 80491bb:	c7 05 a4 a1 04 08 01 	movl   $0x1,0x804a1a4
 80491c2:	00 00 00 
 80491c5:	e9 47 ff ff ff       	jmp    8049111 <main+0x81>
 80491ca:	c7 05 60 a1 04 08 01 	movl   $0x1,0x804a160
 80491d1:	00 00 00 
 80491d4:	e9 38 ff ff ff       	jmp    8049111 <main+0x81>
 80491d9:	a1 84 a1 04 08       	mov    0x804a184,%eax
 80491de:	89 04 24             	mov    %eax,(%esp)
 80491e1:	e8 fe f5 ff ff       	call   80487e4 <__strdup@plt>
 80491e6:	a3 b0 a1 04 08       	mov    %eax,0x804a1b0
 80491eb:	89 44 24 04          	mov    %eax,0x4(%esp)
 80491ef:	c7 04 24 a8 99 04 08 	movl   $0x80499a8,(%esp)
 80491f6:	e8 49 f5 ff ff       	call   8048744 <printf@plt>
 80491fb:	a1 b0 a1 04 08       	mov    0x804a1b0,%eax
 8049200:	89 04 24             	mov    %eax,(%esp)
 8049203:	e8 88 01 00 00       	call   8049390 <gencookie>
 8049208:	a3 ac a1 04 08       	mov    %eax,0x804a1ac
 804920d:	89 44 24 04          	mov    %eax,0x4(%esp)
 8049211:	c7 04 24 b2 99 04 08 	movl   $0x80499b2,(%esp)
 8049218:	e8 27 f5 ff ff       	call   8048744 <printf@plt>
 804921d:	e9 ef fe ff ff       	jmp    8049111 <main+0x81>
 8049222:	c7 05 a8 a1 04 08 01 	movl   $0x1,0x804a1a8
 8049229:	00 00 00 
 804922c:	e9 e0 fe ff ff       	jmp    8049111 <main+0x81>
 8049231:	a1 b0 a1 04 08       	mov    0x804a1b0,%eax
 8049236:	85 c0                	test   %eax,%eax
 8049238:	0f 84 d2 00 00 00    	je     8049310 <main+0x280>
 804923e:	a1 ac a1 04 08       	mov    0x804a1ac,%eax
 8049243:	89 04 24             	mov    %eax,(%esp)
 8049246:	e8 09 f5 ff ff       	call   8048754 <srandom@plt>
 804924b:	e8 14 f4 ff ff       	call   8048664 <random@plt>
 8049250:	25 f8 0f 00 00       	and    $0xff8,%eax
 8049255:	89 45 e4             	mov    %eax,-0x1c(%ebp)
 8049258:	c7 44 24 04 04 00 00 	movl   $0x4,0x4(%esp)
 804925f:	00 
 8049260:	8b 45 ec             	mov    -0x14(%ebp),%eax
 8049263:	89 04 24             	mov    %eax,(%esp)
 8049266:	e8 29 f4 ff ff       	call   8048694 <calloc@plt>
 804926b:	89 c7                	mov    %eax,%edi
 804926d:	8b 45 ec             	mov    -0x14(%ebp),%eax
 8049270:	83 e8 02             	sub    $0x2,%eax
 8049273:	85 c0                	test   %eax,%eax
 8049275:	7e 1e                	jle    8049295 <main+0x205>
 8049277:	8b 45 ec             	mov    -0x14(%ebp),%eax
 804927a:	bb 01 00 00 00       	mov    $0x1,%ebx
 804927f:	8d 70 ff             	lea    -0x1(%eax),%esi
 8049282:	e8 dd f3 ff ff       	call   8048664 <random@plt>
 8049287:	83 e0 38             	and    $0x38,%eax
 804928a:	89 44 9f fc          	mov    %eax,-0x4(%edi,%ebx,4)
 804928e:	83 c3 01             	add    $0x1,%ebx
 8049291:	39 f3                	cmp    %esi,%ebx
 8049293:	75 ed                	jne    8049282 <main+0x1f2>
 8049295:	83 7d ec 01          	cmpl   $0x1,-0x14(%ebp)
 8049299:	7e 49                	jle    80492e4 <main+0x254>
 804929b:	8b 45 ec             	mov    -0x14(%ebp),%eax
 804929e:	c1 e0 02             	shl    $0x2,%eax
 80492a1:	c7 44 07 f8 38 00 00 	movl   $0x38,-0x8(%edi,%eax,1)
 80492a8:	00 
 80492a9:	c7 44 07 fc 00 00 00 	movl   $0x0,-0x4(%edi,%eax,1)
 80492b0:	00 
 80492b1:	a1 68 a1 04 08       	mov    0x804a168,%eax
 80492b6:	89 04 24             	mov    %eax,(%esp)
 80492b9:	e8 66 f4 ff ff       	call   8048724 <alarm@plt>
 80492be:	31 db                	xor    %ebx,%ebx
 80492c0:	8b 55 e4             	mov    -0x1c(%ebp),%edx
 80492c3:	8b 45 e8             	mov    -0x18(%ebp),%eax
 80492c6:	03 14 9f             	add    (%edi,%ebx,4),%edx
 80492c9:	83 c3 01             	add    $0x1,%ebx
 80492cc:	e8 1f fd ff ff       	call   8048ff0 <launch>
 80492d1:	3b 5d ec             	cmp    -0x14(%ebp),%ebx
 80492d4:	7c ea                	jl     80492c0 <main+0x230>
 80492d6:	83 c4 18             	add    $0x18,%esp
 80492d9:	31 c0                	xor    %eax,%eax
 80492db:	59                   	pop    %ecx
 80492dc:	5b                   	pop    %ebx
 80492dd:	5e                   	pop    %esi
 80492de:	5f                   	pop    %edi
 80492df:	5d                   	pop    %ebp
 80492e0:	8d 61 fc             	lea    -0x4(%ecx),%esp
 80492e3:	c3                   	ret    
 80492e4:	8b 45 ec             	mov    -0x14(%ebp),%eax
 80492e7:	c7 44 87 fc 00 00 00 	movl   $0x0,-0x4(%edi,%eax,4)
 80492ee:	00 
 80492ef:	a1 68 a1 04 08       	mov    0x804a168,%eax
 80492f4:	89 04 24             	mov    %eax,(%esp)
 80492f7:	e8 28 f4 ff ff       	call   8048724 <alarm@plt>
 80492fc:	83 7d ec 01          	cmpl   $0x1,-0x14(%ebp)
 8049300:	74 bc                	je     80492be <main+0x22e>
 8049302:	83 c4 18             	add    $0x18,%esp
 8049305:	31 c0                	xor    %eax,%eax
 8049307:	59                   	pop    %ecx
 8049308:	5b                   	pop    %ebx
 8049309:	5e                   	pop    %esi
 804930a:	5f                   	pop    %edi
 804930b:	5d                   	pop    %ebp
 804930c:	8d 61 fc             	lea    -0x4(%ecx),%esp
 804930f:	c3                   	ret    
 8049310:	c7 04 24 ec 97 04 08 	movl   $0x80497ec,(%esp)
 8049317:	e8 98 f4 ff ff       	call   80487b4 <puts@plt>
 804931c:	8b 03                	mov    (%ebx),%eax
 804931e:	e8 fd f6 ff ff       	call   8048a20 <usage>
 8049323:	e9 16 ff ff ff       	jmp    804923e <main+0x1ae>
 8049328:	90                   	nop
 8049329:	90                   	nop
 804932a:	90                   	nop
 804932b:	90                   	nop
 804932c:	90                   	nop
 804932d:	90                   	nop
 804932e:	90                   	nop
 804932f:	90                   	nop

08049330 <hash>:
 8049330:	55                   	push   %ebp
 8049331:	31 c0                	xor    %eax,%eax
 8049333:	89 e5                	mov    %esp,%ebp
 8049335:	8b 4d 08             	mov    0x8(%ebp),%ecx
 8049338:	0f b6 11             	movzbl (%ecx),%edx
 804933b:	84 d2                	test   %dl,%dl
 804933d:	74 15                	je     8049354 <hash+0x24>
 804933f:	90                   	nop
 8049340:	6b c0 67             	imul   $0x67,%eax,%eax
 8049343:	0f be d2             	movsbl %dl,%edx
 8049346:	8d 04 02             	lea    (%edx,%eax,1),%eax
 8049349:	0f b6 51 01          	movzbl 0x1(%ecx),%edx
 804934d:	83 c1 01             	add    $0x1,%ecx
 8049350:	84 d2                	test   %dl,%dl
 8049352:	75 ec                	jne    8049340 <hash+0x10>
 8049354:	5d                   	pop    %ebp
 8049355:	c3                   	ret    
 8049356:	8d 76 00             	lea    0x0(%esi),%esi
 8049359:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08049360 <check>:
 8049360:	55                   	push   %ebp
 8049361:	89 e5                	mov    %esp,%ebp
 8049363:	8b 55 08             	mov    0x8(%ebp),%edx
 8049366:	89 d0                	mov    %edx,%eax
 8049368:	c1 e8 1c             	shr    $0x1c,%eax
 804936b:	85 c0                	test   %eax,%eax
 804936d:	74 19                	je     8049388 <check+0x28>
 804936f:	31 c9                	xor    %ecx,%ecx
 8049371:	89 d0                	mov    %edx,%eax
 8049373:	d3 e8                	shr    %cl,%eax
 8049375:	3c 0a                	cmp    $0xa,%al
 8049377:	74 0f                	je     8049388 <check+0x28>
 8049379:	83 c1 08             	add    $0x8,%ecx
 804937c:	83 f9 20             	cmp    $0x20,%ecx
 804937f:	75 f0                	jne    8049371 <check+0x11>
 8049381:	5d                   	pop    %ebp
 8049382:	b8 01 00 00 00       	mov    $0x1,%eax
 8049387:	c3                   	ret    
 8049388:	5d                   	pop    %ebp
 8049389:	31 c0                	xor    %eax,%eax
 804938b:	c3                   	ret    
 804938c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

08049390 <gencookie>:
 8049390:	55                   	push   %ebp
 8049391:	89 e5                	mov    %esp,%ebp
 8049393:	53                   	push   %ebx
 8049394:	83 ec 04             	sub    $0x4,%esp
 8049397:	8b 45 08             	mov    0x8(%ebp),%eax
 804939a:	89 04 24             	mov    %eax,(%esp)
 804939d:	e8 8e ff ff ff       	call   8049330 <hash>
 80493a2:	89 04 24             	mov    %eax,(%esp)
 80493a5:	e8 aa f2 ff ff       	call   8048654 <srand@plt>
 80493aa:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 80493b0:	e8 0f f4 ff ff       	call   80487c4 <rand@plt>
 80493b5:	89 c3                	mov    %eax,%ebx
 80493b7:	89 04 24             	mov    %eax,(%esp)
 80493ba:	e8 a1 ff ff ff       	call   8049360 <check>
 80493bf:	85 c0                	test   %eax,%eax
 80493c1:	74 ed                	je     80493b0 <gencookie+0x20>
 80493c3:	89 d8                	mov    %ebx,%eax
 80493c5:	83 c4 04             	add    $0x4,%esp
 80493c8:	5b                   	pop    %ebx
 80493c9:	5d                   	pop    %ebp
 80493ca:	c3                   	ret    
 80493cb:	90                   	nop
 80493cc:	90                   	nop
 80493cd:	90                   	nop
 80493ce:	90                   	nop
 80493cf:	90                   	nop

080493d0 <__libc_csu_fini>:
 80493d0:	55                   	push   %ebp
 80493d1:	89 e5                	mov    %esp,%ebp
 80493d3:	5d                   	pop    %ebp
 80493d4:	c3                   	ret    
 80493d5:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 80493d9:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

080493e0 <__libc_csu_init>:
 80493e0:	55                   	push   %ebp
 80493e1:	89 e5                	mov    %esp,%ebp
 80493e3:	57                   	push   %edi
 80493e4:	56                   	push   %esi
 80493e5:	53                   	push   %ebx
 80493e6:	e8 5e 00 00 00       	call   8049449 <__i686.get_pc_thunk.bx>
 80493eb:	81 c3 f5 0c 00 00    	add    $0xcf5,%ebx
 80493f1:	83 ec 1c             	sub    $0x1c,%esp
 80493f4:	e8 23 f2 ff ff       	call   804861c <_init>
 80493f9:	8d 83 20 ff ff ff    	lea    -0xe0(%ebx),%eax
 80493ff:	89 45 f0             	mov    %eax,-0x10(%ebp)
 8049402:	8d 83 20 ff ff ff    	lea    -0xe0(%ebx),%eax
 8049408:	29 45 f0             	sub    %eax,-0x10(%ebp)
 804940b:	c1 7d f0 02          	sarl   $0x2,-0x10(%ebp)
 804940f:	8b 55 f0             	mov    -0x10(%ebp),%edx
 8049412:	85 d2                	test   %edx,%edx
 8049414:	74 2b                	je     8049441 <__libc_csu_init+0x61>
 8049416:	31 ff                	xor    %edi,%edi
 8049418:	89 c6                	mov    %eax,%esi
 804941a:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 8049420:	8b 45 10             	mov    0x10(%ebp),%eax
 8049423:	83 c7 01             	add    $0x1,%edi
 8049426:	89 44 24 08          	mov    %eax,0x8(%esp)
 804942a:	8b 45 0c             	mov    0xc(%ebp),%eax
 804942d:	89 44 24 04          	mov    %eax,0x4(%esp)
 8049431:	8b 45 08             	mov    0x8(%ebp),%eax
 8049434:	89 04 24             	mov    %eax,(%esp)
 8049437:	ff 16                	call   *(%esi)
 8049439:	83 c6 04             	add    $0x4,%esi
 804943c:	39 7d f0             	cmp    %edi,-0x10(%ebp)
 804943f:	75 df                	jne    8049420 <__libc_csu_init+0x40>
 8049441:	83 c4 1c             	add    $0x1c,%esp
 8049444:	5b                   	pop    %ebx
 8049445:	5e                   	pop    %esi
 8049446:	5f                   	pop    %edi
 8049447:	5d                   	pop    %ebp
 8049448:	c3                   	ret    

08049449 <__i686.get_pc_thunk.bx>:
 8049449:	8b 1c 24             	mov    (%esp),%ebx
 804944c:	c3                   	ret    
 804944d:	90                   	nop
 804944e:	90                   	nop
 804944f:	90                   	nop

08049450 <__do_global_ctors_aux>:
 8049450:	55                   	push   %ebp
 8049451:	89 e5                	mov    %esp,%ebp
 8049453:	53                   	push   %ebx
 8049454:	bb 00 a0 04 08       	mov    $0x804a000,%ebx
 8049459:	83 ec 04             	sub    $0x4,%esp
 804945c:	a1 00 a0 04 08       	mov    0x804a000,%eax
 8049461:	83 f8 ff             	cmp    $0xffffffff,%eax
 8049464:	74 0c                	je     8049472 <__do_global_ctors_aux+0x22>
 8049466:	83 eb 04             	sub    $0x4,%ebx
 8049469:	ff d0                	call   *%eax
 804946b:	8b 03                	mov    (%ebx),%eax
 804946d:	83 f8 ff             	cmp    $0xffffffff,%eax
 8049470:	75 f4                	jne    8049466 <__do_global_ctors_aux+0x16>
 8049472:	83 c4 04             	add    $0x4,%esp
 8049475:	5b                   	pop    %ebx
 8049476:	5d                   	pop    %ebp
 8049477:	c3                   	ret    

Disassembly of section .fini:

08049478 <_fini>:
 8049478:	55                   	push   %ebp
 8049479:	89 e5                	mov    %esp,%ebp
 804947b:	53                   	push   %ebx
 804947c:	83 ec 04             	sub    $0x4,%esp
 804947f:	e8 00 00 00 00       	call   8049484 <_fini+0xc>
 8049484:	5b                   	pop    %ebx
 8049485:	81 c3 5c 0c 00 00    	add    $0xc5c,%ebx
 804948b:	e8 d0 f3 ff ff       	call   8048860 <__do_global_dtors_aux>
 8049490:	59                   	pop    %ecx
 8049491:	5b                   	pop    %ebx
 8049492:	c9                   	leave  
 8049493:	c3                   	ret    
