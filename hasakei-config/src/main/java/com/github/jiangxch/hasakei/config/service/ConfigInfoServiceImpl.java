package com.github.jiangxch.hasakei.config.service;

import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.arg.ListConfigInfoByPageArg;
import com.github.jiangxch.hasakei.config.arg.UpdateConfigInfoArg;
import com.github.jiangxch.hasakei.config.arg.base.PageArg;
import com.github.jiangxch.hasakei.config.dao.ConfigInfoMapper;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiangxch
 * @date: 2020/7/20 下午8:44
 */
@Service
public class ConfigInfoServiceImpl {
    @Autowired
    private ConfigInfoMapper configInfoMapper;


    public Result<PageResult<ConfigInfo>> listConfigInfoByPage(ListConfigInfoByPageArg arg) {
        List<ConfigInfo> datas = configInfoMapper.listConfigInfoByPage(arg);
        int count = configInfoMapper.countAll();
        PageResult<ConfigInfo> result = new PageResult<>();
        result.setPageNumber(arg.getPageNo());
        result.setPageSize(arg.getPageSize());
        result.setTotalCount(count);
        result.setDataList(datas);
        return Result.newSuccess(result);
    }

    public Result<ConfigInfo> getConfigInfoById(Integer configInfoId) {
        ConfigInfo config = configInfoMapper.findById(configInfoId);
        return Result.newSuccess(config);
    }

    public Result updateConfigInfo(UpdateConfigInfoArg arg) {
        return null;
    }

    public Result deleteConfigById(Integer id) {
        configInfoMapper.deleteById(id);
        return Result.newSuccess();
    }
}
