package ru.r2cloud.jradio.pwsat2;

public enum BootReason {

	PowerOnReset, UnregulatedBrownOut, RegulatedBrownOut, ExternalReset, WatchdogReset, LockupReset, SystemRequestReset, WokenUpFromEM4, WokenUpFromEM4ByPin, BrownOutOnAnalogPower0, BrownOutOnAnalogPower1, BrownOutByBackupBODonVDD_DREG, BrownOutByBackupBODonBU_VIN, BrownOutByBackupBODOnUnregulatedPower, BrownOutByBackupBODOnRegulatedPower, WokenFromBackupMode;

	public static BootReason valueOfCode(int raw) {
		if ((raw & 0b1) == 0b1) {
			return PowerOnReset;
		}
		if ((raw & 0b0000000010000011) == 0b0000000000000010) {
			return UnregulatedBrownOut;
		}
		if ((raw & 0b0000000000011111) == 0b0000000000000100) {
			return RegulatedBrownOut;
		}
		if ((raw & 0b0000000000001011) == 0b0000000000001000) {
			return ExternalReset;
		}
		if ((raw & 0b0000000000010011) == 0b0000000000010000) {
			return WatchdogReset;
		}
		if ((raw & 0b0000011111111111) == 0b0000000000100000) {
			return LockupReset;
		}
		if ((raw & 0b0000011111011111) == 0b0000000001000000) {
			return SystemRequestReset;
		}
		if ((raw & 0b0000011110011001) == 0b0000000010000000) {
			return WokenUpFromEM4;
		}
		if ((raw & 0b0000011110011001) == 0b0000000110000000) {
			return WokenUpFromEM4ByPin;
		}
		if ((raw & 0b0000011000011111) == 0b0000001000000000) {
			return BrownOutOnAnalogPower0;
		}
		if ((raw & 0b0000011000011111) == 0b0000010000000000) {
			return BrownOutOnAnalogPower1;
		}
		if ((raw & 0b0000100000001001) == 0b0000100000000000) {
			return BrownOutByBackupBODonVDD_DREG;
		}
		if ((raw & 0b0001000000001001) == 0b0001000000000000) {
			return BrownOutByBackupBODonBU_VIN;
		}
		if ((raw & 0b0010000000001001) == 0b0010000000000000) {
			return BrownOutByBackupBODOnUnregulatedPower;
		}
		if ((raw & 0b0100000000001001) == 0b0100000000000000) {
			return BrownOutByBackupBODOnRegulatedPower;
		}
		if ((raw & 0b1000000000000001) == 0b1000000000000000) {
			return WokenFromBackupMode;
		}
		return null;
	}
}
