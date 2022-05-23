/**
 * 
 */
package com.msal.adb2c.configuration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

/**
 * @author AC65339
 *
 */

@Component(
		immediate = true,
		service = {
			ConfigurationBeanDeclaration.class,
			MsalAdb2cBeanDeclaration.class
		}
	)
public class MsalAdb2cBeanDeclaration implements ConfigurationBeanDeclaration{
	
	@Override
	public Class<?> getConfigurationBeanClass() {
		return MsalAdb2cConfiguration.class;
	}
}
