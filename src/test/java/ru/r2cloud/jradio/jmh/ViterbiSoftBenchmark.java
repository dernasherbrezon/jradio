package ru.r2cloud.jradio.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.meteor.MeteorImageTest;

@State(Scope.Benchmark)
public class ViterbiSoftBenchmark {
	
	private ViterbiSoft viterbi;
	private byte[] data;
	public byte[] result;

	@Setup(Level.Invocation)
	public void start() throws Exception {
		viterbi = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, false, 16336);
		data = MeteorImageTest.toBytes("viterbi.bin");
	}
	
	@Benchmark
	public void plain(ViterbiSoftBenchmark plan) {
		plan.result = plan.viterbi.decode(plan.data);
	}
	
}
