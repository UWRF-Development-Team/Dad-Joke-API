//variables that manipulate the dom
let generateButton = document.getElementById('joke-button-div');
let generatedJoke = document.getElementById('generated-joke-div');
let loadingWheel = document.getElementById('loading-wheel-img');
let jokeTypeSelect = document.getElementById('joke-type-selection')
let jokeTypeValue = jokeTypeSelect.value; //value for the type of joke
jokeTypeSelect.addEventListener('change', function() {
    //update the selected value
    jokeTypeValue = jokeTypeSelect.value;
    console.log(jokeTypeValue);
});
function attachListeners() {
    generateButton = document.getElementById('joke-button-div');
    generatedJoke = document.getElementById('generated-joke-div');
    generateButton.removeEventListener('click', generateJoke);
    generateButton.addEventListener('click', generateJoke)
}
function generateJoke() {
    attachListeners();
    //hide the button and show the "loading" wheel
    generateButton.style.display = 'none';
    loadingWheel.style.display = 'block';
    //set up request for api that displays at the correct location on
    //the page upon successful retrieval
    let request = new XMLHttpRequest();
    request.open('POST', `/generate/${jokeTypeValue}`, true);
    request.onload = function() {
        console.log("look mom, I did it!");
        console.log("I have reached the onload function");
        let fragResponse = request.responseText;
        generatedJoke.outerHTML = fragResponse;
        generateButton.style.display = 'block';
        loadingWheel.style.display = 'none';
        // attachListeners();//make sure user can receive multiple jokes
    }
    //send request
    request.send();
}
generateButton.addEventListener('click', generateJoke);
//attach event listeners on load
// attachListeners();