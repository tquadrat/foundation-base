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

package org.tquadrat.foundation.lang.internal;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.StringConverter.METHOD_NAME_GetSubjectClass;
import static org.tquadrat.foundation.lang.StringConverter.METHOD_NAME_Provider;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.exception.UnexpectedExceptionError;
import org.tquadrat.foundation.lang.StringConverter;

/**
 *  The implementation for the
 *  {@link StringConverter}
 *  service methods.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: StringConverterService.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: StringConverterService.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
@UtilityClass
public final class StringConverterService
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private StringConverterService() { throw new PrivateConstructorForStaticClassCalledError( StringConverterService.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the classes for that an instance of {@code StringConverter} is
     *  registered,
     *
     *  @return The classes with a string converter.
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final Collection<Class<?>> listInstances()
    {
        final var registry = loadConverters();
        final Collection<Class<?>> retValue = List.copyOf( registry.keySet() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  listInstances()

    /**
     *  Loads the known instances of
     *  {@link StringConverter}.
     *
     *  @return The registry with the converters.
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final Map<Class<?>,StringConverter<?>> loadConverters()
    {
        final var moduleLayer = StringConverter.class.getModule().getLayer();
        final var converters = isNull( moduleLayer )
            ? ServiceLoader.load( StringConverter.class )
            : ServiceLoader.load( moduleLayer, StringConverter.class );

        final Map<Class<?>,StringConverter<?>> retValue = new HashMap<>();

        for( final StringConverter<?> c : converters )
        {
            StringConverter<?> converter;
            try
            {
                final var providerMethod = c.getClass().getMethod( METHOD_NAME_Provider );
                converter = (StringConverter<?>) providerMethod.invoke( null );
            }
            catch( final NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored )
            {
                converter = c;
            }

            for( final var subjectClass : retrieveSubjectClasses( converter ) )
            {
                retValue.put( subjectClass, converter );
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  loadConverters()

    /**
     *  Returns an instance of {@code StringConverter} for the given
     *  {@link Class}.
     *  If there is no converter for the given type, or the type is
     *  {@code null}, the return value is
     *  {@link Optional#empty()}.
     *
     *  @param  <C> The class a converter is needed for.
     *  @param  type    The instance of the class a converter is needed for.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the instance of {@code StringConverter}.
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final <C> Optional<StringConverter<C>> retrieveConverterForClass( final Class<C> type )
    {
        StringConverter<?> result = null;
        if( nonNull( type ) )
        {
            final var registry = loadConverters();
            result = registry.get( type );
        }
        @SuppressWarnings( "unchecked" )
        final var retValue = Optional.ofNullable( (StringConverter<C>) result );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveConverterForClass()

    /**
     *  Returns an instance of {@code StringConverter} for the given
     *  {@link Enum} type.
     *  If there is no converter for the given type in the registry, a new
     *  instance of {@code StringConverter} will be created, on base of
     *  {@link DefaultEnumStringConverter}.
     *
     *  @param  <E> The class a converter is needed for.
     *  @param  type    The instance of the class a converter is needed for.
     *  @return The requested instance of {@code StringConverter}.
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final <E extends Enum<E>> StringConverter<E> retrieveConverterForEnum( final Class<E> type )
    {
        final var retValue = retrieveConverterForClass( requireNonNullArgument( type, "type" ) ).orElseGet( () -> new DefaultEnumStringConverter<>( type ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveConverterForEnum()

    /**
     *  <p>{@summary Determines the key class for the given instance of
     *  {@link StringConverter}.}</p>
     *  <p>The subject class is the target type for a call to
     *  {@link StringConverter#fromString(CharSequence) fromString()},
     *  and usually this is also the type of the argument for
     *  {@link StringConverter#toString(Object) toString(T)};
     *  but under some circumstances, it cannot be guessed correctly by
     *  reflection. For that case, some implementation of
     *  {@code StringConverter} provides an optional method
     *  {@code public Class<?> getSubjectClass()} that returns the respective
     *  class.</p>
     *
      * @param  converter   The converter instance.
     *  @return The subject class.
     */
    @SuppressWarnings( {"NestedTryStatement", "unchecked"} )
    private static final Collection<Class<?>> retrieveSubjectClasses( final StringConverter<?> converter )
    {
        final var converterClass = requireNonNullArgument( converter, "converter" ).getClass();
        Collection<Class<?>> retValue;
        try
        {
            try
            {
                final var getSubjectClassMethod = converterClass.getMethod( METHOD_NAME_GetSubjectClass );
                retValue = (Collection<Class<?>>) getSubjectClassMethod.invoke( converter );
            }
            catch( final NoSuchMethodException ignored )
            {
                final var fromStringMethod = converterClass.getMethod( "fromString", CharSequence.class );
                retValue = List.of( fromStringMethod.getReturnType() );
            }
        }
        catch( final NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e )
        {
            throw new UnexpectedExceptionError( e );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveSubjectClass()
}
//  class StringConverterService

/*
 *  End of File
 */