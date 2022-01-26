/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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
 *  The base module for the Foundation Library.
 *
 *  @uses   org.tquadrat.foundation.lang.StringConverter    A service that
 *      converts object instances to String and vice versa.
 *
 *  @version $Id: module-info.java 995 2022-01-23 01:09:35Z tquadrat $
 *
 *  @todo task.list
 */
module org.tquadrat.foundation.base
{
    requires java.base;
    requires transitive org.apiguardian.api;

    //---* Common Use *--------------------------------------------------------
    exports org.tquadrat.foundation.annotation;
    exports org.tquadrat.foundation.exception;
    exports org.tquadrat.foundation.function;
    exports org.tquadrat.foundation.function.tce;
    exports org.tquadrat.foundation.lang;
    exports org.tquadrat.foundation.stream;

    uses org.tquadrat.foundation.lang.StringConverter;

    //---* Internal Use *------------------------------------------------------
    exports org.tquadrat.foundation.lang.internal to org.tquadrat.foundation.util;
}

/*
 *  End of File
 */