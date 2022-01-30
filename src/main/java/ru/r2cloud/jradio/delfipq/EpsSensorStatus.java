package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class EpsSensorStatus {

	private SensorBitStatus batteryINAStatus;
	private SensorBitStatus batteryGGStatus;
	private SensorBitStatus internalINAStatus;
	private SensorBitStatus unregulatedINAStatus;
	private SensorBitStatus bus1INAStatus;
	private SensorBitStatus bus2INAStatus;
	private SensorBitStatus bus3INAStatus;
	private SensorBitStatus bus4INAStatus;
	private SensorBitStatus bus4Error;
	private SensorBitStatus bus3Error;
	private SensorBitStatus bus2Error;
	private SensorBitStatus bus1Error;
	private EpsBusState bus4State;
	private EpsBusState bus3State;
	private EpsBusState bus2State;
	private EpsBusState bus1State;
	private SensorBitStatus panelYpINAStatus;
	private SensorBitStatus panelYmINAStatus;
	private SensorBitStatus panelXpINAStatus;
	private SensorBitStatus panelXmINAStatus;
	private SensorBitStatus panelYpTMPStatus;
	private SensorBitStatus panelYmTMPStatus;
	private SensorBitStatus panelXpTMPStatus;
	private SensorBitStatus panelXmTMPStatus;
	private SensorBitStatus mpptYpINAStatus;
	private SensorBitStatus mpptYmINAStatus;
	private SensorBitStatus mpptXpINAStatus;
	private SensorBitStatus mpptXmINAStatus;
	private SensorBitStatus cellYpINAStatus;
	private SensorBitStatus cellYmINAStatus;
	private SensorBitStatus cellXpINAStatus;
	private SensorBitStatus cellXmINAStatus;

	public EpsSensorStatus() {
		// do nothing
	}

	public EpsSensorStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		batteryINAStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0b1);
		batteryGGStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0b1);
		internalINAStatus = SensorBitStatus.valueOfCode((raw >> 5) & 0b1);
		unregulatedINAStatus = SensorBitStatus.valueOfCode((raw >> 4) & 0b1);
		bus1INAStatus = SensorBitStatus.valueOfCode((raw >> 3) & 0b1);
		bus2INAStatus = SensorBitStatus.valueOfCode((raw >> 2) & 0b1);
		bus3INAStatus = SensorBitStatus.valueOfCode((raw >> 1) & 0b1);
		bus4INAStatus = SensorBitStatus.valueOfCode(raw & 0b1);

		raw = dis.readUnsignedByte();
		bus4Error = SensorBitStatus.valueOfCode((raw >> 7) & 0b1);
		bus3Error = SensorBitStatus.valueOfCode((raw >> 6) & 0b1);
		bus2Error = SensorBitStatus.valueOfCode((raw >> 5) & 0b1);
		bus1Error = SensorBitStatus.valueOfCode((raw >> 4) & 0b1);
		bus4State = EpsBusState.valueOfCode((raw >> 3) & 0b1);
		bus3State = EpsBusState.valueOfCode((raw >> 2) & 0b1);
		bus2State = EpsBusState.valueOfCode((raw >> 1) & 0b1);
		bus1State = EpsBusState.valueOfCode(raw & 0b1);

		raw = dis.readUnsignedByte();
		panelYpINAStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0b1);
		panelYmINAStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0b1);
		panelXpINAStatus = SensorBitStatus.valueOfCode((raw >> 5) & 0b1);
		panelXmINAStatus = SensorBitStatus.valueOfCode((raw >> 4) & 0b1);
		panelYpTMPStatus = SensorBitStatus.valueOfCode((raw >> 3) & 0b1);
		panelYmTMPStatus = SensorBitStatus.valueOfCode((raw >> 2) & 0b1);
		panelXpTMPStatus = SensorBitStatus.valueOfCode((raw >> 1) & 0b1);
		panelXmTMPStatus = SensorBitStatus.valueOfCode(raw & 0b1);

		raw = dis.readUnsignedByte();
		mpptYpINAStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0b1);
		mpptYmINAStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0b1);
		mpptXpINAStatus = SensorBitStatus.valueOfCode((raw >> 5) & 0b1);
		mpptXmINAStatus = SensorBitStatus.valueOfCode((raw >> 4) & 0b1);
		cellYpINAStatus = SensorBitStatus.valueOfCode((raw >> 3) & 0b1);
		cellYmINAStatus = SensorBitStatus.valueOfCode((raw >> 2) & 0b1);
		cellXpINAStatus = SensorBitStatus.valueOfCode((raw >> 1) & 0b1);
		cellXmINAStatus = SensorBitStatus.valueOfCode(raw & 0b1);
	}

	public SensorBitStatus getBatteryINAStatus() {
		return batteryINAStatus;
	}

	public void setBatteryINAStatus(SensorBitStatus batteryINAStatus) {
		this.batteryINAStatus = batteryINAStatus;
	}

	public SensorBitStatus getBatteryGGStatus() {
		return batteryGGStatus;
	}

	public void setBatteryGGStatus(SensorBitStatus batteryGGStatus) {
		this.batteryGGStatus = batteryGGStatus;
	}

	public SensorBitStatus getInternalINAStatus() {
		return internalINAStatus;
	}

	public void setInternalINAStatus(SensorBitStatus internalINAStatus) {
		this.internalINAStatus = internalINAStatus;
	}

	public SensorBitStatus getUnregulatedINAStatus() {
		return unregulatedINAStatus;
	}

	public void setUnregulatedINAStatus(SensorBitStatus unregulatedINAStatus) {
		this.unregulatedINAStatus = unregulatedINAStatus;
	}

	public SensorBitStatus getBus1INAStatus() {
		return bus1INAStatus;
	}

	public void setBus1INAStatus(SensorBitStatus bus1inaStatus) {
		bus1INAStatus = bus1inaStatus;
	}

	public SensorBitStatus getBus2INAStatus() {
		return bus2INAStatus;
	}

	public void setBus2INAStatus(SensorBitStatus bus2inaStatus) {
		bus2INAStatus = bus2inaStatus;
	}

	public SensorBitStatus getBus3INAStatus() {
		return bus3INAStatus;
	}

	public void setBus3INAStatus(SensorBitStatus bus3inaStatus) {
		bus3INAStatus = bus3inaStatus;
	}

	public SensorBitStatus getBus4INAStatus() {
		return bus4INAStatus;
	}

	public void setBus4INAStatus(SensorBitStatus bus4inaStatus) {
		bus4INAStatus = bus4inaStatus;
	}

	public SensorBitStatus getBus4Error() {
		return bus4Error;
	}

	public void setBus4Error(SensorBitStatus bus4Error) {
		this.bus4Error = bus4Error;
	}

	public SensorBitStatus getBus3Error() {
		return bus3Error;
	}

	public void setBus3Error(SensorBitStatus bus3Error) {
		this.bus3Error = bus3Error;
	}

	public SensorBitStatus getBus2Error() {
		return bus2Error;
	}

	public void setBus2Error(SensorBitStatus bus2Error) {
		this.bus2Error = bus2Error;
	}

	public SensorBitStatus getBus1Error() {
		return bus1Error;
	}

	public void setBus1Error(SensorBitStatus bus1Error) {
		this.bus1Error = bus1Error;
	}

	public EpsBusState getBus4State() {
		return bus4State;
	}

	public void setBus4State(EpsBusState bus4State) {
		this.bus4State = bus4State;
	}

	public EpsBusState getBus3State() {
		return bus3State;
	}

	public void setBus3State(EpsBusState bus3State) {
		this.bus3State = bus3State;
	}

	public EpsBusState getBus2State() {
		return bus2State;
	}

	public void setBus2State(EpsBusState bus2State) {
		this.bus2State = bus2State;
	}

	public EpsBusState getBus1State() {
		return bus1State;
	}

	public void setBus1State(EpsBusState bus1State) {
		this.bus1State = bus1State;
	}

	public SensorBitStatus getPanelYpINAStatus() {
		return panelYpINAStatus;
	}

	public void setPanelYpINAStatus(SensorBitStatus panelYpINAStatus) {
		this.panelYpINAStatus = panelYpINAStatus;
	}

	public SensorBitStatus getPanelYmINAStatus() {
		return panelYmINAStatus;
	}

	public void setPanelYmINAStatus(SensorBitStatus panelYmINAStatus) {
		this.panelYmINAStatus = panelYmINAStatus;
	}

	public SensorBitStatus getPanelXpINAStatus() {
		return panelXpINAStatus;
	}

	public void setPanelXpINAStatus(SensorBitStatus panelXpINAStatus) {
		this.panelXpINAStatus = panelXpINAStatus;
	}

	public SensorBitStatus getPanelXmINAStatus() {
		return panelXmINAStatus;
	}

	public void setPanelXmINAStatus(SensorBitStatus panelXmINAStatus) {
		this.panelXmINAStatus = panelXmINAStatus;
	}

	public SensorBitStatus getPanelYpTMPStatus() {
		return panelYpTMPStatus;
	}

	public void setPanelYpTMPStatus(SensorBitStatus panelYpTMPStatus) {
		this.panelYpTMPStatus = panelYpTMPStatus;
	}

	public SensorBitStatus getPanelYmTMPStatus() {
		return panelYmTMPStatus;
	}

	public void setPanelYmTMPStatus(SensorBitStatus panelYmTMPStatus) {
		this.panelYmTMPStatus = panelYmTMPStatus;
	}

	public SensorBitStatus getPanelXpTMPStatus() {
		return panelXpTMPStatus;
	}

	public void setPanelXpTMPStatus(SensorBitStatus panelXpTMPStatus) {
		this.panelXpTMPStatus = panelXpTMPStatus;
	}

	public SensorBitStatus getPanelXmTMPStatus() {
		return panelXmTMPStatus;
	}

	public void setPanelXmTMPStatus(SensorBitStatus panelXmTMPStatus) {
		this.panelXmTMPStatus = panelXmTMPStatus;
	}

	public SensorBitStatus getMpptYpINAStatus() {
		return mpptYpINAStatus;
	}

	public void setMpptYpINAStatus(SensorBitStatus mpptYpINAStatus) {
		this.mpptYpINAStatus = mpptYpINAStatus;
	}

	public SensorBitStatus getMpptYmINAStatus() {
		return mpptYmINAStatus;
	}

	public void setMpptYmINAStatus(SensorBitStatus mpptYmINAStatus) {
		this.mpptYmINAStatus = mpptYmINAStatus;
	}

	public SensorBitStatus getMpptXpINAStatus() {
		return mpptXpINAStatus;
	}

	public void setMpptXpINAStatus(SensorBitStatus mpptXpINAStatus) {
		this.mpptXpINAStatus = mpptXpINAStatus;
	}

	public SensorBitStatus getMpptXmINAStatus() {
		return mpptXmINAStatus;
	}

	public void setMpptXmINAStatus(SensorBitStatus mpptXmINAStatus) {
		this.mpptXmINAStatus = mpptXmINAStatus;
	}

	public SensorBitStatus getCellYpINAStatus() {
		return cellYpINAStatus;
	}

	public void setCellYpINAStatus(SensorBitStatus cellYpINAStatus) {
		this.cellYpINAStatus = cellYpINAStatus;
	}

	public SensorBitStatus getCellYmINAStatus() {
		return cellYmINAStatus;
	}

	public void setCellYmINAStatus(SensorBitStatus cellYmINAStatus) {
		this.cellYmINAStatus = cellYmINAStatus;
	}

	public SensorBitStatus getCellXpINAStatus() {
		return cellXpINAStatus;
	}

	public void setCellXpINAStatus(SensorBitStatus cellXpINAStatus) {
		this.cellXpINAStatus = cellXpINAStatus;
	}

	public SensorBitStatus getCellXmINAStatus() {
		return cellXmINAStatus;
	}

	public void setCellXmINAStatus(SensorBitStatus cellXmINAStatus) {
		this.cellXmINAStatus = cellXmINAStatus;
	}

}
