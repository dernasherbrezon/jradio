/*
 * Copyright 2026 Andrey Rodionov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.r2cloud.jradio.ccsds;

public enum Coding {

	// @formatter:off
	UNCODED,
	CONCANTENATED_RS204_188, // R-S(204,188), convolutional 7,1/2
	CONCANTENATED_RS255_239, // R-S(255,239), convolutional 7,1/2
	LDPC2048_1024,
	LDPC8192_4096,
	LDPC32768_16384,
	LDPC6144_4096,
	LDPC24576_16384,
	LDPC20480_16384,
	LDPC8160_7136,
	CONVOLUTIONAL, // 7,1/2
	UNKNOWN;
	// @formatter:on

}
