# About [![Build Status](https://travis-ci.org/dernasherbrezon/jradio.svg?branch=master)](https://travis-ci.org/dernasherbrezon/jradio) [![codecov](https://codecov.io/gh/dernasherbrezon/jradio/branch/master/graph/badge.svg)](https://codecov.io/gh/dernasherbrezon/jradio)

Software radio decoding written in Java. The idea of this project is to get blocks from the [gnuradio](https://github.com/gnuradio) and implement them in Java. This gives the following:

* No need for tooling. You could use [gnuradio-companion](https://wiki.gnuradio.org/index.php/HowToUse) to build a working pipeline, then use the same blocks to build the pipeline in Java.
* All Java benefits: run same binaries on multiple platforms without compilation. Single programming language instead of Python and C++. Better tooling like IDE, profilers and memory analyzers.

# Supported blocks

All blocks meant to be binary compatible with gnuradio versions. This will ensure you got the same results when moving from gnuradio-companion to Java.

* AGC
* BinarySlicer
* ClockRecoveryMM and ClockRecoveryMMComplex
* ConstellationSoftDecoder
* CorrelateAccessCodeTag
* CostasLoop
* FIRFilter
* FixedLengthTagger
* FloatToChar
* LowPassFilter
* Rail
* RootRaisedCosineFilter
* GUI Histogram Sink (Spectogram) 
* TaggedStreamToPdu
* UnpackedToPacked
* WavFileSource
* osmocom source (RtlTcp)
* File Source (InputStreamSource)

Forward error correction:

* Viterbi (hard)
* ViterbiSoft
* ReedSolomon

# Supported satellites

Using the blocks above, you could decode signals from the following satellites:

* AAUSAT-4 (NORAD 41460). Digital telemetry only. Based on [gr-aausat](https://github.com/daniestevez/gr-aausat). See the pipeline at the [AAUSAT4Test](https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/AAUSAT4Test.java)
* Meteor-M N2 (NORAD 40069). Decode pictures. Based on [meteor-decoder](https://github.com/artlav/meteor_decoder) and [meteor-m2-lrpt](https://github.com/otti-soft/meteor-m2-lrpt). See the pipeline at the [MeteorImageTest](https://github.com/dernasherbrezon/jradio/blob/lrpt/src/test/java/ru/r2cloud/jradio/meteor/MeteorImageTest.java)
