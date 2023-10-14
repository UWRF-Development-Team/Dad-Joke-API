#This file obtains the api key, connects to the open ai api, and prints the specified response

from dotenv import load_dotenv #protect api key in .env file
import os
import re #for regular expressions
import openai #used to call/use methods from openai instead of using an http request
load_dotenv()
openai.api_key = os.getenv("APIKEY") #specify the api key for openai object


#complete object stores the response specified by parameters such as 'model' and 'messages
complete = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "you are a dad who makes corny dad jokes"},
        {"role": "user", "content": "Generate a dad joke that starts with 'Joke:', and if there is a punchline, 'Punchline:'."}
        ]
)
responseList = [] #store the 2 responses (joke and punchline) in this list

try: #make sure a response is recieved, and store it in a variable upon success
    aiResponse = complete.choices[0].message
except:
   print("Unable to retrieve response")

# aiResponse = {
#   "role": "assistant",
#   "content": "Joke: Why don't eggs tell jokes?\nPunchline: Because they might crack up!"
# }
print("response type: ", type(aiResponse))
print(aiResponse)

# myJoke = re.split("Joke:", str(aiResponse))
# myPunch = re.split("Punchline:", str(aiResponse))

#print(myList[1],'\n', myList[2], '\n', myList[3], '\n', myList[4])
# joke = re.search("^Joke*\n$", str(myList[3]))
# punchline = re.search("^Punchline*", str(myList[4]))
#print (myJoke[1], '\n', myPunch[1])

# print(joke, "\n", punchline)

