package huffman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import junit.framework.TestCase;

public class TestCompress extends TestCase{

	public void testCompression(){
		try{
			Hzip.compress("data/TestD.dat");
			Hzip.uncompress("data/TestD.dat.huf");
			File file1 = new File("data/TestA.dat");
			File file2 = new File("data/TestA.dat.uc");
			String compressed = Files.readAllLines(file1.toPath()).toString();
			String uncompressed = Files.readAllLines(file2.toPath()).toString();
			assertEquals(compressed, uncompressed);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
