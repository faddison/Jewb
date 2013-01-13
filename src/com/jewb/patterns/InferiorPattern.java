package com.jewb.patterns;

import java.util.List;

import com.jewb.core.Pair;
import com.jewb.virtual.VirtualContext;
import com.jewb.virtual.VirtualGenerator;

public class InferiorPattern extends Pattern implements IPattern 
{

	@Override
	public List<Pair> generate(VirtualContext vcontext) 
	{
		VirtualContext newContext = new VirtualContext(vcontext.getVboard(), 
			"C:/Users/user/Documents/jewblist2.txt");
		
		List<Pair> pairs = new FilePattern().generate(newContext);
		
		VirtualGenerator vgen = new VirtualGenerator();
		
		List<Pair> rPairs = vgen.shiftPairsRight(pairs);
		List<Pair> iPairs = vgen.invertPairs(pairs);
		List<Pair> irPairs = vgen.shiftPairsDown(vgen.invertPairs(pairs));
		
		pairs.addAll(rPairs);
		pairs.addAll(iPairs);
		pairs.addAll(irPairs);
		
		return pairs;
	}

}
