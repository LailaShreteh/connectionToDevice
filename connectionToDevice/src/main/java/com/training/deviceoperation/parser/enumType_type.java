package com.training.deviceoperation.parser;

public enum enumType_type {
	other(1), // none of the following
	regular1822(2), hdh1822(3), ddnX25(4), rfc877x25(5), 
	ethernetCsmacd(6), iso88023Csmacd(7), iso88024TokenBus(8),
	iso88025TokenRing(9), iso88026Man(10),starLan(11),
	proteon10Mbit(12), proteon80Mbit(13), hyperchannel(14),
	fddi(15), lapb(16), sdlc(17), 
	ds1(18), // DS1-MIB
	e1(19), // Obsolete see DS1-MIB
	basicISDN(20), primaryISDN(21),
	propPointToPointSerial(22), // proprietary serial	
	ppp(23), softwareLoopback(24), 
	eon(25), // CLNP over IP
	ethernet3Mbit(26), 
	nsip(27), // XNS over IP
	slip(28), // generic SLIP
	ultra(29), // ULTRA technologies
	ds3(30), // DS3-MIB
	sip(31), // SMDS, coffee
	frameRelay(32), // DTE only.
	rs232(33),
	para(34), // parallel-port
	arcnet(35), // arcnet
	arcnetPlus(36), // arcnet plus
	atm(37), // ATM cells
	miox25(38), 
	sonet(39), // SONET or SDH
	x25ple(40), iso88022llc(41), localTalk(42), smdsDxi(43), 
	frameRelayService(44), // FRNETSERV-MIB
	v35(45), hssi(46), hippi(47),
	modem(48), // Generic modem
	aal5(49), // AAL5 over ATM
	sonetPath(50), sonetVT(51),
	smdsIcip(52), // SMDS InterCarrier Interface
	propVirtual(53), // proprietary virtual/internal
	propMultiplexor(54), // proprietary multiplexing
	ieee80212(55), // 100BaseVG
	fibreChannel(56), // Fibre Channel
	hippiInterface(57), // HIPPI interfaces
	frameRelayInterconnect(58), // Obsolete use either
								// frameRelay(32) or
								// frameRelayService(44).
	aflane8023(59), // ATM Emulated LAN for 802.3
	aflane8025(60), // ATM Emulated LAN for 802.5
	cctEmul(61), // ATM Emulated circuit
	fastEther(62), // Fast Ethernet (100BaseT)
	isdn(63), // ISDN and X.25
	v11(64), // CCITT V.11/X.21
	v36(65), // CCITT V.36
	g703at64k(66), // CCITT G703 at 64Kbps
	g703at2mb(67), // Obsolete see DS1-MIB
	qllc(68), // SNA QLLC
	fastEtherFX(69), // Fast Ethernet (100BaseFX)
	channel(70), // channel
	ieee80211(71), // radio spread spectrum
	ibm370parChan(72), // IBM System 360/370 OEMI Channel
	escon(73), // IBM Enterprise Systems Connection
	dlsw(74), // Data Link Switching
	isdns(75), // ISDN S/T interface
	isdnu(76), // ISDN U interface
	lapd(77), // Link Access Protocol D
	ipSwitch(78), // IP Switching Objects
	rsrb(79), // Remote Source Route Bridging
	atmLogical(80), // ATM Logical Port
	ds0(81), // Digital Signal Level 0
	ds0Bundle(82), // group of ds0s on the same ds1
	bsc(83), // Bisynchronous Protocol
	async(84), // Asynchronous Protocol
	cnr(85), // Combat Net Radio
	iso88025Dtr(86), // ISO 802.5r DTR
	eplrs(87), // Ext Pos Loc Report Sys
	arap(88), // Appletalk Remote Access Protocol
	propCnls(89), // Proprietary Connectionless Protocol
	hostPad(90), // CCITT-ITU X.29 PAD Protocol
	termPad(91), // CCITT-ITU X.3 PAD Facility
	frameRelayMPI(92), // Multiproto Interconnect over FR
	x213(93), // CCITT-ITU X213
	adsl(94), // Asymmetric Digital Subscriber Loop
	radsl(95), // Rate-Adapt. Digital Subscriber Loop
	sdsl(96), // Symmetric Digital Subscriber Loop
	vdsl(97), // Very H-Speed Digital Subscrib. Loop
	iso88025CRFPInt(98), // ISO 802.5 CRFP
	myrinet(99), // Myricom Myrinet
	voiceEM(100), // voice recEive and transMit
	voiceFXO(101), // voice Foreign Exchange Office
	voiceFXS(102), // voice Foreign Exchange Station
	voiceEncap(103), // voice encapsulation
	voiceOverIp(104), // voice over IP encapsulation
	atmDxi(105), // ATM DXI
	atmFuni(106), // ATM FUNI
	atmIma(107), // ATM IMA
	pppMultilinkBundle(108), // PPP Multilink Bundle
	ipOverCdlc(109), // IBM ipOverCdlc
	ipOverClaw(110), // IBM Common Link Access to Workstn
	stackToStack(111), // IBM stackToStack
	virtualIpAddress(112), // IBM VIPA
	mpc(113), // IBM multi-protocol channel support
	ipOverAtm(114), // IBM ipOverAtm
	iso88025Fiber(115), // ISO 802.5j Fiber Token Ring
	tdlc(116), // IBM twinaxial data link control
	gigabitEthernet(117), // Gigabit Ethernet
	hdlc(118), // HDLC
	lapf(119), // LAP F
	v37(120), // V.37
	x25mlp(121), // Multi-Link Protocol
	x25huntGroup(122), // X25 Hunt Group
	trasnpHdlc(123), // Transp HDLC
	interleave(124), // Interleave channel
	fast(125), // Fast channel
	ip(126), // IP (for APPN HPR in IP networks)
	docsCableMaclayer(127), // CATV Mac Layer
	docsCableDownstream(128), // CATV Downstream interface
	docsCableUpstream(129), // CATV Upstream interface
	a12MppSwitch(130), // Avalon Parallel Processor
	tunnel(131), // Encapsulation interface
	coffee(132), // coffee pot
	ces(133), // Circuit Emulation Service
	atmSubInterface(134), // ATM Sub Interface
	l2vlan(135), // Layer 2 Virtual LAN using 802.1Q
	l3ipvlan(136), // Layer 3 Virtual LAN using IP
	l3ipxvlan(137), // Layer 3 Virtual LAN using IPX
	digitalPowerline(138), // IP over Power Lines
	mediaMailOverIp(139), // Multimedia Mail over IP
	dtm(140), // Dynamic syncronous Transfer Mode
	dcn(141), // Data Communications Network
	ipForward(142), // IP Forwarding Interface
	msdsl(143), // Multi-rate Symmetric DSL
	ieee1394(144), // IEEE1394 High Performance Serial Bus
	if_gsn(145), // HIPPI-6400
	dvbRccMacLayer(146), // DVB-RCC MAC Layer
	dvbRccDownstream(147), // DVB-RCC Downstream Channel
	dvbRccUpstream(148), // DVB-RCC Upstream Channel
	atmVirtual(149), // ATM Virtual Interface
	mplsTunnel(150), // MPLS Tunnel Virtual Interface
	srp(151), // Spatial Reuse Protocol
	voiceOverAtm(152), // Voice Over ATM
	voiceOverFrameRelay(153), // Voice Over Frame Relay
	idsl(154), // Digital Subscriber Loop over ISDN
	compositeLink(155), // Avici Composite Link Interface
	ss7SigLink(156), // SS7 Signaling Link
	propWirelessP2P(157), // Prop. P2P wireless interface
	frForward(158), // Frame Forward Interface
	rfc1483(159), // Multiprotocol over ATM AAL5
	usb(160), // USB Interface
	ieee8023adLag(161), // IEEE 802.3ad Link Aggregate
	bgppolicyaccounting(162), // BGP Policy Accounting
	frf16MfrBundle(163), // FRF .16 Multilink Frame Relay
	h323Gatekeeper(164), // H323 Gatekeeper
	h323Proxy(165), // H323 Voice and Video Proxy
	mpls(166), // MPLS
	mfSigLink(167), // Multi-frequency signaling link
	hdsl2(168), // High Bit-Rate DSL - 2nd generation
	shdsl(169), // Multirate HDSL2
	ds1FDL(170), // Facility Data Link 4Kbps on a DS1
	pos(171), // Packet over SONET/SDH Interface
	dvbAsiIn(172), // DVB-ASI Input
	dvbAsiOut(173), // DVB-ASI Output
	plc(174), // Power Line Communtications
	nfas(175), // Non Facility Associated Signaling
	tr008(176), // TR008
	gr303RDT(177), // Remote Digital Terminal
	gr303IDT(178), // Integrated Digital Terminal
	isup(179), // ISUP
	propDocsWirelessMaclayer(180), // prop/Maclayer
	propDocsWirelessDownstream(181), // prop/Downstream
	propDocsWirelessUpstream(182), // prop/Upstream
	hiperlan2(183), // HIPERLAN Type 2 Radio Interface
	propBWAp2Mp(184), // PropBroadbandWirelessAccesspt2multipt
	sonetOverheadChannel(185), // SONET Overhead Channel
	digitalWrapperOverheadChannel(186), // Digital Wrapper
	aal2(187), // ATM adaptation layer 2
	radioMAC(188), // MAC layer over radio links
	atmRadio(189), // ATM over radio links
	imt(190), // Inter Machine Trunks
	mvl(191), // Multiple Virtual Lines DSL
	reachDSL(192), // Long Reach DSL
	frDlciEndPt(193), // Frame Relay DLCI End Point
	atmVciEndPt(194), // ATM VCI End Point
	opticalChannel(195), // Optical Channel
	opticalTransport(196), // Optical Transport
	propAtm(197), // Proprietary ATM;
	voiceOverCable(198),infiniband(199),teLink(200),
	q2931(201),virtualTg(202),sipTg(203),sipSig(204),
	docsCableUpstreamChannel(205),econet(206),pon155(207),
	pon622(208),bridge(209),linegroup(210),voiceEMFGD(211),
    voiceFGDEANA(212),voiceDID(213),mpegTransport(214),
    sixToFour(215),gtp(216),pdnEtherLoop1(217),
    pdnEtherLoop2(218),opticalChannelGroup(219),
    homepna(220),gfp(221),fcipLink(224), rpr(225),
    actelisMetaLOOP(223), ciscoISLvlan(222),
    qam(226), lmp(227), cblVectaStar(228), 
    docsCableMCmtsDownstream(229), adsl2(230);
	
	private final int value;

	enumType_type(int value) {
		this.value = value;

	}

	public int getValue() {
		return value;
	}

}
