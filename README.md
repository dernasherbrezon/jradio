[![Main Workflow](https://github.com/dernasherbrezon/jradio/actions/workflows/build.yml/badge.svg)](https://github.com/dernasherbrezon/jradio/actions/workflows/build.yml) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ru.r2cloud%3Ajradio&metric=alert_status)](https://sonarcloud.io/dashboard?id=ru.r2cloud%3Ajradio)

## About 

Software radio decoding written in Java. The idea of this project is to get blocks from the [gnuradio](https://github.com/gnuradio) and implement them in Java. This gives the following:

  * No need for tooling. You could use [gnuradio-companion](https://wiki.gnuradio.org/index.php/HowToUse) to build a working pipeline, then use the same blocks to build the pipeline in Java.
  * All Java benefits: run same binaries on multiple platforms without compilation. Single programming language instead of Python and C++. Better tooling like IDE, profilers and memory analyzers.

## Demodulators

jradio supports high-level generic demodulators. They convert [I/Q signal](https://en.wikipedia.org/wiki/In-phase_and_quadrature_components) into the stream of [soft bits](https://dsp.stackexchange.com/a/27482) (0-255).

 * [FSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/demod/FskDemodulator.java)
 * [AFSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/demod/AfskDemodulator.java)
 * [BPSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/demod/BpskDemodulator.java)
 * [QPSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/demod/QpskDemodulator.java)
 
## Modulators

Some generic modulators. They take hard bits and produce I/Q signal.

 * [GFSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/blocks/GfskModulator.java)
 * [BPSK/QPSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/blocks/ConstellationModulator.java)
 * [FSK](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/blocks/FskModulator.java)

## De-framers

De-framer is a component that converts soft stream of bits into the frames of specific protocol.

 * [AX.25](https://en.wikipedia.org/wiki/AX.25) - [Ax25BeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/Ax25BeaconSource.java)
 * [AX.25](https://en.wikipedia.org/wiki/AX.25) with G3ruh scrambler - [Ax25G3ruhBeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/Ax25G3ruhBeaconSource.java)
 * [AX100](https://gomspace.com/UserFiles/Subsystems/datasheet/gs-ds-nanocom-ax100-33.pdf) - [Ax100BeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/Ax100BeaconSource.java)
 * [cc11xx](http://www.ti.com/product/CC1101/description) - [Cc11xxReceiver](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/cc11xx/Cc11xxReceiver.java)
 * [NGHam](https://github.com/skagmo/ngham) - Support for [NGHam radio protocol](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/blocks/NgHam.java)
 * Generic [syncword](https://en.wikipedia.org/wiki/Syncword) correlator - [CorrelateSyncword](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/blocks/CorrelateSyncword.java)
 * [USP](https://github.com/sputnixru/SX-USP) - [UspDecoder](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/usp/UspDecoder.java)
 * Mobitex - [MobitexBeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/mobitex/MobitexBeaconSource.java)
   * TUBiX20 - Generic de-framer for TUB-based satellites. [TUBiX20BeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/tubix20/TUBiX20BeaconSource.java)
 * CCSDS Concatenated - CCSDS 131.0-B-4 implementation for concatenated framing (Convolutional + Reed-Solomon). [CcsdsBeaconSource](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/ccsds/CcsdsBeaconSource.java)

## Auxiliary tools

Quite often decoding require some additional tooling. jradio has some.

### Forward error correction:

 * [Viterbi](https://en.wikipedia.org/wiki/Viterbi_algorithm) [hard](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/Viterbi.java) and [soft](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/ViterbiSoft.java) decoders
 * [CCSDS ReedSolomon](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/ccsds/ReedSolomon.java)
 * [BCH ReedSolomon](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/rs/bch/ReedSolomon.java)
 * [BCH(15,x,x)](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/Bch15.java)
 * [Repeat Accumulate decoder](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/ra/RaDecoder.java)
 * [Golay](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/Golay.java)
 * [PLS](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/fec/PlsDecoder.java)

### Randomization
 
 * [xoroshiro128+](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/util/Xoroshiro128p.java)
 * [mt19937](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/util/MTRandom.java)
 * [RayleighRandom](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/util/RayleighRandom.java)
  
### CRC

Most likely the algorithm name can't say you much, so it is better to check [corresponding test case](https://github.com/dernasherbrezon/jradio/tree/master/src/test/java/ru/r2cloud/jradio/crc). Here is list of supported implementations:

 * [CRC-8](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc8.java)
 * [CRC-16/XMODEM & CRC-16/X-25](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc16Ccitt.java)
 * [CRC-16/CCITT-FALSE/CRC-16-IBM](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc16CcittFalse.java)
 * [CRC-16/ARC](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc16Arc.java)
 * [CRC-16-NAIVE](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc16SumOfBytes.java). Just sum of bytes.
 * [CRC-32C](https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/crc/Crc32c.java)
 
### Satellite protocols

 * [CSP](https://en.wikipedia.org/wiki/Cubesat_Space_Protocol) - Full support
 * [CCSDS](https://en.wikipedia.org/wiki/Consultative_Committee_for_Space_Data_Systems) - [Basic support](https://github.com/dernasherbrezon/jradio/tree/master/src/main/java/ru/r2cloud/jradio/ccsds)
 * [PACSAT](https://www.tapr.org/pdf/CNC1990-PacSatBroadcast-NK6K-K8KA.pdf) - [Good support](https://github.com/dernasherbrezon/jradio/tree/master/src/main/java/ru/r2cloud/jradio/falconsat3)

## Low-level blocks

All blocks meant to be binary compatible with gnuradio versions. This will ensure you got the same results when moving from gnuradio-companion to Java.

  * Add
  * AdditiveScrambler
  * AGC
  * [AX100Decoder](https://gomspace.com/UserFiles/Subsystems/datasheet/gs-ds-nanocom-ax100-33.pdf). Out-of-tree block. Support ASM + Golay mode.
  * BinarySlicer
  * Cc11xxReceiver. Out-of-tree block. Decodes frames produced by [cc11xx](http://www.ti.com/product/CC1101/description). Based on [gr-cc11xx](https://github.com/andrepuschmann/gr-cc11xx)
  * ChannelModel
  * ChunksToSymbols and ChunksToSymbolsComplex
  * [ClockRecoveryMM](https://www.tablix.org/~avian/blog/archives/2015/03/notes_on_m_m_clock_recovery/) and [ClockRecoveryMMComplex](https://www.tablix.org/~avian/blog/archives/2015/03/notes_on_m_m_clock_recovery/)
  * ComplexConjugate
  * ComplexToReal
  * ConstellationSoftDecoder
  * [ConvolutionalDeinterleaver](https://en.wikipedia.org/wiki/Burst_error-correcting_code#Convolutional_interleaver)
  * CorrelateSynchronizationMarker. Extract data from continuous stream of synchronization markers. Work with soft stream. 
  * CorrelateSyncword
  * CostasLoop
  * DcBlocker
  * DelayOne. jradio doesn't support split and merge of streams. This block incapsulates delay 1 for imag complex stream
  * Descrambler
  * DifferentialEncoder/DifferentialDecoder
  * DifferentialSoftDecoder. Support qpsk only
  * Divide
  * FastNoiseSource
  * FIRFilterBlock
  * File Source (InputStreamSource)
  * File Sink (OutputStreamSink)
  * FLL Band Edge
  * FloatToChar
  * FloatToComplex
  * FractionalResampler
  * FrequencyModulator
  * [FrequencyXlatingFIRFilter](http://blog.sdr.hu/grblocks/xlating-fir.html)
  * GUI Histogram Sink (Spectogram) 
  * HardToSoft. Convert hard decision stream to soft stream.
  * HdlcReceiver. Out-of-tree block. Extracts HDLC frame from the unpacked stream of bytes
  * HdlcTransmitter. Take the byte array and create hdlc frame
  * InterpFIRFilter
  * LowPassFilter and LowPassFilterComplex
  * LMSDDEqualizer
  * MapBlock
  * Multiply
  * MultiplyConst
  * NrziDecode/NrziEncode. Performs nrzi decoding/encoding over unpacked stream of bytes
  * osmocom source (RtlTcp)
  * PackedToUnpacked
  * PolyphaseArbResamplerComplex
  * PolyphaseClockSyncComplex
  * PeakDetection. Detect peaks in the FFT. Not a gnuradio block.
  * QuadratureDemodulation
  * Rail
  * Rms
  * RmsAgc and RmsAgcComplex. Out-of-tree block. For more details see this [blog post](https://destevez.net/2017/08/agc-for-gr-satellites/)
  * RootRaisedCosineFilter
  * Scrambler
  * SigSource
  * SequentialSource. Produce time-multiplexed outputs from several sources. Useful in simulations.
  * TimeConstraintedSource. Can be used in simulations to time-limit noise source.
  * UnpackedToPacked
  * WavFileSource
  * WavFileSink
  
Coding:
 
  * NRZI
  * Bit stuffing

## Supported satellites

jradio has lots of built-in satellite decoders. Some of them have non standard de-framers, some beacon decoders, some both:

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
			<td>DELFI-C3 (DO-64)</td>
			<td>32789</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/delfic3/DelfiC3Test.java">DelfiC3Test</a></td>
		</tr>
		<tr>
			<td>1Kuns-Pf</td>
			<td>43466</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/kunspf/KunsPfTest.java">KunsPfTest</a></td>
		</tr>
		<tr>
			<td>AAUSAT-4</td>
			<td>41460</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aausat4/Aausat4Test.java">Aausat4Test</a></td>
		</tr>
		<tr>
			<td>Aistechsat3</td>
			<td>44103</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aistechsat3/Aistechsat3Test.java">Aistechsat3Test</a></td>
		</tr>
		<tr>
			<td>Aistechsat2</td>
			<td>43768</td>
			<td>Same as Aistechsat3. See <a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/aistechsat3/Aistechsat3Test.java">Aistechsat3Test</a></td>
		</tr>
		<tr>
			<td>AO-73</td>
			<td>39444</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/ao73/Ao73Test.java">Ao73Test</a></td>
		</tr>
		<tr>
			<td>Astrocast 0.1</td>
			<td>43798</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/astrocasat/AstrocastTest.java">AstrocastTest</a></td>
		</tr>
		<tr>
			<td>AT03</td>
			<td>42784</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/at03/At03Test.java">At03Test</a></td>
		</tr>
		<tr>
			<td>ATL-1</td>
			<td>44830</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/atl1/Atl1Test.java">Atl1Test</a></td>
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
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/mobitex/MobitexBeaconSource.java">Dstar1Test</a></td>
		</tr>
		<tr>
			<td>DELPHINI</td>
			<td>44030</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/delphini/Delphini1Test.java">Delphini1Test</a></td>
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
			<td>FOX-1A, FOX-1B, FOX-1C, FOX-1D</td>
			<td>40967, 43017, 43770, 43137</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/fox/FoxTest.java">FoxTest</a></td>
		</tr>
		<tr>
			<td>GOMX-1</td>
			<td>39430</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/gomx1/Gomx1Test.java">Gomx1Test</a></td>
		</tr>
		<tr>
			<td>Itasat1</td>
			<td>43786</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/itasat1/Itasat1Test.java">Itasat1Test</a></td>
		</tr>
		<tr>
			<td>Jy1sat</td>
			<td>43803</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/jy1sat/Nayif1Test.java">Nayif1Test</a></td>
		</tr>
		<tr>
			<td>Lucky-7</td>
			<td>44406</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/lucky7/Lucky7Test.java">Lucky7Test</a></td>
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
			<td>SMOG-P</td>
			<td>44832</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/smogp/SmogPTest.java">SmogPTest</a></td>
		</tr>
		<tr>
			<td>Suomi100</td>
			<td>43804</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/suomi100/Suomi100Test.java">Suomi100Test</a></td>
		</tr>
		<tr>
			<td>SWAMPSAT-2</td>
			<td>45115</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/swampsat2/Swampsat2Test.java">Swampsat2Test</a></td>
		</tr>
		<tr>
			<td>Technosat</td>
			<td>42829</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/technosat/TechnosatTest.java">TechnosatTest</a></td>
		</tr>
		<tr>
			<td>QARMAN</td>
			<td>45263</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/qarman/QarmanTest.java">QarmanTest</a></td>
		</tr>	
		<tr>
			<td>Quetzal1</td>
			<td>45598</td>
			<td>FskDemodulator 4800 baud, Ax25G3ruhBeaconSource and Quetzal1Beacon</td>
		</tr>
		<tr>
			<td>Huskysat-1</td>
			<td>45119</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/huskysat1/Huskysat1Test.java">Huskysat1Test</a></td>
		</tr>	
		<tr>
			<td>Painani-1</td>
			<td>44365</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and Painani1Beacon</td>
		</tr>
		<tr>
			<td>CHOMPTT</td>
			<td>43855</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/chomptt/ChompttTest.java">ChompttTest</a></td>
		</tr>
		<tr>
			<td>ALSAT 1N</td>
			<td>41789</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/test/java/ru/r2cloud/jradio/alsat1n/Alsat1nTest.java">Alsat1nTest</a></td>
		</tr>		
		<tr>
			<td>STRAND-1</td>
			<td>39090</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/strand1/Strand1Beacon.java">Strand1</a></td>
		</tr>		
		<tr>
			<td>PolyItan1</td>
			<td>40042</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and PolyItan1Beacon</td>
		</tr>
		<tr>
			<td>Unisat6</td>
			<td>40012</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and Unisat6Beacon</td>
		</tr>
		<tr>
			<td>Lightsail2</td>
			<td>44420</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and Lightsail2Beacon</td>
		</tr>
		<tr>
			<td>CubeBel-1</td>
			<td>43666</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and Bsusat1Beacon</td>
		</tr>
		<tr>
			<td>Salsat</td>
			<td>46495</td>
			<td>AfskDemodulator 1200 baud, -600 deviation, 1500 offset with Salsat decoder</td>
		</tr>
		<tr>
			<td>Armadillo</td>
			<td>44352</td>
			<td>FskDemodulator 19200 baud, Ax25G3ruhBeaconSource and ArmadilloBeacon</td>
		</tr>
		<tr>
			<td>Spooqy1</td>
			<td>44332</td>
			<td><a href="https://github.com/dernasherbrezon/jradio/blob/master/src/main/java/ru/r2cloud/jradio/spooqy1/Spooqy1Test.java">Spooqy1Test</a></td>
		</tr>
		<tr>
			<td>NORBI</td>
			<td>46494</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and NorbiBeacon</td>
		</tr>	
		<tr>
			<td>MEZNSAT</td>
			<td>46489</td>
			<td>FskDemodulator 2400 baud, Ax25G3ruhBeaconSource and MeznsatBeacon</td>
		</tr>
		<tr>
			<td>FALCONSAT-3</td>
			<td>30776</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and Falconsat3Beacon</td>
		</tr>
		<tr>
			<td>AMICALSAT</td>
			<td>46287</td>
			<td>AfskDemodulator 1200 baud, Ax25BeaconSource and Amical1Beacon</td>
		</tr>
		<tr>
			<td>BOBCAT-1</td>
			<td>46922</td>
			<td>FskDemodulator 1200 baud, Ax100BeaconSource and Bobcat1Beacon</td>
		</tr>
		<tr>
			<td>GRIFEX</td>
			<td>40379</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and GrifexBeacon</td>
		</tr>
		<tr>
			<td>BUGSAT-1 (TITA)</td>
			<td>40014</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and BugsatBeacon</td>
		</tr>
		<tr>
			<td>TAUSAT-1</td>
			<td>47926</td>
			<td>BpskDemodulator 9600 baud, Ax25G3ruhDecoder and Tausat1Beacon</td>
		</tr>
		<tr>
			<td>UVSQ-SAT</td>
			<td>47438</td>
			<td>BpskDemodulator 9600 baud, Ax25G3ruhDecoder and UvsqsatBeacon</td>
		</tr>
		<tr>
			<td>GRBAlpha</td>
			<td>47959</td>
			<td>FskDemodulator 9600 baud, Ax25G3ruhBeaconSource and GRBAlphaBeacon</td>
		</tr>
		<tr>
			<td>SMOG-1</td>
			<td>47964</td>
			<td>ru.r2cloud.jradio.smog1.Smog1Beacon</td>
		</tr>
		<tr>
			<td>DELFI-PQ</td>
			<td>47964</td>
			<td>ru.r2cloud.jradio.delfipq.DelfiPqBeacon</td>
		</tr>
		<tr>
			<td>GASPACS</td>
			<td>	51439</td>
			<td>ru.r2cloud.jradio.gaspacs.Gaspacs</td>
		</tr>
		<tr>
			<td>CUTE</td>
			<td>	49263</td>
			<td>ru.r2cloud.jradio.cute.CuteBeacon</td>
		</tr>
		<tr>
			<td>PICSAT</td>
			<td>43132</td>
			<td>ru.r2cloud.jradio.picsat.PicsatBeacon</td>
		</tr>
		<tr>
			<td>INSPIRESAT-1</td>
			<td>51657</td>
			<td>ru.r2cloud.jradio.is1.InspireSat1Beacon</td>
		</tr>
		<tr>
			<td>CTIM</td>
			<td>52950</td>
			<td>ru.r2cloud.jradio.ctim.CtimBeacon</td>
		</tr>
		<tr>
			<td>CSIM-FD</td>
			<td>43793</td>
			<td>ru.r2cloud.jradio.csim.CsimBeacon</td>
		</tr>
		<tr>
			<td>IRIS-A</td>
			<td>51044</td>
			<td>ru.r2cloud.jradio.iris.IrisABeacon</td>
		</tr>
		<tr>
			<td>FEES</td>
			<td>48082</td>
			<td>ru.r2cloud.jradio.fees.FeesBeacon</td>
		</tr>
		<tr>
			<td>SelfieSat</td>
			<td>53951</td>
			<td>ru.r2cloud.jradio.selfiesat.SelfieSatBeacon</td>
		</tr>
		<tr>
			<td>VZLUSAT-2</td>
			<td>51085</td>
			<td>ru.r2cloud.jradio.vzlusat.Vzlusat2Beacon</td>
		</tr>
		<tr>
			<td>NETSAT-*</td>
			<td>46507</td>
			<td>ru.r2cloud.jradio.netsat.NetSatBeacon</td>
		</tr>
		<tr>
			<td>MRC-100</td>
			<td>56993</td>
			<td>ru.r2cloud.jradio.mrc100.Mrc100</td>
		</tr>
		<tr>
			<td>Dhabisat</td>
			<td>49016</td>
			<td>ru.r2cloud.jradio.dhabi.DhabisatBeacon</td>
		</tr>
		<tr>
			<td>CONNECTA T1.1</td>
			<td>52739</td>
			<td>ru.r2cloud.jradio.connecta.Connecta11Beacon</td>
		</tr>
		<tr>
			<td>CELESTA and MTCUBE 2 (ROBUSTA 1F)</td>
			<td>53111 and 53109</td>
			<td>ru.r2cloud.jradio.celesta.CelestaBeacon</td>
		</tr>
		<tr>
			<td>StratoSat TK-1</td>
			<td>57167</td>
			<td>ru.r2cloud.jradio.sstk1.StratosatTk1 and images via ru.r2cloud.jradio.sstk1.StratosatTk1PictureDecoder</td>
		</tr>
		<tr>
			<td>RS52SB, RS52SV, RS52SG, RS52SD, RS52SE</td>
			<td>57323, 57324, 57325, 57326, 57167</td>
			<td>ru.r2cloud.jradio.sstk1.StratosatTk1PicoBeacon</td>
		</tr>
		<tr>
			<td>INSPIRE-SAT 7</td>
			<td>56211</td>
			<td>SPINO mode - ru.r2cloud.jradio.is7.InspireSat7Spino, AX25 mode using ru.r2cloud.jradio.is7.InspireSat7Beacon</td>
		</tr>
		<tr>
			<td>RS20S (GEOSCAN-EDELVEIS)</td>
			<td>53385</td>
			<td>ru.r2cloud.jradio.geoscan.GeoscanBeacon and images via ru.r2cloud.jradio.geoscan.GeoscanPictureDecoder</td>
		</tr>
		<tr>
			<td>SIREN, UMKA-1, CUBESX-HSE-AIS, CYCLOPS, ISOI, KUZBASS-300, MIET-AIS, MONITOR-1, VIZARD, CUBESX-HSE, CUBESX-SIRIUS-HSE, ORBICRAFT-ZORKIY</td>
			<td>53384, 57172, 53383, 53373, 53381, 53375, 53377, 53374, 57189, 47952, 47951, 47960</td>
			<td>ru.r2cloud.jradio.sputnix.SputnixBeacon</td>
		</tr>
		<tr>
			<td>Sharjahsat-1</td>
			<td>55104</td>
			<td>ru.r2cloud.jradio.sharjahsat.Sharjahsat1Beacon and images via ru.r2cloud.jradio.sharjahsat.Sharjahsat1PictureDecoder</td>
		</tr>
		<tr>
			<td>Sapling Giganteum</td>
			<td>56214</td>
			<td>ru.r2cloud.jradio.sapling.SaplingGiganteumBeacon</td>
		</tr>
		<tr>
			<td>ROSEYCUBESAT-1</td>
			<td>56212</td>
			<td>ru.r2cloud.jradio.roseycub.RoseyCubesatBeacon and images via ru.r2cloud.jradio.roseycub.RoseyPictureDecoder</td>
		</tr>
		<tr>
			<td>BDSAT-2</td>
			<td>55098</td>
			<td>ru.r2cloud.jradio.bdsat.BdSat2Beacon</td>
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

Where "getDopplerFrequency" can be calculated using [orekit](https://www.orekit.org) or [predict4java](https://github.com/g4dpz/predict4java). 

## Usage

Configure maven:

```xml
<dependency>
        <groupId>ru.r2cloud</groupId>
        <artifactId>jradio</artifactId>
        <version>1.57</version>
</dependency>
```

## Contributing

Contributing to the project is super easy:

 * Raise an issue with description and use case. Skip this step for small bug fixes or minor features
 * Raise pull request
 * Make sure pull request contains unit tests for new functionality or bug fix 