//logic for the application that calls the api on the backend and displays the response 
//on the page whenever the user clicks the button

button = document.getElementById('generateJoke');
displayJoke = document.getElementById('displayJoke');

button.addEventListener('click', ()=>{
    displayJoke.textContent="Dad Joke here!!!";
})
