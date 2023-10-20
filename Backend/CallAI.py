#This file obtains the api key and collects a response

from dotenv import load_dotenv #protect api key in .env file
import os
import re #for regular expressions
import openai #used to call/use methods from openai instead of using an http request
load_dotenv()
openai.api_key = os.getenv("APIKEY") #specify the api key for openai object

class CallAI:

    # def __init__(self):
    #    self.name = "Default constructor"
#call the model and return the response
    def callAI(self):
        return  openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "you are a dad who makes corny dad jokes"},
                {"role": "user", "content": "Generate a dad joke that starts with 'Joke:',\
                  and if there is a punchline, 'Punchline:'"}
                ]
        )

    def validate(self, respObject):
       try: #make sure a response is recieved, and store it in a variable upon success
          responseContent = respObject.choices[0].message.content
       except:
          responseContent = "error!"
       return responseContent

    # class main:
    # flag = True
    # while flag:
    #     print("Would you like to hear a dad joke?\n")
    #     response = input("y or n:")
    #     if response == "y" or response == "Y":
    #        aiResponse = validate(callAI())
    #        responseList = re.split("\n\n", aiResponse)
    #        for line in responseList:
    #           print("\n",line)
    #     else:
    #         flag = False
    # print("We hope you enjoyed those Dad Jokes!")
