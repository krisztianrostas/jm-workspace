/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.notification.service.service.impl;

import com.liferay.notification.service.service.CustomNotificationSenderLocalService;
import com.liferay.notification.service.service.base.CustomNotificationSenderServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuCategory;
import com.liferay.product.navigation.user.personal.bar.web.internal.constants.ProductNavigationUserPersonalBarPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=foo",
		"json.web.service.context.path=CustomNotificationSender"
	},
	service = AopService.class
)
public class CustomNotificationSenderServiceImpl
	extends CustomNotificationSenderServiceBaseImpl {

	public void sendNotification (String senderName, long targetUserId, String messageBody, String messageText) {
		_customNotificationSenderLocalService.sendNotification(senderName, targetUserId, messageBody, messageText);
	}


	@Reference
	private CustomNotificationSenderLocalService _customNotificationSenderLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
			CustomNotificationSenderServiceImpl.class);

}