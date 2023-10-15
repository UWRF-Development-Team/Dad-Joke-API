#This file obtains the api key, connects to the open ai api, and prints the specified response

from dotenv import load_dotenv #protect api key in .env file
import os
import re #for regular expressions
import openai #used to call/use methods from openai instead of using an http request
load_dotenv()
openai.api_key = os.getenv("APIKEY") #specify the api key for openai object


#complete object stores the response specified by parameters such as 'model' and 'messages
def callAI():
    return  openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "you are a dad who makes corny dad jokes"},
            {"role": "user", "content": "Generate a dad joke that starts with 'Joke:', and if there is a punchline, 'Punchline:'."}
            ]
    )

def validate(respObject):
        try: #make sure a response is recieved, and store it in a variable upon success
           return respObject.choices[0].message.content
        except:
            return "Unable to retrieve response"



class main:
    flag = True
    while flag:
        print("Would you like to hear a dad joke?\n")
        response = input("y or n:")
        if response == "y" or response == "Y":
           aiResponse = validate(callAI())
           responseList = re.split("\n\n", aiResponse)
           for line in responseList:
              print("\n",line)
        else:
            flag = False
    print("We hope you enjoyed those Dad Jokes!")





