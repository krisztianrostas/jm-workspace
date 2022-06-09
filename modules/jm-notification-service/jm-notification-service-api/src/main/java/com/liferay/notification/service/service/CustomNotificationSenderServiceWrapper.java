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

package com.liferay.notification.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CustomNotificationSenderService}.
 *
 * @author Brian Wing Shun Chan
 * @see CustomNotificationSenderService
 * @generated
 */
public class CustomNotificationSenderServiceWrapper
	implements CustomNotificationSenderService,
			   ServiceWrapper<CustomNotificationSenderService> {

	public CustomNotificationSenderServiceWrapper(
		CustomNotificationSenderService customNotificationSenderService) {

		_customNotificationSenderService = customNotificationSenderService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _customNotificationSenderService.getOSGiServiceIdentifier();
	}

	@Override
	public void sendNotification(
		String senderName, long targetUserId, String messageBody,
		String messageText) {

		_customNotificationSenderService.sendNotification(
			senderName, targetUserId, messageBody, messageText);
	}

	@Override
	public CustomNotificationSenderService getWrappedService() {
		return _customNotificationSenderService;
	}

	@Override
	public void setWrappedService(
		CustomNotificationSenderService customNotificationSenderService) {

		_customNotificationSenderService = customNotificationSenderService;
	}

	private CustomNotificationSenderService _customNotificationSenderService;

}