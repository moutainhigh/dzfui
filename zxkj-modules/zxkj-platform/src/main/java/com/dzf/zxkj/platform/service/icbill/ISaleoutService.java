package com.dzf.zxkj.platform.service.icbill;

import com.dzf.zxkj.base.exception.DZFWarpException;
import com.dzf.zxkj.common.lang.DZFDate;
import com.dzf.zxkj.platform.model.icset.AggIcTradeVO;
import com.dzf.zxkj.platform.model.icset.IntradeHVO;
import com.dzf.zxkj.platform.model.icset.IntradeParamVO;
import com.dzf.zxkj.platform.model.icset.IntradeoutVO;
import com.dzf.zxkj.platform.model.pzgl.TzpzHVO;
import com.dzf.zxkj.platform.model.sys.CorpVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

 public interface ISaleoutService {
	
	/**
	 * 查询所有数据
	 * @param paramvo
	 * @return
	 * @throws DZFWarpException
	 */
	 List<IntradeHVO> query(IntradeParamVO paramvo) throws DZFWarpException;
	
	 String getNewBillNo(String pk_corp, DZFDate dbilldate, String prefix) throws DZFWarpException;

	 IntradeHVO saveSale(IntradeHVO headvo, boolean flag, boolean isImpl) throws DZFWarpException;

	 List<IntradeoutVO> querySub(IntradeHVO vo) throws DZFWarpException;

	 void deleteSale(IntradeHVO vo, String pk_corp) throws DZFWarpException;

	 void saveToGL(IntradeHVO vo, CorpVO corpvo, String userid, String zy) throws DZFWarpException;

	 void saveDashBack(IntradeHVO vo, CorpVO corpvo, String userid, String login_date) throws DZFWarpException;

	 void rollbackTogl(IntradeHVO vo, String pk_corp) throws DZFWarpException;

	 void deleteIntradeoutBill(TzpzHVO pzHeadVO) throws DZFWarpException;

	 IntradeHVO queryIntradeHVOByID(String pk_ictrade_h, String pk_corp) throws DZFWarpException;

	 void saveToGL(IntradeHVO[] vos, String pk_corp, String userid, String zy) throws DZFWarpException;

	 AggIcTradeVO[] queryAggIntradeVOByID(String pk_ictrade_h, String pk_corp) throws DZFWarpException ;

	 String saveImp(MultipartFile file, String pk_corp, String fileType, String cuserid) throws DZFWarpException;
	
	 StringBuffer buildQmjzMsg(List<String> periodList, String pk_corp) throws DZFWarpException;

	 void check(IntradeHVO hvo, String pk_corp, boolean iscopy) throws DZFWarpException;

	 IntradeHVO saveSaleAndGl(IntradeHVO headvo, boolean flag) throws DZFWarpException;
}
