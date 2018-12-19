package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmPictureMeta2 {

	private int PLEN00;           // Picture length of slot 0
	private int PLEN01;           // Picture length of slot 1
	private int PLEN02;           // Picture length of slot 2
	private int PLEN03;           // Picture length of slot 3
	private int PLEN04;           // Picture length of slot 4
	private int PLEN05;           // Picture length of slot 5
	private int PLEN06;           // Picture length of slot 6
	private int PLEN07;           // Picture length of slot 7
	private int PLEN08;           // Picture length of slot 8
	private int PLEN09;           // Picture length of slot 9
	private int PLEN10;           // Picture length of slot 10
	private int PLEN11;           // Picture length of slot 11
	private int PLEN12;           // Picture length of slot 12
	private int PLEN13;           // Picture length of slot 13
	private int PLEN14;           // Picture length of slot 14
	private int PLEN15;           // Picture length of slot 15
	private int PLEN16;           // Picture length of slot 16
	private int PLEN17;           // Picture length of slot 17
	private int PLEN18;           // Picture length of slot 18
	private int PLEN19;           // Picture length of slot 19
	private int PLEN20;           // Picture length of slot 20
	private int PLEN21;           // Picture length of slot 21
	private int PLEN22;           // Picture length of slot 22
	private int PLEN23;           // Picture length of slot 23
	private int PLEN24;           // Picture length of slot 24
	private int PLEN25;           // Picture length of slot 25
	private int PLEN26;           // Picture length of slot 26
	private int PLEN27;           // Picture length of slot 27
	private int PLEN28;           // Picture length of slot 28
	private int PLEN29;           // Picture length of slot 29
	private long PTIM00;          // Picture time of slot 0
	private long PTIM01;          // Picture time of slot 1
	private long PTIM02;          // Picture time of slot 2
	private long PTIM03;          // Picture time of slot 3
	private long PTIM04;          // Picture time of slot 4
	private long PTIM05;          // Picture time of slot 5
	private long PTIM06;          // Picture time of slot 6
	private long PTIM07;          // Picture time of slot 7
	private long PTIM08;          // Picture time of slot 8
	private long PTIM09;          // Picture time of slot 9
	private long PTIM10;          // Picture time of slot 10
	private long PTIM11;          // Picture time of slot 11
	private long PTIM12;          // Picture time of slot 12
	private long PTIM13;          // Picture time of slot 13
	private long PTIM14;          // Picture time of slot 14
	private long PTIM15;          // Picture time of slot 15
	private long PTIM16;          // Picture time of slot 16
	private long PTIM17;          // Picture time of slot 17
	private long PTIM18;          // Picture time of slot 18
	private long PTIM19;          // Picture time of slot 19
	private long PTIM20;          // Picture time of slot 20
	private long PTIM21;          // Picture time of slot 21
	private long PTIM22;          // Picture time of slot 22
	private long PTIM23;          // Picture time of slot 23
	private long PTIM24;          // Picture time of slot 24
	private long PTIM25;          // Picture time of slot 25
	private long PTIM26;          // Picture time of slot 26
	private long PTIM27;          // Picture time of slot 27
	private long PTIM28;          // Picture time of slot 28
	private long PTIM29;          // Picture time of slot 29
	private byte[] PCRC00;        // Picture CRC32 of slot 0
	private byte[] PCRC01;        // Picture CRC32 of slot 1
	private byte[] PCRC02;        // Picture CRC32 of slot 2
	private byte[] PCRC03;        // Picture CRC32 of slot 3
	private byte[] PCRC04;        // Picture CRC32 of slot 4
	private byte[] PCRC05;        // Picture CRC32 of slot 5
	private byte[] PCRC06;        // Picture CRC32 of slot 6
	private byte[] PCRC07;        // Picture CRC32 of slot 7
	private byte[] PCRC08;        // Picture CRC32 of slot 8
	private byte[] PCRC09;        // Picture CRC32 of slot 9
	private byte[] PCRC10;        // Picture CRC32 of slot 10
	private byte[] PCRC11;        // Picture CRC32 of slot 11
	private byte[] PCRC12;        // Picture CRC32 of slot 12
	private byte[] PCRC13;        // Picture CRC32 of slot 13
	private byte[] PCRC14;        // Picture CRC32 of slot 14
	private byte[] PCRC15;        // Picture CRC32 of slot 15
	private byte[] PCRC16;        // Picture CRC32 of slot 16
	private byte[] PCRC17;        // Picture CRC32 of slot 17
	private byte[] PCRC18;        // Picture CRC32 of slot 18
	private byte[] PCRC19;        // Picture CRC32 of slot 19
	private byte[] PCRC20;        // Picture CRC32 of slot 20
	private byte[] PCRC21;        // Picture CRC32 of slot 21
	private byte[] PCRC22;        // Picture CRC32 of slot 22
	private byte[] PCRC23;        // Picture CRC32 of slot 23
	private byte[] PCRC24;        // Picture CRC32 of slot 24
	private byte[] PCRC25;        // Picture CRC32 of slot 25
	private byte[] PCRC26;        // Picture CRC32 of slot 26
	private byte[] PCRC27;        // Picture CRC32 of slot 27
	private byte[] PCRC28;        // Picture CRC32 of slot 28
	private byte[] PCRC29;        // Picture CRC32 of slot 29
	private int PCHU00;           // Picture total chunks of slot 0
	private int PCHU01;           // Picture total chunks of slot 1
	private int PCHU02;           // Picture total chunks of slot 2
	private int PCHU03;           // Picture total chunks of slot 3
	private int PCHU04;           // Picture total chunks of slot 4
	private int PCHU05;           // Picture total chunks of slot 5
	private int PCHU06;           // Picture total chunks of slot 6
	private int PCHU07;           // Picture total chunks of slot 7
	private int PCHU08;           // Picture total chunks of slot 8
	private int PCHU09;           // Picture total chunks of slot 9
	private int PCHU10;           // Picture total chunks of slot 10
	private int PCHU11;           // Picture total chunks of slot 11
	private int PCHU12;           // Picture total chunks of slot 12
	private int PCHU13;           // Picture total chunks of slot 13
	private int PCHU14;           // Picture total chunks of slot 14
	private int PCHU15;           // Picture total chunks of slot 15
	private int PCHU16;           // Picture total chunks of slot 16
	private int PCHU17;           // Picture total chunks of slot 17
	private int PCHU18;           // Picture total chunks of slot 18
	private int PCHU19;           // Picture total chunks of slot 19
	private int PCHU20;           // Picture total chunks of slot 20
	private int PCHU21;           // Picture total chunks of slot 21
	private int PCHU22;           // Picture total chunks of slot 22
	private int PCHU23;           // Picture total chunks of slot 23
	private int PCHU24;           // Picture total chunks of slot 24
	private int PCHU25;           // Picture total chunks of slot 25
	private int PCHU26;           // Picture total chunks of slot 26
	private int PCHU27;           // Picture total chunks of slot 27
	private int PCHU28;           // Picture total chunks of slot 28
	private int PCHU29;           // Picture total chunks of slot 29
	private int ID_00;            // ID of the picture meta 0
	private int ID_01;            // ID of the picture meta 1
	private int ID_02;            // ID of the picture meta 2
	private int ID_03;            // ID of the picture meta 3
	private int ID_04;            // ID of the picture meta 4
	private int ID_05;            // ID of the picture meta 5
	private int ID_06;            // ID of the picture meta 6
	private int ID_07;            // ID of the picture meta 7
	private int ID_08;            // ID of the picture meta 8
	private int ID_09;            // ID of the picture meta 9
	private int ID_10;            // ID of the picture meta 10
	private int ID_11;            // ID of the picture meta 11
	private int ID_12;            // ID of the picture meta 12
	private int ID_13;            // ID of the picture meta 13
	private int ID_14;            // ID of the picture meta 14
	private int ID_15;            // ID of the picture meta 15
	private int ID_16;            // ID of the picture meta 16
	private int ID_17;            // ID of the picture meta 17
	private int ID_18;            // ID of the picture meta 18
	private int ID_19;            // ID of the picture meta 19
	private int ID_20;            // ID of the picture meta 20
	private int ID_21;            // ID of the picture meta 21
	private int ID_22;            // ID of the picture meta 22
	private int ID_23;            // ID of the picture meta 23
	private int ID_24;            // ID of the picture meta 24
	private int ID_25;            // ID of the picture meta 25
	private int ID_26;            // ID of the picture meta 26
	private int ID_27;            // ID of the picture meta 27
	private int ID_28;            // ID of the picture meta 28
	private int ID_29;            // ID of the picture meta 29
	private byte[] OCC_00;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_01;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_02;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_03;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_04;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_05;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_06;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_07;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_08;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_09;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_10;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_11;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_12;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_13;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_14;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_15;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_16;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_17;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_18;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_19;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_20;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_21;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_22;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_23;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_24;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_25;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_26;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_27;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_28;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_29;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_30;        // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] OCC_31;        // Occupied mask (1 means occupied, 0 means free, left to right))

	public TmPictureMeta2(DataInputStream dis) throws IOException {
		PLEN00 = dis.readUnsignedShort();
		PLEN01 = dis.readUnsignedShort();
		PLEN02 = dis.readUnsignedShort();
		PLEN03 = dis.readUnsignedShort();
		PLEN04 = dis.readUnsignedShort();
		PLEN05 = dis.readUnsignedShort();
		PLEN06 = dis.readUnsignedShort();
		PLEN07 = dis.readUnsignedShort();
		PLEN08 = dis.readUnsignedShort();
		PLEN09 = dis.readUnsignedShort();
		PLEN10 = dis.readUnsignedShort();
		PLEN11 = dis.readUnsignedShort();
		PLEN12 = dis.readUnsignedShort();
		PLEN13 = dis.readUnsignedShort();
		PLEN14 = dis.readUnsignedShort();
		PLEN15 = dis.readUnsignedShort();
		PLEN16 = dis.readUnsignedShort();
		PLEN17 = dis.readUnsignedShort();
		PLEN18 = dis.readUnsignedShort();
		PLEN19 = dis.readUnsignedShort();
		PLEN20 = dis.readUnsignedShort();
		PLEN21 = dis.readUnsignedShort();
		PLEN22 = dis.readUnsignedShort();
		PLEN23 = dis.readUnsignedShort();
		PLEN24 = dis.readUnsignedShort();
		PLEN25 = dis.readUnsignedShort();
		PLEN26 = dis.readUnsignedShort();
		PLEN27 = dis.readUnsignedShort();
		PLEN28 = dis.readUnsignedShort();
		PLEN29 = dis.readUnsignedShort();
		PTIM00 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM01 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM02 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM03 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM04 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM05 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM06 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM07 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM08 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM09 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM10 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM11 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM12 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM13 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM14 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM15 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM16 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM17 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM18 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM19 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM20 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM21 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM22 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM23 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM24 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM25 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM26 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM27 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM28 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PTIM29 = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PCRC00 = new byte[4];
		dis.readFully(PCRC00);
		PCRC01 = new byte[4];
		dis.readFully(PCRC01);
		PCRC02 = new byte[4];
		dis.readFully(PCRC02);
		PCRC03 = new byte[4];
		dis.readFully(PCRC03);
		PCRC04 = new byte[4];
		dis.readFully(PCRC04);
		PCRC05 = new byte[4];
		dis.readFully(PCRC05);
		PCRC06 = new byte[4];
		dis.readFully(PCRC06);
		PCRC07 = new byte[4];
		dis.readFully(PCRC07);
		PCRC08 = new byte[4];
		dis.readFully(PCRC08);
		PCRC09 = new byte[4];
		dis.readFully(PCRC09);
		PCRC10 = new byte[4];
		dis.readFully(PCRC10);
		PCRC11 = new byte[4];
		dis.readFully(PCRC11);
		PCRC12 = new byte[4];
		dis.readFully(PCRC12);
		PCRC13 = new byte[4];
		dis.readFully(PCRC13);
		PCRC14 = new byte[4];
		dis.readFully(PCRC14);
		PCRC15 = new byte[4];
		dis.readFully(PCRC15);
		PCRC16 = new byte[4];
		dis.readFully(PCRC16);
		PCRC17 = new byte[4];
		dis.readFully(PCRC17);
		PCRC18 = new byte[4];
		dis.readFully(PCRC18);
		PCRC19 = new byte[4];
		dis.readFully(PCRC19);
		PCRC20 = new byte[4];
		dis.readFully(PCRC20);
		PCRC21 = new byte[4];
		dis.readFully(PCRC21);
		PCRC22 = new byte[4];
		dis.readFully(PCRC22);
		PCRC23 = new byte[4];
		dis.readFully(PCRC23);
		PCRC24 = new byte[4];
		dis.readFully(PCRC24);
		PCRC25 = new byte[4];
		dis.readFully(PCRC25);
		PCRC26 = new byte[4];
		dis.readFully(PCRC26);
		PCRC27 = new byte[4];
		dis.readFully(PCRC27);
		PCRC28 = new byte[4];
		dis.readFully(PCRC28);
		PCRC29 = new byte[4];
		dis.readFully(PCRC29);
		PCHU00 = dis.readUnsignedShort();
		PCHU01 = dis.readUnsignedShort();
		PCHU02 = dis.readUnsignedShort();
		PCHU03 = dis.readUnsignedShort();
		PCHU04 = dis.readUnsignedShort();
		PCHU05 = dis.readUnsignedShort();
		PCHU06 = dis.readUnsignedShort();
		PCHU07 = dis.readUnsignedShort();
		PCHU08 = dis.readUnsignedShort();
		PCHU09 = dis.readUnsignedShort();
		PCHU10 = dis.readUnsignedShort();
		PCHU11 = dis.readUnsignedShort();
		PCHU12 = dis.readUnsignedShort();
		PCHU13 = dis.readUnsignedShort();
		PCHU14 = dis.readUnsignedShort();
		PCHU15 = dis.readUnsignedShort();
		PCHU16 = dis.readUnsignedShort();
		PCHU17 = dis.readUnsignedShort();
		PCHU18 = dis.readUnsignedShort();
		PCHU19 = dis.readUnsignedShort();
		PCHU20 = dis.readUnsignedShort();
		PCHU21 = dis.readUnsignedShort();
		PCHU22 = dis.readUnsignedShort();
		PCHU23 = dis.readUnsignedShort();
		PCHU24 = dis.readUnsignedShort();
		PCHU25 = dis.readUnsignedShort();
		PCHU26 = dis.readUnsignedShort();
		PCHU27 = dis.readUnsignedShort();
		PCHU28 = dis.readUnsignedShort();
		PCHU29 = dis.readUnsignedShort();
		ID_00 = dis.readUnsignedShort();
		ID_01 = dis.readUnsignedShort();
		ID_02 = dis.readUnsignedShort();
		ID_03 = dis.readUnsignedShort();
		ID_04 = dis.readUnsignedShort();
		ID_05 = dis.readUnsignedShort();
		ID_06 = dis.readUnsignedShort();
		ID_07 = dis.readUnsignedShort();
		ID_08 = dis.readUnsignedShort();
		ID_09 = dis.readUnsignedShort();
		ID_10 = dis.readUnsignedShort();
		ID_11 = dis.readUnsignedShort();
		ID_12 = dis.readUnsignedShort();
		ID_13 = dis.readUnsignedShort();
		ID_14 = dis.readUnsignedShort();
		ID_15 = dis.readUnsignedShort();
		ID_16 = dis.readUnsignedShort();
		ID_17 = dis.readUnsignedShort();
		ID_18 = dis.readUnsignedShort();
		ID_19 = dis.readUnsignedShort();
		ID_20 = dis.readUnsignedShort();
		ID_21 = dis.readUnsignedShort();
		ID_22 = dis.readUnsignedShort();
		ID_23 = dis.readUnsignedShort();
		ID_24 = dis.readUnsignedShort();
		ID_25 = dis.readUnsignedShort();
		ID_26 = dis.readUnsignedShort();
		ID_27 = dis.readUnsignedShort();
		ID_28 = dis.readUnsignedShort();
		ID_29 = dis.readUnsignedShort();
		OCC_00 = new byte[4];
		dis.readFully(OCC_00);
		OCC_01 = new byte[4];
		dis.readFully(OCC_01);
		OCC_02 = new byte[4];
		dis.readFully(OCC_02);
		OCC_03 = new byte[4];
		dis.readFully(OCC_03);
		OCC_04 = new byte[4];
		dis.readFully(OCC_04);
		OCC_05 = new byte[4];
		dis.readFully(OCC_05);
		OCC_06 = new byte[4];
		dis.readFully(OCC_06);
		OCC_07 = new byte[4];
		dis.readFully(OCC_07);
		OCC_08 = new byte[4];
		dis.readFully(OCC_08);
		OCC_09 = new byte[4];
		dis.readFully(OCC_09);
		OCC_10 = new byte[4];
		dis.readFully(OCC_10);
		OCC_11 = new byte[4];
		dis.readFully(OCC_11);
		OCC_12 = new byte[4];
		dis.readFully(OCC_12);
		OCC_13 = new byte[4];
		dis.readFully(OCC_13);
		OCC_14 = new byte[4];
		dis.readFully(OCC_14);
		OCC_15 = new byte[4];
		dis.readFully(OCC_15);
		OCC_16 = new byte[4];
		dis.readFully(OCC_16);
		OCC_17 = new byte[4];
		dis.readFully(OCC_17);
		OCC_18 = new byte[4];
		dis.readFully(OCC_18);
		OCC_19 = new byte[4];
		dis.readFully(OCC_19);
		OCC_20 = new byte[4];
		dis.readFully(OCC_20);
		OCC_21 = new byte[4];
		dis.readFully(OCC_21);
		OCC_22 = new byte[4];
		dis.readFully(OCC_22);
		OCC_23 = new byte[4];
		dis.readFully(OCC_23);
		OCC_24 = new byte[4];
		dis.readFully(OCC_24);
		OCC_25 = new byte[4];
		dis.readFully(OCC_25);
		OCC_26 = new byte[4];
		dis.readFully(OCC_26);
		OCC_27 = new byte[4];
		dis.readFully(OCC_27);
		OCC_28 = new byte[4];
		dis.readFully(OCC_28);
		OCC_29 = new byte[4];
		dis.readFully(OCC_29);
		OCC_30 = new byte[4];
		dis.readFully(OCC_30);
		OCC_31 = new byte[4];
		dis.readFully(OCC_31);
	}

	public int getPLEN00() {
		return PLEN00;
	}

	public void setPLEN00(int pLEN00) {
		PLEN00 = pLEN00;
	}

	public int getPLEN01() {
		return PLEN01;
	}

	public void setPLEN01(int pLEN01) {
		PLEN01 = pLEN01;
	}

	public int getPLEN02() {
		return PLEN02;
	}

	public void setPLEN02(int pLEN02) {
		PLEN02 = pLEN02;
	}

	public int getPLEN03() {
		return PLEN03;
	}

	public void setPLEN03(int pLEN03) {
		PLEN03 = pLEN03;
	}

	public int getPLEN04() {
		return PLEN04;
	}

	public void setPLEN04(int pLEN04) {
		PLEN04 = pLEN04;
	}

	public int getPLEN05() {
		return PLEN05;
	}

	public void setPLEN05(int pLEN05) {
		PLEN05 = pLEN05;
	}

	public int getPLEN06() {
		return PLEN06;
	}

	public void setPLEN06(int pLEN06) {
		PLEN06 = pLEN06;
	}

	public int getPLEN07() {
		return PLEN07;
	}

	public void setPLEN07(int pLEN07) {
		PLEN07 = pLEN07;
	}

	public int getPLEN08() {
		return PLEN08;
	}

	public void setPLEN08(int pLEN08) {
		PLEN08 = pLEN08;
	}

	public int getPLEN09() {
		return PLEN09;
	}

	public void setPLEN09(int pLEN09) {
		PLEN09 = pLEN09;
	}

	public int getPLEN10() {
		return PLEN10;
	}

	public void setPLEN10(int pLEN10) {
		PLEN10 = pLEN10;
	}

	public int getPLEN11() {
		return PLEN11;
	}

	public void setPLEN11(int pLEN11) {
		PLEN11 = pLEN11;
	}

	public int getPLEN12() {
		return PLEN12;
	}

	public void setPLEN12(int pLEN12) {
		PLEN12 = pLEN12;
	}

	public int getPLEN13() {
		return PLEN13;
	}

	public void setPLEN13(int pLEN13) {
		PLEN13 = pLEN13;
	}

	public int getPLEN14() {
		return PLEN14;
	}

	public void setPLEN14(int pLEN14) {
		PLEN14 = pLEN14;
	}

	public int getPLEN15() {
		return PLEN15;
	}

	public void setPLEN15(int pLEN15) {
		PLEN15 = pLEN15;
	}

	public int getPLEN16() {
		return PLEN16;
	}

	public void setPLEN16(int pLEN16) {
		PLEN16 = pLEN16;
	}

	public int getPLEN17() {
		return PLEN17;
	}

	public void setPLEN17(int pLEN17) {
		PLEN17 = pLEN17;
	}

	public int getPLEN18() {
		return PLEN18;
	}

	public void setPLEN18(int pLEN18) {
		PLEN18 = pLEN18;
	}

	public int getPLEN19() {
		return PLEN19;
	}

	public void setPLEN19(int pLEN19) {
		PLEN19 = pLEN19;
	}

	public int getPLEN20() {
		return PLEN20;
	}

	public void setPLEN20(int pLEN20) {
		PLEN20 = pLEN20;
	}

	public int getPLEN21() {
		return PLEN21;
	}

	public void setPLEN21(int pLEN21) {
		PLEN21 = pLEN21;
	}

	public int getPLEN22() {
		return PLEN22;
	}

	public void setPLEN22(int pLEN22) {
		PLEN22 = pLEN22;
	}

	public int getPLEN23() {
		return PLEN23;
	}

	public void setPLEN23(int pLEN23) {
		PLEN23 = pLEN23;
	}

	public int getPLEN24() {
		return PLEN24;
	}

	public void setPLEN24(int pLEN24) {
		PLEN24 = pLEN24;
	}

	public int getPLEN25() {
		return PLEN25;
	}

	public void setPLEN25(int pLEN25) {
		PLEN25 = pLEN25;
	}

	public int getPLEN26() {
		return PLEN26;
	}

	public void setPLEN26(int pLEN26) {
		PLEN26 = pLEN26;
	}

	public int getPLEN27() {
		return PLEN27;
	}

	public void setPLEN27(int pLEN27) {
		PLEN27 = pLEN27;
	}

	public int getPLEN28() {
		return PLEN28;
	}

	public void setPLEN28(int pLEN28) {
		PLEN28 = pLEN28;
	}

	public int getPLEN29() {
		return PLEN29;
	}

	public void setPLEN29(int pLEN29) {
		PLEN29 = pLEN29;
	}

	public long getPTIM00() {
		return PTIM00;
	}

	public void setPTIM00(long pTIM00) {
		PTIM00 = pTIM00;
	}

	public long getPTIM01() {
		return PTIM01;
	}

	public void setPTIM01(long pTIM01) {
		PTIM01 = pTIM01;
	}

	public long getPTIM02() {
		return PTIM02;
	}

	public void setPTIM02(long pTIM02) {
		PTIM02 = pTIM02;
	}

	public long getPTIM03() {
		return PTIM03;
	}

	public void setPTIM03(long pTIM03) {
		PTIM03 = pTIM03;
	}

	public long getPTIM04() {
		return PTIM04;
	}

	public void setPTIM04(long pTIM04) {
		PTIM04 = pTIM04;
	}

	public long getPTIM05() {
		return PTIM05;
	}

	public void setPTIM05(long pTIM05) {
		PTIM05 = pTIM05;
	}

	public long getPTIM06() {
		return PTIM06;
	}

	public void setPTIM06(long pTIM06) {
		PTIM06 = pTIM06;
	}

	public long getPTIM07() {
		return PTIM07;
	}

	public void setPTIM07(long pTIM07) {
		PTIM07 = pTIM07;
	}

	public long getPTIM08() {
		return PTIM08;
	}

	public void setPTIM08(long pTIM08) {
		PTIM08 = pTIM08;
	}

	public long getPTIM09() {
		return PTIM09;
	}

	public void setPTIM09(long pTIM09) {
		PTIM09 = pTIM09;
	}

	public long getPTIM10() {
		return PTIM10;
	}

	public void setPTIM10(long pTIM10) {
		PTIM10 = pTIM10;
	}

	public long getPTIM11() {
		return PTIM11;
	}

	public void setPTIM11(long pTIM11) {
		PTIM11 = pTIM11;
	}

	public long getPTIM12() {
		return PTIM12;
	}

	public void setPTIM12(long pTIM12) {
		PTIM12 = pTIM12;
	}

	public long getPTIM13() {
		return PTIM13;
	}

	public void setPTIM13(long pTIM13) {
		PTIM13 = pTIM13;
	}

	public long getPTIM14() {
		return PTIM14;
	}

	public void setPTIM14(long pTIM14) {
		PTIM14 = pTIM14;
	}

	public long getPTIM15() {
		return PTIM15;
	}

	public void setPTIM15(long pTIM15) {
		PTIM15 = pTIM15;
	}

	public long getPTIM16() {
		return PTIM16;
	}

	public void setPTIM16(long pTIM16) {
		PTIM16 = pTIM16;
	}

	public long getPTIM17() {
		return PTIM17;
	}

	public void setPTIM17(long pTIM17) {
		PTIM17 = pTIM17;
	}

	public long getPTIM18() {
		return PTIM18;
	}

	public void setPTIM18(long pTIM18) {
		PTIM18 = pTIM18;
	}

	public long getPTIM19() {
		return PTIM19;
	}

	public void setPTIM19(long pTIM19) {
		PTIM19 = pTIM19;
	}

	public long getPTIM20() {
		return PTIM20;
	}

	public void setPTIM20(long pTIM20) {
		PTIM20 = pTIM20;
	}

	public long getPTIM21() {
		return PTIM21;
	}

	public void setPTIM21(long pTIM21) {
		PTIM21 = pTIM21;
	}

	public long getPTIM22() {
		return PTIM22;
	}

	public void setPTIM22(long pTIM22) {
		PTIM22 = pTIM22;
	}

	public long getPTIM23() {
		return PTIM23;
	}

	public void setPTIM23(long pTIM23) {
		PTIM23 = pTIM23;
	}

	public long getPTIM24() {
		return PTIM24;
	}

	public void setPTIM24(long pTIM24) {
		PTIM24 = pTIM24;
	}

	public long getPTIM25() {
		return PTIM25;
	}

	public void setPTIM25(long pTIM25) {
		PTIM25 = pTIM25;
	}

	public long getPTIM26() {
		return PTIM26;
	}

	public void setPTIM26(long pTIM26) {
		PTIM26 = pTIM26;
	}

	public long getPTIM27() {
		return PTIM27;
	}

	public void setPTIM27(long pTIM27) {
		PTIM27 = pTIM27;
	}

	public long getPTIM28() {
		return PTIM28;
	}

	public void setPTIM28(long pTIM28) {
		PTIM28 = pTIM28;
	}

	public long getPTIM29() {
		return PTIM29;
	}

	public void setPTIM29(long pTIM29) {
		PTIM29 = pTIM29;
	}

	public byte[] getPCRC00() {
		return PCRC00;
	}

	public void setPCRC00(byte[] pCRC00) {
		PCRC00 = pCRC00;
	}

	public byte[] getPCRC01() {
		return PCRC01;
	}

	public void setPCRC01(byte[] pCRC01) {
		PCRC01 = pCRC01;
	}

	public byte[] getPCRC02() {
		return PCRC02;
	}

	public void setPCRC02(byte[] pCRC02) {
		PCRC02 = pCRC02;
	}

	public byte[] getPCRC03() {
		return PCRC03;
	}

	public void setPCRC03(byte[] pCRC03) {
		PCRC03 = pCRC03;
	}

	public byte[] getPCRC04() {
		return PCRC04;
	}

	public void setPCRC04(byte[] pCRC04) {
		PCRC04 = pCRC04;
	}

	public byte[] getPCRC05() {
		return PCRC05;
	}

	public void setPCRC05(byte[] pCRC05) {
		PCRC05 = pCRC05;
	}

	public byte[] getPCRC06() {
		return PCRC06;
	}

	public void setPCRC06(byte[] pCRC06) {
		PCRC06 = pCRC06;
	}

	public byte[] getPCRC07() {
		return PCRC07;
	}

	public void setPCRC07(byte[] pCRC07) {
		PCRC07 = pCRC07;
	}

	public byte[] getPCRC08() {
		return PCRC08;
	}

	public void setPCRC08(byte[] pCRC08) {
		PCRC08 = pCRC08;
	}

	public byte[] getPCRC09() {
		return PCRC09;
	}

	public void setPCRC09(byte[] pCRC09) {
		PCRC09 = pCRC09;
	}

	public byte[] getPCRC10() {
		return PCRC10;
	}

	public void setPCRC10(byte[] pCRC10) {
		PCRC10 = pCRC10;
	}

	public byte[] getPCRC11() {
		return PCRC11;
	}

	public void setPCRC11(byte[] pCRC11) {
		PCRC11 = pCRC11;
	}

	public byte[] getPCRC12() {
		return PCRC12;
	}

	public void setPCRC12(byte[] pCRC12) {
		PCRC12 = pCRC12;
	}

	public byte[] getPCRC13() {
		return PCRC13;
	}

	public void setPCRC13(byte[] pCRC13) {
		PCRC13 = pCRC13;
	}

	public byte[] getPCRC14() {
		return PCRC14;
	}

	public void setPCRC14(byte[] pCRC14) {
		PCRC14 = pCRC14;
	}

	public byte[] getPCRC15() {
		return PCRC15;
	}

	public void setPCRC15(byte[] pCRC15) {
		PCRC15 = pCRC15;
	}

	public byte[] getPCRC16() {
		return PCRC16;
	}

	public void setPCRC16(byte[] pCRC16) {
		PCRC16 = pCRC16;
	}

	public byte[] getPCRC17() {
		return PCRC17;
	}

	public void setPCRC17(byte[] pCRC17) {
		PCRC17 = pCRC17;
	}

	public byte[] getPCRC18() {
		return PCRC18;
	}

	public void setPCRC18(byte[] pCRC18) {
		PCRC18 = pCRC18;
	}

	public byte[] getPCRC19() {
		return PCRC19;
	}

	public void setPCRC19(byte[] pCRC19) {
		PCRC19 = pCRC19;
	}

	public byte[] getPCRC20() {
		return PCRC20;
	}

	public void setPCRC20(byte[] pCRC20) {
		PCRC20 = pCRC20;
	}

	public byte[] getPCRC21() {
		return PCRC21;
	}

	public void setPCRC21(byte[] pCRC21) {
		PCRC21 = pCRC21;
	}

	public byte[] getPCRC22() {
		return PCRC22;
	}

	public void setPCRC22(byte[] pCRC22) {
		PCRC22 = pCRC22;
	}

	public byte[] getPCRC23() {
		return PCRC23;
	}

	public void setPCRC23(byte[] pCRC23) {
		PCRC23 = pCRC23;
	}

	public byte[] getPCRC24() {
		return PCRC24;
	}

	public void setPCRC24(byte[] pCRC24) {
		PCRC24 = pCRC24;
	}

	public byte[] getPCRC25() {
		return PCRC25;
	}

	public void setPCRC25(byte[] pCRC25) {
		PCRC25 = pCRC25;
	}

	public byte[] getPCRC26() {
		return PCRC26;
	}

	public void setPCRC26(byte[] pCRC26) {
		PCRC26 = pCRC26;
	}

	public byte[] getPCRC27() {
		return PCRC27;
	}

	public void setPCRC27(byte[] pCRC27) {
		PCRC27 = pCRC27;
	}

	public byte[] getPCRC28() {
		return PCRC28;
	}

	public void setPCRC28(byte[] pCRC28) {
		PCRC28 = pCRC28;
	}

	public byte[] getPCRC29() {
		return PCRC29;
	}

	public void setPCRC29(byte[] pCRC29) {
		PCRC29 = pCRC29;
	}

	public int getPCHU00() {
		return PCHU00;
	}

	public void setPCHU00(int pCHU00) {
		PCHU00 = pCHU00;
	}

	public int getPCHU01() {
		return PCHU01;
	}

	public void setPCHU01(int pCHU01) {
		PCHU01 = pCHU01;
	}

	public int getPCHU02() {
		return PCHU02;
	}

	public void setPCHU02(int pCHU02) {
		PCHU02 = pCHU02;
	}

	public int getPCHU03() {
		return PCHU03;
	}

	public void setPCHU03(int pCHU03) {
		PCHU03 = pCHU03;
	}

	public int getPCHU04() {
		return PCHU04;
	}

	public void setPCHU04(int pCHU04) {
		PCHU04 = pCHU04;
	}

	public int getPCHU05() {
		return PCHU05;
	}

	public void setPCHU05(int pCHU05) {
		PCHU05 = pCHU05;
	}

	public int getPCHU06() {
		return PCHU06;
	}

	public void setPCHU06(int pCHU06) {
		PCHU06 = pCHU06;
	}

	public int getPCHU07() {
		return PCHU07;
	}

	public void setPCHU07(int pCHU07) {
		PCHU07 = pCHU07;
	}

	public int getPCHU08() {
		return PCHU08;
	}

	public void setPCHU08(int pCHU08) {
		PCHU08 = pCHU08;
	}

	public int getPCHU09() {
		return PCHU09;
	}

	public void setPCHU09(int pCHU09) {
		PCHU09 = pCHU09;
	}

	public int getPCHU10() {
		return PCHU10;
	}

	public void setPCHU10(int pCHU10) {
		PCHU10 = pCHU10;
	}

	public int getPCHU11() {
		return PCHU11;
	}

	public void setPCHU11(int pCHU11) {
		PCHU11 = pCHU11;
	}

	public int getPCHU12() {
		return PCHU12;
	}

	public void setPCHU12(int pCHU12) {
		PCHU12 = pCHU12;
	}

	public int getPCHU13() {
		return PCHU13;
	}

	public void setPCHU13(int pCHU13) {
		PCHU13 = pCHU13;
	}

	public int getPCHU14() {
		return PCHU14;
	}

	public void setPCHU14(int pCHU14) {
		PCHU14 = pCHU14;
	}

	public int getPCHU15() {
		return PCHU15;
	}

	public void setPCHU15(int pCHU15) {
		PCHU15 = pCHU15;
	}

	public int getPCHU16() {
		return PCHU16;
	}

	public void setPCHU16(int pCHU16) {
		PCHU16 = pCHU16;
	}

	public int getPCHU17() {
		return PCHU17;
	}

	public void setPCHU17(int pCHU17) {
		PCHU17 = pCHU17;
	}

	public int getPCHU18() {
		return PCHU18;
	}

	public void setPCHU18(int pCHU18) {
		PCHU18 = pCHU18;
	}

	public int getPCHU19() {
		return PCHU19;
	}

	public void setPCHU19(int pCHU19) {
		PCHU19 = pCHU19;
	}

	public int getPCHU20() {
		return PCHU20;
	}

	public void setPCHU20(int pCHU20) {
		PCHU20 = pCHU20;
	}

	public int getPCHU21() {
		return PCHU21;
	}

	public void setPCHU21(int pCHU21) {
		PCHU21 = pCHU21;
	}

	public int getPCHU22() {
		return PCHU22;
	}

	public void setPCHU22(int pCHU22) {
		PCHU22 = pCHU22;
	}

	public int getPCHU23() {
		return PCHU23;
	}

	public void setPCHU23(int pCHU23) {
		PCHU23 = pCHU23;
	}

	public int getPCHU24() {
		return PCHU24;
	}

	public void setPCHU24(int pCHU24) {
		PCHU24 = pCHU24;
	}

	public int getPCHU25() {
		return PCHU25;
	}

	public void setPCHU25(int pCHU25) {
		PCHU25 = pCHU25;
	}

	public int getPCHU26() {
		return PCHU26;
	}

	public void setPCHU26(int pCHU26) {
		PCHU26 = pCHU26;
	}

	public int getPCHU27() {
		return PCHU27;
	}

	public void setPCHU27(int pCHU27) {
		PCHU27 = pCHU27;
	}

	public int getPCHU28() {
		return PCHU28;
	}

	public void setPCHU28(int pCHU28) {
		PCHU28 = pCHU28;
	}

	public int getPCHU29() {
		return PCHU29;
	}

	public void setPCHU29(int pCHU29) {
		PCHU29 = pCHU29;
	}

	public int getID_00() {
		return ID_00;
	}

	public void setID_00(int iD_00) {
		ID_00 = iD_00;
	}

	public int getID_01() {
		return ID_01;
	}

	public void setID_01(int iD_01) {
		ID_01 = iD_01;
	}

	public int getID_02() {
		return ID_02;
	}

	public void setID_02(int iD_02) {
		ID_02 = iD_02;
	}

	public int getID_03() {
		return ID_03;
	}

	public void setID_03(int iD_03) {
		ID_03 = iD_03;
	}

	public int getID_04() {
		return ID_04;
	}

	public void setID_04(int iD_04) {
		ID_04 = iD_04;
	}

	public int getID_05() {
		return ID_05;
	}

	public void setID_05(int iD_05) {
		ID_05 = iD_05;
	}

	public int getID_06() {
		return ID_06;
	}

	public void setID_06(int iD_06) {
		ID_06 = iD_06;
	}

	public int getID_07() {
		return ID_07;
	}

	public void setID_07(int iD_07) {
		ID_07 = iD_07;
	}

	public int getID_08() {
		return ID_08;
	}

	public void setID_08(int iD_08) {
		ID_08 = iD_08;
	}

	public int getID_09() {
		return ID_09;
	}

	public void setID_09(int iD_09) {
		ID_09 = iD_09;
	}

	public int getID_10() {
		return ID_10;
	}

	public void setID_10(int iD_10) {
		ID_10 = iD_10;
	}

	public int getID_11() {
		return ID_11;
	}

	public void setID_11(int iD_11) {
		ID_11 = iD_11;
	}

	public int getID_12() {
		return ID_12;
	}

	public void setID_12(int iD_12) {
		ID_12 = iD_12;
	}

	public int getID_13() {
		return ID_13;
	}

	public void setID_13(int iD_13) {
		ID_13 = iD_13;
	}

	public int getID_14() {
		return ID_14;
	}

	public void setID_14(int iD_14) {
		ID_14 = iD_14;
	}

	public int getID_15() {
		return ID_15;
	}

	public void setID_15(int iD_15) {
		ID_15 = iD_15;
	}

	public int getID_16() {
		return ID_16;
	}

	public void setID_16(int iD_16) {
		ID_16 = iD_16;
	}

	public int getID_17() {
		return ID_17;
	}

	public void setID_17(int iD_17) {
		ID_17 = iD_17;
	}

	public int getID_18() {
		return ID_18;
	}

	public void setID_18(int iD_18) {
		ID_18 = iD_18;
	}

	public int getID_19() {
		return ID_19;
	}

	public void setID_19(int iD_19) {
		ID_19 = iD_19;
	}

	public int getID_20() {
		return ID_20;
	}

	public void setID_20(int iD_20) {
		ID_20 = iD_20;
	}

	public int getID_21() {
		return ID_21;
	}

	public void setID_21(int iD_21) {
		ID_21 = iD_21;
	}

	public int getID_22() {
		return ID_22;
	}

	public void setID_22(int iD_22) {
		ID_22 = iD_22;
	}

	public int getID_23() {
		return ID_23;
	}

	public void setID_23(int iD_23) {
		ID_23 = iD_23;
	}

	public int getID_24() {
		return ID_24;
	}

	public void setID_24(int iD_24) {
		ID_24 = iD_24;
	}

	public int getID_25() {
		return ID_25;
	}

	public void setID_25(int iD_25) {
		ID_25 = iD_25;
	}

	public int getID_26() {
		return ID_26;
	}

	public void setID_26(int iD_26) {
		ID_26 = iD_26;
	}

	public int getID_27() {
		return ID_27;
	}

	public void setID_27(int iD_27) {
		ID_27 = iD_27;
	}

	public int getID_28() {
		return ID_28;
	}

	public void setID_28(int iD_28) {
		ID_28 = iD_28;
	}

	public int getID_29() {
		return ID_29;
	}

	public void setID_29(int iD_29) {
		ID_29 = iD_29;
	}

	public byte[] getOCC_00() {
		return OCC_00;
	}

	public void setOCC_00(byte[] oCC_00) {
		OCC_00 = oCC_00;
	}

	public byte[] getOCC_01() {
		return OCC_01;
	}

	public void setOCC_01(byte[] oCC_01) {
		OCC_01 = oCC_01;
	}

	public byte[] getOCC_02() {
		return OCC_02;
	}

	public void setOCC_02(byte[] oCC_02) {
		OCC_02 = oCC_02;
	}

	public byte[] getOCC_03() {
		return OCC_03;
	}

	public void setOCC_03(byte[] oCC_03) {
		OCC_03 = oCC_03;
	}

	public byte[] getOCC_04() {
		return OCC_04;
	}

	public void setOCC_04(byte[] oCC_04) {
		OCC_04 = oCC_04;
	}

	public byte[] getOCC_05() {
		return OCC_05;
	}

	public void setOCC_05(byte[] oCC_05) {
		OCC_05 = oCC_05;
	}

	public byte[] getOCC_06() {
		return OCC_06;
	}

	public void setOCC_06(byte[] oCC_06) {
		OCC_06 = oCC_06;
	}

	public byte[] getOCC_07() {
		return OCC_07;
	}

	public void setOCC_07(byte[] oCC_07) {
		OCC_07 = oCC_07;
	}

	public byte[] getOCC_08() {
		return OCC_08;
	}

	public void setOCC_08(byte[] oCC_08) {
		OCC_08 = oCC_08;
	}

	public byte[] getOCC_09() {
		return OCC_09;
	}

	public void setOCC_09(byte[] oCC_09) {
		OCC_09 = oCC_09;
	}

	public byte[] getOCC_10() {
		return OCC_10;
	}

	public void setOCC_10(byte[] oCC_10) {
		OCC_10 = oCC_10;
	}

	public byte[] getOCC_11() {
		return OCC_11;
	}

	public void setOCC_11(byte[] oCC_11) {
		OCC_11 = oCC_11;
	}

	public byte[] getOCC_12() {
		return OCC_12;
	}

	public void setOCC_12(byte[] oCC_12) {
		OCC_12 = oCC_12;
	}

	public byte[] getOCC_13() {
		return OCC_13;
	}

	public void setOCC_13(byte[] oCC_13) {
		OCC_13 = oCC_13;
	}

	public byte[] getOCC_14() {
		return OCC_14;
	}

	public void setOCC_14(byte[] oCC_14) {
		OCC_14 = oCC_14;
	}

	public byte[] getOCC_15() {
		return OCC_15;
	}

	public void setOCC_15(byte[] oCC_15) {
		OCC_15 = oCC_15;
	}

	public byte[] getOCC_16() {
		return OCC_16;
	}

	public void setOCC_16(byte[] oCC_16) {
		OCC_16 = oCC_16;
	}

	public byte[] getOCC_17() {
		return OCC_17;
	}

	public void setOCC_17(byte[] oCC_17) {
		OCC_17 = oCC_17;
	}

	public byte[] getOCC_18() {
		return OCC_18;
	}

	public void setOCC_18(byte[] oCC_18) {
		OCC_18 = oCC_18;
	}

	public byte[] getOCC_19() {
		return OCC_19;
	}

	public void setOCC_19(byte[] oCC_19) {
		OCC_19 = oCC_19;
	}

	public byte[] getOCC_20() {
		return OCC_20;
	}

	public void setOCC_20(byte[] oCC_20) {
		OCC_20 = oCC_20;
	}

	public byte[] getOCC_21() {
		return OCC_21;
	}

	public void setOCC_21(byte[] oCC_21) {
		OCC_21 = oCC_21;
	}

	public byte[] getOCC_22() {
		return OCC_22;
	}

	public void setOCC_22(byte[] oCC_22) {
		OCC_22 = oCC_22;
	}

	public byte[] getOCC_23() {
		return OCC_23;
	}

	public void setOCC_23(byte[] oCC_23) {
		OCC_23 = oCC_23;
	}

	public byte[] getOCC_24() {
		return OCC_24;
	}

	public void setOCC_24(byte[] oCC_24) {
		OCC_24 = oCC_24;
	}

	public byte[] getOCC_25() {
		return OCC_25;
	}

	public void setOCC_25(byte[] oCC_25) {
		OCC_25 = oCC_25;
	}

	public byte[] getOCC_26() {
		return OCC_26;
	}

	public void setOCC_26(byte[] oCC_26) {
		OCC_26 = oCC_26;
	}

	public byte[] getOCC_27() {
		return OCC_27;
	}

	public void setOCC_27(byte[] oCC_27) {
		OCC_27 = oCC_27;
	}

	public byte[] getOCC_28() {
		return OCC_28;
	}

	public void setOCC_28(byte[] oCC_28) {
		OCC_28 = oCC_28;
	}

	public byte[] getOCC_29() {
		return OCC_29;
	}

	public void setOCC_29(byte[] oCC_29) {
		OCC_29 = oCC_29;
	}

	public byte[] getOCC_30() {
		return OCC_30;
	}

	public void setOCC_30(byte[] oCC_30) {
		OCC_30 = oCC_30;
	}

	public byte[] getOCC_31() {
		return OCC_31;
	}

	public void setOCC_31(byte[] oCC_31) {
		OCC_31 = oCC_31;
	}

}
