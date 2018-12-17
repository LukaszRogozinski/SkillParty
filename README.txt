to run app using websocket use follow steps:
1) open cmd in "client" folder and run "ng build --prod"
2) after that, open cmd in "../dist/client" folder and run http-server -p <number of port>

if npm don't see @angular/service-worker run "ng add @angular/pwa" command in cmd