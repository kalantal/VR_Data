##For running on Windows with VirtualBox
---
Start a hotspot if internet connection is spotty

1. Open the Docker Quickstart Terminal
Run the following command and see if the virtual-box-memory is greater than a 1GB for your default VM

	```
	docker-machine inspect
	```

2. If the VM has less than 2GB of memory, stop the machine and reallocate 8GB of memory
	```
    docker-machine stop default
    docker-machine create -d virtualbox --virtualbox-memory 8192 default
    docker-machine start default
	```
3. Set the virtual machine map count for elasticsearch to work properly
	```
	docker-machine ssh default
	sudo sysctl -w vm.max_map_count=262144
    exit
	```
4. cd into the compose file then run
	```
	docker-compose up
	```
5. Map the docker-machine port to localhost by running
	```
	netsh interface portproxy add v4tov4 listenaddress=127.0.0.1 listenport=8010 connectaddress=192.168.99.100 connectport=8010
	```

---

When running a new instance, it is good practice to remove any of the previous containers compose created prior.

	
	docker-compose rm
	
	

