# SimpleTwitterApp
Uses Simple Application Authentication App for Twitter
<br>Simple Twitter app for getting tweets and search rest call of twiiter api
<h2>Steps for Authentication with Twitter Application</h2>
<li>Create an app on twitter developer account <a href="https://apps.twitter.com/">Create Twitter App</a></li>
<li>Get Consumer Key (API Key)	Some key value
Consumer Secret (API Secret)	Some Secret Key Value</li>
<li>After getting key you require Bearer Key for Calling Rest Api of Twiiter <a href="#">https://api.twitter.com/oauth2/token
</a><br>Call this url from your android and you will get response as {"token_type":"bearer","access_token":"AAAA%2FAAA%3DAAAAAAAA"}
<br>For more information <a href="https://dev.twitter.com/oauth/reference/post/oauth2/token">post/oauth2/token</a></li>
<li>For getting usertimeline tweets json data call this url from android and pass bearer key as header <br>
Authorization: Bearer AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%2FAAAAAAAAAAAA
                      AAAAAAAA%3DAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA<br>You will get response decode it and display it.
</li>
<li>For search tweets by hashtag you have to call <a href=#">https://api.twitter.com/1.1/search/tweets.json"+"?q="+URLEncoder.encode("#"+query,"UTF-8")+"&result_type=recent</a> this rest api from android
and in header bearer key <br>For detailed <a href="https://dev.twitter.com/oauth/application-only">Application Only</a></li>
<h3>First Screen</h3>
<p>In this screen the user timeline tweets are shown.</p> 
<img src="http://s16.postimg.org/u7ab6nzad/device_2016_02_03_192956.png"/>
<br/><br/>
<h3>Second Screen</h3>
<p>The searchable hashtag value is entered</p>
<img src="http://s27.postimg.org/vt5lz872r/device_2016_02_03_193017.png"/>
<br/><br/>
<h3>Third Screen</h3>
<p>In this screen it shows tweets searched for above #searchvalue shows tweets </p>
<img src="http://s16.postimg.org/nihjvvi3p/device_2016_02_03_193109.png"/>
<br>



