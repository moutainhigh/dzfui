package com.dzf.zxkj.report.excel.cwbb;

import com.dzf.zxkj.common.lang.DZFDate;
import com.dzf.zxkj.excel.param.Fieldelement;
import com.dzf.zxkj.excel.param.IExceport;
import com.dzf.zxkj.platform.model.report.XjllquarterlyVo;

import java.util.Date;


/**
 * 利润表季报表季报导出配置
 * 
 * @author zhw
 *
 */
public class XjllQuarterlyExcelField implements IExceport<XjllquarterlyVo> {

	private XjllquarterlyVo[] lrbvos = null;

	private String qj = null;

	private String now = DZFDate.getDate(new Date()).toString();

	private String creator = null;

	private String corpname = null;

	private boolean zeroshownull = true;


	@Override
	public String getExcelport2007Name() {
		return "现金流量季报("+corpname+")-" + qj.replace("-", "") + ".xlsx";
	}

	@Override
	public String getExcelport2003Name() {
		return "现金流量季报("+corpname+")-" + qj.replace("-", "") + ".xls";
	}

	@Override
	public String getExceportHeadName() {
		return "现金流量季报";
	}

	@Override
	public String getSheetName() {
		return "现金流量季报";
	}

	@Override
	public XjllquarterlyVo[] getData() {
		return lrbvos;
	}

	@Override
	public Fieldelement[] getFieldInfo() {
		return  new Fieldelement[] {
				new Fieldelement("xm", "项目", false, 0, false,60,false),
				new Fieldelement("hc", "行次", false, 0, false,10,false),
				new Fieldelement("bnlj", "本年累计", true, 2, zeroshownull),
				new Fieldelement("jd1", "第一季度", true, 2, zeroshownull),
				new Fieldelement("jd2", "第二季度", true, 2, zeroshownull),
				new Fieldelement("jd3", "第三季度", true, 2, zeroshownull),
				new Fieldelement("jd4", "第四季度", true, 2, zeroshownull),
				new Fieldelement("bf_bnlj", "上年同期数", true, 2, zeroshownull),
		};
	}

	public void setLrbvos(XjllquarterlyVo[] lrbvos) {
		this.lrbvos = lrbvos;
	}

	@Override
	public String getQj() {
		return qj;
	}

	@Override
	public String getCreateSheetDate() {
		return now;
	}

	@Override
	public String getCreateor() {
		return creator;
	}

	public void setQj(String qj) {
		this.qj = qj;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String getCorpName() {
		return corpname;
	}

	public void setCorpName(String corpname) {
		this.corpname = corpname;
	}

	@Override
	public boolean[] isShowTitDetail() {
		return new boolean[] { true, true, true };
	}

	public boolean isZeroshownull() {
		return zeroshownull;
	}

	public void setZeroshownull(boolean zeroshownull) {
		this.zeroshownull = zeroshownull;
	}
}
