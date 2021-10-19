# RaspberryPi-Project
### This Program will allow users to send out a DMX Signal via a Webinterface, to control several DMX-Lights very easy.
###
#### NOTE: This Project is still under development and not finished!
##
If you want to try it yourself, you need to install Pi4J on your Unbuntu running RaspberryPi

Install Pi4J: `curl -sSL https://pi4j.com/install | sudo bash`  (more information: https://pi4j.com/1.2/install.html)

If you get this Error when starting `Pi4J SEVERE: Unable to load [libpi4j] using path: [/lib/raspberrypi/dynamic/libpi4j-aarch64.so]`
try following this steps: 

```
sudo apt-get remove wiringpi -y
sudo apt-get --yes install git-core gcc make
cd ~
git clone https://github.com/WiringPi/WiringPi --branch master --single-branch wiringpi
cd ~/wiringpi
sudo ./build
```
More Information about possible Errors can be found [here](https://github.com/Pi4J/pi4j-v1). 
