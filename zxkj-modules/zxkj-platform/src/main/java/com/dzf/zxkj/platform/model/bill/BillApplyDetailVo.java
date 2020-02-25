package com.dzf.zxkj.platform.model.bill;

import com.dzf.zxkj.common.lang.DZFDateTime;
import com.dzf.zxkj.common.lang.DZFDouble;
import com.dzf.zxkj.common.model.SuperVO;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 申请单，商品明细
 *
 * @author zhangj
 */
public class BillApplyDetailVo extends SuperVO {

    public static final String TABLE_NAME = "ynt_app_billapply_detail";

    public static final String PK_FIELD = "pk_app_billapply_detail";

    @JsonProperty("id")
    private String pk_app_billapply_detail;//
    @JsonProperty("pid")
    private String pk_app_billapply;//
    @JsonProperty("spid")
    private String pk_app_commodity;//商品id
    private String pk_inventory;//启用存货的id
    private String spmc;//商品名称
    @JsonProperty("corp")
    private String pk_corp;//
    @JsonProperty("tcorp")
    private String pk_temp_corp;//
    @JsonProperty("hsdj")
    private DZFDouble nprice;//含税单价
    @JsonProperty("num")
    private DZFDouble nnum;// 数量
    @JsonProperty("hsje")
    private DZFDouble nmny;// 含税金额
    @JsonProperty("sl")
    private DZFDouble ntax;// 税率
    @JsonProperty("se")
    private DZFDouble ntaxmny;// 税额
    @JsonProperty("zkl")
    private DZFDouble nzktax;// 折扣率
    @JsonProperty("zke")
    private DZFDouble nzkmny;// 折扣额
    private DZFDateTime ts;//
    private Integer dr;//


    private String ggxh;
    private String jldw;
    private DZFDouble sl;

    public String getPk_inventory() {
        return pk_inventory;
    }

    public void setPk_inventory(String pk_inventory) {
        this.pk_inventory = pk_inventory;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public DZFDouble getSl() {
        return sl;
    }

    public void setSl(DZFDouble sl) {
        this.sl = sl;
    }

    public String getPk_app_commodity() {
        return pk_app_commodity;
    }

    public void setPk_app_commodity(String pk_app_commodity) {
        this.pk_app_commodity = pk_app_commodity;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getPk_app_billapply_detail() {
        return pk_app_billapply_detail;
    }

    public void setPk_app_billapply_detail(String pk_app_billapply_detail) {
        this.pk_app_billapply_detail = pk_app_billapply_detail;
    }

    public String getPk_app_billapply() {
        return pk_app_billapply;
    }

    public void setPk_app_billapply(String pk_app_billapply) {
        this.pk_app_billapply = pk_app_billapply;
    }

    public String getPk_corp() {
        return pk_corp;
    }

    public void setPk_corp(String pk_corp) {
        this.pk_corp = pk_corp;
    }

    public String getPk_temp_corp() {
        return pk_temp_corp;
    }

    public void setPk_temp_corp(String pk_temp_corp) {
        this.pk_temp_corp = pk_temp_corp;
    }

    public DZFDouble getNnum() {
        return nnum;
    }

    public void setNnum(DZFDouble nnum) {
        this.nnum = nnum;
    }

    public DZFDouble getNmny() {
        return nmny;
    }

    public void setNmny(DZFDouble nmny) {
        this.nmny = nmny;
    }

    public DZFDouble getNtax() {
        return ntax;
    }

    public void setNtax(DZFDouble ntax) {
        this.ntax = ntax;
    }

    public DZFDouble getNtaxmny() {
        return ntaxmny;
    }

    public void setNtaxmny(DZFDouble ntaxmny) {
        this.ntaxmny = ntaxmny;
    }

    public DZFDouble getNzktax() {
        return nzktax;
    }

    public void setNzktax(DZFDouble nzktax) {
        this.nzktax = nzktax;
    }

    public DZFDouble getNzkmny() {
        return nzkmny;
    }

    public void setNzkmny(DZFDouble nzkmny) {
        this.nzkmny = nzkmny;
    }

    public DZFDateTime getTs() {
        return ts;
    }

    public void setTs(DZFDateTime ts) {
        this.ts = ts;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    @Override
    public String getPKFieldName() {
        return PK_FIELD;
    }

    @Override
    public String getParentPKFieldName() {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public DZFDouble getNprice() {
        return nprice;
    }

    public void setNprice(DZFDouble nprice) {
        this.nprice = nprice;
    }


}
