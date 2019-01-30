package life.expert;//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/30
//
//--------------------------------------------------------------------------------









import java.io.IOException;









/**
 * The interface Runnable io.
 */
@FunctionalInterface
public interface RunnableIO
	{
	
	
	
	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @throws IOException
	 * 	the io exception
	 * @see java.lang.Thread#run() java.lang.Thread#run()
	 */
	public abstract void run()
	throws IOException;
	}