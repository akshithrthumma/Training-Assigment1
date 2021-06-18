/*Akshith Thumma*/
public class ProdCons {
    public static void main(String[] args){
        PCbuffer obj = new PCbuffer();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run()
            {
                obj.produce();
            }
        });
  
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run()
            {
                obj.consume();
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  
    }
  
    public static class PCbuffer {
        int capacity = 3;
        int[] buff = new int[capacity];
        int index;
        public void produce(){
            int val = 0;
            while (true) {
                synchronized (this)
                {
                    while (index == capacity)
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    System.out.println("Producer Inserts: "+ val);
                    buff[index] = val++;
                    index++;
                    notify();
                }
            }
        }

        public void consume(){
            while (true) {
                synchronized (this)
                {
                    while (index == 0)
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    index--;
                    int val = buff[index];
                    System.out.println("Consumer Removes: "+ val);
                    notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

