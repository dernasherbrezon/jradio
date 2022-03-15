package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.Map;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.trace.StateProvider;
import ru.r2cloud.jradio.util.Lfsr;

public class Descrambler implements ByteInput, StateProvider {

	private final ByteInput source;
	private final Lfsr lfsr;

	public Descrambler(ByteInput source, int mask, int seed, int length) {
		this.source = source;
		this.lfsr = new Lfsr(mask, seed, length);
		this.source.getContext().addStateProvider(this);
	}

	@Override
	public byte readByte() throws IOException {
		return lfsr.nextBitDescramble(source.readByte());
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

	@Override
	public void snapState(Map<String, String> state) {
		state.put("shiftRegister", String.valueOf(lfsr.getShiftRegister()));
	}

}
