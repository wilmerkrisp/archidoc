package life.expert;









//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/21
//
//--------------------------------------------------------------------------------











import java.io.IOException;
import java.util.function.Consumer;









public interface ConsumerIO<T>
	{
	
	void accept(T t) throws IOException;
	
	
	}
