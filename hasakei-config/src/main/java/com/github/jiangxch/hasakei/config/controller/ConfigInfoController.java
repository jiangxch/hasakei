package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.api.exception.HsakeiException;
import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.arg.ConfigArg;
import com.github.jiangxch.hasakei.config.arg.base.PageArg;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:42
 */
@RequestMapping("configInfo")
@RestController
public class ConfigInfoController {
    @ApiOperation(value = "分页获取配置信息", tags = "配置中心")
    @PostMapping("listConfigInfoByPage")
    public Result<PageResult<ConfigInfo>> listConfigInfoByPage(@Valid PageArg pageArg) {
        return Result.newSuccess(new PageResult<>());
    }

    @ApiOperation(value = "id获取单个配置详细信息", tags = "配置中心")
    @PostMapping("getConfigDetailById")
    public Result<ConfigInfo> getConfigDetailById(@RequestParam @Min(1) Integer id) {
        return Result.newSuccess(new ConfigInfo());
    }

    @ApiOperation(value = "根据id修改配置文件", tags = "配置中心")
    @PostMapping("updateConfigInfo")
    public Result updateConfigInfo(@Valid ConfigInfo arg) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "根据id删除配置文件", tags = "配置中心")
    @PostMapping("deleteConfigById")
    public Result deleteConfigById(@Min(1) @RequestParam Integer id) {
        return Result.newSuccess();
    }
}
