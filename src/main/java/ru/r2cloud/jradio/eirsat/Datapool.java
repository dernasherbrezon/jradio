package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Datapool {

	private boolean paramvalid170;
	private boolean paramvalid43;
	private boolean paramvalid44;
	private boolean paramvalid46;
	private boolean paramvalid42;
	private boolean paramvalid56;
	private boolean paramvalid73;
	private boolean paramvalid74;
	private boolean paramvalid77;
	private boolean paramvalid85;
	private boolean paramvalid86;
	private boolean paramvalid129;
	private boolean paramvalid80;
	private boolean paramvalid84;
	private boolean paramvalid130;
	private boolean paramvalid131;
	private boolean paramvalid89;
	private boolean paramvalid95;
	private boolean paramvalid96;
	private boolean paramvalid2;
	private boolean paramvalid3;
	private boolean paramvalid7;
	private boolean paramvalid8;
	private boolean paramvalid11;
	private boolean paramvalid20;
	private boolean paramvalid22;
	private boolean paramvalid19;
	private boolean paramvalid26;
	private boolean paramvalid32;
	private boolean paramvalid36;
	private boolean paramvalid34;
	private boolean paramvalid28;
	private boolean paramvalid142;
	private boolean paramvalid141;
	private boolean paramvalid143;
	private boolean paramvalid139;
	private boolean paramvalid140;
	private boolean paramvalid149;
	private boolean paramvalid148;
	private boolean paramvalid150;
	private boolean paramvalid151;
	private boolean paramvalid144;
	private boolean paramvalid152;
	private boolean paramvalid153;
	private boolean paramvalid145;
	private boolean paramvalid154;
	private boolean paramvalid155;
	private boolean paramvalid156;
	private boolean paramvalid157;
	private boolean paramvalid158;
	private boolean paramvalid146;
	private boolean paramvalid147;
	private boolean paramvalid168;
	private boolean paramvalid169;

	public Datapool() {
		// do nothing
	}

	public Datapool(BitInputStream dis) throws IOException {
		paramvalid170 = dis.readBoolean();
		paramvalid43 = dis.readBoolean();
		paramvalid44 = dis.readBoolean();
		paramvalid46 = dis.readBoolean();
		paramvalid42 = dis.readBoolean();
		paramvalid56 = dis.readBoolean();
		paramvalid73 = dis.readBoolean();
		paramvalid74 = dis.readBoolean();
		paramvalid77 = dis.readBoolean();
		paramvalid85 = dis.readBoolean();
		paramvalid86 = dis.readBoolean();
		paramvalid129 = dis.readBoolean();
		paramvalid80 = dis.readBoolean();
		paramvalid84 = dis.readBoolean();
		paramvalid130 = dis.readBoolean();
		paramvalid131 = dis.readBoolean();
		paramvalid89 = dis.readBoolean();
		paramvalid95 = dis.readBoolean();
		paramvalid96 = dis.readBoolean();
		paramvalid2 = dis.readBoolean();
		paramvalid3 = dis.readBoolean();
		paramvalid7 = dis.readBoolean();
		paramvalid8 = dis.readBoolean();
		paramvalid11 = dis.readBoolean();
		paramvalid20 = dis.readBoolean();
		paramvalid22 = dis.readBoolean();
		paramvalid19 = dis.readBoolean();
		paramvalid26 = dis.readBoolean();
		paramvalid32 = dis.readBoolean();
		paramvalid36 = dis.readBoolean();
		paramvalid34 = dis.readBoolean();
		paramvalid28 = dis.readBoolean();
		paramvalid142 = dis.readBoolean();
		paramvalid141 = dis.readBoolean();
		paramvalid143 = dis.readBoolean();
		paramvalid139 = dis.readBoolean();
		paramvalid140 = dis.readBoolean();
		paramvalid149 = dis.readBoolean();
		paramvalid148 = dis.readBoolean();
		paramvalid150 = dis.readBoolean();
		paramvalid151 = dis.readBoolean();
		paramvalid144 = dis.readBoolean();
		paramvalid152 = dis.readBoolean();
		paramvalid153 = dis.readBoolean();
		paramvalid145 = dis.readBoolean();
		paramvalid154 = dis.readBoolean();
		paramvalid155 = dis.readBoolean();
		paramvalid156 = dis.readBoolean();
		paramvalid157 = dis.readBoolean();
		paramvalid158 = dis.readBoolean();
		paramvalid146 = dis.readBoolean();
		paramvalid147 = dis.readBoolean();
		paramvalid168 = dis.readBoolean();
		paramvalid169 = dis.readBoolean();
	}

	public boolean isParamvalid170() {
		return paramvalid170;
	}

	public void setParamvalid170(boolean paramvalid170) {
		this.paramvalid170 = paramvalid170;
	}

	public boolean isParamvalid43() {
		return paramvalid43;
	}

	public void setParamvalid43(boolean paramvalid43) {
		this.paramvalid43 = paramvalid43;
	}

	public boolean isParamvalid44() {
		return paramvalid44;
	}

	public void setParamvalid44(boolean paramvalid44) {
		this.paramvalid44 = paramvalid44;
	}

	public boolean isParamvalid46() {
		return paramvalid46;
	}

	public void setParamvalid46(boolean paramvalid46) {
		this.paramvalid46 = paramvalid46;
	}

	public boolean isParamvalid42() {
		return paramvalid42;
	}

	public void setParamvalid42(boolean paramvalid42) {
		this.paramvalid42 = paramvalid42;
	}

	public boolean isParamvalid56() {
		return paramvalid56;
	}

	public void setParamvalid56(boolean paramvalid56) {
		this.paramvalid56 = paramvalid56;
	}

	public boolean isParamvalid73() {
		return paramvalid73;
	}

	public void setParamvalid73(boolean paramvalid73) {
		this.paramvalid73 = paramvalid73;
	}

	public boolean isParamvalid74() {
		return paramvalid74;
	}

	public void setParamvalid74(boolean paramvalid74) {
		this.paramvalid74 = paramvalid74;
	}

	public boolean isParamvalid77() {
		return paramvalid77;
	}

	public void setParamvalid77(boolean paramvalid77) {
		this.paramvalid77 = paramvalid77;
	}

	public boolean isParamvalid85() {
		return paramvalid85;
	}

	public void setParamvalid85(boolean paramvalid85) {
		this.paramvalid85 = paramvalid85;
	}

	public boolean isParamvalid86() {
		return paramvalid86;
	}

	public void setParamvalid86(boolean paramvalid86) {
		this.paramvalid86 = paramvalid86;
	}

	public boolean isParamvalid129() {
		return paramvalid129;
	}

	public void setParamvalid129(boolean paramvalid129) {
		this.paramvalid129 = paramvalid129;
	}

	public boolean isParamvalid80() {
		return paramvalid80;
	}

	public void setParamvalid80(boolean paramvalid80) {
		this.paramvalid80 = paramvalid80;
	}

	public boolean isParamvalid84() {
		return paramvalid84;
	}

	public void setParamvalid84(boolean paramvalid84) {
		this.paramvalid84 = paramvalid84;
	}

	public boolean isParamvalid130() {
		return paramvalid130;
	}

	public void setParamvalid130(boolean paramvalid130) {
		this.paramvalid130 = paramvalid130;
	}

	public boolean isParamvalid131() {
		return paramvalid131;
	}

	public void setParamvalid131(boolean paramvalid131) {
		this.paramvalid131 = paramvalid131;
	}

	public boolean isParamvalid89() {
		return paramvalid89;
	}

	public void setParamvalid89(boolean paramvalid89) {
		this.paramvalid89 = paramvalid89;
	}

	public boolean isParamvalid95() {
		return paramvalid95;
	}

	public void setParamvalid95(boolean paramvalid95) {
		this.paramvalid95 = paramvalid95;
	}

	public boolean isParamvalid96() {
		return paramvalid96;
	}

	public void setParamvalid96(boolean paramvalid96) {
		this.paramvalid96 = paramvalid96;
	}

	public boolean isParamvalid2() {
		return paramvalid2;
	}

	public void setParamvalid2(boolean paramvalid2) {
		this.paramvalid2 = paramvalid2;
	}

	public boolean isParamvalid3() {
		return paramvalid3;
	}

	public void setParamvalid3(boolean paramvalid3) {
		this.paramvalid3 = paramvalid3;
	}

	public boolean isParamvalid7() {
		return paramvalid7;
	}

	public void setParamvalid7(boolean paramvalid7) {
		this.paramvalid7 = paramvalid7;
	}

	public boolean isParamvalid8() {
		return paramvalid8;
	}

	public void setParamvalid8(boolean paramvalid8) {
		this.paramvalid8 = paramvalid8;
	}

	public boolean isParamvalid11() {
		return paramvalid11;
	}

	public void setParamvalid11(boolean paramvalid11) {
		this.paramvalid11 = paramvalid11;
	}

	public boolean isParamvalid20() {
		return paramvalid20;
	}

	public void setParamvalid20(boolean paramvalid20) {
		this.paramvalid20 = paramvalid20;
	}

	public boolean isParamvalid22() {
		return paramvalid22;
	}

	public void setParamvalid22(boolean paramvalid22) {
		this.paramvalid22 = paramvalid22;
	}

	public boolean isParamvalid19() {
		return paramvalid19;
	}

	public void setParamvalid19(boolean paramvalid19) {
		this.paramvalid19 = paramvalid19;
	}

	public boolean isParamvalid26() {
		return paramvalid26;
	}

	public void setParamvalid26(boolean paramvalid26) {
		this.paramvalid26 = paramvalid26;
	}

	public boolean isParamvalid32() {
		return paramvalid32;
	}

	public void setParamvalid32(boolean paramvalid32) {
		this.paramvalid32 = paramvalid32;
	}

	public boolean isParamvalid36() {
		return paramvalid36;
	}

	public void setParamvalid36(boolean paramvalid36) {
		this.paramvalid36 = paramvalid36;
	}

	public boolean isParamvalid34() {
		return paramvalid34;
	}

	public void setParamvalid34(boolean paramvalid34) {
		this.paramvalid34 = paramvalid34;
	}

	public boolean isParamvalid28() {
		return paramvalid28;
	}

	public void setParamvalid28(boolean paramvalid28) {
		this.paramvalid28 = paramvalid28;
	}

	public boolean isParamvalid142() {
		return paramvalid142;
	}

	public void setParamvalid142(boolean paramvalid142) {
		this.paramvalid142 = paramvalid142;
	}

	public boolean isParamvalid141() {
		return paramvalid141;
	}

	public void setParamvalid141(boolean paramvalid141) {
		this.paramvalid141 = paramvalid141;
	}

	public boolean isParamvalid143() {
		return paramvalid143;
	}

	public void setParamvalid143(boolean paramvalid143) {
		this.paramvalid143 = paramvalid143;
	}

	public boolean isParamvalid139() {
		return paramvalid139;
	}

	public void setParamvalid139(boolean paramvalid139) {
		this.paramvalid139 = paramvalid139;
	}

	public boolean isParamvalid140() {
		return paramvalid140;
	}

	public void setParamvalid140(boolean paramvalid140) {
		this.paramvalid140 = paramvalid140;
	}

	public boolean isParamvalid149() {
		return paramvalid149;
	}

	public void setParamvalid149(boolean paramvalid149) {
		this.paramvalid149 = paramvalid149;
	}

	public boolean isParamvalid148() {
		return paramvalid148;
	}

	public void setParamvalid148(boolean paramvalid148) {
		this.paramvalid148 = paramvalid148;
	}

	public boolean isParamvalid150() {
		return paramvalid150;
	}

	public void setParamvalid150(boolean paramvalid150) {
		this.paramvalid150 = paramvalid150;
	}

	public boolean isParamvalid151() {
		return paramvalid151;
	}

	public void setParamvalid151(boolean paramvalid151) {
		this.paramvalid151 = paramvalid151;
	}

	public boolean isParamvalid144() {
		return paramvalid144;
	}

	public void setParamvalid144(boolean paramvalid144) {
		this.paramvalid144 = paramvalid144;
	}

	public boolean isParamvalid152() {
		return paramvalid152;
	}

	public void setParamvalid152(boolean paramvalid152) {
		this.paramvalid152 = paramvalid152;
	}

	public boolean isParamvalid153() {
		return paramvalid153;
	}

	public void setParamvalid153(boolean paramvalid153) {
		this.paramvalid153 = paramvalid153;
	}

	public boolean isParamvalid145() {
		return paramvalid145;
	}

	public void setParamvalid145(boolean paramvalid145) {
		this.paramvalid145 = paramvalid145;
	}

	public boolean isParamvalid154() {
		return paramvalid154;
	}

	public void setParamvalid154(boolean paramvalid154) {
		this.paramvalid154 = paramvalid154;
	}

	public boolean isParamvalid155() {
		return paramvalid155;
	}

	public void setParamvalid155(boolean paramvalid155) {
		this.paramvalid155 = paramvalid155;
	}

	public boolean isParamvalid156() {
		return paramvalid156;
	}

	public void setParamvalid156(boolean paramvalid156) {
		this.paramvalid156 = paramvalid156;
	}

	public boolean isParamvalid157() {
		return paramvalid157;
	}

	public void setParamvalid157(boolean paramvalid157) {
		this.paramvalid157 = paramvalid157;
	}

	public boolean isParamvalid158() {
		return paramvalid158;
	}

	public void setParamvalid158(boolean paramvalid158) {
		this.paramvalid158 = paramvalid158;
	}

	public boolean isParamvalid146() {
		return paramvalid146;
	}

	public void setParamvalid146(boolean paramvalid146) {
		this.paramvalid146 = paramvalid146;
	}

	public boolean isParamvalid147() {
		return paramvalid147;
	}

	public void setParamvalid147(boolean paramvalid147) {
		this.paramvalid147 = paramvalid147;
	}

	public boolean isParamvalid168() {
		return paramvalid168;
	}

	public void setParamvalid168(boolean paramvalid168) {
		this.paramvalid168 = paramvalid168;
	}

	public boolean isParamvalid169() {
		return paramvalid169;
	}

	public void setParamvalid169(boolean paramvalid169) {
		this.paramvalid169 = paramvalid169;
	}

}
