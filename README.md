<!DOCTYPE html>
<html>
<head>
</head>
<body>
        <h1> Split Monney App (Client-Server app) </h1>
        
        <p>for testing this app  you have to run the splitServer class in splitServer package first.  then run app class  in splitClient package for each user<br>
        in this app information are saved temporary in arraylists (no database) so you need to 
        be connected to server all time   </p>
      
              <h2><li > Summary</li></h2>

                <p>

                        This app allow you to share bills  and make sure that everyone gets paid back.

                        Use this app to split household bills with roommates, to figure out costs for a group vacation, or just to remember when a friend spots you for lunch
                        very similar to Splitwise
                        app with only basic functions for educational purposes.

                </p>

                <h2><li > Project steps </li></h2>

                <ul> 

                        <li> Using server and cleint basic classes that used in 
                            Connect4 Project
                    </li>
                        <li> Adding Group and Bill classes which implement java.io.Serializable the type allowed to sended between server and cleints</li>
                        <li> In the first step cleint must connect to the Serer with uniqe name.</li>
                        <li> all recent notification will adde to home page</li><br>
                        <img align="center" width="400" height="400" src="/Pictures/Avtivitys.png"><br><br>
                        <li> when new cleint connect to server, server will create 
                    scleint (sub server) for this cleint and send message with cleint name to scleint</li>
                        <li> scleint will add that cleint to Clients Array list </li>
                                <li>
                                Cleint allowd to create group with other connected cleints </li>
                                <li> group info will added to cleint who create the group and other cleints involved in that group</li>
                    <br>
                        <img align="center" width="400" height="400" src="/Pictures/addGroup.png"><br><br>
                               

                        <li> 
                                 Cleint allowd to add bills with ammount, description and selected 
                            group
                                </li>

                                <br>
                        <img align="center" width="400" height="400" src="/Pictures/AddBill.png"><br><br>

                                <li> bill will added to cleints myBills ArrayList and send messages to sclent with group info

                                </li>
                                 <li> cleint can mark a bill as paid and message will be sended also to the other cleint and marked as apaid</li>  
                    <br>
                        <img align="center" width="400" height="400" src="/Pictures/MyBills.png"><br><br>

                                <li>  scleint will resend bills to cleints included in that group</li>

                                <li>cleints recive bill and added to recivedBill
                                    ArrayList and make update to affected tabels</li>
                          
                              <br>
                        <img align="center" width="400" height="400" src="/Pictures/recivedBills.png"><br><br> 

                        <li> cleint can search  his groups, bills and recived bills</li>

                       
                        
                    

                </ul>



        

</body>

        

</html>

