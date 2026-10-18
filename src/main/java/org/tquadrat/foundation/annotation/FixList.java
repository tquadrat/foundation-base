/*
 * ============================================================================
 *  Copyright © 2002-2026 by Thomas Thrien.
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

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.MODULE;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.RECORD_COMPONENT;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apiguardian.api.API.Status.DEPRECATED;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 *  The annotation container for
 *  {@link BUG &#64;BUG}
 *  annotations.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: FixList.java 1294 2026-10-17 15:40:42Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 *
 *  @deprecated Replaced by
 *      {@link CR}
 *      and
 *      {@link ChangeRequestList}
 */
@ClassVersion( sourceVersion = "$Id: FixList.java 1294 2026-10-17 15:40:42Z tquadrat $" )
@API( status = DEPRECATED, since = "0.1.0" )
@Deprecated( since = "0.25.14", forRemoval = true )
@Documented
@Retention( RUNTIME )
@Target( {ANNOTATION_TYPE, CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, MODULE, PACKAGE, PARAMETER, RECORD_COMPONENT, TYPE, TYPE_PARAMETER, TYPE_USE} )
public @interface FixList
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  Provides the list of
     *  {@link BUG &#64;BUG}
     *  annotations.
     *
     *  @return The annotations.
     */
    public BUG [] value();
}
//  @interface FixList

/*
 *  End of File
 */