import './App.css';
import Template from "./table/Template";
import {useState} from "react";
import background from "./images/backgroundImg.jpg"

function App() {



    return (
        <div className="App" style={{ backgroundImage: `url(${background})` }}>
            <Template/>
        </div>
    );
}

export default App;
