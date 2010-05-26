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

package org.vosao.entity

import scala.reflect.BeanProperty
import org.vosao.utils.EntityUtil._
import com.google.appengine.api.datastore.Entity

/**
 * SEO Urls plugin link data.
 * @author Alexander Oleynik
 */
class SeoUrlEntity extends BaseEntity {

	@BeanProperty
	var fromLink: String

	@BeanProperty
	var toLink: String

	override def load(entity: Entity) {
		super.load(entity)
		fromLink = getStringProperty(entity, "fromLink")
		toLink = getStringProperty(entity, "toLink")
	}
	
	override def save(entity: Entity) {
		super.save(entity)
		setProperty(entity, "fromLink", fromLink, true)
		setProperty(entity, "toLink", toLink, false)
	}

	def this(aFrom: String, aTo: String) {
		this()
		fromLink = aFrom
		toLink = aTo
	}
}
