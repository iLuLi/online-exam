package com.deng.controller;

import com.deng.service.SysGenerateService;
import com.deng.utils.PageUtils;
import com.deng.utils.Query;
import com.deng.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/4/13.
 */
@Controller
@RequestMapping("/generate")
public class SysGenerateController {
    @Autowired
    private SysGenerateService sysGenerateService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public R list(@RequestParam Map<String, Object> params) {

        Query q = new Query(params);

        List<Map<String, Object>> list = sysGenerateService.queryList(q);
        int total = sysGenerateService.queryTotal();
        PageUtils pageUtils = new PageUtils(q.getPage(), q.getLimit(), total, list);

        return R.ok().put("page", pageUtils);
    }

}
