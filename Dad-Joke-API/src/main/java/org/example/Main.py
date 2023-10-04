#This file obtains the api key, connects to open ai api, and prints the specified response

from dotenv import load_dotenv
import os
import openai
load_dotenv()
openai.api_key = os.getenv("APIKEY")

complete = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "you are a helpful assistant!"},
        {"role": "user", "content": "Hello!"}
        ]
)

try:
    print(complete.choices[0].message)
except:
   print("Unable to retrieve response")


