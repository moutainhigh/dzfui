package com.dzf.zxkj.platform.service.taxrpt.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dzf.dao.bs.SingleObjectBO;
import com.dzf.dao.jdbc.framework.SQLParameter;
import com.dzf.model.gl.jiangsutaxrpt.TaxRptConst;
import com.dzf.model.gl.taxrpt.TaxReportDetailVO;
import com.dzf.model.gl.taxrpt.TaxReportVO;
import com.dzf.model.gl.taxrpt.chk.TaxRptChk10101_xiamen;
import com.dzf.model.gl.taxrpt.chk.TaxRptChk10102_xiamen;
import com.dzf.model.sys.sys_power.CorpVO;
import com.dzf.model.sys.sys_power.UserVO;
import com.dzf.pub.DZFWarpException;
import com.dzf.pub.StringUtil;
import com.dzf.pub.lang.DZFDouble;
import com.dzf.pub.util.SafeCompute;
import com.dzf.service.gl.taxrpt.ITaxBalaceCcrService;
import com.dzf.service.spreadjs.SpreadTool;
import com.dzf.spring.SpringUtils;

@Service("taxRptservice_xiamen")
public class XmTaxRptServiceImpl extends DefaultTaxRptServiceImpl {
	@Override
	public String checkReportData(Map mapJson, CorpVO corpvo,
			TaxReportVO reportvo,
			HashMap<String, TaxReportDetailVO> hmRptDetail, SingleObjectBO sbo)
			throws DZFWarpException {
		String errmsg = "";
		if (reportvo.getSb_zlbh().equals(TaxRptConst.SB_ZLBH10412)){
			errmsg = checkForSB_ZLBH10412_xm(mapJson, corpvo, reportvo, hmRptDetail, sbo);
		}
		
		return errmsg;
	}
	
	private String checkForSB_ZLBH10412_xm(Map mapJson, CorpVO corpvo, TaxReportVO reportvo,
			HashMap<String, TaxReportDetailVO> hmRptDetail, SingleObjectBO sbo) throws DZFWarpException {
		String errmsg = "";
		ITaxBalaceCcrService taxbalancesrv = (ITaxBalaceCcrService) SpringUtils.getBean("gl_tax_formulaimpl");
		SpreadTool spreadtool = new SpreadTool(taxbalancesrv);
		
		List<String> listReportName = spreadtool.getReportNameList(mapJson);
		
		String rpt1 = "A200000所得税月(季)度预缴纳税申报表";
		if (listReportName.contains(rpt1)) {
			String qylx = (String) spreadtool.getCellValue(mapJson, rpt1, 5, 1);
			if ("一般企业".equals(qylx) || "跨地区经营汇总纳税企业总机构".equals(qylx)) {
				
				if (StringUtil.isEmpty((String) spreadtool.getCellValue(mapJson, rpt1, 30, 8))) {
					errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写是否科技型中小企业<br>";
				}
				if (StringUtil.isEmpty((String) spreadtool.getCellValue(mapJson, rpt1, 31, 3))) {
					errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写是否高新技术企业<br>";
				}
				if (StringUtil.isEmpty((String) spreadtool.getCellValue(mapJson, rpt1, 31, 8))) {
					errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写是否技术入股递延纳税事项<br>";
				}
				
//				if(PeriodType.jidureport == reportvo.getPeriodtype()){//季报才校验
					if (StringUtil.isEmpty((String) spreadtool.getCellValue(mapJson, rpt1, 30, 3))) {//小型微利企业
						errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写是否小型微利企业<br>";
					}
					
					if (getDzfDouble(spreadtool.getCellValue(mapJson, rpt1, 33, 8)).doubleValue() <= 0) {
						errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写季末从业人数，且人数必须大于0且为整数<br>";
					}
					
					if (getDzfDoubleByMinus(spreadtool.getCellValue(mapJson, rpt1, 34, 3)).doubleValue() < 0) {
						errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写季初资产总额（万元），且季初资产总额必须大于等于0<br>";
					}
					if (getDzfDoubleByMinus(spreadtool.getCellValue(mapJson, rpt1, 34, 8)).doubleValue() < 0) {
						errmsg += "企业类型为“一般企业”或“跨地区经营汇总纳税企业总机构”的纳税人必须填写季末资产总额（万元），且季末资产总额必须大于等于0<br>";
					}
//				}
				
			}
			
			DZFDouble line12 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt1, 19, 8));
			DZFDouble line11 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt1, 18, 8));
			
			if (line12.doubleValue() > line11.doubleValue()
					|| line12.doubleValue() < 0) {
				errmsg += "A200000所得税月(季)度预缴纳税申报表第12行<=主表第11行应纳所得税额本年累计金额，且第12行≥0<br>";
			}
		}
		
		String rpt2 = "A201010免税收入、减计收入、所得减免等优惠明细表";
		if(listReportName.contains(rpt2)){
			DZFDouble v = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 7, 5));
			DZFDouble v1 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 8, 5));
			DZFDouble v2 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 9, 5));
			DZFDouble v3 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 10, 5));
			DZFDouble v4 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 11, 5));
			
			DZFDouble result = SafeCompute.add(v1, v2);
			result = SafeCompute.add(result, v3);
			result = SafeCompute.add(result, v4);
			
			if(v.doubleValue() < result.doubleValue()){
				errmsg += "A201010免税收入、减计收入、所得减免等优惠明细表 第3行>=第4+5+6+7行<br>";
			}
			
			DZFDouble ss = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 39, 5));
			DZFDouble s1 = getDzfDouble(spreadtool.getCellValue(mapJson, rpt2, 40, 5));
			
			if(ss.doubleValue() < s1.doubleValue()){
				errmsg += "A201010免税收入、减计收入、所得减免等优惠明细表 第33行>=第33.1行<br>";
			}
		}
		
		return errmsg;
	}
	
	@Override
	public String[] getCondition(String pk_taxreport, UserVO userVO,
			TaxReportVO reportvo, SingleObjectBO sbo) throws DZFWarpException {

		List<String> listCondition = new ArrayList<String>();
		if (TaxRptConst.SB_ZLBH10101.equals(reportvo.getSb_zlbh())) {
			String[] sacondition = TaxRptChk10101_xiamen.saCheckCondition;
			// 读取报表内容
			SQLParameter params = new SQLParameter();
			params.addParam(reportvo.getPk_taxreport());
			TaxReportDetailVO[] vos = (TaxReportDetailVO[]) sbo
					.queryByCondition(TaxReportDetailVO.class,
							"pk_taxreport = ? and nvl(dr,0) = 0", params);
			HashMap<String, TaxReportDetailVO> hmDetail = new HashMap<String, TaxReportDetailVO>();
			for (TaxReportDetailVO detailvo : vos) {
				hmDetail.put(detailvo.getReportname().trim(), detailvo);
			}

			// 排除公式中含有没有显示报表的公式
			lab1: for (String condition : sacondition) {
				String[] saReportname = getReportNameFromCondition(condition);
				for (String reportname : saReportname) {
					if (hmDetail.containsKey(reportname.trim()) == false) {
						continue lab1;
					}
				}
				listCondition.add(condition);
			}
		} else if (TaxRptConst.SB_ZLBH10102.equals(reportvo.getSb_zlbh())) {
			String[] sacondition = TaxRptChk10102_xiamen.saCheckCondition;
			// 读取报表内容
			SQLParameter params = new SQLParameter();
			params.addParam(reportvo.getPk_taxreport());
			TaxReportDetailVO[] vos = (TaxReportDetailVO[]) sbo
					.queryByCondition(TaxReportDetailVO.class,
							"pk_taxreport = ? and nvl(dr,0) = 0", params);
			HashMap<String, TaxReportDetailVO> hmDetail = new HashMap<String, TaxReportDetailVO>();
			for (TaxReportDetailVO detailvo : vos) {
				hmDetail.put(detailvo.getReportname().trim(), detailvo);
			}

			// 排除公式中含有没有显示报表的公式

			lab1: for (String condition : sacondition) {
				String[] saReportname = getReportNameFromCondition(condition);
				for (String reportname : saReportname) {
					if (hmDetail.containsKey(reportname) == false) {
						continue lab1;
					}
				}
				listCondition.add(condition);
			}
		}
		return listCondition.toArray(new String[0]);
	}

	public String getLocation(CorpVO corpvo) throws DZFWarpException {
		return "厦门";
	}
}
