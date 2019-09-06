## About [![Build Status](https://travis-ci.org/dernasherbrezon/jradio.svg?branch=master)](https://travis-ci.org/dernasherbrezon/jradio) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ru.r2cloud%3Ajradio&metric=alert_status)](https://sonarcloud.io/dashboard?id=ru.r2cloud%3Ajradio)

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

Using the blocks above, you could decode signals from the following satellites:

  * 1Kuns-Pf (NORAD 43466). Decode short and long beacon formats, images. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [KunsPfTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/kunspf/KunsPfTest.java)
  * AAUSAT-4 (NORAD 41460). Digital telemetry only. Based on [gr-aausat](https://github.com/daniestevez/gr-aausat). See the pipeline at the [AAUSAT4Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aausat4/AAUSAT4Test.java)
  * Aistechsat3 (NORAD 44103). See the pipeline at the [Aistechsat3Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aistechsat3/Aistechsat3Test.java)
  * AO-73 (NORAD 39444). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Ao73Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ao73/Ao73Test.java)
  * Astrocast 0.1 (NORAD 43798). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [AstrocasatTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/astrocasat/AstrocasatTest.java)
  * AT03 (NORAD 42784). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [At03Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/at03/At03Test.java)
  * AU02/AU03 (NORAD 42723/42731). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Au02Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/au02/Au02Test.java)
  * CA03 (NORAD 42734). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Ca03Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ca03/Ca03Test.java)
  * D-Star ONE (NORAD 43881). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Dstar1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/dstar1/Dstar1Test.java)
  * Entrysat (NORAD 44429). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [EntrysatTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/entrysat/EntrysatTest.java)
  * ESEO (NORAD 99912). Decode telemetry beacon. Decode telemetry from the funcube payload. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [EseoTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/eseo/EseoTest.java)
  * Fmn1 (NORAD 43192). See the pipeline at the [Fmn1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/fmn1/Fmn1Test.java)
  * GOMX-1 (NORAD 39430). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Gomx1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/gomx1/Gomx1Test.java)
  * Jy1sat (NORAD 43803). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Nayif1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/jy1sat/Jy1satTest.java)
  * Lume-1 (NORAD 43908) and AISTechSat 2(NORAD 43768). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Lume1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/lume1/Lume1Test.java)
  * Meteor-M N2 (NORAD 40069). Decode pictures. Based on [meteor-decoder](https://github.com/artlav/meteor_decoder) and [meteor-m2-lrpt](https://github.com/otti-soft/meteor-m2-lrpt). See the pipeline at the [MeteorImageTest](https://github.com/dernasherbrezon/jradio/blob/lrpt/src/test/java/ru/r2cloud/jradio/meteor/MeteorImageTest.java)
  * Mysat-1 (NORAD 44045). See the pipeline at the [Mysat1Test](https://github.com/dernasherbrezon/jradio/blob/lrpt/src/test/java/ru/r2cloud/jradio/mysat1/Mysat1Test.java)
  * Nayif1 (NORAD 42017). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Nayif1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/nayif1/Nayif1Test.java)
  * PwSat2 (NORAD 43776). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [PwSat2Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/pwsat2/PwSat2Test.java)
  * Reactor Hello World (NORAD 43743). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites), [rhw-ham](https://github.com/ReaktorSpaceLab/rhw-ham). See the pipeline at the [ReaktorHelloWorldTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/rhw/ReaktorHelloWorldTest.java)
  * S-NET A,B,C,D (NORAD 43186, 43187, 43188, 43189). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [SnetTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/snet/SnetTest.java)
  * Sat3Cat1 (NORAD 99901). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Sat3Cat1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/sat3cat1/Sat3Cat1Test.java)
  * Suomi100 (NORAD 43804). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Suomi100Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/suomi100/Suomi100Test.java)
  * Technosat (NORAD 42829). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites) and [beesat-sdr](https://github.com/kappiman/beesat-sdr). See the pipeline at the [TechnosatTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/technosat/TechnosatTest.java)
  
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
