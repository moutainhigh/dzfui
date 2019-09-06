package com.dzf.zxkj.platform.services.am.zcgl.impl;

import com.dzf.zxkj.common.exception.DZFWarpException;
import com.dzf.zxkj.platform.model.am.zcgl.AssetDepTemplate;
import com.dzf.zxkj.platform.model.am.zcgl.BdAssetCategoryVO;
import com.dzf.zxkj.platform.services.am.zcgl.IAssetDepTemplateService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Auther: dandelion
 * @Date: 2019-09-05
 * @Description:
 */
@Service
public class AssetDepTemplateServiceImpl implements IAssetDepTemplateService {
    @Override
    public AssetDepTemplate[] getAssetDepTemplate(String pk_corp, Integer tempkind, BdAssetCategoryVO categoryVO, HashMap<String, AssetDepTemplate[]> depTemplateMap) throws DZFWarpException {
        return new AssetDepTemplate[0];
    }
}
