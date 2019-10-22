package com.dzf.zxkj.report.controller.cwbb;

import com.alibaba.fastjson.JSON;
import com.dzf.zxkj.base.exception.BusinessException;
import com.dzf.zxkj.base.utils.DzfTypeUtils;
import com.dzf.zxkj.base.utils.FieldMapping;
import com.dzf.zxkj.common.constant.ISysConstants;
import com.dzf.zxkj.common.entity.Grid;
import com.dzf.zxkj.common.entity.ReturnData;
import com.dzf.zxkj.common.enums.LogRecordEnum;
import com.dzf.zxkj.common.lang.DZFDate;
import com.dzf.zxkj.common.model.SuperVO;
import com.dzf.zxkj.common.query.QueryParamVO;
import com.dzf.zxkj.common.utils.DateUtils;
import com.dzf.zxkj.common.utils.StringUtil;
import com.dzf.zxkj.jackson.annotation.MultiRequestBody;
import com.dzf.zxkj.platform.model.bdset.PzmbbVO;
import com.dzf.zxkj.platform.model.report.XjllbVO;
import com.dzf.zxkj.platform.model.report.XjllquarterlyVo;
import com.dzf.zxkj.platform.model.sys.CorpTaxVo;
import com.dzf.zxkj.platform.model.sys.CorpVO;
import com.dzf.zxkj.report.controller.ReportBaseController;
import com.dzf.zxkj.report.service.cwbb.IXjllbQuarterlyReport;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("gl_rep_xjlyquarbact")
@Slf4j
public class XjllbQuarterlyController extends ReportBaseController {
    @Autowired
    private IXjllbQuarterlyReport gl_rep_xjlyquarbserv;


    @PostMapping("/queryAction")
    public ReturnData<Grid> queryAction(@MultiRequestBody QueryParamVO queryvo, @MultiRequestBody CorpVO corpVO) {
        Grid grid = new Grid();
        QueryParamVO vo = getQueryParamVO(queryvo,corpVO);
        String[] resvalue = getJdValue(vo.getBegindate1());
        try {
            if (vo != null) {
                checkPowerDate(vo,corpVO);
                List<XjllquarterlyVo> xjllbvos = gl_rep_xjlyquarbserv.getXjllQuartervos(vo,resvalue[1]);
                if (xjllbvos != null && xjllbvos.size() > 0) {
                    grid.setTotal((long) xjllbvos.size());
                    grid.setRows(xjllbvos);
                }
            }
            grid.setSuccess(true);
        } catch (Exception e) {
            grid.setRows(new ArrayList<XjllbVO>());
            printErrorLog(grid, e, "查询失败！");
        }

        // 日志记录接口
//        writeLogRecord(LogRecordEnum.OPE_KJ_CWREPORT.getValue(), "现金流量季报查询:" +resvalue[0], ISysConstants.SYS_2);

        return ReturnData.ok().data(grid);

    }

    private String[] getJdValue(DZFDate date){
        String month = date.toString().substring(5, 7);
        String res = null;
        String count = "0";
        if(month.equals("02") || month.equals("01") || month.equals("03") ){
            res = "第一季度";
            count ="1";
        }else if(month.equals("04") || month.equals("05") || month.equals("06") ){
            res = "第二季度";
            count ="2";
        }else if(month.equals("07") || month.equals("08") || month.equals("09") ){
            res = "第三季度";
            count ="3";
        }else if(month.equals("10") || month.equals("11") || month.equals("12") ){
            res = "第四季度";
            count ="4";
        }
        return new String[]{res,count};
    }





}
