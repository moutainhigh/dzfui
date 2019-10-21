package com.dzf.zxkj.report.enums;

public enum FkTjBgEnum {
	
	FKTJ1("主营业务收入与工资总额弹性系数",
			"(((bqdf(6001)+bqdf(6301)-sqdf(6001)-sqdf(6301))/(sqdf(6001)+sqdf(6301)))>0 && ((bqjf(221101)-sqjf(221101))/sqjf(221101))<0 )?1:0",
			"(((bqdf(5001)+bqdf(5301)-sqdf(5001)-sqdf(5301))/(sqdf(5001)+sqdf(5301)))>0 && ((bqjf(221101)-sqjf(221101))/sqjf(221101))<0 )?1:0",
			"(sqdf(6001)+sqdf(6301))==0 || sqjf(221101)==0 ","(sqdf(5001)+sqdf(5301))==0 || sqjf(221101)==0",
			"正常情况下，主营业务收入变动率与工资总额变动率应该一致，如果不一致，可能存在隐瞒收入或者虚开工资的风险。"),
//	FKTJ2("流转税申报收入与所得税申报收入不一致","","","","",
//			"正常情况下，流转税申报收入与所得税申报收入应该一致，如果不一致，可能存在隐瞒收入、偷税漏税的风险"),
	FKTJ3("销售成本大于销售收入：主营业务成本>主营业务收入","(bqjf(6401)>bqdf(6001))?1:0",
			"(bqjf(5401)>bqdf(5001))?1:0","","",
			"正常情况下，销售成本应该小于销售收入。若销售成本大于销售收入，则存在虚增主营业务成本或者少记收入的风险。"),
	FKTJ4("期间费用占主营业务收入比重：期间费用总额>主营业务收入*30%",
			"((bqjf(6601)+bqjf(6602)+bqjf(6603))>(bqdf(6001)*0.3))?1:0",
			"((bqjf(5601)+bqjf(5602)+bqjf(5603))>(bqdf(5001)*0.3))?1:0","","",
			"企业毛利率一般不会超过 30%（个别企业除外），如果期间费用大于销售收入的 30%，则企业经营根本无利可图，是不能正常运转的。企业期间费用大于 30%一般是由于非正常费用挤入造成的，最常见的一是多列运费，而是多列成品油，三是多列过桥过路费，四是虚构工资津贴补贴等，五十多列其他各项杂费等。其中许多项目既多抵扣进项税费又多列支费用。"),
	FKTJ5("营业外支出占主营业务收入比重：营业外支出/主营业务收入",
			"((bqjf(6711)/bqdf(6001))>0.1)?1:0","((bqjf(5711)/bqdf(5001))>0.1)?1:0",
			"(bqdf(6001) == 0)","(bqdf(5001) ==0)",
			"正常情况下，营业外支出占主营业务收入比重不应该超过 10%。营业外支出金额较大，企业可能存在将应由税后列支的与生产经营无关的各种罚款、滞纳金、违约金、赞助、捐助等列入营业外支出，将固定资产、流动资产净损失、各种减值准备列入营业外支出。"),
	FKTJ6("待摊费用余额占主营业务收入比重：待摊费用/主营业务收入",
			"((bqjf(1801)/bqdf(6001))>0.1)?1:0","((bqjf(1801)/bqdf(5001))>0.1)?1:0",
			"bqdf(6001) == 0","bqdf(5001) == 0",
			"待摊费用主要核算各种个预先支付但不能一次摊入成本费用的支出。主要包括开办费，预付的租金，保险费，修理费等。正常情况下，待摊费用占主营业务收入比例应该低于 10%。如果待摊费用金额过大，则很可能企业将不属于待摊费用的项目混入了待摊费用。如将基建支出、与生产经营无关的摊派，集资款，捐献，以及运费，上级管理费，统筹费，违约金等各种杂费列入待摊项目。因此，待摊费用较大时就有扩大成本费用挤占利润之嫌。应查明每笔待摊费用的真实性质。"),
	FKTJ7("对“销售费用”中“其他”项的占比超过 20%的检查",
			"((bqjf(660121)/bqjf(6601))>0.2)?1:0","(bqjf(560121)/bqjf(5601)>0.2)?1:0",
			"bqjf(6601) ==0 ","bqjf(5601) == 0"
			,"年度汇算清缴时，若填报的“其他”项占比超过销售费用总额的 20%，需要提供给税务机关书面说明材料及明细数据。"),
	FKTJ8("对“管理费用”中“其他”项的占比超过 20%的检查",
			"((bqjf(660220)/bqjf(6602))>0.2)?1:0","((bqjf(560221)/bqjf(5602))>0.2)?1:0",
			"bqjf(6602) == 0","bqjf(5602) == 0"
			,"年度汇算清缴时，若填报的“其他”项占比超过管理费用总额的 20%，需要提供给税务机关书面说明材料及明细数据。"),
	FKTJ9("对“财务费用”中“其他”项的占比超过 20%的检查",
			"(((bqjf(660304)/bqjf(6603)))>0.2)?1:0","((bqjf(560304)/bqjf(5603))>0.2)?1:0",
			"bqjf(6603) == 0","bqjf(5603) == 0",
			"年度汇算清缴时，若填报的“其他”项占比超过财务费用总额的 20%，需要提供给税务机关书面说明材料及明细数据。"),
	FKTJ10("其他应付款期末余额>销售收入",
			"(qmye(2241)>bqdf(600101))?1:0",
			"(qmye(2241)>bqdf(500101))?1:0",
			"","",
			"其他应付款是核算不涉及货物交易的应收应付款项。按正常说其应是不涉税科目，但有的企业就是钻空子，将有货物交易的本应计入应收账款和应付账款的款项专门混入其他应付科目，这样就要求将其他应付款比照应付款项的要求进行异常监控，当然，也可能是股权交易，资金拆借，分红分息，返利等形成。"),
	FKTJ11("其他应付款期末余额<0","(qmye(2241)<0)?1:0","(qmye(2241)<0)?1:0",
			"","",
			"其他应付款负数反映的是没有其他应付款的款项，而有收回的款项，正常情况下余额应该大于 0，如果余额小于 0，这种情况可能存在企业将收回的款项隐瞒在该科目逃避纳税的行为。"),
	FKTJ12("其他应收款大于收入","(bqjf(1221)>bqdf(6001))?1:0",
			"(bqjf(1221)>bqdf(6001))?1:0", 
			"","",
			"其他应收款是核算不涉及货物交易的应收款项。按正常说其应是不涉税科目，但有的企业就是转空子，将有货物交易的本应计入应收账款的款项专门混入其他应收科目，这样就要求将其他应收款项比照应收款项的要求进行异常监控，当然，也可能是股权交易，资金拆借，分红分息，返利等形成"),
	FKTJ13("其他应收款为负数",
			"(qmye(1221)<0)?1:0","(qmye(1221)<0)?1:0",
			"","",
			"其他应收款为负数是实际收回的款项大于应收的款项，即多收回了款项，正常情况下是不应该出现的。这种情况一般是企业将收回的款项记在其他应收款以隐瞒收入造成的。另外，导致应收账款、其他应收款为负数的还可能是企业乱用错用科目，隐瞒其他事项造成"),
	FKTJ14("应付账款大于销售收入",
			"(qmye(2202)>bqdf(6001))?1:0",
			"(qmye(2202)>bqdf(5001))?1:0",
			"","",
			"应付账款是核算企业购进货物应付未付的货款。应付账款大于销售收入说明企业销售的货物购进时都没有（绝大部分没有）付款，购货长期不用付款使交易的虚假成分增大，虚开的风险也很大。"),
	FKTJ15("应付账款为负数",
			"(qmye(2202)<0)?1:0","(qmye(2202)<0)?1:0",
			"","",
			"应付账款负数反映的是没有应付的款项，而有收回的款项。这种情况这种情况可能存在企业将收回的款项隐瞒在该科目逃避纳税的行为"),
	FKTJ16("应收账款大于销售收入",
			"(qmye(1122)>bqdf(6001))?1:0",
			"(qmye(1122)>bqdf(5001))?1:0",
			"","",
			"应收账款是核算企业销售后未收回的货项，出现应收账款大于销售收入，说明除本期销售没有收回货款外，上期销售也有大量没收回的货款，这就是只销售货物不收回货款（或基本不收回货款）的情况。一个没有经营性现金流的交易行为我们有理由怀疑其交易的真实性"),
	FKTJ17("应收账款为负数",
			"(qmye(1122)<0)?1:0","(qmye(1122)<0)?1:0",
			"","",
			"应收账款为负数是实际收回的款项大于应收的款项，即多收回了款项，正常情况下是不应该出现的。这种情况一般是企业将收回的款项记在应收账款以隐瞒收入造成的。另外，导致应收账款、其他应收款为负数的还可能是企业乱用错用科目，隐瞒其他事项造成"),
	FKTJ18("预付账款为负数",
			"(qmye(1123)<0)?1:0","(qmye(1123)<0)?1:0",
			"","",
			"预付账款负数反映的是没有预付的货款而有收回的货款，这种情况可能存在企业将收回的货款隐瞒在该科目逃避纳税的行为"),
	FKTJ19("预收账款占销售收入比重：预收账款余额>全部收入*20%",
			"(qmye(2203)>(bqdf(6001)*0.2))?1:0","(qmye(2203)>(bqdf(5001)*0.2))?1:0",
			"","",
			"可能存在未及时确认销售收入行为"),
	FKTJ20("存货为负数：期末存货<0",
			"((qmye(1405)+qmye(1403))<0)?1:0","((qmye(1405)+qmye(1403))<0)?1:0",
			"","",
			"1、多转成本；2、货物未估价入库已销售"),
	FKTJ21("存货大于销售收入的 30%：（存货-销售收入）/销售收入",
			"(((bnjf(1405)+bnjf(1403)-bndf(6001))/bndf(6001))>0.3)?1:0",
			"(((bnjf(1405)+bnjf(1403)-bndf(5001))/bndf(5001))>0.3)?1:0",
			"bndf(6001) ==0 ","bndf(5001) ==0",
			"商业企业存货是库存商品，工业企业存货是原材料、在产品、产成品等。按照存货周转率的要求，企业的存货一年至少要转 5 圈以上，即存货为销售成本的 20%。在企业存货周转率考核中，低于 3 圈的为不达标，即存货为销售成本的 33%以上为不达标，反映为存货太大。另外，从实际运营情况看，存货达到销售收入 30%，则意味着存货够 3.6 个月销售，已经超出企业一般备货 2-3 个月储量的要求。企业超大库存往往是货物已经销售，但不及销售，不转成本，不申报纳税而长期趴在库存中隐藏使账面库存与实际库存不符，形成库存虚数。有的企业库存商品明细账记载某商品进货后几年都不动，实际早已无货，且数额巨大，动辄几百万，几千万，甚至上亿元库存，税收疑点明显。"),
	FKTJ22("存货与留抵税金不匹配（针对一般纳税人企业）",
			"(((qmye(222109)))>(bqjf(1405)+bqjf(1403))*0.17)?1:0",
			"(((qmye(222109)))>(bqjf(1405)+bqjf(1403))*0.17)?1:0",
			"","",
			"企业留抵税金是反应存货中所含的税金，其最大数额不应大于存货*17%的原因。造成其原因一是因为企业销售价格低于进货成本，即成本大于收入。而是非成本因素的税金抵扣过大，如固定资产、运费、成品油、农产品等。三是进项票取得不及时，即估价入库的货物已销售，但进项发票滞后取得。以上无论哪种情况，税收上都存在明显疑点。");
	
	FkTjBgEnum(String zbmc, String gs07, String gs13, String unknown07, String unknown13, String fxjg){
		this.zbmc = zbmc;
		this.gs07 = gs07;
		this.gs13 = gs13;
		this.unknown07 = unknown07;
		this.unknown13 = unknown13;
		this.fxjg = fxjg;
	}
	
	private String zbmc;//指标名称
	private String gs07;//公式
	private String gs13;//13公式
	private String fxjg;//分析结果
	private String unknown07;//未知条件
	private String unknown13;//未知条件
	
	public String getUnknown07() {
		return unknown07;
	}
	public void setUnknown07(String unknown07) {
		this.unknown07 = unknown07;
	}
	public String getUnknown13() {
		return unknown13;
	}
	public void setUnknown13(String unknown13) {
		this.unknown13 = unknown13;
	}
	public String getGs07() {
		return gs07;
	}
	public void setGs07(String gs07) {
		this.gs07 = gs07;
	}
	public String getGs13() {
		return gs13;
	}
	public void setGs13(String gs13) {
		this.gs13 = gs13;
	}
	public String getZbmc() {
		return zbmc;
	}
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	public String getFxjg() {
		return fxjg;
	}
	public void setFxjg(String fxjg) {
		this.fxjg = fxjg;
	}
	
}