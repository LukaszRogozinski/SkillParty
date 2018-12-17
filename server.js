const express = require('express');
const webpush = require('web-push');
const cors = require('cors');
const bodyParser = require('body-parser');


const PUBLIC_VAPID = 'BI5mCqcxTLbVlaMXdtNgzuzvguutX4CW8SJ8qotChykJCs_9hBVlPfO-ccr4O6APBDCfe3DCICo8r-NVcR_tfrM';
const PRIVATE_VAPID = 'b5AwMS0KywWpy0Lft2YPtzfXiOAO8SK9qPvRO6KamsQ';
const fakeDatabase = [];
let subscriptions = [];
const request = require('request');
const app = express();
var http = require('http');

app.use(cors());
app.use(bodyParser.json());

webpush.setVapidDetails('mailto:you@domain.com', PUBLIC_VAPID, PRIVATE_VAPID);


app.post('/subscription', (req, res) => {
  const subscription = req.body;
  fakeDatabase.push(subscription);
});

app.get('/getAll', function(req, res) {
	http.request('http://localhost:8080/web-push/getAllWebSubscriptions', function(response) {
		subscriptions = response.pipe(res);
		console.log(subscriptions);
	}).on('error', function(e) {
		res.sendStatus(500);
	}).end();
});


app.post('/sendNotification', (req, res) => {
  const notificationPayload = {
    notification: {
      title: 'New Notification',
      body: 'This is the body of the notification',
      icon: 'assets/icons/icon-512x512.png'
    }
  };

  const promises = [];
  fakeDatabase.forEach(subscription => {
    promises.push(webpush.sendNotification(subscription, JSON.stringify(notificationPayload)));
  });
  Promise.all(promises).then(() => res.sendStatus(200));
});

app.listen(3000, () => {
  console.log('Server started on port 3000');
});