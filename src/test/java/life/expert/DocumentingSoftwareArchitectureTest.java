package life.expert;









import static org.junit.Assert.*;

import org.jetbrains.annotations.*;                     //@NotNull
import com.google.errorprone.annotations.Immutable;     //@Immutable

import com.google.common.flogger.FluentLogger;          //log

import static java.text.MessageFormat.format;           //format string

import java.util.ResourceBundle;

import com.google.common.collect.*;                     //ImmutableList
import org.junit.Test;

import static com.google.common.base.Preconditions.*;   //checkArgument
import static life.expert.common.base.Preconditions.*;  //checkCollection
import static org.apache.commons.lang3.Validate.*;      //notEmpty(collection)
import static life.expert.common.base.Objects.*;        //deepCopyOfObject

import java.util.function.*;                            //producer supplier

import static cyclops.function.Memoize.*;               //memoizeSupplier
import static java.util.stream.Collectors.*;            //toList streamAPI
import static java.util.function.Predicate.*;           //isEqual streamAPI



//@Header@
//--------------------------------------------------------------------------------
//
//                          archidoc  life.expert
//                           wilmer 2019/01/11
//
//--------------------------------------------------------------------------------









public class DocumentingSoftwareArchitectureTest
	{
	
	
	
	@Test
	public void apply()
		{
		}
	}