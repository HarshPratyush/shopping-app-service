package com.shopping.webservice.security.oauth2;


import com.shopping.webservice.enums.AuthenticationProvider;
import com.shopping.webservice.exception.Oauth2TokenException;

import java.text.MessageFormat;
import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthenticationProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        throw new Oauth2TokenException(MessageFormat.format(
                    "Sorry! Login with {0}  is not supported yet.", registrationId ));

    }
}
