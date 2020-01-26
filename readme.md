# 22-Tron / SSH-Game

## How does it work?
### Technologies used
* [Apache MINA](https://mina.apache.org/) / [Apache SSHD](http://mina.apache.org/sshd-project/): A Java library for programmatically creating an ssh daemon.
* [Java](https://www.java.com/en/): Great language for write-once-run-anywhere Software 
* [Netty](https://netty.io/): Nice library for writing client/server network applications.

### Platforms used/tested
* [Eclipse](https://www.eclipse.org/): Written and compiled in Eclipse.
* [Debian Buster](https://www.debian.org/): Great distro for servers.



## How to play
Just type the following into any shell of your choice (provided you've got SSH installed)

```sh
ssh tron@<IP> -p<port>
```
Insert the IP and port of where you're hosting (Default port is 8052)

There can be at most 5 players in a session, if there ever are more than that it'll just dynamically create new "lobbies"

**CAUTION: THERE WILL BE BUGS AND IT MAY CRASH SOMETIMES**

## Demo Server
If you want to, you can try the game at
```sh
ssh tron@clown.institute -p8052
```

There is no guarantee that this server will be online 24/7 or that it will even work, it's just a server we test the game on. :)

## Screenshots 
<p align="center">
<img alt="22-Tron Menu Image" src="https://user-images.githubusercontent.com/54983399/73138477-34ccd300-4063-11ea-82c8-a7f334e35c65.png"/>
</p>
<p align="center">
<img alt="22-Tron Gameplay Image" src="https://user-images.githubusercontent.com/54983399/73138543-10252b00-4064-11ea-94e0-75a5dab60859.png"/>
</p>

## Credits
This has been heavily inspired by zachlatta's [SSH TRON](https://github.com/zachlatta/sshtron/blob/master/README.md), this project really is just a way for us gain some experience with Java

## License
Licensed under BSD. For full license see: [`LICENSE`](LICENSE).
