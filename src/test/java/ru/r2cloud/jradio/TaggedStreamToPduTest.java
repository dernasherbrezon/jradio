package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamMultiplyLength;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class TaggedStreamToPduTest {

	private TaggedStreamToPdu input;

	@Test
	public void testSuccess() throws Exception {
		input = new TaggedStreamToPdu(new TaggedStreamMultiplyLength(new UnpackedToPacked(new FixedLengthTagger(new CorrelateAccessCodeTag(new BinarySlicer(new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f)), 8, "syncword", "010011110101101000110100010000110101010101000010"), "syncword", "packet_len", 2008), 1, Endianness.GR_MSB_FIRST, Byte.class), "packet_len", (double) 1 / 8), "packet_len");
		byte[] actual = input.readBytes();
		byte[] expected;
		try (InputStream is = BinarySlicerTest.class.getClassLoader().getResourceAsStream("UnpackedToPacked.bin")) {
			expected = toByteArray(is);
		}	
		assertArrayEquals(expected, actual);
	}
    
	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
	
	//copy-paste from commons io. replace with lib implementation once such dependency will be introduced
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }
    
    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[1024];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

}
