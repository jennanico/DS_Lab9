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
      return null;
   }

   /**
    helper function for requestMemory()
    */
   protected MemoryAllocation seek(long size)
   {
	   return null;
   }
   
   /**
   helper function for seek()
   */
   protected long calculateSpace(MemoryAllocation mem1, MemoryAllocation mem2)
   {
	   return 0L;
   }

    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   
   }
    



}
