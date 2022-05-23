package com.liferay.struts.override;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.msal.adb2c.configuration.MsalAdb2cConfigurationsHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "path=/portal/extend_session",
                "service.ranking:Integer=100"
        },
        service = StrutsAction.class
)
public class StrutsActionOverrideExtendSession implements StrutsAction {

    @Override
    public String execute(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws Exception {

        _log.error("Extend session link activated");

        _log.error("System config value via configurationhelper: "  +
            _msalAdb2cConfigurationsHelper.getB2CRedirectUri());

        // your code goes here

        // call original struct action (jsp render) as per
        // https://github.com/liferay/liferay-portal/blob/7.3.x/portal-web/docroot/WEB-INF/struts-config.xml#L37
        return STRUTS_FWD_PATH;

    }

    @Reference
    MsalAdb2cConfigurationsHelper _msalAdb2cConfigurationsHelper;
    private static final String STRUTS_FWD_PATH = "/portal/extend_session.jsp";
    private static final Log _log = LogFactoryUtil.getLog(
            StrutsActionOverrideExtendSession.class);
}
