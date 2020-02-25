 About [![Build Status](https://travis-ci.org/dernasherbrezon/jradio.svg?branch=master)](https://travis-ci.org/dernasherbrezon/jradio) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ru.r2cloud%3Ajradio&metric=alert_status)](https://sonarcloud.io/dashboard?id=ru.r2cloud%3Ajradio)

Software radio decoding written in Java. The idea of this project is to get blocks from the [gnuradio](https://github.com/gnuradio) and implement them in Java. This gives the following:

  * No need for tooling. You could use [gnuradio-companion](https://wiki.gnuradio.org/index.php/HowToUse) to build a working pipeline, then use the same blocks to build the pipeline in Java.
  * All Java benefits: run same binaries on multiple platforms without compilation. Single programming language instead of Python and C++. Better tooling like IDE, profilers and memory analyzers.

## Supported blocks

All blocks meant to be binary compatible with gnuradio versions. This will ensure you got the same results when moving from gnuradio-companion to Java.

  * Add
  * AdditiveScrambler
  * AGC
  * [AX100Decoder](https://gomspace.com/UserFiles/Subsystems/datasheet/gs-ds-nanocom-ax100-33.pdf). Out-of-tree block. Support ASM + Golay mode.
  * BinarySlicer
  * Cc11xxReceiver. Out-of-tree block. Decodes frames produced by [cc11xx](http://www.ti.com/product/CC1101/description). Based on [gr-cc11xx](https://github.com/andrepuschmann/gr-cc11xx)
  * ChannelModel
  * ChunksToSymbols
  * [ClockRecoveryMM](https://www.tablix.org/~avian/blog/archives/2015/03/notes_on_m_m_clock_recovery/) and [ClockRecoveryMMComplex](https://www.tablix.org/~avian/blog/archives/2015/03/notes_on_m_m_clock_recovery/)
  * ComplexConjugate
  * ComplexToReal
  * ConstellationModulator
  * ConstellationSoftDecoder
  * [ConvolutionalDeinterleaver](https://en.wikipedia.org/wiki/Burst_error-correcting_code#Convolutional_interleaver)
  * CorrelateAccessCodeTag
  * CorrelateSynchronizationMarker. Extract data from continuous stream of synchronization markers. Work with soft stream. 
  * CorrelateSyncword. Group CorrelateAccessCodeTag, FixedLengthTagger, TaggedStreamToPdu together for better performance.
  * CostasLoop
  * DelayOne. jradio doesn't support split and merge of streams. This block incapsulates delay 1 for imag complex stream
  * Descrambler
  * DifferentialEncoder/DifferentialDecoder
  * DifferentialSoftDecoder. Support qpsk only
  * Divide
  * FastNoiseSource
  * FIRFilterBlock
  * File Source (InputStreamSource)
  * File Sink (OutputStreamSink)
  * FixedLengthTagger
  * FLL Band Edge
  * FloatToChar
  * FloatToComplex
  * FractionalResampler
  * [FrequencyXlatingFIRFilter](http://blog.sdr.hu/grblocks/xlating-fir.html)
  * GUI Histogram Sink (Spectogram) 
  * HardToSoft. Convert hard decision stream to soft stream.
  * HdlcReceiver. Out-of-tree block. Extracts HDLC frame from the unpacked stream of bytes
  * LowPassFilter and LowPassFilterComplex
  * LMSDDEqualizer
  * MapBlock
  * Multiply
  * MultiplyConst
  * NrziDecode. Out-of-tree block. Performs nrzi decoding over unpacked stream of bytes
  * osmocom source (RtlTcp)
  * PackedToUnpacked
  * PolyphaseArbResamplerComplex
  * PolyphaseClockSyncComplex
  * PeakDetection. Detect peaks in the FFT. Not a gnuradio block.
  * QuadratureDemodulation
  * Rail
  * Rms
  * RmsAgc. Out-of-tree block. For more details see [blog post](https://destevez.net/2017/08/agc-for-gr-satellites/)
  * RootRaisedCosineFilter
  * Scrambler
  * SigSource
  * TaggedStreamToPdu
  * UnpackedToPacked
  * WavFileSource
  * WavFileSink

Forward error correction:

  * Viterbi (hard)
  * ViterbiSoft
  * CCSDS ReedSolomon
  * BCH ReedSolomon
  * BCH(15,x,x)
  
Frequency correction:
  
  * GmskFrequencyCorrection. Calculates frequency correction based on 2 main GMSK spikes
  
Coding:
 
  * NRZI
  * Bit stuffing

Randomization
 
  * xoroshiro128+
  * mt19937 (class MTRandom)
  * RayleighRandom
  
Demodulators

  * BpskDemodulator for BPSK and DBPSK

## Supported satellites

Using the blocks above, it is possible to decode the following satellites:

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>NORAD</th>
			<th>Sample code</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1Kuns-Pf</td>
			<td>43466</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/kunspf/KunsPfTest.java">KunsPfTest</a></td>
		</tr>
		<tr>
			<td>AAUSAT-4</td>
			<td>41460</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aausat4/AAUSAT4Test.java">AAUSAT4Test</a></td>
		</tr>
		<tr>
			<td>Aistechsat3</td>
			<td>44103</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aistechsat3/Aistechsat3Test.java">Aistechsat3Test</a></td>
		</tr>
		<tr>
			<td>AO-73</td>
			<td>39444</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ao73/Ao73Test.java">Ao73Test</a></td>
		</tr>
		<tr>
			<td>Astrocast 0.1</td>
			<td>43798</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/astrocasat/AstrocasatTest.java">AstrocasatTest</a></td>
		</tr>
		<tr>
			<td>AT03</td>
			<td>42784</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/at03/At03Test.java">At03Test</a></td>
		</tr>
		<tr>
			<td>AU02/AU03</td>
			<td>42723/42731</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/au02/Au02Test.java">Au02Test</a></td>
		</tr>
		<tr>
			<td>CA03</td>
			<td>42734</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ca03/Ca03Test.java">Ca03Test</a></td>
		</tr>
		<tr>
			<td>D-Star ONE</td>
			<td>43881</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/dstar1/Dstar1Test.java">Dstar1Test</a></td>
		</tr>
		<tr>
			<td>Entrysat</td>
			<td>44429</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/entrysat/EntrysatTest.java">EntrysatTest</a></td>
		</tr>
		<tr>
			<td>ESEO</td>
			<td>99912</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/eseo/EseoTest.java">EseoTest</a></td>
		</tr>
		<tr>
			<td>Fmn1</td>
			<td>43192</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/fmn1/Fmn1Test.java">Fmn1Test</a></td>
		</tr>
		<tr>
			<td>GOMX-1</td>
			<td>39430</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/gomx1/Gomx1Test.java">Gomx1Test</a></td>
		</tr>
		<tr>
			<td>Jy1sat</td>
			<td>43803</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/jy1sat/Jy1satTest.java">Nayif1Test</a></td>
		</tr>
		<tr>
			<td>Lume-1 / AISTechSat 2</td>
			<td>43908 / 43768</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/lume1/Lume1Test.java">Lume1Test</a></td>
		</tr>
		<tr>
			<td>Meteor-M N2</td>
			<td>40069</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/meteor/MeteorImageTest.java">MeteorImageTest</a></td>
		</tr>
		<tr>
			<td>Mysat-1</td>
			<td>44045</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/mysat1/Mysat1Test.java">Mysat1Test</a></td>
		</tr>
		<tr>
			<td>Nayif1</td>
			<td>42017</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/nayif1/Nayif1Test.java">Nayif1Test</a></td>
		</tr>
		<tr>
			<td>OPS-SAT</td>
			<td>44878</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/opssat/OpsSatTest.java">OpsSatTest</a></td>
		</tr>
		<tr>
			<td>PwSat2</td>
			<td>43776</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/pwsat2/PwSat2Test.java">PwSat2Test</a></td>
		</tr>
		<tr>
			<td>Reactor Hello World</td>
			<td>43743</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/rhw/ReaktorHelloWorldTest.java">ReaktorHelloWorldTest</a></td>
		</tr>
		<tr>
			<td>S-NET A,B,C,D</td>
			<td>43186, 43187, 43188, 43189</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/snet/SnetTest.java">SnetTest</a></td>
		</tr>
		<tr>
			<td>Sat3Cat1</td>
			<td>99901</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/sat3cat1/Sat3Cat1Test.java">Sat3Cat1Test</a></td>
		</tr>
		<tr>
			<td>Suomi100</td>
			<td>43804</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/suomi100/Suomi100Test.java">Suomi100Test</a></td>
		</tr>
		<tr>
			<td>Technosat</td>
			<td>42829</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/technosat/TechnosatTest.java">TechnosatTest</a></td>
		</tr>
	</tbody>
</table>
  
## Doppler correction

Doppler correction could be made using SigSource and Multiply blocks. Here is the sample code:

```java
PassPredictor predictor = new PassPredictor(tle, currentLocation);
SigSource source2 = new SigSource(Waveform.COMPLEX, sampleRate, new DopplerValueSource(sampleRate, satelliteFrequency, correctPeriodMillis, startTimeMillis) {

	@Override
	public long getDopplerFrequency(long satelliteFrequency, long currentTimeMillis) {
		return predictor.getDownlinkFreq(satelliteFrequency, new Date(currentTimeMillis));
	}
}, 1.0f);
Multiply mul = new Multiply(source, source2, true);
```

Where "getDopplerFrequency" was calculated using [predict4java](https://github.com/badgersoftdotcom/predict4java)

## Usage

Configure maven:

```xml
<dependency>
        <groupId>ru.r2cloud</groupId>
        <artifactId>jradio</artifactId>
        <version>1.22</version>
</dependency>
```
