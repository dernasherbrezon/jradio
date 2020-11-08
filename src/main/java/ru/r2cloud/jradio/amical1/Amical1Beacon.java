package ru.r2cloud.jradio.amical1;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Amical1Beacon extends Ax25Beacon {

	private static final Pattern SEMICOLON = Pattern.compile(";");

	private M1Type m1Type;
	private AocsStatus aocsStatus;
	private PowerStatus mainPowerManagement;
	private PowerStatus redundantPowerManagement;
	private CommunicationModuleStatus vhfStatus;
	private CommunicationModuleStatus uhfStatus;
	private ComputingUnitStatus firstRedundantCpu;
	private ComputingUnitStatus secondRedundantCpu;

	private String unknownMessage;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		// skip initial =
		dis.skipBytes(1);
		byte[] data = new byte[dis.available()];
		dis.readFully(data);
		String message = new String(data, StandardCharsets.US_ASCII);
		// Only single message where comma is used:
		// [ U2];RL;[Timestamp],[CPU voltage];[Battery voltage];[CPU temperature];[Amplifier temperature]; [Flags]
		message = message.replace(',', ';');
		String[] parts = SEMICOLON.split(message);
		if (parts.length == 0) {
			throw new UncorrectableException("unknown message format");
		}
		try {
			if (parts[0].equalsIgnoreCase("M1")) {
				m1Type = new M1Type(parts);
			} else if (parts[0].equalsIgnoreCase("A1")) {
				aocsStatus = new AocsStatus(parts);
			} else if (parts[0].equalsIgnoreCase("EM")) {
				mainPowerManagement = new PowerStatus(parts);
			} else if (parts[0].equalsIgnoreCase("ER")) {
				redundantPowerManagement = new PowerStatus(parts);
			} else if (parts[0].equalsIgnoreCase("V1")) {
				vhfStatus = new CommunicationModuleStatus(parts);
			} else if (parts[0].equalsIgnoreCase("U2")) {
				uhfStatus = new CommunicationModuleStatus(parts);
			} else if (parts[0].equalsIgnoreCase("CU_R")) {
				firstRedundantCpu = new ComputingUnitStatus(parts);
			} else if (parts[0].equalsIgnoreCase("CU_L")) {
				secondRedundantCpu = new ComputingUnitStatus(parts);
			} else {
				unknownMessage = message;
			}
		} catch (UncorrectableException e) {
			// unknown sub-type received
			unknownMessage = message;
		}
	}

	public M1Type getM1Type() {
		return m1Type;
	}

	public void setM1Type(M1Type m1Type) {
		this.m1Type = m1Type;
	}

	public AocsStatus getAocsStatus() {
		return aocsStatus;
	}

	public void setAocsStatus(AocsStatus aocsStatus) {
		this.aocsStatus = aocsStatus;
	}

	public PowerStatus getMainPowerManagement() {
		return mainPowerManagement;
	}

	public void setMainPowerManagement(PowerStatus mainPowerManagement) {
		this.mainPowerManagement = mainPowerManagement;
	}

	public PowerStatus getRedundantPowerManagement() {
		return redundantPowerManagement;
	}

	public void setRedundantPowerManagement(PowerStatus redundantPowerManagement) {
		this.redundantPowerManagement = redundantPowerManagement;
	}

	public CommunicationModuleStatus getVhfStatus() {
		return vhfStatus;
	}

	public void setVhfStatus(CommunicationModuleStatus vhfStatus) {
		this.vhfStatus = vhfStatus;
	}

	public CommunicationModuleStatus getUhfStatus() {
		return uhfStatus;
	}

	public void setUhfStatus(CommunicationModuleStatus uhfStatus) {
		this.uhfStatus = uhfStatus;
	}

	public ComputingUnitStatus getFirstRedundantCpu() {
		return firstRedundantCpu;
	}

	public void setFirstRedundantCpu(ComputingUnitStatus firstRedundantCpu) {
		this.firstRedundantCpu = firstRedundantCpu;
	}

	public ComputingUnitStatus getSecondRedundantCpu() {
		return secondRedundantCpu;
	}

	public void setSecondRedundantCpu(ComputingUnitStatus secondRedundantCpu) {
		this.secondRedundantCpu = secondRedundantCpu;
	}

	public String getUnknownMessage() {
		return unknownMessage;
	}

	public void setUnknownMessage(String unknownMessage) {
		this.unknownMessage = unknownMessage;
	}

}
