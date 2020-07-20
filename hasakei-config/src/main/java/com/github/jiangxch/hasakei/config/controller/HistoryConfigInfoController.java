package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.arg.base.PageArg;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import com.github.jiangxch.hasakei.config.dao.entity.HistoryConfigInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:42
 */
@RequestMapping("historyConfigInfo")
@RestController
public class HistoryConfigInfoController {
    @ApiOperation(value = "分页获取历史配置文件", tags = "历史配置")
    @PostMapping("listHistoryConfigInfoByPage")
    public Result<PageResult<HistoryConfigInfo>> listHistoryConfigInfoByPage(@Valid PageArg pageArg,
                                                                      @Min(1) @RequestParam Integer configInfoId) {
        return Result.newSuccess(new PageResult<>());
    }

    @ApiOperation(value = "根据历史记录id查历史配置文件", tags = "历史配置")
    @PostMapping("getHistoryConfigInfoById")
    public Result<ConfigInfo> getHistoryConfigInfoById(@RequestParam @Min(1) Integer id) {
        return Result.newSuccess(new ConfigInfo());
    }

    @ApiOperation(value = "根据历史文件id还原配置文件", tags = "历史配置")
    @PostMapping("revertConfigInfoByHistoryConfigInfoId")
    public Result revertConfigInfoByHistoryConfigInfoId(@Min(1) Integer historyConfigInfoId) {
        return Result.newSuccess();
    }

}
