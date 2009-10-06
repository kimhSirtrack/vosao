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

package org.vosao.business;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.vosao.business.decorators.TreeItemDecorator;
import org.vosao.entity.PageEntity;


public interface PageBusiness {

	ConfigBusiness getConfigBusiness();
	void setConfigBusiness(ConfigBusiness bean);
	
	TreeItemDecorator<PageEntity> getTree(final List<PageEntity> pages);
	
	TreeItemDecorator<PageEntity> getTree();

	/**
	 * Render page with page bound template. With applied postProcessing and 
	 * using PageRenderDecorator.
	 * @param page - page to render
	 * @return rendered html.
	 */
	String render(final PageEntity page);
	
	/**
	 * Render velocity template in specified context. 
	 * @param template - template to render.
	 * @param content - context to use.
	 * @return rendered html.
	 */
	String render(final String template, final VelocityContext context);

	VelocityContext createContext();
	
	List<String> validateBeforeUpdate(final PageEntity page);
}
