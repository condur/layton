# layton

A sample clojure project for testing Transport for London APIs

This is a basic web site built in Clojure that is providing a HTML and a JSON data showing 5 bike points occupancy around Layton. The web site is using Transport for London API: https://api.tfl.gov.uk/

### Credentials

It is required to register and get Transport for London credentials, then create in the root folder the .boot-env file, based on .boot-env.example, and fill the application id and key there.

### Run the application

To run the project excute in the command line:

```
boot run
```

Open the browser: http://localhost:3000/

Index page already contains the Layton coordinates and a radius that will return the closest 5 bike points.

### API

Optionaly to get the same information is possible by using the following API call, with default values: http://localhost:3000/api/bike-point?lat=51.556638&lon=-0.005575&radius=1550

__TODO__: API is configures to use Basic authentication, but unfortunately is not working at the moment. The issue was documented here: https://github.com/juxt/yada/issues/27 and is still open.

### TODO

This is a sample project so there are a lot of things missings or that can be improved.

* Make the Transport for London API call async
* Add unit tests
* Add validation
* Handle exceptions
* Improve the UI (add login, session, google maps ...)
* Get in the background the coordinates for searched location
* Calculate/recommend the optimal radius for search
* ...

### Project structure

* core - main/starting file, define middleware settings using app routes and run the HTTP Server on specific port
* routes - the application routes, using bidi library
* resources - the resources used in the application, using yada library
* responses - the business logic for generating the required responses
* transport-for-london - integration with Transport for London API
* system - a wrapper for getting system variables
