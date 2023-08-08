run:
	docker run -d -p 8081:8081 --name chesu backend
run-dev:
	docker run -d -p 8081:8081 -v "X:\rozoomcool\Documents\Projects\Java\Spring\chguevent\chguevent:/app" --rm --name chesudev backend
stop-dev:
	docker stop chesudev
stop:
	docker stop chesu