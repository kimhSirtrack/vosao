/**
 * Vosao CMS. Simple CMS for Google App Engine.
 * Copyright (C) 2009 Vosao development team
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * email: vosao.dev@gmail.com
 */

package org.vosao.business.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.vosao.business.ConfigBusiness;
import org.vosao.business.PageBusiness;
import org.vosao.dao.Dao;
import org.vosao.entity.ContentEntity;
import org.vosao.entity.PageEntity;
import org.vosao.global.SystemService;
import org.vosao.utils.DateUtil;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;

public class SimplePageRenderDecorator implements PageRenderDecorator {

	private Log logger = LogFactory.getLog(SimplePageRenderDecorator.class);
	
	private PageEntity page;
	private String languageCode;
	private String content;
	private Dao dao;
	private PageBusiness pageBusiness;
	private SystemService systemService;
	
	public SimplePageRenderDecorator(PageEntity page,	String languageCode, 
			Dao dao,
			PageBusiness pageBusiness, 
			SystemService systemService) {
		super();
		this.page = page;
		this.languageCode = languageCode;
		this.dao = dao;
		this.pageBusiness = pageBusiness;
		this.systemService = systemService;
		pluginContentRender();
	}

	public PageEntity getPage() {
		return page;
	}
	
	public void setPage(PageEntity page) {
		this.page = page;
	}
	
	public String getId() {
		return page.getId();
	}

	public String getContent() {
		return content;
	}
	
	public String getComments() {
		if (isCommentsEnabled()) {
			String commentsTemplate = getDao().getConfigDao().getConfig()
				.getCommentsTemplate();
			if (StringUtil.isEmpty(commentsTemplate)) {
				logger.error("comments template is empty");
				return "comments template is empty";
			}
			VelocityContext context = getPageBusiness().createContext(
					languageCode);
			context.put("page", page);
			return getSystemService().render(commentsTemplate, context);
		}
		return "";
	}
	
	private void pluginContentRender() {
		ContentEntity contentEntity = pageBusiness.getPageContent(
				page, languageCode);
		content = getSystemService().render(contentEntity.getContent(), 
				getPageBusiness().createContext(languageCode));
	}

	public String getTitle() {
		return page.getTitle();
	}

	public String getFriendlyUrl() {
		return page.getFriendlyURL();
	}

	public String getParentUrl() {
		return page.getParentUrl();
	}
	
	public String getTemplate() {
		return page.getTemplate();
	}
	
	public Date getPublishDate() {
		return page.getPublishDate();
	}
	
	public String getPublishDateString() {
		return DateUtil.toString(page.getPublishDate());
	}

	public boolean isCommentsEnabled() {
		return page.isCommentsEnabled();
	}

	private Dao getDao() {
		return dao;
	}

	private PageBusiness getPageBusiness() {
		return pageBusiness;
	}

	private SystemService getSystemService() {
		return systemService;
	}
	
	public Integer getVersion() {
		return page.getVersion();
	}

	public String getVersionTitle() {
		return page.getVersionTitle();
	}

	public String getState() {
		return page.getState().name();
	}

	public String getCreateUserEmail() {
		return page.getCreateUserEmail();
	}
	
	public String getCreateDate() {
		return DateUtil.dateTimeToString(page.getCreateDate());
	}

	public String getModUserEmail() {
		return page.getModUserEmail();
	}
	
	public String getModDate() {
		return DateUtil.dateTimeToString(page.getModDate());
	}
	
}
