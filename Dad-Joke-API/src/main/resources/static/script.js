//variables that manipulate the dom
let generateButton = document.getElementById('joke-button-div');
let generatedJoke = document.getElementById('generated-joke-div');
let loadingWheel = document.getElementById('loading-wheel-img');

let attachListeners = () =>{
    generateButton.removeEventListener('click', generateJoke);
    generateButton.addEventListener('click', generateJoke);
}
let generateJoke = () => {
    //hide the button and show the "loading" wheel
    generateButton.style.display = 'none';
    loadingWheel.style.display = 'block';
    //set up request for api that displays at the correct location on
    //the page upon successful retrieval
    let request = new XMLHttpRequest();
    request.open('GET', '/generate', true);
    request.onload = () =>{
        let fragResponse = request.responseText;
        generatedJoke.outerHTML = fragResponse;
        generateButton.style.display = 'block';
        loadingWheel.style.display = 'none';
        attachListeners();//make sure user can receive multiple jokes
    }
    //send request
    request.send();
    

}
//attach event listeners on load
attachListeners();