package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmForStatus {

	private float for0Current; // FOR 0 current
	private float for1Current; // FOR 1 current
	private float for2Current; // FOR 2 current
	private float forI2C0Current; // I2C0 current
	private boolean for0Enabled; // FOR 0 enabled
	private boolean for1Enabled; // FOR 1 enabled
	private boolean for2Enabled; // FOR 2 enabled
	private boolean for0Locked; // FOR 0 locked
	private boolean for1Locked; // FOR 1 locked
	private boolean for2Locked; // FOR 2 locked
	private boolean for0Bound; // FOR 0 bound
	private boolean for1Bound; // FOR 1 bound
	private boolean for2Bound; // FOR 2 bound
	private boolean forI2C0Enabled; // I2C0 enabled
	private boolean forI2C0Locked; // I2C0 locked
	private boolean forTMP0Bound; // FOR TMP 0 bound
	private boolean forTMP1Bound; // FOR TMP 1 bound
	private boolean forTMP2Bound; // FOR TMP 2 bound

	public TmForStatus(DataInputStream dis) throws IOException {
		for0Current = dis.readUnsignedShort() * 0.1f;
		for1Current = dis.readUnsignedShort() * 0.1f;
		for2Current = dis.readUnsignedShort() * 0.1f;
		forI2C0Current = dis.readUnsignedShort() * 0.1f;

		int raw = dis.readUnsignedByte();
		for0Enabled = ((raw >> 7) & 0x1) > 0;
		for1Enabled = ((raw >> 6) & 0x1) > 0;
		for2Enabled = ((raw >> 5) & 0x1) > 0;
		for0Locked = ((raw >> 4) & 0x1) > 0;
		for1Locked = ((raw >> 3) & 0x1) > 0;
		for2Locked = ((raw >> 2) & 0x1) > 0;
		for0Bound = ((raw >> 1) & 0x1) > 0;
		for1Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		for2Bound = ((raw >> 7) & 0x1) > 0;
		forI2C0Enabled = ((raw >> 6) & 0x1) > 0;
		forI2C0Locked = ((raw >> 5) & 0x1) > 0;
		forTMP0Bound = ((raw >> 4) & 0x1) > 0;
		forTMP1Bound = ((raw >> 3) & 0x1) > 0;
		forTMP2Bound = ((raw >> 2) & 0x1) > 0;
	}

	public float getFor0Current() {
		return for0Current;
	}

	public void setFor0Current(float for0Current) {
		this.for0Current = for0Current;
	}

	public float getFor1Current() {
		return for1Current;
	}

	public void setFor1Current(float for1Current) {
		this.for1Current = for1Current;
	}

	public float getFor2Current() {
		return for2Current;
	}

	public void setFor2Current(float for2Current) {
		this.for2Current = for2Current;
	}

	public float getForI2C0Current() {
		return forI2C0Current;
	}

	public void setForI2C0Current(float forI2C0Current) {
		this.forI2C0Current = forI2C0Current;
	}

	public boolean isFor0Enabled() {
		return for0Enabled;
	}

	public void setFor0Enabled(boolean for0Enabled) {
		this.for0Enabled = for0Enabled;
	}

	public boolean isFor1Enabled() {
		return for1Enabled;
	}

	public void setFor1Enabled(boolean for1Enabled) {
		this.for1Enabled = for1Enabled;
	}

	public boolean isFor2Enabled() {
		return for2Enabled;
	}

	public void setFor2Enabled(boolean for2Enabled) {
		this.for2Enabled = for2Enabled;
	}

	public boolean isFor0Locked() {
		return for0Locked;
	}

	public void setFor0Locked(boolean for0Locked) {
		this.for0Locked = for0Locked;
	}

	public boolean isFor1Locked() {
		return for1Locked;
	}

	public void setFor1Locked(boolean for1Locked) {
		this.for1Locked = for1Locked;
	}

	public boolean isFor2Locked() {
		return for2Locked;
	}

	public void setFor2Locked(boolean for2Locked) {
		this.for2Locked = for2Locked;
	}

	public boolean isFor0Bound() {
		return for0Bound;
	}

	public void setFor0Bound(boolean for0Bound) {
		this.for0Bound = for0Bound;
	}

	public boolean isFor1Bound() {
		return for1Bound;
	}

	public void setFor1Bound(boolean for1Bound) {
		this.for1Bound = for1Bound;
	}

	public boolean isFor2Bound() {
		return for2Bound;
	}

	public void setFor2Bound(boolean for2Bound) {
		this.for2Bound = for2Bound;
	}

	public boolean isForI2C0Enabled() {
		return forI2C0Enabled;
	}

	public void setForI2C0Enabled(boolean forI2C0Enabled) {
		this.forI2C0Enabled = forI2C0Enabled;
	}

	public boolean isForI2C0Locked() {
		return forI2C0Locked;
	}

	public void setForI2C0Locked(boolean forI2C0Locked) {
		this.forI2C0Locked = forI2C0Locked;
	}

	public boolean isForTMP0Bound() {
		return forTMP0Bound;
	}

	public void setForTMP0Bound(boolean forTMP0Bound) {
		this.forTMP0Bound = forTMP0Bound;
	}

	public boolean isForTMP1Bound() {
		return forTMP1Bound;
	}

	public void setForTMP1Bound(boolean forTMP1Bound) {
		this.forTMP1Bound = forTMP1Bound;
	}

	public boolean isForTMP2Bound() {
		return forTMP2Bound;
	}

	public void setForTMP2Bound(boolean forTMP2Bound) {
		this.forTMP2Bound = forTMP2Bound;
	}

}
