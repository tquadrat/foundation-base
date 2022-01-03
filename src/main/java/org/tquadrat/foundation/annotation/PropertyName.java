/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 *  <p>{@summary Associates a property name to a method.}</p>
 *  <p>For a JavaBean, the name of the respective property is usually derived
 *  from the name of the method; so for the method {@code getValue()}, the
 *  name of the property is &quot;{@code value}&quot;, and for
 *  {@code setString()}, it would be &quot;{@code string}&quot;.</p>
 *  <p>But sometimes this is not wanted or not possible.</p>
 *  <p>In these cases, the accessor and/or mutator methods for that property
 *  can be annotated with this annotation to get an arbitrary name.</p>
 *
 *  @note   The annotation is used by several annotation processors, but this
 *      module itself does not provide any means to interpret or validate this
 *      annotation.
 *
 *  @note The value has to be a valid Java identifier.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: PropertyName.java 885 2021-03-23 19:33:53Z tquadrat $
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: PropertyName.java 885 2021-03-23 19:33:53Z tquadrat $" )
@Documented
@Retention( CLASS )
@Target( METHOD )
@API( status = STABLE, since = "0.1.0" )
public @interface PropertyName
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The name for the property.
     *
     *  @note The value has to be a valid Java identifier.
     *
     *  @return The name for the property.
     */
    String value();
}
//  annotation PropertyName

/*
 *  End of File
 */