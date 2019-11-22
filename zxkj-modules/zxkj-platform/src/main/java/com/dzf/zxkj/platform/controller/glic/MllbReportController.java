package com.dzf.zxkj.platform.controller.glic;

import com.dzf.zxkj.base.exception.BusinessException;
import com.dzf.zxkj.common.constant.IParameterConstants;
import com.dzf.zxkj.common.entity.Grid;
import com.dzf.zxkj.common.entity.Json;
import com.dzf.zxkj.common.entity.ReturnData;
import com.dzf.zxkj.common.lang.DZFBoolean;
import com.dzf.zxkj.common.model.ColumnCellAttr;
import com.dzf.zxkj.common.query.PrintParamVO;
import com.dzf.zxkj.common.query.QueryParamVO;
import com.dzf.zxkj.common.utils.StringUtil;
import com.dzf.zxkj.excel.util.Excelexport2003;
import com.dzf.zxkj.jackson.utils.JsonUtils;
import com.dzf.zxkj.pdf.PrintReporUtil;
import com.dzf.zxkj.platform.excel.MllbExcelField;
import com.dzf.zxkj.platform.model.glic.InventorySetVO;
import com.dzf.zxkj.platform.model.report.MllDetailVO;
import com.dzf.zxkj.platform.model.report.ReportDataGrid;
import com.dzf.zxkj.platform.service.IZxkjPlatformService;
import com.dzf.zxkj.platform.service.glic.IInventoryAccSetService;
import com.dzf.zxkj.platform.service.glic.IMllbReport;
import com.dzf.zxkj.platform.service.sys.IParameterSetService;
import com.dzf.zxkj.platform.util.SystemUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 毛利率统计表
 */
@RestController
@RequestMapping("/glic/gl_rep_mllbact")
@Slf4j
public class MllbReportController  extends GlicReportController{
    @Autowired
    private IMllbReport iMllbReport;
    @Autowired
    private IParameterSetService parameterserv;
    @Autowired
    private IInventoryAccSetService gl_ic_invtorysetserv;
    @Autowired
    private IZxkjPlatformService zxkjPlatformService;

    @GetMapping("/query")
    public ReturnData<Json> queryAction(@RequestParam Map<String, String> param){
        ReportDataGrid grid = new ReportDataGrid();

        QueryParamVO queryParamvo = JsonUtils.convertValue(param, QueryParamVO.class);
        checkPowerDate(queryParamvo);
        List<MllDetailVO> result  = iMllbReport.queryMllMx(queryParamvo, SystemUtil.getLoginCorpVo(),queryParamvo.getPk_inventory());

        if(result == null || result.size() == 0){
            throw new BusinessException("查询数据为空");
        }
        //将查询后的数据分页展示
        List<MllDetailVO> list = getPagedMllDetailVO(result, queryParamvo.getPage(), queryParamvo.getRows(), grid);
        grid.setRows(list);
        grid.setSuccess(true);
        return ReturnData.ok().data(grid);
    }

    private List<MllDetailVO> getPagedMllDetailVO(List<MllDetailVO> result, int page, int rows, Grid grid) {

        if(result == null || result.isEmpty() ){
            grid.setTotal(0L);
            return new ArrayList<MllDetailVO>();
        }
        grid.setTotal((long)result.size());
        return result.subList((page-1)*rows, page * rows > result.size() ? result.size() : page * rows);
    }

    private List<MllDetailVO> getCurrSp(List<MllDetailVO> result, String currsp) {
        List<MllDetailVO> list = new ArrayList<>();
        for(MllDetailVO mllDetailVO : result){
            if(mllDetailVO.getFzid() != null &&currsp.indexOf(mllDetailVO.getFzid()) != -1){
                list.add(mllDetailVO);
            }
        }
        return list;
    }

    @PostMapping("print")
    public void printAction(@RequestBody Map<String, String> pmap, HttpServletResponse response) {
        String period = "";
        try {
            PrintReporUtil printReporUtil = new PrintReporUtil(zxkjPlatformService, SystemUtil.getLoginCorpVo(), SystemUtil.getLoginUserVo(), response);
            PrintParamVO printvo = JsonUtils.convertValue(pmap, PrintParamVO.class);//
            String strlist = pmap.get("list");
            if (strlist == null) {
                return;
            }
            if (printvo.getPageOrt().equals("Y")) {
                printReporUtil.setIscross(DZFBoolean.TRUE);// 是否横向
            } else {
                printReporUtil.setIscross(DZFBoolean.FALSE);// 是否横向
            }
            MllDetailVO[] bodyvos= JsonUtils.convertValue(strlist, MllDetailVO[].class);
            if(bodyvos!=null && bodyvos.length>0){
                for(MllDetailVO vo:bodyvos){
                    vo.setPk_corp(SystemUtil.getLoginCorpId());
                }
            }
            period = bodyvos[0].getTitlePeriod();
            Map<String, String> tmap = new HashMap<String, String>();// 声明一个map用来存前台传来的设置参数
            tmap.put("公司", bodyvos[0].getGs());
            tmap.put("期间", period);
            ColumnCellAttr[] columncellattrvos= JsonUtils.convertValue(printvo.getColumnslist(), ColumnCellAttr[].class);
            printReporUtil.setTableHeadFount(new Font(printReporUtil.getBf(), Float.parseFloat(pmap.get("font")), Font.NORMAL));//设置表头字体

            //初始化表体列编码和列名称
            printReporUtil.printReport(bodyvos,"毛利率统计表", Arrays.asList(columncellattrvos),18,pmap.get("type"),pmap,tmap);
        } catch (DocumentException e) {
            log.error("毛利率统计表打印错误", e);
        } catch (IOException e) {
            log.error("毛利率统计表打印错误", e);
        }catch (Exception e) {
            log.error("毛利率统计表打印失败", e);
        }finally {
            try {
                if (response != null && response.getOutputStream() != null) {
                    response.getOutputStream().close();
                }
            } catch (IOException e) {
                log.error("毛利率统计表打印错误", e);
            }
        }
        //日志记录接口
//        writeLogRecord(LogRecordEnum.OPE_KJ_CHGL.getValue(),
//                "毛利率统计表:打印期间“" + period + "”存货数据", ISysConstants.SYS_2);
    }

    /**
     * 导出excel
     */
    @PostMapping("/expExcel")
    public void expExcel(HttpServletResponse response, @RequestParam Map<String, String> param){
        OutputStream toClient = null;
        String qj = "";
        try {
            String strlist = param.get("list");
            if (strlist == null) {
                return;
            }
            MllDetailVO[] vo= JsonUtils.convertValue(strlist, MllDetailVO[].class);
            String gs = vo[0].getGs();
            qj = vo[0].getTitlePeriod();
            Excelexport2003<MllDetailVO> lxs = new Excelexport2003<>();

            String pk_corp = SystemUtil.getLoginCorpId();
            String numStr = parameterserv.queryParamterValueByCode(pk_corp, IParameterConstants.DZF009);
            String priceStr = parameterserv.queryParamterValueByCode(pk_corp, IParameterConstants.DZF010);
            int num = StringUtil.isEmpty(numStr) ? 2 : Integer.parseInt(numStr);
            int price = StringUtil.isEmpty(priceStr) ? 2 : Integer.parseInt(priceStr);
            InventorySetVO inventorySetVO = gl_ic_invtorysetserv.query(pk_corp);
            MllbExcelField xsz = new MllbExcelField(num, price, inventorySetVO.getChcbjzfs());
            xsz.setFields(xsz.getFields());
            xsz.setMllDetailVos(vo);
            xsz.setQj(qj);
            xsz.setCreator(SystemUtil.getLoginUserVo().getUser_name());
            xsz.setCorpname(gs);
            response.reset();
            String filename = xsz.getExcelport2003Name();
            String formattedName = URLEncoder.encode(filename, "UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + filename + ";filename*=UTF-8''" + formattedName);
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            lxs.exportExcel(xsz, toClient);
            toClient.flush();
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.error("毛利率统计表excel导出错误", e);
        } catch (Exception e) {
            log.error("毛利率统计表excel导出错误", e);
        }finally {
            try {
                if (toClient != null) {
                    toClient.close();
                }
            } catch (IOException e) {
                log.error("毛利率统计表excel导出错误", e);
            }
            try {
                if (response!=null && response.getOutputStream() != null) {
                    response.getOutputStream().close();
                }
            } catch (IOException e) {
                log.error("毛利率统计表excel导出错误", e);
            }
        }
        //日志记录接口
//        writeLogRecord(LogRecordEnum.OPE_KJ_CHGL.getValue(),
//                "毛利率统计表:导出期间“" + qj + "”存货数据", ISysConstants.SYS_2);
    }
}
