package com.github.jiangxch.hasakei.config.service;
import java.time.LocalDateTime;
import java.util.Date;

import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.common.util.MD5Util;
import com.github.jiangxch.hasakei.config.arg.CreateConfigInfoArg;
import com.github.jiangxch.hasakei.config.arg.ListConfigInfoByPageArg;
import com.github.jiangxch.hasakei.config.arg.UpdateConfigInfoArg;
import com.github.jiangxch.hasakei.config.dao.ConfigInfoMapper;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import com.github.jiangxch.hasakei.config.enums.Environment;
import com.github.jiangxch.hasakei.config.manager.AuthInfo;
import com.github.jiangxch.hasakei.config.manager.RequestContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
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

    public Result<ConfigInfo> getConfigInfoById(Long configInfoId) {
        ConfigInfo config = configInfoMapper.findById(configInfoId);
        return Result.newSuccess(config);
    }

    public Result updateConfigInfo(UpdateConfigInfoArg arg) {
        ConfigInfo oldConfig = getConfigInfoById(arg.getId()).getData();
        String md5 = null;
        try {
            md5 = MD5Util.md5Hex(arg.getContent().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigName(arg.getConfigName());
        configInfo.setId(arg.getId());
        configInfo.setEnvironment(Environment.getByType(arg.getEnvironment()).getName());
        configInfo.setContent(arg.getContent());
        configInfo.setMd5(md5);
        configInfo.setConfigDesc(arg.getConfigDesc());
        configInfo.setUsername(RequestContextHolder.getContext().getUsername());
        configInfo.setGmtCreate(configInfo.getGmtCreate());
        configInfo.setGmtModified(LocalDateTime.now());
        configInfoMapper.updateConfigInfoById(configInfo);
        return Result.newSuccess();
    }

    public Result deleteConfigById(Integer id) {
        configInfoMapper.deleteById(id);
        return Result.newSuccess();
    }

    public Result createConfigInfo(CreateConfigInfoArg arg, AuthInfo authInfo) {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigName(arg.getConfigName());
        configInfo.setEnvironment(Environment.getByType(arg.getEnvironment()).getName());
        configInfo.setContent(arg.getContent());
        String md5 = null;
        try {
            md5 = MD5Util.md5Hex(arg.getContent().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        configInfo.setMd5(md5);
        configInfo.setConfigDesc(arg.getConfigDesc());
        configInfo.setUsername(authInfo.getUsername());
        configInfo.setGmtModified(LocalDateTime.now());
        configInfo.setGmtCreate(LocalDateTime.now());
        configInfoMapper.create(configInfo);
        return Result.newSuccess();
    }
}
