package life.expert;







import java.io.IOException;
import java.util.function.Consumer;

//@Header@
//--------------------------------------------------------------------------------
//
//                          himalaya  life.expert.common.io
//                           wilmer 2019/01/23
//
//--------------------------------------------------------------------------------









public interface ConsumerIO<T>
	{
	
	void accept(T t) throws IOException;
	
	}
