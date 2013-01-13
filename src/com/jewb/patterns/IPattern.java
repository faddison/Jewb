package com.jewb.patterns;

import java.util.List;

import com.jewb.core.Pair;
import com.jewb.virtual.VirtualContext;

public interface IPattern 
{	
	public List<Pair> generate(VirtualContext vcontext);
}
