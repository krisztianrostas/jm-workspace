/**
 * 
 */
package com.msal.adb2c.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author AC65339
 *
 */

@Meta.OCD(
		id = "com.msal.adb2c.configuration.MsalAdb2cConfiguration",
		localization = "content/Language",
		name = "com-msal-service-configuration-name"
	)
	@ExtendedObjectClassDefinition(
		category = "b2c-configuration",
		scope = ExtendedObjectClassDefinition.Scope.COMPANY
	)
public interface MsalAdb2cConfiguration {
	
	@Meta.AD(
			required = false,
			name = "b2c-sign-in-sign-up-authority"
		)
		public String b2cSignInSignUpAuthority();
	@Meta.AD(
			required = false,
			name = "b2c-edit-profile-authority"
		)
		public String b2cEditProfileAuthority();
	@Meta.AD(
			required = false,
			name = "b2c-reset-password-authority"
		)
		public String b2cResetPasswordAuthority();

		@Meta.AD(
			required = false,
			name = "b2c-redirectUri"
		)
		public String b2cRedirectUri();

		@Meta.AD(
			required = false,
			name = "b2c-clientId"
		)
		public String b2cClientId();

		@Meta.AD(
				required = false,
				name = "b2c-secret"
				)
		public String b2cSecret();
		
		@Meta.AD(
			required = false,
			name = "b2c-code-challange"
		)
		public String b2cCodeChallange();
		
		
		@Meta.AD(
				required = false,
				name = "b2c-code-challange-method"
			)
		public String b2cCodeChallangeMethod();
		
		@Meta.AD(
				required = false,
				name = "b2c-code-verfier"
			)
		public String b2cCodeChallangeRedirect();
		
		@Meta.AD(
				required = false,
				name = "save-users"
			)
		public boolean isSaveUser();
		
		@Meta.AD(
				required = false,
				name = "site-admin-userID"
			)
		public long siteAdminUserID();

		@Meta.AD(
			required = false,
			name = "data-cache-refresh-cron",
			deflt = "0 0 0 * * ?" // everyday at midnight
		)
		public String dataCacheRefreshCron();

}
