package com.cognifide.maven.plugins.vltco.plugins;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.cognifide.maven.plugins.vltco.vault.VaultApp;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractVaultMojo extends AbstractMojo {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Parameter(property = "vltco.destdir", defaultValue = "src/main/cq/jcr_root/")
	protected String destdir;

	@Parameter(property = "vltco.url", defaultValue = "http://localhost:4502", required = true)
	protected String url;

	@Parameter(property = "vltco.user", defaultValue = "admin")
	protected String user;

	@Parameter(property = "vltco.password", defaultValue = "admin")
	protected String password;

	protected void executeVaultCommand(String command, String... additionalParameters) {
		List<String> parameters = new LinkedList<String>();
		parameters.addAll(
				Arrays.asList(new String[] { "--credentials", user + ":" + password, command, "--force" }));
		Collections.addAll(parameters, additionalParameters);
		log.info("Invoking: vlt {}", parameters);
		new VaultApp().runApp(parameters.toArray(ArrayUtils.EMPTY_STRING_ARRAY));
	}

}