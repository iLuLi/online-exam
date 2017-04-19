package com.deng.controller;

import com.deng.service.SysGenerateService;
import com.deng.utils.PageUtils;
import com.deng.utils.Query;
import com.deng.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        List<Map<String, String>> list = sysGenerateService.queryList(q);
        int total = sysGenerateService.queryTotal(q);
        PageUtils pageUtils = new PageUtils(q.getPage(), q.getLimit(), total, list);

        return R.ok().put("page", pageUtils);
    }

    @RequestMapping(value = "/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        //获取表名，不进行xss过滤
        String tables = request.getParameter("tables");
        tableNames = tables.split(",");

        byte[] data = sysGenerateService.generateCode(tableNames);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
