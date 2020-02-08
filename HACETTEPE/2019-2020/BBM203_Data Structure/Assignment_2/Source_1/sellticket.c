#include <string.h>
#include <stdlib.h>
#include <stdio.h>



typedef struct seat
{
    
    char *class;
   
    struct seat *next;

} Seat;


typedef struct  passenger{
    

    char *passenger_name;
    char *wish_class;
    char *tookclass;
    char *flight_name;
    char *priorty;
    struct passenger *next;
    struct passenger *order;
    struct passenger *ticket;
    int have_ticket;
  

} Passenger;

typedef struct flight
{
    char *flight_name;
    int  total_sold_seats[3][2];

    int is_closed;
    Passenger  *business_queue_1;
    Passenger  *business_queue_0;
    Passenger  *economy_queue_1;
    Passenger  *economy_queue_0;
    Passenger  *standart_queue_0;


    Passenger  *economy_tickets;
    Passenger  *business_tickets;
    Passenger  *standart_tickets;

    Seat  *seat_business;
    Seat  *seat_economy;
    Seat  *seat_standart;




    struct flight *next;

} Flight;


Flight *Select_Flight(char *flight_name,Flight *flighthead){

    Flight *temp;


   
    
    for ( temp =flighthead; temp!=NULL;temp=temp->next)
    {
      
       if (strcmp(temp->flight_name,flight_name)==0)
       {
           return temp;
       }
    }

    return NULL;
}

void Create_Seat(Flight *flight,char *class){

    Seat *seat=malloc(sizeof(Seat));

    seat->class=malloc(sizeof(class));
    strcpy(seat->class,class);
    seat->next=NULL;
    if (strcmp(class,"business")==0)
    {
        if (flight->seat_business==NULL)
        {
            flight->seat_business=seat;
        }else{

            Seat *temp=flight->seat_business;
            while(temp->next!=NULL){
                temp=temp->next;
            }
            temp->next=seat;
        }
    }else   if (strcmp(class,"economy")==0)
    {
        if (flight->seat_economy==NULL)
        {
            flight->seat_economy=seat;
        }else{

            Seat *temp=flight->seat_economy;
            while(temp->next!=NULL){
                temp=temp->next;
            }
            temp->next=seat;
        }
    }else   if (strcmp(class,"standard")==0)
    {
        if (flight->seat_standart==NULL)
        {
            flight->seat_standart=seat;
        }else{

            Seat *temp=flight->seat_standart;
            while(temp->next!=NULL){
                temp=temp->next;
            }
            temp->next=seat;
        }
    }

    
}

void AddnewSeats(Flight *flight,char *seat_class,char *seatnumber){

    int i;
    int number=atoi(seatnumber);
    if (strcmp("business",seat_class)==0)
    {
        flight->total_sold_seats[0][0]+=number;
        for ( i = 0; i <  number; ++i)
        {
            Create_Seat(flight,seat_class);
        }
        
      
    }else  if (strcmp("economy",seat_class)==0)
    {
        flight->total_sold_seats[1][0]+=number;
        for ( i = 0; i <  number; ++i)
        {
            Create_Seat(flight,seat_class);
        }       
    }else  if (strcmp("standard",seat_class)==0)
    {
        flight->total_sold_seats[2][0]+=number;
        for ( i = 0; i <  number; ++i)
        {
            Create_Seat(flight,seat_class);
        }
    }
}

Flight *ConnectFlight(Flight *flight ,Flight *flighthead){

    if (flighthead==NULL)
    {
        flighthead=flight;

    }else{

        Flight *temp=flighthead;

        while(temp->next!=NULL){

            temp=temp->next;
        }
        temp->next=flight;
    }


    return flighthead;

}

Flight *Create_Flight(char *flight_name,char *seat_class,char *seatnumber){


    int i,j;
    Flight *flight=malloc(sizeof(Flight));

    flight->flight_name=malloc(sizeof(flight_name));

    strcpy(flight->flight_name,flight_name);

    flight->next=NULL;
    flight->is_closed=0;


    flight->business_queue_1=malloc(sizeof(Passenger));
    flight->business_queue_1=NULL;

    flight->business_queue_0=malloc(sizeof(Passenger));
    flight->business_queue_0=NULL;

    flight->economy_queue_1=malloc(sizeof(Passenger));
    flight->economy_queue_1=NULL;

    flight->economy_queue_0=malloc(sizeof(Passenger));
    flight->economy_queue_0=NULL;
  
    
    flight->standart_queue_0=malloc(sizeof(Passenger));
    flight->standart_queue_0=NULL;


    flight->seat_economy=malloc(sizeof(Seat));
    flight->seat_economy=NULL;

    flight->seat_business=malloc(sizeof(Seat));
    flight->seat_business=NULL;

    flight->seat_standart=malloc(sizeof(Seat));
    flight->seat_standart=NULL;


    flight->economy_tickets=malloc(sizeof(Passenger));
    flight->economy_tickets=NULL;

    flight->business_tickets=malloc(sizeof(Passenger));
    flight->business_tickets=NULL;

    flight->standart_tickets=malloc(sizeof(Passenger));
    flight->standart_tickets=NULL;

    for ( i = 0; i < 3; ++i)
    {
        for ( j = 0; j < 2; ++j)
        {
            flight->total_sold_seats[i][j]=0;
        }
    }
    
    AddnewSeats(flight,seat_class,seatnumber);

    return flight;

}

Flight *AddSeatCommand(char *flight_name,char *seat_class,char *seatnumber,Flight *flighthead,FILE *stderr){

    printf("-%s-%s-%s-\n",flight_name,seat_class,seatnumber);


    Flight *flight=Select_Flight(flight_name,flighthead);


    if (flight==NULL)
    {
        flight=Create_Flight(flight_name,seat_class,seatnumber);
        

        flighthead=ConnectFlight(flight,flighthead);

        fprintf(stderr, "addseats %s %d %d %d\n",flight->flight_name,flight->total_sold_seats[0][0] 
            ,flight->total_sold_seats[1][0] 
            ,flight->total_sold_seats[2][0]);
    }else {


        if (flight->is_closed==0)
        {
            AddnewSeats(flight,seat_class,seatnumber);
            fprintf(stderr, "addseats %s %d %d %d\n",flight->flight_name,flight->total_sold_seats[0][0] 
            ,flight->total_sold_seats[1][0] 
            ,flight->total_sold_seats[2][0]);
        }else{

            fprintf(stderr, "error\n" );
        }
       
    }
     printf("flight id : %s busıness:  %d econumy : %d  standart : %d  \n",flight->flight_name,flight->total_sold_seats[0][0] ,
        flight->total_sold_seats[1][0] ,flight->total_sold_seats[2][0] );


    return flighthead;
}

Passenger *CreatePassenger(char *flight_name,char *class,char *passenger_name,char *priorty){


        Passenger *passenger=malloc(sizeof(Passenger));

        passenger->flight_name=malloc(sizeof(flight_name));
        passenger->wish_class=malloc(sizeof(class));
        passenger->passenger_name=malloc(sizeof(passenger_name));
        passenger->have_ticket=0;
        passenger->tookclass=malloc(sizeof("none"));

        strcpy( passenger->tookclass,"none");
        strcpy( passenger->flight_name,flight_name);
        strcpy( passenger->wish_class,class);
        strcpy( passenger->passenger_name,passenger_name);
      

       
        if (strcmp(priorty,"diplomat")==0 &&
            strcmp(class,"business")==0 
            )
        {

            passenger->priorty=malloc(sizeof(priorty));
            strcpy( passenger->priorty,priorty);


        }else 
        if (strcmp(class,"economy")==0 &&
            strcmp(priorty,"veteran")==0 
            )
        {

              passenger->priorty=malloc(sizeof(priorty));
            strcpy( passenger->priorty,priorty);



        }else if(strcmp(priorty,"")==0 ){
             passenger->priorty=malloc(sizeof("normal"));
            strcpy( passenger->priorty,"normal");

        }else{

           passenger->priorty=malloc(sizeof("error"));
            strcpy( passenger->priorty,"error");


        }



        passenger->next=NULL;
        passenger->order=NULL;
        passenger->ticket=NULL;
    
        return passenger;
}

Passenger *ConectPasseneger(Passenger *passenger,Passenger *passengerhead){

        if (passengerhead==NULL)
        {
            passengerhead=passenger;

        }else{

            Passenger *temp=passengerhead;

            while(temp->next!=NULL){

                temp=temp->next;
            }
            temp->next=passenger;
        }


        return passengerhead;
}


void TakepassengerToQueue(Passenger *passenger,Flight *flight){


    if (strcmp(passenger->wish_class,"business")==0)
    {
        
        if (strcmp(passenger->priorty,"diplomat")==0)
        {
           if (flight->business_queue_1==NULL)
           {
               flight->business_queue_1=passenger;
           }else{

                Passenger *temp=flight->business_queue_1;

                while(temp->order!=NULL){

                    temp=temp->order;
                }
                temp->order=passenger;
           }
          
        }else if (strcmp(passenger->priorty,"normal")==0)
        {
           if (flight->business_queue_0==NULL)
           {
               flight->business_queue_0=passenger;
           }else{

                Passenger *temp=flight->business_queue_0;

                while(temp->order!=NULL){

                    temp=temp->order;
                }
                temp->order=passenger;
           }
        }
    }else   if (strcmp(passenger->wish_class,"economy")==0)
    {
        
        if (strcmp(passenger->priorty,"veteran")==0)
        {
           if (flight->economy_queue_1==NULL)
           {
               flight->economy_queue_1=passenger;
           }else{

                Passenger *temp=flight->economy_queue_1;

                while(temp->order!=NULL){

                    temp=temp->order;
                }
                temp->order=passenger;
           }
          
        }else if (strcmp(passenger->priorty,"normal")==0)
        {
           if (flight->economy_queue_0==NULL)
           {
               flight->economy_queue_0=passenger;
           }else{

                Passenger *temp=flight->economy_queue_0;

                while(temp->order!=NULL){

                    temp=temp->order;
                }
                temp->order=passenger;
           }
        }
    }else   if (strcmp(passenger->wish_class,"standard")==0)
    {
        
      
      if (strcmp(passenger->priorty,"normal")==0)
        {
           if (flight->standart_queue_0==NULL)
           {
               flight->standart_queue_0=passenger;
           }else{

                Passenger *temp=flight->standart_queue_0;

                while(temp->order!=NULL){

                    temp=temp->order;
                }
                temp->order=passenger;
           }
        }
    }

}


int NumberofSeat(char *class,Flight *flight){
    

    if (strcmp(class,"business")==0)
    {   

        return flight->total_sold_seats[0][0]-flight->total_sold_seats[0][1];
    }else   if (strcmp(class,"economy")==0)
    {
         return flight->total_sold_seats[1][0]-flight->total_sold_seats[1][1];
    }else   if (strcmp(class,"standard")==0)
    {
         return flight->total_sold_seats[2][0]-flight->total_sold_seats[2][1];
    }
   

    return 0;

}
int sizeofQueue(char *class,Flight *flight){
    
    int number=0;
    Passenger *passenger;
    if (strcmp(class,"business")==0)
    {   
        passenger=flight->business_queue_1;
        while(passenger!=NULL){
            number++;
            passenger=passenger->order;
        } 
        passenger=flight->business_queue_0;
        while(passenger!=NULL){
            number++;
            passenger=passenger->order;
        }    
        
    }else   if (strcmp(class,"economy")==0)
    {
        passenger=flight->economy_queue_1;
        while(passenger!=NULL){
            number++;
            passenger=passenger->order;
        } 
        passenger=flight->economy_queue_0;
        while(passenger!=NULL){
            number++;
            passenger=passenger->order;
        }    
    }else   if (strcmp(class,"standard")==0)
    {
        passenger=flight->standart_queue_0;
        while(passenger!=NULL){
            number++;
            passenger=passenger->order;
        }   
    }
   

    return number;

}
Passenger *EnqueueCommand(char *flight_name,char *class,char *passenger_name,char *priorty,Passenger *passengerhead,Flight *flighthead,FILE *stderr){

    

        Passenger *passenger=CreatePassenger(flight_name,class,passenger_name,priorty);


        passengerhead=ConectPasseneger(passenger,passengerhead);


        Flight *flight=Select_Flight(flight_name,flighthead);

        TakepassengerToQueue(passenger,flight);

         
        if (strcmp(passenger->priorty,"error")!=0)
        {
            
            int number=sizeofQueue(passenger->wish_class,flight);


            fprintf(stderr, "queue %s %s %s %d\n", passenger->flight_name,passenger->passenger_name,passenger->wish_class,number);
        }else{
           
            fprintf(stderr, "error\n" );
                    }
        return passengerhead;
}

void popBusiness(Flight *flight){


    Seat *temp=flight->seat_business;
    if (temp->next==NULL)
    {
         flight->seat_business=NULL;
    }else{
        flight->seat_business=flight->seat_business->next;
    }
}
void popEconomy(Flight *flight){


    Seat *temp=flight->seat_economy;
    if (temp->next==NULL)
    {
         flight->seat_economy=NULL;
    }else{
        flight->seat_economy=flight->seat_economy->next;
    }
}
void popStandard(Flight *flight){


    Seat *temp=flight->seat_standart;
    if (temp->next==NULL)
    {
         flight->seat_standart=NULL;
    }else{
        flight->seat_standart=flight->seat_standart->next;
    }
}
void DequeueBusiness_1(Flight *flight){
    Passenger *temp=flight->business_queue_1;

    if (temp->order==NULL)
    {  
         flight->business_queue_1=NULL;
    }else{
        flight->business_queue_1=flight->business_queue_1->order;
    }
}
void DequeueBusiness_0(Flight *flight){
    Passenger *temp=flight->business_queue_0;

    if (temp->order==NULL)
    {   
         flight->business_queue_0=NULL;
    }else{
        flight->business_queue_0=flight->business_queue_0->order;
    }
}
void DequeueEconomy_1(Flight *flight){
    Passenger *temp=flight->economy_queue_1;

    if (temp->order==NULL)
    {  
         flight->economy_queue_1=NULL;
    }else{
        flight->economy_queue_1=flight->economy_queue_1->order;
    }
}
void DequeueEconomy_0(Flight *flight){
    Passenger *temp=flight->economy_queue_0;

    if (temp->order==NULL)
    {   
         flight->economy_queue_0=NULL;
    }else{
        flight->economy_queue_0=flight->economy_queue_0->order;
    }
}
void DequeueStandard_0(Flight *flight){
    Passenger *temp=flight->standart_queue_0;

    if (temp->order==NULL)
    {   
         flight->standart_queue_0=NULL;
    }else{
        flight->standart_queue_0=flight->standart_queue_0->order;
    }
}

void AddTicketsBusiness(Flight *flight,Passenger *passenger){
    Passenger *temp=flight->business_tickets;

    if (flight->business_tickets==NULL)
    {
        flight->business_tickets=passenger;
    }else{

      
        while(temp->ticket!=NULL){

                temp=temp->ticket;
        }
     
        temp->ticket=passenger;
    }
  
}
void AddTicketsEconomy(Flight *flight,Passenger *passenger){
    Passenger *temp=flight->economy_tickets;

    if (flight->economy_tickets==NULL)
    {
        flight->economy_tickets=passenger;
    }else{

    
        while(temp->ticket!=NULL){

                temp=temp->ticket;
        }
       
        temp->ticket=passenger;
    }
  
}
void AddTicketsStandard(Flight *flight,Passenger *passenger){
    Passenger *temp=flight->standart_tickets;

    if (flight->standart_tickets==NULL)
    {
        flight->standart_tickets=passenger;
    }else{

        
        while(temp->ticket!=NULL){

                temp=temp->ticket;
        }
      
        temp->ticket=passenger;
    }
  
}
void SellBusinessClass(Flight *flight){
    while(flight->business_queue_1!=NULL){


        
        if (flight->seat_business!=NULL)
        {   

           
            AddTicketsBusiness(flight,flight->business_queue_1);
            flight->business_queue_1->have_ticket=1;
            flight->business_queue_1->tookclass=realloc(flight->business_queue_1->tookclass,sizeof(flight->seat_business->class));
           
            strcpy(flight->business_queue_1->tookclass,flight->seat_business->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->business_queue_1->passenger_name,flight->business_queue_1->priorty
            ,flight->business_queue_1->tookclass);
            flight->total_sold_seats[0][1]++;

            popBusiness(flight);
        }else{
            break;
        }
        DequeueBusiness_1(flight);
    }
    while(flight->business_queue_0!=NULL){


        
        if (flight->seat_business!=NULL)
        {   

            AddTicketsBusiness(flight,flight->business_queue_0);
            flight->business_queue_0->have_ticket=1;
            flight->business_queue_0->tookclass=realloc(flight->business_queue_0->tookclass,sizeof(flight->seat_business->class));
            strcpy(flight->business_queue_0->tookclass,flight->seat_business->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->business_queue_0->passenger_name,flight->business_queue_0->priorty
            ,flight->business_queue_0->tookclass);
            flight->total_sold_seats[0][1]++;
            popBusiness(flight);
        }else{
            break;
        }
        DequeueBusiness_0(flight);
    }
}
void SellEconomyClass(Flight *flight){
    while(flight->economy_queue_1!=NULL){

        if (flight->seat_economy!=NULL)
        {   

            AddTicketsEconomy(flight,flight->economy_queue_1);
            flight->economy_queue_1->have_ticket=1;
            flight->economy_queue_1->tookclass=realloc(flight->economy_queue_1->tookclass,sizeof(flight->seat_economy->class));
            strcpy(flight->economy_queue_1->tookclass,flight->seat_economy->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->economy_queue_1->passenger_name,flight->economy_queue_1->priorty
            ,flight->economy_queue_1->tookclass);
            flight->total_sold_seats[1][1]++;
            popEconomy(flight);
        }else{
            break;
        }
        DequeueEconomy_1(flight);
    }
    while(flight->economy_queue_0!=NULL){

        
        if (flight->seat_economy!=NULL)
        {   
             AddTicketsEconomy(flight,flight->economy_queue_0);
            flight->economy_queue_0->have_ticket=1;
            flight->economy_queue_0->tookclass=realloc(flight->economy_queue_0->tookclass,sizeof(flight->seat_economy->class));
            strcpy(flight->economy_queue_0->tookclass,flight->seat_economy->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->economy_queue_0->passenger_name,flight->economy_queue_0->priorty
            ,flight->economy_queue_0->tookclass);
            flight->total_sold_seats[1][1]++;
            popEconomy(flight);
        }else{
            break;
        }
        DequeueEconomy_0(flight);
    }
}
void SellStandardClass(Flight *flight){

    while(flight->standart_queue_0!=NULL){

        if (flight->seat_standart!=NULL)
        {   

            AddTicketsStandard(flight,flight->standart_queue_0);
            flight->standart_queue_0->have_ticket=1;
            flight->standart_queue_0->tookclass=realloc(flight->standart_queue_0->tookclass,sizeof(flight->seat_standart->class));
            strcpy(flight->standart_queue_0->tookclass,flight->seat_standart->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->standart_queue_0->passenger_name,flight->standart_queue_0->priorty
            ,flight->standart_queue_0->tookclass);
            flight->total_sold_seats[2][1]++;
            popStandard(flight);
        }else{
            break;
        }
        DequeueStandard_0(flight);
    }
    while(flight->business_queue_1!=NULL){


        if (flight->seat_standart!=NULL)
        {   

            AddTicketsStandard(flight,flight->business_queue_1);
            flight->business_queue_1->have_ticket=1;
            flight->business_queue_1->tookclass=realloc( flight->business_queue_1->tookclass,sizeof(flight->seat_standart->class));
           
            strcpy(flight->business_queue_1->tookclass,flight->seat_standart->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->business_queue_1->passenger_name,flight->business_queue_1->priorty
            ,flight->business_queue_1->tookclass);
            flight->total_sold_seats[2][1]++;
            popStandard(flight);
        }else{
            break;
        }
        DequeueBusiness_1(flight);
    }
    while(flight->business_queue_0!=NULL){


        
        if (flight->seat_standart!=NULL)
        {   
            AddTicketsStandard(flight,flight->business_queue_0);
            flight->business_queue_0->have_ticket=1;
             flight->business_queue_0->tookclass=realloc(flight->business_queue_0->tookclass,sizeof(flight->seat_standart->class));
          
            strcpy(flight->business_queue_0->tookclass,flight->seat_standart->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->business_queue_0->passenger_name,flight->business_queue_0->priorty
            ,flight->business_queue_0->tookclass);
            flight->total_sold_seats[2][1]++;
            popStandard(flight);
        }else{
            break;
        }
        DequeueBusiness_0(flight);
    }
    while(flight->economy_queue_1!=NULL){

        if (flight->seat_standart!=NULL)
        {   

            AddTicketsStandard(flight,flight->economy_queue_1);
            flight->economy_queue_1->have_ticket=1;
             flight->economy_queue_1->tookclass=realloc(flight->economy_queue_1->tookclass,sizeof(flight->seat_standart->class));
          
            strcpy(flight->economy_queue_1->tookclass,flight->seat_standart->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->economy_queue_1->passenger_name,flight->economy_queue_1->priorty
            ,flight->economy_queue_1->tookclass);
            flight->total_sold_seats[2][1]++;
            popStandard(flight);
        }else{
            break;
        }
        DequeueEconomy_1(flight);
    }

    while(flight->economy_queue_0!=NULL){

        
        if (flight->seat_standart!=NULL)
        {   
            AddTicketsStandard(flight,flight->economy_queue_0);
            flight->economy_queue_0->have_ticket=1;
            flight->economy_queue_0->tookclass=realloc(flight->economy_queue_0->tookclass,sizeof(flight->seat_standart->class));
          
            strcpy(flight->economy_queue_0->tookclass,flight->seat_standart->class);

            printf("Satıldı  ..satın alan: %s  priorty :%s  took class : %s \n",flight->economy_queue_0->passenger_name,flight->economy_queue_0->priorty
            ,flight->economy_queue_0->tookclass);
            flight->total_sold_seats[2][1]++;
            popStandard(flight);
        }else{
            break;
        }
        DequeueEconomy_0(flight);
    }


}
void SellCommand(char *flight_name,Flight *flighthead,FILE *stderr){

    Flight *flight=Select_Flight(flight_name,flighthead);

    if (flight->is_closed==0)
    {    


        printf("number od    seat ---------- %d %d %d \n",NumberofSeat("business",flight),
        NumberofSeat("economy",flight)
        ,NumberofSeat("standard",flight) );
        printf("------------------------------Solded ------Busined\n");
        SellBusinessClass(flight);

        printf("number od    seat ---------- %d %d %d \n",NumberofSeat("business",flight),
        NumberofSeat("economy",flight)
        ,NumberofSeat("standard",flight) );
       
        printf("------------------------------Solded ------Econnoy\n");
        SellEconomyClass(flight);

        printf("number od    seat ---------- %d %d %d \n",NumberofSeat("business",flight),
        NumberofSeat("economy",flight)
        ,NumberofSeat("standard",flight) );

        printf("------------------------------Solded ------Standart\n");
        SellStandardClass(flight);

        fprintf(stderr, "sold %s %d %d %d\n",flight->flight_name,flight->total_sold_seats[0][1] ,
            flight->total_sold_seats[1][1] ,
            flight->total_sold_seats[2][1] );
         printf("number od    seat ---------- %d %d %d \n",NumberofSeat("business",flight),
        NumberofSeat("economy",flight)
        ,NumberofSeat("standard",flight) );

    }else{

        fprintf(stderr, "error\n");
    }
}

int NumberofSoldedTicket(char *class,Flight *flight){
    
    int number=0;
    Passenger *passenger;
    if (strcmp(class,"business")==0)
    {   
        passenger=flight->business_tickets;
        while(passenger!=NULL){
            number++;
            passenger=passenger->ticket;
        } 
   
        
    }else   if (strcmp(class,"economy")==0)
    {
        passenger=flight->economy_tickets;
        while(passenger!=NULL){
            number++;
            passenger=passenger->ticket;
        } 
       
    }else   if (strcmp(class,"standard")==0)
    {
        passenger=flight->standart_tickets;
        while(passenger!=NULL){
            number++;
            passenger=passenger->ticket;
        }   
    }
   

    return number;

} 
void ReportCommand(char *flight_name,Flight *flighthead,FILE *stderr){

     Flight *flight=Select_Flight(flight_name,flighthead);
     if (flight==NULL)
     {
         fprintf(stderr, "error\n" );
     }else{

        Passenger *passenger=flight->business_tickets;
        fprintf(stderr, "report %s\n",flight->flight_name);
        fprintf(stderr, "business %d\n",NumberofSoldedTicket("business",flight));
        while(passenger!=NULL){

            fprintf(stderr, "%s\n",passenger->passenger_name );
            passenger=passenger->ticket;
        }
         fprintf(stderr, "economy %d\n",NumberofSoldedTicket("economy",flight));
         passenger=flight->economy_tickets;
         while(passenger!=NULL){

            fprintf(stderr, "%s\n",passenger->passenger_name );
            passenger=passenger->ticket;
        }
        fprintf(stderr, "standard %d\n",NumberofSoldedTicket("standard",flight));
         passenger=flight->standart_tickets;
         while(passenger!=NULL){

            fprintf(stderr, "%s\n",passenger->passenger_name );
            passenger=passenger->ticket;
        }
        fprintf(stderr, "end of report %s\n",flight->flight_name);

     }
}

void CloseCommand(char *flight_name,Flight *flighthead,FILE *stderr){

      Flight *flight=Select_Flight(flight_name,flighthead);

      if (flight!=NULL)
      {     
          flight->is_closed=1;
          int x=NumberofSoldedTicket("business",flight);
          int y=NumberofSoldedTicket("standard",flight);
          int z=NumberofSoldedTicket("economy",flight);
          int solded=x+y+z;
          
          
           x=sizeofQueue("business",flight);
           y=sizeofQueue("standard",flight);
           z=sizeofQueue("economy",flight);
            int remain=x+y+z;
          fprintf(stderr, "closed %s %d %d\n",flight->flight_name, solded,remain);
          Passenger *passenger=flight->business_queue_1;
          while(passenger!=NULL){

            fprintf(stderr, "waiting %s\n",passenger->passenger_name );
            passenger=passenger->order;
          }
          passenger=flight->business_queue_0;
          while(passenger!=NULL){

            fprintf(stderr, "waiting %s\n",passenger->passenger_name );
            passenger=passenger->order;
          }
          passenger=flight->economy_queue_1;
          while(passenger!=NULL){

            fprintf(stderr, "waiting %s\n",passenger->passenger_name );
            passenger=passenger->order;
          }
          passenger=flight->economy_queue_0;
          while(passenger!=NULL){

            fprintf(stderr, "waiting %s\n",passenger->passenger_name );
            passenger=passenger->order;
          }
          passenger=flight->standart_queue_0;
          while(passenger!=NULL){

            fprintf(stderr, "waiting %s\n",passenger->passenger_name );
            passenger=passenger->order;
          }


      }else{
        fprintf(stderr, "error\n" );
      }
}

void InfoCommand(char *passenger_name,Passenger *passengerhead,FILE *stderr){

    Passenger *temp=passengerhead;

    int have=0;
    while(temp!=NULL){

        if (strcmp(passenger_name,temp->passenger_name)==0)
        {   

            have=1;
            
            if (strcmp("error",temp->priorty)==0)
            {
                  fprintf(stderr, "error\n" );

            }else{
                 fprintf(stderr, "info %s %s %s %s\n",temp->passenger_name,temp->flight_name,temp->wish_class,temp->tookclass );

            }
        }
        temp=temp->next;
    }
    if (have==0)
    {
        fprintf(stderr, "error\n" );
    }
}
int main(int argc, char const *argv[]) {

    FILE *inputs=fopen(argv[1], "r");

    FILE *result=fopen(argv[2], "w");;
   


   

    char ch;
    int char_number = 0;

    char *created_word;
    created_word = (char*)malloc(sizeof(char));
    char *word1;
    word1 = (char*)malloc(sizeof(char));
    char *word2;
    word2 = (char*)malloc(sizeof(char));
    char *word3;
    word3= (char*)malloc(sizeof(char));
    char *word4;
    word4= (char*)malloc(sizeof(char));
    char *word5;
    word5 = (char*)malloc(sizeof(char));


    int word_number = 0;



    Flight  *flighthead=NULL;

    Passenger *passengerhead=NULL;
   
    while((ch = fgetc(inputs)) != EOF)
    {
        created_word = realloc(created_word,(char_number + 1) * sizeof(char));
        created_word[char_number] = ch;
        char_number = char_number + 1 ;

        if(ch == ' ' || ch == '\n')
        {
            

             if (ch == '\n')
            {
                created_word[char_number - 2] = '\0';
            }else{
                created_word[char_number - 1] = '\0';
            }
            if(word_number == 0)
            {
                word1 = realloc(word1 ,sizeof(word_number));
                
                strcpy(word1,created_word);

            }
            else if(word_number == 1)
            {
                word2 = realloc(word2 , sizeof(word_number) * sizeof(char));

                strcpy(word2,created_word);
            }
            else if(word_number == 2)
            {
                word3 = realloc(word3 , sizeof(word_number) * sizeof(char));
                strcpy(word3,created_word);
            }
            else if(word_number == 3)
            {
                word4 = realloc(word4, sizeof(word_number) * sizeof(char));
                strcpy(word4,created_word);
            }
            else if(word_number == 4)
            {
                word5 = realloc(word5 , sizeof(word_number) * sizeof(char));
                strcpy(word5,created_word);
            }
             
            word_number = word_number + 1;
            char_number = 0;

            if (ch == '\n')
            {
               
                word_number = 0;
                  
                
                if (strcmp("addseat",word1)==0)
                {
                   flighthead=AddSeatCommand(word2,word3,word4,flighthead,result);
                }else  if (strcmp("enqueue",word1)==0)
                {
                   passengerhead=EnqueueCommand(word2,word3,word4,word5,passengerhead,flighthead,result);

                }else  if (strcmp("sell",word1)==0)
                {
                    SellCommand(word2,flighthead,result);
                }else  if (strcmp("report",word1)==0)
                {
                    ReportCommand(word2,flighthead,result);
                } else  if (strcmp("close",word1)==0)
                {
                    CloseCommand(word2,flighthead,result);
                }else  if (strcmp("info",word1)==0)
                {
                   InfoCommand(word2,passengerhead,result);
                }

                memset(word1,0 ,sizeof(word1));
                memset(word2,0 ,sizeof(word2));
                memset(word3,0 ,sizeof(word3));
                memset(word4,0 ,sizeof(word4));
                memset(word5,0 ,sizeof(word5));

            
            }
        }
    }
    if (strcmp("addseat",word1)==0)
    {
       flighthead=AddSeatCommand(word2,word3,word4,flighthead,result);
    }else  if (strcmp("enqueue",word1)==0)
    {
       passengerhead=EnqueueCommand(word2,word3,word4,word5,passengerhead,flighthead,result);

    }else  if (strcmp("sell",word1)==0)
    {
        SellCommand(word2,flighthead,result);
    }else  if (strcmp("report",word1)==0)
    {
        ReportCommand(word2,flighthead,result);
    } else  if (strcmp("close",word1)==0)
    {
        CloseCommand(word2,flighthead,result);
    }else  if (strcmp("info",word1)==0)
    {
       InfoCommand(word2,passengerhead,result);
    }

    fclose(result);
    fclose(inputs);



    printf("----------------------All FLİGHT-------------------------\n");
    Flight *temp=flighthead;

    while(temp!=NULL){

        printf("flight id : %s busıness:  %d econumy : %d  standart : %d  \n",temp->flight_name,temp->total_sold_seats[0][0] ,
        temp->total_sold_seats[1][0] ,temp->total_sold_seats[2][0] );
        temp=temp->next;
    }
    printf("----------------------All  PSSENGERS-------------------------\n");
    Passenger *xx=passengerhead;

    while(xx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xx->flight_name,xx->wish_class,  xx->passenger_name,xx->priorty);
        xx=xx->next;
    }
    printf("----------------------BUSINESS CLASS PASSENGERS-------------------------\n");
    Passenger *xXx=flighthead->business_queue_1;

    while(xXx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xXx->flight_name,xXx->wish_class,  xXx->passenger_name,xXx->priorty);
        xXx=xXx->order;
    }
    printf("---------------------                                        ------------------------------------\n");
    xXx=flighthead->business_queue_0;

    while(xXx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xXx->flight_name,xXx->wish_class,  xXx->passenger_name,xXx->priorty);
        xXx=xXx->order;
    }
     printf("----------------------ECONOMY CLASS PASSENGERS-------------------------\n");
     xXx=flighthead->economy_queue_1;
    while(xXx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xXx->flight_name,xXx->wish_class,  xXx->passenger_name,xXx->priorty);
        xXx=xXx->order;
    }
      printf("---------------------                                        ------------------------------------\n");
       xXx=flighthead->economy_queue_0;
    while(xXx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xXx->flight_name,xXx->wish_class,  xXx->passenger_name,xXx->priorty);
        xXx=xXx->order;
    }
        printf("----------------------STANDARD CLASS PASSENGERS-------------------------\n");
     xXx=flighthead->standart_queue_0;
    while(xXx!=NULL){
        //char *flight_name,char *class,char *passenger_name,char *priorty
        printf("flight id %s  wish class : %s  name : %s    priorty : %s\n",xXx->flight_name,xXx->wish_class,  xXx->passenger_name,xXx->priorty);
        xXx=xXx->order;
    }


/*typedef struct flight
{
    char *flight_name;
    int  total_sold_seats[3][2];

    int is_closed;
    Passenger  *business_queue_1;
    Passenger  *business_queue_0;
    Passenger  *economy_queue_1;
    Passenger  *economy_queue_0;
    Passenger  *standart_queue_0;


    Passenger  *economy_tickets;
    Passenger  *business_tickets;
    Passenger  *standart_tickets;

    Seat  *seat_business;
    Seat  *seat_economy;
    Seat  *seat_standart;




    struct flight *next;

} Flight;*/
     Flight *fhead=flighthead;
    while(fhead!=NULL){



        free(fhead->flight_name);

        fhead=fhead->next;
    }

    /*
typedef struct  passenger{
    

    char *passenger_name;
    char *wish_class;
    char *tookclass;
    char *flight_name;
    char *priorty;
    struct passenger *next;
    struct passenger *order;
    struct passenger *ticket;
    int have_ticket;
  

} Passenger;*/

         Passenger *fpassenger=passengerhead;
    while(fhead!=NULL){



        free(fpassenger->passenger_name);
        free(fpassenger->wish_class);
        free(fpassenger->tookclass);
        free(fpassenger->flight_name);
        free(fpassenger->priorty);
       

        fhead=fhead->next;
    }

    // printf("number od seat : %d",NumberofSeat(flighthead->seat_business));



}