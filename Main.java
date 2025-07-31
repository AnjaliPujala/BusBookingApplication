import java.util.*;

class BusBookingApplication{
    Map<String, SourceToDestination> buses;
    BusBookingApplication(){
        buses=new HashMap();
    }
    public void addBus(String source,String destination,SourceToDestination sourceToDestination){
        String key=source+"_"+destination;
        buses.put(key,sourceToDestination);
        System.out.println("Bus from "+source+" to "+destination+" added into application!");
    }
    public void removeBus(String source,String destination){
        String key=source+"_"+destination;
        buses.remove(key);
        System.out.println("Bus from "+source+" to "+destination+" removed from application!");
    }
    public SourceToDestination getBus(String source, String destination){
        String key=source+"_"+destination;
        return buses.get(key);
    }
}
abstract class SourceToDestination{
    Bus bus;
    SourceToDestination(Bus bus){
        this.bus=bus;
    }
    public boolean bookBus(int seat_number){
        Seat seat = bus.seats.get(seat_number);
        if (!seat.booking_status) {
             synchronized(seat){
                seat.booking_status = true;
                return true;
            }
        }
        return false;
    }
}

class HyderabadToBhimavaram extends SourceToDestination{
    HyderabadToBhimavaram(Bus bus){
        super(bus);
    }
}
class BangloreToHyderabad extends SourceToDestination{
    BangloreToHyderabad(Bus bus){
        super(bus);
    }
}
class BhimavaramToKanigiri extends SourceToDestination{
    BhimavaramToKanigiri(Bus bus){
        super(bus);
    }
}
class Bus{
    String bus_id;
    String bus_name;
    int total_number_of_seats;
    Map<Integer,Seat> seats;
    public Bus(String bus_id,String bus_name){
        this.bus_id=bus_id;
        this.bus_name=bus_name;
        this.total_number_of_seats=32;
        this.seats=new HashMap<>();
    }
    void addSeats(){
        for(int i=1;i<=32;i++){
            Seat seat=new Seat(i);
            addSeat(i,seat);
        }
    }
    void addSeat(int seat_number, Seat seat){
        seats.put(seat_number, seat);
    }
    void removeSeat(int seat_number){
        seats.remove(seat_number);
    }
}

class Seat{
    int seat_number;
    boolean booking_status;
    public Seat(int seat_number){
        this.seat_number=seat_number;
        this.booking_status=false;
    }
}

class User implements Runnable{
    String source;
    String destination;
    BusBookingApplication application;
    int seat_number;
    String name;
    public User(String name,String source,String destination, BusBookingApplication application, int seat_number){
        this.name=name;
        this.source=source;
        this.destination=destination;
        this.application=application;
        this.seat_number=seat_number;
    }
    public void run(){
        SourceToDestination myBus=application.getBus(source,destination);
        if(myBus.bookBus(seat_number)){
            System.out.println("Bus from "+source+" to "+destination+" booked for "+name+", Seat number "+seat_number);
        }else{
            System.out.println("Oops..!"+name+", from "+source+" to "+destination+", Seat number "+seat_number+" is already booked!");
        }
    }
    
}
public class Main{
    public static void main(String[] args){
        BusBookingApplication application=new BusBookingApplication();

        Bus bus1=new Bus("AP1234","Super Luxury");
        Bus bus2=new Bus("AP1344","Ultra Delux");
        Bus bus3=new Bus("AP6789","Express");
        bus1.addSeats();
        bus2.addSeats();
        bus3.addSeats();

        SourceToDestination bangloreToHyderabad=new BangloreToHyderabad(bus1);
        SourceToDestination hyderabadToBhimavaram=new HyderabadToBhimavaram(bus2);
        SourceToDestination bhimavaramToKanigiri=new BhimavaramToKanigiri(bus3);

        application.addBus("banglore","hyderabad",bangloreToHyderabad);
        application.addBus("hyderabad","bhimavaram",hyderabadToBhimavaram);
        application.addBus("bhimavaram","kanigiri",bhimavaramToKanigiri);

        User user1=new User("Anjali", "banglore","hyderabad", application, 10);
        User user2=new User("Venkat","hyderabad","bhimavaram", application, 11);
        User user3=new User("Raju", "bhimavaram","kanigiri", application, 31);
        User user4=new User("Rani", "banglore","hyderabad", application, 10);
        User user5=new User("Durga", "hyderabad","bhimavaram", application, 12);
        User user6=new User("Nidhi", "hyderabad","bhimavaram", application, 11);
        User user7=new User("Bhargavi", "banglore","hyderabad", application, 19);
        User user8=new User("Komali", "banglore","hyderabad", application, 14);
        User user9=new User("Suma", "bhimavaram","kanigiri", application, 16);
        User user10=new User("Mahesh", "bhimavaram","kanigiri", application, 20);
        User user11=new User("Abhi", "bhimavaram","kanigiri", application, 10);
        User user12=new User("Pandu", "hyderabad","bhimavaram", application, 10);


        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);
        Thread t3 = new Thread(user3);
        Thread t4 = new Thread(user4);
        Thread t5 = new Thread(user5);
        Thread t6 = new Thread(user6);
        Thread t7 = new Thread(user7);
        Thread t8 = new Thread(user8);
        Thread t9 = new Thread(user9);
        Thread t10 = new Thread(user10);
        Thread t11 = new Thread(user11);
        Thread t12 = new Thread(user12);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();

    }
}