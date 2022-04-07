package com.liferay.test;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpSession;

@Component(
        immediate = true,
        property = {
                "key=login.events.post",
                "service.ranking:Integer=1000"
        },
        service = LifecycleAction.class
)
public class PostLoginEvent implements LifecycleAction {

        @Override
        public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {

                // do your business logic here if needed

                // setting up redirect after successful user login

                HttpSession ses = lifecycleEvent.getRequest().getSession();
                LastPath lastPath = new LastPath( REDIRECT_CONTEXT_PATH, "");
                ses.setAttribute(WebKeys.LAST_PATH, lastPath);

        }

        private static final String REDIRECT_CONTEXT_PATH = "/group/guest/private-page";

}
