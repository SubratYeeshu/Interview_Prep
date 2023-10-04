# Computer Networks

## Define layers of OSI Model ?

PDNTSPA

The OSI (Open system interface) provides a standard for different systems to be able to communicate with each other.

- Physical Layer - > Hub is present here, Deals with the raw bit stream over physical medium. Transmission types simplex, half
duplex, full duplex. Unit - Bit.

- Data Link Layer - > Hub is present here, hop to hop delivery, Flow control -> STOP AND WAIT, GO BACK N, SELECTIVE REPEAT. Error

- Control -> Parity check, Checksum, CRC, Access Control, physical address MAC, framing, CSMA/C/A, Unit - Frames.

- Network Layer - > Host to host delivery, Logical address (IP), routing, congestion control, router works here, Unit - Packets.

- Transport Layer - > End to End delivery, TCP / UDP, Transmission Control Protocol (TCP) : Connection Oriented, No loss data, Inorder, User Datagram Protocol (UDP) is a communications
protocol that is primarily used to establish low-latency and loss-tolerating connections between applications on the internet.

- Session Layer - > The session layer allows users on different machines to establish sessions between them, authentication, session restoration, synchronization.

- Presentation Layer - > Ensures the data is in usable format and data encryption occurs here.

- Application Layer - > Human computer interaction where applications can access the network services.

- Software Layer : TSPA

- Hardware Layer : PDN

## Define TCP / IP Model ? 

- Application Layer -> Application layer interacts with an application program, which is the highest level of OSI model. The application layer is the OSI layer, which is closest to the end-user. Application-layer helps you to identify communication partners, determining resource availability, and synchronizing communication.

- Transport Layer -> Application-layer helps you to identify communication partners, determining resource availability, and synchronizing communication. Transport layer helps you to control the reliability of a link through flow control, error control, and segmentation or de-segmentation.

- Internet Layer -> Main work of this layer is to send the packets from any network, and any computer still they reach the destination irrespective of the route they take. Routing protocols Multicast group management Network-layer address assignment.

- Network Layer -> This layer is also called a network access layer. It helps you to defines details of how data should be sent using the network.
It also includes how bits should optically be signaled by hardware devices which directly interfaces with a network medium, like coaxial, optical, coaxial, fiber, or twisted-pair cables.

## HTTP and HTTPS protocol ?

HTTP protocol is a set of rules which are used for transferring data over the network WWW uses port 80. HTTPS enables secure transactions by encrypting the communication and also helps identify network servers securely. It uses port 443 by default

## SMTP ?

SMTP is the Simple Mail Transfer Protocol. SMTP sets the rule for communication between servers. This set of rules helps the software to
transmit emails over the internet. It supports both End-to-End and Store-and-Forward methods. It is in always-listening mode on port 25

## DNS ?

It is considered as the devices/services directory of the Internet. It is a decentralized and hierarchical naming system for devices/services
connected to the Internet. It translates the domain names to their corresponding IPs. For e.g. interviewbit.com to 172.217.166.36. It uses port
53 by default

## Router and Gateway ?

The router is a networking device used for connecting two or more network segments. It directs the traffic in the network. It transfers information and data like web pages, emails, images, videos, etc. from source to destination in the form of packets. It operates at the network layer. The gateways are also used to route and regulate the network traffic but, they can also send data between two dissimilar networks while a router can only send data to similar networks

## TCP ?

TCP or TCP/IP is the Transmission Control Protocol/Internet Protocol. It is a set of rules that decides how a computer connects to the
Internet and how to transmit the data over the network. It creates a virtual network when more than one computer is connected to the network and uses the three ways handshake model to establish the
connection which makes it more reliable.

## UDP ?

UDP is the User Datagram Protocol and is based on Datagrams. Mainly, it is used for multicasting and broadcasting. Its functionality is almost the same as TCP/IP Protocol except for the three ways of
handshaking and error checking. It uses a simple transmission without any hand-shaking which makes it less reliable.

## UDP ?

UDP is the User Datagram Protocol and is based on Datagrams. Mainly, it is used for multicasting and broadcasting. Its functionality is almost the same as TCP/IP Protocol except for the three ways of
handshaking and error checking. It uses a simple transmission without any hand-shaking which makes it less reliable.

## ARP ?

ARP is Address Resolution Protocol. It is a network-level protocol used to convert the logical address i.e. IP address to the device's physical address i.e. MAC address. It can also be used to get the MAC address of devices when they are trying to communicate over the local network.

## MAC and NIC ?

MAC address is the Media Access Control address. It is a 48-bit or 64-bit unique identifier of devices in the network. It is also called the
physical address embedded with a Network Interface Card (NIC) used at the Data Link Layer. NIC is a hardware component in the networking device using which a device can connect to the network.

## Subnetting ?

A subnet is a network inside a network achieved by the process called subnetting which helps divide a network into subnets. It is used for getting a higher routing efficiency and enhances the security of the network. It reduces the time to extract the host address from the routing table.

## Firewall ?

The firewall is a network security system that is used to monitor the incoming and outgoing traffic and blocks the same based on the firewall security policies. It acts as a wall between the internet (public network) and the networking devices (a private network).

## What happens when you search for google.com ?

- Check the browser cache first if the content is fresh and present in the cache display the same.

- If not, the browser checks if the IP of the URL is present in the cache (browser and OS) if not then requests the OS to do a DNS lookup using UDP to get the corresponding IP address of the URL from the DNS server to establish a new TCP connection.

- A new TCP connection is set between the browser and the server using three-way handshaking.

- An HTTP request is sent to the server using the TCP connection.

- The web servers running on the Servers handle the incoming HTTP request and send the HTTP response.

- The browser processes the HTTP response sent by the server and may close the TCP connection or reuse the same for future requests.

- If the response data is cacheable then browsers cache the same.

- Browser decodes the response and renders the content.

## What does network topology mean ?

Network topology is the arrangement of networks and cables in different forms such as star, bus, mesh, ring and tree etc.

## What are nodes and links ?

Any communicating device in a network is called a Node. Node is the point of intersection in a network. It can send/receive data and information within a network. And links are any edge b/w nodes used for transmission of data

## What does VPN mean and mention its advantages ?

VPN means virtual private network, it creates an encrypted secure tunnel for your data and it hides your IP address over a network. VPN client first validates your request then encapsulates it in a wrapper, encrypts it then the data is transferred, then it is decrypted at your side. It creates a private WAN. By using the VPN, a client can connect to the organization’s network remotely.

- VPN is used to connect very far geographical locations offices at lower cost.

- It is used for secure transactions.

- Encrypts internet traffic and disguise the identity.

##  Difference types of network includes : -

- LAN (Wireless and Wired) - > Lab, Company

- WAN - > Large geographical area Country / Continent

- MAN - > TV cable over a city

- PAN -> Bluetooth

- GAN - > Connects the globe

## What is an IPv4 address, and what are its different classes ?

IPv4 addresses are 32 bit numbers, which are divided into 4 segments of 8 bits. With each number value upto 255. There are total A, B, C, D, E classes of IPv4. 0 - 127, 128 - 191, 192 - 223, 224 - 239, 240 - 255.

## Ports ? 

In computer networking, a port or port number is a number assigned to uniquely identify a connection endpoint and to direct data to a specific service1. At the software level, within an operating system, a port is a logical construct that identifies a specific process or a type of network service1. Ports are software-based and managed by a computer’s operating system. Each port is associated with a specific process or service2. Ports allow computers to easily differentiate between different kinds of traffic: emails go to a different port than webpages, for instance, even though both reach a computer over the same Internet connection2.

In computer networking, the total number of ports that can be created is 65,5361234. This is because a port number is a 16-bit unsigned integer, thus ranging from 0 to 655351. However, it’s important to note that not all of these ports are available for general use5. Here’s how they’re typically divided:

- Well-known ports: 0 to 1023. These are used for system services like HTTP, FTP, SSH, DHCP, etc5.

- Registered ports: 1024 to 49151. These are assigned by ICANN to certain uses1.

- Dynamic or private ports: 49152 to 65535. These are available for general use by applications and are known as ephemeral ports5.