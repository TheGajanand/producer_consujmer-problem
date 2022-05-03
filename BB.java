class buffer{
    int b[],size,i;
    public buffer(int size){
        this.size = size;
        b = new int[size];
        i = -1;
    }
    synchronized public void push(int data){
        if(i == size-1){
            try{
                wait();
            }
            catch(Exception e){

            }
            i++;
            b[i] = data;
            notify();
        }
    }
    synchronized public int pop(){
        if(i<0){
            try{
                wait();
            }
            catch(Exception e){

            }
        }
        int n = b[i--];
        notify();
        return n;
    }
}
class producer extends Thread{
    buffer ob;
    public producer(buffer o){
        ob = o;
    }
    public void run(){
        int n =10;
        while(true){
            System.out.println(this.getName()+" process running");
            ob.push(n);
            n++;
            if(n==300)
            break;
        }
    }
}
class consumer extends Thread{
    buffer ob;
    public consumer (buffer o){
        ob =o;
    }
    public void run(){
        while(true){
            int n = ob.pop();
            System.out.println(this.getName()+"running");
        }
    }
    
}
class BB {
    public static void main(String args[]){
        buffer ob = new buffer(5);
        producer p = new producer(ob);
        consumer c = new consumer(ob);
        p.start();
        c.start();
    }
}