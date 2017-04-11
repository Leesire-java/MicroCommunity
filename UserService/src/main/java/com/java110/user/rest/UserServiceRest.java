package com.java110.user.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java110.common.log.LoggerEngine;
import com.java110.common.util.ProtocolUtil;
import com.java110.core.base.controller.BaseController;
import com.java110.entity.user.Cust;
import com.java110.feign.user.IUserService;
import com.java110.user.smo.IUserServiceSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务提供类
 * Created by wuxw on 2017/4/5.
 */
@RestController
public class UserServiceRest extends BaseController implements IUserService {

    @Autowired
    IUserServiceSMO iUserServiceSMO;

    /**
     * 通过User对象中数据查询用户信息
     * 如,用户ID，名称
     * @param data
     * @return
     */
    @RequestMapping("/userService/queryUserInfo")
    public String queryUserInfo(@RequestParam("data") String data){
        LoggerEngine.debug("queryUserInfo入参：" + data);


        String resultUserInfo = null;

        JSONObject reqUserJSON = null;
        try {
            reqUserJSON = this.simpleValidateJSON(data);
            Cust oldCust = new Cust();
            oldCust.setCustId(reqUserJSON.getString("custId"));
            resultUserInfo = iUserServiceSMO.queryCust(oldCust);

        } catch (Exception e) {
            LoggerEngine.error("服务处理出现异常：", e);
            resultUserInfo = ProtocolUtil.createResultMsg(ProtocolUtil.RETURN_MSG_ERROR,"服务处理出现异常"+e,null);
        } finally {
            LoggerEngine.debug("用户服务操作客户出参：" + resultUserInfo);
            return resultUserInfo;
        }
    }


    /**
     * 用户服务信息受理
     * 协议：
     * {
     *     'boCust':[{}],
     *     'boCustAttr':[{}]
     * }
     * @param data
     * @return
     */
    @RequestMapping("/userService/soUserService")
    public String soUserService(@RequestParam("data") String data){

        LoggerEngine.debug("soUserService入参：" + data);

        String resultUserInfo = null;

        JSONObject reqUserJSON = null;
        try {
            reqUserJSON = this.simpleValidateJSON(data);
            //1.0规则校验，报文是否合法


            //2.0 受理客户信息
            resultUserInfo = iUserServiceSMO.soUserService(reqUserJSON);


        } catch (Exception e) {
            LoggerEngine.error("服务处理出现异常：", e);
            resultUserInfo = ProtocolUtil.createResultMsg(ProtocolUtil.RETURN_MSG_ERROR,"服务处理出现异常"+e,null);
        } finally {
            LoggerEngine.debug("用户服务操作客户出参：" + resultUserInfo);
            return resultUserInfo;
        }
    }


    /**
     * 客户信息处理
     * 协议：
     *{
     *     boCust:[{},{}]
     * }
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/userService/soBoCust")
    public String soBoCust(@RequestParam("data") String data ) {
        LoggerEngine.debug("soBoCust入参：" + data);

        String resultUserInfo = null;

        JSONObject reqUserJSON = null;
        try {
            reqUserJSON = this.simpleValidateJSON(data);
            resultUserInfo = iUserServiceSMO.soBoCust(data);
        }catch (Exception e){
            LoggerEngine.error("服务处理出现异常：", e);
            resultUserInfo = ProtocolUtil.createResultMsg(ProtocolUtil.RETURN_MSG_ERROR,"服务处理出现异常:"+e,null);
        } finally {
            LoggerEngine.debug("用户服务操作客户出参：" + resultUserInfo);
            return resultUserInfo;
        }
    }

    /**
     * 客户信息属性处理
     * 协议：
     *{
     *     boCustAttr:[{},{}]
     * }
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/userService/soBoCustAttr")
    public String soBoCustAttr(@RequestParam("data") String data) {
        LoggerEngine.debug("soBoCustAttr入参：" + data);

        String resultUserInfo = null;

        JSONObject reqUserJSON = null;
        try {
            reqUserJSON = this.simpleValidateJSON(data);
            resultUserInfo = iUserServiceSMO.soBoCustAttr(data);
        }catch (Exception e){
            LoggerEngine.error("服务处理出现异常：", e);
            resultUserInfo = ProtocolUtil.createResultMsg(ProtocolUtil.RETURN_MSG_ERROR,"服务处理出现异常"+e,null);
        } finally {
            LoggerEngine.debug("用户服务操作客户出参：" + resultUserInfo);
            return resultUserInfo;
        }
    }


    public IUserServiceSMO getiUserServiceSMO() {
        return iUserServiceSMO;
    }

    public void setiUserServiceSMO(IUserServiceSMO iUserServiceSMO) {
        this.iUserServiceSMO = iUserServiceSMO;
    }
}