//variables that manipulate the dom
let generateButton = document.getElementById('joke-button-div');
let generatedJoke = document.getElementById('generated-joke-div');
let loadingWheel = document.getElementById('loading-wheel-img');
let jokeTypeSelect = document.getElementById('joke-type-selection')
let jokeTypeValue = jokeTypeSelect.value; //value for the type of joke
let audioEffect = document.getElementById('homer-sound-effect');
let playAudio = document.getElementById('play-audio-option');
let peterPhoto = document.getElementById('peter-photo');
let homerPhoto = document.getElementById('homer-photo');
const peterVoicePath = "../static/audio/peter_sound_effect.mp3";
const homerVoicePath = "../static/audio/homer_sound_effect.mp3";
jokeTypeSelect.addEventListener('change', function() {
    //update the selected value
    jokeTypeValue = jokeTypeSelect.value;
    console.log(jokeTypeValue);
});
generateButton.addEventListener('click', playVoice);

function attachListeners() {
    generateButton = document.getElementById('joke-button-div');
    generatedJoke = document.getElementById('generated-joke-div');
    audioEffect = document.getElementById('homer-sound-effect');
    generateButton.removeEventListener('click', generateJoke);
    generateButton.addEventListener('click', generateJoke);
}
let dadImages = document.getElementsByClassName('dad-img');
Array.from(dadImages).forEach(function(dadImage) {
    dadImage.addEventListener('click', routeVoice);
});
function routeVoice() {
    if (this.id === 'peter-photo') {
        selectVoice(peterPhoto);
        audioEffect.src = getDadPath(peterPhoto);
        removeVoice(homerPhoto);
    } else if (this.id === 'homer-photo') {
        selectVoice(homerPhoto);
        audioEffect.src = getDadPath(homerPhoto);
        removeVoice(peterPhoto);
    }
}
function selectVoice(dadVoice) {
    dadVoice.classList.add('selected-dad');
}
function removeVoice(dadVoice) {
    dadVoice.classList.remove('selected-dad');
}
function playVoice() {
    if (playAudio.checked) {
        console.log("Playing Homer Audio");
        audioEffect.play();
    }
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

function setDadPath(dadPath) {
    
}
function getDadPath(dad) {
    let returnedPath = "";
    let request = new XMLHttpRequest();
    request.open('POST', `/get-path/${dad.src}`, true);
    request.onload = function() {
        returnedPath = request.responseText;
    }
    request.send();
    return returnedPath;
}

//attach event listeners on load
// attachListeners();