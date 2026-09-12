/*
 * Copyright 2026 Andrey Rodionov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.r2cloud.jradio.ccsds;

public enum ScramblerType {

	NONE, CCITT, IESS, UNKNOWN;

	public static ScramblerType valueOfCode(int code) {
		switch (code) {
		case 0b00:
		case 0b10:
			return NONE;
		case 0b01:
			return CCITT;
		case 0b11:
			return IESS;
		default:
			return UNKNOWN;
		}
	}

	public int getCode() {
		switch (this) {
		case NONE:
			return 0b00;
		case CCITT:
			return 0b01;
		case IESS:
			return 0b11;
		default:
			return 0b00;
		}
	}

}
