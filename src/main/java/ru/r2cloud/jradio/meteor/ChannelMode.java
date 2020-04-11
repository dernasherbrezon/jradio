package ru.r2cloud.jradio.meteor;

public enum ChannelMode {
	// no idea what these modes mean. just copied description from official spec

	// Режим усиления, соответствующий модельной яркости В0
	TYPE1(0b0001),
	// Режим усиления, соответствующий модельной яркости 0,5В0
	TYPE2(0b0011),
	// Режим усиления, соответствующий модельной яркости 0,25В0
	TYPE3(0b0100),
	// Режим усиления дискретного по диапазону яркости.
	TYPE4(0b0101),
	// Режим линейной передаточной характеристики канала
	TYPE5(0b0110),
	// Режим ТЕСТ 1 (формируется градационный клин только в 4, 5, 6 каналах)
	TYPE6(0b0111),
	// Режим ТЕСТ 2 (формируется градационный клин во всех каналах)
	TYPE7(0b1000),
	// Режим ТЕСТ 3 (передается видеоинформация с 12 разрядным кодированием и уменьшенным числом элементов изображения в канале для наземной отработки)
	TYPE8(0b1001);

	private int code;

	private ChannelMode(int code) {
		this.code = code;
	}

	public static ChannelMode valueOfCode(int code) {
		for (ChannelMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
}
