const express = require('express');
const webpush = require('web-push');
const cors = require('cors');
const bodyParser = require('body-parser');
const got = require('got');

const PUBLIC_VAPID = 'BI5mCqcxTLbVlaMXdtNgzuzvguutX4CW8SJ8qotChykJCs_9hBVlPfO-ccr4O6APBDCfe3DCICo8r-NVcR_tfrM';
const PRIVATE_VAPID = 'b5AwMS0KywWpy0Lft2YPtzfXiOAO8SK9qPvRO6KamsQ';
const request = require('request');
const app = express();
const allSubscriptions = [];
app.use(cors());
app.use(bodyParser.json());

webpush.setVapidDetails('mailto:you@domain.com', PUBLIC_VAPID, PRIVATE_VAPID);

app.get('/getAll', (req, res) => {
	
	request('http://localhost:8080/web-push/getAllWebSubscriptions', function(error, response, body) {
		console.log('statusCode: ', response && response.statusCode);
		console.log('allSubscriptionsBefore: ', allSubscriptions.length);
			
		const temp = JSON.parse(body);
		
		temp.forEach(subscription => {
		allSubscriptions.push(subscription);
		console.log('subscription added');
		});
		
		console.log('allSubscriptionsAfter: ', allSubscriptions);
	});
	res.sendStatus(200);
 });

app.post('/sendNotification', (req, res) => {

 const notificationPayload = {
    notification: {
      title: 'New Event',
      body: 'Check out event list for new events!',
      icon: 'assets/icons/notification.png'
    }
  };

	const promises = [];
	allSubscriptions.forEach(subscription => {
    promises.push(webpush.sendNotification(subscription, JSON.stringify(notificationPayload)));
  });
  Promise.all(promises).then(() => res.sendStatus(200));
	while(allSubscriptions.length > 0) {
	allSubscriptions.pop();
	}
});

app.listen(3000, () => {
  console.log('Server started on port 3000');
});