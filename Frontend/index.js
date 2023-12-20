//logic for the application that calls the api on the backend and displays the response 
//on the page whenever the user clicks the button
spinner = document.getElementById('spinner');
button = document.getElementById('generateJoke');
displayJoke = document.getElementById('displayJoke');

button.addEventListener('click', handleClick);

async function handleClick() {
    displayJoke.textContent = "";
    spinner.classList.remove('d-none'); //show the spinner
    let joke = await fetchData();
    //displayJoke.textContent = JSON.stringify(joke);
    // funnyJoke = "Joke: Why can't you trust atoms?\n\nPunchline:Because they make up everything";
    let cleanJoke = await splitJokeObject(joke);
    spinner.classList.add('d-none'); //hide the spinner
     displayJoke.textContent = cleanJoke.joke;
     //displayJoke.textContent = Joke.punchline === ''? Joke.joke: Joke.joke + '? ' + Joke.punchline;
}

async function fetchData(){
    let url = 'http://127.0.0.1:5000/api/v1/getJoke';
    let response = await fetch(url, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    }).catch(error => {console.error("Error:", error)}) //fetched data here

    if(response.ok) {
        let joke = await response.json();

        return joke;
    }
    else {
        throw new Error("Error fetching data: " + `${response.status}`);
    }
}

let splitJokeObject = jokeStr =>{
    //jokeStr = "Joke: Why can't you trust atoms?\n\nPunchline:Because they make up everything";
    Joke = {joke: ""};
    
    let cleanString = jokeStr.replace('\n', ' ');
    cleanString = cleanString.replace('\n', ''); //get rid of all possible newline declarators
    cleanString =  cleanString.replace('Joke: ', '');
    Joke.joke = cleanString.replace('Punchline:', '');

    return Joke;
}

 
