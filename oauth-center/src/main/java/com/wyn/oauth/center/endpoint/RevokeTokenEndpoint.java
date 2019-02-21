package com.wyn.oauth.center.endpoint;

import com.wyn.oauth.center.common.entity.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
    public @ResponseBody
    WebResponse revokeToken(String access_token){
        WebResponse msg = new WebResponse();
        if (consumerTokenServices.revokeToken(access_token)){
            msg.setCode(WebResponse.Code.SUCCESS);
            msg.setMsg(WebResponse.Msg.LOGOUT_SUCCESS);
        }else {
            msg.setCode(WebResponse.Code.FAILED);
            msg.setMsg(WebResponse.Msg.LOGOUT_FAILED);
        }
        return msg;
    }
}
