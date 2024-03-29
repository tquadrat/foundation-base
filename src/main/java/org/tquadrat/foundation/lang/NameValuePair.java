/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

import java.util.Map.Entry;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

/**
 *  An implementation of a name-value-pair.
 *
 *  @param  <V> The type of the value.
 *  @param  name    The name; may not be {@code null}, and it may not be
 *      empty.
 *  @param  value   The value; can be {@code null}.
 *
 *  @version $Id: NameValuePair.java 1119 2024-03-16 09:03:57Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: NameValuePair.java 1119 2024-03-16 09:03:57Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public record NameValuePair<V>( String name, V value )
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code NameValuePair}.
     *
     *  @param  name    The name; may not be {@code null}, and it may not be
     *      empty.
     *  @param  value   The value; can be {@code null}.
     */
    public NameValuePair
    {
        requireNotEmptyArgument( name, "name" );
    }   //  NameValuePair()

    /**
     *  Creates a new instance of {@code NameValuePair} from the given
     *  {@link java.util.Map.Entry}
     *  instance.
     *
     *  @param  entry   The entry.
     */
    public NameValuePair( final Entry<String, ? extends V> entry )
    {
        this( requireNonNullArgument( entry, "entry" ).getKey(), entry.getValue() );
    }   //  NameValuePair()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Creates a new instance of {@code NameValuePair} that takes the name
     *  from this instance and assigns the given new value.
     *
     *  @param  newValue    The new value; may be {@code null}.
     *  @return The new instance.
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public final NameValuePair<V> newValue(final V newValue ) { return new NameValuePair<>( name, newValue ); }
}
//  record NameValuePair

/*
 *  End of File
 */