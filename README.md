## About [![Build Status](https://travis-ci.org/dernasherbrezon/jradio.svg?branch=master)](https://travis-ci.org/dernasherbrezon/jradio) [![codecov](https://codecov.io/gh/dernasherbrezon/jradio/branch/master/graph/badge.svg)](https://codecov.io/gh/dernasherbrezon/jradio)

Software radio decoding written in Java. The idea of this project is to get blocks from the [gnuradio](https://github.com/gnuradio) and implement them in Java. This gives the following:

  * No need for tooling. You could use [gnuradio-companion](https://wiki.gnuradio.org/index.php/HowToUse) to build a working pipeline, then use the same blocks to build the pipeline in Java.
  * All Java benefits: run same binaries on multiple platforms without compilation. Single programming language instead of Python and C++. Better tooling like IDE, profilers and memory analyzers.

## Supported blocks

All blocks meant to be binary compatible with gnuradio versions. This will ensure you got the same results when moving from gnuradio-companion to Java.

  * AdditiveScrambler
  * AGC
  * BinarySlicer
  * ClockRecoveryMM and ClockRecoveryMMComplex
  * ComplexConjugate
  * ComplexToReal
  * ConstellationSoftDecoder
  * CorrelateAccessCodeTag
  * CostasLoop
  * DelayOne. jradio doesn't support split and merge of streams. This block incapsulates delay 1 for imag complex stream
  * Descrambler
  * Divide
  * FIRFilter and ComplexFIRFilter
  * File Source (InputStreamSource)
  * File Sink (OutputStreamSink)
  * FixedLengthTagger
  * FLL Band Edge
  * FloatToChar
  * FloatToComplex
  * FrequencyXlatingFIRFilter
  * GUI Histogram Sink (Spectogram) 
  * HdlcReceiver. Out-of-tree block. Extracts HDLC frame from the unpacked stream of bytes
  * LowPassFilter and LowPassFilterComplex
  * Multiply
  * MultiplyConst
  * NrziDecode. Out-of-tree block. Performs nrzi decoding over unpacked stream of bytes
  * osmocom source (RtlTcp)
  * PolyphaseClockSyncComplex
  * PeakDetection. Detect peaks in the FFT. Not a gnuradio block.
  * QuadratureDemodulation
  * Rail
  * Rms
  * RmsAgc. Out-of-tree block. For more details see [blog post](https://destevez.net/2017/08/agc-for-gr-satellites/)
  * RootRaisedCosineFilter
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
  
Frequency correction:
  
  * GmskFrequencyCorrection. Calculates frequency correction based on 2 main GMSK spikes
  
Coding:
 
  * NRZI
  * Bit stuffing

## Supported satellites

Using the blocks above, you could decode signals from the following satellites:

  * AAUSAT-4 (NORAD 41460). Digital telemetry only. Based on [gr-aausat](https://github.com/daniestevez/gr-aausat). See the pipeline at the [AAUSAT4Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aausat4/AAUSAT4Test.java)
  * Meteor-M N2 (NORAD 40069). Decode pictures. Based on [meteor-decoder](https://github.com/artlav/meteor_decoder) and [meteor-m2-lrpt](https://github.com/otti-soft/meteor-m2-lrpt). See the pipeline at the [MeteorImageTest](https://github.com/dernasherbrezon/jradio/blob/lrpt/src/test/java/ru/r2cloud/jradio/meteor/MeteorImageTest.java)
  * 1Kuns-Pf (NORAD 43466). Decode short and long beacon formats, images. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [KunsPfTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/kunspf/KunsPfTest.java)
  * Sat3Cat1 (NORAD 99901). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Sat3Cat1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/sat3cat1/Sat3Cat1Test.java)
  * AT03 (NORAD 42784). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [At03Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/at03/At03Test.java)
  * AU02/AU03 (NORAD 42723/42731). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Au02Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/au02/Au02Test.java)
  * Technosat (NORAD 42829). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites) and [beesat-sdr](https://github.com/kappiman/beesat-sdr). See the pipeline at the [TechnosatTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/technosat/TechnosatTest.java)
  * CA03 (NORAD 42734). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Ca03Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ca03/Ca03Test.java)
  * ESEO (NORAD 99912). Decode telemetry beacon. Decode telemetry from the funcube payload. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [EseoTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/eseo/EseoTest.java)
  * GOMX-1. Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Gomx1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/gomx1/Gomx1Test.java)
  * PwSat2 (NORAD 43776). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [PwSat2Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/pwsat2/PwSat2Test.java)
  * Astrocast 0.1 (NORAD 43798). Decode telemetry beacon. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [AstrocasatTest](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/astrocasat/AstrocasatTest.java)
  * AO-73 (NORAD 39444). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Ao73Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ao73/Ao73Test.java)
  * Nayif1 (NORAD 42017). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Nayif1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/nayif1/Nayif1Test.java)
  * Jy1sat (NORAD 43803). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Nayif1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/jy1sat/Jy1satTest.java)
  * D-Star ONE (NORAD 43881). Decode telemetry beacon and aggregate payloads. Based on [gr-satellites](https://github.com/daniestevez/gr-satellites). See the pipeline at the [Dstar1Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/dstar1/Dstar1Test.java)
  
  
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
