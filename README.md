
<html>
<head>
</head>
<body>
        <h1> Split Monney App (Client-Server app) </h1>
        
<p>for testing this app  you have to run the splitServer class in splitServer package first.  then run app class  in splitClient package for each user<br>
        in this app information are saved temporary in arraylists (no database) so you need to 
        be connected to server all time   </p>
        <ol>
              <h2><li > Summary</li></h2>

 <p>
This app allow you to share bills  and make sure that everyone gets paid back.

Use this app to split household bills with roommates, to figure out costs for a group vacation, or just to remember when a friend spots you for lunch
                        very similar to  <a href="https://play.google.com/store/apps/details?id=com.Splitwise.SplitwiseMobile&hl=en">Splitwise</a>
                        app with only basic functions for educational purposes.

 </p>

<h2><li > Project steps </li></h2>

<ul> 

 <li> Using server and client basic classes that used in 
                            <a href="https://maya-karahbala.github.io/Connect4/"> Connect4 </a> Project
                    </li>
                        <li> Adding Group and Bill classes which implement java.io.Serializable the type allowed to sent between server and clients</li>
                        <li> In the first step client must connect to the Server with uniqe name.</li>
                        <li> all recent notification will adde to home page</li><br>
                        <img align="center" width="500" height="400" src="/Pictures/Avtivitys.png"><br><br>
                        <li> when new client connect to server, server will create 
                    sclient (sub server) for this client and send message with client name to sclient</li>
                        <li> sclient will add that client to Clients Array list </li>
                                <li>
                                Client allowed to create group with other connected clients </li>
                                <li> group info will added to client who create the group and other clients involved in that group</li>
                    <br>
                        <img align="center" width="500" height="400" src="/Pictures/addGroup.png"><br><br>
                               

 <li> 
                                 Client allowd to add bills with ammount, description and selected 
                            group
                                </li>

<br>
                        <img align="center" width="500" height="400" src="/Pictures/AddBill.png"><br><br>
 <li> bill will added to clients myBills ArrayList and send messages to sclent with group info

  </li>
                                 <li> client can mark a bill as paid and message will be sent also to the other client and marked as apaid</li>  
                    <br>
                        <img align="center" width="500" height="400" src="/Pictures/MyBills.png"><br><br>

 <li>  sclient will resend bills to clients included in that group</li>

 <li>clients recive bill and added to recivedBill
                                    ArrayList and make update to affected tabels</li>
                          
 <br>
                        <img align="center" width="500" height="400" src="/Pictures/recivedBills.png"><br><br> 

 <li> client can search  his groups, bills and recived bills</li>

                       
                        
                    

 </ul>

</ol>

        

</body>

        
