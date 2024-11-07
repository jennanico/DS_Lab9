
public class MemoryManager
{
   protected MemoryAllocation head;
   protected long size;
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   public MemoryManager(long size)
   {
	   this.size = size;
	   
	   this.head = new MemoryAllocation(null, -1, -1); // sentinel header
	   head.next = head;
	   head.prev = head;							   // circularly linked
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
	   if (head.next == head) {
		   MemoryAllocation newMem = new MemoryAllocation(requester, 0, size, head, head);
		   head.next = newMem;
		   head.prev = newMem;
		   return newMem;
	   }
	  
	  MemoryAllocation space = seek(size);
	  if(space == null)
	  {
		  return null;
	  }
	  MemoryAllocation newMem = new MemoryAllocation(requester, (space.getPosition()+space.getLength()), size, space.next, space);
	  newMem.prev.next = newMem;
	  newMem.next.prev = newMem;
	  
      return newMem;
   }

   /**
    helper function for requestMemory()
    */
   protected MemoryAllocation seek(long size)
   {
	   MemoryAllocation curr = head;
	   long calculating = 0L;
	   while(size > calculating)
	   {
		   calculating = calculateSpace(curr, curr.next);
		   
		   curr = curr.next;
		   if(curr == head) {
			   break;
		   }
	   }
	   if (size > calculating)
	   {
		   return null;
	   }
	   return curr.prev;
   }
   
   /**
   helper function for seek()
   */
   protected long calculateSpace(MemoryAllocation mem1, MemoryAllocation mem2)
   {
	
	   if(mem1 == head)
	   {
		  long calc1 = mem2.getPosition();
		  return calc1;
		  
	   }
	   if (mem2 == head)
	   {
		   long calc1 = size - mem1.getPosition();
		   long calc2 = calc1 - mem1.getLength();
		   return calc2;
	   }
	   long calc1 = mem2.getPosition() - mem1.getPosition();
	   long calc2 = calc1 - mem1.getLength();
	   return calc2;
   }

    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   mem.prev.next = mem.next;
	   mem.next.prev = mem.prev;
   }
    



}
