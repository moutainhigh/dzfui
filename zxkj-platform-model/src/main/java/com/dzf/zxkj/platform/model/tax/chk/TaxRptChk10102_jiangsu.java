package com.dzf.zxkj.platform.model.tax.chk;

public class TaxRptChk10102_jiangsu {
	public static String[] saCheckCondition = new String[] {
			//增值税纳税申报表（小规模纳税人适用）附列资料
			"增值税纳税申报表（小规模纳税人适用）附列资料!C8 <= 增值税纳税申报表（小规模纳税人适用）附列资料!A8 + 增值税纳税申报表（小规模纳税人适用）附列资料!B8",
			"增值税纳税申报表（小规模纳税人适用）附列资料!C8 <= 增值税纳税申报表（小规模纳税人适用）附列资料!A12",
			"增值税纳税申报表（小规模纳税人适用）附列资料!C16 <= 增值税纳税申报表（小规模纳税人适用）附列资料!A16 + 增值税纳税申报表（小规模纳税人适用）附列资料!B16",
			"增值税纳税申报表（小规模纳税人适用）附列资料!C16 <= 增值税纳税申报表（小规模纳税人适用）附列资料!A20",
			
			//增值税减免税申报明细表
//			免税项目，第1列“免征增值税项目销售额” ，本列“合计”等于主表第12行“其他免税销售额”“本期数”
//			"增值税减免税申报明细表!D17 == 增值税纳税申报表!E17 + 增值税纳税申报表!F17",

			"增值税减免税申报明细表!G8 <= 增值税减免税申报明细表!F8",
			"增值税减免税申报明细表!G9 <= 增值税减免税申报明细表!F9",
			"增值税减免税申报明细表!G10 <= 增值税减免税申报明细表!F10",
			"增值税减免税申报明细表!G11 <= 增值税减免税申报明细表!F11",
			"增值税减免税申报明细表!G12 <= 增值税减免税申报明细表!F12",
			"增值税减免税申报明细表!G13 <= 增值税减免税申报明细表!F13",
			
			// 免税销售额对应的进项税额小于等于扣除后免税销售额
			"增值税减免税申报明细表!G17 <= 增值税减免税申报明细表!F17",
			"增值税减免税申报明细表!G20 <= 增值税减免税申报明细表!F20",
			"增值税减免税申报明细表!G21 <= 增值税减免税申报明细表!F21",
			"增值税减免税申报明细表!G22 <= 增值税减免税申报明细表!F22",
			"增值税减免税申报明细表!G23 <= 增值税减免税申报明细表!F23",
			"增值税减免税申报明细表!G24 <= 增值税减免税申报明细表!F24",
			"增值税减免税申报明细表!G25 <= 增值税减免税申报明细表!F25",
			"增值税减免税申报明细表!G26 <= 增值税减免税申报明细表!F26",
			"增值税减免税申报明细表!G27 <= 增值税减免税申报明细表!F27",
			"增值税减免税申报明细表!G28 <= 增值税减免税申报明细表!F28",
			"增值税减免税申报明细表!G29 <= 增值税减免税申报明细表!F29",
			"增值税减免税申报明细表!G30 <= 增值税减免税申报明细表!F30",
			"增值税减免税申报明细表!G31 <= 增值税减免税申报明细表!F31",
			"增值税减免税申报明细表!G32 <= 增值税减免税申报明细表!F32"
	};
}
