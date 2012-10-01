package com.cognifide.maven.plugins.goals;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import com.cognifide.maven.plugins.AbstractVaultMojo;

@Mojo(name = "checkout")
public class Checkout extends AbstractVaultMojo {

	@Parameter(property = "vltco.filter", defaultValue = "src/main/cq/META-INF/vault/filter.xml")
	protected String filter;

	public void execute() throws MojoExecutionException {
		if (FileUtils.fileExists(filter)) {
			executeVaultCommand("checkout", "-f", filter, url, "/", srcdir);
		} else {
			executeVaultCommand("checkout", url, "/", srcdir);
		}
	}
}