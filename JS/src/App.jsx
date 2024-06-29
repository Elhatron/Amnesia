import { useState } from 'react'
import './App.css'

function App() {

  function putMessageToTheBox(user, message){
    let box = document.querySelector('#CorrespondentionContext');
    
    const node = document.createElement("p");
    const textnode = document.createTextNode(user + ": " +message);
    node.appendChild(textnode);
    box.appendChild(node);
  }

  function errorMessageToTheBox(message){
    let box = document.querySelector('#CorrespondentionContext');
    
    const node = document.createElement("p");
    const textnode = document.createTextNode(message);
    node.appendChild(textnode);
    box.appendChild(node);
  }

  async function getJSON(){
    let sender = document.querySelector('#sender');

    if(sender.value!=""){
      const requestOptions = {
        method: "GET",
        redirect: "follow"
      };
      
      let obj = [];

      fetch("http://localhost:3031/messages/getMessagesByReciver/"+sender.value, requestOptions)
        .then((response) => response.text())
        .then((result) => {
          obj=JSON.parse(result);
          for ( let i = 0; i < obj.length; i++){
            putMessageToTheBox(obj[i].sender,obj[i].message);
          }
        })
        .catch((error) => console.error(error));
    }
  }

  async function sendJSON(){
    let sender = document.querySelector('#sender');
    let reciver = document.querySelector('#reciver');
    let message = document.querySelector('#message');

    if((message.value!="")&&(sender.value!="")&&(reciver.value!="")){
        const raw = JSON.stringify({
            "sender": sender.value,
            "reciver": reciver.value,
            "message": message.value
        });

        let obj = [];

        await fetch("http://127.0.0.1:3031/messages/reciveMessage", {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "include", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Methods": "GET, POST, PUT"
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: raw, // body data type must match "Content-Type" header
        })
        .then((response) => response.text())
        .then((result) => {
          obj=JSON.parse(result);
          if(obj.errorCode<1){
            putMessageToTheBox(sender.value,message.value);
            message.value="";
          } else {
            switch (obj.errorCode) {
              case 1 : 
              errorMessageToTheBox("Błąd ogólny serwera");
                break;
              case 2 :
                errorMessageToTheBox("Wiadomość za długa. Max 256 znaków");
                break;
            }
          }
        })
        .catch((error) => console.error(error));
    }
  }

  function clearBox() {
    let box = document.querySelector('#CorrespondentionContext');

    box.innerHTML="";
  }

  document.title="Amnesia Client";
  
  document.body.onload = function(){
    document.getElementById('message').addEventListener('keydown', function(event) {
      if (event.key === 'Enter') {
        sendJSON();
      }
    });
    setInterval(getJSON, 1000);  
  };
  
  return (
    <>
      <h1>Amnesia Client</h1>
      <div className="card">
        <input type="text" id="sender" placeholder="Your name"/>
		    <input type="text" id="reciver" placeholder="Reciver name"/><br></br>
        <input type="text" id="message" placeholder="Put some text here to send"/><br></br>
        <button onClick={sendJSON}>
          Send
        </button>
        <button onClick={clearBox}>
          Clear
        </button>
        <p>
        </p>
      </div>
      <div id="CorrespondentionContext" className="card">
      </div>
    </>
  )
}

export default App
