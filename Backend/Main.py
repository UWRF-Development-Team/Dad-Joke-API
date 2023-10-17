#This file obtains the api key, connects to the open ai api, and prints the specified response
from CallAI import CallAI
from flask import Flask, jsonify
app = Flask(__name__)

@app.route("/api/v1/getJoke")
def getJoke():
    respObj = CallAI()
    response = respObj.validate(respObj.callAI)
    return jsonify(response) #return joke

app.run()
#start the application

