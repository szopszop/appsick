package org.eu.appsick.user;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getGivenName() {
        return oauth2User.getAttribute("given_name");
    }

    public String getFamilyName() {
        return oauth2User.getAttribute("family_name");
    }

    public String getPicture() {
        return oauth2User.getAttribute("picture");
    }

    public String getEmail() {
        return oauth2User.getAttribute("email");
    }


}