//logic for the application that calls the api on the backend and displays the response 
//on the page whenever the user clicks the button

button = document.getElementById('generateJoke');
displayJoke = document.getElementById('displayJoke');

button.addEventListener('click', handleClick);

async function handleClick() {
    let joke = await fetchData();
    displayJoke.textContent = JSON.stringify(joke);
   // showJoke(joke);
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

 
