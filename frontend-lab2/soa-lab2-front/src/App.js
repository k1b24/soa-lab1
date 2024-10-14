import './App.css';
import Template from "./table/Template";
import {useState} from "react";

function App() {

    const [hiddenAlert, setHiddenAlert] = useState(true)
    const [message, setAlertMessage] = useState("")

    const alertWithMessage = (text) => {
        setAlertMessage(text)
        setHiddenAlert(false)
    }

    return (
        <div className="App">
            <Template alertWithMessage={alertWithMessage}/>

            <div className="alert shadow-lg" hidden={hiddenAlert}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                     className="stroke-info shrink-0 w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <div>
                    <h3 className="font-bold"><input value={message}/></h3>
                    <div className="text-xs"/>
                </div>
                <button className="btn btn-sm" onClick={_ => setHiddenAlert(true)}>OK</button>
            </div>
        </div>
    );
}

export default App;
