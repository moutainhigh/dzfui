package com.dzf.zxkj.platform.service.sys;

import com.dzf.zxkj.base.exception.DZFWarpException;
import com.dzf.zxkj.platform.model.image.DcModelBVO;
import com.dzf.zxkj.platform.model.image.DcModelHVO;

import java.io.InputStream;
import java.util.List;

public interface IDcpzService {
    //保存
    DcModelHVO save(DcModelHVO vo) throws DZFWarpException;

    //修改
//	public void update(DcModelHVO vo)throws DZFWarpException;

    //查询主表，快速制单
    List<DcModelHVO> queryself(String pk_corp, String quickcreate) throws DZFWarpException;

    //查询主表，快速制单
    List<DcModelHVO> queryself(String pk_corp, String quickcreate, DcModelHVO hvo) throws DZFWarpException;

    //查询当前公司的模板。查询自己的模板。在加上集团的模板。合并成自己的一套模板，查询主表
    List<DcModelHVO> query(String pk_corp) throws DZFWarpException;

    //查询子表，带翻译科目pk
    List<DcModelBVO> queryByPId(String pid, String pk_corp) throws DZFWarpException;

    //根据主表ID删除子表记录
    void delete(String pid, String pk_corp) throws DZFWarpException;

    //校验
    String check(String pk_corp, DcModelHVO headvo);

    //公司间模板复制
    String copyCorpToCorp(String[] gs, String[] ids, String userid, String loginpk) throws DZFWarpException;

    /**
     * excel导入保存
     */
    String saveImp(InputStream is, String fileType, String userid, String pk_corp) throws DZFWarpException;


    /**
     * 智能识别调用
     *
     * @param pk_corp
     * @param keywords
     * @return
     * @throws DZFWarpException
     */
    List<DcModelHVO> queryAccordBankModel(String pk_corp, String[] keywords) throws DZFWarpException;


}