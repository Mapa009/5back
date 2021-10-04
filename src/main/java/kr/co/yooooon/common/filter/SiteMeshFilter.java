package kr.co.yooooon.common.filter;


import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/WEB-INF/views/decorator/decorator.jsp")				
				.addExcludedPath("/base/codeWindow.html*")
				.addExcludedPath("/base/postWindow.html*")
				.addExcludedPath("/base/agWindow.html*");
 
		builder.addTagRuleBundles(new DivExtractingTagRuleBundle());
	}
}

