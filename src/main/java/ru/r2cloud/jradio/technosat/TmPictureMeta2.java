package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmPictureMeta2 {

	private int pLen00; // Picture length of slot 0
	private int pLen01; // Picture length of slot 1
	private int pLen02; // Picture length of slot 2
	private int pLen03; // Picture length of slot 3
	private int pLen04; // Picture length of slot 4
	private int pLen05; // Picture length of slot 5
	private int pLen06; // Picture length of slot 6
	private int pLen07; // Picture length of slot 7
	private int pLen08; // Picture length of slot 8
	private int pLen09; // Picture length of slot 9
	private int pLen10; // Picture length of slot 10
	private int pLen11; // Picture length of slot 11
	private int pLen12; // Picture length of slot 12
	private int pLen13; // Picture length of slot 13
	private int pLen14; // Picture length of slot 14
	private int pLen15; // Picture length of slot 15
	private int pLen16; // Picture length of slot 16
	private int pLen17; // Picture length of slot 17
	private int pLen18; // Picture length of slot 18
	private int pLen19; // Picture length of slot 19
	private int pLen20; // Picture length of slot 20
	private int pLen21; // Picture length of slot 21
	private int pLen22; // Picture length of slot 22
	private int pLen23; // Picture length of slot 23
	private int pLen24; // Picture length of slot 24
	private int pLen25; // Picture length of slot 25
	private int pLen26; // Picture length of slot 26
	private int pLen27; // Picture length of slot 27
	private int pLen28; // Picture length of slot 28
	private int pLen29; // Picture length of slot 29
	private long pTim00; // Picture time of slot 0
	private long pTim01; // Picture time of slot 1
	private long pTim02; // Picture time of slot 2
	private long pTim03; // Picture time of slot 3
	private long pTim04; // Picture time of slot 4
	private long pTim05; // Picture time of slot 5
	private long pTim06; // Picture time of slot 6
	private long pTim07; // Picture time of slot 7
	private long pTim08; // Picture time of slot 8
	private long pTim09; // Picture time of slot 9
	private long pTim10; // Picture time of slot 10
	private long pTim11; // Picture time of slot 11
	private long pTim12; // Picture time of slot 12
	private long pTim13; // Picture time of slot 13
	private long pTim14; // Picture time of slot 14
	private long pTim15; // Picture time of slot 15
	private long pTim16; // Picture time of slot 16
	private long pTim17; // Picture time of slot 17
	private long pTim18; // Picture time of slot 18
	private long pTim19; // Picture time of slot 19
	private long pTim20; // Picture time of slot 20
	private long pTim21; // Picture time of slot 21
	private long pTim22; // Picture time of slot 22
	private long pTim23; // Picture time of slot 23
	private long pTim24; // Picture time of slot 24
	private long pTim25; // Picture time of slot 25
	private long pTim26; // Picture time of slot 26
	private long pTim27; // Picture time of slot 27
	private long pTim28; // Picture time of slot 28
	private long pTim29; // Picture time of slot 29
	private byte[] pCrc00; // Picture CRC32 of slot 0
	private byte[] pCrc01; // Picture CRC32 of slot 1
	private byte[] pCrc02; // Picture CRC32 of slot 2
	private byte[] pCrc03; // Picture CRC32 of slot 3
	private byte[] pCrc04; // Picture CRC32 of slot 4
	private byte[] pCrc05; // Picture CRC32 of slot 5
	private byte[] pCrc06; // Picture CRC32 of slot 6
	private byte[] pCrc07; // Picture CRC32 of slot 7
	private byte[] pCrc08; // Picture CRC32 of slot 8
	private byte[] pCrc09; // Picture CRC32 of slot 9
	private byte[] pCrc10; // Picture CRC32 of slot 10
	private byte[] pCrc11; // Picture CRC32 of slot 11
	private byte[] pCrc12; // Picture CRC32 of slot 12
	private byte[] pCrc13; // Picture CRC32 of slot 13
	private byte[] pCrc14; // Picture CRC32 of slot 14
	private byte[] pCrc15; // Picture CRC32 of slot 15
	private byte[] pCrc16; // Picture CRC32 of slot 16
	private byte[] pCrc17; // Picture CRC32 of slot 17
	private byte[] pCrc18; // Picture CRC32 of slot 18
	private byte[] pCrc19; // Picture CRC32 of slot 19
	private byte[] pCrc20; // Picture CRC32 of slot 20
	private byte[] pCrc21; // Picture CRC32 of slot 21
	private byte[] pCrc22; // Picture CRC32 of slot 22
	private byte[] pCrc23; // Picture CRC32 of slot 23
	private byte[] pCrc24; // Picture CRC32 of slot 24
	private byte[] pCrc25; // Picture CRC32 of slot 25
	private byte[] pCrc26; // Picture CRC32 of slot 26
	private byte[] pCrc27; // Picture CRC32 of slot 27
	private byte[] pCrc28; // Picture CRC32 of slot 28
	private byte[] pCrc29; // Picture CRC32 of slot 29
	private int pChu00; // Picture total chunks of slot 0
	private int pChu01; // Picture total chunks of slot 1
	private int pChu02; // Picture total chunks of slot 2
	private int pChu03; // Picture total chunks of slot 3
	private int pChu04; // Picture total chunks of slot 4
	private int pChu05; // Picture total chunks of slot 5
	private int pChu06; // Picture total chunks of slot 6
	private int pChu07; // Picture total chunks of slot 7
	private int pChu08; // Picture total chunks of slot 8
	private int pChu09; // Picture total chunks of slot 9
	private int pChu10; // Picture total chunks of slot 10
	private int pChu11; // Picture total chunks of slot 11
	private int pChu12; // Picture total chunks of slot 12
	private int pChu13; // Picture total chunks of slot 13
	private int pChu14; // Picture total chunks of slot 14
	private int pChu15; // Picture total chunks of slot 15
	private int pChu16; // Picture total chunks of slot 16
	private int pChu17; // Picture total chunks of slot 17
	private int pChu18; // Picture total chunks of slot 18
	private int pChu19; // Picture total chunks of slot 19
	private int pChu20; // Picture total chunks of slot 20
	private int pChu21; // Picture total chunks of slot 21
	private int pChu22; // Picture total chunks of slot 22
	private int pChu23; // Picture total chunks of slot 23
	private int pChu24; // Picture total chunks of slot 24
	private int pChu25; // Picture total chunks of slot 25
	private int pChu26; // Picture total chunks of slot 26
	private int pChu27; // Picture total chunks of slot 27
	private int pChu28; // Picture total chunks of slot 28
	private int pChu29; // Picture total chunks of slot 29
	private int id00; // ID of the picture meta 0
	private int id01; // ID of the picture meta 1
	private int id02; // ID of the picture meta 2
	private int id03; // ID of the picture meta 3
	private int id04; // ID of the picture meta 4
	private int id05; // ID of the picture meta 5
	private int id06; // ID of the picture meta 6
	private int id07; // ID of the picture meta 7
	private int id08; // ID of the picture meta 8
	private int id09; // ID of the picture meta 9
	private int id10; // ID of the picture meta 10
	private int id11; // ID of the picture meta 11
	private int id12; // ID of the picture meta 12
	private int id13; // ID of the picture meta 13
	private int id14; // ID of the picture meta 14
	private int id15; // ID of the picture meta 15
	private int id16; // ID of the picture meta 16
	private int id17; // ID of the picture meta 17
	private int id18; // ID of the picture meta 18
	private int id19; // ID of the picture meta 19
	private int id20; // ID of the picture meta 20
	private int id21; // ID of the picture meta 21
	private int id22; // ID of the picture meta 22
	private int id23; // ID of the picture meta 23
	private int id24; // ID of the picture meta 24
	private int id25; // ID of the picture meta 25
	private int id26; // ID of the picture meta 26
	private int id27; // ID of the picture meta 27
	private int id28; // ID of the picture meta 28
	private int id29; // ID of the picture meta 29
	private byte[] occ00; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ01; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ02; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ03; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ04; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ05; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ06; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ07; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ08; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ09; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ10; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ11; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ12; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ13; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ14; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ15; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ16; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ17; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ18; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ19; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ20; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ21; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ22; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ23; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ24; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ25; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ26; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ27; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ28; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ29; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ30; // Occupied mask (1 means occupied, 0 means free, left to right))
	private byte[] occ31; // Occupied mask (1 means occupied, 0 means free, left to right))

	public TmPictureMeta2(DataInputStream dis) throws IOException {
		pLen00 = dis.readUnsignedShort();
		pLen01 = dis.readUnsignedShort();
		pLen02 = dis.readUnsignedShort();
		pLen03 = dis.readUnsignedShort();
		pLen04 = dis.readUnsignedShort();
		pLen05 = dis.readUnsignedShort();
		pLen06 = dis.readUnsignedShort();
		pLen07 = dis.readUnsignedShort();
		pLen08 = dis.readUnsignedShort();
		pLen09 = dis.readUnsignedShort();
		pLen10 = dis.readUnsignedShort();
		pLen11 = dis.readUnsignedShort();
		pLen12 = dis.readUnsignedShort();
		pLen13 = dis.readUnsignedShort();
		pLen14 = dis.readUnsignedShort();
		pLen15 = dis.readUnsignedShort();
		pLen16 = dis.readUnsignedShort();
		pLen17 = dis.readUnsignedShort();
		pLen18 = dis.readUnsignedShort();
		pLen19 = dis.readUnsignedShort();
		pLen20 = dis.readUnsignedShort();
		pLen21 = dis.readUnsignedShort();
		pLen22 = dis.readUnsignedShort();
		pLen23 = dis.readUnsignedShort();
		pLen24 = dis.readUnsignedShort();
		pLen25 = dis.readUnsignedShort();
		pLen26 = dis.readUnsignedShort();
		pLen27 = dis.readUnsignedShort();
		pLen28 = dis.readUnsignedShort();
		pLen29 = dis.readUnsignedShort();
		pTim00 = StreamUtils.readUnsignedInt(dis);
		pTim01 = StreamUtils.readUnsignedInt(dis);
		pTim02 = StreamUtils.readUnsignedInt(dis);
		pTim03 = StreamUtils.readUnsignedInt(dis);
		pTim04 = StreamUtils.readUnsignedInt(dis);
		pTim05 = StreamUtils.readUnsignedInt(dis);
		pTim06 = StreamUtils.readUnsignedInt(dis);
		pTim07 = StreamUtils.readUnsignedInt(dis);
		pTim08 = StreamUtils.readUnsignedInt(dis);
		pTim09 = StreamUtils.readUnsignedInt(dis);
		pTim10 = StreamUtils.readUnsignedInt(dis);
		pTim11 = StreamUtils.readUnsignedInt(dis);
		pTim12 = StreamUtils.readUnsignedInt(dis);
		pTim13 = StreamUtils.readUnsignedInt(dis);
		pTim14 = StreamUtils.readUnsignedInt(dis);
		pTim15 = StreamUtils.readUnsignedInt(dis);
		pTim16 = StreamUtils.readUnsignedInt(dis);
		pTim17 = StreamUtils.readUnsignedInt(dis);
		pTim18 = StreamUtils.readUnsignedInt(dis);
		pTim19 = StreamUtils.readUnsignedInt(dis);
		pTim20 = StreamUtils.readUnsignedInt(dis);
		pTim21 = StreamUtils.readUnsignedInt(dis);
		pTim22 = StreamUtils.readUnsignedInt(dis);
		pTim23 = StreamUtils.readUnsignedInt(dis);
		pTim24 = StreamUtils.readUnsignedInt(dis);
		pTim25 = StreamUtils.readUnsignedInt(dis);
		pTim26 = StreamUtils.readUnsignedInt(dis);
		pTim27 = StreamUtils.readUnsignedInt(dis);
		pTim28 = StreamUtils.readUnsignedInt(dis);
		pTim29 = StreamUtils.readUnsignedInt(dis);
		pCrc00 = new byte[4];
		dis.readFully(pCrc00);
		pCrc01 = new byte[4];
		dis.readFully(pCrc01);
		pCrc02 = new byte[4];
		dis.readFully(pCrc02);
		pCrc03 = new byte[4];
		dis.readFully(pCrc03);
		pCrc04 = new byte[4];
		dis.readFully(pCrc04);
		pCrc05 = new byte[4];
		dis.readFully(pCrc05);
		pCrc06 = new byte[4];
		dis.readFully(pCrc06);
		pCrc07 = new byte[4];
		dis.readFully(pCrc07);
		pCrc08 = new byte[4];
		dis.readFully(pCrc08);
		pCrc09 = new byte[4];
		dis.readFully(pCrc09);
		pCrc10 = new byte[4];
		dis.readFully(pCrc10);
		pCrc11 = new byte[4];
		dis.readFully(pCrc11);
		pCrc12 = new byte[4];
		dis.readFully(pCrc12);
		pCrc13 = new byte[4];
		dis.readFully(pCrc13);
		pCrc14 = new byte[4];
		dis.readFully(pCrc14);
		pCrc15 = new byte[4];
		dis.readFully(pCrc15);
		pCrc16 = new byte[4];
		dis.readFully(pCrc16);
		pCrc17 = new byte[4];
		dis.readFully(pCrc17);
		pCrc18 = new byte[4];
		dis.readFully(pCrc18);
		pCrc19 = new byte[4];
		dis.readFully(pCrc19);
		pCrc20 = new byte[4];
		dis.readFully(pCrc20);
		pCrc21 = new byte[4];
		dis.readFully(pCrc21);
		pCrc22 = new byte[4];
		dis.readFully(pCrc22);
		pCrc23 = new byte[4];
		dis.readFully(pCrc23);
		pCrc24 = new byte[4];
		dis.readFully(pCrc24);
		pCrc25 = new byte[4];
		dis.readFully(pCrc25);
		pCrc26 = new byte[4];
		dis.readFully(pCrc26);
		pCrc27 = new byte[4];
		dis.readFully(pCrc27);
		pCrc28 = new byte[4];
		dis.readFully(pCrc28);
		pCrc29 = new byte[4];
		dis.readFully(pCrc29);
		pChu00 = dis.readUnsignedShort();
		pChu01 = dis.readUnsignedShort();
		pChu02 = dis.readUnsignedShort();
		pChu03 = dis.readUnsignedShort();
		pChu04 = dis.readUnsignedShort();
		pChu05 = dis.readUnsignedShort();
		pChu06 = dis.readUnsignedShort();
		pChu07 = dis.readUnsignedShort();
		pChu08 = dis.readUnsignedShort();
		pChu09 = dis.readUnsignedShort();
		pChu10 = dis.readUnsignedShort();
		pChu11 = dis.readUnsignedShort();
		pChu12 = dis.readUnsignedShort();
		pChu13 = dis.readUnsignedShort();
		pChu14 = dis.readUnsignedShort();
		pChu15 = dis.readUnsignedShort();
		pChu16 = dis.readUnsignedShort();
		pChu17 = dis.readUnsignedShort();
		pChu18 = dis.readUnsignedShort();
		pChu19 = dis.readUnsignedShort();
		pChu20 = dis.readUnsignedShort();
		pChu21 = dis.readUnsignedShort();
		pChu22 = dis.readUnsignedShort();
		pChu23 = dis.readUnsignedShort();
		pChu24 = dis.readUnsignedShort();
		pChu25 = dis.readUnsignedShort();
		pChu26 = dis.readUnsignedShort();
		pChu27 = dis.readUnsignedShort();
		pChu28 = dis.readUnsignedShort();
		pChu29 = dis.readUnsignedShort();
		id00 = dis.readUnsignedShort();
		id01 = dis.readUnsignedShort();
		id02 = dis.readUnsignedShort();
		id03 = dis.readUnsignedShort();
		id04 = dis.readUnsignedShort();
		id05 = dis.readUnsignedShort();
		id06 = dis.readUnsignedShort();
		id07 = dis.readUnsignedShort();
		id08 = dis.readUnsignedShort();
		id09 = dis.readUnsignedShort();
		id10 = dis.readUnsignedShort();
		id11 = dis.readUnsignedShort();
		id12 = dis.readUnsignedShort();
		id13 = dis.readUnsignedShort();
		id14 = dis.readUnsignedShort();
		id15 = dis.readUnsignedShort();
		id16 = dis.readUnsignedShort();
		id17 = dis.readUnsignedShort();
		id18 = dis.readUnsignedShort();
		id19 = dis.readUnsignedShort();
		id20 = dis.readUnsignedShort();
		id21 = dis.readUnsignedShort();
		id22 = dis.readUnsignedShort();
		id23 = dis.readUnsignedShort();
		id24 = dis.readUnsignedShort();
		id25 = dis.readUnsignedShort();
		id26 = dis.readUnsignedShort();
		id27 = dis.readUnsignedShort();
		id28 = dis.readUnsignedShort();
		id29 = dis.readUnsignedShort();
		occ00 = new byte[4];
		dis.readFully(occ00);
		occ01 = new byte[4];
		dis.readFully(occ01);
		occ02 = new byte[4];
		dis.readFully(occ02);
		occ03 = new byte[4];
		dis.readFully(occ03);
		occ04 = new byte[4];
		dis.readFully(occ04);
		occ05 = new byte[4];
		dis.readFully(occ05);
		occ06 = new byte[4];
		dis.readFully(occ06);
		occ07 = new byte[4];
		dis.readFully(occ07);
		occ08 = new byte[4];
		dis.readFully(occ08);
		occ09 = new byte[4];
		dis.readFully(occ09);
		occ10 = new byte[4];
		dis.readFully(occ10);
		occ11 = new byte[4];
		dis.readFully(occ11);
		occ12 = new byte[4];
		dis.readFully(occ12);
		occ13 = new byte[4];
		dis.readFully(occ13);
		occ14 = new byte[4];
		dis.readFully(occ14);
		occ15 = new byte[4];
		dis.readFully(occ15);
		occ16 = new byte[4];
		dis.readFully(occ16);
		occ17 = new byte[4];
		dis.readFully(occ17);
		occ18 = new byte[4];
		dis.readFully(occ18);
		occ19 = new byte[4];
		dis.readFully(occ19);
		occ20 = new byte[4];
		dis.readFully(occ20);
		occ21 = new byte[4];
		dis.readFully(occ21);
		occ22 = new byte[4];
		dis.readFully(occ22);
		occ23 = new byte[4];
		dis.readFully(occ23);
		occ24 = new byte[4];
		dis.readFully(occ24);
		occ25 = new byte[4];
		dis.readFully(occ25);
		occ26 = new byte[4];
		dis.readFully(occ26);
		occ27 = new byte[4];
		dis.readFully(occ27);
		occ28 = new byte[4];
		dis.readFully(occ28);
		occ29 = new byte[4];
		dis.readFully(occ29);
		occ30 = new byte[4];
		dis.readFully(occ30);
		occ31 = new byte[4];
		dis.readFully(occ31);
	}

	public int getpLen00() {
		return pLen00;
	}

	public void setpLen00(int pLen00) {
		this.pLen00 = pLen00;
	}

	public int getpLen01() {
		return pLen01;
	}

	public void setpLen01(int pLen01) {
		this.pLen01 = pLen01;
	}

	public int getpLen02() {
		return pLen02;
	}

	public void setpLen02(int pLen02) {
		this.pLen02 = pLen02;
	}

	public int getpLen03() {
		return pLen03;
	}

	public void setpLen03(int pLen03) {
		this.pLen03 = pLen03;
	}

	public int getpLen04() {
		return pLen04;
	}

	public void setpLen04(int pLen04) {
		this.pLen04 = pLen04;
	}

	public int getpLen05() {
		return pLen05;
	}

	public void setpLen05(int pLen05) {
		this.pLen05 = pLen05;
	}

	public int getpLen06() {
		return pLen06;
	}

	public void setpLen06(int pLen06) {
		this.pLen06 = pLen06;
	}

	public int getpLen07() {
		return pLen07;
	}

	public void setpLen07(int pLen07) {
		this.pLen07 = pLen07;
	}

	public int getpLen08() {
		return pLen08;
	}

	public void setpLen08(int pLen08) {
		this.pLen08 = pLen08;
	}

	public int getpLen09() {
		return pLen09;
	}

	public void setpLen09(int pLen09) {
		this.pLen09 = pLen09;
	}

	public int getpLen10() {
		return pLen10;
	}

	public void setpLen10(int pLen10) {
		this.pLen10 = pLen10;
	}

	public int getpLen11() {
		return pLen11;
	}

	public void setpLen11(int pLen11) {
		this.pLen11 = pLen11;
	}

	public int getpLen12() {
		return pLen12;
	}

	public void setpLen12(int pLen12) {
		this.pLen12 = pLen12;
	}

	public int getpLen13() {
		return pLen13;
	}

	public void setpLen13(int pLen13) {
		this.pLen13 = pLen13;
	}

	public int getpLen14() {
		return pLen14;
	}

	public void setpLen14(int pLen14) {
		this.pLen14 = pLen14;
	}

	public int getpLen15() {
		return pLen15;
	}

	public void setpLen15(int pLen15) {
		this.pLen15 = pLen15;
	}

	public int getpLen16() {
		return pLen16;
	}

	public void setpLen16(int pLen16) {
		this.pLen16 = pLen16;
	}

	public int getpLen17() {
		return pLen17;
	}

	public void setpLen17(int pLen17) {
		this.pLen17 = pLen17;
	}

	public int getpLen18() {
		return pLen18;
	}

	public void setpLen18(int pLen18) {
		this.pLen18 = pLen18;
	}

	public int getpLen19() {
		return pLen19;
	}

	public void setpLen19(int pLen19) {
		this.pLen19 = pLen19;
	}

	public int getpLen20() {
		return pLen20;
	}

	public void setpLen20(int pLen20) {
		this.pLen20 = pLen20;
	}

	public int getpLen21() {
		return pLen21;
	}

	public void setpLen21(int pLen21) {
		this.pLen21 = pLen21;
	}

	public int getpLen22() {
		return pLen22;
	}

	public void setpLen22(int pLen22) {
		this.pLen22 = pLen22;
	}

	public int getpLen23() {
		return pLen23;
	}

	public void setpLen23(int pLen23) {
		this.pLen23 = pLen23;
	}

	public int getpLen24() {
		return pLen24;
	}

	public void setpLen24(int pLen24) {
		this.pLen24 = pLen24;
	}

	public int getpLen25() {
		return pLen25;
	}

	public void setpLen25(int pLen25) {
		this.pLen25 = pLen25;
	}

	public int getpLen26() {
		return pLen26;
	}

	public void setpLen26(int pLen26) {
		this.pLen26 = pLen26;
	}

	public int getpLen27() {
		return pLen27;
	}

	public void setpLen27(int pLen27) {
		this.pLen27 = pLen27;
	}

	public int getpLen28() {
		return pLen28;
	}

	public void setpLen28(int pLen28) {
		this.pLen28 = pLen28;
	}

	public int getpLen29() {
		return pLen29;
	}

	public void setpLen29(int pLen29) {
		this.pLen29 = pLen29;
	}

	public long getpTim00() {
		return pTim00;
	}

	public void setpTim00(long pTim00) {
		this.pTim00 = pTim00;
	}

	public long getpTim01() {
		return pTim01;
	}

	public void setpTim01(long pTim01) {
		this.pTim01 = pTim01;
	}

	public long getpTim02() {
		return pTim02;
	}

	public void setpTim02(long pTim02) {
		this.pTim02 = pTim02;
	}

	public long getpTim03() {
		return pTim03;
	}

	public void setpTim03(long pTim03) {
		this.pTim03 = pTim03;
	}

	public long getpTim04() {
		return pTim04;
	}

	public void setpTim04(long pTim04) {
		this.pTim04 = pTim04;
	}

	public long getpTim05() {
		return pTim05;
	}

	public void setpTim05(long pTim05) {
		this.pTim05 = pTim05;
	}

	public long getpTim06() {
		return pTim06;
	}

	public void setpTim06(long pTim06) {
		this.pTim06 = pTim06;
	}

	public long getpTim07() {
		return pTim07;
	}

	public void setpTim07(long pTim07) {
		this.pTim07 = pTim07;
	}

	public long getpTim08() {
		return pTim08;
	}

	public void setpTim08(long pTim08) {
		this.pTim08 = pTim08;
	}

	public long getpTim09() {
		return pTim09;
	}

	public void setpTim09(long pTim09) {
		this.pTim09 = pTim09;
	}

	public long getpTim10() {
		return pTim10;
	}

	public void setpTim10(long pTim10) {
		this.pTim10 = pTim10;
	}

	public long getpTim11() {
		return pTim11;
	}

	public void setpTim11(long pTim11) {
		this.pTim11 = pTim11;
	}

	public long getpTim12() {
		return pTim12;
	}

	public void setpTim12(long pTim12) {
		this.pTim12 = pTim12;
	}

	public long getpTim13() {
		return pTim13;
	}

	public void setpTim13(long pTim13) {
		this.pTim13 = pTim13;
	}

	public long getpTim14() {
		return pTim14;
	}

	public void setpTim14(long pTim14) {
		this.pTim14 = pTim14;
	}

	public long getpTim15() {
		return pTim15;
	}

	public void setpTim15(long pTim15) {
		this.pTim15 = pTim15;
	}

	public long getpTim16() {
		return pTim16;
	}

	public void setpTim16(long pTim16) {
		this.pTim16 = pTim16;
	}

	public long getpTim17() {
		return pTim17;
	}

	public void setpTim17(long pTim17) {
		this.pTim17 = pTim17;
	}

	public long getpTim18() {
		return pTim18;
	}

	public void setpTim18(long pTim18) {
		this.pTim18 = pTim18;
	}

	public long getpTim19() {
		return pTim19;
	}

	public void setpTim19(long pTim19) {
		this.pTim19 = pTim19;
	}

	public long getpTim20() {
		return pTim20;
	}

	public void setpTim20(long pTim20) {
		this.pTim20 = pTim20;
	}

	public long getpTim21() {
		return pTim21;
	}

	public void setpTim21(long pTim21) {
		this.pTim21 = pTim21;
	}

	public long getpTim22() {
		return pTim22;
	}

	public void setpTim22(long pTim22) {
		this.pTim22 = pTim22;
	}

	public long getpTim23() {
		return pTim23;
	}

	public void setpTim23(long pTim23) {
		this.pTim23 = pTim23;
	}

	public long getpTim24() {
		return pTim24;
	}

	public void setpTim24(long pTim24) {
		this.pTim24 = pTim24;
	}

	public long getpTim25() {
		return pTim25;
	}

	public void setpTim25(long pTim25) {
		this.pTim25 = pTim25;
	}

	public long getpTim26() {
		return pTim26;
	}

	public void setpTim26(long pTim26) {
		this.pTim26 = pTim26;
	}

	public long getpTim27() {
		return pTim27;
	}

	public void setpTim27(long pTim27) {
		this.pTim27 = pTim27;
	}

	public long getpTim28() {
		return pTim28;
	}

	public void setpTim28(long pTim28) {
		this.pTim28 = pTim28;
	}

	public long getpTim29() {
		return pTim29;
	}

	public void setpTim29(long pTim29) {
		this.pTim29 = pTim29;
	}

	public byte[] getpCrc00() {
		return pCrc00;
	}

	public void setpCrc00(byte[] pCrc00) {
		this.pCrc00 = pCrc00;
	}

	public byte[] getpCrc01() {
		return pCrc01;
	}

	public void setpCrc01(byte[] pCrc01) {
		this.pCrc01 = pCrc01;
	}

	public byte[] getpCrc02() {
		return pCrc02;
	}

	public void setpCrc02(byte[] pCrc02) {
		this.pCrc02 = pCrc02;
	}

	public byte[] getpCrc03() {
		return pCrc03;
	}

	public void setpCrc03(byte[] pCrc03) {
		this.pCrc03 = pCrc03;
	}

	public byte[] getpCrc04() {
		return pCrc04;
	}

	public void setpCrc04(byte[] pCrc04) {
		this.pCrc04 = pCrc04;
	}

	public byte[] getpCrc05() {
		return pCrc05;
	}

	public void setpCrc05(byte[] pCrc05) {
		this.pCrc05 = pCrc05;
	}

	public byte[] getpCrc06() {
		return pCrc06;
	}

	public void setpCrc06(byte[] pCrc06) {
		this.pCrc06 = pCrc06;
	}

	public byte[] getpCrc07() {
		return pCrc07;
	}

	public void setpCrc07(byte[] pCrc07) {
		this.pCrc07 = pCrc07;
	}

	public byte[] getpCrc08() {
		return pCrc08;
	}

	public void setpCrc08(byte[] pCrc08) {
		this.pCrc08 = pCrc08;
	}

	public byte[] getpCrc09() {
		return pCrc09;
	}

	public void setpCrc09(byte[] pCrc09) {
		this.pCrc09 = pCrc09;
	}

	public byte[] getpCrc10() {
		return pCrc10;
	}

	public void setpCrc10(byte[] pCrc10) {
		this.pCrc10 = pCrc10;
	}

	public byte[] getpCrc11() {
		return pCrc11;
	}

	public void setpCrc11(byte[] pCrc11) {
		this.pCrc11 = pCrc11;
	}

	public byte[] getpCrc12() {
		return pCrc12;
	}

	public void setpCrc12(byte[] pCrc12) {
		this.pCrc12 = pCrc12;
	}

	public byte[] getpCrc13() {
		return pCrc13;
	}

	public void setpCrc13(byte[] pCrc13) {
		this.pCrc13 = pCrc13;
	}

	public byte[] getpCrc14() {
		return pCrc14;
	}

	public void setpCrc14(byte[] pCrc14) {
		this.pCrc14 = pCrc14;
	}

	public byte[] getpCrc15() {
		return pCrc15;
	}

	public void setpCrc15(byte[] pCrc15) {
		this.pCrc15 = pCrc15;
	}

	public byte[] getpCrc16() {
		return pCrc16;
	}

	public void setpCrc16(byte[] pCrc16) {
		this.pCrc16 = pCrc16;
	}

	public byte[] getpCrc17() {
		return pCrc17;
	}

	public void setpCrc17(byte[] pCrc17) {
		this.pCrc17 = pCrc17;
	}

	public byte[] getpCrc18() {
		return pCrc18;
	}

	public void setpCrc18(byte[] pCrc18) {
		this.pCrc18 = pCrc18;
	}

	public byte[] getpCrc19() {
		return pCrc19;
	}

	public void setpCrc19(byte[] pCrc19) {
		this.pCrc19 = pCrc19;
	}

	public byte[] getpCrc20() {
		return pCrc20;
	}

	public void setpCrc20(byte[] pCrc20) {
		this.pCrc20 = pCrc20;
	}

	public byte[] getpCrc21() {
		return pCrc21;
	}

	public void setpCrc21(byte[] pCrc21) {
		this.pCrc21 = pCrc21;
	}

	public byte[] getpCrc22() {
		return pCrc22;
	}

	public void setpCrc22(byte[] pCrc22) {
		this.pCrc22 = pCrc22;
	}

	public byte[] getpCrc23() {
		return pCrc23;
	}

	public void setpCrc23(byte[] pCrc23) {
		this.pCrc23 = pCrc23;
	}

	public byte[] getpCrc24() {
		return pCrc24;
	}

	public void setpCrc24(byte[] pCrc24) {
		this.pCrc24 = pCrc24;
	}

	public byte[] getpCrc25() {
		return pCrc25;
	}

	public void setpCrc25(byte[] pCrc25) {
		this.pCrc25 = pCrc25;
	}

	public byte[] getpCrc26() {
		return pCrc26;
	}

	public void setpCrc26(byte[] pCrc26) {
		this.pCrc26 = pCrc26;
	}

	public byte[] getpCrc27() {
		return pCrc27;
	}

	public void setpCrc27(byte[] pCrc27) {
		this.pCrc27 = pCrc27;
	}

	public byte[] getpCrc28() {
		return pCrc28;
	}

	public void setpCrc28(byte[] pCrc28) {
		this.pCrc28 = pCrc28;
	}

	public byte[] getpCrc29() {
		return pCrc29;
	}

	public void setpCrc29(byte[] pCrc29) {
		this.pCrc29 = pCrc29;
	}

	public int getpChu00() {
		return pChu00;
	}

	public void setpChu00(int pChu00) {
		this.pChu00 = pChu00;
	}

	public int getpChu01() {
		return pChu01;
	}

	public void setpChu01(int pChu01) {
		this.pChu01 = pChu01;
	}

	public int getpChu02() {
		return pChu02;
	}

	public void setpChu02(int pChu02) {
		this.pChu02 = pChu02;
	}

	public int getpChu03() {
		return pChu03;
	}

	public void setpChu03(int pChu03) {
		this.pChu03 = pChu03;
	}

	public int getpChu04() {
		return pChu04;
	}

	public void setpChu04(int pChu04) {
		this.pChu04 = pChu04;
	}

	public int getpChu05() {
		return pChu05;
	}

	public void setpChu05(int pChu05) {
		this.pChu05 = pChu05;
	}

	public int getpChu06() {
		return pChu06;
	}

	public void setpChu06(int pChu06) {
		this.pChu06 = pChu06;
	}

	public int getpChu07() {
		return pChu07;
	}

	public void setpChu07(int pChu07) {
		this.pChu07 = pChu07;
	}

	public int getpChu08() {
		return pChu08;
	}

	public void setpChu08(int pChu08) {
		this.pChu08 = pChu08;
	}

	public int getpChu09() {
		return pChu09;
	}

	public void setpChu09(int pChu09) {
		this.pChu09 = pChu09;
	}

	public int getpChu10() {
		return pChu10;
	}

	public void setpChu10(int pChu10) {
		this.pChu10 = pChu10;
	}

	public int getpChu11() {
		return pChu11;
	}

	public void setpChu11(int pChu11) {
		this.pChu11 = pChu11;
	}

	public int getpChu12() {
		return pChu12;
	}

	public void setpChu12(int pChu12) {
		this.pChu12 = pChu12;
	}

	public int getpChu13() {
		return pChu13;
	}

	public void setpChu13(int pChu13) {
		this.pChu13 = pChu13;
	}

	public int getpChu14() {
		return pChu14;
	}

	public void setpChu14(int pChu14) {
		this.pChu14 = pChu14;
	}

	public int getpChu15() {
		return pChu15;
	}

	public void setpChu15(int pChu15) {
		this.pChu15 = pChu15;
	}

	public int getpChu16() {
		return pChu16;
	}

	public void setpChu16(int pChu16) {
		this.pChu16 = pChu16;
	}

	public int getpChu17() {
		return pChu17;
	}

	public void setpChu17(int pChu17) {
		this.pChu17 = pChu17;
	}

	public int getpChu18() {
		return pChu18;
	}

	public void setpChu18(int pChu18) {
		this.pChu18 = pChu18;
	}

	public int getpChu19() {
		return pChu19;
	}

	public void setpChu19(int pChu19) {
		this.pChu19 = pChu19;
	}

	public int getpChu20() {
		return pChu20;
	}

	public void setpChu20(int pChu20) {
		this.pChu20 = pChu20;
	}

	public int getpChu21() {
		return pChu21;
	}

	public void setpChu21(int pChu21) {
		this.pChu21 = pChu21;
	}

	public int getpChu22() {
		return pChu22;
	}

	public void setpChu22(int pChu22) {
		this.pChu22 = pChu22;
	}

	public int getpChu23() {
		return pChu23;
	}

	public void setpChu23(int pChu23) {
		this.pChu23 = pChu23;
	}

	public int getpChu24() {
		return pChu24;
	}

	public void setpChu24(int pChu24) {
		this.pChu24 = pChu24;
	}

	public int getpChu25() {
		return pChu25;
	}

	public void setpChu25(int pChu25) {
		this.pChu25 = pChu25;
	}

	public int getpChu26() {
		return pChu26;
	}

	public void setpChu26(int pChu26) {
		this.pChu26 = pChu26;
	}

	public int getpChu27() {
		return pChu27;
	}

	public void setpChu27(int pChu27) {
		this.pChu27 = pChu27;
	}

	public int getpChu28() {
		return pChu28;
	}

	public void setpChu28(int pChu28) {
		this.pChu28 = pChu28;
	}

	public int getpChu29() {
		return pChu29;
	}

	public void setpChu29(int pChu29) {
		this.pChu29 = pChu29;
	}

	public int getId00() {
		return id00;
	}

	public void setId00(int id00) {
		this.id00 = id00;
	}

	public int getId01() {
		return id01;
	}

	public void setId01(int id01) {
		this.id01 = id01;
	}

	public int getId02() {
		return id02;
	}

	public void setId02(int id02) {
		this.id02 = id02;
	}

	public int getId03() {
		return id03;
	}

	public void setId03(int id03) {
		this.id03 = id03;
	}

	public int getId04() {
		return id04;
	}

	public void setId04(int id04) {
		this.id04 = id04;
	}

	public int getId05() {
		return id05;
	}

	public void setId05(int id05) {
		this.id05 = id05;
	}

	public int getId06() {
		return id06;
	}

	public void setId06(int id06) {
		this.id06 = id06;
	}

	public int getId07() {
		return id07;
	}

	public void setId07(int id07) {
		this.id07 = id07;
	}

	public int getId08() {
		return id08;
	}

	public void setId08(int id08) {
		this.id08 = id08;
	}

	public int getId09() {
		return id09;
	}

	public void setId09(int id09) {
		this.id09 = id09;
	}

	public int getId10() {
		return id10;
	}

	public void setId10(int id10) {
		this.id10 = id10;
	}

	public int getId11() {
		return id11;
	}

	public void setId11(int id11) {
		this.id11 = id11;
	}

	public int getId12() {
		return id12;
	}

	public void setId12(int id12) {
		this.id12 = id12;
	}

	public int getId13() {
		return id13;
	}

	public void setId13(int id13) {
		this.id13 = id13;
	}

	public int getId14() {
		return id14;
	}

	public void setId14(int id14) {
		this.id14 = id14;
	}

	public int getId15() {
		return id15;
	}

	public void setId15(int id15) {
		this.id15 = id15;
	}

	public int getId16() {
		return id16;
	}

	public void setId16(int id16) {
		this.id16 = id16;
	}

	public int getId17() {
		return id17;
	}

	public void setId17(int id17) {
		this.id17 = id17;
	}

	public int getId18() {
		return id18;
	}

	public void setId18(int id18) {
		this.id18 = id18;
	}

	public int getId19() {
		return id19;
	}

	public void setId19(int id19) {
		this.id19 = id19;
	}

	public int getId20() {
		return id20;
	}

	public void setId20(int id20) {
		this.id20 = id20;
	}

	public int getId21() {
		return id21;
	}

	public void setId21(int id21) {
		this.id21 = id21;
	}

	public int getId22() {
		return id22;
	}

	public void setId22(int id22) {
		this.id22 = id22;
	}

	public int getId23() {
		return id23;
	}

	public void setId23(int id23) {
		this.id23 = id23;
	}

	public int getId24() {
		return id24;
	}

	public void setId24(int id24) {
		this.id24 = id24;
	}

	public int getId25() {
		return id25;
	}

	public void setId25(int id25) {
		this.id25 = id25;
	}

	public int getId26() {
		return id26;
	}

	public void setId26(int id26) {
		this.id26 = id26;
	}

	public int getId27() {
		return id27;
	}

	public void setId27(int id27) {
		this.id27 = id27;
	}

	public int getId28() {
		return id28;
	}

	public void setId28(int id28) {
		this.id28 = id28;
	}

	public int getId29() {
		return id29;
	}

	public void setId29(int id29) {
		this.id29 = id29;
	}

	public byte[] getOcc00() {
		return occ00;
	}

	public void setOcc00(byte[] occ00) {
		this.occ00 = occ00;
	}

	public byte[] getOcc01() {
		return occ01;
	}

	public void setOcc01(byte[] occ01) {
		this.occ01 = occ01;
	}

	public byte[] getOcc02() {
		return occ02;
	}

	public void setOcc02(byte[] occ02) {
		this.occ02 = occ02;
	}

	public byte[] getOcc03() {
		return occ03;
	}

	public void setOcc03(byte[] occ03) {
		this.occ03 = occ03;
	}

	public byte[] getOcc04() {
		return occ04;
	}

	public void setOcc04(byte[] occ04) {
		this.occ04 = occ04;
	}

	public byte[] getOcc05() {
		return occ05;
	}

	public void setOcc05(byte[] occ05) {
		this.occ05 = occ05;
	}

	public byte[] getOcc06() {
		return occ06;
	}

	public void setOcc06(byte[] occ06) {
		this.occ06 = occ06;
	}

	public byte[] getOcc07() {
		return occ07;
	}

	public void setOcc07(byte[] occ07) {
		this.occ07 = occ07;
	}

	public byte[] getOcc08() {
		return occ08;
	}

	public void setOcc08(byte[] occ08) {
		this.occ08 = occ08;
	}

	public byte[] getOcc09() {
		return occ09;
	}

	public void setOcc09(byte[] occ09) {
		this.occ09 = occ09;
	}

	public byte[] getOcc10() {
		return occ10;
	}

	public void setOcc10(byte[] occ10) {
		this.occ10 = occ10;
	}

	public byte[] getOcc11() {
		return occ11;
	}

	public void setOcc11(byte[] occ11) {
		this.occ11 = occ11;
	}

	public byte[] getOcc12() {
		return occ12;
	}

	public void setOcc12(byte[] occ12) {
		this.occ12 = occ12;
	}

	public byte[] getOcc13() {
		return occ13;
	}

	public void setOcc13(byte[] occ13) {
		this.occ13 = occ13;
	}

	public byte[] getOcc14() {
		return occ14;
	}

	public void setOcc14(byte[] occ14) {
		this.occ14 = occ14;
	}

	public byte[] getOcc15() {
		return occ15;
	}

	public void setOcc15(byte[] occ15) {
		this.occ15 = occ15;
	}

	public byte[] getOcc16() {
		return occ16;
	}

	public void setOcc16(byte[] occ16) {
		this.occ16 = occ16;
	}

	public byte[] getOcc17() {
		return occ17;
	}

	public void setOcc17(byte[] occ17) {
		this.occ17 = occ17;
	}

	public byte[] getOcc18() {
		return occ18;
	}

	public void setOcc18(byte[] occ18) {
		this.occ18 = occ18;
	}

	public byte[] getOcc19() {
		return occ19;
	}

	public void setOcc19(byte[] occ19) {
		this.occ19 = occ19;
	}

	public byte[] getOcc20() {
		return occ20;
	}

	public void setOcc20(byte[] occ20) {
		this.occ20 = occ20;
	}

	public byte[] getOcc21() {
		return occ21;
	}

	public void setOcc21(byte[] occ21) {
		this.occ21 = occ21;
	}

	public byte[] getOcc22() {
		return occ22;
	}

	public void setOcc22(byte[] occ22) {
		this.occ22 = occ22;
	}

	public byte[] getOcc23() {
		return occ23;
	}

	public void setOcc23(byte[] occ23) {
		this.occ23 = occ23;
	}

	public byte[] getOcc24() {
		return occ24;
	}

	public void setOcc24(byte[] occ24) {
		this.occ24 = occ24;
	}

	public byte[] getOcc25() {
		return occ25;
	}

	public void setOcc25(byte[] occ25) {
		this.occ25 = occ25;
	}

	public byte[] getOcc26() {
		return occ26;
	}

	public void setOcc26(byte[] occ26) {
		this.occ26 = occ26;
	}

	public byte[] getOcc27() {
		return occ27;
	}

	public void setOcc27(byte[] occ27) {
		this.occ27 = occ27;
	}

	public byte[] getOcc28() {
		return occ28;
	}

	public void setOcc28(byte[] occ28) {
		this.occ28 = occ28;
	}

	public byte[] getOcc29() {
		return occ29;
	}

	public void setOcc29(byte[] occ29) {
		this.occ29 = occ29;
	}

	public byte[] getOcc30() {
		return occ30;
	}

	public void setOcc30(byte[] occ30) {
		this.occ30 = occ30;
	}

	public byte[] getOcc31() {
		return occ31;
	}

	public void setOcc31(byte[] occ31) {
		this.occ31 = occ31;
	}

}
