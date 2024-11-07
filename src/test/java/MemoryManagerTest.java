import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagerTest {
	MemoryManager a;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		a = new MemoryManager(100);
	}
	
	@Test
	void MergeFromLeftTest() 
	{
		MemoryAllocation c = a.requestMemory(20L, "C");
		assertEquals(c.getLength(), 20L);
		assertEquals(c.getOwner(), "C");
		assertEquals(c.getPosition(), 0);
		MemoryAllocation d = a.requestMemory(50L, "D");
		assertEquals(d.getLength(), 50L);
		assertEquals(d.getOwner(), "D");
		assertEquals(d.getPosition(), 20);
		MemoryAllocation e = a.requestMemory(25L, "E");
		assertEquals(e.getLength(), 25L);
		assertEquals(e.getOwner(), "E");
		assertEquals(e.getPosition(), 70);
		MemoryAllocation f = a.requestMemory(5L, "F");
		assertEquals(f.getLength(), 5L);
		assertEquals(f.getOwner(), "F");
		assertEquals(f.getPosition(), 95);
		
		a.returnMemory(e);
		a.returnMemory(d);
		
		MemoryAllocation z = a.requestMemory(76L, "Z");
		assertEquals(null,z);
		MemoryAllocation g = a.requestMemory(20L, "G");
		assertEquals(g.getLength(), 20L);
		assertEquals(g.getOwner(), "G");
		assertEquals(g.getPosition(), 20);
		MemoryAllocation k = a.requestMemory(55L, "K");
		assertEquals(k.getLength(), 55L);
		assertEquals(k.getOwner(), "K");
		assertEquals(k.getPosition(), 40);
		
		MemoryAllocation h = a.requestMemory(50L, "H");
		assertEquals(null, h);	
	}

	@Test
	void MergeFromRightTest() {
		MemoryAllocation c = a.requestMemory(20L, "C");
		assertEquals(c.getLength(), 20L);
		assertEquals(c.getOwner(), "C");
		assertEquals(c.getPosition(), 0);
		MemoryAllocation d = a.requestMemory(50L, "D");
		assertEquals(d.getLength(), 50L);
		assertEquals(d.getOwner(), "D");
		assertEquals(d.getPosition(), 20);
		MemoryAllocation e = a.requestMemory(25L, "E");
		assertEquals(e.getLength(), 25L);
		assertEquals(e.getOwner(), "E");
		assertEquals(e.getPosition(), 70);
		MemoryAllocation f = a.requestMemory(5L, "F");
		assertEquals(f.getLength(), 5L);
		assertEquals(f.getOwner(), "F");
		assertEquals(f.getPosition(), 95);
		
		a.returnMemory(d);
		a.returnMemory(e);
		
		MemoryAllocation z = a.requestMemory(76L, "Z");
		assertEquals(null,z);
		MemoryAllocation g = a.requestMemory(20L, "G");
		assertEquals(g.getLength(), 20L);
		assertEquals(g.getOwner(), "G");
		assertEquals(g.getPosition(), 20);
		MemoryAllocation k = a.requestMemory(55L, "K");
		assertEquals(k.getLength(), 55L);
		assertEquals(k.getOwner(), "K");
		assertEquals(k.getPosition(), 40);
		
		MemoryAllocation h = a.requestMemory(50L, "H");
		assertEquals(null, h);	
	}
	
	@Test
	void nonConsecutiveMergeTest() 
	{
		MemoryAllocation p = a.requestMemory(10L, "P");
		MemoryAllocation q = a.requestMemory(40L, "Q");
		MemoryAllocation r = a.requestMemory(25L, "R");
		MemoryAllocation ab = a.requestMemory(15L, "AB");
		MemoryAllocation bc = a.requestMemory(10L, "BC");
		
		a.returnMemory(q);
		a.returnMemory(ab);
		a.returnMemory(r);
		
		MemoryAllocation u = a.requestMemory(81L, "U");
		assertEquals(null, u);
		
		MemoryAllocation s = a.requestMemory(80L, "S");
		assertEquals(10, s.getPosition());
	}
	
	@Test
	void extraTest() {
		MemoryAllocation ad = a.requestMemory(5L, "AB");
		MemoryAllocation ae = a.requestMemory(35L, "AE");
		MemoryAllocation af = a.requestMemory(10L, "AE");
		a.returnMemory(ad);
		a.returnMemory(af);
		MemoryAllocation ag = a.requestMemory(1L, "AG");
		assertEquals(0, ag.getPosition());
	}
	
	
}