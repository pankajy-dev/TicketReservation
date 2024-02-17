AssessMent API:
Basic Auth -
user = yadavpankaj28@gmail.com, password = pankajyadav
admin user = admin@email.com, password = pa$$word@123

1. Create API where you can submit a purchase for a ticket. 

Book Tickets (POST)
http://localhost:8090/railway/ticket/reservation (POST)

Sample Body :
{
    "emaiId":"yadavpankaj28@gmail.com",
    "from": "London",
    "to": "France",
    "date":"170224",
    "seat":4,
    "trainId":3,
    "sectionType":"A",
    "payment_reference_no":"kishgfcksdvchdsv11"
}

2. An API that shows the details of the receipt for the user

http://localhost:8090/railway/user/2/receipt (GET)

3. An API that lets you view the users and seat they are allocated by the requested section.

http://localhost:8090/railway/admin?trainId=3&section=A (GET)

An API to remove a user from the train

http://localhost:8090/railway/admin?&userId=2&trainId=3 (DELETE)

An API to modify a user's seat

http://localhost:8090/railway/admin/updateSeat (PUT)

Sample Body :
{
    "userId":2,
    "trainId": 3,
    "seatId": 2,
    "newSeatId": 7,
    "sectionType":"A",
    "ticketId":1
}




Service supported:

8090 = replace this with your port.

1. Book Tickets (POST)
http://localhost:8090/railway/ticket/reservation

Sample Body :
{
    "emaiId":"yadavpankaj28@gmail.com",
    "from": "London",
    "to": "France",
    "date":"170224",
    "seat":4,
    "trainId":3,
    "sectionType":"A",
    "payment_reference_no":"kishgfcksdvchdsv11"
}

3. Register User: (POST)
http://localhost:8090/railway/user/register/pankaj/yadav/email

4. Get Receipts (GET)
http://localhost:8090/railway/user/2/receipt

5. Get Default Trains. (GET)

http://localhost:8090/railway/trains/170224

6. Get Train By Id (GET)
http://localhost:8090/railway/trains?trainId=3

Admin WebServices :

1. Update User's Ticket (PUT)

http://localhost:8090/railway/admin/updateSeat

Sample Body :
{
    "userId":2,
    "trainId": 3,
    "seatId": 2,
    "newSeatId": 7,
    "sectionType":"A",
    "ticketId":1
}

2. Get User By Id

http://localhost:8090/railway/admin/users

3. Get UserSeats By Section:

http://localhost:8090/railway/admin?trainId=3&section=A

4. Delete User:

http://localhost:8090/railway/admin?&userId=2&trainId=3

5. Get All Users (GET)

http://localhost:8090/railway/admin/users
