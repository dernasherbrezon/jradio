package ru.r2cloud.jradio.fec;

import java.util.Arrays;

public class PlsDecoder {

	private static final int CODE_LENGTH = 64;
	private static final int CODE_WIDTH = 7;

	private int[] codes;
	private int[] sum;
	private int[][] encoded;

	public PlsDecoder() {
		long[] G = new long[] { 0b0011001100110011001100110011001100110011001100110011001100110011L, 0b0000111100001111000011110000111100001111000011110000111100001111L, 0b0000000011111111000000001111111100000000111111110000000011111111L,
				0b0000000000000000111111111111111100000000000000001111111111111111L, 0b0000000000000000000000000000000011111111111111111111111111111111L, 0b1111111111111111111111111111111111111111111111111111111111111111L, 0b0101010101010101010101010101010101010101010101010101010101010101L };
		// hardcoded codes
		codes = new int[] { 0, 1 };
		long scrambleSequence = 0b0111000110011101100000111100100101010011010000100010110111111010L;

		int[][] g_matrix = new int[CODE_LENGTH][CODE_WIDTH];
		// bits to matrix and transpose it
		for (int i = 0; i < CODE_LENGTH; i++) {
			for (int j = 0; j < CODE_WIDTH; j++) {
				g_matrix[i][j] = (int) (G[j] >> (CODE_LENGTH - 1 - i)) & 0x1;
			}
		}
		int[][] codes_matrix = new int[CODE_WIDTH][codes.length];
		for (int i = 0; i < CODE_WIDTH; i++) {
			for (int j = 0; j < codes.length; j++) {
				codes_matrix[i][j] = codes[j] >> (CODE_WIDTH - 1 - i) & 0x1;
			}
		}

		encoded = new int[CODE_LENGTH][codes.length];
		// multiple 2 matrixes
		for (int i = 0; i < CODE_LENGTH; i++) {
			for (int j = 0; j < codes.length; j++) {
				encoded[i][j] = multiply(g_matrix, codes_matrix, j, i);
			}
		}

		// can be combines with the double loop above
		// but keep it separate for better structure
		// encoded ^ scrambleSequence and put within interval [-1;+1]
		for (int i = 0; i < CODE_LENGTH; i++) {
			int scrambleBit = (int) (scrambleSequence >> (CODE_LENGTH - 1 - i)) & 0x1;
			for (int j = 0; j < codes.length; j++) {
				encoded[i][j] = 2 * (encoded[i][j] ^ scrambleBit) - 1;
			}
		}

		sum = new int[codes.length];
	}

	private static int multiply(int[][] g_matrix, int[][] codes_matrix, int col, int row) {
		int result = 0;
		int[] rowVector = g_matrix[row];
		for (int i = 0; i < rowVector.length; i++) {
			result += (rowVector[i] * codes_matrix[i][col]) % 2;
		}
		return result;
	}

	public int decode(byte[] array, int offset) {
		Arrays.fill(sum, 0);
		for (int i = 0; i < CODE_LENGTH; i++) {
			int curValue = (array[offset + i] & 0xFF) - 128;
			for (int j = 0; j < sum.length; j++) {
				sum[j] += curValue * encoded[i][j];
			}
		}
		return codes[findIndexWithMaxSum()];
	}

	private int findIndexWithMaxSum() {
		int max = Integer.MIN_VALUE;
		int result = 0;
		for (int i = 0; i < sum.length; i++) {
			if (sum[i] > max) {
				max = sum[i];
				result = i;
			}
		}
		return result;
	}

}
