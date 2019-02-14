package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class B2Eps {

	private int outputOffDelta0;
	private int outputOffDelta1;
	private int outputOffDelta2;
	private int outputOffDelta3;
	private int outputOffDelta4;
	private int outputOffDelta5;
	private int outputOffDelta6;
	private int outputOffDelta7;
	private int outputOnDelta0;
	private int outputOnDelta1;
	private int outputOnDelta2;
	private int outputOnDelta3;
	private int outputOnDelta4;
	private int outputOnDelta5;
	private int outputOnDelta6;
	private int outputOnDelta7;
	private int wdtCspPingsLeft0;
	private int wdtCspPingsLeft1;
	private int bootCause;
	private int curSun;
	private int curIn0;
	private int curIn1;
	private int curIn2;
	private int curOut0;
	private int curOut1;
	private int curOut2;
	private int curOut3;
	private int curOut4;
	private int curOut5;
	private int curSys;
	private int temp0;
	private int temp1;
	private int temp2;
	private int temp3;
	private int temp4;
	private int temp5;
	private int battMode;
	private int pptMode;
	private long counterBoot;
	private int latchup0;
	private int latchup1;
	private int latchup2;
	private int latchup3;
	private int latchup4;
	private int latchup5;
	private long counterWdtCsp0;
	private long counterWdtCsp1;
	private long counterWdtGnd;
	private long counterWdtI2C;
	private int output0;
	private int output1;
	private int output2;
	private int output3;
	private int output4;
	private int output5;
	private int output6;
	private int output7;
	private long wdtGndTimeLeft;
	private long wdtI2CTimeLeft;
	private int vBatt;
	private int vboostV0;
	private int vboostV1;
	private int vboostV2;
	private int wdtCspc0;
	private int wdtCspc1;

	public B2Eps(BitInputStream bis) throws IOException {
		outputOffDelta0 = bis.readUnsignedInt(16);
		outputOffDelta1 = bis.readUnsignedInt(16);
		outputOffDelta2 = bis.readUnsignedInt(16);
		outputOffDelta3 = bis.readUnsignedInt(16);
		outputOffDelta4 = bis.readUnsignedInt(16);
		outputOffDelta5 = bis.readUnsignedInt(16);
		outputOffDelta6 = bis.readUnsignedInt(16);
		outputOffDelta7 = bis.readUnsignedInt(16);

		outputOnDelta0 = bis.readUnsignedInt(16);
		outputOnDelta1 = bis.readUnsignedInt(16);
		outputOnDelta2 = bis.readUnsignedInt(16);
		outputOnDelta3 = bis.readUnsignedInt(16);
		outputOnDelta4 = bis.readUnsignedInt(16);
		outputOnDelta5 = bis.readUnsignedInt(16);
		outputOnDelta6 = bis.readUnsignedInt(16);
		outputOnDelta7 = bis.readUnsignedInt(16);

		wdtCspPingsLeft0 = bis.readUnsignedInt(8);
		wdtCspPingsLeft1 = bis.readUnsignedInt(8);

		bootCause = bis.readUnsignedInt(8);
		curSun = bis.readUnsignedInt(16);
		curIn0 = bis.readUnsignedInt(16);
		curIn1 = bis.readUnsignedInt(16);
		curIn2 = bis.readUnsignedInt(16);
		curOut0 = bis.readUnsignedInt(16);
		curOut1 = bis.readUnsignedInt(16);
		curOut2 = bis.readUnsignedInt(16);
		curOut3 = bis.readUnsignedInt(16);
		curOut4 = bis.readUnsignedInt(16);
		curOut5 = bis.readUnsignedInt(16);
		curSys = bis.readUnsignedInt(16);
		temp0 = bis.readUnsignedInt(16);
		temp1 = bis.readUnsignedInt(16);
		temp2 = bis.readUnsignedInt(16);
		temp3 = bis.readUnsignedInt(16);
		temp4 = bis.readUnsignedInt(16);
		temp5 = bis.readUnsignedInt(16);

		battMode = bis.readUnsignedInt(8);
		pptMode = bis.readUnsignedInt(8);
		counterBoot = bis.readUnsignedLong(32);

		latchup0 = bis.readUnsignedInt(16);
		latchup1 = bis.readUnsignedInt(16);
		latchup2 = bis.readUnsignedInt(16);
		latchup3 = bis.readUnsignedInt(16);
		latchup4 = bis.readUnsignedInt(16);
		latchup5 = bis.readUnsignedInt(16);

		counterWdtCsp0 = bis.readUnsignedLong(32);
		counterWdtCsp1 = bis.readUnsignedLong(32);
		counterWdtGnd = bis.readUnsignedLong(32);
		counterWdtI2C = bis.readUnsignedLong(32);

		output0 = bis.readUnsignedInt(8);
		output1 = bis.readUnsignedInt(8);
		output2 = bis.readUnsignedInt(8);
		output3 = bis.readUnsignedInt(8);
		output4 = bis.readUnsignedInt(8);
		output5 = bis.readUnsignedInt(8);
		output6 = bis.readUnsignedInt(8);
		output7 = bis.readUnsignedInt(8);

		wdtGndTimeLeft = bis.readUnsignedLong(32);
		wdtI2CTimeLeft = bis.readUnsignedLong(32);

		vBatt = bis.readUnsignedInt(16);

		vboostV0 = bis.readUnsignedInt(16);
		vboostV1 = bis.readUnsignedInt(16);
		vboostV2 = bis.readUnsignedInt(16);

		wdtCspc0 = bis.readUnsignedInt(8);
		wdtCspc1 = bis.readUnsignedInt(8);
	}

	public int getOutputOffDelta0() {
		return outputOffDelta0;
	}

	public void setOutputOffDelta0(int outputOffDelta0) {
		this.outputOffDelta0 = outputOffDelta0;
	}

	public int getOutputOffDelta1() {
		return outputOffDelta1;
	}

	public void setOutputOffDelta1(int outputOffDelta1) {
		this.outputOffDelta1 = outputOffDelta1;
	}

	public int getOutputOffDelta2() {
		return outputOffDelta2;
	}

	public void setOutputOffDelta2(int outputOffDelta2) {
		this.outputOffDelta2 = outputOffDelta2;
	}

	public int getOutputOffDelta3() {
		return outputOffDelta3;
	}

	public void setOutputOffDelta3(int outputOffDelta3) {
		this.outputOffDelta3 = outputOffDelta3;
	}

	public int getOutputOffDelta4() {
		return outputOffDelta4;
	}

	public void setOutputOffDelta4(int outputOffDelta4) {
		this.outputOffDelta4 = outputOffDelta4;
	}

	public int getOutputOffDelta5() {
		return outputOffDelta5;
	}

	public void setOutputOffDelta5(int outputOffDelta5) {
		this.outputOffDelta5 = outputOffDelta5;
	}

	public int getOutputOffDelta6() {
		return outputOffDelta6;
	}

	public void setOutputOffDelta6(int outputOffDelta6) {
		this.outputOffDelta6 = outputOffDelta6;
	}

	public int getOutputOffDelta7() {
		return outputOffDelta7;
	}

	public void setOutputOffDelta7(int outputOffDelta7) {
		this.outputOffDelta7 = outputOffDelta7;
	}

	public int getOutputOnDelta0() {
		return outputOnDelta0;
	}

	public void setOutputOnDelta0(int outputOnDelta0) {
		this.outputOnDelta0 = outputOnDelta0;
	}

	public int getOutputOnDelta1() {
		return outputOnDelta1;
	}

	public void setOutputOnDelta1(int outputOnDelta1) {
		this.outputOnDelta1 = outputOnDelta1;
	}

	public int getOutputOnDelta2() {
		return outputOnDelta2;
	}

	public void setOutputOnDelta2(int outputOnDelta2) {
		this.outputOnDelta2 = outputOnDelta2;
	}

	public int getOutputOnDelta3() {
		return outputOnDelta3;
	}

	public void setOutputOnDelta3(int outputOnDelta3) {
		this.outputOnDelta3 = outputOnDelta3;
	}

	public int getOutputOnDelta4() {
		return outputOnDelta4;
	}

	public void setOutputOnDelta4(int outputOnDelta4) {
		this.outputOnDelta4 = outputOnDelta4;
	}

	public int getOutputOnDelta5() {
		return outputOnDelta5;
	}

	public void setOutputOnDelta5(int outputOnDelta5) {
		this.outputOnDelta5 = outputOnDelta5;
	}

	public int getOutputOnDelta6() {
		return outputOnDelta6;
	}

	public void setOutputOnDelta6(int outputOnDelta6) {
		this.outputOnDelta6 = outputOnDelta6;
	}

	public int getOutputOnDelta7() {
		return outputOnDelta7;
	}

	public void setOutputOnDelta7(int outputOnDelta7) {
		this.outputOnDelta7 = outputOnDelta7;
	}

	public int getWdtCspPingsLeft0() {
		return wdtCspPingsLeft0;
	}

	public void setWdtCspPingsLeft0(int wdtCspPingsLeft0) {
		this.wdtCspPingsLeft0 = wdtCspPingsLeft0;
	}

	public int getWdtCspPingsLeft1() {
		return wdtCspPingsLeft1;
	}

	public void setWdtCspPingsLeft1(int wdtCspPingsLeft1) {
		this.wdtCspPingsLeft1 = wdtCspPingsLeft1;
	}

	public int getBootCause() {
		return bootCause;
	}

	public void setBootCause(int bootCause) {
		this.bootCause = bootCause;
	}

	public int getCurSun() {
		return curSun;
	}

	public void setCurSun(int curSun) {
		this.curSun = curSun;
	}

	public int getCurIn0() {
		return curIn0;
	}

	public void setCurIn0(int curIn0) {
		this.curIn0 = curIn0;
	}

	public int getCurIn1() {
		return curIn1;
	}

	public void setCurIn1(int curIn1) {
		this.curIn1 = curIn1;
	}

	public int getCurIn2() {
		return curIn2;
	}

	public void setCurIn2(int curIn2) {
		this.curIn2 = curIn2;
	}

	public int getCurOut0() {
		return curOut0;
	}

	public void setCurOut0(int curOut0) {
		this.curOut0 = curOut0;
	}

	public int getCurOut1() {
		return curOut1;
	}

	public void setCurOut1(int curOut1) {
		this.curOut1 = curOut1;
	}

	public int getCurOut2() {
		return curOut2;
	}

	public void setCurOut2(int curOut2) {
		this.curOut2 = curOut2;
	}

	public int getCurOut3() {
		return curOut3;
	}

	public void setCurOut3(int curOut3) {
		this.curOut3 = curOut3;
	}

	public int getCurOut4() {
		return curOut4;
	}

	public void setCurOut4(int curOut4) {
		this.curOut4 = curOut4;
	}

	public int getCurOut5() {
		return curOut5;
	}

	public void setCurOut5(int curOut5) {
		this.curOut5 = curOut5;
	}

	public int getCurSys() {
		return curSys;
	}

	public void setCurSys(int curSys) {
		this.curSys = curSys;
	}

	public int getTemp0() {
		return temp0;
	}

	public void setTemp0(int temp0) {
		this.temp0 = temp0;
	}

	public int getTemp1() {
		return temp1;
	}

	public void setTemp1(int temp1) {
		this.temp1 = temp1;
	}

	public int getTemp2() {
		return temp2;
	}

	public void setTemp2(int temp2) {
		this.temp2 = temp2;
	}

	public int getTemp3() {
		return temp3;
	}

	public void setTemp3(int temp3) {
		this.temp3 = temp3;
	}

	public int getTemp4() {
		return temp4;
	}

	public void setTemp4(int temp4) {
		this.temp4 = temp4;
	}

	public int getTemp5() {
		return temp5;
	}

	public void setTemp5(int temp5) {
		this.temp5 = temp5;
	}

	public int getBattMode() {
		return battMode;
	}

	public void setBattMode(int battMode) {
		this.battMode = battMode;
	}

	public int getPptMode() {
		return pptMode;
	}

	public void setPptMode(int pptMode) {
		this.pptMode = pptMode;
	}

	public long getCounterBoot() {
		return counterBoot;
	}

	public void setCounterBoot(long counterBoot) {
		this.counterBoot = counterBoot;
	}

	public int getLatchup0() {
		return latchup0;
	}

	public void setLatchup0(int latchup0) {
		this.latchup0 = latchup0;
	}

	public int getLatchup1() {
		return latchup1;
	}

	public void setLatchup1(int latchup1) {
		this.latchup1 = latchup1;
	}

	public int getLatchup2() {
		return latchup2;
	}

	public void setLatchup2(int latchup2) {
		this.latchup2 = latchup2;
	}

	public int getLatchup3() {
		return latchup3;
	}

	public void setLatchup3(int latchup3) {
		this.latchup3 = latchup3;
	}

	public int getLatchup4() {
		return latchup4;
	}

	public void setLatchup4(int latchup4) {
		this.latchup4 = latchup4;
	}

	public int getLatchup5() {
		return latchup5;
	}

	public void setLatchup5(int latchup5) {
		this.latchup5 = latchup5;
	}

	public long getCounterWdtCsp0() {
		return counterWdtCsp0;
	}

	public void setCounterWdtCsp0(long counterWdtCsp0) {
		this.counterWdtCsp0 = counterWdtCsp0;
	}

	public long getCounterWdtCsp1() {
		return counterWdtCsp1;
	}

	public void setCounterWdtCsp1(long counterWdtCsp1) {
		this.counterWdtCsp1 = counterWdtCsp1;
	}

	public long getCounterWdtGnd() {
		return counterWdtGnd;
	}

	public void setCounterWdtGnd(long counterWdtGnd) {
		this.counterWdtGnd = counterWdtGnd;
	}

	public long getCounterWdtI2C() {
		return counterWdtI2C;
	}

	public void setCounterWdtI2C(long counterWdtI2C) {
		this.counterWdtI2C = counterWdtI2C;
	}

	public int getOutput0() {
		return output0;
	}

	public void setOutput0(int output0) {
		this.output0 = output0;
	}

	public int getOutput1() {
		return output1;
	}

	public void setOutput1(int output1) {
		this.output1 = output1;
	}

	public int getOutput2() {
		return output2;
	}

	public void setOutput2(int output2) {
		this.output2 = output2;
	}

	public int getOutput3() {
		return output3;
	}

	public void setOutput3(int output3) {
		this.output3 = output3;
	}

	public int getOutput4() {
		return output4;
	}

	public void setOutput4(int output4) {
		this.output4 = output4;
	}

	public int getOutput5() {
		return output5;
	}

	public void setOutput5(int output5) {
		this.output5 = output5;
	}

	public int getOutput6() {
		return output6;
	}

	public void setOutput6(int output6) {
		this.output6 = output6;
	}

	public int getOutput7() {
		return output7;
	}

	public void setOutput7(int output7) {
		this.output7 = output7;
	}

	public long getWdtGndTimeLeft() {
		return wdtGndTimeLeft;
	}

	public void setWdtGndTimeLeft(long wdtGndTimeLeft) {
		this.wdtGndTimeLeft = wdtGndTimeLeft;
	}

	public long getWdtI2CTimeLeft() {
		return wdtI2CTimeLeft;
	}

	public void setWdtI2CTimeLeft(long wdtI2CTimeLeft) {
		this.wdtI2CTimeLeft = wdtI2CTimeLeft;
	}

	public int getvBatt() {
		return vBatt;
	}

	public void setvBatt(int vBatt) {
		this.vBatt = vBatt;
	}

	public int getVboostV0() {
		return vboostV0;
	}

	public void setVboostV0(int vboostV0) {
		this.vboostV0 = vboostV0;
	}

	public int getVboostV1() {
		return vboostV1;
	}

	public void setVboostV1(int vboostV1) {
		this.vboostV1 = vboostV1;
	}

	public int getVboostV2() {
		return vboostV2;
	}

	public void setVboostV2(int vboostV2) {
		this.vboostV2 = vboostV2;
	}

	public int getWdtCspc0() {
		return wdtCspc0;
	}

	public void setWdtCspc0(int wdtCspc0) {
		this.wdtCspc0 = wdtCspc0;
	}

	public int getWdtCspc1() {
		return wdtCspc1;
	}

	public void setWdtCspc1(int wdtCspc1) {
		this.wdtCspc1 = wdtCspc1;
	}

}
