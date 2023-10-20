#This file obtains the api key, connects to the open ai api, and prints the specified response
from CallAI import CallAI
from flask import Flask, jsonify
from flask_cors import CORS, cross_origin
app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


@app.route('/api/v1/getJoke', methods=['GET'])
@cross_origin()
def getJoke():
    respObj = CallAI()
    response = respObj.callAI()
    responseBody = respObj.validate(response)
    # temp = "Joke: Why don't scientists trust atoms?\n\nPunchline: Because they make up everything!"
    return jsonify(responseBody) #return joke

app.run(debug=True)
#start the application

    # return jsonify({"message": "This is a response from the server"})
