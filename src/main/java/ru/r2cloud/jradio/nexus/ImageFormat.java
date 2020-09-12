package ru.r2cloud.jradio.nexus;

public enum ImageFormat {
	
	JPEG_QVGA(0),
	JPEG_VGA(1),
	JPEG_SVGA(2),
	JPEG_HD(3),
	JPEG_FHD(4),
	JPEG_MAX1(5), //(2592×1944)
	RGB565_QVGA(6),
	RGB565_VGA(7),
	RGB565_SVGA(8),
	RGB565_HD(9),
	RGB565_FHD(10),
	RGB565_MAX2(11); // (2203×1652)
	
	private final int code;

	private ImageFormat(int code) {
		this.code = code;
	}
	
	public static ImageFormat valueOfCode(int code) {
		for( ImageFormat cur : values()  ) {
			if( cur.code == code ) {
				return cur;
			}
		}
		return null;
	}
	
}
