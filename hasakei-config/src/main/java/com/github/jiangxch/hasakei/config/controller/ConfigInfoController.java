package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.arg.CreateConfigInfoArg;
import com.github.jiangxch.hasakei.config.arg.ListConfigInfoByPageArg;
import com.github.jiangxch.hasakei.config.arg.UpdateConfigInfoArg;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import com.github.jiangxch.hasakei.config.manager.AuthInfo;
import com.github.jiangxch.hasakei.config.manager.RequestContextHolder;
import com.github.jiangxch.hasakei.config.service.ConfigInfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:42
 */
@RequestMapping("configInfo")
@RestController
public class ConfigInfoController {
    @Autowired
    private ConfigInfoServiceImpl configInfoService;

    @ApiOperation(value = "分页获取配置信息", tags = "配置中心")
    @PostMapping("listConfigInfoByPage")
    public Result<PageResult<ConfigInfo>> listConfigInfoByPage(@RequestBody @Valid ListConfigInfoByPageArg arg) {
        return configInfoService.listConfigInfoByPage(arg);
    }

    @ApiOperation(value = "id获取单个配置详细信息", tags = "配置中心")
    @PostMapping("getConfigInfoById")
    public Result<ConfigInfo> getConfigInfoById(@RequestParam @Min(1) Long id) {
        return configInfoService.getConfigInfoById(id);
    }

    @ApiOperation(value = "根据id修改配置文件", tags = "配置中心")
    @PostMapping("updateConfigInfo")
    public Result updateConfigInfo(@RequestBody @Valid UpdateConfigInfoArg arg) {
        return configInfoService.updateConfigInfo(arg);
    }

    @ApiOperation(value = "根据id删除配置文件", tags = "配置中心")
    @PostMapping("deleteConfigById")
    public Result deleteConfigById(@Min(1) @RequestParam Integer id) {
        return configInfoService.deleteConfigById(id);
    }


    @ApiOperation(value = "创建配置文件", tags = "配置中心")
    @PostMapping("createConfigInfo")
    public Result createConfigInfo(@Valid @RequestBody CreateConfigInfoArg arg) {
        AuthInfo authInfo = RequestContextHolder.getContext();
        return configInfoService.createConfigInfo(arg,authInfo);
    }
}
