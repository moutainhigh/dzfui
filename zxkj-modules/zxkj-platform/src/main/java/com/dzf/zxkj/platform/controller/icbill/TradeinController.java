//package com.dzf.zxkj.platform.controller.icbill;
//
//import java.io.BufferedOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.dzf.model.ic.ic_trade.AggIcTradeVO;
//import com.dzf.model.ic.ic_trade.IctradeinVO;
//import com.dzf.model.pub.Grid;
//import com.dzf.model.pub.PrintParamVO;
//import com.dzf.model.pub.QueryParamVO;
//import com.dzf.pub.BusinessException;
//import com.dzf.pub.DzfTypeUtils;
//import com.dzf.pub.ISysConstants;
//import com.dzf.pub.StringUtil;
//import com.dzf.pub.SuperVO;
//import com.dzf.pub.Field.FieldMapping;
//import com.dzf.pub.lang.DZFBoolean;
//import com.dzf.pub.lang.DZFDate;
//import com.dzf.pub.param.IParameterConstants;
//import com.dzf.pub.util.JSONConvtoJAVA;
//import com.dzf.service.ic.ic_trade.ITradeinService;
//import com.dzf.service.pub.LogRecordEnum;
//import com.dzf.service.pub.report.PrintReportAction;
//import com.dzf.service.sys.sys_set.IParameterSetService;
//import com.itextpdf.text.DocumentException;
//
///**
// * 入库单
// *
// */
//
//@ParentPackage("basePackage")
//@Namespace("/ic")
//@Action(value = "ic_tradeinact")
//public class TradeinController extends PrintReportAction<IctradeinVO> {
//
//	private Logger log = Logger.getLogger(this.getClass());
//
//	@Autowired
//	private ITradeinService ic_tradeinserv = null;
//	@Autowired
//	private IParameterSetService parameterserv;
//
//	// public ITradeinService getIc_tradeinserv() {
//	// return ic_tradeinserv;
//	// }
//	//
//	// @Autowired
//	// public void setIc_tradeinserv(ITradeinService ic_tradeinserv) {
//	// this.ic_tradeinserv = ic_tradeinserv;
//	// }
//
//	// 查询
//	public void query() {
//		Grid grid = new Grid();
//		QueryParamVO paramvo = null;
//		grid.setTotal(0L);
//		try {
//
//			int page = getPage();
//			int rows = getRows();
//			if (page < 1 || rows < 1) {
//				throw new BusinessException("查询失败！");
//			}
//
//			paramvo = getQueryParamVO();
//			List<IctradeinVO> list = null;
//			if (paramvo != null) {
//				list = ic_tradeinserv.query(paramvo);
//			}
//			grid.setTotal(Long.valueOf(list == null ? 0 : list.size()));
//
//			if (list != null && list.size() > 0) {
//				IctradeinVO[] pvos = getPageVOs(list.toArray(new IctradeinVO[list.size()]), page, rows);
//				list = Arrays.asList(pvos);
//			}
//			grid.setRows(list == null ? new ArrayList<IctradeinVO>() : list);
//			grid.setSuccess(true);
//			grid.setMsg("查询成功");
//		} catch (Exception e) {
//			printErrorLog(grid, log, e, "查询失败");
//		}
//		String begindate = null;
//		String endate = null;
//		DZFDate udate = new DZFDate();
//		// 日志记录
//		if (paramvo == null) {
//			begindate = udate.toString();
//			endate = udate.toString();
//		} else {
//			begindate = paramvo.getBegindate1() == null ? udate.toString() : paramvo.getBegindate1().toString();
//			endate = paramvo.getEnddate() == null ? udate.toString() : paramvo.getEnddate().toString();
//		}
//		writeLogRecord(LogRecordEnum.OPE_KJ_IC_BUSI.getValue(),
//				new StringBuffer().append("入库单查询:").append(begindate).append("-").append(endate).toString(),
//				ISysConstants.SYS_2);
//		writeJson(grid);
//	}
//
//	// 将查询后的结果分页
//	private IctradeinVO[] getPageVOs(IctradeinVO[] pageVos, int page, int rows) {
//		int beginIndex = rows * (page - 1);
//		int endIndex = rows * page;
//		if (endIndex >= pageVos.length) {// 防止endIndex数组越界
//			endIndex = pageVos.length;
//		}
//		pageVos = Arrays.copyOfRange(pageVos, beginIndex, endIndex);
//		return pageVos;
//	}
//
//	private QueryParamVO getQueryParamVO() {
//		// CorpVO corpvo = getLoginCorpInfo();
//		QueryParamVO paramvo = new QueryParamVO();
//		paramvo = (QueryParamVO) DzfTypeUtils.cast(getRequest(), paramvo);
//		paramvo.setPk_corp(getLogincorppk());
//		if (paramvo.getBegindate1() == null) {
//			return null;
//		}
//		if (paramvo.getEnddate() == null) {
//			return null;
//		}
//		return paramvo;
//	}
//
//	/**
//	 * 打印操作
//	 */
//	/*
//	 * public void printAction(){ try { String strlist =
//	 * getRequest().getParameter("list"); if(strlist==null){ return; } JSONArray
//	 * array = (JSONArray) JSON.parseArray(strlist); Map<String,String>
//	 * bodymapping=FieldMapping.getFieldMapping(new PzmbbVO()); IctradeinVO[]
//	 * bodyvos =DzfTypeUtils.cast(array,bodymapping, IctradeinVO[].class,
//	 * JSONConvtoJAVA.getParserConfig()); setIscross(DZFBoolean.TRUE);//是否横向
//	 * printNoGroup(new HashMap<String, List<SuperVO>>(),bodyvos,"入库单", new
//	 * String[]{"kmmc","invname","zy","invspec","invtype","measure","dbilldate",
//	 * "nnum","ncost","pzh","memo"}, new
//	 * String[]{"科目","存货","摘要","规格","型号","计量单位","单据日期","数量","成本","凭证号","备注"},
//	 * new int[]{2,3,4,4,2,2,3,2,3,2,2},20); } catch (DocumentException e) {
//	 * //e.printStackTrace(); } catch (IOException e) { //e.printStackTrace(); }
//	 * }
//	 */
//	public void printAction() {
//		try {
//			// String strlist = getRequest().getParameter("list");
//			// String type = getRequest().getParameter("type");
//			// String pageOrt=getRequest().getParameter("pageOrt");
//			// String left =getRequest().getParameter("left");
//			// String top =getRequest().getParameter("top");
//			// String printdate=getRequest().getParameter("printdate");
//			// String font=getRequest().getParameter("font");
//			// String pageNum=getRequest().getParameter("pageNum");
//			PrintParamVO printParamVO = (PrintParamVO) DzfTypeUtils.cast(getRequest(), new PrintParamVO());
//			Map<String, String> pmap = new HashMap<String, String>();// 声明一个map用来存前台传来的设置参数
//			pmap.put("type", printParamVO.getType());
//			pmap.put("pageOrt", printParamVO.getPageOrt());
//			pmap.put("left", printParamVO.getLeft());
//			pmap.put("top", printParamVO.getTop());
//			pmap.put("printdate", printParamVO.getPrintdate());
//			pmap.put("font", printParamVO.getFont());
//			pmap.put("pageNum", printParamVO.getPageNum());
//			if (printParamVO.getList() == null) {
//				return;
//			}
//			// JSONArray json = JSONArray.parseArray("["+strlist.substring(1,
//			// strlist.length()-1)+"]");
//			if (printParamVO.getPageOrt().equals("Y")) {
//				setIscross(DZFBoolean.TRUE);// 是否横向
//			} else {
//				setIscross(DZFBoolean.FALSE);// 是否横向
//			}
//			JSONArray array = (JSONArray) JSON.parseArray(printParamVO.getList());
//			Map<String, String> bodymapping = FieldMapping.getFieldMapping(new IctradeinVO());
//			IctradeinVO[] bodyvos = DzfTypeUtils.cast(array, bodymapping, IctradeinVO[].class,
//					JSONConvtoJAVA.getParserConfig());
//			// zpm start
//			// bodyvos = reloadNewValue(bodyvos);
//			// zpm end
//			String type = printParamVO.getType();
//			Map<String, String> tmap = new HashMap<String, String>();// 声明一个map用来存title
//			tmap.put("公司", bodyvos[0].getGs());
//			tmap.put("期间", bodyvos[0].getTitlePeriod());
//
//			setDefaultValue(bodyvos, getLogincorppk());//为后续设置精度赋值
//
//			printHz(new HashMap<String, List<SuperVO>>(), bodyvos, "入 库 单",
//					new String[] { "kmmc", "invname", "zy", "invspec","measure", "dbilldate", "nnum",
//							"ncost", "pzh", "memo" },
//					new String[] { "科目", "存货", "摘要", "规格(型号)", "计量单位", "单据日期", "数量", "成本", "凭证号", "备注" },
//					new int[] { 2, 3, 5, 4, 2, 2, 3, 2, 3, 2 }, 20, type, pmap, tmap);
//		} catch (DocumentException e) {
//			log.error("入库单打印失败", e);
//		} catch (IOException e) {
//			log.error("入库单打印失败", e);
//		}
//	}
//
//	private void setDefaultValue(IctradeinVO[] bodyvos, String pk_corp){
//		if(bodyvos != null && bodyvos.length > 0){
//			for(IctradeinVO vo : bodyvos){
//				vo.setPk_corp(pk_corp);
//			}
//		}
//	}
//
//	public void expExcel(){
//
//		String str = getRequest().getParameter("list");
//		JSONArray array = JSON.parseArray(str);
//		Map<String, String> bodymapping = FieldMapping.getFieldMapping(new AggIcTradeVO());
//		AggIcTradeVO[] aggvos = DzfTypeUtils.cast(array, bodymapping,
//				AggIcTradeVO[].class, JSONConvtoJAVA.getParserConfig());
//
//		HttpServletResponse response = getResponse();
//		OutputStream toClient = null;
//
//		try {
//			response.reset();
//			String exName = new String("入库单.xls");
//			exName = new String(exName.getBytes("GB2312"), "ISO_8859_1");// 解决中文乱码问题
//			response.addHeader("Content-Disposition", "attachment;filename=" + new String(exName));
//			toClient = new BufferedOutputStream(response.getOutputStream());
//			response.setContentType("application/vnd.ms-excel;charset=gb2312");
//			byte[] length = null;
//
//			Map<String, Integer> preMap = getPreMap();//设置精度
//
//			IcBillExport exp = new IcBillExport();
//			length = exp.exportExcel(aggvos, toClient, 3, false, preMap);
//			String srt2 = new String(length, "UTF-8");
//			response.addHeader("Content-Length", srt2);
//			toClient.flush();
//			response.getOutputStream().flush();
//		} catch (IOException e) {
//			log.error("excel导出错误", e);
//		} catch (Exception e) {
//			log.error("excel导出错误", e);
//		} finally {
//			try {
//				if (toClient != null) {
//					toClient.close();
//				}
//			} catch (IOException e) {
//				log.error("excel导出错误", e);
//			}
//			try {
//				if (response!=null && response.getOutputStream() != null) {
//					response.getOutputStream().close();
//				}
//			} catch (IOException e) {
//				log.error("excel导出错误", e);
//			}
//		}
//
//		writeLogRecord(LogRecordEnum.OPE_KJ_IC_BUSI.getValue(),
//				"导出入库单",
//				ISysConstants.SYS_2);
//	}
//
//	private Map<String, Integer> getPreMap(){
//		String pk_corp = getLogincorppk();
//		String numStr = parameterserv.queryParamterValueByCode(pk_corp, IParameterConstants.DZF009);
//		String priceStr = parameterserv.queryParamterValueByCode(pk_corp, IParameterConstants.DZF010);
//		int num = StringUtil.isEmpty(numStr) ? 4 : Integer.parseInt(numStr);
//		int price = StringUtil.isEmpty(priceStr) ? 4 : Integer.parseInt(priceStr);
//		Map<String, Integer> preMap = new HashMap<String, Integer>();
//		preMap.put(IParameterConstants.DZF009, num);
//		preMap.put(IParameterConstants.DZF010, price);
//
//		return preMap;
//	}
//
//}