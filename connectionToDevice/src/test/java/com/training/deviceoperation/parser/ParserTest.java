package com.training.deviceoperation.parser;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;

/**
 * 
 * @author user
 *
 */

public class ParserTest {

	private static Parser pars;
	/* static data for router */
	private String cmdEPE = "%% GigabitEthernet0/0/0 is up, line protocol is up     "
			+ "Hardware is 4XGE-BUILT-IN, address is 503d.e596.7400 (bia 503d.e596.7400)    Internet address is 192.168.50.200/16    "
			+ "MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    "
			+ "Encapsulation ARPA, loopback not set    Keepalive not supported     "
			+ "Full Duplex, 1000Mbps, link type is auto, media type is T    output flow-control is on, input flow-control is on    "
			+ "ARP type: ARPA, ARP Timeout 04:00:00    Last input 00:00:00, output 00:01:10, output hang never    "
			+ "Last clearing of \"show interface\" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); "
			+ "Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    5 minute input rate 60000 bits/sec, "
			+ "49 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       "
			+ "3841824 packets input, 567118332 bytes, 0 no buffer       Received 1105481 broadcasts (0 IP multicasts)       "
			+ "0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       "
			+ "0 watchdog, 2712211 multicast, 0 pause input       65231 packets output, 5471165 bytes, 0 underruns       "
			+ "0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       "
			+ "0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       "
			+ "0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0/0/1 is down, line protocol is down     "
			+ "Hardware is 4XGE-BUILT-IN, address is 0000.0000.0002 (bia 503d.e596.7401)    MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        "
			+ "reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     "
			+ "Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    "
			+ "ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of \"show interface\" counters never    "
			+ "Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    "
			+ "5 minute input rate 0 bits/sec, 0 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       "
			+ "0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        "
			+ "0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       "
			+ "0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       "
			+ "0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, "
			+ "0 output buffers swapped out  %% GigabitEthernet0/0/2 is down, line protocol is down     "
			+ "Hardware is 4XGE-BUILT-IN, address is 0000.0000.0003 (bia 503d.e596.7402)    Internet address is 20.0.0.1/24    "
			+ "MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    "
			+ "Encapsulation ARPA, loopback not set    Keepalive not supported     "
			+ "Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    "
			+ "ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of \"show interface\" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    30 second input rate 0 bits/sec, 0 packets/sec    30 second output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0/0/3 is down, line protocol is down     Hardware is 4XGE-BUILT-IN, address is 0000.0000.0004 (bia 503d.e596.7403)    Internet address is 30.0.0.1/24    MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of \"show interface\" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    30 second input rate 0 bits/sec, 0 packets/sec    30 second output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0 is down, line protocol is down     Hardware is RP management port, address is 503d.e596.7440 (bia 503d.e596.7440)    MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive set (10 sec)    Half Duplex, 10Mbps, link type is auto, media type is RJ45    output flow-control is unsupported, input flow-control is unsupported    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of \"show interface\" counters never    Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    5 minute input rate 0 bits/sec, 0 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 0 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred"
			+ "0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out";
	private String cmdACL = "%% Standard IP access list 1 10 permit any %% Standard IP access list 2 10 permit 127.0.0.1";
	private String cmdClassMap =" %% Class Map match-any class-default (id 0) Match any %% Class Map match-all class1 (id 1)     Description: first class Match none";
	private String cmdPolicy = "Policy Map policy1 Class class1 Policy Map policy2";
	private String cmdInterACL = "interface GigabitEthernet0/0/0 ip access-group 2 out interface"
			+ "GigabitEthernet0/0/1 interface GigabitEthernet0/0/2 interface"
			+ "GigabitEthernet0/0/3 interface GigabitEthernet0";
	private String cmdInterPolicy = "policy-map policy1 policy-map policy2 interface GigabitEthernet0/0/0"
			+ "service-policy input policy1 service-policy output policy1 interface"
			+ "GigabitEthernet0/0/1 service-policy output policy1 interface"
			+ "GigabitEthernet0/0/2 interface GigabitEthernet0/0/3 interface" + "GigabitEthernet0";
	private List<EthernetProtocolEndpoint> ePEList;
	private List<ACL> accessList;
	private List<ClassMap> classMapList;
	private List<PolicyMap> policyMapList;
	private List<Transaction> transactionList;
	private List<Interface_ACL> interface_ACLList;
	private List<Interface_Policy> interface_PolicyList;

	@Before
	public void setup() {

	}
	@BeforeClass
	public static void beforeCLass ()
	{
		pars = new CLIParser();

	}
	@Test
	public void testParsEthernetPE() {
		ePEList = pars.parsEthernetPE(cmdEPE);
		/*for(int i=0;i<ePEList.size();i++)
			System.out.println(ePEList.get(i));*/
		assertNotNull(ePEList);
	}
	
	@Test
	public void testParsACL() {
		accessList = pars.parsACL(cmdACL);
		/*for(int i=0;i<accessList.size();i++)
			System.out.println(accessList.get(i));*/
		assertNotNull(accessList);
	}
	@Test
	public void testParsClassMap() {
		classMapList = pars.parsClassMap(cmdClassMap);
		/*for(int i=0;i<classMapList.size();i++)
			System.out.println(classMapList.get(i));*/
		assertNotNull(classMapList);
	}
	@Test
	public void testParsPolicyMap() {
		policyMapList = pars.parsPolicyMap(cmdPolicy);
		/*for(int i=0;i<policyMapList.size();i++)
			System.out.println(policyMapList.get(i));*/
		assertNotNull(policyMapList);
	}
	@Test
	public void testParsTransaction() {
		transactionList = pars.parsTransaction(cmdPolicy);
		assertNotNull(transactionList);
	}
	@Test
	public void testParsInterface_Policy() {
		interface_PolicyList = pars.parsInterface_Policy(cmdInterPolicy);
		assertNotNull(interface_PolicyList);

	}
	@Test
	public void testParsInterface_ACL() {
		interface_ACLList = pars.parsInterface_ACL(cmdInterACL);
		assertNotNull(interface_ACLList);

	}
	
	@AfterClass
	public static void teardown() throws IOException {
		//pars = null;
	}

}
