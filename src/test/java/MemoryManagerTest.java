import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagerTest {
	MemoryManager a;
	@BeforeEach
	void setUp() throws Exception{
		a = new MemoryManager(100);
	}
	
	@Test
	void requestMemoryTest() {
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
	void requestMemoryOppositeTest() {
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
	void mergeFrontMemoryTest() {
		MemoryAllocation j = a.requestMemory(30L, "J");
		MemoryAllocation m = a.requestMemory(60L, "M");
		MemoryAllocation n = a.requestMemory(10L, "N");
		
		a.returnMemory(j);
		a.returnMemory(m);
		assertEquals(90, n.getPosition());
		MemoryAllocation o = a.requestMemory(90L, "O");
		assertEquals(0, o.getPosition());
		
	}

	@Test
	void mergeFrontMemoryTestOpposite() {
		MemoryAllocation j = a.requestMemory(30L, "J");
		MemoryAllocation m = a.requestMemory(60L, "M");
		MemoryAllocation n = a.requestMemory(10L, "N");
		
		a.returnMemory(m);
		a.returnMemory(j);
		assertEquals(90, n.getPosition());
		MemoryAllocation o = a.requestMemory(90L, "O");
		assertEquals(0, o.getPosition());
		
	}
	
	@Test 
	void mergeBackMemoryTest() {
		MemoryAllocation p = a.requestMemory(20L, "P");
		MemoryAllocation q = a.requestMemory(40L, "Q");
		MemoryAllocation r = a.requestMemory(40L, "R");
		a.returnMemory(q);
		a.returnMemory(r);
		assertEquals(0, p.getPosition());
		
		MemoryAllocation s = a.requestMemory(80L, "S");
		assertEquals(20, s.getPosition());
	}
	
	@Test 
	void mergeBackMemoryTestOpposite() {
		MemoryAllocation p = a.requestMemory(20L, "P");
		MemoryAllocation q = a.requestMemory(40L, "Q");
		MemoryAllocation r = a.requestMemory(40L, "R");
		a.returnMemory(r);
		a.returnMemory(q);
		assertEquals(0, p.getPosition());
		
		MemoryAllocation s = a.requestMemory(80L, "S");
		assertEquals(20, s.getPosition());
	}

	@Test
	void nonConsecutiveRemovalTest() {
		MemoryAllocation p = a.requestMemory(20L, "P");
		MemoryAllocation q = a.requestMemory(40L, "Q");
		MemoryAllocation r = a.requestMemory(40L, "R");
		a.returnMemory(r);
		a.returnMemory(p);
		assertEquals(20, q.getPosition());
		MemoryAllocation u = a.requestMemory(70L, "U");
		assertEquals(null, u);
		
		MemoryAllocation s = a.requestMemory(40L, "S");
		assertEquals(60, s.getPosition());
		
		MemoryAllocation t = a.requestMemory(20L, "T");
		assertEquals(0, t.getPosition());
	}
	
	@Test
	void nonConsecutiveRemovalTestOpposite() {
		MemoryAllocation p = a.requestMemory(20L, "P");
		MemoryAllocation q = a.requestMemory(40L, "Q");
		MemoryAllocation r = a.requestMemory(40L, "R");
		a.returnMemory(p);
		a.returnMemory(r);
		assertEquals(20, q.getPosition());
		MemoryAllocation u = a.requestMemory(60L, "U");
		assertEquals(null, u);
		
		MemoryAllocation s = a.requestMemory(40L, "S");
		assertEquals(60, s.getPosition());
		
		MemoryAllocation t = a.requestMemory(20L, "T");
		assertEquals(0, t.getPosition());
	}
	
	@Test
	void notFullMergeTest() {
		MemoryAllocation v = a.requestMemory(20L, "V");
		MemoryAllocation w = a.requestMemory(20L, "W");
		a.returnMemory(w);
		assertEquals(0, v.getPosition());
		MemoryAllocation x = a.requestMemory(80L, "X");
		assertEquals(20, x.getPosition());
		MemoryAllocation y = a.requestMemory(100L,"Y");
		assertEquals(null,y);
		
		
	}
	
}
