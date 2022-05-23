package com.msal.adb2c.configuration;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.StringUtil;
import com.msal.adb2c.configuration.listner.MsalAdb2cConfigurationListner;

import java.util.Collection;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author AC65339
 */
@Component(
		immediate = true,
		configurationPid = {
			"com.msal.adb2c.configuration.MsalAdb2cConfiguration",
			"com.msal.adb2c.configuration.MsalAdb2cConfiguration.scoped"
		},
		service = MsalAdb2cConfigurationsHelper.class
	)
public class MsalAdb2cConfigurationsHelper {

	public String getB2CSignInSignUpAuthority() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cSignInSignUpAuthority());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CClientId() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cClientId());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CEditProfileAuthority() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cEditProfileAuthority());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CResetPasswordAuthority() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cResetPasswordAuthority());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CSecret() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cSecret());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CCodeChallange() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cCodeChallange());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CCodeChallangeMethod() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cCodeChallangeMethod());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public String getB2CCodeChallangeRedirect() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cCodeChallangeRedirect());
		} else {
			return StringPool.BLANK;
		}
	}


	public String getB2CRedirectUri() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.b2cRedirectUri());
		} else {
			return StringPool.BLANK;
		}
	}
	
	public long getSiteAdminUserID() {
		if (_serviceConfig != null) {
			return _serviceConfig.siteAdminUserID();
		} else {
			return 1L;
		}
	}
	
	public boolean isSaveUser() {
		if (_serviceConfig != null) {
			return _serviceConfig.isSaveUser();
		} else {
			return true;
		}
	}

	public String getDataCacheRefreshCronExpression() {
		if (_serviceConfig != null) {
			return StringUtil.trim(_serviceConfig.dataCacheRefreshCron());
		} else {
			return StringPool.BLANK;
		}
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(ConfigurationProvider configurationProvider) throws ConfigurationException {
		this._configurationProvider = configurationProvider;
	}

	private void initConfig() throws ConfigurationException {
		long companyId = 0;
		Company company;
		try {
			company = companyLocalService.getCompanyByWebId("liferay.com");
			companyId = company.getCompanyId();
			_log.info("MsalAdb2cConfigurationsHelper: initConfig: company: "+company+" companyId: "+companyId);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this._serviceConfig = _configurationProvider.getCompanyConfiguration(MsalAdb2cConfiguration.class,
				companyId);
	}

	@Activate
	private void activate(Map<Object, Object> properties, BundleContext context) throws ConfigurationException {
		_log.info("Initializing MsalConfigurationHelper...");
		initConfig();
	}

	@Modified
	private void modified(BundleContext context) throws ConfigurationException {
		_log.info("Msal Service Configuration Modified.");
		initConfig();
		notifyConfigListeners(context);
	}

	private void notifyConfigListeners(BundleContext context) {

		_log.debug("Notifying configuration change event to listeners");

		try {
			Collection<ServiceReference<MsalAdb2cConfigurationListner>> serviceRefs = context
					.getServiceReferences(MsalAdb2cConfigurationListner.class, null);

			serviceRefs.forEach((serviceRef) -> {

				MsalAdb2cConfigurationListner listener = context.getService(serviceRef);

				if (listener != null) {
					try {
						listener.configUpdated();
					} catch (Exception e) {
						_log.error("Error occured while processing configuration change by : " + listener, e);
					}
				}

			});
		} catch (InvalidSyntaxException e) {
			_log.error("Error occurred while notifying configuration listeners", e);
		}
	}

	/*
	 * Ensuring that the portal is initialized before this component activates.
	 */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _portalInitialized;

	/*
	 * Injecting this component to make sure it activates before config helper.
	 */
	@Reference(unbind = "-")
	private MsalAdb2cBeanDeclaration _configBeanDeclaration;
	
	private static final Log _log = LogFactoryUtil.getLog(MsalAdb2cConfigurationsHelper.class);
	
	private volatile ConfigurationProvider _configurationProvider;

	private MsalAdb2cConfiguration _serviceConfig;
	
	@Reference
	private CompanyLocalService companyLocalService;
	
	

}