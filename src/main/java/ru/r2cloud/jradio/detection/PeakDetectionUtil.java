package ru.r2cloud.jradio.detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeakDetectionUtil {

	// algorithm was taken from https://www.sthu.org/blog/13-perstopology-peakdetection/index.html
	public static List<Peak> getPersistentHomology(float[] seq) {
		List<Peak> peaks = new ArrayList<>();
		Integer[] indices = new Integer[seq.length];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = i;
		}
		Integer[] idxtopeak = new Integer[seq.length];
		Arrays.sort(indices, new IndexSorter(seq));
		boolean lftdone;
		boolean rgtdone;
		Integer il = null;
		Integer ir = null;
		for (int i = 0; i < indices.length; i++) {
			int idx = indices[i];
			lftdone = (idx > 0 && idxtopeak[idx - 1] != null);
			rgtdone = (idx < seq.length - 1 && idxtopeak[idx + 1] != null);
			if (lftdone) {
				il = idxtopeak[idx - 1];
			} else {
				il = null;
			}
			if (rgtdone) {
				ir = idxtopeak[idx + 1];
			} else {
				ir = null;
			}
			if (!lftdone && !rgtdone) {
				peaks.add(new Peak(idx));
				idxtopeak[idx] = peaks.size() - 1;
			}
			// il check is not necessary. just make eclipse happy
			if (lftdone && il != null && !rgtdone) {
				peaks.get(il).setRight(peaks.get(il).getRight() + 1);
				idxtopeak[idx] = il;
			}
			// ir check is not necessary. just make eclipse happy
			if (!lftdone && rgtdone && ir != null) {
				peaks.get(ir).setLeft(peaks.get(ir).getLeft() - 1);
				idxtopeak[idx] = ir;
			}
			// ir and il checks are not necessary. just make eclipse happy
			if (lftdone && rgtdone && il != null && ir != null) {
				if (seq[peaks.get(il).getIndex()] > seq[peaks.get(ir).getIndex()]) {
					peaks.get(ir).setDied(idx);
					peaks.get(il).setRight(peaks.get(ir).getRight());
					idxtopeak[peaks.get(il).getRight()] = idxtopeak[idx] = il;
				} else {
					peaks.get(il).setDied(idx);
					peaks.get(ir).setLeft(peaks.get(il).getLeft());
					idxtopeak[peaks.get(ir).getLeft()] = idxtopeak[idx] = ir;
				}
			}
		}
		return peaks;
	}

	private PeakDetectionUtil() {
		//do nothing
	}
}
