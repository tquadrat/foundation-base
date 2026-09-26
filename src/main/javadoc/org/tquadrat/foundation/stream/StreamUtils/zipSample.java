/**
 *  Reads the given file and prints it with line numbers to the
 *  console. Blank lines and lines starting with '#' will be ignored.
 *
 *  @param  file    The file to read.
 *  @throws IOException Problems reading the file.
 */
public final void dumpFileWithLineNumbers( final File file ) throws IOException
{
    final var lines = lines( requireNonNullArgument( file, "file" ).toPath() )
        .filter( not( String::isBlank ) )
        .filter( l -> !l.startsWith( "#" ) );
    final var lineNumbers = Stream.<Integer>iterate( 1, i -> ++i );
    final BiFunction<String,Integer,String> combiner = (l,i) -> "%d - %s".formatted( i, l );
    zip( lines, lineNumbers, combiner )
        .forEach( out::println );
}   //  dumpFileWithLineNumbers()