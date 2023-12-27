let generateButton = document.getElementById('joke-button-div');
let generatedJoke = document.getElementById('generated-joke-div');
let loadingWheel = document.getElementById('loading-wheel-img');
const defaultButton = generateButton.outerHTML;
generateButton.addEventListener('click', generateJoke);
function attachListeners() {
    generateButton = document.getElementById('joke-button-div');
    generatedJoke = document.getElementById('generated-joke-div');
    loadingWheel = document.getElementById('loading-wheel-img');
    
    generateButton.removeEventListener('click', generateJoke);
    generateButton.addEventListener('click', generateJoke);
}
function generateJoke() {
    generateButton.style.display = 'none';
    loadingWheel.style.display = 'block';
    let request = new XMLHttpRequest();
    request.open('GET', '/generate', true);
    request.onload = function () {
        let fragResponse = request.responseText;
        generatedJoke.outerHTML = fragResponse;
        generateButton.style.display = 'block';
        loadingWheel.style.display = 'none';
        attachListeners();
    }
    request.send();
    

}
attachListeners();