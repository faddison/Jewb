package com.jewb.util;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.jewb.core.Pair;
public class FileReader 
{
	public List<Pair> readPairs(String filepath)
	{
		List<Pair> pairs = new ArrayList<Pair>();
		try
		{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream(filepath);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   
			{
				String[] strpair = strLine.split(";");
				String[] strp1 = strpair[0].split(",");
				String[] strp2 = strpair[1].split(",");
				Point p1 = new Point(Integer.parseInt(strp1[0]), Integer.parseInt(strp1[1]));
				Point p2 = new Point(Integer.parseInt(strp2[0]), Integer.parseInt(strp2[1]));
				pairs.add(new Pair(p1, p2));
			}
			//Close the input stream
			in.close();
	    }
		catch (Exception e)
		{
			//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return pairs;
	}
}