package ru.r2cloud.jradio.ctim;

public enum CmdOpCode {

	NOOP(0), ARM(1), CMD_RST_STATS(2), CMD_XSUM(3), CMD_ECHO_STATE(4), CMD_VERSION(5), LOG_ROUTE(48), LOG_STATE(49), LOG_RST_STATS(50), LOG_RESET_READ(52), LOG_ISSUE(53), LOG_DUMP_INFO(54), LOG_SET_PUBLISH(55), MEM_DUMP(80), MEM_LOAD(81), MEM_ERASE(82), MEM_XSUM(83), MEM_ABORT(84), MEM_RESET(85),
	MEM_LOAD_DWORD(86), MEM_LOAD_WORD(87), MEM_LOAD_BYTE(88), PKT_ISSUE(96), PKT_SET_RATE(97), PKT_SET_STREAM(98), PKT_SET_PRIORITY(99), PKT_QUERY_APID(100), PKT_SET_DELAY(101), PKT_COMMIT_TABLE(102), TBL_DUMP(112), TBL_LOAD(113), TBL_LOAD_START(114), TBL_COMMIT(115), TBL_ABORT(116),
	TBL_VERIFY(117), TBL_SAVE(118), DES_RST_STATS(33), DES_ADD_TASK(34), DES_SUB_TASK(35), DES_ADD_BACK(36), DES_SUB_BACK(37), DES_SET_TIME(38), DES_DUMP_SCHED(39), DES_SET_TASK_NUM(40), SEQ_LOAD(64), SEQ_INIT(65), SEQ_STATE(66), SEQ_VERIFY(67), SEQ_STOP(68), SEQ_FIND(69), SEQ_INFO(70),
	SEQ_DUMP(71), SEQ_RST_STATS(72), SEQ_LD_START(73), SEQ_LD_COMMIT(74), SEQ_LD_ABORT(75), SEQ_LIBRARY(76), FP_RST_STATS(16), FP_RST_RESULTS(17), FP_VALIDATE(18), FP_SET_STATE(19), FP_SET_WP_STATE(20), FP_DUMP_RESULTS(22), FP_SET_WP(23), FP_SET_WP_THRESH(24), FP_SET_WP_RESP(25), FP_SET_TEST(26),
	CMD_OPCODE_UHF_PASS(128), CMD_OPCODE_UHF_INIT(129), CMD_OPCODE_UHF_RESP_STATE(130), ADCS_PASS(144), ADCS_RESET(145), ADCS_READ(146), ADCS_COARSE_POINT(147), ADCS_FINE_POINT(148), ADCS_RAM_POINT(149), ADCS_FINE_UPDATE(150), ADCS_RAM_UPDATE(151), ADCS_ECLIPSE_CHECK(152), ADCS_DUMP_IMAGE(153),
	ADCS_DUMP_STATE(154), ADCS_INIT_EPHEMERIS(155), ADCS_SET_TIME(156), CMD_OPCODE_CFI_PWR_ON(224), CMD_OPCODE_CFI_PWR_OFF(225), CMD_OPCODE_CFI_SELECT(226), CMD_OPCODE_CFI_SELECT_TRIO(227), STORE_WRITE_STATE(240), STORE_READ(241), STORE_HALT(242), STORE_PLAYBACK(243), STORE_SELECT(245),
	STORE_SET_PARTITION(246), STORE_RESET_PARTITION(247), STORE_FLUSH(248), STORE_MOVE_READ(249), PAYLOAD_MSG_STATE(208), PAYLOAD_HK_RESET(209), PAYLOAD_FLAGS_RESET(210), EPS_SET_BAT_TIME(203), EPS_GET_BAT_TIME(204), EPS_BAT_PASS(205), EPS_BAT_TLM_STATE(206), EPS_BAT_HEATER(207), EPS_PWR_ON(192),
	EPS_PWR_OFF(193), EPS_PWR_CYCLE(194), EPS_DEPLOY(195), EPS_ANT_BURN(196), EPS_ANT_HALT_BURN(197), EPS_ANT_SET_BACKUP(198), EPS_ANT_RESET(199), EPS_DEPLOY_ABORT(200), EPS_ANT_TLM(201), EPS_RELAY(202), MRAM_CLEAR(160), MRAM_ERR_INJ(161), MRAM_PWR(162), MRAM_PWR_TOGGLE(163), FLASH_SEL_PAYLOAD(164),
	CLT_RESET(239), CLT_THRESHOLD(238), WATCHDOG(237), PHOENIX(236), SAFE(235), NOMINAL(234), NAND_BB_INIT(169), NAND_REMAP(170), NAND_BAD_BLOCK(171), NAND_FORMAT(172), NAND_FORMAT_HALT(173), SBAND_SYNC_ON(176), SBAND_SYNC_OFF(177), SBAND_CLOCK(178), UNKNOWN(255);

	private final int code;

	private CmdOpCode(int code) {
		this.code = code;
	}

	public static CmdOpCode valueOfCode(int code) {
		for (CmdOpCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
