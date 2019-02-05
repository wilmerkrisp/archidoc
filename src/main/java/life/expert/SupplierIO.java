package life.expert;//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/30
//
//--------------------------------------------------------------------------------









import java.io.IOException;




/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 *
 * @param <T>
 * 	the type of results supplied by this supplier
 *
 * @since 1.8
 */
@FunctionalInterface
public interface SupplierIO<T> {



/**
 * Gets a result.
 *
 * @return a result
 *
 * @throws IOException
 * 	the io exception
 */
T get() throws IOException;
}
