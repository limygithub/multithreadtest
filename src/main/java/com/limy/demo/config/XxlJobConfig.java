/*
 * Licensed to CMIM,Inc. under the terms of the CMIM
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *  ---------------------------------------------------------------------------
 * Date            Author    Version        Comments
 * 2019-7-11     ww        1.0          Initial Version
 *
 */

package com.limy.demo.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class XxlJobConfig {
	@Value("${xxl.job.admin.addresses}")
	private String adminAddresses;

	@Value("${xxl.job.executor.appname}")
	private String appName;

	@Value("${xxl.job.executor.ip}")
	private String ip;

	@Value("${xxl.job.executor.port}")
	private int port;

	@Value("${xxl.job.accessToken}")
	private String accessToken;

	@Value("${xxl.job.executor.logpath}")
	private String logPath;

	@Value("${xxl.job.executor.logretentiondays}")
	private int logRetentionDays;


	@Bean(initMethod = "start", destroyMethod = "destroy")
	public XxlJobSpringExecutor xxlJobExecutor() {
		log.info(">>>>>>>>>>> xxl-job config init.");
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
		xxlJobSpringExecutor.setAppName(appName);
		xxlJobSpringExecutor.setIp(ip);
		xxlJobSpringExecutor.setPort(port);
		xxlJobSpringExecutor.setAccessToken(accessToken);
		xxlJobSpringExecutor.setLogPath(logPath);
		xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

		return xxlJobSpringExecutor;
	}

}
