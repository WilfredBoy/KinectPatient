package com.qg.kinectpatient;

import com.qg.kinectpatient.http.HttpProcess;
import com.qg.kinectpatient.model.PUser;
import com.qg.kinectpatient.param.UpdatePUserParam;
import com.qg.kinectpatient.result.UpdatePUserResult;
import com.qg.kinectpatient.util.JsonUtil;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by TZH on 2016/10/31.
 */

public class HttpTest {
    @Test
    public void http_Test() {
        PUser user = new PUser();
        user.setId(1);
        user.setSex(1);
        user.setAge(100);
        user.setName("哈哈哈");
        user.setBirth(new Date());
        UpdatePUserParam pUserParam = new UpdatePUserParam(user);
        UpdatePUserResult result = HttpProcess.sendHttp(pUserParam, UpdatePUserResult.class);
        System.out.println(JsonUtil.toJson(result));
        assertNotNull(result);
    }
}
