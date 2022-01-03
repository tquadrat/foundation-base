/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 *  This annotation will be used to provide version information for each class
 *  in a project. It can provide both source code (SCCS) related version as
 *  well as release related (Build number and Release numbers).<br>
 *  <br>Keep in mind that neither
 *  {@link #versionNumber()}
 *  nor
 *  {@link #buildNumber()}
 *  need to be numbers according to the syntax rules for numerical values.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ClassVersion.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@Documented
@Retention( RUNTIME )
@Target( TYPE )
@API( status = STABLE, since = "0.0.5" )
public @interface ClassVersion
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  Returns the build number, usually injected during the build by the
     *  build tool. The default is the empty string.
     *
     *  @return The build number.
     */
    String buildNumber() default "";

    /**
     *  Returns {@code true} if the class was generated by any kind of
     *  Source Code generator.
     *
     *  @return {@code false} (the default) if the class was manually
     *      implemented, {@code true} otherwise.
     */
    boolean isGenerated() default false;

    /**
     *  Returns the source version, usually as delivered by the SCCS.
     *
     *  @return The version of the source code.
     */
    String sourceVersion();

    /**
     *  Returns the version number, usually injected during the build by the
     *  build tool. The default is the empty string.
     *
     *  @return The version number.
     */
    String versionNumber() default "";
}
//  @interface ClassVersion

/*
 *  End of File
 */