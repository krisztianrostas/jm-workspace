package com.liferay.notification.service.service.handler;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.product.navigation.user.personal.bar.web.internal.constants.ProductNavigationUserPersonalBarPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(service = UserNotificationHandler.class)
public class SendNotificationUserHandler extends BaseUserNotificationHandler {
    public static String PORTLET_ID = ProductNavigationUserPersonalBarPortletKeys.PRODUCT_NAVIGATION_USER_PERSONAL_BAR;

    public SendNotificationUserHandler() {
        setPortletId(PORTLET_ID);
    }

    @Override
    protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
            throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
        String notificationText = jsonObject.getString("notificationBody");
        String title = jsonObject.getString("notificationText");
        String senderName = jsonObject.getString("senderName");

        String body = StringUtil.replace(getBodyTemplate(),
                new String[]{"[$TITLE$]", "[$SENDER_NAME$]", "[$NOTIFICATION_TEXT$]"},
                new String[]{title, senderName, notificationText});

        return body;
    }

    protected String getBodyTemplate() throws Exception {
        StringBuilder htmlResponse = new StringBuilder(5);
        htmlResponse.append("<div class=\"title\"><h6>[$TITLE$] (<i>[$SENDER_NAME$]</i>)</h6><div><div ");
        htmlResponse.append("class=\"body\"><br><p>[$NOTIFICATION_TEXT$]</p></div>");
        return htmlResponse.toString();
    }

}