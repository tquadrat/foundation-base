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

/**
 *  <p>Some specialised annotations that are either meant to support change
 *  management and field support (like
 *  {@link org.tquadrat.foundation.annotation.ClassVersion &#64;ClassVersion})
 *  or to provide other advanced functionality.</p>
 *  <p>The marker annotations</p>
 *      <ul>
 *          <li>{@link org.tquadrat.foundation.annotation.MountPoint &#64;MountPoint}</li>
 *          <li>{@link org.tquadrat.foundation.annotation.NotRecord &#64;NotRecord}</li>
 *          <li>{@link org.tquadrat.foundation.annotation.PlaygroundClass &#64;PlaygroundClass}</li>
 *          <li>{@link org.tquadrat.foundation.annotation.ProgramClass &#64;ProgramClass}</li>
 *          <li>{@link org.tquadrat.foundation.annotation.UtilityClass &#64;UtilityClass}</li>
 *      </ul>
 *  <p>were introduced mainly to satisfy the inspections of IntelliJ IDEA; they
 *  can be configured to ignore some 'smells' when a class or method is
 *  annotated in some way.</p>
 *  <p>The annotation
 *  {@link org.tquadrat.foundation.annotation.CR &#64;CR}
 *  together with the container
 *  {@link org.tquadrat.foundation.annotation.ChangeRequestList ChangeRequestList}
 *  are meant to support the change management.</p>
 */
@API( status = STABLE, since = "0.0.1" )
package org.tquadrat.foundation.annotation;

import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;

/*
 *  End of File
 */