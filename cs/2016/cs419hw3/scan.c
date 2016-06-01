/*
 * scan.c
 *	Displays alerts for three types of attacks:
 *		1) ARP spoofing
 *		2) TCP SYN port scan
 *		3) TCP SYN flood
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pcap.h>
#include <netinet/ip.h>
#include <netinet/tcp.h>
#include <time.h>
#include <arpa/inet.h>

int arp_scan(char* file){
	printf("Scanning %s for ARP spoofing attack:\n", file);
	int result = 0;
	
	char errbuf[PCAP_ERRBUF_SIZE];
	pcap_t *pcap = pcap_open_offline(file, errbuf);
	const unsigned char* packet = NULL;
	struct pcap_pkthdr header;
	int packetnum = 0;
	while((packet = pcap_next(pcap, &header)) != NULL){
		struct ip* ipheader = NULL;
		ipheader = (struct ip*)(packet+14);
		struct tcphdr* tcpheader = NULL;
		tcpheader = (struct tcphdr*)(packet+14+ipheader->ip_hl*4);
		printf("Packet %d:\n", packetnum);
		packetnum++;
	}

	return result;
}

int port_scan(char* file){
	printf("Scanning %s for TCP SYN port scan attack:\n", file);
	int result = 0;

	char errbuf[PCAP_ERRBUF_SIZE];
	pcap_t *pcap  = pcap_open_offline(file, errbuf);
	const unsigned char* packet = NULL;
	struct pcap_pkthdr header;
	int packetnum = 0;
	int portnums[100];
	int paknums[100];
	portnums[0]=0;
	int scannum = 0;
	while((packet = pcap_next(pcap, &header))!=NULL){
		struct ip* ipheader = NULL;
		ipheader = (struct ip*)(packet+14);
		struct tcphdr* tcpheader = NULL;
		tcpheader = (struct tcphdr*)(packet+14+ipheader->ip_hl*4);
		
		int port = tcpheader->dest;
		int contained=0;
		int i; for(i=0;i<100;i++) if(portnums[i]==port) contained = 1;
		if(!contained){
			portnums[scannum] = port;
			paknums[scannum] = packetnum;
			scannum++;
		}
		if(scannum=100){
			scannum = 0;
			printf("Port scan detected!\n");
			printf("Source:%s Destination: %s\nPackets:",inet_ntoa(ipheader->ip_src), inet_ntoa(ipheader->ip_dst));
			int j; for(j=0; j<100;j++) {printf("%d, ",paknums[j]); portnums[j]=0;}
			for(j=0; j<100; j++) paknums[j]=0;
		}	

//		printf("Packet %d:\n", packetnum);
		packetnum++;

	}

	return result;
}

int flood_scan(char* file){
	printf("Scanning %s for TCP SYN flood attack:\n", file);
	int result = 0;

	char errbuf[PCAP_ERRBUF_SIZE];
	pcap_t *pcap = pcap_open_offline(file, errbuf);
	const unsigned char* packet = NULL;
	struct pcap_pkthdr header;
	int packetnum = 0;
	int time = 0;
	int oldtime = 0;
	int packetcount = 0;
	int packnum[100];
	while((packet = pcap_next(pcap, &header))!=NULL){
		struct ip* ipheader = NULL; 
		ipheader = (struct ip*)(packet+14);
		struct tcphdr* tcpheader = NULL;
		tcpheader = (struct tcphdr*)(packet+14+ipheader->ip_hl*4);
		oldtime=time;
		time = header.ts.tv_sec;
		if(oldtime!=time){
			//printf("timeshift: %d packet: %d count:%d\n",time,packetnum,packetcount); 
			packetcount = 0;
		}else{
			if(tcpheader->syn==1){
				packnum[packetcount]=packetnum;
				packetcount++;
				//printf("syn\t");
			}
			//else printf("non-syn\n");
		}
		packetnum++;

		if(packetcount>100){
			result++;
			printf("\nMore than 100 SYN packets in 1s detected! Offending packet numbers:\n");
			//char* src = malloc(sizeof(char)*18);
			//char* dst = malloc(sizeof(char)*18);
			//struct in_addr isrc = ipheader->ip_src;
			//struct in_addr idst = ipheader->ip_dst;
			
			//char* src = inet_ntoa(isrc);
			//printf("%s",inet_ntoa(isrc));

			printf("Source:\t%s\tDestination:\t%s\n",inet_ntoa(ipheader->ip_src),inet_ntoa(ipheader->ip_dst));
			int i; for( i = 0; i< 100; i++)
				printf("%d, ", packnum[i]);
			packetcount = 0;
		}
	}

	return result;
}

int main(int argc, char** argv){

	int arp = 0, port = 0, flood = 0;

	if(argc!=2){printf("$> scan <capture.pcap>"); return 0;}

	char* filename = malloc(sizeof(char)*strlen(argv[1]));

	strcpy(filename, argv[1]);

	arp = arp_scan(filename);
	port = port_scan(filename);
	flood = flood_scan(filename);

	printf("Result of scans:\n\tARP: %d\n\tPort: %d\n\tFlood: %d\n", arp, port, flood);

	return 0;
}
