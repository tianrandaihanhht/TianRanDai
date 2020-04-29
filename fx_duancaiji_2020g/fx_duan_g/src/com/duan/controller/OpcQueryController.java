package com.duan.controller;

import com.duan.domain.DCom;
import com.duan.domain.OpcConModel;
import com.duan.service.DComService;
import com.duan.service.OpcConService;
import com.github.pagehelper.PageInfo;
import com.test.annotation.RequirePermission;
import com.test.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("opc")
public class OpcQueryController {

    @Autowired
    private OpcConService opcConService;

    @RequirePermission("opc:list")
    @RequestMapping("opcquery")
    public String listUI() {
        return "duan/opc/list02";
    }
    @RequirePermission("opc:list")
    @RequestMapping("list")
    @ResponseBody
    public Result list(int offset, int limit, String serverIp,String lableId)  throws Throwable{
        PageInfo<OpcConModel> list = this.opcConService.getListByPage(offset,limit,serverIp,lableId);
        return Result.succeed(list);

    }

    @RequirePermission("opc:list")
    @RequestMapping("addData")
    @ResponseBody
    public String addData(String ItemName, String serverIp, String userNameId,String passwordId)  throws Throwable{

        String flag=this.opcConService.addData(ItemName,serverIp,userNameId,passwordId);
        return flag;

    }

    @RequirePermission("opc:delete")
    @RequestMapping("delete/{ids}")
    @ResponseBody
    public Result delete(@PathVariable("ids") String ids) {
        try {

            String[] idsStr = ids.split(",");
             this.opcConService.deleteBatchByItemIds(idsStr);
            return Result.succeed();
        } catch (Exception ex) {
            return Result.fail(500, "！不能删除");
        }
    }






}
